# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Invoke-DBSeed.ps1 -BuildWorkingDir "C:\Repositories\surveyor-qa" `
#       -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#       -ArtifactoryBaseUrl "http://picarro.artifactoryonline.com/picarro" `
#       -ArtifactoryAPIKey "AKCp2VoGPeRruh1fKTMx8K99yceZRay15wBQHHuoFoDuAgib16cSrM8VuKaTtjznPeEC9QXGL" `   # NOTE: Not valid key. Replace with a valid API key.
#       -ArtifactoryRepository "picarro-generic-private-qa" `
#       -ArtifactoryFolder "2.4" `
#       -BinaryVersionToUse "2.4.0.5a4erf2" `   
#       -EnvironmentID 1

param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Path to working directory (for eg. C:\Repositories\surveyor)

  [Parameter(Mandatory=$true)]
  [String] $AutomationReportingAPIBaseUrl,         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryBaseUrl,                    # Artifactory Base Url. For eg. http://picarro.artifactoryonline.com/picarro

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryAPIKey,                     # Artifactory API Key. 

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryRepository,

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryFolder,

  [Parameter(Mandatory=$true)]
  [int] $EnvironmentID,

  [Parameter(Mandatory=$false)]
  [String] $BinaryVersionToUse                     # If NOT specified latest version will be searched and used.
)

# 1.
# Prepare destination folder.
$qaBinariesDestFolder = "$BuildWorkingDir\dbseedtests"
$seleniumWdFolder = "$BuildWorkingDir\selenium-wd"
if (-not (Test-Path $qaBinariesDestFolder)) {
    New-item -ItemType Directory $qaBinariesDestFolder
}

if ($PSBoundParameters.ContainsKey("BinaryVersionToUse")) {
    $version = $BinaryVersionToUse
}

$dbSeedJarFile = ""
$dbSeedDepZipFile = ""

# 2.
# Download the specified JAR and dependencies from Artifactory.
$Headers = @{
    "X-JFrog-Art-Api" = $ArtifactoryAPIKey
}

$JARPrefix = "dbseed"
$ZIPPrefix = "selenium-wd"
$ManifestPrefix = "manifest"

$aql = "items.find     " + 
        "(                " + 
        "    {" + 
        "        ""repo"": {""`$eq"":""$ArtifactoryRepository""}," + 
        "        ""`$or"":[{""name"": {""`$match"":""$JARPrefix*.jar""}, ""name"": {""`$match"":""$ZIPPrefix*.zip""}}]," +
        "        ""modified"" : {""`$last"" : ""4w""}" + 
        "    }" + 
        ")" + 
        ".sort({""`$desc"" : [""modified""]})" + 
        ".limit(100)"


$uri = "$ArtifactoryBaseUrl/api/search/aql"
Write-Host "INVOKE Uri -> $uri"
$response = Invoke-RestMethod -Uri $uri -Headers $Headers -Method POST -Body $aql -ContentType "text/plain" -SessionVariable wSession

if ($version -eq $null -or $version -eq "") {
    # version NOT specified. Find the most latest version.
    $largestVersion = ""
    [int] $largestBUildNum = -1
    $response.results.name | % {
        [string]$name = $_
        if ($name.StartsWith($JARPrefix)) {
            [int] $currBuildNum = [int]($name.Split(".")[2])
            if ($currBuildNum -gt $largestBUildNum) {
                $largestBUildNum = $currBuildNum
                $largestVersion = $name.Replace("$JARPrefix-", "").Replace(".jar", "")
            }
        }
    }
    $version = $largestVersion
}

$dbSeedJarFile = "$JARPrefix-$version.jar"
$dbSeedDepZipFile = "$ZIPPrefix-$version.zip"
$dbSeedDepManifestFile = "$ManifestPrefix-$version.xml"

$jarDownloadPath = "$qaBinariesDestFolder\$dbSeedJarFile"
$zipDownloadPath = "$qaBinariesDestFolder\$dbSeedDepZipFile"
$manifestDownloadPath = "$qaBinariesDestFolder\$dbSeedDepManifestFile"

$downloadURL = "$ArtifactoryBaseUrl/$ArtifactoryRepository/$ArtifactoryFolder/$dbSeedJarFile"
"Start downloading JAR file from->[$downloadURL], to->[$jarDownloadPath]"
Invoke-WebRequest -Uri $downloadURL -WebSession $wSession -OutFile $jarDownloadPath
"DONE downloading JAR file."

$downloadURL = "$ArtifactoryBaseUrl/$ArtifactoryRepository/$ArtifactoryFolder/$dbSeedDepZipFile"
"Start downloading ZIP file from->[$downloadURL], to->[$zipDownloadPath]"
Invoke-WebRequest -Uri $downloadURL -WebSession $wSession -OutFile $zipDownloadPath
"DONE downloading ZIP file."

$downloadURL = "$ArtifactoryBaseUrl/$ArtifactoryRepository/$ArtifactoryFolder/$dbSeedDepManifestFile"
"Start downloading Manifest file from->[$downloadURL], to->[$manifestDownloadPath]"
Invoke-WebRequest -Uri $downloadURL -WebSession $wSession -OutFile $manifestDownloadPath
"DONE downloading Manifest file."


# 3.
# Unzip the Archive zip to BINARIESDEST\selenium-wd.
$sourceArchiveFileName = $zipDownloadPath
$destinationDirectoryName = "$qaBinariesDestFolder\selenium-wd"
if (-not (Test-Path $destinationDirectoryName)) {
    New-item -ItemType Directory $destinationDirectoryName
} else {
    Remove-Item $destinationDirectoryName -Recurse -Force
}

Add-Type -AssemblyName "System.IO.Compression.FileSystem" 
[System.IO.Compression.ZipFile]::ExtractToDirectory($sourceArchiveFileName, $destinationDirectoryName)

# 4.
# Download additional dependencies
$additionalDeps = @{
    "selenium-server-standalone-2.53.1.jar" = "http://selenium-release.storage.googleapis.com/2.53/selenium-server-standalone-2.53.1.jar"
    "chromedriver_win32.zip" = "https://chromedriver.storage.googleapis.com/2.26/chromedriver_win32.zip"
}

$destinationDirectoryName = "$qaBinariesDestFolder\selenium-wd\lib"
$additionalDeps.Keys | % {
    [string]$dep = $_
    $downloadURL = $additionalDeps.get_item($dep)
    $downloadFilePath = "$destinationDirectoryName\$dep"
    "Start downloading file from->[$downloadURL], to->[$downloadFilePath]"
    Invoke-WebRequest -Uri $downloadURL -WebSession $wSession -OutFile $downloadFilePath
    "DONE downloading file."

    if ($dep.EndsWith(".zip")) {
        $sourceArchiveFileName = $downloadFilePath
        [System.IO.Compression.ZipFile]::ExtractToDirectory($sourceArchiveFileName, $destinationDirectoryName)
    }
}

# 5.
# Invoke DBSeed Sanity test
cd $qaBinariesDestFolder
$retVal = java -cp "$qaBinariesDestFolder\$dbSeedJarFile;$qaBinariesDestFolder\selenium-wd\lib\*" org.junit.runner.JUnitCore surveyor.unittest.source.DbSeedExecutorSanityTest
$success = $false
$testPass = $false
$runPass = $false
$retVal | %{
    [string]$msg = $_
    if ($msg.Contains("OK (1 test)")) {
        $runPass = $true
    }
    if ($msg.Contains("reportTestSucceeded") -and $msg.Contains("_PASS_")) {
        $testPass = $true
    }
}

$retVal
$success = $testPass -and $runPass
"DbSeedExecutorSanityTest command execution status = [$success]"

# 6.
# If DB seeding worked successfully, store ProductTest binaries mapping in Automation DB. 

# Replace manifest.xml with one from Artifisa
Copy-Item "$manifestDownloadPath" "$seleniumWdFolder\manifest.xml" -Force

if ($success) {
    cd $BuildWorkingDir
    java -cp "$qaBinariesDestFolder\$dbSeedJarFile;$qaBinariesDestFolder\selenium-wd\lib\*" common.source.ProductTestBinariesMapExecutor "$dbSeedJarFile"
}
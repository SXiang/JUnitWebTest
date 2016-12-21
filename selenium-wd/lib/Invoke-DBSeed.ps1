# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Invoke-DBSeed.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa"  `
#           -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#           -APIName "Measurement"  `
#           -EnvironmentID 3  `
# ---------------------------------------------------------------
param
(
  [Parameter(Mandatory=$false)]
  [String] $BuildWorkingDir = "C:\Repositories\surveyor",                       # Path to working directory (for eg. C:\Repositories\surveyor)

  [Parameter(Mandatory=$false)]
  [String] $AutomationReportingAPIBaseUrl = "http://localhost:63087",         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087

  [Parameter(Mandatory=$false)]
  [String] $ArtifactoryBaseUrl = "http://picarro.artifactoryonline.com/picarro",         # Artifactory Base Url. For eg. http://picarro.artifactoryonline.com/picarro

  [Parameter(Mandatory=$false)]
  [String] $ArtifactoryAPIKey = "AKCp2VoGPeRruh1fKTMx8K99yceZRay15wBQHHuoFoDuAgib16cSrM8VuKaTtjznPeEC9QXGL",         # Artifactory API Key. 

  [Parameter(Mandatory=$false)]
  [String] $ArtifactoryRepository = "picarro-generic-private-qa",

  [Parameter(Mandatory=$false)]
  [String] $BinaryVersionToUse = "",

  [Parameter(Mandatory=$false)]
  [int] $EnvironmentID = 1
)

# 1.
# Prepare destination folder.
$qaBinariesDestFolder = "$BuildWorkingDir\dbseedtests"
if (-not (Test-Path $qaBinariesDestFolder)) {
    New-item -ItemType Directory $qaBinariesDestFolder
}

$version = $BinaryVersionToUse
$dbSeedJarFile = ""
$dbSeedDepZipFile = ""

# 2.
# Download the specified JAR and dependencies from Artifactory.
$Headers = @{
    "X-JFrog-Art-Api" = $ArtifactoryAPIKey
}

$JARPrefix = "dbseed"
$ZIPPrefix = "selenium-wd"

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

$jarDownloadPath = "$qaBinariesDestFolder\$dbSeedJarFile"
$zipDownloadPath = "$qaBinariesDestFolder\$dbSeedDepZipFile"

$downloadURL = "$ArtifactoryBaseUrl/$ArtifactoryRepository/$dbSeedJarFile"
"Start downloading JAR file from->[$downloadURL], to->[$jarDownloadPath]"
Invoke-WebRequest -Uri $downloadURL -WebSession $wSession -OutFile $jarDownloadPath
"DONE downloading JAR file."

$downloadURL = "$ArtifactoryBaseUrl/$ArtifactoryRepository/$dbSeedDepZipFile"
"Start downloading ZIP file from->[$downloadURL], to->[$zipDownloadPath]"
Invoke-WebRequest -Uri $downloadURL -WebSession $wSession -OutFile $zipDownloadPath
"DONE downloading JAR file."

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

# 4.
# Invoke DBSeed Sanity test
cd $qaBinariesDestFolder
java -cp "$qaBinariesDestFolder\$dbSeedJarFile;$qaBinariesDestFolder\selenium-wd\lib\*" org.junit.runner.JUnitCore surveyor.unittest.source.DbSeedExecutorSanityTest
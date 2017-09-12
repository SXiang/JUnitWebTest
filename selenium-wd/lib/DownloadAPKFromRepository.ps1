﻿# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\DownloadAPKFromRepository.ps1 -BuildWorkingDir "C:\Repositories\surveyor-qa" `
#       -ArtifactoryBaseUrl "http://picarro.artifactoryonline.com/picarro" `
#       -ArtifactoryAPIKey "AKCp2VoGPeRruh1fKTMx8K99yceZRay15wBQHHuoFoDuAgib16cSrM8VuKaTtjznPeEC9QXGL" `   # NOTE: Not valid key. Replace with a valid API key.
#       -ArtifactoryRepository "picarro-generic-private" `
#       -ArtifactoryFolder "AndroidApp" `
#       -APKFlavor "debug"   `     # [optional] If NOT specified debug version will be used. 
#       -APKVersion "1.0.0"  `     # [optional] If NOT specified 1.0.0 will be assumed.
#       -APKBuildNumber "95"       # [optional] If not specified APK with largest build number for specified flavor will be downloaded.

param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Path to working directory (for eg. C:\Repositories\surveyor)

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryBaseUrl,                    # Artifactory Base Url. For eg. http://picarro.artifactoryonline.com/picarro

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryAPIKey,                     # Artifactory API Key. 

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryRepository,

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryFolder,

  [Parameter(Mandatory=$false)]
  [String] $APKFlavor,

  [Parameter(Mandatory=$false)]
  [String] $APKVersion,                             # If NOT specified latest debug version will be searched and used.

  [Parameter(Mandatory=$false)]
  [String] $APKBuildNumber                          # If not specified APK with largest build number for specified flavor will be downloaded.
)

# 1.
# Prepare destination folder.
$apkDestFolder = "$BuildWorkingDir\apk"
if (-not (Test-Path $apkDestFolder)) {
    New-item -ItemType Directory $apkDestFolder
}

$version = "1.0.0"
if ($PSBoundParameters.ContainsKey("APKVersion")) {
    $version = $APKVersion
}

$flavor = "debug"
if ($PSBoundParameters.ContainsKey("APKFlavor")) {
    $flavor = $APKFlavor
}

# 2.
# Download the latest build number for specified APK from Artifactory.
$Headers = @{
    "X-JFrog-Art-Api" = $ArtifactoryAPIKey
}

$APKPrefix = "app-${flavor}-${version}"
$matchStr = "$APKPrefix*.apk"
$requiredVersionAPK = ""
if ($PSBoundParameters.ContainsKey("APKBuildNumber")) {
    $apkBuildNumberSpecified = $true
    $APKPrefix = "app-${flavor}-${version}-${APKBuildNumber}"
    $requiredVersionAPK = "$APKPrefix.apk"
    $matchStr = $requiredVersionAPK
}

Write-Host "Searching for APKs matching prefix - $APKPrefix "

$aql = "items.find     " + 
        "(                " + 
        "    {" + 
        "        ""repo"": {""`$eq"":""$ArtifactoryRepository""}," + 
        "        ""name"": {""`$match"":""$matchStr""}," +
        "        ""modified"" : {""`$last"" : ""4w""}" + 
        "    }" + 
        ")" + 
        ".sort({""`$desc"" : [""modified""]})" + 
        ".limit(100)"

$uri = "$ArtifactoryBaseUrl/api/search/aql"
Write-Host "INVOKE Uri -> $uri"
$response = Invoke-RestMethod -Uri $uri -Headers $Headers -Method POST -Body $aql -ContentType "text/plain" -SessionVariable wSession

# Use highest build number APK if exact build number APK not specified.
if ($requiredVersionAPK -eq "") {    
    $largestVersionAPK = ""
    [int] $largestBuildNum = -1
    $response.results.name | % {
        [string]$name = $_
        if ($name.StartsWith($APKPrefix)) {
            $nameWithoutExt = $name.Replace(".apk", "")
            $partsList = $nameWithoutExt.Split("-")
            [int] $currBuildNum = [int]$name[$partsList.Length - 1]
            if ($currBuildNum -gt $largestBUildNum) {
                $largestBuildNum = $currBuildNum
                $largestVersionAPK = $name
            }
        }
    }

    $requiredVersionAPK = $largestVersionAPK
}

if ($requiredVersionAPK -eq "") {
    Write-Error "Did NOT find any APK - '$requiredVersionAPK' matching search query - $aql"
} else {
    $apkFile = $requiredVersionAPK
    $apkDownloadPath = "$apkDestFolder\$apkFile"

    if (Test-path $apkDownloadPath) {
        Write-Host "Latest APK already found in location: ${apkDownloadPath}. SKIPPING download..."
    } else {
        Remove-Item "$apkDestFolder\*.apk"
        $downloadURL = "$ArtifactoryBaseUrl/$ArtifactoryRepository/$ArtifactoryFolder/$apkFile"
        "Start downloading APK file from->[$downloadURL], to->[$apkDownloadPath]"
        Invoke-WebRequest -Uri $downloadURL -WebSession $wSession -OutFile $apkDownloadPath
        "DONE downloading APK file."
    }
}
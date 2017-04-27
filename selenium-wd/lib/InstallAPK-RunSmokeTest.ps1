# ---------------------------------------------------------------
# SCRIPT: Use this script to install Picarro App APK on Emulator and run smoke test on the APK.
# PRE-REQUISITES: Start Emulator (using 'StartAndroidAutomationTools.ps1') before invoking this script.
# SAMPLE USAGE:
#   .\InstallAPK-RunSmokeTest.ps1 -BuildWorkingDir "C:\Repositories\PicarroApp" `
#       -BuildScriptsBaseDir "C:\Repositories\surveyor-qa" `
#       -ArtifactoryBaseUrl "https://picarro.artifactoryonline.com/picarro" `
#       -ArtifactoryAPIKey "AKCp2VoGPeRruh1fKTMx8K99yceZRay15wBQHHuoFoDuAgib16cSrM8VuKaTtjznPeEC9QXGL" `   # NOTE: Not valid key. Replace with a valid API key.
#       -ArtifactoryRepository "picarro-generic-private" `
#       -ArtifactoryFolder "AndroidApp" `
#       -APKVersion "1.0.0" `   
#       -APKFlavor "debug" `   
#       -APKClassifier "SNAPSHOT" `   
#       -APKBuildNumber "25"
# ---------------------------------------------------------------

param
(
  [Parameter(Mandatory=$true)]
  [string] $BuildWorkingDir,                       # eg. "C:\Repositories\PicarroApp"

  [Parameter(Mandatory=$true)]
  [string] $BuildScriptsBaseDir,                   # eg. "C:\Repositories\surveyor-qa"

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryBaseUrl,                    # Artifactory Base Url. For eg. http://picarro.artifactoryonline.com/picarro

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryAPIKey,                     # Artifactory API Key. 

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryRepository,

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryFolder,

  [Parameter(Mandatory=$true)]
  [String] $APKVersion,                            # Version on the Binary file to be searched.

  [Parameter(Mandatory=$true)]
  [String] $APKFlavor,                             # For eg. debug, release

  [Parameter(Mandatory=$true)]
  [String] $APKClassifier,                         # For eg. SNAPSHOT, BETA, etc.

  [Parameter(Mandatory=$false)]
  [String] $APKBuildNumber                         # For eg. "25". If NOT specified latest build number will be searched and used.
)

function IsEndPointAlive($url) {
    $retVal = $false
    $request = [System.Net.WebRequest]::Create($url)
    try {
        $response = $request.GetResponse()  
        $statusCode = $response.StatusCode
        $retVal = $statusCode -eq 200
    } catch {
        $retVal = $false
    }
    $retVal
}

function WaitFor-ActivityToGainFocus($activityName) {
    Write-Host "Waiting for $activityName to gain focus." -NoNewLine
    $cnt = 0;$MAX_ITER = 200;
    $found = $false
    while((-not $found) -and ($cnt -lt $MAX_ITER)) {
        $cnt++
        Write-Host "." -NoNewLine
        $logLines = adb shell dumpsys activity 2>&1
        $logLines | % {
            [string]$lgLine = [string]$_
            if ($lgLine.Contains("mFocusedActivity") -and $lgLine.Contains($activityName)) {
                $found = $true
            }
        }

	    if (-not $found) {
            Start-Sleep -Milliseconds 1000
        }
    }

    $found
}

. "$BuildScriptsBaseDir\selenium-wd\lib\ApplicationInstaller.ps1"

# 1.
# Prepare destination folder.
$apkDestFolder = "$BuildWorkingDir\apk"
$seleniumWdFolder = "$BuildWorkingDir\selenium-wd"
if (-not (Test-Path $apkDestFolder)) {
    New-item -ItemType Directory $apkDestFolder
}

if ($PSBoundParameters.ContainsKey("APKBuildNumber")) {
    $buildNumber = $APKBuildNumber
}

$apkFile = ""
$dbSeedDepZipFile = ""

# 2.
# Download APK from Artifactory.
$Headers = @{
    "X-JFrog-Art-Api" = $ArtifactoryAPIKey
}

$apkPrefix = "app-"
$apkToSearch = "${apkPrefix}$APKFlavor-$APKVersion-$APKClassifier"

$aql = "items.find     " + 
        "(                " + 
        "    {" + 
        "        ""repo"": {""`$eq"":""$ArtifactoryRepository""}," + 
        "        ""name"": {""`$match"":""$apkToSearch*.apk""}," +
        "        ""modified"" : {""`$last"" : ""4w""}" + 
        "    }" + 
        ")" + 
        ".sort({""`$desc"" : [""modified""]})" + 
        ".limit(100)"


$uri = "$ArtifactoryBaseUrl/api/search/aql"
Write-Host "INVOKE Uri -> $uri"
$response = Invoke-RestMethod -Uri $uri -Headers $Headers -Method POST -Body $aql -ContentType "text/plain" -SessionVariable wSession

if ($buildNumber -eq $null -or $buildNumber -eq "") {
    # version NOT specified. Find the most latest build number version.
    $largestVersion = ""
    [int] $largestBuildNum = -1
    $response.results.name | % {
        [string]$name = $_
        if ($name.StartsWith($apkPrefix)) {
            [int] $currBuildNum = [int]($name.Split("-")[4].Replace(".apk", ""))
            if ($currBuildNum -gt $largestBuildNum) {
                $largestBuildNum = $currBuildNum
                $largestVersion = $name.Replace("$apkToSearch-", "").Replace(".apk", "")
            }
        }
    }
    $buildNumber = $largestVersion
}

$apkFile = "$apkToSearch-$buildNumber.apk"

$apkDownloadPath = "$apkDestFolder\$apkFile"

if (Test-path $apkDownloadPath) {
    "Found existing file - $apkDownloadPath. Deleting ..."
    Remove-Item -Path $apkDownloadPath -Force
}

$downloadURL = "$ArtifactoryBaseUrl/$ArtifactoryRepository/$ArtifactoryFolder/$apkFile"
"Start downloading APK file from->[$downloadURL], to->[$apkDownloadPath]"
Invoke-WebRequest -Uri $downloadURL -WebSession $wSession -OutFile $apkDownloadPath
"DONE downloading APK file."

# 3. 
# START React Native Packager
cd "$BuildWorkingDir"

"Triggering NPM install (updating to latest npm) ..."
npm install npm@$CHOCO_NPM_VERSION -g
npm install

Write-Host "[INSTALL_REACT_NATIVE]: Check/Install pre-requisite applications"
$installApplications = @{
    "01.react-native-cli"="react-native-cli"
}
InstallApplications-FromDictTable -installAppsDictTable $installApplications
Write-Host "[INSTALL_REACT_NATIVE]: Done installing pre-requisite applications"

$REACT_NATIVE_NODEJS_APP_BASEURL = "http://localhost:8081"
$MAX_ITERATIONS = 300

Start-Process "react-native" -ArgumentList "start"

Write-Host "Waiting for react-native Packager to start ." -NoNewLine
$iteration = 0
while((-not (IsEndPointAlive -url $REACT_NATIVE_NODEJS_APP_BASEURL)) -and ($iteration -lt $MAX_ITERATIONS)) {
    $iteration++
    Write-Host "." -NoNewLine
	Start-Sleep -Milliseconds 1000
}

# 4. 
# INSTALL APK on Emulator.
adb kill-server
adb start-server
adb devices
adb install -r -d "$apkDownloadPath"

# 5.
# Run Smoke test.
# Launch MainActivity
adb shell am start -n com.picarroapp/com.picarroapp.MainActivity
sleep -Seconds 5
$smokeTestPassed = WaitFor-ActivityToGainFocus -activityName "MainActivity"
if ($smokeTestPassed) {
    Write-Host "Test PASSED. MainActivity was launched."
} else {
    Write-Error "Test FAILED. MainActivity was NOT launched."
    exit(9)
}
# Reset APK
adb install -r -d "$apkDownloadPath"

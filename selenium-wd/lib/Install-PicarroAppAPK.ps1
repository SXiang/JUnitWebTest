# ---------------------------------------------------------------
# SCRIPT: Use this script to install Picarro App APK on Emulator.
# PRE-REQUISITES: Start Emulator (using 'StartAndroidAutomationTools.ps1') before invoking this script.
# SAMPLE USAGE:
#   .\Install-PicarroAppAPK.ps1 `
#           -BuildWorkingDir "C:\Repositories\PicarroApp"
# ---------------------------------------------------------------
<# 
#>

param
(
  [Parameter(Mandatory=$true)]
  [string] $BuildWorkingDir             # eg. "C:\Repositories\PicarroApp"
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

# 1. 
# START React Native Packager
$REACT_NATIVE_NODEJS_APP_BASEURL = "http://localhost:8081"
$MAX_ITERATIONS = 300

# WORKAROUND [MAY NOT BE NEEDED]
# workaround for .babel.json becoming readonly on machine.
#$userProfile = $env:USERPROFILE
#Set-ItemProperty "$userProfile\.babel.json" -name IsReadOnly -value $false

cd "$BuildWorkingDir"
Start-Process "react-native" -ArgumentList "start"

Write-Host "Waiting for react-native Packager to start ." -NoNewLine
$iteration = 0
while((-not (IsEndPointAlive -url $REACT_NATIVE_NODEJS_APP_BASEURL)) -and ($iteration -lt $MAX_ITERATIONS)) {
    $iteration++
    Write-Host "." -NoNewLine
	Start-Sleep -Milliseconds 1000
}

# WORKAROUND [MAY NOT BE NEEDED]
# if (-not (Test-path "$BuildWorkingDir\app\src\main\assets\")) {
#     New-Item -ItemType Directory "$BuildWorkingDir\app\src\main\assets\"
# }
#
# Bundle the application first before triggering Gradle build:
#   http://stackoverflow.com/questions/38870710/error-could-not-get-batchedbridge-make-sure-your-bundle-is-packaged-properly
#(curl $REACT_NATIVE_NODEJS_APP_BASEURL/index.android.js?platform=Android).Content | Out-File "app/src/main/assets/index.android.bundle"


# 2. 
# INSTALL APK on Emulator.
Start-Process "react-native" -ArgumentList "run-android"
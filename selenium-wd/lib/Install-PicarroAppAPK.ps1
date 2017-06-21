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

cd "$BuildWorkingDir"
Start-Process "react-native" -ArgumentList "start"

Write-Host "Waiting for react-native Packager to start ." -NoNewLine
$iteration = 0
while((-not (IsEndPointAlive -url $REACT_NATIVE_NODEJS_APP_BASEURL)) -and ($iteration -lt $MAX_ITERATIONS)) {
    $iteration++
    Write-Host "." -NoNewLine
	Start-Sleep -Milliseconds 1000
}

# 2. 
# INSTALL APK on Emulator.
Start-Process "react-native" -ArgumentList "run-android"
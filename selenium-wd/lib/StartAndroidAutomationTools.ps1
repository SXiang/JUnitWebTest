param
(
  [string] $avdName = "android_25_google_apis_x86"
)

Write-Host "[ANDROID-TOOLS]: Starting Android Emulator ..."

$ANDROIDHOME = $env:ANDROID_HOME
Start-Process -FilePath "$ANDROIDHOME\tools\emulator.exe" -ArgumentList "-avd $avdName"

$APPDATA = $env:APPDATA
$appiumCmdPath = "$APPDATA\npm\appium"

Write-Host "[ANDROID-TOOLS]: Starting Appium Server ..."
$null = taskkill /F /IM node.exe 2>&1
Start-Process -FilePath "$appiumCmdPath"

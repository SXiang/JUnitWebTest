﻿# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\StartAndroidAutomationTools.ps1 `
#           -AvdName "android_23_google_apis_x86"
# ---------------------------------------------------------------

param
(
  [Parameter(Mandatory=$true)]
  [string] $AvdName                      # Eg. "android_23_google_apis_x86"
)

Write-Host "[ANDROID-TOOLS]: Starting Android Emulator ..."

$ANDROIDHOME = $env:ANDROID_HOME
Start-Process -FilePath "$ANDROIDHOME\tools\emulator.exe" -ArgumentList "-avd $AvdName"

$APPDATA = $env:APPDATA
$appiumCmdPath = "$APPDATA\npm\appium"

Write-Host "[ANDROID-TOOLS]: Starting Appium Server ..."
$procs = Get-Process node 2>&1
if ($procs -ne $null) {
    $procs | % { 
        if ($_.Id -ne $null -and $_.Id -ne "") { 
            $null = taskkill /F /pid $_.Id
        }
        "NOTE: Process NOT found errors can be ignored."
    }
}

Start-Process -FilePath "$appiumCmdPath"
sleep -Seconds 30
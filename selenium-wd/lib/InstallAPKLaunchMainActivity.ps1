param
(
  [Parameter(Mandatory=$true)]
  [string] $APKFilePath,                           # eg. "C:\Repositories\PicarroApp"

  [Parameter(Mandatory=$true)]
  [string] $ActivityToWaitFor                      # eg. "MainActivity", "AppDrawOverlaySettingsActivity"
)

function WaitFor-ActivityToGainFocus($activityName) {
    Write-Host "Waiting for $activityName to gain focus." -NoNewLine
    $cnt = 0;$MAX_ITER = 5;
    if ($activityName -eq "MainActivity") {
        $MAX_ITER = 15;
    }

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

$INSTALL_NEW_OVERRIDE = $true

$VERSION_MARKER_FILE = "C:\QATestLogs\installed-apk.md"
if (Test-Path $VERSION_MARKER_FILE) {
    Remove-Item $VERSION_MARKER_FILE
}

$null = adb pull /sdcard/installed-apk.md $VERSION_MARKER_FILE 2>&1

$sameVersionFound = $false
$fName = Split-Path -Path $APKFilePath -Leaf 

if (Test-Path $VERSION_MARKER_FILE) {
    Get-Content $VERSION_MARKER_FILE | %{
        $apkOnDevice = $_
        if ($fName.tolower() -eq $apkOnDevice) {
            Write-Host "Found same version-[$apkOnDevice] APK on device.. Skipping installation."
            $sameVersionFound = $true
        }
    }
}

if ($INSTALL_NEW_OVERRIDE -or (-not $sameVersionFound)) {
    Write-Host "Installing APK - $fName on device..."
    adb install -r -d -g "$APKFilePath"
}

adb shell settings put secure location_providers_allowed +network
adb shell settings put secure location_providers_allowed +wifi
adb shell settings put secure send_action_app_error 0
adb shell settings put secure anr_show_background 0

adb shell am start -n com.picarroapp/com.picarroapp.MainActivity
sleep -Seconds 2
$activityFound = WaitFor-ActivityToGainFocus -activityName "$ActivityToWaitFor"
if ($activityFound) {
    Write-Host "$ActivityToWaitFor was launched successfully."
} else {
    if ($ActivityToWaitFor -ne "MainActivity") {
        Write-Host "$ActivityToWaitFor was NOT found. Searching for default activity instead."
        $activityFound = WaitFor-ActivityToGainFocus -activityName "MainActivity"
        if (-not $activityFound) {
            Write-Error "Neither $activityName nor MainActivity was found."
        }
    } else {
        Write-Error "MainActivity was NOT found."
    }
}

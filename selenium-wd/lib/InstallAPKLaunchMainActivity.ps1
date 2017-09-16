param
(
  [Parameter(Mandatory=$true)]
  [string] $APKFilePath,                           # eg. "C:\Repositories\PicarroApp"

  [Parameter(Mandatory=$true)]
  [string] $ActivityToWaitFor                      # eg. "MainActivity", "AppDrawOverlaySettingsActivity"
)

$INSTALL_NEW_OVERRIDE = $true                      # flag which could be set to FALSE during development to avoid installing the same APK
$APK_FILE_MATCH_PATTERN = ".+(debug|release)\-(\d+)\.(\d+)\.(\d+)(.+)?\-(\d+).apk"
$VERSION_MARKER_FILE = "C:\QATestLogs\installed-apk.md"

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

function Detect-UninstallConflictingAPK($apkFileNameToInstall, $apkFileNameOnDevice) {
    if ($apkFileNameToInstall -match $APK_FILE_MATCH_PATTERN) {
        [int]$toInstallBuildNum = [int]$Matches[6]
        $toInstallFlavor = $Matches[1]
        [int]$toInstallMajor = [int]$Matches[2]
        [int]$toInstallMinor = [int]$Matches[3]
        [int]$toInstallPatch = [int]$Matches[4]
    }

    if ($apkFileNameOnDevice -match $APK_FILE_MATCH_PATTERN) {
        [int]$onDeviceBuildNum = [int]$Matches[6]
        $onDeviceFlavor = $Matches[1]
        [int]$onDeviceMajor = [int]$Matches[2]
        [int]$onDeviceMinor = [int]$Matches[3]
        [int]$onDevicePatch = [int]$Matches[4]
    }

    if ($toInstallFlavor -ne $onDeviceFlavor) {
        Write-Host "Detected different flavor APK on device - '$apkFileNameOnDevice'. Uninstalling ..."
        adb uninstall com.picarroapp
    } else {
        if ($onDeviceBuildNum -le $toInstallBuildNum) {
            $onDeviceVerCode = (10000 * $onDeviceMajor) + (100 * $onDeviceMinor) + $onDevicePatch
            $toInstallVerCode = (10000 * $toInstallMajor) + (100 * $toInstallMinor) + $toInstallPatch
            if ($onDeviceVerCode -gt $toInstallVerCode) {
                Write-Host "Detected higher version code APK on device - '$apkFileNameOnDevice'. Uninstalling ..."
                adb uninstall com.picarroapp
            }
        } else {
            Write-Host "Found higher build number APK on device - '$apkFileNameOnDevice'. Uninstalling ..."
            adb uninstall com.picarroapp
        }
    }
}

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
    if ($apkOnDevice -ne $NULL -and $apkOnDevice -ne "") {
        Detect-UninstallConflictingAPK -apkFileNameToInstall $fName -apkFileNameOnDevice $apkOnDevice
    }

    Write-Host "Installing APK - $fName on device... InstallOverride set to [$INSTALL_NEW_OVERRIDE]"
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

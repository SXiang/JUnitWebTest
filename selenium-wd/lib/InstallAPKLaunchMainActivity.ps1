param
(
  [Parameter(Mandatory=$true)]
  [string] $APKFilePath                           # eg. "C:\Repositories\PicarroApp"
)

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

adb install -r -d -g "$APKFilePath"

adb shell am start -n com.picarroapp/com.picarroapp.MainActivity
sleep -Seconds 5
$smokeTestPassed = WaitFor-ActivityToGainFocus -activityName "MainActivity"
if ($smokeTestPassed) {
    Write-Host "MainActivity was launched successfully."
} else {
    Write-Error "MainActivity failed to be launched."
}

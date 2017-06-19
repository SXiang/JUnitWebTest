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

Start-Process -FilePath "$appiumCmdPath" -ArgumentList "--session-override"
sleep -Seconds 30
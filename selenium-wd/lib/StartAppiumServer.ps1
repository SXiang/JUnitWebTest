$APPDATA = $env:APPDATA
$appiumCmdPath = "$APPDATA\npm\appium"

function New-GuidNoDashes() {
    [guid]::NewGuid().ToString().Replace("-", "").ToUpper()
}

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

$uuid = New-GuidNoDashes
Start-Process -FilePath "$appiumCmdPath" -ArgumentList "--session-override --local-timezone --log c:\logs\automationappium-$uuid.log"
sleep -Seconds 30
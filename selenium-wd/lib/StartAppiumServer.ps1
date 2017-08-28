param
(
  [Parameter(Mandatory=$true)]
  [string] $WorkingDirectory                      # Eg. "C:\repositories\surveyor-qa"
)

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
$testProperties = "$WorkingDirectory\selenium-wd\tests\surveyor\test.properties"
$runUUID = 0
Get-Content $testProperties | % {$line = $_; if ($line -match "runUUID\s+=\s+(\d+)") {$runUUID = $Matches[1]} }
Start-Process -FilePath "$appiumCmdPath" -ArgumentList "--session-override --local-timezone --log c:\QATestLogs\automationappium-$uuid-$runUUID.log"
sleep -Seconds 30
# ---------------------------------------------------------------
# SCRIPT: This script monitors Customer with GIS data pool and unlocks customers that have been locked for more than $MaxAllocatedTimeInSeconds.
#         
# USAGE:
#   .\Monitor-CustomerWithGisDataPool.ps1  `
#       -DatabaseServerIP "20.20.152.36"  `
#       -DatabaseName "AutomationReporting"  `
#       -HelperScriptsRootFolder "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts"  `
#       -MaxAllocatedTimeInSeconds 14400  `
#       -CheckInterval 30  `
#       -OutFileFolder "C:\QATestLogs"
# ---------------------------------------------------------------

param (
  [Parameter(Mandatory=$true)]
  [String] $DatabaseServerIP,

  [Parameter(Mandatory=$true)]
  [String] $DatabaseName,

  [Parameter(Mandatory=$true)]
  [String] $HelperScriptsRootFolder,

  [Parameter(Mandatory=$true)]
  [string] $MaxAllocatedTimeInSeconds,       # Max time (in seconds) each test can lock a Customer with GIS data.

  [Parameter(Mandatory=$true)]
  [Int32]  $CheckInterval,                   # seconds to pause before performing next check

  [Parameter(Mandatory=$true)]
  [String] $OutFileFolder
)

$SecondsInATick = 0.0000001

function New-GuidNoDashes() {
    [guid]::NewGuid().ToString().Replace("-", "").ToLower()
}

$OUTFILE = New-Item -Path "$OutFileFolder\MonitorCustomerWithGisData_$(New-GuidNoDashes).txt" -Force

. "$HelperScriptsRootFolder\DatabaseHelpers.ps1"

$connString = "Server=$DatabaseServerIP;Database=$DatabaseName;integrated security=True;"


function Get-LongDateTimeString() {
    get-date -format "yyyy-MM-dd HH:mm:ss"
}

function Write-ToOutputFile($text) {
    Write-Host "$(Get-LongDateTimeString) : $text"
    $null = Add-Content $OUTFILE "$(Get-LongDateTimeString) : $text"
}

function Is-MaxAllocatedTimeReached($itemLastUpdated) {
	$maxTimeReached = $false

	$lastUpdated = [datetime]($itemLastUpdated)
	$lastUpdatedTicks = $lastUpdated.Ticks
	$currTicks = (get-date).Ticks
	$secondsSinceLastUpdate = ($currTicks - $lastUpdatedTicks) * $SecondsInATick
	$maxTimeReached = $secondsSinceLastUpdate -gt $MaxAllocatedTimeInSeconds

    $maxTimeReached
}

function Execute-Monitor() {
    $query = "SELECT [Id],[Name],[EnvironmentId],[RunUUID],[IsLocked],[LockingMachineIP],[UnlockedBySystem],[LastUpdated],[LastUnlockedBySystem] FROM [dbo].[CustomersWithGISData] WHERE [IsLocked]=1"
    $objResults = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
    $i = 0
    $cnt = 0
    $len = $objResults.length - 1
	Write-ToOutputFile -text "Found $len entries in database with locked state. Verifying Max Allocated Lock duration for each entry."
    $objResults | foreach { 
        if ($i -gt 0) {     # first row is length of array. datarows are from index=1
            $obj = $_; 
            $Id = $obj.Id
            $Name = $obj.Name
            $EnvironmentId = $obj.EnvironmentId
            $RunUUID = $obj.RunUUID
            $IsLocked = $obj.IsLocked
            $LockingMachineIP = $obj.LockingMachineIP
            $UnlockedBySystem = $obj.UnlockedBySystem
            $LastUpdated = $obj.LastUpdated
            $LastUnlockedBySystem = $obj.LastUnlockedBySystem

            $applyFix = Is-MaxAllocatedTimeReached -itemLastUpdated $LastUpdated

            if ($applyFix) {
                $cnt++
            	Write-ToOutputFile -text "Max Allocated Lock duration exceeded for [Id=$Id], [Name=$Name]. Unlocking entry..."
                $updateQuery = "UPDATE [dbo].[CustomersWithGISData] SET [IsLocked]=0,[LockingMachineIP]='',[UnlockedBySystem]=1,[LastUnlockedBySystem]='$(Get-LongDateTimeString)' WHERE [Id]=$Id"
                Write-ToOutputFile -text "Update query -> $updateQuery"
                Invoke-DatabaseQuery -connectionString $connString -query $updateQuery -isSQLServer:$true
                Write-ToOutputFile -text "Entry unlocked."
            }
        }
        $i++
    }

	Write-ToOutputFile -text "Found $cnt entries which exceeded Max Allocated Lock duration."
}

do {
	Write-ToOutputFile -text "Triggering monitoring check >>>> [START]"
    Execute-Monitor

	if ($CheckInterval -gt 0) {
    	sleep $CheckInterval
	}

} while ($true)
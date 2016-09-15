<#
 DESCRIPTION:
	This is an helper script to invoke test run with local Selenium Grid.	
	Sample Scenarios:
	1. Running long running tests with Selenium Grid where-in you want to keep working on other features while the tests are executing.
 INSTRUCTIONS:
	To run this script specify the testTarget as a list of comman seperated values:
		.\Invoke-LocalGridRun.ps1 -targetsToRun "tc_localrun" -cleanWorkingFolder:$true
 NOTES:
    1. Tests are triggered from a seperate directory in C:\Build\work. 
	2. You can continue working on your repository once the test run has started. The changes in your local repository won't affect
	   the test run provided your changes happen after the tests are triggered.
#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $targetsToRun,         # comma-seperated list of targets. For eg. tc_localrun, testsanity, testregression

  [Parameter(Mandatory=$false)]
  [switch] $cleanWorkingFolder = $true
)

$buildRoot = "C:\Build\work"
$buildSrcRoot = "C:\Repositories\surveyor-qa"
$buildSrcDir = "$buildSrcRoot\selenium-wd"
$qaAutoLogFile = "$buildSrcRoot\logs\qaauto.log"

$start = Get-Date 
Write-Host "Starting execution of specified targets - [$targetsToRun] ..."

function Prepend-Zero($val) {
	$value = "0"
	if ($val -lt 10) { $value += "$val" } else { $value = "$val" }
	$value
}

function Get-CurrentTimestamp() {
	$date = get-date
	$yr = $date.Year
	$mnth = Prepend-Zero -val $date.Month
	$dy = Prepend-Zero -val $date.Day
	$hr = Prepend-Zero -val $date.Hour
	$min = Prepend-Zero -val $date.Minute
	$sec = Prepend-Zero -val $date.Second
	$msec = $date.MilliSecond
	"$yr$mnth$dy-$hr$min$sec-$msec"
}

# delete all previous directories from build root.
if ($cleanWorkingFolder -eq $true) {
	$childFolders = Get-ChildItem -Path $buildRoot | where {$_.Attributes -eq 'Directory'} 
	$childFolders | % { 
		$fullName=$_.fullName; 
		Remove-Item -Path $fullName -Recurse -Force
	}
}

# trigger local run for all the specified test targets.
$targets = $targetsToRun -split ","
$targets | % { 
	$target = $_ 
	$target = $target.trim()
	$guid = [guid]::NewGuid().toString()
	$guid = $guid.replace("-", "")

	$buildWork = "$buildRoot\$guid"
	$buildWorkingDir = "$buildWork\selenium-wd"

	# create new working folder for build
	New-Item -Force -ItemType directory -Path $buildWorkingDir

	Robocopy $buildSrcDir $buildWorkingDir /E /MT:32

	# set Ant Options for JVM.
	Write-host "Setting ANT Options -> SET ANT_OPTS=-Xmx1024m -XX:MaxPermSize=512m"
	SET ANT_OPTS="-Xmx1024m -XX:MaxPermSize=512m"
	
	# add firewall rule to chromedriver.
	. C:\Repositories\surveyor-qa\selenium-wd\lib\ParallelExecution\Add-FirewallRule.ps1
	$chromeDriverPath = "$buildWork\selenium-wd\lib\chromedriver.exe"
	Write-Host "Adding firewall rule for - '$chromeDriverPath'"
	Add-FirewallRule "ChromeDriver-LocalGridRun" "1000-59000" "$chromeDriverPath" $null

	$timeStamp = Get-CurrentTimestamp
	$qaAutoLogFileCopy = "$buildSrcRoot\logs\qaauto-$timeStamp.log"
	
	# for each target create a new log file specific to the run.
	if ((Test-Path $qaAutoLogFileCopy) -eq $false) {
		copy-item $qaAutoLogFile $qaAutoLogFileCopy
	}
	
	# build files.
    cd "$buildWork"
    ant -buildfile selenium-wd/build.xml $target
}

# revert back to lib folder
cd "$buildSrcRoot\selenium-wd\lib\ParallelExecution"

$end = Get-Date
$totalSeconds = ($end - $start).TotalSeconds
$totalMinutes = $totalSeconds/60
Write-Host -ForegroundColor Green "Completed all execution in $totalSeconds seconds, $totalMinutes minutes"
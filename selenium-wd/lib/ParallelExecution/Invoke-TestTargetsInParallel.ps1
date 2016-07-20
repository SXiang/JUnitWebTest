<#
 DESCRIPTION:
	This is an helper script to invoke multiple ANT test targets in parallel.	
	Sample Scenarios:
	1. Running multiple compliance reports test classes in parallel for testing parallelism.
	2. Running multiple compliance reports performance test classes in parallel for perf/load scenarios.
 INSTRUCTIONS:
	To run this script change the test targets specified in $targetsToRun and then execute:
		.\Invoke-TestTargetsInParallel.ps1
 NOTES:
    1. For each test target a webdriver browser instance will be spawned. 
	   Make sure that the machine where the test runs has enough memory in case large number of test targets are specified.
	   Note that we use the following JVM settings during execution: -Xmx1024m -XX:MaxPermSize=512m
	   These JVM settings can be tweaked depending on the number of test targets specified in the script.
#>

#--------- MODIFY THIS BEFORE RUNNING SCRIPT -------#
# Ant Test Targets
$targetsToRun = 
 "performancetestsLight",
 "performancetestsMedium",
 "performancetestsHigh",
 "performancetestsUltraHigh"
#---------------------------------------------------#

 . C:\Repositories\surveyor-qa\selenium-wd\lib\ParallelExecution\Invoke-Parallel.ps1
 
$jobs = New-Object System.Collections.ArrayList
 
$start = Get-Date 
Write-Host "Starting parallel test execution of specified targets - [$targetsToRun] ..."

# delete all previous directories from build root.
$childFolders = Get-ChildItem -Path $buildRoot | where {$_.Attributes -eq 'Directory'} 
$childFolders | % { 
	$fullName=$_.fullName; 
	Remove-Item -Path $fullName -Recurse -Force
}

Invoke-Parallel -InputObject $targetsToRun -runspaceTimeout 18000 -ScriptBlock { 
	$target = $_ 
	$guid = [guid]::NewGuid().toString()
	$guid = $guid.replace("-", "")

    $buildSrcDir = "C:\Repositories\surveyor-qa\selenium-wd"
    $buildRoot = "C:\Build\work"

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
	Add-FirewallRule "ChromeDriver-ParallelTest" "1000-59000" "$chromeDriverPath" $null

	# build files.
    cd "$buildWork"
    ant -buildfile selenium-wd/build.xml $target
}

$end = Get-Date
$totalSeconds = ($end - $start).TotalSeconds
$totalMinutes = $totalSeconds/60
Write-Host -ForegroundColor Green "Completed all jobs in $totalSeconds seconds, $totalMinutes minutes"
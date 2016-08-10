<#
 DESCRIPTION:
	Purpose of this script is to invoke a parallel Automation RUN using an HTTP API call.	
	Sample Scenarios:
	1. Trigger a run of pre-determined scale (ie number of nodes required) for testing purpose.
 INSTRUCTIONS:
	To run this script change the test targets specified in $targetsToRun and then execute:
		.\Trigger-AutomationRun.ps1 -GridHost 10.100.3.51 -GridPort 4444 -GridPlatform LINUX -Nodes 10 -TestTarget testgridpoc
#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $GridHost,

  [Parameter(Mandatory=$true)]
  [String] $GridPlatform,

  [Parameter(Mandatory=$true)]
  [Int] $GridPort,

  [Parameter(Mandatory=$true)]
  [Int] $Nodes,               # If nodes is specified and TargetToRun is NOT specified default to running GridPOC test target. Spin more builds as necessary to meet the specified Nodes count.
  
  [Parameter(Mandatory=$false)]
  [String] $CleanBuildEnabled,    # true or false.

  [Parameter(Mandatory=$false)]
  [String] $TargetToRun
)

#--------- MODIFY THIS BEFORE RUNNING SCRIPT -------#
# Ant Test Targets

$buildSrcDir = "C:\Repositories\surveyor-qa\selenium-wd"     # make sure you update the path to Add-FirewallRule.ps1 as well.
$buildRoot = "C:\Build\work"

#---------------------------------------------------#

if ($nodes -gt 40) {
    Write-Host "Nodes > 40 are NOT supported. Please use lesser number of nodes"
}
 
$NUM_ITERATIONS = 1

$target = "testgridpoc"
if ($PSBoundParameters.ContainsKey("TargetToRun")) {
    $target = $TargetToRun
}

# If run is for Grid POC spin up build to match the number of nodes.
if ($target -eq "testgridpoc") {
    $NUM_ITERATIONS = $Nodes / 8
}

$start = Get-Date 
Write-Host "Starting execution of specified target - [$target] ..."

if ($PSBoundParameters.ContainsKey("CleanBuildEnabled")) {
    if ($CleanBuildEnabled) {
        # delete all previous directories from build root.
        $childFolders = Get-ChildItem -Path $buildRoot | where {$_.Attributes -eq 'Directory'} 
        $childFolders | % { 
	        $fullName=$_.fullName; 
	        Remove-Item -Path $fullName -Recurse -Force
        }
    }
}

# Create multiple builds as necessary.
for ($i=0; $i -lt $NUM_ITERATIONS; $i++) {
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
    Add-FirewallRule "ChromeDriver-AutomationTest" "1000-59000" "$chromeDriverPath" $null

    # build files.
    cd "$buildWork"
    ant -buildfile selenium-wd/build.xml $target
}

$end = Get-Date
$totalSeconds = ($end - $start).TotalSeconds
$totalMinutes = $totalSeconds/60
Write-Host -ForegroundColor Green "Completed all jobs in $totalSeconds seconds, $totalMinutes minutes"
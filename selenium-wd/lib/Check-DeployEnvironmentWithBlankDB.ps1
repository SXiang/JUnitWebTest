# 
# Sample Usage: 
#   .\Check-DeployEnvironmentWithBlankDB.ps1 `
#        -BuildWorkingDir "C:\Repositories\surveyor-qa" `
#        -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#        -DeployIntervalInDays 7 `
#        -EnvironmentTeamCityBuildConfigId "Surveyor_2_SqaautoDevelop" `
#        -EnvironmentId 1  `
#        -CurrentTeamCityBuildConfigId "Surveyor_2_SqaSeleniumTestExecutionRunComplianceReportsSurveyorQaMaster" `
#        -WaitTimeInMinutesForDeployment 20

param
(
  [Parameter(Mandatory=$false)]
  [String] $BuildWorkingDir="C:\Repositories\surveyor-qa",                       # Path to working directory (for eg. C:\Repositories\surveyor-qa)

  [Parameter(Mandatory=$false)]
  [String] $AutomationReportingAPIBaseUrl="http://localhost:63087",         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087

  [Parameter(Mandatory=$false)]
  [String] $DeployIntervalInDays=7,

  [Parameter(Mandatory=$false)]
  [String] $EnvironmentTeamCityBuildConfigId="Surveyor_2_SqaautoDevelop",      # Provide environmentTeamCityBuildConfigId of the environment along with EnvironmentId to ensure incorrect environment does not get deployed.

  [Parameter(Mandatory=$false)]
  [int] $EnvironmentId=1,

  [Parameter(Mandatory=$false)]
  [String] $CurrentTeamCityBuildConfigId="Surveyor_2_SqaSeleniumTestExecutionRunComplianceReportsSurveyorQaMaster",      

  [Parameter(Mandatory=$false)]
  [int] $WaitTimeInMinutesForDeployment=20
) 

$libFolder = "selenium-wd\lib"

. $BuildWorkingDir\$libFolder\Execute-WithRetry.ps1
. $BuildWorkingDir\$libFolder\Reporting-CommonFunctions.ps1

$triggerDeploymentApiUrl = "api/TriggerEnvironmentDeployment"

# ----------------------------------------------------------------------------------------------
# Post entry to trigger deployment on specified build config.
# ----------------------------------------------------------------------------------------------

try {
	$authToken = Get-ReportingAppAuthToken -BuildWorkingDir $BuildWorkingDir -AutomationReportingAPIBaseUrl $AutomationReportingAPIBaseUrl
} catch {
    # retry on failure.
	$authToken = Execute-WithRetry -RetryDelay 1 -MaxRetries 5 { Get-ReportingAppAuthToken -BuildWorkingDir $BuildWorkingDir -AutomationReportingAPIBaseUrl $AutomationReportingAPIBaseUrl }
}

$postContentType = "application/json; charset=UTF-8"
$Headers = @{
    Token = $authToken
}

$Body = @{
    Id = 0       # sample ID. Ignored on server-side 
    EnvironmentId = $EnvironmentId
    EnvironmentToTriggerBuildConfigId = $EnvironmentTeamCityBuildConfigId
    CurrentBuildConfigId = $CurrentTeamCityBuildConfigId
    DeployIntervalInDays = $DeployIntervalInDays
    DeployWithBlankDB = "true"
}

$jsonBody = (ConvertTo-Json $Body)
Write-Host "Triggering $EnvironmentTeamCityBuildConfigId deployment - $AutomationReportingAPIBaseUrl/$triggerDeploymentApiUrl ..." 
$response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$triggerDeploymentApiUrl" -Headers $Headers -Method POST -Body $jsonBody -ContentType $postContentType
$json = $response.Content | ConvertFrom-Json
Write-Host "Triggering $EnvironmentTeamCityBuildConfigId deployment successful! Response = $json"

# ----------------------------------------------------------------------------------------------
# Wait for deployment to complete if blank DB deployment was triggered.
# ----------------------------------------------------------------------------------------------
if ($json.DeployWithBlankDB) {
    $iteration = 1
    while ($WaitTimeInMinutesForDeployment -gt 0) {
        Write-Host "[Iteration-$iteration] Waiting for deployment to complete... Remaining wait time = $WaitTimeInMinutesForDeployment minutes ..."
        Start-Sleep -Seconds 60
        $WaitTimeInMinutesForDeployment--
        $iteration++
    }
}

Write-Host "Done with script execution!"
# 
# Sample Usage: 
#   .\Check-DeployEnvironmentWithBlankDB.ps1 `
#        -BuildWorkingDir "C:\Repositories\surveyor-qa" `
#        -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#        -DeployIntervalInDays 1 `
#        -EnvironmentTeamCityBuildConfigId "<build_config_id>" `
#        -EnvironmentId 1

param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Path to working directory (for eg. C:\Repositories\surveyor-qa)

  [Parameter(Mandatory=$true)]
  [String] $AutomationReportingAPIBaseUrl,         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087

  [Parameter(Mandatory=$true)]
  [String] $DeployIntervalInDays,

  [Parameter(Mandatory=$true)]
  [String] $EnvironmentTeamCityBuildConfigId,      # Provide environmentTeamCityBuildConfigId of the environment along with EnvironmentId to ensure incorrect environment does not get deployed.

  [Parameter(Mandatory=$true)]
  [int] $EnvironmentId
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

$nowDate = (get-date).ToString()
$Body = @{
    Id = 0       # sample ID. Ignored on server-side 
    EnvironmentId = $EnvironmentId
    TeamCityBuildConfigId = $EnvironmentTeamCityBuildConfigId
    DeployIntervalInDays = $DeployIntervalInDays
}

$jsonBody = (ConvertTo-Json $Body)
Write-Host "Triggering $EnvironmentTeamCityBuildConfigId deployment - $AutomationReportingAPIBaseUrl/$triggerDeploymentApiUrl ..." 
$response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$triggerDeploymentApiUrl" -Headers $Headers -Method POST -Body $jsonBody -ContentType $postContentType
$json = $response.Content | ConvertFrom-Json
Write-Host "Triggering $EnvironmentTeamCityBuildConfigId deployment successful! Response -> $json"

# 
# Sample Usage: 
#   .\CreateAutomationRunTrigger.ps1 `
#        -BuildWorkingDir "C:\Repositories\surveyor-qa" `
#        -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#        -teamCityBuildConfigId "Surveyor_2_AutoTriggeredAutomationRunSqaSeleniumRunSurveyorQaMaster_01" `
#        -environmentId 1 `
#        -gridHost "10.10.3.54" `
#        -gridPlatform "WINDOWS" `
#        -gridPort "4444" `
#        -testTarget "testsanity" `
#        -emailTo "spulikkal@picarro.com" `
#        -runFriendlyName "SP TestRun 01" `
#        -triggeredBy "Shirish Pulikkal"

param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Path to working directory (for eg. C:\Repositories\surveyor-qa)

  [Parameter(Mandatory=$true)]
  [String] $AutomationReportingAPIBaseUrl,         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087

  [Parameter(Mandatory=$true)]
  [String] $teamCityBuildConfigId,

  [Parameter(Mandatory=$true)]
  [int] $environmentId,

  [Parameter(Mandatory=$true)]
  [String] $testTarget,

  [Parameter(Mandatory=$true)]
  [String] $emailTo,

  [Parameter(Mandatory=$true)]
  [String] $runFriendlyName,

  [Parameter(Mandatory=$true)]
  [String] $triggeredBy,

  [Parameter(Mandatory=$false)]
  [String] $gridHost,

  [Parameter(Mandatory=$false)]
  [String] $gridPlatform,

  [Parameter(Mandatory=$false)]
  [int] $gridPort
)

$libFolder = "selenium-wd\lib"

. $BuildWorkingDir\$libFolder\Execute-WithRetry.ps1
. $BuildWorkingDir\$libFolder\Reporting-CommonFunctions.ps1

$runTriggerPostApiUrl = "api/TriggerAutomationRun"

# ----------------------------------------------------------------------------------------------
# Post entry to AutomationRunTrigger table. (This also sets 'env.RunUUID' in CI build config)
# ----------------------------------------------------------------------------------------------

$authToken = Execute-WithRetry -RetryDelay 1 -MaxRetries 5 { Get-ReportingAppAuthToken -BuildWorkingDir $BuildWorkingDir -AutomationReportingAPIBaseUrl $AutomationReportingAPIBaseUrl }

$postContentType = "application/json; charset=UTF-8"
$Headers = @{
    Token = $authToken
}

$nowDate = (get-date).ToString()
if ($PSBoundParameters.ContainsKey("gridHost") -and $PSBoundParameters.ContainsKey("gridPlatform") -and $PSBoundParameters.ContainsKey("gridPort")) {
    $Body = @{
      Id = 0       # sample ID. Ignored on server-side 
      EnvironmentId = $environmentId
      GridHost = $gridHost
      GridPlatform = $gridPlatform
      GridPort = $gridPort  
      DateStarted = $nowDate
      TestTarget = $testTarget
      RunStatus = "InProgress"
      EmailTo = $emailTo
      TriggeredBy = $triggeredBy
      RunFriendlyName = $runFriendlyName
      TeamCityBuildConfigId = $teamCityBuildConfigId
    }
} else {
    $Body = @{
      Id = 0       # sample ID. Ignored on server-side 
      EnvironmentId = $environmentId
      DateStarted = $nowDate
      TestTarget = $testTarget
      RunStatus = "InProgress"
      EmailTo = $emailTo
      TriggeredBy = $triggeredBy
      RunFriendlyName = $runFriendlyName
      TeamCityBuildConfigId = $teamCityBuildConfigId
    }
}

$jsonBody = (ConvertTo-Json $Body)
Write-Host "Posting automation run trigger to $AutomationReportingAPIBaseUrl/$runTriggerPostApiUrl ..." 
$response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$runTriggerPostApiUrl" -Headers $Headers -Method POST -Body $jsonBody -ContentType $postContentType
Write-Host "Posting automation run trigger successful!"


# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Post-ReportJobPerfStat.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa"  `
#           -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#           -ReportTitle "SAMPLE_REPORT_TITLE" `
#           -ReportJobTypeId "00000000-0000-0000-0008-000000000000" `
#           -ReportJobTypeName "ReportMeta"  `
#           -ReportJobStartTime "2016-07-19T14:48:19.0332528-07:00" `
#           -ReportJobEndTime "2016-07-19T14:48:19.0332528-07:00"  `
#           -TestExecutionStartDate "2016-07-19T14:48:19.0332528-07:00" `
#           -TestExecutionEndDate "2016-07-19T14:48:19.0332528-07:00" `
#           -BuildNumber "2.4.0.0" `
#			-TestCaseID "TC1841"  `
#           -EnvironmentId 3
# ---------------------------------------------------------------
param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Path to working directory (for eg. C:\Repositories\surveyor-qa)

  [Parameter(Mandatory=$true)]
  [String] $AutomationReportingAPIBaseUrl,         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087

  [Parameter(Mandatory=$true)]
  [String] $ReportTitle,

  [Parameter(Mandatory=$true)]
  [String] $ReportJobTypeId,

  [Parameter(Mandatory=$true)]
  [String] $ReportJobTypeName,

  [Parameter(Mandatory=$true)]
  [String] $ReportJobStartTime,

  [Parameter(Mandatory=$true)]
  [String] $ReportJobEndTime,

  [Parameter(Mandatory=$true)]
  [String] $TestExecutionStartDate,

  [Parameter(Mandatory=$true)]
  [String] $TestExecutionEndDate,

  [Parameter(Mandatory=$true)]
  [String] $BuildNumber,

  [Parameter(Mandatory=$true)]
  [String] $TestCaseID,

  [Parameter(Mandatory=$true)]
  [int] $EnvironmentId
)

$libFolder = "selenium-wd\lib"

. $BuildWorkingDir\$libFolder\Execute-WithRetry.ps1
. $BuildWorkingDir\$libFolder\Reporting-CommonFunctions.ps1

# ------------------------------------------------------------
# Post run results code starts from here
# ------------------------------------------------------------

$authToken = Execute-WithRetry -RetryDelay 1 -MaxRetries 5 { Get-ReportingAppAuthToken -BuildWorkingDir $BuildWorkingDir -AutomationReportingAPIBaseUrl $AutomationReportingAPIBaseUrl }

# Post perf stat info to API/PerfStatReportJobs url
$reportJobPerfStatApiUrl = "api/PerfStatReportJobs"
$postContentType = "application/json; charset=UTF-8"
$Headers = @{
    Token = $authToken
}

# Build post body. 
$Body = @{
    ReportTitle = $ReportTitle
    ReportJobTypeId = $ReportJobTypeId
    ReportJobTypeName = $ReportJobTypeName
    ReportJobStartTime = $ReportJobStartTime
    ReportJobEndTime = $ReportJobEndTime
    TestExecutionStartDate = $TestExecutionStartDate
    TestExecutionEndDate = $TestExecutionEndDate
    BuildNumber = $BuildNumber
	TestCaseID = $TestCaseID
    EnvironmentId = $EnvironmentId
}

$jsonBody = (ConvertTo-Json $Body)
Write-Host "Posting report job perf stat result to $AutomationReportingAPIBaseUrl ..." 
$response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$reportJobPerfStatApiUrl" -Headers $Headers -Method POST -Body $jsonBody -ContentType $postContentType
Write-Host "Posting report job perf stat successful!"
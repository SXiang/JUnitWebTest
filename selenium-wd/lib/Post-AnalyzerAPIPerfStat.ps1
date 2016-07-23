# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Post-AnalyzerAPIPerfStat.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa"  `
#           -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#           -APIName "Measurement"  `
#           -APIUrl "/api/analyzer/measurement"  `
#           -NumberOfSamples 10  `
#           -Average 1.2  `
#           -Median 1.2  `
#           -ResponseTime90Pctl 1.2  `
#           -ResponseTime95Pctl 1.2  `
#           -Responsetime99Pctl 1.2  `
#           -Min 1.2  `
#           -Max 1.2  `
#           -ErrorPercent 1.2  `
#           -ThroughputPerSec 1.2  `
#           -KBPerSec 1.2  `
#           -TestExecutionStartDate "2016-07-19T14:48:19.0332528-07:00"  `
#           -TestExecutionEndDate "2016-07-19T14:48:19.0332528-07:00"  `
#           -BuildNumber "2.4.0.0"  `
#			-TestCaseID "TC1841"  `
#           -EnvironmentID 3  `
# ---------------------------------------------------------------
param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Path to working directory (for eg. C:\Repositories\surveyor-qa)

  [Parameter(Mandatory=$true)]
  [String] $AutomationReportingAPIBaseUrl,         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087

  [Parameter(Mandatory=$true)]
  [String] $APIName,

  [Parameter(Mandatory=$true)]
  [String] $APIUrl,

  [Parameter(Mandatory=$true)]
  [int] $NumberOfSamples,

  [Parameter(Mandatory=$true)]
  [float] $Average,

  [Parameter(Mandatory=$true)]
  [float] $Median,

  [Parameter(Mandatory=$true)]
  [float] $ResponseTime90Pctl,

  [Parameter(Mandatory=$true)]
  [float] $ResponseTime95Pctl,

  [Parameter(Mandatory=$true)]
  [float] $Responsetime99Pctl,

  [Parameter(Mandatory=$true)]
  [float] $Min,

  [Parameter(Mandatory=$true)]
  [float] $Max,

  [Parameter(Mandatory=$true)]
  [float] $ErrorPercent,

  [Parameter(Mandatory=$true)]
  [float] $ThroughputPerSec,

  [Parameter(Mandatory=$true)]
  [float] $KBPerSec,

  [Parameter(Mandatory=$true)]
  [String] $TestExecutionStartDate,

  [Parameter(Mandatory=$true)]
  [String] $TestExecutionEndDate,

  [Parameter(Mandatory=$true)]
  [String] $BuildNumber,

  [Parameter(Mandatory=$true)]
  [String] $TestCaseID,
  
  [Parameter(Mandatory=$true)]
  [int] $EnvironmentID
)

$libFolder = "selenium-wd\lib"

. $BuildWorkingDir\$libFolder\Reporting-CommonFunctions.ps1

# ------------------------------------------------------------
# Post run results code starts from here
# ------------------------------------------------------------

$authToken = Get-ReportingAppAuthToken -BuildWorkingDir $BuildWorkingDir -AutomationReportingAPIBaseUrl $AutomationReportingAPIBaseUrl

# Post perf stat info to API/PerfStatAnalyzerAPIs url
$analyzerApiPerfStatApiUrl = "api/PerfStatAnalyzerAPIs"
$postContentType = "application/json; charset=UTF-8"
$Headers = @{
    Token = $authToken
}

# Build post body. 
$Body = @{
    APIName = $APIName
    APIUrl = $APIUrl
    NumberOfSamples = $NumberOfSamples
    Average = $Average
    Median = $Median
    ResponseTime90Pctl = $ResponseTime90Pctl
    ResponseTime95Pctl = $ResponseTime95Pctl
    Responsetime99Pctl = $Responsetime99Pctl
    Min = $Min
    Max = $Max
    ErrorPercent = $ErrorPercent
    ThroughputPerSec = $ThroughputPerSec
    KBPerSec = $KBPerSec
    TestExecutionStartDate = $TestExecutionStartDate
    TestExecutionEndDate = $TestExecutionEndDate
    BuildNumber = $BuildNumber
	TestCaseID = $TestCaseID
    EnvironmentID = $EnvironmentID
}

$jsonBody = (ConvertTo-Json $Body)
Write-Host "Posting analyzer API perf stat result to $AutomationReportingAPIBaseUrl ..." 
$response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$analyzerApiPerfStatApiUrl" -Headers $Headers -Method POST -Body $jsonBody -ContentType $postContentType
Write-Host "Posting analyzer API perf stat successful!"
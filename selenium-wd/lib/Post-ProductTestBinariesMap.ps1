# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Post-ProductTestBinariesMap.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa"  `
#           -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#           -TestBinaryFileName "scommon-2.4.79.4e7e36a.jar" `
#           -TestBinaryMajor "2" `
#           -TestBinaryMinor "4"  `
#           -TestBinaryBuildNumber "79" `
#           -TestBinaryGitHash "4e7e36a"  `
#           -TestBranch "master" `
#           -EnvironmentUrl "https://p3sqaauto.picarro.com" `
#           -EnvironmentDBName "SurveyorSQAAuto_blankDB_20161202" `
#           -EnvironmentBuildVersion "2.4.0.aa6e555" `
#           -BuildDate "2016-12-13" `
#			-BuildSuccess "true"
# ---------------------------------------------------------------
param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Path to working directory (for eg. C:\Repositories\surveyor-qa)

  [Parameter(Mandatory=$true)]
  [String] $AutomationReportingAPIBaseUrl,         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087

  [Parameter(Mandatory=$true)]
  [String] $TestBinaryFileName,

  [Parameter(Mandatory=$true)]
  [String] $TestBinaryMajor,

  [Parameter(Mandatory=$true)]
  [String] $TestBinaryMinor,

  [Parameter(Mandatory=$true)]
  [String] $TestBinaryBuildNumber,

  [Parameter(Mandatory=$true)]
  [String] $TestBinaryGitHash,

  [Parameter(Mandatory=$true)]
  [String] $TestBranch,

  [Parameter(Mandatory=$true)]
  [String] $EnvironmentUrl,

  [Parameter(Mandatory=$true)]
  [String] $EnvironmentDBName,

  [Parameter(Mandatory=$true)]
  [String] $EnvironmentBuildVersion,

  [Parameter(Mandatory=$true)]
  [String] $BuildDate,

  [Parameter(Mandatory=$false)]
  [String] $BuildSuccess
)

$libFolder = "selenium-wd\lib"

. $BuildWorkingDir\$libFolder\Execute-WithRetry.ps1
. $BuildWorkingDir\$libFolder\Reporting-CommonFunctions.ps1

# ------------------------------------------------------------
# Post run results code starts from here
# ------------------------------------------------------------

$authToken = Execute-WithRetry -RetryDelay 1 -MaxRetries 5 { Get-ReportingAppAuthToken -BuildWorkingDir $BuildWorkingDir -AutomationReportingAPIBaseUrl $AutomationReportingAPIBaseUrl }

# Post product/test binaries mapping to API/ProductTestBinariesMap url
$productTestBinariesMapApiUrl = "api/ProductTestBinariesMap"
$postContentType = "application/json; charset=UTF-8"
$Headers = @{
    Token = $authToken
}

# Build post body. 
$Body = @{
    TestBinaryFileName = $TestBinaryFileName
    TestBinaryMajor = $TestBinaryMajor
    TestBinaryMinor = $TestBinaryMinor
    TestBinaryBuildNumber = $TestBinaryBuildNumber
    TestBinaryGitHash = $TestBinaryGitHash
    TestBranch = $TestBranch
    EnvironmentUrl = $EnvironmentUrl
    EnvironmentDBName = $EnvironmentDBName
    EnvironmentBuildVersion = $EnvironmentBuildVersion
    BuildDate = $BuildDate
    BuildSuccess = $BuildSuccess
}

$jsonBody = (ConvertTo-Json $Body)
Write-Host "Posting product test binaries map to $AutomationReportingAPIBaseUrl ... Body -> $jsonBody" 
$response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$productTestBinariesMapApiUrl" -Headers $Headers -Method POST -Body $jsonBody -ContentType $postContentType
Write-Host "Posting product test binaries map successful!"
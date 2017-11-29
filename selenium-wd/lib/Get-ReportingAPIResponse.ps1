# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Get-ReportingAPIResponse.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa" `
#           -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#           -APIRelativeUrl ""
#             
# ---------------------------------------------------------------
param
(
  [Parameter(Mandatory=$false)]
  [String] $BuildWorkingDir="C:\Repositories\surveyor-qa",                       # Path to working directory (for eg. C:\Repositories\surveyor-qa)

  [Parameter(Mandatory=$false)]
  [String] $AutomationReportingAPIBaseUrl="http://localhost:63087",         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087

  [Parameter(Mandatory=$false)]
  [String] $APIRelativeUrl="api/EnvironmentBuilds?environmentName=SQAAuto",

  [Parameter(Mandatory=$false)]
  [String] $Method="GET",

  [Parameter(Mandatory=$false)]
  [String] $Body=""
)

$libFolder = "selenium-wd\lib"

. $BuildWorkingDir\$libFolder\Execute-WithRetry.ps1
. $BuildWorkingDir\$libFolder\Reporting-CommonFunctions.ps1
    
[String] $HtmlAgilityDllPath = "$BuildWorkingDir\$libFolder\HtmlAgilityPack.dll"

$authToken = Execute-WithRetry -RetryDelay 1 -MaxRetries 5 { Get-ReportingAppAuthToken -BuildWorkingDir $BuildWorkingDir -AutomationReportingAPIBaseUrl $AutomationReportingAPIBaseUrl }

# ------------------------------------------------------------
# Get results code starts from here
# ------------------------------------------------------------

$Headers = @{
    Token = $authToken
}

if ($Method -eq "POST") {
    $response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$APIRelativeUrl" -Headers $Headers -Method $Method -Body $Body -ContentType "application/json"
} else {
    $response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$APIRelativeUrl" -Headers $Headers -Method $Method
}

$response.Content
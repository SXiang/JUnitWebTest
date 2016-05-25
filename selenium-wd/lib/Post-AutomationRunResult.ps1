# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Post-AutomationRunResult.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa"
#           -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#           -HtmlResultFilePath "C:\Repositories\surveyor-qa\selenium-wd\reports\report-Local-SQAAuto-surveyor.regression.source.DriverViewPageTest.html"
#             
# ---------------------------------------------------------------
param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Path to AutomationReporting.Util.dll

  [Parameter(Mandatory=$true)]
  [String] $AutomationReportingAPIBaseUrl,         # Path to AutomationReporting.Util.dll

  [Parameter(Mandatory=$true)]
  [String] $HtmlResultFilePath                     # Path to report HTML file
)

# ------------------------------------------------------------
# Converts datetime to Unix epoch time
# ------------------------------------------------------------
function ToUnixTime([System.DateTime] $dateTime) {
    $referenceTime = New-Object System.DateTime -ArgumentList (1970,1,1,0,0,0,0)
    [System.TimeSpan]$timeSpan = $dateTime - $referenceTime;
    $timeSpan.TotalSeconds
}

$apiUsername = "api"
$apiPassword = "AP1_Us3r@#"    

$libFolder = "selenium-wd\lib"
[String] $HtmlAgilityDllPath = "$BuildWorkingDir\$libFolder\HtmlAgilityPack.dll"
[String] $AutomationReportingUtilDllPath = "$BuildWorkingDir\$libFolder\AutomationReporting.Util.dll"

# ------------------------------------------------------------
# Post run results code starts from here
# ------------------------------------------------------------

# Get the api authentication token
[Reflection.Assembly]::LoadFile($AutomationReportingUtilDllPath) | out-null
$cryptoString = [AutomationReporting.Util.CryptoFunctions]::CreateExpiringUserPassCryptoString($apiUsername, $apiPassword)
$authenticationTokenUrl = "get/token"
$basicAuthValue = "Basic $cryptoString"
$Headers = @{
    Authorization = $basicAuthValue
}
$response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$authenticationTokenUrl" -Headers $Headers -Method POST
$authToken = $response.Headers["Token"]


# Post run result to API/RunResults url
$runResultPostApiUrl = "api/RunResults"
$postContentType = "application/json; charset=UTF-8"
$Headers = @{
    Token = $authToken
}

# Build post parameters. Read HTML as string from .html file.
# Read StartEpoch,EndEpoch and Environment from .html file.
$htmlString = [System.IO.File]::ReadAllText($HtmlResultFilePath)
Add-Type -Path $HtmlAgilityDllPath
$htmlDoc = New-Object HtmlAgilityPack.HtmlDocument
$htmlDoc.LoadHTML($htmlString)
$xpathStartDate = "//*[@class='test-started-time label green lighten-2 text-white']"
$xpathEndDate = "//*[@class='test-ended-time label red lighten-2 text-white']"    
$xpathEnvironment = "//*[@id='dashboard-view']/div[3]/div/div/table/tbody/tr[5]/td[2]"
$environment = $htmlDoc.DocumentNode.SelectSingleNode($xpathEnvironment).InnerText
$startDate = $htmlDoc.DocumentNode.SelectSingleNode($xpathStartDate).InnerText
$endDate = $htmlDoc.DocumentNode.SelectSingleNode($xpathEndDate).InnerText
$startDateTime = [datetime]$startDate
$endDateTime = [datetime]$endDate
$epochStartTime = ToUnixTime -dateTime $startDateTime
$epochEndTime = ToUnixTime -dateTime $endDateTime

$Body = @{
  Id = 0       # sample ID. Ignored on server-side 
  StartEpoch = $epochStartTime
  EndEpoch = $epochEndTime
  Environment = $environment
  HTMLString = $htmlString
}

$jsonBody = (ConvertTo-Json $Body)
Write-Host "Posting automation run result from file $HtmlResultFilePath to $AutomationReportingAPIBaseUrl ..." 
$response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$runResultPostApiUrl" -Headers $Headers -Method POST -Body $jsonBody -ContentType $postContentType
Write-Host "Posting run result successful!"
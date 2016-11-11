# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Post-AutomationRunResult.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa" `
#           -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#           -HtmlResultFilePath "C:\Repositories\surveyor-qa-US3236\selenium-wd\reports\Regression\report-Local-SQAAuto-surveyor.regression.source.ComplianceReportsPageTest7.html" `
#           -RunUUID "5"
#             
# ---------------------------------------------------------------
param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Path to working directory (for eg. C:\Repositories\surveyor-qa)

  [Parameter(Mandatory=$true)]
  [String] $AutomationReportingAPIBaseUrl,         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087

  [Parameter(Mandatory=$true)]
  [String] $HtmlResultFilePath,                     # Path to report HTML file

  [Parameter(Mandatory=$true)]
  [String] $RunUUID
)

$libFolder = "selenium-wd\lib"

. $BuildWorkingDir\$libFolder\Execute-WithRetry.ps1
. $BuildWorkingDir\$libFolder\Reporting-CommonFunctions.ps1
    
[String] $HtmlAgilityDllPath = "$BuildWorkingDir\$libFolder\HtmlAgilityPack.dll"

# ------------------------------------------------------------
# Post run results code starts from here
# ------------------------------------------------------------

$authToken = Execute-WithRetry -RetryDelay 1 -MaxRetries 5 { Get-ReportingAppAuthToken -BuildWorkingDir $BuildWorkingDir -AutomationReportingAPIBaseUrl $AutomationReportingAPIBaseUrl }

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

$lRunUUID = [long]$RunUUID

# Get TestClass, Compute PassCount and FailCount from the HTML document.
$testNodes = $htmlDoc.DocumentNode.SelectNodes("//*[@id='test-collection']/li")
$totalTestCount = $testNodes.Count
$passCount = 0
$failCount = 0
for ($i=1; $i -le $totalTestCount; $i++) {
    $tcName = $htmlDoc.DocumentNode.SelectSingleNode("//*[@id='test-collection']/li[$i]/div[1]/span[1]").InnerText
    $tcName = $tcName.Replace(",", ":")
    $tcResult = $htmlDoc.DocumentNode.SelectSingleNode("//*[@id='test-collection']/li[$i]/div[1]/span[2]").InnerText
    if (([string]$tcResult).ToLowerInvariant().Equals("pass")) {
        $passCount++
    } else {
        $failCount++
    }
}

$className = "<Unknown>"
$fileName = [string]([string]$HtmlResultFilePath).Replace(".html", "");
$fileParts = $fileName.Split('-');
if ($fileParts -ne $null -and $fileParts.Length -gt 0)
{
    $fileName = $fileParts[$fileParts.Length-1];
    $className = $fileName;
    $fileParts = $fileName.Split('.');
    if ($fileParts -ne $null -and $fileParts.Length -gt 0)
    {
        $className = $fileParts[$fileParts.Length-1];
    }
}

$Body = @{
  Id = 0       # sample ID. Ignored on server-side 
  StartEpoch = $epochStartTime
  EndEpoch = $epochEndTime
  Environment = $environment
  HTMLString = $htmlString
  RunUUID = $lRunUUID
  TestClass = $className
  PassCount = $passCount
  FailCount = $failCount
}

$jsonBody = (ConvertTo-Json $Body)
Write-Host "Posting automation run result from file $HtmlResultFilePath to $AutomationReportingAPIBaseUrl ..." 
Write-Host "Post Body : $jsonBody"
$response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$runResultPostApiUrl" -Headers $Headers -Method POST -Body $jsonBody -ContentType $postContentType
Write-Host "Posting run result successful!"
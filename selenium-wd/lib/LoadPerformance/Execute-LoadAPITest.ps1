# ---------------------------------------------------------------------------------------------------
# DESCRIPTION:
#   Use script to trigger API and record test results in automation reporting DB.
#
# SAMPLE USAGE:
#   .\Execute-LoadAPITest.ps1 -BaseWorkingDir "c:\Repositories\surveyor-qa" `
#       -AutomationReportingAPIBaseUrl "http://localhost:63087" `
#       -ABExeFolder "C:\Tools\Apache Benchmark\Apache24\bin" `
#       -ApiEndpoint "http://30.30.150.198:8080/geoserver/PICARRO/ows?service=WFS&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=PICARRO:Asset" `
#       -Method "POST" `
#       -ContentType "application/x-www-form-urlencoded" `
#       -RequestBody "username=<replace_with_username>&password=<replace_with_password>" `
#       -ResponseContentLength 224299 `
#       -NumConcurrentRequests 100 `
#       -NumRequestsInOneSession 10 `
#       -NumPrimingRuns 1 `
#       -RunUUID 0 `
#       -TestCaseName "GeoServer-DriverViewAPI-01" `
#       -OutputFolder "C:\QATestLogs"
#
# ---------------------------------------------------------------------------------------------------

param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Source root directory. Eg. C:\Repositories\surveyor-qa

  [Parameter(Mandatory=$true)]
  [String] $AutomationReportingAPIBaseUrl,         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087

  [Parameter(Mandatory=$true)]                     
  [String] $ABExeFolder,                           # Folder containing ab.exe

  [Parameter(Mandatory=$true)]
  [String] $ApiEndpointUrl,                        # API url to test

  [Parameter(Mandatory=$true)]
  [String] $Method,                                # Http method. Eg. POST,GET 

  [Parameter(Mandatory=$true)]
  [String] $ContentType,                           # ContentType for the request. Eg. application/x-www-form-urlencoded

  [Parameter(Mandatory=$true)]
  [String] $RequestBody,                           # Request Body.   

  [Parameter(Mandatory=$true)]
  [Int64] $ResponseContentLength,                  # Expected response content length. Test is considered FAIL if response content length does NOT match. This check is made to avoid recording result data for incorrect responses.

  [Parameter(Mandatory=$true)]
  [Int32] $NumConcurrentRequests,                  # Number of concurrent requests for API load test.  

  [Parameter(Mandatory=$true)]
  [Int32] $NumRequestsInOneSession,                # Number of requests that are considered part of single session.  

  [Parameter(Mandatory=$false)]
  [Int32] $NumPrimingRuns=1,                       # Number of priming runs that are done before executing the actual run for recording API response metrics. Results from priming runs are ignored.

  [Parameter(Mandatory=$false)]
  [Int64] $RunUUID=0,                              # When executing in CI the runTriggerId to identify the run. Else a unique ID to identify the run.

  [Parameter(Mandatory=$true)]
  [String] $TestCaseName,                          # TestCase ID or TestCase name

  [Parameter(Mandatory=$true)]
  [String] $OutputFolder                           # Output folder where results/logs from test will be written
)

function New-GuidNoDashes() {
    [guid]::NewGuid().ToString().Replace("-", "").ToUpper()
}

$guid = New-GuidNoDashes
$outResultFile = "$OutputFolder\ab.output-$guid.txt"
$OutDataFile = "$OutputFolder\loadmetrics.out-$guid.data"

$VERBOSE_LEVEL = 2

$script:RawResponseLines = New-Object System.Collections.ArrayList
$script:ContentLength = 0
$script:SuccessResponseCount = 0
$script:ABStartTime = $null
$script:ABEndTime = $null

function Parse-ABDate($dateString) {
    [System.DateTime]::ParseExact($dateString, "ddd MMM dd HH:mm:ss yyyy", [System.Globalization.CultureInfo].InvariantCulture)
}

function Get-RawResult() {
    $rawResult = ""
    $script:RawResponseLines | %{
        $line = $_
        $rawResult += "$line`r`n"
    }

    $rawResult
}

function GnuPlotTSV-ToJsonArray($TsvFile, $TestCaseResultId) {
    $arrList = New-Object System.Collections.ArrayList
    Import-Csv -Path $tsvFile -Delimiter "`t" | %{
        $row = $_
        $obj = New-Object PSObject -Property @{
            Id = 0
            LoadStatAPITestCaseResultId = $TestCaseResultId
            StartTime = (Parse-ABDate -dateString $row.starttime).ToUniversalTime().ToString("u")
            StartEpochTime = [Int64]$row.seconds
            CTime = [Int32]$row.ctime
            DTime = [Int32]$row.dtime
            TTime = [Int32]$row.ttime
            Wait = [Int32]$row.wait
        }

        $null = $arrList.Add($obj)
    }

    $arr = $arrList.ToArray()
    ConvertTo-Json $arr
}

function VerifyExtract-Response($outLogFile) {
    $script:SuccessResponseCount = 0
    $foundStart = $false
    gc $outLogFile | %{
        $line = $_

        if ($foundStart) {
            $null = $script:RawResponseLines.Add($line)
        } else {
            if ($line -match "HTTP/\d\.\d\s+(\d+)\s+(.+)") {
                $responseCode = $Matches[1]
                $responseStatus = $Matches[2]
                if (($responseCode -eq "200") -and ($responseStatus -eq "OK")) {
                    $script:SuccessResponseCount++            
                }
            } else {
                if (-not $foundStart) {
                    if ($line -match "Server Software:\s+Apache-Coyote/\d+\.\d+") {
                        $foundStart = $true
                        $null = $script:RawResponseLines.Add($line)
                    }
                }
            }
        }
    }

    $allResponsesOK = $script:SuccessResponseCount -eq $NumConcurrentRequests

    $script:ContentLength = 0
    $script:RawResponseLines | %{
        $line = $_
        if ($contentLength -eq 0) {
            if ($line -match "Document Length:\s+(\d+)\s+bytes") {
                $script:ContentLength = $Matches[1]
            }
        }
    }

    $contentLengthCorrect = $script:ContentLength -eq $ResponseContentLength

    $allResponsesOK -and $contentLengthCorrect -and $foundStart
}

# ----------------------------------------------------------------------------------------------
# 1. Run Apache benchmark and gather API perf metrics.
# ----------------------------------------------------------------------------------------------

Write-Host "Running Apache Benchmark for API -> $ApiEndpointUrl"

$currDir = $pwd
cd $ABExeFolder

if ($Method -eq "POST") {
    $ReqBodyFile = New-Item "$BuildWorkingDir\selenium-wd\data\reqbody-$guid.txt"
    Write-Host "Creating request body file - '$ReqBodyFile' with content -> $RequestBody"
    Add-Content $ReqBodyFile $RequestBody
    Write-Host "Created request body file - '$ReqBodyFile'"

    if ($NumPrimingRuns -gt 0) {
        Write-Host "Executing $NumPrimingRuns priming run(s) ..."
        .\ab.exe -n $NumPrimingRuns -c $NumPrimingRuns -p "$ReqBodyFile" -q -T $ContentType -k "$ApiEndpointUrl"
        Write-Host "Done with priming run(s)"
    }

    Write-Host "Executing Apache Benchmark run [concurrency=$NumConcurrentRequests, requests=$NumRequestsInOneSession]"
    $script:ABStartTime = [System.DateTime]::UtcNow
    .\ab.exe -n $NumConcurrentRequests -c $NumRequestsInOneSession -v $VERBOSE_LEVEL -g "$OutDataFile" -p "$ReqBodyFile" -q -T $ContentType -k "$ApiEndpointUrl" > "$outResultFile"
    $script:ABEndTime = [System.DateTime]::UtcNow
    Write-Host "Done with Apache Benchmark run"
    
    Write-Host "Deleting request body file - '$ReqBodyFile' ..."
    Remove-Item $ReqBodyFile -Force
    Write-Host "Deleted request body file - '$ReqBodyFile'"
} else {
    $script:ABStartTime = [System.DateTime]::UtcNow
    .\ab.exe -n $NumConcurrentRequests -c $NumRequestsInOneSession -v $VERBOSE_LEVEL -g "$OutDataFile" -q -T $ContentType -k "$ApiEndpointUrl" > "$outResultFile"
    $script:ABEndTime = [System.DateTime]::UtcNow
}

cd $currDir

Write-Host "Done with API call."

# ----------------------------------------------------------------------------------------------
# 2. Ensure API responses are success.
# ----------------------------------------------------------------------------------------------

$success = VerifyExtract-Response -outLogFile $outResultFile
if (-not $success) {
    Write-Host "ERROR: Response verification failed. Found total = ${script:SuccessResponseCount} success responses. Expected content length=$ResponseContentLength, found content length=${script:ContentLength} "
    exit
} 

# ----------------------------------------------------------------------------------------------
# 3. Post raw load test results to results table in Automation DB.
# ----------------------------------------------------------------------------------------------

$libFolder = "selenium-wd\lib"

. "$BuildWorkingDir\$libFolder\Execute-WithRetry.ps1"
. "$BuildWorkingDir\$libFolder\Reporting-CommonFunctions.ps1"
. "$BuildWorkingDir\$libFolder\HelperScripts\DateTimeHelpers.ps1"

$loadStatAPITestCaseResultsUrl = "api/LoadStatAPITestCaseResults"

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

$ABStart = $script:ABStartTime.ToString("u")
$ABEnd = $script:ABEndTime.ToString("u")
$Body = @{
    Id = 0       # sample ID. Ignored on server-side 
    RunUUID = $RunUUID
    TestCaseName = $TestCaseName
    StartTime = "$ABStart"
    EndTime = "$ABEnd"
    RawResult = Get-RawResult
}

$jsonBody = (ConvertTo-Json $Body)
Write-Host "Posting load API stats result to $AutomationReportingAPIBaseUrl/$loadStatAPITestCaseResultsUrl ..." 
$response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$loadStatAPITestCaseResultsUrl" -Headers $Headers -Method POST -Body $jsonBody -ContentType $postContentType
$json = $response.Content | ConvertFrom-Json
Write-Host "Posting load API stats result successful! Response -> $json"

$apiResultId = $json.Id

Write-Host "apiResultId = $apiResultId"

# ----------------------------------------------------------------------------------------------
# 4. Post raw result data to results data table in automation DB.
# ----------------------------------------------------------------------------------------------

$loadStatAPITestCaseDatasUrl = "api/LoadStatAPITestCaseDatas"

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

$jsonBody = GnuPlotTSV-ToJsonArray -TsvFile $OutDataFile -TestCaseResultId $apiResultId

Write-Host "Posting load API stats data to $AutomationReportingAPIBaseUrl/$loadStatAPITestCaseDatasUrl ..." 
$response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$loadStatAPITestCaseDatasUrl" -Headers $Headers -Method POST -Body $jsonBody -ContentType $postContentType
$responseContent = $response.Content

Write-Host "Posting load API stats data successful! Response -> $responseContent"

# ----------------------------------------------------------------------------------------------
# 5. Record test result in success marker file.
# ----------------------------------------------------------------------------------------------

$successMarkerFile = New-Item "$OutputFolder\LoadTest-$RunUUID-${TestCaseName}.result" -Force
Add-Content $successMarkerFile "LoadStatAPITestCaseResultId = $apiResultId"
Add-Content $successMarkerFile "OutputResultFile = $outResultFile"
Add-Content $successMarkerFile "OutputResultDataFile = $OutDataFile"

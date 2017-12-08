<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION:
  - This script can be used to generate HTML report post load test execution.

 Sample Run Script:
   .\Generate-LoadTestRunReport.ps1 `
        -TestStartTime "12/05/2017 5:45:56 PM"  `
        -TestDataProviderFile "C:\Repositories\surveyor-qa\selenium-wd\tests\surveyor\dataprovider\LoadAPITestMultipleCustomersDataProvider.java"

 NOTES:
   1. If the APIs being load tested give failures when running the load tests then pass result file will NOT be written.
      If wanting to generate report in case when the APIs under test are failing comment out the 'exit' line on 'VerifyExtract-Response' 
      and success result file will be written even if there are failures.

----------------------------------------------------------------------------------------------------------------------------------#>
param(
   [Parameter(Mandatory=$true)]
   $TestStartTime,

  [Parameter(Mandatory=$true)]
   $TestDataProviderFile
)

$OUTFILE = New-item "C:\temp\LoadTestResult.html" -ItemType File -Force

$StartDate=[datetime] $TestStartTime
$TEST_LOG_FOLDER = "C:\QATestLogs"
$files = Get-ChildItem "$TEST_LOG_FOLDER\*" -Include *.result

$script:expectedContentLenMap = @{}

function Read-ExpectedContentLengths() {
    $tcName = ""
    gc $TestDataProviderFile | %{
        [String]$line = $_
        $expCL = ""
        if ($line.Contains("final String apiName")) {            
            if ($line -match "\s+final String apiName_\d+\s+=\s+""(.+)"";") {
                $tcName = $Matches[1]
            }
        } elseif ($line.Contains("final Integer expectedResponseContentLength")) {
            if ($line -match "\s+final Integer expectedResponseContentLength_\d+\s+=\s+(\d+);") {
                $expCL = $Matches[1]
                $script:expectedContentLenMap.set_item($tcName, $expCL)
                $tcName = ""
            }
        }
    }
}

function Write-ReportHeader() {
    add-content $OUTFILE "<html>"
    add-content $OUTFILE "    <head>"
    add-content $OUTFILE "        <title>"
    add-content $OUTFILE "            Test Report"
    add-content $OUTFILE "        </title>"
    add-content $OUTFILE "        <style type=""text/css"">"
    add-content $OUTFILE "        .result-table {"
    add-content $OUTFILE "            border: 1px solid black;"
    add-content $OUTFILE "            width: 100%;"
    add-content $OUTFILE "        }"
    add-content $OUTFILE "        .result-table-header-cell {"
    add-content $OUTFILE "            border-bottom: 1px solid black;"
    add-content $OUTFILE "            background-color: silver;"
    add-content $OUTFILE "        }"
    add-content $OUTFILE "        .result-step-command-cell {"
    add-content $OUTFILE "            border-bottom: 1px solid gray;"
    add-content $OUTFILE "        }"
    add-content $OUTFILE "        .result-step-description-cell {"
    add-content $OUTFILE "            border-bottom: 1px solid gray;"
    add-content $OUTFILE "        }"
    add-content $OUTFILE "        .result-step-result-cell-ok {"
    add-content $OUTFILE "            border-bottom: 1px solid gray;"
    add-content $OUTFILE "            background-color: green;"
    add-content $OUTFILE "        }"
    add-content $OUTFILE "        .result-step-result-cell-failure {"
    add-content $OUTFILE "            border-bottom: 1px solid gray;"
    add-content $OUTFILE "            background-color: red;"
    add-content $OUTFILE "        }"
    add-content $OUTFILE "        .result-step-result-cell-notperformed {"
    add-content $OUTFILE "            border-bottom: 1px solid gray;"
    add-content $OUTFILE "            background-color: white;"
    add-content $OUTFILE "        }"
    add-content $OUTFILE "        .result-step-result-cell-warn {"
    add-content $OUTFILE "            border-bottom: 1px solid gray;"
    add-content $OUTFILE "            background-color: orange;"
    add-content $OUTFILE "        }"
    add-content $OUTFILE "        .result-describe-cell {"
    add-content $OUTFILE "            background-color: tan;"
    add-content $OUTFILE "            font-style: italic;"
    add-content $OUTFILE "        }"
    add-content $OUTFILE "        .test-cast-status-box-ok {"
    add-content $OUTFILE "            border: 1px solid black;"
    add-content $OUTFILE "            float: left;"
    add-content $OUTFILE "            margin-right: 10px;"
    add-content $OUTFILE "            height: 25px;"
    add-content $OUTFILE "            background-color: green;"
    add-content $OUTFILE "        }"
    add-content $OUTFILE "        </style>"
    add-content $OUTFILE "    </head>"
    add-content $OUTFILE "    <body>"
    add-content $OUTFILE "        <h1 class=""test-results-header"">"
    add-content $OUTFILE "            Load Test Report"
    add-content $OUTFILE "        </h1>"
    add-content $OUTFILE ""
    add-content $OUTFILE "        <table class=""result-table"" cellspacing=""0"">"
    add-content $OUTFILE "            <thead>"
    add-content $OUTFILE "                <tr>"
    add-content $OUTFILE "                    <td class=""result-table-header-cell"">"
    add-content $OUTFILE "                        Test Case"
    add-content $OUTFILE "                    </td>"
    add-content $OUTFILE "                    <td class=""result-table-header-cell"">"
    add-content $OUTFILE "                        Status Code Counts"
    add-content $OUTFILE "                    </td>"
    add-content $OUTFILE "                    <td class=""result-table-header-cell"">"
    add-content $OUTFILE "                        Response Content Length"
    add-content $OUTFILE "                    </td>"
    add-content $OUTFILE "                    <td class=""result-table-header-cell"">"
    add-content $OUTFILE "                        Time for tests (N concurrent requests)"
    add-content $OUTFILE "                    </td>"
    add-content $OUTFILE "                    <td class=""result-table-header-cell"">"
    add-content $OUTFILE "                        Complete Requests"
    add-content $OUTFILE "                    </td>"
    add-content $OUTFILE "                    <td class=""result-table-header-cell"">"
    add-content $OUTFILE "                        Failed Requests"
    add-content $OUTFILE "                    </td>"
    add-content $OUTFILE "                </tr>"
    add-content $OUTFILE "            </thead>"
    add-content $OUTFILE "            <tbody>"
}

function Write-ResultRow($resultRow) {
    [String]$TestName         = $resultRow.TestName    [String]$StatusCodeCounts = $resultRow.StatusCodeCounts    [String]$ResponseLength   = $resultRow.ResponseLength    [String]$TimeForTests     = $resultRow.TimeForTests    [String]$CompleteRequests = $resultRow.CompleteRequests    [String]$FailedRequests   = $resultRow.FailedRequests    [String]$RawFileFullPath  = $resultRow.RawFileFullPath

    $fPath = $RawFileFullPath.Replace("\", "/")
    $fPath = "file:///$fPath"

    add-content $OUTFILE "                <tr class=""result-step-row result-step-row-altone"">"
    add-content $OUTFILE "                    <td class=""result-step-command-cell"">"
    add-content $OUTFILE "                        $TestName"
    add-content $OUTFILE "                    </td>"

    if ($StatusCodeCounts.Contains("OTHER = [0]")) {
        add-content $OUTFILE "                    <td class=""result-step-result-cell-ok"">"
    } else {
        add-content $OUTFILE "                    <td class=""result-step-result-cell-failure"">"
    }

    add-content $OUTFILE "                        $StatusCodeCounts"
    add-content $OUTFILE "                    </td>"
    
    $expRespLen = $script:expectedContentLenMap.get_item($TestName)
    $actRespLen = $ResponseLength.Replace(" bytes", "")
    if ($actRespLen -eq "0") {
        add-content $OUTFILE "                    <td class=""result-step-result-cell-failure"">"
    } elseif ($actRespLen -ne $expRespLen) {
        add-content $OUTFILE "                    <td class=""result-step-result-cell-warn"">"
    } else {
        add-content $OUTFILE "                    <td class=""result-step-description-cell"">"
    }
    
    add-content $OUTFILE "                        Actual=$ResponseLength [Expected=$expRespLen]"
    add-content $OUTFILE "                    </td>"
    
    
    add-content $OUTFILE "                    <td class=""result-step-description-cell"">"
    add-content $OUTFILE "                        $TimeForTests"
    add-content $OUTFILE "                    </td>"
    add-content $OUTFILE "                    <td class=""result-step-description-cell"">"
    add-content $OUTFILE "                        $CompleteRequests"
    add-content $OUTFILE "                    </td>"

    if ($FailedRequests -eq "0") {
        add-content $OUTFILE "                    <td class=""result-step-result-cell-ok"">"
    } else {
        add-content $OUTFILE "                    <td class=""result-step-result-cell-failure"">"
    }

    add-content $OUTFILE "                        <a href=""$fPath"">$FailedRequests</a>"
    add-content $OUTFILE "                    </td>"
    add-content $OUTFILE "                </tr>"
}

function Write-ReportFooter() {
    add-content $OUTFILE "            </tbody>"
    add-content $OUTFILE "        </table>"
    add-content $OUTFILE "    </body>"
    add-content $OUTFILE "</html>"
}

$negStatusCodes = New-Object System.Collections.ArrayList

Read-ExpectedContentLengths

Write-ReportHeader

$files | ? { $_.LastWriteTime -gt $StartDate } | %{
    $file = $_
    [String] $filename = $file.Name
    [String] $fileFullpath = $file.FullName
    $lastWriteTime = $file.LastWriteTime.ToShortDateString() + " " + $file.LastWriteTime.ToLongTimeString()

    if ($filename.StartsWith("LoadTest")) {
        Write-Host "Processing file -> $fileFullpath"                if ($filename -match "LoadTest\-\d+\-(.+)\.result") {            $TestName = $Matches[1]        }        $OutputResultFile = ""        $OutputResultDataFile = ""        gc $fileFullpath | %{            $line = $_            if ($line -match "OutputResultFile\s+=\s+(.+)") {                $OutputResultFile = $Matches[1]            } elseif ($line -match "OutputResultDataFile\s+=\s+(.+)") {                $OutputResultDataFile = $Matches[1]            }        }        $scCountPositives = 0        $scCountNegatives = 0        $ResponseLength = ""        $TimeForTests = ""        $CompleteRequests = ""        $FailedRequests = ""        if (Test-path $OutputResultFile) {            gc $OutputResultFile | %{                [string]$line = $_                if ($line.StartsWith("HTTP/")) {                    if ($line -match "HTTP/\d+\.\d+ 200 OK") {                        $scCountPositives++                            } else {                        $scCountNegatives++                        $null = $negStatusCodes.Add("[$filename]: $line")                    }                } elseif ($line.StartsWith("Document Length:")) {                    if ($line -match "Document Length:\s+(\d+.+)") {                        $ResponseLength = $Matches[1]                    }                } elseif ($line.StartsWith("Time taken for tests:")) {                    if ($line -match "Time taken for tests:\s+(\d+\.?\d+.+)") {                        $TimeForTests = $Matches[1]                    }                } elseif ($line.StartsWith("Complete requests:")) {                    if ($line -match "Complete requests:\s+(\d+)") {                        $CompleteRequests = $Matches[1]                    }                } elseif ($line.StartsWith("Failed requests:")) {                    if ($line -match "Failed requests:\s+(\d+)") {                        $FailedRequests = $Matches[1]                    }                }            }        }        $StatusCodeCounts = "HTTP/1.1 200 OK = [$scCountPositives], OTHER = [$scCountNegatives]"        $rowObj = New-Object PSObject -Property @{                            TestName         = $TestName                StatusCodeCounts = $StatusCodeCounts                ResponseLength   = $ResponseLength                TimeForTests     = $TimeForTests                CompleteRequests = $CompleteRequests                FailedRequests   = $FailedRequests                RawFileFullPath  = $OutputResultFile            }  
        
        Write-host "Writing row result -> $rowObj"

        Write-ResultRow -resultRow $rowObj
    }
}

Write-ReportFooter

ii $OUTFILE

# print list of negative status codes.
$ERRORLOG = New-Item "C:\temp\errorsInLoadTest.txt" -ItemType File -Force
$negStatusCodes | %{
    $l = $_
    Add-Content $ERRORLOG $l
}

ii $ERRORLOG
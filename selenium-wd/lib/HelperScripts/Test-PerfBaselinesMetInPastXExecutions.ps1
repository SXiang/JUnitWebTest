##########################################################################################################
# PRE-REQUISITES FOR THIS SCRIPT:
#  1. PowerShell 5.0 or above
#     PackageManagement PowerShell Modules required, which is part of PS 5.0
#  2. Install-Module Statistics
# 
# DESCRIPTION:
#  This is a testing script to see the +/- delta of ProcessingTime diffs when updating baselines.
#
# USAGE:
#  Test-PerfBaselinesMetInPastXExecutions.ps1 -pastXDays 5  `
#        -folderWithBaselineCSVs "C:\temp\PerfTests-UpdatedBaselines-20170531-90PercentilePlus2SigmaUpdates"   `
#        -reportJobTypesToVerify ""  `  # if left EMPTY all job types will be verified
#        -isRunningOnServer:$true
#
#  Good set of baselines to satisfy following conditions:  
#   1. Small percentage of +ve deltas for individual test case.
#   2. Not very high percentage of -ve deltas.
#
##########################################################################################################

param(
    [Parameter(Mandatory=$true)]
    [int] $pastXDays,

    [Parameter(Mandatory=$true)]
    [string] $folderWithBaselineCSVs,

    [Parameter(Mandatory=$true)]
    [string] $reportJobTypesToVerify,

    [Parameter(Mandatory=$true)]
    [switch] $isRunningOnServer
)

Import-Module Statistics

. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DateTimeHelpers.ps1"

$SEPERATOR = "|"

# LOCAL machine connection string. UPDATE when running locally.
$LOCAL_MACHINE_CONN_STRING = "Server=SPULIKKAL-ZBOOK\PULIKKALSQLSVR;Database=AutomationReporting;Trusted_Connection=True;"
# Reporting machine connection string
$BUILD_AGENT_CONN_STRING = "Server=WIN-JEPVISGJUTG\SQLSERVEREXP;Database=AutomationReporting;integrated security=True;"

$lastXDays = (-1) * $pastXDays

# stores values for report job time processing from DB.
$script:rptJobDBStatsTable = @{}
# stores values for report job time processing from baseline CSVs.
$script:rptJobCSVStatsTable = @{}

if ($isRunningOnServer) {
    $connString = $BUILD_AGENT_CONN_STRING
} else {
    $connString = $LOCAL_MACHINE_CONN_STRING
}

# HashTable with ArrayList value
function AddTo-StatsMapTable($mapTable, $tcIDJobType, $value) 
{
    if (-not $mapTable.ContainsKey($tcIDJobType)) {
        $tcStatsList = New-Object System.Collections.ArrayList
        $null = $tcStatsList.Add($value)
        $null = $mapTable.Add($tcIDJobType, $tcStatsList)
    } else {
        $tcStatsList = [System.Collections.ArrayList]$mapTable.Get_Item($tcIDJobType)
        if (-not $tcStatsList.Contains($value)) {
            $null = $tcStatsList.Add($value)
        }
        $mapTable.Set_Item($tcIDJobType, $tcStatsList)
    }        
}

function Fill-ReportJobProcessingTimesTable($processingTimesMap, $baseResultsFolder) {
    $searchFilter = "*.csv"
    Get-ChildItem -Path $baseResultsFolder -Filter $searchFilter -Recurse | % { 
        $file = $_
        $filename = $file.Name
        $fileFullPath = $file.FullName
        $directory = $file.Directory.Name

        $tcId = $filename.Replace(".csv", "")
        if ($filename.StartsWith("TC")) {
            Write-Host "Reading - $fileFullPath ..."
	
            $csvData = Import-Csv $fileFullPath
            $csvData | % {
                $reportJobTypeId = $_.ReportJobTypeId
                $startTime = $_.StartTime
                $endTime = $_.EndTime
                $processingTimeInMs = $_.ProcessingTimeInMs
                $key = "${tcId}${SEPERATOR}${reportJobTypeId}"
                $valueObj = New-Object PSObject -Property @{                                ReportJobTypeId       = $ReportJobTypeId                                     StartTime             = $StartTime                                  EndTime               = $EndTime                                ProcessingTimeInMs    = [int]$ProcessingTimeInMs 
                }

                AddTo-StatsMapTable -mapTable $processingTimesMap -tcIDJobType $key -value $valueObj
            }
        }
    }
}

function Debug-PrintMap($processingTimesMap) {
    $processingTimesMap.Keys | %{
        $ky = $_
        $procInfoList = $processingTimesMap.get_item($ky)
        $procInfoList | Sort-Object | % {
            $reportJobTypeId = $_.ReportJobTypeId
            $startTime = $_.StartTime
            $endTime = $_.EndTime
            $processingTimeInMs = $_.ProcessingTimeInMs

            # Write row.
            Write-Host "[$ky] $reportJobTypeId,$startTime,$endTime,$processingTimeInMs"
        }
    }
}

# Fill CSV map
Fill-ReportJobProcessingTimesTable -processingTimesMap $script:rptJobCSVStatsTable -baseResultsFolder $folderWithBaselineCSVs

# Fill DB map
$query = "SELECT [ReportTitle],[ReportJobTypeId],[ReportJobTypeName],[ReportJobStartTime],[ReportJobEndTime],DATEDIFF(MILLISECOND,[ReportJobStartTime],[ReportJobEndTime]) AS ProcessingTimeInMSec,[TestExecutionStartDate],[TestExecutionEndDate],[TestCaseID] FROM [dbo].[PerfStatReportJob] WHERE [TestExecutionStartDate] > DATEADD(d, $lastXDays, getdate())"
$objReportStats = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
$i = 0
$objReportStats | foreach { 
    if ($i -gt 0) {     # first row is length of array. datarows are from index=1
        $obj = $_; 
        $ReportTitle = $obj.ReportTitle
        $ReportJobTypeId = $obj.ReportJobTypeId
        $ReportJobTypeName = $obj.ReportJobTypeName
        $ReportJobStartTime = $obj.ReportJobStartTime
        $ReportJobEndTime = $obj.ReportJobEndTime
        $ProcessingTimeInMSec = $obj.ProcessingTimeInMSec
        $TestCaseID = $obj.TestCaseID                $key = "${TestCaseID}${SEPERATOR}${ReportJobTypeId}"        $valueObj = New-Object PSObject -Property @{                        ReportJobTypeId       = $ReportJobTypeId                             StartTime             = $ReportJobStartTime                          EndTime               = $ReportJobEndTime                        ProcessingTimeInMs    = [int]$ProcessingTimeInMSec                    }

        AddTo-StatsMapTable -mapTable $script:rptJobDBStatsTable -tcIDJobType $key -value $valueObj
    }
    $i++
}

# Debug print
Write-Host "[CSV ProcessingTime Map]:"
Debug-PrintMap -processingTimesMap $script:rptJobCSVStatsTable

# Debug print
Write-Host "[DB ProcessingTime Map]:"
Debug-PrintMap -processingTimesMap $script:rptJobDBStatsTable


$rptJobsTypesToVerifyList = $null
if ($reportJobTypesToVerify -ne $null -and $reportJobTypesToVerify -ne "") {
    $rptJobsTypesToVerifyList = @($reportJobTypesToVerify -split ",")    
}

# Write delta diff to output CSV.
$TEMP_PATH = [System.IO.Path]::GetTempPath()
$tempFolder = [guid]::NewGuid().toString().replace("-", "")
$outputFolder = [System.IO.Path]::Combine($TEMP_PATH, $tempFolder)
$outputResultFile = [System.IO.Path]::Combine($outputFolder, "diffResults.csv")

if (Test-Path $outputResultFile) {
    "Deleting previous diff delta results file -> $outputResultFile"
    Remove-item $outputResultFile -Force
}

"Creating new  diff delta results file at -> $outputResultFile"
$OUTFILE = New-Item -type file $outputResultFile -force

# Write header.
add-content $OUTFILE "TCID,RptJobStartTime,ReportJobTypeId,BaselineInMs,ActualTimeInMs,Delta,Percentile90,Average,StandardDeviation"

# Write rows.
$script:rptJobDBStatsTable.Keys | % {
    $key = $_
    $tcId = $key.Split($SEPERATOR)[0]    
    $jobType = $key.Split($SEPERATOR)[1]    
    $objList = $script:rptJobDBStatsTable.get_item($key)
    $stats = $objList | Measure-Object -Property ProcessingTimeInMs
    $perc90 = $stats.Percentile90 
    $avg = $stats.Average
    $stdDev = $stats.StandardDeviation
    $objList | % {
        $obj = $_
        $ReportJobTypeId = $obj.ReportJobTypeId
        $StartTime = $obj.StartTime
        $EndTime = $obj.EndTime
        [int]$ProcessingTimeInMs = [int]$obj.ProcessingTimeInMs
        
        if ($rptJobsTypesToVerifyList -eq $null -or $rptJobsTypesToVerifyList.Contains($ReportJobTypeId)) {            
            $csvObject = $script:rptJobCSVStatsTable.get_item($key)
            if ($csvObject -eq $null) {
                Write-Host "ERROR: Did NOT find value in CSV for TCRptJobId -> $key"
            } else {
                # Write value.
                [int]$baselineProcessingTimeInMs = [int]$csvObject.ProcessingTimeInMs
                [int]$delta = $ProcessingTimeInMs - $baselineProcessingTimeInMs
                add-content $OUTFILE "$tcId,$StartTime,$ReportJobTypeId,$baselineProcessingTimeInMs,$ProcessingTimeInMs,$delta,$perc90,$avg,$stdDev"
            }
        }
    }
}

ii $OUTFILE

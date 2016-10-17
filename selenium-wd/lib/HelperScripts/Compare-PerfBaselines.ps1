<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - This script can be used to compare results from 2 different runs to generate baselines.

 USAGE: To use this script: 
  1. Create 2 folder and place results from 'data\report-job-metrics' folder into the them
  2. Run the script and view differences in baselines generated across 2 runs. 
  
 Sample Run Script: 
   .\Compare-PerfBaselines.ps1 `
        -perfResultsFolder1 "C:\temp\perf-baseline-comparison\Current" `
        -perfResultsFolder2 "C:\temp\perf-baseline-comparison\LatestRun" `
        -outputFilePath "C:\temp\perfbaselinecomparisonreport.csv"

----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $perfResultsFolder1,

  [Parameter(Mandatory=$true)]
  [String] $perfResultsFolder2,

  [Parameter(Mandatory=$true)]
  [String] $outputFilePath
)

$OUTFILE = New-Item -type file $outputFilePath -force

$script:reportJobTypeMap = @{
    "00000000-0000-0000-0001-000000000000" = "Map"
    "00000000-0000-0000-0002-000000000000" = "SSRS"
    "00000000-0000-0000-0003-000000000000" = "DataGeneration"
    "00000000-0000-0000-0004-000000000000" = "EQMap"
    "00000000-0000-0000-0005-000000000000" = "EQSSRS"
    "00000000-0000-0000-0006-000000000000" = "EQDataGeneration"
    "00000000-0000-0000-0007-000000000000" = "ShapeFile"
    "00000000-0000-0000-0008-000000000000" = "ReportMeta"
    "00000000-0000-0000-0009-000000000000" = "PercentCoverageForecast"
    "00000000-0000-0000-0010-000000000000" = "Zip"
    "00000000-0000-0000-0011-000000000000" = "AssetBoxHighlight"
}

$script:baselineDataTable = @{}


function Read-ValuesToTable($folderPath, $keyPrefix) {
    $searchFilter = "*.csv"
    Get-ChildItem -Path $folderPath -Filter $searchFilter -Recurse | % { 
        $file = $_
        $filename = $file.Name
        $fileFullPath = $file.FullName
    
        # read only file with computed baselines.
        if ($filename.StartsWith("TC")) {
            Write-Host "Reading - $fileFullPath ..."
	
            $csvData = Import-Csv $fileFullPath
            $i = 1

            $rptJobProcessingTimesTable = @{}
            $csvData | % {
                $valueObject = New-Object PSObject -Property @{            
                    ReportJobTypeId = $_.ReportJobTypeId
                    StartTime = $_.StartTime
                    EndTime = $_.EndTime
                    ProcessingTimeInMs = $_.ProcessingTimeInMs
                }

                $rptJobProcessingTimesTable.set_item($valueObject.ReportJobTypeId, $valueObject)
            }

            $key = $keyPrefix + $filename
            $script:baselineDataTable.set_item($key, $rptJobProcessingTimesTable)
        }
    }
}

# Populate hashtable with baseline values from the 2 folders.
Read-ValuesToTable -folderPath $perfResultsFolder1 -keyPrefix "Current"
Read-ValuesToTable -folderPath $perfResultsFolder2 -keyPrefix "LatestRun"

# Start comparison and generate the comparison report
add-content $OUTFILE "TestCase,JobTypeId,JobTypeName,PreviousProcessingTimeInMs,CurrentProcessingTimeInMs,TimeHasIncreased"

$script:baselineDataTable.Keys | % {
    $key = $_
    $valuePrevious = $script:baselineDataTable.get_item($key)

    if ($key.contains("Current")) {
        # We have the current value. Get the latest run value.
        # Current value -> Previous
        # Latest run value -> Current

        $latestkey = $key.replace("Current", "LatestRun")
        $valueCurrent = $script:baselineDataTable.get_item($latestkey)

        $testCaseId = $key.replace("Current", "").replace(".csv", "")

        $valueCurrent.Keys | % {
            $kyJobTypeId = $_
            $valObjectCurr = $valueCurrent.get_item($kyJobTypeId)
            $valObjectPrev = $valuePrevious.get_item($kyJobTypeId)
            $jobTypeId = $kyJobTypeId
            $jobTypeName = $script:reportJobTypeMap.get_item($jobTypeId)
            $prevProcessingTime = [int]$valObjectPrev.ProcessingTimeInMs
            $currProcessingTime = [int]$valObjectCurr.ProcessingTimeInMs
            $timeHasIncreased = "false"
            if ($currProcessingTime -gt $prevProcessingTime) {
                $timeHasIncreased = "true"
            }

            add-content $OUTFILE "$testCaseId,$jobTypeId,$jobTypeName,$prevProcessingTime,$currProcessingTime,$timeHasIncreased"
        }
    }
}

# Display result.
ii $OUTFILE 
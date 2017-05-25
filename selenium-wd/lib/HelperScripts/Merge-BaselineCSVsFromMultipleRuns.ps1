<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - Use this script to generate perf baselines by merging results from two sources of CSV results.

 USAGE: To use this script: 
  1. Place the perf test results from run 1 to 'perfResultsFolder1'.
  2. Place the perf test results from run 2 to 'perfResultsFolder2'.
  3. Specify list of report job types to use from run1 in 'reportJobTypesToUseFromResult1'
  4. Specify a folder for output. NOTE: Files from this folder will be deleted when executing this script. 
  
 Sample Run Script: 
   .\Tweak-BaselineNumbers.ps1 `
        -perfResultsFolder1 "C:\Shirish\Automation\AutomationRuns\Performance-Baselines-Tweaking-20170525\RESULT-Previous-14Days\Percentile" `
        -perfResultsFolder2 "C:\Shirish\Automation\AutomationRuns\Performance-Baselines-Tweaking-20170525\RESULT-Previous-2Days\Percentile" `
        -reportJobTypesToUseFromResult1 "00000000-0000-0000-0001-000000000000" `    # comma-seperated list of ReportJobIDs
        -outputFolder "C:\temp\perf-baseline-comparison-2\Tweaked-Baselines"

----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $perfResultsFolder1,

  [Parameter(Mandatory=$true)]
  [String] $perfResultsFolder2,

  [Parameter(Mandatory=$true)]
  [String] $reportJobTypesToUseFromResult1,

  [Parameter(Mandatory=$true)]
  [String] $outputFolder
)

$script:rptJobProcessingTimesTable = @{}

function AddTo-ReportJobProcessingTimesTable($tcID, $processingTimeInfo) 
{
    if (-not $script:rptJobProcessingTimesTable.ContainsKey($tcID)) {
        $procInfoList = New-Object System.Collections.ArrayList
        $null = $procInfoList.Add($processingTimeInfo)
        $script:rptJobProcessingTimesTable.Add($tcID, $procInfoList)
    } else {
        $procInfoList = [System.Collections.ArrayList]$script:rptJobProcessingTimesTable.Get_Item($tcID)
        if (-not $procInfoList.Contains($processingTimeInfo)) {
            $null = $procInfoList.Add($processingTimeInfo)
        }
        $script:rptJobProcessingTimesTable.Set_Item($tcID, $procInfoList)
    }        
}

function Fill-ReportJobProcessingTimesTable($baseResultsFolder, $jobTypesToInclude, $jobTypesToExclude) {
    $searchFilter = "*.csv"
    Get-ChildItem -Path $baseResultsFolder -Filter $searchFilter -Recurse | % { 
        $file = $_
        $filename = $file.Name
        $fileFullPath = $file.FullName
        $directory = $file.Directory.Name
    
        if ($filename.StartsWith("TC")) {
            Write-Host "Reading - $fileFullPath ..."
	
            $csvData = Import-Csv $fileFullPath
            $csvData | % {
                $reportJobTypeId = $_.ReportJobTypeId
                $startTime = $_.StartTime
                $endTime = $_.EndTime
                $processingTimeInMs = $_.ProcessingTimeInMs
                
                $key = $filename.Replace(".csv", "")
                $valueObj = New-Object PSObject -Property @{                                ReportJobTypeId       = $ReportJobTypeId                                     StartTime             = $StartTime                                  EndTime               = $EndTime                                ProcessingTimeInMs    = $ProcessingTimeInMs 
                }


                if ($jobTypesToInclude -ne "") {
                    # include - ON, only add specified
                    $includeList = @($jobTypesToInclude -split ",")
                    $includeList | %{
                        $incJobTypeId = $_
                        if ($reportJobTypeId -eq $incJobTypeId) {
                            Write-Host "Include filter applied for TCID=$key, JobTypeId=$reportJobTypeId"
                            AddTo-ReportJobProcessingTimesTable -tcID $key -processingTimeInfo $valueObj
                        }
                    }
                } elseif ($jobTypesToExclude -ne "") {
                    # exclude - ON, only add not specified.
                    $excludeList = @($jobTypesToExclude -split ",")
                    $excludeList | %{
                        $excJobTypeId = $_
                        if ($reportJobTypeId -ne $excJobTypeId) {
                            Write-Host "Exclude filter applied for TCID=$key, JobTypeId=$reportJobTypeId"
                            AddTo-ReportJobProcessingTimesTable -tcID $key -processingTimeInfo $valueObj  
                        }
                    }
                } else {
                    # no fitler. insert.
                    AddTo-ReportJobProcessingTimesTable -tcID $key -processingTimeInfo $valueObj
                }
            }
        }
    }
}

function Write-BaselineCSVsFromMap() {
    $script:rptJobProcessingTimesTable.Keys | %{
        $tcId = $_
        $outFileFullPath = "$outputFolder\$tcId\${tcId}.csv"

        if (-not (Test-path "$outputFolder\$tcId")) {
            Write-Host "Creating directory - $outputFolder\$tcId ..."
            New-Item "$outputFolder\$tcId" -ItemType Directory -Force
        }         

        # Create a file in output folder.
        Write-Host "Generating merged file at - $outFileFullPath"
        $OUTFILE = New-Item -type file $outFileFullPath -force
            
        # Write header.
        add-content $OUTFILE "ReportJobTypeId,StartTime,EndTime,ProcessingTimeInMs"

        $procInfoList = $script:rptJobProcessingTimesTable.get_item($tcId)
        $procInfoList | Sort-Object | % {
            $reportJobTypeId = $_.ReportJobTypeId
            $startTime = $_.StartTime
            $endTime = $_.EndTime
            $processingTimeInMs = $_.ProcessingTimeInMs

            # Write row.
            add-content $OUTFILE "$reportJobTypeId,$startTime,$endTime,$processingTimeInMs"
        }
    }
}

function Debug-PrintMap() {
    $script:rptJobProcessingTimesTable.Keys | %{
        $tcId = $_
        $procInfoList = $script:rptJobProcessingTimesTable.get_item($tcId)
        $procInfoList | Sort-Object | % {
            $reportJobTypeId = $_.ReportJobTypeId
            $startTime = $_.StartTime
            $endTime = $_.EndTime
            $processingTimeInMs = $_.ProcessingTimeInMs

            # Write row.
            Write-Host "[$tcId] $reportJobTypeId,$startTime,$endTime,$processingTimeInMs"
        }
    }
}

# Cleanup output folder first.
if (Test-Path $outputFolder) {
    Remove-Item -Path $outputFolder -Recurse
}

# Populate hashtable with baseline values from the 2 folders.
Fill-ReportJobProcessingTimesTable -baseResultsFolder $perfResultsFolder1 -jobTypesToInclude "$reportJobTypesToUseFromResult1" -jobTypesToExclude ""

Write-Host "AFTER FILL-1"
Debug-PrintMap

Fill-ReportJobProcessingTimesTable -baseResultsFolder $perfResultsFolder2 -jobTypesToInclude "" -jobTypesToExclude "$reportJobTypesToUseFromResult1"

Write-Host "AFTER FILL-2"
Debug-PrintMap


# Write new CSVs
Write-BaselineCSVsFromMap
Write-Host ""
Write-Host "###################################################################"
Write-Host " Merged baseline files written to - $outputFolder"
Write-Host "###################################################################"

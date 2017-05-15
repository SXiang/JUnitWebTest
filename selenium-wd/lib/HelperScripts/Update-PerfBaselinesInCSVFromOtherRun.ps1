<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - Use this script to update perf baselines in CSV files for specific report job types with values read from another set of CSVs.

 USAGE: To use this script: 
  1. Place the perf test CSV files from your run at '$newPerfResultsFolder'.
  2. Specify repo root folder - Eg. "C:\Repositories\surveyor-qa"
  3. Specify a folder for output. 
     #### NOTE ####: Files from this folder will be deleted when executing this script. 
  4. Specify types of report job types that you want to update in '$reportTypesToUpdate' and type of report job types to add in '$newJobTypes'.
  5. Specify a change factor to apply to the baseline.
  
 Sample Run Script: 
   .\Update-PerfBaselinesInCSVFromOtherRun.ps1  `
        -newPerfResultsFolder "C:\Temp\PerformanceUpdatedBaselines-20170510"  `
        -repoBaseFolder "C:\Repositories\surveyor-qa"  `
		-reportTypesToUpdate "00000000-0000-0000-0003-000000000000,00000000-0000-0000-0011-000000000000"  `
		-newJobTypes "00000000-0000-0000-0018-000000000000"  `
        -changeFactor 1.25  `
        -outputFolder "C:\Temp\PerformanceUpdatedBaselines-20170510\Tweaked-Baselines"

----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $newPerfResultsFolder,

  [Parameter(Mandatory=$true)]
  [String] $repoBaseFolder,

  [Parameter(Mandatory=$true)]
  [String] $reportTypesToUpdate,

  [Parameter(Mandatory=$true)]
  [String] $newJobTypes,

  [Parameter(Mandatory=$true)]
  [float] $changeFactor,

  [Parameter(Mandatory=$true)]
  [String] $outputFolder
)

$script:rptJobProcessingTimesUpdateTable = @{}
$script:rptJobProcessingTimesAddTable = @{}
$script:reportTypesToUpdateList = $reportTypesToUpdate -split ","
$script:reportTypesToAddList = $newJobTypes -split ","

function Update-ReportJobProcessingTimesTable($rptJobTbl, $baselineFile, $rptJobTypeObject) 
{
    if (-not $rptJobTbl.ContainsKey($baselineFile)) {
        $rptJobsList = New-Object System.Collections.ArrayList
        $null = $rptJobsList.Add($rptJobTypeObject)
        $rptJobTbl.Add($baselineFile, $rptJobsList)
    } else {
        $rptJobsList = [System.Collections.ArrayList]$rptJobTbl.Get_Item($baselineFile)
        if (-not $rptJobsList.Contains($rptJobTypeObject)) {
            $null = $rptJobsList.Add($rptJobTypeObject)
        }
        $rptJobTbl.Set_Item($baselineFile, $rptJobsList)
    }        
}


function Update-BaselineCSVs() {
    $searchFilter = "*.csv"
    
    # First read the desired report job baselines from inFolder and place in Map.
    Get-ChildItem -Path $newPerfResultsFolder -Filter $searchFilter | % { 
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
                $processingTimeInMs = [int]($changeFactor * [int]$_.ProcessingTimeInMs)

                # Store in Map.
                $rptJobTypObj = New-Object PSObject -Property @{                                ReportJobTypeId = $reportJobTypeId
                    StartTime = $startTime
                    EndTime = $endTime
                    ProcessingTimeInMs = $processingTimeInMs
                }

                if ($script:reportTypesToUpdateList.Contains($reportJobTypeId)) {
                    Update-ReportJobProcessingTimesTable -rptJobTbl $script:rptJobProcessingTimesUpdateTable -baselineFile $filename -rptJobTypeObject $rptJobTypObj
                }

                if ($script:reportTypesToAddList.Contains($reportJobTypeId)) {
                    Update-ReportJobProcessingTimesTable -rptJobTbl $script:rptJobProcessingTimesAddTable -baselineFile $filename -rptJobTypeObject $rptJobTypObj
                }
            }
        }
    }

    # Next build the output CSVs
    $originalBaselinesFolder = "$repoBaseFolder\selenium-wd\data\perf-metric\report-job-metrics"

    Get-ChildItem -Path $originalBaselinesFolder -Filter $searchFilter -Recurse | % { 
        $file = $_
        $filename = $file.Name
        $fileFullPath = $file.FullName
        $directory = $file.Directory.Name

        $outFileFullPath = "$outputFolder\$directory\$filename"
    
        # Update file with baselines.
        if ($filename.StartsWith("TC")) {
            Write-Host "Reading - $fileFullPath ..."
	
            $csvData = Import-Csv $fileFullPath

            # Create a file in output folder.
            Write-Host "Generating updated baseline file at - $outFileFullPath"
            $OUTFILE = New-Item -type file $outFileFullPath -force
            
            # Write header.
            add-content $OUTFILE "ReportJobTypeId,StartTime,EndTime,ProcessingTimeInMs"

            $csvData | % {
                $reportJobTypeId = $_.ReportJobTypeId
                $startTime = $_.StartTime
                $endTime = $_.EndTime
                $processingTimeInMs = $_.ProcessingTimeInMs

                # update existing rows.
                if ($script:rptJobProcessingTimesUpdateTable.ContainsKey($filename)) {
                    $rptTypObjList = [System.Collections.ArrayList]$script:rptJobProcessingTimesUpdateTable.get_item($filename)
                    $rptTypObjList | %{
                        $rptTypObj = $_
                        if ($reportJobTypeId -eq $rptTypObj.ReportJobTypeId) {
                            $reportJobTypeId = $rptTypObj.ReportJobTypeId
                            $startTime = $rptTypObj.StartTime
                            $endTime = $rptTypObj.EndTime
                            $processingTimeInMs = $rptTypObj.ProcessingTimeInMs
                        }
                    }
                } 

                # For specified report job types write updated row.
                add-content $OUTFILE "$reportJobTypeId,$startTime,$endTime,$processingTimeInMs"
            }

            # add new rows.
            if ($script:rptJobProcessingTimesAddTable.ContainsKey($filename)) {
                $rptTypObjList = [System.Collections.ArrayList]$script:rptJobProcessingTimesAddTable.get_item($filename)
                $rptTypObjList | %{
                    $rptTypObj = $_
                    $reportJobTypeId = $rptTypObj.ReportJobTypeId
                    $startTime = $rptTypObj.StartTime
                    $endTime = $rptTypObj.EndTime
                    $processingTimeInMs = $rptTypObj.ProcessingTimeInMs
                    add-content $OUTFILE "$reportJobTypeId,$startTime,$endTime,$processingTimeInMs"
                }
            }

        } else {
            if (-not (Test-Path -Path "$outputFolder\$directory")) {
                New-Item -Path "$outputFolder\$directory" -ItemType Directory
            }
			Copy-Item -Path $fileFullPath -Destination $outFileFullPath
        }
    }
}

# Cleanup output folder first.
Remove-Item -Path $outputFolder -Recurse

if (-not (Test-Path $outputFolder)) {
    New-Item -Path $outputFolder -ItemType Directory
}

# Update baselines with values from specified CSVs.
Update-BaselineCSVs -folderPath $newPerfResultsFolder

Write-Host "Updated baseline files written to - $outputFolder"
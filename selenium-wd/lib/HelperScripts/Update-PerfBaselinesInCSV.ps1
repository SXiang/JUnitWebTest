<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - Use this script to tweak file with perf baselines.

 USAGE: To use this script: 
  1. Place the perf test results to '$perfResultsFolder'.
  2. Specify a folder for output. 
     #### NOTE ####: Files from this folder will be deleted when executing this script. 
  3. Specify a '$changeFactor' by which to tweak the baselines. Current baseline number will be multiplied with the specified $changeFactor.
  
 Sample Run Script: 
   .\Update-PerfBaselinesInCSV.ps1  `
        -perfResultsFolder "C:\temp\perf-baseline-comparison-2\LatestRun"  `
		-changeFactor 1.4  `
        -outputFolder "C:\temp\perf-baseline-comparison-2\Tweaked-Baselines"

----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $perfResultsFolder,

  [Parameter(Mandatory=$true)]
  [float] $changeFactor,

  [Parameter(Mandatory=$true)]
  [String] $outputFolder
)

function Tweak-BaselineNumbers($folderPath) {
    $searchFilter = "*.csv"
    Get-ChildItem -Path $folderPath -Filter $searchFilter -Recurse | % { 
        $file = $_
        $filename = $file.Name
        $fileFullPath = $file.FullName
        $directory = $file.Directory.Name

        $outFileFullPath = "$outputFolder\$directory\$filename"
    
        # Tweak file with baselines.
        if ($filename.StartsWith("TC")) {
            Write-Host "Reading - $fileFullPath ..."
	
            $csvData = Import-Csv $fileFullPath

            # Create a file in output folder.
            Write-Host "Generating tweaked file at - $outFileFullPath"
            $OUTFILE = New-Item -type file $outFileFullPath -force
            
            # Write header.
            add-content $OUTFILE "ReportJobTypeId,StartTime,EndTime,ProcessingTimeInMs"

            $rptJobProcessingTimesTable = @{}
            $csvData | % {
                $reportJobTypeId = $_.ReportJobTypeId
                $startTime = $_.StartTime
                $endTime = $_.EndTime
                $processingTimeInMs = [int]($changeFactor * [int]$_.ProcessingTimeInMs)

                # Write tweaked row.
                add-content $OUTFILE "$reportJobTypeId,$startTime,$endTime,$processingTimeInMs"
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

# Update baselines with specified CHANGE FACTOR.
Tweak-BaselineNumbers -folderPath $perfResultsFolder

Write-Host "Tweaked baseline files written to - $outputFolder"
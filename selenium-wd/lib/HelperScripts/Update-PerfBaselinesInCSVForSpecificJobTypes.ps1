<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - Use this script to tweak file with perf baselines only for specified job types.

 USAGE: To use this script: 
  1. Place the perf test results to '$perfResultsFolder'.
  2. Specify a folder for output. 
     #### NOTE ####: Files from this folder will be deleted when executing this script. 
  3. Specify a '$additionalPercentIncrease' by which to tweak the baselines. Current baseline number will be incremented by the specified percentage.
  4. Specify the test cases and job types to be updated in '$testCasesAndJobTypesToUpdate'. Use format -  <TcID>|<JobType>|<Increment>,<TcID>|<JobType>|<Increment>
  
 Sample Run Script: 
   .\Update-PerfBaselinesInCSV.ps1  `
        -perfResultsFolder "C:\Repositories\surveyor-qa\selenium-wd\data\perf-metric\report-job-metrics"  `
        -testCasesAndJobTypesToUpdate "TC1843-11|00000000-0000-0000-0001-000000000000|2804,TC2315-3|00000000-0000-0000-0001-000000000000|7854,TC2316-3|00000000-0000-0000-0001-000000000000|4126,TC1841|00000000-0000-0000-0001-000000000000|5232,TC1842|00000000-0000-0000-0001-000000000000|5002,TC1844-31|00000000-0000-0000-0001-000000000000|698,TC1844|00000000-0000-0000-0001-000000000000|7340,TC1843-31|00000000-0000-0000-0011-000000000000|14994,TC1843-11|00000000-0000-0000-0011-000000000000|17446,TC2315-3|00000000-0000-0000-0011-000000000000|6532,TC2316-3|00000000-0000-0000-0011-000000000000|38178,TC1844-31|00000000-0000-0000-0011-000000000000|844,TC1844-21|00000000-0000-0000-0011-000000000000|2439,TC1844-11|00000000-0000-0000-0011-000000000000|609"  `
		-additionalPercentIncrease 0.05  `
        -outputFolder "C:\temp\perf-baseline-comparison-2\Tweaked-Baselines"

----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $perfResultsFolder,

  [Parameter(Mandatory=$true)]
  [String] $testCasesAndJobTypesToUpdate,   # value in following format:
                                            #  <TcID>|<JobType>|<Increment>,<TcID>|<JobType>|<Increment>
  [Parameter(Mandatory=$true)]
  [float] $additionalPercentIncrease,

  [Parameter(Mandatory=$true)]
  [String] $outputFolder
)

$script:TCUpdateMap = @{}

function Tweak-BaselineNumbers($folderPath) {
    $searchFilter = "*.csv"
    Get-ChildItem -Path $folderPath -Filter $searchFilter -Recurse | % { 
        $file = $_
        $filename = $file.Name
        $fileFullPath = $file.FullName
        $directory = $file.Directory.Name

        $outFileFullPath = "$outputFolder\$directory\$filename"
    
        $tcID = $filename.Replace(".csv", "")

        # Tweak file with baselines.
        if ($filename.StartsWith("TC") -and ($script:TCUpdateMap.ContainsKey($tcID))) {
            Write-Host "Reading - $fileFullPath ..."
	
            $csvData = Import-Csv $fileFullPath

            # Create a file in output folder.
            Write-Host "Generating tweaked file at - $outFileFullPath"
            $OUTFILE = New-Item -type file $outFileFullPath -force
            
            # Write header.
            add-content $OUTFILE "ReportJobTypeId,StartTime,EndTime,ProcessingTimeInMs"
            $csvData | % {
                $reportJobTypeId = $_.ReportJobTypeId
                $tcJobTypeList = [System.Collections.ArrayList]$script:TCUpdateMap.get_item($tcID)
                $tcJobTypeInfo = Get-TCJobTypeInfoFromList -list $tcJobTypeList -jobType $reportJobTypeId

                if ($tcJobTypeInfo -ne $null) {
                    $startTime = [int]$_.StartTime
                    $processingTimeInMs = [int](([int]$_.ProcessingTimeInMs) * (1+$additionalPercentIncrease)) + [int]$tcJobTypeInfo.Increment
                    $endTime = $startTime + $processingTimeInMs
                } else {
                    $startTime = $_.StartTime
                    $endTime = $_.EndTime
                    $processingTimeInMs = [int]$_.ProcessingTimeInMs 
                }

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

function AddTo-TestCaseInfoMapTable($tcID, $tcJobTypeInfo) {
    if (-not $script:TCUpdateMap.ContainsKey($tcID)) {
        $tcJobTypeList = New-Object System.Collections.ArrayList
        $null = $tcJobTypeList.Add($tcJobTypeInfo)
        $script:TCUpdateMap.Add($tcID, $tcJobTypeList)
    } else {
        $tcJobTypeList = [System.Collections.ArrayList]$script:TCUpdateMap.Get_Item($tcID)
        if (-not $tcJobTypeList.Contains($tcJobTypeInfo)) {
            $null = $tcJobTypeList.Add($tcJobTypeInfo)
        }
        $script:TCUpdateMap.Set_Item($tcID, $tcJobTypeList)
    }        
}

function Get-TCJobTypeInfoFromList($list, $jobType) {
    $infoObj = $null
    $found = $false
    $list | %{
        if (-not $found) {
            $obj = $_
            if ($obj.JobType -eq $jobType) {
                $infoObj = $obj
                $found = $true
            }
        }
    }

    $infoObj
}

function Build-TCUpdateMap($testCasesAndJobTypesInfo) {
    $tcList = @($testCasesAndJobTypesInfo -split ",")
    $tcList | %{
        $tcInfo = $_
        $tcInfoParts = @($tcInfo.Split("|"))
        $tcId = $tcInfoParts[0]
        $jbType = $tcInfoParts[1]
        $incr = $tcInfoParts[2]

        $tcInfoObj = New-Object PSObject -Property @{                        TcID       = $tcId                             JobType    = $jbType                          Increment  = $incr 
        }

        AddTo-TestCaseInfoMapTable -tcID $tcid -tcJobTypeInfo $tcInfoObj
    }
}


# Cleanup output folder first.
if (Test-Path $outputFolder) {
    Remove-Item -Path $outputFolder -Recurse
}

Build-TCUpdateMap -testCasesAndJobTypesInfo $testCasesAndJobTypesToUpdate

# Update baselines with specified CHANGE FACTOR.
Tweak-BaselineNumbers -folderPath $perfResultsFolder

Write-Host "Tweaked baseline files written to - $outputFolder"
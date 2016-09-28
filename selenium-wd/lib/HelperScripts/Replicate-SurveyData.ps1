<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - This script can be used to replicate a survey by creating multiple surveys from given set of survey csv files.
  - This script takes as INPUT *.csv files with Survey data and replaces Survey tag, SurveyId references and restamps Epoch times in the 
    files and create new set of *.csv files which can be pushed to automation environment using DBSeedExecutor classes.
  - TIP: When replicating large surveys if your test cases do NOT need RAW data, exclude RAW data csv files (Measurement, GPS, Anemometer)
    from the INPUT folder for the script to execute faster.

 USAGE SCENARIOS: 
  1. If you want to automate test cases that require large number of surveys, you can take existing surveys and replicate it 
     multiple times using this script.
  2. If you want to make multiple replicas of the same survey use this script.
  
 Sample Run Script: 
   .\Replicate-SurveyData.ps1 `
        -databaseServerIP "20.20.130.210" `
        -databaseName "SurveyorSQAAUTO_blankDB_20160823" `
        -databaseUser "awssa" `
        -databasePassword "3Vf763pSg2" `
        -inSurveyDataFolder "C:\temp\03" `
        -outGeneratedSurveysFolder "C:\temp\03\Replaced" `
        -startingFileCounter 2 `
        -numSurveysToGenerate 11

----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$false)]
  [String] $databaseServerIP = "192.168.56.1",    # SQAAuto

  [Parameter(Mandatory=$false)]
  [String] $databaseName = "Surveyor_BlankDB_20160919",

  [Parameter(Mandatory=$false)]
  [String] $databaseUser = "awssa",

  [Parameter(Mandatory=$false)]
  [String] $databasePassword = "3Vf763pSg2",

  [Parameter(Mandatory=$false)]
  [String] $inSurveyDataFolder = "C:\temp\03",
  
  [Parameter(Mandatory=$false)]
  [String] $outGeneratedSurveysFolder = "C:\temp\03\Replaced",

  [Parameter(Mandatory=$false)]
  [int] $startingFileCounter = 2,        # value should be 1-less than the current value on the file in 'IN' folder. For eg. If file in IN folder is '-1.csv' then this value should be 2.

  [Parameter(Mandatory=$false)]
  [int] $numSurveysToGenerate = 11
)

$script:DEBUG = $false    # Set to TRUE to view additional logs (for eg. logs displaying execution time per block)

# Include database helper and file read-write helper libs.
. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1
. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\FileReadWriteHelper.ps1

$connString = "Server=$databaseServerIP;Database=$databaseName;User Id=$databaseUser;Password=$databasePassword;"

$script:startEpoch = 0
$script:endEpoch = 0
$script:surveyId = ""
$script:surveyTag = ""

$script:replacedFilesList = New-Object System.Collections.ArrayList
$script:restampedFilesList = New-Object System.Collections.ArrayList


function Print-Elapsed($stopWatch, $blockNum) {
    if ($script:DEBUG) {
        $elapsed = $stopWatch.ElapsedMilliseconds
        "BLOCK-$blockNum : $elapsed"
    }
}

function Get-AdjustedEpochTime($value, $iterationNumber) {
    $adjustedValue = $script:adjustedStartEpoch + $value - $script:startEpoch
    $adjustedValue
}

# Read SurveyID from Survey.csv
$searchFilter = "*.csv"
$countOfSurveyFiles = 0
Write-Host "Reading Survey StartEpoch and EndEpoch and computing adjusted EpochTime values ..."
Get-ChildItem -Path $inSurveyDataFolder -Filter $searchFilter | % { 
    $file = $_
    $filename = $file.Name
    $fileFullPath = $file.FullName
    Write-Host "Processing file - $fileFullPath ..."
    if ($filename.StartsWith("Survey-")) {
        $countOfSurveyFiles++

        $surveyCSVData = Import-Csv $fileFullPath
        $surveyCSVData | % {
            $analyzerId = $_.AnalyzerId
            $script:surveyId = $_.Id
            $script:surveyTag = $_.Tag
            $script:startEpoch = $_.StartEpoch
            $script:endEpoch = $_.EndEpoch
        }

        $leftShift = $script:endEpoch - $script:startEpoch

        # Get Last Epoch Time in Measurement table for this Analyzer.
        $query = "SELECT TOP 1 [AnalyzerId],[EpochTime]  FROM [dbo].[Measurement] WHERE AnalyzerId='$analyzerId' ORDER BY EpochTime ASC"
        $objResults = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
        $i = 0
        $measurementEpoch = $script:startEpoch
        $objResults | foreach { 
            if ($i -gt 0) {     # first row is length of array. datarows are from index=1
                $obj = $_; 
                $measurementEpoch = $obj.EpochTime
            }
            $i++
        }

        # Get Last Epoch Time in AnemometerRaw table for this Analyzer.
        $query = "SELECT TOP 1 [AnalyzerId],[EpochTime]  FROM [dbo].[AnemometerRaw] WHERE AnalyzerId='$analyzerId' ORDER BY EpochTime ASC"
        $i = 0
        $anemometerRawEpoch = $script:startEpoch
        $objResults | foreach { 
            if ($i -gt 0) {     # first row is length of array. datarows are from index=1
                $obj = $_; 
                $anemometerRawEpoch = $obj.EpochTime
            }
            $i++
        }

        # Get Last Epoch Time in GPSRaw table for this Analyzer.
        $query = "SELECT TOP 1 [AnalyzerId],[EpochTime]  FROM [dbo].[GPSRaw] WHERE AnalyzerId='$analyzerId' ORDER BY EpochTime ASC"
        $i = 0
        $gpsRawEpoch = $script:startEpoch
        $objResults | foreach { 
            if ($i -gt 0) {     # first row is length of array. datarows are from index=1
                $obj = $_; 
                $gpsRawEpoch = $obj.EpochTime
            }
            $i++
        }

        # Use the lowest Epoch Time from the RAW tables as the REFERENCE Epoch time which we'll use for restamping.
        # Compute EpochTime Delta from the Reference. Each EpochTime will be restamped (adjusted) by using the DELTA value.
        $smallestEpoch = $measurementEpoch
        if ($anemometerRawEpoch -lt $smallestEpoch) {
            $smallestEpoch = $anemometerRawEpoch
        }
        if ($gpsRawEpoch -lt $smallestEpoch) {
            $smallestEpoch = $gpsRawEpoch
        }

        $script:adjustedStartEpoch = $smallestEpoch - $leftShift - 1000
        $script:adjustedEndEpoch = $script:adjustedStartEpoch + $leftShift
    }
}

if ($countOfSurveyFiles -ne 1) {
    Write-Host "### ERROR: There are [$countOfSurveyFiles] Survey.csv files in $inSurveyDataFolder. Make sure your input folder contains data for ONLY 1 survey"
    Write-Host "EXITING script..."
    return
}

Write-Host "Found Survey with StartEpoch='$script:startEpoch', EndEpoch='$script:endEpoch' ..."

# Start generating the Surveys. 
$iteration = 0
for ([int]$j=$startingFileCounter; $j -lt $startingFileCounter + $numSurveysToGenerate; $j++) {
    $script:newSurveyId = [guid]::NewGuid().ToString().ToUpperInvariant()
    
    $leftShift = $script:endEpoch - $script:startEpoch
    $script:adjustedStartEpoch = $script:adjustedStartEpoch - ($iteration * $leftShift) - 1000
    $script:adjustedEndEpoch = $script:adjustedStartEpoch + $leftShift
    $iteration++

    Write-Host "ITERATION=$iteration, ADJUSTED StartEpoch='$script:adjustedStartEpoch', ADJUSTED EndEpoch='$script:adjustedEndEpoch'"

    Get-ChildItem -Path $inSurveyDataFolder -Filter $searchFilter | % { 
        $file = $_
        $filename = $file.Name
        $fileFullPath = $file.FullName
        $directoryName = $file.DirectoryName
        Write-Host "ITERATION=$iteration, Processing - $fileFullPath ..."

        # Read all file content.
        $fileContent = Read-File -filePath $fileFullPath

        $stopWatch = [System.Diagnostics.Stopwatch]::StartNew()

        $fileLinesList = New-Object System.Collections.ArrayList

        # Restamp values first.
        if ($filename.StartsWith("Measurement-") `
             -or $filename.StartsWith("AnemometerRaw-") `
             -or $filename.StartsWith("GPSRaw-") `
             -or $filename.StartsWith("FieldOfView-") `
             -or $filename.StartsWith("CaptureEvent-") `
             -or $filename.StartsWith("Survey-") `
             -or $filename.StartsWith("Peak-")) {

            if (!$script:restampedFilesList.Contains($fileFullPath)) {
                $null = $script:restampedFilesList.Add($fileFullPath)
            }

            Write-Host "ITERATION=$iteration, Adjust EpochTimes and start writing to fileContent for - '$fileFullPath'"
            Print-Elapsed $stopWatch 1

            $csvData = Import-Csv "$fileFullPath"
            
            # get headers in the same order and Write header line to fileLinesList.
            $headers = $csvData[0].psObject.Properties | foreach { $_.Name }
            $len = $headers.Length   
            
            # First write the Header line.
            $k = 0                
            $lineText = ""             
            $headers | % { 
                $headerName = $_
                $lineText += $headerName
                if ($k -ne $len - 1) {
                    $lineText += ","
                }
                $k++
            }
            $null = $fileLinesList.Add($lineText)

            # Next write the line text by replacing certain column values.
            $i = 0
            $csvData | % {
                $line = $_

                # Build line text from csvdata.
                $lineText = ""
                $k = 0
                $headers | % { 
                    $headerName = $_
                    $lineText += $line."$headerName"
                    if ($k -ne $len - 1) {
                        $lineText += ","
                    }
                    $k++ 
                }
                                
                # For Survey.csv adjust StartEpoch and EndEpoch. For all other files adjust EpochTime.
                if ($filename.StartsWith("Survey-")) {
                    # Adjust StartEpoch and EndEpoch
                    $startEpoch = $line.StartEpoch
                    $endEpoch = $line.EndEpoch
                    $adjustedStartEpoch = Get-AdjustedEpochTime -value $startEpoch
                    $adjustedEndEpoch = Get-AdjustedEpochTime -value $endEpoch
                
                    if ($script:DEBUG) {
                        Write-Host "########### Adjusting Start EpochTime ############ Original Epoch=$startEpoch, Adjusted Epoch=$adjustedStartEpoch"
                        Write-Host "########### Adjusting End EpochTime ############ Original Epoch=$endEpoch, Adjusted Epoch=$adjustedEndEpoch"
                    }

                    # Replace Epoch time in line text with adjusted epoch start and end times.
                    $lineText = $lineText -replace $startEpoch, $adjustedStartEpoch
                    $lineText = $lineText -replace $endEpoch, $adjustedEndEpoch

                } else {

                    # Adjust EpochTime
                    $epochTime = $line.EpochTime    
                    $adjustedEpoch = Get-AdjustedEpochTime -value $epochTime
                
                    if ($script:DEBUG) {
                        Write-Host "########### Adjusting EpochTime ############ Original Epoch=$epochTime, Adjusted Epoch=$adjustedEpoch"
                    }

                    # Replace Epoch time in line text with adjusted epoch time.
                    $lineText = $lineText -replace $epochTime, $adjustedEpoch
                }

                # Add replaced line to ArrayList
                $null = $fileLinesList.Add("$lineText")

                if ($i % 250 -eq 0) {
                    Write-Host "ITERATION=$iteration, File='$fileName', Processing LINE-$i"
                }

                $i++
            }

            Write-Host "ITERATION=$iteration, DONE writing restamped EpochTime values to fileContent for - '$fileFullPath'"
            Print-Elapsed $stopWatch 2
        }

        # If fileLinesList is EMPTY read all file lines into the lines list.
        if ($fileLinesList.Count -eq 0) {
            $fileLinesList = Read-FileLines -filePath $fileFullPath
        }

        # Replace surveyId references next
        if (($filename.StartsWith("Survey-") -and (!$filename.StartsWith("Survey-Geom"))) `
            -or $filename.StartsWith("SurveyCondition-") -or $filename.StartsWith("SurveyResult-") `
             -or $filename.StartsWith("FieldOfView-") `
             -or $filename.StartsWith("Peak-") `
             -or $filename.StartsWith("CaptureEvent-") `
             -or ($filename.StartsWith("Segment-") -and (!$filename.StartsWith("Segment-Geom")))) {

            if (!$script:replacedFilesList.Contains($fileFullPath)) {
                $null = $script:replacedFilesList.Add($fileFullPath)
            }

            Write-Host "ITERATION=$iteration, Replacing SurveyID references in '$fileFullPath' in memory. Prev-> $script:surveyId, New-> $script:newSurveyId"
            Print-Elapsed $stopWatch 3 

            $surveyIdReplacedLinesList = New-Object System.Collections.ArrayList

            # Replace SurveyId
            $counter = 0
            $fileLinesList | % {
                $lineText = $_
                $lineText = $lineText -replace $script:surveyId, $script:newSurveyId

                # If current file is 'Survey.csv' file also create new SurveyTag.
                if ($filename.StartsWith("Survey-")) {
                    $newSurveyTag = "${script:surveyTag}0$j"
                    $lineText = $lineText -replace $script:surveyTag, $newSurveyTag

                # For SurveyCondition and CaptureEvent files generate new PK Id value.
                } elseif ($filename.StartsWith("SurveyCondition-") -or $filename.StartsWith("CaptureEvent-")) {
                    
                    if ($counter -ne 0) {
                        # 1st column value is PK (Id). Generate new value for PK (Id)
                        $parts = $lineText -split ","
                        $len = $parts.Length
                        $text = ""
                        for ($si=1; $si -lt $len; $si++) {    # all values other than 1st.
                            $text += "," + $parts[$si]
                        }
                    
                        # Replace 1st column value with new guid.
                        $newGuid = [guid]::NewGuid().ToString().ToUpperInvariant()
                        $lineText = $newGuid + $text
                    }
                }

                $null = $surveyIdReplacedLinesList.Add($lineText)
                $counter++
            }

            $fileLinesList = $surveyIdReplacedLinesList
        } 

        # Write back the replaced file content to the file.
        $currFileCounter = $startingFileCounter - 1
        $newFileName = $filename.Replace("-${currFileCounter}.csv", "-$j.csv")
        $newFileFullPath = "$outGeneratedSurveysFolder\$newFileName"

        Write-Host "ITERATION=$iteration, Start writing replaced content to FILE for - '$newFileFullPath'"
        Print-Elapsed $stopWatch 4

        Write-FileLines -filePath $newFileFullPath -fileContentLines $fileLinesList

        Write-Host "ITERATION=$iteration, DONE writing replaced content to FILE for - '$newFileFullPath'"
        Print-Elapsed $stopWatch 5
    }
}

# Print which files were touched.
if ($script:replacedFilesList.Count -gt 0) {
    Write-Host "`r`n#### SurveyId references were FOUND & MODIFIED for Surveys created from following files:-" 
    $script:replacedFilesList | % { $file = $_; Write-Host $file }
} else {
    Write-Host "SurveyId references NOT changed in any files."
}

if ($script:restampedFilesList.Count -gt 0) {
    Write-Host "`r`n#### EpochTime restamping DONE for Surveys created from following files:-" 
    $script:restampedFilesList | % { $file = $_; Write-Host $file }
} else {
    Write-Host "EpochTime restamping NOT done in any files."
}
# Turn ON to print additional debugging information to console.
$script:DEBUG = $false

<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION: 
  - Computes restamped timestamp value for the specified timestamp. Subtracts the specified offset from the restamped value.
----------------------------------------------------------------------------------------------------------------------------------#>
function Get-AdjustedEpochTime($value, $offset) {
    $adjustedValue = $script:adjustedStartEpoch + $value - $script:startEpoch - $offset
    $adjustedValue
}

<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION: 
  - Converts specified unix date to a date string in 'yyyy-MM-dd HH:mm:ss.fff' format.
----------------------------------------------------------------------------------------------------------------------------------#>
function UnixDate-ToString($unixDate) {
    $smDate = FromUnixTime -epochTime $unixDate
    $dateStr = Date-ToString -value $smDate
    $dateStr
}

<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION: 
  - Converts a date to a date string in 'yyyy-MM-dd HH:mm:ss.fff' format.
----------------------------------------------------------------------------------------------------------------------------------#>
function Date-ToString($value) {
    if ($value -eq $NULL -or $value.ToString() -eq "") {
        "NULL"
    } else {
        [String]::Format("{0:yyyy-MM-dd HH:mm:ss.fff}", $value)
    }
}

<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION: 
  - Restamps values in specified CSV files by looking up database to ensure no PK {AnalyzerId, Epoch} conflicts happen during INSERTS
----------------------------------------------------------------------------------------------------------------------------------#>
function Restamp-EpochTimes() {
    param
    (
      ####--------------------------------------------------------------####
      #### New Database -> Database to where the survey is to be PUSHED ####
      ####--------------------------------------------------------------####

      [Parameter(Mandatory=$true)]
      [String] $newDatabaseIP,

      [Parameter(Mandatory=$true)]
      [String] $newDatabaseName,

      [Parameter(Mandatory=$true)]
      [String] $newDatabaseUser,

      [Parameter(Mandatory=$true)]
      [String] $newDatabasePwd,

      ####--------------------------------------------------------------####
      #### CSV file parameters                                          ####
      ####--------------------------------------------------------------####

      [Parameter(Mandatory=$true)]
      [String] $inDirectory,

      [Parameter(Mandatory=$true)]
      [String] $fileExtFilter,

      [Parameter(Mandatory=$true)]
      [String] $outDirectory,

      [Parameter(Mandatory=$true)]
      [int64] $offsetInRestamp
    )

    $script:replacedFilesList = New-Object System.Collections.ArrayList
    $script:restampedFilesList = New-Object System.Collections.ArrayList

    . C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\FileReadWriteHelper.ps1
    . C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1

    # CONNECTION for new database where the survey is to be pushed.
    $newConnString = "Server=$newDatabaseIP;Database=$newDatabaseName;User Id=$newDatabaseUser;Password=$newDatabasePwd;"

    # CREATE/CLEANUP out directory
    if (-not (Test-Path -Path $outDirectory)) {
        Write-host "Directory does NOT exist - $outDirectory . Creating NEW directory ..."
        New-Item -ItemType Directory -Path $outDirectory
    } else {
        # Path already exists. Remove existing files.
        Remove-Item "$outDirectory\*" -recurse
    }

    # Start RESTAMPING.
    $countOfSurveyFiles = 0
    Write-Host "[RESTAMP] -> Reading Survey StartEpoch and EndEpoch and computing adjusted EpochTime values ..."
    Get-ChildItem -Path $inDirectory -Filter $fileExtFilter | % { 
        $file = $_
        $filename = $file.Name
        $fileFullPath = $file.FullName
        Write-Host "[RESTAMP] -> Processing file - $fileFullPath ..."
        if ($filename.StartsWith("Survey-")) {

            $countOfSurveyFiles++
            $surveyCSVData = Import-Csv $fileFullPath
            $surveyCSVData | % {
                $analyzerId = $_.AnalyzerId
                $script:surveyId = $_.Id
                $script:startEpoch = $_.StartEpoch
                $script:endEpoch = $_.EndEpoch
            }

            $leftShift = $script:endEpoch - $script:startEpoch

            # Get Last Epoch Time in Measurement table for this Analyzer.
            $query = "SELECT TOP 1 [AnalyzerId],[EpochTime]  FROM [dbo].[Measurement] WHERE AnalyzerId='$analyzerId' ORDER BY EpochTime ASC"
            $objResults = Get-DatabaseData -connectionString $newConnString -query $query -isSQLServer:$true
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
            $objResults = Get-DatabaseData -connectionString $newConnString -query $query -isSQLServer:$true
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
            $objResults = Get-DatabaseData -connectionString $newConnString -query $query -isSQLServer:$true
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
            # NOTE: If there is NO RAW data for the Analyzer then smallest is pointing to Survey StartEpoch.
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
        Write-Host "[RESTAMP] -> ### ERROR: There are [$countOfSurveyFiles] Survey.csv files in $inDirectory. Make sure your input folder contains data for ONLY 1 survey"
        Write-Host "[RESTAMP] -> EXITING script..."
        return
    }

    Write-Host "[RESTAMP] -> Found Survey with StartEpoch='$script:startEpoch', EndEpoch='$script:endEpoch' ..."

    # Debug print, startEpoch and endEpoch after restamping.
    if ($script:DEBUG) {
        $testValue1 = Get-AdjustedEpochTime -value $script:startEpoch -offset $offsetInRestamp
        $testValue2 = Get-AdjustedEpochTime -value $script:endEpoch -offset $offsetInRestamp
        $adjustedStartDatetime = UnixDate-ToString -unixDate $testValue1
        $adjustedEndDatetime = UnixDate-ToString -unixDate $testValue2
        Write-Host "[RESTAMP] -> ########### StartEpoch After Restamping=[$adjustedStartDatetime], EndEpoch After Restamping=[$adjustedEndDatetime]"
    }

    Get-ChildItem -Path $inDirectory -Filter $fileExtFilter | % { 
        $file = $_
        $filename = $file.Name
        $fileFullPath = $file.FullName
        $directoryName = $file.DirectoryName
        Write-Host "[RESTAMP] -> Processing - $fileFullPath ..."

        # Read all file content.
        $fileContent = Read-File -filePath $fileFullPath

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

            Write-Host "[RESTAMP] -> Adjust EpochTimes and start writing to fileContent for - '$fileFullPath'"

            $csvData = Import-Csv "$fileFullPath"
            $csvData = @($csvData)       # convert to array to handle single line files. 

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
                    $adjustedStartEpoch = Get-AdjustedEpochTime -value $startEpoch -offset $offsetInRestamp
                    $adjustedEndEpoch = Get-AdjustedEpochTime -value $endEpoch -offset $offsetInRestamp
                
                    if ($script:DEBUG) {
                        Write-Host "[RESTAMP] -> ########### Adjusting Start EpochTime ############ Original Epoch=$startEpoch, Adjusted Epoch=$adjustedStartEpoch"
                        Write-Host "[RESTAMP] -> ########### Adjusting End EpochTime ############ Original Epoch=$endEpoch, Adjusted Epoch=$adjustedEndEpoch"
                    }

                    # Replace Epoch time in line text with adjusted epoch start and end times.
                    $lineText = $lineText -replace $startEpoch, $adjustedStartEpoch
                    $lineText = $lineText -replace $endEpoch, $adjustedEndEpoch

                } else {

                    # Adjust EpochTime
                    $epochTime = $line.EpochTime    
                    $adjustedEpoch = Get-AdjustedEpochTime -value $epochTime -offset $offsetInRestamp
                
                    if ($script:DEBUG) {
                        Write-Host "[RESTAMP] -> ########### Adjusting EpochTime ############ Original Epoch=$epochTime, Adjusted Epoch=$adjustedEpoch"
                    }

                    # Replace Epoch time in line text with adjusted epoch time.
                    $lineText = $lineText -replace $epochTime, $adjustedEpoch
                }

                # Add replaced line to ArrayList
                $null = $fileLinesList.Add("$lineText")

                if ($i % 250 -eq 0) {
                    Write-Host "[RESTAMP] -> File='$fileName', Processing LINE-$i"
                }

                $i++
            }

            Write-Host "[RESTAMP] -> DONE writing restamped EpochTime values to fileContent for - '$fileFullPath'"
        }

        # If fileLinesList is EMPTY read all file lines into the lines list.
        if ($fileLinesList.Count -eq 0) {
            $fileLinesList = Read-FileLines -filePath $fileFullPath
        }

        if ($fileLinesList -ne $NULL -and $fileLinesList.Count -gt 0) {
            # Files with single line can get read as a string. Handle this case.
            if ($fileLinesList.GetType().Name -eq "String") {
                $fileLinesList = @($fileLinesList)       # convert to array if list is a string.
            }

            # Write back the replaced file content to the file.
            $newFileFullPath = "$outDirectory\$filename"

            Write-Host "[RESTAMP] -> Start writing replaced content to FILE for - '$newFileFullPath'"
            Write-FileLines -filePath $newFileFullPath -fileContentLines $fileLinesList
            Write-Host "[RESTAMP] -> DONE writing replaced content to FILE for - '$newFileFullPath'"

        } else {
            Write-Host "[RESTAMP] -> Nothing to write. SKIPPED for file - '$newFileFullPath'"
        }
    }

    if ($script:replacedFilesList.Count -gt 0) {
        Write-Host "[RESTAMP] -> `r`n#### SurveyId references were FOUND & MODIFIED for Surveys created from following files:-" 
        $script:replacedFilesList | % { $file = $_; Write-Host $file }
    } else {
        Write-Host "[RESTAMP] -> SurveyId references NOT changed in any files."
    }

    if ($script:restampedFilesList.Count -gt 0) {
        Write-Host "[RESTAMP] -> `r`n#### EpochTime restamping DONE for Surveys created from following files:-" 
        $script:restampedFilesList | % { $file = $_; Write-Host $file }
    } else {
        Write-Host "[RESTAMP] -> EpochTime restamping NOT done in any files."
    }

    Write-Host "##### ------------------------------------------------------------- #####"
    Write-Host " Restamped files can be found in directory: $outDirectory "
    Write-Host "##### ------------------------------------------------------------- #####"
}
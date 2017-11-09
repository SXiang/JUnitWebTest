<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION:
  - Tests to run when verifying seed data related changes for blank DB deployment.

 Sample Run Script:
   .\RunTests-VerifyBlankDBSeedDataPush.ps1 `
        -BaseScriptFolder "C:\Repositories\surveyor-qa"  `
        -databaseIPAddress "SPULIKKAL-ZBOOK\PULIKKALSQLSVR"  `
        -databaseName "Surveyor_20171107"  `
        -databaseUser "awssa"  `
        -databasePassword "j!RuL1Gd7A"  `
        -inputFolderWithCSVFiles "C:\Repositories\surveyor-qa\selenium-wd\data\sql\SurveySeedData"  `
        -outputFolder "C:\temp"

----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $baseScriptFolder,

  [Parameter(Mandatory=$true)]
  [String] $databaseIPAddress,           # Database where the surveys are located. eg. 20.20.130.238

  [Parameter(Mandatory=$true)]
  [String] $databaseName,                # Database name. eg. SurveyorSQA

  [Parameter(Mandatory=$true)]
  [String] $databaseUser,                # User with access to DB. Eg awssa

  [Parameter(Mandatory=$true)]
  [String] $databasePassword,            # DB user password

  [Parameter(Mandatory=$true)]
  [String] $inputFolderWithCSVFiles,     # Folder which contains the survey seed CSV files.

  [Parameter(Mandatory=$true)]
  [String] $outputFolder                 # Folder where test result output will be written.
)

# Tests to RUN
$IT_SHOULD_FIND_EXPECTED_NUMBER_OF_SURVEYS_IN_DB = $true
$IT_SHOULD_FIND_ALL_SURVEYS_WITH_COMPLETED_STATUS_IN_DB = $true
$IT_SHOULD_FIND_CORRECT_RAW_DATA_COUNT_FOR_SURVEYS_IN_DB = $true
$IT_SHOULD_FIND_SAME_SURVEY_RESULT_FOR_SQACUS_AND_PICARRO_CUSTOMERS = $true

. "$baseScriptFolder\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"
. "$baseScriptFolder\selenium-wd\lib\HelperScripts\FileReadWriteHelper.ps1"
. "$baseScriptFolder\selenium-wd\lib\HelperScripts\SurveyCsvRelated\Survey-CommonHelpers.ps1"

[Int64] $expectedSurveysInDB = 0
$EPSILON = "0.5"
$SURVEY_PREFIX = "Survey-"

# Stores info about Raw data rowcounts for each survey from the CSV
$surveyRawDataMap = @{}

# Stores all survey IDs from the CSVs
$surveyIdList = New-Object System.Collections.ArrayList

$OUTRESULT = New-Item "$outputFolder\testresults.txt" -Force
$connString = "Server=$databaseIPAddress;Database=$databaseName;User Id=$databaseUser;Password=$databasePassword;"

# Compute Expected surveys
$measure = Split-Path -Path "$inputFolderWithCSVFiles\Survey-*.csv" -Leaf -Resolve | Measure-Object
$expectedSurveysInDB = $measure.Count

Split-Path -Path "$inputFolderWithCSVFiles\Survey-*.csv" -Leaf -Resolve | % {
    [string]$fileName = [string]$_
    $fileFullPath = "$inputFolderWithCSVFiles\$fileName"
    Write-Host "Processing survey file -> $fileFullPath"
    $csvData = Import-Csv $fileFullPath
    $csvData | % {
        $surveyId = $_.Id
        $null = $surveyIdList.Add($surveyId)

        $len = $SURVEY_PREFIX.Length
        $fileNameSuffix = $fileName.Substring($len, $fileName.Length-$len)

        $anemometerRawFile = "AnemometerRaw-$fileNameSuffix"
        $anemometerRawFileFullPath = "$inputFolderWithCSVFiles\$anemometerRawFile"
        $anemRowCount = 0        
        if (Test-Path $anemometerRawFileFullPath) {
            $anemRowCount = (gc $anemometerRawFileFullPath | measure).Count - 1
        }

        $gpsRawFile = "GPSRaw-$fileNameSuffix"
        $gpsRawFileFullPath = "$inputFolderWithCSVFiles\$gpsRawFile"
        $gpsRowCount = 0
        if (Test-Path $gpsRawFileFullPath) {
            $gpsRowCount = (gc $gpsRawFileFullPath | measure).Count - 1
        }

        $measRawFile = "Measurement-$fileNameSuffix"
        $measRawFileFullPath = "$inputFolderWithCSVFiles\$measRawFile"
        $measRowCount = 0
        if (Test-Path $measRawFileFullPath) {
            $measRowCount = (gc $measRawFileFullPath | measure).Count - 1
        }

        $object = New-Object PSObject -Property @{                        SurveyId             = $surveyId                             AnemometerRowCount   = $anemRowCount                          GpsRowCount          = $gpsRowCount                        MeasurementRowCount  = $measRowCount
        }

        $surveyRawDataMap.set_item($surveyId, $object)
    }
}

$i=0
$inClause = New-Object System.Text.StringBuilder
$surveyIdList | %{
    $srvId = $_
    if ($i -eq 0) {
        $inClause.Append("'$srvId'")
    } else {
        $inClause.Append(",'$srvId'")
    }

    $i++
}

$surveyIDs = $inClause.ToString()

# Inner joins included intentionally to ensure referenced test seed data (eg. Analyzer, SurveyorUnit, Customer, User) present in DB.
$query = "SELECT S.[Id] AS SurveyId" +
            "      ,A.SerialNumber AS AnalyzerSerialNumber" +
            "      ,SU.Description AS SurveyorUnitDesc" +
            "      ,U.UserName" +
            "      ,C.[Name] AS CustomerName" +
            "      ,S.[Tag] AS SurveyTag" +
            "	  ,(SELECT COUNT(*) FROM [dbo].[Measurement] AS M WHERE M.AnalyzerId=S.AnalyzerId AND M.EpochTime > S.StartEpoch - $EPSILON AND M.EpochTime < S.EndEpoch + $EPSILON) AS MeasurementRecordCount" +
            "	  ,(SELECT COUNT(*) FROM [dbo].[GPSRaw] AS G WHERE G.AnalyzerId=S.AnalyzerId AND G.EpochTime > S.StartEpoch - $EPSILON AND G.EpochTime < S.EndEpoch + $EPSILON) AS GpsRecordCount" +
            "	  ,(SELECT COUNT(*) FROM [dbo].[AnemometerRaw] AS AN WHERE AN.AnalyzerId=S.AnalyzerId AND AN.EpochTime > S.StartEpoch - $EPSILON AND AN.EpochTime < S.EndEpoch + $EPSILON) AS AnemometerRecordCount" +
            "      ,S.[Status]" +
            "      ,S.[Deleted]" +
            "      ,S.[ProcessingDateStarted]" +
            "      ,S.[ProcessingDateCompleted]" +
            "  FROM [dbo].[Survey] AS S" +
            "   INNER JOIN [dbo].[Analyzer] AS A ON S.AnalyzerId=A.Id" +
            "   INNER JOIN [dbo].[SurveyorUnit] AS SU ON S.SurveyorUnitId=SU.Id" +
            "   INNER JOIN [dbo].[User] AS U ON S.UserId=U.Id" +
            "   INNER JOIN [dbo].[Customer] AS C ON U.CustomerId=C.Id" +
            "   WHERE S.[Id] IN ($surveyIDs)" +
            "   ORDER BY SurveyTag"

$surveyInfoInDB = @{}
$objSurveys = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
$idx = 0
$objSurveys | %{
    if ($idx -gt 0) {
        $obj = $_
        [String]$SurveyId = [String]$obj.SurveyId

        $object = New-Object PSObject -Property @{
            SurveyId = $obj.SurveyId
            MeasurementRecordCount = $obj.MeasurementRecordCount
            GpsRecordCount = $obj.GpsRecordCount
            AnemometerRecordCount = $obj.AnemometerRecordCount
            Status = $obj.Status
            ProcessingDateStarted = $obj.ProcessingDateStarted
            ProcessingDateCompleted = $obj.ProcessingDateCompleted
        }

        $surveyInfoInDB.set_item($SurveyId, $object)
    }

    $idx++
}

function Write-Result($result, $message) {
    Write-ToOutput -line "[$result] : $message"
}

function Write-ToOutput($line) {
    Write-Host $line
    Add-Content $OUTRESULT $line
}

function Print-SurveysNotFoundInDB() {
    $surveyIdList | % {
        $srvIdFromCSV = $_
        if (!$surveyInfoInDB.ContainsKey($srvIdFromCSV)) {
            Write-ToOutput -line "Survey from CSV NOT found in DB -> $srvIdFromCSV"
        }
    }
}

### TEST 1: Survey Count in DB should match expectedSurveyCount
if ($IT_SHOULD_FIND_EXPECTED_NUMBER_OF_SURVEYS_IN_DB) {
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line "Executing TEST 1 - Survey Count in DB should match expectedSurveyCount"
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line ""
    $actualSurveyCount = $objSurveys.Count - 1
    if ($actualSurveyCount -eq $expectedSurveysInDB) {
        Write-Result -result "PASS" -message "Expected survey count=[$expectedSurveysInDB] and actual survey count=[$actualSurveyCount] MATCH."
    } else {
        Write-Result -result "FAIL" -message "Expected survey count=[$expectedSurveysInDB] and actual survey count=[$actualSurveyCount] do NOT MATCH."
        Print-SurveysNotFoundInDB 
    }
}

### TEST 2: All Surveys should have Completed status
if ($IT_SHOULD_FIND_ALL_SURVEYS_WITH_COMPLETED_STATUS_IN_DB) {
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line "Executing TEST 2 - All Surveys should have Completed status"
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line ""
    $surveyInfoInDB.Keys | %{
        [String]$key = [String]$_
        [String]$SurveyId = $key
        $obj = $surveyInfoInDB.get_item($key)

        if ($obj -eq $null) {
            Write-Result -result "FAIL" -message "Survey - '$SurveyId' NOT found in database. Check correct input data files and DB credentials have been used."
        } else {    
            $Status = $obj.Status
            if ($Status -eq "Completed") {
                Write-Result -result "PASS" -message "Expected status='Completed' MATCH for survey id=[$SurveyId]."    
            } else {
                Write-Result -result "FAIL" -message "Expected status='Completed' does NOT MATCH for survey id=[$SurveyId]. Actual status='$Status'."    
            }
        }
    }
}

### TEST 3: Raw data in DB should match pushed raw data for all surveys
if ($IT_SHOULD_FIND_CORRECT_RAW_DATA_COUNT_FOR_SURVEYS_IN_DB) {
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line "Executing TEST 3 - Raw data in DB should match pushed raw data for all surveys"
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line ""
    $surveyInfoInDB.Keys | %{
        $key = $_
        $obj = $surveyInfoInDB.get_item($key)
        $SurveyId = $key

        if ($obj -eq $null) {
            Write-Result -result "FAIL" -message "Survey - '$SurveyId' NOT found in database. Check correct input data files and DB credentials have been used."
        } else {    
            # actual
            $AnemometerRecordCount = $obj.AnemometerRecordCount
            $GpsRecordCount = $obj.GpsRecordCount
            $MeasurementRecordCount = $obj.MeasurementRecordCount

            # expected
            $rawData = $surveyRawDataMap.get_item($SurveyId)
            $expectedAnemCount = $rawData.AnemometerRowCount
            $expectedGpsCount = $rawData.GpsRowCount
            $expectedMeasCount = $rawData.MeasurementRowCount

            if ($AnemometerRecordCount -eq $expectedAnemCount) {
                Write-Result -result "PASS" -message "Expected Anemometer raw count=[$expectedAnemCount] MATCH for survey id=[$SurveyId]."    
            } else {
                Write-Result -result "FAIL" -message "Expected Anemometer raw count=[$expectedAnemCount] does NOT MATCH for survey id=[$SurveyId]. Actual count=[$AnemometerRecordCount]"    
            }

            if ($GpsRecordCount -eq $expectedGpsCount) {
                Write-Result -result "PASS" -message "Expected GPS raw count=[$expectedGpsCount] MATCH for survey id=[$SurveyId]."    
            } else {
                Write-Result -result "FAIL" -message "Expected GPS raw count=[$expectedGpsCount] does NOT MATCH for survey id=[$SurveyId]. Actual count=[$GpsRecordCount]"    
            }

            if ($MeasurementRecordCount -eq $expectedMeasCount) {
                Write-Result -result "PASS" -message "Expected Measurement raw count=[$expectedMeasCount] MATCH for survey id=[$SurveyId]."    
            } else {
                Write-Result -result "FAIL" -message "Expected Measurement raw count=[$expectedMeasCount] does NOT MATCH for survey id=[$SurveyId]. Actual count=[$MeasurementRecordCount]"    
            }
        }
    }
}

### TEST 4: SQACUS and PICARRO surveys should have SAME survey results
if ($IT_SHOULD_FIND_SAME_SURVEY_RESULT_FOR_SQACUS_AND_PICARRO_CUSTOMERS) {
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line "Executing TEST 4 - SQACUS and PICARRO surveys should have SAME survey results"
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line ""

    $surveyCsvFolder = "$baseScriptFolder\selenium-wd\data\sql\SurveySeedData"
    get-childItem $surveyCsvFolder -Filter "*.csv" | %{
        $file = $_
        $fileName = $file.Name
        $fileFullpath = $file.FullName
        $fileDirPath = $file.DirectoryName
    
        if ($fileName.StartsWith("Survey-")) {
            $isPicarroSurvey = $fileName.StartsWith("Survey-") -and (-not $fileName.Contains("sqacus"))        
            if ($isPicarroSurvey) { 
                $correspondingSqacusSurvey = Get-OtherCustomerSurvey -filename $filename -fileDirectory $fileDirPath -customerName "sqacus"
                if ($correspondingSqacusSurvey -ne "") {
                    Write-Host "Found survey CSV - '$filename' that exists for both 'Picarro' and 'sqacus'"

                    $null = $fileName -match "Survey-(.+)\.csv"
                    $picFilenameTag = $Matches[1]

                    Write-ToOutput -line ""
                    Write-ToOutput -line "TEST 4 - Verifying survey results for '$picFilenameTag'"
                    Write-ToOutput -line "----------------------------------------------------------------------------------------"
                    Write-ToOutput -line ""

                    $null = $correspondingSqacusSurvey -match "Survey-(.+)\.csv"
                    $sqacusFilenameTag = $Matches[1]

                    # Verify Segment MATCH.
                    $picSegmentFile = "$fileDirPath\Segment-${picFilenameTag}.csv"
                    $sqacusSegmentFile = "$fileDirPath\Segment-${sqacusFilenameTag}.csv"
                    if (Test-Path $picSegmentFile) {
                        $picSegmentCount = $(Read-FileLines -filePath $picSegmentFile).Count
                        $sqacusSegmentCount = $(Read-FileLines -filePath $sqacusSegmentFile).Count

                        if ($picSegmentCount -eq $sqacusSegmentCount) {
                            Write-Result -result "PASS" -message "Segments MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]." 
                        } else {
                            Write-Result -result "FAIL" -message "Segments do NOT MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]."
                        }
                    }

                    # Verify Segment-Geom MATCH.
                    $picSegmentGeomFile = "$fileDirPath\Segment-Geom-${picFilenameTag}.csv"
                    $sqacusSegmentGeomFile = "$fileDirPath\Segment-Geom-${sqacusFilenameTag}.csv"
                    if (Test-Path $picSegmentGeomFile) {
                        $picSegmentGeomCount = $(Read-FileLines -filePath $picSegmentGeomFile).Count
                        $sqacusSegmentGeomCount = $(Read-FileLines -filePath $sqacusSegmentGeomFile).Count

                        if ($picSegmentGeomCount -eq $sqacusSegmentGeomCount) {
                            Write-Result -result "PASS" -message "Segment-Geoms MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]." 
                        } else {
                            Write-Result -result "FAIL" -message "Segment-Geoms do NOT MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]."
                        }
                    }

                    # Verify SurveyResult MATCH.
                    $picSurveyResultFile = "$fileDirPath\SurveyResult-${picFilenameTag}.csv"
                    $sqacusSurveyResultFile = "$fileDirPath\SurveyResult-${sqacusFilenameTag}.csv"
                    if (Test-Path $picSurveyResultFile) {
                        $picSurveyResultCount = $(Read-FileLines -filePath $picSurveyResultFile).Count
                        $sqacusSurveyResultCount = $(Read-FileLines -filePath $sqacusSurveyResultFile).Count

                        if ($picSurveyResultCount -eq $sqacusSurveyResultCount) {
                            Write-Result -result "PASS" -message "SurveyResults MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]." 
                        } else {
                            Write-Result -result "FAIL" -message "SurveyResults do NOT MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]."
                        }
                    }

                    # Verify SurveyResult-Geom MATCH.
                    $picSurveyResultGeomFile = "$fileDirPath\SurveyResult-Geom-${picFilenameTag}.csv"
                    $sqacusSurveyResultGeomFile = "$fileDirPath\SurveyResult-Geom-${sqacusFilenameTag}.csv"
                    if (Test-Path $picSurveyResultGeomFile) {
                        $picSurveyResultGeomCount = $(Read-FileLines -filePath $picSurveyResultGeomFile).Count
                        $sqacusSurveyResultGeomCount = $(Read-FileLines -filePath $sqacusSurveyResultGeomFile).Count

                        if ($picSurveyResultGeomCount -eq $sqacusSurveyResultGeomCount) {
                            Write-Result -result "PASS" -message "SurveyResult-Geoms MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]." 
                        } else {
                            Write-Result -result "FAIL" -message "SurveyResult-Geoms do NOT MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]."
                        }
                    }

                    # Verify Peak MATCH.
                    $picPeakFile = "$fileDirPath\Peak-${picFilenameTag}.csv"
                    $sqacusPeakFile = "$fileDirPath\Peak-${sqacusFilenameTag}.csv"
                    if (Test-Path $picPeakFile) {
                        $picPeakCount = $(Read-FileLines -filePath $picPeakFile).Count
                        $sqacusPeakCount = $(Read-FileLines -filePath $sqacusPeakFile).Count

                        if ($picPeakCount -eq $sqacusPeakCount) {
                            Write-Result -result "PASS" -message "Peaks MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]." 
                        } else {
                            Write-Result -result "FAIL" -message "Peaks do NOT MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]."
                        }
                    }

                    # Verify CaptureEvent MATCH.
                    $picCaptureEventFile = "$fileDirPath\CaptureEvent-${picFilenameTag}.csv"
                    $sqacusCaptureEventFile = "$fileDirPath\CaptureEvent-${sqacusFilenameTag}.csv"
                    if (Test-Path $picCaptureEventFile) {
                        $picCaptureEventCount = $(Read-FileLines -filePath $picCaptureEventFile).Count
                        $sqacusCaptureEventCount = $(Read-FileLines -filePath $sqacusCaptureEventFile).Count

                        if ($picCaptureEventCount -eq $sqacusCaptureEventCount) {
                            Write-Result -result "PASS" -message "CaptureEvents MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]." 
                        } else {
                            Write-Result -result "FAIL" -message "CaptureEvents do NOT MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]."
                        }
                    }

                    # Verify FieldOfView MATCH.
                    $picFieldOfViewFile = "$fileDirPath\FieldOfView-${picFilenameTag}.csv"
                    $sqacusFieldOfViewFile = "$fileDirPath\FieldOfView-${sqacusFilenameTag}.csv"
                    if (Test-Path $picFieldOfViewFile) {
                        $picFieldOfViewCount = $(Read-FileLines -filePath $picFieldOfViewFile).Count
                        $sqacusFieldOfViewCount = $(Read-FileLines -filePath $sqacusFieldOfViewFile).Count

                        if ($picFieldOfViewCount -eq $sqacusFieldOfViewCount) {
                            Write-Result -result "PASS" -message "FieldOfViews MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]." 
                        } else {
                            Write-Result -result "FAIL" -message "FieldOfViews do NOT MATCH for Picarro and Sqacus surveys for survey filetag =[$picFilenameTag]."
                        }
                    }
                }
            }
        }
    }
}

ii $OUTRESULT
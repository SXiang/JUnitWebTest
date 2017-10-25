<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION:
  - Tests to run when verifying seed data related changes for blank DB deployment.

 Sample Run Script:
   .\RunTests-VerifyBlankDBSeedDataPush.ps1 `
        -databaseIPAddress "SPULIKKAL-ZBOOK\PULIKKALSQLSVR"  `
        -databaseName "Surveyor_20171017"  `
        -databaseUser "awssa"  `
        -databasePassword "j!RuL1Gd7A"  `
        -inputFolderWithCSVFiles "C:\Repositories\surveyor-qa\selenium-wd\data\sql\SurveySeedData"  `
        -outputFolder "C:\temp"

----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $databaseIPAddress,           # Database where the surveys are located. eg. 20.20.130.238

  [Parameter(Mandatory=$true)]
  [String] $databaseName,                # Database name. eg. SurveyorSQA

  [Parameter(Mandatory=$true)]
  [String] $databaseUser,                # User with access to DB. Eg awssa

  [Parameter(Mandatory=$true)]
  [String] $databasePassword,            # DB user password

  [Parameter(Mandatory=$true)]
  [String] $inputFolderWithCSVFiles,     # Folder with contains the CSV files.

  [Parameter(Mandatory=$true)]
  [String] $outputFolder                 # Folder where test result output will be written.
)

. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"

[Int64] $expectedSurveysInDB = 0

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

        $anemometerRawFile = $fileName.Replace("Survey-", "AnemometerRaw-")
        $anemometerRawFileFullPath = "$inputFolderWithCSVFiles\$anemometerRawFile"
        $anemRowCount = 0        
        if (Test-Path $anemometerRawFileFullPath) {
            $anemRowCount = (gc $anemometerRawFileFullPath | measure).Count - 1
        }

        $gpsRawFile = $fileName.Replace("Survey-", "GPSRaw-")
        $gpsRawFileFullPath = "$inputFolderWithCSVFiles\$gpsRawFile"
        $gpsRowCount = 0
        if (Test-Path $gpsRawFileFullPath) {
            $gpsRowCount = (gc $gpsRawFileFullPath | measure).Count - 1
        }

        $measRawFile = $fileName.Replace("Survey-", "Measurement-")
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
            "	  ,(SELECT COUNT(*) FROM [dbo].[Measurement] AS M WHERE M.AnalyzerId=S.AnalyzerId AND M.EpochTime > S.StartEpoch - 0.5 AND M.EpochTime < S.EndEpoch + 0.5) AS MeasurementRecordCount" +
            "	  ,(SELECT COUNT(*) FROM [dbo].[GPSRaw] AS G WHERE G.AnalyzerId=S.AnalyzerId AND G.EpochTime > S.StartEpoch - 0.5 AND G.EpochTime < S.EndEpoch + 0.5) AS GpsRecordCount" +
            "	  ,(SELECT COUNT(*) FROM [dbo].[AnemometerRaw] AS AN WHERE AN.AnalyzerId=S.AnalyzerId AND AN.EpochTime > S.StartEpoch - 0.5 AND AN.EpochTime < S.EndEpoch + 0.5) AS AnemometerRecordCount" +
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


### TEST 1: Survey Count in DB should match expectedSurveyCount
Write-ToOutput -line "----------------------------------------------------------------------------------------"
Write-ToOutput -line "Executing TEST 1 - Survey Count in DB should match expectedSurveyCount"
Write-ToOutput -line "----------------------------------------------------------------------------------------"
Write-ToOutput -line ""
$actualSurveyCount = $objSurveys.Count - 1
if ($actualSurveyCount -eq $expectedSurveysInDB) {
    Write-Result -result "PASS" -message "Expected survey count=[$expectedSurveysInDB] and actual survey count=[$actualSurveyCount] MATCH."
} else {
    Write-Result -result "FAIL" -message "Expected survey count=[$expectedSurveysInDB] and actual survey count=[$actualSurveyCount] do NOT MATCH."
}

### TEST 2: All Surveys should have Completed status
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


### TEST 3: Raw data in DB should match pushed raw data for all surveys
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


ii $OUTRESULT
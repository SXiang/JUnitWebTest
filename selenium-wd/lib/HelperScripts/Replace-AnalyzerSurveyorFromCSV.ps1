<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION: 
  - This script can be used to replace column values in survey csv files with the specified values.
    Column values that will be replaced are:
    1. AnalyzerId
    2. SurveyorUnitId
    3. ReferenceGasBottleId
    4. UserId
  When changing the AnalyzerId we have the following 2 considerations to take care of:
  a) Ensuring that we are using the correct hardware type for the Analyzer. 
  EXECUTE following SQL script to get the new AnalyzerId, SurveyorUnitId, RefGasBottleId and UserId for a specific AnalyzerSerialNumber and CapabilityType.
    USE [Surveyor_BlankDB_20160926]  -- Change DB name.
    GO
    SELECT A.[Id],A.[SurveyorUnitId],RGB.Id AS ReferenceGasBottleId, U.Id AS UserID, U.UserName,A.SerialNumber FROM [dbo].[Analyzer] AS A 
	    INNER JOIN [dbo].[SurveyorUnit] AS SU ON A.SurveyorUnitId=SU.Id 
	    INNER JOIN [dbo].[ReferenceGasBottle] AS RGB ON A.SurveyorUnitId=RGB.SurveyorUnitId 
	    INNER JOIN [dbo].[AnalyzerHardwareCapabilityType] AS AHC ON A.Id = AHC.AnalyzerId
	    INNER JOIN [dbo].[HardwareCapabilityTypes] AS HC ON AHC.HardwareCapabilityTypeId = HC.Id
	    INNER JOIN [dbo].[Location] AS L ON L.Id = SU.LocationId
	    INNER JOIN [dbo].[User] AS U on U.LocationId=L.Id
	    WHERE A.SerialNumber='RFADS2004' AND HC.Name= 'Ethane' --AND U.Username='sqapicsu1@picarro.com'   -- (Uncomment username to get information for specific username)
  
  b) Ensuring that the values in the CSV are restamped to avoid PrimaryKey conflicts on tables with PK={AnalyzerId, EpochTime} 

 USAGE SCENARIOS: 
  1. Use this script if you have survey data generated using an Analyzer that is NOT part of Automation seed data.
  
 Sample Run Script: 
   .\Replace-AnalyzerSurveyorFromCSV.ps1 `
        -oldDatabaseIP "20.20.130.210"  `
        -oldDatabaseName "SurveyorSQAAUTO_blankDB_20160823"  `
        -oldDatabaseUser "awssa"  `
        -oldDatabasePwd "3Vf763pSg2"  `
        -newDatabaseIP "20.20.130.210"  `
        -newDatabaseName "SurveyorSQAAUTO_blankDB_20160923"  `
        -newDatabaseUser "awssa"  `
        -newDatabasePwd "3Vf763pSg2"  `
        -surveyId "31683DB5-3592-E0AC-3B49-39DA65CE0811"  `
        -inDirectory "C:\temp\P3300ManualSurvey\csvs"  `
        -fileExtFilter "*.csv"  `
        -outDirectory "C:\temp\P3300ManualSurvey\csvs\Replaced"  `
        -newAnalyzerId "26F7026D-788B-0413-0D89-39D76AFCAAFE"  `
        -newSurveyorUnitId "47FC54A4-26ED-7306-4D1D-39D76AFC27C4"  `
        -newReferenceGasBottleId "34E929E4-CEF1-F8A6-C3A6-39D76AFD4CAC"  `
        -newUserId "4A48E909-6264-5B94-C448-39D71E69823B"
----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  ####--------------------------------------------------------------####
  #### Old Database -> Database from where the survey is to be READ ####  
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $oldDatabaseIP="20.20.130.210",

  [Parameter(Mandatory=$false)]
  [String] $oldDatabaseName="SurveyorSQAAUTO_blankDB_20160823",

  [Parameter(Mandatory=$false)]
  [String] $oldDatabaseUser="awssa",

  [Parameter(Mandatory=$false)]
  [String] $oldDatabasePwd="3Vf763pSg2",

  ####--------------------------------------------------------------####
  #### New Database -> Database to where the survey is to be PUSHED ####
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $newDatabaseIP="20.20.130.210",

  [Parameter(Mandatory=$false)]
  [String] $newDatabaseName="SurveyorSQAAUTO_blankDB_20160923",

  [Parameter(Mandatory=$false)]
  [String] $newDatabaseUser="awssa",

  [Parameter(Mandatory=$false)]
  [String] $newDatabasePwd="3Vf763pSg2",

  ####--------------------------------------------------------------####
  #### Id of survey is to be PUSHED. Value from Old Database        ####
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $surveyId = "31683DB5-3592-E0AC-3B49-39DA65CE0811",

  ####--------------------------------------------------------------####
  #### CSV file parameters                                          ####
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $inDirectory="C:\temp\P3300ManualSurvey\csvs",

  [Parameter(Mandatory=$false)]
  [String] $fileExtFilter="*.csv",

  [Parameter(Mandatory=$false)]
  [String] $outDirectory="C:\temp\P3300ManualSurvey\csvs\Replaced",

  ####--------------------------------------------------------------####
  #### New IDs to be used in the survey data CSV files              ####
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $newAnalyzerId="26F7026D-788B-0413-0D89-39D76AFCAAFE",

  [Parameter(Mandatory=$false)]
  [String] $newSurveyorUnitId="47FC54A4-26ED-7306-4D1D-39D76AFC27C4",

  [Parameter(Mandatory=$false)]
  [String] $newReferenceGasBottleId="34E929E4-CEF1-F8A6-C3A6-39D76AFD4CAC",

  [Parameter(Mandatory=$false)]
  [String] $newUserId="4A48E909-6264-5B94-C448-39D71E69823B"
)

. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1
. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\Restamp-EpochTimesInCSV.ps1

# CONNECTION for original database where the survey was generated.
$oldConnString = "Server=$oldDatabaseIP;Database=$oldDatabaseName;User Id=$oldDatabaseUser;Password=$oldDatabasePwd;"
$oldSurveyQuery = "SELECT [Id],[Tag],[StartEpoch] - 0.05 AS [AdjustedStartEpoch],[EndEpoch] + 0.05 AS [AdjustedEndEpoch],[AnalyzerId],[SurveyorUnitId],[ReferenceGasBottleId],[UserId] FROM [dbo].[Survey] WHERE ID = '$surveyId'"
$objOldSurvey = Get-DatabaseData -connectionString $oldConnString -query $oldSurveyQuery -isSQLServer:$true

$script:oldAnalyzerId = ""
$script:oldSurveyorUnitId = ""
$script:oldReferenceGasBottleId = ""
$script:oldUserId = ""

#### First replace AnalyzerId, SurveyorUnitId, ReferenceGasBottleId and UserId ####
$i = 0
$objOldSurvey | foreach { 
    if ($i -gt 0) {     # first row is length of array. datarows are from index=1
        $obj = $_; 
        $id = $obj.Id
        $tag = $obj.Tag
        $startEpoch = $obj.AdjustedStartEpoch
        $endEpoch = $obj.AdjustedEndEpoch
        $script:oldAnalyzerId = $obj.AnalyzerId.ToString()
        $script:oldSurveyorUnitId = $obj.SurveyorUnitId.ToString()
        $script:oldReferenceGasBottleId = $obj.ReferenceGasBottleId.ToString()
        $script:oldUserId = $obj.UserId.ToString();
    }

    $i++
}

if ($script:oldAnalyzerId -eq "") {
    Write-Host "Did NOT find any surveys for the specified SurveyID. Check SurveyID"
    return
}

# Create out directory if it does NOT exist.
if (-not (Test-Path -Path $outDirectory)) {
    Write-host "Directory does NOT exist - $outDirectory . Creating NEW directory ..."
    New-Item -ItemType Directory -Path $outDirectory
} else {
    #Path already exists. Remove existing files.
    Remove-Item "$outDirectory\*" -recurse
}

# Replace AnalyzerId ,SurveyorUnitId, RefGasBottleId, UserId
Write-Host "####--------------------------------------------------------------####"
Write-Host "#### Replacing AnalyzerId ,SurveyorUnitId, RefGasBottleId, UserId ####"
Write-Host "####--------------------------------------------------------------####"

Split-Path -Path "$inDirectory\$fileExtFilter" -Leaf -Resolve | % {
    [string]$fileName = [string]$_
    $fileFullPath = "$inDirectory\$fileName"
    $newFileFullPath = "$outDirectory\$fileName"

    Write-Host "[REPLACE_IDS] -> Processing file - $fileFullPath ..."

    $fileContent = ""

    # replace IDs with specified IDs.
    Get-Content $fileFullPath | % { 
        $line = $_.trim()
        if ($line -ne "") {
            $fileContent += $line + "`r`n"
        }
    } 

    $fileContent = $fileContent -replace $script:oldAnalyzerId, $newAnalyzerId
    $fileContent = $fileContent -replace $script:oldSurveyorUnitId, $newSurveyorUnitId
    $fileContent = $fileContent -replace $script:oldReferenceGasBottleId, $newReferenceGasBottleId
    $fileContent = $fileContent -replace $script:oldUserId, $newUserId

    #write content into output file
    $stream = [System.IO.StreamWriter] $newFileFullPath
    $stream.Write($fileContent)
    $stream.close()
}

#### Next restamp Epoch times in CSV files. ####
Write-Host "####--------------------------------------------------------------####"
Write-Host "#### RESTAMP EPOCH TIMES                                          ####"
Write-Host "####--------------------------------------------------------------####"

$restampedDirectory = "$inDirectory\Restamped"
# Create restamp directory if it does NOT exist.
if (-not (Test-Path -Path $restampedDirectory)) {
    Write-host "Directory does NOT exist - $restampedDirectory . Creating NEW directory ..."
    New-Item -ItemType Directory -Path $restampedDirectory
} else {
    #Path already exists. Remove existing files.
    Remove-Item "$restampedDirectory\*" -recurse
}

Restamp-EpochTimes -newDatabaseIP $newDatabaseIP -newDatabaseName $newDatabaseName -newDatabaseUser $newDatabaseUser  `
    -newDatabasePwd $newDatabasePwd -inDirectory $outDirectory -fileExtFilter $fileExtFilter -outDirectory $restampedDirectory
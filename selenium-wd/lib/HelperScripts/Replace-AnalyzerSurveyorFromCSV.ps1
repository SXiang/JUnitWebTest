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
  2. To replicate survey data from one customer to another.
  
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
        -surveyIds "31683DB5-3592-E0AC-3B49-39DA65CE0811,31683DB5-3592-E0AC-3B49-39DA65CE0810"  `
        -inDirectory "C:\temp\P3300ManualSurvey\csvs"  `
        -fileExtFilter "*.csv"  `
        -outDirectory "C:\temp\P3300ManualSurvey\csvs\Replaced"  `
        -newAnalyzerId "26F7026D-788B-0413-0D89-39D76AFCAAFE"  `
        -newSurveyorUnitId "47FC54A4-26ED-7306-4D1D-39D76AFC27C4"  `
        -newReferenceGasBottleId "34E929E4-CEF1-F8A6-C3A6-39D76AFD4CAC"  `
        -newUserId "4A48E909-6264-5B94-C448-39D71E69823B"  `
        -newLocationId "DE13ACD0-C158-ECAC-7F48-39D18113D702"  `
        -surveyTagFileMapCSVRelativePath "SurveyTagFileMap.csv"  `
        -restampCSVs:$false  `
        -generateNewGuidsForSurvey:$true
----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  ####--------------------------------------------------------------####
  #### Old Database -> Database from where the survey is to be READ ####  
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $oldDatabaseIP="20.20.130.210",

  [Parameter(Mandatory=$false)]
  [String] $oldDatabaseName="SurveyorSQAAuto_blankDB_20161202",

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
  [String] $newDatabaseName="SurveyorSQAAuto_blankDB_20161202",

  [Parameter(Mandatory=$false)]
  [String] $newDatabaseUser="awssa",

  [Parameter(Mandatory=$false)]
  [String] $newDatabasePwd="3Vf763pSg2",

  ####--------------------------------------------------------------------------------------####
  #### Comma seperated value of Ids of surveys to be PUSHED. Value from Old Database        ####
  ####--------------------------------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $surveyIds = "43A218A4-00AE-F7BC-588A-39D15301044F,02510F74-5EE7-CD23-5C54-39D153175095,271D9DB6-C0BD-EB32-2B48-39D1531DC264,C8782024-CC65-B53A-5317-39D4B4F4731F,04B2FEDA-EE18-A49E-3208-39D4B50F48FE,1556AC85-A125-0347-2A02-39D4B529C6BD,4D7BF7AC-6CC2-9B18-1894-39D4B546927B,2278D26F-8D69-B070-56FD-39D4B552F8F2,8497A375-2D3A-94E7-7AD4-39D4B9762AB7,E15E6D60-F49B-0F45-C9FA-39D4B99D9FDC,BAAA0BF0-5D42-A94E-8311-39D4BED10910,D55FA29B-E69E-0F61-D00E-39D4BEEB76E5,18533532-4401-1F22-0EFD-39D60E1D0E2B,069DB64B-55D5-7522-48E4-39D61C888093,028F8FB7-8BA3-A44B-F778-39D626CD322B,8AF3B409-FAD3-9B1C-1846-39D6D20F64C6,7D502F69-0921-C3E8-22ED-39D76B1CAB8E,F0052077-F662-1C01-5654-39D76B475E9C,068F0961-32BC-D508-5DAB-39D76B9AB1EA,E0802F97-591D-4806-D48E-39D76BB73231,094EA028-139C-DC47-29F9-39D76BCB2F3C,946338AA-A24E-0E92-1DC3-39D76BD9F54D,4CE925C5-A081-319D-220A-39D83A6B8751,861905EE-400E-7FC8-558F-39D9FE5E5484,B1354DDD-DCF3-0550-B153-39D9FE6F86FB,50BEC7EF-AD37-8AD0-D604-39D9FE70AEBF,DC17FED5-F182-FFAA-F02B-39D9FE713CB5,31683DB5-3592-E0AC-3B49-39DA65CE0811,FA698FCA-CF41-0098-BD89-39D76B1412FA,B71A994A-7739-9239-3C7C-39D76B14D67D,C21B3872-23AF-DCE6-83C7-39D76B467FF1",

  ####--------------------------------------------------------------####
  #### CSV file parameters                                          ####
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $inDirectory="C:\temp\SqaCusCSVs\csvs",

  [Parameter(Mandatory=$false)]
  [String] $fileExtFilter="*.csv",

  [Parameter(Mandatory=$false)]
  [String] $outDirectory="C:\temp\SqaCusCSVs\csvs\Replaced",

  [Parameter(Mandatory=$false)]
  [String] $outFileSuffix="sqacus",

  ####--------------------------------------------------------------####
  #### New IDs to be used in the survey data CSV files              ####
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $newAnalyzerId="43A34021-8814-8A01-9183-39D4B4DE03EB",

  [Parameter(Mandatory=$false)]
  [String] $newSurveyorUnitId="EDBACFF7-E103-C14C-9DF8-39CD7B5F2A1A",

  [Parameter(Mandatory=$false)]
  [String] $newReferenceGasBottleId="FA197B16-C53F-FF50-8056-39CEC9EA1F32",

  [Parameter(Mandatory=$false)]
  [String] $newUserId="DE734DDF-363E-49FC-8DBC-39C8C221C572",

  [Parameter(Mandatory=$false)]
  [String] $newLocationId = "DE13ACD0-C158-ECAC-7F48-39D18113D702",

  ####--------------------------------------------------------------####
  #### Survey Tag/File map CSV                                      ####
  ####--------------------------------------------------------------####
  [Parameter(Mandatory=$false)]
  [String] $surveyTagFileMapCSVRelativePath = "SurveyTagFileMap.csv",

  ####-----------------------------------------------------------------------------####
  #### Flag to specify if Epoch times need to be restamped in generated CSV files  ####
  #### NOTE: Currently we support restamping for 1 survey at a time. If restamping ####
  ####       is ENABLED ensure ONLY 1 survey is specified in '$surveyIds' above    ####
  ####-----------------------------------------------------------------------------####
  [Parameter(Mandatory=$false)]
  [switch] $restampCSVs = $false,

  ####-----------------------------------------------------------------------------####
  #### Specifies whether new survey IDs need to be generated in the CSV files      ####
  ####-----------------------------------------------------------------------------####
  [Parameter(Mandatory=$false)]
  [switch] $generateNewGuidsForSurvey = $true
)

. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\FileReadWriteHelper.ps1
. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1
. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\Restamp-EpochTimesInCSV.ps1

# Populate the surveyMap object with SurveyId -> {SurveyTag, SurveyFileSuffix} info
$script:surveyTagFileMap = @{}
$executingScriptFolder = Split-Path -Parent -Path $MyInvocation.MyCommand.Definition
$surveyTagFileMapCSVPath = [System.IO.Path]::Combine($executingScriptFolder, $surveyTagFileMapCSVRelativePath).ToString()
$surveyMapCsvData = Import-Csv "$surveyTagFileMapCSVPath"
$surveyMapCsvData | % {
    $SurveyId = $_.SurveyId
    $SurveyTag = $_.SurveyTag
    $SurveyFileSuffix = $_.SurveyFileSuffix

    $surveyMapObject = New-Object PSObject -Property @{                    SurveyId         = $SurveyId                         SurveyTag        = $SurveyTag                      SurveyFileSuffix = $SurveyFileSuffix                }  

    $script:surveyTagFileMap.Add($SurveyId, $surveyMapObject)
}

# Create out directory if it does NOT exist.
if (-not (Test-Path -Path $outDirectory)) {
    Write-host "Directory does NOT exist - $outDirectory . Creating NEW directory ..."
    New-Item -ItemType Directory -Path $outDirectory
} else {
    #Path already exists. Remove existing files.
    Remove-Item "$outDirectory\*" -recurse
}

if ($restampCSVs) {
    # Create restamp directory if it does NOT exist.
    $restampedDirectory = "$inDirectory\Restamped"
    if (-not (Test-Path -Path $restampedDirectory)) {
        Write-host "Directory does NOT exist - $restampedDirectory . Creating NEW directory ..."
        New-Item -ItemType Directory -Path $restampedDirectory
    } else {
        #Path already exists. Remove existing files.
        Remove-Item "$restampedDirectory\*" -recurse
    }
}

$surveyIds -split "," | % {
    $surveyId = $_
    $newSurveyId = [guid]::NewGuid().toString().toUpper()

    # CONNECTION for original database where the survey was generated.
    $oldConnString = "Server=$oldDatabaseIP;Database=$oldDatabaseName;User Id=$oldDatabaseUser;Password=$oldDatabasePwd;"
    $oldSurveyQuery = "SELECT [Id],[Tag],[StartEpoch] - 0.05 AS [AdjustedStartEpoch],[EndEpoch] + 0.05 AS [AdjustedEndEpoch],[AnalyzerId],[SurveyorUnitId],[ReferenceGasBottleId],[UserId],[LocationId] FROM [dbo].[Survey] WHERE ID = '$surveyId'"
    $objOldSurvey = Get-DatabaseData -connectionString $oldConnString -query $oldSurveyQuery -isSQLServer:$true

    $script:oldSurveyId = ""
    $script:oldAnalyzerId = ""
    $script:oldSurveyorUnitId = ""
    $script:oldReferenceGasBottleId = ""
    $script:oldUserId = ""
    $script:oldLocationId = ""

    #### First replace AnalyzerId, SurveyorUnitId, ReferenceGasBottleId and UserId ####
    $i = 0
    $objOldSurvey | foreach { 
        if ($i -gt 0) {     # first row is length of array. datarows are from index=1
            $obj = $_; 
            $id = $obj.Id
            $tag = $obj.Tag
            $startEpoch = $obj.AdjustedStartEpoch
            $endEpoch = $obj.AdjustedEndEpoch
            $script:oldSurveyId = $obj.Id
            $script:oldAnalyzerId = $obj.AnalyzerId.ToString()
            $script:oldSurveyorUnitId = $obj.SurveyorUnitId.ToString()
            $script:oldReferenceGasBottleId = $obj.ReferenceGasBottleId.ToString()
            $script:oldUserId = $obj.UserId.ToString()
            $script:oldLocationId = $obj.LocationId.ToString();
        }

        $i++
    }

    Write-Host ""
    Write-Host "################################################################################################" 
    Write-Host "#### Processing Survey ID=$surveyId, OldSurveyID=${script:oldSurveyId}, NewSurveyId=$newSurveyId"
    Write-Host "################################################################################################"
    Write-Host ""

    if ($script:oldAnalyzerId -eq "") {
        Write-Host "Did NOT find any surveys for the specified SurveyID. Check SurveyID"
        return
    }

    # Replace AnalyzerId ,SurveyorUnitId, RefGasBottleId, UserId, LocationId
    Write-Host "####--------------------------------------------------------------------------####"
    Write-Host "#### Replacing AnalyzerId ,SurveyorUnitId, RefGasBottleId, UserId, LocationId ####"
    Write-Host "####--------------------------------------------------------------------------####"

    Split-Path -Path "$inDirectory\$fileExtFilter" -Leaf -Resolve | % {
        [string]$fileName = [string]$_
        $fileNameWithoutExt = $fileName.Replace(".csv", "")
        $fileFullPath = "$inDirectory\$fileName"
        $newFileName = $fileName.Replace(".csv", "-$outFileSuffix.csv")
        $newFileFullPath = "$outDirectory\$newFileName"

        # Check if files contain information for the survey under processing.
        $processFile = $false
        $srvMapObject = $script:surveyTagFileMap.get_item($surveyId)
        $srvFileSuffix = $srvMapObject.SurveyFileSuffix
        if (([String]$srvFileSuffix).EndsWith("$")) {
            $srvFileSuffix = $srvFileSuffix.Replace("$", "")
            $processFile = ($fileNameWithoutExt.EndsWith($srvFileSuffix))
        } else {
            $processFile = ($fileNameWithoutExt.Contains($srvFileSuffix))
        }

        if ($processFile) {
            Write-Host "[REPLACE_IDS] -> Processing file - $fileFullPath ..."

            # Generate new IDs for files with [ID] as primary key.
            if ($filename.StartsWith("SurveyCondition-") `
                -or $filename.StartsWith("CaptureEvent-") `
                -or $filename.StartsWith("Note-")) {
                $fileLinesList = New-Object System.Collections.ArrayList
            
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
                        if ($headerName -eq "Id") {
                            $lineText += [guid]::NewGuid().toString().toUpper()
                        } else {
                            $lineText += $line."$headerName"
                        }
                        if ($k -ne $len - 1) {
                            $lineText += ","
                        }
                        $k++ 
                    }            

                    # Replace old values with new values.
                    $lineText = $lineText -replace $script:oldSurveyId, $newSurveyId
                    $lineText = $lineText -replace $script:oldAnalyzerId, $newAnalyzerId
                    $lineText = $lineText -replace $script:oldSurveyorUnitId, $newSurveyorUnitId
                    $lineText = $lineText -replace $script:oldReferenceGasBottleId, $newReferenceGasBottleId
                    $lineText = $lineText -replace $script:oldUserId, $newUserId
                    $lineText = $lineText -replace $script:oldLocationId, $newLocationId

                    # Add replaced line to ArrayList
                    $null = $fileLinesList.Add("$lineText")

                    if ($i % 250 -eq 0) {
                        Write-Host "[REPLACE_IDS] -> File='$fileName', Processing LINE-$i"
                    }

                    $i++
                }

                Write-Host "[REPLACE_IDS] -> Start writing replaced content to FILE for - '$newFileFullPath'"
                Write-FileLines -filePath $newFileFullPath -fileContentLines $fileLinesList
                Write-Host "[REPLACE_IDS] -> DONE writing replaced content to FILE for - '$newFileFullPath'"


            } else {
            
                $fileContent = ""

                # replace IDs with specified IDs.
                Get-Content $fileFullPath | % { 
                    $line = $_.trim()
                    if ($line -ne "") {
                        $fileContent += $line + "`r`n"
                    }
                } 

                # Replace old values with new values.
                $fileContent = $fileContent -replace $script:oldSurveyId, $newSurveyId
                $fileContent = $fileContent -replace $script:oldAnalyzerId, $newAnalyzerId
                $fileContent = $fileContent -replace $script:oldSurveyorUnitId, $newSurveyorUnitId
                $fileContent = $fileContent -replace $script:oldReferenceGasBottleId, $newReferenceGasBottleId
                $fileContent = $fileContent -replace $script:oldUserId, $newUserId
                $fileContent = $fileContent -replace $script:oldLocationId, $newLocationId

                #write content into output file
                $stream = [System.IO.StreamWriter] $newFileFullPath
                $stream.Write($fileContent)
                $stream.close()
            }
        }
    }

    if ($restampCSVs) {
        #### Restamp Epoch times in CSV files. ####
        Write-Host "####--------------------------------------------------------------####"
        Write-Host "#### RESTAMP EPOCH TIMES                                          ####"
        Write-Host "####--------------------------------------------------------------####"

        Restamp-EpochTimes -newDatabaseIP $newDatabaseIP -newDatabaseName $newDatabaseName -newDatabaseUser $newDatabaseUser  `
            -newDatabasePwd $newDatabasePwd -inDirectory $outDirectory -fileExtFilter $fileExtFilter -outDirectory $restampedDirectory
    }
}
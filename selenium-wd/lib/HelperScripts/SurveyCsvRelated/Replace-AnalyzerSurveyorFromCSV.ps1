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
    SELECT A.[Id],A.[SurveyorUnitId],RGB.Id AS ReferenceGasBottleId, U.Id AS UserID, L.Id AS LocationId, U.UserName,A.SerialNumber FROM [dbo].[Analyzer] AS A
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
        -oldDatabaseIP "20.20.130.238"  `
        -oldDatabaseName "SurveyorSQA"  `
        -oldDatabaseUser "awssa"  `
        -oldDatabasePwd "j!RuL1Gd7A"  `
        -newDatabaseIP "20.20.130.210"  `
        -newDatabaseName "SurveyorSQAAuto_blankDB_20170420"  `
        -newDatabaseUser "awssa"  `
        -newDatabasePwd "3Vf763pSg2"  `
        -surveyIds "10E42534-6411-1117-5A52-39DEC3776FDA,0AB9CC3C-C299-D0CB-AF0E-39DEC377AD10,3210F764-F72C-9363-A37E-39DEC385F977,346B99F8-7644-C30C-7A1B-39DEC385FEB2"  `
        -inDirectory "C:\temp\MEQ_FEQ_Surveys"  `
        -fileExtFilter "*.csv"  `
        -outDirectory "C:\temp\MEQ_FEQ_Surveys\Replaced2"  `
        -newAnalyzerId "6479A0F7-37C1-B40C-C53B-39DE6C1E9D69"  `
        -newSurveyorUnitId "36D72DF6-2823-4542-8298-39DE6C296D8E"  `
        -newReferenceGasBottleId "5D74209F-340A-3239-6905-39DE6C2A6A25"  `
        -newUserId "DE734DDF-363E-49FC-8DBC-39C8C223D558"  `
        -newLocationId "50C98274-034A-672A-38B1-39DEC2E650EE"  `
        -surveyTagFileMapCSVRelativePath "SurveyTagFileMap.csv"  `          # make sure surveyIds used above have a mapping in this .csv file.
        -restampCSVs:$true  `
        -generateNewGuidsForSurvey:$true
----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  ####--------------------------------------------------------------####
  #### Old Database -> Database from where the survey is to be READ ####
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $oldDatabaseIP="20.20.180.115",

  [Parameter(Mandatory=$false)]
  [String] $oldDatabaseName="SurveyorP3ENGSQA2",

  [Parameter(Mandatory=$false)]
  [String] $oldDatabaseUser="awssa",

  [Parameter(Mandatory=$false)]
  [String] $oldDatabasePwd="j!RuL1Gd7A",

  ####--------------------------------------------------------------####
  #### New Database -> Database to where the survey is to be PUSHED ####
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $newDatabaseIP="20.20.130.210",

  [Parameter(Mandatory=$false)]
  [String] $newDatabaseName="SurveyorSQAAuto_blankDB_20170330",

  [Parameter(Mandatory=$false)]
  [String] $newDatabaseUser="awssa",

  [Parameter(Mandatory=$false)]
  [String] $newDatabasePwd="3Vf763pSg2",

  ####--------------------------------------------------------------------------------------####
  #### Comma seperated value of Ids of surveys to be PUSHED. Value from Old Database        ####
  ####--------------------------------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $surveyIds = "2B690480-BC87-8C19-4B32-39DBF7ED8628",

  ####--------------------------------------------------------------####
  #### CSV file parameters                                          ####
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $inDirectory="C:\temp\TC624Survey",

  [Parameter(Mandatory=$false)]
  [String] $fileExtFilter="*.csv",

  [Parameter(Mandatory=$false)]
  [String] $outDirectory="C:\temp\TC624Survey\Replaced",

  [Parameter(Mandatory=$false)]
  [String] $outFileSuffix="",

  ####--------------------------------------------------------------####
  #### New IDs to be used in the survey data CSV files              ####
  ####--------------------------------------------------------------####

  [Parameter(Mandatory=$false)]
  [String] $newAnalyzerId="88cfc43e-9005-03e1-1d7a-39dbd3f483de",

  [Parameter(Mandatory=$false)]
  [String] $newSurveyorUnitId="58c51edd-51a4-1266-1159-39dbd3f3366c",

  [Parameter(Mandatory=$false)]
  [String] $newReferenceGasBottleId="49ef9ff8-c480-632d-8202-39dbd3f4f555",

  [Parameter(Mandatory=$false)]
  [String] $newUserId="DE734DDF-363E-49FC-8DBC-39C8C221C562",

  [Parameter(Mandatory=$false)]
  [String] $newLocationId = "00000000-0000-0000-0001-000000000000",

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
  [switch] $restampCSVs = $true,

  ####-----------------------------------------------------------------------------####
  #### Specifies whether new survey IDs need to be generated in the CSV files      ####
  ####-----------------------------------------------------------------------------####
  [Parameter(Mandatory=$false)]
  [switch] $generateNewGuidsForSurvey = $true
)

. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\FileReadWriteHelper.ps1
. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1
. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\SurveyCsvRelated\Restamp-EpochTimesInCSV.ps1

# Populate the surveyMap object with SurveyId -> {SurveyTag, SurveyFileSuffix} info
$script:surveyTagFileMap = @{}
$executingScriptFolder = Split-Path -Parent -Path $MyInvocation.MyCommand.Definition
$surveyTagFileMapCSVPath = [System.IO.Path]::Combine($executingScriptFolder, $surveyTagFileMapCSVRelativePath).ToString()
$surveyMapCsvData = Import-Csv "$surveyTagFileMapCSVPath"
$surveyMapCsvData | % {
    $SurveyId = $_.SurveyId
    $SurveyTag = $_.SurveyTag
    $SurveyFileSuffix = $_.SurveyFileSuffix

    $surveyMapObject = New-Object PSObject -Property @{        SurveyId         = $SurveyId        SurveyTag        = $SurveyTag        SurveyFileSuffix = $SurveyFileSuffix    }

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
                    if ($generateNewGuidsForSurvey) {
                        $lineText = $lineText -replace $script:oldSurveyId, $newSurveyId
                    }
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
                if ($generateNewGuidsForSurvey) {
                    $fileContent = $fileContent -replace $script:oldSurveyId, $newSurveyId
                }
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
}

if ($restampCSVs) {

    # RESTAMP LOGIC: NOTE that we process one survey at a time in the Restamp script.
    #  1. Place files related to a survey in temp folder
    #  2. Restamp survey files
    #  3. Move all restamped files back to an OUT directory

    $TEMP_PATH = [System.IO.Path]::GetTempPath()

    $surveyTempDirectoryMap = @{}

    # Build [csv file suffix -> tempInDirectory] map
    $surveyIds -split "," | % {
        $surveyId = $_
        $srvMapObject = $script:surveyTagFileMap.get_item($surveyId)
        $srvFileSuffix = $srvMapObject.SurveyFileSuffix
        if (([String]$srvFileSuffix).EndsWith("$")) {
            $srvFileSuffix = $srvFileSuffix.Replace("$", "")
        }

        $tempInDirForRestamp = "$TEMP_PATH\$srvFileSuffix"
        $tempOutDirForRestamp = "$tempInDirForRestamp\Restamped"

        $ensureExistDirs = @("$tempInDirForRestamp", "$tempOutDirForRestamp")
        $ensureExistDirs | % {
            $chkDir = $_
            if (-not (Test-Path -Path $chkDir)) {
                Write-host "Directory does NOT exist - $chkDir . Creating NEW directory ..."
                New-Item -ItemType Directory -Path $chkDir
            } else {
                # Path already exists. Remove existing files.
                Remove-Item "$chkDir\*" -recurse
            }
        }

        # store csv file suffix in map.
        if (-not $surveyTempDirectoryMap.ContainsKey($srvFileSuffix)) {
            $surveyTempDirectoryMap.set_item($srvFileSuffix, $tempInDirForRestamp)
        }
    }

    # 1. Move files to temp IN folder.
    Write-Host "####--------------------------------------------------------------------------####"
    Write-Host "#### [RESTAMP-PREP] Copy Survey specific files to temporary folders.          ####"
    Write-Host "####--------------------------------------------------------------------------####"
    Get-ChildItem $outDirectory -filter "*.csv" | % {
        $file = $_
        $fileName = $file.Name
        $fileNameWithoutExt = $fileName.Replace(".csv", "")
        $fileFullName = $file.FullName

        $doneCopy = $false
        $surveyTempDirectoryMap.Keys | % {
            if (-not $doneCopy) {
                $suffix = $_
                $tempInDir = $surveyTempDirectoryMap.get_item($suffix)
                if ($fileNameWithoutExt.EndsWith("$suffix-$outFileSuffix")) {
                    Write-host "[COPYING] -> '$fileFullName' to '$tempInDir'"
                    copy $fileFullName "$tempInDir"
                    $doneCopy = $true
                }
            }
        }
    }

    # 2. Restamp survey files
    Write-Host "####--------------------------------------------------------------------------####"
    Write-Host "#### [RESTAMP-PROCESS] Starting ...                                           ####"
    Write-Host "####--------------------------------------------------------------------------####"
    [int64]$offset = 0
    $surveyTempDirectoryMap.Keys | % {
        $suffix = $_
        $tempInDir = $surveyTempDirectoryMap.get_item($suffix)
        $tempOutDir = "$tempInDir\Restamped"

        Get-ChildItem $tempInDir -filter "*.csv" | % {
            $file = $_
            $fileName = $file.Name
            $fileFullName = $file.FullName

            # incrementally build offset for each survey that is being processed.
            if ($fileName.StartsWith("Survey-")) {
                $csvData = Import-Csv $fileFullName
                $csvData | % {
                    $objSur = $_
                    $startEpoch = $objSur.StartEpoch;
                    $endEpoch = $objSur.EndEpoch;

                    $diff = $endEpoch - $startEpoch + 1200  # some buffer.
                    $offset += $diff
                }
            }
        }

        Write-host "[RESTAMPING] -> newDatabaseIP=$newDatabaseIP, newDatabaseName=$newDatabaseName, newDatabaseUser=$newDatabaseUser, newDatabasePwd=$newDatabasePwd, inDirectory=$tempInDir, fileExtFilter=$fileExtFilter, outDirectory=$tempOutDir, offsetInRestamp=$offset"
        Restamp-EpochTimes -newDatabaseIP $newDatabaseIP -newDatabaseName $newDatabaseName -newDatabaseUser $newDatabaseUser  `
            -newDatabasePwd $newDatabasePwd -inDirectory $tempInDir -fileExtFilter $fileExtFilter -outDirectory $tempOutDir -offsetInRestamp $offset

    }

    # 3. Move all restamped files to an OUT dir
    Write-Host "####--------------------------------------------------------------------------####"
    Write-Host "#### [RESTAMP-OUTPUT] Copying restamped files to output dir                   ####"
    Write-Host "####--------------------------------------------------------------------------####"
    $outRestampDir = "$outDirectory\Restamped"
    if (-not (Test-Path -Path $outRestampDir)) {
        Write-host "Directory does NOT exist - $outRestampDir . Creating NEW directory ..."
        New-Item -ItemType Directory -Path $outRestampDir
    } else {
        # Path already exists. Remove existing files.
        Remove-Item "$outRestampDir\*" -recurse
    }

    $surveyTempDirectoryMap.Keys | % {
        $suffix = $_
        $tempInDir = $surveyTempDirectoryMap.get_item($suffix)
        $tempOutDir = "$tempInDir\Restamped"

        Get-ChildItem $tempInDir -filter "*.csv" | % {
            $file = $_
            $fileFullName = $file.FullName

            Write-host "[COPYING] -> '$fileFullName' to '$outRestampDir'"
            copy $fileFullName $outRestampDir
        }
    }

    # CLEANUP -> Remove temporary folders
    Write-Host "####--------------------------------------------------------------------------####"
    Write-Host "#### [RESTAMP-CLEANUP] Deleting temporary folders ...                         ####"
    Write-Host "####--------------------------------------------------------------------------####"
    $surveyTempDirectoryMap.Keys | % {
        $suffix = $_
        $tempInDir = $surveyTempDirectoryMap.get_item($suffix)
        $tempOutDir = "$tempInDir\Restamped"

        Write-host "[DELETING] -> Files from - '$tempInDir' "
        Remove-Item "$tempInDir\*" -recurse
        Remove-Item "$tempInDir"
    }

    Write-Host "####--------------------------------------------------------------####"
    Write-Host "#### DONE RESTAMPING EPOCH TIMES                                  ####"
    Write-Host "#### Restamped files located at -> $outRestampDir ####"
    Write-Host "####--------------------------------------------------------------####"
}

<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION:
  - Use this script to re-generate SQACUS surveys based on original Picarro surveys.

----------------------------------------------------------------------------------------------------------------------------------#>

param(
    $BaseScriptFolder = "C:\Repositories\surveyor-qa",
    $CustomerName = "sqacus",
    $DatabaseIP="20.20.130.210",
    $DatabaseName="SurveyorSQAAuto_blankDB_20171025",
    $DatabaseUser="awssa",
    $DatabasePwd="3Vf763pSg2",
    $ProcessingFolderPath = "C:\Temp\RegenerateSurveys-SQACUS-20171106",
    $GenerateReportForNotMatchingAnalyzers = $false
)

# load helper scripts
. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"
. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"

$script:SurveyInfoMapPicarro = @{}
$script:SurveyInfoMapForReplace = @{}
$script:ProcessSurveysInputOutputMap = @{}

function New-GuidWithDashes() {
    [guid]::NewGuid().ToString().ToUpper()
}

function Get-OtherCustomerSurvey($filename, $fileDirectory, $customerName) { 
    $otherCustSrv = ""
    if ($filename -match "(.+)\-?(\d+?)\.csv") {
        $fname = $Matches[1]
        $fIdx = ""
        if ($Matches.Count -gt 2) {
            $fIdx = $Matches[2]
        }

        $otherCustFNamePattern1 = "${fname}${customerName}-${fIdx}.csv"
        $otherCustFNamePattern2 = "${fname}${fIdx}-${customerName}.csv"
        if (Test-Path -PathType Leaf "$fileDirectory\$otherCustFNamePattern1") {
            $otherCustSrv = $otherCustFNamePattern1
        } elseif (Test-Path -PathType Leaf "$fileDirectory\$otherCustFNamePattern2") {
            $otherCustSrv = $otherCustFNamePattern2
        }
    }

    $otherCustSrv
}

function Get-HardwareCapabilityTypes($analyzerId) {
    $ahctList = New-Object System.Collections.ArrayList
    
    $connString = "Server=$DatabaseIP;Database=$DatabaseName;User Id=$DatabaseUser;Password=$DatabasePwd;"

    $i1=0
    Write-Host "Fetching HardwareCapabilityTypes for Analyzer: $analyzerId"
    $query = "SELECT [AnalyzerId],[HardwareCapabilityTypeId]  FROM [$DatabaseName].[dbo].[AnalyzerHardwareCapabilityType] WHERE AnalyzerId='$analyzerId'"
    $objAnalyzerHardwareCapabilityType = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
    $objAnalyzerHardwareCapabilityType | foreach {
        if ($i1 -gt 0) {     # first row is length of array. datarows are from index=1
            Write-Host "Table -> AnalyzerHardwareCapabilityType, Analyzer -> $analyzerId - Processing row - $i1"
                    
            $objAne = $_;
            $aneAnalyzerId = $objAne.AnalyzerId;
            [Int64]$aneHardwareCapTypeId = $objAne.HardwareCapabilityTypeId;

            $null = $ahctList.Add($aneHardwareCapTypeId);
        }

        $i1++
    }

    $ahctList
}

# Function to determine if LIST IS EQUAL for lists that have unique elements
function Is-ListEqual([System.Collections.ArrayList] $list1, [System.Collections.ArrayList] $list2) {
    $equal = $false
    $lenSame = $list1.Count -eq $list2.Count
    if ($lenSame) {
        $contentSame = $true
        $list1 | % {
            if ($contentSame) {
                $item1 = $_
                $contentSame = $list2.Contains($item1)
            }
        }
    }

    $equal = $lenSame -and $contentSame
    $equal
}

function List-ToString([System.Collections.ArrayList] $list) {
    $strBuilder = ""
    $list | % {
        $item = $_
        $strBuilder += "$item  "
    }

    $strBuilder
}

# 1.
# Find all Picarro surveys that have a corresponding sqacus survey

Write-Host "-------------------------------------------------------------------------------------------"
Write-Host "### Stage 1 ### - Finding all Picarro surveys that have a corresponding sqacus survey .... "
Write-Host "-------------------------------------------------------------------------------------------" 

$surveyCsvFolder = "$BaseScriptFolder\selenium-wd\data\sql\SurveySeedData"
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
                Write-Host "Found Picarro survey - '$filename' that has a corresponding survey for 'sqacus'"
                $csvData = Import-Csv "$fileFullpath"
                $i = 1
                
                # get surveyId and Tag from Picarro customer survey.
                $csvData | % {
                    if ($i -eq 1) {
                        [String]$PicSrvId = $_.Id
                        [String]$PicTag = $_.Tag
                        [String]$PicAnalyzerId = $_.AnalyzerId
                        [String]$PicSurveyorUnitId = $_.SurveyorUnitId
                        [String]$PicReferenceGasBottleId = $_.ReferenceGasBottleId
                        [String]$PicLocationId = $_.LocationId
                        [String]$PicUserId = $_.UserId
                    }

                    $i++
                }

                # get Surveyor unit, Analyzer, RefGasBottle, etc ids from sqacus customer survey.
                $csvData = Import-Csv "$fileDirPath\$correspondingSqacusSurvey"
                $i = 1
                $csvData | % {
                    if ($i -eq 1) {
                        [String]$SqacusTag = $_.Tag
                        [String]$SqacusAnalyzerId = $_.AnalyzerId
                        [String]$SqacusSurveyorUnitId = $_.SurveyorUnitId
                        [String]$SqacusReferenceGasBottleId = $_.ReferenceGasBottleId
                        [String]$SqacusLocationId = $_.LocationId
                        [String]$SqacusUserId = $_.UserId
                    }
                    $i++
                }
                                # MAP info for Picarro customer.                            $srvInfoObject = New-Object PSObject -Property @{                                AnalyzerId = $PicAnalyzerId.ToUpper()
                    SurveyorUnitId = $PicSurveyorUnitId.ToUpper()
                    ReferenceGasBottleId = $PicReferenceGasBottleId.ToUpper()
                    Tag = $PicTag
                    LocationId = $PicLocationId.ToUpper()
                    UserId = $PicUserId.ToUpper()
                }

                $script:SurveyInfoMapPicarro.set_item($PicSrvId.ToUpper(), $srvInfoObject)
                # MAP info for replace csv generation.                            $srvInfoObject = New-Object PSObject -Property @{                                AnalyzerId = $SqacusAnalyzerId.ToUpper()
                    SurveyorUnitId = $SqacusSurveyorUnitId.ToUpper()
                    ReferenceGasBottleId = $SqacusReferenceGasBottleId.ToUpper()
                    Tag = $SqacusTag
                    LocationId = $SqacusLocationId.ToUpper()
                    UserId = $SqacusUserId.ToUpper()
                    FilenameTag = $fileName.Replace("Survey-", "")
                }

                $script:SurveyInfoMapForReplace.set_item($PicSrvId.ToUpper(), $srvInfoObject)
            }
        }
    }
}

# 2. 
# Move all the survey related files to individual survey specific folders inside Processing folder.

Write-Host "-----------------------------------------------------------------------------------------------"
Write-Host "### Stage 2 ### - Moving survey related files to individual survey folders for processing .... "
Write-Host "-----------------------------------------------------------------------------------------------" 

$script:SurveyInfoMapForReplace.Keys | % {
    $key = $_
    $obj = $script:SurveyInfoMapForReplace.get_item($key)

    $SurveyId = $key

    # create out dir for processing each survey of interest.
    $newFolderName = "$ProcessingFolderPath\$SurveyId"
    $newOutputFolderName = "$ProcessingFolderPath\Output\${SurveyId}"
    
    $AnalyzerId = $obj.AnalyzerId
    $SurveyorUnitId = $obj.SurveyorUnitId
    $ReferenceGasBottleId = $obj.ReferenceGasBottleId
    $Tag = $obj.Tag
    $LocationId = $obj.LocationId
    $UserId = $obj.UserId

    Write-Host "Creating new folder - $newFolderName for processing survey -> [ID=$key, Tag=$Tag]"
    $null = New-item -ItemType Directory "$newFolderName"

    Write-Host "Creating new folder - $newOutputFolderName for OUTPUT from processed survey -> [ID=$key, Tag=$Tag]"
    $null = New-item -ItemType Directory "$newOutputFolderName"

    Write-Host "Copying Picarro surveys to new folder -> $newFolderName ..."

    . "$BaseScriptFolder\selenium-wd\lib\HelperScripts\SurveyCsvRelated\Find-SurveyCSVFiles.ps1" -surveyIDs "$SurveyId" -inDirectory "$surveyCsvFolder" -fileExtFilter "*.csv" -outDirectory "$newFolderName" -generateSurveyNameList:$false

    $script:ProcessSurveysInputOutputMap.set_item($newFolderName, $newOutputFolderName)
}

# 3.
# Ensure SurveyTagFileMap.csv has a mapping for all Surveys we are generating
# Generate new to ensure mappings present.

Write-Host "-----------------------------------------------------------------------------------------------"
Write-Host "### Stage 3 ### - Generating SurveyTagFileMap.csv for use in generate surveys CSVs script .... "
Write-Host "-----------------------------------------------------------------------------------------------" 

$SurveyTagFileMap = "$ProcessingFolderPath\SurveyTagFileMap.csv"
if (Test-path $SurveyTagFileMap) {
    Remove-Item $SurveyTagFileMap -Force
} 

$uuid = New-GuidWithDashes
Write-Host "Generating SurveyTagFileMap.csv file at -> $SurveyTagFileMap"
$OUT_SRVMAP_FILE = New-Item -ItemType File "$ProcessingFolderPath\SurveyTagFileMap.csv"
Add-Content $OUT_SRVMAP_FILE "SurveyId,SurveyTag,SurveyFileSuffix"
$script:SurveyInfoMapForReplace.Keys | % {
    $key = $_
    $SurveyId = $key

    $obj = $script:SurveyInfoMapForReplace.get_item($key)
    $SurveyTag = $obj.Tag
    $FilenameTag = $obj.FilenameTag.Replace(".csv", "")

    Add-Content $OUT_SRVMAP_FILE "$SurveyId,$SurveyTag,$FilenameTag`$"
}

Write-Host "Replacing $BaseScriptFolder\selenium-wd\lib\HelperScripts\SurveyCsvRelated\SurveyTagFileMap-$uuid.csv with $SurveyTagFileMap ..."
Copy-Item $SurveyTagFileMap "$BaseScriptFolder\selenium-wd\lib\HelperScripts\SurveyCsvRelated\SurveyTagFileMap-$uuid.csv"

# 4.
# Build params for the replace Analyzer, Surveyor script.

Write-Host "-----------------------------------------------------------------------------------------------"
Write-Host "### Stage 4 ### - Detect NOT matching Analyzer Hardware capability types .... "
Write-Host "-----------------------------------------------------------------------------------------------" 

if ($GenerateReportForNotMatchingAnalyzers) {
    $OUT_NOTMATCHING_HCT = New-item "C:\temp\NotMatchingHardwareCapTypes.txt" -Force 

    $script:SurveyInfoMapForReplace.Keys | % {
        $key = $_
        $srvId = $key
        $objRpl = $script:SurveyInfoMapForReplace.get_item($srvId)
        $rplAnalyzerId = $objRpl.AnalyzerId
        $rplFilenameTag = $objRpl.FilenameTag

        $objPic = $script:SurveyInfoMapPicarro.get_item($srvId)
        $picAnalyzerId = $objPic.AnalyzerId

        $match = $false
        [System.Collections.ArrayList]$ahctList1 = @(Get-HardwareCapabilityTypes -analyzerId $picAnalyzerId)
        [System.Collections.ArrayList]$ahctList2 = @(Get-HardwareCapabilityTypes -analyzerId $rplAnalyzerId)
        $match = Is-ListEqual -list1 $ahctList1 -list2 $ahctList2

        if (-not $match) {
            Add-Content $OUT_NOTMATCHING_HCT "Survey [FileTag=$rplFilenameTag], AnalyzerHardwareCapabilityType NOT match."
            Add-Content $OUT_NOTMATCHING_HCT "       [Picarro Analyzer Id=$picAnalyzerId], [Sqacus Analyzer Id=$rplAnalyzerId]" 
            Add-Content $OUT_NOTMATCHING_HCT "       [Picarro Analyzer HardWare Capability Types = $(List-ToString -list $ahctList1) ]" 
            Add-Content $OUT_NOTMATCHING_HCT "       [Sqacus  Analyzer HardWare Capability Types = $(List-ToString -list $ahctList2) ]" 
            Add-Content $OUT_NOTMATCHING_HCT " "
        }
    }

    ii $OUT_NOTMATCHING_HCT
}

Write-Host "-----------------------------------------------------------------------------------------------"
Write-Host "### Stage 5 ### - Invoking ./Replace-AnalyzerSurveyorFromCSV.ps1 for each survey .... "
Write-Host "-----------------------------------------------------------------------------------------------" 

$script:ProcessSurveysInputOutputMap.Keys | % {
    $key = $_

    $inDir = $key
    $outDir = $script:ProcessSurveysInputOutputMap.get_item($key)
    $SurveyId = $inDir.replace("$ProcessingFolderPath\", "")

    $obj = $script:SurveyInfoMapForReplace.get_item($SurveyId)
    $AnalyzerId = $obj.AnalyzerId
    $SurveyorUnitId = $obj.SurveyorUnitId
    $ReferenceGasBottleId = $obj.ReferenceGasBottleId
    $Tag = $obj.Tag
    $LocationId = $obj.LocationId
    $UserId = $obj.UserId

    # input params to replace surveyorunit, analyzer script
    $oldDatabaseIP = $DatabaseIP
    $oldDatabaseName = $DatabaseName
    $oldDatabaseUser = $DatabaseUser
    $oldDatabasePwd = $DatabasePwd
    $newDatabaseIP = $DatabaseIP
    $newDatabaseName = $DatabaseName
    $newDatabaseUser = $DatabaseUser
    $newDatabasePwd = $DatabasePwd
    $surveyIds = "$SurveyId"
    $inDirectory = "$inDir"
    $fileExtFilter = "*.csv"
    $outDirectory = "$outDir"
    $outFileSuffix = "sqacus"
    $newAnalyzerId = $AnalyzerId
    $newSurveyorUnitId = $SurveyorUnitId
    $newReferenceGasBottleId = $ReferenceGasBottleId
    $newUserId = $UserId
    $newLocationId = $LocationId
    $surveyTagFileMapCSVRelativePath = "SurveyTagFileMap-$uuid.csv"

    Write-Host "Invoking Replace-AnalyzerSurveyorFromCSV.ps1 for survey -> [ID=$key, Tag=$Tag]"
    . "$BaseScriptFolder\selenium-wd\lib\HelperScripts\SurveyCsvRelated\Replace-AnalyzerSurveyorFromCSV.ps1" -oldDatabaseIP "$oldDatabaseIP" -oldDatabaseName "$oldDatabaseName" -oldDatabaseUser "$oldDatabaseUser" -oldDatabasePwd "$oldDatabasePwd" -newDatabaseIP "$newDatabaseIP" -newDatabaseName "$newDatabaseName" -newDatabaseUser "$newDatabaseUser" -newDatabasePwd "$newDatabasePwd" -surveyIds "$surveyIds" -inDirectory "$inDirectory" -fileExtFilter "$fileExtFilter" -outDirectory "$outDirectory" -outFileSuffix "$outFileSuffix" -newAnalyzerId "$newAnalyzerId" -newSurveyorUnitId "$newSurveyorUnitId" -newReferenceGasBottleId "$newReferenceGasBottleId" -newUserId "$newUserId" -newLocationId "$newLocationId" -surveyTagFileMapCSVRelativePath "$surveyTagFileMapCSVRelativePath" -restampCSVs:$true -generateNewGuidsForSurvey:$true
}
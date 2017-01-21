<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION: 
  - This script contains helper functions for reading data from Surveyor DB3
----------------------------------------------------------------------------------------------------------------------------------#>
param
(
  [Parameter(Mandatory=$true)]
  [String] $WorkingFolder
)

$localAppData = $env:LOCALAPPDATA

[string]$SQLiteModulePath = "$WorkingFolder\selenium-wd\lib\SQLite.1.1.0.0\SQLite"
[string]$SQLiteCryptExePath = "$WorkingFolder\selenium-wd\lib\Picarro.SQLiteCrypt\Picarro.Surveyor.Analyzer.SQLiteCrypt.exe"
[string]$SurveyorDB3FilePath = "$localAppData\Picarro\Surveyor\Data\Surveyor.db3"

$TEMP_PATH = [System.IO.Path]::GetTempPath()

$RUN_TESTS = $false

# *************************************************************
#
#                     HELPER METHODS
#
# *************************************************************

# ------------------------------------------------------------
# DESCRIPTION: 
# This method decrypts the Surveyor DB3 and return the 
# decrypted file location. 
# ------------------------------------------------------------
function Decrypt-DB3 {
    param (
        [string]$AnalyzerSerialNumber
    )

    $newFolder = [guid]::NewGuid().ToString().Replace("-", "")
    $db3FileFolder = [System.IO.Path]::Combine($TEMP_PATH, $newFolder)
    $db3FilePath = [System.IO.Path]::Combine($db3FileFolder, "Surveyor.db3")

    $null = New-Item -ItemType Directory $db3FileFolder -Force
    $null = Copy-Item -Path $SurveyorDB3FilePath -Destination $db3FilePath
    $null = Start-Process "$SQLiteCryptExePath" -ArgumentList " -d $db3FilePath $AnalyzerSerialNumber" -Wait

    $db3FilePath
}

# ------------------------------------------------------------
# DESCRIPTION: 
# Returns all records from specified table.
# ------------------------------------------------------------
function Read-TableData {
    param (
        [string]$TableName,
        [string]$AnalyzerSerialNumber
    )

    [string]$db3ToRead = Decrypt-DB3 -AnalyzerSerialNumber $AnalyzerSerialNumber

    Import-Module -Name $SQLiteModulePath -verbose
    
    try {
        $null = Remove-PSDrive -name DB
    } catch { }

    # "Mounting DB3 - $db3ToRead" 
    Mount-SQLite -name DB -dataSource $db3ToRead
    # "Reading $tableName table data from DB3  -> '$db3ToRead'"
    $tblItems = Get-ChildItem "DB:/$TableName"
    $tblItems
}

# ------------------------------------------------------------
# DESCRIPTION: 
# Return all the rows from Survey table in Surveyor.DB3.
# ------------------------------------------------------------
function Get-Surveys {
    param (
        [string]$AnalyzerSerialNumber
    )

    $surveyRows = @()
    $tableRows = Read-TableData -TableName "Survey" -AnalyzerSerialNumber "$AnalyzerSerialNumber"
    $i=0
    $tableRows | % {
        if ($i -gt 0) {
            $row = New-Object PSObject -Property @{    
                ID = $_.ID
                StartEpoch = $_.StartEpoch
                EndEpoch = $_.EndEpoch
                User = $_.User
                Tag = $_.Tag
                StabilityClass = $_.StabilityClass
                SurveyType = $_.SurveyType
                MinimumAmplitude = $_.MinimumAmplitude
            }
            
            $surveyRows += $row
        }
        $i++
    }

    $surveyRows
}

# ------------------------------------------------------------
# DESCRIPTION: 
# Return all the rows from SurveyCondition table in Surveyor.DB3.
# ------------------------------------------------------------
function Get-SurveyConditions {
    param (
        [string]$AnalyzerSerialNumber,
        [string]$SurveyId
    )

    $surveyCondRows = @()
    $tableRows = Read-TableData -TableName "SurveyCondition" -AnalyzerSerialNumber "$AnalyzerSerialNumber"
    $i=0
    $tableRows | % {
        if ($i -gt 0) {
            $row = New-Object PSObject -Property @{    
                ID = $_.ID
                SurveyId = $_.SurveyId
                Name = $_.Name
                Value = $_.Value
            }
            
            if ($SurveyId -ne $NULL -and $SurveyId -ne "") {
                if ($row.SurveyId -eq $SurveyId) {
                    $surveyCondRows += $row
                }
            } else {
                $surveyCondRows += $row
            }
        }
        $i++
    }

    $surveyCondRows
}


# *************************************************************
#
#                     TEST METHODS
#
# *************************************************************

# ------------------------------------------------------------
# TEST METHOD: Test Read-TableData method
# ------------------------------------------------------------
function Test-Read-TableData() {
    "Running TEST - Test-Read-TableData ..."
    $tableRows = Read-TableData -TableName "Survey" -AnalyzerSerialNumber "SimAuto-Analyzer1"
    $i=0
    $tableRows | % {
        if ($i -gt 0) {
            $ID = $_.ID
            $StartEpoch = $_.StartEpoch
            $EndEpoch = $_.EndEpoch
            $User = $_.User
            $Tag = $_.Tag
            $StabilityClass = $_.StabilityClass
            $SurveyType = $_.SurveyType
            $MinimumAmplitude = $_.MinimumAmplitude

            "Survey TABLE Row -> Id=$Id, StartEpoch=$StartEpoch, EndEpoch=$EndEpoch, User=$User, Tag=$Tag, StabilityClass=$StabilityClass, SurveyType=$SurveyType, MinimumAmplitude=$MinimumAmplitude"
        }
        $i++
    }

    $tableRows = Read-TableData -TableName "SurveyCondition" -AnalyzerSerialNumber "SimAuto-Analyzer1"
    $i=0
    $tableRows | % {
        if ($i -gt 0) {
            $ID = $_.ID
            $SurveyId = $_.SurveyId
            $Name = $_.Name
            $Value = $_.Value

            "SurveyCondition TABLE Row -> Id=$Id, SurveyId=$SurveyId, Name=$Name, Value=$Value"
        }
        $i++
    }
}

# ------------------------------------------------------------
# TEST METHOD: Test Get-Surveys method
# ------------------------------------------------------------
function Test-Get-Surveys() {
    "Running TEST - Test-Get-Surveys ..."
    $tableRows = Get-Surveys -AnalyzerSerialNumber "SimAuto-Analyzer1"
    $tableRows | % {
        $ID = $_.ID
        $StartEpoch = $_.StartEpoch
        $EndEpoch = $_.EndEpoch
        $User = $_.User
        $Tag = $_.Tag
        $StabilityClass = $_.StabilityClass
        $SurveyType = $_.SurveyType
        $MinimumAmplitude = $_.MinimumAmplitude

        "Survey TABLE Row -> Id=$Id, StartEpoch=$StartEpoch, EndEpoch=$EndEpoch, User=$User, Tag=$Tag, StabilityClass=$StabilityClass, SurveyType=$SurveyType, MinimumAmplitude=$MinimumAmplitude"
    }
}

# ------------------------------------------------------------
# TEST METHOD: Test Get-SurveyConditions method
# ------------------------------------------------------------
function Test-Get-SurveyConditions() {
    "Running TEST - Test-Get-SurveyConditions ..."
    "Reading ALL SurveyConditions rows ..."
    $tableRows = Get-SurveyConditions -AnalyzerSerialNumber "SimAuto-Analyzer1"
    $tableRows | % {
        $ID = $_.ID
        $SurveyId = $_.SurveyId
        $Name = $_.Name
        $Value = $_.Value

        "SurveyCondition TABLE Row -> Id=$Id, SurveyId=$SurveyId, Name=$Name, Value=$Value"
    }

    $SURVEY_ID = "8add2149-8af6-76fd-dcaf-39dcac5e2371"
    "Reading SurveyCondition rows for surveyId=[$SURVEY_ID] ..."
    $tableRows = Get-SurveyConditions -AnalyzerSerialNumber "SimAuto-Analyzer1" -SurveyId "$SURVEY_ID"
    $tableRows | % {
        $ID = $_.ID
        $SurveyId = $_.SurveyId
        $Name = $_.Name
        $Value = $_.Value

        "SurveyCondition TABLE Row -> Id=$Id, SurveyId=$SurveyId, Name=$Name, Value=$Value"
    }
}

if ($RUN_TESTS) {
    Test-Read-TableData
    Test-Get-Surveys
    Test-Get-SurveyConditions
}
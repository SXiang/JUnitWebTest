<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION:
  - Finds files related to specified survey ids and copies them to specified output folder.

 Sample Run Script:
   .\Find-SurveyCSVFiles.ps1 `
        -surveyIDs "971B7CDB-5922-4206-BD1E-BA8D676E5C94,730B045D-77EA-446D-8D62-133C1B0F5658"  `
        -inDirectory "20.20.130.210"  `
        -fileExtFilter "*.csv"  `
        -outDirectory "C:\temp\SUR-121-Surveys"  `
        -generateSurveyNameList:$true

----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $surveyIDs,                    # comma seperated list of survey ids.

  [Parameter(Mandatory=$true)]
  [String] $inDirectory,                  # directory where survey data files are located.

  [Parameter(Mandatory=$true)]
  [String] $fileExtFilter,                # file extension to search to find data files.

  [Parameter(Mandatory=$true)]
  [String] $outDirectory,                 # $outDirectory="C:\temp\FindCSVs-03"

  [Parameter(Mandatory=$false)]
  [switch] $generateSurveyNameList=$true  # set to 'true' to generate code for survey array constant used in DbSeedExecutor.java
)

. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\FileReadWriteHelper.ps1"
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"

# Create out directory if it does NOT exist.
if (-not (Test-Path -Path $outDirectory)) {
    Write-host "Directory does NOT exist - $outDirectory . Creating NEW directory ..."
    New-Item -ItemType Directory -Path $outDirectory
} else {
    #Path already exists. Remove existing files.
    Write-host "'$outDirectory' directory already exists. Removing files from directory ..."
    Remove-Item "$outDirectory\*"
}

$script:surveyRelatedFileNameList = New-Object System.Collections.ArrayList
$script:fileTagMap = @{}

function Get-SurveyFileName($surveyId) {
    $surFilename = ""

    Split-Path -Path "$inDirectory\Survey-*.csv" -Leaf -Resolve | % {
        if ($surFilename -eq "") {
            [string]$fileName = [string]$_
            $fileFullPath = "$inDirectory\$fileName"
            Get-Content $fileFullPath | %{
                if ($surFilename -eq "") {
                    $line = $_
                    if ($line.toUpper().StartsWith($surveyId.toUpper())) {
                        $surFilename = $fileName
                    }
                }
            }
        }
    }

    $surFilename
}

function Is-SurveyFile([String]$filename) {
    $found = $false
    $script:surveyRelatedFileNameList | %{
        if (-not $found) {
            $surIdPrefix = $_
            if ($filename.StartsWith("$surIdPrefix.csv")) {
                $found = $true
            }
        }
    }

    $found
}

function AddTo-FileTagMapTable($key, $value) 
{	
    if (-not $script:fileTagMap.ContainsKey($key)) {
        $fTagsList = New-Object System.Collections.ArrayList
        $null = $fTagsList.Add($value)
        $script:fileTagMap.Add($key, $fTagsList)
    } else {
        $fTagsList = [System.Collections.ArrayList]$script:fileTagMap.Get_Item($key)
        if (-not $fTagsList.Contains($value)) {
            $null = $fTagsList.Add($value)
        }
        $script:fileTagMap.Set_Item($key, $fTagsList)
    }        
}


# generate list of survey related file name prefixes.
$surveys = @($surveyIDs -split ",")
$surveys | %{
    $surId = $_
    [String]$surveyCsvFilename = [String](Get-SurveyFileName -surveyId $surId)
    $surveyCsvFileSuffix = $surveyCsvFilename.Substring(7, $surveyCsvFilename.Length-7).Replace(".csv", "")
    $COLUMN_HEADINGS.Keys | % {
        $key = $_
        $null = $script:surveyRelatedFileNameList.Add("${key}${surveyCsvFileSuffix}")
        if ($key -eq "Survey-") {
            $cust = "picarro"
            if ($surveyCsvFileSuffix.Contains("sqacus")) {
                $cust = "sqacus"
            }

            AddTo-FileTagMapTable -key $cust -value $surveyCsvFileSuffix
        }

        if (($key -eq "Segment-" ) -or ($key -eq "SurveyResult-")) {
            $null = $script:surveyRelatedFileNameList.Add("${key}Geom-${surveyCsvFileSuffix}")
        }
    }
}

Write-Host "--------------------------------------------------------------"
Write-Host " Finding CSVs for specified surveys.                          "
Write-Host "--------------------------------------------------------------"

Split-Path -Path "$inDirectory\$fileExtFilter" -Leaf -Resolve | % {
    [string]$fileName = [string]$_

    if (Is-SurveyFile -filename $filename) {
        $fileFullPath = "$inDirectory\$fileName"
        $destFileFullPath = "$outDirectory\$fileName"
        
        Write-Host "[FOUND_MATCH] -> Copying file - $fileFullPath to $destFileFullPath ..."
        Copy-Item $fileFullPath $destFileFullPath
    }
}

Write-Host "####--------------------------------------------------------------####"
Write-Host "#### DONE copying files for the specified surveys                 ####"
Write-Host "####--------------------------------------------------------------####"

if ($generateSurveyNameList) {

    Write-Host "--------------------------------------------------------------"
    Write-Host " Generating survey name list.                                 "
    Write-Host "--------------------------------------------------------------"

    $OUTFILE = New-Item "C:\temp\SurveyNamesList3.txt" -Force
    Write-Host "Generating survey name list at -> $OUTFILE ..."

    $script:fileTagMap.Keys | Sort-Object | %{
        [String]$key = [String]$_
        $custUpper = $key.ToUpper()
        add-content $OUTFILE "public static final String[] ${custUpper}_CUSTOMER_SURVEYS = {"
        $fileTagList = [System.Collections.ArrayList]$script:fileTagMap.get_item($key)
        $i = 0
        $line = ""
        $fileTagList | % {
            $fTag = $_
            if ($i -eq 0) {
                $line += """$fTag"""
            } else {
                $line += ", ""$fTag"""
            }

            $i++
        }

        add-content $OUTFILE "$line };"
        add-content $OUTFILE ""
    }

    ii $OUTFILE
}

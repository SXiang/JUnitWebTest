<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION:
  - Use script to ensure survey data csv files have correct headers.

 Sample Run Script:
   .\EnsureSurveyDataCSVsHaveCorrectHeaders `
        -BaseScriptFolder "C:\Repositories\surveyor-qa"  `
        -InDirectory "C:\temp\4095F1281EA64C7F96F4A4A4DDF453D2"  `
        -FileExtFilter "*.csv"  `
        -BackupDirectory "C:\temp\4095F1281EA64C7F96F4A4A4DDF453D2\Backup"
----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $BaseScriptFolder,

  [Parameter(Mandatory=$true)]
  [String] $InDirectory,

  [Parameter(Mandatory=$true)]
  [String] $FileExtFilter,

  [Parameter(Mandatory=$true)]
  [String] $BackupDirectory
)

function Convert-ToASCII([String] $input) {
    $encoding = [system.text.encoding]::GetEncoding("ascii")
    [byte[]]$bytes = $encoding.GetBytes($input)
    $asciiString = $encoding.GetString($bytes)
    $asciiString
}

. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"

# Consider line with only commas as invalid
function Is-LineInvalid($line) {
    $invalid = $false
    if ($line.Trim() -ne "") {
        $updLine = $line.Replace(",", "")
        $invalid = $updLine -eq ""
    }

    $invalid
}

# Add headings to CSV files if they are NOT present.

# Ensure each CSV ends with exactly one newline.
# Replace all NULL values with EMPTY
Split-Path -Path "$InDirectory\$FileExtFilter" -Leaf -Resolve | % {
    [string]$fileName = [string]$_
    $fileFullPath = "$InDirectory\$fileName"
    $newFileFullPath = "$BackupDirectory\$fileName"

    $fileContent = ""

    if ($fileName.StartsWith("SurveyResult-") -and (-not $fileName.StartsWith("SurveyResult-Geom-"))) {
        $fileContent = $COLUMN_HEADINGS.get_item("SurveyResult-")
    } elseif ($fileName.StartsWith("AnemometerRaw-")) {
        $fileContent = $COLUMN_HEADINGS.get_item("AnemometerRaw-")
    } elseif ($fileName.StartsWith("FieldOfView-")) {
        $fileContent = $COLUMN_HEADINGS.get_item("FieldOfView-")
    } elseif ($fileName.StartsWith("GPSRaw-")) {
        $fileContent = $COLUMN_HEADINGS.get_item("GPSRaw-")
    } elseif ($fileName.StartsWith("Measurement-")) {
        $fileContent = $COLUMN_HEADINGS.get_item("Measurement-")
    } elseif ($fileName.StartsWith("Peak-")) {
        $fileContent = $COLUMN_HEADINGS.get_item("Peak-")
    } elseif ($fileName.StartsWith("Segment-") -and (-not $fileName.StartsWith("Segment-Geom-"))) {
        $fileContent = $COLUMN_HEADINGS.get_item("Segment-")
    } elseif ($fileName.StartsWith("Survey-")) {
        $fileContent = $COLUMN_HEADINGS.get_item("Survey-")
    } elseif ($fileName.StartsWith("SurveyCondition-")) {
        $fileContent = $COLUMN_HEADINGS.get_item("SurveyCondition-")
    } elseif ($fileName.StartsWith("CaptureEvent-")) {
        $fileContent = $COLUMN_HEADINGS.get_item("CaptureEvent-")
    }

    # For non-geom files add HEADER + newline
    if (-not $fileName.Contains("-Geom")) {
        $fileContent +=  "`r`n"
    }

    # build the keys table.
    Get-Content $fileFullPath | % { 
        $line = $_.trim()
        if (-not (Is-LineInvalid -line $line)) {
            if ($line -ne "") {
                $fileContent += $line + "`r`n"
            }
        }
    } 

    #create a copy of the original file
    if (Test-Path $newFileFullPath) {
        Write-Host "Removing existing file at - $newFileFullPath"
        remove-item $newFileFullPath
    }

    if ((Test-Path $newFileFullPath) -eq $false) {
        Write-Host "Backing up original file at - $newFileFullPath"
        copy-item $fileFullPath $newFileFullPath
    }

    Write-host "Cleaning - $fileFullPath"

    # No last new-line character for geom files.
    if ($fileName.Contains("-Geom")) {
        if ($fileContent.EndsWith("`r`n")) {
            $fileContent = $fileContent.Substring(0, $fileContent.Length-2)
        }
    }

    #write content into output file
    $stream = [System.IO.StreamWriter] $fileFullPath
    $stream.Write($fileContent)
    $stream.close()
}
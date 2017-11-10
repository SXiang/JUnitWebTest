param
(
  [Parameter(Mandatory=$false)]
  [String] $inDirectory="C:\Repositories\surveyor-qa\selenium-wd\data\sql\SurveySeedData",

  [Parameter(Mandatory=$false)]
  [String] $fileExtFilter="*.csv",

  [Parameter(Mandatory=$false)]
  [String] $outputFolder="C:\temp"
)

$OUTRESULT = New-Item "$outputFolder\verifyHeaderResults.txt" -Force

. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"

function Write-ToOutput($line) {
    Write-Host $line
    Add-Content $OUTRESULT $line
}

function New-GuidNoDashes() {
    [guid]::NewGuid().ToString().Replace("-", "").ToUpper()
}

$failingFilesList = New-Object System.Collections.ArrayList

Write-ToOutput -line "-----------------------------------------------------------------------------"
Write-ToOutput -line "Starting verifications... Failures (if any) will be listed below."
Write-ToOutput -line "-----------------------------------------------------------------------------"
Write-ToOutput -line ""

$foundFailure = $false
Split-Path -Path "$inDirectory\$fileExtFilter" -Leaf -Resolve | % {
    [string]$fileName = [string]$_
    $fileFullPath = "$inDirectory\$fileName"

    $expectedFileHeader = ""

    if ($fileName.StartsWith("SurveyResult-") -and (-not $fileName.StartsWith("SurveyResult-Geom-"))) {
        $expectedFileHeader = $COLUMN_HEADINGS.get_item("SurveyResult-")
    } elseif ($fileName.StartsWith("AnemometerRaw-")) {
        $expectedFileHeader = $COLUMN_HEADINGS.get_item("AnemometerRaw-")
    } elseif ($fileName.StartsWith("FieldOfView-")) {
        $expectedFileHeader = $COLUMN_HEADINGS.get_item("FieldOfView-")
    } elseif ($fileName.StartsWith("GPSRaw-")) {
        $expectedFileHeader = $COLUMN_HEADINGS.get_item("GPSRaw-")
    } elseif ($fileName.StartsWith("Measurement-")) {
        $expectedFileHeader = $COLUMN_HEADINGS.get_item("Measurement-")
    } elseif ($fileName.StartsWith("Peak-")) {
        $expectedFileHeader = $COLUMN_HEADINGS.get_item("Peak-")
    } elseif ($fileName.StartsWith("Segment-") -and (-not $fileName.StartsWith("Segment-Geom-"))) {
        $expectedFileHeader = $COLUMN_HEADINGS.get_item("Segment-")
    } elseif ($fileName.StartsWith("Survey-")) {
        $expectedFileHeader = $COLUMN_HEADINGS.get_item("Survey-")
    } elseif ($fileName.StartsWith("SurveyCondition-")) {
        $expectedFileHeader = $COLUMN_HEADINGS.get_item("SurveyCondition-")
    } elseif ($fileName.StartsWith("CaptureEvent-")) {
        $expectedFileHeader = $COLUMN_HEADINGS.get_item("CaptureEvent-")
    }

    if ($expectedFileHeader -ne "") {
        $actualFileHeader = (gc $fileFullPath | select -First 1)

        if ($actualFileHeader -ne $expectedFileHeader) {
            Write-ToOutput -line "[$fileName] : Headers do NOT Match. Actual=[$actualFileHeader], Expected=[$expectedFileHeader]"
            $null = $failingFilesList.Add($fileName)
            $foundFailure = $true
        }
    }
}

if (-not $foundFailure) {
    Write-ToOutput -line "All CSVs passed test."
} else {
    $uuid = New-GuidNoDashes
    $copyFolderPath = "$outputFolder\$uuid"
    Write-Host "Creating new directory to store failing files - '$copyFolderPath' "
    New-Item -ItemType Directory $copyFolderPath
    $failingFilesList | %{
        $fName = $_
        $fFullPath = "$inDirectory\$fName"
        $copyFileFullPath = "$copyFolderPath\$fName"
        Write-Host "Copying $fFullPath -> $copyFileFullPath ..."
        Copy-Item $fFullPath $copyFileFullPath
    }
}

ii $OUTRESULT
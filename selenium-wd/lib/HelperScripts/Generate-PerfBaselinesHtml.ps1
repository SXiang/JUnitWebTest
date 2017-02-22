<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - Use this script to generate static HTML for report job perf baselines (based on what is set in source code).

 Sample Run Script: 
   .\Generate-PerfBaselinesHtml.ps1  `
        -perfBaselinesCSVFolder "C:\Repositories\surveyor-qa\selenium-wd\data\perf-metric\report-job-metrics"  `
		-perfTestDataXlsx "C:\Repositories\surveyor-qa\selenium-wd\data\TestCaseData.xlsx"  `
        -outputFolder "C:\temp"

----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $perfBaselinesCSVFolder,

  [Parameter(Mandatory=$true)]
  [String] $perfTestDataXlsx,

  [Parameter(Mandatory=$true)]
  [String] $outputFolder
)

$script:JobTypeMap = @{
    "00000000-0000-0000-0001-000000000000" = "Map"
    "00000000-0000-0000-0002-000000000000" = "SSRS"
    "00000000-0000-0000-0003-000000000000" = "DataGeneration"
    "00000000-0000-0000-0004-000000000000" = "EQMap"
    "00000000-0000-0000-0005-000000000000" = "EQSSRS"
    "00000000-0000-0000-0006-000000000000" = "EQDataGeneration"
    "00000000-0000-0000-0007-000000000000" = "ShapeFile"
    "00000000-0000-0000-0008-000000000000" = "ReportMeta"
    "00000000-0000-0000-0009-000000000000" = "PercentCoverageForecast"
    "00000000-0000-0000-0010-000000000000" = "Zip"
    "00000000-0000-0000-0011-000000000000" = "AssetBoxHighlight"
    "00000000-0000-0000-0012-000000000000" = "FacilityEQDataGeneration"
    "00000000-0000-0000-0013-000000000000" = "FacilityEQMap"
    "00000000-0000-0000-0014-000000000000" = "FacilityEQConcentrationChart"
    "00000000-0000-0000-0015-000000000000" = "FacilityEQReportMeta"
    "00000000-0000-0000-0016-000000000000" = "FTP"
}

$OutHtmlFilePath = "C:\temp\out.html"
$OUTFILE = New-Item -ItemType File -Path $OutHtmlFilePath -Force

# stores processing times from perf test CSVs.
$script:rptJobProcessingTimesTable = @{}

# stores the test data read from TestCaseData.xlsx
$script:customerTable = @{}
$script:cmpRptTable = @{}
$script:rptViewsTable = @{}
$script:rptOptviewLayersTable = @{}
$script:rptOptTabPDFContentTable = @{}

# stores the final output to be printed in result HTML.
$script:testCaseParameters = @{}

# load excel read/write library
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\Excel-functions.ps1"

function AddTo-RptJobProcessingTimesTable($tcID, $tcRptJob) 
{
    if (-not $script:rptJobProcessingTimesTable.ContainsKey($tcID)) {
        $tcRptJobList = New-Object System.Collections.ArrayList
        $null = $tcRptJobList.Add($tcRptJob)
        $script:rptJobProcessingTimesTable.Add($tcID, $tcRptJobList)
    } else {
        $tcRptJobList = [System.Collections.ArrayList]$script:rptJobProcessingTimesTable.Get_Item($tcID)
        if (-not $tcRptJobList.Contains($tcRptJob)) {
            $null = $tcRptJobList.Add($tcRptJob)
        }
        $script:rptJobProcessingTimesTable.Set_Item($tcID, $tcRptJobList)
    }        
}

function Fill-RptJobProcessingTimesTable($folderPath) {
    $searchFilter = "*.csv"
    Get-ChildItem -Path $folderPath -Filter $searchFilter -Recurse | % { 
        $file = $_
        $filename = $file.Name
        $fileFullPath = $file.FullName
        $directory = $file.Directory.Name

        # Read baselines from CSV files.
        if ($filename.StartsWith("TC")) {
            Write-Host "Reading - $fileFullPath ..."

            $tcId = $filename.Replace(".csv", "")
            $csvData = Import-Csv $fileFullPath
            $csvData | % {
                $tcRptJobObject = New-Object PSObject -Property @{                                ReportJobTypeId       = $_.ReportJobTypeId                                 ReportJobType         = $script:JobTypeMap.get_item($_.ReportJobTypeId)                    StartTime             = $_.StartTime                                EndTime               = $_.EndTime                                ProcessingTimeInMs    = $_.ProcessingTimeInMs                           }  

                AddTo-RptJobProcessingTimesTable -tcID $tcId -tcRptJob $tcRptJobObject
            }
        }
    }
}

function Fill-ExcelTestDataTables() {
    $sheetName = "Customers"
    Write-Host "Reading - '$perfTestDataXlsx' -> SheetName = $sheetName"
    $spreadsheetData_Customers = Import-Excel -FilePath $perfTestDataXlsx -SheetName $sheetName
    $lineNum = 0
    $spreadsheetData_Customers | % {
        $lineNum++

        $rowID = $_."RowID"
        $name = $_."Name"
        $enabled = $_."Enabled"
        $eULA = $_."EULA"
        $licensedFeaturesRowIDs = $_."Licensed Features RowIDs"

        $object = New-Object PSObject -Property @{
            RowID = $rowID
            Name = $name
            Enabled = $enabled
            EULA = $eULA
            LicensedFeaturesRowIDs = $licensedFeaturesRowIDs
        }

        $script:customerTable.set_item($rowID, $object)

        Write-Host "Read row-$lineNum"
    }

    $sheetName = "Compliance Report Test Data"
    Write-Host "Reading - '$perfTestDataXlsx' -> SheetName = $sheetName"
    $spreadsheetData_CmptRpts = Import-Excel -FilePath $perfTestDataXlsx -SheetName $sheetName
    $lineNum = 0
    $spreadsheetData_CmptRpts | % {
        $lineNum++
	
        $rowID = $_."RowID"
        $tCID = $_."TCID"
        $title = $_."Title"
        $customerRowID = $_."Customer Row ID"
        $timezone = $_."Timezone"
        $exclusionRadius = $_."Exclusion Radius"
        $reportMode = $_."Report Mode"
        $minAmplitude = $_."Min Amplitude"
        $customBoundaryNELat = $_."Custom Boundary - NELat"
        $customBoundaryNELong = $_."Custom Boundary - NELong"
        $customBoundarySWLat = $_."Custom Boundary - SWLat"
        $customBoundarySWLong = $_."Custom Boundary - SWLong"
        $customerBoundaryType = $_."Customer Boundary - Type"
        $customerBoundaryName = $_."Customer Boundary - Name"
        $opacityFOV = $_."Opacity - FOV"
        $opacityLISA = $_."Opacity - LISA"
        $pDFImageOutputWidth = $_."PDF Image Output - Width"
        $pDFImageOutputHeight = $_."PDF Image Output - Height"
        $reportViewRowIDs = $_."Report View RowIDs"
        $reportOptViewLayerRowID = $_."Report Opt View Layer RowID"
        $reportOptTabularPDFContentRowID = $_."Report Opt Tabular PDF Content RowID"
        $reportSurveyRowIDs = $_."Report Survey RowIDs"
        $searchAreaPreference = $_."Search Area Preference"

        $object = New-Object PSObject -Property @{
            RowID = $rowID
            TCID = $tCID
            Title = $title
            CustomerRowID = $customerRowID
            Timezone = $timezone
            ExclusionRadius = $exclusionRadius
            ReportMode = $reportMode
            MinAmplitude = $minAmplitude
            CustomBoundaryNELat = $customBoundaryNELat
            CustomBoundaryNELong = $customBoundaryNELong
            CustomBoundarySWLat = $customBoundarySWLat
            CustomBoundarySWLong = $customBoundarySWLong
            CustomerBoundaryType = $customerBoundaryType
            CustomerBoundaryName = $customerBoundaryName
            OpacityFOV = $opacityFOV
            OpacityLISA = $opacityLISA
            PDFImageOutputWidth = $pDFImageOutputWidth
            PDFImageOutputHeight = $pDFImageOutputHeight
            ReportViewRowIDs = $reportViewRowIDs
            ReportOptViewLayerRowID = $reportOptViewLayerRowID
            ReportOptTabularPDFContentRowID = $reportOptTabularPDFContentRowID
            ReportSurveyRowIDs = $reportSurveyRowIDs
            SearchAreaPreference = $searchAreaPreference
        }

        if ($tCID -ne $null -and $tCID.Length -gt 2) {
            if (-not $script:cmpRptTable.ContainsKey($tCID)) {
                $script:cmpRptTable.set_item($tCID, $object)
            }
        }

        Write-Host "Read row-$lineNum"
    }

    $sheetName = "Report Views Data"
    Write-Host "Reading - '$perfTestDataXlsx' -> SheetName = $sheetName"
    $spreadsheetData_RptViews = Import-Excel -FilePath $perfTestDataXlsx -SheetName $sheetName
    $lineNum = 0
    $spreadsheetData_RptViews | % {
        $lineNum++
	
        $rowID = $_."RowID"
        $name = $_."Name"
        $lISAs = $_."LISAs"
        $fOV = $_."FOV"
        $breadcrumbs = $_."Breadcrumbs"
        $indications = $_."Indications"
        $fieldNotes = $_."Field Notes"
        $gaps = $_."Gaps"
        $assets = $_."Assets"
        $boundaries = $_."Boundaries"
        $isotopicCapture = $_."Isotopic Capture"
        $annotation = $_."Annotation"
        $baseMap = $_."Base Map"
        $highlightLISAAssets = $_."Highlight LISA Assets"
        $highlightGAPAssets = $_."Highlight GAP Assets"
        $assetBoxNumber = $_."Asset Box Number"
        $nOTES = $_."NOTES"

        $object = New-Object PSObject -Property @{
            RowID = $rowID
            Name = $name
            LISAs = $lISAs
            FOV = $fOV
            Breadcrumbs = $breadcrumbs
            Indications = $indications
            FieldNotes = $fieldNotes
            Gaps = $gaps
            Assets = $assets
            Boundaries = $boundaries
            IsotopicCapture = $isotopicCapture
            Annotation = $annotation
            BaseMap = $baseMap
            HighlightLISAAssets = $highlightLISAAssets
            HighlightGAPAssets = $highlightGAPAssets
            AssetBoxNumber = $assetBoxNumber
            NOTES = $nOTES
        }

        $script:rptViewsTable.set_item($rowID, $object)

        Write-Host "Read row-$lineNum"
    }

    $sheetName = "Report Opt View Layers"
    Write-Host "Reading - '$perfTestDataXlsx' -> SheetName = $sheetName"
    $spreadsheetData_RptViewLayers = Import-Excel -FilePath $perfTestDataXlsx -SheetName $sheetName
    $lineNum = 0
    $spreadsheetData_RptViewLayers | % {
        $lineNum++

        $rowID = $_."RowID"
        $assetRowIDs = $_."Asset Row IDs"
        $boundariesRowIDs = $_."Boundaries Row IDs"
        $notes = $_."Notes"

        $object = New-Object PSObject -Property @{
            RowID = $rowID
            AssetRowIDs = $assetRowIDs
            BoundariesRowIDs = $boundariesRowIDs
            Notes = $notes
        }

        $script:rptOptviewLayersTable.set_item($rowID, $object)

        Write-Host "Read row-$lineNum"
    }

    $sheetName = "Report Opt Tabular PDF Content"
    Write-Host "Reading - '$perfTestDataXlsx' -> SheetName = $sheetName"
    $spreadsheetData_RptTabPDFContent = Import-Excel -FilePath $perfTestDataXlsx -SheetName $sheetName
    $lineNum = 0
    $spreadsheetData_RptTabPDFContent | % {
        $lineNum++

        $rowID = $_."RowID"
        $indicationTable = $_."Indication Table"
        $isotopicAnalysis = $_."Isotopic Analysis"
        $gapTable = $_."Gap Table"
        $percentCoverageAssets = $_."Percent Coverage Assets"
        $percentCoverageReportArea = $_."Percent Coverage Report Area"
        $percentCoverageForecast = $_."Percent Coverage Forecast"
        $nOTES = $_."NOTES"

        $object = New-Object PSObject -Property @{
            RowID = $rowID
            IndicationTable = $indicationTable
            IsotopicAnalysis = $isotopicAnalysis
            GapTable = $gapTable
            PercentCoverageAssets = $percentCoverageAssets
            PercentCoverageReportArea = $percentCoverageReportArea
            PercentCoverageForecast = $percentCoverageForecast
            NOTES = $nOTES
        }

        $script:rptOptTabPDFContentTable.set_item($rowID, $object)

        Write-Host "Read row-$lineNum"
    }
}

########## 1. Generate HTML for Baselines
Fill-RptJobProcessingTimesTable -folderPath $perfBaselinesCSVFolder

# build list of report job types
$foundJobTypesList = New-Object System.Collections.ArrayList
$script:rptJobProcessingTimesTable.Keys | Sort-Object | % {
    $key = $_
    $objList = [System.Collections.ArrayList]$script:rptJobProcessingTimesTable.get_item($key)
    $objList | %{
        $obj = $_
        $reportJobType = $obj.ReportJobType

        if (-not $foundJobTypesList.Contains($reportJobType)) {
            $null = $foundJobTypesList.Add($reportJobType)
        }
    }
}

add-content $OUTFILE "<table>";
add-content $OUTFILE "        <tr>";
add-content $OUTFILE "            <td style=""text-align:center"" colspan=""9"">";
add-content $OUTFILE "                <p>&nbsp;</p>";
add-content $OUTFILE "                <p><h4><strong>Baselines for Test Cases</strong></h4> (in milliseconds)</p>";
add-content $OUTFILE "            </td>";
add-content $OUTFILE "        </tr>";
add-content $OUTFILE "        <tr>";
add-content $OUTFILE "            <td><h6>Test Case</h6></td>";
$foundJobTypesList | %{
    $item = $_
    add-content $OUTFILE "            <td><h6>$item</h6></td>";
}
add-content $OUTFILE "        </tr>";

$script:rptJobProcessingTimesTable.Keys | Sort-Object | % {
    $key = $_

    $objList = [System.Collections.ArrayList]$script:rptJobProcessingTimesTable.get_item($key)

    add-content $OUTFILE "        <tr>";
    add-content $OUTFILE "            <td>$key</td>";
    
    $foundJobTypesList | %{
        $jbType = $_

        $found = $false
        [int]$jbProcessingTime = 0
        $objList | %{
            $obj = $_
            $reportJobType = $obj.ReportJobType
            $processingTimeInMs = $obj.ProcessingTimeInMs
         
            if ($jbType -eq $reportJobType) {            
                $found = $true
                $jbProcessingTime = [int]$processingTimeInMs
            } 
        }

        $formattedProcessingTm = '{0:N0}' -f $jbProcessingTime

        if ($found) {
            add-content $OUTFILE "            <td>$formattedProcessingTm</td>";
        } else {
            add-content $OUTFILE "            <td>&nbsp;</td>";
        }
    }

    add-content $OUTFILE "        </tr>";
}

add-content $OUTFILE "</table>";


########## 2. Generate HTML for Perf Test case data
Fill-ExcelTestDataTables

add-content $OUTFILE "    <table>";
add-content $OUTFILE "        <tr>";
add-content $OUTFILE "            <td style=""text-align:center"" colspan=""14"">";
add-content $OUTFILE "                <p>&nbsp;</p>";
add-content $OUTFILE "                <p><h4><strong>Parameters for Test Cases</strong></h4></p>";
add-content $OUTFILE "            </td>";
add-content $OUTFILE "        </tr>";
add-content $OUTFILE "        <tr>";
add-content $OUTFILE "            <td style=""width: 90px""><strong>Test Case</strong></td>";
add-content $OUTFILE "            <td style=""width: 100px""><strong>Customer</strong></td>";
add-content $OUTFILE "            <td><strong>Exclusion Radius</strong></td>";
add-content $OUTFILE "            <td style=""width: 100px""><strong>Report Mode</strong></td>";
add-content $OUTFILE "            <td style=""width: 200px""><strong>Report Area</strong></td>";
add-content $OUTFILE "            <td style=""width: 100px""><strong>Opacity-FOV</strong></td>";
add-content $OUTFILE "            <td style=""width: 100px""><strong>Opacity-LISA</strong></td>";
add-content $OUTFILE "            <td style=""width: 90px""><strong>PDF Width</strong></td>";
add-content $OUTFILE "            <td style=""width: 90px""><strong>PDF Height</strong></td>";
add-content $OUTFILE "            <td style=""width: 80px""><strong># Views</strong></td>";
add-content $OUTFILE "            <td style=""width: 80px""><strong># Surveys</strong></td>";
add-content $OUTFILE "            <td><strong>Opt View Layer</strong></td>";
add-content $OUTFILE "            <td style=""width: 260px""><strong>Opt Tabular Content</strong></td>";
add-content $OUTFILE "            <td><strong>Search Area Preference</strong></td>";
add-content $OUTFILE "        </tr>";

$script:rptJobProcessingTimesTable.Keys | Sort-Object | %{
    $tcId = $_
    $obj = $script:cmpRptTable.get_item($tcId)

    $rowID = $obj.RowID
    $tCID = $obj.TCID
    $title = $obj.Title
    $customerRowID = $obj.CustomerRowID
    $timezone = $obj.Timezone
    $exclusionRadius = $obj.ExclusionRadius
    $reportMode = $obj.ReportMode
    $minAmplitude = $obj.MinAmplitude
    $customBoundaryNELat = $obj.CustomBoundaryNELat
    $customBoundaryNELong = $obj.CustomBoundaryNELong
    $customBoundarySWLat = $obj.CustomBoundarySWLat
    $customBoundarySWLong = $obj.CustomBoundarySWLong
    $customerBoundaryType = $obj.CustomerBoundaryType
    $customerBoundaryName = $obj.CustomerBoundaryName
    $opacityFOV = $obj.OpacityFOV
    $opacityLISA = $obj.OpacityLISA
    $pDFImageOutputWidth = $obj.PDFImageOutputWidth
    $pDFImageOutputHeight = $obj.PDFImageOutputHeight
    [string]$reportViewRowIDs = [string]$obj.ReportViewRowIDs
    [string]$reportOptViewLayerRowID = [string]$obj.ReportOptViewLayerRowID
    [string]$reportOptTabularPDFContentRowID = [string]$obj.ReportOptTabularPDFContentRowID
    [string]$reportSurveyRowIDs = [string]$obj.ReportSurveyRowIDs
    $searchAreaPreference = $obj.SearchAreaPreference

    if ($opacityFOV -eq "") {
        $opacityFOV = "0.5"
    }
    
    if ($opacityLISA -eq "") {
        $opacityLISA = "0.5"
    }

    $customerName = ""
    if ($customerRowID -ne "") {
        $objCust = $script:customerTable.get_item($customerRowID)
        $customerName = $objCust.Name
    }

    $reportArea = ""
    if ($customBoundaryNELat -ne "" -and $customBoundaryNELong -ne "") {
        $reportArea += "Custom Boundary:<br/>"
        $reportArea += "&nbsp;&nbsp;NELat:$customBoundaryNELat<br/>"
        $reportArea += "&nbsp;&nbsp;NELong: $customBoundaryNELong<br/>"
        $reportArea += "&nbsp;&nbsp;SWLat:$customBoundarySWLat<br/>"
        $reportArea += "&nbsp;&nbsp;SWLong: $customBoundarySWLong<br/>"
    } else {
        $reportArea += "Customer Boundary:<br/>"
        $reportArea += "&nbsp;&nbsp;Type:$customerBoundaryType<br/>"
        $reportArea += "&nbsp;&nbsp;Name:$customerBoundaryName<br/>"
    }

    $numView = 0
    if ($reportViewRowIDs -ne "") {
        $arrViews = $reportViewRowIDs.Split(",")
        $numView = $arrViews.Length
    }

    $numSurveys = 0
    if ($reportSurveyRowIDs -ne "") {
        $arrSurveys = $reportSurveyRowIDs.Split(",")
        $numSurveys = $arrSurveys.Length
    }

    $viewLayers = ""
    if ($reportOptViewLayerRowID -ne "") {
        $objVwLayers = $script:rptOptviewLayersTable.get_item($reportOptViewLayerRowID)
        $viewLayers = $objVwLayers.Notes
    }

    $tabContent = ""
    if ($reportOptTabularPDFContentRowID -ne "") {
        $objTabContent = $script:rptOptTabPDFContentTable.get_item($reportOptTabularPDFContentRowID)
        if ($objTabContent.IndicationTable -eq "TRUE") {
            $tabContent += "&nbsp;&nbsp;IndicationTable=TRUE<br/>"
        }
        if ($objTabContent.IsotopicAnalysis -eq "TRUE") {
            $tabContent += "&nbsp;&nbsp;IsotopicAnalysis=TRUE<br/>"
        }
        if ($objTabContent.GapTable -eq "TRUE") {
            $tabContent += "&nbsp;&nbsp;GapTable=TRUE<br/>"
        }
        if ($objTabContent.PercentCoverageAssets -eq "TRUE") {
            $tabContent += "&nbsp;&nbsp;PercentCoverageAssets=TRUE<br/>"
        }
        if ($objTabContent.PercentCoverageReportArea -eq "TRUE") {
            $tabContent += "&nbsp;&nbsp;PercentCoverageReportArea=TRUE<br/>"
        }
        if ($objTabContent.PercentCoverageForecast -eq "TRUE") {
            $tabContent += "&nbsp;&nbsp;PercentCoverageForecast=TRUE<br/>"
        }
    }

    add-content $OUTFILE "        <tr>";
    add-content $OUTFILE "            <td>$tcId</td>";
    add-content $OUTFILE "            <td>$customerName</td>";
    add-content $OUTFILE "            <td>$exclusionRadius</td>";
    add-content $OUTFILE "            <td>$reportMode</td>";
    add-content $OUTFILE "            <td>$reportArea</td>";
    add-content $OUTFILE "            <td>$opacityFOV</td>";
    add-content $OUTFILE "            <td>$opacityLISA</td>";
    add-content $OUTFILE "            <td>$pDFImageOutputWidth</td>";
    add-content $OUTFILE "            <td>$pDFImageOutputHeight</td>";
    add-content $OUTFILE "            <td>$numView</td>";
    add-content $OUTFILE "            <td>$numSurveys</td>";
    add-content $OUTFILE "            <td>$viewLayers</td>";
    add-content $OUTFILE "            <td>$tabContent</td>";
    add-content $OUTFILE "            <td>$searchAreaPreference</td>";
    add-content $OUTFILE "        </tr>";
}

add-content $OUTFILE "    </table>";
ii $OUTFILE
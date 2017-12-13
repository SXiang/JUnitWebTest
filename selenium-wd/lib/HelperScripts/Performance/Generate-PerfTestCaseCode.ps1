<#
SAMPLE USAGE:

.\Generate-PerfTestCaseCode.ps1 `
    -BaseScriptFolder "C:\Repositories\surveyor-qa"   `
    -DatabaseIP "30.30.124.110"   `
    -DatabaseName "SurveyorScale"   `
    -DatabaseUser "spulikkal"   `
    -DatabasePwd "<password>"   `
    -ReportIDCsvFile "C:\temp\reportsForPerfTest-Filtered.csv"   `
    -OutputFolder "C:\temp"
#>

param(
  [Parameter(Mandatory=$true)]
  [String] $BaseScriptFolder,

  [Parameter(Mandatory=$true)]
  [String] $DatabaseIP,

  [Parameter(Mandatory=$true)]
  [String] $DatabaseName,

  [Parameter(Mandatory=$true)]
  [String] $DatabaseUser,

  [Parameter(Mandatory=$true)]
  [String] $DatabasePwd,

  [Parameter(Mandatory=$true)]
  [String] $ReportIDCsvFile,

  [Parameter(Mandatory=$true)]
  [String] $OutputFolder
)

#=============================================================================================================
### NOTE: These are RowIDs from TestCaseData.xlsx. Verify and update this at the time of running this script.
#=============================================================================================================

$StartingReportRowID = 313
$StartingSurveyRowID = 127
$StartingViewRowID = 93
$StartingLayerRowID = 13
$StartingAssetLayerRowID = 42
$StartingBoundaryLayerRowID = 10
$StartingOptTabRowID = 18

### ----------------------------------------------------------------------------------------------------------

. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"
. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"

$ConnString="Server=$DatabaseIP;Database=$DatabaseName;User Id=$DatabaseUser;Password=$DatabasePwd;"

$OUTFILE_RPT = New-Item "$OutputFolder\Perf_ComplianceReportXLSXData.txt" -ItemType File -Force
$OUTFILE_CBT = New-Item "$OutputFolder\Perf_CBTXLSXData.txt" -ItemType File -Force
$OUTFILE_CMT = New-Item "$OutputFolder\Perf_CMTXLSXData.txt" -ItemType File -Force
$OUTFILE_LAYERS = New-Item "$OutputFolder\Perf_LayersXLSXData.txt" -ItemType File -Force
$OUTFILE_VIEWS = New-Item "$OutputFolder\Perf_ViewsXLSXData.txt" -ItemType File -Force
$OUTFILE_OPTTAB = New-Item "$OutputFolder\Perf_OptTabXLSXData.txt" -ItemType File -Force

# FIX this. Only created survey rows currently.
$OUTFILE_SURVEYS = New-Item "$OutputFolder\Perf_SurveysXLSXData.txt" -ItemType File -Force

$reportIdList = New-Object System.Collections.ArrayList

$boundaryLayerMap = @{}
$materialLayerMap = @{}
$layerMap = @{}
$surveyMap = @{}
$viewMap = @{}
$optTabMap = @{}

#--------------------------------------#
# Helper functions
#--------------------------------------#

function Populate-ReportIDList() {
    $rptIDsArray =$ReportIDs.Split(",")
    $rptIDsArray | %{
        $rptId = $_
        $rptId = $rptId.Trim().Replace("'", "")
        $null = $reportIdList.Add($rptId)
    }
}


function Get-RowIDsFromMap($map, $reportID) {
    $retRowIds = ""
    if ($map.ContainsKey($reportID)) {
        $rptDataList = $map.get_item($reportID)
        $i = 0
        $rptDataList | Sort-Object -Property "RowID" | %{
            $rowId = $_.RowID
            if ($i -eq 0) {
                $retRowIds += "$rowId"
            } else {
                $retRowIds += ",$rowId"
            }

            $i++
        }
    }

    $retRowIds
}

function Get-ReportViewRowIDs($reportID) {
    $retRowIds = Get-RowIDsFromMap -map $viewMap -reportID $reportID
    $retRowIds
}

function Get-SurveyRowIDs($reportID) {
    $retRowIds = Get-RowIDsFromMap -map $surveyMap -reportID $reportID
    $retRowIds
}

function Get-BoundaryLayerRowIDs($reportID) {
    $retRowIds = Get-RowIDsFromMap -map $boundaryLayerMap -reportID $reportID
    $retRowIds
}

function Get-AssetLayerRowIDs($reportID) {
    $retRowIds = Get-RowIDsFromMap -map $materialLayerMap -reportID $reportID
    $retRowIds
}

function Get-LayerRowIDs($reportID) {
    $retRowIds = Get-RowIDsFromMap -map $layerMap -reportID $reportID
    $retRowIds
}

function Get-OptTabRowID($reportID) {
    $retRowIds = Get-RowIDsFromMap -map $optTabMap -reportID $reportID
    $retRowIds
}

function BaseMapTypeId-To-BaseMap($baseMapTypeId) {
    $baseMap = "Map"
    if ($baseMapTypeId -eq "00000000-0000-0000-0001-000000000000") {
        $baseMap = "Map"
    } elseif ($baseMapTypeId -eq "00000000-0000-0000-0002-000000000000") {
        $baseMap = "Satellite"
    } elseif ($baseMapTypeId -eq "00000000-0000-0000-0003-000000000000") {
        $baseMap = "None"
    }

    $baseMap
}

function Populate-Map($map, $reportID, $rptData) 
{
    if (-not $map.ContainsKey($reportID)) {
        $dataList = New-Object System.Collections.ArrayList
        $null = $dataList.Add($rptData)
        $map.Add($reportID, $dataList)
    } else {
        $dataList = [System.Collections.ArrayList]$map.Get_Item($reportID)
        if (-not $dataList.Contains($rptData)) {
            $null = $dataList.Add($rptData)
        }
        $map.Set_Item($reportID, $dataList)
    }        
}

function Is-InMaterialLayerList($custMaterialId) {
    [Int64]$retId = -1
    $materialLayerMap.Keys | %{
        $rptId = $_
        $objList = $materialLayerMap.get_item($rptId)
        $found = $false
        $objList | % {
            $obj = $_
            $CustomerMaterialId = $obj.CustomerMaterialId;
            [Int64]$RowID = $obj.RowID
            if ($custMaterialId -eq $CustomerMaterialId) {
                $found = $true
                $retId = $RowID + 1
            }
        }
    }

    $retId
}

function Is-InBoundaryLayerList($custBoundaryId) {
    [Int64]$retId = -1
    $boundaryLayerMap.Keys | %{
        $rptId = $_
        $objList = $boundaryLayerMap.get_item($rptId)
        $found = $false
        $objList | % {
            $obj = $_
            $CustomerBoundaryId = $obj.CustomerBoundaryId;
            [Int64]$RowID = $obj.RowID
            if ($custBoundaryId -eq $CustomerBoundaryId) {
                $found = $true
                $retId = $RowID + 1
            }
        }
    }

    $retId
}

function Is-InSurveyMapList($surveyId) {
}


function Pad-Zeroes([String]$value, [Int64]$width) {
    $value.PadLeft($width, "0")
}

$i = 0
$ReportIDs = ""
Import-Csv $ReportIDCsvFile | %{
    $row = $_
    $reportId = $row.ReportId

    if ($i -eq 0) {
        $ReportIDs = "'$reportId'"
    } else {
        $ReportIDs += ",'$reportId'"
    }

    $i++
}

#--------------------------------------#
# Store reportIDs in list for later use
#--------------------------------------#

Populate-ReportIDList

#--------------------------------------#
# Populate boundary layer map
#--------------------------------------#

$sqlQuery = "SELECT R.ID AS ReportId, CBT.Id AS CustomerBoundaryId, CBT.FeatureClassDescription AS CustomerBoundaryType FROM [$DatabaseName].[dbo].[Report] AS R
	INNER JOIN [$DatabaseName].[dbo].[ReportBoundaryLayer] AS RBL ON RBL.ReportId=R.Id
	INNER JOIN [$DatabaseName].[dbo].[CustomerBoundaryType] AS CBT ON RBL.CustomerBoundaryTypeId=CBT.Id
WHERE R.Id IN ($ReportIDs)"
Write-Host "Populating boundary layer map ... [DBQuery] -> $sqlQuery"
$objData = Get-DatabaseData -connectionString $ConnString -query $sqlQuery -isSQLServer:$true

$i = 0
$RowID = $StartingBoundaryLayerRowID
$objData | % {
    if ($i -gt 0) {
        $obj = $_
        $ReportId = $obj.ReportId;
        $CustomerBoundaryId = $obj.CustomerBoundaryId; 
        $CustomerBoundaryType = $obj.CustomerBoundaryType; 

        $retId = Is-InBoundaryLayerList -custBoundaryId $CustomerBoundaryId
        if ($retId -eq -1) {
            # Not already present. Add entry for the report.
            $Object = New-Object PSObject -Property @{            
                    CustomerBoundaryId   = $CustomerBoundaryId              
                    CustomerBoundaryType = $CustomerBoundaryType
                    RowID                = $RowID
            }

            Populate-Map -map $boundaryLayerMap -reportID $ReportId -rptData $Object
            $RowID++           # only if NOT already present in Map we increment the rowID

        } else {
            # Entry present. If present for same report ID then skip. Else use the rowID and add entry to map.
            if (!$boundaryLayerMap.ContainsKey($ReportId)) {
                $RowID = $retId
                $Object = New-Object PSObject -Property @{            
                        CustomerBoundaryId   = $CustomerBoundaryId              
                        CustomerBoundaryType = $CustomerBoundaryType
                        RowID                = $RowID
                }

                Populate-Map -map $boundaryLayerMap -reportID $ReportId -rptData $Object
            }
        }

        #add-content $OUTFILE_CBT "$CustomerBoundaryId|$CustomerBoundaryType"
    }

    $i++
}

#--------------------------------------#
# Populate asset layer map
#--------------------------------------#

$sqlQuery = "SELECT R.ID AS ReportId, CMT.Id AS CustomerMaterialId, CMT.Description AS CustomerMaterialType FROM [$DatabaseName].[dbo].[Report] AS R
	INNER JOIN [$DatabaseName].[dbo].[ReportAssetLayer] AS RAL ON RAL.ReportId=R.Id
	INNER JOIN [$DatabaseName].[dbo].[CustomerMaterialType] AS CMT ON RAL.CustomerMaterialTypeId=CMT.Id
WHERE R.Id IN ($ReportIDs)"
Write-Host "Populating asset layer map ... [DBQuery] -> $sqlQuery"
$objData = Get-DatabaseData -connectionString $ConnString -query $sqlQuery -isSQLServer:$true

$i = 0
$RowID = $StartingAssetLayerRowID
$objData | % {
    if ($i -gt 0) {
        $obj = $_
        $ReportId = $obj.ReportId;
        $CustomerMaterialId = $obj.CustomerMaterialId; 
        $CustomerMaterialType = $obj.CustomerMaterialType; 

        $retId = Is-InMaterialLayerList -custMaterialId $CustomerMaterialId
        if ($retId -eq -1) {
            # Not already present. Add entry for the report.
            $Object = New-Object PSObject -Property @{            
                    CustomerMaterialId   = $CustomerMaterialId              
                    CustomerMaterialType = $CustomerMaterialType
                    RowID                = $RowID
            }

            Populate-Map -map $materialLayerMap -reportID $ReportId -rptData $Object
            $RowID++           # only if NOT already present in Map we increment the rowID

        } else {
            # Entry present. If present for same report ID then skip. Else use the rowID and add entry to map.
            if (!$materialLayerMap.ContainsKey($ReportId)) {
                $RowID = $retId
                $Object = New-Object PSObject -Property @{            
                        CustomerMaterialId   = $CustomerMaterialId              
                        CustomerMaterialType = $CustomerMaterialType
                        RowID                = $RowID
                }

                Populate-Map -map $materialLayerMap -reportID $ReportId -rptData $Object
            }
        }
        #add-content $OUTFILE_CMT "$CustomerMaterialId|$CustomerMaterialType"
    }

    $i++
}

#--------------------------------------#
# Populate report survey map
#--------------------------------------#

$sqlQuery = "SELECT R.ID AS ReportId, RDS.SurveyId, S.Tag AS SurveyTag, RDS.Snapped FROM [$DatabaseName].[dbo].[Report] AS R
	INNER JOIN [$DatabaseName].[dbo].[ReportDrivingSurvey] AS RDS ON RDS.ReportId=R.Id
	INNER JOIN [$DatabaseName].[dbo].[Survey] AS S ON RDS.SurveyId=S.Id
WHERE R.Id IN ($ReportIDs)"
Write-Host "Populating report survey map ... [DBQuery] -> $sqlQuery"
$objData = Get-DatabaseData -connectionString $ConnString -query $sqlQuery -isSQLServer:$true

$i = 0
$objData | % {
    if ($i -gt 0) {
        $obj = $_
        $ReportId = $obj.ReportId;
        $SurveyId = $obj.SurveyId; 
        $SurveyTag = $obj.SurveyTag; 
        $Snapped = $obj.Snapped; 

        add-content $OUTFILE_SURVEYS "$SurveyId|$SurveyTag|$Snapped"

        # FIX: First round run create a list.
        #      In second round run rename and fix mapping.

    }

    $i++
}

#--------------------------------------#
# Populate report views map
#--------------------------------------#

$sqlQuery = "SELECT R.ID AS ReportId, RV.[ViewName],RV.[ShowLisa],RV.[ShowFov],RV.[ShowVehiclePath],RV.[ShowIndications],RV.[ShowIsotopicCaptures],RV.[ShowGaps],RV.[ShowAssets],RV.[ShowBoundaries],RV.[BaseMapId],RV.[BaseMapType_Id],RV.[HighlightLISAAssets],RV.[HighlightGAPAssets],RV.[ShowAssetBoxNumber] FROM [$DatabaseName].[dbo].[Report] AS R
	INNER JOIN [$DatabaseName].[dbo].[ReportView] AS RV ON RV.ReportId=R.Id
WHERE R.Id IN ($ReportIDs)"
Write-Host "Populating report views map ... [DBQuery] -> $sqlQuery"
$objData = Get-DatabaseData -connectionString $ConnString -query $sqlQuery -isSQLServer:$true

$i = 0
$RowID = $StartingViewRowID
$objData | % {
    if ($i -gt 0) {
        $obj = $_
        $ReportId = $obj.ReportId;
        $ViewName = $obj.ViewName; 
        $ShowLisa = $obj.ShowLisa; 
        $ShowFov = $obj.ShowFov; 
        $ShowVehiclePath = $obj.ShowVehiclePath; 
        $ShowIndications = $obj.ShowIndications; 
        $ShowIsotopicCaptures = $obj.ShowIsotopicCaptures; 
        $ShowGaps = $obj.ShowGaps; 
        $ShowAssets = $obj.ShowAssets; 
        $ShowBoundaries = $obj.ShowBoundaries; 
        $BaseMapId = $obj.BaseMapId; 
        $BaseMapType_Id = $obj.BaseMapType_Id; 
        $HighlightLISAAssets = $obj.HighlightLISAAssets; 
        $HighlightGAPAssets = $obj.HighlightGAPAssets; 
        $ShowAssetBoxNumber = $obj.ShowAssetBoxNumber; 

        $Object = New-Object PSObject -Property @{                        ViewName             = $ViewName                             ShowLisa             = $ShowLisa                          ShowFov              = $ShowFov                        ShowVehiclePath      = $ShowVehiclePath
            ShowIndications      = $ShowIndications
            ShowIsotopicCaptures = $ShowIsotopicCaptures
            ShowGaps             = $ShowGaps
            ShowAssets           = $ShowAssets
            ShowBoundaries       = $ShowBoundaries
            BaseMap              = BaseMapTypeId-To-BaseMap -baseMapTypeId $BaseMapType_Id
            HighlightLISAAssets  = $HighlightLISAAssets
            HighlightGAPAssets   = $HighlightGAPAssets
            ShowAssetBoxNumber   = $ShowAssetBoxNumber
            RowID                = $RowID
        }

        Populate-Map -map $viewMap -reportID $reportId -rptData $Object
        $RowID++
        
        #add-content $OUTFILE_VIEWS "$ViewName|$ShowLisa|$ShowFov|$ShowVehiclePath|$ShowIndications|$ShowIsotopicCaptures|$ShowGaps|$ShowAssets|$ShowBoundaries|$BaseMapId|$BaseMapType_Id|$HighlightLISAAssets|$HighlightGAPAssets|$ShowAssetBoxNumber"
    }

    $i++
}

#------------------------------------------------------------------------#
# Generate layerMap data from AssetLayerMap and BoundaryLayerMap
#------------------------------------------------------------------------#

$RowID = $StartingLayerRowID
$reportIdList | %{
    $key = $_
    $rptId = [System.Guid]::Parse($key)      # key used in Maps is GUID. Use guid instead of String.
    $assetRowIDs = Get-AssetLayerRowIDs -reportID $rptId
    $boundaryRowIDs = Get-BoundaryLayerRowIDs -reportID $rptId

    $Object = New-Object PSObject -Property @{                    RowID             = $RowID        AssetRowIDs       = $assetRowIDs                      BoundaryRowIDs    = $boundaryRowIDs                }

    Populate-Map -map $layerMap -reportID $rptId -rptData $Object

    $RowID++
}

#--------------------------------------#
# Populate reports map
#--------------------------------------#

$sqlQuery = "SELECT R.[Id] AS ReportId
      ,R.[ReportTitle]
      ,R.[CustomerId]
      ,T.UIDescription AS TimeZone
	  ,RC.ExclusionRadius
	  ,SMT.Description AS ReportMode
	  ,RC.MinimumAmplitude
	  ,RA.StartLat
	  ,RA.StartLong
	  ,RA.EndLat
	  ,RA.EndLong
	  ,'' AS CustomerBoundaryType
	  ,RA.ExternalId AS CustomerBoundaryName
      ,RC.FovOpacity
	  ,RC.LisaOpacity
	  ,R.MapWidth
	  ,R.MapHeight
	  ,RC.ShowIndications AS OptTabIndications
	  ,RC.ShowIsotopicAnalysis AS OptTabShowIsotopicAnalysis
	  ,RC.ShowGaps AS OptTabShowGaps
	  ,RC.ShowPercentCoverageAssets AS OptTabShowPercentCoverageAssets
	  ,RC.ShowPercentCoverageForecast AS OptTabShowPercentCoverageForecast
	  ,RC.ShowPercentCoverageReportArea AS OptTabShowPercentCoverageReportArea
	  ,AHT.Name AS SearchAreaPreference
	  ,R.UserId 
  FROM [$DatabaseName].[dbo].[Report] AS R
   INNER JOIN [$DatabaseName].[dbo].[ReportCompliance] AS RC ON R.Id=RC.ReportId
   INNER JOIN [$DatabaseName].[dbo].[ReportArea] AS RA ON R.Id=RA.ReportId
   INNER JOIN [$DatabaseName].[dbo].[TimeZone] AS T ON R.TimeZoneId=T.Id
   INNER JOIN [$DatabaseName].[dbo].[SurveyModeType] AS SMT ON SMT.Id=RC.SurveyModeTypeId
   INNER JOIN [$DatabaseName].[dbo].[AssetHighlightingTypes] AS AHT ON R.AssetHighlightingTypeId=AHT.Id
 WHERE R.ID IN ($ReportIDs)"

Write-Host "Generating report data from Report Table ... [DBQuery] -> $sqlQuery"
$objData = Get-DatabaseData -connectionString $ConnString -query $sqlQuery -isSQLServer:$true

$i = 0
$RowID = $StartingReportRowID
$OptTabRowID = $StartingOptTabRowID
$objData | % {
    if ($i -gt 0) {
        $obj = $_
        $ReportId = $obj.ReportId; 
        $ReportTitle = $obj.ReportTitle; 
        $CustomerId = $obj.CustomerId; 
        $TimeZone = $obj.TimeZone; 
        $ExclusionRadius = $obj.ExclusionRadius; 
        $ReportMode = $obj.ReportMode; 
        $MinimumAmplitude = $obj.MinimumAmplitude; 
        $StartLat = $obj.StartLat; 
        $StartLong = $obj.StartLong; 
        $EndLat = $obj.EndLat; 
        $EndLong = $obj.EndLong; 
        $CustomerBoundaryType = $obj.CustomerBoundaryType; 
        $CustomerBoundaryName = $obj.CustomerBoundaryName; 
        $FovOpacity = $obj.FovOpacity; 
        $LisaOpacity = $obj.LisaOpacity; 
        $MapWidth = $obj.MapWidth; 
        $MapHeight = $obj.MapHeight; 

        # data pertaining to OptLayer
        $OptTabIndications = $obj.OptTabIndications; 
        $OptTabShowIsotopicAnalysis = $obj.OptTabShowIsotopicAnalysis; 
        $OptTabShowGaps = $obj.OptTabShowGaps; 
        $OptTabShowPercentCoverageAssets = $obj.OptTabShowPercentCoverageAssets; 
        $OptTabShowPercentCoverageReportArea = $obj.OptTabShowPercentCoverageReportArea; 
        $OptTabShowPercentCoverageForecast = $obj.OptTabShowPercentCoverageForecast; 

        $optTabObj = New-Object PSObject -Property @{                        Indications                   = $OptTabIndications                             ShowIsotopicAnalysis          = $OptTabShowIsotopicAnalysis                          ShowGaps                      = $OptTabShowGaps                        ShowPercentCoverageAssets     = $OptTabShowPercentCoverageAssets
            ShowPercentCoverageReportArea = $OptTabShowPercentCoverageForecast
            RowID                         = $OptTabRowID
        }

        Populate-Map -map $optTabMap -reportID $ReportId -rptData $optTabObj
        $OptTabRowID++

        $SearchAreaPreference = $obj.SearchAreaPreference; 
        $UserId = $obj.UserId; 

        $ReportViewRowIDs = Get-ReportViewRowIDs -reportID $ReportId
        $ReportOptViewLayerRowIDs = Get-LayerRowIDs -reportID $ReportId
        $ReportOptTabularPDFContentRowIDs = Get-OptTabRowID -reportID $ReportId
        $ReportSurveyRowIDs = Get-SurveyRowIDs -reportID $ReportId

        add-content $OUTFILE_RPT "$RowID|PerfTestPGE-$(Pad-Zeroes -value $i -width 4)|GenerateRandomString(20)|4|$TimeZone|$ExclusionRadius|$ReportMode|$MinimumAmplitude|$StartLat|$StartLong|$EndLat|$EndLong|$CustomerBoundaryType|$CustomerBoundaryName|$FovOpacity|$LisaOpacity|$MapWidth|$MapHeight|$ReportViewRowIDs|$ReportOptViewLayerRowIDs|$ReportOptTabularPDFContentRowIDs|$ReportSurveyRowIDs|$SearchAreaPreference"
        $RowID++
    }

    $i++
}


#--------------------------------------#
# Print out all the referenced datas
#--------------------------------------#

Write-Host "Building boundary layer test case data..."
$boundaryLayerMapList = New-Object System.Collections.ArrayList
$boundaryLayerMap.Keys | %{
    $reportId = $_
    $objList = $boundaryLayerMap.get_item($reportId)
    $objList | %{
        $null = $boundaryLayerMapList.Add($_)
    }
}

$boundaryLayerMapList | Sort-Object -Property "RowID" | %{
    $obj = $_
    $RowID                = $obj.RowID
    $CustomerBoundaryId   = $obj.CustomerBoundaryId              
    $CustomerBoundaryType = $obj.CustomerBoundaryType
    Add-Content $OUTFILE_CBT "$RowID|$CustomerBoundaryId|$CustomerBoundaryType|4|PERF|PGE"
}

Write-Host "Building asset layer test case data..."
$materialLayerMapList = New-Object System.Collections.ArrayList
$materialLayerMap.Keys | %{
    $reportId = $_
    $objList = $materialLayerMap.get_item($reportId)
    $objList | %{
        $null = $materialLayerMapList.add($_)
    }
}

$materialLayerMapList | Sort-Object -Property "RowID" | %{
    $obj = $_
    $RowID                = $obj.RowID
    $CustomerMaterialId   = $obj.CustomerMaterialId              
    $CustomerMaterialType = $obj.CustomerMaterialType

    Add-Content $OUTFILE_CMT "$RowID|$CustomerMaterialId|$CustomerMaterialType|4|PERF|PGE"
}

Write-Host "Building layers test case data..."
$layerMapList = New-Object System.Collections.ArrayList
$layerMap.Keys | %{
    $reportId = $_
    $objList = $layerMap.get_item($reportId)
    $objList | %{
        $null = $layerMapList.Add($_)
    }
}

$layerMapList | Sort-Object -Property "RowID" | %{
    $obj = $_
    $RowID                = $obj.RowID
    $AssetRowIDs          = $obj.AssetRowIDs              
    $BoundaryRowIDs       = $obj.BoundaryRowIDs

    Add-Content $OUTFILE_LAYERS "$RowID|$AssetRowIDs|$BoundaryRowIDs|PGE - Perf Test assets and boundaries"
}

Write-Host "Building report survey test case data..."
$surveyMapList = New-Object System.Collections.ArrayList 
$surveyMap.Keys | %{
    $reportId = $_
}

Write-Host "Building report views test case data..."
$viewMapList = New-Object System.Collections.ArrayList 
$viewMap.Keys | %{
    $reportId = $_
    $objList = $viewMap.get_item($reportId)
    $objList | %{
        $null = $viewMapList.Add($_)
    }
}

$viewMapList | Sort-Object -Property "RowID" | %{
    $obj = $_
    $RowID                = $obj.RowID
    $ViewName             = $obj.ViewName                     $ShowLisa             = $obj.ShowLisa                  $ShowFov              = $obj.ShowFov                $ShowVehiclePath      = $obj.ShowVehiclePath
    $ShowIndications      = $obj.ShowIndications
    $ShowGaps             = $obj.ShowGaps
    $ShowAssets           = $obj.ShowAssets
    $ShowBoundaries       = $obj.ShowBoundaries
    $ShowIsotopicCaptures = $obj.ShowIsotopicCaptures
    $BaseMap              = $obj.BaseMap
    $HighlightLISAAssets  = $obj.HighlightLISAAssets
    $HighlightGAPAssets   = $obj.HighlightGAPAssets
    $ShowAssetBoxNumber   = $obj.ShowAssetBoxNumber

    Add-Content $OUTFILE_VIEWS "$RowID|$ViewName|$ShowLisa|$ShowFov|$ShowVehiclePath|$ShowIndications|$ShowGaps|$ShowAssets|$ShowBoundaries|$ShowIsotopicCaptures|$BaseMap|$HighlightLISAAssets|$HighlightGAPAssets|$ShowAssetBoxNumber"
}

Write-Host "Building opt tabular content test case data..."
$optTabMapList = New-Object System.Collections.ArrayList 
$optTabMap.Keys | %{
    $reportId = $_
    $objList = $optTabMap.get_item($reportId)
    $objList | % {
        $null = $optTabMapList.Add($_)
    }
}

$optTabMapList | Sort-Object -Property "RowID" | %{
    $obj = $_
    $RowID                         = $obj.RowID
    $Indications                   = $obj.Indications                     $ShowIsotopicAnalysis          = $obj.ShowIsotopicAnalysis                  $ShowGaps                      = $obj.ShowGaps                $ShowPercentCoverageAssets     = $obj.ShowPercentCoverageAssets
    $ShowPercentCoverageReportArea = $obj.ShowPercentCoverageReportArea

    Add-Content $OUTFILE_OPTTAB "$RowID|$Indications|$ShowIsotopicAnalysis|$ShowGaps|$ShowPercentCoverageAssets|$ShowPercentCoverageReportArea"
}

#--------------------------------------#
# Display results
#--------------------------------------#

Write-Host "Opening result files ..."

ii $OUTFILE_RPT
ii $OUTFILE_CBT
ii $OUTFILE_CMT
ii $OUTFILE_LAYERS
ii $OUTFILE_VIEWS
ii $OUTFILE_SURVEYS
ii $OUTFILE_OPTTAB

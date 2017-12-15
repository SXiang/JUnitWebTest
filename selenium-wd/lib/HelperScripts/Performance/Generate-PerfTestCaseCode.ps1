<#
SAMPLE USAGE:

.\Generate-PerfTestCaseCode.ps1 `
    -BaseScriptFolder "C:\Repositories\surveyor-qa"   `
    -CustomerName "PGE"   `
    -DatabaseIP "30.30.124.110"   `
    -DatabaseName "SurveyorScale"   `
    -DatabaseUser "spulikkal"   `
    -DatabasePwd "<password>"   `
    -ReportIDCsvFile "C:\temp\reportsForPerfTest-Filtered.csv"   `
    -DataProviderClassName "PerformancePGEReportJobDataProvider"   `
    -TestCategoryName "PGEPerf"   `
    -TestCategoryShortName  "PGE"   `
    -OutputFolder "C:\temp"
#>

param(
  [Parameter(Mandatory=$true)]
  [String] $BaseScriptFolder,

  [Parameter(Mandatory=$true)]
  [String] $CustomerName,

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
  [String] $DataProviderClassName,

  [Parameter(Mandatory=$true)]
  [String] $TestCategoryName,

  [Parameter(Mandatory=$true)]
  [String] $TestCategoryShortName,

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
$userIDToXLSXRowIDMap = @{
    "8CBBA239-38C1-9869-5595-39D7CBF041E7"="34"           # userID in report and corresponding RowID in XLSX. this is list of unique users across all the reports in input reportIDCSVFile
    "68E53CEE-4B70-5A37-9F8E-39D68F05EFC9"="35"
    "4DF19C72-13BE-74E3-64B7-39D68F0988AF"="36"
    "14DD2F4F-465D-2D29-8B88-39D68F195F12"="37"
}

### ----------------------------------------------------------------------------------------------------------

. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"
. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"

$ConnString="Server=$DatabaseIP;Database=$DatabaseName;User Id=$DatabaseUser;Password=$DatabasePwd;"

$OUTFILE_PROVIDER_CODE = New-Item "$OutputFolder\ProviderCode.txt" -ItemType File -Force

$OUTFILE_RPT = New-Item "$OutputFolder\Perf_ComplianceReportXLSXData.txt" -ItemType File -Force
$OUTFILE_CBT = New-Item "$OutputFolder\Perf_CBTXLSXData.txt" -ItemType File -Force
$OUTFILE_CMT = New-Item "$OutputFolder\Perf_CMTXLSXData.txt" -ItemType File -Force
$OUTFILE_LAYERS = New-Item "$OutputFolder\Perf_LayersXLSXData.txt" -ItemType File -Force
$OUTFILE_VIEWS = New-Item "$OutputFolder\Perf_ViewsXLSXData.txt" -ItemType File -Force
$OUTFILE_OPTTAB = New-Item "$OutputFolder\Perf_OptTabXLSXData.txt" -ItemType File -Force
$OUTFILE_SURVEYS = New-Item "$OutputFolder\Perf_SurveysXLSXData.txt" -ItemType File -Force

$reportIdList = New-Object System.Collections.ArrayList
$reportInfoList = New-Object System.Collections.ArrayList

# all values list
$surveysAllList = New-Object System.Collections.ArrayList
$materialLayerAllList = New-Object System.Collections.ArrayList
$boundaryLayerAllList = New-Object System.Collections.ArrayList
 
# report specific maps
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

function Populate-MapAndList($map, $list, $reportID, $rptData) 
{
    if (-not $map.ContainsKey($reportID)) {
        $dataList = New-Object System.Collections.ArrayList
        $null = $dataList.Add($rptData)
        if ($list -eq $null) { <# handles explicit NULL passed case #> }
        else {
            $null = $list.Add($rptData)
        }
        $map.Add($reportID, $dataList)
    } else {
        $dataList = [System.Collections.ArrayList]$map.Get_Item($reportID)
        if (-not $dataList.Contains($rptData)) {
            $null = $dataList.Add($rptData)
            if ($list -eq $null) { <# handles explicit NULL passed case #> }
            else {
                $null = $list.Add($rptData)
            }
        }
        $map.Set_Item($reportID, $dataList)
    }        
}

function Populate-Map($map, $reportID, $rptData) 
{
    Populate-MapAndList -map $map -list $null -reportId $reportID -rptData $rptData
}

function Is-InMaterialLayerList($custMaterialId) {
    $objMatLayer = $null
    $found = $false
    $MaterialLayerAllList | %{
        if (-not $found) {
            $obj = $_
            $matId = $obj.CustomerMaterialId;
            if ($matId -eq $custMaterialId) {
                $found = $true
                $objMatLayer = New-Object PSObject -Property @{            
                    CustomerMaterialId        = $obj.CustomerMaterialId              
                    CustomerMaterialType      = $obj.CustomerMaterialType
                    RowID                     = $obj.RowID
                }
            }
        }
    }

    $objMatLayer
}

function Is-InBoundaryLayerList($custBoundaryId) {
    $objBoundLayer = $null
    $found = $false
    $boundaryLayerAllList | %{
        if (-not $found) {
            $obj = $_
            $boundId = $obj.CustomerBoundaryId;
            if ($boundId -eq $custBoundaryId) {
                $found = $true
                $objBoundLayer = New-Object PSObject -Property @{            
                    CustomerBoundaryId        = $obj.CustomerBoundaryId              
                    CustomerBoundaryType      = $obj.CustomerBoundaryType
                    RowID                     = $obj.RowID
                }
            }
        }
    }

    $objBoundLayer
}

function Is-InAllSurveysList($surveyId) {
    $objSrv = $null
    $found = $false
    $surveysAllList | %{
        if (-not $found) {
            $obj = $_
            $SrvId = $obj.SurveyId;
            if ($SrvId -eq $surveyId) {
                $found = $true
                $objSrv = New-Object PSObject -Property @{            
                    SurveyId        = $obj.SurveyId              
                    SurveyTag       = $obj.SurveyTag
                    SurveyModeType  = $obj.SurveyModeType
                    RowID           = $obj.RowID
                }
            }
        }
    }

    $objSrv
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

        $isNew = $false
        $Object = Is-InBoundaryLayerList -custBoundaryId $CustomerBoundaryId
        if ($Object -eq $null) {
            $isNew = $true
            $Object = New-Object PSObject -Property @{            
                    CustomerBoundaryId   = $CustomerBoundaryId              
                    CustomerBoundaryType = $CustomerBoundaryType
                    RowID                = $RowID
            }
        }

        Populate-MapAndList -map $boundaryLayerMap -list $BoundaryLayerAllList -reportID $ReportId -rptData $Object
        
        if ($isNew) {
            # increment RowID if new survey row.
            $RowID++
        }
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

        $isNew = $false
        $Object = Is-InMaterialLayerList -custMaterialId $CustomerMaterialId
        if ($Object -eq $null) {
            $isNew = $true
            $Object = New-Object PSObject -Property @{            
                    CustomerMaterialId   = $CustomerMaterialId              
                    CustomerMaterialType = $CustomerMaterialType
                    RowID                = $RowID
            }
        }

        Populate-MapAndList -map $materialLayerMap -list $MaterialLayerAllList -reportID $ReportId -rptData $Object
        
        if ($isNew) {
            # increment RowID if new survey row.
            $RowID++
        }
    }

    $i++
}

#--------------------------------------#
# Populate report survey map
#--------------------------------------#

$sqlQuery = "SELECT R.Id AS ReportId, S.Id AS SurveyId, S.Tag as SurveyTag, SMT.Description as SurveyModeType
     FROM [$DatabaseName].[dbo].[Report] AS R
       INNER JOIN [$DatabaseName].[dbo].[ReportDrivingSurvey] AS RDS ON R.Id=RDS.ReportId
       INNER JOIN [$DatabaseName].[dbo].[Survey] AS S ON RDS.SurveyId=S.Id
       INNER JOIN [$DatabaseName].[dbo].[SurveyModeType] AS SMT ON SMT.Id=S.SurveyModeTypeId
       INNER JOIN [$DatabaseName].[dbo].[Customer] AS C on R.CustomerId=C.Id
     WHERE R.Id IN ($ReportIDs)"
Write-Host "Populating report survey map ... [DBQuery] -> $sqlQuery"
$objData = Get-DatabaseData -connectionString $ConnString -query $sqlQuery -isSQLServer:$true

$i = 0
$RowID = $StartingSurveyRowID
$objData | % {
    if ($i -gt 0) {
        $obj = $_
        $ReportId = $obj.ReportId;
        $SurveyId = $obj.SurveyId;
        $SurveyTag = $obj.SurveyTag;
        $SurveyModeType = $obj.SurveyModeType; 

        $isNew = $false
        $Object = Is-InAllSurveysList -surveyId $SurveyId
        if ($Object -eq $null) {
            $isNew = $true
            $Object = New-Object PSObject -Property @{            
                    SurveyId        = $SurveyId              
                    SurveyTag       = $SurveyTag
                    SurveyModeType  = $SurveyModeType
                    RowID           = $RowID
                }
        }

        Populate-MapAndList -map $surveyMap -list $surveysAllList -reportID $ReportId -rptData $Object
        
        if ($isNew) {
            # increment RowID if new survey row.
            $RowID++
        }
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

        $testCaseName = "PerfTest${CustomerName}-$(Pad-Zeroes -value $i -width 4)"

        add-content $OUTFILE_RPT "$RowID|$testCaseName|GenerateRandomString(20)|4|$TimeZone|$ExclusionRadius|$ReportMode|$MinimumAmplitude|$StartLat|$StartLong|$EndLat|$EndLong|$CustomerBoundaryType|$CustomerBoundaryName|$FovOpacity|$LisaOpacity|$MapWidth|$MapHeight|$ReportViewRowIDs|$ReportOptViewLayerRowIDs|$ReportOptTabularPDFContentRowIDs|$ReportSurveyRowIDs|$SearchAreaPreference"

        # Store report info for use with DataProvider code generation.
        $ReportInfoObject = New-Object PSObject -Property @{                        ReportId          = $ReportId            UserId            = $UserId            ReportTitle       = $ReportTitle                          RowID             = $RowID            TestCaseName      = $testCaseName        }

        $null = $reportInfoList.Add($ReportInfoObject)

        $RowID++
    }

    $i++
}

#--------------------------------------#
# Generate TestCase data
#--------------------------------------#

Write-Host "Building boundary layer test case data..."
$printedBoundaryLayers = New-Object System.Collections.ArrayList
$boundaryLayerAllList | Sort-Object -Property "RowID" | %{
    $obj = $_
    $RowID                = $obj.RowID
    $CustomerBoundaryId   = $obj.CustomerBoundaryId              
    $CustomerBoundaryType = $obj.CustomerBoundaryType

    if (!$printedBoundaryLayers.Contains($CustomerBoundaryId)) {
        $null = $printedBoundaryLayers.Add($CustomerBoundaryId)
        Add-Content $OUTFILE_CBT "$RowID|$CustomerBoundaryId|$CustomerBoundaryType|4|PERF|${CustomerName}"
    }
}

Write-Host "Building asset layer test case data..."
$printedMaterialLayers = New-Object System.Collections.ArrayList
$materialLayerAllList | Sort-Object -Property "RowID" | %{
    $obj = $_
    $RowID                = $obj.RowID
    $CustomerMaterialId   = $obj.CustomerMaterialId              
    $CustomerMaterialType = $obj.CustomerMaterialType

    if (!$printedMaterialLayers.Contains($CustomerMaterialId)) {
        $null = $printedMaterialLayers.Add($CustomerMaterialId)
        Add-Content $OUTFILE_CMT "$RowID|$CustomerMaterialId|$CustomerMaterialType|4|PERF|${CustomerName}"
    }
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

    Add-Content $OUTFILE_LAYERS "$RowID|$AssetRowIDs|$BoundaryRowIDs|${CustomerName} - Perf Test assets and boundaries"
}

Write-Host "Building report survey test case data..."
$surveysAllList | Sort-Object -Property "RowID" | %{
    $obj = $_
    $SurveyId        = $obj.SurveyId              
    $SurveyTag       = $obj.SurveyTag
    $SurveyModeType  = $obj.SurveyModeType
    $RowID           = $obj.RowID

    Add-Content $OUTFILE_SURVEYS "$RowID|||$SurveyTag|||$SurveyModeType|FALSE|1|TRUE|0"

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
# Generate Test Case Provider code
#--------------------------------------#

function Generate-DataProviderTestClassCode() {
    add-content $OUTFILE_PROVIDER_CODE "package surveyor.dataprovider;"
    add-content $OUTFILE_PROVIDER_CODE ""
    add-content $OUTFILE_PROVIDER_CODE "import org.junit.runner.notification.RunNotifier;"
    add-content $OUTFILE_PROVIDER_CODE "import org.junit.runners.model.InitializationError;"
    add-content $OUTFILE_PROVIDER_CODE ""
    add-content $OUTFILE_PROVIDER_CODE "import com.tngtech.java.junit.dataprovider.DataProvider;"
    add-content $OUTFILE_PROVIDER_CODE ""
    add-content $OUTFILE_PROVIDER_CODE "public class $DataProviderClassName extends ReportDataProvider {"
    add-content $OUTFILE_PROVIDER_CODE ""
    
    $TestCategoryShortNameToUpper = $TestCategoryShortName.ToUpper()

    $total = $reportInfoList.Count
    $tcsInEachProviderMethod = [Int64]($total / 5) + 1
        1..5 | %{        $idx = $_
        $providerConst = "REPORT_JOB_PERFORMANCE_PROVIDER_${TestCategoryShortNameToUpper}${idx}"
        $providerMethod = "dataProviderReportJobPerformance${TestCategoryShortName}${idx}"        add-content $OUTFILE_PROVIDER_CODE "	public static final String $providerConst = ""$providerMethod"";"
        $idx++
    }

    $TestCategoryNameToUpper = $TestCategoryName.ToUpper()

    $idx = 1    $reportInfoList | %{        $obj = $_        $RowID = $obj.RowID                $reportDataRowIDConst = "${TestCategoryNameToUpper}$(Pad-Zeroes -value $idx -width 4)_REPORT_DATA_ROW_ID"
        add-content $OUTFILE_PROVIDER_CODE "	private static final int $reportDataRowIDConst = $RowID;"
        $idx++
    }
    $idx = 1    $reportInfoList | %{        $obj = $_                $originalUserId = $obj.UserId.ToString().ToUpper()        $xlsxUserId = $userIDToXLSXRowIDMap.get_item($originalUserId)        $userRowIDConst = "${TestCategoryNameToUpper}$(Pad-Zeroes -value $idx -width 4)_USER_ROW_ID"
        add-content $OUTFILE_PROVIDER_CODE "	private static final int $userRowIDConst = $xlsxUserId;"
        $idx++
    }    
    1..5 | %{
        $idx = $_
        $providerMethod = "dataProviderReportJobPerformance${TestCategoryShortName}${idx}"
        add-content $OUTFILE_PROVIDER_CODE ""
        add-content $OUTFILE_PROVIDER_CODE "	@DataProvider"
        add-content $OUTFILE_PROVIDER_CODE "	public static Object[][] $providerMethod() {"
        add-content $OUTFILE_PROVIDER_CODE ""
        add-content $OUTFILE_PROVIDER_CODE "		return new Object[][] {"

        $endIdx = $idx * $tcsInEachProviderMethod        $startIdx = $endIdx - $tcsInEachProviderMethod + 1        $i = 1        $reportInfoList | %{            if (($i -ge $startIdx) -and ($i -le $endIdx)) {                $obj = $_                        $ReportId          = $obj.ReportId                $ReportTitle       = $obj.ReportTitle                              $RowID             = $obj.RowID                $TestCaseName      = $obj.TestCaseName                $userRowIDConst = "${TestCategoryNameToUpper}$(Pad-Zeroes -value $i -width 4)_USER_ROW_ID"                $reportDataRowIDConst = "${TestCategoryNameToUpper}$(Pad-Zeroes -value $i -width 4)_REPORT_DATA_ROW_ID"                add-content $OUTFILE_PROVIDER_CODE "			{ ""$TestCaseName"", $userRowIDConst, $reportDataRowIDConst },"
            }

            $i++
        }
        add-content $OUTFILE_PROVIDER_CODE "		};"
        add-content $OUTFILE_PROVIDER_CODE "	}"
    }

    add-content $OUTFILE_PROVIDER_CODE "}"
}

Generate-DataProviderTestClassCode


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

ii $OUTFILE_PROVIDER_CODE

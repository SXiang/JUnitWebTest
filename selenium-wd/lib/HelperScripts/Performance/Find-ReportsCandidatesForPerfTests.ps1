<#
DESCRIPTION: This script can be used to generate list of Reports for specified customer that are good candidates for including in Perf test suite.
  Reports are looked up based on following criteria:
  - Large ProcessingTimeInMins	
  - Large SurveyCount	
  - Large PeakCount	
  - Large Area
  Output includes top 20 from each group filtering out duplicates.

NOTES: Script will generate output CSVs which can be used as input to 

SAMPLE USAGE:
.\Find-ReportsCandidatesForPerfTests.ps1 `
    -BaseScriptFolder "C:\Repositories\surveyor-qa"   `
    -CustomerName "PG&E"   `
    -CustomerWorkspaceName "PGE"   `
    -DatabaseIP "30.30.124.110"   `
    -DatabaseName "SurveyorScale"   `
    -DatabaseUser "spulikkal"   `
    -DatabasePwd "<password>"   `
    -GeoserverBaseUrl "http://30.30.150.198:8080"   `
    -GeoserverUsername "PGE_VIEWER"   `
    -GeoserverPassword "<password>"   `
    -OutputFolder "C:\temp"
#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $BaseScriptFolder,

  [Parameter(Mandatory=$true)]
  [String] $CustomerName,

  [Parameter(Mandatory=$true)]
  [String] $CustomerWorkspaceName,

  [Parameter(Mandatory=$true)]
  [String] $DatabaseIP,

  [Parameter(Mandatory=$true)]
  [String] $DatabaseName,

  [Parameter(Mandatory=$true)]
  [String] $DatabaseUser,

  [Parameter(Mandatory=$true)]
  [String] $DatabasePwd,

  [Parameter(Mandatory=$true)]
  [String] $GeoserverBaseUrl,

  [Parameter(Mandatory=$true)]
  [String] $GeoserverUsername,

  [Parameter(Mandatory=$true)]
  [String] $GeoserverPassword,

  [Parameter(Mandatory=$true)]
  [String] $OutputFolder
)

$MIN_PROCESSING_TIME_IN_MINUTES = 5
$TOP_REPORTS = 100
$SINGLE_QUOTE_ENCODED = "%27"

$script:reportsAll = @{}
$script:reportExternalIdMap = @{}
$script:reportUsersMap = @{}
$script:reportsBySurveyCount = @{}
$script:reportsByNumIndications = @{}
$script:reportsBySelectedArea = @{}
$script:reportsByAssetCountInSelectedArea = @{}

. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"
. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"

# Output files.
$OUTFILE = New-Item "$OutputFolder\reportsForPerfTest-ALL.csv" -ItemType File -Force
$OUTFILE_FILTERED = New-Item "$OutputFolder\reportsForPerfTest-Filtered.csv" -ItemType File -Force
$ERRORFILE = New-Item "$OutputFolder\Errors.txt" -ItemType File -Force

#----------------------------------------------------------
# Helper functions
#----------------------------------------------------------
function Get-ResponseFromGeoServer($geoServerQuery) {
    $authInfo = "${GeoserverUsername}:${GeoserverPassword}"
    $authInfoBase64 = [System.Convert]::ToBase64String([System.Text.Encoding]::ASCII.GetBytes($authInfo))
    $authValue = "Basic $authInfoBase64"
    $authHeader = @{
        Authorization = "$authValue"
    }

    Write-Host "Created authentication header -> $authHeader"

    Write-Host "Invoking request -> $geoServerQuery "
    $responseString = Invoke-WebRequest -Uri $geoServerQuery -Headers $authHeader -Method POST
    $objResponse = ConvertFrom-Json $responseString
    $objResponse
}

function Generate-ReportInClause() {
    $inClause = ""
    $i = 0
    $script:reportsAll.Keys | %{
        $reportId = $_
        if ($i -eq 0) {
            $inClause = "'$reportId'"
        } else {
            $inClause += ",'$reportId'"
        }

        $i++
    }

    $inClause
}


#----------------------------------------------------------
# 1. Find all reports that took longer than specified minutes for execution.
#----------------------------------------------------------
$query = "SELECT TOP $TOP_REPORTS R.[Id], DATEDIFF(MINUTE, R.ProcessingStarted, R.ProcessingCompleted) AS ProcessingTimeInMins FROM [$DatabaseName].[dbo].[Report] AS R " +
            " INNER JOIN [$DatabaseName].[dbo].[Customer] AS C ON R.CustomerId = C.Id " +
            " WHERE C.Name = '$CustomerName' AND DATEDIFF(MINUTE, R.ProcessingStarted, R.ProcessingCompleted)>$MIN_PROCESSING_TIME_IN_MINUTES " +
            "  ORDER BY ProcessingTimeInMins DESC "

$ConnString = "Server=$DatabaseIP;Database=$DatabaseName;User Id=$DatabaseUser;Password=$DatabasePwd;"
$objReports = Get-DatabaseData -connectionString $ConnString -query $query -isSQLServer:$true

$i = 0
$objReports | % {
    if ($i -gt 0) {
        $obj = $_
        $reportId  = $obj.Id;
        $processingTimeInMins = $obj.ProcessingTimeInMins;

        Write-Host "$reportId -> $processingTimeInMins mins"

        $Object = New-Object PSObject -Property @{
	        ReportId              = $ReportId
            ProcessingTimeInMins  = $ProcessingTimeInMins
        }

        $script:reportsAll.set_item($reportId, $Object)
    }

    $i++
}

#----------------------------------------------------------
# 2. Categorize reports into following buckets:-
#  - Large ProcessingTimeInMins	
#  - Large SurveyCount	
#  - Large PeakCount	
#  - Large Area
#----------------------------------------------------------
function Build-ReportBySurveyCountMap() {
    $inClause = Generate-ReportInClause
    $query = "SELECT R.Id, COUNT (*) SurveyCount" +
                " FROM [$DatabaseName].[dbo].[Report] AS R" +
                "   INNER JOIN [$DatabaseName].[dbo].[ReportDrivingSurvey] AS RDS ON R.Id=RDS.ReportId" +
                "   INNER JOIN [$DatabaseName].[dbo].[Survey] AS S ON RDS.SurveyId=S.Id" +
                "   INNER JOIN [$DatabaseName].[dbo].[Customer] AS C on R.CustomerId=C.Id" +
                " WHERE C.Name='$CustomerName' AND R.ID IN ($inClause)" +
                " GROUP BY R.ID" +
                " Order by SurveyCount DESC"
    $objReports = Get-DatabaseData -connectionString $ConnString -query $query -isSQLServer:$true
    $j = 0
    $objReports | % {
        if ($j -gt 0) {
            $obj = $_
            $reportId  = $obj.Id;
            $SurveyCount = $obj.SurveyCount;

            Write-Host "$reportId -> $SurveyCount surveys"

            $Object = New-Object PSObject -Property @{
	            ReportId      = $ReportId
                SurveyCount   = $SurveyCount
            }

            $script:reportsBySurveyCount.set_item($reportId, $Object)
        }

        $j++
    }
}

function Build-ReportByPeakCountMap() {
    $inClause = Generate-ReportInClause
    $query = "SELECT R.Id, COUNT (*) PeakCount
                FROM [$DatabaseName].[dbo].[Report] AS R
                INNER JOIN [$DatabaseName].[dbo].[ReportPeak] AS RP ON R.Id=RP.ReportId
                INNER JOIN [$DatabaseName].[dbo].[Box] AS B ON B.ReportPeakId=RP.Id
                INNER JOIN [$DatabaseName].[dbo].[BoxTypes] AS BT ON B.BoxTypeId=BT.Id
                INNER JOIN [$DatabaseName].[dbo].[Customer] AS C on R.CustomerId=C.Id
                WHERE C.Name='$CustomerName' AND BT.Name='Indication' AND R.ID IN ($inClause)
                GROUP BY R.ID 
                Order by PeakCount DESC"
    $objReports = Get-DatabaseData -connectionString $ConnString -query $query -isSQLServer:$true
    $j = 0
    $objReports | % {
        if ($j -gt 0) {
            $obj = $_
            $reportId  = $obj.Id;
            $PeakCount = $obj.PeakCount;

            Write-Host "$reportId -> $PeakCount indications"

            $Object = New-Object PSObject -Property @{
	            ReportId      = $ReportId
                PeakCount     = $PeakCount
            }

            $script:reportsByNumIndications.set_item($reportId, $Object)
        }

        $j++
    }
}

function Build-ReportByAreaMap() {
    $inClause = Generate-ReportInClause
    $query = "SELECT R.ID, 
	            CASE 
                    WHEN (RA.ReportAreaModeTypeId = '00000000-0000-0000-0002-000000000000') THEN 
		            geography::STGeomFromText(
			            geometry::STGeomFromText('MULTIPOINT (('
				            + STR(RA.StartLong, 25, 12) + ' ' + STR(RA.StartLat, 25, 12) + '),('
				            + STR(RA.EndLong, 25, 12) + ' ' + STR(RA.EndLat, 25, 12) + '))', 4326).STEnvelope()
			            .STAsText(),4326)
		              .STArea()/(1000*1000)  
                    WHEN (RA.ReportAreaModeTypeId = '00000000-0000-0000-0001-000000000000') THEN
		             0
                    WHEN (RA.Shape IS NOT NULL) THEN
		             RA.Shape.STArea()/(1000*1000)
	                ELSE 0
                    END  AS Area, RA.*
                 FROM [$DatabaseName].[dbo].[Report] AS R
                 INNER JOIN [$DatabaseName].[dbo].[ReportArea] AS RA ON R.Id = RA.ReportId
                 INNER JOIN [$DatabaseName].[dbo].[Customer] AS C on R.CustomerId=C.Id
                 WHERE C.Name='$CustomerName' AND R.Id IN ($inClause)
                 Order BY Area DESC"
    $objReports = Get-DatabaseData -connectionString $ConnString -query $query -isSQLServer:$true
    $j = 0
    $objReports | % {
        if ($j -gt 0) {
            $obj = $_
            $reportId  = $obj.Id;
            $Area = $obj.Area;

            Write-Host "$reportId -> $Area sqkms"

            if ("$Area" -ne "0") {
                $Object = New-Object PSObject -Property @{
	                ReportId  = $ReportId
                    Area      = $Area
                }

                $script:reportsBySelectedArea.set_item($reportId, $Object)
            }
        }

        $j++
    }
}

function Build-ReportExternalIdMap() {
    $inClause = Generate-ReportInClause

    $query = "SELECT R.ID, ISNULL(RA.ExternalID, 'NULL') AS ExternalID
                 FROM [$DatabaseName].[dbo].[Report] AS R
                 INNER JOIN [$DatabaseName].[dbo].[ReportArea] AS RA ON R.Id = RA.ReportId
                 INNER JOIN [$DatabaseName].[dbo].[Customer] AS C on R.CustomerId=C.Id
                 WHERE C.Name='$CustomerName' AND R.Id IN ($inClause)"
    $objReports = Get-DatabaseData -connectionString $ConnString -query $query -isSQLServer:$true
    $j = 0
    $objReports | % {
        if ($j -gt 0) {
            $obj = $_
            $reportId  = $obj.Id;
            $ExternalID = $obj.ExternalID;

            if ($ExternalID -ne "NULL") {
                Write-Host "$reportId -> ExternalID = $ExternalID"
                $script:reportExternalIdMap.set_item($reportId, $ExternalID)
            }
        }

        $j++
    }
}

function Build-ReportUsersMap() {
    $inClause = Generate-ReportInClause

    $query = "SELECT R.ID, ISNULL(RA.ExternalID, 'NULL') AS ExternalID
                 FROM [$DatabaseName].[dbo].[Report] AS R
                 INNER JOIN [$DatabaseName].[dbo].[ReportArea] AS RA ON R.Id = RA.ReportId
                 INNER JOIN [$DatabaseName].[dbo].[Customer] AS C on R.CustomerId=C.Id
                 WHERE C.Name='$CustomerName' AND R.Id IN ($inClause)"
    $objReports = Get-DatabaseData -connectionString $ConnString -query $query -isSQLServer:$true
    $j = 0
    $objReports | % {
        if ($j -gt 0) {
            $obj = $_
            $reportId  = $obj.Id;
            $ExternalID = $obj.ExternalID;

            if ($ExternalID -ne "NULL") {
                Write-Host "$reportId -> ExternalID = $ExternalID"
                $script:reportExternalIdMap.set_item($reportId, $ExternalID)
            }
        }

        $j++
    }
}


Build-ReportExternalIdMap
Build-ReportBySurveyCountMap
Build-ReportByPeakCountMap
Build-ReportByAreaMap

#----------------------------------------------------------
# Populate area info for customer boundary types by querying geoserver
#----------------------------------------------------------

$DEBUG = $FALSE
$DEBUG_PRINT_GEO_SERVER_URLS_WITH_FEATURE_TYPE = $FALSE
$LOGFILE = New-Item "C:\temp\logFile.txt" -ItemType File -Force

$script:reportExternalIdMap.Keys | %{
    $reportId = $_
    $externalId = $script:reportExternalIdMap.get_item($reportId)
    $CustomerFeatureClassName = "Boundary"
    $filter = "ExternalId=${SINGLE_QUOTE}$externalId${SINGLE_QUOTE}"
    $geoServerQuery = "$GeoserverBaseUrl/geoserver/${CustomerWorkspaceName}/wfs/ows?typeName=${CustomerWorkspaceName}:${CustomerFeatureClassName}&outputFormat=json&cql_filter=$filter&version=1.0.0&service=WFS&request=GetFeature"
    $qResponse = Get-ResponseFromGeoServer -geoServerQuery $geoServerQuery
    
    $feature = $qResponse.features[0]
    [String]$featureType = $feature.geometry.type

    if ($featureType -ne "") {
        $featureCoords = $feature.geometry.coordinates
        $pointsText = ""
        $j = 0

        if ($DEBUG_PRINT_GEO_SERVER_URLS_WITH_FEATURE_TYPE) {
            Add-Content $LOGFILE "[$featureType] $geoServerQuery"
        }

        $featureTypeUpper = $featureType.Trim().ToUpperInvariant()        
        if ($featureTypeUpper -ne "MULTIPOLYGON") {
            Add-Content $ERRORFILE "[WARN] Found feature type other than MULTIPOLYGON. Check if logic will work. FeatureType = $featureType"
            Add-Content $ERRORFILE "`$geoServerQuery -> $geoServerQuery"
        }

        $featureCoords | %{
            $featureCoordsArr = $_

            if ($j -gt 0) {
                Add-Content $ERRORFILE "[ERROR] MULTIPOLYGON LOGIC used to compute AREA MIGHT NOT work. Found more than 1 array item."
                Add-Content $ERRORFILE "`$geoServerQuery -> $geoServerQuery"
            }

            $m = 0        
            $featureCoordsArr | %{
                $coords = $_
            
                if ($m -gt 0) {
                    Add-Content $ERRORFILE "[WARN] Check logic for computing MULTIPOLYGON"
                    Add-Content $ERRORFILE "`$geoServerQuery -> $geoServerQuery"
                }

                $k = 0
                $coords | %{
                    $coord = $_
                    $long = $coord[0]
                    $lat = $coord[1]
                    $singlePoint = "$long $lat"
	                if ($k -eq 0) {
		                $pointsText = "$singlePoint"
	                } else {
		                $pointsText += ",$singlePoint"
	                }

                    $k++
                }

                $m++
            }

	        $j++
        }

        $geometry = "geometry::STGeomFromText('$featureType ((( $pointsText )))', 4326).STEnvelope()"
        $geography = "geography::STGeomFromText(${geometry}.STAsText(),4326)"
        $area = "${geography}.STArea()/(1000*1000)"
        $areaSQLQuery = "SELECT $area AS Area"
        $objReports = Get-DatabaseData -connectionString $ConnString -query $areaSQLQuery -isSQLServer:$true
        $j = 0
        $objReports | % {
            if ($j -gt 0) {
                $obj = $_
                $Area = $obj.Area;

                Write-Host "$reportId -> $Area sqkms"

                if ("$Area" -ne "0") {
                    $Object = New-Object PSObject -Property @{
	                    ReportId  = $ReportId
                        Area      = $Area
                    }

                    $script:reportsBySelectedArea.set_item($reportId, $Object)
                }
            }

            $j++
        }
    }
}

#----------------------------------------------------------
# 3. Choose TOP 20 from each category, filtering out duplicates
#----------------------------------------------------------
Add-Content $OUTFILE "ReportId,ProcessingTimeInMins,SurveyCount,PeakCount,Area"

$script:processingTimeInMSecList = New-Object System.Collections.ArrayList
$script:SurveyCountList = New-Object System.Collections.ArrayList
$script:PeakCountList = New-Object System.Collections.ArrayList
$script:AreaList = New-Object System.Collections.ArrayList

$script:ReportsFull = @{}

$script:reportsAll.Keys | %{
    $reportId = $_
    
    $objProcTimeInMins = $script:reportsAll.get_item($reportId)
    $null = $script:processingTimeInMSecList.Add($objProcTimeInMins)
    $procTimeInMins = $objProcTimeInMins.ProcessingTimeInMins
    
    $surveyCount = 0
    if ($script:reportsBySurveyCount.ContainsKey($reportId)) {
        $objSurveyCount = $script:reportsBySurveyCount.get_item($reportId)
        $null = $script:SurveyCountList.Add($objSurveyCount)
        $surveyCount = $objSurveyCount.SurveyCount
    }
    $peakCount = 0
    if ($script:reportsByNumIndications.ContainsKey($reportId)) {
        $objPeakCount = $script:reportsByNumIndications.get_item($reportId)
        $null = $script:PeakCountList.Add($objPeakCount)
        $peakCount = $objPeakCount.PeakCount
    }
    $area = 0
    if ($script:reportsBySelectedArea.ContainsKey($reportId)) {
        $objArea = $script:reportsBySelectedArea.get_item($reportId)
        $null = $script:AreaList.Add($objArea)
        $area = $objArea.Area
    }

    Add-Content $OUTFILE "$reportId,$procTimeInMins,$surveyCount,$peakCount,$area"

    $objFull = New-Object PSObject -Property @{                    ReportId             = $reportId                         ProcessingTimeInMins = $procTimeInMins                      surveyCount          = $surveyCount                    PeakCount            = $peakCount                    Area                 = $area    }

    $script:ReportsFull.set_item($reportId, $objFull)
}

#----------------------------------------------------------
# 4. Create the final list of report to use for perf testing
#----------------------------------------------------------
$script:ReportsFinalFiltered = @{}

function Populate-ReportsFinalFilteredMap($valuesList, $sortPropertyName, $top) {
    $valuesList | Sort-Object -Property "$sortPropertyName" -Descending | Select-Object -First $top | %{
        $obj = $_
        $reportId = $obj.ReportId

        $objFull = $script:ReportsFull.get_item($reportId)

        if (!$script:ReportsFinalFiltered.ContainsKey($reportId)) {
            $script:ReportsFinalFiltered.Add($reportId, $objFull)

            $procTimeInMins = $objFull.ProcessingTimeInMins
            $surveyCount = $objFull.SurveyCount
            $peakCount = $objFull.PeakCount
            $area =$objFull.Area

            Add-Content $OUTFILE_FILTERED "$reportId,$procTimeInMins,$surveyCount,$peakCount,$area"
        }
    }
}

Add-Content $OUTFILE_FILTERED "ReportId,ProcessingTimeInMins,SurveyCount,PeakCount,Area"
Populate-ReportsFinalFilteredMap -valuesList $script:processingTimeInMSecList -sortPropertyName "ProcessingTimeInMins" -top 20
Populate-ReportsFinalFilteredMap -valuesList $script:SurveyCountList -sortPropertyName "SurveyCount" -top 20
Populate-ReportsFinalFilteredMap -valuesList $script:PeakCountList -sortPropertyName "PeakCount" -top 20
Populate-ReportsFinalFilteredMap -valuesList $script:AreaList -sortPropertyName "Area" -top 20

#----------------------------------------------------------
# Display result files
#----------------------------------------------------------
ii $OUTFILE
ii $ERRORFILE
ii $LOGFILE
ii $OUTFILE_FILTERED
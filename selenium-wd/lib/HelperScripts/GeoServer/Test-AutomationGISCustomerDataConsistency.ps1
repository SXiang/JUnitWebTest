# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Test-AutomationGISCustomerDataConsistency.ps1 -BaseScriptFolder "C:\Repositories\surveyor-qa"  `
#                        -DatabaseIP "20.20.130.210"  `
#                        -DatabaseName "SurveyorSQAAuto_blankDB_20170830"  `
#                        -DatabaseUser "awssa"  `
#                        -DatabasePwd "<password>"  `
#                        -geoserverBaseUrl "http://20.20.152.180:8080"  `
#                        -geoserverUsername "admin"  `
#                        -geoserverPassword "<password>"  `
#                        -outputFolder "C:\temp"
# ---------------------------------------------------------------
param
(
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
  [String] $geoserverBaseUrl,

  [Parameter(Mandatory=$true)]
  [String] $geoserverUsername,

  [Parameter(Mandatory=$true)]
  [String] $geoserverPassword,

  [Parameter(Mandatory=$true)]
  [String] $outputFolder
)

# Tests to execute.
$IT_SHOULD_FIND_MATCHING_CUSTOMER_MATERIAL_TYPEIDS_IN_GEOSERVER_AND_AUTOMATIONDB = $true
$IT_SHOULD_FIND_MATCHING_CUSTOMER_BOUNDARY_TYPEIDS_IN_GEOSERVER_AND_AUTOMATIONDB = $true

$script:CustomerAssetInfoMap = @{}
$script:CustomerBoundaryInfoMap = @{}

$script:CustomerAssetTypeIdMap = @{}
$script:CustomerBoundaryTypeIdMap = @{}

$OUTRESULT = New-Item "$outputFolder\testresults.txt" -Force

$ConnString = "Server=$DatabaseIP;Database=$DatabaseName;User Id=$DatabaseUser;Password=$DatabasePwd;"

. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"
. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"
. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\GeoServer\Common-Functions.ps1"

function Write-Result($result, $message) {
    Write-ToOutput -line "[$result] : $message"
}

function Write-ToOutput($line) {
    Write-Host $line
    Add-Content $OUTRESULT $line
}

$csvFile = "$BaseScriptFolder\selenium-wd\lib\HelperScripts\GeoServer\CustomersImportedToGeoServer.csv"
$csvData = Import-Csv $csvFile 
$csvData | % {
    $row = $_
    $customerId = $row.CustomerId
    $workspaceName = $row.WorkspaceName
    Build-GISAssetBoundaryMappingList -map $script:CustomerAssetInfoMap -geoserverUsername $geoserverUsername -geoserverPassword $geoserverPassword -customerId $customerId -workspaceName $workspaceName -gisType $ASSET_FEATURE_CLASSNAME
    Build-GISAssetBoundaryMappingList -map $script:CustomerBoundaryInfoMap -geoserverUsername $geoserverUsername -geoserverPassword $geoserverPassword -customerId $customerId -workspaceName $workspaceName -gisType $BOUNDARY_FEATURE_CLASSNAME

    Build-CustomerAssetTypeIdMap -assetInfoMap $script:CustomerAssetInfoMap -assetTypeIdMap $script:CustomerAssetTypeIdMap -customerId $customerId
    Build-CustomerBoundaryTypeIdMap -boundaryInfoMap $script:CustomerBoundaryInfoMap -boundaryTypeIdMap $script:CustomerBoundaryTypeIdMap -customerId $customerId
}

### TEST 1: Customer material type ids in geoserver and automation DB should match.
if ($IT_SHOULD_FIND_MATCHING_CUSTOMER_MATERIAL_TYPEIDS_IN_GEOSERVER_AND_AUTOMATIONDB) {
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line "Executing TEST 1 - CustomerMaterialType Ids in Geoserver and automation DB should match"
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line ""

    $script:CustomerAssetTypeIdMap.Keys | %{
        $key = $_
        $cmtIdList = $script:CustomerAssetTypeIdMap.get_item($key)
        $custId = $key
        $inClause = ""
        $j = 0
        $cmtIdList | %{
            $cId = $_
            if ($j -eq 0) {
                $inClause += "'$cId'"
            } else {
                $inClause += ",'$cId'"
            }

            $j++
        }
        
        $cmtQuery = "SELECT * FROM [$DatabaseName].[dbo].[CustomerMaterialType] WHERE CustomerId='$custId' AND ID NOT IN ($inclause)"

        # for Picarro and SQACus customers skip 'Cast Iron' and 'Other Plastic' from customer material types.
        if (($custId -eq "B1252204-04FB-4A67-82D4-3F4666FD855C") -or ($custId -eq "00000000-0000-0000-0000-000000000002")) {
            $cmtQuery += " AND [Description] NOT IN ('Cast Iron', 'Other Plastic')"
        }        

        Write-Host "`$cmtQuery -> $cmtQuery"
        $objCMTs = Get-DatabaseData -connectionString $ConnString -query $cmtQuery -isSQLServer:$true

        Write-ToOutput -line "Verifying CustomerMaterialType Ids for customer - $custId"

        $i = 0
        $objCMTs | % {
            if ($i -gt 0) {
                $objCMT = $_
                $CMTId  = $objCMT.Id; 
                $CustomerId  = $objCMT.CustomerId; 
                $Description  = $objCMT.Description; 
                $Color  = $objCMT.Color; 
                $LineWeight  = $objCMT.LineWeight; 
                $IsDotted  = Bool-ToBit -value $objCMT.IsDotted; 

                Write-Result -result "FAIL" -message "NOT found in GeoServer -> [CMTId=$CMTId], [Description=$Description], [Query=$cmtQuery] "
            }

            $i++
        }

        if ($i -le 1) {
            Write-Result -result "PASS" -message "All CustomerMaterialType Ids for customer - $custId found in GeoServer"
        } 
    }
}

### TEST 2: Customer Boundary Type ids in geoserver and automation DB should match
if ($IT_SHOULD_FIND_MATCHING_CUSTOMER_BOUNDARY_TYPEIDS_IN_GEOSERVER_AND_AUTOMATIONDB) {
    Write-ToOutput -line ""
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line "Executing TEST 2 - CustomerBoundaryType Ids in Geoserver and automation DB should match"
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line ""

    $script:CustomerBoundaryTypeIdMap.Keys | %{
        $key = $_
        $cbtIdList = $script:CustomerBoundaryTypeIdMap.get_item($key)
        $custId = $key
        $inClause = ""
        $j = 0
        $cbtIdList | %{
            $cId = $_
            if ($j -eq 0) {
                $inClause += "'$cId'"
            } else {
                $inClause += ",'$cId'"
            }

            $j++
        }
        
        $cbtQuery = "SELECT * FROM [$DatabaseName].[dbo].[CustomerBoundaryType] WHERE CustomerId='$custId' AND ID NOT IN ($inclause)"
        Write-Host "`$cbtQuery -> $cbtQuery"
        $objCBTs = Get-DatabaseData -connectionString $ConnString -query $cbtQuery -isSQLServer:$true

        Write-ToOutput -line "Verifying CustomerBoundaryType Ids for customer - $custId"

        $i = 0
        $objCBTs | % {
            if ($i -gt 0) {
                $objCBT = $_
                $CBTId  = $objCBT.Id; 
                $CustomerId  = $objCBT.CustomerId; 
                $FeatureClassDescription  = $objCBT.FeatureClassDescription; 
                $Color  = $objCBT.Color; 
                $LineWeight  = $objCBT.LineWeight; 
                $IsDotted  = Bool-ToBit -value $objCBT.IsDotted; 
                $Zoomlevel  = $objCBT.Zoomlevel; 
                $IsReportable  = Bool-ToBit -value $objCBT.IsReportable; 

                Write-Result -result "FAIL" -message "NOT found in GeoServer -> [CBTId=$CBTId], [FeatureClassDescription=$FeatureClassDescription], [Query=$cbtQuery] "
            }

            $i++
        }

        if ($i -le 1) {
            Write-Result -result "PASS" -message "All CustomerBoundaryType Ids for customer - $custId found in GeoServer"
        } 
    }
}

ii $OUTRESULT
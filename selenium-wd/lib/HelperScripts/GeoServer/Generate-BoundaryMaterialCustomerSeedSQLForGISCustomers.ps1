# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Generate-BoundaryMaterialCustomerSeedSQLForGISCustomers -BaseScriptFolder "C:\Repositories\surveyor-qa"  `
#                        -DatabaseIP "20.20.130.210"  `
#                        -DatabaseName "SurveyorSQAAuto_blankDB_20170830"  `
#                        -DatabaseUser "awssa"  `
#                        -DatabasePwd "<password>"  `
#                        -geoserverBaseUrl "http://20.20.152.180:8080"  `
#                        -geoserverUsername "admin"  `
#                        -geoserverPassword "<password>"  `
#                        -outputFolder "C:\temp"
#
# NOTE: Before executing this script ensure CBT Ids and CMT Ids in Geoserver and Automation DB are consistent by executing Test-AutomationGISCustomerDataConsistency.ps1
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

. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"
. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"
. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\GeoServer\Common-Functions.ps1"

$script:CustomerAssetInfoMap = @{}
$script:CustomerBoundaryInfoMap = @{}

$script:CustomerAssetTypeIdMap = @{}
$script:CustomerBoundaryTypeIdMap = @{}

$OUTFILE   = New-Item "$outputFolder\automationDB_GeoServer.sql" -Force
$OUTRESULT = New-Item "$outputFolder\sqlgen.results.txt" -Force

$ConnString = "Server=$DatabaseIP;Database=$DatabaseName;User Id=$DatabaseUser;Password=$DatabasePwd;"
$errorEncountered = $false

# Build list of CBT and CMT ids
$csvFile = "$BaseScriptFolder\selenium-wd\lib\HelperScripts\GeoServer\CustomersImportedToGeoServer.csv"
$csvData = Import-Csv $csvFile 

$csvData | % {
    $row = $_
    $customerId = $row.CustomerId
    $customerName = $row.CustomerName
    Build-GISAssetBoundaryMappingList -map $script:CustomerAssetInfoMap -geoserverUsername $geoserverUsername -geoserverPassword $geoserverPassword -customerId $customerId -customerName $customerName -gisType "Asset"
    Build-GISAssetBoundaryMappingList -map $script:CustomerBoundaryInfoMap -geoserverUsername $geoserverUsername -geoserverPassword $geoserverPassword -customerId $customerId -customerName $customerName -gisType "Boundary"

    Build-CustomerAssetTypeIdMap -assetInfoMap $script:CustomerAssetInfoMap -assetTypeIdMap $script:CustomerAssetTypeIdMap -customerId $customerId
    Build-CustomerBoundaryTypeIdMap -boundaryInfoMap $script:CustomerBoundaryInfoMap -boundaryTypeIdMap $script:CustomerBoundaryTypeIdMap -customerId $customerId
}

add-content $OUTFILE ""

# For all CBT and CMT Ids seen in GeoServer build SQL queries.
$csvData | % {
    $row = $_
    [string]$customerId = $row.CustomerId
    $customerId = $customerId.toUpper()
    $customerName = $row.CustomerName

    Write-Host "Generating CustomerBoundaryType query for [$customerName] ..."

    add-content $OUTFILE "-------------------------------------------------------------------------------------"
    add-content $OUTFILE "-- Customer '$customerName' "
    add-content $OUTFILE "-------------------------------------------------------------------------------------"
    add-content $OUTFILE ""
    add-content $OUTFILE "SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='$customerName'"
    add-content $OUTFILE ""

    # --- generate CBT queries
    add-content $OUTFILE "-- CustomerBoundaryType - [$customerName]"
    add-content $OUTFILE ""

    $cbtIdList = $script:CustomerBoundaryTypeIdMap.get_item($customerId)
    $cbtIdList | %{
        $cId = $_
        $cbtQuery = "SELECT * FROM [$DatabaseName].[dbo].[CustomerBoundaryType] WHERE Id='$cId'"
        $objCBTs = Get-DatabaseData -connectionString $ConnString -query $cbtQuery -isSQLServer:$true

        $i = 0
        $objCBTs | % {
            if ($i -gt 0) {
                $objCBT = $_
                $CBTId  = $objCBT.Id; 
                $CBTId = $CBTId.toString().toUpper()
                $CustomerId  = $objCBT.CustomerId; 
                $FeatureClassDescription  = $objCBT.FeatureClassDescription; 
                $Color  = $objCBT.Color; 
                $LineWeight  = $objCBT.LineWeight; 
                $IsDotted  = Bool-ToBit -value $objCBT.IsDotted; 
                $Zoomlevel  = $objCBT.Zoomlevel; 
                $IsReportable  = Bool-ToBit -value $objCBT.IsReportable; 

                add-content $OUTFILE "UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'$FeatureClassDescription', [Color]=N'$Color', [LineWeight]=$LineWeight, [IsDotted]=$IsDotted, [Zoomlevel]=$Zoomlevel, [IsReportable]=$IsReportable WHERE [Id]=N'$CBTId'"
                add-content $OUTFILE "IF @@ROWCOUNT=0"
                add-content $OUTFILE "	INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'$CBTId', @customerId, N'$FeatureClassDescription', N'$Color', $LineWeight, $IsDotted, $Zoomlevel, $IsReportable)"
                add-content $OUTFILE ""
            }

            $i++
        }

        if ($i -le 1) {
            $errorEncountered = $true
            add-content $OUTRESULT "[ERROR] : Not found in DB. CustomerBoundaryType ID = $cId"
        }
    }

    Write-Host "Generating CustomerMaterialType query for [$customerName] ..."

    # -- generate CMT queries
    add-content $OUTFILE "-- CustomerMaterialType - [$customerName]"
    add-content $OUTFILE ""

    $cmtIdList = $script:CustomerAssetTypeIdMap.get_item($customerId)
    $cmtIdList | %{
        $cId = $_
        $cmtQuery = "SELECT * FROM [$DatabaseName].[dbo].[CustomerMaterialType] WHERE Id='$cId'"
        $objCMTs = Get-DatabaseData -connectionString $ConnString -query $cmtQuery -isSQLServer:$true

        $i = 0
        $objCMTs | % {
            if ($i -gt 0) {
                $objCMT = $_
                $CMTId  = $objCMT.Id; 
                $CMTId = $CMTId.toString().toUpper()
                $CustomerId  = $objCMT.CustomerId; 
                $Description  = $objCMT.Description; 
                $Color  = $objCMT.Color; 
                $LineWeight  = $objCMT.LineWeight; 
                $IsDotted  = Bool-ToBit -value $objCMT.IsDotted; 

                add-content $OUTFILE "UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='$Description', [Color]=N'$Color', [LineWeight]=$LineWeight, [IsDotted]=$IsDotted WHERE [Id]='$CMTId' "
                add-content $OUTFILE "IF @@ROWCOUNT=0"
                add-content $OUTFILE "	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'$CMTId', @customerId, N'$Description', N'$Color', $LineWeight, $IsDotted)"
                add-content $OUTFILE ""
            }

            $i++
        }

        if ($i -le 1) {
            $errorEncountered = $true
            add-content $OUTRESULT "[ERROR] : Not found in DB. CustomerMaterialType ID = $cId"
        }
    }
}

add-content $OUTFILE ""

if (-not $errorEncountered) {
    Add-Content $OUTRESULT "[All Tests Passed] No errors encountered."
}

ii $OUTFILE
ii $OUTRESULT
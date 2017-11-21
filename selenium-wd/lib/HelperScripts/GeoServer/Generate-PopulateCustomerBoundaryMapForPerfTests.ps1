# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Generate-PopulateCustomerBoundaryMapForPerfTests.ps1 -BaseScriptFolder "C:\Repositories\surveyor-qa"  `
#                        -InputFileWithCustBoundaryDescriptions "C:\temp\perfCustBoundaryDescriptions.txt"  `
#                        -customerName "Centerpoint"  `
#                        -customerWorkspaceName "CNP"  `
#                        -customerBoundaryFeatureClassDescription "Leak Survey Area"  `
#                        -DatabaseIP "30.30.124.110"  `
#                        -DatabaseName "SurveyorScale"  `
#                        -DatabaseUser "spulikkal"  `
#                        -DatabasePwd "<password>"  `
#                        -geoserverBaseUrl "http://30.30.150.198:8080"  `
#                        -geoserverUsername "<username>"  `
#                        -geoserverPassword "<password>"  `
#                        -outputFolder "C:\temp"
# ---------------------------------------------------------------

# Notes:
# 1. Currently script ONLY supports generating source for same CBT FeatureClassDescription. 
#    All BoundaryDescriptions in InputFile should belong to same FeatureClassDescription.
# 2. Sample input file content for 'InputFileWithCustBoundaryDescriptions' :
#      LeakSurveyMap-O31440728
#      LeakSurveyMap-O31320720

param
(
  [Parameter(Mandatory=$true)]
  [String] $BaseScriptFolder,

  [Parameter(Mandatory=$true)]
  [String] $InputFileWithCustBoundaryDescriptions,

  [Parameter(Mandatory=$true)]
  [String] $CustomerName,

  [Parameter(Mandatory=$true)]
  [String] $CustomerWorkspaceName,

  [Parameter(Mandatory=$true)]
  [String] $CustomerBoundaryFeatureClassDescription,

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

. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"
. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"
. "$BaseScriptFolder\selenium-wd\lib\HelperScripts\GeoServer\Common-Functions.ps1"

$script:CustomerBoundaryInfoMap = @{}

$OUTFILE   = New-Item "$OutputFolder\populateCustomerBoundary.txt" -Force
$OUTRESULT = New-Item "$OutputFolder\codegen.results.txt" -Force

$errorEncountered = $false
$ConnString = "Server=$DatabaseIP;Database=$DatabaseName;User Id=$DatabaseUser;Password=$DatabasePwd;"

# 1. Fetch customerId from DB.
$custQuery = "SELECT * FROM [$DatabaseName].[dbo].[Customer] WHERE Name='$CustomerName'"
$objCust = Get-DatabaseData -connectionString $ConnString -query $custQuery -isSQLServer:$true

$i = 0
$objCust | % {
    if ($i -gt 0) {
        $obj = $_
        $customerId  = $obj.Id; 
    }

    $i++
}

# 2. Build CustomerBoundaryType mapping list.
Build-GISAssetBoundaryMappingList -map $script:CustomerBoundaryInfoMap -geoserverUsername $GeoserverUsername -geoserverPassword $GeoserverPassword -customerId $customerId -customerName $CustomerWorkspaceName -gisType "Boundary"

# 3. Generate source code for map (run check to ensure duplicate descriptions are NOT found).
gc $InputFileWithCustBoundaryDescriptions | % {
    $expectedDescription = $_

    $idx = 0
    $foundObj = $null
    $script:CustomerBoundaryInfoMap.Keys | %{
        $idx++ 
        $key = $_
        $objList = $script:CustomerBoundaryInfoMap.get_item($key)
        $objList | % {
            $obj = $_
            $cbId = $obj.Id
            $ExternalId = $obj.ExternalId
            $CustomerBoundaryTypeId = $obj.CustomerBoundaryTypeId
            $Description = $obj.Description
            if ($Description -eq $expectedDescription) {
                if ($foundObj -ne $null) { 
                    Write-Host "[ERROR]: Found 2 CBT descriptions for $expectedDescription"
                    Add-Content $OUTRESULT "[ERROR]: Found 2 CBT descriptions for $expectedDescription"
                } else {
                    $foundObj = $obj
                    Write-Host "[PASS]: Found CBT description for $expectedDescription"
                    Add-Content $OUTRESULT "[PASS]: Found CBT description for $expectedDescription"
                    Add-Content $OUTFILE "		ComplexBoundaryNames.add(""$CustomerBoundaryFeatureClassDescription|${Description}|$ExternalId"");"
                }
            }
        }
    }
}

ii $OUTRESULT
ii $OUTFILE

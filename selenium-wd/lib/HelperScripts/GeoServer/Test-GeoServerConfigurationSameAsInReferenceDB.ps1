# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Test-GeoServerConfigurationSameAsInReferenceDB.ps1 -BaseScriptFolder "C:\Repositories\surveyor-qa"  `
#                        -DatabaseIP "10.0.0.1"  `
#                        -DatabaseName "Surveyor_20170830"  `
#                        -DatabaseUser "awssa"  `
#                        -DatabasePwd "<password>"  `
#                        -ReferenceDatabaseIP "20.20.130.210"  `
#                        -ReferenceDatabaseName "SurveyorSQAAuto_blankDB_20171108"  `
#                        -ReferenceDatabaseUser "awssa"  `
#                        -ReferenceDatabasePwd "<password>"
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
  [String] $ReferenceDatabaseIP,

  [Parameter(Mandatory=$true)]
  [String] $ReferenceDatabaseName,

  [Parameter(Mandatory=$true)]
  [String] $ReferenceDatabaseUser,

  [Parameter(Mandatory=$true)]
  [String] $ReferenceDatabasePwd
)

# Tests to execute.
$IT_SHOULD_FIND_SAME_GEOSERVERCONFIGURATION_AS_PRESENT_IN_REFERENCE_DB = $true

$script:GeoServerDataInDB = @{}
$script:GeoServerDataInReferenceDB = @{}

$OUTRESULT = New-Item "$outputFolder\testresults.txt" -Force

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

# Fill config hashmap
function Fill-GeoConfigMap($map, $key, $data) 
{
    if (-not $map.ContainsKey($key)) {
        $configList = New-Object System.Collections.ArrayList
        $null = $configList.Add($data)
        $map.Add($key, $configList)
    } else {
        $configList = [System.Collections.ArrayList]$map.Get_Item($key)
        if (-not $configList.Contains($data)) {
            $null = $configList.Add($data)
        }
        $map.Set_Item($key, $configList)
    }        
}

function Populate-GeoConfigMaps($map, $DBName, $connectionString) {
    $geoQuery = "SELECT [Id],[CustomerId],[GeoServerURL],[WorkSpaceName],[FeatureClassName],[Description],[IsActive],[IsCustomerData],[DataExtractDate],[APIVersion],[UserName],[Password] FROM [$DBName].[dbo].[GeoServerConfiguration]"
    Write-Host "Executing `$geoQuery -> $geoQuery"
    $objGeoConfigs = Get-DatabaseData -connectionString $connectionString -query $geoQuery -isSQLServer:$true

    $i = 0
    $objGeoConfigs | % {
        if ($i -gt 0) {
            $obj = $_
            $Id = $obj.Id
            $CustomerId = $obj.CustomerId
            $GeoServerURL = $obj.GeoServerURL
            $WorkSpaceName = $obj.WorkSpaceName
            $FeatureClassName = $obj.FeatureClassName
            $Description = $obj.Description
            $IsActive = $obj.IsActive
            $IsCustomerData = $obj.IsCustomerData
            $DataExtractDate = $obj.DataExtractDate
            $APIVersion = $obj.APIVersion
            $UserName = $obj.UserName
            $Password = $obj.Password

            $Object = New-Object PSObject -Property @{                                Id                = $Id                                     CustomerId        = $CustomerId                                  GeoServerURL      = $GeoServerURL                                WorkSpaceName     = $WorkSpaceName                                FeatureClassName  = $FeatureClassName                                Description       = $Description                                IsActive          = $IsActive                                IsCustomerData    = $IsCustomerData                                DataExtractDate   = $DataExtractDate                                APIVersion        = $APIVersion                                UserName          = $UserName                                Password          = $Password                            } 

            $key = "${WorkSpaceName}:${$FeatureClassName}"
            Fill-GeoConfigMap -map $map -key $key -data $Object
        }

        $i++
    }
}

function Object-Equals($obj1, $obj2) {
    $retVal = $true    
   if ($obj1.Id -ne $obj2.Id) {
        Write-ToOutput "IDs did NOT match. [1] Id=$($obj1.Id), [2] Id=$($obj2.Id)"
        $retVal = $false
    } elseif ($obj1.CustomerId -ne $obj2.CustomerId) {
        $retVal = $false
        Write-ToOutput "CustomerIds did NOT match. [1] CustomerId=$($obj1.CustomerId), [2] CustomerId=$($obj2.CustomerId)"
    } elseif ($obj1.GeoServerURL -ne $obj2.GeoServerURL) {
        $retVal = $false
        Write-ToOutput "GeoServerURLs did NOT match. [1] GeoServerURL=$($obj1.GeoServerURL), [2] GeoServerURL=$($obj2.GeoServerURL)"
    } elseif ($obj1.WorkSpaceName -ne $obj2.WorkSpaceName) {
        $retVal = $false
        Write-ToOutput "WorkSpaceNames did NOT match. [1] WorkSpaceName=$($obj1.WorkSpaceName), [2] WorkSpaceName=$($obj2.WorkSpaceName)"
    } elseif ($obj1.FeatureClassNames -ne $obj2.FeatureClassNames) {
        $retVal = $false
        Write-ToOutput "FeatureClassNames did NOT match. [1] FeatureClassName=$($obj1.FeatureClassName), [2] FeatureClassName=$($obj2.FeatureClassName)"
    } elseif ($obj1.Description -ne $obj2.Description) {
        $retVal = $false
        Write-ToOutput "Descriptions did NOT match. [1] Description=$($obj1.Description), [2] Description=$($obj2.Description)"
    } elseif ($obj1.IsActive -ne $obj2.IsActive) {
        $retVal = $false
        Write-ToOutput "IsActives did NOT match. [1] IsActive=$($obj1.IsActive), [2] IsActive=$($obj2.IsActive)"
    } elseif ($obj1.IsCustomerData -ne $obj2.IsCustomerData) {
        $retVal = $false
        Write-ToOutput "IsCustomerDatas did NOT match. [1] IsCustomerData=$($obj1.IsCustomerData), [2] IsCustomerData=$($obj2.IsCustomerData)"
    } elseif ($obj1.DataExtractDate -ne $obj2.DataExtractDate) {
        $retVal = $false
        Write-ToOutput "DataExtractDates did NOT match. [1] DataExtractDate=$($obj1.DataExtractDate), [2] DataExtractDate=$($obj2.DataExtractDate)"
    } elseif ($obj1.APIVersion -ne $obj2.APIVersion) {
        $retVal = $false
        Write-ToOutput "APIVersions did NOT match. [1] APIVersion=$($obj1.APIVersion), [2] APIVersion=$($obj2.APIVersion)"
    } elseif ($obj1.UserName -ne $obj2.UserName) {
        $retVal = $false
        Write-ToOutput "UserNames did NOT match. [1] UserName=$($obj1.UserName), [2] UserName=$($obj2.UserName)"
    } elseif ($obj1.Password -ne $obj2.Password) {
        $retVal = $false
        Write-ToOutput "Passwords did NOT match. [1] Password=$($obj1.Password), [2] Password=$($obj2.Password)"
    }


    $retVal 
}

$ConnString = "Server=$DatabaseIP;Database=$DatabaseName;User Id=$DatabaseUser;Password=$DatabasePwd;"
$RefConnString = "Server=$ReferenceDatabaseIP;Database=$ReferenceDatabaseName;User Id=$ReferenceDatabaseUser;Password=$ReferenceDatabasePwd;"

### TEST 1: GeoserverConfiguration data should match.
if ($IT_SHOULD_FIND_SAME_GEOSERVERCONFIGURATION_AS_PRESENT_IN_REFERENCE_DB) {
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line "Executing TEST 1 - GeoserverConfiguration data in '$DatabaseIP -> $DatabaseName' should match data found in '$ReferenceDatabaseIP -> $ReferenceDatabaseName'"
    Write-ToOutput -line "----------------------------------------------------------------------------------------"
    Write-ToOutput -line ""

    Write-Host "Populating Map from $DatabaseName DB. Connection string -> $ConnString"
    Populate-GeoConfigMaps -map $script:GeoServerDataInDB -DBName $DatabaseName -connectionString $ConnString

    Write-Host "Populating Map from $ReferenceDatabaseName DB. Connection string -> $RefConnString"
    Populate-GeoConfigMaps -map $script:GeoServerDataInReferenceDB -DBName $ReferenceDatabaseName -connectionString $RefConnString

    if ($script:GeoServerDataInDB.Keys.Count -ne $script:GeoServerDataInReferenceDB.Keys.Count) {
        Write-Result -result "FAIL" -message "All CustomerMaterialType Ids for customer - $custId found in GeoServer"
        Write-Host " `$script:GeoServerDataInDB values -> "
        Print-MapValues -map $script:GeoServerDataInDB
        Write-Host " `$script:GeoServerDataInReferenceDB values -> "
        Print-MapValues -map $script:GeoServerDataInReferenceDB
    }

    $script:GeoServerDataInDB.Keys | Sort-Object | %{
        $key = $_
        $configValueList = $script:GeoServerDataInDB.get_item($key)
        $configValue = $configValueList[0]
        $refConfigValueList = $script:GeoServerDataInReferenceDB.get_item($key)
        $refConfigValue = ""
        if ($refConfigValueList -ne $null) {
            $refConfigValue = $refConfigValueList[0]
        }

        $ret = Object-Equals -obj1 $configValue -obj2 $refConfigValue
        if ($ret) {
            Write-Result -result "PASS" -message "[$key] match in '$DatabaseName ' and '$ReferenceDatabaseName'"
        } else {
            Write-Result -result "FAIL" -message "[$key] did NOT match in '$DatabaseName ' and '$ReferenceDatabaseName'"
        }
    }
}

ii $OUTRESULT
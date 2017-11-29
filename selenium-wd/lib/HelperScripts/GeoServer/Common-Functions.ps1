$geoserverBaseUrl="http://20.20.152.180:8080"
$script:EndPoint = "$geoserverBaseUrl/geoserver/%WORKSPACE%/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=%WORKSPACE%:%GISTYPE%&maxFeatures=%MAXFEATURECOUNT%&outputFormat=application%2Fjson"

$script:DefaultMaxFeatures = 10000
$script:FeaturesMaxLimit   = 50000

$ASSET_FEATURE_CLASSNAME = "Asset"
$BOUNDARY_FEATURE_CLASSNAME = "Boundary"

# use for debug print.
function Print-MapValues($map) {
    $map.Keys | %{
        $k = $_
        Write-Host "Found key = [$k]"
        $val = $map.get_item($k)
        $val | %{
            $v = $_
            Write-Host "Found value = [$v]"
        }
    }
}

function AddTo-CustomerGisMapTable($map, $customerID, $propertiesObject) 
{
    if (-not $map.ContainsKey($customerID)) {
        $propObjList = New-Object System.Collections.ArrayList
        $null = $propObjList.Add($propertiesObject)
        $null = $map.Add($customerID, $propObjList)
    } else {
        $propObjList = [System.Collections.ArrayList]$map.Get_Item($customerID)
        if (-not $propObjList.Contains($propertiesObject)) {
            $null = $propObjList.Add($propertiesObject)
        }
        $map.Set_Item($customerID, $propObjList)
    }        
}

function Build-CustomerAssetTypeIdMap($assetInfoMap, $assetTypeIdMap, $customerId) {
    $assetInfoMap.get_item($customerId) | %{
        $obj = $_
        $CustomerMaterialTypeId = $obj.CustomerMaterialTypeId
        AddTo-CustomerGisMapTable -map $assetTypeIdMap -customerID $customerID -propertiesObject $CustomerMaterialTypeId
    }
}

function Build-CustomerBoundaryTypeIdMap($boundaryInfoMap, $boundaryTypeIdMap, $customerId) {
    $boundaryInfoMap.get_item($customerId) | %{
        $obj = $_
        $CustomerBoundaryTypeId = $obj.CustomerBoundaryTypeId
        AddTo-CustomerGisMapTable -map $boundaryTypeIdMap -customerID $customerID -propertiesObject $CustomerBoundaryTypeId
    }
}

function Build-GISAssetBoundaryMappingList($map, $geoserverUsername, $geoserverPassword, $customerId, $workspaceName, $gisType) {
    $geoServerUrl = $script:EndPoint.Replace("%WORKSPACE%", $workspaceName)
    $geoServerUrl = $geoServerUrl.Replace("%GISTYPE%", $gisType)
    $geoServerUrl = $geoServerUrl.Replace("%MAXFEATURECOUNT%", "${script:DefaultMaxFeatures}")

    $loginURL = "$geoserverBaseUrl/geoserver/web/"
    $loginResponse = Invoke-WebRequest $loginURL -SessionVariable wSession
    $html = $loginResponse.parsedHTML
    $form = $loginResponse.forms[0]
    $form.Fields.Username = $geoserverUsername
    $form.Fields.Password = $geoserverPassword
    $loginResponse = Invoke-WebRequest -Uri $loginURL -WebSession $wSession -Method POST -Body $form.Fields

    Write-Host "Invoking request -> $geoServerUrl "

    $responseString = Invoke-WebRequest -Uri $geoServerUrl -WebSession $wSession -Method GET 
    $objResponse = ConvertFrom-Json $responseString
    
    Write-Host "Got response -> $objResponse "

    [Int64]$totalFeatures = $objResponse.totalFeatures
    if ($totalFeatures -le $script:DefaultMaxFeatures) {
        Build-MapFromResponse -map $map -response $objResponse -customerId $customerId -gisType $gisType
                
    } else {
        Write-Host "Customer has more features [total=$totalFeatures] than the default requested feature count of ${script:DefaultMaxFeatures}.. Fetching all features..."
        if ($totalFeatures -gt $script:FeaturesMaxLimit) {
            Write-Host "[WARNING] Found $totalFeatures features which is MORE than max limit of ${script:FeaturesMaxLimit} set in script. Capping to max limit of ${script:FeaturesMaxLimit}"
            $totalFeatures = $script:FeaturesMaxLimit
        }
    
        Build-GISAssetBoundaryMappingListWithMax -map $map -geoserverUsername $geoserverUsername -geoserverPassword $geoserverPassword -customerId $customerId -workspaceName $workspaceName -gisType $gisType -maxFeatures $totalFeatures
    }
}

function Build-GISAssetBoundaryMappingListWithMax($map, $geoserverUsername, $geoserverPassword, $customerId, $workspaceName, $gisType, $maxFeatures) {
    $webClient = New-Object System.Net.WebClient
    $networkCreds = New-Object System.Net.NetworkCredential($geoserverUsername, $geoserverPassword)
    $webClient.Credentials = $networkCreds
    $geoServerUrl = $script:EndPoint.Replace("%WORKSPACE%", $workspaceName).Replace("%GISTYPE%", $gisType).Replace("%MAXFEATURECOUNT%", "$maxFeatures")
    
    Write-Host "Invoking request -> $geoServerUrl "
    
    $responseString = $webClient.DownloadString($geoServerUrl)
    $objResponse = ConvertFrom-Json $responseString
    
    Write-Host "Got response -> $objResponse "

    Build-MapFromResponse -map $map -response $objResponse -customerId $customerId -gisType $gisType
}

function Build-MapFromResponse($map, $response, $customerId, $gisType) {
    $features = $response.features
    $features | %{
        $f = $_

        if ($gisType -eq $BOUNDARY_FEATURE_CLASSNAME) {
            $BId = $f.properties.Id
            $CBTId = $f.properties.CustomerBo
            $CBTId = $CBTId.Replace("{", "").Replace("}", "")
            $ExternalId = $f.properties.ExternalId
            $Description = $f.properties.Descriptio
            $Object = New-Object PSObject -Property @{            
                            Id               = $BId                 
                            ExternalId       = $ExternalId              
                            CustomerBoundaryTypeId = $CBTId            
                            Description      = $Description
                        }  

            AddTo-CustomerGisMapTable -map $map -customerID $customerId -propertiesObject $Object
        
        } elseif ($gisType -eq $ASSET_FEATURE_CLASSNAME) {
            $AId = $f.properties.Id
            $CMTId = $f.properties.CustomerMa
            $CMTId = $CMTId.Replace("{", "").Replace("}", "")    
            $ExternalId = $f.properties.ExternalId
            $AssetTypeId = $f.properties.AssetTypeI
            $Object = New-Object PSObject -Property @{            
                            Id               = $AId                 
                            ExternalId       = $ExternalId              
                            AssetTypeId      = $AssetTypeId            
                            CustomerMaterialTypeId = $CMTId
                        }  
            
            AddTo-CustomerGisMapTable -map $map -customerID $customerId -propertiesObject $Object
        }

    }
}
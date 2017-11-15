$script:EndPoint = "$geoserverBaseUrl/geoserver/%WORKSPACE%/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=%WORKSPACE%:%GISTYPE%&maxFeatures=50&outputFormat=application%2Fjson"

function AddTo-CustomerGisMapTable($map, $customerID, $cbmtId) 
{
    if (-not $map.ContainsKey($customerID)) {
        $cbmtIdList = New-Object System.Collections.ArrayList
        $null = $cbmtIdList.Add($cbmtId)
        $null = $map.Add($customerID, $cbmtIdList)
    } else {
        $cbmtIdList = [System.Collections.ArrayList]$map.Get_Item($customerID)
        if (-not $cbmtIdList.Contains($cbmtId)) {
            $null = $cbmtIdList.Add($cbmtId)
        }
        $map.Set_Item($customerID, $cbmtIdList)
    }        
}

function Build-GISAssetBoundaryMappingList($map, $geoserverUsername, $geoserverPassword, $customerId, $customerName, $gisType) {
    $webClient = New-Object System.Net.WebClient
    $networkCreds = New-Object System.Net.NetworkCredential($geoserverUsername, $geoserverPassword)
    $webClient.Credentials = $networkCreds
    $workspace  = $customerName
    $geoServerUrl = $script:EndPoint.Replace("%WORKSPACE%", $workspace).Replace("%GISTYPE%", $gisType)
    
    Write-Host "Invoking request -> $geoServerUrl "
    
    $responseString = $webClient.DownloadString($geoServerUrl)
    $objResponse = ConvertFrom-Json $responseString
    
    Write-Host "Got response -> $objResponse "

    $features = $objResponse.features
    $features | %{
        $f = $_

        if ($gisType -eq "Boundary") {
            $BId = $f.properties.Id
            $CBTId = $f.properties.CustomerBo
            $CBTId = $CBTId.Replace("{", "").Replace("}", "")    
            AddTo-CustomerGisMapTable -map $map -customerID $customerId -cbmtId $CBTId
        
        } elseif ($gisType -eq "Asset") {
            $BId = $f.properties.Id
            $CMTId = $f.properties.CustomerMa
            $CMTId = $CMTId.Replace("{", "").Replace("}", "")    
            AddTo-CustomerGisMapTable -map $map -customerID $customerId -cbmtId $CMTId
        }

    }
}

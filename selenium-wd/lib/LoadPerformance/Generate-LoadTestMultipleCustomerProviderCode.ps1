$CSVFolder = "C:\Shirish\Automation\LoadTest-ContentLengths"

$RESULTFILE = New-Item "C:\temp\result.txt" -ItemType File -Force
$OUTFILE = New-Item "C:\temp\providerCode.txt" -ItemType File -Force

$script:gisAssetAPICallsMap = @{}        # Customer -> API call data provider parameters
$script:gisBoundaryAPICallsMap = @{}     # Customer -> API call data provider parameters

function AddTo-GisAssetMap($map, $key, $valueObj) 
{
    if (-not $map.ContainsKey($key)) {
        $valueObjList = New-Object System.Collections.ArrayList
        $null = $valueObjList.Add($valueObj)
        $map.Add($key, $valueObjList)
    } else {
        $valueObjList = [System.Collections.ArrayList]$map.Get_Item($key)
        if (-not $valueObjList.Contains($valueObj)) {
            $null = $valueObjList.Add($valueObj)
        }
        $map.Set_Item($key, $valueObjList)
    }        
}

function Generate-HeaderCode() {
    add-content $OUTFILE "package surveyor.dataprovider;"
    add-content $OUTFILE ""
    add-content $OUTFILE "import java.util.Arrays;"
    add-content $OUTFILE "import org.junit.runners.model.InitializationError;"
    add-content $OUTFILE ""
    add-content $OUTFILE "import com.tngtech.java.junit.dataprovider.DataProvider;"
    add-content $OUTFILE ""
    add-content $OUTFILE "import common.source.LoadTestJob;"
    add-content $OUTFILE "import common.source.LoadTestExecutor.HttpMethod;"
    add-content $OUTFILE ""
    add-content $OUTFILE "public class LoadGeoServerAPITestMultipleCustomersDataProvider extends ReportDataProvider {"
    add-content $OUTFILE ""
    add-content $OUTFILE "	public LoadGeoServerAPITestMultipleCustomersDataProvider(Class<?> klass) throws InitializationError {"
    add-content $OUTFILE "		super(klass);"
    add-content $OUTFILE "	}"
    add-content $OUTFILE ""
    add-content $OUTFILE "	public static final String LOAD_TEST_LOW_FREQUENCY_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL = ""dataProviderProdGeoServerMultipleCustomersInParallelAPITest_LowFrequency"";"
    add-content $OUTFILE "	public static final String LOAD_TEST_MEDIUM_FREQUENCY_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL = ""dataProviderProdGeoServerMultipleCustomersInParallelAPITest_MediumFrequency"";"
    add-content $OUTFILE "	public static final String LOAD_TEST_HIGH_FREQUENCY_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL = ""dataProviderProdGeoServerMultipleCustomersInParallelAPITest_HighFrequency"";"
    add-content $OUTFILE ""
}

function Generate-FooterCode() {
    add-content $OUTFILE ""
    add-content $OUTFILE "}"
}

function Generate-DataProviderMethod($methodSuffix, $apiNameSuffix) {
    add-content $OUTFILE "	@DataProvider"
    add-content $OUTFILE "	public static Object[][] dataProviderProdGeoServerMultipleCustomersInParallelAPITest_$methodSuffix() {"
    add-content $OUTFILE ""
    add-content $OUTFILE "		final String contentType = ""application/x-www-form-urlencoded"";"
    add-content $OUTFILE "		final HttpMethod method = HttpMethod.POST;"
    add-content $OUTFILE "		final Integer concurrentRequests = 100;"
    add-content $OUTFILE "		final Integer requestsInOneSession = 10;"
    add-content $OUTFILE "		final Integer numPrimingRuns = 1;"
    add-content $OUTFILE ""

    # generate Asset API calls.
    $counter = 1
    $script:gisAssetAPICallsMap.Keys | % {
        $key = $_
        add-content $OUTFILE "		// Asset API calls for - '$key'"

        $valueList = $script:gisAssetAPICallsMap.get_item($key)
        $valueList | %{
            $obj = $_                        $ApiType = $obj.ApiType            if ($ApiType -eq $apiNameSuffix) {                $ApiURL = $obj.ApiURL                $ResponseContentLength = $obj.ExpectedResponseContentLength                $Username = $obj.Username                $Password = $obj.Password
                add-content $OUTFILE "		final String apiName_$counter = ""GeoServer-MultipleCustomersAPI-${apiNameSuffix}-$counter"";"
                add-content $OUTFILE "		final String apiURL_$counter = ""$ApiURL"";"
                add-content $OUTFILE "		final Integer expectedResponseContentLength_$counter = $ResponseContentLength;"
                add-content $OUTFILE "		final String username_$counter = ""$Username"";"
                add-content $OUTFILE "		final String password_$counter = ""$Password"";"
                add-content $OUTFILE ""

                Write-Host "  generated code for Asset api test -> GeoServer-MultipleCustomersAPI-${apiNameSuffix}-$counter "

                $counter++
            }
        }

        Add-Content $OUTFILE ""
    }

    # generate Boundary API calls.
    $script:gisBoundaryAPICallsMap.Keys | % {
        $key = $_
        add-content $OUTFILE "		// Boundary API calls for - '$key'"

        $valueList = $script:gisBoundaryAPICallsMap.get_item($key)
        $valueList | %{
            $obj = $_
            $ApiType = $obj.ApiType            if ($ApiType -eq $apiNameSuffix) {                $ApiURL = $obj.ApiURL                $ResponseContentLength = $obj.ExpectedResponseContentLength                $Username = $obj.Username                $Password = $obj.Password
                add-content $OUTFILE "		final String apiName_$counter = ""GeoServer-MultipleCustomersAPI-${apiNameSuffix}-$counter"";"
                add-content $OUTFILE "		final String apiURL_$counter = ""$ApiURL"";"
                add-content $OUTFILE "		final Integer expectedResponseContentLength_$counter = $ResponseContentLength;"
                add-content $OUTFILE "		final String username_$counter = ""$Username"";"
                add-content $OUTFILE "		final String password_$counter = ""$Password"";"
                add-content $OUTFILE ""

                Write-Host "  generated code for Boundary api test -> GeoServer-MultipleCustomersAPI-${apiNameSuffix}-$counter "

                $counter++
            }
        }

        Add-Content $OUTFILE ""
    }

    $TIMES = $counter - 1
    $tJobCsvList = ""
    1..$TIMES | %{
        $idx = $_
        add-content $OUTFILE "		LoadTestJob testJob$idx = new LoadTestJob();"
        add-content $OUTFILE "		testJob${idx}.setApiURL(apiURL_$idx)"
        add-content $OUTFILE "				.setConcurrentRequests(concurrentRequests)"
        add-content $OUTFILE "				.setContentType(contentType)"
        add-content $OUTFILE "				.setExpectedResponseContentLength(expectedResponseContentLength_$idx)"
        add-content $OUTFILE "				.setMethod(method)"
        add-content $OUTFILE "				.setNumPrimingRuns(numPrimingRuns)"
        add-content $OUTFILE "				.setUsername(username_$idx)"
        add-content $OUTFILE "				.setPassword(password_$idx)"
        add-content $OUTFILE "				.setRequestsInOneSession(requestsInOneSession)"
        add-content $OUTFILE "				.setTestCaseName(apiName_$idx);"
        add-content $OUTFILE ""

        if ($idx -eq 1) {
            $tJobCsvList += "testJob$idx"
        } else {
            $tJobCsvList += ", testJob$idx"
        } 
    }

    add-content $OUTFILE "		LoadTestJob[] jobs = { $tJobCsvList };"

    add-content $OUTFILE "		return new Object[][] {"
    add-content $OUTFILE "			{ Arrays.asList(jobs) }"
    add-content $OUTFILE "		};"
    add-content $OUTFILE "	}"
    add-content $OUTFILE ""
}

# Loop through CSVs and fill Maps to be used for data generation.
Get-ChildItem $CSVFolder -Filter "*.csv" | %{
    $file = $_
    $filename = $file.Name
    $fileFullPath = $file.FullName

    Write-Host "Processing file -> $fileFullPath ..."

    [Int64] $cLen = 0
    Import-Csv $fileFullPath | %{
        $obj = $_
        $apiname = $obj.apiname
        $contentlength = $obj.contentlength
        $resultfilepath = $obj.resultfilepath
        $username = $obj.username
        $password = $obj.password
        $geoserverurl = $obj.geoserverurl

        # Verification #1

        [Int64]$iContentLen = $contentLength
        
        if (($cLen -ne 0) -and ($cLen -ne $iContentLen)) {
            Add-Content $RESULTFILE "[CONTENT_LEN MISMATCH] Previous = $cLen, Current = $iContentLen"
        }

        $cLen = $iContentLen
    }

    if ($geoserverurl -match "typeName=(\w+:\w+)&cql_filter") {
        $customerGISType = $Matches[1]
    }

    if ($filename -match "\w+\-\w+\-(\w+)") {
        $apiType = $Matches[1]
    }

    $Object = New-Object PSObject -Property @{                    ApiType                       = $apiType        ApiURL                        = $geoserverurl                      ExpectedResponseContentLength = $contentlength        Username                      = $username                    Password                      = $password    }

    if ($customerGISType.Contains("Asset")) {
        AddTo-GisAssetMap -map $script:gisAssetAPICallsMap -key $customerGISType -valueObj $Object
    } else {
        AddTo-GisAssetMap -map $script:gisBoundaryAPICallsMap -key $customerGISType -valueObj $Object
    }
}

# Generate header code
Write-Host "GENERATING header code ..."
Generate-HeaderCode

# Generate LowFrequency test
Write-Host "GENERATING low frequency test code ..."
Generate-DataProviderMethod -methodSuffix "LowFrequency" -apiNameSuffix "Low"

# Generate MediumFrequency test
Write-Host "GENERATING medium frequency test code ..."
Generate-DataProviderMethod -methodSuffix "MediumFrequency" -apiNameSuffix "Medium"

# Generate HighFrequency test
Write-Host "GENERATING high frequency test code ..."
Generate-DataProviderMethod -methodSuffix "HighFrequency" -apiNameSuffix "High"

# Generate footer code
Write-Host "GENERATING footer code ..."

Generate-FooterCode

ii $RESULTFILE
ii $OUTFILE

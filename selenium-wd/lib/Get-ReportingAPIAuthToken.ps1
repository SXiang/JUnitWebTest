# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\GetAPIResponse.ps1 -baseURL "https://p3sqaauto.picarro.com" `
#                        -apiRelativePath "api/Analyzer/Version" `
#                        -loginUser "AutomationAdmin" `
#                        -loginPwd "<password>"     
# ---------------------------------------------------------------
param
(
  #[Parameter(Mandatory=$true)]
  [String] $startEpoch = "1456382592",               # for eg. https://p3sqaauto.picarro.com

  #[Parameter(Mandatory=$true)]
  [String] $endEpoch = "1456856576",                 # for eg. api/Analyzer/Version. 
  
  #[Parameter(Mandatory=$true)]
  [String] $environment = "SQAAuto",              # This is web app login username

  #[Parameter(Mandatory=$true)]
  [String] $outFile                # This is web app login password
)

$username = "api"
$password = "AP1_Us3r@#"    # TODO: Use Encrypted hash instead and decrypt using utility
$baseUrl = "http://localhost:63087"


$pathToReportingUtilDLL = "C:\Repositories\tools\automation_reporting\AutomationReporting.Util\bin\Release\AutomationReporting.Util.dll"

[Reflection.Assembly]::LoadFile($pathToReportingUtilDLL) | out-null
$cryptoString = [AutomationReporting.Util.CryptoFunctions]::CreateExpiringUserPassCryptoString($username, $password)

$authenticationTokenUrl = "get/token"
$basicAuthValue = "Basic $cryptoString"
$Headers = @{
    Authorization = $basicAuthValue
}

# Get the authentication token
$response = Invoke-WebRequest -Uri "$baseUrl/$authenticationTokenUrl" -Headers $Headers -Method POST
$authToken = $response.Headers["Token"]

$runResultApiUrl = "api/RunResults?environment=$environment&startEpoch=$startEpoch&endEpoch=$endEpoch"
$Headers = @{
    Token = $authToken
}

$response = Invoke-WebRequest -Uri "$baseUrl/$runResultApiUrl" -Headers $Headers -Method GET
Write-Host $response

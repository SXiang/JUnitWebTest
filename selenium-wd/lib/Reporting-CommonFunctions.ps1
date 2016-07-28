
# ------------------------------------------------------------
# Converts datetime to Unix epoch time
# ------------------------------------------------------------
function ToUnixTime([System.DateTime] $dateTime) {
    $referenceTime = New-Object System.DateTime -ArgumentList (1970,1,1,0,0,0,0)
    [System.TimeSpan]$timeSpan = $dateTime - $referenceTime;
    $timeSpan.TotalSeconds
}

# ----------------------------------------------------------------------
# Gets the authorization token for Automation Reporting API web app
# ----------------------------------------------------------------------
function Get-ReportingAppAuthToken {
    param
    (
    [Parameter(Mandatory=$true)]
    [String] $BuildWorkingDir,                       # Path to working directory (for eg. C:\Repositories\surveyor-qa)

    [Parameter(Mandatory=$true)]
    [String] $AutomationReportingAPIBaseUrl
    )

    $apiUsername = "api"
    $apiPassword = "AP1_Us3r@#"    

    $libFolder = "selenium-wd\lib"
    [String] $AutomationReportingUtilDllPath = "$BuildWorkingDir\$libFolder\AutomationReporting.Util.dll"

    # ------------------------------------------------------------
    # Get the api authentication token
    # ------------------------------------------------------------

    [Reflection.Assembly]::LoadFile($AutomationReportingUtilDllPath) | out-null
    $cryptoString = [AutomationReporting.Util.CryptoFunctions]::CreateExpiringUserPassCryptoString($apiUsername, $apiPassword)
    $authenticationTokenUrl = "get/token"
    $basicAuthValue = "Basic $cryptoString"
    $Headers = @{
        Authorization = $basicAuthValue
    }
    $response = Invoke-WebRequest -Uri "$AutomationReportingAPIBaseUrl/$authenticationTokenUrl" -Headers $Headers -Method POST
    $authToken = $response.Headers["Token"]
    $authToken
}
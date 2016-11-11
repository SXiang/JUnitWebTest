# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\GetGridNodesAvailability.v2.ps1 -gridHost "selenium-grid-hub.qa.aws.picarro.com" `
#                        -gridPort "4444" `
#                        -testRunUUID "testRun1" `
#                        -browser "CHROME" `
#                        -requestedThreadCount "5" `
#                        -OS "WIN8"     
# ---------------------------------------------------------------
param
(
  [Parameter(Mandatory=$true)]
  [String] $gridHost,      # for eg. selenium-grid-hub.qa.aws.picarro.com

  [Parameter(Mandatory=$true)]
  [String] $gridPort = "4444",       # for eg. 4444 

  [Parameter(Mandatory=$true)]
  [String] $testRunUUID,             # Run Unique ID
  
  [Parameter(Mandatory=$true)]
  [String] $requestedThreadCount,    # Number of threads required for the run.

  [Parameter(Mandatory=$false)]
  [String] $browser="CHROME",        # Type of browser. Valid values = "CHROME", "FIREFOX", "IE"

  [Parameter(Mandatory=$false)]
  [String] $os = "WIN8"              # OS Type. Eg. "WIN8", "LINUX"
)


$automationServletURL = "http://${gridHost}:${gridPort}/grid/admin/AutomationTestRunServlet?uuid=${testRunUUID}&threadCount=${requestedThreadCount}&browser=${browser}&os=${os}"

$exception = $null

<# GRID SCALER Status Codes:
   201 -> if the request can be fulfilled but AMIs must be started
   202 -> if the request can be fulfilled with current capacity
   400 -> if the required parameters are not passed in. This error can be thrown if TestRun already exists with the given RunID.
   409 -> if the server is at full node capacity
   500 -> for an unexpected error condition (see log for details) #>
try {
    $statusCode = (invoke-webrequest -uri $automationServletURL -UseBasicParsing -TimeoutSec 30).statuscode
} catch {
    # Ignore exceptions in case where TestRun already exists if statusCode=202
    if ($statusCode -ne 202) {
        $exception = $_.Exception.Message
    }
}
 
if($statusCode -eq 202)
{
    $countOfFreeNodes = $requestedThreadCount
} else {
    $countOfFreeNodes = 0
}

Write-Host "Free Nodes Count=[$countOfFreeNodes], HTTP Status Code=($statusCode)"

if ($exception -ne $NULL -and $exeption -ne "") {
    Write-Host "Exception: $exception"
}

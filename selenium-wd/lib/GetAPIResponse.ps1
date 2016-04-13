# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\GetAPIResponse.ps1 -baseURL "https://p3sqaauto.picarro.com" `
#                        -apiRelativePath "api/Analyzer/Version" `
#                        -loginUser "AutomationAdmin" `
#                        -loginPwd "<password>"     
# ---------------------------------------------------------------
param
(
  [Parameter(Mandatory=$true)]
  [String] $baseURL,                # for eg. https://p3sqaauto.picarro.com

  [Parameter(Mandatory=$true)]
  [String] $apiRelativePath,        # for eg. api/Analyzer/Version. 
  
  [Parameter(Mandatory=$true)]
  [String] $loginUser,              # This is web app login username

  [Parameter(Mandatory=$true)]
  [String] $loginPwd                # This is web app login password
)

$LoginURL = "$baseURL/Account/Login"
$SampleApiURL = "$baseURL/$apiRelativePath"
$loginResponse = Invoke-WebRequest $LoginURL -SessionVariable wSession

$html = $loginResponse.parsedHTML
$form = $loginResponse.forms[0]
$form.Fields.Username = $loginUser
$form.Fields.Password = $loginPwd

$loginResponse = Invoke-WebRequest -Uri $LoginURL -WebSession $wSession -Method POST -Body $form.Fields

# Invoke API and return response.
$apiResponse = Invoke-WebRequest -Uri $SampleApiURL -WebSession $wSession -Method GET 
$html = $apiResponse.parsedHTML
Write-Host $html.body.innerText
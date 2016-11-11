# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\DownloadFile.ps1 -baseURL "https://p3sqaauto.picarro.com" `
#                        -downloadFileRelativeUrl "Reports/ViewReportPdf?reportId=774a04fa-ab37-bbdf-944c-39d91cbffdeb&ReportType=Compliance" `
#                        -outputFileFullPath "C:\Users\spulikkal\Downloads\TC123_774a04fa-ab37-bbdf-944c-39d91cbffdeb.pdf" `
#                        -loginUser "AutomationAdmin" `
#                        -loginPwd "<password>"     
# ---------------------------------------------------------------
param
(
  [Parameter(Mandatory=$true)]
  [String] $baseURL,                # for eg. https://p3sqaauto.picarro.com

  [Parameter(Mandatory=$true)]
  [String] $downloadFileRelativeUrl,        # for eg. Reports/ViewReportPdf?reportId=774a04fa-ab37-bbdf-944c-39d91cbffdeb&ReportType=Compliance 

  [Parameter(Mandatory=$true)]
  [String] $outputFileFullPath,         # for eg. C:\Users\spulikkal\Downloads\TC123_774a04fa-ab37-bbdf-944c-39d91cbffdeb.pdf. 
  
  [Parameter(Mandatory=$true)]
  [String] $loginUser,              # This is web app login username

  [Parameter(Mandatory=$true)]
  [String] $loginPwd                # This is web app login password
)

$LoginURL = "$baseURL/Account/Login"
$downloadURL = "$baseURL/$downloadFileRelativeUrl"
$loginResponse = Invoke-WebRequest $LoginURL -SessionVariable wSession

$html = $loginResponse.parsedHTML
$form = $loginResponse.forms[0]
$form.Fields.Username = $loginUser
$form.Fields.Password = $loginPwd

$loginResponse = Invoke-WebRequest -Uri $LoginURL -WebSession $wSession -Method POST -Body $form.Fields
$html = $loginResponse.parsedHTML

$start_time = Get-Date
Write-Host "Starting download : $start_time"
Write-Host "Download URL : $downloadURL"
Write-Host "Output file save path : $outputFileFullPath"

# Invoke download.
$apiResponse = Invoke-WebRequest -Uri $downloadURL -WebSession $wSession -OutFile $outputFileFullPath

$totalSeconds = (Get-Date).Subtract($start_time).Seconds
Write-Host "Completed download in : $totalSeconds second(s)"

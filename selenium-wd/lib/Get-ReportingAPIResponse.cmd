Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\Get-ReportingAPIResponse.ps1;%~1\selenium-wd\lib\Get-ReportingAPIResponse.ps1 -BuildWorkingDir '%~1' -AutomationReportingAPIBaseUrl '%~2' -APIRelativeUrl '%~3' -Method '%~4' -Body '%~5'; exit;}"
exit

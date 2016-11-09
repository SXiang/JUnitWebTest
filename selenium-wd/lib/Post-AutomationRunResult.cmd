Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\Post-AutomationRunResult.ps1;%~1\selenium-wd\lib\Post-AutomationRunResult.ps1 -BuildWorkingDir '%~1'  -AutomationReportingAPIBaseUrl '%~2' -HtmlResultFilePath '%~3' -RunUUID '%~4'; exit;}"
exit

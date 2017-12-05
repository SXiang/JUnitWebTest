SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Start - Execute Load API test ...
set "cmdArgs=Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\LoadPerformance\Execute-LoadAPITest.ps1;%~1\selenium-wd\lib\LoadPerformance\Execute-LoadAPITest.ps1 -BuildWorkingDir '%~1' -AutomationReportingAPIBaseUrl '%~2' -ABExeFolder '%~3' -ApiEndpointUrl '%~4' -Method '%~5' -ContentType '%~6' -Username '%~7' -Password '%~8' -UseBasicAuthentication:%~9"
for /L %%i in (0,1,8) do @shift
set "cmdArgs=%cmdArgs% -ResponseContentLength %~1 -NumConcurrentRequests %~2 -NumRequestsInOneSession %~3 -NumPrimingRuns %~4 -RunUUID %~5 -TestCaseName '%~6' -OutputFolder '%~7'; exit;"
Powershell -command "& { %cmdArgs% }"
echo [%me%]: Complete - Load API test!
@echo on
exit

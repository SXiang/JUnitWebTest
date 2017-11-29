SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Start - Execute Load API test ...
set "cmdArgs=Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\LoadPerformance\Execute-LoadAPITest.ps1;%~1\selenium-wd\lib\LoadPerformance\Execute-LoadAPITest.ps1 -BuildWorkingDir '%~1' -AutomationReportingAPIBaseUrl '%~2' -ABExeFolder '%~3' -ApiEndpointUrl '%~4' -Method '%~5' -ContentType '%~6' -RequestBody '%~7' -ResponseContentLength %~8 -NumConcurrentRequests %~9"
for /L %%i in (0,1,8) do @shift
set "cmdArgs=%cmdArgs% -NumRequestsInOneSession %~1 -NumPrimingRuns %~2 -RunUUID %~3 -TestCaseName '%~4' -OutputFolder '%~5'; exit;"
Powershell -command "& { %cmdArgs% }"
echo [%me%]: Complete - Load API test!
@echo on
exit

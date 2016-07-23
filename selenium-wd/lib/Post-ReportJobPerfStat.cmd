set "cmdArgs=Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\Post-ReportJobPerfStat.ps1;%~1\selenium-wd\lib\Post-ReportJobPerfStat.ps1 -BuildWorkingDir '%~1' -AutomationReportingAPIBaseUrl '%~2' -ReportTitle '%~3' -ReportJobTypeId '%~4' -ReportJobTypeName '%~5' -ReportJobStartTime '%~6' -ReportJobEndTime '%~7' -TestExecutionStartDate '%~8' -TestExecutionEndDate '%~9'"
for /L %%i in (0,1,8) do @shift
set "cmdArgs=%cmdArgs% -BuildNumber '%~1' -TestCaseID %~2 -EnvironmentId %~3; exit;"
Powershell -command "& { %cmdArgs% }"
exit

set "cmdArgs=Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\Post-ReportJobPerfStat.ps1;%~1\selenium-wd\lib\Post-ReportJobPerfStat.ps1 -BuildWorkingDir '%~1' -AutomationReportingAPIBaseUrl '%~2' -ReportTitle '%~3' -ReportJobTypeId '%~4' -ReportJobTypeName '%~5' -ReportJobStartTime '%~6' -ReportJobEndTime '%~7' -TestExecutionStartDate '%~8' -TestExecutionEndDate '%~9'"
for /L %%i in (0,1,8) do @shift
set "cmdArgs=%cmdArgs% -ReportStartTime '%~1' -ReportEndTime '%~2' -BuildNumber '%~3' -TestCaseID %~4 -EnvironmentId %~5; exit;"
Powershell -command "& { %cmdArgs% }"
exit

set "cmdArgs=Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\Post-AnalyzerAPIPerfStat.ps1;%~1\selenium-wd\lib\Post-AnalyzerAPIPerfStat.ps1 -BuildWorkingDir '%~1' -AutomationReportingAPIBaseUrl '%~2' -APIName '%~3' -APIUrl '%~4' -NumberOfSamples %~5 -Average %~6 -Median %~7 -ResponseTime90Pctl %~8 -ResponseTime95Pctl %~9"
for /L %%i in (0,1,8) do @shift
set "cmdArgs=%cmdArgs% -Responsetime99Pctl %~1 -Min %~2 -Max %~3 -ErrorPercent %~4 -ThroughputPerSec %~5 -KBPerSec %~6 -TestExecutionStartDate '%~7' -TestExecutionEndDate '%~8' -BuildNumber '%~9'"
for /L %%i in (0,1,8) do @shift
set "cmdArgs=%cmdArgs% -TestCaseID %~1 -EnvironmentID %~2; exit;"
Powershell -command "& { %cmdArgs% }"
exit

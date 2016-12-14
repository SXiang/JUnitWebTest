set "cmdArgs=Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\Post-ProductTestBinariesMap.ps1;%~1\selenium-wd\lib\Post-ProductTestBinariesMap.ps1 -BuildWorkingDir '%~1' -AutomationReportingAPIBaseUrl '%~2' -TestBinaryFileName '%~3' -TestBinaryMajor '%~4' -TestBinaryMinor '%~5' -TestBinaryBuildNumber '%~6' -TestBinaryGitHash '%~7' -TestBranch '%~8' -EnvironmentUrl '%~9'"
for /L %%i in (0,1,8) do @shift
set "cmdArgs=%cmdArgs% -EnvironmentDBName '%~1' -EnvironmentBuildVersion '%~2' -BuildDate '%~3' -BuildSuccess '%~4'; exit;"
Powershell -command "& { %cmdArgs% }"
exit

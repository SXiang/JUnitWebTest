REM This script detects and installs load testing tools on a Windows machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Installing load testing tools ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\LoadPerformance\Install-LoadTestingTools.ps1;%~1\selenium-wd\lib\LoadPerformance\Install-LoadTestingTools.ps1 -BuildWorkingDir '%~1' -ArtifactoryBaseUrl '%~2' -ArtifactoryAPIKey '%~3' -ArtifactoryRepository '%~4' -ArtifactoryFileRelativePath '%~5' -InstallFolder '%~6'; exit;}"
echo [%me%]: Done with installation!
@echo on
exit;
REM This script publishes android app perf metric .dat files and test recording to default store.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Publishing Android App Perf Test Results To Store ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\Publish-AndroidAppPerfTestResults.ps1;%~1\selenium-wd\lib\Publish-AndroidAppPerfTestResults.ps1 -WorkingDirectory '%~1' -StoreLocation '%~2' -StartEpoch %~3; exit;}"
echo [%me%]: Done with publish
@echo on
exit;
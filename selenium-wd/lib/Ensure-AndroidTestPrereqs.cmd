REM This script ensures pre-requisites required for Android tests are present on the machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Ensuring android test pre-requisites present ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\Ensure-AndroidTestPrereqs.ps1;%~1\selenium-wd\lib\Ensure-AndroidTestPrereqs.ps1 -WorkingDirectory '%~1'; exit;}"
echo [%me%]: Done with checks!
@echo on
exit;
REM This script starts appium server on a Windows machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Starting appium server ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\StartAppiumServer.ps1;%~1\selenium-wd\lib\StartAppiumServer.ps1; exit;}"
echo [%me%]: Started appium!
@echo on
exit;
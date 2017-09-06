REM This script stops appium server on a Windows machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Stopping appium server ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\StopAppiumServer.ps1;%~1\selenium-wd\lib\StopAppiumServer.ps1; exit;}"
echo [%me%]: Stopped appium!
@echo on

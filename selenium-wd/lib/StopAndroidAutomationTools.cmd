REM This script stop android automation tools (emulator, appium server) on a Windows machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Stopping android automation tools ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\StopAndroidAutomationTools.ps1;%~1\selenium-wd\lib\StopAndroidAutomationTools.ps1; exit;}"
echo [%me%]: Stopped android automation tools!
@echo on
REM exit;
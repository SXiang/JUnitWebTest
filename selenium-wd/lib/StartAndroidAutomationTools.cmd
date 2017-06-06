REM This script starts android automation tools (emulator, appium server) on a Windows machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Starting android automation tools ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\StartAndroidAutomationTools.ps1;%~1\selenium-wd\lib\StartAndroidAutomationTools.ps1 -AvdName '%~2'; exit;}"
echo [%me%]: Started android automation tools!
@echo on
exit;
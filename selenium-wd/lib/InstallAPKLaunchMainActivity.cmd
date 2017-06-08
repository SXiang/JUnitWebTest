REM This script install APK on attached android device and launches the main activity.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Start - Install/Launch Main Activity ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\InstallAPKLaunchMainActivity.ps1;%~1\selenium-wd\lib\InstallAPKLaunchMainActivity.ps1 -APKFilePath '%~2' -ActivityToWaitFor '%~3'; exit;}"
echo [%me%]: Complete - Install/Launch Main Activity!
@echo on
exit;
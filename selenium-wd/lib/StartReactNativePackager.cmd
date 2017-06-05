REM This script starts react-native packager on a Windows machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Starting react native packager ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\StartReactNativePackager.ps1;%~1\selenium-wd\lib\StartReactNativePackager.ps1 -RootFolder '%~2'; exit;}"
echo [%me%]: Started react native packager!
@echo on
REM exit;
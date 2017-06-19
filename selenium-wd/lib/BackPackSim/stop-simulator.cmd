REM This script stops backpack server simulator processes running on a Windows machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Stopping backpack simulator processes ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\BackPackSim\stop-simulator.ps1;%~1\selenium-wd\lib\BackPackSim\stop-simulator.ps1; exit;}"
echo [%me%]: Stopped backpack simulator processes!
@echo on
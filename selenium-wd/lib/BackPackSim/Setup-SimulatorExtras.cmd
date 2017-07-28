REM This script ensures the extra python files required for backpack simulator are present on machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Check if extra backpack simulator files are present on machine ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\BackPackSim\Setup-SimulatorExtras.ps1;%~1\selenium-wd\lib\BackPackSim\Setup-SimulatorExtras.ps1 -ScriptRootFolder '%~1' -BroadcastConstantValuesInSimLinearFitter '%~2'; exit;}"
echo [%me%]: Check completed!
@echo on
exit;
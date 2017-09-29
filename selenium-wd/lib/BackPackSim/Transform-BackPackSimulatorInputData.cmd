REM This script transforms input data to backpack simulator.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Transforming backpack simulator input data ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\BackPackSim\Transform-BackPackSimulatorInputData.ps1;%~1\selenium-wd\lib\BackPackSim\Transform-BackPackSimulatorInputData.ps1 -WorkingDir '%~1' -InputDataFile '%~2' -OutputDataFile '%~3' -InstructionsFile:'%~4'; exit;}"
echo [%me%]: Done with transforms!
@echo on
exit;
REM This script can be used to pause/resume processes on a remote machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Executing pause/resume processes on remote machine ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\BackPackSim\PauseResumeWinProcsOnRemoteMachine.ps1;%~1\selenium-wd\lib\BackPackSim\PauseResumeWinProcsOnRemoteMachine.ps1 -DestMachineIPAddress '%~2' -DestMachineUsername '%~3' -DestMachinePassword '%~4' -IsResume '%~5'; exit;}"
echo [%me%]: Done executing pause/resume processes on remote machine!
@echo on
exit;
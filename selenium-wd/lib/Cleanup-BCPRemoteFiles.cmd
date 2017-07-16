REM Script to cleanup bcp .dat files on a remote machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Start - Cleanup BCP Remote Files ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\Cleanup-BCPRemoteFiles.ps1;%~1\selenium-wd\lib\Cleanup-BCPRemoteFiles.ps1 -BuildWorkingDir '%~1' -DestMachineIPAddress '%~2' -DestMachineUsername '%~3' -DestMachinePassword '%~4' -FilesToDelete '%~5'; exit;}"
echo [%me%]: Complete - Cleanup BCP Remote Files!
@echo on
exit;
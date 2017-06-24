REM Script to compress and transfer file to a remote machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Start - File Transfer ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\File-Transfer.ps1;%~1\selenium-wd\lib\File-Transfer.ps1 -BuildWorkingDir '%~1' -DestMachineIPAddress '%~2' -DestMachineUsername '%~3' -DestMachinePassword '%~4' -SourceFileLocation '%~5' -DestFileLocation '%~6'; exit;}"
echo [%me%]: Complete - File Transfer!
@echo on
exit;
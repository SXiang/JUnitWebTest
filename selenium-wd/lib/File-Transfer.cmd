REM Script to compress and transfer file to a remote machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Start - File Transfer ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\File-Transfer.ps1;%~1\selenium-wd\lib\File-Transfer.ps1 -BuildWorkingDir '%~2' -DestMachineIPAddress '%~3' -DestMachineUsername '%~4' -DestMachinePassword '%~5' -SourceFileLocation '%~6' -DestFileLocation '%~7'; exit;}"
echo [%me%]: Complete - File Transfer!
@echo on
exit;
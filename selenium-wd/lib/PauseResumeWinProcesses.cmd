REM This script stops processes matching the specified window titles on a Windows machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Pause/resume processes matching window titles ...
Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\PauseResumeWinProcesses.ps1;%~1\selenium-wd\lib\PauseResumeWinProcesses.ps1 -WorkingFolder '%~1' -WindowTitles '%~2' -IsResume '%~3'; exit;}"
echo [%me%]: Paused/resumed processes matching window titles
@echo on
exit;
REM This script starts backpack server simulator pre-requisites on a Windows machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Starting backpack simulator and extra processes ...
set BUILDROOT=%~1
start cmd.exe /C %~1\host\src\main\python\Host\Utilities\BackpackServer\startsim.bat
start cmd.exe /C %~1\host\src\main\python\Host\Utilities\BackpackServer\startsimextras.bat
echo [%me%]: Started backpack simulator and extra processes!
@echo on
REM This script starts backpack server simulator pre-requisites on a Windows machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Starting backpack simulator processes ...
echo [%me%]: cd %~1\host\src\main\python\Host\Utilities\BackpackServer
cd %~1\host\src\main\python\Host\Utilities\BackpackServer
set BUILDROOT=%~1
startsim.bat
echo [%me%]: Started backpack simulator!
@echo on
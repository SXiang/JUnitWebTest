REM This script installs backpack server simulator pre-requisites on a Windows machine.
REM This script assumes 2.7.x Python installed on the machine.
SETLOCAL
SET me=%~n0

@echo off
echo [%me%]: Executing setup-simulator.py ...
python setup-simulator.py %~1 1> setup.log 2> setup-errors.log

if ERRORLEVEL == 0 (
  goto Continue
) else (
  goto LogErrorTerminate
)

:Continue
SET /a scriptExitCode = 0
for /F "tokens=*" %%A in (setup-errors.log) do (
  Echo.%%A | findstr /C:"ERROR">null && (SET scriptExitCode=1) || (SET scriptExitCode=0)
)

echo [%me%]: Script exit code : %scriptExitCode%
if "%scriptExitCode%" == "1" (
  goto LogErrorTerminate
)

echo [%me%]: 'setup-simulator.py' execution log written to setup.log
echo [%me%]: Installing simulator pre-requisite packages ...
pip install -r simulator-requirements.txt
echo [%me%]: Done installing simulator pre-requisite packages.
goto ScriptTerminate

:LogErrorTerminate
echo [%me%]: Script did NOT execute correctly.
echo [%me%]: CHECK setup-errors.log for more details

:ScriptTerminate
@echo on
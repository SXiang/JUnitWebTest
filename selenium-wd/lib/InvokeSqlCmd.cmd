@echo off
sqlcmd -b -S tcp:%1,%2 -d %3 -U %4 -P %5 -i "%~6" -o "%~7"
if %ERRORLEVEL%==0 (
  echo Executed %~6 successfully!
) else (
  echo ERROR=%ERRORLEVEL% when executing %~6 . Check error logs at - %~7
) 
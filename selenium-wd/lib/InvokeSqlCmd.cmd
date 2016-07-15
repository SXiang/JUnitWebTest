@echo off
sqlcmd -b -S tcp:%1,%2 -d %3 -U %4 -P %5 -i "%~6" -o "%~7"
REM EXAMPLE: sqlcmd -b -E -S tcp:20.20.130.210,1433 -d SurveyorSQAAuto -U awssa -P 3Vf763pSg2 -i "C:\temp\sqlcmd\sampleQuery.sql" -o "C:\temp\sqlcmd\out2.txt"
if %ERRORLEVEL%==0 (
  echo Executed %~6 successfully!
) else (
  echo ERROR=%ERRORLEVEL% when executing %~6 . Check error logs at - %~7
) 
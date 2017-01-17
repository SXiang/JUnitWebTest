Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\Post-SurveySessionFromDB3.ps1;%~1\selenium-wd\lib\Post-SurveySessionFromDB3.ps1 -WorkingFolder '%~1' -BaseURL '%~2' -AnalyzerSerialNumber '%~3' -AnalyzerSharedKey '%~4' -Surveyor '%~5'; exit;}"
exit

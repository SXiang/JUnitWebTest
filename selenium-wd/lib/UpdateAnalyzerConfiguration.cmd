Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %WORKING_DIR%\selenium-wd\lib\UpdateAnalyzerConfiguration.ps1;%WORKING_DIR%\lib\UpdateAnalyzerConfiguration.ps1 -analyzerSerialNumber '%1%' -analyzerSharedKey '%2%' -analyzerMaxSurveyDuration %3%; exit;}"
exit

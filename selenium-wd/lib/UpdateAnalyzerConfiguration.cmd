Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %WORKING_DIR%\lib\UpdateAnalyzerConfiguration.ps1;%WORKING_DIR%\lib\UpdateAnalyzerConfiguration.ps1 -p3Url '%0%' -analyzerSerialNumber '%1%' -analyzerSharedKey '%2%' -analyzerMaxSurveyDuration %3%; exit;}"
exit

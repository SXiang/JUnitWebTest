Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path C:\Repositories\surveyor-qa\selenium-wd\\lib\UpdateAnalyzerConfiguration.ps1;C:\Repositories\surveyor-qa\selenium-wd\\lib\UpdateAnalyzerConfiguration.ps1 -p3Url 'https://p3sqaauto.picarro.com' -analyzerSerialNumber 'AutoTestAnalyzer047' -analyzerSharedKey '7f17af6bd3fb48d' -analyzerMaxSurveyDuration 0; exit;}"
exit

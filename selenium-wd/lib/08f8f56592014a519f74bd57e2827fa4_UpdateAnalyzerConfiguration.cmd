Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path C:\Repositories\surveyor-qa\selenium-wd\\lib\UpdateAnalyzerConfiguration.ps1;C:\Repositories\surveyor-qa\selenium-wd\\lib\UpdateAnalyzerConfiguration.ps1 -p3Url 'https://p3sqaauto.picarro.com' -analyzerSerialNumber 'AutoTestAnalyzer021' -analyzerSharedKey '49b97c7d3f92451' -analyzerMaxSurveyDuration 0; exit;}"
exit

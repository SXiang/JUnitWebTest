Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path C:\Repositories\surveyor-qa\selenium-wd\lib\SetupSimulatorPreReqs.ps1;C:\Repositories\surveyor-qa\selenium-wd\lib\SetupSimulatorPreReqs.ps1 -isRunningLocally:$true -overwritePicarroAnalyzer:$true; exit;}"
exit

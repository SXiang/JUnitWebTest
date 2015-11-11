Powershell -noexit -command "& {Set-ExecutionPolicy remotesigned -Force;C:\Repositories\surveyor-qa\selenium-wd\lib\SetupSimulatorPreReqs.ps1 -isRunningLocally:$false; exit;}"
exit

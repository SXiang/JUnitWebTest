Powershell -noexit -command "& {Set-ExecutionPolicy remotesigned -Force;C:\BuildAgent\work\d535fe66144ca699\selenium-wd\lib\SetupSimulatorPreReqs.ps1 -isRunningLocally:$false; exit;}"
exit

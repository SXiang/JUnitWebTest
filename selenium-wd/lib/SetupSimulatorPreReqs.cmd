Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %CI_WORKING_DIR%\selenium-wd\lib\SetupSimulatorPreReqs.ps1;%CI_WORKING_DIR%\selenium-wd\lib\SetupSimulatorPreReqs.ps1 -isRunningLocally:$false -envHost '%~1' -buildWorkingDir '%~2'; exit;}"
exit

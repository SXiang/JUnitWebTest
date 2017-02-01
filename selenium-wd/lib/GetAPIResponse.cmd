Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\GetAPIResponse.ps1;%~1\selenium-wd\lib\GetAPIResponse.ps1 -baseURL '%~2' -apiRelativePath '%~3' -loginUser '%~4' -loginPwd '%~5'; exit;}"
exit

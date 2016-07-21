Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %~1\selenium-wd\lib\DownloadFile.ps1;%~1\selenium-wd\lib\DownloadFile.ps1 -baseURL '%~2' -downloadFileRelativeUrl '%~3' -outputFileFullPath '%~4' -loginUser '%~5' -loginPwd '%~6'; exit;}"
exit

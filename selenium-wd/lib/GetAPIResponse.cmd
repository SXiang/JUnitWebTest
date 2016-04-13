Powershell -command "& {Set-ExecutionPolicy Unrestricted -Force;Unblock-File -Path %WORKING_DIR%lib\GetAPIResponse.ps1;%WORKING_DIR%lib\GetAPIResponse.ps1 -baseURL '%1%' -apiRelativePath '%2%' -loginUser '%3%' -loginPwd '%4%'; exit;}"
exit

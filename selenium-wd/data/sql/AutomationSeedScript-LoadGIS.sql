-- Push GIS data
exec master..xp_cmdshell 'bcp "[%DB_NAME%].[dbo].[Asset]" in "%ASSET_DAT_FILE_PATH%" -t\t -w -U "%DB_USER%" -P "%DB_PASSWORD%" -S "%SERVER_IP_ADDR%" -o "C:\QATestLogs\bcp-asset.txt"'
exec master..xp_cmdshell 'bcp "[%DB_NAME%].[dbo].[Boundary]" in "%BOUNDARY_DAT_FILE_PATH%" -t\t -w -U "%DB_USER%" -P "%DB_PASSWORD%" -S "%SERVER_IP_ADDR%" -o "C:\QATestLogs\bcp-boundary.txt"'
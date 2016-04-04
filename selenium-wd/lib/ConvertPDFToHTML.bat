cd "%DOWNLOAD_DIR%"
if exist %HTML_FILE% DEL %HTML_FILE%
%EXE_DIR%\pdf2htmlEX-win32-0.13.6\pdf2htmlEX.exe %PDF_FILE%
exit

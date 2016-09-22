@echo on

set currentDir=%cd%
set cmdLibDir=%JMETER_HOME%/lib/ext
REM set cmd=java -jar %cmdLibDir%/CMDRunner.jar --tool Reporter
set cmd=%cmdLibDir%/JMeterPluginsCMD.bat
REM set inputFileName=resultTable_AnalyzerAPIPerformance.csv
REM set outputFileName=resultTable.csv

if not "%1"=="" set inputFileName=%1
if not "%2"=="" set outputFileName=%2
set inputFile=%currentDir%/../result/%inputFileName%
set outputFile=%currentDir%/../result/%outputFileName%
set reportType=SynthesisReport

cd %cmdLibDir%
%cmd% --generate-csv %outputFile% --input-jtl %inputFile% --plugin-type %reportType% ^
&& cd %currentDir%
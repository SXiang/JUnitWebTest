# 
# Sample Usage Examples: 
#   ./UpdateAnalyzerConfiguration.ps1 -p3Url "https://p3sqaauto.picarro.com" -analyzerSerialNumber "SimAuto-Analyzer2" -analyzerSharedKey "SimAuto-AnalyzerKey2" -analyzerMaxSurveyDuration 0          # to skip setting max survey duration pass value of 0
#   ./UpdateAnalyzerConfiguration.ps1 -p3Url "https://p3sqaauto.picarro.com" -analyzerSerialNumber "SimAuto-Analyzer2" -analyzerSharedKey "SimAuto-AnalyzerKey2" -analyzerMaxSurveyDuration 3600000

param
(
  [Parameter(Mandatory=$true)]
  [String] $p3Url,

  [Parameter(Mandatory=$true)]
  [String] $analyzerSerialNumber,

  [Parameter(Mandatory=$true)]
  [String] $analyzerSharedKey,
  
  [Parameter(Mandatory=$true)]
  [String] $analyzerMaxSurveyDuration       # Survey Duration in milliseconds. Default value is 28800000 (8 hours)
)

#---------------------------------------------------------
# Stops Analyzer EXE and child processes.
#---------------------------------------------------------
function Stop-Analyzer() {
	"Stopping pipelinerunner.exe ..."
	taskkill /F /IM pipelinerunner.exe
	"Stopping DataManagerPublisher.exe ..."
	taskkill /F /IM DataManagerPublisher.exe
	"Stopping supervisor.exe ..."
	taskkill /F /IM supervisor.exe
	"Stopping Picarro.Surveyor.Analyzer.exe ..."
	taskkill /F /IM Picarro.Surveyor.Analyzer.exe
}

Write-Host "Stopping picarro.surveyor.analyzer and child processes ..."
Stop-Analyzer

Write-Host "Deleting .db3 from AppData"
$appDataRoaming = $env:APPDATA
$surveyorDB3Path = $appDataRoaming.replace("Roaming", "Local\Picarro\Surveyor\Data\Surveyor.db3")
Remove-Item -Path $surveyorDB3Path -Force

$ANALYZER_FOLDER_PATH = "C:\Picarro\G2000\Picarro.Surveyor.Analyzer"
$PICARRO_SURVEYOR_ANALYZER_EXE_CONFIG = "Picarro.Surveyor.Analyzer.exe.config"

Write-Host "Updating Analyzer.exe.config values"

$exeConfig = new-object Xml
$exeConfig.load("$ANALYZER_FOLDER_PATH\$PICARRO_SURVEYOR_ANALYZER_EXE_CONFIG")

Write-Host "Updating 'P3Url' in .exe.config to $p3Url "
$exeConfig.configuration.SelectSingleNode("appSettings/add[@key='P3Url']").value = $p3Url

Write-Host "Updating 'SerialNumber' in .exe.config to $analyzerSerialNumber "
$exeConfig.configuration.SelectSingleNode("appSettings/add[@key='SerialNumber']").value = $analyzerSerialNumber

Write-Host "Updating 'SharedKey' in .exe.config to $analyzerSharedKey "
$exeConfig.configuration.SelectSingleNode("appSettings/add[@key='SharedKey']").value = $analyzerSharedKey

if($analyzerMaxSurveyDuration -ne 0) {
	Write-Host "Updating 'MaxSurveyDuration' in .exe.config to $analyzerMaxSurveyDuration "
	$exeConfig.configuration.SelectSingleNode("appSettings/add[@key='MaxSurveyDuration']").value = $analyzerMaxSurveyDuration
}

$exeConfig.save("$ANALYZER_FOLDER_PATH\$PICARRO_SURVEYOR_ANALYZER_EXE_CONFIG")

Write-Host "Done. Configuration file saved at $ANALYZER_FOLDER_PATH\$PICARRO_SURVEYOR_ANALYZER_EXE_CONFIG"

# update hosts file
$hostsEntry = "127.0.0.1       localhost ${analyzerSerialNumber}.surveyordev.com"
Write-Host "Updating 'hosts' file. Adding $hostsEntry"
$len = get-content C:\Windows\system32\drivers\etc\hosts | Measure-Object -Line
$hostsExistingContent = get-content C:\Windows\system32\drivers\etc\hosts | Select-Object -first ($len.lines-1)
$hostsFileContent = $hostsExistingContent + "`r`n" + $hostsEntry
Write-Output $hostsFileContent | Out-File C:\Windows\system32\drivers\etc\hosts -Encoding utf8
Write-Host "Done. Updating 'hosts' file"

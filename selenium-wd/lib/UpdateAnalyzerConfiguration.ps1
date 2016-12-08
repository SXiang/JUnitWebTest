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

Write-Host "Stopping process picarro.surveyor.analyzer ..."
Stop-Process -processname "picarro.surveyor.analyzer"

Write-Host "Deleting .db3 from AppData"
$appDataRoaming = $env:APPDATA
$surveyorDB3Path = $appDataRoaming.replace("Roaming", "Local\Picarro\Surveyor\Data\Surveyor.db3")
Remove-Item -Path $surveyorDB3Path -Force

$ANALYZER_FOLDER_PATH = "C:\PicarroAnalyzer"
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

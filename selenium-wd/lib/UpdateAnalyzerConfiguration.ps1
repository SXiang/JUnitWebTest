# 
# Sample Usage: 
#   ./UpdateAnalyzerConfiguration.ps1 -analyzerSerialNumber "SimAuto-Analyzer2" -analyzerSharedKey "SimAuto-AnalyzerKey2"

param
(
  [Parameter(Mandatory=$true)]
  [String] $analyzerSerialNumber,

  [Parameter(Mandatory=$true)]
  [String] $analyzerSharedKey
)

$ANALYZER_FOLDER_PATH = "C:\PicarroAnalyzer"
$PICARRO_SURVEYOR_ANALYZER_EXE_CONFIG = "Picarro.Surveyor.Analyzer.exe.config"

Write-Host "Updating Analyzer.exe.config values"

$exeConfig = new-object Xml
$exeConfig.load("$ANALYZER_FOLDER_PATH\$PICARRO_SURVEYOR_ANALYZER_EXE_CONFIG")

Write-Host "Updating 'SerialNumber' in .exe.config to $SIMAUTO_ANALYZERKEY1 "
$exeConfig.configuration.SelectSingleNode("appSettings/add[@key='SerialNumber']").value = $analyzerSerialNumber

Write-Host "Updating 'SharedKey' in .exe.config to $SIMAUTO_ANALYZERKEY1 "
$exeConfig.configuration.SelectSingleNode("appSettings/add[@key='SharedKey']").value = $analyzerSharedKey

$exeConfig.save("$ANALYZER_FOLDER_PATH\$PICARRO_SURVEYOR_ANALYZER_EXE_CONFIG")

Write-Host "Done. Configuration file saved at $ANALYZER_FOLDER_PATH\$PICARRO_SURVEYOR_ANALYZER_EXE_CONFIG"

<#
 SAMPLE USAGE:
  ./TweakOdorCallServerIni.ps1 -HostRootFolder "C:\Repositories\host"
#>

param(
 [string] $HostRootFolder
)

$IN_FILE = "$HostRootFolder\src\main\python\Host\Utilities\BackpackServer\odorcallServer.ini"
$REPLACE_STRING = "InstrData_File = ../../../InstrConfig/Calibration/InstrCal/InstrCal.ini"
(Get-Content $IN_FILE).Replace($REPLACE_STRING, "") |  Set-Content $IN_FILE -Encoding Ascii
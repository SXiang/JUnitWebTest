<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION: 
  - This script can be used to transform the input data to Backpack Simulator.
  - Following transforms are currently supported and can be specified in an instructions file (see below for sample file):
      a) Replace column values for:
         - CH4
         - Disposition
         - SpectrumID
      b) Drop column value for all the columns in input data file (except last column).

  - Here is a sample instructions file that could be specified as input to this script for transforming input data:

    [replace]
    Disposition = 3.0000000000E+00
    CH4 = 7.23478234783

    [drop]
    TIME = delete
    EthaneRatioSdev = delete

----------------------------------------------------------------------------------------------------------------------------------#>
W
param(
  [Parameter(Mandatory=$true)]
  [String] $WorkingDir,                        # eg. "C:\Repositories\surveyor-qa"

  [Parameter(Mandatory=$true)]
  [String] $InputDataFile,                     # eg. "C:\Repositories\host\src\main\python\Host\Utilities\BackpackServer\TestUtilities\Nomad4001-20170713-212401Z-DataLog_User_Minimal.dat"

  [Parameter(Mandatory=$true)]
  [String] $OutputDataFile,                    # eg. "C:\Repositories\host\src\main\python\Host\Utilities\BackpackServer\TestUtilities\Nomad4001-20170713-212401Z-DataLog_User_Minimal.modified.dat"

  [Parameter(Mandatory=$true)]
  [String] $InstructionsFile                   # eg. "C:\Temp\testInstr.ini"
)

if (-not (Test-Path $InputDataFile)) {
    Write-Error "Did NOT find the input data file -> $InputDataFile"
    exit
}

if (-not (Test-Path $InstructionsFile)) {
    Write-Error "Did NOT find the input instructions file -> $InstructionsFile"
    exit
}

. "$WorkingDir\selenium-wd\lib\HelperScripts\IniFileHelper.ps1"

$script:replaceDict = @{}
$script:dropDict = @{}
$iniContents = [System.Collections.Hashtable] (Read-IniFile -iniFile "$InstructionsFile")
$iniContents.Keys | %{
    $key = $_
    if ($key.ToLower() -eq "replace") {
        $script:replaceDict = $iniContents.get_item($key)
    }

    if ($key.ToLower() -eq "drop") {
        $script:dropDict = $iniContents.get_item($key)
    }
}

Write-Host "Reading files lines from $InputDataFile"
$lines = Get-Content "$InputDataFile" -Encoding Ascii |
   select -Property @{name='DATE';expression={$_.Substring(0,26).Trim()}},
					@{name='TIME';expression={$_.Substring(26,26).Trim()}},
					@{name='FRAC_DAYS_SINCE_JAN1';expression={$_.Substring(52,26).Trim()}},
					@{name='FRAC_HRS_SINCE_JAN1';expression={$_.Substring(78,26).Trim()}},
					@{name='EPOCH_TIME';expression={$_.Substring(104,26).Trim()}},
					@{name='ALARM_STATUS';expression={$_.Substring(130,26).Trim()}},
					@{name='INST_STATUS';expression={$_.Substring(156,26).Trim()}},
					@{name='CH4';expression={$_.Substring(182,26).Trim()}},
					@{name='C2H6';expression={$_.Substring(208,26).Trim()}},
					@{name='Confidence';expression={$_.Substring(234,26).Trim()}},
					@{name='Disposition';expression={$_.Substring(260,26).Trim()}},
					@{name='EthaneRatio';expression={$_.Substring(286,26).Trim()}},
					@{name='EthaneRatioSdev';expression={$_.Substring(312,26).Trim()}},
					@{name='GPS_ABS_LAT_ADA';expression={$_.Substring(338,26).Trim()}},
					@{name='GPS_ABS_LONG_ADA';expression={$_.Substring(364,26).Trim()}},
					@{name='GPS_ALTITUDE_ADA';expression={$_.Substring(390,26).Trim()}},
                    @{name='GPS_FIT_ADA';expression={$_.Substring(416,26).Trim()}},
					@{name='GPS_TIME_ADA';expression={$_.Substring(442,26).Trim()}},
					@{name='SpectrumID';expression={$_.Substring(468,26).Trim()}}

$format = @()
if (-not $script:dropDict.ContainsKey("DATE")) { $format += @{expression={$_.DATE};label="DATE";width=25} }
if (-not $script:dropDict.ContainsKey("TIME")) { $format += @{expression={$_.TIME};label="TIME";width=25} }
if (-not $script:dropDict.ContainsKey("FRAC_DAYS_SINCE_JAN1")) { $format += @{expression={$_.FRAC_DAYS_SINCE_JAN1};label="FRAC_DAYS_SINCE_JAN1";width=25} }
if (-not $script:dropDict.ContainsKey("FRAC_HRS_SINCE_JAN1")) { $format += @{expression={$_.FRAC_HRS_SINCE_JAN1};label="FRAC_HRS_SINCE_JAN1";width=25} }
if (-not $script:dropDict.ContainsKey("EPOCH_TIME")) { $format += @{expression={$_.EPOCH_TIME};label="EPOCH_TIME";width=25} }
if (-not $script:dropDict.ContainsKey("ALARM_STATUS")) { $format += @{expression={$_.ALARM_STATUS};label="ALARM_STATUS";width=25} }
if (-not $script:dropDict.ContainsKey("INST_STATUS")) { $format += @{expression={$_.INST_STATUS};label="INST_STATUS";width=25} }
if (-not $script:dropDict.ContainsKey("CH4")) { $format += @{expression={ 
                if (-not ($_.CH4 -match "CH4\s*") -and ($script:replaceDict.containsKey("CH4"))) {
                    $script:replaceDict.get_item("CH4")
                } else {
                    $_.CH4
                }};label="CH4";width=25} }

if (-not $script:dropDict.ContainsKey("C2H6")) { $format += @{expression={$_.C2H6};label="C2H6";width=25} }
if (-not $script:dropDict.ContainsKey("Confidence")) { $format += @{expression={$_.Confidence};label="Confidence";width=25} }
if (-not $script:dropDict.ContainsKey("Disposition")) { $format += @{expression={
                if (-not ($_.Disposition -match "Disposition\s*") -and ($script:replaceDict.containsKey("Disposition"))) {
                    $script:replaceDict.get_item("Disposition")
                } else {
                    $_.Disposition
                }};label="Disposition";width=25} }

if (-not $script:dropDict.ContainsKey("EthaneRatio")) { $format += @{expression={$_.EthaneRatio};label="EthaneRatio";width=25} }
if (-not $script:dropDict.ContainsKey("EthaneRatioSdev")) { $format += @{expression={$_.EthaneRatioSdev};label="EthaneRatioSdev";width=25} }
if (-not $script:dropDict.ContainsKey("GPS_ABS_LAT_ADA")) { $format += @{expression={$_.GPS_ABS_LAT_ADA};label="GPS_ABS_LAT_ADA";width=25} }
if (-not $script:dropDict.ContainsKey("GPS_ABS_LONG_ADA")) { $format += @{expression={$_.GPS_ABS_LONG_ADA};label="GPS_ABS_LONG_ADA";width=25} }
if (-not $script:dropDict.ContainsKey("GPS_ALTITUDE_ADA")) { $format += @{expression={$_.GPS_ALTITUDE_ADA};label="GPS_ALTITUDE_ADA";width=25} }
if (-not $script:dropDict.ContainsKey("GPS_FIT_ADA")) { $format += @{expression={$_.GPS_FIT_ADA};label="GPS_FIT_ADA";width=25} }
if (-not $script:dropDict.ContainsKey("GPS_TIME_ADA")) { $format += @{expression={$_.GPS_TIME_ADA};label="GPS_TIME_ADA";width=25} }
if (-not $script:dropDict.ContainsKey("SpectrumID")) { $format += @{expression={
                if (-not ($_.SpectrumID -match "SpectrumID\s*") -and ($script:replaceDict.containsKey("SpectrumID"))) {
                    $script:replaceDict.get_item("SpectrumID")
                } else {
                    $_.SpectrumID
                }};label="SpectrumID";width=26} }


Write-Host "Transforming input data file and writting to -> $OutputDataFile ..."

$outStr = $lines | format-table $format -HideTableHeaders | Out-String -Width 900
$outStr.Trim() | Out-file $OutputDataFile -Encoding ASCII 

Write-Host "Transformed file written successfully to -> $OutputDataFile"
<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION: 
  - This script can be used to ensure datetime values match corresponding epoch times in CaptureEvent, Measurement and Survey 
    table csvs generated automation survey seed data.
  
 Sample Run Script: 
   .\Ensure-CorrectDateTimesInSurveyDataCSVs.ps1 `
        -inDirectory "C:\temp\EQSurveys"  `
        -outDirectory "C:\temp\EQSurveys\cleaned"  `
        -fileExtFilter "*.csv"  
----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $inDirectory,

  [Parameter(Mandatory=$true)]
  [String] $outDirectory,

  [Parameter(Mandatory=$true)]
  [String] $fileExtFilter
)

. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DateTimeHelpers.ps1"
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"


Split-Path -Path "$inDirectory\$fileExtFilter" -Leaf -Resolve | % {
    [string]$fileName = [string]$_
    $fileFullPath = "$inDirectory\$fileName"
    $outFileFullPath = "$outDirectory\$fileName"

    # Clean up datetimes in CaptureEvent CSVs.
    if ($fileName.StartsWith("CaptureEvent-")) {
        
        Write-Host "[ENSURE_CORRECT_DATETIME] -> Processing file - $fileFullPath ..."

        $header = $COLUMN_HEADINGS.get_item("CaptureEvent-")
        Add-Content $outFileFullPath "$header"
        $i = 0
        $csvData = Import-Csv -Path $fileFullPath
        $csvData | % {
            $objCap = $_;
            $capAnalyzerId = $objCap.AnalyzerId;
            $capCaptureType = $objCap.CaptureType;
            $capClassificationConfidence = $objCap.ClassificationConfidence;
            $capConcentration = $objCap.Concentration;
            $capDateTime = $objCap.DateTime;
            $capDelta = $objCap.Delta;
            $capDisposition = $objCap.Disposition;
            $capDistance = $objCap.Distance;
            $capEpochTime = $objCap.EpochTime;
            $capEthaneRatio = $objCap.EthaneRatio;
            $capEthaneRatioSdev = $objCap.EthaneRatioSdev;
            $capGpsLatitude = $objCap.GpsLatitude;
            $capGpsLongitude = $objCap.GpsLongitude;
            $capId = $objCap.Id;
            $capReplayLMin = $objCap.ReplayLMin;
            $capReplayMax = $objCap.ReplayMax;
            $capReplayRMin = $objCap.ReplayRMin;
            $capShape = $objCap.Shape;
            $capSurveyId = $objCap.SurveyId;
            $capUncertainty = $objCap.Uncertainty;

            # Ensure epoch and datetime match.
            if ($capEpochTime -ne $null -and $capEpochTime -ne "") {
                [double]$fCapEpochTime = [double]$capEpochTime
                $gmtDate = FromUnixTime $fCapEpochTime
                $capDateTime = Date-ToString -value $gmtDate
            }

            if ($i % 100 -eq 0) {
                Write-Host "[ENSURE_CORRECT_DATETIME] -> File='$fileName', Processing LINE-$i"
            }

            $i++

            Add-Content $outFileFullPath "$capId,$capAnalyzerId,$capEpochTime,$capDateTime,$capGpsLatitude,$capGpsLongitude,$capShape,$capDisposition,$capDelta,$capConcentration,$capUncertainty,$capCaptureType,$capDistance,$capReplayMax,$capReplayLMin,$capReplayRMin,$capSurveyId,$capEthaneRatio,$capEthaneRatioSdev,$capClassificationConfidence"
        }
    }
    # Clean up datetimes in Measurement CSVs.
    elseif ($fileName.StartsWith("Measurement-")) {

        Write-Host "[ENSURE_CORRECT_DATETIME] -> Processing file - $fileFullPath ..."

        $header = $COLUMN_HEADINGS.get_item("Measurement-")
        Add-Content $outFileFullPath "$header"
        $i = 0
        $csvData = Import-Csv -Path $fileFullPath
        $csvData | % {
            $objMea = $_;
            $meaAnalyzerEthaneConcentrationUncertainty = $objMea.AnalyzerEthaneConcentrationUncertainty;
            $meaAnalyzerId = $objMea.AnalyzerId;
            $meaAnalyzerMode = $objMea.AnalyzerMode;
            $meaAnalyzerStatus = $objMea.AnalyzerStatus;
            $meaC2H4 = $objMea.C2H4;
            $meaC2H6 = $objMea.C2H6;
            $meaCarSpeedEast = $objMea.CarSpeedEast;
            $meaCarSpeedNorth = $objMea.CarSpeedNorth;
            $meaCavityPressure = $objMea.CavityPressure;
            $meaCH4 = $objMea.CH4;
            $meaChemDetect = $objMea.ChemDetect;
            $meaCO2 = $objMea.CO2;
            $meaCreateDate = $objMea.CreateDate;
            $meaDeltaCH4 = $objMea.DeltaCH4;
            $meaEpochTime = $objMea.EpochTime;
            $meaGpsFit = $objMea.GpsFit;
            $meaGpsLatitude = $objMea.GpsLatitude;
            $meaGpsLongitude = $objMea.GpsLongitude;
            $meaH2OPercent = $objMea.H2OPercent;
            $meaHotBoxTemperature = $objMea.HotBoxTemperature;
            $meaInstrumentStatus = $objMea.InstrumentStatus;
            $meaMobileFlowRate = $objMea.MobileFlowRate;
            $meaPeakDetectorState = $objMea.PeakDetectorState;
            $meaPeripheralStatus = $objMea.PeripheralStatus;
            $meaShape = $objMea.Shape;
            $meaSpecies = $objMea.Species;
            $meaValveMask = $objMea.ValveMask;
            $meaWarmBoxTemperature = $objMea.WarmBoxTemperature;
            $meaWeatherStationRotation = $objMea.WeatherStationRotation;
            $meaWindDirectionStdDev = $objMea.WindDirectionStdDev;
            $meaWindSpeedEast = $objMea.WindSpeedEast;
            $meaWindSpeedLateral = $objMea.WindSpeedLateral;
            $meaWindSpeedLongitudinal = $objMea.WindSpeedLongitudinal;
            $meaWindSpeedNorth = $objMea.WindSpeedNorth;

            # Ensure epoch and datetime match.
            if ($meaEpochTime -ne $null -and $meaEpochTime -ne "") {
                [double]$fMeaEpochTime = [double]$meaEpochTime
                $gmtDate = FromUnixTime $fMeaEpochTime
                $meaCreateDate = Date-ToString -value $gmtDate
            }

            if ($i % 100 -eq 0) {
                Write-Host "[ENSURE_CORRECT_DATETIME] -> File='$fileName', Processing LINE-$i"
            }

            $i++

            Add-Content $outFileFullPath "$meaAnalyzerId,$meaEpochTime,$meaCreateDate,$meaGpsLatitude,$meaGpsLongitude,$meaGpsFit,$meaShape,$meaInstrumentStatus,$meaValveMask,$meaCarSpeedNorth,$meaCarSpeedEast,$meaWindSpeedNorth,$meaWindSpeedEast,$meaWindDirectionStdDev,$meaWeatherStationRotation,$meaWindSpeedLateral,$meaWindSpeedLongitudinal,$meaChemDetect,$meaSpecies,$meaCH4,$meaCO2,$meaH2OPercent,$meaDeltaCH4,$meaPeripheralStatus,$meaAnalyzerStatus,$meaCavityPressure,$meaWarmBoxTemperature,$meaHotBoxTemperature,$meaMobileFlowRate,$meaAnalyzerMode,$meaPeakDetectorState,$meaC2H6,$meaC2H4,$meaAnalyzerEthaneConcentrationUncertainty"
        }
    }
    # Clean up datetimes in Survey CSVs.
    elseif ($fileName.StartsWith("Survey-")) {

        Write-Host "[ENSURE_CORRECT_DATETIME] -> Processing file - $fileFullPath ..."
     
        $header = $COLUMN_HEADINGS.get_item("Survey-")
        Add-Content $outFileFullPath "$header"
        $i = 0
        $csvData = Import-Csv -Path $fileFullPath
        $csvData | % {
            $objSur = $_;
            $surAnalyzerId = $objSur.AnalyzerId;
            $surBuildNumber = $objSur.BuildNumber;
            $surDeleted = $objSur.Deleted;
            $surEndDateTime = $objSur.EndDateTime;
            $surEndEpoch = $objSur.EndEpoch;
            $surId = $objSur.Id;
            $surLocationId = $objSur.LocationId;
            $surMinimumAmplitude = $objSur.MinimumAmplitude;
            $surProcessingDateCompleted = $objSur.ProcessingDateCompleted;
            $surProcessingDateStarted = $objSur.ProcessingDateStarted;
            $surReferenceGasBottleId = $objSur.ReferenceGasBottleId;
            $surStabilityClass = $objSur.StabilityClass;
            $surStartDateTime = $objSur.StartDateTime;
            $surStartEpoch = $objSur.StartEpoch;
            $surStatus = $objSur.Status;
            $surSurveyModeTypeId = $objSur.SurveyModeTypeId;
            $surSurveyorUnitId = $objSur.SurveyorUnitId;
            $surTag = $objSur.Tag;
            $surUserId = $objSur.UserId;

            # Ensure epoch and datetime match for both startDate and endDate.
            if ($surStartEpoch -ne $null -and $surStartEpoch -ne "") {
                [double]$fSurStartEpoch = [double]$surStartEpoch
                $gmtDate = FromUnixTime $fSurStartEpoch
                $surStartDateTime = Date-ToString -value $gmtDate
            }
            if ($surEndEpoch -ne $null -and $surEndEpoch -ne "") {
                [double]$fSurEndEpoch = [double]$surEndEpoch
                $gmtDate = FromUnixTime $fSurEndEpoch
                $surEndDateTime = Date-ToString -value $gmtDate
            }

            if ($i % 100 -eq 0) {
                Write-Host "[ENSURE_CORRECT_DATETIME] -> File='$fileName', Processing LINE-$i"
            }

            $i++

            Add-Content $outFileFullPath "$surId,$surAnalyzerId,$surSurveyorUnitId,$surReferenceGasBottleId,$surUserId,$surSurveyModeTypeId,$surStartEpoch,$surEndEpoch,$surStartDateTime,$surEndDateTime,$surTag,$surStabilityClass,$surMinimumAmplitude,$surStatus,$surDeleted,$surProcessingDateStarted,$surLocationId,$surBuildNumber,$surProcessingDateCompleted"
        }
    }
}

Write-Host "##### ------------------------------------------------------------- #####"
Write-Host " Done cleaning CSV files "
Write-Host " Cleaned files can be found in directory: $outDirectory "
Write-Host "##### ------------------------------------------------------------- #####"

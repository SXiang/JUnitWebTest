<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - This script contains generic helper functions.

----------------------------------------------------------------------------------------------------------------------------------#>

<#--------------------------------------------------------------------------------------------------------------------------------
- DB Table and Column Names map. Used in generating Survey data csvs for automation seed data.
----------------------------------------------------------------------------------------------------------------------------------#>

# Column Heading Maps - (To maintain desired order in generated CSVs)
$COLUMN_HEADINGS = @{
"SurveyResult-"="SurveyId,FieldOfView,Breadcrumb";
"AnemometerRaw-"="AnalyzerId,EpochTime,WindSpeedLateral,WindSpeedLongitudinal,Status,Index";
"FieldOfView-"="AnalyzerId,EpochTime,Shape,SurveyId";
"GPSRaw-"="AnalyzerId,EpochTime,GpsTime,GpsLatitude,GpsLongitude,GpsFit,GPSLatitudeUncertainty,GPSLongitudeUncertainty";
"Measurement-"="AnalyzerId,EpochTime,CreateDate,GpsLatitude,GpsLongitude,GpsFit,Shape,InstrumentStatus,ValveMask,CarSpeedNorth,CarSpeedEast,WindSpeedNorth,WindSpeedEast,WindDirectionStdDev,WeatherStationRotation,WindSpeedLateral,WindSpeedLongitudinal,ChemDetect,Species,CH4,CO2,H2OPercent,DeltaCH4,PeripheralStatus,AnalyzerStatus,CavityPressure,WarmBoxTemperature,HotBoxTemperature,MobileFlowRate,AnalyzerMode,PeakDetectorState,C2H6,C2H4,AnalyzerEthaneConcentrationUncertainty";
"Note-"="Id,AnalyzerId,EpochTime,UserId,Lat,Lon,Text,Deleted";
"Peak-"="AnalyzerId,EpochTime,Amplitude,CH4,Position,Lisa,LisaOpeningAngle,LisaBearing,CarBearing,Major,Minor,CarSpeedNorth,CarSpeedEast,WindDirectionStdDev,WindSpeedNorth,WindSpeedEast,Sigma,Distance,GpsLatitude,GpsLongitude,PassedAutoThreshold,SurveyId,EthaneRatio,EthaneRatioSdevRaw,EthaneRatioSdev,EthaneConcentrationSdev,EthyleneRatio,EthyleneRatioSdevRaw,EthyleneRatioSdev,EthyleneConcentrationSdev,PipEnergy,MethanePeaktoPeak,Disposition,ClassificationConfidence";
"Segment-"="SurveyId,Order,Mode,Shape";
"Survey-"="Id,AnalyzerId,SurveyorUnitId,ReferenceGasBottleId,UserId,SurveyModeTypeId,StartEpoch,EndEpoch,StartDateTime,EndDateTime,Tag,StabilityClass,MinimumAmplitude,Status,Deleted,ProcessingDateStarted,LocationId,BuildNumber,ProcessingDateCompleted";
"SurveyCondition-"="Id,SurveyId,Name,Value";
"CaptureEvent-"="Id,AnalyzerId,EpochTime,DateTime,GpsLatitude,GpsLongitude,Shape,Disposition,Delta,Concentration,Uncertainty,CaptureType,Distance,ReplayMax,ReplayLMin,ReplayRMin,SurveyId,EthaneRatio,EthaneRatioSdev,ClassificationConfidence";
}

<#--------------------------------------------------------------------------------------------------------------------------------
- Generic helper methods.
----------------------------------------------------------------------------------------------------------------------------------#>

function Convert-StringArrayToNumericArray($strArray) {
    $intArray = @()
    if ($strArray) {
        $strArray | % {
            $intArray += @([int]$_)
        }
    }
    $intArray
}

function Bool-ToBit($value) {
    if ($value -eq $false) {
        "0"
    } else {
        "1"
    }
}


function Null-ToValue($value) {
    if ($value -eq $NULL) {
        "NULL"
    } else {
        if ($value.GetType().Name -eq "DBNull") {
            "NULL"
        } else {
            $value
        }
    }
}

function Geometry-ToText($value) {
    if ($value -eq $NULL -or $value.ToString() -eq "") {
        "NULL"
    } else {
        $hexValue = [System.BitConverter]::ToString($value)
        $hexValue = $hexValue.Replace("-", "")
        "0x$hexValue"
    }
}

function Date-ToString($value) {
    if ($value -eq $NULL -or $value.ToString() -eq "") {
        "NULL"
    } else {
        [String]::Format("{0:yyyy-MM-dd HH:mm:ss.fff}", $value)
    }
}

<#--------------------------------------------------------------------------------------------------------------------------------
- CSV related methods.
----------------------------------------------------------------------------------------------------------------------------------#>

function Get-LastLineFromCSV($csvFilePath, $colHeaderForSort, $colHeaderForStats) {
    $csvLines = Import-Csv $csvFilePath | Sort-Object $colHeaderForSort
    $stats = $csvLines | Measure-Object "$colHeaderForStats" -Average -Minimum -Maximum
    $linesCount = $stats.Count
    $csvLines[$linesCount-1]
}

<#--------------------------------------------------------------------------------------------------------------------------------
- Statistic computation helper methods.
----------------------------------------------------------------------------------------------------------------------------------#>

function Get-Average($csvFilePath, $colHeaderForAverage) {
    $csvLines = Import-Csv $csvFilePath
    $stats = $csvLines | Measure-Object "$colHeaderForAverage" -Average -Minimum -Maximum
    $stats.Average
}

function Get-NthPercentile($n, $csvFilePath, $colHeaderForPercentile) {
    $csvLines = Import-Csv $csvFilePath
    $stats = $csvLines | Measure-Object "$colHeaderForPercentile" -Average -Minimum -Maximum
    $linesCount = $stats.Count
    $csvColLines = $csvLines."$colHeaderForPercentile"
    if ($csvColLines.GetType().Name -eq "String") {
        [int]$csvColLines
    } else {
        $sortedLines = Convert-StringArrayToNumericArray -strArray $csvColLines | Sort-Object
        $upperBound = [int]($linesCount * ($n/100))
        [int]$sortedLines[$upperBound-1]
    }
}
<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION:
  - This script can be used to generate seed data survey CSV files for list of Survey Ids in database.

 Sample Run Script:
   .\Generate-SurveyCSVs.ps1 `
        -surveyIDs "abc74dfd-091f-17b0-acd7-39e1f7914d5e"  `
        -databaseIPAddress "20.20.130.210"  `
        -databaseName "SurveyorSQAAuto_blankDB_20170830"  `
        -databaseUser "awssa"  `
        -databasePassword "3Vf763pSg2"  `
        -outputFolder "C:\temp\SUR-121-Surveys"  `
        -addCustomerNameAsSuffix:$true   `
        -generateSurveyNameList:$true   `
        -disableRawDataGeneration:$true

 NOTE: Script assumes surveyIDs provided as input to the script are ordered by Survey Tags 
       (ie ascending or descending order of Surveys tags) for file indices to be computed correctly for the CSV files.
----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $surveyIDs,               # Comma-seperated list of survey id guids (ie [ID] from Survey table in DB)

  [Parameter(Mandatory=$true)]
  [String] $databaseIPAddress,       # Database where the surveys are located. eg. 20.20.130.238

  [Parameter(Mandatory=$true)]
  [String] $databaseName,            # Database name. eg. SurveyorSQA

  [Parameter(Mandatory=$true)]
  [String] $databaseUser,            # User with access to DB. Eg awssa

  [Parameter(Mandatory=$true)]
  [String] $databasePassword,        # DB user password

  [Parameter(Mandatory=$true)]
  [String] $outputFolder,            # Folder where Survey CSV files will be generated. Eg. 'C:\temp\SurveyCSVs'. NOTE: Existing files in this folder will be deleted.

  [Parameter(Mandatory=$false)]
  [switch] $addCustomerNameAsSuffix=$false, # set to 'true' to add customer name as suffix to generated survey csv files. eg. - 'AnemometerRaw-StandardSurveyEQ02-1-sqacus.csv'

  [Parameter(Mandatory=$false)]
  [switch] $generateSurveyNameList=$false,   # set to 'true' to generate code for survey array constant used in DbSeedExecutor.java

  [Parameter(Mandatory=$false)]
  [switch] $disableRawDataGeneration=$false      # set to 'true' to disable data generation for raw data tables (AnemometerRaw, GpsRaw, Measurement)
)

# Load helper functions.
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"

$connString = "Server=$databaseIPAddress;Database=$databaseName;User Id=$databaseUser;Password=$databasePassword;"
$counter = 1
$currSurveyTag = ""
$idsArr = $surveyIDs -split ","
$surveyIDArr = @($idsArr)
$script:fileTagMap = @{}

"Removing files from output folder -> $outputFolder ..."
Get-ChildItem "$outputFolder" -Filter "*.csv" | Remove-Item

$loggingEnabled = $true

if ($loggingEnabled) {
    $LOGFILE = New-Item "C:\temp\Generate-SurveyCSVsLog2.txt" -Force
}

function Write-Output($log) {
    if ($loggingEnabled) {
        Add-Content $LOGFILE $log
    } else {
        Write-Host $log
    }
}

function AddTo-FileTagMapTable($key, $value) 
{	
    if (-not $script:fileTagMap.ContainsKey($key)) {
        $fTagsList = New-Object System.Collections.ArrayList
        $null = $fTagsList.Add($value)
        $script:fileTagMap.Add($key, $fTagsList)
    } else {
        $fTagsList = [System.Collections.ArrayList]$script:fileTagMap.Get_Item($key)
        if (-not $fTagsList.Contains($value)) {
            $null = $fTagsList.Add($value)
        }
        $script:fileTagMap.Set_Item($key, $fTagsList)
    }        
}

# Start CSV creation.
$surveyIDArr | % {
    $surveyID = $_
    $surveyQuery = "SELECT S.[Id],S.[Tag],S.[StartEpoch] - 0.05 AS AdjustedStartEpoch,S.[EndEpoch] + 0.05 AS AdjustedEndEpoch,[AnalyzerId], C.Name AS CustomerName FROM [dbo].[Survey] AS S INNER JOIN [dbo].[Analyzer] AS A ON S.AnalyzerId=A.Id INNER JOIN [dbo].[SurveyorUnit] AS SU ON S.SurveyorUnitId=SU.Id INNER JOIN [dbo].[User] AS U ON S.UserId=U.Id INNER JOIN [dbo].[Customer] AS C ON U.CustomerId=C.Id  WHERE S.Id='$surveyId'"
    $objSurveys = Get-DatabaseData -connectionString $connString -query $surveyQuery -isSQLServer:$true

    $idx = 0

    $objSurveys | % {
        if ($idx -gt 0) {     # first row is length of array. datarows are from index=1
            # --- AnemometerRaw ---

            $obj = $_;
            $id = $obj.Id
            $tag = $obj.Tag
            $startEpoch = $obj.AdjustedStartEpoch
            $endEpoch = $obj.AdjustedEndEpoch
            $analyzerId = $obj.AnalyzerId.ToString()
            $customerName = $obj.CustomerName

            # if survey tag has changed, reset counter.
            if ($tag -ne $currSurveyTag) {
                $counter = 1
            }

            $currSurveyTag = $tag

            $fileTag = $tag.Replace("/", "-").Replace(" ", "-")
            if ($addCustomerNameAsSuffix) {
                if ($customerName -ne "Picarro") {
                    $fileTag += "-$customerName"
                }
            }

            AddTo-FileTagMapTable -key $customerName -value "$fileTag-$counter"

            if (-not $disableRawDataGeneration) {

                $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\AnemometerRaw-$fileTag-$counter.csv"
                Write-Output -log "Printing CSV header for AnemometerRaw -> Survey Tag: $tag"
                $COLUMN_HEADINGS.get_item("AnemometerRaw-")
                $colHeaders = $COLUMN_HEADINGS.get_item("AnemometerRaw-")
                Add-Content $OUTCSV "$colHeaders"

                $i1=0
                Write-Output -log "Fetching AnemometerRaw values for Survey Tag: $tag"
                $query = "SELECT * FROM [$databaseName].[dbo].[AnemometerRaw] WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId'"
                $objAnemometerRaw = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
                $objAnemometerRaw | foreach {
                    if ($i1 -gt 0) {     # first row is length of array. datarows are from index=1
                        Write-Output -log "Table -> AnemometerRaw, Survey Tag -> $tag - Processing row - $i1"

                        $objAne = $_;
                        $aneAnalyzerId = $objAne.AnalyzerId;
                        $aneEpochTime = $objAne.EpochTime;
                        $aneIndex = $objAne.Index;
                        $aneStatus = $objAne.Status;
                        $aneWindSpeedLateral = Null-ToValue -value $objAne.WindSpeedLateral;
                        $aneWindSpeedLongitudinal = Null-ToValue -value $objAne.WindSpeedLongitudinal;

                        Add-Content $OUTCSV "$aneAnalyzerId,$aneEpochTime,$aneWindSpeedLateral,$aneWindSpeedLongitudinal,$aneStatus,$aneIndex"
                    }

                    $i1++
                }
            }

            # --- CaptureEvent ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\CaptureEvent-$fileTag-$counter.csv"
            Write-Output -log "Printing CSV header for CaptureEvent -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("CaptureEvent-")
            $colHeaders = $COLUMN_HEADINGS.get_item("CaptureEvent-")
            Add-Content $OUTCSV "$colHeaders"

            $i2=0
            Write-Output -log "Fetching CaptureEvent values for Survey Tag: $tag"
            $query = "SELECT [Id],[AnalyzerId],[EpochTime],[DateTime],[GpsLatitude],[GpsLongitude],CONVERT(VARBINARY(MAX), [Shape]) AS Shape,[Disposition],[Delta],[Concentration],[Uncertainty],[CaptureType],[Distance],[ReplayMax],[ReplayLMin],[ReplayRMin],[SurveyId],[EthaneRatio],[EthaneRatioSdev],[ClassificationConfidence] FROM [$databaseName].[dbo].[CaptureEvent]  WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId' AND SurveyId='$id'"
            $objCaptureEvent = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objCaptureEvent | foreach {
                if ($i2 -gt 0) {     # first row is length of array. datarows are from index=1
                    Write-Output -log "Table -> CaptureEvent, Survey Tag -> $tag - Processing row - $i2"

                    $objCap = $_;
                    $capAnalyzerId = $objCap.AnalyzerId;
                    $capCaptureType = Bool-ToBit -value $objCap.CaptureType;
                    $capClassificationConfidence = Null-ToValue -value $objCap.ClassificationConfidence;
                    $capConcentration = $objCap.Concentration;
                    $capDateTime = Date-ToString -value $objCap.DateTime;
                    $capDelta = $objCap.Delta;
                    $capDisposition = $objCap.Disposition;
                    $capDistance = Null-ToValue -value $objCap.Distance;
                    $capEpochTime = $objCap.EpochTime;
                    $capEthaneRatio = Null-ToValue -value $objCap.EthaneRatio;
                    $capEthaneRatioSdev = Null-ToValue -value $objCap.EthaneRatioSdev;
                    $capGpsLatitude = $objCap.GpsLatitude;
                    $capGpsLongitude = $objCap.GpsLongitude;
                    $capId = $objCap.Id;
                    $capReplayLMin = $objCap.ReplayLMin;
                    $capReplayMax = $objCap.ReplayMax;
                    $capReplayRMin = $objCap.ReplayRMin;
                    $capShape = Geometry-ToText -value $objCap.Shape;
                    $capSurveyId = Null-ToValue -value $objCap.SurveyId;
                    $capUncertainty = $objCap.Uncertainty;

                    Add-Content $OUTCSV "$capId,$capAnalyzerId,$capEpochTime,$capDateTime,$capGpsLatitude,$capGpsLongitude,$capShape,$capDisposition,$capDelta,$capConcentration,$capUncertainty,$capCaptureType,$capDistance,$capReplayMax,$capReplayLMin,$capReplayRMin,$capSurveyId,$capEthaneRatio,$capEthaneRatioSdev,$capClassificationConfidence"
                }

                $i2++
            }

            # --- FieldOfView ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\FieldOfView-$fileTag-$counter.csv"
            Write-Output -log "Printing CSV header for FieldOfView -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("FieldOfView-")
            $colHeaders = $COLUMN_HEADINGS.get_item("FieldOfView-")
            Add-Content $OUTCSV "$colHeaders"

            $i3=0
            Write-Output -log "Fetching FieldOfView values for Survey Tag: $tag"
            $query = "SELECT [AnalyzerId],[EpochTime],CONVERT(VARBINARY(MAX), [Shape]) AS Shape,[SurveyId] FROM [$databaseName].[dbo].[FieldOfView] WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId' AND SurveyId='$id'"
            $objFieldOfView = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objFieldOfView | foreach {
                if ($i3 -gt 0) {     # first row is length of array. datarows are from index=1
                    Write-Output -log "Table -> FieldOfView, Survey Tag -> $tag - Processing row - $i3"

                    $objFie = $_;
                    $fieAnalyzerId = $objFie.AnalyzerId;
                    $fieEpochTime = $objFie.EpochTime;
                    $fieShape = Geometry-ToText -value $objFie.Shape;
                    $fieSurveyId = Null-ToValue -value $objFie.SurveyId;

                    Add-Content $OUTCSV "$fieAnalyzerId,$fieEpochTime,$fieShape,$fieSurveyId"
                }

                $i3++
            }

            # --- GPSRaw ---

            if (-not $disableRawDataGeneration) {

                $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\GPSRaw-$fileTag-$counter.csv"
                Write-Output -log "Printing CSV header for GPSRaw -> Survey Tag: $tag"
                $COLUMN_HEADINGS.get_item("GPSRaw-")
                $colHeaders = $COLUMN_HEADINGS.get_item("GPSRaw-")
                Add-Content $OUTCSV "$colHeaders"

                $i4=0
                Write-Output -log "Fetching GPSRaw values for Survey Tag: $tag"
                $query = "SELECT * FROM [$databaseName].[dbo].[GPSRaw] WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId'"
                $objGPSRaw = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
                $objGPSRaw | foreach {
                    if ($i4 -gt 0) {     # first row is length of array. datarows are from index=1
                        Write-Output -log "Table -> GPSRaw, Survey Tag -> $tag - Processing row - $i4"

                        $objGPS = $_;
                        $gPSAnalyzerId = $objGPS.AnalyzerId;
                        $gPSEpochTime = $objGPS.EpochTime;
                        $gPSGpsFit = Null-ToValue -value $objGPS.GpsFit;
                        $gPSGpsLatitude = Null-ToValue -value $objGPS.GpsLatitude;
                        $gPSGPSLatitudeUncertainty = Null-ToValue -value $objGPS.GPSLatitudeUncertainty;
                        $gPSGpsLongitude = Null-ToValue -value $objGPS.GpsLongitude;
                        $gPSGPSLongitudeUncertainty = Null-ToValue -value $objGPS.GPSLongitudeUncertainty;
                        $gPSGpsTime = Null-ToValue -value $objGPS.GpsTime;

                        Add-Content $OUTCSV "$gPSAnalyzerId,$gPSEpochTime,$gPSGpsTime,$gPSGpsLatitude,$gPSGpsLongitude,$gPSGpsFit,$gPSGPSLatitudeUncertainty,$gPSGPSLongitudeUncertainty"
                    }

                    $i4++
                }
            }

            # --- Measurement ---

            if (-not $disableRawDataGeneration) {

                $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\Measurement-$fileTag-$counter.csv"
                Write-Output -log "Printing CSV header for Measurement -> Survey Tag: $tag"
                $COLUMN_HEADINGS.get_item("Measurement-")
                $colHeaders = $COLUMN_HEADINGS.get_item("Measurement-")
                Add-Content $OUTCSV "$colHeaders"

                $i5=0
                Write-Output -log "Fetching Measurement values for Survey Tag: $tag"
                $query = "SELECT [AnalyzerId],[EpochTime],[CreateDate],[GpsLatitude],[GpsLongitude],[GpsFit],CONVERT(VARBINARY(MAX), [Shape]) AS Shape,[InstrumentStatus],[ValveMask],[CarSpeedNorth],[CarSpeedEast],[WindSpeedNorth],[WindSpeedEast],[WindDirectionStdDev],[WeatherStationRotation],[WindSpeedLateral],[WindSpeedLongitudinal],[ChemDetect],[Species],[CH4],[CO2],[H2OPercent],[DeltaCH4],[PeripheralStatus],[AnalyzerStatus],[CavityPressure],[WarmBoxTemperature],[HotBoxTemperature],[MobileFlowRate],[AnalyzerMode],[PeakDetectorState],[C2H6],[C2H4],[AnalyzerEthaneConcentrationUncertainty] FROM [$databaseName].[dbo].[Measurement] WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId'"
                $objMeasurement = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
                $objMeasurement | foreach {
                    if ($i5 -gt 0) {     # first row is length of array. datarows are from index=1
                        Write-Output -log "Table -> Measurement, Survey Tag -> $tag - Processing row - $i5"

                        $objMea = $_;
                        $meaAnalyzerEthaneConcentrationUncertainty = Null-ToValue -value $objMea.AnalyzerEthaneConcentrationUncertainty;
                        $meaAnalyzerId = $objMea.AnalyzerId;
                        $meaAnalyzerMode = $objMea.AnalyzerMode;
                        $meaAnalyzerStatus = Null-ToValue -value $objMea.AnalyzerStatus;
                        $meaC2H4 = Null-ToValue -value $objMea.C2H4;
                        $meaC2H6 = Null-ToValue -value $objMea.C2H6;
                        $meaCarSpeedEast = Null-ToValue -value $objMea.CarSpeedEast;
                        $meaCarSpeedNorth = Null-ToValue -value $objMea.CarSpeedNorth;
                        $meaCavityPressure = $objMea.CavityPressure;
                        $meaCH4 = Null-ToValue -value $objMea.CH4;
                        $meaChemDetect = Bool-ToBitWithNullCheck -value $objMea.ChemDetect;
                        $meaCO2 = Null-ToValue -value $objMea.CO2;
                        $meaCreateDate = Date-ToString -value $objMea.CreateDate;
                        $meaDeltaCH4 = Null-ToValue -value $objMea.DeltaCH4;
                        $meaEpochTime = $objMea.EpochTime;
                        $meaGpsFit = Null-ToValue -value $objMea.GpsFit;
                        $meaGpsLatitude = Null-ToValue -value $objMea.GpsLatitude;
                        $meaGpsLongitude = Null-ToValue -value $objMea.GpsLongitude;
                        $meaH2OPercent = Null-ToValue -value $objMea.H2OPercent;
                        $meaHotBoxTemperature = $objMea.HotBoxTemperature;
                        $meaInstrumentStatus = $objMea.InstrumentStatus;
                        $meaMobileFlowRate = $objMea.MobileFlowRate;
                        $meaPeakDetectorState = Null-ToValue -value $objMea.PeakDetectorState;
                        $meaPeripheralStatus = Null-ToValue -value $objMea.PeripheralStatus;
                        $meaShape = Geometry-ToText -value $objMea.Shape;
                        $meaSpecies = Null-ToValue -value $objMea.Species;
                        $meaValveMask = $objMea.ValveMask;
                        $meaWarmBoxTemperature = $objMea.WarmBoxTemperature;
                        $meaWeatherStationRotation = Null-ToValue -value $objMea.WeatherStationRotation;
                        $meaWindDirectionStdDev = Null-ToValue -value $objMea.WindDirectionStdDev;
                        $meaWindSpeedEast = Null-ToValue -value $objMea.WindSpeedEast;
                        $meaWindSpeedLateral = Null-ToValue -value $objMea.WindSpeedLateral;
                        $meaWindSpeedLongitudinal = Null-ToValue -value $objMea.WindSpeedLongitudinal;
                        $meaWindSpeedNorth = Null-ToValue -value $objMea.WindSpeedNorth;

                        Add-Content $OUTCSV "$meaAnalyzerId,$meaEpochTime,$meaCreateDate,$meaGpsLatitude,$meaGpsLongitude,$meaGpsFit,$meaShape,$meaInstrumentStatus,$meaValveMask,$meaCarSpeedNorth,$meaCarSpeedEast,$meaWindSpeedNorth,$meaWindSpeedEast,$meaWindDirectionStdDev,$meaWeatherStationRotation,$meaWindSpeedLateral,$meaWindSpeedLongitudinal,$meaChemDetect,$meaSpecies,$meaCH4,$meaCO2,$meaH2OPercent,$meaDeltaCH4,$meaPeripheralStatus,$meaAnalyzerStatus,$meaCavityPressure,$meaWarmBoxTemperature,$meaHotBoxTemperature,$meaMobileFlowRate,$meaAnalyzerMode,$meaPeakDetectorState,$meaC2H6,$meaC2H4,$meaAnalyzerEthaneConcentrationUncertainty"
                    }

                    $i5++
                }
            }

            # --- Peak ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\Peak-$fileTag-$counter.csv"
            Write-Output -log "Printing CSV header for Peak -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("Peak-")
            $colHeaders = $COLUMN_HEADINGS.get_item("Peak-")
            Add-Content $OUTCSV "$colHeaders"

            $i7=0
            Write-Output -log "Fetching Peak values for Survey Tag: $tag"
            $query = "SELECT [AnalyzerId],[EpochTime],[Amplitude],[CH4],CONVERT(VARBINARY(MAX), [Position]) AS Position,CONVERT(VARBINARY(MAX), [Lisa]) AS Lisa,[LisaOpeningAngle],[LisaBearing],[CarBearing],[Major],[Minor],[CarSpeedNorth],[CarSpeedEast],[WindDirectionStdDev],[WindSpeedNorth],[WindSpeedEast],[Sigma],[Distance],[GpsLatitude],[GpsLongitude],[PassedAutoThreshold],[SurveyId],[EthaneRatio],[EthaneRatioSdevRaw],[EthaneRatioSdev],[EthaneConcentrationSdev],[EthyleneRatio],[EthyleneRatioSdevRaw],[EthyleneRatioSdev],[EthyleneConcentrationSdev],[PipEnergy],[MethanePeaktoPeak],[Disposition],[ClassificationConfidence] FROM [$databaseName].[dbo].[Peak] WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId' AND SurveyId='$id'"
            $objPeak = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objPeak | foreach {
                if ($i7 -gt 0) {     # first row is length of array. datarows are from index=1
                    Write-Output -log "Table -> Peak, Survey Tag -> $tag - Processing row - $i7"

                    $objPea = $_;
                    $peaAmplitude = $objPea.Amplitude;
                    $peaAnalyzerId = $objPea.AnalyzerId;
                    $peaCarBearing = $objPea.CarBearing;
                    $peaCarSpeedEast = Null-ToValue -value $objPea.CarSpeedEast;
                    $peaCarSpeedNorth = Null-ToValue -value $objPea.CarSpeedNorth;
                    $peaCH4 = $objPea.CH4;
                    $peaClassificationConfidence = Null-ToValue -value $objPea.ClassificationConfidence;
                    $peaDisposition = $objPea.Disposition;
                    $peaDistance = $objPea.Distance;
                    $peaEpochTime = $objPea.EpochTime;
                    $peaEthaneConcentrationSdev = Null-ToValue -value $objPea.EthaneConcentrationSdev;
                    $peaEthaneRatio = Null-ToValue -value $objPea.EthaneRatio;
                    $peaEthaneRatioSdev = Null-ToValue -value $objPea.EthaneRatioSdev;
                    $peaEthaneRatioSdevRaw = Null-ToValue -value $objPea.EthaneRatioSdevRaw;
                    $peaEthyleneConcentrationSdev = Null-ToValue -value $objPea.EthyleneConcentrationSdev;
                    $peaEthyleneRatio = Null-ToValue -value $objPea.EthyleneRatio;
                    $peaEthyleneRatioSdev = Null-ToValue -value $objPea.EthyleneRatioSdev;
                    $peaEthyleneRatioSdevRaw = Null-ToValue -value $objPea.EthyleneRatioSdevRaw;
                    $peaGpsLatitude = $objPea.GpsLatitude;
                    $peaGpsLongitude = $objPea.GpsLongitude;
                    $peaLisa = Geometry-ToText -value $objPea.Lisa;
                    $peaLisaBearing = $objPea.LisaBearing;
                    $peaLisaOpeningAngle = $objPea.LisaOpeningAngle;
                    $peaMajor = $objPea.Major;
                    $peaMethanePeaktoPeak = Null-ToValue -value $objPea.MethanePeaktoPeak;
                    $peaMinor = $objPea.Minor;
                    $peaPassedAutoThreshold = Bool-ToBit -value $objPea.PassedAutoThreshold;
                    $peaPipEnergy = Null-ToValue -value $objPea.PipEnergy;
                    $peaPosition = Geometry-ToText -value $objPea.Position;
                    $peaSigma = $objPea.Sigma;
                    $peaSurveyId = Null-ToValue -value $objPea.SurveyId;
                    $peaSurvivedCollection = Bool-ToBit -value $objPea.SurvivedCollection;
                    $peaWindDirectionStdDev = Null-ToValue -value $objPea.WindDirectionStdDev;
                    $peaWindSpeedEast = Null-ToValue -value $objPea.WindSpeedEast;
                    $peaWindSpeedNorth = Null-ToValue -value $objPea.WindSpeedNorth;

                    Add-Content $OUTCSV "$peaAnalyzerId,$peaEpochTime,$peaAmplitude,$peaCH4,$peaPosition,$peaLisa,$peaLisaOpeningAngle,$peaLisaBearing,$peaCarBearing,$peaMajor,$peaMinor,$peaCarSpeedNorth,$peaCarSpeedEast,$peaWindDirectionStdDev,$peaWindSpeedNorth,$peaWindSpeedEast,$peaSigma,$peaDistance,$peaGpsLatitude,$peaGpsLongitude,$peaPassedAutoThreshold,$peaSurveyId,$peaEthaneRatio,$peaEthaneRatioSdevRaw,$peaEthaneRatioSdev,$peaEthaneConcentrationSdev,$peaEthyleneRatio,$peaEthyleneRatioSdevRaw,$peaEthyleneRatioSdev,$peaEthyleneConcentrationSdev,$peaPipEnergy,$peaMethanePeaktoPeak,$peaDisposition,$peaClassificationConfidence,$peaSurvivedCollection"
                }

                $i7++
            }

            # --- Segment ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\Segment-$fileTag-$counter.csv"
            Write-Output -log "Printing CSV header for Segment -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("Segment-")
            $colHeaders = $COLUMN_HEADINGS.get_item("Segment-")
            Add-Content $OUTCSV "$colHeaders"

            $i8=0
            Write-Output -log "Fetching Segment values for Survey Tag: $tag"
            $query = "SELECT [SurveyId],[Order],[Mode],CONVERT(VARBINARY(MAX), [Shape]) AS Shape FROM [$databaseName].[dbo].[Segment] WHERE SurveyId='$id'"
            $objSegment = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSegment | foreach {
                if ($i8 -gt 0) {     # first row is length of array. datarows are from index=1
                    Write-Output -log "Table -> Segment, Survey Tag -> $tag - Processing row - $i8"

                    $objSeg = $_;
                    $segMode = $objSeg.Mode;
                    $segOrder = $objSeg.Order;
                    $segShape = Geometry-ToText -value $objSeg.Shape;
                    $segSurveyId = $objSeg.SurveyId;

                    Add-Content $OUTCSV "$segSurveyId,$segOrder,$segMode,$segShape"
                }

                $i8++
            }

            # --- Survey ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\Survey-$fileTag-$counter.csv"
            Write-Output -log "Printing CSV header for Survey -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("Survey-")
            $colHeaders = $COLUMN_HEADINGS.get_item("Survey-")
            Add-Content $OUTCSV "$colHeaders"

            $i9=0
            Write-Output -log "Fetching Survey values for Survey Tag: $tag"
            $query = "SELECT * FROM [$databaseName].[dbo].[Survey] WHERE Id='$id'"
            $objSurvey = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSurvey | foreach {
                if ($i9 -gt 0) {     # first row is length of array. datarows are from index=1
                    Write-Output -log "Table -> Survey, Survey Tag -> $tag - Processing row - $i9"

                    $objSur = $_;
                    $surAnalyzerId = $objSur.AnalyzerId;
                    $surBuildNumber = Null-ToValue -value $objSur.BuildNumber;
                    $surDeleted = Bool-ToBit -value $objSur.Deleted;
                    $surEndDateTime = Date-ToString -value $objSur.EndDateTime;
                    $surEndEpoch = $objSur.EndEpoch;
                    $surId = $objSur.Id;
                    $surLocationId = Null-ToValue -value $objSur.LocationId;
                    $surMinimumAmplitude = Null-ToValue -value $objSur.MinimumAmplitude;
                    $surProcessingDateCompleted = Date-ToString -value $objSur.ProcessingDateCompleted;
                    $surProcessingDateStarted = Date-ToString -value $objSur.ProcessingDateStarted;
                    $surReferenceGasBottleId = $objSur.ReferenceGasBottleId;
                    $surSnapped = Bool-ToBit -value $objSur.Snapped;
                    $surStabilityClass = $objSur.StabilityClass;
                    $surStartDateTime = Date-ToString -value $objSur.StartDateTime;
                    $surStartEpoch = $objSur.StartEpoch;
                    $surStatus = $objSur.Status;
                    $surSurveyModeTypeId = $objSur.SurveyModeTypeId;
                    $surSurveyorUnitId = $objSur.SurveyorUnitId;
                    $surTag = Null-ToValue -value $objSur.Tag;
                    $surUserId = $objSur.UserId;

                    Add-Content $OUTCSV "$surId,$surAnalyzerId,$surSurveyorUnitId,$surReferenceGasBottleId,$surUserId,$surSurveyModeTypeId,$surStartEpoch,$surEndEpoch,$surStartDateTime,$surEndDateTime,$surTag,$surStabilityClass,$surMinimumAmplitude,$surStatus,$surDeleted,$surProcessingDateStarted,$surLocationId,$surBuildNumber,$surProcessingDateCompleted,$surSnapped"
                }

                $i9++
            }

            # --- SurveyCondition ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\SurveyCondition-$fileTag-$counter.csv"
            Write-Output -log "Printing CSV header for SurveyCondition -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("SurveyCondition-")
            $colHeaders = $COLUMN_HEADINGS.get_item("SurveyCondition-")
            Add-Content $OUTCSV "$colHeaders"

            $i10=0
            Write-Output -log "Fetching SurveyCondition values for Survey Tag: $tag"
            $query = "SELECT * FROM [$databaseName].[dbo].[SurveyCondition] WHERE SurveyId='$id'"
            $objSurveyCondition = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSurveyCondition | foreach {
                if ($i10 -gt 0) {     # first row is length of array. datarows are from index=1
                    Write-Output -log "Table -> SurveyCondition, Survey Tag -> $tag - Processing row - $i10"

                    $objSur = $_;
                    $surId = $objSur.Id;
                    $surName = $objSur.Name;
                    $surSurveyId = $objSur.SurveyId;
                    $surValue = $objSur.Value;

                    Add-Content $OUTCSV "$surId,$surSurveyId,$surName,$surValue"
                }

                $i10++
            }

            # --- SurveyResult ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\SurveyResult-$fileTag-$counter.csv"
            Write-Output -log "Printing CSV header for SurveyResult -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("SurveyResult-")
            $colHeaders = $COLUMN_HEADINGS.get_item("SurveyResult-")
            Add-Content $OUTCSV "$colHeaders"

            $i11=0
            Write-Output -log "Fetching SurveyResult values for Survey Tag: $tag"
            $query = "SELECT SurveyId,CONVERT(VARBINARY(MAX), [FieldOfView]) AS FieldOfView,CONVERT(VARBINARY(MAX), [Breadcrumb]) AS Breadcrumb FROM [$databaseName].[dbo].[SurveyResult] WHERE SurveyId='$id'"
            $objSurveyResult = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSurveyResult | foreach {
                if ($i11 -gt 0) {     # first row is length of array. datarows are from index=1
                    Write-Output -log "Table -> SurveyResult, Survey Tag -> $tag - Processing row - $i11"

                    $objSur = $_;
                    $surBreadcrumb = Geometry-ToText -value $objSur.Breadcrumb;
                    $surFieldOfView = Geometry-ToText -value $objSur.FieldOfView;
                    $surSurveyId = $objSur.SurveyId;

                    Add-Content $OUTCSV "$surSurveyId,$surFieldOfView,$surBreadcrumb"
                }

                $i11++
            }

            # --- SurveyResult-Geom ---

            # Create GEOM file for SurveyResult
            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\SurveyResult-Geom-$fileTag-$counter.csv"
            $i12=0
            Write-Output -log "Generating SurveyResult-Geom file for Survey Tag: $tag"
            $query = "SELECT [FieldOfView].STAsText() AS SurveyResultFieldOfViewAsWKT FROM [$databaseName].[dbo].[SurveyResult] WHERE SurveyId='$id'"
            $objSurveyResult = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSurveyResult | foreach {
                if ($i12 -gt 0) {     # first row is length of array. datarows are from index=1
                    Write-Output -log "Table -> SurveyResult(-geom), Survey Tag -> $tag - Processing row - $i12"

                    $surSurveyResultFieldOfViewAsWKT = $_.SurveyResultFieldOfViewAsWKT;

                    Add-Content $OUTCSV "$surSurveyResultFieldOfViewAsWKT"
                }

                $i12++
            }

            # --- Segment-Geom ---

            # Create GEOM file for Segment
            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\Segment-Geom-$fileTag-$counter.csv"
            $i13=0
            Write-Output -log "Generating Segment-Geom file for Survey Tag: $tag"
            $query = "SELECT [Shape].STAsText() AS SegmentShapeWKT FROM [$databaseName].[dbo].[Segment] WHERE SurveyId='$id'"
            $objSegment = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSegment | foreach {
                if ($i13 -gt 0) {     # first row is length of array. datarows are from index=1
                    Write-Output -log "Table -> Segment, Survey Tag -> $tag - Processing row - $i13"

                    $segSegmentShapeWKT = $_.SegmentShapeWKT;

                    Add-Content $OUTCSV "$segSegmentShapeWKT"
                }

                $i13++
            }
        }

        $idx++
    }

    $counter++
}

"###################################################################"
" "
" Survey csv files generated at -> $outputFolder"
" "
"###################################################################"


if ($generateSurveyNameList) {
    $OUTFILE = New-Item "C:\temp\SurveyNamesList2.txt" -Force
    Write-Output -log "Generating survey name list at -> $OUTFILE ..."

    $script:fileTagMap.Keys | Sort-Object | %{
        [String]$key = [String]$_
        $custUpper = $key.ToUpper()
        add-content $OUTFILE "public static final String[] ${custUpper}_CUSTOMER_SURVEYS = {"
        $fileTagList = [System.Collections.ArrayList]$script:fileTagMap.get_item($key)
        $i = 0
        $line = ""
        $fileTagList | % {
            $fTag = $_
            if ($i -eq 0) {
                $line += """$fTag"""
            } else {
                $line += ", ""$fTag"""
            }

            $i++
        }

        add-content $OUTFILE "$line };"
        add-content $OUTFILE ""
    }

    ii $OUTFILE
}

if ($loggingEnabled) {
    "------------------------------------------------------------------"
    " Output log file created at -> $LOGFILE "
    "------------------------------------------------------------------"
} 
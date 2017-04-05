<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION: 
  - This script can be used to generate seed data survey CSV files for list of Survey Ids in database.
  
 Sample Run Script: 
   .\Generate-SurveyCSVs.ps1 `
        -surveyIDs "DA2C4869-58DD-70FE-3B6A-39DD2C131AAA,A0EE2DB5-1B37-B1B0-554C-39DD2C1D41B5,F5D5BF4C-A286-BB4E-9C08-39DD2C310655"  `
        -databaseIPAddress "20.20.130.238"  `
        -databaseName "SurveyorSQA"  `
        -databaseUser "awssa"  `
        -databasePassword "<password>"  `
        -outputFolder "C:\temp\SurveyCSVs"  
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
  [String] $outputFolder             # Folder where Survey CSV files will be generated. Eg. 'C:\temp\SurveyCSVs'. NOTE: Existing files in this folder will be deleted.
)

# Load helper functions.
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"

$connString = "Server=$databaseIPAddress;Database=$databaseName;User Id=$databaseUser;Password=$databasePassword;"
$counter = 1
$currSurveyTag = ""
$idsArr = $surveyIDs -split ","
$surveyIDArr = @($idsArr)

"Removing files from output folder -> $outputFolder ..."
Get-ChildItem "$outputFolder" -Filter "*.csv" | Remove-Item

# Start CSV creation.
$surveyIDArr | % {
    $surveyID = $_
    $surveyQuery = "SELECT [Id],[Tag],[StartEpoch] - 0.05 AS AdjustedStartEpoch,[EndEpoch] + 0.05 AS AdjustedEndEpoch,[AnalyzerId] FROM [$databaseName].[dbo].[Survey] WHERE Id IN ('$surveyId')"
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

            # if survey tag has changed, reset counter.
            if ($tag -ne $currSurveyTag) {
                $counter = 1
            }

            $currSurveyTag = $tag

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\AnemometerRaw-$tag-$counter.csv"
            Write-Host "Printing CSV header for AnemometerRaw -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("AnemometerRaw-")
            $colHeaders = $COLUMN_HEADINGS.get_item("AnemometerRaw-")
            Add-Content $OUTCSV "$colHeaders"

            $i1=0
            Write-Host "Fetching AnemometerRaw values for Survey Tag: $tag"
            $query = "SELECT * FROM [$databaseName].[dbo].[AnemometerRaw] WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId'"
            $objAnemometerRaw = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objAnemometerRaw | foreach {
                if ($i1 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> AnemometerRaw, Survey Tag -> $tag - Processing row - $i1"
                    
                    $objAne = $_;
                    $aneAnalyzerId = Null-ToValue -value $objAne.AnalyzerId;
                    $aneEpochTime = Null-ToValue -value $objAne.EpochTime;
                    $aneIndex = Null-ToValue -value $objAne.Index;
                    $aneStatus = Null-ToValue -value $objAne.Status;
                    $aneWindSpeedLateral = $objAne.WindSpeedLateral;
                    $aneWindSpeedLongitudinal = $objAne.WindSpeedLongitudinal;

                    Add-Content $OUTCSV "$aneAnalyzerId,$aneEpochTime,$aneWindSpeedLateral,$aneWindSpeedLongitudinal,$aneStatus,$aneIndex"
                }

                $i1++
            }

            # --- CaptureEvent ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\CaptureEvent-$tag-$counter.csv"
            Write-Host "Printing CSV header for CaptureEvent -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("CaptureEvent-")
            $colHeaders = $COLUMN_HEADINGS.get_item("CaptureEvent-")
            Add-Content $OUTCSV "$colHeaders"

            $i2=0
            Write-Host "Fetching CaptureEvent values for Survey Tag: $tag"
            $query = "SELECT [Id],[AnalyzerId],[EpochTime],[DateTime],[GpsLatitude],[GpsLongitude],CONVERT(VARBINARY(MAX), [Shape]) AS Shape,[Disposition],[Delta],[Concentration],[Uncertainty],[CaptureType],[Distance],[ReplayMax],[ReplayLMin],[ReplayRMin],[SurveyId],[EthaneRatio],[EthaneRatioSdev],[ClassificationConfidence] FROM [$databaseName].[dbo].[CaptureEvent]  WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId' AND SurveyId='$id'"
            $objCaptureEvent = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objCaptureEvent | foreach {
                if ($i2 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> CaptureEvent, Survey Tag -> $tag - Processing row - $i2"
                    
                    $objCap = $_;
                    $capAnalyzerId = Null-ToValue -value $objCap.AnalyzerId;
                    $capCaptureType = Bool-ToBit -value $objCap.CaptureType;
                    $capClassificationConfidence = $objCap.ClassificationConfidence;
                    $capConcentration = Null-ToValue -value $objCap.Concentration;
                    $capDateTime = Date-ToString -value $objCap.DateTime;
                    $capDelta = Null-ToValue -value $objCap.Delta;
                    $capDisposition = Null-ToValue -value $objCap.Disposition;
                    $capDistance = $objCap.Distance;
                    $capEpochTime = Null-ToValue -value $objCap.EpochTime;
                    $capEthaneRatio = $objCap.EthaneRatio;
                    $capEthaneRatioSdev = $objCap.EthaneRatioSdev;
                    $capGpsLatitude = Null-ToValue -value $objCap.GpsLatitude;
                    $capGpsLongitude = Null-ToValue -value $objCap.GpsLongitude;
                    $capId = Null-ToValue -value $objCap.Id;
                    $capReplayLMin = Null-ToValue -value $objCap.ReplayLMin;
                    $capReplayMax = Null-ToValue -value $objCap.ReplayMax;
                    $capReplayRMin = Null-ToValue -value $objCap.ReplayRMin;
                    $capShape = Geometry-ToText -value $objCap.Shape;
                    $capSurveyId = $objCap.SurveyId;
                    $capUncertainty = Null-ToValue -value $objCap.Uncertainty;

                    Add-Content $OUTCSV "$capId,$capAnalyzerId,$capEpochTime,$capDateTime,$capGpsLatitude,$capGpsLongitude,$capShape,$capDisposition,$capDelta,$capConcentration,$capUncertainty,$capCaptureType,$capDistance,$capReplayMax,$capReplayLMin,$capReplayRMin,$capSurveyId,$capEthaneRatio,$capEthaneRatioSdev,$capClassificationConfidence"
                }

                $i2++
            }

            # --- FieldOfView ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\FieldOfView-$tag-$counter.csv"
            Write-Host "Printing CSV header for FieldOfView -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("FieldOfView-")
            $colHeaders = $COLUMN_HEADINGS.get_item("FieldOfView-")
            Add-Content $OUTCSV "$colHeaders"

            $i3=0
            Write-Host "Fetching FieldOfView values for Survey Tag: $tag"
            $query = "SELECT [AnalyzerId],[EpochTime],CONVERT(VARBINARY(MAX), [Shape]) AS Shape,[SurveyId] FROM [$databaseName].[dbo].[FieldOfView] WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId' AND SurveyId='$id'"
            $objFieldOfView = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objFieldOfView | foreach {
                if ($i3 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> FieldOfView, Survey Tag -> $tag - Processing row - $i3"
                    
                    $objFie = $_;
                    $fieAnalyzerId = Null-ToValue -value $objFie.AnalyzerId;
                    $fieEpochTime = Null-ToValue -value $objFie.EpochTime;
                    $fieShape = Geometry-ToText -value $objFie.Shape;
                    $fieSurveyId = $objFie.SurveyId;

                    Add-Content $OUTCSV "$fieAnalyzerId,$fieEpochTime,$fieShape,$fieSurveyId"
                }

                $i3++
            }

            # --- GPSRaw ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\GPSRaw-$tag-$counter.csv"
            Write-Host "Printing CSV header for GPSRaw -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("GPSRaw-")
            $colHeaders = $COLUMN_HEADINGS.get_item("GPSRaw-")
            Add-Content $OUTCSV "$colHeaders"

            $i4=0
            Write-Host "Fetching GPSRaw values for Survey Tag: $tag"
            $query = "SELECT * FROM [$databaseName].[dbo].[GPSRaw] WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId'"
            $objGPSRaw = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objGPSRaw | foreach {
                if ($i4 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> GPSRaw, Survey Tag -> $tag - Processing row - $i4"
                    
                    $objGPS = $_;
                    $gPSAnalyzerId = Null-ToValue -value $objGPS.AnalyzerId;
                    $gPSEpochTime = Null-ToValue -value $objGPS.EpochTime;
                    $gPSGpsFit = $objGPS.GpsFit;
                    $gPSGpsLatitude = $objGPS.GpsLatitude;
                    $gPSGPSLatitudeUncertainty = $objGPS.GPSLatitudeUncertainty;
                    $gPSGpsLongitude = $objGPS.GpsLongitude;
                    $gPSGPSLongitudeUncertainty = $objGPS.GPSLongitudeUncertainty;
                    $gPSGpsTime = $objGPS.GpsTime;

                    Add-Content $OUTCSV "$gPSAnalyzerId,$gPSEpochTime,$gPSGpsTime,$gPSGpsLatitude,$gPSGpsLongitude,$gPSGpsFit,$gPSGPSLatitudeUncertainty,$gPSGPSLongitudeUncertainty"
                }

                $i4++
            }

            # --- Measurement ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\Measurement-$tag-$counter.csv"
            Write-Host "Printing CSV header for Measurement -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("Measurement-")
            $colHeaders = $COLUMN_HEADINGS.get_item("Measurement-")
            Add-Content $OUTCSV "$colHeaders"

            $i5=0
            Write-Host "Fetching Measurement values for Survey Tag: $tag"
            $query = "SELECT [AnalyzerId],[EpochTime],[CreateDate],[GpsLatitude],[GpsLongitude],[GpsFit],CONVERT(VARBINARY(MAX), [Shape]) AS Shape,[InstrumentStatus],[ValveMask],[CarSpeedNorth],[CarSpeedEast],[WindSpeedNorth],[WindSpeedEast],[WindDirectionStdDev],[WeatherStationRotation],[WindSpeedLateral],[WindSpeedLongitudinal],[ChemDetect],[Species],[CH4],[CO2],[H2OPercent],[DeltaCH4],[PeripheralStatus],[AnalyzerStatus],[CavityPressure],[WarmBoxTemperature],[HotBoxTemperature],[MobileFlowRate],[AnalyzerMode],[PeakDetectorState],[C2H6],[C2H4],[AnalyzerEthaneConcentrationUncertainty] FROM [$databaseName].[dbo].[Measurement] WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId'"
            $objMeasurement = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objMeasurement | foreach {
                if ($i5 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> Measurement, Survey Tag -> $tag - Processing row - $i5"
                    
                    $objMea = $_;
                    $meaAnalyzerEthaneConcentrationUncertainty = $objMea.AnalyzerEthaneConcentrationUncertainty;
                    $meaAnalyzerId = Null-ToValue -value $objMea.AnalyzerId;
                    $meaAnalyzerMode = Null-ToValue -value $objMea.AnalyzerMode;
                    $meaAnalyzerStatus = $objMea.AnalyzerStatus;
                    $meaC2H4 = $objMea.C2H4;
                    $meaC2H6 = $objMea.C2H6;
                    $meaCarSpeedEast = $objMea.CarSpeedEast;
                    $meaCarSpeedNorth = $objMea.CarSpeedNorth;
                    $meaCavityPressure = Null-ToValue -value $objMea.CavityPressure;
                    $meaCH4 = $objMea.CH4;
                    $meaChemDetect = Bool-ToBit -value $objMea.ChemDetect;
                    $meaCO2 = $objMea.CO2;
                    $meaCreateDate = Date-ToString -value $objMea.CreateDate;
                    $meaDeltaCH4 = $objMea.DeltaCH4;
                    $meaEpochTime = Null-ToValue -value $objMea.EpochTime;
                    $meaGpsFit = $objMea.GpsFit;
                    $meaGpsLatitude = $objMea.GpsLatitude;
                    $meaGpsLongitude = $objMea.GpsLongitude;
                    $meaH2OPercent = $objMea.H2OPercent;
                    $meaHotBoxTemperature = Null-ToValue -value $objMea.HotBoxTemperature;
                    $meaInstrumentStatus = Null-ToValue -value $objMea.InstrumentStatus;
                    $meaMobileFlowRate = Null-ToValue -value $objMea.MobileFlowRate;
                    $meaPeakDetectorState = $objMea.PeakDetectorState;
                    $meaPeripheralStatus = $objMea.PeripheralStatus;
                    $meaShape = Geometry-ToText -value $objMea.Shape;
                    $meaSpecies = $objMea.Species;
                    $meaValveMask = Null-ToValue -value $objMea.ValveMask;
                    $meaWarmBoxTemperature = Null-ToValue -value $objMea.WarmBoxTemperature;
                    $meaWeatherStationRotation = $objMea.WeatherStationRotation;
                    $meaWindDirectionStdDev = $objMea.WindDirectionStdDev;
                    $meaWindSpeedEast = $objMea.WindSpeedEast;
                    $meaWindSpeedLateral = $objMea.WindSpeedLateral;
                    $meaWindSpeedLongitudinal = $objMea.WindSpeedLongitudinal;
                    $meaWindSpeedNorth = $objMea.WindSpeedNorth;

                    Add-Content $OUTCSV "$meaAnalyzerId,$meaEpochTime,$meaCreateDate,$meaGpsLatitude,$meaGpsLongitude,$meaGpsFit,$meaShape,$meaInstrumentStatus,$meaValveMask,$meaCarSpeedNorth,$meaCarSpeedEast,$meaWindSpeedNorth,$meaWindSpeedEast,$meaWindDirectionStdDev,$meaWeatherStationRotation,$meaWindSpeedLateral,$meaWindSpeedLongitudinal,$meaChemDetect,$meaSpecies,$meaCH4,$meaCO2,$meaH2OPercent,$meaDeltaCH4,$meaPeripheralStatus,$meaAnalyzerStatus,$meaCavityPressure,$meaWarmBoxTemperature,$meaHotBoxTemperature,$meaMobileFlowRate,$meaAnalyzerMode,$meaPeakDetectorState,$meaC2H6,$meaC2H4,$meaAnalyzerEthaneConcentrationUncertainty"
                }

                $i5++
            }

            # --- Note ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\Note-$tag-$counter.csv"
            Write-Host "Printing CSV header for Note -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("Note-")
            $colHeaders = $COLUMN_HEADINGS.get_item("Note-")
            Add-Content $OUTCSV "$colHeaders"

            $i6=0
            Write-Host "Fetching Note values for Survey Tag: $tag"
            $query = "SELECT * FROM [$databaseName].[dbo].[Note] WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId'"
            $objNote = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objNote | foreach {
                if ($i6 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> Note, Survey Tag -> $tag - Processing row - $i6"
                    
                    $objNot = $_;
                    $notAnalyzerId = Null-ToValue -value $objNot.AnalyzerId;
                    $notDeleted = Null-ToValue -value $objNot.Deleted;
                    $notEpochTime = Null-ToValue -value $objNot.EpochTime;
                    $notId = Null-ToValue -value $objNot.Id;
                    $notLat = Null-ToValue -value $objNot.Lat;
                    $notLon = Null-ToValue -value $objNot.Lon;
                    $notText = $objNot.Text;
                    $notUserId = Null-ToValue -value $objNot.UserId;

                    Add-Content $OUTCSV "$notId,$notAnalyzerId,$notEpochTime,$notUserId,$notLat,$notLon,$notText,$notDeleted"
                }

                $i6++
            }

            # --- Peak ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\Peak-$tag-$counter.csv"
            Write-Host "Printing CSV header for Peak -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("Peak-")
            $colHeaders = $COLUMN_HEADINGS.get_item("Peak-")
            Add-Content $OUTCSV "$colHeaders"

            $i7=0
            Write-Host "Fetching Peak values for Survey Tag: $tag"
            "SELECT [AnalyzerId],[EpochTime],[Amplitude],[CH4],CONVERT(VARBINARY(MAX), [Position]) AS Position,CONVERT(VARBINARY(MAX), [Lisa]) AS Lisa,[LisaOpeningAngle],[LisaBearing],[CarBearing],[Major],[Minor],[CarSpeedNorth],[CarSpeedEast],[WindDirectionStdDev],[WindSpeedNorth],[WindSpeedEast],[Sigma],[Distance],[GpsLatitude],[GpsLongitude],[PassedAutoThreshold],[SurveyId],[EthaneRatio],[EthaneRatioSdevRaw],[EthaneRatioSdev],[EthaneConcentrationSdev],[EthyleneRatio],[EthyleneRatioSdevRaw],[EthyleneRatioSdev],[EthyleneConcentrationSdev],[PipEnergy],[MethanePeaktoPeak],[Disposition],[ClassificationConfidence] FROM [$databaseName].[dbo].[Peak] WHERE EpochTime >= $startEpoch AND EpochTime <= $endEpoch AND AnalyzerId='$analyzerId' AND SurveyId='$id'"
            $objPeak = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objPeak | foreach {
                if ($i7 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> Peak, Survey Tag -> $tag - Processing row - $i7"
                    
                    $objPea = $_;
                    $peaAmplitude = Null-ToValue -value $objPea.Amplitude;
                    $peaAnalyzerId = Null-ToValue -value $objPea.AnalyzerId;
                    $peaCarBearing = Null-ToValue -value $objPea.CarBearing;
                    $peaCarSpeedEast = $objPea.CarSpeedEast;
                    $peaCarSpeedNorth = $objPea.CarSpeedNorth;
                    $peaCH4 = Null-ToValue -value $objPea.CH4;
                    $peaClassificationConfidence = $objPea.ClassificationConfidence;
                    $peaDisposition = Null-ToValue -value $objPea.Disposition;
                    $peaDistance = Null-ToValue -value $objPea.Distance;
                    $peaEpochTime = Null-ToValue -value $objPea.EpochTime;
                    $peaEthaneConcentrationSdev = $objPea.EthaneConcentrationSdev;
                    $peaEthaneRatio = $objPea.EthaneRatio;
                    $peaEthaneRatioSdev = $objPea.EthaneRatioSdev;
                    $peaEthaneRatioSdevRaw = $objPea.EthaneRatioSdevRaw;
                    $peaEthyleneConcentrationSdev = $objPea.EthyleneConcentrationSdev;
                    $peaEthyleneRatio = $objPea.EthyleneRatio;
                    $peaEthyleneRatioSdev = $objPea.EthyleneRatioSdev;
                    $peaEthyleneRatioSdevRaw = $objPea.EthyleneRatioSdevRaw;
                    $peaGpsLatitude = Null-ToValue -value $objPea.GpsLatitude;
                    $peaGpsLongitude = Null-ToValue -value $objPea.GpsLongitude;
                    $peaLisa = Geometry-ToText -value $objPea.Lisa;
                    $peaLisaBearing = Null-ToValue -value $objPea.LisaBearing;
                    $peaLisaOpeningAngle = Null-ToValue -value $objPea.LisaOpeningAngle;
                    $peaMajor = Null-ToValue -value $objPea.Major;
                    $peaMethanePeaktoPeak = $objPea.MethanePeaktoPeak;
                    $peaMinor = Null-ToValue -value $objPea.Minor;
                    $peaPassedAutoThreshold = Null-ToValue -value $objPea.PassedAutoThreshold;
                    $peaPipEnergy = $objPea.PipEnergy;
                    $peaPosition = Geometry-ToText -value $objPea.Position;
                    $peaSigma = Null-ToValue -value $objPea.Sigma;
                    $peaSurveyId = $objPea.SurveyId;
                    $peaWindDirectionStdDev = $objPea.WindDirectionStdDev;
                    $peaWindSpeedEast = $objPea.WindSpeedEast;
                    $peaWindSpeedNorth = $objPea.WindSpeedNorth;

                    Add-Content $OUTCSV "$peaAnalyzerId,$peaEpochTime,$peaAmplitude,$peaCH4,$peaPosition,$peaLisa,$peaLisaOpeningAngle,$peaLisaBearing,$peaCarBearing,$peaMajor,$peaMinor,$peaCarSpeedNorth,$peaCarSpeedEast,$peaWindDirectionStdDev,$peaWindSpeedNorth,$peaWindSpeedEast,$peaSigma,$peaDistance,$peaGpsLatitude,$peaGpsLongitude,$peaPassedAutoThreshold,$peaSurveyId,$peaEthaneRatio,$peaEthaneRatioSdevRaw,$peaEthaneRatioSdev,$peaEthaneConcentrationSdev,$peaEthyleneRatio,$peaEthyleneRatioSdevRaw,$peaEthyleneRatioSdev,$peaEthyleneConcentrationSdev,$peaPipEnergy,$peaMethanePeaktoPeak,$peaDisposition,$peaClassificationConfidence"
                }

                $i7++
            }

            # --- Segment ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\Segment-$tag-$counter.csv"
            Write-Host "Printing CSV header for Segment -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("Segment-")
            $colHeaders = $COLUMN_HEADINGS.get_item("Segment-")
            Add-Content $OUTCSV "$colHeaders"

            $i8=0
            Write-Host "Fetching Segment values for Survey Tag: $tag"
            $query = "SELECT [SurveyId],[Order],[Mode],CONVERT(VARBINARY(MAX), [Shape]) AS Shape FROM [$databaseName].[dbo].[Segment] WHERE SurveyId='$id'"
            $objSegment = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSegment | foreach {
                if ($i8 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> Segment, Survey Tag -> $tag - Processing row - $i8"
                    
                    $objSeg = $_;
                    $segMode = Null-ToValue -value $objSeg.Mode;
                    $segOrder = Null-ToValue -value $objSeg.Order;
                    $segShape = Geometry-ToText -value $objSeg.Shape;
                    $segSurveyId = Null-ToValue -value $objSeg.SurveyId;

                    Add-Content $OUTCSV "$segSurveyId,$segOrder,$segMode,$segShape"
                }

                $i8++
            }

            # --- Survey ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\Survey-$tag-$counter.csv"
            Write-Host "Printing CSV header for Survey -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("Survey-")
            $colHeaders = $COLUMN_HEADINGS.get_item("Survey-")
            Add-Content $OUTCSV "$colHeaders"

            $i9=0
            Write-Host "Fetching Survey values for Survey Tag: $tag"
            $query = "SELECT * FROM [$databaseName].[dbo].[Survey] WHERE Id='$id'"
            $objSurvey = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSurvey | foreach {
                if ($i9 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> Survey, Survey Tag -> $tag - Processing row - $i9"
                    
                    $objSur = $_;
                    $surAnalyzerId = Null-ToValue -value $objSur.AnalyzerId;
                    $surBuildNumber = $objSur.BuildNumber;
                    $surDeleted = Null-ToValue -value $objSur.Deleted;
                    $surEndDateTime = Date-ToString -value $objSur.EndDateTime;
                    $surEndEpoch = Null-ToValue -value $objSur.EndEpoch;
                    $surId = Null-ToValue -value $objSur.Id;
                    $surLocationId = $objSur.LocationId;
                    $surMinimumAmplitude = $objSur.MinimumAmplitude;
                    $surProcessingDateCompleted = Date-ToString -value $objSur.ProcessingDateCompleted;
                    $surProcessingDateStarted = Date-ToString -value $objSur.ProcessingDateStarted;
                    $surReferenceGasBottleId = Null-ToValue -value $objSur.ReferenceGasBottleId;
                    $surStabilityClass = Null-ToValue -value $objSur.StabilityClass;
                    $surStartDateTime = Date-ToString -value $objSur.StartDateTime;
                    $surStartEpoch = Null-ToValue -value $objSur.StartEpoch;
                    $surStatus = Null-ToValue -value $objSur.Status;
                    $surSurveyModeTypeId = Null-ToValue -value $objSur.SurveyModeTypeId;
                    $surSurveyorUnitId = Null-ToValue -value $objSur.SurveyorUnitId;
                    $surTag = $objSur.Tag;
                    $surUserId = Null-ToValue -value $objSur.UserId;

                    Add-Content $OUTCSV "$surId,$surAnalyzerId,$surSurveyorUnitId,$surReferenceGasBottleId,$surUserId,$surSurveyModeTypeId,$surStartEpoch,$surEndEpoch,$surStartDateTime,$surEndDateTime,$surTag,$surStabilityClass,$surMinimumAmplitude,$surStatus,$surDeleted,$surProcessingDateStarted,$surLocationId,$surBuildNumber,$surProcessingDateCompleted"
                }

                $i9++
            }

            # --- SurveyCondition ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\SurveyCondition-$tag-$counter.csv"
            Write-Host "Printing CSV header for SurveyCondition -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("SurveyCondition-")
            $colHeaders = $COLUMN_HEADINGS.get_item("SurveyCondition-")
            Add-Content $OUTCSV "$colHeaders"

            $i10=0
            Write-Host "Fetching SurveyCondition values for Survey Tag: $tag"
            $query = "SELECT * FROM [$databaseName].[dbo].[SurveyCondition] WHERE SurveyId='$id'"
            $objSurveyCondition = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSurveyCondition | foreach {
                if ($i10 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> SurveyCondition, Survey Tag -> $tag - Processing row - $i10"
                    
                    $objSur = $_;
                    $surId = Null-ToValue -value $objSur.Id;
                    $surName = Null-ToValue -value $objSur.Name;
                    $surSurveyId = Null-ToValue -value $objSur.SurveyId;
                    $surValue = Null-ToValue -value $objSur.Value;

                    Add-Content $OUTCSV "$surId,$surSurveyId,$surName,$surValue"
                }

                $i10++
            }

            # --- SurveyResult ---

            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\SurveyResult-$tag-$counter.csv"
            Write-Host "Printing CSV header for SurveyResult -> Survey Tag: $tag"
            $COLUMN_HEADINGS.get_item("SurveyResult-")
            $colHeaders = $COLUMN_HEADINGS.get_item("SurveyResult-")
            Add-Content $OUTCSV "$colHeaders"

            $i11=0
            Write-Host "Fetching SurveyResult values for Survey Tag: $tag"
            $query = "SELECT SurveyId,CONVERT(VARBINARY(MAX), [FieldOfView]) AS FieldOfView,CONVERT(VARBINARY(MAX), [Breadcrumb]) AS Breadcrumb FROM [$databaseName].[dbo].[SurveyResult] WHERE SurveyId='$id'"
            $objSurveyResult = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSurveyResult | foreach {
                if ($i11 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> SurveyResult, Survey Tag -> $tag - Processing row - $i11"
                    
                    $objSur = $_;
                    $surBreadcrumb = Geometry-ToText -value $objSur.Breadcrumb;
                    $surFieldOfView = Geometry-ToText -value $objSur.FieldOfView;
                    $surSurveyId = Null-ToValue -value $objSur.SurveyId;

                    Add-Content $OUTCSV "$surSurveyId,$surFieldOfView,$surBreadcrumb"
                }

                $i11++
            }

            # --- SurveyResult-Geom ---

            # Create GEOM file for SurveyResult
            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\SurveyResult-Geom-$tag-$counter.csv"
            $i12=0
            Write-Host "Generating SurveyResult-Geom file for Survey Tag: $tag"
            $query = "SELECT [FieldOfView].STAsText() AS SurveyResultFieldOfViewAsWKT FROM [$databaseName].[dbo].[SurveyResult] WHERE SurveyId='$id'"
            $objSurveyResult = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSurveyResult | foreach {
                if ($i12 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> SurveyResult(-geom), Survey Tag -> $tag - Processing row - $i12"
                    
                    $surSurveyResultFieldOfViewAsWKT = $_.SurveyResultFieldOfViewAsWKT;

                    Add-Content $OUTCSV "$surSurveyResultFieldOfViewAsWKT"
                }

                $i12++
            }

            # --- Segment-Geom ---

            # Create GEOM file for Segment
            $OUTCSV = New-Item -ItemType "File" -Path "$outputFolder\Segment-Geom-$tag-$counter.csv"
            $i13=0
            Write-Host "Generating Segment-Geom file for Survey Tag: $tag"
            $query = "SELECT [Shape].STAsText() AS SegmentShapeWKT FROM [$databaseName].[dbo].[Segment] WHERE SurveyId='$id'"
            $objSegment = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSegment | foreach {
                if ($i13 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Table -> Segment, Survey Tag -> $tag - Processing row - $i13"
                    
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
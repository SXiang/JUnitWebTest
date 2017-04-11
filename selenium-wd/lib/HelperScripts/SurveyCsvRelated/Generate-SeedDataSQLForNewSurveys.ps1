<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION: 
  - This script can be used to generate SQL script for injecting surveys related table entities
    (for eg. Analyzer, SurveyorUnit, RefGasBottle, User, Location, CalibrationRecord, Inlet, Anemometer, etc).
  
 Sample Run Script: 
   .\Generate-SeedDataSQLForNewSurveys.ps1 `
        -surveyIDs "2B690480-BC87-8C19-4B32-39DBF7ED8628"  `
        -DatabaseIP "20.20.180.115"  `
        -DatabaseName "SurveyorP3ENGSQA2"  `
        -DatabaseUser "awssa"  `
        -DatabasePwd "<password>"  `
        -OutputFolder "C:\temp\SurveyCSVs"  
----------------------------------------------------------------------------------------------------------------------------------#>

param
(
  [Parameter(Mandatory=$true)]
  [String] $surveyIds,       # comma seperated list of survey ids.

  [Parameter(Mandatory=$true)]
  [String] $DatabaseIP,

  [Parameter(Mandatory=$true)]
  [String] $DatabaseName,

  [Parameter(Mandatory=$true)]
  [String] $DatabaseUser,

  [Parameter(Mandatory=$true)]
  [String] $DatabasePwd,

  [Parameter(Mandatory=$true)]
  [String] $OutputFolder
)

$OUTFILE = New-Item -type file "$OutputFolder\AnlSurRefGLocUser.sql.txt" -force
$DB_NAME = $DatabaseName

. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"

$surveyIds -split "," | % {
    $surveyId = $_

    $ConnString = "Server=$DatabaseIP;Database=$DatabaseName;User Id=$DatabaseUser;Password=$DatabasePwd;"
    $SurveyQuery = "SELECT S.[AnalyzerId],S.[LocationId],S.[SurveyorUnitId],S.[UserId],S.[ReferenceGasBottleId],L.[CustomerId] FROM [dbo].[Survey] AS S INNER JOIN [dbo].[Location] AS L ON L.Id=S.[LocationId] WHERE S.ID = '$surveyId'"
    $objSurveys = Get-DatabaseData -connectionString $ConnString -query $SurveyQuery -isSQLServer:$true

    $idx = 0
    $objSurveys | % { 
        if ($idx -gt 0) {     # first row is length of array. datarows are from index=1
            # --- Survey ---
            $objSur = $_;
            $surAnalyzerId = $objSur.AnalyzerId;
            $surLocationId = $objSur.LocationId;
            $surSurveyorUnitId = $objSur.SurveyorUnitId;
            $surUserId = $objSur.UserId;            
            $surReferenceGasBottleId = $objSur.ReferenceGasBottleId;
            $surCustomerId = $objSur.CustomerId;

            # --- Analyzer ---
            $i1=0
            Write-Host "Fetching Analyzer values for Survey Tag: $surveyId"
            $query = "SELECT * FROM [$DB_NAME].[dbo].[Analyzer] WHERE Id='$surAnalyzerId'"
            $objAnalyzer = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objAnalyzer | foreach {
                if ($i1 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Processing row - $i1"
                    
                    $objAna = $_;
                    $anaId = Null-ToValue -value $objAna.Id;
                    $anaSerialNumber = Null-ToValue -value $objAna.SerialNumber;
                    $anaSharedKey = Null-ToValue -value $objAna.SharedKey;
                    $anaSurveyorUnitId = Null-ToValue -value $objAna.SurveyorUnitId;
                }

                $i1++
            }

            add-content $OUTFILE "-- Analyzer - ($anaSerialNumber)"
            add-content $OUTFILE "IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'$anaSerialNumber' AND [SharedKey]=N'$anaSharedKey')"
            add-content $OUTFILE "BEGIN "
            add-content $OUTFILE "UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'$surSurveyorUnitId', [SerialNumber]=N'$anaSerialNumber', [SharedKey]=N'$anaSharedKey' WHERE [Id]='$anaId'"
            add-content $OUTFILE "IF @@ROWCOUNT=0"
            add-content $OUTFILE "	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'$anaId', N'$surSurveyorUnitId', N'$anaSerialNumber', N'$anaSharedKey')"
            add-content $OUTFILE "END"
            add-content $OUTFILE ""

            # --- Customer ---
            $i2=0
            Write-Host "Fetching Customer values for Survey Tag: $surveyId"
            $query = "SELECT * FROM [$DB_NAME].[dbo].[Customer] WHERE Id='$surCustomerId'"
            $objCustomer = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objCustomer | foreach {
                if ($i2 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Processing row - $i2"
                    
                    $objCus = $_;
                    $cusActive = Null-ToValue -value $objCus.Active;
                    $cusEula = Null-ToValue -value $objCus.Eula;
                    $cusId = Null-ToValue -value $objCus.Id;
                    $cusName = Null-ToValue -value $objCus.Name;
                }

                $i2++
            }

            add-content $OUTFILE "-- Customer - ($cusName)"
            add-content $OUTFILE "IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='$cusName')"
            add-content $OUTFILE "BEGIN"
            add-content $OUTFILE "	UPDATE [dbo].[Customer] SET [Name]=N'$cusName',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='$cusId' "
            add-content $OUTFILE "	IF @@ROWCOUNT=0"
            add-content $OUTFILE "		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'$cusId',N'$cusName' ,N'Accept the agreement',1)"
            add-content $OUTFILE "END		"


            # --- Location ---
            $i3=0
            Write-Host "Fetching Location values for Survey Tag: $surveyId"
            $query = "SELECT * FROM [$DB_NAME].[dbo].[Location] WHERE Id='$surLocationId'"
            $objLocation = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objLocation | foreach {
                if ($i3 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Processing row - $i3"
                    
                    $objLoc = $_;
                    $locCustomerId = Null-ToValue -value $objLoc.CustomerId;
                    $locDescription = Null-ToValue -value $objLoc.Description;
                    $locId = Null-ToValue -value $objLoc.Id;
                    $locLatitude = $objLoc.Latitude;
                    $locLongitude = $objLoc.Longitude;
                }

                $i3++
            }

            add-content $OUTFILE "-- Location - ($locDescription)"
            add-content $OUTFILE "SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='$cusName'"
            add-content $OUTFILE "UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'$locDescription',[Latitude]='$locLatitude',[Longitude]='$locLongitude' WHERE [Id]='$locId' "
            add-content $OUTFILE "IF @@ROWCOUNT=0"
            add-content $OUTFILE "	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'$locId', @customerId, N'$locDescription','$locLatitude','$locLongitude')"
            add-content $OUTFILE ""

            # --- SurveyorUnit ---

            $i5=0
            Write-Host "Fetching SurveyorUnit values for Survey Tag: $surveyId"
            $query = "SELECT * FROM [$DB_NAME].[dbo].[SurveyorUnit] WHERE Id='$surSurveyorUnitId'"
            $objSurveyorUnit = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objSurveyorUnit | foreach {
                if ($i5 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Processing row - $i5"
                    
                    $objSur = $_;
                    $surDescription = Null-ToValue -value $objSur.Description;
                    $surId = Null-ToValue -value $objSur.Id;
                    $surLocationId = Null-ToValue -value $objSur.LocationId;
                }

                $i5++
            }

            add-content $OUTFILE "-- SurveyorUnit - ($surDescription)"
            add-content $OUTFILE "IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='$surId')"
            add-content $OUTFILE "BEGIN"
            add-content $OUTFILE "	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='$locDescription'"
            add-content $OUTFILE "	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'$surId', @locationID, N'$surDescription')"
            add-content $OUTFILE "END"
            add-content $OUTFILE ""

            # --- ReferenceGasBottle ---

            $i4=0
            Write-Host "Fetching ReferenceGasBottle values for Survey Tag: $surveyId"
            $query = "SELECT * FROM [$DB_NAME].[dbo].[ReferenceGasBottle] WHERE Id='$surReferenceGasBottleId'"
            $objReferenceGasBottle = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objReferenceGasBottle | foreach {
                if ($i4 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Processing row - $i4"
                    
                    $objRef = $_;
                    $refBatchId = Null-ToValue -value $objRef.BatchId;
                    $refDate = Date-ToString -value $objRef.Date;
                    $refEthaneToMethaneRatio = $objRef.EthaneToMethaneRatio;
                    $refId = Null-ToValue -value $objRef.Id;
                    $refIsotopicValue = Null-ToValue -value $objRef.IsotopicValue;
                    $refSurveyorUnitId = Null-ToValue -value $objRef.SurveyorUnitId;
                }

                $i4++
            }

            add-content $OUTFILE "-- RefGasBottle for Surveyor - '$surDescription'"
            add-content $OUTFILE "UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='$surId', [BatchId]='$refBatchId', [IsotopicValue]=$refIsotopicValue, [Date]=CAST(N'$refDate' AS DateTime) WHERE [Id]='$refId'"
            add-content $OUTFILE "IF @@ROWCOUNT=0"
            add-content $OUTFILE "	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'$refId', N'$surId', N'$refBatchId', $refIsotopicValue, CAST(N'$refDate' AS DateTime))"
            add-content $OUTFILE ""

            # --- User ---

            $i6=0
            Write-Host "Fetching User values for Survey Id: $surveyId"
            $query = "SELECT * FROM [$DB_NAME].[dbo].[User] WHERE Id='$surUserId'"
            $objUser = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objUser | foreach {
                if ($i6 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Processing row - $i6"
                    
                    $objUse = $_;
                    $useAccessFailedCount = Null-ToValue -value $objUse.AccessFailedCount;
                    $useActive = Null-ToValue -value $objUse.Active;
                    $useCellPhoneNumber = $objUse.CellPhoneNumber;
                    $useCultureId = Null-ToValue -value $objUse.CultureId;
                    $useCustomerId = Null-ToValue -value $objUse.CustomerId;
                    $useEmail = $objUse.Email;
                    $useEmailConfirmed = Null-ToValue -value $objUse.EmailConfirmed;
                    $useEulaAccepted = Null-ToValue -value $objUse.EulaAccepted;
                    $useFirstName = $objUse.FirstName;
                    $useId = Null-ToValue -value $objUse.Id;
                    $useLastName = $objUse.LastName;
                    $useLocationId = $objUse.LocationId;
                    $useLockoutEnabled = Null-ToValue -value $objUse.LockoutEnabled;
                    $useLockoutEndDateUtc = Date-ToString -value $objUse.LockoutEndDateUtc;
                    $useOpQualExpiration = Date-ToString -value $objUse.OpQualExpiration;
                    $usePasswordHash = $objUse.PasswordHash;
                    $usePhoneNumber = $objUse.PhoneNumber;
                    $usePhoneNumberConfirmed = Null-ToValue -value $objUse.PhoneNumberConfirmed;
                    $useSecurityStamp = $objUse.SecurityStamp;
                    $useTimeZoneId = Null-ToValue -value $objUse.TimeZoneId;
                    $useTwoFactorEnabled = Null-ToValue -value $objUse.TwoFactorEnabled;
                    $useUserName = Null-ToValue -value $objUse.UserName;
                }

                $i6++
            }

            add-content $OUTFILE "-- User ($useUserName)"
            add-content $OUTFILE "SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'$cusName' "
            add-content $OUTFILE "SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='$locDescription'"
            add-content $OUTFILE "UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'$useTimeZoneId',[LocationId]=@locationID,[FirstName]=N'$useFirstName',[LastName]=N'$useLastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='$useUserName'"
            add-content $OUTFILE "IF @@ROWCOUNT=0"
            add-content $OUTFILE "	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'$useId',@customerId, NULL,N'1',N'1',N'$useTimeZoneId',@locationID,N'$useFirstName',N'$useLastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','$useUserName')"
            add-content $OUTFILE ""


            $i7=0
            Write-Host "Fetching AnalyzerHardwareCapabilityType values for Survey Id: $surveyId"
            $query = "SELECT * FROM [$DB_NAME].[dbo].[AnalyzerHardwareCapabilityType] WHERE AnalyzerId='$surAnalyzerId'"
            $objAnalyzerHardwareCapabilityType = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objAnalyzerHardwareCapabilityType | foreach {
                if ($i7 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Processing row - $i7"
                    
                    $objAna = $_;
                    $anaAnalyzerId = Null-ToValue -value $objAna.AnalyzerId;
                    $anaHardwareCapabilityTypeId = Null-ToValue -value $objAna.HardwareCapabilityTypeId;
                }

                $i7++
            }

            add-content $OUTFILE "-- AnalyzerHardwareCapabilityType for Surveyor - '$surDescription'"
            add-content $OUTFILE "UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=$anaHardwareCapabilityTypeId WHERE [AnalyzerId]=N'$anaAnalyzerId'"
            add-content $OUTFILE "IF @@ROWCOUNT=0"
            add-content $OUTFILE "	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'$anaAnalyzerId', $anaHardwareCapabilityTypeId)"
            add-content $OUTFILE ""

            $i9=0
            Write-Host "Fetching CalibrationRecord values for Survey Id: $surveyId"
            $query = "SELECT * FROM [$DB_NAME].[dbo].[CalibrationRecord] WHERE SurveyorUnitId='$surSurveyorUnitId'"
            $objCalibrationRecord = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
            $objCalibrationRecord | foreach {
                if ($i9 -gt 0) {     # first row is length of array. datarows are from index=1
                    "Processing row - $i9"
                    
                    $objCal = $_;
                    $calBackgroundFilterThreshold = Null-ToValue -value $objCal.BackgroundFilterThreshold;
                    $calGPSOffset = Null-ToValue -value $objCal.GPSOffset;
                    $calId = Null-ToValue -value $objCal.Id;
                    $calStartEpoch = Null-ToValue -value $objCal.StartEpoch;
                    $calSurveyorUnitId = Null-ToValue -value $objCal.SurveyorUnitId;
                    $calTriggerThresholdPPM = Null-ToValue -value $objCal.TriggerThresholdPPM;

                    add-content $OUTFILE "-- Calibration record for '$surDescription'"
                    add-content $OUTFILE "UPDATE [dbo].[CalibrationRecord] SET [SurveyorUnitId]='$surSurveyorUnitId',[StartEpoch]=$calStartEpoch,[BackgroundFilterThreshold]=$calBackgroundFilterThreshold,[TriggerThresholdPPM]=$calTriggerThresholdPPM,[GPSOffset]=$calGPSOffset WHERE [Id]='$calId'"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT INTO [dbo].[CalibrationRecord] ([Id],[SurveyorUnitId],[StartEpoch],[BackgroundFilterThreshold],[TriggerThresholdPPM],[GPSOffset]) VALUES ('$calId','$surSurveyorUnitId',$calStartEpoch,$calBackgroundFilterThreshold,$calTriggerThresholdPPM,$calGPSOffset)"
                    add-content $OUTFILE ""

                    # Anemometer and Inlet rows for calibration record.
                    $i8=0
                    Write-Host "Fetching Anemometer values for Survey Id: $surveyId"
                    $query = "SELECT * FROM [$DB_NAME].[dbo].[Anemometer] WHERE CalibrationRecordId='$calId'"
                    $objAnemometer = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
                    $objAnemometer | foreach {
                        if ($i8 -gt 0) {     # first row is length of array. datarows are from index=1
                            "Processing row - $i8"
                    
                            $objAne = $_;
                            $aneCalibrationRecordId = Null-ToValue -value $objAne.CalibrationRecordId;
                            $aneHeight = Null-ToValue -value $objAne.Height;
                            $aneIndex = Null-ToValue -value $objAne.Index;
                            $aneOffset = Null-ToValue -value $objAne.Offset;
                            $aneRotation = Null-ToValue -value $objAne.Rotation;
                            $aneSpeedFactor = Null-ToValue -value $objAne.SpeedFactor;
                        }

                        $i8++
                    }

                    add-content $OUTFILE "-- Anemometer entry for '$surDescription', calibration record"
                    add-content $OUTFILE "UPDATE [dbo].[Anemometer] SET [Offset]=$aneOffset, [SpeedFactor]=$aneSpeedFactor, [Height]=$aneHeight, [Rotation]=$aneRotation WHERE [CalibrationRecordId]=N'$calId' AND [Index]=$aneIndex"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Anemometer] ([CalibrationRecordId], [Index], [Offset], [SpeedFactor], [Height], [Rotation]) VALUES (N'$calId', $aneIndex, $aneOffset, $aneSpeedFactor, $aneHeight, $aneRotation)	"
                    add-content $OUTFILE ""

                    $i10=0
                    Write-Host "Fetching Inlet values for Survey Id: $surveyId"
                    $query = "SELECT * FROM [$DB_NAME].[dbo].[Inlet] WHERE CalibrationRecordId='$calId'"
                    $objInlet = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
                    $objInlet | foreach {
                        if ($i10 -gt 0) {     # first row is length of array. datarows are from index=1
                            "Processing row - $i10"
                    
                            $objInl = $_;
                            $inlCalibrationRecordId = Null-ToValue -value $objInl.CalibrationRecordId;
                            $inlHeight = Null-ToValue -value $objInl.Height;
                            $inlIndex = Null-ToValue -value $objInl.Index;
                        }

                        $i10++
                    }

                    add-content $OUTFILE "-- Inlet entry for '$surDescription' calibration record"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=0.625 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=0"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 0,0.625)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=0.75 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=1"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 1,0.75)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=0.875 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=2"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 2,0.875)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=1 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=3"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 3,1)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=1.125 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=4"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 4,1.125)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=1.25 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=5"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 5,1.25)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=1.375 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=6"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 6,1.375)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=1.5 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=7"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 7,1.5)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=1.625 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=8"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 8,1.625)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=1.75 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=9"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 9,1.75)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=1.875 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=10"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 10,1.875)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=2 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=11"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 11,2)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=2.125 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=12"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 12,2.125)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=2.25 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=13"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 13,2.25)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=2.375 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=14"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 14,2.375)"
                    add-content $OUTFILE "UPDATE [dbo].[Inlet] SET [Height]=2.5 WHERE [CalibrationRecordId]=N'$calId' AND [Index]=15"
                    add-content $OUTFILE "IF @@ROWCOUNT=0"
                    add-content $OUTFILE "	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'$calId', 15,2.5)"
                    add-content $OUTFILE ""
                }

                $i9++
            }
        }

       $idx++
    }
}

ii $OUTFILE
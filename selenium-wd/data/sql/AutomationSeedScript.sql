-- SQA seed script

BEGIN TRY
    BEGIN TRAN
 -- sample data

 
--TimeZone:

IF NOT EXISTS (SELECT * FROM [dbo].[TimeZone] WHERE [Description]='Pacific Standard Time') 
	INSERT INTO [dbo].[TimeZone] ([Id],[Description],[UIDescription])VALUES (N'00000000-0000-0000-0001-000000000000',N'Pacific Standard Time',N'Pacific Time (US and Canada)')

IF NOT EXISTS (SELECT * FROM [dbo].[TimeZone] WHERE [Description]='Mountain Standard Time') 
	INSERT INTO [dbo].[TimeZone] ([Id],[Description],[UIDescription])VALUES (N'00000000-0000-0000-0002-000000000000',N'Mountain Standard Time',N'Mountain Time (US and Canada)')

IF NOT EXISTS (SELECT * FROM [dbo].[TimeZone] WHERE [Description]='Central Standard Time') 
	INSERT INTO [dbo].[TimeZone] ([Id],[Description],[UIDescription])VALUES (N'00000000-0000-0000-0003-000000000000',N'Central Standard Time',N'Central Time (US and Canada)')

IF NOT EXISTS (SELECT * FROM [dbo].[TimeZone] WHERE [Description]='Eastern Standard Time') 
	INSERT INTO [dbo].[TimeZone] ([Id],[Description],[UIDescription])VALUES (N'00000000-0000-0000-0004-000000000000',N'Eastern Standard Time',N'Eastern Time (US and Canada)')


 -- Customer:
IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE [Name]=N'Picarro')
	INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'b1252204-04fb-4a67-82d4-3f4666fd855c',N'Picarro' ,N'Accept the agreement',1)

IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE [Name]=N'sqacus')
	INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'00000000-0000-0000-0000-000000000002',N'sqacus' ,N'Accept the agreement',1)

IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE [Name]=N'sqaTest')
	INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'00000000-0000-0000-0000-000000000003',N'sqaTest' ,N'Accept the agreement',1)

-- Location:

IF NOT EXISTS (SELECT * FROM [dbo].[Location] WHERE [Description] = 'Santa Clara-picarro')
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D701', N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'Santa Clara-picarro','37.4020925705503','-121.984820397399')

IF NOT EXISTS (SELECT * FROM [dbo].[Location] WHERE [Description] = 'sqacusloc')
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D702', N'00000000-0000-0000-0000-000000000002', N'sqacusloc','37.4020925705503','-121.984820397399')

IF NOT EXISTS (SELECT * FROM [dbo].[Location] WHERE [Description] = 'sqaTestloc')
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D703', N'00000000-0000-0000-0000-000000000003', N'sqaTestloc','37.4020925705503','-121.984820397399')


-- SurveyModeType:

IF NOT EXISTS (SELECT * FROM [dbo].[SurveyModeType] WHERE [Description] = 'Rapid Response')
	INSERT INTO [dbo].[SurveyModeType]([Id],[Description])VALUES (N'4901E67A-4C00-4436-ADC0-9CFB277BB310',N'Rapid Response');
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyModeType] WHERE [Description] = 'Manual')
	INSERT INTO [dbo].[SurveyModeType]([Id],[Description])VALUES (N'4901E67A-4C00-4436-ADC0-9CFB277BB311',N'Manual');
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyModeType] WHERE [Description] = 'Operator')
	INSERT INTO [dbo].[SurveyModeType]([Id],[Description])VALUES (N'4901E67A-4C00-4436-ADC0-9CFB277BB312',N'Operator');
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyModeType] WHERE [Description] = 'Standard')
	INSERT INTO [dbo].[SurveyModeType]([Id],[Description])VALUES (N'B310238A-A5AE-4E94-927B-F0F165E24522',N'Standard');
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyModeType] WHERE [Description] = 'EQ')
	INSERT [dbo].[SurveyModeType] ([Id], [Description]) VALUES (N'E9DD9F53-E5CB-45B3-9517-9DC8E0276C6D', N'EQ')


--SurveyModeTypeConfiguration:

DECLARE @locationID uniqueidentifier
DECLARE @surveyModeTypeID uniqueidentifier 

/* NOTE: FovVersion is set to 1 for 3100 algorithm in web.config, code will use FovVersion1Ratio value.
 * Here are corresponding values for MinimumAmplitude, LeakRate
*/
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara-picarro'
SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Rapid Response'
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyModeTypeConfiguration] WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID)
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 5, 166.666666667)

SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Manual'
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyModeTypeConfiguration] WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID)
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 0.035, 1.16666666667)

SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Operator'
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyModeTypeConfiguration] WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID)
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 5, 166.666666667)

SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Standard'
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyModeTypeConfiguration] WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID)
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 0.035, 1.16666666667)

SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='EQ'
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyModeTypeConfiguration] WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID)
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 5, 166.666666667)

/* NOTE: Upon changing the FovVersion to 2 for 3200 algorithm in web.config, code will use FovVersion2Ratio value.
 * Please adjust the values for MinimumAmplitude, LeakRate accordingly
INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D701', N'4901E67A-4C00-4436-ADC0-9CFB277BB310', CAST(N'1980-01-01 00:00:00.000' AS DateTime), 5, 500)
INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D701', N'4901E67A-4C00-4436-ADC0-9CFB277BB311', CAST(N'1980-01-01 00:00:00.000' AS DateTime), 0.03, 3)
INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D701', N'4901E67A-4C00-4436-ADC0-9CFB277BB312', CAST(N'1980-01-01 00:00:00.000' AS DateTime), 5, 500)
INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D701', N'B310238A-A5AE-4E94-927B-F0F165E24522', CAST(N'1980-01-01 00:00:00.000' AS DateTime), 0.1, 10)
INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D701', N'E9DD9F53-E5CB-45B3-9517-9DC8E0276C6D', CAST(N'1980-01-01 00:00:00.000' AS DateTime), 0.1, 10)
*/


--IsotopicIdentity:
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara-picarro'
IF NOT EXISTS (SELECT * FROM [dbo].[IsotopicIdentity] WHERE [LocationId] = @locationID AND [NoLowerBound]=-43 AND [YesLowerBound]=-40 AND [YesUpperBound]=-30 AND [NoUpperBound]=-25)
	INSERT [dbo].[IsotopicIdentity] ([LocationId], [FromDate], [NoLowerBound], [YesLowerBound], [YesUpperBound], [NoUpperBound]) VALUES (@locationID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), -43, -40, -30, -25)

SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqacusloc'
IF NOT EXISTS (SELECT * FROM [dbo].[IsotopicIdentity] WHERE [LocationId] = @locationID AND [NoLowerBound]=-43 AND [YesLowerBound]=-40 AND [YesUpperBound]=-30 AND [NoUpperBound]=-25)
	INSERT [dbo].[IsotopicIdentity] ([LocationId], [FromDate], [NoLowerBound], [YesLowerBound], [YesUpperBound], [NoUpperBound]) VALUES (@locationID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), -43, -40, -30, -25)

SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqaTestloc'
IF NOT EXISTS (SELECT * FROM [dbo].[IsotopicIdentity] WHERE [LocationId] = @locationID AND [NoLowerBound]=-43 AND [YesLowerBound]=-40 AND [YesUpperBound]=-30 AND [NoUpperBound]=-25)
	INSERT [dbo].[IsotopicIdentity] ([LocationId], [FromDate], [NoLowerBound], [YesLowerBound], [YesUpperBound], [NoUpperBound]) VALUES (@locationID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), -43, -40, -30, -25)

	
--ReportStatusType:
IF NOT EXISTS (SELECT * FROM [dbo].[ReportStatusType] WHERE [Description]='Pending')
	INSERT [dbo].[ReportStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Pending')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportStatusType] WHERE [Description]='Processing')
	INSERT [dbo].[ReportStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Processing')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportStatusType] WHERE [Description]='Complete')
	INSERT [dbo].[ReportStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'Complete')


--ReportType:

IF NOT EXISTS (SELECT * FROM [dbo].[ReportType] WHERE [Description]='Compliance')
	INSERT [dbo].[ReportType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Compliance')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportType] WHERE [Description]='Investigation')
	INSERT [dbo].[ReportType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Investigation')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportType] WHERE [Description]='ReferenceGas')
	INSERT [dbo].[ReportType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'ReferenceGas')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportType] WHERE [Description]='SystemHistory')
	INSERT [dbo].[ReportType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0004-000000000000', N'SystemHistory')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportType] WHERE [Description]='EQ')
	INSERT [dbo].[ReportType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0005-000000000000', N'EQ')

--ReportJobStatusType:

IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobStatusType] WHERE [Description]='Pending')
	INSERT [dbo].[ReportJobStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Pending')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobStatusType] WHERE [Description]='Processing')
	INSERT [dbo].[ReportJobStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Processing')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobStatusType] WHERE [Description]='Complete')
	INSERT [dbo].[ReportJobStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'Complete')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobStatusType] WHERE [Description]='Failed')
	INSERT [dbo].[ReportJobStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0004-000000000000', N'Failed')


--ReportJobType
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobType] WHERE [Description]='Map')
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Map')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobType] WHERE [Description]='SSRS')
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'SSRS')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobType] WHERE [Description]='DataGeneration')
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'DataGeneration')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobType] WHERE [Description]='EQMap')
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0004-000000000000', N'EQMap')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobType] WHERE [Description]='EQSSRS')
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0005-000000000000', N'EQSSRS')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobType] WHERE [Description]='EQDataGeneration')
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0006-000000000000', N'EQDataGeneration')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobType] WHERE [Description]='ShapeFile')
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0007-000000000000', N'ShapeFile')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobType] WHERE [Description]='ReportMeta')
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0008-000000000000', N'ReportMeta')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportJobType] WHERE [Description]='PercentCoverageForecast')
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0009-000000000000', N'PercentCoverageForecast')

--ReportAreaModeType:

IF NOT EXISTS (SELECT * FROM [dbo].[ReportAreaModeType] WHERE [Description]='Customer Boundary')
	INSERT [dbo].[ReportAreaModeType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Customer Boundary')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportAreaModeType] WHERE [Description]='Custom Boundary')
	INSERT [dbo].[ReportAreaModeType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Custom Boundary')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportAreaModeType] WHERE [Description]='Driving Area')
	INSERT [dbo].[ReportAreaModeType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'Driving Area')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportAreaModeType] WHERE [Description]='Lat/long')
	INSERT [dbo].[ReportAreaModeType] ([Id], [Description]) VALUES (N'cc26bb15-db64-fde7-7e67-39c8ac522d49', N'Lat/long')
IF NOT EXISTS (SELECT * FROM [dbo].[ReportAreaModeType] WHERE [Description]='Boundary')
	INSERT [dbo].[ReportAreaModeType] ([Id], [Description]) VALUES (N'cc26bb15-db64-fde7-7e67-39c8ac522d50', N'Boundary')

--AssetType:

IF NOT EXISTS (SELECT * FROM [dbo].[AssetType] WHERE [Description]='Main')
	INSERT [dbo].[AssetType] ([Id], [Description]) VALUES (N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'Main')
IF NOT EXISTS (SELECT * FROM [dbo].[AssetType] WHERE [Description]='Service')
	INSERT [dbo].[AssetType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0000-000000000002', N'Service')

--MaterialType: (UPDATE if EXISTS, else INSERT) 
UPDATE [dbo].[MaterialType] SET [Description]='Other Plastic' WHERE [Id]='b1252204-04fb-4a67-82d4-3f4666fd855c'
IF @@ROWCOUNT=0
	INSERT [dbo].[MaterialType] ([Id], [Description]) VALUES (N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'Other Plastic')
UPDATE [dbo].[MaterialType] SET [Description]='PE Plastic' WHERE [Id]='00000000-0000-0000-0000-000000000002'
IF @@ROWCOUNT=0
	INSERT [dbo].[MaterialType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0000-000000000002', N'PE Plastic')
UPDATE [dbo].[MaterialType] SET [Description]='Protected Steel' WHERE [Id]='00000000-0000-0000-0000-000000000003'
IF @@ROWCOUNT=0
	INSERT [dbo].[MaterialType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0000-000000000003', N'Protected Steel')
UPDATE [dbo].[MaterialType] SET [Description]='Un-protected Steel' WHERE [Id]='00000000-0000-0000-0000-000000000004'
IF @@ROWCOUNT=0
	INSERT [dbo].[MaterialType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0000-000000000004', N'Un-protected Steel')
UPDATE [dbo].[MaterialType] SET [Description]='Cast Iron' WHERE [Id]='00000000-0000-0000-0000-000000000005'
IF @@ROWCOUNT=0
	INSERT [dbo].[MaterialType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0000-000000000005', N'Cast Iron')
UPDATE [dbo].[MaterialType] SET [Description]='Copper' WHERE [Id]='00000000-0000-0000-0000-000000000006'
IF @@ROWCOUNT=0
	INSERT [dbo].[MaterialType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0000-000000000006', N'Copper')

	
--BaseMapType:

IF NOT EXISTS (SELECT * FROM [dbo].[BaseMapType] WHERE [Description]='Map')
	INSERT [dbo].[BaseMapType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Map')
IF NOT EXISTS (SELECT * FROM [dbo].[BaseMapType] WHERE [Description]='Satellite')
	INSERT [dbo].[BaseMapType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Satellite')
IF NOT EXISTS (SELECT * FROM [dbo].[BaseMapType] WHERE [Description]='None')
	INSERT [dbo].[BaseMapType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'None')

--ColorType:

IF NOT EXISTS (SELECT * FROM [dbo].[ColorType] WHERE [Description]='Light blue')
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Light blue', N'#B5DBF4')
IF NOT EXISTS (SELECT * FROM [dbo].[ColorType] WHERE [Description]='Blue')
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Blue', N'#1F78B4')
IF NOT EXISTS (SELECT * FROM [dbo].[ColorType] WHERE [Description]='Light green')
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0003-000000000000', N'Light green', N'#B2DF8A')
IF NOT EXISTS (SELECT * FROM [dbo].[ColorType] WHERE [Description]='Green')
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0004-000000000000', N'Green', N'#33A02C')
IF NOT EXISTS (SELECT * FROM [dbo].[ColorType] WHERE [Description]='Salmon')
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0005-000000000000', N'Salmon', N'#FB9A99')
IF NOT EXISTS (SELECT * FROM [dbo].[ColorType] WHERE [Description]='Red')
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0006-000000000000', N'Red', N'#E31A1C')
IF NOT EXISTS (SELECT * FROM [dbo].[ColorType] WHERE [Description]='Yellow')
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0007-000000000000', N'Yellow', N'#FDE36F')
IF NOT EXISTS (SELECT * FROM [dbo].[ColorType] WHERE [Description]='Orange')
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0008-000000000000', N'Orange', N'#FF7F00')
IF NOT EXISTS (SELECT * FROM [dbo].[ColorType] WHERE [Description]='Light purple')
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0009-000000000000', N'Light purple', N'#CAB2D6')
IF NOT EXISTS (SELECT * FROM [dbo].[ColorType] WHERE [Description]='Purple')
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0010-000000000000', N'Purple', N'#6A3D9A')


--LicensedFeature

IF NOT EXISTS (SELECT * FROM [dbo].[LicensedFeature] WHERE [Description]='EQ')
	INSERT [dbo].[LicensedFeature] ([Id], [Description]) VALUES (N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'EQ')
IF NOT EXISTS (SELECT * FROM [dbo].[LicensedFeature] WHERE [Description]='ReportMeta')
	INSERT [dbo].[LicensedFeature] ([Id], [Description]) VALUES (N'00000000-0000-0000-0000-000000000002', N'ReportMeta')
IF NOT EXISTS (SELECT * FROM [dbo].[LicensedFeature] WHERE [Description]='ShapeFile')
	INSERT [dbo].[LicensedFeature] ([Id], [Description]) VALUES (N'00000000-0000-0000-0000-000000000003', N'ShapeFile')

--IsotopicAnalysisDispositionType

IF NOT EXISTS (SELECT * FROM [dbo].[IsotopicAnalysisDispositionType] WHERE [IsotopicCaptureDescription]='Unknown')
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'0', N'Unknown', N'RERUN')
IF NOT EXISTS (SELECT * FROM [dbo].[IsotopicAnalysisDispositionType] WHERE [IsotopicCaptureDescription]='Isotopic Canceled')
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'1', N'Isotopic Canceled', N'CANCELED')
IF NOT EXISTS (SELECT * FROM [dbo].[IsotopicAnalysisDispositionType] WHERE [IsotopicCaptureDescription]='Sample size too small')
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'2', N'Sample size too small', N'RERUN')
IF NOT EXISTS (SELECT * FROM [dbo].[IsotopicAnalysisDispositionType] WHERE [IsotopicCaptureDescription]='Uncertainty out of range')
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'3', N'Uncertainty out of range', N'RERUN')
IF NOT EXISTS (SELECT * FROM [dbo].[IsotopicAnalysisDispositionType] WHERE [IsotopicCaptureDescription]='Delta out of range')
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'4', N'Delta out of range', N'RERUN')
IF NOT EXISTS (SELECT * FROM [dbo].[IsotopicAnalysisDispositionType] WHERE [IsotopicCaptureDescription]='Not Natural Gas')
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'5', N'Not Natural Gas', N'RERUN')
IF NOT EXISTS (SELECT * FROM [dbo].[IsotopicAnalysisDispositionType] WHERE [IsotopicCaptureDescription]='Natural Gas')
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'6', N'Natural Gas', N'PASSED')


--Surveyor:
-- Assign Surveyor to 'Santa Clara-picarro', 'sqacusloc', 'pge_SC' and 'sqaTestloc' locations.
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='00000014-fb61-2ef6-5dd1-39c8ac533d40')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara-picarro'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'00000014-fb61-2ef6-5dd1-39c8ac533d40', @locationID, N'SoftwareCar_2037_picarro')
END
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='00000014-fb61-2ef6-5dd1-39c8ac533d41')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqacusloc'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'00000014-fb61-2ef6-5dd1-39c8ac533d41', @locationID, N'SoftwareCar_2037_cust')
END
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='C24E9253-F195-9AEC-DE1E-39D0FBB5D8A1')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='pge_SC'
	INSERT [dbo].[SurveyorUnit] ([Id],[LocationId],[Description] ) VALUES('C24E9253-F195-9AEC-DE1E-39D0FBB5D8A1', @locationID, 'PGE-FEDS2015')
END
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='00000014-fb61-2ef6-5dd1-39c8ac533d42')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqaTestloc'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'00000014-fb61-2ef6-5dd1-39c8ac533d42', @locationID, N'SoftwareCar_2037_Testcust')
END


-- Analyzer
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]='FDDS2037' AND [SharedKey]='fdds2037')
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'00000015-db64-fde7-7e67-39c8ac533d50', N'00000014-fb61-2ef6-5dd1-39c8ac533d40', N'FDDS2037', N'fdds2037')
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]='FDDS2037-2' AND [SharedKey]='fdds2037-2')
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'00000015-db64-fde7-7e67-39c8ac533d52', N'00000014-fb61-2ef6-5dd1-39c8ac533d42', N'FDDS2037-2', N'fdds2037-2')
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]='FEDS2015' AND [SharedKey]='feds2015')
	INSERT [dbo].[Analyzer] ([Id],[SurveyorUnitId],[SerialNumber],[SharedKey]) VALUES ('00000015-DB64-FDE7-7E67-39C8AC533D49','C24E9253-F195-9AEC-DE1E-39D0FBB5D8A1','FEDS2015','feds2015')
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]='FDDS2037-1' AND [SharedKey]='fdds2037-1')
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'00000015-db64-fde7-7e67-39c8ac533d51', N'00000014-fb61-2ef6-5dd1-39c8ac533d41', N'FDDS2037-1', N'fdds2037-1')

	
--ReferenceGasBottle: (UPDATE if EXISTS, else INSERT)
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='00000014-fb61-2ef6-5dd1-39c8ac533d40', [BatchId]='109-56-12100', [IsotopicValue]=-32.7, [Date]=CAST(N'2014-01-01 00:00:00.000' AS DateTime) WHERE [Id]='00000015-db64-fde7-7e67-39c8ac544d60'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'00000015-db64-fde7-7e67-39c8ac544d60', N'00000014-fb61-2ef6-5dd1-39c8ac533d40', N'109-56-12100', -32.7, CAST(N'2014-01-01 00:00:00.000' AS DateTime))
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='00000014-fb61-2ef6-5dd1-39c8ac533d42', [BatchId]='109-56-12100', [IsotopicValue]=-32.7, [Date]=CAST(N'2014-01-01 00:00:00.000' AS DateTime) WHERE [Id]='00000015-db64-fde7-7e67-39c8ac544d62'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'00000015-db64-fde7-7e67-39c8ac544d62', N'00000014-fb61-2ef6-5dd1-39c8ac533d42', N'109-56-12100', -32.7, CAST(N'2014-01-01 00:00:00.000' AS DateTime))
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='00000014-fb61-2ef6-5dd1-39c8ac533d41', [BatchId]='109-56-12100', [IsotopicValue]=-32.7, [Date]=CAST(N'2014-01-01 00:00:00.000' AS DateTime) WHERE [Id]='00000015-db64-fde7-7e67-39c8ac544d61'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'00000015-db64-fde7-7e67-39c8ac544d61', N'00000014-fb61-2ef6-5dd1-39c8ac533d41', N'109-56-12100', -32.7, CAST(N'2014-01-01 00:00:00.000' AS DateTime))
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='C24E9253-F195-9AEC-DE1E-39D0FBB5D8A1', [BatchId]='109-56-12100', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-09-29 00:00:00.000' AS DateTime) WHERE [Id]='00000015-DB64-FDE7-7E67-39C8AC544D63'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'00000015-DB64-FDE7-7E67-39C8AC544D63', N'C24E9253-F195-9AEC-DE1E-39D0FBB5D8A1', N'109-56-12100', -32.7 ,CAST(N'2015-09-29 00:00:00.000' AS DateTime))


DECLARE @customerId uniqueidentifier
--Users:
--Users for manual
-- Users assigned to Location='Santa Clara-picarro', Customer='Picarro'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Picarro' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara-picarro'
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='Administrator')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C558',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'Seed_first',N'seed_last',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','Administrator')
-- Users assigned to Location='sqaTestloc', Customer='sqaTest'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'sqaTest' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqaTestloc'
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='uadmin@sqatest.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C580',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqatestua',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','uadmin@sqatest.com')
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='supervisor@sqatest.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C581',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqatestsu',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','supervisor@sqatest.com')
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='driver@sqatest.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C582',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqatestdr',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','driver@sqatest.com')

--Users for selenium automation
-- Users assigned to Location='Santa Clara-picarro', Customer='Picarro'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Picarro' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara-picarro'
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='sqapicua@picarro.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C560',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqapicua',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapicua@picarro.com')
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='sqapicad@picarro.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C559',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqapicad',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapicad@picarro.com')
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='sqapicsu@picarro.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C561',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqapicsu',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapicsu@picarro.com')
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='sqapicdr@picarro.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C562',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqapicdr',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapicdr@picarro.com')
-- Users assigned to Location='sqacusloc', Customer='sqacus'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'sqacus' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqaTestloc'
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='sqacusua@email.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C570',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqacusua',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqacusua@email.com')
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='sqacussu@email.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C571',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqacussu',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqacussu@email.com')
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='sqacusdr1@email.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C572',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqacusdr',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqacusdr1@email.com')
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='driver@testmr.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C575',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'driverTestMR',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','driver@testmr.com')


--Roles:
IF NOT EXISTS (SELECT * FROM [dbo].[Role] WHERE [Name]='Administrator')
	INSERT INTO [dbo].[Role] ([Id] ,[Name]) VALUES (N'2DAD1417-00B6-D003-63B9-39CA528B5884',N'Administrator')
IF NOT EXISTS (SELECT * FROM [dbo].[Role] WHERE [Name]='Driver')
	INSERT INTO [dbo].[Role] ([Id] ,[Name]) VALUES (N'00000000-0000-0000-0001-000000000000',N'Driver')
IF NOT EXISTS (SELECT * FROM [dbo].[Role] WHERE [Name]='Supervisor')
	INSERT INTO [dbo].[Role] ([Id] ,[Name]) VALUES (N'00000000-0000-0000-0002-000000000000',N'Supervisor')
IF NOT EXISTS (SELECT * FROM [dbo].[Role] WHERE [Name]='Utility Administrator')
	INSERT INTO [dbo].[Role] ([Id] ,[Name]) VALUES (N'00000000-0000-0000-0003-000000000000',N'Utility Administrator')
IF NOT EXISTS (SELECT * FROM [dbo].[Role] WHERE [Name]='Picarro Support')
	INSERT INTO [dbo].[Role] ([Id] ,[Name]) VALUES (N'00000000-0000-0000-0004-000000000000',N'Picarro Support')


--User Role:

---Manual
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='2DAD1417-00B6-D003-63B9-39CA528B5884' AND [UserId]='DE734DDF-363E-49FC-8DBC-39C8C221C558')
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'2DAD1417-00B6-D003-63B9-39CA528B5884',N'DE734DDF-363E-49FC-8DBC-39C8C221C558')
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]='DE734DDF-363E-49FC-8DBC-39C8C221C580')
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',N'DE734DDF-363E-49FC-8DBC-39C8C221C580')
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0002-000000000000' AND [UserId]='DE734DDF-363E-49FC-8DBC-39C8C221C581')
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0002-000000000000',N'DE734DDF-363E-49FC-8DBC-39C8C221C581')
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0001-000000000000' AND [UserId]='DE734DDF-363E-49FC-8DBC-39C8C221C582')
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0001-000000000000',N'DE734DDF-363E-49FC-8DBC-39C8C221C582')



--Automation (Add roles for sqapic* and sqacus* roles)

DECLARE @userId  uniqueidentifier

SET @userId = (SELECT [Id] FROM [dbo].[User] WHERE [UserName]='sqapicad@picarro.com')
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='2DAD1417-00B6-D003-63B9-39CA528B5884' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'2DAD1417-00B6-D003-63B9-39CA528B5884',@userId)
SET @userId = (SELECT [Id] FROM [dbo].[User] WHERE [UserName]='sqapicua@picarro.com')
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)
SET @userId = (SELECT [Id] FROM [dbo].[User] WHERE [UserName]='sqapicsu@picarro.com')
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0002-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0002-000000000000',@userId)
SET @userId = (SELECT [Id] FROM [dbo].[User] WHERE [UserName]='sqapicdr@picarro.com')
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0001-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0001-000000000000',@userId)
SET @userId = (SELECT [Id] FROM [dbo].[User] WHERE [UserName]='sqacusua@email.com') 
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)
SET @userId = (SELECT [Id] FROM [dbo].[User] WHERE [UserName]='sqacussu@email.com')  
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0002-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0002-000000000000',@userId)
SET @userId = (SELECT [Id] FROM [dbo].[User] WHERE [UserName]='sqacusdr1@email.com')  
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0001-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0001-000000000000',@userId)

---------------------------------------------------------------------------------------
--GIS Seed Script
-----------------------------------------------------------------------------------

IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE [Name]='PG&E')
	INSERT [dbo].[Customer] ([Id], [Name], [Eula], [Active]) VALUES (N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'PG&E', 
	  N'END USER LICENSE AND SOLUTION ASSURANCE AGREEMENT RES SOFTWARE (“EULA”) USER NOTICE: BY INSTALLING THIS SOFTWARE YOU AS LICENSEE ACKNOWLEDGE THAT 
	  YOU HAVE READ AND UNDERSTOOD THIS EULA AND AGREE TO THE CONDITIONS AND PROVISIONS HEREIN AND THAT YOU ARE DULY AUTHORIZED TO EXECUTE THIS EULA. 
	  YOU SHALL INFORM ALL USERS OF THE SOFTWARE OF THE TERMS AND CONDITIONS OF THIS EULA. YOU ACCEPT THAT THIS EULA IS THE FULL AND EXCLUSIVE EXPRESSION 
	  OF THE AGREEMENT BETWEEN YOU AND RES SOFTWARE AND THAT IT TAKES PRECEDENCE OVER ALL PREVIOUS PROPOSALS OR VERBAL OR WRITTEN AGREEMENTS AND OTHER 
	  POSSIBLE COMMUNICATIONS REGARDING THE SUBJECT OF THIS EULA. IF YOU DO NOT ACCEPT THE TERMS OF THIS AGREEMENT, YOU MAY NOT INSTALL THIS SOFTWARE.
	   ', 1);

IF NOT EXISTS (SELECT * FROM [dbo].[CustomerBoundaryType] WHERE [CustomerId]='b1252204-04fb-4a67-82d4-3f4666fd855c' AND [FeatureClassDescription]='District Plat')
	INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'551cb7c0-005b-4e3e-bfae-d19da0ed7efe', N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'District Plat', N'', 2, 0, 5, 1)
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerBoundaryType] WHERE [CustomerId]='b1252204-04fb-4a67-82d4-3f4666fd855c' AND [FeatureClassDescription]='District')
	INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'024249ae-374b-4f6f-bd87-e8fdcacb48e1', N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'District', N'#B5DBF4', 2, 0, 5, 1)
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerBoundaryType] WHERE [CustomerId]='e871c797-b62d-ef28-0ea7-39cae44e5c19' AND [FeatureClassDescription]='District Plat')
	INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'b9e69b56-e43e-4f6c-afb1-24c01c1dd9dc', N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'District Plat', N'', 2, 0, 5, 1)
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerBoundaryType] WHERE [CustomerId]='e871c797-b62d-ef28-0ea7-39cae44e5c19' AND [FeatureClassDescription]='District')
	INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'3bb4b2ae-9591-4607-9cad-fd2eb377760a', N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'District', N'#B5DBF4', 2, 0, 5, 0)

-- CustomerMaterialType (UPDATE if exists, else INSERT)
UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]='e871c797-b62d-ef28-0ea7-39cae44e5c19', [Description]='PGE-Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5fb149dc-0318-4e53-8e55-252c96634ce9' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5fb149dc-0318-4e53-8e55-252c96634ce9', N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'PGE-Copper', N'#B5DBF4', 2, 0)

UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]='e871c797-b62d-ef28-0ea7-39cae44e5c19', [Description]='PGE-Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='c37aa3cc-f41f-42b9-8c7d-3bea58f6f495'
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'c37aa3cc-f41f-42b9-8c7d-3bea58f6f495', N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'PGE-Protected Steel', N'#FB9A99', 2, 0)

UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]='e871c797-b62d-ef28-0ea7-39cae44e5c19', [Description]='PGE-Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9119e94c-b7cb-4e1d-ae92-57dd72cb41f7'
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9119e94c-b7cb-4e1d-ae92-57dd72cb41f7', N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'PGE-Un-protected Steel', N'#B2DF8A', 2, 0)

UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]='e871c797-b62d-ef28-0ea7-39cae44e5c19', [Description]='PGE-PE Plastic', [Color]=N'', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='69ceab1e-77fc-47a2-a774-ae3554939c95'
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'69ceab1e-77fc-47a2-a774-ae3554939c95', N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'PGE-PE Plastic', N'', 2, 0)

UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]='e871c797-b62d-ef28-0ea7-39cae44e5c19', [Description]='PGE-Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='50987927-5724-4d0a-bd7e-bd770ebb911c' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'50987927-5724-4d0a-bd7e-bd770ebb911c', N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'PGE-Cast Iron', N'#E31A1C', 2, 0)

UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]='e871c797-b62d-ef28-0ea7-39cae44e5c19', [Description]='PGE-Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6fc8f1cf-614c-4107-8209-c40569d53193' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6fc8f1cf-614c-4107-8209-c40569d53193', N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'PGE-Other Plastic', N'#FDE36F', 2, 0)

UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]='b1252204-04fb-4a67-82d4-3f4666fd855c', [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='d08fc87f-f979-4131-92a9-3d82f37f4bba' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'd08fc87f-f979-4131-92a9-3d82f37f4bba', N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'Copper', N'#B5DBF4', 2, 0)

UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]='b1252204-04fb-4a67-82d4-3f4666fd855c', [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='44353e68-0694-4f05-85cb-84d753ea278c' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'44353e68-0694-4f05-85cb-84d753ea278c', N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'Protected Steel', N'#FB9A99', 2, 0)

UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]='b1252204-04fb-4a67-82d4-3f4666fd855c', [Description]='PE Plastic', [Color]=N'', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='f14735de-6c9b-4423-8533-f243a7fe4e90' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'f14735de-6c9b-4423-8533-f243a7fe4e90', N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'PE Plastic', N'', 2, 0)

UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]='b1252204-04fb-4a67-82d4-3f4666fd855c', [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='96caf1f5-d5c5-461d-9ce3-d210c20a1bb0' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'96caf1f5-d5c5-461d-9ce3-d210c20a1bb0', N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'Cast Iron', N'#E31A1C', 2, 0)

UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]='b1252204-04fb-4a67-82d4-3f4666fd855c', [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='ad701312-c470-482a-be45-ef37770e2ce6' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'ad701312-c470-482a-be45-ef37770e2ce6', N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'Other Plastic', N'#FDE36F', 2, 0)

--Sample BCP import command, set the appropriate Database name, file path, SQL login user name and password
--bcp "[SurveyorXXXX].[dbo].[Boundary]" in "...\Boundary_Sample_Data.dat" -t\t -w -U awssa -P j!RuL1Gd7A -S 20.20.64.100
--for sqa:--bcp "[[SurveyorSQA964]].[dbo].[Boundary]" in "C:\Repo\surveyor\Src\Web\Picarro.Surveyor.Db\Seed\Boundary_Sample_Data.dat" -t\t -w -U awssa -P j!RuL1Gd7A -S 20.20.130.238
--bcp "[SurveyorXXXX].[dbo].[Asset]" in "...\Asset_Sample_Data.dat" -t\t -w -U awssa -P j!RuL1Gd7A -S 20.20.64.100
--for sqa:--bcp "[[SurveyorSQA964]].[dbo].[Asset]" in "C:\Repo\surveyor\Src\Web\Picarro.Surveyor.Db\Seed\Asset_Sample_Data.dat" -t\t -w -U awssa -P j!RuL1Gd7A -S 20.20.130.238
---------------------------------------------------------------------------------------------------
--pge location

IF NOT EXISTS (SELECT * FROM [dbo].[Location] WHERE [CustomerId]='e871c797-b62d-ef28-0ea7-39cae44e5c19' AND [Description]='pge_SC')
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'EE13ACD0-C158-ECAC-7F48-39D18113D501', N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'pge_SC','37.4020925705503','-121.984820397399')


--PG&E Users for GIS

IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='driver@pge.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'EE734DDF-363E-49FC-8DBC-39C8C221C550',N'e871c797-b62d-ef28-0ea7-39cae44e5c19', NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',N'EE13ACD0-C158-ECAC-7F48-39D18113D501',N'pge_driver',N'last',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','driver@pge.com')
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='uadmin@pge.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'EE734DDF-363E-49FC-8DBC-39C8C221C551',N'e871c797-b62d-ef28-0ea7-39cae44e5c19', NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',N'EE13ACD0-C158-ECAC-7F48-39D18113D501',N'pge_admin',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','uadmin@pge.com')
IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='supervisor@pge.com')
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'EE734DDF-363E-49FC-8DBC-39C8C221C552',N'e871c797-b62d-ef28-0ea7-39cae44e5c19', NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',N'EE13ACD0-C158-ECAC-7F48-39D18113D501',N'pgesu',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','supervisor@pge.com')

IF NOT EXISTS (SELECT * FROM [dbo].[EQConfidenceGroup] WHERE [Description]='0 - 1 SCFH')
	INSERT [dbo].[EQConfidenceGroup] ([ConfidenceGroup], [Description], [Color]) VALUES (0, N'0 - 1 SCFH', N'FFFFFF')
IF NOT EXISTS (SELECT * FROM [dbo].[EQConfidenceGroup] WHERE [Description]='0.5 - 2 SCFH')
	INSERT [dbo].[EQConfidenceGroup] ([ConfidenceGroup], [Description], [Color]) VALUES (1, N'0.5 - 2 SCFH', N'FFFFFF')
IF NOT EXISTS (SELECT * FROM [dbo].[EQConfidenceGroup] WHERE [Description]='1 - 4 SCFH')
	INSERT [dbo].[EQConfidenceGroup] ([ConfidenceGroup], [Description], [Color]) VALUES (2, N'1 - 4 SCFH', N'FFFFFF')
IF NOT EXISTS (SELECT * FROM [dbo].[EQConfidenceGroup] WHERE [Description]='2 - 8 SCFH')
	INSERT [dbo].[EQConfidenceGroup] ([ConfidenceGroup], [Description], [Color]) VALUES (3, N'2 - 8 SCFH', N'FFFFFF')
IF NOT EXISTS (SELECT * FROM [dbo].[EQConfidenceGroup] WHERE [Description]='4 - 16 SCFH')
	INSERT [dbo].[EQConfidenceGroup] ([ConfidenceGroup], [Description], [Color]) VALUES (4, N'4 - 16 SCFH', N'FFFFFF')
IF NOT EXISTS (SELECT * FROM [dbo].[EQConfidenceGroup] WHERE [Description]='8 - 32 SCFH')
	INSERT [dbo].[EQConfidenceGroup] ([ConfidenceGroup], [Description], [Color]) VALUES (5, N'8 - 32 SCFH', N'FFFFFF')
IF NOT EXISTS (SELECT * FROM [dbo].[EQConfidenceGroup] WHERE [Description]='16 - 64 SCFH')
	INSERT [dbo].[EQConfidenceGroup] ([ConfidenceGroup], [Description], [Color]) VALUES (6, N'16 - 64 SCFH', N'FFFFFF')
IF NOT EXISTS (SELECT * FROM [dbo].[EQConfidenceGroup] WHERE [Description]='32 - 128 SCFH')
	INSERT [dbo].[EQConfidenceGroup] ([ConfidenceGroup], [Description], [Color]) VALUES (7, N'32 - 128 SCFH', N'FFFFFF')
IF NOT EXISTS (SELECT * FROM [dbo].[EQConfidenceGroup] WHERE [Description]='64 - 256 SCFH')
	INSERT [dbo].[EQConfidenceGroup] ([ConfidenceGroup], [Description], [Color]) VALUES (8, N'64 - 256 SCFH', N'FFFFFF')
IF NOT EXISTS (SELECT * FROM [dbo].[EQConfidenceGroup] WHERE [Description]='128 - 512 SCFH')
	INSERT [dbo].[EQConfidenceGroup] ([ConfidenceGroup], [Description], [Color]) VALUES (9, N'128 - 512 SCFH', N'FFFFFF')

COMMIT TRAN

END TRY

BEGIN CATCH
    --returns the complete original error message as a result set
    SELECT 
        ERROR_NUMBER() AS ErrorNumber
        ,ERROR_SEVERITY() AS ErrorSeverity
        ,ERROR_STATE() AS ErrorState
        ,ERROR_PROCEDURE() AS ErrorProcedure
        ,ERROR_LINE() AS ErrorLine
        ,ERROR_MESSAGE() AS ErrorMessage

    --will return the complete original error message as an error message
    DECLARE @ErrorMessage nvarchar(400), @ErrorNumber int, @ErrorSeverity int, @ErrorState int, @ErrorLine int
    SELECT @ErrorMessage = N'Error %d, Line %d, Message: '+ERROR_MESSAGE(),@ErrorNumber = ERROR_NUMBER(),@ErrorSeverity = ERROR_SEVERITY(),@ErrorState = ERROR_STATE(),@ErrorLine = ERROR_LINE()
    RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState, @ErrorNumber,@ErrorLine)
    
END CATCH
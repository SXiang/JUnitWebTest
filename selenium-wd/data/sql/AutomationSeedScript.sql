-- Automation seed script

BEGIN TRY
    BEGIN TRAN
 -- sample data

 
--TimeZone:

UPDATE [dbo].[TimeZone] SET [Description]=N'Pacific Standard Time',[UIDescription]=N'Pacific Time (US and Canada)' WHERE [Id]=N'00000000-0000-0000-0001-000000000000'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[TimeZone] ([Id],[Description],[UIDescription])VALUES (N'00000000-0000-0000-0001-000000000000',N'Pacific Standard Time',N'Pacific Time (US and Canada)')
UPDATE [dbo].[TimeZone] SET [Description]=N'Mountain Standard Time',[UIDescription]=N'Mountain Time (US and Canada)' WHERE [Id]=N'00000000-0000-0000-0002-000000000000'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[TimeZone] ([Id],[Description],[UIDescription])VALUES (N'00000000-0000-0000-0002-000000000000',N'Mountain Standard Time',N'Mountain Time (US and Canada)')
UPDATE [dbo].[TimeZone] SET [Description]=N'Central Standard Time',[UIDescription]=N'Central Time (US and Canada)' WHERE [Id]=N'00000000-0000-0000-0003-000000000000'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[TimeZone] ([Id],[Description],[UIDescription])VALUES (N'00000000-0000-0000-0003-000000000000',N'Central Standard Time',N'Central Time (US and Canada)')
UPDATE [dbo].[TimeZone] SET [Description]=N'Eastern Standard Time',[UIDescription]=N'Eastern Time (US and Canada)' WHERE [Id]=N'00000000-0000-0000-0004-000000000000'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[TimeZone] ([Id],[Description],[UIDescription])VALUES (N'00000000-0000-0000-0004-000000000000',N'Eastern Standard Time',N'Eastern Time (US and Canada)')


 -- Customer:

--SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='sqaTest'
--SELECT * FROM [dbo].[Customer] WHERE [Id]='b1252204-04fb-4a67-82d4-3f4666fd855c'

-- Name is UNIQUE. Check for Name first. If NOT existing, INSERT after checking for ID.
-- NEW UID has been generated. Do NOT check for IDs for Customer as the generated IDs might differ. Instead get ID by Customer Name.
IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='Picarro')
BEGIN
	UPDATE [dbo].[Customer] SET [Name]=N'Picarro',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='b1252204-04fb-4a67-82d4-3f4666fd855c' 
	IF @@ROWCOUNT=0
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'b1252204-04fb-4a67-82d4-3f4666fd855c',N'Picarro' ,N'Accept the agreement',1)
	ELSE
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (NEWID(), N'Picarro' ,N'Accept the agreement',1)		
END
IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='sqacus')
BEGIN
	UPDATE [dbo].[Customer] SET [Name]=N'sqacus',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='00000000-0000-0000-0000-000000000002' 
	IF @@ROWCOUNT=0
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'00000000-0000-0000-0000-000000000002',N'sqacus' ,N'Accept the agreement',1)
	ELSE
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (NEWID(), N'sqacus' ,N'Accept the agreement',1)		
END
IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='sqaTest')
BEGIN
	UPDATE [dbo].[Customer] SET [Name]=N'sqaTest',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='00000000-0000-0000-0000-000000000003' 
	IF @@ROWCOUNT=0
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'00000000-0000-0000-0000-000000000003',N'sqaTest' ,N'Accept the agreement',1)
	ELSE
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (NEWID(), N'sqaTest' ,N'Accept the agreement',1)		
END

-- Location:
DECLARE @customerId nvarchar(400)

SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'Santa Clara-picarro',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='DE13ACD0-C158-ECAC-7F48-39D18113D701' 
IF @@ROWCOUNT=0
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D701', @customerId, N'Santa Clara-picarro','37.4020925705503','-121.984820397399')
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='sqacus'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'sqacusloc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='DE13ACD0-C158-ECAC-7F48-39D18113D702' 
IF @@ROWCOUNT=0
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D702', @customerId, N'sqacusloc','37.4020925705503','-121.984820397399')
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='sqaTest'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'sqaTestloc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='DE13ACD0-C158-ECAC-7F48-39D18113D703' 
IF @@ROWCOUNT=0
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D703', @customerId, N'sqaTestloc','37.4020925705503','-121.984820397399')


-- SurveyModeType:

UPDATE [dbo].[SurveyModeType] SET [Description]=N'Rapid Response' WHERE [Id]=N'4901E67A-4C00-4436-ADC0-9CFB277BB310' 
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[SurveyModeType]([Id],[Description])VALUES (N'4901E67A-4C00-4436-ADC0-9CFB277BB310',N'Rapid Response');
UPDATE [dbo].[SurveyModeType] SET [Description]=N'Manual' WHERE [Id]=N'4901E67A-4C00-4436-ADC0-9CFB277BB311' 
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[SurveyModeType]([Id],[Description])VALUES (N'4901E67A-4C00-4436-ADC0-9CFB277BB311',N'Manual');
UPDATE [dbo].[SurveyModeType] SET [Description]=N'Operator' WHERE [Id]=N'4901E67A-4C00-4436-ADC0-9CFB277BB312' 
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[SurveyModeType]([Id],[Description])VALUES (N'4901E67A-4C00-4436-ADC0-9CFB277BB312',N'Operator');
UPDATE [dbo].[SurveyModeType] SET [Description]=N'Standard' WHERE [Id]=N'B310238A-A5AE-4E94-927B-F0F165E24522' 
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[SurveyModeType]([Id],[Description])VALUES (N'B310238A-A5AE-4E94-927B-F0F165E24522',N'Standard');
UPDATE [dbo].[SurveyModeType] SET [Description]=N'EQ' WHERE [Id]=N'E9DD9F53-E5CB-45B3-9517-9DC8E0276C6D' 
IF @@ROWCOUNT=0
	INSERT [dbo].[SurveyModeType] ([Id], [Description]) VALUES (N'E9DD9F53-E5CB-45B3-9517-9DC8E0276C6D', N'EQ')


--SurveyModeTypeConfiguration:

DECLARE @locationID uniqueidentifier
DECLARE @surveyModeTypeID uniqueidentifier 

/* NOTE: FovVersion is set to 1 for 3100 algorithm in web.config, code will use FovVersion1Ratio value.
 * Here are corresponding values for MinimumAmplitude, LeakRate
*/
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara-picarro'
SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Rapid Response'

UPDATE [dbo].[SurveyModeTypeConfiguration] SET [MinimumAmplitude]=5, [LeakRate]=166.666666667 WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 5, 166.666666667)
SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Manual'
UPDATE [dbo].[SurveyModeTypeConfiguration] SET [MinimumAmplitude]=0.035, [LeakRate]=166.666666667 WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 0.035, 1.16666666667)
SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Operator'
UPDATE [dbo].[SurveyModeTypeConfiguration] SET [MinimumAmplitude]=5, [LeakRate]=166.666666667 WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 5, 166.666666667)
SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Standard'
UPDATE [dbo].[SurveyModeTypeConfiguration] SET [MinimumAmplitude]=0.035, [LeakRate]=166.666666667 WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude], [LeakRate]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 0.035, 1.16666666667)
SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='EQ'
UPDATE [dbo].[SurveyModeTypeConfiguration] SET [MinimumAmplitude]=05, [LeakRate]=166.666666667 WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
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
UPDATE [dbo].[IsotopicIdentity] SET [NoLowerBound]=-43, [YesLowerBound]=-40, [YesUpperBound]=-30, [NoUpperBound]=-25 WHERE [LocationId] = @locationID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[IsotopicIdentity] ([LocationId], [FromDate], [NoLowerBound], [YesLowerBound], [YesUpperBound], [NoUpperBound]) VALUES (@locationID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), -43, -40, -30, -25)
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqacusloc'
UPDATE [dbo].[IsotopicIdentity] SET [NoLowerBound]=-43, [YesLowerBound]=-40, [YesUpperBound]=-30, [NoUpperBound]=-25 WHERE [LocationId] = @locationID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[IsotopicIdentity] ([LocationId], [FromDate], [NoLowerBound], [YesLowerBound], [YesUpperBound], [NoUpperBound]) VALUES (@locationID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), -43, -40, -30, -25)
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqaTestloc'
UPDATE [dbo].[IsotopicIdentity] SET [NoLowerBound]=-43, [YesLowerBound]=-40, [YesUpperBound]=-30, [NoUpperBound]=-25 WHERE [LocationId] = @locationID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[IsotopicIdentity] ([LocationId], [FromDate], [NoLowerBound], [YesLowerBound], [YesUpperBound], [NoUpperBound]) VALUES (@locationID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), -43, -40, -30, -25)

	
--ReportStatusType:
UPDATE [dbo].[ReportStatusType] SET [Description]=N'Pending' WHERE [Id]=N'00000000-0000-0000-0001-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Pending')
UPDATE [dbo].[ReportStatusType] SET [Description]=N'Processing' WHERE [Id]=N'00000000-0000-0000-0002-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Processing')
UPDATE [dbo].[ReportStatusType] SET [Description]=N'Complete' WHERE [Id]=N'00000000-0000-0000-0003-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'Complete')


--ReportType:

UPDATE [dbo].[ReportType] SET [Description]=N'Compliance' WHERE [Id]=N'00000000-0000-0000-0001-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Compliance')
UPDATE [dbo].[ReportType] SET [Description]=N'Investigation' WHERE [Id]=N'00000000-0000-0000-0002-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Investigation')
UPDATE [dbo].[ReportType] SET [Description]=N'ReferenceGas' WHERE [Id]=N'00000000-0000-0000-0003-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'ReferenceGas')
UPDATE [dbo].[ReportType] SET [Description]=N'SystemHistory' WHERE [Id]=N'00000000-0000-0000-0004-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0004-000000000000', N'SystemHistory')
UPDATE [dbo].[ReportType] SET [Description]=N'EQ' WHERE [Id]=N'00000000-0000-0000-0005-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0005-000000000000', N'EQ')

--ReportJobStatusType:

UPDATE [dbo].[ReportJobStatusType] SET [Description]=N'Pending' WHERE [Id]=N'00000000-0000-0000-0001-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Pending')
UPDATE [dbo].[ReportJobStatusType] SET [Description]=N'Processing' WHERE [Id]=N'00000000-0000-0000-0002-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Processing')
UPDATE [dbo].[ReportJobStatusType] SET [Description]=N'Complete' WHERE [Id]=N'00000000-0000-0000-0003-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'Complete')
UPDATE [dbo].[ReportJobStatusType] SET [Description]=N'Failed' WHERE [Id]=N'00000000-0000-0000-0004-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobStatusType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0004-000000000000', N'Failed')


--ReportJobType
UPDATE [dbo].[ReportJobType] SET [Description]=N'Map' WHERE [Id]=N'00000000-0000-0000-0001-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Map')
UPDATE [dbo].[ReportJobType] SET [Description]=N'SSRS' WHERE [Id]=N'00000000-0000-0000-0002-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'SSRS')
UPDATE [dbo].[ReportJobType] SET [Description]=N'DataGeneration' WHERE [Id]=N'00000000-0000-0000-0003-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'DataGeneration')
UPDATE [dbo].[ReportJobType] SET [Description]=N'EQMap' WHERE [Id]=N'00000000-0000-0000-0004-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0004-000000000000', N'EQMap')
UPDATE [dbo].[ReportJobType] SET [Description]=N'EQSSRS' WHERE [Id]=N'00000000-0000-0000-0005-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0005-000000000000', N'EQSSRS')
UPDATE [dbo].[ReportJobType] SET [Description]=N'EQDataGeneration' WHERE [Id]=N'00000000-0000-0000-0006-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0006-000000000000', N'EQDataGeneration')
UPDATE [dbo].[ReportJobType] SET [Description]=N'ShapeFile' WHERE [Id]=N'00000000-0000-0000-0007-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0007-000000000000', N'ShapeFile')
UPDATE [dbo].[ReportJobType] SET [Description]=N'ReportMeta' WHERE [Id]=N'00000000-0000-0000-0008-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0008-000000000000', N'ReportMeta')
UPDATE [dbo].[ReportJobType] SET [Description]=N'PercentCoverageForecast' WHERE [Id]=N'00000000-0000-0000-0009-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportJobType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0009-000000000000', N'PercentCoverageForecast')

--ReportAreaModeType:

UPDATE [dbo].[ReportAreaModeType] SET [Description]=N'Customer Boundary' WHERE [Id]=N'00000000-0000-0000-0001-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportAreaModeType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Customer Boundary')
UPDATE [dbo].[ReportAreaModeType] SET [Description]=N'Custom Boundary' WHERE [Id]=N'00000000-0000-0000-0002-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportAreaModeType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Custom Boundary')
UPDATE [dbo].[ReportAreaModeType] SET [Description]=N'Driving Area' WHERE [Id]=N'00000000-0000-0000-0003-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportAreaModeType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'Driving Area')
UPDATE [dbo].[ReportAreaModeType] SET [Description]=N'Lat/long' WHERE [Id]=N'cc26bb15-db64-fde7-7e67-39c8ac522d49'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportAreaModeType] ([Id], [Description]) VALUES (N'cc26bb15-db64-fde7-7e67-39c8ac522d49', N'Lat/long')
UPDATE [dbo].[ReportAreaModeType] SET [Description]=N'Boundary' WHERE [Id]=N'cc26bb15-db64-fde7-7e67-39c8ac522d50'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReportAreaModeType] ([Id], [Description]) VALUES (N'cc26bb15-db64-fde7-7e67-39c8ac522d50', N'Boundary')

--AssetType:

UPDATE [dbo].[AssetType] SET [Description]=N'Main' WHERE [Id]=N'b1252204-04fb-4a67-82d4-3f4666fd855c'
IF @@ROWCOUNT=0
	INSERT [dbo].[AssetType] ([Id], [Description]) VALUES (N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'Main')
UPDATE [dbo].[AssetType] SET [Description]=N'Service' WHERE [Id]=N'00000000-0000-0000-0000-000000000002'
IF @@ROWCOUNT=0
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

UPDATE [dbo].[BaseMapType] SET [Description]='Map' WHERE [Id]='00000000-0000-0000-0001-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[BaseMapType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Map')
UPDATE [dbo].[BaseMapType] SET [Description]='Satellite' WHERE [Id]='00000000-0000-0000-0002-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[BaseMapType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Satellite')
UPDATE [dbo].[BaseMapType] SET [Description]='None' WHERE [Id]='00000000-0000-0000-0003-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[BaseMapType] ([Id], [Description]) VALUES (N'00000000-0000-0000-0003-000000000000', N'None')

--ColorType:

UPDATE [dbo].[ColorType] SET [Description]=N'Light blue', [Hex]=N'#B5DBF4' WHERE [Id]='00000000-0000-0000-0001-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0001-000000000000', N'Light blue', N'#B5DBF4')
UPDATE [dbo].[ColorType] SET [Description]=N'Blue', [Hex]=N'#1F78B4' WHERE [Id]='00000000-0000-0000-0002-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0002-000000000000', N'Blue', N'#1F78B4')
UPDATE [dbo].[ColorType] SET [Description]=N'Light green', [Hex]=N'#B2DF8A' WHERE [Id]='00000000-0000-0000-0003-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0003-000000000000', N'Light green', N'#B2DF8A')
UPDATE [dbo].[ColorType] SET [Description]=N'Green', [Hex]=N'#33A02C' WHERE [Id]='00000000-0000-0000-0004-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0004-000000000000', N'Green', N'#33A02C')
UPDATE [dbo].[ColorType] SET [Description]=N'Salmon', [Hex]=N'#FB9A99' WHERE [Id]='00000000-0000-0000-0005-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0005-000000000000', N'Salmon', N'#FB9A99')
UPDATE [dbo].[ColorType] SET [Description]=N'Red', [Hex]=N'#E31A1C' WHERE [Id]='00000000-0000-0000-0006-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0006-000000000000', N'Red', N'#E31A1C')
UPDATE [dbo].[ColorType] SET [Description]=N'Yellow', [Hex]=N'#FDE36F' WHERE [Id]='00000000-0000-0000-0007-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0007-000000000000', N'Yellow', N'#FDE36F')
UPDATE [dbo].[ColorType] SET [Description]=N'Orange', [Hex]=N'#FF7F00' WHERE [Id]='00000000-0000-0000-0008-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0008-000000000000', N'Orange', N'#FF7F00')
UPDATE [dbo].[ColorType] SET [Description]=N'Light purple', [Hex]=N'#CAB2D6' WHERE [Id]='00000000-0000-0000-0009-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0009-000000000000', N'Light purple', N'#CAB2D6')
UPDATE [dbo].[ColorType] SET [Description]=N'Purple', [Hex]=N'#6A3D9A' WHERE [Id]='00000000-0000-0000-0010-000000000000'
IF @@ROWCOUNT=0
	INSERT [dbo].[ColorType] ([Id], [Description], [Hex]) VALUES (N'00000000-0000-0000-0010-000000000000', N'Purple', N'#6A3D9A')


--LicensedFeature

UPDATE [dbo].[LicensedFeature] SET [Description]='EQ' WHERE [Id]='b1252204-04fb-4a67-82d4-3f4666fd855c'
IF @@ROWCOUNT=0
	INSERT [dbo].[LicensedFeature] ([Id], [Description]) VALUES (N'b1252204-04fb-4a67-82d4-3f4666fd855c', N'EQ')
UPDATE [dbo].[LicensedFeature] SET [Description]='ReportMeta' WHERE [Id]='00000000-0000-0000-0000-000000000002'
IF @@ROWCOUNT=0
	INSERT [dbo].[LicensedFeature] ([Id], [Description]) VALUES (N'00000000-0000-0000-0000-000000000002', N'ReportMeta')
UPDATE [dbo].[LicensedFeature] SET [Description]='ShapeFile' WHERE [Id]='00000000-0000-0000-0000-000000000003'
IF @@ROWCOUNT=0
	INSERT [dbo].[LicensedFeature] ([Id], [Description]) VALUES (N'00000000-0000-0000-0000-000000000003', N'ShapeFile')

--IsotopicAnalysisDispositionType

UPDATE [dbo].[IsotopicAnalysisDispositionType] SET [IsotopicCaptureDescription]=N'Unknown', [ReferenceGasCaptureDescription]=N'RERUN' WHERE [Id]=N'0'
IF @@ROWCOUNT=0
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'0', N'Unknown', N'RERUN')
UPDATE [dbo].[IsotopicAnalysisDispositionType] SET [IsotopicCaptureDescription]=N'Isotopic Canceled', [ReferenceGasCaptureDescription]=N'CANCELED' WHERE [Id]=N'1'
IF @@ROWCOUNT=0
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'1', N'Isotopic Canceled', N'CANCELED')
UPDATE [dbo].[IsotopicAnalysisDispositionType] SET [IsotopicCaptureDescription]=N'Sample size too small', [ReferenceGasCaptureDescription]=N'RERUN' WHERE [Id]=N'2'
IF @@ROWCOUNT=0
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'2', N'Sample size too small', N'RERUN')
UPDATE [dbo].[IsotopicAnalysisDispositionType] SET [IsotopicCaptureDescription]=N'Uncertainty out of range', [ReferenceGasCaptureDescription]=N'RERUN' WHERE [Id]=N'3'
IF @@ROWCOUNT=0
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'3', N'Uncertainty out of range', N'RERUN')
UPDATE [dbo].[IsotopicAnalysisDispositionType] SET [IsotopicCaptureDescription]=N'Delta out of range', [ReferenceGasCaptureDescription]=N'RERUN' WHERE [Id]=N'4'
IF @@ROWCOUNT=0
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'4', N'Delta out of range', N'RERUN')
UPDATE [dbo].[IsotopicAnalysisDispositionType] SET [IsotopicCaptureDescription]=N'Not Natural Gas', [ReferenceGasCaptureDescription]=N'RERUN' WHERE [Id]=N'5'
IF @@ROWCOUNT=0
	INSERT [dbo].[IsotopicAnalysisDispositionType] ([Id], [IsotopicCaptureDescription], [ReferenceGasCaptureDescription]) VALUES (N'5', N'Not Natural Gas', N'RERUN')
UPDATE [dbo].[IsotopicAnalysisDispositionType] SET [IsotopicCaptureDescription]=N'Natural Gas', [ReferenceGasCaptureDescription]=N'PASSED' WHERE [Id]=N'6'
IF @@ROWCOUNT=0
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
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='FB4F3579-843A-113E-001C-39D4011393C9')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara-picarro'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'FB4F3579-843A-113E-001C-39D4011393C9', @locationID, N'SimAuto-Surveyor1')
END


-- Analyzer
-- NOTE: [SerialNumber] AND [SharedKey] are UNIQUE for Analyzer. 
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'FDDS2037' AND [SharedKey]=N'fdds2037')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'00000014-fb61-2ef6-5dd1-39c8ac533d40', [SerialNumber]=N'FDDS2037', [SharedKey]=N'fdds2037' WHERE [Id]='00000015-db64-fde7-7e67-39c8ac533d50'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'00000015-db64-fde7-7e67-39c8ac533d50', N'00000014-fb61-2ef6-5dd1-39c8ac533d40', N'FDDS2037', N'fdds2037')
END
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'FDDS2037-2' AND [SharedKey]=N'fdds2037-2')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'00000014-fb61-2ef6-5dd1-39c8ac533d42', [SerialNumber]=N'FDDS2037-2', [SharedKey]=N'fdds2037-2' WHERE [Id]='00000015-db64-fde7-7e67-39c8ac533d52'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'00000015-db64-fde7-7e67-39c8ac533d52', N'00000014-fb61-2ef6-5dd1-39c8ac533d42', N'FDDS2037-2', N'fdds2037-2')
END
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'FEDS2015' AND [SharedKey]=N'feds2015')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'C24E9253-F195-9AEC-DE1E-39D0FBB5D8A1', [SerialNumber]=N'FEDS2015', [SharedKey]=N'feds2015' WHERE [Id]='00000015-DB64-FDE7-7E67-39C8AC533D49'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id],[SurveyorUnitId],[SerialNumber],[SharedKey]) VALUES ('00000015-DB64-FDE7-7E67-39C8AC533D49','C24E9253-F195-9AEC-DE1E-39D0FBB5D8A1','FEDS2015','feds2015')
END
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'FDDS2037-1' AND [SharedKey]=N'fdds2037-1')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'00000014-fb61-2ef6-5dd1-39c8ac533d41', [SerialNumber]=N'FDDS2037-1', [SharedKey]=N'fdds2037-1' WHERE [Id]='00000015-db64-fde7-7e67-39c8ac533d51'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'00000015-db64-fde7-7e67-39c8ac533d51', N'00000014-fb61-2ef6-5dd1-39c8ac533d41', N'FDDS2037-1', N'fdds2037-1')
END
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-Analyzer1' AND [SharedKey]=N'SimAuto-AnalyzerKey1')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'FB4F3579-843A-113E-001C-39D4011393C90', [SerialNumber]=N'SimAuto-Analyzer1', [SharedKey]=N'SimAuto-AnalyzerKey1' WHERE [Id]='58839947-9569-952D-16D6-39D4011442FA'
IF @@ROWCOUNT=0
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]='SimAuto-Analyzer1' AND [SharedKey]='SimAuto-AnalyzerKey1')
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'58839947-9569-952D-16D6-39D4011442FA', N'FB4F3579-843A-113E-001C-39D4011393C9', N'SimAuto-Analyzer1', N'SimAuto-AnalyzerKey1')
END
	
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
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='FB4F3579-843A-113E-001C-39D4011393C9', [BatchId]='Sim-RefGasBottle1', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='6FA982CC-6232-D7B9-F5AC-39D40114FA56'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'6FA982CC-6232-D7B9-F5AC-39D40114FA56', N'FB4F3579-843A-113E-001C-39D4011393C9', N'Sim-RefGasBottle1', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))


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
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqapicua',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqapicua@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C560',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqapicua',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapicua@picarro.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqapicad',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqapicad@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C559',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqapicad',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapicad@picarro.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqapicsu',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqapicsu@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C561',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqapicsu',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapicsu@picarro.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqapicsup',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqapicsup@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'575DC0C7-7927-3EA6-56E4-39D375BFA723',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqapicsup',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapicsup@picarro.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqapicdr',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqapicdr@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C562',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqapicdr',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapicdr@picarro.com')
-- Users assigned to Location='sqacusloc', Customer='sqacus'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'sqacus' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqaTestloc'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqacusua',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqacusua@email.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C570',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqacusua',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqacusua@email.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqacussu',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqacussu@email.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C571',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqacussu',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqacussu@email.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqacusdr',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqacusdr1@email.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C572',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqacusdr',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqacusdr1@email.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'driverTestMR',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='driver@testmr.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C575',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'driverTestMR',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','driver@testmr.com')


--Roles:

UPDATE [dbo].[Role] SET [Name]='Administrator' WHERE [Id]='2DAD1417-00B6-D003-63B9-39CA528B5884'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[Role] ([Id] ,[Name]) VALUES (N'2DAD1417-00B6-D003-63B9-39CA528B5884',N'Administrator')
UPDATE [dbo].[Role] SET [Name]='Driver' WHERE [Id]='00000000-0000-0000-0001-000000000000'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[Role] ([Id] ,[Name]) VALUES (N'00000000-0000-0000-0001-000000000000',N'Driver')
UPDATE [dbo].[Role] SET [Name]='Supervisor' WHERE [Id]='00000000-0000-0000-0002-000000000000'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[Role] ([Id] ,[Name]) VALUES (N'00000000-0000-0000-0002-000000000000',N'Supervisor')
UPDATE [dbo].[Role] SET [Name]='Utility Administrator' WHERE [Id]='00000000-0000-0000-0003-000000000000'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[Role] ([Id] ,[Name]) VALUES (N'00000000-0000-0000-0003-000000000000',N'Utility Administrator')
UPDATE [dbo].[Role] SET [Name]='Picarro Support' WHERE [Id]='00000000-0000-0000-0004-000000000000'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[Role] ([Id] ,[Name]) VALUES (N'00000000-0000-0000-0004-000000000000',N'Picarro Support')


--User Role:

---Manual
DECLARE @userId uniqueidentifier

SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='Administrator'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='2DAD1417-00B6-D003-63B9-39CA528B5884' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'2DAD1417-00B6-D003-63B9-39CA528B5884',@userId)
SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='uadmin@sqatest.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)
SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='supervisor@sqatest.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0002-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0002-000000000000',@userId)
SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='driver@sqatest.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0001-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0001-000000000000',@userId)



--Automation (Add roles for sqapic* and sqacus* roles)

SELECT @userId =[Id] FROM [dbo].[User] WHERE [UserName]='sqapicad@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='2DAD1417-00B6-D003-63B9-39CA528B5884' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'2DAD1417-00B6-D003-63B9-39CA528B5884',@userId)
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='sqapicua@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='sqapicsu@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0002-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0002-000000000000',@userId)
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='sqapicsup@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0004-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0004-000000000000',@userId)
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='sqapicdr@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0001-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0001-000000000000',@userId)
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='sqacusua@email.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='sqacussu@email.com'  
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0002-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0002-000000000000',@userId)
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='sqacusdr1@email.com'  
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

SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'551cb7c0-005b-4e3e-bfae-d19da0ed7efe'
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'551cb7c0-005b-4e3e-bfae-d19da0ed7efe', @customerId, N'District Plat', N'', 2, 0, 5, 1)
UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'024249ae-374b-4f6f-bd87-e8fdcacb48e1'
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'024249ae-374b-4f6f-bd87-e8fdcacb48e1', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=N'e871c797-b62d-ef28-0ea7-39cae44e5c19', [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'b9e69b56-e43e-4f6c-afb1-24c01c1dd9dc'
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'b9e69b56-e43e-4f6c-afb1-24c01c1dd9dc', N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'District Plat', N'', 2, 0, 5, 0)
UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=N'e871c797-b62d-ef28-0ea7-39cae44e5c19', [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'3bb4b2ae-9591-4607-9cad-fd2eb377760a'
IF @@ROWCOUNT=0
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

SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='d08fc87f-f979-4131-92a9-3d82f37f4bba' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'd08fc87f-f979-4131-92a9-3d82f37f4bba', @customerId, N'Copper', N'#B5DBF4', 2, 0)

SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='44353e68-0694-4f05-85cb-84d753ea278c' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'44353e68-0694-4f05-85cb-84d753ea278c', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='f14735de-6c9b-4423-8533-f243a7fe4e90' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'f14735de-6c9b-4423-8533-f243a7fe4e90', @customerId, N'PE Plastic', N'', 2, 0)

SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='96caf1f5-d5c5-461d-9ce3-d210c20a1bb0' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'96caf1f5-d5c5-461d-9ce3-d210c20a1bb0', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='ad701312-c470-482a-be45-ef37770e2ce6' 
IF @@ROWCOUNT=0
	INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'ad701312-c470-482a-be45-ef37770e2ce6', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

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
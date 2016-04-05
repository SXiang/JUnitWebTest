-- Automation seed script

BEGIN TRANSACTION;

BEGIN TRY
 -- sample data 

DECLARE @userID uniqueidentifier
DECLARE @analyzerID uniqueidentifier
DECLARE @surveyorUnitID uniqueidentifier
DECLARE @referenceGasBottleId uniqueidentifier

DECLARE @customerId uniqueidentifier
DECLARE @locationID uniqueidentifier

 -- Customer:

--SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='sqaTest'
--SELECT * FROM [dbo].[Customer] WHERE [Id]='b1252204-04fb-4a67-82d4-3f4666fd855c'

-- Name is UNIQUE. Check for Name first. If NOT existing, INSERT after checking for ID.
IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='Picarro')
BEGIN
	UPDATE [dbo].[Customer] SET [Name]=N'Picarro',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='b1252204-04fb-4a67-82d4-3f4666fd855c' 
	IF @@ROWCOUNT=0
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'b1252204-04fb-4a67-82d4-3f4666fd855c',N'Picarro' ,N'Accept the agreement',1)
	ELSE
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (NEWID(), N'Picarro' ,N'Accept the agreement',1)		
END
ELSE
BEGIN
	UPDATE [dbo].[Customer] SET [Name]=N'Picarro',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='b1252204-04fb-4a67-82d4-3f4666fd855c' 
	IF @@ROWCOUNT=0  -- This indicates that Picarro customer is using OLD customer ID
	BEGIN
		-- Insert a new row with the correct customer ID. To avoid unique constraint use 'Picarro-NEW' as customer name
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'b1252204-04fb-4a67-82d4-3f4666fd855c',N'Picarro-NEW' ,N'Accept the agreement',1)
		-- Update the customer name on the old row to say '-DEPRECATED'		
		SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
		UPDATE [dbo].[Customer] SET [Name]=N'Picarro-DEPRECATED',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]=@customerId
		-- Re-update the Customer name to 'Picarro' on the new row
		UPDATE [dbo].[Customer] SET [Name]=N'Picarro',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='b1252204-04fb-4a67-82d4-3f4666fd855c' 
	END		
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

SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'Santa Clara',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='DE13ACD0-C158-ECAC-7F48-39D18113D701' 
IF @@ROWCOUNT=0
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D701', @customerId, N'Santa Clara','37.4020925705503','-121.984820397399')
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='sqacus'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'sqacusloc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='DE13ACD0-C158-ECAC-7F48-39D18113D702' 
IF @@ROWCOUNT=0
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D702', @customerId, N'sqacusloc','37.4020925705503','-121.984820397399')
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='sqaTest'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'sqaTestloc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='DE13ACD0-C158-ECAC-7F48-39D18113D703' 
IF @@ROWCOUNT=0
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113D703', @customerId, N'sqaTestloc','37.4020925705503','-121.984820397399')

--SurveyModeTypeConfiguration:

DECLARE @locationID uniqueidentifier
DECLARE @surveyModeTypeID uniqueidentifier 

/* NOTE: FovVersion is set to 1 for 3100 algorithm in web.config, code will use FovVersion1Ratio value.
 * Here are corresponding values for MinimumAmplitude, LeakRate
*/
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
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

	
--IsotopicIdentity:
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
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

	
--Surveyor:
-- Assign Surveyor to 'Santa Clara', 'sqacusloc', 'pge_SC' and 'sqaTestloc' locations.
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='00000014-fb61-2ef6-5dd1-39c8ac533d40')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
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
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'FB4F3579-843A-113E-001C-39D4011393C9', @locationID, N'SimAuto-Surveyor1')
END
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='FB5F3579-843A-113E-001C-39D4011393C9')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'FB5F3579-843A-113E-001C-39D4011393C9', @locationID, N'SimAuto-Surveyor2')
END
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='FB6F3579-843A-113E-001C-39D4011393C9')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'FB6F3579-843A-113E-001C-39D4011393C9', @locationID, N'SimAuto-Surveyor3')
END
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='DEBACFF7-E103-C14C-9DF8-39CD7B5F2A0A')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqacusloc'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'DEBACFF7-E103-C14C-9DF8-39CD7B5F2A0A', @locationID, N'White Dodge')
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
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'FB4F3579-843A-113E-001C-39D4011393C9', [SerialNumber]=N'SimAuto-Analyzer1', [SharedKey]=N'SimAuto-AnalyzerKey1' WHERE [Id]='58839947-9569-952D-16D6-39D4011442FA'
IF @@ROWCOUNT=0
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]='SimAuto-Analyzer1' AND [SharedKey]='SimAuto-AnalyzerKey1')
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'58839947-9569-952D-16D6-39D4011442FA', N'FB4F3579-843A-113E-001C-39D4011393C9', N'SimAuto-Analyzer1', N'SimAuto-AnalyzerKey1')
END
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-Analyzer2' AND [SharedKey]=N'SimAuto-AnalyzerKey2')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'FB5F3579-843A-113E-001C-39D4011393C90', [SerialNumber]=N'SimAuto-Analyzer2', [SharedKey]=N'SimAuto-AnalyzerKey2' WHERE [Id]='59839947-9569-952D-16D6-39D4011442FA'
IF @@ROWCOUNT=0
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]='SimAuto-Analyzer2' AND [SharedKey]='SimAuto-AnalyzerKey2')
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'59839947-9569-952D-16D6-39D4011442FA', N'FB5F3579-843A-113E-001C-39D4011393C9', N'SimAuto-Analyzer2', N'SimAuto-AnalyzerKey2')
END
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-Analyzer3' AND [SharedKey]=N'SimAuto-AnalyzerKey3')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'FB6F3579-843A-113E-001C-39D4011393C90', [SerialNumber]=N'SimAuto-Analyzer3', [SharedKey]=N'SimAuto-AnalyzerKey3' WHERE [Id]='5a839947-9569-952D-16D6-39D4011442FA'
IF @@ROWCOUNT=0
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]='SimAuto-Analyzer3' AND [SharedKey]='SimAuto-AnalyzerKey3')
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'5a839947-9569-952D-16D6-39D4011442FA', N'FB6F3579-843A-113E-001C-39D4011393C9', N'SimAuto-Analyzer3', N'SimAuto-AnalyzerKey3')
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
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='FB5F3579-843A-113E-001C-39D4011393C9', [BatchId]='Sim-RefGasBottle2', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='6FB982CC-6232-D7B9-F5AC-39D40114FA56'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'6FB982CC-6232-D7B9-F5AC-39D40114FA56', N'FB5F3579-843A-113E-001C-39D4011393C9', N'Sim-RefGasBottle2', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='FB6F3579-843A-113E-001C-39D4011393C9', [BatchId]='Sim-RefGasBottle3', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='6FC982CC-6232-D7B9-F5AC-39D40114FA56'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'6FC982CC-6232-D7B9-F5AC-39D40114FA56', N'FB6F3579-843A-113E-001C-39D4011393C9', N'Sim-RefGasBottle3', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))


--Users:
--Users for manual
-- Users assigned to Location='Santa Clara', Customer='Picarro'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Picarro' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'Seed_first',[LastName]=N'seed_last',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='AutomationAdmin'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C600',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'Seed_first',N'seed_last',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','AutomationAdmin')
-- Users assigned to Location='sqaTestloc', Customer='sqaTest'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'sqaTest' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqaTestloc'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqatestua',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='uadmin@sqatest.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C580',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqatestua',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','uadmin@sqatest.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqatestsu',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='supervisor@sqatest.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C581',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqatestsu',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','supervisor@sqatest.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqatestdr',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='driver@sqatest.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C582',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqatestdr',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','driver@sqatest.com')

--Users for selenium automation
-- Users assigned to Location='Santa Clara', Customer='Picarro'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Picarro' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
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
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqacusloc'
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


	
--User Role:

---Manual

SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='AutomationAdmin'
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
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='driver@testmr.com'  
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0001-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0001-000000000000',@userId)
	
	
	
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

 	IF @@TRANCOUNT > 0
        ROLLBACK TRANSACTION;

	RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState, @ErrorNumber,@ErrorLine)
    
END CATCH;

IF @@TRANCOUNT > 0
    COMMIT TRANSACTION;
GO
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
IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='PG&E')
BEGIN
	UPDATE [dbo].[Customer] SET [Name]=N'PG&E',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='E871C797-B62D-EF28-0EA7-39CAE44E5C19' 
	IF @@ROWCOUNT=0
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'E871C797-B62D-EF28-0EA7-39CAE44E5C19',N'PG&E' ,N'Accept the agreement',1)
	ELSE
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (NEWID(), N'PG&E' ,N'Accept the agreement',1)		
END

-- Assigned Licensed Features to Customers
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900B5-E0C6-4B79-8F94-3EA7F00D879F' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900B5-E0C6-4B79-8F94-3EA7F00D879F', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5F93BB71-4F95-4E26-ABD3-500B68838D7B' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5F93BB71-4F95-4E26-ABD3-500B68838D7B', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'B5D075A8-94EE-4D28-AFC4-69283D124A53' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'B5D075A8-94EE-4D28-AFC4-69283D124A53', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05D91EB6-90B2-440C-B887-69AC44A071ED' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05D91EB6-90B2-440C-B887-69AC44A071ED', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105E725-4913-477A-B58D-CBD9D83E7C70' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105E725-4913-477A-B58D-CBD9D83E7C70', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'F5E43F3D-9F69-430D-9013-E902A34A1D18' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'F5E43F3D-9F69-430D-9013-E902A34A1D18', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8CEF7673-80D2-407A-B833-F164C472CFDA' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8CEF7673-80D2-407A-B833-F164C472CFDA', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900B5-E0C6-4B79-8F94-3EA7F00D879F' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900B5-E0C6-4B79-8F94-3EA7F00D879F', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5F93BB71-4F95-4E26-ABD3-500B68838D7B' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5F93BB71-4F95-4E26-ABD3-500B68838D7B', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'B5D075A8-94EE-4D28-AFC4-69283D124A53' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'B5D075A8-94EE-4D28-AFC4-69283D124A53', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05D91EB6-90B2-440C-B887-69AC44A071ED' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05D91EB6-90B2-440C-B887-69AC44A071ED', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105E725-4913-477A-B58D-CBD9D83E7C70' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105E725-4913-477A-B58D-CBD9D83E7C70', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'F5E43F3D-9F69-430D-9013-E902A34A1D18' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'F5E43F3D-9F69-430D-9013-E902A34A1D18', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8CEF7673-80D2-407A-B833-F164C472CFDA' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8CEF7673-80D2-407A-B833-F164C472CFDA', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900B5-E0C6-4B79-8F94-3EA7F00D879F' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900B5-E0C6-4B79-8F94-3EA7F00D879F', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5F93BB71-4F95-4E26-ABD3-500B68838D7B' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5F93BB71-4F95-4E26-ABD3-500B68838D7B', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'B5D075A8-94EE-4D28-AFC4-69283D124A53' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'B5D075A8-94EE-4D28-AFC4-69283D124A53', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05D91EB6-90B2-440C-B887-69AC44A071ED' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05D91EB6-90B2-440C-B887-69AC44A071ED', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105E725-4913-477A-B58D-CBD9D83E7C70' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105E725-4913-477A-B58D-CBD9D83E7C70', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'F5E43F3D-9F69-430D-9013-E902A34A1D18' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'F5E43F3D-9F69-430D-9013-E902A34A1D18', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8CEF7673-80D2-407A-B833-F164C472CFDA' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8CEF7673-80D2-407A-B833-F164C472CFDA', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900B5-E0C6-4B79-8F94-3EA7F00D879F' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900B5-E0C6-4B79-8F94-3EA7F00D879F', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5F93BB71-4F95-4E26-ABD3-500B68838D7B' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5F93BB71-4F95-4E26-ABD3-500B68838D7B', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'B5D075A8-94EE-4D28-AFC4-69283D124A53' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'B5D075A8-94EE-4D28-AFC4-69283D124A53', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05D91EB6-90B2-440C-B887-69AC44A071ED' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05D91EB6-90B2-440C-B887-69AC44A071ED', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105E725-4913-477A-B58D-CBD9D83E7C70' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105E725-4913-477A-B58D-CBD9D83E7C70', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'F5E43F3D-9F69-430D-9013-E902A34A1D18' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'F5E43F3D-9F69-430D-9013-E902A34A1D18', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8CEF7673-80D2-407A-B833-F164C472CFDA' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8CEF7673-80D2-407A-B833-F164C472CFDA', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB', N'00000000-0000-0000-0000-000000000003')


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
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='PG&E'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'pge_SC',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='EE13ACD0-C158-ECAC-7F48-39D18113D501' 
IF @@ROWCOUNT=0
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'EE13ACD0-C158-ECAC-7F48-39D18113D501', @customerId, N'pge_SC','37.4020925705503','-121.984820397399')

-- Update lat/long on 'DEFAULT' location pushed by product seed script. (This is needed for Surveys pushed in automation DB to work correctly.)
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'Default',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='00000000-0000-0000-0001-000000000000' 
	
	
--SurveyModeTypeConfiguration:

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
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='2AA6AC90-B839-4787-F3B5-39D4B4DCEE95')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'2AA6AC90-B839-4787-F3B5-39D4B4DCEE95', @locationID, N'Software Car')
END
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='8F434C73-637B-CE9F-80B0-39D7F5B5A258')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'8F434C73-637B-CE9F-80B0-39D7F5B5A258', @locationID, N'Silver Nissan Rogue')
END
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='4A474FE4-B1AC-9A12-1F51-39CFD683AF02')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'4A474FE4-B1AC-9A12-1F51-39CFD683AF02', @locationID, N'Picarro Production #10')
END
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='F4A45601-E357-3CED-6EE6-39D6D20B16D9')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'F4A45601-E357-3CED-6EE6-39D6D20B16D9', @locationID, N'Light Blue Escape')
END
-- SimAuto-Surveyor1 (Picarro)
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='FB4F3579-843A-113E-001C-39D4011393C9')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'FB4F3579-843A-113E-001C-39D4011393C9', @locationID, N'SimAuto-Surveyor1')
END
-- SimAuto-Surveyor2 (Picarro)
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='FB5F3579-843A-113E-001C-39D4011393C9')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'FB5F3579-843A-113E-001C-39D4011393C9', @locationID, N'SimAuto-Surveyor2')
END
-- SimAuto-Surveyor3 (Picarro)
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='FB6F3579-843A-113E-001C-39D4011393C9')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'FB6F3579-843A-113E-001C-39D4011393C9', @locationID, N'SimAuto-Surveyor3')
END
-- SimAuto-Surveyor4 (sqacus)
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='84F35FAA-71CB-44AE-AB4A-15C343F1C84A')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqacusloc'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'84F35FAA-71CB-44AE-AB4A-15C343F1C84A', @locationID, N'SimAuto-Surveyor4')
END
-- SimAuto-Surveyor5 (PG&E)
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='B432D533-83DB-4EC2-8A39-38DF4125B609')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='pge_SC'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'B432D533-83DB-4EC2-8A39-38DF4125B609', @locationID, N'SimAuto-Surveyor5')
END
-- White Dodge
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='DEBACFF7-E103-C14C-9DF8-39CD7B5F2A0A')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqacusloc'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'DEBACFF7-E103-C14C-9DF8-39CD7B5F2A0A', @locationID, N'White Dodge')
END
-- BlackDodgeP3300
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='47FC54A4-26ED-7306-4D1D-39D76AFC27C4')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'47FC54A4-26ED-7306-4D1D-39D76AFC27C4', @locationID, N'BlackDodgeP3300')
END

-- Analyzer
-- NOTE: [SerialNumber] AND [SharedKey] are UNIQUE for Analyzer. 

-- RFADS2004 - BlackDodgeP3300
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'RFADS2004' AND [SharedKey]=N'rfads2004')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'47FC54A4-26ED-7306-4D1D-39D76AFC27C4', [SerialNumber]=N'RFADS2004', [SharedKey]=N'rfads2004' WHERE [Id]='26F7026D-788B-0413-0D89-39D76AFCAAFE'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'26F7026D-788B-0413-0D89-39D76AFCAAFE', N'47FC54A4-26ED-7306-4D1D-39D76AFC27C4', N'RFADS2004', N'rfads2004')
END
-- FDDS2037 - White Dodge
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'FDDS2037' AND [SharedKey]=N'fdds2037')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'2AA6AC90-B839-4787-F3B5-39D4B4DCEE95', [SerialNumber]=N'FDDS2037', [SharedKey]=N'fdds2037' WHERE [Id]='00000015-DB64-FDE7-7E67-39C8AC533D50'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'00000015-DB64-FDE7-7E67-39C8AC533D50', N'2AA6AC90-B839-4787-F3B5-39D4B4DCEE95', N'FDDS2037', N'fdds2037')
END
-- FDDS2038 - Software Car
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'FDDS2038' AND [SharedKey]=N'fdds2038')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'DEBACFF7-E103-C14C-9DF8-39CD7B5F2A0A', [SerialNumber]=N'FDDS2038', [SharedKey]=N'fdds2038' WHERE [Id]='34A34021-8814-8A01-9183-39D4B4DE03BE'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'34A34021-8814-8A01-9183-39D4B4DE03BE', N'DEBACFF7-E103-C14C-9DF8-39CD7B5F2A0A', N'FDDS2038', N'fdds2038')
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
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'FEDS2050' AND [SharedKey]=N'feds2050')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'2AA6AC90-B839-4787-F3B5-39D4B4DCEE95', [SerialNumber]=N'FEDS2050', [SharedKey]=N'feds2050' WHERE [Id]='1CF19683-DB1E-CBD8-37AD-39D6D20BE2B7'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id],[SurveyorUnitId],[SerialNumber],[SharedKey]) VALUES ('1CF19683-DB1E-CBD8-37AD-39D6D20BE2B7','2AA6AC90-B839-4787-F3B5-39D4B4DCEE95','FEDS2050','feds2050')
END
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'FEDS2055' AND [SharedKey]=N'feds2055')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'8F434C73-637B-CE9F-80B0-39D7F5B5A258', [SerialNumber]=N'FEDS2055', [SharedKey]=N'feds2055' WHERE [Id]='45E3BFBC-E42C-459A-91A2-39CFD6851706'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id],[SurveyorUnitId],[SerialNumber],[SharedKey]) VALUES ('45E3BFBC-E42C-459A-91A2-39CFD6851706','8F434C73-637B-CE9F-80B0-39D7F5B5A258','FEDS2055','feds2055')
END
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'FDDS2037-1' AND [SharedKey]=N'fdds2037-1')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'00000014-fb61-2ef6-5dd1-39c8ac533d41', [SerialNumber]=N'FDDS2037-1', [SharedKey]=N'fdds2037-1' WHERE [Id]='00000015-db64-fde7-7e67-39c8ac533d51'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'00000015-db64-fde7-7e67-39c8ac533d51', N'00000014-fb61-2ef6-5dd1-39c8ac533d41', N'FDDS2037-1', N'fdds2037-1')
END
-- SimAuto-Analyzer1 (Picarro)
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-Analyzer1' AND [SharedKey]=N'SimAuto-AnalyzerKey1')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'FB4F3579-843A-113E-001C-39D4011393C9', [SerialNumber]=N'SimAuto-Analyzer1', [SharedKey]=N'SimAuto-AnalyzerKey1' WHERE [Id]='58839947-9569-952D-16D6-39D4011442FA'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'58839947-9569-952D-16D6-39D4011442FA', N'FB4F3579-843A-113E-001C-39D4011393C9', N'SimAuto-Analyzer1', N'SimAuto-AnalyzerKey1')
END
-- SimAuto-Analyzer2 (Picarro)
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-Analyzer2' AND [SharedKey]=N'SimAuto-AnalyzerKey2')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'FB5F3579-843A-113E-001C-39D4011393C90', [SerialNumber]=N'SimAuto-Analyzer2', [SharedKey]=N'SimAuto-AnalyzerKey2' WHERE [Id]='59839947-9569-952D-16D6-39D4011442FA'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'59839947-9569-952D-16D6-39D4011442FA', N'FB5F3579-843A-113E-001C-39D4011393C9', N'SimAuto-Analyzer2', N'SimAuto-AnalyzerKey2')
END
-- SimAuto-Analyzer3 (Picarro)
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-Analyzer3' AND [SharedKey]=N'SimAuto-AnalyzerKey3')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'FB6F3579-843A-113E-001C-39D4011393C90', [SerialNumber]=N'SimAuto-Analyzer3', [SharedKey]=N'SimAuto-AnalyzerKey3' WHERE [Id]='5a839947-9569-952D-16D6-39D4011442FA'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'5a839947-9569-952D-16D6-39D4011442FA', N'FB6F3579-843A-113E-001C-39D4011393C9', N'SimAuto-Analyzer3', N'SimAuto-AnalyzerKey3')
END
-- SimAuto-Analyzer4 (sqacus)
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-Analyzer4' AND [SharedKey]=N'SimAuto-AnalyzerKey4')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'84F35FAA-71CB-44AE-AB4A-15C343F1C84A', [SerialNumber]=N'SimAuto-Analyzer4', [SharedKey]=N'SimAuto-AnalyzerKey4' WHERE [Id]='AB60E5EC-4B6D-4CAF-B0D2-7D5813AA572E'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'AB60E5EC-4B6D-4CAF-B0D2-7D5813AA572E', N'84F35FAA-71CB-44AE-AB4A-15C343F1C84A', N'SimAuto-Analyzer4', N'SimAuto-AnalyzerKey4')
END
-- SimAuto-Analyzer5 (PG&E)
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-Analyzer5' AND [SharedKey]=N'SimAuto-AnalyzerKey5')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'B432D533-83DB-4EC2-8A39-38DF4125B609', [SerialNumber]=N'SimAuto-Analyzer5', [SharedKey]=N'SimAuto-AnalyzerKey4' WHERE [Id]='56E4AA85-2E22-4233-A64A-00D75D284111'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'56E4AA85-2E22-4233-A64A-00D75D284111', N'B432D533-83DB-4EC2-8A39-38DF4125B609', N'SimAuto-Analyzer5', N'SimAuto-AnalyzerKey5')
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
-- RefGasBottle for 'SimAuto-Surveyor1'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='FB4F3579-843A-113E-001C-39D4011393C9', [BatchId]='Sim-RefGasBottle1', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='6FA982CC-6232-D7B9-F5AC-39D40114FA56'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'6FA982CC-6232-D7B9-F5AC-39D40114FA56', N'FB4F3579-843A-113E-001C-39D4011393C9', N'Sim-RefGasBottle1', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SimAuto-Surveyor2'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='FB5F3579-843A-113E-001C-39D4011393C9', [BatchId]='Sim-RefGasBottle2', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='6FB982CC-6232-D7B9-F5AC-39D40114FA56'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'6FB982CC-6232-D7B9-F5AC-39D40114FA56', N'FB5F3579-843A-113E-001C-39D4011393C9', N'Sim-RefGasBottle2', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SimAuto-Surveyor3'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='FB6F3579-843A-113E-001C-39D4011393C9', [BatchId]='Sim-RefGasBottle3', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='6FC982CC-6232-D7B9-F5AC-39D40114FA56'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'6FC982CC-6232-D7B9-F5AC-39D40114FA56', N'FB6F3579-843A-113E-001C-39D4011393C9', N'Sim-RefGasBottle3', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SimAuto-Surveyor4'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='84F35FAA-71CB-44AE-AB4A-15C343F1C84A', [BatchId]='Sim-RefGasBottle4', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-09-14 00:00:00.000' AS DateTime) WHERE [Id]='10A2997C-7D61-4D27-B97A-20453837300A'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'10A2997C-7D61-4D27-B97A-20453837300A', N'84F35FAA-71CB-44AE-AB4A-15C343F1C84A', N'Sim-RefGasBottle4', -32.7 ,CAST(N'2016-09-14 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SimAuto-Surveyor5'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='B432D533-83DB-4EC2-8A39-38DF4125B609', [BatchId]='Sim-RefGasBottle5', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-09-14 00:00:00.000' AS DateTime) WHERE [Id]='B65CFDB0-7964-4E1E-80CE-F0DA93A6140E'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'B65CFDB0-7964-4E1E-80CE-F0DA93A6140E', N'B432D533-83DB-4EC2-8A39-38DF4125B609', N'Sim-RefGasBottle5', -32.7 ,CAST(N'2016-09-14 00:00:00.000' AS DateTime))
-- RefGasBottle for 'White Dodge'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='DEBACFF7-E103-C14C-9DF8-39CD7B5F2A0A', [BatchId]='WhiteDodge-RefGasBottle1', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='AF197B16-C53F-FF50-8056-39CEC9EA1F23'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'AF197B16-C53F-FF50-8056-39CEC9EA1F23', N'DEBACFF7-E103-C14C-9DF8-39CD7B5F2A0A', N'WhiteDodge-RefGasBottle1', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'Software Car'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='2AA6AC90-B839-4787-F3B5-39D4B4DCEE95', [BatchId]='SoftwareCar-RefGasBottle1', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='00000015-DB64-FDE7-7E67-39C8AC544D68'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'00000015-DB64-FDE7-7E67-39C8AC544D68', N'2AA6AC90-B839-4787-F3B5-39D4B4DCEE95', N'SoftwareCar-RefGasBottle1', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'Picarro Production #10'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='4A474FE4-B1AC-9A12-1F51-39CFD683AF02', [BatchId]='109-56-12523', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-02-19 21:47:06.363' AS DateTime) WHERE [Id]='3636977B-4928-AFD1-CAA5-39D60DAD00FA'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'3636977B-4928-AFD1-CAA5-39D60DAD00FA', N'4A474FE4-B1AC-9A12-1F51-39CFD683AF02', N'109-56-12523', -32.7 ,CAST(N'2016-02-19 21:47:06.363' AS DateTime))
-- RefGasBottle for 'Light Blue Escape'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='F4A45601-E357-3CED-6EE6-39D6D20B16D9', [BatchId]='109-56-12523', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-03-29 00:56:43.150' AS DateTime) WHERE [Id]='FF3BD070-9E44-48F6-D992-39D6D20C418E'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'FF3BD070-9E44-48F6-D992-39D6D20C418E', N'F4A45601-E357-3CED-6EE6-39D6D20B16D9', N'109-56-12523', -32.7 ,CAST(N'2016-03-29 00:56:43.150' AS DateTime))
-- RefGasBottle for 'BlackDodgeP3300'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='47FC54A4-26ED-7306-4D1D-39D76AFC27C4', [BatchId]='RFADS2004', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-04-27 17:42:17.007' AS DateTime), [EthaneToMethaneRatio]=0.03 WHERE [Id]='34E929E4-CEF1-F8A6-C3A6-39D76AFD4CAC'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date], [EthaneToMethaneRatio]) VALUES (N'34E929E4-CEF1-F8A6-C3A6-39D76AFD4CAC', N'47FC54A4-26ED-7306-4D1D-39D76AFC27C4', N'RFADS2004', -32.7 ,CAST(N'2016-04-27 17:42:17.007' AS DateTime), 0.03)

-- Add AnalyzerHardwareCapabilityType
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'00000015-DB64-FDE7-7E67-39C8AC533D49'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'00000015-DB64-FDE7-7E67-39C8AC533D49', 1)
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'00000015-DB64-FDE7-7E67-39C8AC533D50'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'00000015-DB64-FDE7-7E67-39C8AC533D50', 1)
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'00000015-DB64-FDE7-7E67-39C8AC533D51'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'00000015-DB64-FDE7-7E67-39C8AC533D51', 1)
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'00000015-DB64-FDE7-7E67-39C8AC533D52'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'00000015-DB64-FDE7-7E67-39C8AC533D52', 1)
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'45E3BFBC-E42C-459A-91A2-39CFD6851706'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'45E3BFBC-E42C-459A-91A2-39CFD6851706', 1)
-- HardwareCapabilityType for SimAuto-Analyzer1
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'58839947-9569-952D-16D6-39D4011442FA'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'58839947-9569-952D-16D6-39D4011442FA', 1)
-- HardwareCapabilityType for SimAuto-Analyzer2
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'59839947-9569-952D-16D6-39D4011442FA'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'59839947-9569-952D-16D6-39D4011442FA', 1)
-- HardwareCapabilityType for SimAuto-Analyzer3
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'5A839947-9569-952D-16D6-39D4011442FA'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'5A839947-9569-952D-16D6-39D4011442FA', 1)
-- HardwareCapabilityType for SimAuto-Analyzer4
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'AB60E5EC-4B6D-4CAF-B0D2-7D5813AA572E'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'AB60E5EC-4B6D-4CAF-B0D2-7D5813AA572E', 1)
-- HardwareCapabilityType for SimAuto-Analyzer5
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'56E4AA85-2E22-4233-A64A-00D75D284111'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'56E4AA85-2E22-4233-A64A-00D75D284111', 1)
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'34A34021-8814-8A01-9183-39D4B4DE03BE'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'34A34021-8814-8A01-9183-39D4B4DE03BE', 1)
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=2 WHERE [AnalyzerId]=N'1CF19683-DB1E-CBD8-37AD-39D6D20BE2B7'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'1CF19683-DB1E-CBD8-37AD-39D6D20BE2B7', 2)
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=0 WHERE [AnalyzerId]=N'26F7026D-788B-0413-0D89-39D76AFCAAFE'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'26F7026D-788B-0413-0D89-39D76AFCAAFE', 0)


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
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqatestua',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='uadmin@sqatest.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C580',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqatestua',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','uadmin@sqatest.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqatestsu',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='supervisor@sqatest.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C581',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqatestsu',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','supervisor@sqatest.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqatestdr',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='driver@sqatest.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C582',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqatestdr',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','driver@sqatest.com')
-- Users assigned to Location='pge_SC', Customer='PG&E'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'PG&E' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='pge_SC'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'SQAPGE',[LastName]=N'Dr1',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqapgedr1@email.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'FFB92186-375D-5142-920D-39D8B54800BB',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'SQAPGE',N'Dr1',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapgedr1@email.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'SQAPGE',[LastName]=N'Su',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqapgesu@email.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'2CB14A5F-B307-E416-A9CD-39D55A1C994D',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'SQAPGE',N'Su',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapgesu@email.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'SQAPGE',[LastName]=N'Ua',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqapgeua@email.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'FC6EFA59-8AE4-CCC6-0B8C-39D55A1BE75E',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'SQAPGE',N'Ua',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapgeua@email.com')

	
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
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqapicsu1',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AJY3iHNvOJ8M76JVoq3Rz1rIG6sNDpdkyPY2ujxkoHlCj0cz+MQdGxm70zbb/zXN9Q==',[SecurityStamp]=N'5987b78a-6a4c-4172-ad70-213f5fd05922',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0', [CultureId]='en-US' WHERE [UserName]='sqapicsu1@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id],[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName],[CultureId]) VALUES (N'4a48e909-6264-5b94-c448-39d71e69823b', @customerId, NULL, 1, 1, N'00000000-0000-0000-0001-000000000000', @locationID,N'sqapicsu1',N'lastName', NULL, NULL, 0, N'AJY3iHNvOJ8M76JVoq3Rz1rIG6sNDpdkyPY2ujxkoHlCj0cz+MQdGxm70zbb/zXN9Q==', N'5987b78a-6a4c-4172-ad70-213f5fd05922', NULL, 0, 0, NULL, 0, 0, N'sqapicsu1@picarro.com', N'en-US')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'picdr',[LastName]=N'lName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'APzfKg2J7WbjrSj9t2MBHjNnAtGscp1cmFXYZpXjERYmvfcEGfoV1AMuWTGiswmGtQ==',[SecurityStamp]=N'7aa6b035-f94b-4e73-9b8c-008663e71e45',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0', [CultureId]='en-US' WHERE [UserName]='picdr@picarro.com'
IF @@ROWCOUNT=0
	INSERT [dbo].[User] ([Id], [CustomerId], [OpQualExpiration], [Active], [EulaAccepted], [TimeZoneId], [LocationId], [FirstName], [LastName], [CellPhoneNumber], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [CultureId]) VALUES (N'c67baf53-693b-78f8-0498-39d76b0009aa', @customerId, NULL, 1, 1, N'00000000-0000-0000-0001-000000000000', @locationID, 'picdr', 'lname', NULL, NULL, 0, N'APzfKg2J7WbjrSj9t2MBHjNnAtGscp1cmFXYZpXjERYmvfcEGfoV1AMuWTGiswmGtQ==', N'7aa6b035-f94b-4e73-9b8c-008663e71e45', NULL, 0, 0, NULL, 0, 0, N'picdr@picarro.com', N'en-US')
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
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


	
-- User for assessment surveys.
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Picarro' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'driver1',[LastName]=N'Picarro',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='driver1@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'B195D287-52BA-FFA4-9405-39D60DAE335C',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'driver1',N'Picarro',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','driver1@picarro.com')

	
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
	
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='sqapgedr1@email.com'  
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0001-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0001-000000000000',@userId)	
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='sqapgesu@email.com'  
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0002-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0002-000000000000',@userId)	
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='sqapgeua@email.com'  
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)	

SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='driver1@picarro.com'  
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0001-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0001-000000000000',@userId)	
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='picdr@picarro.com'  
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0001-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0001-000000000000',@userId)	
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='sqapicsu1@picarro.com'  
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0004-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0004-000000000000',@userId)	
	
	
	
	
--Sample BCP import command, set the appropriate Database name, file path, SQL login user name and password
--bcp "[SurveyorXXXX].[dbo].[Boundary]" in "...\Boundary_Sample_Data.dat" -t\t -w -U awssa -P j!RuL1Gd7A -S 20.20.64.100
--for sqa:--bcp "[[SurveyorSQA964]].[dbo].[Boundary]" in "C:\Repo\surveyor\Src\Web\Picarro.Surveyor.Db\Seed\Boundary_Sample_Data.dat" -t\t -w -U awssa -P j!RuL1Gd7A -S 20.20.130.238
--bcp "[SurveyorXXXX].[dbo].[Asset]" in "...\Asset_Sample_Data.dat" -t\t -w -U awssa -P j!RuL1Gd7A -S 20.20.64.100
--for sqa:--bcp "[[SurveyorSQA964]].[dbo].[Asset]" in "C:\Repo\surveyor\Src\Web\Picarro.Surveyor.Db\Seed\Asset_Sample_Data.dat" -t\t -w -U awssa -P j!RuL1Gd7A -S 20.20.130.238
---------------------------------------------------------------------------------------------------
--pge location

-- IF NOT EXISTS (SELECT * FROM [dbo].[Location] WHERE [CustomerId]='e871c797-b62d-ef28-0ea7-39cae44e5c19' AND [Description]='pge_SC')
	-- INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'EE13ACD0-C158-ECAC-7F48-39D18113D501', N'e871c797-b62d-ef28-0ea7-39cae44e5c19', N'pge_SC','37.4020925705503','-121.984820397399')


-- --PG&E Users for GIS

-- IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='driver@pge.com')
	-- INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'EE734DDF-363E-49FC-8DBC-39C8C221C550',N'e871c797-b62d-ef28-0ea7-39cae44e5c19', NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',N'EE13ACD0-C158-ECAC-7F48-39D18113D501',N'pge_driver',N'last',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','driver@pge.com')
-- IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='uadmin@pge.com')
	-- INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'EE734DDF-363E-49FC-8DBC-39C8C221C551',N'e871c797-b62d-ef28-0ea7-39cae44e5c19', NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',N'EE13ACD0-C158-ECAC-7F48-39D18113D501',N'pge_admin',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','uadmin@pge.com')
-- IF NOT EXISTS (SELECT * FROM [dbo].[User] WHERE [UserName]='supervisor@pge.com')
	-- INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'EE734DDF-363E-49FC-8DBC-39C8C221C552',N'e871c797-b62d-ef28-0ea7-39cae44e5c19', NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',N'EE13ACD0-C158-ECAC-7F48-39D18113D501',N'pgesu',N'lastName',NULL,NULL,N'0',N'APz3KJuhDTxu0zR+m4imjCDxI7hcTqij1JtFVubHNeJU269uFgTQxFWxLqiFmZ6BJg==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','supervisor@pge.com')


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
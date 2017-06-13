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
END
IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='sqaTest')
BEGIN
	UPDATE [dbo].[Customer] SET [Name]=N'sqaTest',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='00000000-0000-0000-0000-000000000003' 
	IF @@ROWCOUNT=0
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'00000000-0000-0000-0000-000000000003',N'sqaTest' ,N'Accept the agreement',1)
END
IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='PG&E')
BEGIN
	UPDATE [dbo].[Customer] SET [Name]=N'PG&E',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='E871C797-B62D-EF28-0EA7-39CAE44E5C19' 
	IF @@ROWCOUNT=0
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'E871C797-B62D-EF28-0EA7-39CAE44E5C19',N'PG&E' ,N'Accept the agreement',1)
END
IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='CustomerWithNoLicense')
BEGIN
	UPDATE [dbo].[Customer] SET [Name]=N'CustomerWithNoLicense',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='5D073EF1-40E1-9BA0-E7BC-39DA8027337E' 
	IF @@ROWCOUNT=0
		INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'5D073EF1-40E1-9BA0-E7BC-39DA8027337E',N'CustomerWithNoLicense' ,N'Accept the agreement',1)
END		

-- Assigned Licensed Features to Customers
---- PG&E
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'E871C797-B62D-EF28-0EA7-39CAE44E5C19')
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
---- Picarro
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
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
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0F726186-19EE-4703-9C77-6EC6155AB255' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0F726186-19EE-4703-9C77-6EC6155AB255', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
---- sqaCus
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'00000000-0000-0000-0000-000000000002')
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
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0F726186-19EE-4703-9C77-6EC6155AB255' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0F726186-19EE-4703-9C77-6EC6155AB255', N'00000000-0000-0000-0000-000000000002')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900B5-E0C6-4B79-8F94-3EA7F00D879F' AND [CustomerId]=N'00000000-0000-0000-0000-000000000002')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900B5-E0C6-4B79-8F94-3EA7F00D879F', N'00000000-0000-0000-0000-000000000002')
---- sqaTest
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'00000000-0000-0000-0000-000000000003')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'00000000-0000-0000-0000-000000000003')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'00000000-0000-0000-0000-000000000003')
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
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='CustomerWithNoLicense'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'NolicenseLoc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='D99CE05E-9D85-D308-0F5D-39DA8027E9DD' 
IF @@ROWCOUNT=0
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'D99CE05E-9D85-D308-0F5D-39DA8027E9DD', @customerId, N'NolicenseLoc','37.4020925705503','-121.984820397399')
-- Location - (Default FEQ Location)
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'Default FEQ Location',[Latitude]='37.3966870748048',[Longitude]='-121.984376907349' WHERE [Id]='50c98274-034a-672a-38b1-39dec2e650ee' 
IF @@ROWCOUNT=0
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'50c98274-034a-672a-38b1-39dec2e650ee', @customerId, N'Default FEQ Location','37.3966870748048','-121.984376907349')


-- Update lat/long on 'DEFAULT' location pushed by product seed script. (This is needed for Surveys pushed in automation DB to work correctly.)
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'Default',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='00000000-0000-0000-0001-000000000000' 
	
	
--SurveyModeTypeConfiguration:

DECLARE @surveyModeTypeID uniqueidentifier 

/* NOTE: FovVersion is set to 1 for 3100 algorithm in web.config, code will use FovVersion1Ratio value.
 * Here are corresponding values for MinimumAmplitude
*/
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Rapid Response'

UPDATE [dbo].[SurveyModeTypeConfiguration] SET [MinimumAmplitude]=5 WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 5)
SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Manual'
UPDATE [dbo].[SurveyModeTypeConfiguration] SET [MinimumAmplitude]=0.035 WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 0.035)
SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Operator'
UPDATE [dbo].[SurveyModeTypeConfiguration] SET [MinimumAmplitude]=5 WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 5)
SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='Standard'
UPDATE [dbo].[SurveyModeTypeConfiguration] SET [MinimumAmplitude]=0.035 WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 0.035)
SELECT @surveyModeTypeID=[Id] FROM [dbo].[SurveyModeType] WHERE Description='EQ'
UPDATE [dbo].[SurveyModeTypeConfiguration] SET [MinimumAmplitude]=5 WHERE [LocationId] = @locationID AND [SurveyModeTypeId] = @surveyModeTypeID AND [FromDate]=CAST(N'1980-01-01 00:00:00.000' AS DateTime)
IF @@ROWCOUNT=0
	INSERT [dbo].[SurveyModeTypeConfiguration] ([LocationId],[SurveyModeTypeId], [FromDate], [MinimumAmplitude]) VALUES (@locationID, @surveyModeTypeID, CAST(N'1980-01-01 00:00:00.000' AS DateTime), 5)

	
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
-- sqacus SurveyorUnit - SQACusSrvUnit-1
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='EDBACFF7-E103-C14C-9DF8-39CD7B5F2A1A')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqacusloc'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'EDBACFF7-E103-C14C-9DF8-39CD7B5F2A1A', @locationID, N'SQACusSrvUnit-1')
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
-- iGPS car
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='EDA5A3A0-7B86-A343-69F6-39D8A7186DC1')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'EDA5A3A0-7B86-A343-69F6-39D8A7186DC1', @locationID, N'iGPS car')
END
-- Nolicesnse
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='1E7426FF-6C7C-EFF3-303E-39DA802825EB')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='NolicenseLoc'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'1E7426FF-6C7C-EFF3-303E-39DA802825EB', @locationID, N'NoLicSur')
END
-- EQ Surveyor Unit
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='BFEEA4F3-093D-B963-4AE6-39D7B44D9899')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'BFEEA4F3-093D-B963-4AE6-39D7B44D9899', @locationID, N'Nissan Rogue - Picarro')
END
-- SurveyorUnit - (Black Rhino FEQ)
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='58c51edd-51a4-1266-1159-39dbd3f3366c')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'58c51edd-51a4-1266-1159-39dbd3f3366c', @locationID, N'Black Rhino FEQ')
END
-- SurveyorUnit - (White Rhino 2003 Picarro)
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='36d72df6-2823-4542-8298-39de6c296d8e')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default FEQ Location'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'36d72df6-2823-4542-8298-39de6c296d8e', @locationID, N'White Rhino 2003 Picarro')
END
-- SurveyorUnit - (SimAuto-EQSrvUnit1)
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='52D4D54B-1179-4712-AFB4-FA70DDEBA96A')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'52D4D54B-1179-4712-AFB4-FA70DDEBA96A', @locationID, N'SimAuto-EQSrvUnit1')
END
-- SurveyorUnit - (SimAuto-EQSrvUnitSqaCus-1)
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='400F642D-F2F3-4A0A-8F11-601F9FDCC217')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqacusloc'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'400F642D-F2F3-4A0A-8F11-601F9FDCC217', @locationID, N'SimAuto-EQSrvUnitSqaCus-1')
END
-- SurveyorUnit - (SimAuto-EQSrvUnit-Eth1)
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='1C374486-FF8A-4117-BCBA-5E0C2B9D3DF7')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'1C374486-FF8A-4117-BCBA-5E0C2B9D3DF7', @locationID, N'SimAuto-EQSrvUnit-Eth1')
END
-- SurveyorUnit - (SimAuto-EQSrvUnitSqaCus-Eth1)
IF NOT EXISTS (SELECT * FROM [dbo].[SurveyorUnit] WHERE [Id]='C5354534-336F-44D1-9FB9-6270DFE2EAD7')
BEGIN
	SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqacusloc'
	INSERT [dbo].[SurveyorUnit] ([Id], [LocationId], [Description]) VALUES (N'C5354534-336F-44D1-9FB9-6270DFE2EAD7', @locationID, N'SimAuto-EQSrvUnitSqaCus-Eth1')
END


-- Analyzer
-- NOTE: [SerialNumber] AND [SharedKey] are UNIQUE for Analyzer. 

-- sqacus Analyzer - sqacus2016
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SQACUS2016-1' AND [SharedKey]=N'sqacus2016-1')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'EDBACFF7-E103-C14C-9DF8-39CD7B5F2A1A', [SerialNumber]=N'SQACUS2016-1', [SharedKey]=N'sqacus2016-1' WHERE [Id]='43A34021-8814-8A01-9183-39D4B4DE03EB'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'43A34021-8814-8A01-9183-39D4B4DE03EB', N'EDBACFF7-E103-C14C-9DF8-39CD7B5F2A1A', N'SQACUS2016-1', N'sqacus2016-1')
END

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
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'RFADS2004-PICARRO' AND [SharedKey]=N'rfads2004-picarro')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'EDA5A3A0-7B86-A343-69F6-39D8A7186DC1', [SerialNumber]=N'RFADS2004-PICARRO', [SharedKey]=N'rfads2004-picarro' WHERE [Id]='D2CCC285-2BD3-5D22-667E-39D823D4D255'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'D2CCC285-2BD3-5D22-667E-39D823D4D255', N'EDA5A3A0-7B86-A343-69F6-39D8A7186DC1', N'RFADS2004-PICARRO', N'rfads2004-picarro')
END
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'1E7426FF-6C7C-EFF3-303E-39DA802825EB', [SerialNumber]=N'NoLicAna', [SharedKey]=N'NoLicAnaKey' WHERE [Id]='AFBE9B5B-5222-C1C0-7D96-39DA80286015'
IF @@ROWCOUNT=0
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]='NoLicAna' AND [SharedKey]='NoLicAnaKey')
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'AFBE9B5B-5222-C1C0-7D96-39DA80286015', N'1E7426FF-6C7C-EFF3-303E-39DA802825EB', N'NoLicAna', N'NoLicAnaKey')
END
-- EQ Analyzer
BEGIN
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'BFEEA4F3-093D-B963-4AE6-39D7B44D9899', [SerialNumber]=N'FEDS2055-PICARRO', [SharedKey]=N'feds2055-picarro' WHERE [Id]='F5970731-CE37-F7F3-DB5F-39D7E2D02053'
IF @@ROWCOUNT=0
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]='FEDS2055-PICARRO' AND [SharedKey]='feds2055-picarro')
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'F5970731-CE37-F7F3-DB5F-39D7E2D02053', N'BFEEA4F3-093D-B963-4AE6-39D7B44D9899', N'FEDS2055-PICARRO', N'feds2055-picarro')
END
-- Analyzer - (RFADS2004-FEQ)
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'RFADS2004-FEQ' AND [SharedKey]=N'rfads2004-feq')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'58c51edd-51a4-1266-1159-39dbd3f3366c', [SerialNumber]=N'RFADS2004-FEQ', [SharedKey]=N'rfads2004-feq' WHERE [Id]='88cfc43e-9005-03e1-1d7a-39dbd3f483de'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'88cfc43e-9005-03e1-1d7a-39dbd3f483de', N'58c51edd-51a4-1266-1159-39dbd3f3366c', N'RFADS2004-FEQ', N'rfads2004-feq')
END
-- Analyzer - (RFADS2003)
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'RFADS2003' AND [SharedKey]=N'rfads2003')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'36d72df6-2823-4542-8298-39de6c296d8e', [SerialNumber]=N'RFADS2003', [SharedKey]=N'rfads2003' WHERE [Id]='6479a0f7-37c1-b40c-c53b-39de6c1e9d69'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'6479a0f7-37c1-b40c-c53b-39de6c1e9d69', N'36d72df6-2823-4542-8298-39de6c296d8e', N'RFADS2003', N'rfads2003')
END
-- Analyzer - (SimAuto-EQAnalyzer1)
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-EQAnalyzer1' AND [SharedKey]=N'SimAuto-EQAnalyzer1Key')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'52D4D54B-1179-4712-AFB4-FA70DDEBA96A', [SerialNumber]=N'SimAuto-EQAnalyzer1', [SharedKey]=N'SimAuto-EQAnalyzer1Key' WHERE [Id]='B89BF795-F1FA-45E6-A6DC-F674A84F5E47'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'B89BF795-F1FA-45E6-A6DC-F674A84F5E47', N'52D4D54B-1179-4712-AFB4-FA70DDEBA96A', N'SimAuto-EQAnalyzer1', N'SimAuto-EQAnalyzer1Key')
END
-- Analyzer - (SimAuto-EQAnalyzer-SqaCus-1) 
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-EQAnalyzer-SqaCus-1' AND [SharedKey]=N'SimAuto-EQAnalyzer-SqaCus-1Key')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'400F642D-F2F3-4A0A-8F11-601F9FDCC217', [SerialNumber]=N'SimAuto-EQAnalyzer-SqaCus-1', [SharedKey]=N'SimAuto-EQAnalyzer-SqaCus-1Key' WHERE [Id]='3029DBB8-F824-491D-8D90-6CBD92CFDF8F'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'3029DBB8-F824-491D-8D90-6CBD92CFDF8F', N'400F642D-F2F3-4A0A-8F11-601F9FDCC217', N'SimAuto-EQAnalyzer-SqaCus-1', N'SimAuto-EQAnalyzer-SqaCus-1Key')
END
-- Analyzer - (SimAuto-EQAnalyzer-Eth1)
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-EQAnalyzer-Eth1' AND [SharedKey]=N'SimAuto-EQAnalyzer-Eth1Key')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'1C374486-FF8A-4117-BCBA-5E0C2B9D3DF7', [SerialNumber]=N'SimAuto-EQAnalyzer-Eth1', [SharedKey]=N'SimAuto-EQAnalyzer-Eth1Key' WHERE [Id]='AF4006D5-C01C-4D28-8099-530E350D1439'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'AF4006D5-C01C-4D28-8099-530E350D1439', N'1C374486-FF8A-4117-BCBA-5E0C2B9D3DF7', N'SimAuto-EQAnalyzer-Eth1', N'SimAuto-EQAnalyzer-Eth1Key')
END
-- Analyzer - (SimAuto-EQAnalyzer-SqaCus-Eth1)
IF NOT EXISTS (SELECT * FROM [dbo].[Analyzer] WHERE [SerialNumber]=N'SimAuto-EQAnalyzer-SqaCus-Eth1' AND [SharedKey]=N'SimAuto-EQAnalyzer-SqaCus-Eth1Key')
BEGIN 
UPDATE [dbo].[Analyzer] SET [SurveyorUnitId]=N'C5354534-336F-44D1-9FB9-6270DFE2EAD7', [SerialNumber]=N'SimAuto-EQAnalyzer-SqaCus-Eth1', [SharedKey]=N'SimAuto-EQAnalyzer-SqaCus-Eth1Key' WHERE [Id]='3BB5D6C4-17D0-4088-A616-11CE03DD3274'
IF @@ROWCOUNT=0
	INSERT [dbo].[Analyzer] ([Id], [SurveyorUnitId], [SerialNumber], [SharedKey]) VALUES (N'3BB5D6C4-17D0-4088-A616-11CE03DD3274', N'C5354534-336F-44D1-9FB9-6270DFE2EAD7', N'SimAuto-EQAnalyzer-SqaCus-Eth1', N'SimAuto-EQAnalyzer-SqaCus-Eth1Key')
END


--ReferenceGasBottle: (UPDATE if EXISTS, else INSERT)
-- RefGasBottle for 'SoftwareCar_2037_picarro' - 'Picarro'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='00000014-fb61-2ef6-5dd1-39c8ac533d40', [BatchId]='109-56-12100', [IsotopicValue]=-32.7, [Date]=CAST(N'2014-01-01 00:00:00.000' AS DateTime) WHERE [Id]='00000015-db64-fde7-7e67-39c8ac544d60'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'00000015-db64-fde7-7e67-39c8ac544d60', N'00000014-fb61-2ef6-5dd1-39c8ac533d40', N'109-56-12100', -32.7, CAST(N'2014-01-01 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SoftwareCar_2037_Testcust' - 'sqaTest'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='00000014-fb61-2ef6-5dd1-39c8ac533d42', [BatchId]='109-56-12100', [IsotopicValue]=-32.7, [Date]=CAST(N'2014-01-01 00:00:00.000' AS DateTime) WHERE [Id]='00000015-db64-fde7-7e67-39c8ac544d62'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'00000015-db64-fde7-7e67-39c8ac544d62', N'00000014-fb61-2ef6-5dd1-39c8ac533d42', N'109-56-12100', -32.7, CAST(N'2014-01-01 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SoftwareCar_2037_cust' - 'sqacus'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='00000014-fb61-2ef6-5dd1-39c8ac533d41', [BatchId]='109-56-12100', [IsotopicValue]=-32.7, [Date]=CAST(N'2014-01-01 00:00:00.000' AS DateTime) WHERE [Id]='00000015-db64-fde7-7e67-39c8ac544d61'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'00000015-db64-fde7-7e67-39c8ac544d61', N'00000014-fb61-2ef6-5dd1-39c8ac533d41', N'109-56-12100', -32.7, CAST(N'2014-01-01 00:00:00.000' AS DateTime))
-- RefGasBottle for 'PGE-FEDS2015' - 'PGE'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='C24E9253-F195-9AEC-DE1E-39D0FBB5D8A1', [BatchId]='109-56-12100', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-09-29 00:00:00.000' AS DateTime) WHERE [Id]='00000015-DB64-FDE7-7E67-39C8AC544D63'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'00000015-DB64-FDE7-7E67-39C8AC544D63', N'C24E9253-F195-9AEC-DE1E-39D0FBB5D8A1', N'109-56-12100', -32.7 ,CAST(N'2015-09-29 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SimAuto-Surveyor1' - 'Picarro'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='FB4F3579-843A-113E-001C-39D4011393C9', [BatchId]='Sim-RefGasBottle1', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='6FA982CC-6232-D7B9-F5AC-39D40114FA56'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'6FA982CC-6232-D7B9-F5AC-39D40114FA56', N'FB4F3579-843A-113E-001C-39D4011393C9', N'Sim-RefGasBottle1', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SimAuto-Surveyor2' - 'Picarro'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='FB5F3579-843A-113E-001C-39D4011393C9', [BatchId]='Sim-RefGasBottle2', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='6FB982CC-6232-D7B9-F5AC-39D40114FA56'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'6FB982CC-6232-D7B9-F5AC-39D40114FA56', N'FB5F3579-843A-113E-001C-39D4011393C9', N'Sim-RefGasBottle2', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SimAuto-Surveyor3' - 'Picarro'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='FB6F3579-843A-113E-001C-39D4011393C9', [BatchId]='Sim-RefGasBottle3', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='6FC982CC-6232-D7B9-F5AC-39D40114FA56'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'6FC982CC-6232-D7B9-F5AC-39D40114FA56', N'FB6F3579-843A-113E-001C-39D4011393C9', N'Sim-RefGasBottle3', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SimAuto-Surveyor4' - 'sqacus'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='84F35FAA-71CB-44AE-AB4A-15C343F1C84A', [BatchId]='Sim-RefGasBottle4', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-09-14 00:00:00.000' AS DateTime) WHERE [Id]='10A2997C-7D61-4D27-B97A-20453837300A'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'10A2997C-7D61-4D27-B97A-20453837300A', N'84F35FAA-71CB-44AE-AB4A-15C343F1C84A', N'Sim-RefGasBottle4', -32.7 ,CAST(N'2016-09-14 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SimAuto-Surveyor5' - 'PG&E'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='B432D533-83DB-4EC2-8A39-38DF4125B609', [BatchId]='Sim-RefGasBottle5', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-09-14 00:00:00.000' AS DateTime) WHERE [Id]='B65CFDB0-7964-4E1E-80CE-F0DA93A6140E'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'B65CFDB0-7964-4E1E-80CE-F0DA93A6140E', N'B432D533-83DB-4EC2-8A39-38DF4125B609', N'Sim-RefGasBottle5', -32.7 ,CAST(N'2016-09-14 00:00:00.000' AS DateTime))
-- RefGasBottle for 'SQACusSrvUnit-1' - 'sqacus'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='EDBACFF7-E103-C14C-9DF8-39CD7B5F2A1A', [BatchId]='SQACusSrvUnit1-RefGasBottle1', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='FA197B16-C53F-FF50-8056-39CEC9EA1F32'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'FA197B16-C53F-FF50-8056-39CEC9EA1F32', N'EDBACFF7-E103-C14C-9DF8-39CD7B5F2A1A', N'SQACusSrvUnit1-RefGasBottle1', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'White Dodge' - 'sqacus'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='DEBACFF7-E103-C14C-9DF8-39CD7B5F2A0A', [BatchId]='WhiteDodge-RefGasBottle1', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='AF197B16-C53F-FF50-8056-39CEC9EA1F23'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'AF197B16-C53F-FF50-8056-39CEC9EA1F23', N'DEBACFF7-E103-C14C-9DF8-39CD7B5F2A0A', N'WhiteDodge-RefGasBottle1', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'Software Car' - 'Picarro'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='2AA6AC90-B839-4787-F3B5-39D4B4DCEE95', [BatchId]='SoftwareCar-RefGasBottle1', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='00000015-DB64-FDE7-7E67-39C8AC544D68'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'00000015-DB64-FDE7-7E67-39C8AC544D68', N'2AA6AC90-B839-4787-F3B5-39D4B4DCEE95', N'SoftwareCar-RefGasBottle1', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'Picarro Production #10' - 'Picarro'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='4A474FE4-B1AC-9A12-1F51-39CFD683AF02', [BatchId]='109-56-12523', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-02-19 21:47:06.363' AS DateTime) WHERE [Id]='3636977B-4928-AFD1-CAA5-39D60DAD00FA'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'3636977B-4928-AFD1-CAA5-39D60DAD00FA', N'4A474FE4-B1AC-9A12-1F51-39CFD683AF02', N'109-56-12523', -32.7 ,CAST(N'2016-02-19 21:47:06.363' AS DateTime))
-- RefGasBottle for 'Light Blue Escape' - 'Picarro'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='F4A45601-E357-3CED-6EE6-39D6D20B16D9', [BatchId]='109-56-12523', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-03-29 00:56:43.150' AS DateTime) WHERE [Id]='FF3BD070-9E44-48F6-D992-39D6D20C418E'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'FF3BD070-9E44-48F6-D992-39D6D20C418E', N'F4A45601-E357-3CED-6EE6-39D6D20B16D9', N'109-56-12523', -32.7 ,CAST(N'2016-03-29 00:56:43.150' AS DateTime))
-- RefGasBottle for 'BlackDodgeP3300' - 'Picarro'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='47FC54A4-26ED-7306-4D1D-39D76AFC27C4', [BatchId]='RFADS2004', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-04-27 17:42:17.007' AS DateTime), [EthaneToMethaneRatio]=0.03 WHERE [Id]='34E929E4-CEF1-F8A6-C3A6-39D76AFD4CAC'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date], [EthaneToMethaneRatio]) VALUES (N'34E929E4-CEF1-F8A6-C3A6-39D76AFD4CAC', N'47FC54A4-26ED-7306-4D1D-39D76AFC27C4', N'RFADS2004', -32.7 ,CAST(N'2016-04-27 17:42:17.007' AS DateTime), 0.03)
-- RefGasBottle for 'iGPS car' - 'Picarro'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='EDA5A3A0-7B86-A343-69F6-39D8A7186DC1', [BatchId]='109-56-12523', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-06-28 02:52:45.447' AS DateTime), [EthaneToMethaneRatio]=0.03 WHERE [Id]='AE226F93-63FB-181B-F319-39D8A7193208'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date], [EthaneToMethaneRatio]) VALUES (N'AE226F93-63FB-181B-F319-39D8A7193208', N'EDA5A3A0-7B86-A343-69F6-39D8A7186DC1', N'109-56-12523', -32.7 ,CAST(N'2016-06-28 02:52:45.447' AS DateTime), 0.03)
-- RefGasBottle for 'NoLicAna' - CustomerWithNoLicense''
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='1E7426FF-6C7C-EFF3-303E-39DA802825EB', [BatchId]='NoLicSur-RefGasBottle1', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime) WHERE [Id]='24F12158-BB25-09EF-1DFA-39DA8028A2EA'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'24F12158-BB25-09EF-1DFA-39DA8028A2EA', N'1E7426FF-6C7C-EFF3-303E-39DA802825EB', N'NoLicSur-RefGasBottle1', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime))
-- RefGasBottle for 'EQ SurveyorUnit'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='BFEEA4F3-093D-B963-4AE6-39D7B44D9899', [BatchId]='109-56-12523', [IsotopicValue]=-32.7, [Date]=CAST(N'2015-11-10 00:00:00.000' AS DateTime), [EthaneToMethaneRatio]=0.03 WHERE [Id]='C4890397-D624-7D79-CA78-39D895DB6962'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date], [EthaneToMethaneRatio]) VALUES (N'C4890397-D624-7D79-CA78-39D895DB6962', N'BFEEA4F3-093D-B963-4AE6-39D7B44D9899', N'109-56-12523', -32.7 ,CAST(N'2015-11-10 00:00:00.000' AS DateTime), 0.03)
-- RefGasBottle - (RFADS2004-FEQ)
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='58c51edd-51a4-1266-1159-39dbd3f3366c', [BatchId]='A', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-12-02 23:04:27.220' AS DateTime) WHERE [Id]='49ef9ff8-c480-632d-8202-39dbd3f4f555'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'49ef9ff8-c480-632d-8202-39dbd3f4f555', N'58c51edd-51a4-1266-1159-39dbd3f3366c', N'A', -32.7, CAST(N'2016-12-02 23:04:27.220' AS DateTime))
-- RefGasBottle for Surveyor - 'White Rhino 2003 Picarro'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='36d72df6-2823-4542-8298-39de6c296d8e', [BatchId]='109-56-12523', [IsotopicValue]=-32.7, [Date]=CAST(N'2017-04-10 22:30:41.957' AS DateTime) WHERE [Id]='5d74209f-340a-3239-6905-39de6c2a6a25'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'5d74209f-340a-3239-6905-39de6c2a6a25', N'36d72df6-2823-4542-8298-39de6c296d8e', N'109-56-12523', -32.7, CAST(N'2017-04-10 22:30:41.957' AS DateTime))
-- RefGasBottle for Surveyor - 'SimAuto-EQSrvUnit1'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='52D4D54B-1179-4712-AFB4-FA70DDEBA96A', [BatchId]='109-56-12523', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-06-28 02:52:45.447' AS DateTime) WHERE [Id]='F08CBBAC-7ED8-4E02-BA5F-3585C8FE3068'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'F08CBBAC-7ED8-4E02-BA5F-3585C8FE3068', N'52D4D54B-1179-4712-AFB4-FA70DDEBA96A', N'109-56-12523', -32.7, CAST(N'2016-06-28 02:52:45.447' AS DateTime))
-- RefGasBottle for Surveyor - 'SimAuto-EQSrvUnitSqaCus-1'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='400F642D-F2F3-4A0A-8F11-601F9FDCC217', [BatchId]='109-56-12100', [IsotopicValue]=-32.7, [Date]=CAST(N'2014-01-01 00:00:00.000' AS DateTime) WHERE [Id]='D0A26D43-301D-4D5A-BA14-28B3243240B7'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'D0A26D43-301D-4D5A-BA14-28B3243240B7', N'400F642D-F2F3-4A0A-8F11-601F9FDCC217', N'109-56-12100', -32.7, CAST(N'2014-01-01 00:00:00.000' AS DateTime))
-- RefGasBottle for Surveyor - 'SimAuto-EQSrvUnit-Eth1'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='1C374486-FF8A-4117-BCBA-5E0C2B9D3DF7', [BatchId]='109-56-12523', [IsotopicValue]=-32.7, [Date]=CAST(N'2016-06-28 02:52:45.447' AS DateTime) WHERE [Id]='12BA2DA9-3763-42F5-AEFF-59E6C3A3A941'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'12BA2DA9-3763-42F5-AEFF-59E6C3A3A941', N'1C374486-FF8A-4117-BCBA-5E0C2B9D3DF7', N'109-56-12523', -32.7, CAST(N'2016-06-28 02:52:45.447' AS DateTime))
-- RefGasBottle for Surveyor - 'SimAuto-EQSrvUnitSqaCus-Eth1'
UPDATE [dbo].[ReferenceGasBottle] SET [SurveyorUnitId]='C5354534-336F-44D1-9FB9-6270DFE2EAD7', [BatchId]='109-56-12100', [IsotopicValue]=-32.7, [Date]=CAST(N'2014-01-01 00:00:00.000' AS DateTime) WHERE [Id]='1DD47E33-627B-4405-A6B1-A908A966A7A3'
IF @@ROWCOUNT=0
	INSERT [dbo].[ReferenceGasBottle] ([Id], [SurveyorUnitId], [BatchId], [IsotopicValue], [Date]) VALUES (N'1DD47E33-627B-4405-A6B1-A908A966A7A3', N'C5354534-336F-44D1-9FB9-6270DFE2EAD7', N'109-56-12100', -32.7, CAST(N'2014-01-01 00:00:00.000' AS DateTime))
	
	
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
-- HardwareCapabilityType for RFADS2004-PICARRO
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=0 WHERE [AnalyzerId]=N'D2CCC285-2BD3-5D22-667E-39D823D4D255'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'D2CCC285-2BD3-5D22-667E-39D823D4D255', 0)
-- HardwareCapabilityType for SQACUS2016-1
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=0 WHERE [AnalyzerId]=N'43A34021-8814-8A01-9183-39D4B4DE03EB'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'43A34021-8814-8A01-9183-39D4B4DE03EB', 0)
-- HardwareCapabilityType for FEDS2055-PICARRO
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=0 WHERE [AnalyzerId]=N'F5970731-CE37-F7F3-DB5F-39D7E2D02053'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'F5970731-CE37-F7F3-DB5F-39D7E2D02053', 1)
-- AnalyzerHardwareCapabilityType for Surveyor - 'Black Rhino FEQ'
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=0 WHERE [AnalyzerId]=N'88cfc43e-9005-03e1-1d7a-39dbd3f483de'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'88cfc43e-9005-03e1-1d7a-39dbd3f483de', 0)
-- AnalyzerHardwareCapabilityType for Surveyor - 'White Rhino 2003 Picarro'
IF NOT EXISTS (SELECT * FROM [dbo].[AnalyzerHardwareCapabilityType] WHERE [AnalyzerId]=N'6479a0f7-37c1-b40c-c53b-39de6c1e9d69' AND [HardwareCapabilityTypeId]=2)
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'6479a0f7-37c1-b40c-c53b-39de6c1e9d69', 2)
IF NOT EXISTS (SELECT * FROM [dbo].[AnalyzerHardwareCapabilityType] WHERE [AnalyzerId]=N'6479a0f7-37c1-b40c-c53b-39de6c1e9d69' AND [HardwareCapabilityTypeId]=0)
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'6479a0f7-37c1-b40c-c53b-39de6c1e9d69', 0)
-- AnalyzerHardwareCapabilityType for Surveyor - 'SimAuto-EQSrvUnit1'
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'B89BF795-F1FA-45E6-A6DC-F674A84F5E47'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'B89BF795-F1FA-45E6-A6DC-F674A84F5E47', 1)
-- AnalyzerHardwareCapabilityType for Surveyor - 'SimAuto-EQSrvUnitSqaCus-1'
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=1 WHERE [AnalyzerId]=N'3029DBB8-F824-491D-8D90-6CBD92CFDF8F'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'3029DBB8-F824-491D-8D90-6CBD92CFDF8F', 1)
-- AnalyzerHardwareCapabilityType for Surveyor - 'SimAuto-EQSrvUnit-Eth1'
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=0 WHERE [AnalyzerId]=N'AF4006D5-C01C-4D28-8099-530E350D1439'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'AF4006D5-C01C-4D28-8099-530E350D1439', 0)
-- AnalyzerHardwareCapabilityType for Surveyor - 'SimAuto-EQSrvUnitSqaCus-Eth1'
UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=0 WHERE [AnalyzerId]=N'3BB5D6C4-17D0-4088-A616-11CE03DD3274'
IF @@ROWCOUNT=0
	INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'3BB5D6C4-17D0-4088-A616-11CE03DD3274', 0)
	
	
-- CalibrationRecords
-- Add calibration record for EQ Surveyor - Nissan Rogue
UPDATE [dbo].[CalibrationRecord] SET [SurveyorUnitId]='BFEEA4F3-093D-B963-4AE6-39D7B44D9899',[StartEpoch]=1479538985.544,[BackgroundFilterThreshold]=0,[TriggerThresholdPPM]=0,[GPSOffset]=-4 WHERE [Id]='23BEBF59-BB6E-85C9-C889-39DD83BD36E5'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[CalibrationRecord] ([Id],[SurveyorUnitId],[StartEpoch],[BackgroundFilterThreshold],[TriggerThresholdPPM],[GPSOffset]) VALUES ('23BEBF59-BB6E-85C9-C889-39DD83BD36E5','BFEEA4F3-093D-B963-4AE6-39D7B44D9899',1479538985.544,0,0,-4)
-- Calibration record for 'SoftwareCar_2037_cust'
UPDATE [dbo].[CalibrationRecord] SET [SurveyorUnitId]='00000014-FB61-2EF6-5DD1-39C8AC533D41',[StartEpoch]=1483809491,[BackgroundFilterThreshold]=0,[TriggerThresholdPPM]=0,[GPSOffset]=-4 WHERE [Id]='09FD8BE0-98B1-480A-BEC6-54AC5847E141'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[CalibrationRecord] ([Id],[SurveyorUnitId],[StartEpoch],[BackgroundFilterThreshold],[TriggerThresholdPPM],[GPSOffset]) VALUES ('09FD8BE0-98B1-480A-BEC6-54AC5847E141','00000014-FB61-2EF6-5DD1-39C8AC533D41',1483809491,0,0,-4)
-- Calibration record for 'Black Rhino FEQ'
UPDATE [dbo].[CalibrationRecord] SET [SurveyorUnitId]='58c51edd-51a4-1266-1159-39dbd3f3366c',[StartEpoch]=1480719926,[BackgroundFilterThreshold]=0,[TriggerThresholdPPM]=0,[GPSOffset]=-5.2 WHERE [Id]='c9cf29da-0f18-0782-e93c-39dbd3f8f705'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[CalibrationRecord] ([Id],[SurveyorUnitId],[StartEpoch],[BackgroundFilterThreshold],[TriggerThresholdPPM],[GPSOffset]) VALUES ('c9cf29da-0f18-0782-e93c-39dbd3f8f705','58c51edd-51a4-1266-1159-39dbd3f3366c',1480719926,0,0,-5.2)

-- Calibration record for 'iGPS Car'
UPDATE [dbo].[CalibrationRecord] SET [SurveyorUnitId]='EDA5A3A0-7B86-A343-69F6-39D8A7186DC1',[StartEpoch]=1485109206,[BackgroundFilterThreshold]=0,[TriggerThresholdPPM]=0,[GPSOffset]=-4 WHERE [Id]='60540576-77EF-198F-F284-39DF4393A70F'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[CalibrationRecord] ([Id],[SurveyorUnitId],[StartEpoch],[BackgroundFilterThreshold],[TriggerThresholdPPM],[GPSOffset]) VALUES ('60540576-77EF-198F-F284-39DF4393A70F','EDA5A3A0-7B86-A343-69F6-39D8A7186DC1',1485109206,0,0,-4)	

-- Anemometer entry for 'iGPS car', calibration record
UPDATE [dbo].[Anemometer] SET [Offset]=-3.6, [SpeedFactor]=2, [Height]=2, [Rotation]=0 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Anemometer] ([CalibrationRecordId], [Index], [Offset], [SpeedFactor], [Height], [Rotation]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 0, -3.6, 2, 2, 0)	

-- Inlet entry for 'iGPS car' calibration record
UPDATE [dbo].[Inlet] SET [Height]=0.625 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 0,0.625)
UPDATE [dbo].[Inlet] SET [Height]=0.75 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=1
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 1,0.75)
UPDATE [dbo].[Inlet] SET [Height]=0.875 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=2
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 2,0.875)
UPDATE [dbo].[Inlet] SET [Height]=1 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=3
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 3,1)
UPDATE [dbo].[Inlet] SET [Height]=1.125 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=4
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 4,1.125)
UPDATE [dbo].[Inlet] SET [Height]=1.25 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=5
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 5,1.25)
UPDATE [dbo].[Inlet] SET [Height]=1.375 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=6
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 6,1.375)
UPDATE [dbo].[Inlet] SET [Height]=1.5 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=7
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 7,1.5)
UPDATE [dbo].[Inlet] SET [Height]=1.625 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=8
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 8,1.625)
UPDATE [dbo].[Inlet] SET [Height]=1.75 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=9
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 9,1.75)
UPDATE [dbo].[Inlet] SET [Height]=1.875 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=10
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 10,1.875)
UPDATE [dbo].[Inlet] SET [Height]=2 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=11
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 11,2)
UPDATE [dbo].[Inlet] SET [Height]=2.125 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=12
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 12,2.125)
UPDATE [dbo].[Inlet] SET [Height]=2.25 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=13
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 13,2.25)
UPDATE [dbo].[Inlet] SET [Height]=2.375 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=14
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 14,2.375)
UPDATE [dbo].[Inlet] SET [Height]=2.5 WHERE [CalibrationRecordId]=N'60540576-77EF-198F-F284-39DF4393A70F' AND [Index]=15
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'60540576-77EF-198F-F284-39DF4393A70F', 15,2.5)
	
	
-- Calibration record for 'White Rhino 2003 Picarro'
UPDATE [dbo].[CalibrationRecord] SET [SurveyorUnitId]='36d72df6-2823-4542-8298-39de6c296d8e',[StartEpoch]=1479366940,[BackgroundFilterThreshold]=0,[TriggerThresholdPPM]=0,[GPSOffset]=-4 WHERE [Id]='39650697-2f7c-9fa0-a12d-39dec2e766aa'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[CalibrationRecord] ([Id],[SurveyorUnitId],[StartEpoch],[BackgroundFilterThreshold],[TriggerThresholdPPM],[GPSOffset]) VALUES ('39650697-2f7c-9fa0-a12d-39dec2e766aa','36d72df6-2823-4542-8298-39de6c296d8e',1479366940,0,0,-4)

-- Anemometer entry for 'White Rhino 2003 Picarro', calibration record
UPDATE [dbo].[Anemometer] SET [Offset]=-3.6, [SpeedFactor]=1, [Height]=2, [Rotation]=0 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Anemometer] ([CalibrationRecordId], [Index], [Offset], [SpeedFactor], [Height], [Rotation]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 0, -3.6, 1, 2, 0)	

-- Inlet entry for 'White Rhino 2003 Picarro' calibration record
UPDATE [dbo].[Inlet] SET [Height]=0.625 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 0,0.625)
UPDATE [dbo].[Inlet] SET [Height]=0.75 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=1
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 1,0.75)
UPDATE [dbo].[Inlet] SET [Height]=0.875 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=2
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 2,0.875)
UPDATE [dbo].[Inlet] SET [Height]=1 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=3
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 3,1)
UPDATE [dbo].[Inlet] SET [Height]=1.125 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=4
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 4,1.125)
UPDATE [dbo].[Inlet] SET [Height]=1.25 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=5
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 5,1.25)
UPDATE [dbo].[Inlet] SET [Height]=1.375 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=6
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 6,1.375)
UPDATE [dbo].[Inlet] SET [Height]=1.5 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=7
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 7,1.5)
UPDATE [dbo].[Inlet] SET [Height]=1.625 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=8
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 8,1.625)
UPDATE [dbo].[Inlet] SET [Height]=1.75 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=9
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 9,1.75)
UPDATE [dbo].[Inlet] SET [Height]=1.875 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=10
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 10,1.875)
UPDATE [dbo].[Inlet] SET [Height]=2 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=11
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 11,2)
UPDATE [dbo].[Inlet] SET [Height]=2.125 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=12
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 12,2.125)
UPDATE [dbo].[Inlet] SET [Height]=2.25 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=13
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 13,2.25)
UPDATE [dbo].[Inlet] SET [Height]=2.375 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=14
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 14,2.375)
UPDATE [dbo].[Inlet] SET [Height]=2.5 WHERE [CalibrationRecordId]=N'39650697-2f7c-9fa0-a12d-39dec2e766aa' AND [Index]=15
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'39650697-2f7c-9fa0-a12d-39dec2e766aa', 15,2.5)
	
-- Inlet entries for EQ Surveyor - Nissan Rogue, calibration record
UPDATE [dbo].[Inlet] SET [Height]=0.625 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 0,0.625)
UPDATE [dbo].[Inlet] SET [Height]=0.75 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=1
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 1,0.75)
UPDATE [dbo].[Inlet] SET [Height]=0.875 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=2
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 2,0.875)
UPDATE [dbo].[Inlet] SET [Height]=1 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=3
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 3,1)
UPDATE [dbo].[Inlet] SET [Height]=1.125 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=4
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 4,1.125)
UPDATE [dbo].[Inlet] SET [Height]=1.25 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=5
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 5,1.25)
UPDATE [dbo].[Inlet] SET [Height]=1.375 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=6
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 6,1.375)
UPDATE [dbo].[Inlet] SET [Height]=1.5 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=7
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 7,1.5)
UPDATE [dbo].[Inlet] SET [Height]=1.625 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=8
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 8,1.625)
UPDATE [dbo].[Inlet] SET [Height]=1.75 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=9
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 9,1.75)
UPDATE [dbo].[Inlet] SET [Height]=1.875 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=10
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 10,1.875)
UPDATE [dbo].[Inlet] SET [Height]=2 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=11
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 11,2)
UPDATE [dbo].[Inlet] SET [Height]=2.125 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=12
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 12,2.125)
UPDATE [dbo].[Inlet] SET [Height]=2.25 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=13
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 13,2.25)
UPDATE [dbo].[Inlet] SET [Height]=2.375 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=14
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 14,2.375)
UPDATE [dbo].[Inlet] SET [Height]=2.5 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=15
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 15,2.5)
	
-- Anemometer entry for EQ Surveyor - Nissan Rogue, calibration record
UPDATE [dbo].[Anemometer] SET [Offset]=-3.6, [SpeedFactor]=1, [Height]=2, [Rotation]=0 WHERE [CalibrationRecordId]=N'23bebf59-bb6e-85c9-c889-39dd83bd36e5' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Anemometer] ([CalibrationRecordId], [Index], [Offset], [SpeedFactor], [Height], [Rotation]) VALUES (N'23bebf59-bb6e-85c9-c889-39dd83bd36e5', 0, -3.6, 1, 2, 0)	
	
-- Inlet entry for 'SoftwareCar_2037_cust' calibration record
UPDATE [dbo].[Inlet] SET [Height]=0.625 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 0,0.625)
UPDATE [dbo].[Inlet] SET [Height]=0.75 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=1
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 1,0.75)
UPDATE [dbo].[Inlet] SET [Height]=0.875 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=2
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 2,0.875)
UPDATE [dbo].[Inlet] SET [Height]=1 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=3
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 3,1)
UPDATE [dbo].[Inlet] SET [Height]=1.125 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=4
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 4,1.125)
UPDATE [dbo].[Inlet] SET [Height]=1.25 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=5
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 5,1.25)
UPDATE [dbo].[Inlet] SET [Height]=1.375 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=6
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 6,1.375)
UPDATE [dbo].[Inlet] SET [Height]=1.5 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=7
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 7,1.5)
UPDATE [dbo].[Inlet] SET [Height]=1.625 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=8
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 8,1.625)
UPDATE [dbo].[Inlet] SET [Height]=1.75 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=9
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 9,1.75)
UPDATE [dbo].[Inlet] SET [Height]=1.875 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=10
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 10,1.875)
UPDATE [dbo].[Inlet] SET [Height]=2 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=11
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 11,2)
UPDATE [dbo].[Inlet] SET [Height]=2.125 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=12
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 12,2.125)
UPDATE [dbo].[Inlet] SET [Height]=2.25 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=13
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 13,2.25)
UPDATE [dbo].[Inlet] SET [Height]=2.375 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=14
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 14,2.375)
UPDATE [dbo].[Inlet] SET [Height]=2.5 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=15
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 15,2.5)

-- Anemometer entry for 'SoftwareCar_2037_cust' calibration record
UPDATE [dbo].[Anemometer] SET [Offset]=-3.6, [SpeedFactor]=1, [Height]=2, [Rotation]=0 WHERE [CalibrationRecordId]=N'09FD8BE0-98B1-480A-BEC6-54AC5847E141' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Anemometer] ([CalibrationRecordId], [Index], [Offset], [SpeedFactor], [Height], [Rotation]) VALUES (N'09FD8BE0-98B1-480A-BEC6-54AC5847E141', 0, -3.6, 1, 2, 0)
	
-- Inlet entry for 'Black Rhino FEQ' calibration record
UPDATE [dbo].[Inlet] SET [Height]=0.625 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 0,0.625)
UPDATE [dbo].[Inlet] SET [Height]=0.75 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=1
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 1,0.75)
UPDATE [dbo].[Inlet] SET [Height]=0.875 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=2
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 2,0.875)
UPDATE [dbo].[Inlet] SET [Height]=1 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=3
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 3,1)
UPDATE [dbo].[Inlet] SET [Height]=1.125 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=4
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 4,1.125)
UPDATE [dbo].[Inlet] SET [Height]=1.25 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=5
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 5,1.25)
UPDATE [dbo].[Inlet] SET [Height]=1.375 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=6
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 6,1.375)
UPDATE [dbo].[Inlet] SET [Height]=1.5 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=7
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 7,1.5)
UPDATE [dbo].[Inlet] SET [Height]=1.625 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=8
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 8,1.625)
UPDATE [dbo].[Inlet] SET [Height]=1.75 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=9
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 9,1.75)
UPDATE [dbo].[Inlet] SET [Height]=1.875 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=10
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 10,1.875)
UPDATE [dbo].[Inlet] SET [Height]=2 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=11
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 11,2)
UPDATE [dbo].[Inlet] SET [Height]=2.125 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=12
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 12,2.125)
UPDATE [dbo].[Inlet] SET [Height]=2.25 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=13
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 13,2.25)
UPDATE [dbo].[Inlet] SET [Height]=2.375 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=14
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 14,2.375)
UPDATE [dbo].[Inlet] SET [Height]=2.5 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=15
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 15,2.5)

-- Anemometer entry for 'Black Rhino FEQ', calibration record
UPDATE [dbo].[Anemometer] SET [Offset]=0, [SpeedFactor]=1, [Height]=0, [Rotation]=0 WHERE [CalibrationRecordId]=N'c9cf29da-0f18-0782-e93c-39dbd3f8f705' AND [Index]=3
IF @@ROWCOUNT=0
	INSERT [dbo].[Anemometer] ([CalibrationRecordId], [Index], [Offset], [SpeedFactor], [Height], [Rotation]) VALUES (N'c9cf29da-0f18-0782-e93c-39dbd3f8f705', 3, 0, 1, 0, 0)	

-- Calibration record for 'SimAuto-EQSrvUnit1'
UPDATE [dbo].[CalibrationRecord] SET [SurveyorUnitId]='52D4D54B-1179-4712-AFB4-FA70DDEBA96A',[StartEpoch]=1479366940,[BackgroundFilterThreshold]=0,[TriggerThresholdPPM]=0,[GPSOffset]=-4 WHERE [Id]='BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[CalibrationRecord] ([Id],[SurveyorUnitId],[StartEpoch],[BackgroundFilterThreshold],[TriggerThresholdPPM],[GPSOffset]) VALUES ('BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD','52D4D54B-1179-4712-AFB4-FA70DDEBA96A',1479366940,0,0,-4)

-- Anemometer entry for 'SimAuto-EQSrvUnit1', calibration record
UPDATE [dbo].[Anemometer] SET [Offset]=-3.6, [SpeedFactor]=2, [Height]=2, [Rotation]=0 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Anemometer] ([CalibrationRecordId], [Index], [Offset], [SpeedFactor], [Height], [Rotation]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 0, -3.6, 2, 2, 0)	

-- Inlet entry for 'SimAuto-EQSrvUnit1' calibration record
UPDATE [dbo].[Inlet] SET [Height]=0.625 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 0,0.625)
UPDATE [dbo].[Inlet] SET [Height]=0.75 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=1
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 1,0.75)
UPDATE [dbo].[Inlet] SET [Height]=0.875 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=2
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 2,0.875)
UPDATE [dbo].[Inlet] SET [Height]=1 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=3
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 3,1)
UPDATE [dbo].[Inlet] SET [Height]=1.125 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=4
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 4,1.125)
UPDATE [dbo].[Inlet] SET [Height]=1.25 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=5
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 5,1.25)
UPDATE [dbo].[Inlet] SET [Height]=1.375 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=6
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 6,1.375)
UPDATE [dbo].[Inlet] SET [Height]=1.5 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=7
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 7,1.5)
UPDATE [dbo].[Inlet] SET [Height]=1.625 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=8
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 8,1.625)
UPDATE [dbo].[Inlet] SET [Height]=1.75 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=9
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 9,1.75)
UPDATE [dbo].[Inlet] SET [Height]=1.875 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=10
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 10,1.875)
UPDATE [dbo].[Inlet] SET [Height]=2 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=11
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 11,2)
UPDATE [dbo].[Inlet] SET [Height]=2.125 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=12
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 12,2.125)
UPDATE [dbo].[Inlet] SET [Height]=2.25 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=13
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 13,2.25)
UPDATE [dbo].[Inlet] SET [Height]=2.375 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=14
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 14,2.375)
UPDATE [dbo].[Inlet] SET [Height]=2.5 WHERE [CalibrationRecordId]=N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD' AND [Index]=15
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'BAFB6AF2-44F4-460D-B208-F2FE42E1A0BD', 15,2.5)

-- Calibration record for 'SimAuto-EQSrvUnitSqaCus-1'
UPDATE [dbo].[CalibrationRecord] SET [SurveyorUnitId]='400F642D-F2F3-4A0A-8F11-601F9FDCC217',[StartEpoch]=1479366940,[BackgroundFilterThreshold]=0,[TriggerThresholdPPM]=0,[GPSOffset]=-4 WHERE [Id]='37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[CalibrationRecord] ([Id],[SurveyorUnitId],[StartEpoch],[BackgroundFilterThreshold],[TriggerThresholdPPM],[GPSOffset]) VALUES ('37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1','400F642D-F2F3-4A0A-8F11-601F9FDCC217',1479366940,0,0,-4)

-- Anemometer entry for 'SimAuto-EQSrvUnitSqaCus-1', calibration record
UPDATE [dbo].[Anemometer] SET [Offset]=-3.6, [SpeedFactor]=1, [Height]=2, [Rotation]=0 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Anemometer] ([CalibrationRecordId], [Index], [Offset], [SpeedFactor], [Height], [Rotation]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 0, -3.6, 1, 2, 0)	

-- Inlet entry for 'SimAuto-EQSrvUnitSqaCus-1' calibration record
UPDATE [dbo].[Inlet] SET [Height]=0.625 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 0,0.625)
UPDATE [dbo].[Inlet] SET [Height]=0.75 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=1
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 1,0.75)
UPDATE [dbo].[Inlet] SET [Height]=0.875 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=2
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 2,0.875)
UPDATE [dbo].[Inlet] SET [Height]=1 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=3
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 3,1)
UPDATE [dbo].[Inlet] SET [Height]=1.125 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=4
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 4,1.125)
UPDATE [dbo].[Inlet] SET [Height]=1.25 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=5
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 5,1.25)
UPDATE [dbo].[Inlet] SET [Height]=1.375 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=6
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 6,1.375)
UPDATE [dbo].[Inlet] SET [Height]=1.5 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=7
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 7,1.5)
UPDATE [dbo].[Inlet] SET [Height]=1.625 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=8
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 8,1.625)
UPDATE [dbo].[Inlet] SET [Height]=1.75 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=9
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 9,1.75)
UPDATE [dbo].[Inlet] SET [Height]=1.875 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=10
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 10,1.875)
UPDATE [dbo].[Inlet] SET [Height]=2 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=11
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 11,2)
UPDATE [dbo].[Inlet] SET [Height]=2.125 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=12
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 12,2.125)
UPDATE [dbo].[Inlet] SET [Height]=2.25 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=13
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 13,2.25)
UPDATE [dbo].[Inlet] SET [Height]=2.375 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=14
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 14,2.375)
UPDATE [dbo].[Inlet] SET [Height]=2.5 WHERE [CalibrationRecordId]=N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1' AND [Index]=15
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'37C0C6C3-ECD5-4DA5-9B28-C3E986CB1ED1', 15,2.5)

-- Calibration record for 'SimAuto-EQSrvUnit-Eth1'
UPDATE [dbo].[CalibrationRecord] SET [SurveyorUnitId]='1C374486-FF8A-4117-BCBA-5E0C2B9D3DF7',[StartEpoch]=1479366940,[BackgroundFilterThreshold]=0,[TriggerThresholdPPM]=0,[GPSOffset]=-4 WHERE [Id]='2BC10730-A313-4C13-AB22-AADE43267721'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[CalibrationRecord] ([Id],[SurveyorUnitId],[StartEpoch],[BackgroundFilterThreshold],[TriggerThresholdPPM],[GPSOffset]) VALUES ('2BC10730-A313-4C13-AB22-AADE43267721','1C374486-FF8A-4117-BCBA-5E0C2B9D3DF7',1479366940,0,0,-4)

-- Anemometer entry for 'SimAuto-EQSrvUnit-Eth1', calibration record
UPDATE [dbo].[Anemometer] SET [Offset]=-3.6, [SpeedFactor]=2, [Height]=2, [Rotation]=0 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Anemometer] ([CalibrationRecordId], [Index], [Offset], [SpeedFactor], [Height], [Rotation]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 0, -3.6, 2, 2, 0)	

-- Inlet entry for 'SimAuto-EQSrvUnit-Eth1' calibration record
UPDATE [dbo].[Inlet] SET [Height]=0.625 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 0,0.625)
UPDATE [dbo].[Inlet] SET [Height]=0.75 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=1
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 1,0.75)
UPDATE [dbo].[Inlet] SET [Height]=0.875 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=2
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 2,0.875)
UPDATE [dbo].[Inlet] SET [Height]=1 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=3
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 3,1)
UPDATE [dbo].[Inlet] SET [Height]=1.125 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=4
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 4,1.125)
UPDATE [dbo].[Inlet] SET [Height]=1.25 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=5
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 5,1.25)
UPDATE [dbo].[Inlet] SET [Height]=1.375 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=6
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 6,1.375)
UPDATE [dbo].[Inlet] SET [Height]=1.5 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=7
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 7,1.5)
UPDATE [dbo].[Inlet] SET [Height]=1.625 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=8
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 8,1.625)
UPDATE [dbo].[Inlet] SET [Height]=1.75 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=9
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 9,1.75)
UPDATE [dbo].[Inlet] SET [Height]=1.875 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=10
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 10,1.875)
UPDATE [dbo].[Inlet] SET [Height]=2 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=11
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 11,2)
UPDATE [dbo].[Inlet] SET [Height]=2.125 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=12
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 12,2.125)
UPDATE [dbo].[Inlet] SET [Height]=2.25 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=13
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 13,2.25)
UPDATE [dbo].[Inlet] SET [Height]=2.375 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=14
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 14,2.375)
UPDATE [dbo].[Inlet] SET [Height]=2.5 WHERE [CalibrationRecordId]=N'2BC10730-A313-4C13-AB22-AADE43267721' AND [Index]=15
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC10730-A313-4C13-AB22-AADE43267721', 15,2.5)

-- Calibration record for 'SimAuto-EQSrvUnitSqaCus-Eth1'
UPDATE [dbo].[CalibrationRecord] SET [SurveyorUnitId]='C5354534-336F-44D1-9FB9-6270DFE2EAD7',[StartEpoch]=1479366940,[BackgroundFilterThreshold]=0,[TriggerThresholdPPM]=0,[GPSOffset]=-4 WHERE [Id]='2BC9C664-99BD-4842-B86D-10E13C37DDB0'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[CalibrationRecord] ([Id],[SurveyorUnitId],[StartEpoch],[BackgroundFilterThreshold],[TriggerThresholdPPM],[GPSOffset]) VALUES ('2BC9C664-99BD-4842-B86D-10E13C37DDB0','C5354534-336F-44D1-9FB9-6270DFE2EAD7',1479366940,0,0,-4)

-- Anemometer entry for 'SimAuto-EQSrvUnitSqaCus-Eth1', calibration record
UPDATE [dbo].[Anemometer] SET [Offset]=-3.6, [SpeedFactor]=1, [Height]=2, [Rotation]=0 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Anemometer] ([CalibrationRecordId], [Index], [Offset], [SpeedFactor], [Height], [Rotation]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 0, -3.6, 1, 2, 0)	

-- Inlet entry for 'SimAuto-EQSrvUnitSqaCus-Eth1' calibration record
UPDATE [dbo].[Inlet] SET [Height]=0.625 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 0,0.625)
UPDATE [dbo].[Inlet] SET [Height]=0.75 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=1
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 1,0.75)
UPDATE [dbo].[Inlet] SET [Height]=0.875 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=2
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 2,0.875)
UPDATE [dbo].[Inlet] SET [Height]=1 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=3
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 3,1)
UPDATE [dbo].[Inlet] SET [Height]=1.125 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=4
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 4,1.125)
UPDATE [dbo].[Inlet] SET [Height]=1.25 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=5
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 5,1.25)
UPDATE [dbo].[Inlet] SET [Height]=1.375 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=6
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 6,1.375)
UPDATE [dbo].[Inlet] SET [Height]=1.5 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=7
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 7,1.5)
UPDATE [dbo].[Inlet] SET [Height]=1.625 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=8
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 8,1.625)
UPDATE [dbo].[Inlet] SET [Height]=1.75 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=9
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 9,1.75)
UPDATE [dbo].[Inlet] SET [Height]=1.875 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=10
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 10,1.875)
UPDATE [dbo].[Inlet] SET [Height]=2 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=11
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 11,2)
UPDATE [dbo].[Inlet] SET [Height]=2.125 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=12
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 12,2.125)
UPDATE [dbo].[Inlet] SET [Height]=2.25 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=13
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 13,2.25)
UPDATE [dbo].[Inlet] SET [Height]=2.375 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=14
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 14,2.375)
UPDATE [dbo].[Inlet] SET [Height]=2.5 WHERE [CalibrationRecordId]=N'2BC9C664-99BD-4842-B86D-10E13C37DDB0' AND [Index]=15
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'2BC9C664-99BD-4842-B86D-10E13C37DDB0', 15,2.5)
	
-- Calibration record for 'SQACusSrvUnit-1'
UPDATE [dbo].[CalibrationRecord] SET [SurveyorUnitId]='EDBACFF7-E103-C14C-9DF8-39CD7B5F2A1A',[StartEpoch]=1485109206,[BackgroundFilterThreshold]=0,[TriggerThresholdPPM]=0,[GPSOffset]=-4 WHERE [Id]='31D6E287-E55A-4962-8DA4-8B358C4202E5'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[CalibrationRecord] ([Id],[SurveyorUnitId],[StartEpoch],[BackgroundFilterThreshold],[TriggerThresholdPPM],[GPSOffset]) VALUES ('31D6E287-E55A-4962-8DA4-8B358C4202E5','EDBACFF7-E103-C14C-9DF8-39CD7B5F2A1A',1485109206,0,0,-4)

-- Anemometer entry for 'SQACusSrvUnit-1', calibration record
UPDATE [dbo].[Anemometer] SET [Offset]=-3.6, [SpeedFactor]=2, [Height]=2, [Rotation]=0 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Anemometer] ([CalibrationRecordId], [Index], [Offset], [SpeedFactor], [Height], [Rotation]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 0, -3.6, 2, 2, 0)	

-- Inlet entry for 'SQACusSrvUnit-1' calibration record
UPDATE [dbo].[Inlet] SET [Height]=0.625 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=0
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 0,0.625)
UPDATE [dbo].[Inlet] SET [Height]=0.75 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=1
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 1,0.75)
UPDATE [dbo].[Inlet] SET [Height]=0.875 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=2
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 2,0.875)
UPDATE [dbo].[Inlet] SET [Height]=1 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=3
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 3,1)
UPDATE [dbo].[Inlet] SET [Height]=1.125 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=4
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 4,1.125)
UPDATE [dbo].[Inlet] SET [Height]=1.25 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=5
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 5,1.25)
UPDATE [dbo].[Inlet] SET [Height]=1.375 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=6
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 6,1.375)
UPDATE [dbo].[Inlet] SET [Height]=1.5 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=7
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 7,1.5)
UPDATE [dbo].[Inlet] SET [Height]=1.625 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=8
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 8,1.625)
UPDATE [dbo].[Inlet] SET [Height]=1.75 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=9
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 9,1.75)
UPDATE [dbo].[Inlet] SET [Height]=1.875 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=10
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 10,1.875)
UPDATE [dbo].[Inlet] SET [Height]=2 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=11
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 11,2)
UPDATE [dbo].[Inlet] SET [Height]=2.125 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=12
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 12,2.125)
UPDATE [dbo].[Inlet] SET [Height]=2.25 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=13
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 13,2.25)
UPDATE [dbo].[Inlet] SET [Height]=2.375 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=14
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 14,2.375)
UPDATE [dbo].[Inlet] SET [Height]=2.5 WHERE [CalibrationRecordId]=N'31D6E287-E55A-4962-8DA4-8B358C4202E5' AND [Index]=15
IF @@ROWCOUNT=0
	INSERT [dbo].[Inlet] ([CalibrationRecordId], [Index], [Height]) VALUES (N'31D6E287-E55A-4962-8DA4-8B358C4202E5', 15,2.5)

	
--Users:
--Users for manual
-- Users assigned to Location='Santa Clara', Customer='Picarro'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Picarro' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default' AND CustomerId=@customerId
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'Seed_first',[LastName]=N'seed_last',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='AutomationAdmin'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C600',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'Seed_first',N'seed_last',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','AutomationAdmin')
-- Users assigned to Location='sqaTestloc', Customer='sqaTest'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'sqaTest' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqaTestloc' AND CustomerId=@customerId
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
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='pge_SC' AND CustomerId=@customerId
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
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara' AND CustomerId=@customerId
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
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara' AND CustomerId=@customerId
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqapicsup',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqapicsup@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'575DC0C7-7927-3EA6-56E4-39D375BFA723',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqapicsup',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapicsup@picarro.com')
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'sqapicdr',[LastName]=N'lastName',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='sqapicdr@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C562',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'sqapicdr',N'lastName',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','sqapicdr@picarro.com')
-- Users assigned to Location='sqacusloc', Customer='sqacus'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'sqacus' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='sqacusloc' AND CustomerId=@customerId
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
-- User assigned to Location='NolicenseLoc', Customer='CustomerWithNoLicense'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'CustomerWithNoLicense' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='NolicenseLoc' AND CustomerId=@customerId
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=Null,[LastName]=Null,[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='CusAdmUserWithNoLic@email.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName])
	VALUES   (N'A4DCBF18-3A08-ADD1-0C85-39DA802F57AB',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,Null,Null,NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','CusAdmUserWithNoLic@email.com')

-- User (picdr2@picarro.com) - for FEQ
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Picarro' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default FEQ Location'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'',[LastName]=N'',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='picdr2@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'de734ddf-363e-49fc-8dbc-39c8c223d558',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'',N'',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','picdr2@picarro.com')

-- User for assessment surveys.
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Picarro' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default' AND CustomerId=@customerId
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'driver1',[LastName]=N'Picarro',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='driver1@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'B195D287-52BA-FFA4-9405-39D60DAE335C',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'driver1',N'Picarro',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','driver1@picarro.com')
-- User for StandardWithLeak,NoFov surveys.
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'driver2',[LastName]=N'Picarro',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',[SecurityStamp]=N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='driver2@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'A4897D62-26A6-48E5-9966-39D23A68B161',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'driver2',N'Picarro',NULL,NULL,N'0',N'AA7woOuTNxwDCcQoo2Xq/Z5372UeFyS4beksZrkaU5Orz/b22355leGbNHZdLSlHjw==',N'254fc4fe-7a90-4e6d-9b5e-aa3bdc319f4a',NULL,N'0',N'0',NULL,N'0',N'0','driver2@picarro.com')

	
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
SELECT @userId = [Id] FROM [dbo].[User] WHERE [UserName]='driver2@picarro.com'  
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
BEGIN TRANSACTION;

BEGIN TRY

	DECLARE @customerId uniqueidentifier

	-- Customer - 'AutomationSeedCustomer00001'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00001')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00001',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='1c1063e4-7bc4-b700-dede-39e23737567f' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'1c1063e4-7bc4-b700-dede-39e23737567f',N'AutomationSeedCustomer00001' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00001'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'1c1063e4-7bc4-b700-dede-39e23737567f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'1c1063e4-7bc4-b700-dede-39e23737567f')

	-- Location for Customer - 'AutomationSeedCustomer00001'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00001'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00001Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='ff5c0836-d585-2dcd-f5c3-39e237399df8' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'ff5c0836-d585-2dcd-f5c3-39e237399df8', @customerId, N'AutomationSeedCustomer00001Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00002'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00002')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00002',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='fbe8bddf-105e-f19d-2a9b-39e1ef1db870' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870',N'AutomationSeedCustomer00002' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00002'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'fbe8bddf-105e-f19d-2a9b-39e1ef1db870')

	-- Location for Customer - 'AutomationSeedCustomer00002'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00002'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00002Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='45ddecea-f0a6-b25e-0c1c-39e1ef1ff420' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'45ddecea-f0a6-b25e-0c1c-39e1ef1ff420', @customerId, N'AutomationSeedCustomer00002Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00003'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00003')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00003',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='8da641df-c53c-4b4a-c3b6-39e15e9f3466' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'8da641df-c53c-4b4a-c3b6-39e15e9f3466',N'AutomationSeedCustomer00003' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00003'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'8da641df-c53c-4b4a-c3b6-39e15e9f3466')

	-- Location for Customer - 'AutomationSeedCustomer00003'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00003'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00003Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='aa364dba-55c9-4164-bfd2-39e15ea15b66' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'aa364dba-55c9-4164-bfd2-39e15ea15b66', @customerId, N'AutomationSeedCustomer00003Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00004'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00004')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00004',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='ebbeba32-68a2-4185-005a-39e19762dd00' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'ebbeba32-68a2-4185-005a-39e19762dd00',N'AutomationSeedCustomer00004' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00004'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'ebbeba32-68a2-4185-005a-39e19762dd00')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'ebbeba32-68a2-4185-005a-39e19762dd00')

	-- Location for Customer - 'AutomationSeedCustomer00004'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00004'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00004Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='05c670df-607e-cd18-8ba9-39e197650be6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'05c670df-607e-cd18-8ba9-39e197650be6', @customerId, N'AutomationSeedCustomer00004Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00005'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00005')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00005',[Eula]=N'regcus168955LicFeature: Testing eula string, TBD',[Active]=1 WHERE [Id]='026a34c6-0ddf-c24c-e4cf-39e1b8632427' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'026a34c6-0ddf-c24c-e4cf-39e1b8632427',N'AutomationSeedCustomer00005' ,N'regcus168955LicFeature: Testing eula string, TBD',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00005'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'026a34c6-0ddf-c24c-e4cf-39e1b8632427')

	-- Location for Customer - 'AutomationSeedCustomer00005'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00005'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00005Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='d99254a0-549b-3bc5-3167-39e1b8638838' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'd99254a0-549b-3bc5-3167-39e1b8638838', @customerId, N'AutomationSeedCustomer00005Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00006'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00006')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00006',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='81004af3-0cdd-31d6-4104-39e1b1221939' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'81004af3-0cdd-31d6-4104-39e1b1221939',N'AutomationSeedCustomer00006' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00006'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'81004af3-0cdd-31d6-4104-39e1b1221939')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'81004af3-0cdd-31d6-4104-39e1b1221939')

	-- Location for Customer - 'AutomationSeedCustomer00006'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00006'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00006Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='9f5fadf5-79e0-22ee-854d-39e1b1244dcb' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'9f5fadf5-79e0-22ee-854d-39e1b1244dcb', @customerId, N'AutomationSeedCustomer00006Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00007'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00007')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00007',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='30a55690-5ad1-005f-e7f7-39e2320e3910' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'30a55690-5ad1-005f-e7f7-39e2320e3910',N'AutomationSeedCustomer00007' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00007'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'30a55690-5ad1-005f-e7f7-39e2320e3910')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'30a55690-5ad1-005f-e7f7-39e2320e3910')

	-- Location for Customer - 'AutomationSeedCustomer00007'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00007'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00007Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='e355fe72-3e45-54c2-3ba0-39e232107d80' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'e355fe72-3e45-54c2-3ba0-39e232107d80', @customerId, N'AutomationSeedCustomer00007Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00008'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00008')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00008',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='c75e3343-5c0f-2fed-4d40-39e256257597' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'c75e3343-5c0f-2fed-4d40-39e256257597',N'AutomationSeedCustomer00008' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00008'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'c75e3343-5c0f-2fed-4d40-39e256257597')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'c75e3343-5c0f-2fed-4d40-39e256257597')

	-- Location for Customer - 'AutomationSeedCustomer00008'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00008'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00008Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='3cca958c-3231-4ebb-b94f-39e25627c284' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'3cca958c-3231-4ebb-b94f-39e25627c284', @customerId, N'AutomationSeedCustomer00008Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00009'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00009')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00009',[Eula]=N'regcus513974Analytics_Report: Testing eula string, TBD',[Active]=1 WHERE [Id]='2dfb8821-e9f1-bedd-8a24-39e18410b127' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'2dfb8821-e9f1-bedd-8a24-39e18410b127',N'AutomationSeedCustomer00009' ,N'regcus513974Analytics_Report: Testing eula string, TBD',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00009'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'2dfb8821-e9f1-bedd-8a24-39e18410b127')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'2dfb8821-e9f1-bedd-8a24-39e18410b127')

	-- Location for Customer - 'AutomationSeedCustomer00009'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00009'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00009Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='e88fe23f-d132-30e7-9f32-39e184110b00' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'e88fe23f-d132-30e7-9f32-39e184110b00', @customerId, N'AutomationSeedCustomer00009Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00010'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00010')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00010',[Eula]=N'Sample EULA text',[Active]=1 WHERE [Id]='14bb8521-6af6-e44c-af0c-39e1f70a8aae' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'14bb8521-6af6-e44c-af0c-39e1f70a8aae',N'AutomationSeedCustomer00010' ,N'Sample EULA text',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00010'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'14bb8521-6af6-e44c-af0c-39e1f70a8aae')

	-- Location for Customer - 'AutomationSeedCustomer00010'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00010'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00010Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='346ac02f-52c3-48d9-90f1-39e1f70d5a9b' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'346ac02f-52c3-48d9-90f1-39e1f70d5a9b', @customerId, N'AutomationSeedCustomer00010Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00011'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00011')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00011',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='d0f6a395-94ea-2d57-7355-39e1d542a051' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'd0f6a395-94ea-2d57-7355-39e1d542a051',N'AutomationSeedCustomer00011' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00011'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'd0f6a395-94ea-2d57-7355-39e1d542a051')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'd0f6a395-94ea-2d57-7355-39e1d542a051')

	-- Location for Customer - 'AutomationSeedCustomer00011'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00011'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00011Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='2a825845-5e87-46aa-6ad6-39e1d544d8db' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'2a825845-5e87-46aa-6ad6-39e1d544d8db', @customerId, N'AutomationSeedCustomer00011Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00012'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00012')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00012',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='1210df93-a433-4c9b-e051-39e1e9f6db9d' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'1210df93-a433-4c9b-e051-39e1e9f6db9d',N'AutomationSeedCustomer00012' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00012'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'1210df93-a433-4c9b-e051-39e1e9f6db9d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'1210df93-a433-4c9b-e051-39e1e9f6db9d')

	-- Location for Customer - 'AutomationSeedCustomer00012'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00012'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00012Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='22129c12-25b5-5467-6488-39e1e9f91815' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'22129c12-25b5-5467-6488-39e1e9f91815', @customerId, N'AutomationSeedCustomer00012Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00013'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00013')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00013',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='cb734061-8cd6-85db-6238-39e25b4c97e7' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'cb734061-8cd6-85db-6238-39e25b4c97e7',N'AutomationSeedCustomer00013' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00013'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'cb734061-8cd6-85db-6238-39e25b4c97e7')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'cb734061-8cd6-85db-6238-39e25b4c97e7')

	-- Location for Customer - 'AutomationSeedCustomer00013'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00013'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00013Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='23710b40-e8fc-9437-7107-39e25b4ee5f0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'23710b40-e8fc-9437-7107-39e25b4ee5f0', @customerId, N'AutomationSeedCustomer00013Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00014'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00014')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00014',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='f2170979-e081-7e11-ee53-39e250f8438e' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'f2170979-e081-7e11-ee53-39e250f8438e',N'AutomationSeedCustomer00014' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00014'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'f2170979-e081-7e11-ee53-39e250f8438e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'f2170979-e081-7e11-ee53-39e250f8438e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'f2170979-e081-7e11-ee53-39e250f8438e')

	-- Location for Customer - 'AutomationSeedCustomer00014'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00014'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00014Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='f7fedc9b-8c92-151d-6ffe-39e250fa8f08' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'f7fedc9b-8c92-151d-6ffe-39e250fa8f08', @customerId, N'AutomationSeedCustomer00014Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00015'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00015')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00015',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='948e22d9-85b4-d724-ed3f-39e2659b722f' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'948e22d9-85b4-d724-ed3f-39e2659b722f',N'AutomationSeedCustomer00015' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00015'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'948e22d9-85b4-d724-ed3f-39e2659b722f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'948e22d9-85b4-d724-ed3f-39e2659b722f')

	-- Location for Customer - 'AutomationSeedCustomer00015'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00015'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00015Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='ce45f606-7e14-924c-7498-39e2659dc301' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'ce45f606-7e14-924c-7498-39e2659dc301', @customerId, N'AutomationSeedCustomer00015Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00016'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00016')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00016',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='a521c5c1-96d7-1cc3-a014-39e21d73943b' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'a521c5c1-96d7-1cc3-a014-39e21d73943b',N'AutomationSeedCustomer00016' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00016'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'a521c5c1-96d7-1cc3-a014-39e21d73943b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'a521c5c1-96d7-1cc3-a014-39e21d73943b')

	-- Location for Customer - 'AutomationSeedCustomer00016'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00016'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00016Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='d5f86e2f-3b2e-943a-583b-39e21d75d68b' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'd5f86e2f-3b2e-943a-583b-39e21d75d68b', @customerId, N'AutomationSeedCustomer00016Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00017'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00017')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00017',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='329b4aec-1835-af38-3a5b-39e18cfc8a33' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'329b4aec-1835-af38-3a5b-39e18cfc8a33',N'AutomationSeedCustomer00017' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00017'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'329b4aec-1835-af38-3a5b-39e18cfc8a33')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'329b4aec-1835-af38-3a5b-39e18cfc8a33')

	-- Location for Customer - 'AutomationSeedCustomer00017'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00017'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00017Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='51efe684-8029-be60-3b35-39e18cfeb61a' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'51efe684-8029-be60-3b35-39e18cfeb61a', @customerId, N'AutomationSeedCustomer00017Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00018'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00018')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00018',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='8b1af892-7a0b-436b-756e-39e14f314223' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'8b1af892-7a0b-436b-756e-39e14f314223',N'AutomationSeedCustomer00018' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00018'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'8b1af892-7a0b-436b-756e-39e14f314223')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'8b1af892-7a0b-436b-756e-39e14f314223')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'8b1af892-7a0b-436b-756e-39e14f314223')

	-- Location for Customer - 'AutomationSeedCustomer00018'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00018'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00018Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='2759bdd3-1f21-1031-a846-39e14f336744' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'2759bdd3-1f21-1031-a846-39e14f336744', @customerId, N'AutomationSeedCustomer00018Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00019'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00019')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00019',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='036d207a-9c01-f6ec-81e5-39e260724f70' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'036d207a-9c01-f6ec-81e5-39e260724f70',N'AutomationSeedCustomer00019' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00019'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'036d207a-9c01-f6ec-81e5-39e260724f70')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'036d207a-9c01-f6ec-81e5-39e260724f70')

	-- Location for Customer - 'AutomationSeedCustomer00019'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00019'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00019Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='59284b5c-8049-284e-683a-39e260749eb9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'59284b5c-8049-284e-683a-39e260749eb9', @customerId, N'AutomationSeedCustomer00019Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00020'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00020')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00020',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='837021d1-764b-cff8-2bc6-39e246b06ba8' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'837021d1-764b-cff8-2bc6-39e246b06ba8',N'AutomationSeedCustomer00020' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00020'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'837021d1-764b-cff8-2bc6-39e246b06ba8')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'837021d1-764b-cff8-2bc6-39e246b06ba8')

	-- Location for Customer - 'AutomationSeedCustomer00020'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00020'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00020Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='8654e78a-83bf-6217-f89c-39e246b2b4f5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'8654e78a-83bf-6217-f89c-39e246b2b4f5', @customerId, N'AutomationSeedCustomer00020Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00021'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00021')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00021',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='1e54f5c0-849b-e583-687a-39e1caeb7cc3' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'1e54f5c0-849b-e583-687a-39e1caeb7cc3',N'AutomationSeedCustomer00021' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00021'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'1e54f5c0-849b-e583-687a-39e1caeb7cc3')

	-- Location for Customer - 'AutomationSeedCustomer00021'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00021'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00021Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='ad4ab986-bb47-a0f7-f5e8-39e1caedb4ad' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'ad4ab986-bb47-a0f7-f5e8-39e1caedb4ad', @customerId, N'AutomationSeedCustomer00021Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00022'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00022')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00022',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='e3eee267-8ace-f482-436a-39e1d01d432d' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'e3eee267-8ace-f482-436a-39e1d01d432d',N'AutomationSeedCustomer00022' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00022'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'e3eee267-8ace-f482-436a-39e1d01d432d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'e3eee267-8ace-f482-436a-39e1d01d432d')

	-- Location for Customer - 'AutomationSeedCustomer00022'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00022'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00022Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='e1e10721-89f0-85dc-4c51-39e1d01f7b65' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'e1e10721-89f0-85dc-4c51-39e1d01f7b65', @customerId, N'AutomationSeedCustomer00022Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00023'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00023')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00023',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='9eb91768-0f30-c59a-4db9-39e149bf86df' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'9eb91768-0f30-c59a-4db9-39e149bf86df',N'AutomationSeedCustomer00023' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00023'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'9eb91768-0f30-c59a-4db9-39e149bf86df')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'9eb91768-0f30-c59a-4db9-39e149bf86df')

	-- Location for Customer - 'AutomationSeedCustomer00023'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00023'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00023Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='550d8b83-c4f4-bb54-8db9-39e149c1a956' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'550d8b83-c4f4-bb54-8db9-39e149c1a956', @customerId, N'AutomationSeedCustomer00023Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00024'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00024')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00024',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='654e0a3d-ddbb-67dd-51f7-39e1b64a57b3' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3',N'AutomationSeedCustomer00024' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00024'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'654e0a3d-ddbb-67dd-51f7-39e1b64a57b3')

	-- Location for Customer - 'AutomationSeedCustomer00024'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00024'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00024Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='809f2caf-9ba4-713a-187f-39e1b64c8b94' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'809f2caf-9ba4-713a-187f-39e1b64c8b94', @customerId, N'AutomationSeedCustomer00024Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00025'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00025')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00025',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='01b88814-a0e2-6ccb-fd86-39e24bd306c3' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'01b88814-a0e2-6ccb-fd86-39e24bd306c3',N'AutomationSeedCustomer00025' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00025'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'01b88814-a0e2-6ccb-fd86-39e24bd306c3')

	-- Location for Customer - 'AutomationSeedCustomer00025'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00025'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00025Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='3e1dee9a-0806-bbb3-3afa-39e24bd550c9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'3e1dee9a-0806-bbb3-3afa-39e24bd550c9', @customerId, N'AutomationSeedCustomer00025Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00026'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00026')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00026',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='9ea8d045-7bd1-dfaa-3288-39e1c09804c5' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5',N'AutomationSeedCustomer00026' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00026'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'9ea8d045-7bd1-dfaa-3288-39e1c09804c5')

	-- Location for Customer - 'AutomationSeedCustomer00026'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00026'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00026Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='7006fbe2-0914-41c9-4b42-39e1c09a3a26' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'7006fbe2-0914-41c9-4b42-39e1c09a3a26', @customerId, N'AutomationSeedCustomer00026Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00027'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00027')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00027',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='86d0e7df-b1d8-18d4-3636-39e23f12ffef' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'86d0e7df-b1d8-18d4-3636-39e23f12ffef',N'AutomationSeedCustomer00027' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00027'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'86d0e7df-b1d8-18d4-3636-39e23f12ffef')

	-- Location for Customer - 'AutomationSeedCustomer00027'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00027'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00027Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='145abc41-0c0c-bf4e-0c77-39e23f15492f' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'145abc41-0c0c-bf4e-0c77-39e23f15492f', @customerId, N'AutomationSeedCustomer00027Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00028'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00028')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00028',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='eafdafff-de98-db0e-7d0f-39e187cf6ca4' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'eafdafff-de98-db0e-7d0f-39e187cf6ca4',N'AutomationSeedCustomer00028' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00028'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'eafdafff-de98-db0e-7d0f-39e187cf6ca4')

	-- Location for Customer - 'AutomationSeedCustomer00028'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00028'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00028Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='15ecd2f9-b6a7-46c9-4ec7-39e187d198f3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'15ecd2f9-b6a7-46c9-4ec7-39e187d198f3', @customerId, N'AutomationSeedCustomer00028Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00029'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00029')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00029',[Eula]=N'regcus825567LicFeature: Testing eula string, TBD',[Active]=1 WHERE [Id]='3a74fbe3-afed-5218-2445-39e18470d8fa' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'3a74fbe3-afed-5218-2445-39e18470d8fa',N'AutomationSeedCustomer00029' ,N'regcus825567LicFeature: Testing eula string, TBD',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00029'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'3a74fbe3-afed-5218-2445-39e18470d8fa')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'3a74fbe3-afed-5218-2445-39e18470d8fa')

	-- Location for Customer - 'AutomationSeedCustomer00029'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00029'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00029Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='70d2cb27-71be-0792-49c9-39e1847132bb' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'70d2cb27-71be-0792-49c9-39e1847132bb', @customerId, N'AutomationSeedCustomer00029Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00030'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00030')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00030',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='c84482a0-c888-eccf-1fcd-39e16e0a9fae' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'c84482a0-c888-eccf-1fcd-39e16e0a9fae',N'AutomationSeedCustomer00030' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00030'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'c84482a0-c888-eccf-1fcd-39e16e0a9fae')

	-- Location for Customer - 'AutomationSeedCustomer00030'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00030'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00030Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='35648228-821d-dbaa-b7f3-39e16e0cc868' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'35648228-821d-dbaa-b7f3-39e16e0cc868', @customerId, N'AutomationSeedCustomer00030Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00031'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00031')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00031',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='f4f892cc-42e0-04e0-0ecf-39e168f074dd' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'f4f892cc-42e0-04e0-0ecf-39e168f074dd',N'AutomationSeedCustomer00031' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00031'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'f4f892cc-42e0-04e0-0ecf-39e168f074dd')

	-- Location for Customer - 'AutomationSeedCustomer00031'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00031'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00031Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='184235b0-839d-94a0-6890-39e168f29c9d' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'184235b0-839d-94a0-6890-39e168f29c9d', @customerId, N'AutomationSeedCustomer00031Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00032'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00032')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00032',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='15fe6dd1-2def-0f45-2885-39e19cb504b1' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'15fe6dd1-2def-0f45-2885-39e19cb504b1',N'AutomationSeedCustomer00032' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00032'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'15fe6dd1-2def-0f45-2885-39e19cb504b1')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'15fe6dd1-2def-0f45-2885-39e19cb504b1')

	-- Location for Customer - 'AutomationSeedCustomer00032'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00032'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00032Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='6c021901-53d0-50c1-a959-39e19cb7352a' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'6c021901-53d0-50c1-a959-39e19cb7352a', @customerId, N'AutomationSeedCustomer00032Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00033'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00033')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00033',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='62e635ec-7e95-e579-8bc1-39e1ac2bd1f0' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0',N'AutomationSeedCustomer00033' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00033'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'62e635ec-7e95-e579-8bc1-39e1ac2bd1f0')

	-- Location for Customer - 'AutomationSeedCustomer00033'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00033'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00033Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='35a04a75-3a99-464a-3ff7-39e1ac2e0494' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'35a04a75-3a99-464a-3ff7-39e1ac2e0494', @customerId, N'AutomationSeedCustomer00033Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00034'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00034')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00034',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='896dee4b-4431-001f-69a7-39e17d82b5b6' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'896dee4b-4431-001f-69a7-39e17d82b5b6',N'AutomationSeedCustomer00034' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00034'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'896dee4b-4431-001f-69a7-39e17d82b5b6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'896dee4b-4431-001f-69a7-39e17d82b5b6')

	-- Location for Customer - 'AutomationSeedCustomer00034'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00034'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00034Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='b7c9e090-5545-d425-db1d-39e17d84dfdd' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'b7c9e090-5545-d425-db1d-39e17d84dfdd', @customerId, N'AutomationSeedCustomer00034Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00035'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00035')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00035',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='02e6822f-0938-de98-4fb6-39e17332d489' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'02e6822f-0938-de98-4fb6-39e17332d489',N'AutomationSeedCustomer00035' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00035'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'02e6822f-0938-de98-4fb6-39e17332d489')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'02e6822f-0938-de98-4fb6-39e17332d489')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'02e6822f-0938-de98-4fb6-39e17332d489')

	-- Location for Customer - 'AutomationSeedCustomer00035'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00035'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00035Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='b2321928-3dd3-30e6-b4f3-39e17334fff4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'b2321928-3dd3-30e6-b4f3-39e17334fff4', @customerId, N'AutomationSeedCustomer00035Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00036'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00036')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00036',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='08d93469-40ca-a87b-8ced-39e1f711d9e6' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'08d93469-40ca-a87b-8ced-39e1f711d9e6',N'AutomationSeedCustomer00036' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00036'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'08d93469-40ca-a87b-8ced-39e1f711d9e6')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'08d93469-40ca-a87b-8ced-39e1f711d9e6')

	-- Location for Customer - 'AutomationSeedCustomer00036'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00036'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00036Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='40247e30-5a25-6b74-c996-39e1f7141896' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'40247e30-5a25-6b74-c996-39e1f7141896', @customerId, N'AutomationSeedCustomer00036Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00037'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00037')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00037',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='198af2b1-561f-2227-eef6-39e1da6704ff' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'198af2b1-561f-2227-eef6-39e1da6704ff',N'AutomationSeedCustomer00037' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00037'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'198af2b1-561f-2227-eef6-39e1da6704ff')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'198af2b1-561f-2227-eef6-39e1da6704ff')

	-- Location for Customer - 'AutomationSeedCustomer00037'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00037'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00037Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='df7c6907-ae64-063e-99b6-39e1da6940b9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'df7c6907-ae64-063e-99b6-39e1da6940b9', @customerId, N'AutomationSeedCustomer00037Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00038'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00038')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00038',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='f03d9b29-077b-6f31-6574-39e1929b8251' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'f03d9b29-077b-6f31-6574-39e1929b8251',N'AutomationSeedCustomer00038' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00038'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'f03d9b29-077b-6f31-6574-39e1929b8251')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'f03d9b29-077b-6f31-6574-39e1929b8251')

	-- Location for Customer - 'AutomationSeedCustomer00038'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00038'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00038Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='f8432e69-4360-09db-4aca-39e1929db042' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'f8432e69-4360-09db-4aca-39e1929db042', @customerId, N'AutomationSeedCustomer00038Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00039'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00039')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00039',[Eula]=N'Sample EULA text',[Active]=1 WHERE [Id]='f1a42a5e-bdad-df08-a1d9-39e1d015ba5c' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c',N'AutomationSeedCustomer00039' ,N'Sample EULA text',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00039'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'f1a42a5e-bdad-df08-a1d9-39e1d015ba5c')

	-- Location for Customer - 'AutomationSeedCustomer00039'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00039'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00039Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='b16fbc9d-114c-2f5a-5655-39e1d018e060' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'b16fbc9d-114c-2f5a-5655-39e1d018e060', @customerId, N'AutomationSeedCustomer00039Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00040'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00040')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00040',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='2767ea04-032e-34ca-727c-39e2229dbf0a' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'2767ea04-032e-34ca-727c-39e2229dbf0a',N'AutomationSeedCustomer00040' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00040'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'2767ea04-032e-34ca-727c-39e2229dbf0a')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'2767ea04-032e-34ca-727c-39e2229dbf0a')

	-- Location for Customer - 'AutomationSeedCustomer00040'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00040'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00040Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='d6ee39f4-cb2a-c188-76c5-39e222a0005d' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'd6ee39f4-cb2a-c188-76c5-39e222a0005d', @customerId, N'AutomationSeedCustomer00040Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00041'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00041')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00041',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='8a24d383-4070-9f33-b6f9-39e1c5c2917e' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'8a24d383-4070-9f33-b6f9-39e1c5c2917e',N'AutomationSeedCustomer00041' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00041'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'8a24d383-4070-9f33-b6f9-39e1c5c2917e')

	-- Location for Customer - 'AutomationSeedCustomer00041'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00041'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00041Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='e8027e5f-8cc2-25a6-cdfc-39e1c5c4c7c6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'e8027e5f-8cc2-25a6-cdfc-39e1c5c4c7c6', @customerId, N'AutomationSeedCustomer00041Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00042'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00042')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00042',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='6e67a405-91f0-9100-a243-39e21723709e' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'6e67a405-91f0-9100-a243-39e21723709e',N'AutomationSeedCustomer00042' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00042'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'6e67a405-91f0-9100-a243-39e21723709e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'6e67a405-91f0-9100-a243-39e21723709e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'6e67a405-91f0-9100-a243-39e21723709e')

	-- Location for Customer - 'AutomationSeedCustomer00042'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00042'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00042Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='1e868919-4343-975f-7993-39e21725b46b' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'1e868919-4343-975f-7993-39e21725b46b', @customerId, N'AutomationSeedCustomer00042Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00043'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00043')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00043',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='9e0577d2-1c66-70c1-2c01-39e241aee5b5' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'9e0577d2-1c66-70c1-2c01-39e241aee5b5',N'AutomationSeedCustomer00043' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00043'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'9e0577d2-1c66-70c1-2c01-39e241aee5b5')

	-- Location for Customer - 'AutomationSeedCustomer00043'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00043'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00043Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='17ac7921-935a-2542-ff44-39e241b12f09' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'17ac7921-935a-2542-ff44-39e241b12f09', @customerId, N'AutomationSeedCustomer00043Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00044'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00044')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00044',[Eula]=N'Sample EULA text',[Active]=1 WHERE [Id]='17d4e89c-5856-81bc-cda5-39e227bbb0e0' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'17d4e89c-5856-81bc-cda5-39e227bbb0e0',N'AutomationSeedCustomer00044' ,N'Sample EULA text',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00044'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'17d4e89c-5856-81bc-cda5-39e227bbb0e0')

	-- Location for Customer - 'AutomationSeedCustomer00044'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00044'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00044Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='7ebad2fa-9ec4-fcd0-0ac4-39e227be87f6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'7ebad2fa-9ec4-fcd0-0ac4-39e227be87f6', @customerId, N'AutomationSeedCustomer00044Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00045'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00045')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00045',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='c2d97ab3-f87a-f69f-1d7c-39e227c323b2' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2',N'AutomationSeedCustomer00045' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00045'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'c2d97ab3-f87a-f69f-1d7c-39e227c323b2')

	-- Location for Customer - 'AutomationSeedCustomer00045'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00045'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00045Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='b06718d4-b40d-4f5a-9fd0-39e227c56786' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'b06718d4-b40d-4f5a-9fd0-39e227c56786', @customerId, N'AutomationSeedCustomer00045Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00046'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00046')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00046',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='937a94e1-f232-d7b6-0ab2-39e1544d426e' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'937a94e1-f232-d7b6-0ab2-39e1544d426e',N'AutomationSeedCustomer00046' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00046'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'937a94e1-f232-d7b6-0ab2-39e1544d426e')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'937a94e1-f232-d7b6-0ab2-39e1544d426e')

	-- Location for Customer - 'AutomationSeedCustomer00046'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00046'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00046Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='6f895503-3777-3f4d-82b6-39e1544f6ce1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'6f895503-3777-3f4d-82b6-39e1544f6ce1', @customerId, N'AutomationSeedCustomer00046Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00047'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00047')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00047',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='ccf1f061-1690-4438-134f-39e185186053' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'ccf1f061-1690-4438-134f-39e185186053',N'AutomationSeedCustomer00047' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00047'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'ccf1f061-1690-4438-134f-39e185186053')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'ccf1f061-1690-4438-134f-39e185186053')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'ccf1f061-1690-4438-134f-39e185186053')

	-- Location for Customer - 'AutomationSeedCustomer00047'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00047'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00047Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='75fdf2ed-4c5b-eda1-bda1-39e1851a8eb1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'75fdf2ed-4c5b-eda1-bda1-39e1851a8eb1', @customerId, N'AutomationSeedCustomer00047Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00048'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00048')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00048',[Eula]=N'regcus389384LicFeature: Testing eula string, TBD',[Active]=1 WHERE [Id]='5cf781a4-8f1c-faa7-8e86-39e18b07bc90' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90',N'AutomationSeedCustomer00048' ,N'regcus389384LicFeature: Testing eula string, TBD',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00048'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'5cf781a4-8f1c-faa7-8e86-39e18b07bc90')

	-- Location for Customer - 'AutomationSeedCustomer00048'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00048'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00048Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='d69344c6-3f15-943d-252a-39e18b0814ee' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'd69344c6-3f15-943d-252a-39e18b0814ee', @customerId, N'AutomationSeedCustomer00048Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00049'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00049')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00049',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='ee9e44f0-f185-5e7a-8706-39e1a700d496' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'ee9e44f0-f185-5e7a-8706-39e1a700d496',N'AutomationSeedCustomer00049' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00049'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'ee9e44f0-f185-5e7a-8706-39e1a700d496')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'ee9e44f0-f185-5e7a-8706-39e1a700d496')

	-- Location for Customer - 'AutomationSeedCustomer00049'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00049'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00049Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='0a7c49ca-34b0-2852-720a-39e1a7030575' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'0a7c49ca-34b0-2852-720a-39e1a7030575', @customerId, N'AutomationSeedCustomer00049Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00050'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00050')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00050',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='95343337-4f82-7504-24d8-39e163c8f399' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'95343337-4f82-7504-24d8-39e163c8f399',N'AutomationSeedCustomer00050' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00050'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'95343337-4f82-7504-24d8-39e163c8f399')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'95343337-4f82-7504-24d8-39e163c8f399')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'95343337-4f82-7504-24d8-39e163c8f399')

	-- Location for Customer - 'AutomationSeedCustomer00050'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00050'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00050Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='7f44355b-5a68-b024-bc09-39e163cb1b4a' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'7f44355b-5a68-b024-bc09-39e163cb1b4a', @customerId, N'AutomationSeedCustomer00050Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00051'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00051')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00051',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='ef5bc973-aa51-4ba4-997c-39e182adb262' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'ef5bc973-aa51-4ba4-997c-39e182adb262',N'AutomationSeedCustomer00051' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00051'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'ef5bc973-aa51-4ba4-997c-39e182adb262')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'ef5bc973-aa51-4ba4-997c-39e182adb262')

	-- Location for Customer - 'AutomationSeedCustomer00051'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00051'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00051Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='58b62dd1-b8dc-a418-ad70-39e182afdecf' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'58b62dd1-b8dc-a418-ad70-39e182afdecf', @customerId, N'AutomationSeedCustomer00051Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00052'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00052')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00052',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='91c4ea82-ed7e-0100-c4bc-39e1785eea93' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'91c4ea82-ed7e-0100-c4bc-39e1785eea93',N'AutomationSeedCustomer00052' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00052'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'91c4ea82-ed7e-0100-c4bc-39e1785eea93')

	-- Location for Customer - 'AutomationSeedCustomer00052'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00052'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00052Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='3da7fe2d-563d-5af9-745b-39e17861157d' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'3da7fe2d-563d-5af9-745b-39e17861157d', @customerId, N'AutomationSeedCustomer00052Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00053'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00053')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00053',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='72f4e425-fc81-9e4c-4db1-39e1a1dab153' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'72f4e425-fc81-9e4c-4db1-39e1a1dab153',N'AutomationSeedCustomer00053' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00053'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'72f4e425-fc81-9e4c-4db1-39e1a1dab153')

	-- Location for Customer - 'AutomationSeedCustomer00053'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00053'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00053Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='0dfdc7c4-c5f6-10d0-00af-39e1a1dce2c5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'0dfdc7c4-c5f6-10d0-00af-39e1a1dce2c5', @customerId, N'AutomationSeedCustomer00053Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00054'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00054')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00054',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='015e191a-d030-3a64-ca22-39e1e4d4bf88' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'015e191a-d030-3a64-ca22-39e1e4d4bf88',N'AutomationSeedCustomer00054' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00054'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'015e191a-d030-3a64-ca22-39e1e4d4bf88')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'015e191a-d030-3a64-ca22-39e1e4d4bf88')

	-- Location for Customer - 'AutomationSeedCustomer00054'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00054'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00054Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='2555f535-fc24-5b53-a33b-39e1e4d6fa4b' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'2555f535-fc24-5b53-a33b-39e1e4d6fa4b', @customerId, N'AutomationSeedCustomer00054Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00055'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00055')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00055',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='fa6bca33-5836-b680-04f8-39e22cea9846' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'fa6bca33-5836-b680-04f8-39e22cea9846',N'AutomationSeedCustomer00055' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00055'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'fa6bca33-5836-b680-04f8-39e22cea9846')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'fa6bca33-5836-b680-04f8-39e22cea9846')

	-- Location for Customer - 'AutomationSeedCustomer00055'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00055'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00055Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='1ef0fb36-bfcc-56ad-c660-39e22cecdb97' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'1ef0fb36-bfcc-56ad-c660-39e22cecdb97', @customerId, N'AutomationSeedCustomer00055Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00056'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00056')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00056',[Eula]=N'regcus523975LicFeature: Testing eula string, TBD',[Active]=1 WHERE [Id]='42c38c4f-c7fd-f177-d577-39e1896f2e57' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'42c38c4f-c7fd-f177-d577-39e1896f2e57',N'AutomationSeedCustomer00056' ,N'regcus523975LicFeature: Testing eula string, TBD',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00056'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'42c38c4f-c7fd-f177-d577-39e1896f2e57')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'42c38c4f-c7fd-f177-d577-39e1896f2e57')

	-- Location for Customer - 'AutomationSeedCustomer00056'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00056'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00056Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='8872a2cd-a721-2187-6488-39e1896f8852' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'8872a2cd-a721-2187-6488-39e1896f8852', @customerId, N'AutomationSeedCustomer00056Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00057'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00057')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00057',[Eula]=N'regcus152353LicFeature: Testing eula string, TBD',[Active]=1 WHERE [Id]='0191b2f3-023f-6c1a-62cc-39e18e6e2400' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'0191b2f3-023f-6c1a-62cc-39e18e6e2400',N'AutomationSeedCustomer00057' ,N'regcus152353LicFeature: Testing eula string, TBD',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00057'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'0191b2f3-023f-6c1a-62cc-39e18e6e2400')

	-- Location for Customer - 'AutomationSeedCustomer00057'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00057'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00057Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='9cb47d09-5c3e-64b6-4277-39e18e6e7d20' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'9cb47d09-5c3e-64b6-4277-39e18e6e7d20', @customerId, N'AutomationSeedCustomer00057Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00058'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00058')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00058',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='ef7e9d60-04d3-d267-3d7f-39e15986650f' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'ef7e9d60-04d3-d267-3d7f-39e15986650f',N'AutomationSeedCustomer00058' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00058'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'ef7e9d60-04d3-d267-3d7f-39e15986650f')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'ef7e9d60-04d3-d267-3d7f-39e15986650f')

	-- Location for Customer - 'AutomationSeedCustomer00058'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00058'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00058Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='38dff272-a7a4-17f0-9b6a-39e159888c32' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'38dff272-a7a4-17f0-9b6a-39e159888c32', @customerId, N'AutomationSeedCustomer00058Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00059'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00059')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00059',[Eula]=N'Accept the agreement',[Active]=1 WHERE [Id]='4551fac5-cafe-c117-8a73-39e1bb7ad27d' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'4551fac5-cafe-c117-8a73-39e1bb7ad27d',N'AutomationSeedCustomer00059' ,N'Accept the agreement',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00059'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'4551fac5-cafe-c117-8a73-39e1bb7ad27d')

	-- Location for Customer - 'AutomationSeedCustomer00059'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00059'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00059Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='e3931c9f-4967-54b1-c72d-39e1bb7d07a9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'e3931c9f-4967-54b1-c72d-39e1bb7d07a9', @customerId, N'AutomationSeedCustomer00059Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00060'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00060')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00060',[Eula]=N'regcus341845LicFeature: Testing eula string, TBD',[Active]=1 WHERE [Id]='29afd9a9-f164-4971-5037-39e189a5df5b' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'29afd9a9-f164-4971-5037-39e189a5df5b',N'AutomationSeedCustomer00060' ,N'regcus341845LicFeature: Testing eula string, TBD',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00060'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'29afd9a9-f164-4971-5037-39e189a5df5b')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'29afd9a9-f164-4971-5037-39e189a5df5b')

	-- Location for Customer - 'AutomationSeedCustomer00060'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00060'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00060Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='35c6b70a-aedb-6d48-cdf0-39e189a63a40' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'35c6b70a-aedb-6d48-cdf0-39e189a63a40', @customerId, N'AutomationSeedCustomer00060Loc','37.4020925705503','-121.984820397399')

	-- Customer - 'AutomationSeedCustomer00061'
	IF NOT EXISTS (SELECT * FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00061')
	BEGIN
		UPDATE [dbo].[Customer] SET [Name]=N'AutomationSeedCustomer00061',[Eula]=N'regcus115397CusWithoutAsset: Testing eula string, TBD',[Active]=1 WHERE [Id]='d2b9c144-76d2-5205-e798-39e1655fc294' 
		IF @@ROWCOUNT=0
			INSERT INTO [dbo].[Customer]([Id],[Name],[Eula],[Active]) VALUES (N'd2b9c144-76d2-5205-e798-39e1655fc294',N'AutomationSeedCustomer00061' ,N'regcus115397CusWithoutAsset: Testing eula string, TBD',1)
	END

	-- License for Customer - 'AutomationSeedCustomer00061'
	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'e86cbf64-f9f2-47bd-80a8-008a233ce7d7', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46fb8592-4477-4ee1-ab49-04a991036785' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46fb8592-4477-4ee1-ab49-04a991036785', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387d1519-64da-4abd-b947-1bcd72bd6caa' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387d1519-64da-4abd-b947-1bcd72bd6caa', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3eb7107c-ee5e-467c-92ce-222a83bcb7cf', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664fdf-c940-45ab-b37c-34abd5ebf0a1' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664fdf-c940-45ab-b37c-34abd5ebf0a1', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55cd10c5-80db-004c-f0d6-39d4d9124478' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55cd10c5-80db-004c-f0d6-39d4d9124478', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900b5-e0c6-4b79-8f94-3ea7f00d879f' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900b5-e0c6-4b79-8f94-3ea7f00d879f', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5f93bb71-4f95-4e26-abd3-500b68838d7b' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5f93bb71-4f95-4e26-abd3-500b68838d7b', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5edf6c34-5769-43d1-afe9-5e9223a7f48f' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5edf6c34-5769-43d1-afe9-5e9223a7f48f', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'b5d075a8-94ee-4d28-afc4-69283d124a53' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'b5d075a8-94ee-4d28-afc4-69283d124a53', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05d91eb6-90b2-440c-b887-69ac44a071ed' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05d91eb6-90b2-440c-b887-69ac44a071ed', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0f726186-19ee-4703-9c77-6ec6155ab255' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0f726186-19ee-4703-9c77-6ec6155ab255', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a104c92b-38b6-4c1a-aa25-70b3b8308cb7', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'117aeb6d-5a15-4170-ab85-9978ec68a017' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'117aeb6d-5a15-4170-ab85-9978ec68a017', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765f2dbb-b6f2-4d53-a6d3-9a071e54091e', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405b107-f234-43a6-bac4-a7f0b8ecd7ac', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944b3ef5-3f25-4358-82ad-ae632ea3f4c9', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'a42cfe59-405b-445b-ae63-af74c5d9cbba' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'a42cfe59-405b-445b-ae63-af74c5d9cbba', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'cfd1ead2-9386-4b81-95c7-b3bf1c8252fb', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105e725-4913-477a-b58d-cbd9d83e7c70' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105e725-4913-477a-b58d-cbd9d83e7c70', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'f5e43f3d-9f69-430d-9013-e902a34a1d18' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'f5e43f3d-9f69-430d-9013-e902a34a1d18', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8cef7673-80d2-407a-b833-f164c472cfda' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8cef7673-80d2-407a-b833-f164c472cfda', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb' AND [CustomerId]=N'd2b9c144-76d2-5205-e798-39e1655fc294')
		INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6a412f8c-e97f-4c88-b4d4-f2bcecbeabbb', N'd2b9c144-76d2-5205-e798-39e1655fc294')

	-- Location for Customer - 'AutomationSeedCustomer00061'
	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00061'
	UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutomationSeedCustomer00061Loc',[Latitude]='37.4020925705503',[Longitude]='-121.984820397399' WHERE [Id]='1fa42e13-7d53-5647-217b-39e1656019e4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'1fa42e13-7d53-5647-217b-39e1656019e4', @customerId, N'AutomationSeedCustomer00061Loc','37.4020925705503','-121.984820397399')

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

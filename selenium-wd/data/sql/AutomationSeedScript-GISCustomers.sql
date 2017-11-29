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

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00001' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00001'

	-- CustomerBoundaryType - [AutomationSeedCustomer00001]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8985BD1B-1F97-4BC7-A46C-C497D27F0174'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8985BD1B-1F97-4BC7-A46C-C497D27F0174', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'DD6FD5CB-00EA-4BCD-A95C-1C8456CA47DE'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'DD6FD5CB-00EA-4BCD-A95C-1C8456CA47DE', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00001]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5EE4DDA2-5A18-4420-A692-70179FB11596' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5EE4DDA2-5A18-4420-A692-70179FB11596', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BB6E64F1-3710-466D-A701-F37DE2DA4DC2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BB6E64F1-3710-466D-A701-F37DE2DA4DC2', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0E2B32DB-D8D8-4101-B18B-B1A7E1735BE3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0E2B32DB-D8D8-4101-B18B-B1A7E1735BE3', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3A274B61-D4B9-429C-A722-BA2F00A1A5FB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3A274B61-D4B9-429C-A722-BA2F00A1A5FB', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6CE8AF08-1CA8-4F01-804B-842AF8FE5501' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6CE8AF08-1CA8-4F01-804B-842AF8FE5501', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0124236B-52F6-4208-B3E6-8B28BA2F8C79' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0124236B-52F6-4208-B3E6-8B28BA2F8C79', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00002' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00002'

	-- CustomerBoundaryType - [AutomationSeedCustomer00002]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'238CEB6F-6EF9-43B3-A817-A1E211C0F432'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'238CEB6F-6EF9-43B3-A817-A1E211C0F432', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8F5F341D-7BEE-4AE9-A0B0-F18CC75392B2'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8F5F341D-7BEE-4AE9-A0B0-F18CC75392B2', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00002]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C4A60540-9657-4A17-BD5C-3E0440DE27E6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C4A60540-9657-4A17-BD5C-3E0440DE27E6', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0DEBEB36-5600-478F-9AB0-9D1690D5EC5B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0DEBEB36-5600-478F-9AB0-9D1690D5EC5B', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='00E6DBB3-C63A-4DC4-BBC0-C0704BB420D2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'00E6DBB3-C63A-4DC4-BBC0-C0704BB420D2', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C315F8C1-9327-441E-AB19-16A5C4AA8615' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C315F8C1-9327-441E-AB19-16A5C4AA8615', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B0DB9AD2-2652-429B-A078-A06367C440D3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B0DB9AD2-2652-429B-A078-A06367C440D3', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2EA9CE39-CEE9-4657-840A-43D1B9F36184' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2EA9CE39-CEE9-4657-840A-43D1B9F36184', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00003' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00003'

	-- CustomerBoundaryType - [AutomationSeedCustomer00003]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BFB697FB-EF7A-42D7-AD20-EA4BECEC86BE'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BFB697FB-EF7A-42D7-AD20-EA4BECEC86BE', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A9D86534-028D-47BC-9B8F-B9FFE95C2A2A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A9D86534-028D-47BC-9B8F-B9FFE95C2A2A', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00003]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1FF3DD0D-CFDD-4135-B1EE-7F193ED1797D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1FF3DD0D-CFDD-4135-B1EE-7F193ED1797D', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6F1EECD3-FBB8-4502-B9F8-7F4D236AA5FF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6F1EECD3-FBB8-4502-B9F8-7F4D236AA5FF', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9635B9EA-2431-4233-BE49-9F4601C74AD8' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9635B9EA-2431-4233-BE49-9F4601C74AD8', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5C9D37C2-4925-4E42-B629-F5E280396059' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5C9D37C2-4925-4E42-B629-F5E280396059', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8158C7F6-5E56-4002-B946-BDCCDE24985E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8158C7F6-5E56-4002-B946-BDCCDE24985E', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0E7D22DC-B41E-4AB9-88AC-DC79D03D5F9E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0E7D22DC-B41E-4AB9-88AC-DC79D03D5F9E', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00004' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00004'

	-- CustomerBoundaryType - [AutomationSeedCustomer00004]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9931E377-7D06-4351-89DD-E0E450339A41'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9931E377-7D06-4351-89DD-E0E450339A41', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'42F5088E-406A-493B-A81F-7BD4552D7B37'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'42F5088E-406A-493B-A81F-7BD4552D7B37', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00004]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='20314664-073D-4714-AFA6-EA0DB869DA1C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'20314664-073D-4714-AFA6-EA0DB869DA1C', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='90D9F4E5-E597-4217-B92C-3CBA6CF7488A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'90D9F4E5-E597-4217-B92C-3CBA6CF7488A', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4D512710-D8A8-4D63-9CDE-874EEB302250' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4D512710-D8A8-4D63-9CDE-874EEB302250', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A059306E-2FBC-4E5B-9383-F5D0443D7F39' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A059306E-2FBC-4E5B-9383-F5D0443D7F39', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='76E3C744-9205-4E2D-9E4B-6749CD56E26B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'76E3C744-9205-4E2D-9E4B-6749CD56E26B', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='714C1982-50FE-4EBC-9C70-1BD54E05885C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'714C1982-50FE-4EBC-9C70-1BD54E05885C', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00005' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00005'

	-- CustomerBoundaryType - [AutomationSeedCustomer00005]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'1D588416-35EB-45CE-B6D9-6D55FB6E6CC8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'1D588416-35EB-45CE-B6D9-6D55FB6E6CC8', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8E18B1E1-9C56-4885-B907-E96A7B86CDA6'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8E18B1E1-9C56-4885-B907-E96A7B86CDA6', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00005]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='ED218592-19DA-4938-A477-B9C73F48DB86' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'ED218592-19DA-4938-A477-B9C73F48DB86', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5FAB0C71-9384-4EEF-826F-F0A90BE386A0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5FAB0C71-9384-4EEF-826F-F0A90BE386A0', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='74AED3EB-C1E7-4F79-AC9C-D6F484C9BDAD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'74AED3EB-C1E7-4F79-AC9C-D6F484C9BDAD', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4F22C127-65B1-4355-B7DD-BF565A8ACA84' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4F22C127-65B1-4355-B7DD-BF565A8ACA84', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8D72C18B-81B2-408F-8160-7D0CBBF8B1E4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8D72C18B-81B2-408F-8160-7D0CBBF8B1E4', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A78D103B-6045-4BFF-9177-8446FA7B7F81' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A78D103B-6045-4BFF-9177-8446FA7B7F81', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00006' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00006'

	-- CustomerBoundaryType - [AutomationSeedCustomer00006]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'5A64F1F8-6E54-4A57-9224-370E848E26D3'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'5A64F1F8-6E54-4A57-9224-370E848E26D3', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'6C2469CA-2A89-4548-AEDC-CE009B966746'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'6C2469CA-2A89-4548-AEDC-CE009B966746', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00006]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='831DA1C4-1CAC-48AA-8925-8F1C11814173' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'831DA1C4-1CAC-48AA-8925-8F1C11814173', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2640EF72-F141-422B-A24B-987379F1D85B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2640EF72-F141-422B-A24B-987379F1D85B', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='657E4816-F856-42F5-ABB8-891AF03C0615' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'657E4816-F856-42F5-ABB8-891AF03C0615', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B04FE164-7A8A-481C-A6F5-DF7F9F94D4AD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B04FE164-7A8A-481C-A6F5-DF7F9F94D4AD', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AAA1BA8D-693E-4A81-96CF-A8002C807332' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AAA1BA8D-693E-4A81-96CF-A8002C807332', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E1597794-EFB4-4DE0-93C0-D58B59E188A7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E1597794-EFB4-4DE0-93C0-D58B59E188A7', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00007' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00007'

	-- CustomerBoundaryType - [AutomationSeedCustomer00007]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9F43B836-5F2A-4811-9BAB-32DBBD1CA521'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9F43B836-5F2A-4811-9BAB-32DBBD1CA521', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E4D75D7A-B2BC-46BB-B2FE-24635BCE90EB'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E4D75D7A-B2BC-46BB-B2FE-24635BCE90EB', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00007]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A5F3CA5C-1C55-4573-99E8-FE2B4D7651AB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A5F3CA5C-1C55-4573-99E8-FE2B4D7651AB', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='01480662-C31B-4C9B-AA71-96947365BD8C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'01480662-C31B-4C9B-AA71-96947365BD8C', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='92C67EF0-830E-4DC0-8519-266744AB5B10' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'92C67EF0-830E-4DC0-8519-266744AB5B10', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='69467702-2237-4849-9694-1CE6E22ADB30' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'69467702-2237-4849-9694-1CE6E22ADB30', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='288B9C6A-197B-41C3-994A-5F4C72DD7F59' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'288B9C6A-197B-41C3-994A-5F4C72DD7F59', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='28C66F7C-BE52-4C67-AC99-9F18AD170818' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'28C66F7C-BE52-4C67-AC99-9F18AD170818', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00008' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00008'

	-- CustomerBoundaryType - [AutomationSeedCustomer00008]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'D4CDC25B-68C1-4FB7-9735-C53068CFAB5A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'D4CDC25B-68C1-4FB7-9735-C53068CFAB5A', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F896010C-380A-44F1-A248-661D325160AD'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F896010C-380A-44F1-A248-661D325160AD', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00008]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='691387BF-7C7B-4FE0-B7EA-BAFCB2B4E839' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'691387BF-7C7B-4FE0-B7EA-BAFCB2B4E839', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AD5F7C8A-5AA2-4E4D-96DE-8DC009EC781B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AD5F7C8A-5AA2-4E4D-96DE-8DC009EC781B', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='57DD9254-753B-4FBC-9B4D-92809FC7C6F7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'57DD9254-753B-4FBC-9B4D-92809FC7C6F7', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A2102192-81B6-4391-9E11-0F437B4AD21C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A2102192-81B6-4391-9E11-0F437B4AD21C', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B1EFFBF1-0BF4-487F-81E2-39F7C938E6EE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B1EFFBF1-0BF4-487F-81E2-39F7C938E6EE', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BFE2D7C1-4CE7-4F2A-9164-B80A3471121C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BFE2D7C1-4CE7-4F2A-9164-B80A3471121C', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00009' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00009'

	-- CustomerBoundaryType - [AutomationSeedCustomer00009]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4276CADE-0239-4007-925F-3187BB6F8E52'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4276CADE-0239-4007-925F-3187BB6F8E52', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F9320387-A73F-450F-BBC8-6917FD7642F2'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F9320387-A73F-450F-BBC8-6917FD7642F2', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00009]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B57BFFC3-9CA0-4657-9000-29CCC8D07B88' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B57BFFC3-9CA0-4657-9000-29CCC8D07B88', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0B510ACB-BFB2-4AD4-A8FA-C5DDD3548B58' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0B510ACB-BFB2-4AD4-A8FA-C5DDD3548B58', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C8431035-C2F7-4521-BBE8-9840807B56F9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C8431035-C2F7-4521-BBE8-9840807B56F9', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CD2FB20C-1D9A-4309-AB62-4D0DE99D5553' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CD2FB20C-1D9A-4309-AB62-4D0DE99D5553', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='65492957-31D0-4FF9-9F08-2C9F88526915' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'65492957-31D0-4FF9-9F08-2C9F88526915', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='502CCF76-365A-4568-9798-0BF7414D09D5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'502CCF76-365A-4568-9798-0BF7414D09D5', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00010' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00010'

	-- CustomerBoundaryType - [AutomationSeedCustomer00010]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'2DE9C407-22F4-4133-B46F-B21D5FE37066'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'2DE9C407-22F4-4133-B46F-B21D5FE37066', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'DBFDDEF0-DF34-4713-91BA-4520DB81C292'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'DBFDDEF0-DF34-4713-91BA-4520DB81C292', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00010]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BB456172-B77E-48BB-BB0C-891C9E7D8259' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BB456172-B77E-48BB-BB0C-891C9E7D8259', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8F3541C5-7A3D-4109-95FE-63B0B637DEA3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8F3541C5-7A3D-4109-95FE-63B0B637DEA3', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B3BE8944-8EAF-4DA8-B459-8CCA2D1DC134' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B3BE8944-8EAF-4DA8-B459-8CCA2D1DC134', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BC999AF3-8D50-41DE-B4B4-3ADC06C48389' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BC999AF3-8D50-41DE-B4B4-3ADC06C48389', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BC5E97EE-28E8-4627-84D0-5E0583C8AA10' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BC5E97EE-28E8-4627-84D0-5E0583C8AA10', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='45FAE974-DFF5-4F7A-B0B1-A9FC907BCD08' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'45FAE974-DFF5-4F7A-B0B1-A9FC907BCD08', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00011' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00011'

	-- CustomerBoundaryType - [AutomationSeedCustomer00011]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'C3A6182D-B5EA-451F-A16F-CFB0EA5BF378'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'C3A6182D-B5EA-451F-A16F-CFB0EA5BF378', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9AEC69BC-3EEC-410A-A65E-CD0AC3CD699A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9AEC69BC-3EEC-410A-A65E-CD0AC3CD699A', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00011]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3055B93D-14F7-4DD3-8CC0-AA0DD373944A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3055B93D-14F7-4DD3-8CC0-AA0DD373944A', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5FD126DA-C361-452A-94D6-A68E6C6BBCD2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5FD126DA-C361-452A-94D6-A68E6C6BBCD2', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='19DB4D44-1BC9-4F30-B25B-0D64B89737AD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'19DB4D44-1BC9-4F30-B25B-0D64B89737AD', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='86241CCA-A190-4A6D-8024-82B2445FB51C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'86241CCA-A190-4A6D-8024-82B2445FB51C', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E6554B40-BF2B-48CC-8B68-2E8CD0BC1408' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E6554B40-BF2B-48CC-8B68-2E8CD0BC1408', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FDD45DEF-7E58-442D-9E9E-E9431934AD24' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FDD45DEF-7E58-442D-9E9E-E9431934AD24', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00012' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00012'

	-- CustomerBoundaryType - [AutomationSeedCustomer00012]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'FF9573A0-812B-4FB5-892F-681075EB21DC'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'FF9573A0-812B-4FB5-892F-681075EB21DC', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'C86106FF-F6E3-4F84-979A-A998817DD307'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'C86106FF-F6E3-4F84-979A-A998817DD307', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00012]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='621D598D-2EAE-428F-BB51-1885763B02DE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'621D598D-2EAE-428F-BB51-1885763B02DE', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B09B82FD-CC27-4B04-B609-AB068B74F537' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B09B82FD-CC27-4B04-B609-AB068B74F537', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='81454E24-FE28-41EA-A248-2D4E89D77A61' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'81454E24-FE28-41EA-A248-2D4E89D77A61', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='877FFF79-8344-447D-9ECE-B19887B25E20' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'877FFF79-8344-447D-9ECE-B19887B25E20', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4DB003CD-731F-40FC-B799-CF65D2A18528' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4DB003CD-731F-40FC-B799-CF65D2A18528', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='68AE0A8D-FA53-4D4D-B365-2A7493F759DB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'68AE0A8D-FA53-4D4D-B365-2A7493F759DB', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00013' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00013'

	-- CustomerBoundaryType - [AutomationSeedCustomer00013]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'80CFA48A-1B30-4113-A42D-902AFD9C5C9E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'80CFA48A-1B30-4113-A42D-902AFD9C5C9E', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9568325E-62C9-4110-800F-19590F8047CA'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9568325E-62C9-4110-800F-19590F8047CA', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00013]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='37E683F5-8C58-4189-BD81-09BD15FE07EC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'37E683F5-8C58-4189-BD81-09BD15FE07EC', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7B6E3162-AD3E-488D-8595-9A326539C679' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7B6E3162-AD3E-488D-8595-9A326539C679', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4F8C78C1-81FE-4831-871D-79498232442C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4F8C78C1-81FE-4831-871D-79498232442C', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='187383D5-3296-4172-B97E-106BAF228D35' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'187383D5-3296-4172-B97E-106BAF228D35', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2648B118-0EE3-4833-A361-A4775CC232C6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2648B118-0EE3-4833-A361-A4775CC232C6', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FEAFC980-7BBA-4969-AF55-7F4A6297E6AD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FEAFC980-7BBA-4969-AF55-7F4A6297E6AD', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00014' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00014'

	-- CustomerBoundaryType - [AutomationSeedCustomer00014]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'1DE034FD-1204-472B-A366-1FEE29F38557'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'1DE034FD-1204-472B-A366-1FEE29F38557', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B7457BC5-1244-4B7E-AD22-7F266F283C6C'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B7457BC5-1244-4B7E-AD22-7F266F283C6C', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00014]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='68F18F03-69E6-40E7-A804-0D29D69D773C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'68F18F03-69E6-40E7-A804-0D29D69D773C', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E8B220CD-DC9A-451E-9928-1B202DC23287' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E8B220CD-DC9A-451E-9928-1B202DC23287', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EEF61634-AED1-490A-B520-BF96FEE0E856' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EEF61634-AED1-490A-B520-BF96FEE0E856', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5B3CEF9D-40A2-4688-BD87-57B230DA8F4D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5B3CEF9D-40A2-4688-BD87-57B230DA8F4D', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='408A1AD5-8A53-494B-8CD3-C603F74D3807' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'408A1AD5-8A53-494B-8CD3-C603F74D3807', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E6AEEC56-EE5D-4E83-A3DA-D045BD98DF5F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E6AEEC56-EE5D-4E83-A3DA-D045BD98DF5F', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00015' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00015'

	-- CustomerBoundaryType - [AutomationSeedCustomer00015]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'115E2793-B649-448C-A502-08880DB31CB8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'115E2793-B649-448C-A502-08880DB31CB8', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8C8C70A7-07D2-44D0-86B0-972B6F7E304A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8C8C70A7-07D2-44D0-86B0-972B6F7E304A', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00015]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='53914DA5-E6B4-4E6B-AA77-D957964A9656' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'53914DA5-E6B4-4E6B-AA77-D957964A9656', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2119606C-133C-4720-9913-0F5BBD3E52B7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2119606C-133C-4720-9913-0F5BBD3E52B7', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3BF2EFCE-05A8-4F31-B5BF-E778ABA22699' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3BF2EFCE-05A8-4F31-B5BF-E778ABA22699', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D131454D-9E9F-4EAB-ADB6-2E1447A387B2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D131454D-9E9F-4EAB-ADB6-2E1447A387B2', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1CD20E5D-D684-41B5-B51B-094A4EF5855D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1CD20E5D-D684-41B5-B51B-094A4EF5855D', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3E10FD30-DE09-4E8D-A8CD-5105119AF841' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3E10FD30-DE09-4E8D-A8CD-5105119AF841', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00016' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00016'

	-- CustomerBoundaryType - [AutomationSeedCustomer00016]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BE29FD7A-1027-4279-9ED2-0A080A2AA4B2'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BE29FD7A-1027-4279-9ED2-0A080A2AA4B2', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'EC5566E4-AB1E-4D45-A0FE-7796E52EAD5A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'EC5566E4-AB1E-4D45-A0FE-7796E52EAD5A', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00016]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='24521D3F-38F4-44B1-A90B-85BA1F51E845' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'24521D3F-38F4-44B1-A90B-85BA1F51E845', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='49027A68-4A2B-4350-9E42-251AF84FB5D6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'49027A68-4A2B-4350-9E42-251AF84FB5D6', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8939EA76-B999-47CA-8814-0096C1DA5D4B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8939EA76-B999-47CA-8814-0096C1DA5D4B', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B6B64013-7BB4-49F8-84E0-6686880D5FCB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B6B64013-7BB4-49F8-84E0-6686880D5FCB', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9310B991-D18F-4813-AF94-552C21982DC8' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9310B991-D18F-4813-AF94-552C21982DC8', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='084FB1C0-F1F6-41EB-963B-54C1604EB47B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'084FB1C0-F1F6-41EB-963B-54C1604EB47B', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00017' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00017'

	-- CustomerBoundaryType - [AutomationSeedCustomer00017]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'721298BC-DBF0-495F-860A-B1B56E1BE3DB'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'721298BC-DBF0-495F-860A-B1B56E1BE3DB', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BC96B504-7377-4E3E-88DD-4A1E406F9043'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BC96B504-7377-4E3E-88DD-4A1E406F9043', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00017]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CA6E3A73-100D-46F1-9D41-3CD894B40315' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CA6E3A73-100D-46F1-9D41-3CD894B40315', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='14A54014-C36F-4CFB-A4FE-43A653C2D383' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'14A54014-C36F-4CFB-A4FE-43A653C2D383', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4212CE58-9199-43C8-B1EB-2F222E1AC1A9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4212CE58-9199-43C8-B1EB-2F222E1AC1A9', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D480538F-5BED-4291-9030-2926A418F815' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D480538F-5BED-4291-9030-2926A418F815', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='40E2CF62-68FF-449A-BE25-D1C73DE0E762' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'40E2CF62-68FF-449A-BE25-D1C73DE0E762', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BDB622B4-69DC-477C-BA74-98ABC5D59E0B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BDB622B4-69DC-477C-BA74-98ABC5D59E0B', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00018' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00018'

	-- CustomerBoundaryType - [AutomationSeedCustomer00018]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E8F1BDF2-2E9B-4C2C-820A-5F767186A6A1'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E8F1BDF2-2E9B-4C2C-820A-5F767186A6A1', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'AE8529B4-1C12-4989-AE44-DD67F5363C4D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'AE8529B4-1C12-4989-AE44-DD67F5363C4D', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00018]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6CBC4A37-0FC6-4465-B54A-8574C23D635B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6CBC4A37-0FC6-4465-B54A-8574C23D635B', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7FB3477B-03A6-46EC-9A9C-D0B7E35235F8' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7FB3477B-03A6-46EC-9A9C-D0B7E35235F8', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C7BDE90E-BC62-4230-BE64-20DC27D892B6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C7BDE90E-BC62-4230-BE64-20DC27D892B6', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B0E5D966-6550-4424-A204-BEBB017D33AA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B0E5D966-6550-4424-A204-BEBB017D33AA', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FE83F805-A8AC-4C8E-B41D-8F47F9A921A5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FE83F805-A8AC-4C8E-B41D-8F47F9A921A5', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='787509E9-E0C7-4D32-8B0F-9B50318A9EB1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'787509E9-E0C7-4D32-8B0F-9B50318A9EB1', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00019' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00019'

	-- CustomerBoundaryType - [AutomationSeedCustomer00019]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'EC90DC71-FB66-4360-8701-F962572B6367'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'EC90DC71-FB66-4360-8701-F962572B6367', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4413E625-6A89-420A-9DBB-4C99ED9771B7'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4413E625-6A89-420A-9DBB-4C99ED9771B7', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00019]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='98B31E3F-4E58-45AC-B0EB-874A7AA49D1B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'98B31E3F-4E58-45AC-B0EB-874A7AA49D1B', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8B96F41C-69BA-487F-842C-37FE807C35F1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8B96F41C-69BA-487F-842C-37FE807C35F1', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='77072B26-D735-44A1-9AE4-FD4DE85844C7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'77072B26-D735-44A1-9AE4-FD4DE85844C7', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BF056B00-8BF4-427A-ADCB-1CC9CCFB1850' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BF056B00-8BF4-427A-ADCB-1CC9CCFB1850', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='60898FC0-B806-482A-B2AB-4E4053DC4312' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'60898FC0-B806-482A-B2AB-4E4053DC4312', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='44B9BE96-4281-4CA1-BF32-282D1ABE72F7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'44B9BE96-4281-4CA1-BF32-282D1ABE72F7', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00020' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00020'

	-- CustomerBoundaryType - [AutomationSeedCustomer00020]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B92CB5EF-6FFC-42C0-A2C6-7895296AC613'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B92CB5EF-6FFC-42C0-A2C6-7895296AC613', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4839580B-A430-4CE5-82DF-220CD88BB819'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4839580B-A430-4CE5-82DF-220CD88BB819', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00020]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5E6EFBE3-5F7D-407B-8C7A-9A411F68D717' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5E6EFBE3-5F7D-407B-8C7A-9A411F68D717', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='826B97DE-0640-46E7-B9F8-CA26BC2533BC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'826B97DE-0640-46E7-B9F8-CA26BC2533BC', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9228EF99-C161-490E-AE9D-2DA96E709037' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9228EF99-C161-490E-AE9D-2DA96E709037', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DFCFCAB0-4788-4EE1-BE43-60D4D5DB9F18' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DFCFCAB0-4788-4EE1-BE43-60D4D5DB9F18', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='51F6BA66-5641-4B8A-BECF-4E1706D3148E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'51F6BA66-5641-4B8A-BECF-4E1706D3148E', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D35F5B01-D2DE-4910-8453-348DC9322979' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D35F5B01-D2DE-4910-8453-348DC9322979', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00021' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00021'

	-- CustomerBoundaryType - [AutomationSeedCustomer00021]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'740EC0A1-03D2-4312-8632-D22F88C1743E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'740EC0A1-03D2-4312-8632-D22F88C1743E', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'CE2F0F7D-FB2D-4C21-AE62-771147349987'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'CE2F0F7D-FB2D-4C21-AE62-771147349987', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00021]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='39273932-5A0A-4104-94F4-F0310A7930B4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'39273932-5A0A-4104-94F4-F0310A7930B4', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EEF8DA65-130C-4E44-9FA4-8842D72407CF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EEF8DA65-130C-4E44-9FA4-8842D72407CF', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3D1AA62D-8898-44E8-9034-9645DCC6C5E9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3D1AA62D-8898-44E8-9034-9645DCC6C5E9', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2FD2F53C-A225-4133-97FB-E1EC10E5335F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2FD2F53C-A225-4133-97FB-E1EC10E5335F', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AC8B5719-EE84-44B3-8428-50B81FA7187E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AC8B5719-EE84-44B3-8428-50B81FA7187E', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='23AD6D35-91E4-4A84-ADBB-43B171404CD9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'23AD6D35-91E4-4A84-ADBB-43B171404CD9', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00022' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00022'

	-- CustomerBoundaryType - [AutomationSeedCustomer00022]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'5CBFE62A-150F-4966-BD12-67C5A2699872'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'5CBFE62A-150F-4966-BD12-67C5A2699872', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'65273B02-978B-458F-8FF4-B846B77272DF'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'65273B02-978B-458F-8FF4-B846B77272DF', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00022]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E7D37A49-2240-40F7-9E0C-7E71A232828A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E7D37A49-2240-40F7-9E0C-7E71A232828A', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='26833075-63EB-4CEB-B64A-493326A10C36' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'26833075-63EB-4CEB-B64A-493326A10C36', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D3472BAD-3B62-44BC-8916-7B2B04A8ADB1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D3472BAD-3B62-44BC-8916-7B2B04A8ADB1', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F8B41249-FF09-4533-8862-B844D0226F53' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F8B41249-FF09-4533-8862-B844D0226F53', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F69F85BD-2047-4B44-9472-9F479B16D8DE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F69F85BD-2047-4B44-9472-9F479B16D8DE', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AC0FDE88-4BC6-46E5-9049-6B7DFC09DEDD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AC0FDE88-4BC6-46E5-9049-6B7DFC09DEDD', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00023' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00023'

	-- CustomerBoundaryType - [AutomationSeedCustomer00023]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A63F6121-4A5A-4BF5-AE13-7CA5F7BAA79A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A63F6121-4A5A-4BF5-AE13-7CA5F7BAA79A', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'FBBC9083-3396-4685-A6A8-2E4FEF453121'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'FBBC9083-3396-4685-A6A8-2E4FEF453121', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00023]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='290AB615-9DC2-4493-8584-EEA8EBE55BFF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'290AB615-9DC2-4493-8584-EEA8EBE55BFF', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='52139EEA-8FB2-46D6-8EC8-83BF50F037C7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'52139EEA-8FB2-46D6-8EC8-83BF50F037C7', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C4903DCB-9365-42F1-9910-EC326820DDED' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C4903DCB-9365-42F1-9910-EC326820DDED', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8B8F407E-A673-4C79-92EE-3A17DE468371' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8B8F407E-A673-4C79-92EE-3A17DE468371', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DBA12125-8AC0-432D-8F23-41904675CAD0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DBA12125-8AC0-432D-8F23-41904675CAD0', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8903F1FA-DF1F-4451-A8D8-B7153A0A20EC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8903F1FA-DF1F-4451-A8D8-B7153A0A20EC', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00024' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00024'

	-- CustomerBoundaryType - [AutomationSeedCustomer00024]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'EAA1D9A0-FC6C-4235-BA18-313584707923'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'EAA1D9A0-FC6C-4235-BA18-313584707923', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A914FB16-73EB-4937-9C0D-C33A2F7ABB40'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A914FB16-73EB-4937-9C0D-C33A2F7ABB40', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00024]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8175C689-026B-4632-993A-F84B2F89656A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8175C689-026B-4632-993A-F84B2F89656A', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5A4F9376-ECA4-42A4-A536-C021814F6BE5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5A4F9376-ECA4-42A4-A536-C021814F6BE5', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6B86990D-364C-402F-913F-904AA89D49B6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6B86990D-364C-402F-913F-904AA89D49B6', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CBEC9D66-DF4E-4F9E-A07E-B2A7FAB20619' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CBEC9D66-DF4E-4F9E-A07E-B2A7FAB20619', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='561AFE3D-3DE4-4DF7-8F45-2880DA8AC015' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'561AFE3D-3DE4-4DF7-8F45-2880DA8AC015', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8FF73B60-DCA2-441F-A4C5-48E5D71484E9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8FF73B60-DCA2-441F-A4C5-48E5D71484E9', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00025' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00025'

	-- CustomerBoundaryType - [AutomationSeedCustomer00025]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'848028A1-F81C-471C-8E96-D5A1C741DF36'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'848028A1-F81C-471C-8E96-D5A1C741DF36', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'5143BA9C-E70E-48ED-86F2-DC78707E21ED'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'5143BA9C-E70E-48ED-86F2-DC78707E21ED', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00025]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F50B7723-D801-4B4C-B315-63D59D036DFC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F50B7723-D801-4B4C-B315-63D59D036DFC', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D658BFE7-D756-4103-ADC6-997E7CF5CE37' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D658BFE7-D756-4103-ADC6-997E7CF5CE37', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='01A4496C-81BB-4C56-AC72-59DE02CE7417' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'01A4496C-81BB-4C56-AC72-59DE02CE7417', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='084797BF-CD9A-4F11-BC9F-5A91A2657ED3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'084797BF-CD9A-4F11-BC9F-5A91A2657ED3', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C5FFC259-1268-4BC2-84B4-A9B18F39112F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C5FFC259-1268-4BC2-84B4-A9B18F39112F', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4FBDF2FB-6A53-4E61-97C4-DF2ECE4094F4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4FBDF2FB-6A53-4E61-97C4-DF2ECE4094F4', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00026' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00026'

	-- CustomerBoundaryType - [AutomationSeedCustomer00026]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'57C62A10-9F4A-4435-85C6-06A25F46E1DB'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'57C62A10-9F4A-4435-85C6-06A25F46E1DB', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9FBE559A-6FC4-4D74-A803-EAB9748080B5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9FBE559A-6FC4-4D74-A803-EAB9748080B5', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00026]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9BB945E9-F25D-43D2-9FB6-89C38A033AA5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9BB945E9-F25D-43D2-9FB6-89C38A033AA5', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0E3B53B7-A9E1-469F-B426-4CC6075A6E0F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0E3B53B7-A9E1-469F-B426-4CC6075A6E0F', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2C0E2C33-76AA-4090-A1DC-AF82B4CE842F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2C0E2C33-76AA-4090-A1DC-AF82B4CE842F', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='72593F57-ACF9-4E79-A3C3-2E284D3941EB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'72593F57-ACF9-4E79-A3C3-2E284D3941EB', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='14CDDC5D-F977-46BF-B561-E103D8F65FF0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'14CDDC5D-F977-46BF-B561-E103D8F65FF0', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E768A2CC-A254-425A-8A0D-B3F6676AEF3B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E768A2CC-A254-425A-8A0D-B3F6676AEF3B', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00027' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00027'

	-- CustomerBoundaryType - [AutomationSeedCustomer00027]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'86E5B5E8-8C67-4A23-BF82-6B436A6BC1BD'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'86E5B5E8-8C67-4A23-BF82-6B436A6BC1BD', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'1A712675-1AA6-4219-9589-1DA08E7A7953'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'1A712675-1AA6-4219-9589-1DA08E7A7953', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00027]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3BF950D6-0739-48B0-BED7-54820CE4800D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3BF950D6-0739-48B0-BED7-54820CE4800D', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F17262C6-D0B1-4E8D-ACE0-BDDEF8C84F42' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F17262C6-D0B1-4E8D-ACE0-BDDEF8C84F42', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C08B0C66-91A2-490F-BE53-1815878714A4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C08B0C66-91A2-490F-BE53-1815878714A4', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B0BB000A-8E86-4D5F-B9D4-FF8C07C23680' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B0BB000A-8E86-4D5F-B9D4-FF8C07C23680', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='13913E99-C886-4C96-A762-69D5903B6016' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'13913E99-C886-4C96-A762-69D5903B6016', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5F8C7CA3-7415-4B55-B4CC-E5475CA5137F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5F8C7CA3-7415-4B55-B4CC-E5475CA5137F', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00028' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00028'

	-- CustomerBoundaryType - [AutomationSeedCustomer00028]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9DD3A4DB-B794-4109-B4E1-415571F4F17B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9DD3A4DB-B794-4109-B4E1-415571F4F17B', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'75BED117-AEA5-4C4C-AC8E-FBD6F9F1FDBD'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'75BED117-AEA5-4C4C-AC8E-FBD6F9F1FDBD', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00028]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3C136D5A-B393-4C08-88B5-7F52B50FFE3E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3C136D5A-B393-4C08-88B5-7F52B50FFE3E', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0E15B7E1-3BE0-4121-9DC4-3AA84531E314' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0E15B7E1-3BE0-4121-9DC4-3AA84531E314', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5FBC6178-FDFA-4AD5-AC1E-4378DF9E7942' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5FBC6178-FDFA-4AD5-AC1E-4378DF9E7942', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='40289D67-757D-431C-A48B-5F1326D36F70' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'40289D67-757D-431C-A48B-5F1326D36F70', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='64B5D925-306E-4383-99E7-9DD4B17D3588' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'64B5D925-306E-4383-99E7-9DD4B17D3588', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C28BC9FF-1100-4C65-B4C1-CA4F12771F7C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C28BC9FF-1100-4C65-B4C1-CA4F12771F7C', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00029' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00029'

	-- CustomerBoundaryType - [AutomationSeedCustomer00029]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'89695A6E-2682-48AD-99B6-87A3B2FFBFCB'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'89695A6E-2682-48AD-99B6-87A3B2FFBFCB', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'126D2099-DB76-49A6-A10A-C45C8509C4D9'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'126D2099-DB76-49A6-A10A-C45C8509C4D9', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00029]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EB3F7462-83D4-4367-95AE-573A78D82DAC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EB3F7462-83D4-4367-95AE-573A78D82DAC', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2FE01542-2B84-4E14-9014-B166B45D6E58' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2FE01542-2B84-4E14-9014-B166B45D6E58', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DE5002BF-C8FD-4D35-86FA-1718A84908BD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DE5002BF-C8FD-4D35-86FA-1718A84908BD', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7D18B68B-86C0-4A22-9E2B-A8B1802230ED' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7D18B68B-86C0-4A22-9E2B-A8B1802230ED', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='402BB590-1644-4166-B422-F396E8D99C17' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'402BB590-1644-4166-B422-F396E8D99C17', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='50EA971F-7F47-46E9-A68A-62F8F4CF2E67' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'50EA971F-7F47-46E9-A68A-62F8F4CF2E67', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00030' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00030'

	-- CustomerBoundaryType - [AutomationSeedCustomer00030]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'EEDB119C-F8FC-495B-833D-4230A94AE225'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'EEDB119C-F8FC-495B-833D-4230A94AE225', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E93B4AE1-E982-4C4C-B262-B69ECB62A841'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E93B4AE1-E982-4C4C-B262-B69ECB62A841', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00030]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='00E83B9E-49F0-4E6C-A536-84FCD19C96A7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'00E83B9E-49F0-4E6C-A536-84FCD19C96A7', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='24FE189F-3029-4968-AF09-7C72DB3B6F14' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'24FE189F-3029-4968-AF09-7C72DB3B6F14', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='83270D94-6A85-4010-92F0-CDF4127D7124' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'83270D94-6A85-4010-92F0-CDF4127D7124', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4CCF600C-4DDF-46CD-B999-416E666A2CA1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4CCF600C-4DDF-46CD-B999-416E666A2CA1', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D0FB3F36-B6A0-4566-8630-29BFE4A7A8B7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D0FB3F36-B6A0-4566-8630-29BFE4A7A8B7', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AF9AE61A-BF49-45F9-86E7-DB0E2368C896' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AF9AE61A-BF49-45F9-86E7-DB0E2368C896', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00031' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00031'

	-- CustomerBoundaryType - [AutomationSeedCustomer00031]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4EDFCF67-1A55-47B3-8CA1-75E6C5E9D41B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4EDFCF67-1A55-47B3-8CA1-75E6C5E9D41B', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4BF10E3A-7275-4252-979B-9A97B178751E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4BF10E3A-7275-4252-979B-9A97B178751E', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00031]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='31E7D81C-BF55-4C07-BB42-76320A720785' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'31E7D81C-BF55-4C07-BB42-76320A720785', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E234D536-D490-43D2-8620-61E807C3BB47' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E234D536-D490-43D2-8620-61E807C3BB47', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B5437E08-0D69-4F43-A67D-EA98F838BE01' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B5437E08-0D69-4F43-A67D-EA98F838BE01', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='510436DD-759D-4814-AD76-88D2BB184E93' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'510436DD-759D-4814-AD76-88D2BB184E93', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CD28AA22-D470-496E-814A-85ECD099A19B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CD28AA22-D470-496E-814A-85ECD099A19B', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5602C018-11FB-4156-8F93-C936F71C316F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5602C018-11FB-4156-8F93-C936F71C316F', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00032' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00032'

	-- CustomerBoundaryType - [AutomationSeedCustomer00032]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'3BE1ED7A-F7EE-4163-BA09-4CB25FC13B96'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'3BE1ED7A-F7EE-4163-BA09-4CB25FC13B96', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'EAC0C44E-FA28-44F2-84DF-5FE49AA22118'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'EAC0C44E-FA28-44F2-84DF-5FE49AA22118', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00032]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1CD2772A-E124-43A7-9DAC-F7747549A7DC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1CD2772A-E124-43A7-9DAC-F7747549A7DC', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='000EA029-6D25-4CFE-B990-18C5944FEAF6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'000EA029-6D25-4CFE-B990-18C5944FEAF6', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='536E2DD0-10BE-41FD-9B28-8DEB455F4050' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'536E2DD0-10BE-41FD-9B28-8DEB455F4050', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AF89F5D7-12D1-421F-BC8D-DA3E1E436F86' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AF89F5D7-12D1-421F-BC8D-DA3E1E436F86', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='498EC0E6-848E-4CCD-B224-FD62D33AAA54' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'498EC0E6-848E-4CCD-B224-FD62D33AAA54', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F08FBB2E-5C90-4FB4-BC61-45EF0FA20176' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F08FBB2E-5C90-4FB4-BC61-45EF0FA20176', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00033' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00033'

	-- CustomerBoundaryType - [AutomationSeedCustomer00033]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'3E316087-5379-4A6F-8E71-F5A15AC8EC9D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'3E316087-5379-4A6F-8E71-F5A15AC8EC9D', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'FAB6BD62-792C-4B76-BDC3-E3BD7BB8D17A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'FAB6BD62-792C-4B76-BDC3-E3BD7BB8D17A', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00033]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1C97145E-CB2D-4443-80FA-8AC5813DD36B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1C97145E-CB2D-4443-80FA-8AC5813DD36B', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9572B729-6D94-4152-B6CA-B748C0C9EF4D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9572B729-6D94-4152-B6CA-B748C0C9EF4D', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6ABE1B14-544F-4E5E-9329-0CB833EAC99B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6ABE1B14-544F-4E5E-9329-0CB833EAC99B', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FE8A22DA-92B8-4D31-B6B9-03B03268C041' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FE8A22DA-92B8-4D31-B6B9-03B03268C041', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='518217EA-7779-4EA0-9176-7C87D775844C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'518217EA-7779-4EA0-9176-7C87D775844C', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='93BA9877-58D1-404B-91F3-84661A924193' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'93BA9877-58D1-404B-91F3-84661A924193', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00034' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00034'

	-- CustomerBoundaryType - [AutomationSeedCustomer00034]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'34C91C9E-BAED-4BBE-96D2-CAB937A9755B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'34C91C9E-BAED-4BBE-96D2-CAB937A9755B', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'80BBCD81-0C5A-425D-A8F2-0BF72405DD2F'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'80BBCD81-0C5A-425D-A8F2-0BF72405DD2F', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00034]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4DE63695-D617-4D7B-9D40-5EF9A91312C2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4DE63695-D617-4D7B-9D40-5EF9A91312C2', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D9665A10-728E-4CC2-84FD-7672F28AB9E5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D9665A10-728E-4CC2-84FD-7672F28AB9E5', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='28B672CE-CC36-4643-9709-01F7F18DE1BE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'28B672CE-CC36-4643-9709-01F7F18DE1BE', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4EFA59F2-3FBB-49DF-8E3B-0705651ABED3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4EFA59F2-3FBB-49DF-8E3B-0705651ABED3', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='40F38ED4-BCA6-441B-B107-7679BBC30755' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'40F38ED4-BCA6-441B-B107-7679BBC30755', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='91FE3144-C000-4B34-9213-6AA263F78EDD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'91FE3144-C000-4B34-9213-6AA263F78EDD', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00035' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00035'

	-- CustomerBoundaryType - [AutomationSeedCustomer00035]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E7956374-A2F8-45AA-8020-6B5C1CCCDC74'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E7956374-A2F8-45AA-8020-6B5C1CCCDC74', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B629ED5E-4004-4770-95C3-10D45F89A093'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B629ED5E-4004-4770-95C3-10D45F89A093', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00035]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FB327143-7DDE-4E7D-B22B-12083BA3DCD9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FB327143-7DDE-4E7D-B22B-12083BA3DCD9', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0B0EDA5C-16F0-4686-8584-975B343AC6F9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0B0EDA5C-16F0-4686-8584-975B343AC6F9', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2657F4EE-DBC4-40BE-B67A-86318AABE3A2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2657F4EE-DBC4-40BE-B67A-86318AABE3A2', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9DC006F1-F06C-46DC-B1AE-8AEBF76BAD17' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9DC006F1-F06C-46DC-B1AE-8AEBF76BAD17', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6D1A0373-2A4E-4A42-A736-52C727E1D286' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6D1A0373-2A4E-4A42-A736-52C727E1D286', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BE21E84C-0DBB-49CA-AA80-8D66CFD2C708' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BE21E84C-0DBB-49CA-AA80-8D66CFD2C708', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00036' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00036'

	-- CustomerBoundaryType - [AutomationSeedCustomer00036]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'782EBCAF-2CED-49AC-93FB-5CAA4D1F0CFC'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'782EBCAF-2CED-49AC-93FB-5CAA4D1F0CFC', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'20B51188-D597-4623-89A6-96C5ABF16B10'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'20B51188-D597-4623-89A6-96C5ABF16B10', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00036]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='484DDA4E-3745-43E9-8542-38F76129CAD2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'484DDA4E-3745-43E9-8542-38F76129CAD2', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AAF50B40-44B2-4FEF-896D-2394EC7FB56E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AAF50B40-44B2-4FEF-896D-2394EC7FB56E', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D95A1B49-9D80-4452-8F9E-B3C5FDC5D58E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D95A1B49-9D80-4452-8F9E-B3C5FDC5D58E', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='140B0EDB-547A-4E1E-A287-175EDF12692C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'140B0EDB-547A-4E1E-A287-175EDF12692C', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A3FFDF28-B7F0-4CAE-9977-69144D155FB2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A3FFDF28-B7F0-4CAE-9977-69144D155FB2', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='48AFDF26-53C7-4BFF-9F87-76804AA24664' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'48AFDF26-53C7-4BFF-9F87-76804AA24664', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00037' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00037'

	-- CustomerBoundaryType - [AutomationSeedCustomer00037]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'0B183AC5-28C6-40F5-A957-94ACE30B627D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'0B183AC5-28C6-40F5-A957-94ACE30B627D', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'97C2ED94-2049-4A11-8D78-EA9716FD5671'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'97C2ED94-2049-4A11-8D78-EA9716FD5671', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00037]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FBEEB46C-F974-453E-A663-FE5674B386DB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FBEEB46C-F974-453E-A663-FE5674B386DB', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D8EA77EE-C061-48E9-B9FF-A7CE0669D02A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D8EA77EE-C061-48E9-B9FF-A7CE0669D02A', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3994A5E8-DBDB-4D5E-B7D0-83FC44039B91' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3994A5E8-DBDB-4D5E-B7D0-83FC44039B91', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AA93D729-9520-4C73-8D81-B8C2BE705DB5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AA93D729-9520-4C73-8D81-B8C2BE705DB5', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4159299F-2ADC-4EBE-AF5C-82090FCC4135' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4159299F-2ADC-4EBE-AF5C-82090FCC4135', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F3078298-1993-4683-9250-E69CE510E14C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F3078298-1993-4683-9250-E69CE510E14C', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00038' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00038'

	-- CustomerBoundaryType - [AutomationSeedCustomer00038]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4E9A7E65-F75E-4E93-8936-2AFAA9E8AFD5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4E9A7E65-F75E-4E93-8936-2AFAA9E8AFD5', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'20111846-FF56-4F6C-8468-D7C9F01AC005'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'20111846-FF56-4F6C-8468-D7C9F01AC005', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00038]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0016D16A-8ED4-4114-B580-C0255182F261' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0016D16A-8ED4-4114-B580-C0255182F261', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C5BCB4EE-DE18-4707-AE01-03BB5B2AA141' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C5BCB4EE-DE18-4707-AE01-03BB5B2AA141', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C826CBA0-4E1F-425B-BD8E-D0F9F97D0C3D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C826CBA0-4E1F-425B-BD8E-D0F9F97D0C3D', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EA8D40DE-3781-42EB-AAAE-FD33D21348AF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EA8D40DE-3781-42EB-AAAE-FD33D21348AF', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A7CB2A04-E9AE-44DF-8F05-4876B9FE3C5D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A7CB2A04-E9AE-44DF-8F05-4876B9FE3C5D', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='54A9E911-C9E8-46B2-AE38-A629ED66FE88' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'54A9E911-C9E8-46B2-AE38-A629ED66FE88', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00039' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00039'

	-- CustomerBoundaryType - [AutomationSeedCustomer00039]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'2C890CDF-3700-4126-ACC7-4C4B275147CE'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'2C890CDF-3700-4126-ACC7-4C4B275147CE', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'368AC2F0-2700-45A6-BD59-D525568BB611'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'368AC2F0-2700-45A6-BD59-D525568BB611', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00039]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='18AC5B29-D763-4055-A26F-731E212DEFE5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'18AC5B29-D763-4055-A26F-731E212DEFE5', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2304B1FD-7324-4783-804C-8A5D6D50105B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2304B1FD-7324-4783-804C-8A5D6D50105B', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='36D013E8-87C0-44CD-9E9C-E200C17482DE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'36D013E8-87C0-44CD-9E9C-E200C17482DE', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BDB2AE47-4909-42FF-A388-BAE68A099F32' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BDB2AE47-4909-42FF-A388-BAE68A099F32', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0719F91A-7FAD-4E4B-8343-FD4D96D2A245' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0719F91A-7FAD-4E4B-8343-FD4D96D2A245', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='28BECD50-E85A-4242-A440-F5C93781550D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'28BECD50-E85A-4242-A440-F5C93781550D', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00040' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00040'

	-- CustomerBoundaryType - [AutomationSeedCustomer00040]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'C7A63A26-3548-4182-BC11-D463298D2674'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'C7A63A26-3548-4182-BC11-D463298D2674', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4C6E5E5A-A55D-48BE-8589-B0AA4655BB96'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4C6E5E5A-A55D-48BE-8589-B0AA4655BB96', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00040]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2EEE4D00-A227-4D6C-9C40-853857F5E463' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2EEE4D00-A227-4D6C-9C40-853857F5E463', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='025D85D5-C7EF-46DF-968F-5D35D45E8B02' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'025D85D5-C7EF-46DF-968F-5D35D45E8B02', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F7376F0A-C2F0-4943-A60D-D64E17121D29' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F7376F0A-C2F0-4943-A60D-D64E17121D29', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5D884700-9AB4-4013-A189-492CAEAB954B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5D884700-9AB4-4013-A189-492CAEAB954B', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A34BC417-4EB7-4895-A9B5-371F08E02F42' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A34BC417-4EB7-4895-A9B5-371F08E02F42', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4A280F6F-1A8D-48D9-AF41-2A731E027368' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4A280F6F-1A8D-48D9-AF41-2A731E027368', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00041' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00041'

	-- CustomerBoundaryType - [AutomationSeedCustomer00041]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A599CE30-9ADE-4CC2-9105-2B60A3D583E4'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A599CE30-9ADE-4CC2-9105-2B60A3D583E4', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F11CBE34-DD4C-4C27-9BF9-7784AA49DAB8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F11CBE34-DD4C-4C27-9BF9-7784AA49DAB8', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00041]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AF214A15-1176-438C-B9D0-0A64ED1E1D96' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AF214A15-1176-438C-B9D0-0A64ED1E1D96', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2751981D-2618-4898-BACD-9B0E3F85D3C3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2751981D-2618-4898-BACD-9B0E3F85D3C3', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CE90EB1C-5BEF-4CD7-B198-4E4EC8E2109E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CE90EB1C-5BEF-4CD7-B198-4E4EC8E2109E', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EDF4E2FD-D545-4AC1-ACA1-60B1B0098944' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EDF4E2FD-D545-4AC1-ACA1-60B1B0098944', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='637F3EE5-106C-42CB-AF90-E7901682DCF3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'637F3EE5-106C-42CB-AF90-E7901682DCF3', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4B5E929E-2F20-44C6-8650-C00D135EBC2B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4B5E929E-2F20-44C6-8650-C00D135EBC2B', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00042' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00042'

	-- CustomerBoundaryType - [AutomationSeedCustomer00042]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F9D70FCD-FE90-4882-8C32-8E91BE0E87EF'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F9D70FCD-FE90-4882-8C32-8E91BE0E87EF', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F5CCDB13-5336-4EAB-B081-11C2FC4DFA3B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F5CCDB13-5336-4EAB-B081-11C2FC4DFA3B', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00042]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B9BA8B55-7756-4535-A0CD-6B56D859C4FF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B9BA8B55-7756-4535-A0CD-6B56D859C4FF', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0FB8BAAB-DC8B-41AE-B171-DB56D49452B3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0FB8BAAB-DC8B-41AE-B171-DB56D49452B3', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B757CB38-5078-4F06-9FDB-E8A6AE158AF4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B757CB38-5078-4F06-9FDB-E8A6AE158AF4', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9D95BC13-F827-4F58-8C96-C11258480A16' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9D95BC13-F827-4F58-8C96-C11258480A16', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4D1545FF-D542-46D2-AB39-04936D29F67C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4D1545FF-D542-46D2-AB39-04936D29F67C', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='242B5559-0503-4EF9-B36E-1F31E9ECD358' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'242B5559-0503-4EF9-B36E-1F31E9ECD358', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00043' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00043'

	-- CustomerBoundaryType - [AutomationSeedCustomer00043]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E96FEF84-D90D-4A60-91D6-24063F545B32'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E96FEF84-D90D-4A60-91D6-24063F545B32', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'31496A85-0B50-4B17-B837-15044CC488D0'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'31496A85-0B50-4B17-B837-15044CC488D0', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00043]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='850B4A69-2148-4128-9830-CE1EBC49FF2B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'850B4A69-2148-4128-9830-CE1EBC49FF2B', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C6E162BC-EFA1-4C8E-82EE-71A2266A63D5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C6E162BC-EFA1-4C8E-82EE-71A2266A63D5', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='407A2E41-1904-4FE7-9250-C5420A22F6EB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'407A2E41-1904-4FE7-9250-C5420A22F6EB', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='97305DE8-5C34-4209-AA6F-197B177985E7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'97305DE8-5C34-4209-AA6F-197B177985E7', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='547E7755-47A1-488F-AE3E-A24A01EA60E1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'547E7755-47A1-488F-AE3E-A24A01EA60E1', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C532E718-941A-4434-8A71-A4AAB5077CDF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C532E718-941A-4434-8A71-A4AAB5077CDF', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00044' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00044'

	-- CustomerBoundaryType - [AutomationSeedCustomer00044]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9D18FF53-75E7-4F63-B6EE-36485B57FCAC'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9D18FF53-75E7-4F63-B6EE-36485B57FCAC', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'26ECE4BF-FADD-4217-B5DE-01B16BC82D14'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'26ECE4BF-FADD-4217-B5DE-01B16BC82D14', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00044]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1E8A8104-A4B4-4557-8998-B33DAF639A19' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1E8A8104-A4B4-4557-8998-B33DAF639A19', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CCB00E90-4B15-4B7C-BF2B-386989C35611' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CCB00E90-4B15-4B7C-BF2B-386989C35611', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DF8929EE-7599-4900-969C-3A78A30D9B30' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DF8929EE-7599-4900-969C-3A78A30D9B30', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='16101193-1DEE-48C3-9677-A0592475E5AE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'16101193-1DEE-48C3-9677-A0592475E5AE', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6156472C-D737-4121-82FB-75B55A4864A6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6156472C-D737-4121-82FB-75B55A4864A6', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A174B2C1-B9D6-4BB7-99FF-4C818EC50257' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A174B2C1-B9D6-4BB7-99FF-4C818EC50257', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00045' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00045'

	-- CustomerBoundaryType - [AutomationSeedCustomer00045]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'C8B1171C-C317-496D-B3B4-1D2D14BCB68D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'C8B1171C-C317-496D-B3B4-1D2D14BCB68D', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'1AED7369-DCD0-4599-A4BC-657E30853777'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'1AED7369-DCD0-4599-A4BC-657E30853777', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00045]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6D4992CF-BB62-4FD2-AA45-3E6FC74DC4F2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6D4992CF-BB62-4FD2-AA45-3E6FC74DC4F2', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FFB89F43-EBD7-411B-AD98-B317BE3E7F99' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FFB89F43-EBD7-411B-AD98-B317BE3E7F99', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='ABE5EB27-529B-40AA-881A-BA74729F71D7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'ABE5EB27-529B-40AA-881A-BA74729F71D7', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='155989D7-3FEA-483D-BF8D-598E2667D4A6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'155989D7-3FEA-483D-BF8D-598E2667D4A6', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CDD59574-D781-464D-AD90-012A011245A7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CDD59574-D781-464D-AD90-012A011245A7', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B97DB9D5-0E8A-4522-89AA-EA59866A5CBE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B97DB9D5-0E8A-4522-89AA-EA59866A5CBE', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00046' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00046'

	-- CustomerBoundaryType - [AutomationSeedCustomer00046]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B76C6B1C-3B78-4A6C-86B0-EB813030015A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B76C6B1C-3B78-4A6C-86B0-EB813030015A', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9956DB2F-0446-42F3-A4EB-1BCE174CFEB9'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9956DB2F-0446-42F3-A4EB-1BCE174CFEB9', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00046]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6F19DF0B-E985-4C0B-9C6A-01608DE6871F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6F19DF0B-E985-4C0B-9C6A-01608DE6871F', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E0A029EB-D393-487E-ADAB-320BDA9F41D2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E0A029EB-D393-487E-ADAB-320BDA9F41D2', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D5A237F7-1AC8-4CD2-82E3-2FAD3A6D2536' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D5A237F7-1AC8-4CD2-82E3-2FAD3A6D2536', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D8488594-8267-43DD-A0EC-8DAB09CEC3F2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D8488594-8267-43DD-A0EC-8DAB09CEC3F2', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8FD7816C-9007-401F-BB7B-144E0E39E11A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8FD7816C-9007-401F-BB7B-144E0E39E11A', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='708040E9-2F0C-4DC1-9999-2D2408C81703' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'708040E9-2F0C-4DC1-9999-2D2408C81703', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00047' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00047'

	-- CustomerBoundaryType - [AutomationSeedCustomer00047]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'39E4E72D-7A10-4DCB-90A7-12F8746D4EC0'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'39E4E72D-7A10-4DCB-90A7-12F8746D4EC0', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B9EBBA6F-D0F8-400B-B3B4-8623BCA5E709'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B9EBBA6F-D0F8-400B-B3B4-8623BCA5E709', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00047]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C5235916-41C3-429C-8024-681602EE09E1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C5235916-41C3-429C-8024-681602EE09E1', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8D9A51CF-1BE5-49BA-B427-491F65660D85' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8D9A51CF-1BE5-49BA-B427-491F65660D85', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='37D78A34-808B-4D40-8CC5-89FF7DC2B84E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'37D78A34-808B-4D40-8CC5-89FF7DC2B84E', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C78B7916-72CD-498C-A808-2F10A01F3EAD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C78B7916-72CD-498C-A808-2F10A01F3EAD', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='46D9083A-E874-4C40-A0F7-5B2F48BCD2E0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'46D9083A-E874-4C40-A0F7-5B2F48BCD2E0', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CC9C3117-D09C-4482-9B0B-E6EBDA2336AF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CC9C3117-D09C-4482-9B0B-E6EBDA2336AF', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00048' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00048'

	-- CustomerBoundaryType - [AutomationSeedCustomer00048]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'2D152A31-3BC5-4D28-A25B-8DA560A47828'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'2D152A31-3BC5-4D28-A25B-8DA560A47828', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'70FE36A9-0DCF-4DE1-9D77-45B38D158D7B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'70FE36A9-0DCF-4DE1-9D77-45B38D158D7B', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00048]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='79FCE680-EA96-40D1-8217-5CC93EB75DD2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'79FCE680-EA96-40D1-8217-5CC93EB75DD2', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EACE3EFB-1521-489B-A43E-7AF0B0E2AF9D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EACE3EFB-1521-489B-A43E-7AF0B0E2AF9D', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0DE2E94F-1860-49B4-B446-791786D2A44A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0DE2E94F-1860-49B4-B446-791786D2A44A', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='661DF8D3-A93B-401D-9E81-03CEC7EC5AE2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'661DF8D3-A93B-401D-9E81-03CEC7EC5AE2', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5FA82E4D-C332-4DAB-87E1-6EEACDD5E458' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5FA82E4D-C332-4DAB-87E1-6EEACDD5E458', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AAAFDF12-47DE-4FC5-96DE-50FFB4B7AE06' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AAAFDF12-47DE-4FC5-96DE-50FFB4B7AE06', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00049' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00049'

	-- CustomerBoundaryType - [AutomationSeedCustomer00049]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'08EA93E9-1EF2-4D1F-8EAA-7A2305725F54'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'08EA93E9-1EF2-4D1F-8EAA-7A2305725F54', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'2F619030-9C3B-4C4C-8B2E-88692A9CB2B5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'2F619030-9C3B-4C4C-8B2E-88692A9CB2B5', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00049]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9FC97DE7-3C8C-4D96-910C-8DE2B58760BB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9FC97DE7-3C8C-4D96-910C-8DE2B58760BB', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7DA1B751-8DCE-4D02-BF2D-8BBDF1DB5DF6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7DA1B751-8DCE-4D02-BF2D-8BBDF1DB5DF6', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4C80B884-1AAD-4C04-A2CD-C7DB2BE3F6E5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4C80B884-1AAD-4C04-A2CD-C7DB2BE3F6E5', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A04A29C9-817B-4646-81ED-CB290431D07F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A04A29C9-817B-4646-81ED-CB290431D07F', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='711FA04C-DDCD-4C33-A1AC-785159D2CA8A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'711FA04C-DDCD-4C33-A1AC-785159D2CA8A', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EB0D8256-1806-4D8D-A900-D46FF62FA4F9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EB0D8256-1806-4D8D-A900-D46FF62FA4F9', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00050' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00050'

	-- CustomerBoundaryType - [AutomationSeedCustomer00050]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B4AF3864-9FDA-42A1-9D02-1F7937FB956A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B4AF3864-9FDA-42A1-9D02-1F7937FB956A', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9D274BFC-416A-400D-AD6B-3B94347DEE95'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9D274BFC-416A-400D-AD6B-3B94347DEE95', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00050]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B2F1A19E-8008-4559-A3BE-37072C2E17C1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B2F1A19E-8008-4559-A3BE-37072C2E17C1', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='24E181EE-CC61-46C5-B956-5706F7E760A6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'24E181EE-CC61-46C5-B956-5706F7E760A6', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0B9A4F22-AED7-4D61-86E7-4BC9AA4CA3DD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0B9A4F22-AED7-4D61-86E7-4BC9AA4CA3DD', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E90AFA3F-6235-4EF7-86DD-539FA6963939' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E90AFA3F-6235-4EF7-86DD-539FA6963939', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5D391251-B5A7-4F2A-9DBD-332175DA805F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5D391251-B5A7-4F2A-9DBD-332175DA805F', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='74F9156E-B31B-430A-8E39-0C963DF401B3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'74F9156E-B31B-430A-8E39-0C963DF401B3', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00051' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00051'

	-- CustomerBoundaryType - [AutomationSeedCustomer00051]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'73566FEA-995E-42D0-A91C-51790C759023'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'73566FEA-995E-42D0-A91C-51790C759023', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F6C69DB7-99B3-4489-8458-5FA32CF711A1'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F6C69DB7-99B3-4489-8458-5FA32CF711A1', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00051]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6805BCF5-B806-4647-8E33-3EE8A98EAA69' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6805BCF5-B806-4647-8E33-3EE8A98EAA69', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='103C4600-E6EB-46BD-8EE0-1C074956CA40' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'103C4600-E6EB-46BD-8EE0-1C074956CA40', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D6993134-5DA9-4667-9813-C2AF670D92CB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D6993134-5DA9-4667-9813-C2AF670D92CB', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E3C470E8-B8E0-4E8E-B278-BD0E5D82D97F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E3C470E8-B8E0-4E8E-B278-BD0E5D82D97F', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F5D3C12D-7729-4494-919A-66C8261A9831' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F5D3C12D-7729-4494-919A-66C8261A9831', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6BECF8D4-01B3-40EC-AFC5-F6E4E99BF1BD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6BECF8D4-01B3-40EC-AFC5-F6E4E99BF1BD', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00052' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00052'

	-- CustomerBoundaryType - [AutomationSeedCustomer00052]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'21E59827-8AA8-4CE4-89E7-0CCC65942EF8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'21E59827-8AA8-4CE4-89E7-0CCC65942EF8', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'18D10D6C-A4AF-48BA-91ED-35B2F9A345FC'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'18D10D6C-A4AF-48BA-91ED-35B2F9A345FC', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00052]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3C6A71F2-B0A0-4FCD-9F86-7562A61C9668' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3C6A71F2-B0A0-4FCD-9F86-7562A61C9668', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AB7B37F8-33DB-46A0-A641-7C429D7F64EC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AB7B37F8-33DB-46A0-A641-7C429D7F64EC', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='052B3BA8-33B7-4FA2-B97D-DE5A85E13859' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'052B3BA8-33B7-4FA2-B97D-DE5A85E13859', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EA9BB24E-4EDD-4D5A-B3BC-D7B6E8BC0336' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EA9BB24E-4EDD-4D5A-B3BC-D7B6E8BC0336', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2A3D4629-E417-420B-A10D-D7C4B55895AC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2A3D4629-E417-420B-A10D-D7C4B55895AC', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FC9C6563-3920-4B23-8AA1-CC0134EBDF9A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FC9C6563-3920-4B23-8AA1-CC0134EBDF9A', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00053' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00053'

	-- CustomerBoundaryType - [AutomationSeedCustomer00053]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'352B6EE6-4FC4-45D4-AEE1-E7869A60FD52'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'352B6EE6-4FC4-45D4-AEE1-E7869A60FD52', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'2B1D569A-FF95-4225-BEBE-269407C98DBF'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'2B1D569A-FF95-4225-BEBE-269407C98DBF', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00053]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3C40BBFD-0D76-4D5E-8E55-5881073D0261' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3C40BBFD-0D76-4D5E-8E55-5881073D0261', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8281612D-E8D1-4D05-9E15-59970FEC0E73' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8281612D-E8D1-4D05-9E15-59970FEC0E73', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='071E2CBD-19EA-48C7-9115-2A51E52CDA65' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'071E2CBD-19EA-48C7-9115-2A51E52CDA65', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7724FDA2-5921-41BC-9926-C98C18F92411' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7724FDA2-5921-41BC-9926-C98C18F92411', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5AE5A0EF-FE99-475A-8B89-26773076624E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5AE5A0EF-FE99-475A-8B89-26773076624E', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='778978C3-7FA9-4E9F-A390-F7BC7B116908' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'778978C3-7FA9-4E9F-A390-F7BC7B116908', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00054' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00054'

	-- CustomerBoundaryType - [AutomationSeedCustomer00054]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'957DC2DD-11A3-4229-8C56-3675EADDBA1B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'957DC2DD-11A3-4229-8C56-3675EADDBA1B', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A5620815-E43E-48AB-A758-EB3314BA3A9A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A5620815-E43E-48AB-A758-EB3314BA3A9A', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00054]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='58675D8B-D081-4FF7-B875-40350E586546' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'58675D8B-D081-4FF7-B875-40350E586546', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1CACB621-3242-43C8-9E2D-1068A58241FA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1CACB621-3242-43C8-9E2D-1068A58241FA', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='035A8B0B-447F-46A2-9C0B-B1B696CF515C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'035A8B0B-447F-46A2-9C0B-B1B696CF515C', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B81815AF-B92D-49D1-8D09-C81815389835' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B81815AF-B92D-49D1-8D09-C81815389835', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FAF51368-6E76-4E94-AB8F-55D311B188E0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FAF51368-6E76-4E94-AB8F-55D311B188E0', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FF358055-63BD-4E49-82F8-F6A3360D3193' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FF358055-63BD-4E49-82F8-F6A3360D3193', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00055' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00055'

	-- CustomerBoundaryType - [AutomationSeedCustomer00055]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'846585E9-F791-4F8B-979D-31A3A794EBCF'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'846585E9-F791-4F8B-979D-31A3A794EBCF', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'FDE5AA35-EE75-4382-B700-3FD56BF8EEF5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'FDE5AA35-EE75-4382-B700-3FD56BF8EEF5', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00055]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='34D39304-3ACC-4C31-BA13-645F1FE37E45' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'34D39304-3ACC-4C31-BA13-645F1FE37E45', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7D45F3B0-E9DD-4C60-8B18-EC4B3FD7175E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7D45F3B0-E9DD-4C60-8B18-EC4B3FD7175E', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='54D0105A-168C-4DCC-BDA0-D5D7B3BCFD69' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'54D0105A-168C-4DCC-BDA0-D5D7B3BCFD69', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7902BC51-D1A0-4130-B1BD-BD94332A4941' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7902BC51-D1A0-4130-B1BD-BD94332A4941', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='395722F1-8D3E-46C2-A35E-F908D3AFFAF6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'395722F1-8D3E-46C2-A35E-F908D3AFFAF6', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CD3BE903-8A37-409A-A936-8626D8BC7B11' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CD3BE903-8A37-409A-A936-8626D8BC7B11', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00056' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00056'

	-- CustomerBoundaryType - [AutomationSeedCustomer00056]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'0D265D93-ECF1-4127-B04D-0F489E70A37C'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'0D265D93-ECF1-4127-B04D-0F489E70A37C', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'67730AE3-6065-4ED4-9611-3478C914D936'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'67730AE3-6065-4ED4-9611-3478C914D936', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00056]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='50B3A0E7-538D-418C-85D2-3572F279E357' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'50B3A0E7-538D-418C-85D2-3572F279E357', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='42C81544-0948-4471-B791-8A1097654071' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'42C81544-0948-4471-B791-8A1097654071', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='206F8D7C-D1FB-41DC-B01F-2D2290E9F8CB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'206F8D7C-D1FB-41DC-B01F-2D2290E9F8CB', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A0F96BEE-FEF1-48D7-B102-041641340569' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A0F96BEE-FEF1-48D7-B102-041641340569', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A4F74638-2A01-48E0-A9CD-BCC14853E6A8' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A4F74638-2A01-48E0-A9CD-BCC14853E6A8', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5DBC1B24-FA06-495A-9462-F912FA3FAAF8' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5DBC1B24-FA06-495A-9462-F912FA3FAAF8', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00057' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00057'

	-- CustomerBoundaryType - [AutomationSeedCustomer00057]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'C18876EB-B401-4632-906F-86FB5D747BF8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'C18876EB-B401-4632-906F-86FB5D747BF8', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'064143CD-07EC-46C5-B4DA-86215B1E93DD'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'064143CD-07EC-46C5-B4DA-86215B1E93DD', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00057]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C4DBF607-58AB-4C37-AA01-242593D7D81A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C4DBF607-58AB-4C37-AA01-242593D7D81A', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B0385284-DAF2-4409-A314-143EF83133D1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B0385284-DAF2-4409-A314-143EF83133D1', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6BEC6B13-009F-429A-9BB0-D71C46D376D5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6BEC6B13-009F-429A-9BB0-D71C46D376D5', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AB90974B-B517-42E4-961E-B5BB12E5C623' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AB90974B-B517-42E4-961E-B5BB12E5C623', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B616C04D-A3C9-4EBF-8376-DFEEF014F76E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B616C04D-A3C9-4EBF-8376-DFEEF014F76E', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='91333DF6-A895-45EC-BF98-EB65261E363B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'91333DF6-A895-45EC-BF98-EB65261E363B', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00058' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00058'

	-- CustomerBoundaryType - [AutomationSeedCustomer00058]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'EDADE55B-D4E9-4CF5-AE1C-4A319B21085D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'EDADE55B-D4E9-4CF5-AE1C-4A319B21085D', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BE4F7FD9-29E1-41CA-A91B-A3266318C2F2'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BE4F7FD9-29E1-41CA-A91B-A3266318C2F2', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00058]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1EFE6FA6-BDD6-4396-854F-F3A49168C992' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1EFE6FA6-BDD6-4396-854F-F3A49168C992', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='ABD0C872-CA1E-4A4C-8C05-3221084B143A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'ABD0C872-CA1E-4A4C-8C05-3221084B143A', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='25947235-698D-48FB-8659-0B9EE87C4E8D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'25947235-698D-48FB-8659-0B9EE87C4E8D', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='267DF5B9-C4FA-4642-BA2F-C8B5EE83F50E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'267DF5B9-C4FA-4642-BA2F-C8B5EE83F50E', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2F6A82E2-88A0-49F7-A5CB-6B728B7AEAFE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2F6A82E2-88A0-49F7-A5CB-6B728B7AEAFE', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F1821C75-8CEE-4D8A-84F2-5A0EC9859CE7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F1821C75-8CEE-4D8A-84F2-5A0EC9859CE7', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00059' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00059'

	-- CustomerBoundaryType - [AutomationSeedCustomer00059]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E5E288D3-FC20-4FA5-987B-7336D4375AA5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E5E288D3-FC20-4FA5-987B-7336D4375AA5', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'AAA9C2D1-A745-4F55-9D1C-EBA4CE5C6243'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'AAA9C2D1-A745-4F55-9D1C-EBA4CE5C6243', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00059]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F3424755-5B5F-454E-B57B-E5F3BE18CA9D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F3424755-5B5F-454E-B57B-E5F3BE18CA9D', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CB139F8D-A682-4855-9A3F-7BB5DA8D4182' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CB139F8D-A682-4855-9A3F-7BB5DA8D4182', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1CFAB6F0-BDF3-423A-864A-562B090BCC03' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1CFAB6F0-BDF3-423A-864A-562B090BCC03', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6CC66530-4D60-4B2F-B0DB-A6E40F12F661' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6CC66530-4D60-4B2F-B0DB-A6E40F12F661', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A3C069FE-161D-46FB-9D23-82C7D5E142BF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A3C069FE-161D-46FB-9D23-82C7D5E142BF', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='ECCAC707-18E3-41C3-83E2-88FBAFCAA345' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'ECCAC707-18E3-41C3-83E2-88FBAFCAA345', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00060' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00060'

	-- CustomerBoundaryType - [AutomationSeedCustomer00060]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'405515DB-D918-41C4-A35E-55B427698527'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'405515DB-D918-41C4-A35E-55B427698527', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'82AEB14C-0646-445F-B147-E49AE11C3E97'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'82AEB14C-0646-445F-B147-E49AE11C3E97', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00060]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A4B40855-C8E5-4C5F-890F-3EFAC58C83CE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A4B40855-C8E5-4C5F-890F-3EFAC58C83CE', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E7667F6F-8F6F-4616-AE97-0B0F0BC25423' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E7667F6F-8F6F-4616-AE97-0B0F0BC25423', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AA2CACC8-FC72-4924-AB98-A92730805553' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AA2CACC8-FC72-4924-AB98-A92730805553', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9458205A-F8B5-4601-95E1-DABF8CDC9ED1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9458205A-F8B5-4601-95E1-DABF8CDC9ED1', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3010DD3B-B391-47E5-8F3D-65A6D5134F4F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3010DD3B-B391-47E5-8F3D-65A6D5134F4F', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BBF875F3-B8B2-4307-BAC0-574D37894456' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BBF875F3-B8B2-4307-BAC0-574D37894456', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00061' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00061'

	-- CustomerBoundaryType - [AutomationSeedCustomer00061]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Big Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'67BFD7D2-D594-4F06-B614-01BF7B081E5B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'67BFD7D2-D594-4F06-B614-01BF7B081E5B', @customerId, N'Big Boundary', N'#B5DBF4', 2, 0, 5, 1)

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'Small Boundary', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'553E884B-1795-46E4-8B0F-75EEC3E75E25'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'553E884B-1795-46E4-8B0F-75EEC3E75E25', @customerId, N'Small Boundary', N'#B5DBF4', 2, 0, 5, 1)

	-- CustomerMaterialType - [AutomationSeedCustomer00061]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='92A80FB7-AF73-4741-B401-BB86BDB0373E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'92A80FB7-AF73-4741-B401-BB86BDB0373E', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EE24787B-6904-4CEA-A4AA-FE826B028BEA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EE24787B-6904-4CEA-A4AA-FE826B028BEA', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4839500D-36F7-455E-B8F4-6A8BE15662AA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4839500D-36F7-455E-B8F4-6A8BE15662AA', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2FD930DD-9BE8-4E55-B154-5BC19E420175' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2FD930DD-9BE8-4E55-B154-5BC19E420175', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A17A4686-C0E5-47EA-85C7-80138763AF19' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A17A4686-C0E5-47EA-85C7-80138763AF19', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='39CDA543-9029-4F1C-9187-8D78F8ED7943' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'39CDA543-9029-4F1C-9187-8D78F8ED7943', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

		
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

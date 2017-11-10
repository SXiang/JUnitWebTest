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

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8F97BEA6-5812-4B0E-B389-4808D25F6E46'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8F97BEA6-5812-4B0E-B389-4808D25F6E46', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'DB130C03-87A1-4FC5-9469-DD016F360BDC'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'DB130C03-87A1-4FC5-9469-DD016F360BDC', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'0091C7A4-4D7B-43D6-9C24-852D9260507B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'0091C7A4-4D7B-43D6-9C24-852D9260507B', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B5E9C1DA-4C8B-43D9-8CD8-531C4D4B37A8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B5E9C1DA-4C8B-43D9-8CD8-531C4D4B37A8', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00001]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A56B4525-EE50-4331-9A41-3E6F3248FD59' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A56B4525-EE50-4331-9A41-3E6F3248FD59', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8E7483B5-ABD6-42D5-AB21-D86D0B140F72' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8E7483B5-ABD6-42D5-AB21-D86D0B140F72', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='417A0D0C-DFEE-4759-9740-B1CB830C2644' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'417A0D0C-DFEE-4759-9740-B1CB830C2644', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='755B14F9-18E1-45AE-BD8C-8E976DCCA020' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'755B14F9-18E1-45AE-BD8C-8E976DCCA020', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='21BDBE1A-C7DC-4AE1-9B7E-3CF8537B6FAB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'21BDBE1A-C7DC-4AE1-9B7E-3CF8537B6FAB', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3AE3D32B-ABFC-457E-A533-B5AEF0261F95' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3AE3D32B-ABFC-457E-A533-B5AEF0261F95', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00002' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00002'

	-- CustomerBoundaryType - [AutomationSeedCustomer00002]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'AAB8BDC2-383E-4FB7-8D6D-9FA52D98045A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'AAB8BDC2-383E-4FB7-8D6D-9FA52D98045A', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F82BA7A1-9D3A-46D9-863C-19DFA9EC7938'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F82BA7A1-9D3A-46D9-863C-19DFA9EC7938', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9A9EBEB6-681C-455F-A649-AB9F54D9DCB6'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9A9EBEB6-681C-455F-A649-AB9F54D9DCB6', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7A385DFC-127D-43A4-B517-A46572FBE29E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7A385DFC-127D-43A4-B517-A46572FBE29E', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00002]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C935E8C4-CF6C-49FF-B7FD-D1906A4054DE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C935E8C4-CF6C-49FF-B7FD-D1906A4054DE', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='808394BC-210F-4DFB-B27F-3E7FF23F3A4C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'808394BC-210F-4DFB-B27F-3E7FF23F3A4C', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='560A26B1-783C-4338-A974-CC44A012FF52' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'560A26B1-783C-4338-A974-CC44A012FF52', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='57E6405C-7E72-42EA-AB06-CFD1CDE3B29E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'57E6405C-7E72-42EA-AB06-CFD1CDE3B29E', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BC01425A-AEC6-4032-87DF-842CFBD2B391' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BC01425A-AEC6-4032-87DF-842CFBD2B391', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F2154BC0-5E0C-44C3-BEB4-E207238BAAF5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F2154BC0-5E0C-44C3-BEB4-E207238BAAF5', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00003' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00003'

	-- CustomerBoundaryType - [AutomationSeedCustomer00003]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'DF573B2B-7A39-4DED-9C12-569961AB2DD4'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'DF573B2B-7A39-4DED-9C12-569961AB2DD4', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'51AC761B-ACB4-4242-A0E4-55A8B9F8CDFA'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'51AC761B-ACB4-4242-A0E4-55A8B9F8CDFA', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B6038A37-B2A2-4895-AC34-37909F67D4A1'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B6038A37-B2A2-4895-AC34-37909F67D4A1', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'966D8895-ABC4-418A-92C1-23127B3E9B3E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'966D8895-ABC4-418A-92C1-23127B3E9B3E', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00003]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BC8D1E70-A8AE-4B01-B4B4-1F0911597D57' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BC8D1E70-A8AE-4B01-B4B4-1F0911597D57', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='524F0734-E6DB-49E4-9605-DA53539C931A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'524F0734-E6DB-49E4-9605-DA53539C931A', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B0A62C03-EECC-4EA3-9A89-C2C1FB32562E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B0A62C03-EECC-4EA3-9A89-C2C1FB32562E', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1D35FC8C-93BD-4AC3-B8BF-CFB81734C216' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1D35FC8C-93BD-4AC3-B8BF-CFB81734C216', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0FD84827-7EDC-4DC5-AFE9-F60BF2B226A5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0FD84827-7EDC-4DC5-AFE9-F60BF2B226A5', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='81C6B32A-BABA-46C8-82D1-E3071AFD37FC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'81C6B32A-BABA-46C8-82D1-E3071AFD37FC', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00004' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00004'

	-- CustomerBoundaryType - [AutomationSeedCustomer00004]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'28816413-F7D1-4729-B373-3DFEC883B077'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'28816413-F7D1-4729-B373-3DFEC883B077', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BC631C99-2FB7-4C4D-9B35-702935B5F341'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BC631C99-2FB7-4C4D-9B35-702935B5F341', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BB113E71-D038-4A3D-8348-8DAFB7CE84F6'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BB113E71-D038-4A3D-8348-8DAFB7CE84F6', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'6A895D49-8F28-4907-9A9D-E4898702C1DA'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'6A895D49-8F28-4907-9A9D-E4898702C1DA', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00004]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B7F8D71B-0116-44DA-ADFB-3CAD6E2DF869' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B7F8D71B-0116-44DA-ADFB-3CAD6E2DF869', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F5478482-29AC-4F5F-B429-23F57FB22317' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F5478482-29AC-4F5F-B429-23F57FB22317', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1988FDB1-E2A4-46BD-99A2-E2E5B965BA96' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1988FDB1-E2A4-46BD-99A2-E2E5B965BA96', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4E365801-8E41-426D-9B50-6BBE9F7DCD47' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4E365801-8E41-426D-9B50-6BBE9F7DCD47', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8E109D2B-CC88-447C-B737-459EE2D4BE2F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8E109D2B-CC88-447C-B737-459EE2D4BE2F', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='505FD031-EE58-4C36-A87D-DBFD1B2D13AF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'505FD031-EE58-4C36-A87D-DBFD1B2D13AF', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00005' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00005'

	-- CustomerBoundaryType - [AutomationSeedCustomer00005]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'80E046F1-9D6C-4091-82CD-B421A961EFFA'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'80E046F1-9D6C-4091-82CD-B421A961EFFA', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7E971016-087A-423E-9F3D-1DC2A6210CE8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7E971016-087A-423E-9F3D-1DC2A6210CE8', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7AC46E89-AEED-4787-AD49-18B9B3A14A2B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7AC46E89-AEED-4787-AD49-18B9B3A14A2B', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7891DBE3-C802-4553-89F7-C420245538F1'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7891DBE3-C802-4553-89F7-C420245538F1', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00005]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3B196DCF-F59E-4DE1-A282-1113B9F7EABA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3B196DCF-F59E-4DE1-A282-1113B9F7EABA', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9D15C104-6323-4C19-A5A2-61D612E6D044' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9D15C104-6323-4C19-A5A2-61D612E6D044', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A995B0E3-89D9-43CB-98A1-BC3C37269927' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A995B0E3-89D9-43CB-98A1-BC3C37269927', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2A28ADB7-47DA-4196-A276-1060AE573E27' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2A28ADB7-47DA-4196-A276-1060AE573E27', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2741180C-F72B-4289-BE2D-6DEA126C094C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2741180C-F72B-4289-BE2D-6DEA126C094C', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='471D580C-5993-4B6B-9306-729B4F3EBF3A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'471D580C-5993-4B6B-9306-729B4F3EBF3A', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00006' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00006'

	-- CustomerBoundaryType - [AutomationSeedCustomer00006]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BB5AAE29-F114-4B08-A216-F9514BB6CAB2'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BB5AAE29-F114-4B08-A216-F9514BB6CAB2', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'D3BD79B1-A5D3-47F2-8CBA-1A448C4EEE25'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'D3BD79B1-A5D3-47F2-8CBA-1A448C4EEE25', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BE695A19-F315-43F5-8E16-608A76D9E327'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BE695A19-F315-43F5-8E16-608A76D9E327', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'0CA8EAAB-13AF-4A34-81F7-04157DF9BFD4'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'0CA8EAAB-13AF-4A34-81F7-04157DF9BFD4', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00006]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9F72564D-5413-494A-8941-F5C1C7B9F6B4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9F72564D-5413-494A-8941-F5C1C7B9F6B4', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EAA27469-C0C5-46B1-ACC7-A43E845798CE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EAA27469-C0C5-46B1-ACC7-A43E845798CE', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='276E8CAE-D67F-45C1-B145-7E7CB012D6DA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'276E8CAE-D67F-45C1-B145-7E7CB012D6DA', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1BCF48E8-6516-4739-A691-8109F02FCEC4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1BCF48E8-6516-4739-A691-8109F02FCEC4', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='575AEA98-CAA4-4F72-A6FF-3288C26F9934' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'575AEA98-CAA4-4F72-A6FF-3288C26F9934', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B6C8A2A6-4D4F-496E-8FE5-3CA84139AF96' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B6C8A2A6-4D4F-496E-8FE5-3CA84139AF96', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00007' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00007'

	-- CustomerBoundaryType - [AutomationSeedCustomer00007]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E355DBC6-5A76-42C6-85A0-5D26AAA13B8A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E355DBC6-5A76-42C6-85A0-5D26AAA13B8A', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7B44D6AD-1A9B-4AE3-A120-D4084CE79A97'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7B44D6AD-1A9B-4AE3-A120-D4084CE79A97', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'6B0DDC7E-41B7-44F0-9FAF-DA46B98FA96F'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'6B0DDC7E-41B7-44F0-9FAF-DA46B98FA96F', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'D9792135-A678-4F91-B06D-68FF35C6CD0D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'D9792135-A678-4F91-B06D-68FF35C6CD0D', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00007]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5430653A-4C91-4EFA-8564-DA4926EE543F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5430653A-4C91-4EFA-8564-DA4926EE543F', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2C290A23-52B5-4E1A-9F70-41D970CA21C2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2C290A23-52B5-4E1A-9F70-41D970CA21C2', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='107BBC7C-1756-4A9C-A5DB-2D4DCC894F81' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'107BBC7C-1756-4A9C-A5DB-2D4DCC894F81', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BA05C233-C7E5-4453-8A13-0A9CA2BFB112' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BA05C233-C7E5-4453-8A13-0A9CA2BFB112', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F3EDC4CF-AB1E-4B22-8741-7E7E06181D6F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F3EDC4CF-AB1E-4B22-8741-7E7E06181D6F', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BB6EBB0D-7127-4A44-8A0A-1093B9164175' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BB6EBB0D-7127-4A44-8A0A-1093B9164175', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00008' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00008'

	-- CustomerBoundaryType - [AutomationSeedCustomer00008]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'0020C4FD-688A-4A8C-A61E-8E12B42A916C'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'0020C4FD-688A-4A8C-A61E-8E12B42A916C', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A9C64FF2-966A-429D-BE44-6F6C7A661EB1'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A9C64FF2-966A-429D-BE44-6F6C7A661EB1', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A71D6742-19FC-4EE1-830D-B00F78E5BA5A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A71D6742-19FC-4EE1-830D-B00F78E5BA5A', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'5FFDE631-33CD-4B84-A457-D29440533715'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'5FFDE631-33CD-4B84-A457-D29440533715', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00008]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B8C5DD78-8FFA-421D-9E3F-B6B44B819F08' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B8C5DD78-8FFA-421D-9E3F-B6B44B819F08', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='593D5910-0164-465A-9673-E1730E1770DA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'593D5910-0164-465A-9673-E1730E1770DA', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3A79E6FC-BB66-4304-BD78-732CD27ED67D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3A79E6FC-BB66-4304-BD78-732CD27ED67D', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='55FE483C-CA44-4895-B887-DD77A529C59D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'55FE483C-CA44-4895-B887-DD77A529C59D', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C6C0AAE4-8A7B-4324-A200-4E9720723804' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C6C0AAE4-8A7B-4324-A200-4E9720723804', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8FB13865-28E5-4FFF-A713-31A780D2B1C0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8FB13865-28E5-4FFF-A713-31A780D2B1C0', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00009' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00009'

	-- CustomerBoundaryType - [AutomationSeedCustomer00009]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B54D5D77-3986-4303-A628-F5F044DD04F8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B54D5D77-3986-4303-A628-F5F044DD04F8', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'DB080336-F095-45D7-A54B-EE10A2B3345A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'DB080336-F095-45D7-A54B-EE10A2B3345A', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9C8D17E9-D298-407B-9DD0-0A79F1A74369'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9C8D17E9-D298-407B-9DD0-0A79F1A74369', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'2F141C11-419E-467A-8ACB-04DDBFF0C360'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'2F141C11-419E-467A-8ACB-04DDBFF0C360', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00009]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='669524A4-A875-4361-AC2D-4DA41F98AE58' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'669524A4-A875-4361-AC2D-4DA41F98AE58', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A1DBC21B-AE6A-4864-8DF9-90724C13921E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A1DBC21B-AE6A-4864-8DF9-90724C13921E', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D273C521-920E-450B-AAD0-97C3EECA9B02' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D273C521-920E-450B-AAD0-97C3EECA9B02', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7B4088E2-6CFF-4347-AF29-EBA2B80D7C8A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7B4088E2-6CFF-4347-AF29-EBA2B80D7C8A', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5C1BEA5F-E2A9-4DC1-A89B-E757953C01FE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5C1BEA5F-E2A9-4DC1-A89B-E757953C01FE', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1DCFEEF3-7B6C-4EBF-AC00-9E9014B562C2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1DCFEEF3-7B6C-4EBF-AC00-9E9014B562C2', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00010' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00010'

	-- CustomerBoundaryType - [AutomationSeedCustomer00010]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'19410BB9-87D2-4274-86EB-E8996955D43B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'19410BB9-87D2-4274-86EB-E8996955D43B', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'36F0FCB3-8268-49BF-9D95-6372D9468E5F'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'36F0FCB3-8268-49BF-9D95-6372D9468E5F', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'67B73098-7611-4B20-B5C0-0A235893D8D7'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'67B73098-7611-4B20-B5C0-0A235893D8D7', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'3183A5FC-935C-445B-A655-17E524556D91'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'3183A5FC-935C-445B-A655-17E524556D91', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00010]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='16773259-13AC-4994-8D82-B1B38A14DF21' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'16773259-13AC-4994-8D82-B1B38A14DF21', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C11628B5-B749-44FC-9E80-9ED4F1DEAA92' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C11628B5-B749-44FC-9E80-9ED4F1DEAA92', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='970465D2-B581-48EE-8D03-A9F8926324E8' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'970465D2-B581-48EE-8D03-A9F8926324E8', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='33BAC41E-FFCD-4CBD-9341-C670C729B1AD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'33BAC41E-FFCD-4CBD-9341-C670C729B1AD', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1DC14B4D-7C2D-4D2D-AE5D-6589ADFEB586' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1DC14B4D-7C2D-4D2D-AE5D-6589ADFEB586', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4D26183E-A4BB-4BE7-99EF-E38247C96238' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4D26183E-A4BB-4BE7-99EF-E38247C96238', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00011' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00011'

	-- CustomerBoundaryType - [AutomationSeedCustomer00011]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'299CA2D0-BE68-4626-B829-435F5AFA1FCA'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'299CA2D0-BE68-4626-B829-435F5AFA1FCA', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8E3922A3-9319-4281-A747-0BED165DA997'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8E3922A3-9319-4281-A747-0BED165DA997', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'1531A367-9245-416D-87C7-A439EA42CF75'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'1531A367-9245-416D-87C7-A439EA42CF75', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'FB15DF4B-50D2-4841-B8AC-FFCD15C16AAE'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'FB15DF4B-50D2-4841-B8AC-FFCD15C16AAE', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00011]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D96E78C8-F9FC-4A22-9951-28822E73A9F7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D96E78C8-F9FC-4A22-9951-28822E73A9F7', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3A05EB9E-5C8B-490C-AEA2-FCC23C662A1E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3A05EB9E-5C8B-490C-AEA2-FCC23C662A1E', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DFDC2274-0FCA-4079-BFD6-FFB8FA581E05' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DFDC2274-0FCA-4079-BFD6-FFB8FA581E05', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9A9F411B-DCF6-46ED-B43F-91A5828B087B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9A9F411B-DCF6-46ED-B43F-91A5828B087B', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='558C4437-8564-4590-A8A5-D4E14D957993' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'558C4437-8564-4590-A8A5-D4E14D957993', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='18D4ABE7-8A41-4762-9C0D-8934499A461E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'18D4ABE7-8A41-4762-9C0D-8934499A461E', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00012' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00012'

	-- CustomerBoundaryType - [AutomationSeedCustomer00012]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B2A9C8BF-FD2C-442C-B487-29E937D54643'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B2A9C8BF-FD2C-442C-B487-29E937D54643', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'6703B77E-712F-4BB4-AF4D-635A47A810C1'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'6703B77E-712F-4BB4-AF4D-635A47A810C1', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'91453780-CDF1-4B8D-B651-AD0EF639E16C'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'91453780-CDF1-4B8D-B651-AD0EF639E16C', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8F094071-56FC-4447-9775-C8926F6EB593'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8F094071-56FC-4447-9775-C8926F6EB593', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00012]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D4D7453B-7887-410C-BE7B-17FF34139DCF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D4D7453B-7887-410C-BE7B-17FF34139DCF', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CA101B14-78F4-449A-939D-ACF0DCDF2DDB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CA101B14-78F4-449A-939D-ACF0DCDF2DDB', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F0FCF583-62EA-4D03-8FDB-565A2679C145' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F0FCF583-62EA-4D03-8FDB-565A2679C145', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='58D55D1F-A8C4-43AD-86F1-0C63C54E3A05' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'58D55D1F-A8C4-43AD-86F1-0C63C54E3A05', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='51761241-835F-4F46-A541-809718E12AF1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'51761241-835F-4F46-A541-809718E12AF1', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F1E30EB0-16A9-4A9C-B1E4-D7D63048B09A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F1E30EB0-16A9-4A9C-B1E4-D7D63048B09A', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00013' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00013'

	-- CustomerBoundaryType - [AutomationSeedCustomer00013]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'91B4E5F4-AE17-4262-9E92-D9864F763EF9'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'91B4E5F4-AE17-4262-9E92-D9864F763EF9', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'0305332C-0B9B-4717-98AA-6400F9FA0BF0'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'0305332C-0B9B-4717-98AA-6400F9FA0BF0', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'996FAEF7-2346-4A14-ACC2-EC73F5F78B8D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'996FAEF7-2346-4A14-ACC2-EC73F5F78B8D', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'D3D1CC1F-F0AC-49F2-A676-B8F97DFC3545'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'D3D1CC1F-F0AC-49F2-A676-B8F97DFC3545', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00013]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='586E6CBA-781D-4E77-A4DD-E3BAC21149B4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'586E6CBA-781D-4E77-A4DD-E3BAC21149B4', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5F36D364-4626-4189-9746-52B08B09AB59' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5F36D364-4626-4189-9746-52B08B09AB59', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='46E8B25C-CA8E-47B2-BF7F-F7E2347C04B9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'46E8B25C-CA8E-47B2-BF7F-F7E2347C04B9', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='59F64857-FAD3-4425-B050-00295EA0DCB9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'59F64857-FAD3-4425-B050-00295EA0DCB9', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8793C163-555F-4B5F-B969-50AD4789606F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8793C163-555F-4B5F-B969-50AD4789606F', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F7AD99F6-4D30-4790-BAA6-11EA070269F6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F7AD99F6-4D30-4790-BAA6-11EA070269F6', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00014' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00014'

	-- CustomerBoundaryType - [AutomationSeedCustomer00014]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'632A379F-D1D0-44D5-B1CE-506EA4F56861'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'632A379F-D1D0-44D5-B1CE-506EA4F56861', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'16CEBBB8-6522-4F55-94FF-7825B8A7A879'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'16CEBBB8-6522-4F55-94FF-7825B8A7A879', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'D3422990-F354-4285-85A9-3FC7CE3B8B43'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'D3422990-F354-4285-85A9-3FC7CE3B8B43', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'0ED37536-7307-405C-91C0-F5EBA1EBA375'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'0ED37536-7307-405C-91C0-F5EBA1EBA375', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00014]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BCD5F511-F88A-4141-9C52-4E659804425C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BCD5F511-F88A-4141-9C52-4E659804425C', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B8FE73C9-5328-4DBD-8A8B-A11C283C68C3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B8FE73C9-5328-4DBD-8A8B-A11C283C68C3', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6AC47D2E-1AB4-461D-9631-A7BE815D1A08' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6AC47D2E-1AB4-461D-9631-A7BE815D1A08', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='58CDB072-515E-47F0-9C23-B4960AF89629' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'58CDB072-515E-47F0-9C23-B4960AF89629', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B8135DDD-8EEF-4A04-9A4B-33F2D5B66CCE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B8135DDD-8EEF-4A04-9A4B-33F2D5B66CCE', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='20E4B79F-E9CF-4460-919B-609DF75E415D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'20E4B79F-E9CF-4460-919B-609DF75E415D', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00015' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00015'

	-- CustomerBoundaryType - [AutomationSeedCustomer00015]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BB5AB42A-1870-4AA8-BC03-B833A92D4317'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BB5AB42A-1870-4AA8-BC03-B833A92D4317', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'550718AB-A32B-4A73-93BF-04D543A47BF9'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'550718AB-A32B-4A73-93BF-04D543A47BF9', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'17FA9111-DECC-4864-82B6-166C9C6E5C76'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'17FA9111-DECC-4864-82B6-166C9C6E5C76', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'AFCF240C-71C0-46C7-9652-B1472DEEAF89'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'AFCF240C-71C0-46C7-9652-B1472DEEAF89', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00015]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5E43EF0A-80F8-4A4E-B50E-56F221589AE0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5E43EF0A-80F8-4A4E-B50E-56F221589AE0', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A0B1C79C-8FA8-408A-87F8-7AF02EF2E118' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A0B1C79C-8FA8-408A-87F8-7AF02EF2E118', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='85780F04-FC22-4906-876B-D26E1D1F6BC9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'85780F04-FC22-4906-876B-D26E1D1F6BC9', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='92D4E6EB-23E9-448A-8779-411C8C7D4C0F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'92D4E6EB-23E9-448A-8779-411C8C7D4C0F', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0B307AEE-763F-4AA9-874E-E2A0735C311F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0B307AEE-763F-4AA9-874E-E2A0735C311F', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6FCF6908-7612-434D-9CC3-6757118486D9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6FCF6908-7612-434D-9CC3-6757118486D9', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00016' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00016'

	-- CustomerBoundaryType - [AutomationSeedCustomer00016]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'DCC7FBD3-6EDF-4755-9E92-094051F46190'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'DCC7FBD3-6EDF-4755-9E92-094051F46190', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'5706842C-37D9-4FBC-8C5E-C12D27D6C617'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'5706842C-37D9-4FBC-8C5E-C12D27D6C617', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7BA7B194-DC4C-495E-9161-3C83F5236F7C'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7BA7B194-DC4C-495E-9161-3C83F5236F7C', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'DBAB6C78-FEBF-46A1-AD20-0ED6C58BC533'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'DBAB6C78-FEBF-46A1-AD20-0ED6C58BC533', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00016]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EBCAAB47-A6A0-4F4C-A64A-7FF0759288CA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EBCAAB47-A6A0-4F4C-A64A-7FF0759288CA', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='51924348-9469-4206-9665-FF7D1F572FAD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'51924348-9469-4206-9665-FF7D1F572FAD', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E8CB803E-3901-4EB9-84AC-AB3603D0761A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E8CB803E-3901-4EB9-84AC-AB3603D0761A', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='81DA8ECC-B1CE-4B3E-9754-ACA4E073222B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'81DA8ECC-B1CE-4B3E-9754-ACA4E073222B', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F17D1124-E9DC-417D-B6E3-5329C2FD59A3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F17D1124-E9DC-417D-B6E3-5329C2FD59A3', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3E289CDB-9A5E-4678-801E-C5F09726918E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3E289CDB-9A5E-4678-801E-C5F09726918E', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00017' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00017'

	-- CustomerBoundaryType - [AutomationSeedCustomer00017]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BCA266CE-A40B-4019-A72D-03B16B1CD764'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BCA266CE-A40B-4019-A72D-03B16B1CD764', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'DF6CD5B7-0EB1-4125-B084-94BE8521B607'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'DF6CD5B7-0EB1-4125-B084-94BE8521B607', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B631FD38-ADDA-41EB-A1EC-0FC274024880'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B631FD38-ADDA-41EB-A1EC-0FC274024880', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'DB66FDDE-4320-4840-A644-2135DE71DC3B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'DB66FDDE-4320-4840-A644-2135DE71DC3B', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00017]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B8B78ADE-7EF0-400F-A11C-BC8B94C2537E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B8B78ADE-7EF0-400F-A11C-BC8B94C2537E', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1D1C7157-39C6-4749-8C4A-9A6743A8B9FA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1D1C7157-39C6-4749-8C4A-9A6743A8B9FA', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1D445EF0-98EF-4787-B519-8891ABEA25E8' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1D445EF0-98EF-4787-B519-8891ABEA25E8', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7BCB2311-E0DA-4704-91C5-C6D9D43DF9D2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7BCB2311-E0DA-4704-91C5-C6D9D43DF9D2', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='308E8902-1528-4020-9963-5AAAD6F0A94C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'308E8902-1528-4020-9963-5AAAD6F0A94C', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E8B464FB-6935-43E4-BF23-1136B62F67DF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E8B464FB-6935-43E4-BF23-1136B62F67DF', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00018' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00018'

	-- CustomerBoundaryType - [AutomationSeedCustomer00018]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'56A30628-9BDD-4595-8FF0-BD16C44417C9'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'56A30628-9BDD-4595-8FF0-BD16C44417C9', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'6628DCE1-9F8D-4474-8491-473795A9A571'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'6628DCE1-9F8D-4474-8491-473795A9A571', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'C7682A0B-E8B3-44E3-99F3-3BA3AC14484E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'C7682A0B-E8B3-44E3-99F3-3BA3AC14484E', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'12DD885B-2EC1-47DB-AC99-85C44C615594'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'12DD885B-2EC1-47DB-AC99-85C44C615594', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00018]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='41ACB83A-0263-43D0-8752-7C7F0D699A48' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'41ACB83A-0263-43D0-8752-7C7F0D699A48', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3828467B-8C26-4E21-B821-94816490CE79' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3828467B-8C26-4E21-B821-94816490CE79', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='275D5597-92C3-4240-961B-347233D3F3A0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'275D5597-92C3-4240-961B-347233D3F3A0', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='075C3A3B-24B1-42F0-A425-FD0C773F162A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'075C3A3B-24B1-42F0-A425-FD0C773F162A', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B97F8681-D32D-4157-A937-73A6FFE50CFD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B97F8681-D32D-4157-A937-73A6FFE50CFD', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='803254D3-A888-4B8E-AFE6-57CCB79E5A52' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'803254D3-A888-4B8E-AFE6-57CCB79E5A52', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00019' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00019'

	-- CustomerBoundaryType - [AutomationSeedCustomer00019]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'636018E0-F367-4895-B07B-211BBEE00279'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'636018E0-F367-4895-B07B-211BBEE00279', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'31C36B08-D396-4F2A-BF74-3D59E788DCA0'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'31C36B08-D396-4F2A-BF74-3D59E788DCA0', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'59D546DC-D933-4505-8223-74C06442E662'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'59D546DC-D933-4505-8223-74C06442E662', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'5B39187D-D0DF-4745-8459-97EC871965F4'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'5B39187D-D0DF-4745-8459-97EC871965F4', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00019]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7D9A4DC0-12BD-4171-9D05-73801EBD78C2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7D9A4DC0-12BD-4171-9D05-73801EBD78C2', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DE3C3E43-5846-4F7F-8B33-B3AB674D398A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DE3C3E43-5846-4F7F-8B33-B3AB674D398A', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CF9235A5-E5F6-4126-B1A3-EEDDE454B05E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CF9235A5-E5F6-4126-B1A3-EEDDE454B05E', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='18CB7F4D-DFC3-4200-A05E-AE357BF84FDC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'18CB7F4D-DFC3-4200-A05E-AE357BF84FDC', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C9336F08-622E-45FA-8905-9488E43BB367' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C9336F08-622E-45FA-8905-9488E43BB367', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='29596972-5ACF-4EFB-A4B4-37D3325081E3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'29596972-5ACF-4EFB-A4B4-37D3325081E3', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00020' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00020'

	-- CustomerBoundaryType - [AutomationSeedCustomer00020]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'18B78918-FC9A-4AC4-B085-970B41506543'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'18B78918-FC9A-4AC4-B085-970B41506543', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F25B483E-8B91-47CD-83A0-38D7D045A9B3'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F25B483E-8B91-47CD-83A0-38D7D045A9B3', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'EB66CA7F-B685-4784-B3AC-5F7481FDB3FC'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'EB66CA7F-B685-4784-B3AC-5F7481FDB3FC', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9FA5C4AE-2050-49FD-963F-B2E64412DFF4'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9FA5C4AE-2050-49FD-963F-B2E64412DFF4', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00020]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3965A703-F58D-4AC1-9CCC-7555674067A0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3965A703-F58D-4AC1-9CCC-7555674067A0', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8B0A61FB-DEFF-4E04-A9B1-A08E9AD7C1A4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8B0A61FB-DEFF-4E04-A9B1-A08E9AD7C1A4', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3A1610A4-06BD-43D0-A39A-EFEB25C36E98' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3A1610A4-06BD-43D0-A39A-EFEB25C36E98', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='54197197-59B8-440F-BC00-536F06DCB85B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'54197197-59B8-440F-BC00-536F06DCB85B', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1B26E313-603B-4ED2-AC64-C61D933D5496' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1B26E313-603B-4ED2-AC64-C61D933D5496', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1A8AC1FA-ACA5-4127-A29D-1B919262A15A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1A8AC1FA-ACA5-4127-A29D-1B919262A15A', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00021' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00021'

	-- CustomerBoundaryType - [AutomationSeedCustomer00021]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F6793A1A-5C8C-474B-A3D8-18626DACC556'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F6793A1A-5C8C-474B-A3D8-18626DACC556', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'31C0816D-26DC-4BC1-80BC-A404DCCE6F44'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'31C0816D-26DC-4BC1-80BC-A404DCCE6F44', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9E38E778-2DE6-41B5-9856-A4B1B3734F9D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9E38E778-2DE6-41B5-9856-A4B1B3734F9D', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B0F1F089-9673-4EEE-A040-E3C9C9F8E08A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B0F1F089-9673-4EEE-A040-E3C9C9F8E08A', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00021]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8B55EEF8-83ED-4C49-9DB6-BB779D19E3FF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8B55EEF8-83ED-4C49-9DB6-BB779D19E3FF', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6D220AD5-99E7-49D7-B3DE-757CADB7C61D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6D220AD5-99E7-49D7-B3DE-757CADB7C61D', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0E2C0637-E7E3-401A-9710-754BF69203D2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0E2C0637-E7E3-401A-9710-754BF69203D2', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A1EA5664-1BC3-4B38-B878-6C94BBE336C4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A1EA5664-1BC3-4B38-B878-6C94BBE336C4', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A4A3BAE7-EB3C-4CB2-A84A-FB9C1D85E73F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A4A3BAE7-EB3C-4CB2-A84A-FB9C1D85E73F', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='175B1E9B-6921-43AD-9270-56F1D5DA9F62' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'175B1E9B-6921-43AD-9270-56F1D5DA9F62', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00022' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00022'

	-- CustomerBoundaryType - [AutomationSeedCustomer00022]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'720D029A-80BA-4EBB-B8AA-7482F020EA95'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'720D029A-80BA-4EBB-B8AA-7482F020EA95', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'42AC527B-A0EE-4F12-9A12-07887C67A6E3'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'42AC527B-A0EE-4F12-9A12-07887C67A6E3', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'C3FC45AD-9398-48BD-B8C1-BA22E5746646'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'C3FC45AD-9398-48BD-B8C1-BA22E5746646', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'6E6F6007-2865-44D6-89EA-094DC96B8739'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'6E6F6007-2865-44D6-89EA-094DC96B8739', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00022]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='79AB09DF-C5F0-45C0-A68D-7BC09013B1BB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'79AB09DF-C5F0-45C0-A68D-7BC09013B1BB', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5DF04838-D1F6-485E-8DA5-4EE818AA8937' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5DF04838-D1F6-485E-8DA5-4EE818AA8937', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='83342C41-83B5-4EEF-BDD2-EFC33BAF9A25' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'83342C41-83B5-4EEF-BDD2-EFC33BAF9A25', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='294DD862-6B89-48E8-8EE3-18A02FEF7E7C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'294DD862-6B89-48E8-8EE3-18A02FEF7E7C', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F1B74651-3FC0-4B45-A0BD-108D0CF1AF0D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F1B74651-3FC0-4B45-A0BD-108D0CF1AF0D', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0E2738D9-C4AC-437F-9FA7-2FB2015EA495' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0E2738D9-C4AC-437F-9FA7-2FB2015EA495', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00023' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00023'

	-- CustomerBoundaryType - [AutomationSeedCustomer00023]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E6692B6E-E868-4310-9A09-5651B52490F0'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E6692B6E-E868-4310-9A09-5651B52490F0', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'263F9C2F-A298-417B-ADF3-1583D93D89BE'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'263F9C2F-A298-417B-ADF3-1583D93D89BE', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8C33ACD9-0E50-4C0B-95D1-5045D78862A5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8C33ACD9-0E50-4C0B-95D1-5045D78862A5', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'517EAA14-4550-4EAF-9219-12F5B11DAD3A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'517EAA14-4550-4EAF-9219-12F5B11DAD3A', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00023]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A57F8EA4-9B98-4D59-A29E-8AE367E1B2F1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A57F8EA4-9B98-4D59-A29E-8AE367E1B2F1', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='65361AD1-0ED6-4B5F-AD78-59A6724FD106' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'65361AD1-0ED6-4B5F-AD78-59A6724FD106', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='65E05648-76B8-4C3F-8FF4-C57EC9C9113C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'65E05648-76B8-4C3F-8FF4-C57EC9C9113C', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FAAE37A3-0EF8-44B8-90E7-584CD0A40D4A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FAAE37A3-0EF8-44B8-90E7-584CD0A40D4A', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7C961536-0919-4338-8640-52D9C0A60BC6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7C961536-0919-4338-8640-52D9C0A60BC6', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5657EAEB-E528-4DEA-B9D9-99EE118E67F2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5657EAEB-E528-4DEA-B9D9-99EE118E67F2', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00024' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00024'

	-- CustomerBoundaryType - [AutomationSeedCustomer00024]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'FDEE9E75-ADC3-4A28-A16F-AE1B1A88A025'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'FDEE9E75-ADC3-4A28-A16F-AE1B1A88A025', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A88DDB25-5FA2-4907-A61F-835F531B7BF8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A88DDB25-5FA2-4907-A61F-835F531B7BF8', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'1CAC5225-F296-4CE1-B95C-BF51947BDB95'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'1CAC5225-F296-4CE1-B95C-BF51947BDB95', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'061BECE2-BD6F-4861-9D2D-1AC68BF16CD0'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'061BECE2-BD6F-4861-9D2D-1AC68BF16CD0', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00024]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D0B4BFD1-2CAE-4602-BDCD-B4FA01831BA0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D0B4BFD1-2CAE-4602-BDCD-B4FA01831BA0', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7FA08113-141A-4A36-9BEC-D151102478E3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7FA08113-141A-4A36-9BEC-D151102478E3', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5795DA3D-71B9-4E7D-ADBA-72E876EF3F2C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5795DA3D-71B9-4E7D-ADBA-72E876EF3F2C', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='684648D1-D6E8-4F9A-B86F-7C6F43C7A110' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'684648D1-D6E8-4F9A-B86F-7C6F43C7A110', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BDDEEDC6-00FA-4101-BB40-0FAE16C2DE8D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BDDEEDC6-00FA-4101-BB40-0FAE16C2DE8D', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C5530661-DEDB-441F-833E-C56EDAC65D56' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C5530661-DEDB-441F-833E-C56EDAC65D56', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00025' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00025'

	-- CustomerBoundaryType - [AutomationSeedCustomer00025]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4906E6FB-314C-4C57-AA9E-BFD64E4E017A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4906E6FB-314C-4C57-AA9E-BFD64E4E017A', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'DDB9418F-84FA-4B8C-9557-36D39438FE20'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'DDB9418F-84FA-4B8C-9557-36D39438FE20', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BAF1C4F4-C7F4-4DB1-B482-FD13156B8B4E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BAF1C4F4-C7F4-4DB1-B482-FD13156B8B4E', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'29976EA1-77E5-4763-954C-49D5D8D5FCC6'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'29976EA1-77E5-4763-954C-49D5D8D5FCC6', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00025]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A2C4FB24-B57D-44DA-97DE-190899A49626' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A2C4FB24-B57D-44DA-97DE-190899A49626', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3F6B570C-85C1-446A-AE81-593B243DD76C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3F6B570C-85C1-446A-AE81-593B243DD76C', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='21CA044B-CC4C-4437-8799-747A2EB680C9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'21CA044B-CC4C-4437-8799-747A2EB680C9', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DAD27591-FA4C-4342-9BB6-CDF94702499A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DAD27591-FA4C-4342-9BB6-CDF94702499A', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FDB13374-75FB-49FD-942C-1A074418D681' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FDB13374-75FB-49FD-942C-1A074418D681', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E747FCFB-4B00-4274-A8A5-2104A12272D7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E747FCFB-4B00-4274-A8A5-2104A12272D7', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00026' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00026'

	-- CustomerBoundaryType - [AutomationSeedCustomer00026]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F3FDFD1C-AE9C-41C6-80E2-9F66956D7D18'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F3FDFD1C-AE9C-41C6-80E2-9F66956D7D18', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B10D2B9A-3D38-4789-9E70-DD89036F5796'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B10D2B9A-3D38-4789-9E70-DD89036F5796', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'41FB4D58-19AE-4ECE-A7D6-17D00D5DC6B5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'41FB4D58-19AE-4ECE-A7D6-17D00D5DC6B5', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'1EF4A518-90DC-45B8-AA92-F58986A55738'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'1EF4A518-90DC-45B8-AA92-F58986A55738', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00026]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CC4A4B09-CE44-4033-8D74-043812E8F772' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CC4A4B09-CE44-4033-8D74-043812E8F772', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2B346196-5D0D-44CF-9155-9C2834EAB9F4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2B346196-5D0D-44CF-9155-9C2834EAB9F4', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2673ACE9-A26F-4180-AAD7-D116822D5848' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2673ACE9-A26F-4180-AAD7-D116822D5848', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1EF857E9-DECC-4840-B77C-D2E62D15981B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1EF857E9-DECC-4840-B77C-D2E62D15981B', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0885113C-AADB-41E1-802F-363DBDB01DEB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0885113C-AADB-41E1-802F-363DBDB01DEB', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='780A14C4-F553-44CB-A263-435F1C923C66' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'780A14C4-F553-44CB-A263-435F1C923C66', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00027' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00027'

	-- CustomerBoundaryType - [AutomationSeedCustomer00027]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'42BE701F-3837-4720-9069-6AB4B67B0949'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'42BE701F-3837-4720-9069-6AB4B67B0949', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4B9D451E-CE91-4191-B6FB-5664CC7A1E54'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4B9D451E-CE91-4191-B6FB-5664CC7A1E54', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'3BB6AE8E-2571-4A08-9347-8A055EA3CC82'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'3BB6AE8E-2571-4A08-9347-8A055EA3CC82', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'049A838A-53C7-48BC-A213-E81424391705'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'049A838A-53C7-48BC-A213-E81424391705', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00027]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='59676E7E-0977-4587-AB0C-1E0F7BF77A63' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'59676E7E-0977-4587-AB0C-1E0F7BF77A63', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='86232F2E-E090-472E-9D45-4A5274A7F55B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'86232F2E-E090-472E-9D45-4A5274A7F55B', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6A8D48B7-A129-4FC2-BD95-5FBFBF4216BE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6A8D48B7-A129-4FC2-BD95-5FBFBF4216BE', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7A95D069-BB08-42D4-808F-E4D852E3ABEE' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7A95D069-BB08-42D4-808F-E4D852E3ABEE', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0F1DD3C1-402F-4E4E-9AD2-AF1F366021CF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0F1DD3C1-402F-4E4E-9AD2-AF1F366021CF', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E854FE0C-6DD9-4099-AB9B-0F799EA2370F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E854FE0C-6DD9-4099-AB9B-0F799EA2370F', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00028' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00028'

	-- CustomerBoundaryType - [AutomationSeedCustomer00028]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'11185A63-4F53-4540-A7B6-4C00815CB18F'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'11185A63-4F53-4540-A7B6-4C00815CB18F', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'02617B22-E386-4702-A756-F647C2B97B70'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'02617B22-E386-4702-A756-F647C2B97B70', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'52E0AF48-83F9-462F-B729-AEC5F1B9AFE2'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'52E0AF48-83F9-462F-B729-AEC5F1B9AFE2', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'FFD6CA70-B6E2-43AB-A0A4-E32C081D6539'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'FFD6CA70-B6E2-43AB-A0A4-E32C081D6539', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00028]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B937790D-8969-4F37-85F4-702F80A418BF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B937790D-8969-4F37-85F4-702F80A418BF', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='3604EC62-562E-45D8-9405-47931DEFF29E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'3604EC62-562E-45D8-9405-47931DEFF29E', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D634FAAA-51A8-45D5-81FB-19E379E77959' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D634FAAA-51A8-45D5-81FB-19E379E77959', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A83E88EE-9637-4883-87D6-278B75E0596E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A83E88EE-9637-4883-87D6-278B75E0596E', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='00EB8B68-2454-4827-80F0-BBF19829ACBC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'00EB8B68-2454-4827-80F0-BBF19829ACBC', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='857CAAFD-4AD6-41EE-A14D-416A19E55AA1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'857CAAFD-4AD6-41EE-A14D-416A19E55AA1', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00029' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00029'

	-- CustomerBoundaryType - [AutomationSeedCustomer00029]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A91381CC-7CFC-441D-B466-7D58608CB4BD'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A91381CC-7CFC-441D-B466-7D58608CB4BD', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'01783701-E1BB-4C8F-8FE1-0D2C9C7083BB'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'01783701-E1BB-4C8F-8FE1-0D2C9C7083BB', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4E05C0C7-1F32-4DF5-8800-4BBEF9A6F578'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4E05C0C7-1F32-4DF5-8800-4BBEF9A6F578', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'470974A7-9D82-462B-A081-7239C17D888E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'470974A7-9D82-462B-A081-7239C17D888E', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00029]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C3AAE277-7C71-4130-A9C0-0BFDF5FC79ED' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C3AAE277-7C71-4130-A9C0-0BFDF5FC79ED', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C2B19B71-EC0D-461D-B580-A04B691F69FA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C2B19B71-EC0D-461D-B580-A04B691F69FA', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6A3F7BF0-E0AC-4AC1-9DDB-652D20339B83' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6A3F7BF0-E0AC-4AC1-9DDB-652D20339B83', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D7721066-1976-4CD1-8D21-89199A951BC1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D7721066-1976-4CD1-8D21-89199A951BC1', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8A4DF92C-916A-43ED-BC3E-C8074D5427DF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8A4DF92C-916A-43ED-BC3E-C8074D5427DF', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='976D0FE6-76FC-44A6-A824-7DB3BD0D184F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'976D0FE6-76FC-44A6-A824-7DB3BD0D184F', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00030' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00030'

	-- CustomerBoundaryType - [AutomationSeedCustomer00030]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'99214463-C4FA-40D9-84EF-153F9DA1660C'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'99214463-C4FA-40D9-84EF-153F9DA1660C', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'0CF3B8D4-EAFB-48D5-B9A2-04F529B1DE07'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'0CF3B8D4-EAFB-48D5-B9A2-04F529B1DE07', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'301EF9A6-4EA0-4E62-BDFE-E851EEC1BC29'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'301EF9A6-4EA0-4E62-BDFE-E851EEC1BC29', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'791CF11F-2CED-487E-8A3E-56D131761892'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'791CF11F-2CED-487E-8A3E-56D131761892', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00030]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B1C24591-A4D2-4C8C-B5EB-8D0EF27040F8' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B1C24591-A4D2-4C8C-B5EB-8D0EF27040F8', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A82570C0-D407-4BDA-83EA-22D6AD9A09B3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A82570C0-D407-4BDA-83EA-22D6AD9A09B3', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E2D32371-53C1-4E1E-A92A-4B1EBD2F074C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E2D32371-53C1-4E1E-A92A-4B1EBD2F074C', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='26EC33B1-3B5B-4A9A-B81A-81319DEC8F87' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'26EC33B1-3B5B-4A9A-B81A-81319DEC8F87', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E6643DB8-84A7-4142-8AD6-ABDCF6D45281' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E6643DB8-84A7-4142-8AD6-ABDCF6D45281', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E008C8B0-83C5-4A62-BCF9-262EB4E0FADA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E008C8B0-83C5-4A62-BCF9-262EB4E0FADA', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00031' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00031'

	-- CustomerBoundaryType - [AutomationSeedCustomer00031]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'21D1D4C6-CCA4-4039-88AC-C520D99DEDC1'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'21D1D4C6-CCA4-4039-88AC-C520D99DEDC1', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'827DC41A-1200-49FF-A910-BD32858C2A89'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'827DC41A-1200-49FF-A910-BD32858C2A89', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F1F607D6-1280-4285-9A43-40DBD1D94E21'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F1F607D6-1280-4285-9A43-40DBD1D94E21', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'70CC336F-B2EF-48DC-80F7-BB40418C036E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'70CC336F-B2EF-48DC-80F7-BB40418C036E', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00031]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A854EEC7-BB57-4B23-BE06-F7454CC5D897' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A854EEC7-BB57-4B23-BE06-F7454CC5D897', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='42700EE8-D550-443E-A1BB-70F63FD1EC77' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'42700EE8-D550-443E-A1BB-70F63FD1EC77', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C04BC00D-3EE0-4C63-8DBF-D6ED45F9A04E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C04BC00D-3EE0-4C63-8DBF-D6ED45F9A04E', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0D937DEE-B9E8-4E9E-A389-0223622B8082' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0D937DEE-B9E8-4E9E-A389-0223622B8082', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CB813DA7-5B72-4E0B-B32E-A0B724C64E72' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CB813DA7-5B72-4E0B-B32E-A0B724C64E72', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A266580D-7392-4BAE-93D8-47CABA014EC4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A266580D-7392-4BAE-93D8-47CABA014EC4', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00032' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00032'

	-- CustomerBoundaryType - [AutomationSeedCustomer00032]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'79A8DE3F-065A-4D44-BF0A-3D80C35EF461'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'79A8DE3F-065A-4D44-BF0A-3D80C35EF461', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'595BF5F5-19CC-442F-92E0-F293BF5913F3'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'595BF5F5-19CC-442F-92E0-F293BF5913F3', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9F0574FE-FCF1-4239-9CAF-4734C2F8DDF3'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9F0574FE-FCF1-4239-9CAF-4734C2F8DDF3', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'AF97F490-11CB-4856-802F-830168C5D5FE'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'AF97F490-11CB-4856-802F-830168C5D5FE', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00032]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='627B6383-5BB2-4777-88DC-F9E44D604499' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'627B6383-5BB2-4777-88DC-F9E44D604499', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9C35CCF2-EB57-4DDE-928A-CA816AB23A47' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9C35CCF2-EB57-4DDE-928A-CA816AB23A47', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FB8113EA-FDFD-4F2E-AD9C-0D12C08EC6E7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FB8113EA-FDFD-4F2E-AD9C-0D12C08EC6E7', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6F27843A-C0C2-433E-8E07-0B132C0CE504' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6F27843A-C0C2-433E-8E07-0B132C0CE504', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='69F6AD5F-C8BF-413C-A980-A9E5CCB4E04B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'69F6AD5F-C8BF-413C-A980-A9E5CCB4E04B', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='18D00854-2FF8-4B9F-A7DC-0E50B647E087' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'18D00854-2FF8-4B9F-A7DC-0E50B647E087', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00033' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00033'

	-- CustomerBoundaryType - [AutomationSeedCustomer00033]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9FAC7A7C-40CC-441F-8435-753FC2D2DB86'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9FAC7A7C-40CC-441F-8435-753FC2D2DB86', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'5AD6FD07-D0E9-4ED5-B658-0CE3AC1ED5D6'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'5AD6FD07-D0E9-4ED5-B658-0CE3AC1ED5D6', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'76C99F21-8152-4BA1-A581-63197200BF40'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'76C99F21-8152-4BA1-A581-63197200BF40', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'D8C24DC4-D3B9-4928-ACDF-DC99D930A40F'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'D8C24DC4-D3B9-4928-ACDF-DC99D930A40F', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00033]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='ACA5E274-92E0-44B5-913E-9E112ABA54C9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'ACA5E274-92E0-44B5-913E-9E112ABA54C9', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B2D2F761-5203-4606-B0A7-593DF94A7EE1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B2D2F761-5203-4606-B0A7-593DF94A7EE1', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A8430543-2A65-4CC8-AE61-F244530117AB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A8430543-2A65-4CC8-AE61-F244530117AB', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='34BFE9FB-3602-4CD6-A75B-AF389BE007DF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'34BFE9FB-3602-4CD6-A75B-AF389BE007DF', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='64D912C4-6839-4C5C-B044-2297E1BB9F85' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'64D912C4-6839-4C5C-B044-2297E1BB9F85', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7F8E564B-68F2-403C-9E92-E20027FD9457' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7F8E564B-68F2-403C-9E92-E20027FD9457', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00034' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00034'

	-- CustomerBoundaryType - [AutomationSeedCustomer00034]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A2EDF71B-2504-4C3C-A8EB-D59AC900C9C1'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A2EDF71B-2504-4C3C-A8EB-D59AC900C9C1', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8B07CC45-C0A9-4D5E-964D-EBAF1ED4443B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8B07CC45-C0A9-4D5E-964D-EBAF1ED4443B', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E540B08B-1F42-49F3-8A03-CD58E2BA1002'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E540B08B-1F42-49F3-8A03-CD58E2BA1002', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A92B2F48-0D19-43B5-A294-4C01D5895DA0'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A92B2F48-0D19-43B5-A294-4C01D5895DA0', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00034]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='04BD54FC-DC17-4AAE-ACEC-4998E369B94B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'04BD54FC-DC17-4AAE-ACEC-4998E369B94B', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E64D9514-7216-4FBD-964E-C6063FB80BCF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E64D9514-7216-4FBD-964E-C6063FB80BCF', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='584D2C31-E57D-4506-BDDA-DE984392DA1F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'584D2C31-E57D-4506-BDDA-DE984392DA1F', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='91FEF8EC-E068-460C-8A28-695EE9CB1814' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'91FEF8EC-E068-460C-8A28-695EE9CB1814', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FB12B82E-8A28-4095-8EB6-F7193F180D04' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FB12B82E-8A28-4095-8EB6-F7193F180D04', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='48D27BB2-4EEB-4061-A0A6-A7BEEBCE987E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'48D27BB2-4EEB-4061-A0A6-A7BEEBCE987E', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00035' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00035'

	-- CustomerBoundaryType - [AutomationSeedCustomer00035]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'237863ED-3075-4FC1-9B18-4F217E052D20'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'237863ED-3075-4FC1-9B18-4F217E052D20', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'3F4D2B5D-0FCA-40AC-83E8-FEE512C1BCE5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'3F4D2B5D-0FCA-40AC-83E8-FEE512C1BCE5', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'82E68A53-FC01-4466-A65B-1BED8FBCD800'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'82E68A53-FC01-4466-A65B-1BED8FBCD800', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'20464770-38E2-4F2D-91C0-199B03344F5F'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'20464770-38E2-4F2D-91C0-199B03344F5F', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00035]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='77E552F4-9B4D-4112-AEA0-6A729429C058' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'77E552F4-9B4D-4112-AEA0-6A729429C058', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A2D03DB8-5F34-4A46-80F5-7D4316092E43' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A2D03DB8-5F34-4A46-80F5-7D4316092E43', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='101F9017-E6B4-4E09-BEBF-E8703DBA1F76' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'101F9017-E6B4-4E09-BEBF-E8703DBA1F76', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='362323CF-A23D-4271-A662-B19F095AD299' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'362323CF-A23D-4271-A662-B19F095AD299', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='15596844-0D3E-479E-A580-A51DDDB5A604' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'15596844-0D3E-479E-A580-A51DDDB5A604', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DFA5C6E6-DF6A-4F17-ABAE-69E317920F11' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DFA5C6E6-DF6A-4F17-ABAE-69E317920F11', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00036' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00036'

	-- CustomerBoundaryType - [AutomationSeedCustomer00036]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B83D10EA-BD21-415C-ABE9-E7F6930FF2AA'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B83D10EA-BD21-415C-ABE9-E7F6930FF2AA', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'D3CEBF52-7916-4D8B-9335-2A6DE15AD374'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'D3CEBF52-7916-4D8B-9335-2A6DE15AD374', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'366F0306-AE09-486F-B69B-5C3D66454FD8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'366F0306-AE09-486F-B69B-5C3D66454FD8', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'151BDBA3-2444-49C0-B1CC-C834928E1350'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'151BDBA3-2444-49C0-B1CC-C834928E1350', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00036]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='168ADC63-05E2-4453-8479-3C3CABF197A4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'168ADC63-05E2-4453-8479-3C3CABF197A4', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CDD34CEF-70F9-486D-8E39-72D8DF2E82FF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CDD34CEF-70F9-486D-8E39-72D8DF2E82FF', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9ED3D903-9BD1-4250-904A-0999562C13D2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9ED3D903-9BD1-4250-904A-0999562C13D2', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6869A515-F68D-4A61-90C3-CFF80D7CB86C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6869A515-F68D-4A61-90C3-CFF80D7CB86C', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6738DDB6-A852-497F-89D9-C93CD6FAE505' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6738DDB6-A852-497F-89D9-C93CD6FAE505', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DDE4094F-1463-45BD-8482-832F93070C96' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DDE4094F-1463-45BD-8482-832F93070C96', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00037' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00037'

	-- CustomerBoundaryType - [AutomationSeedCustomer00037]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7D449351-BFD1-41A1-B582-80AFCAF5F89A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7D449351-BFD1-41A1-B582-80AFCAF5F89A', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'1268A5CD-4F6F-4CD2-A523-C1163057F8EA'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'1268A5CD-4F6F-4CD2-A523-C1163057F8EA', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'3C3C777F-9290-4E79-81FF-3EF6A6104473'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'3C3C777F-9290-4E79-81FF-3EF6A6104473', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7834ED0C-30CC-4A34-AA34-B83B0216B6D4'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7834ED0C-30CC-4A34-AA34-B83B0216B6D4', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00037]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FE331E87-1696-4D72-8344-EE29DB2C8A7D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FE331E87-1696-4D72-8344-EE29DB2C8A7D', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5859CFB2-323F-4471-8DF1-614F4DCEB4B5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5859CFB2-323F-4471-8DF1-614F4DCEB4B5', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='65760371-53B4-4F3E-A039-5C4F229913E5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'65760371-53B4-4F3E-A039-5C4F229913E5', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C7309019-1185-4132-8BD3-2730AF8E8CC7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C7309019-1185-4132-8BD3-2730AF8E8CC7', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8ABB36CB-05DD-4C69-B3A0-DC838DAD9536' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8ABB36CB-05DD-4C69-B3A0-DC838DAD9536', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A3468784-F2BC-4F31-92A1-0F7C16A0190D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A3468784-F2BC-4F31-92A1-0F7C16A0190D', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00038' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00038'

	-- CustomerBoundaryType - [AutomationSeedCustomer00038]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'47DABBE7-EBB2-4C29-B41B-4A9D6F150251'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'47DABBE7-EBB2-4C29-B41B-4A9D6F150251', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'EB4DDB55-4A6B-4E19-93A1-0283A255E590'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'EB4DDB55-4A6B-4E19-93A1-0283A255E590', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E828013B-4A87-42AA-BF52-42C5444C356D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E828013B-4A87-42AA-BF52-42C5444C356D', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8BAC760F-6228-4479-90E3-12B0796E9B68'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8BAC760F-6228-4479-90E3-12B0796E9B68', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00038]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5E20C7C3-5355-4778-A2F9-E96D3C807471' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5E20C7C3-5355-4778-A2F9-E96D3C807471', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='64A7AFCA-5BB5-4538-8616-01673890753D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'64A7AFCA-5BB5-4538-8616-01673890753D', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='26E3098F-8E93-4C3F-9B37-EE1EADEAAE97' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'26E3098F-8E93-4C3F-9B37-EE1EADEAAE97', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B5519970-7083-4DF8-BD82-AFEED4E481D4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B5519970-7083-4DF8-BD82-AFEED4E481D4', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='63991995-BAA7-43F4-ACC0-11BE459E366D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'63991995-BAA7-43F4-ACC0-11BE459E366D', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A55A75EC-C040-4D7B-A7A2-6183E6453393' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A55A75EC-C040-4D7B-A7A2-6183E6453393', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00039' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00039'

	-- CustomerBoundaryType - [AutomationSeedCustomer00039]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'45A64AFE-A183-4686-B58C-9D7E5123B1EA'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'45A64AFE-A183-4686-B58C-9D7E5123B1EA', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'AB23979E-6FA4-4E8F-895F-6FB4BD051172'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'AB23979E-6FA4-4E8F-895F-6FB4BD051172', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F8ECE5C2-4AFF-4222-BC8C-079AD36795C5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F8ECE5C2-4AFF-4222-BC8C-079AD36795C5', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'00893EE7-BA50-4591-BB04-02BA06C72809'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'00893EE7-BA50-4591-BB04-02BA06C72809', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00039]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C4331D91-B52C-41B8-AA12-C4C25A3E737F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C4331D91-B52C-41B8-AA12-C4C25A3E737F', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9ECC6CA0-1EAE-4A50-9393-E7EE6A1DB52D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9ECC6CA0-1EAE-4A50-9393-E7EE6A1DB52D', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C658F723-76CC-4E02-8BC1-B3FD3DBCA703' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C658F723-76CC-4E02-8BC1-B3FD3DBCA703', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='44DAFBE7-F873-4531-9262-4592566FF3AD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'44DAFBE7-F873-4531-9262-4592566FF3AD', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='19A4A6C8-9E79-4A0E-B43A-1B1998C15EFA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'19A4A6C8-9E79-4A0E-B43A-1B1998C15EFA', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7A7DC8CA-DC6D-4395-AEA1-47627F0B78D1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7A7DC8CA-DC6D-4395-AEA1-47627F0B78D1', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00040' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00040'

	-- CustomerBoundaryType - [AutomationSeedCustomer00040]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F6CC8D24-2519-497D-A304-70F04F575AFB'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F6CC8D24-2519-497D-A304-70F04F575AFB', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B859BC56-34C9-470B-A72F-913C9D3CB80F'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B859BC56-34C9-470B-A72F-913C9D3CB80F', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'AE0C9DDC-8F0D-42C2-A928-A4BA6EB12645'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'AE0C9DDC-8F0D-42C2-A928-A4BA6EB12645', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'0207AAAF-7676-4585-931D-D95497BC36C3'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'0207AAAF-7676-4585-931D-D95497BC36C3', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00040]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1BBBFBE8-60DF-426A-A09E-98F5546D6B64' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1BBBFBE8-60DF-426A-A09E-98F5546D6B64', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='70AC86A1-6CD2-450B-B73B-D0CE2993FCFD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'70AC86A1-6CD2-450B-B73B-D0CE2993FCFD', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2341EE2F-BFDB-4F19-A399-8704441DC2EA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2341EE2F-BFDB-4F19-A399-8704441DC2EA', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0A9074D9-C0F7-4726-A90E-439E47616A0A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0A9074D9-C0F7-4726-A90E-439E47616A0A', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='97C500F2-F956-4530-929F-BF097A96D588' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'97C500F2-F956-4530-929F-BF097A96D588', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='335968BA-48FC-488C-9040-482690733844' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'335968BA-48FC-488C-9040-482690733844', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00041' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00041'

	-- CustomerBoundaryType - [AutomationSeedCustomer00041]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'C27A69D0-B49B-42E2-84E8-0B29AE57BDE5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'C27A69D0-B49B-42E2-84E8-0B29AE57BDE5', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BFBB39F7-0F3F-41EF-A42C-2DD86A9C69F9'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BFBB39F7-0F3F-41EF-A42C-2DD86A9C69F9', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'66927641-9D34-4B36-8252-CC90D2AD0591'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'66927641-9D34-4B36-8252-CC90D2AD0591', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'FEB40362-307C-434E-B506-AE801F94A493'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'FEB40362-307C-434E-B506-AE801F94A493', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00041]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F34BA268-CE51-47D8-AC7B-5E97DF069B25' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F34BA268-CE51-47D8-AC7B-5E97DF069B25', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='845B6393-8CF6-4EC9-979A-BF1C0BCAD142' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'845B6393-8CF6-4EC9-979A-BF1C0BCAD142', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D98BD3CC-B0BC-41D9-B3AA-92E02A870D07' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D98BD3CC-B0BC-41D9-B3AA-92E02A870D07', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5078C533-7CF9-4098-A2D5-1C21CCE6C5AB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5078C533-7CF9-4098-A2D5-1C21CCE6C5AB', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='03AA0A5B-2C48-4F0A-9168-35AB1510EEF7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'03AA0A5B-2C48-4F0A-9168-35AB1510EEF7', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='014721D2-11E1-4921-92BF-9B5B8220002E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'014721D2-11E1-4921-92BF-9B5B8220002E', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00042' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00042'

	-- CustomerBoundaryType - [AutomationSeedCustomer00042]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'29F59CC1-A7EC-421E-B0AA-F2E83568B2CD'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'29F59CC1-A7EC-421E-B0AA-F2E83568B2CD', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'D0F25181-AFCB-4199-A29D-F4FA7BD52FF0'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'D0F25181-AFCB-4199-A29D-F4FA7BD52FF0', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'09657848-69AE-48C9-BB66-356C73A4A8C9'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'09657848-69AE-48C9-BB66-356C73A4A8C9', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'63EC9A84-6537-4C6F-AEF4-61AF51A73C5D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'63EC9A84-6537-4C6F-AEF4-61AF51A73C5D', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00042]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='673CFDEC-DD9F-4072-B57E-144488007F37' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'673CFDEC-DD9F-4072-B57E-144488007F37', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F9BB9AE4-4615-4766-A15C-BC820B9ECA56' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F9BB9AE4-4615-4766-A15C-BC820B9ECA56', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='59336D14-10EE-49FD-BF55-202F6E6A16C9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'59336D14-10EE-49FD-BF55-202F6E6A16C9', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='32696918-8530-48AC-B870-DC7650862992' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'32696918-8530-48AC-B870-DC7650862992', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DB174F5B-4C6A-4BE0-85E1-CBB103565601' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DB174F5B-4C6A-4BE0-85E1-CBB103565601', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='15C65CEE-F9A8-4CF2-843A-6E5B511E85DD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'15C65CEE-F9A8-4CF2-843A-6E5B511E85DD', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00043' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00043'

	-- CustomerBoundaryType - [AutomationSeedCustomer00043]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'698A5B11-6A33-466E-9A55-DE59B3803A3D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'698A5B11-6A33-466E-9A55-DE59B3803A3D', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'3D4ABFFC-4468-4E8C-AE87-C9C23C1A55BC'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'3D4ABFFC-4468-4E8C-AE87-C9C23C1A55BC', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'73964EF5-E705-4BE7-93C8-F0F1B2880C39'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'73964EF5-E705-4BE7-93C8-F0F1B2880C39', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B7BA1004-705A-4BD1-B83A-C62CA16AD0E2'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B7BA1004-705A-4BD1-B83A-C62CA16AD0E2', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00043]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E049531B-B584-45D4-8805-FADE712F5728' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E049531B-B584-45D4-8805-FADE712F5728', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A26885DF-8902-445C-AEF3-3588006A6A48' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A26885DF-8902-445C-AEF3-3588006A6A48', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='94D0EB85-DC80-4FCC-A478-5449413C035B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'94D0EB85-DC80-4FCC-A478-5449413C035B', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='06DB1385-5C64-433B-9CA9-759420119DCB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'06DB1385-5C64-433B-9CA9-759420119DCB', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A5A1AB24-369B-4FFB-B24E-FF790BD1042A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A5A1AB24-369B-4FFB-B24E-FF790BD1042A', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F9A3A78A-F519-4D36-9DA1-640B57078FA6' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F9A3A78A-F519-4D36-9DA1-640B57078FA6', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00044' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00044'

	-- CustomerBoundaryType - [AutomationSeedCustomer00044]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'B51DDD80-DE17-4B7A-872C-A487364ED2AA'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'B51DDD80-DE17-4B7A-872C-A487364ED2AA', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7F75864C-7F27-45FE-B505-9C28687BC065'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7F75864C-7F27-45FE-B505-9C28687BC065', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'AC076C13-12C3-4EEA-8AE3-5CAC8114B770'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'AC076C13-12C3-4EEA-8AE3-5CAC8114B770', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'17FBCCAE-9C3C-44C3-8DCE-BF5280E891C8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'17FBCCAE-9C3C-44C3-8DCE-BF5280E891C8', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00044]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='63F06768-6AA2-45DD-AFB6-2544F57CBC95' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'63F06768-6AA2-45DD-AFB6-2544F57CBC95', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2ECB1F56-8BD4-474F-B9DB-0B52A0D92652' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2ECB1F56-8BD4-474F-B9DB-0B52A0D92652', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='15A8919E-4B21-4078-AE33-F015B16A09C5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'15A8919E-4B21-4078-AE33-F015B16A09C5', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A0AA9F4B-FC96-4AF0-B7AB-CF6F8FD7EDAD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A0AA9F4B-FC96-4AF0-B7AB-CF6F8FD7EDAD', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='679FB321-65C6-4B90-A451-D5294E5F41D0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'679FB321-65C6-4B90-A451-D5294E5F41D0', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='18BF42EE-8B2B-40A2-98B1-5BB8222B6F83' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'18BF42EE-8B2B-40A2-98B1-5BB8222B6F83', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00045' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00045'

	-- CustomerBoundaryType - [AutomationSeedCustomer00045]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'977C96E0-D0D4-47DC-8C57-8DF878C4F84E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'977C96E0-D0D4-47DC-8C57-8DF878C4F84E', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'FCD4A47D-3506-4B2A-91FC-7DAF19EB6D26'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'FCD4A47D-3506-4B2A-91FC-7DAF19EB6D26', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'13DD2EEA-0BFE-449A-A247-C292FE72F3C3'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'13DD2EEA-0BFE-449A-A247-C292FE72F3C3', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BECCAC0B-EE64-4FE4-B48E-5CDAFBFD9BCE'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BECCAC0B-EE64-4FE4-B48E-5CDAFBFD9BCE', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00045]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='87BDD1E2-4649-4E03-89FF-A9BE37994734' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'87BDD1E2-4649-4E03-89FF-A9BE37994734', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='5F317C3E-82D3-4270-AEE9-D03BD8077DF7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'5F317C3E-82D3-4270-AEE9-D03BD8077DF7', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DD967B06-B26C-42F8-8AEF-70AC407D0D01' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DD967B06-B26C-42F8-8AEF-70AC407D0D01', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C3070EDF-6EE7-4805-943D-08F0513B14F3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C3070EDF-6EE7-4805-943D-08F0513B14F3', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C4AC4DA2-CAA3-459C-A15A-B23FA9BB5B0F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C4AC4DA2-CAA3-459C-A15A-B23FA9BB5B0F', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F887F69D-1312-4285-9B84-2627F69238BC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F887F69D-1312-4285-9B84-2627F69238BC', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00046' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00046'

	-- CustomerBoundaryType - [AutomationSeedCustomer00046]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F4435015-2DAD-4147-9A56-85D2B1293FD0'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F4435015-2DAD-4147-9A56-85D2B1293FD0', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'EC6BFE78-0D19-442F-AFF7-E1FDF2EE84DD'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'EC6BFE78-0D19-442F-AFF7-E1FDF2EE84DD', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'0D47F43C-05E4-4240-A14C-F8FA1C7AC2E1'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'0D47F43C-05E4-4240-A14C-F8FA1C7AC2E1', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8D24E50A-A500-4803-90CF-87F042B43598'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8D24E50A-A500-4803-90CF-87F042B43598', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00046]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AC2EC123-4EE2-4DC5-BFF8-B822837526FD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AC2EC123-4EE2-4DC5-BFF8-B822837526FD', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CCE51424-D5B7-456C-9762-B2FA26AD6674' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CCE51424-D5B7-456C-9762-B2FA26AD6674', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='570D2D59-E596-4C8E-BFF0-E1EE9DCF50A3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'570D2D59-E596-4C8E-BFF0-E1EE9DCF50A3', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='794D3522-0944-4A81-AC52-BFA699F01496' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'794D3522-0944-4A81-AC52-BFA699F01496', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='50276368-FFC2-49FC-80F4-1F39397CB504' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'50276368-FFC2-49FC-80F4-1F39397CB504', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='08601FF1-CF79-4173-9672-5CC468B20327' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'08601FF1-CF79-4173-9672-5CC468B20327', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00047' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00047'

	-- CustomerBoundaryType - [AutomationSeedCustomer00047]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'C2D0FB06-83CE-45D7-9B65-2A67F96EABFD'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'C2D0FB06-83CE-45D7-9B65-2A67F96EABFD', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7B440B20-4E38-4408-8A38-418800B80275'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7B440B20-4E38-4408-8A38-418800B80275', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'AFF7E9C4-CC88-48E2-8E13-9EB500DE27C3'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'AFF7E9C4-CC88-48E2-8E13-9EB500DE27C3', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'ED2B04AF-2E57-4BA4-8CD7-0C9E59A8BB1D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'ED2B04AF-2E57-4BA4-8CD7-0C9E59A8BB1D', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00047]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='512B245B-431F-407C-9630-02A0C43FF666' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'512B245B-431F-407C-9630-02A0C43FF666', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AA3B3864-DC2C-489A-AD35-233D43DF9CCC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AA3B3864-DC2C-489A-AD35-233D43DF9CCC', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='31EA430D-6431-44D5-A3B8-18A2E661B9D0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'31EA430D-6431-44D5-A3B8-18A2E661B9D0', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EE2FACCC-D68B-4544-9405-3EE2FCF79FCD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EE2FACCC-D68B-4544-9405-3EE2FCF79FCD', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='2CE6FAB9-556D-4854-924F-B9901BB9AE90' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'2CE6FAB9-556D-4854-924F-B9901BB9AE90', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8EF54A01-B68A-45D0-9C96-260586ED9B2C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8EF54A01-B68A-45D0-9C96-260586ED9B2C', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00048' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00048'

	-- CustomerBoundaryType - [AutomationSeedCustomer00048]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'293D0107-08E9-47E7-9BA8-035191B2EB0C'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'293D0107-08E9-47E7-9BA8-035191B2EB0C', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'408E5373-2158-4871-BDEC-6AEB556150D7'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'408E5373-2158-4871-BDEC-6AEB556150D7', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'CA62B250-FE12-41E2-AB98-5A96A704BB8B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'CA62B250-FE12-41E2-AB98-5A96A704BB8B', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'6F4CECAB-A40F-4B57-97D1-CC0C632EA969'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'6F4CECAB-A40F-4B57-97D1-CC0C632EA969', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00048]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C376F69F-F798-42C9-A707-B2AAD9468606' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C376F69F-F798-42C9-A707-B2AAD9468606', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8BAC613D-4BF3-4EEF-8096-92F07682BE71' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8BAC613D-4BF3-4EEF-8096-92F07682BE71', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='432BBC0F-FB55-403A-B748-219BC31A467D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'432BBC0F-FB55-403A-B748-219BC31A467D', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AE8E0B1C-E701-47B7-A965-3B172EFE02DB' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AE8E0B1C-E701-47B7-A965-3B172EFE02DB', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F02019A8-270A-4FFF-A349-D145EE7AE40B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F02019A8-270A-4FFF-A349-D145EE7AE40B', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4CDD666D-43A6-43A7-9269-646A97487614' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4CDD666D-43A6-43A7-9269-646A97487614', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00049' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00049'

	-- CustomerBoundaryType - [AutomationSeedCustomer00049]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'3E5D86A3-CE73-4450-B2CD-3ECA04CD2E7A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'3E5D86A3-CE73-4450-B2CD-3ECA04CD2E7A', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'71763789-5D06-4E79-9603-E779A67C81D0'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'71763789-5D06-4E79-9603-E779A67C81D0', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'98506FE6-8E39-498D-BA85-55320EFC93F2'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'98506FE6-8E39-498D-BA85-55320EFC93F2', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'CC2A6FAE-3608-493F-AC25-0F8F0317EF11'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'CC2A6FAE-3608-493F-AC25-0F8F0317EF11', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00049]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='F0F18129-0DFD-464F-BAC8-0C5C23EFCD63' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'F0F18129-0DFD-464F-BAC8-0C5C23EFCD63', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C0FE7938-EF9D-4D67-8638-7AAE38BD0619' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C0FE7938-EF9D-4D67-8638-7AAE38BD0619', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='08043D9F-11D6-4861-8912-7209965E90D3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'08043D9F-11D6-4861-8912-7209965E90D3', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='35D4CD01-A335-4EF1-920C-1F635D49436C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'35D4CD01-A335-4EF1-920C-1F635D49436C', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='4F91D7B9-2E43-4303-BC93-B1377DA3213B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'4F91D7B9-2E43-4303-BC93-B1377DA3213B', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EEA5E267-7C42-46BB-8D59-B8407633C115' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EEA5E267-7C42-46BB-8D59-B8407633C115', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00050' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00050'

	-- CustomerBoundaryType - [AutomationSeedCustomer00050]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'5376E138-2CE8-4367-93C6-7F35692DFDFB'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'5376E138-2CE8-4367-93C6-7F35692DFDFB', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E236997C-1C20-426E-A2F6-38D447A09473'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E236997C-1C20-426E-A2F6-38D447A09473', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'0F9E21B4-930A-4035-BCA6-01F6DB60DB42'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'0F9E21B4-930A-4035-BCA6-01F6DB60DB42', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'45685566-2A8B-4DF4-A803-F530B909ED0A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'45685566-2A8B-4DF4-A803-F530B909ED0A', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00050]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CE9E5CC7-E014-4A82-9029-09743C67FE4E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CE9E5CC7-E014-4A82-9029-09743C67FE4E', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='214F3748-BB9F-4FB1-BFC1-D99546A8D466' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'214F3748-BB9F-4FB1-BFC1-D99546A8D466', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='880F7954-1179-44FD-9206-4A19B75ADD4D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'880F7954-1179-44FD-9206-4A19B75ADD4D', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6CFE07C7-5B73-40DA-9952-99E0D024440C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6CFE07C7-5B73-40DA-9952-99E0D024440C', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='69033D27-CB9F-4C90-8AED-059995B9FFA7' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'69033D27-CB9F-4C90-8AED-059995B9FFA7', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BA1E46FC-7071-4E52-A9B5-957AA5CE35B1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BA1E46FC-7071-4E52-A9B5-957AA5CE35B1', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00051' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00051'

	-- CustomerBoundaryType - [AutomationSeedCustomer00051]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'34F8EF04-BEAB-4C50-B93E-FE25AB40F931'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'34F8EF04-BEAB-4C50-B93E-FE25AB40F931', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'76CA5B20-2602-42F3-88CA-15D2BBBACE2D'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'76CA5B20-2602-42F3-88CA-15D2BBBACE2D', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8560D82C-5CC3-4BB6-92AC-92D79316A0B5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8560D82C-5CC3-4BB6-92AC-92D79316A0B5', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9CEDA8F8-194C-4B89-A710-308D2E461FF2'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9CEDA8F8-194C-4B89-A710-308D2E461FF2', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00051]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='92618879-6097-4D08-A642-E51FD2573C3F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'92618879-6097-4D08-A642-E51FD2573C3F', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='DB779078-5019-49EB-90E8-6CC204656A5C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'DB779078-5019-49EB-90E8-6CC204656A5C', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='D8F5A745-550B-42E8-82A0-70B09117A114' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'D8F5A745-550B-42E8-82A0-70B09117A114', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='11F80388-A563-4642-8581-2EAA1A68C795' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'11F80388-A563-4642-8581-2EAA1A68C795', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='BE60034C-4A5B-409F-A747-68D9CBD0594F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'BE60034C-4A5B-409F-A747-68D9CBD0594F', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='28C4F7DA-CD1B-4FAF-87E1-F470AF57D7E1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'28C4F7DA-CD1B-4FAF-87E1-F470AF57D7E1', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00052' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00052'

	-- CustomerBoundaryType - [AutomationSeedCustomer00052]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8EA50E4F-DA68-4E24-AF9F-33433068C711'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8EA50E4F-DA68-4E24-AF9F-33433068C711', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'3B4D9BBB-3774-407D-A4B7-61423AE513E6'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'3B4D9BBB-3774-407D-A4B7-61423AE513E6', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4586F0A2-A6FF-4C51-973A-E0510C48158C'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4586F0A2-A6FF-4C51-973A-E0510C48158C', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'2131D26F-0CA3-40CB-99D8-12797AEEEF74'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'2131D26F-0CA3-40CB-99D8-12797AEEEF74', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00052]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C5C6C3C2-376B-4E68-BAC9-7CBC91B62263' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C5C6C3C2-376B-4E68-BAC9-7CBC91B62263', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='986668C1-873D-43FA-AFD6-D296C2B07DA5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'986668C1-873D-43FA-AFD6-D296C2B07DA5', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='B8231DDB-751D-4D4E-A3F1-D538329BC580' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'B8231DDB-751D-4D4E-A3F1-D538329BC580', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='01C29632-A7DA-4907-B8C9-7ACCD371DC8F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'01C29632-A7DA-4907-B8C9-7ACCD371DC8F', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='748C1FCD-4230-41D9-B7DC-D4F32B4DEA1B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'748C1FCD-4230-41D9-B7DC-D4F32B4DEA1B', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C02C9C00-F3A1-4AF2-8309-190B766706DF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C02C9C00-F3A1-4AF2-8309-190B766706DF', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00053' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00053'

	-- CustomerBoundaryType - [AutomationSeedCustomer00053]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F46DECAA-1B93-4CE3-9A68-BB6D31ABACE4'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F46DECAA-1B93-4CE3-9A68-BB6D31ABACE4', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'CF45E419-DC1E-42A9-97CE-DE27668772E8'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'CF45E419-DC1E-42A9-97CE-DE27668772E8', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'D00B2955-8B6D-494F-88CD-A11C2777AD81'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'D00B2955-8B6D-494F-88CD-A11C2777AD81', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'86628FC5-16F6-45EC-86D6-D151700706C0'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'86628FC5-16F6-45EC-86D6-D151700706C0', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00053]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A09710D3-338D-43D4-B9A9-058A6635CBD1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A09710D3-338D-43D4-B9A9-058A6635CBD1', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0752DE4C-F30E-4C89-88D6-A0A1CCE69438' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0752DE4C-F30E-4C89-88D6-A0A1CCE69438', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9FA91914-1B3E-4DAA-9D59-1514EAADBDE2' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9FA91914-1B3E-4DAA-9D59-1514EAADBDE2', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='9D778267-8412-46FC-94A6-E0C4190EB30A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'9D778267-8412-46FC-94A6-E0C4190EB30A', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8E326739-819A-4454-8606-1D2F85CA40AD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8E326739-819A-4454-8606-1D2F85CA40AD', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='991A6152-85CC-4A29-9D7F-F32AAB71DF5E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'991A6152-85CC-4A29-9D7F-F32AAB71DF5E', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00054' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00054'

	-- CustomerBoundaryType - [AutomationSeedCustomer00054]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'027A0448-8E04-4481-B19B-881AE8805B69'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'027A0448-8E04-4481-B19B-881AE8805B69', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'9B5CF6EC-414E-45EE-8DF3-359BFE3ED81A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'9B5CF6EC-414E-45EE-8DF3-359BFE3ED81A', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'8E2AE00C-D607-4AAF-8BB1-C2BF73E25CC7'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'8E2AE00C-D607-4AAF-8BB1-C2BF73E25CC7', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'888563D5-7D4D-4AA2-B358-CCCE8AF22DF1'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'888563D5-7D4D-4AA2-B358-CCCE8AF22DF1', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00054]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A1ED339F-4A12-4818-8FC1-61029C2E3A91' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A1ED339F-4A12-4818-8FC1-61029C2E3A91', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E068CF10-B1EA-45C2-9623-979E97B27ABC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E068CF10-B1EA-45C2-9623-979E97B27ABC', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8D05F12C-B6ED-48E2-8620-CF406EE895D9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8D05F12C-B6ED-48E2-8620-CF406EE895D9', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='61F0B8D1-123C-44AB-BF10-D4E754B177B4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'61F0B8D1-123C-44AB-BF10-D4E754B177B4', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C70D5AF4-1816-4820-AD55-1D89ADE40937' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C70D5AF4-1816-4820-AD55-1D89ADE40937', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='74542A75-CFF3-4935-BB90-548A83DAC3D3' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'74542A75-CFF3-4935-BB90-548A83DAC3D3', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00055' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00055'

	-- CustomerBoundaryType - [AutomationSeedCustomer00055]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E1370AC4-EC61-408B-A221-C78178440498'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E1370AC4-EC61-408B-A221-C78178440498', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'1C988AE1-7721-4E67-887E-B49848B10C32'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'1C988AE1-7721-4E67-887E-B49848B10C32', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'59FE0318-242F-4A58-AF8A-A52084DC71FB'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'59FE0318-242F-4A58-AF8A-A52084DC71FB', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'F7914BC9-8D94-448F-A179-507A08CEA082'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'F7914BC9-8D94-448F-A179-507A08CEA082', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00055]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7103701F-662A-4B09-9494-9E9BEF9940DC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7103701F-662A-4B09-9494-9E9BEF9940DC', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='54A2964F-3A68-4109-BE2F-5ABE4A0729F1' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'54A2964F-3A68-4109-BE2F-5ABE4A0729F1', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='0758DC05-BD42-4CBE-8452-C32E276DE330' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'0758DC05-BD42-4CBE-8452-C32E276DE330', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='169B2EBD-8383-4C25-803D-7525DC281EAC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'169B2EBD-8383-4C25-803D-7525DC281EAC', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='ABA60F8A-B30B-4D4B-BF84-F4132B27C985' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'ABA60F8A-B30B-4D4B-BF84-F4132B27C985', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='ECB9BFC6-39B7-4F95-AE9F-6745AD70E668' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'ECB9BFC6-39B7-4F95-AE9F-6745AD70E668', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00056' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00056'

	-- CustomerBoundaryType - [AutomationSeedCustomer00056]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'04DBDA72-2CE0-427A-8406-EF6A1EF5D4D3'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'04DBDA72-2CE0-427A-8406-EF6A1EF5D4D3', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7041D3C1-67FE-41F7-AD96-F8FA96B54340'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7041D3C1-67FE-41F7-AD96-F8FA96B54340', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'A29B1505-5201-4843-9B4B-3645EAF9BD72'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'A29B1505-5201-4843-9B4B-3645EAF9BD72', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'C450C668-E215-4CF2-935F-8827F2C5DB6E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'C450C668-E215-4CF2-935F-8827F2C5DB6E', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00056]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1305A341-8332-49A9-8DC1-CDDFEB8191DA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1305A341-8332-49A9-8DC1-CDDFEB8191DA', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='012C7E49-24CD-4B42-8167-20F41AB4F236' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'012C7E49-24CD-4B42-8167-20F41AB4F236', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8CA96FFE-74CC-43BD-9883-BD3B8ED22932' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8CA96FFE-74CC-43BD-9883-BD3B8ED22932', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='69BB71CA-0012-47D6-A72A-93BF63A681AC' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'69BB71CA-0012-47D6-A72A-93BF63A681AC', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='C280F223-7FC9-4FE6-997A-5B0FA47D475B' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'C280F223-7FC9-4FE6-997A-5B0FA47D475B', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='CFDBC45F-AEAF-4B31-8B9D-3AD8DD37640C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'CFDBC45F-AEAF-4B31-8B9D-3AD8DD37640C', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00057' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00057'

	-- CustomerBoundaryType - [AutomationSeedCustomer00057]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'6FE3679E-054B-424C-AE87-53CBFE5DBE29'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'6FE3679E-054B-424C-AE87-53CBFE5DBE29', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'E328960A-0549-429E-837F-20F310C60A9E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'E328960A-0549-429E-837F-20F310C60A9E', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BB63BDF1-DB15-4D27-ACA8-9BC8A98CFC42'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BB63BDF1-DB15-4D27-ACA8-9BC8A98CFC42', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'AC265C9A-1954-40F5-8F40-DBEA50644419'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'AC265C9A-1954-40F5-8F40-DBEA50644419', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00057]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A43EC78C-EA54-4B56-B69D-710E9216F763' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A43EC78C-EA54-4B56-B69D-710E9216F763', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='A218D365-A7B9-4A1D-8795-809170C106AF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'A218D365-A7B9-4A1D-8795-809170C106AF', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='64FF58C6-CF95-4757-A2C9-729CEC021B0E' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'64FF58C6-CF95-4757-A2C9-729CEC021B0E', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='05C9755F-24E4-4F30-9978-19378F8D35C4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'05C9755F-24E4-4F30-9978-19378F8D35C4', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='8BC52D6D-9BAA-4C4E-BA79-5EBCD5812865' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'8BC52D6D-9BAA-4C4E-BA79-5EBCD5812865', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='23402A57-8263-4803-BFB6-D1575A490CCA' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'23402A57-8263-4803-BFB6-D1575A490CCA', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00058' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00058'

	-- CustomerBoundaryType - [AutomationSeedCustomer00058]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'46A9CA45-CE9A-42E4-82E4-874E792B4C70'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'46A9CA45-CE9A-42E4-82E4-874E792B4C70', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'CD6E036A-4BA3-4A68-A35D-DCF287EC11BE'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'CD6E036A-4BA3-4A68-A35D-DCF287EC11BE', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BDCB43E1-11FE-445E-B569-B5E499F7FB10'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BDCB43E1-11FE-445E-B569-B5E499F7FB10', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'1D645459-8AC5-45CC-B290-4E155965F521'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'1D645459-8AC5-45CC-B290-4E155965F521', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00058]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='6C4AE599-E55C-4ABE-B6B8-A948EBCCEFA8' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'6C4AE599-E55C-4ABE-B6B8-A948EBCCEFA8', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='539957E6-0207-4BE2-AAA8-4C24B2E5D703' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'539957E6-0207-4BE2-AAA8-4C24B2E5D703', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='E770C3AB-6AD7-4F03-8099-A618AD7410F9' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'E770C3AB-6AD7-4F03-8099-A618AD7410F9', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='885591FD-D88B-418E-AB08-A3D5F7567B28' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'885591FD-D88B-418E-AB08-A3D5F7567B28', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='30A54D93-CE15-4A1E-B98C-2EC4B5C9F5EF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'30A54D93-CE15-4A1E-B98C-2EC4B5C9F5EF', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='7F8B3EE5-B377-4FEF-985F-E951981AB016' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'7F8B3EE5-B377-4FEF-985F-E951981AB016', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00059' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00059'

	-- CustomerBoundaryType - [AutomationSeedCustomer00059]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'85DA7A90-396B-4E23-8DDC-533C43390819'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'85DA7A90-396B-4E23-8DDC-533C43390819', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'D1064737-6313-4E64-9D98-130EB4589B2A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'D1064737-6313-4E64-9D98-130EB4589B2A', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'BA4B4A06-DC96-417A-8416-6109B2DE138A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'BA4B4A06-DC96-417A-8416-6109B2DE138A', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'4934D25E-FA4E-4BA0-A8F8-0D2890190AE5'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'4934D25E-FA4E-4BA0-A8F8-0D2890190AE5', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00059]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='59D2D882-2AED-4373-9C5C-E72BB4E3733C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'59D2D882-2AED-4373-9C5C-E72BB4E3733C', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='EED00405-4DA9-401D-807A-C72D77A2C610' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'EED00405-4DA9-401D-807A-C72D77A2C610', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='43993956-6600-4F78-996E-787320FE3F99' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'43993956-6600-4F78-996E-787320FE3F99', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AC39DCC2-972B-42BD-BEF2-88FD863CE5EF' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AC39DCC2-972B-42BD-BEF2-88FD863CE5EF', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='32ECE5EF-5F46-42EC-B646-493EEE8B341A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'32ECE5EF-5F46-42EC-B646-493EEE8B341A', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='85216AF5-873D-417F-9296-EDC2512750E5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'85216AF5-873D-417F-9296-EDC2512750E5', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00060' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00060'

	-- CustomerBoundaryType - [AutomationSeedCustomer00060]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'5DAA0FDD-0180-4AFB-B27B-439B36ABF29A'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'5DAA0FDD-0180-4AFB-B27B-439B36ABF29A', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'2F15702A-179F-4E3D-AF3D-AEC356232FAE'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'2F15702A-179F-4E3D-AF3D-AEC356232FAE', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'7DF578CC-28CE-496F-A2CC-6995483E004E'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'7DF578CC-28CE-496F-A2CC-6995483E004E', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'6BAE4CEB-A89B-4762-8BC6-5B6A9DAE6C0B'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'6BAE4CEB-A89B-4762-8BC6-5B6A9DAE6C0B', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00060]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='1191098C-5D02-4F45-BF64-B3F911CAA162' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'1191098C-5D02-4F45-BF64-B3F911CAA162', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='65821916-F2BB-4F02-B704-037D6363CAE4' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'65821916-F2BB-4F02-B704-037D6363CAE4', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='59964ABC-9A52-41AB-B236-85579944874C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'59964ABC-9A52-41AB-B236-85579944874C', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AC5C2E2D-3D24-4224-9C32-0E6B29902D90' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AC5C2E2D-3D24-4224-9C32-0E6B29902D90', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='FC2A5654-F3B9-4ED7-B6FA-E62E70C76A4D' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'FC2A5654-F3B9-4ED7-B6FA-E62E70C76A4D', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='65881650-3264-4430-ACD9-F4CCDC1278A5' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'65881650-3264-4430-ACD9-F4CCDC1278A5', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)

	-------------------------------------------------------------------------------------
	-- Customer 'AutomationSeedCustomer00061' 
	-------------------------------------------------------------------------------------

	SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='AutomationSeedCustomer00061'

	-- CustomerBoundaryType - [AutomationSeedCustomer00061]

	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'684D6A64-D570-46D7-8580-5D5E8C03DBB1'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'684D6A64-D570-46D7-8580-5D5E8C03DBB1', @customerId, N'District Plat', N'', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'2ACB8C28-AE7A-4103-802D-EC0D53DF51BF'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'2ACB8C28-AE7A-4103-802D-EC0D53DF51BF', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 1)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District Plat', [Color]=N'', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'795529D0-0A2F-46E9-AB62-042807711B74'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'795529D0-0A2F-46E9-AB62-042807711B74', @customerId, N'District Plat', N'', 2, 0, 5, 0)
	UPDATE [dbo].[CustomerBoundaryType] SET [CustomerId]=@customerId, [FeatureClassDescription]=N'District', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0, [Zoomlevel]=5, [IsReportable]=1 WHERE [Id]=N'C2DCDF96-4882-47CE-83E5-E975334EFAC9'
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerBoundaryType] ([Id], [CustomerId], [FeatureClassDescription], [Color], [LineWeight], [IsDotted], [Zoomlevel], [IsReportable]) VALUES (N'C2DCDF96-4882-47CE-83E5-E975334EFAC9', @customerId, N'District', N'#B5DBF4', 2, 0, 5, 0)

	-- CustomerMaterialType - [AutomationSeedCustomer00061]

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Copper', [Color]=N'#B5DBF4', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='29D63365-1E40-484D-A559-F66EAAD415F0' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'29D63365-1E40-484D-A559-F66EAAD415F0', @customerId, N'Copper', N'#B5DBF4', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Protected Steel', [Color]=N'#FB9A99', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AE52F30B-0871-4B55-A447-99382E117C9C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AE52F30B-0871-4B55-A447-99382E117C9C', @customerId, N'Protected Steel', N'#FB9A99', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='PE Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='AE3DA88F-B202-4D45-98B8-1DCE56DD0C4C' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'AE3DA88F-B202-4D45-98B8-1DCE56DD0C4C', @customerId, N'PE Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Cast Iron', [Color]=N'#E31A1C', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='899961AD-DD39-4343-BCB3-B814981199BD' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'899961AD-DD39-4343-BCB3-B814981199BD', @customerId, N'Cast Iron', N'#E31A1C', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Other Plastic', [Color]=N'#FDE36F', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='400DEF81-1178-4179-8640-0797A982518F' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'400DEF81-1178-4179-8640-0797A982518F', @customerId, N'Other Plastic', N'#FDE36F', 2, 0)

	UPDATE [dbo].[CustomerMaterialType] SET [CustomerId]=@customerId, [Description]='Un-protected Steel', [Color]=N'#B2DF8A', [LineWeight]=2, [IsDotted]=0 WHERE [Id]='513C702B-AAF4-41A4-AE24-B8A47648326A' 
	IF @@ROWCOUNT=0
		INSERT [dbo].[CustomerMaterialType] ([Id], [CustomerId], [Description], [Color], [LineWeight], [IsDotted]) VALUES (N'513C702B-AAF4-41A4-AE24-B8A47648326A', @customerId, N'Un-protected Steel', N'#B2DF8A', 2, 0)


		
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

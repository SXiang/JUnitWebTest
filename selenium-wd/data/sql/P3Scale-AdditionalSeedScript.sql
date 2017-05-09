DECLARE @customerId uniqueidentifier
DECLARE @locationID uniqueidentifier
DECLARE @userId uniqueidentifier

-- ############### ADD LOCATIONS ##############

-- NOTE: Creating locations only for users who are NOT re-using existing locations.

-- Add location 'CenterPointPerfTestLocation'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'CenterPointPerfTestLocation',[Latitude]='29.80910',[Longitude]='-95.16920' WHERE [Id]='DE13ACD0-C158-ECAC-7F48-39D18113E812' 
IF @@ROWCOUNT=0
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113E812', @customerId, N'CenterPointPerfTestLocation','29.80910','-95.16920')

-- ############### ADD USERS ##############

-- Add 'AutomationAdmin' user
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Picarro' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'Seed_first',[LastName]=N'seed_last',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',[SecurityStamp]=N'31facf51-53a8-44d7-bf92-828ea916693a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='AutomationAdmin'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C600',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'Seed_first',N'seed_last',NULL,NULL,N'0',N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',N'31facf51-53a8-44d7-bf92-828ea916693a',NULL,N'0',N'0',NULL,N'0',N'0','AutomationAdmin')

-- Add 'autoCenterPtCustBoundaryPerfUser@picarro.com' user
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Centerpoint' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='CenterPointPerfTestLocation'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'autoPicCustBoundary',[LastName]=N'PerfUser1',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',[SecurityStamp]=N'31facf51-53a8-44d7-bf92-828ea916693a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='autoCenterPtCustBoundaryPerfUser@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C711',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'autoPicCustBoundary',N'PerfUser1',NULL,NULL,N'0',N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',N'31facf51-53a8-44d7-bf92-828ea916693a',NULL,N'0',N'0',NULL,N'0',N'0','autoCenterPtCustBoundaryPerfUser@picarro.com')

-- Add 'autoCenterPtCustBoundaryPerfUserAA@picarro.com' user
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Centerpoint' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Texas Region - Houston'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0003-000000000000',[LocationId]=@locationID,[FirstName]=N'AutoCenterPoint',[LastName]=N'TestAA',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',[SecurityStamp]=N'31facf51-53a8-44d7-bf92-828ea916693a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='autoCenterPtCustBoundaryPerfUserAA@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C712',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0003-000000000000',@locationID,N'AutoCenterPoint',N'TestAA',NULL,NULL,N'0',N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',N'31facf51-53a8-44d7-bf92-828ea916693a',NULL,N'0',N'0',NULL,N'0',N'0','autoCenterPtCustBoundaryPerfUserAA@picarro.com')

-- Add 'autoSIGPerfUserAA@picarro.com' user
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'SIG' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Geneva'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0041-000000000000',[LocationId]=@locationID,[FirstName]=N'AutoSIGPerf',[LastName]=N'Test',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',[SecurityStamp]=N'31facf51-53a8-44d7-bf92-828ea916693a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='autoSIGPerfUserAA@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C713',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0041-000000000000',@locationID,N'AutoSIGPerf',N'Test',NULL,NULL,N'0',N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',N'31facf51-53a8-44d7-bf92-828ea916693a',NULL,N'0',N'0',NULL,N'0',N'0','autoSIGPerfUserAA@picarro.com')

-- Add 'autoAtmosPerfUserAA@picarro.com' user
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Atmos' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Dallas, TX'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0003-000000000000',[LocationId]=@locationID,[FirstName]=N'autoAtmosPerf',[LastName]=N'TestUser',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',[SecurityStamp]=N'31facf51-53a8-44d7-bf92-828ea916693a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='autoAtmosPerfUserAA@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C714',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0003-000000000000',@locationID,N'autoAtmosPerf',N'TestUser',NULL,NULL,N'0',N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',N'31facf51-53a8-44d7-bf92-828ea916693a',NULL,N'0',N'0',NULL,N'0',N'0','autoAtmosPerfUserAA@picarro.com')

	
-- ############### ADD ROLES ##############

-- Add roles to the users
SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='AutomationAdmin'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='2DAD1417-00B6-D003-63B9-39CA528B5884' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'2DAD1417-00B6-D003-63B9-39CA528B5884',@userId)
	
SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='autoCenterPtCustBoundaryPerfUser@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)

SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='autoCenterPtCustBoundaryPerfUserAA@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)

SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='autoSIGPerfUserAA@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)

SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='autoAtmosPerfUserAA@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)
	
	
-- ############### ADD LICENSES ##############
	
-- Assign licenses to Picarro customer.
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8CEF7673-80D2-407A-B833-F164C472CFDA' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8CEF7673-80D2-407A-B833-F164C472CFDA', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'F5E43F3D-9F69-430D-9013-E902A34A1D18' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'F5E43F3D-9F69-430D-9013-E902A34A1D18', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105E725-4913-477A-B58D-CBD9D83E7C70' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105E725-4913-477A-B58D-CBD9D83E7C70', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05D91EB6-90B2-440C-B887-69AC44A071ED' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05D91EB6-90B2-440C-B887-69AC44A071ED', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'B5D075A8-94EE-4D28-AFC4-69283D124A53' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'B5D075A8-94EE-4D28-AFC4-69283D124A53', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5F93BB71-4F95-4E26-ABD3-500B68838D7B' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5F93BB71-4F95-4E26-ABD3-500B68838D7B', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900B5-E0C6-4B79-8F94-3EA7F00D879F' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900B5-E0C6-4B79-8F94-3EA7F00D879F', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA', N'B1252204-04FB-4A67-82D4-3F4666FD855C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'B1252204-04FB-4A67-82D4-3F4666FD855C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'B1252204-04FB-4A67-82D4-3F4666FD855C')

-- Assign licenses to CenterPoint customer.	
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8CEF7673-80D2-407A-B833-F164C472CFDA' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8CEF7673-80D2-407A-B833-F164C472CFDA', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'F5E43F3D-9F69-430D-9013-E902A34A1D18' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'F5E43F3D-9F69-430D-9013-E902A34A1D18', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105E725-4913-477A-B58D-CBD9D83E7C70' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105E725-4913-477A-B58D-CBD9D83E7C70', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05D91EB6-90B2-440C-B887-69AC44A071ED' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05D91EB6-90B2-440C-B887-69AC44A071ED', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'B5D075A8-94EE-4D28-AFC4-69283D124A53' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'B5D075A8-94EE-4D28-AFC4-69283D124A53', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5F93BB71-4F95-4E26-ABD3-500B68838D7B' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5F93BB71-4F95-4E26-ABD3-500B68838D7B', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900B5-E0C6-4B79-8F94-3EA7F00D879F' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900B5-E0C6-4B79-8F94-3EA7F00D879F', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')

-- Assign licenses to SIG customer.	
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8CEF7673-80D2-407A-B833-F164C472CFDA' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8CEF7673-80D2-407A-B833-F164C472CFDA', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'F5E43F3D-9F69-430D-9013-E902A34A1D18' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'F5E43F3D-9F69-430D-9013-E902A34A1D18', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105E725-4913-477A-B58D-CBD9D83E7C70' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105E725-4913-477A-B58D-CBD9D83E7C70', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05D91EB6-90B2-440C-B887-69AC44A071ED' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05D91EB6-90B2-440C-B887-69AC44A071ED', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'B5D075A8-94EE-4D28-AFC4-69283D124A53' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'B5D075A8-94EE-4D28-AFC4-69283D124A53', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5F93BB71-4F95-4E26-ABD3-500B68838D7B' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5F93BB71-4F95-4E26-ABD3-500B68838D7B', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900B5-E0C6-4B79-8F94-3EA7F00D879F' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900B5-E0C6-4B79-8F94-3EA7F00D879F', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA', N'26DE07F7-A729-434E-ADFD-0D056643145F')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'26DE07F7-A729-434E-ADFD-0D056643145F')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'26DE07F7-A729-434E-ADFD-0D056643145F')

-- Assign licenses to Atmos customer.	
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8CEF7673-80D2-407A-B833-F164C472CFDA' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8CEF7673-80D2-407A-B833-F164C472CFDA', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'F5E43F3D-9F69-430D-9013-E902A34A1D18' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'F5E43F3D-9F69-430D-9013-E902A34A1D18', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105E725-4913-477A-B58D-CBD9D83E7C70' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105E725-4913-477A-B58D-CBD9D83E7C70', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05D91EB6-90B2-440C-B887-69AC44A071ED' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05D91EB6-90B2-440C-B887-69AC44A071ED', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'B5D075A8-94EE-4D28-AFC4-69283D124A53' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'B5D075A8-94EE-4D28-AFC4-69283D124A53', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5F93BB71-4F95-4E26-ABD3-500B68838D7B' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5F93BB71-4F95-4E26-ABD3-500B68838D7B', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'399900B5-E0C6-4B79-8F94-3EA7F00D879F' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'399900B5-E0C6-4B79-8F94-3EA7F00D879F', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'387D1519-64DA-4ABD-B947-1BCD72BD6CAA', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')
    INSERT INTO [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'68E7E369-FFB0-E5F6-B435-39CF46677D0C')



-- ############### UPDATE SURVEY TAGS ##############
	
-- CenterPoint surveys for Large Area/Large Asset tests.
UPDATE [dbo].[Survey] set Tag='AACPTPerf01' where [Id]='B7B5AA1E-0C59-E56A-3902-39D07E5F8F37'
UPDATE [dbo].[Survey] set Tag='AACPTPerf02' where [Id]='1C9326F3-4397-244F-8E4D-39D7FCCEA01F'
UPDATE [dbo].[Survey] set Tag='AACPTPerf03' where [Id]='FEA7C4D1-B176-DC57-EE1C-39D827142B6C'
UPDATE [dbo].[Survey] set Tag='AACPTPerf04' where [Id]='93348B54-36D2-BC12-095B-39D827EF19A5'
UPDATE [dbo].[Survey] set Tag='AACPTPerf05' where [Id]='F4B4EA37-08B0-CD57-6079-39D7F2C6C0EA'
UPDATE [dbo].[Survey] set Tag='AACPTPerf06' where [Id]='513292E5-54EB-C3DE-284B-39D7F2F23AC7'
UPDATE [dbo].[Survey] set Tag='AACPTPerf07' where [Id]='17B27ABF-8B7C-A17B-1FB0-39D7D9240618'
UPDATE [dbo].[Survey] set Tag='AACPTPerf08' where [Id]='7F7EAC25-30DD-C42D-3506-39D7D98DD327'
UPDATE [dbo].[Survey] set Tag='AACPTPerf09' where [Id]='958E7FEE-AD21-0222-7F80-39D7DDD5DED9'
UPDATE [dbo].[Survey] set Tag='AACPTPerf10' where [Id]='304C01FE-BA18-B161-828C-39D7F7E160E0'
UPDATE [dbo].[Survey] set Tag='AACPTPerf11' where [Id]='C468F44C-2FCD-FE2F-8E0F-39D801E8AF7C'
UPDATE [dbo].[Survey] set Tag='AACPTPerf12' where [Id]='9157EA90-AD90-AF7A-14BB-39D81BBC7C80'
UPDATE [dbo].[Survey] set Tag='AACPTPerf13' where [Id]='C7B752F1-8FF9-4822-5CAD-39D81CB46232'
UPDATE [dbo].[Survey] set Tag='AACPTPerf14' where [Id]='DBC900E9-66C8-B8CF-249C-39D83FDDCC1F'
UPDATE [dbo].[Survey] set Tag='AACPTPerf15' where [Id]='29D43D1B-F583-C8FA-7A24-39D83FF266BF'
UPDATE [dbo].[Survey] set Tag='AACPTPerf16' where [Id]='33ED6C74-A6EB-8029-CFF1-39D8401A42F1'

UPDATE [dbo].[Survey] set Tag='AACPTPerf17' where [Id]='A8866B7B-8647-E108-E3A5-39D7D8DD3CCA'

-- SIG surveys for Large Area/Large Asset tests.
UPDATE [dbo].[Survey] set Tag='AASGPerf01' where [Id]='FC5D3AE1-F174-8CCE-260A-39D7A4D6A898'
UPDATE [dbo].[Survey] set Tag='AASGPerf02' where [Id]='6B17E60D-D2B7-DDF2-D220-39D7F2CF46F5'
UPDATE [dbo].[Survey] set Tag='AASGPerf03' where [Id]='B4F7EA85-F79D-58C8-D5E8-39D7F115CC34'
UPDATE [dbo].[Survey] set Tag='AASGPerf04' where [Id]='1699298B-400A-2795-E5B5-39D7B919A041'
UPDATE [dbo].[Survey] set Tag='AASGPerf05' where [Id]='80777E48-2ADC-7D6A-5002-39D7EC5E2AF7'
UPDATE [dbo].[Survey] set Tag='AASGPerf06' where [Id]='2D4EAC5F-DF9B-20F3-E430-39D7ECBEBAE1'
UPDATE [dbo].[Survey] set Tag='AASGPerf07' where [Id]='43FFBEC2-C7DA-4069-0942-39D7F6CF3F96'

UPDATE [dbo].[Survey] set Tag='AASGPerf08' where [Id]='057668C9-B242-5905-F680-39D881F20067'
UPDATE [dbo].[Survey] set Tag='AASGPerf09' where [Id]='E3180D8B-4F90-5B76-5ABB-39D88C2FA5B0'
UPDATE [dbo].[Survey] set Tag='AASGPerf10' where [Id]='B6D95D63-FF89-FEF4-0D6B-39D85E378C44'
UPDATE [dbo].[Survey] set Tag='AASGPerf11' where [Id]='B34C9E1E-5F2A-7425-EDFE-39D86424F7CE'
UPDATE [dbo].[Survey] set Tag='AASGPerf12' where [Id]='80C56F75-86BB-B2AD-62AD-39D86E3E8DEF'
UPDATE [dbo].[Survey] set Tag='AASGPerf13' where [Id]='812D8D46-EA34-1A05-4141-39D87C9A09DB'
UPDATE [dbo].[Survey] set Tag='AASGPerf14' where [Id]='B06DC220-5402-15CF-C33F-39D8870AE175'

-- Atmos surveys for Large Area/Large Asset tests.
UPDATE [dbo].[Survey] set Tag='AAATMPerf01' where [Id]='C344E4C0-EADB-26C2-5A3C-39D3C66D5D2D'
UPDATE [dbo].[Survey] set Tag='AAATMPerf02' where [Id]='A4BBAF2B-A645-5A46-2080-39D3C6A3B3AD'
UPDATE [dbo].[Survey] set Tag='AAATMPerf03' where [Id]='3676E227-E48E-E800-9D54-39D3C731365A'
UPDATE [dbo].[Survey] set Tag='AAATMPerf04' where [Id]='9FC1BE37-256F-0AD0-6427-39D3C73FFD0F'
UPDATE [dbo].[Survey] set Tag='AAATMPerf05' where [Id]='58A31948-6B0B-6B50-0FB5-39D3C782368B'
UPDATE [dbo].[Survey] set Tag='AAATMPerf06' where [Id]='8A820AAE-B663-97D6-0E28-39D3CBA3B0B7'
-- Note: survey not detected in system and not used in tests.
UPDATE [dbo].[Survey] set Tag='AAATMPerf07' where [Id]='FFD309B8-FC3D-8904-9C8B-39D1A98BDE65'
UPDATE [dbo].[Survey] set Tag='AAATMPerf08' where [Id]='9DFE1519-E27A-9859-D993-39D3CB63D698'

BEGIN TRANSACTION;

BEGIN TRY

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

-- PG&E user - 'autoPGEPerfUserAA0001@picarro.com'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'PG&E' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Concord'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'FNamePerfUserAA0001',[LastName]=N'LNamePerfUserAA0001',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',[SecurityStamp]=N'31facf51-53a8-44d7-bf92-828ea916693a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='autoPGEPerfUserAA0001@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'21F7C40A-964D-4333-9E73-ABCE5D3512B3',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'FNamePerfUserAA0001',N'LNamePerfUserAA0001',NULL,NULL,N'0',N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',N'31facf51-53a8-44d7-bf92-828ea916693a',NULL,N'0',N'0',NULL,N'0',N'0','autoPGEPerfUserAA0001@picarro.com')

-- PG&E user - 'autoPGEPerfUserAA0002@picarro.com'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'PG&E' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'FNamePerfUserAA0002',[LastName]=N'LNamePerfUserAA0002',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',[SecurityStamp]=N'31facf51-53a8-44d7-bf92-828ea916693a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='autoPGEPerfUserAA0002@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'76B3A27E-4E45-4E8B-891A-C710923125C4',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'FNamePerfUserAA0002',N'LNamePerfUserAA0002',NULL,NULL,N'0',N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',N'31facf51-53a8-44d7-bf92-828ea916693a',NULL,N'0',N'0',NULL,N'0',N'0','autoPGEPerfUserAA0002@picarro.com')

-- PG&E user - 'autoPGEPerfUserAA0003@picarro.com'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'PG&E' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'FNamePerfUserAA0003',[LastName]=N'LNamePerfUserAA0003',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',[SecurityStamp]=N'31facf51-53a8-44d7-bf92-828ea916693a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='autoPGEPerfUserAA0003@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'AE8989B1-88CE-4B07-AD97-D44DF0C56C34',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'FNamePerfUserAA0003',N'LNamePerfUserAA0003',NULL,NULL,N'0',N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',N'31facf51-53a8-44d7-bf92-828ea916693a',NULL,N'0',N'0',NULL,N'0',N'0','autoPGEPerfUserAA0003@picarro.com')

-- PG&E user - 'autoPGEPerfUserAA0004@picarro.com'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'PG&E' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Santa Clara'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'FNamePerfUserAA0004',[LastName]=N'LNamePerfUserAA0004',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',[SecurityStamp]=N'31facf51-53a8-44d7-bf92-828ea916693a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='autoPGEPerfUserAA0004@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'8C096FDD-527E-4170-83EF-FE8463CD6134',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'FNamePerfUserAA0004',N'LNamePerfUserAA0004',NULL,NULL,N'0',N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',N'31facf51-53a8-44d7-bf92-828ea916693a',NULL,N'0',N'0',NULL,N'0',N'0','autoPGEPerfUserAA0004@picarro.com')


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

-- Role for PG&E user - 'autoPGEPerfUserAA0001@picarro.com'
SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='autoPGEPerfUserAA0001@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0002-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0002-000000000000',@userId)

-- Role for PG&E user - 'autoPGEPerfUserAA0002@picarro.com'
SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='autoPGEPerfUserAA0002@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0002-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0002-000000000000',@userId)

-- Role for PG&E user - 'autoPGEPerfUserAA0003@picarro.com'
SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='autoPGEPerfUserAA0003@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)

-- Role for PG&E user - 'autoPGEPerfUserAA0004@picarro.com'
SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='autoPGEPerfUserAA0004@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0002-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0002-000000000000',@userId)
	
	
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

UPDATE [dbo].[Survey] set Tag='AACPTPerf21' where [Id]='F937E2FC-71AC-7237-73A8-39DE90E5AE80'
UPDATE [dbo].[Survey] set Tag='AACPTPerf22' where [Id]='1931E03D-1921-86F9-16D2-39DE9B10957A'
UPDATE [dbo].[Survey] set Tag='AACPTPerf23' where [Id]='F39B7892-BB9D-BC86-8CBD-39DEBFD103C8'


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

-- PGE surveys for Large Area/Large Asset tests.
UPDATE [dbo].[Survey] set Tag='PGEPerf001' where [Id]='18cd7d02-c152-5d87-ae59-39d695923cd3'
UPDATE [dbo].[Survey] set Tag='PGEPerf002' where [Id]='0acfd1ee-bba7-c791-6f92-39d69598e6e0'
UPDATE [dbo].[Survey] set Tag='PGEPerf003' where [Id]='990dfb6b-25d6-d339-c2bc-39d6958e5bc8'
UPDATE [dbo].[Survey] set Tag='PGEPerf004' where [Id]='1ae91f73-e029-b6c9-44fe-39d69545ebb4'
UPDATE [dbo].[Survey] set Tag='PGEPerf005' where [Id]='e95944b8-2f79-3104-60a0-39d69597fabe'
UPDATE [dbo].[Survey] set Tag='PGEPerf006' where [Id]='3af497b2-b06e-1762-4693-39d69592ca06'
UPDATE [dbo].[Survey] set Tag='PGEPerf007' where [Id]='36cda2ef-43c3-8864-d7a2-39d6954bd09a'
UPDATE [dbo].[Survey] set Tag='PGEPerf008' where [Id]='51d932e5-b52a-0e60-1a53-39d695aeff06'
UPDATE [dbo].[Survey] set Tag='PGEPerf009' where [Id]='0455b417-3e94-8cc4-e081-39d695b8fdc4'
UPDATE [dbo].[Survey] set Tag='PGEPerf010' where [Id]='5acd582e-17db-d6dd-8f19-39d695b7db98'
UPDATE [dbo].[Survey] set Tag='PGEPerf011' where [Id]='f872fc4f-5946-fc91-6f15-39d6959b03e1'
UPDATE [dbo].[Survey] set Tag='PGEPerf012' where [Id]='058f9f19-5b87-f8cf-2d3f-39d695bf1bcc'
UPDATE [dbo].[Survey] set Tag='PGEPerf013' where [Id]='2772828a-f74c-b6d1-018e-39d695c1b2fd'
UPDATE [dbo].[Survey] set Tag='PGEPerf014' where [Id]='aedd16c6-23a7-024d-dd78-39d695d0edc8'
UPDATE [dbo].[Survey] set Tag='PGEPerf015' where [Id]='6aea273f-2d97-4055-2af4-39d695c03e56'
UPDATE [dbo].[Survey] set Tag='PGEPerf016' where [Id]='538337b3-6d27-ecf6-3ae8-39d695b9c5b1'
UPDATE [dbo].[Survey] set Tag='PGEPerf017' where [Id]='e19ccdd3-1e4c-47ff-eabc-39d695c5eff4'
UPDATE [dbo].[Survey] set Tag='PGEPerf018' where [Id]='adb7dbd5-6064-13ad-74e3-39d695c0dbeb'
UPDATE [dbo].[Survey] set Tag='PGEPerf019' where [Id]='ed092224-d9b7-97cf-75a6-39d695e7c420'
UPDATE [dbo].[Survey] set Tag='PGEPerf020' where [Id]='030d2440-605e-74d7-8b18-39d695e9f050'
UPDATE [dbo].[Survey] set Tag='PGEPerf021' where [Id]='629ea587-cf70-2035-f3d4-39d695cfc51e'
UPDATE [dbo].[Survey] set Tag='PGEPerf022' where [Id]='a0069a80-9a36-b082-c21a-39d695fc2086'
UPDATE [dbo].[Survey] set Tag='PGEPerf023' where [Id]='6f1f5d10-40ed-4cd8-507b-39d696072f4b'
UPDATE [dbo].[Survey] set Tag='PGEPerf024' where [Id]='0428e844-cd13-f7fa-4416-39d695dc5b22'
UPDATE [dbo].[Survey] set Tag='PGEPerf025' where [Id]='4dba1426-57d2-8dd1-0c10-39d69641c929'
UPDATE [dbo].[Survey] set Tag='PGEPerf026' where [Id]='f67a1fa0-734e-e680-88cf-39d69623fc04'
UPDATE [dbo].[Survey] set Tag='PGEPerf027' where [Id]='b6b70cdc-2c30-f9cd-eba7-39d6964298b8'
UPDATE [dbo].[Survey] set Tag='PGEPerf028' where [Id]='24f2f24e-f815-df8f-086a-39d696172e08'
UPDATE [dbo].[Survey] set Tag='PGEPerf029' where [Id]='f747bd80-ff9e-b30c-2c5b-39d69637283c'
UPDATE [dbo].[Survey] set Tag='PGEPerf030' where [Id]='1405bc5e-2c49-a8a3-5a6e-39d6964a70bb'
UPDATE [dbo].[Survey] set Tag='PGEPerf031' where [Id]='9b415a93-1c3c-57ec-c581-39d6964f960a'
UPDATE [dbo].[Survey] set Tag='PGEPerf032' where [Id]='f786ed71-350d-4933-015a-39d6966b0329'
UPDATE [dbo].[Survey] set Tag='PGEPerf033' where [Id]='880a76d3-b159-1d08-7fde-39d6966b7687'
UPDATE [dbo].[Survey] set Tag='PGEPerf034' where [Id]='4a736037-d73d-11a5-5397-39d6969364a5'
UPDATE [dbo].[Survey] set Tag='PGEPerf035' where [Id]='a0a4393f-38ba-c8cb-4a86-39d6969575d1'
UPDATE [dbo].[Survey] set Tag='PGEPerf036' where [Id]='1bd0696c-0676-9c9c-9120-39d696a6944f'
UPDATE [dbo].[Survey] set Tag='PGEPerf037' where [Id]='5a327d5a-070a-70e9-001c-39d696a27486'
UPDATE [dbo].[Survey] set Tag='PGEPerf038' where [Id]='4cabcf6b-7a60-7d98-0151-39d696a7fd41'
UPDATE [dbo].[Survey] set Tag='PGEPerf039' where [Id]='dbe0e257-89c7-a9ce-f2c1-39d69710983e'
UPDATE [dbo].[Survey] set Tag='PGEPerf040' where [Id]='b87cfe92-a945-c443-044f-39d697064916'
UPDATE [dbo].[Survey] set Tag='PGEPerf041' where [Id]='0d0a471c-fa82-890c-a9f2-39d696d61900'
UPDATE [dbo].[Survey] set Tag='PGEPerf042' where [Id]='d93b030f-17ec-0457-886c-39d6971c3a53'
UPDATE [dbo].[Survey] set Tag='PGEPerf043' where [Id]='ab7867a9-24ed-5a13-373c-39d696cd4046'
UPDATE [dbo].[Survey] set Tag='PGEPerf044' where [Id]='b35ca9c7-e1d6-db08-a762-39d6971ff6e3'
UPDATE [dbo].[Survey] set Tag='PGEPerf045' where [Id]='0b9da1a1-28a4-a209-ddad-39d699dd2c6e'
UPDATE [dbo].[Survey] set Tag='PGEPerf046' where [Id]='844f15b7-11ec-1782-3203-39d6972e1b6c'
UPDATE [dbo].[Survey] set Tag='PGEPerf047' where [Id]='6e97107a-b832-e027-3aa8-39d699e8569a'
UPDATE [dbo].[Survey] set Tag='PGEPerf048' where [Id]='8f3c72f6-9b8d-0412-bd04-39d699e95fb6'
UPDATE [dbo].[Survey] set Tag='PGEPerf049' where [Id]='6b74845d-c455-e6be-5269-39d699f0461d'
UPDATE [dbo].[Survey] set Tag='PGEPerf050' where [Id]='171cd799-6381-2f46-a929-39d699eedb5d'
UPDATE [dbo].[Survey] set Tag='PGEPerf051' where [Id]='df025fe3-417b-795c-152e-39d699f1c3d7'
UPDATE [dbo].[Survey] set Tag='PGEPerf052' where [Id]='80a5c9af-6eb2-7e57-463b-39d699ed4682'
UPDATE [dbo].[Survey] set Tag='PGEPerf053' where [Id]='9d9a37de-30d4-21be-0777-39d69a15c792'
UPDATE [dbo].[Survey] set Tag='PGEPerf054' where [Id]='e60fe036-9bee-7ebe-b07d-39d69a0a3aff'
UPDATE [dbo].[Survey] set Tag='PGEPerf055' where [Id]='ae23267f-a06a-ff42-9418-39d69a013f5f'
UPDATE [dbo].[Survey] set Tag='PGEPerf056' where [Id]='28f457bd-e3a3-6747-a02a-39d69a21edb0'
UPDATE [dbo].[Survey] set Tag='PGEPerf057' where [Id]='e1a6b2a9-f3dd-3d4a-7139-39d699ea4d2b'
UPDATE [dbo].[Survey] set Tag='PGEPerf058' where [Id]='d4b320a2-72f7-fd9e-9476-39d69a01b8ad'
UPDATE [dbo].[Survey] set Tag='PGEPerf059' where [Id]='594fca1a-448f-112e-1f7b-39d69a022410'
UPDATE [dbo].[Survey] set Tag='PGEPerf060' where [Id]='4e00ca88-20d9-5c5c-b55d-39d69a6c79e6'
UPDATE [dbo].[Survey] set Tag='PGEPerf061' where [Id]='49185e97-0202-1090-80dd-39d69a6b6ef1'
UPDATE [dbo].[Survey] set Tag='PGEPerf062' where [Id]='8345a0e0-6bda-34c3-a6f2-39d69a2d8eaa'
UPDATE [dbo].[Survey] set Tag='PGEPerf063' where [Id]='a6431493-ac9d-9e60-7c95-39d69a50436c'
UPDATE [dbo].[Survey] set Tag='PGEPerf064' where [Id]='306275a4-3dbb-c13a-7e5e-39d69aa02194'
UPDATE [dbo].[Survey] set Tag='PGEPerf065' where [Id]='a152f625-fa6b-a7f1-020c-39d69a6ed489'
UPDATE [dbo].[Survey] set Tag='PGEPerf066' where [Id]='4b5eb8d3-053b-03d6-a471-39d69a6e0b9e'
UPDATE [dbo].[Survey] set Tag='PGEPerf067' where [Id]='a5c6916b-d91c-448a-e6b0-39d69a7e6378'
UPDATE [dbo].[Survey] set Tag='PGEPerf068' where [Id]='9910f322-f2b6-5205-7e07-39d6af162a07'
UPDATE [dbo].[Survey] set Tag='PGEPerf069' where [Id]='0e41577c-d098-9431-9a68-39d6af001b5f'
UPDATE [dbo].[Survey] set Tag='PGEPerf070' where [Id]='67784b2b-554b-6c55-0d75-39d6aee16a89'
UPDATE [dbo].[Survey] set Tag='PGEPerf071' where [Id]='733acb2a-8951-cf73-8046-39d6a9b6bf95'
UPDATE [dbo].[Survey] set Tag='PGEPerf072' where [Id]='71a3b37b-9f24-087e-4a72-39d6af02d354'
UPDATE [dbo].[Survey] set Tag='PGEPerf073' where [Id]='17714571-c27d-4db0-e7d3-39d6a9f9b214'
UPDATE [dbo].[Survey] set Tag='PGEPerf074' where [Id]='34b6d66e-c36d-6a51-3ef1-39d6aee35956'
UPDATE [dbo].[Survey] set Tag='PGEPerf075' where [Id]='cac4e00f-c351-12c1-413a-39d6a9fbc926'
UPDATE [dbo].[Survey] set Tag='PGEPerf076' where [Id]='164ca40f-71dd-287b-54b4-39d6a9dfbb6b'
UPDATE [dbo].[Survey] set Tag='PGEPerf077' where [Id]='7b8acdd6-a54f-b29b-e683-39d6b398492e'
UPDATE [dbo].[Survey] set Tag='PGEPerf078' where [Id]='f11517e1-543e-9d36-d272-39d6b3b5a272'
UPDATE [dbo].[Survey] set Tag='PGEPerf079' where [Id]='8fb5ec36-61ac-c983-ba5d-39d6b3c0b02b'
UPDATE [dbo].[Survey] set Tag='PGEPerf080' where [Id]='25e0ad96-2596-55ed-c31d-39d6b3aabaa0'
UPDATE [dbo].[Survey] set Tag='PGEPerf081' where [Id]='b2d284cb-f5e2-8917-ec12-39d6b39de17a'
UPDATE [dbo].[Survey] set Tag='PGEPerf082' where [Id]='5280093f-4e7a-258e-41a2-39d6b3b45b71'
UPDATE [dbo].[Survey] set Tag='PGEPerf083' where [Id]='f6615328-3c21-af0b-fa9e-39d6afc02180'
UPDATE [dbo].[Survey] set Tag='PGEPerf084' where [Id]='b5002c52-1639-33fe-77c0-39d6af81a9ed'
UPDATE [dbo].[Survey] set Tag='PGEPerf085' where [Id]='42816404-3b85-330c-81e8-39d6af4b38ae'
UPDATE [dbo].[Survey] set Tag='PGEPerf086' where [Id]='8b0c2076-aaad-ea01-19d5-39d6af71ded5'
UPDATE [dbo].[Survey] set Tag='PGEPerf087' where [Id]='10530f18-4629-db00-7f41-39d6af743c25'
UPDATE [dbo].[Survey] set Tag='PGEPerf088' where [Id]='cfe17653-6809-9c48-f5a0-39d6afdbc3f2'
UPDATE [dbo].[Survey] set Tag='PGEPerf089' where [Id]='cf8554a3-79b0-55ff-bb74-39d6afcdab3b'
UPDATE [dbo].[Survey] set Tag='PGEPerf090' where [Id]='3411a193-24dd-b75f-bdbe-39d6afa9cd4e'
UPDATE [dbo].[Survey] set Tag='PGEPerf091' where [Id]='9adb1a6d-e812-feb7-ee74-39d6afb4263f'
UPDATE [dbo].[Survey] set Tag='PGEPerf092' where [Id]='b412b10d-c841-6ece-4471-39d6af63be1c'
UPDATE [dbo].[Survey] set Tag='PGEPerf093' where [Id]='63987d2a-0243-be12-3fb7-39d6afb28623'
UPDATE [dbo].[Survey] set Tag='PGEPerf094' where [Id]='96d39f94-892d-dbb7-8ea4-39d889b81986'
UPDATE [dbo].[Survey] set Tag='PGEPerf095' where [Id]='6f6f91b6-e731-3302-7ad8-39d889902e67'
UPDATE [dbo].[Survey] set Tag='PGEPerf096' where [Id]='7959a25e-df6e-9d80-52ab-39d91ee41a7f'
UPDATE [dbo].[Survey] set Tag='PGEPerf097' where [Id]='79194e34-3824-fda3-2cf3-39daf386444c'
UPDATE [dbo].[Survey] set Tag='PGEPerf098' where [Id]='515f2ec4-a4e2-526e-daa4-39daf36ebd2c'
UPDATE [dbo].[Survey] set Tag='PGEPerf099' where [Id]='a55dcebd-01db-669c-46e6-39daf37cee4f'
UPDATE [dbo].[Survey] set Tag='PGEPerf100' where [Id]='51e00ad1-2462-850f-3e6f-39dceb5b3273'
UPDATE [dbo].[Survey] set Tag='PGEPerf101' where [Id]='eebb944c-8efc-02db-7fc7-39dcedbac861'
UPDATE [dbo].[Survey] set Tag='PGEPerf102' where [Id]='de0d78e1-04a4-3d63-038b-39dcebdb2a13'
UPDATE [dbo].[Survey] set Tag='PGEPerf103' where [Id]='26c81010-eadb-737e-be27-39dcebdfe589'
UPDATE [dbo].[Survey] set Tag='PGEPerf104' where [Id]='77a0a3bb-9cf6-fdae-20e9-39dcec81a803'
UPDATE [dbo].[Survey] set Tag='PGEPerf105' where [Id]='c6ae26ee-adc8-8a77-6bf4-39dcebb338a5'
UPDATE [dbo].[Survey] set Tag='PGEPerf106' where [Id]='ffe3aed3-a4dc-8aa6-e6a7-39dcebc5a7af'
UPDATE [dbo].[Survey] set Tag='PGEPerf107' where [Id]='71101a2f-4c41-8a92-3793-39dcedb6dec1'
UPDATE [dbo].[Survey] set Tag='PGEPerf108' where [Id]='0392300d-e846-7334-95a6-39dcee2ae0f9'
UPDATE [dbo].[Survey] set Tag='PGEPerf109' where [Id]='017090ce-6415-8d63-7d86-39dcede9a0ae'
UPDATE [dbo].[Survey] set Tag='PGEPerf110' where [Id]='b885cf41-80cb-9426-1632-39dcee16ffa9'
UPDATE [dbo].[Survey] set Tag='PGEPerf111' where [Id]='a156e342-aadb-fcca-b42d-39dcedc270a1'
UPDATE [dbo].[Survey] set Tag='PGEPerf112' where [Id]='dec95ea3-bd7e-b357-fa64-39dcee1cca82'
UPDATE [dbo].[Survey] set Tag='PGEPerf113' where [Id]='12e4491f-752a-e2ea-7579-39dcee29ad1c'
UPDATE [dbo].[Survey] set Tag='PGEPerf114' where [Id]='935e2f03-a584-e3db-63c6-39dcee154fe3'
UPDATE [dbo].[Survey] set Tag='PGEPerf115' where [Id]='a3727485-e7c2-71d1-862a-39dcee478587'
UPDATE [dbo].[Survey] set Tag='PGEPerf116' where [Id]='d1fe0ce8-79e2-f36a-9a4c-39dcee75cdc9'
UPDATE [dbo].[Survey] set Tag='PGEPerf117' where [Id]='6c2657d1-aaa1-4f68-47a7-39dcee467d5a'
UPDATE [dbo].[Survey] set Tag='PGEPerf118' where [Id]='730c7406-37d8-7b2c-c193-39dcee6eecc0'
UPDATE [dbo].[Survey] set Tag='PGEPerf119' where [Id]='d77fc820-22f2-d025-211d-39dcee373600'
UPDATE [dbo].[Survey] set Tag='PGEPerf120' where [Id]='eccbdbb4-6a1c-01a1-355f-39dcee678036'
UPDATE [dbo].[Survey] set Tag='PGEPerf121' where [Id]='a680ee69-3b64-391e-8790-39dcee4b8072'
UPDATE [dbo].[Survey] set Tag='PGEPerf122' where [Id]='af8dde64-a77b-ed7e-2d04-39dcee68e3e6'
UPDATE [dbo].[Survey] set Tag='PGEPerf123' where [Id]='abf4a3e3-118e-de0d-286c-39dcee82e497'
UPDATE [dbo].[Survey] set Tag='PGEPerf124' where [Id]='053717d4-9766-17c7-5f6d-39dcee7d7bee'
UPDATE [dbo].[Survey] set Tag='PGEPerf125' where [Id]='b96a6007-4eef-250f-7f90-39dcee7f05cc'
UPDATE [dbo].[Survey] set Tag='PGEPerf126' where [Id]='a1b9a8a9-f5a5-3222-7bc3-39dcee7b2565'
UPDATE [dbo].[Survey] set Tag='PGEPerf127' where [Id]='3a94bfa9-c4b3-c41f-22e6-39dcf2391f1e'
UPDATE [dbo].[Survey] set Tag='PGEPerf128' where [Id]='eda1bfd6-179a-7fdc-80dd-39dd7cd4924d'
UPDATE [dbo].[Survey] set Tag='PGEPerf129' where [Id]='7c98de87-8172-bca4-3968-39dd7cd2a031'
UPDATE [dbo].[Survey] set Tag='PGEPerf130' where [Id]='6dfd41bc-99ab-ec11-c967-39dd7ca9971d'
UPDATE [dbo].[Survey] set Tag='PGEPerf131' where [Id]='70f4f2f1-6d40-f104-9339-39dd7cf1f002'
UPDATE [dbo].[Survey] set Tag='PGEPerf132' where [Id]='c0aad429-7a63-678e-9581-39dd7ce0f875'
UPDATE [dbo].[Survey] set Tag='PGEPerf133' where [Id]='f1eb7017-8452-a8b8-3629-39dd7ce76ebd'
UPDATE [dbo].[Survey] set Tag='PGEPerf134' where [Id]='b22cd822-f75e-b956-02c1-39dd7cf6e7ee'
UPDATE [dbo].[Survey] set Tag='PGEPerf135' where [Id]='bf80565a-c62c-872a-805c-39dd7cdc7dd9'
UPDATE [dbo].[Survey] set Tag='PGEPerf136' where [Id]='2a1360fd-5f96-bc0f-6ee9-39dd7cc34fe6'
UPDATE [dbo].[Survey] set Tag='PGEPerf137' where [Id]='aa274354-7e25-f006-065b-39dd7cd5b5ba'
UPDATE [dbo].[Survey] set Tag='PGEPerf138' where [Id]='85af261c-5124-2be2-5380-39dd7cfada92'
UPDATE [dbo].[Survey] set Tag='PGEPerf139' where [Id]='1ebfb61b-fc59-f55f-dd51-39dd7d0fb16b'
UPDATE [dbo].[Survey] set Tag='PGEPerf140' where [Id]='e7ba95ad-7280-f3d1-b4f5-39dd7d10ada7'
UPDATE [dbo].[Survey] set Tag='PGEPerf141' where [Id]='142f6e26-c385-3a1e-c5b0-39dd7d3123c9'
UPDATE [dbo].[Survey] set Tag='PGEPerf142' where [Id]='6024a32a-66d9-ab9c-b082-39dd7d1c19f2'
UPDATE [dbo].[Survey] set Tag='PGEPerf143' where [Id]='be9f8252-b856-f0aa-dbba-39dd7d08a072'
UPDATE [dbo].[Survey] set Tag='PGEPerf144' where [Id]='35895a96-0476-a1e4-4c48-39dd7d51f083'
UPDATE [dbo].[Survey] set Tag='PGEPerf145' where [Id]='75b4f4b1-c17e-19a4-7d40-39dd7d5f2686'
UPDATE [dbo].[Survey] set Tag='PGEPerf146' where [Id]='c42fdf00-68b5-7618-e43d-39dd77f070c2'
UPDATE [dbo].[Survey] set Tag='PGEPerf147' where [Id]='6854e5bd-cca1-16e7-6afd-39dd7d628eaa'
UPDATE [dbo].[Survey] set Tag='PGEPerf148' where [Id]='10923737-3bf2-5127-7fc8-39dd7d4da787'
UPDATE [dbo].[Survey] set Tag='PGEPerf149' where [Id]='7ae81105-d3bf-c35b-beb3-39dd7d5acae8'
UPDATE [dbo].[Survey] set Tag='PGEPerf150' where [Id]='adb3de1e-7343-a69d-8481-39dd782c5e0e'
UPDATE [dbo].[Survey] set Tag='PGEPerf151' where [Id]='3715059a-a3ef-c21f-0a67-39dd78084349'
UPDATE [dbo].[Survey] set Tag='PGEPerf152' where [Id]='37dccf7d-45ec-4b24-2a54-39dd7828d902'
UPDATE [dbo].[Survey] set Tag='PGEPerf153' where [Id]='2b091044-5302-ac0b-a96a-39dd77e59cd2'
UPDATE [dbo].[Survey] set Tag='PGEPerf154' where [Id]='08214368-9af7-292e-ff34-39dd7819bcdf'
UPDATE [dbo].[Survey] set Tag='PGEPerf155' where [Id]='1fd3d351-1508-2da0-804f-39dd781289a2'
UPDATE [dbo].[Survey] set Tag='PGEPerf156' where [Id]='da23b3c5-9bb0-20d2-ffb5-39dd8693cc4a'
UPDATE [dbo].[Survey] set Tag='PGEPerf157' where [Id]='b3f62d1a-9e8f-971d-d6bd-39dd86c6e1b7'
UPDATE [dbo].[Survey] set Tag='PGEPerf158' where [Id]='102b4586-2b8f-26da-a134-39dd869f0099'
UPDATE [dbo].[Survey] set Tag='PGEPerf159' where [Id]='1fb656a4-fa68-5a1f-b19f-39dd8693decc'
UPDATE [dbo].[Survey] set Tag='PGEPerf160' where [Id]='16feff05-a930-8fe3-7190-39dd86c99d61'
UPDATE [dbo].[Survey] set Tag='PGEPerf161' where [Id]='3d731799-2a2d-8a7c-c0f4-39dd86c33c55'
UPDATE [dbo].[Survey] set Tag='PGEPerf162' where [Id]='5940772e-9899-0233-0648-39dd86ca945a'
UPDATE [dbo].[Survey] set Tag='PGEPerf163' where [Id]='16d749fd-13b4-6f63-f8fb-39dd86c24a8a'
UPDATE [dbo].[Survey] set Tag='PGEPerf164' where [Id]='c87a038a-28d8-a403-d145-39dd86bcdf3a'
UPDATE [dbo].[Survey] set Tag='PGEPerf165' where [Id]='c8879f03-63d9-89ec-5600-39dd86cbf6c3'
UPDATE [dbo].[Survey] set Tag='PGEPerf166' where [Id]='61f4f7f5-febc-ac32-52c8-39dd86d79e77'
UPDATE [dbo].[Survey] set Tag='PGEPerf167' where [Id]='df3c3778-e42e-e7af-ba83-39dd86d8dbef'
UPDATE [dbo].[Survey] set Tag='PGEPerf168' where [Id]='64fcab5c-dad8-f053-9732-39dd86e1c29c'
UPDATE [dbo].[Survey] set Tag='PGEPerf169' where [Id]='7498684e-22c8-9d37-17c2-39dd86c54daa'
UPDATE [dbo].[Survey] set Tag='PGEPerf170' where [Id]='4176b9f4-40ac-5b8d-8c00-39dd86d39a0c'
UPDATE [dbo].[Survey] set Tag='PGEPerf171' where [Id]='7074f507-9dba-8d1d-fbd7-39dd86dbcf90'
UPDATE [dbo].[Survey] set Tag='PGEPerf172' where [Id]='77c2fde9-21fb-c8c8-8e81-39dd86f21195'
UPDATE [dbo].[Survey] set Tag='PGEPerf173' where [Id]='99587241-df05-fac5-c785-39dd86e31cd2'
UPDATE [dbo].[Survey] set Tag='PGEPerf174' where [Id]='3a256509-80bc-c554-c515-39dd86d291ed'
UPDATE [dbo].[Survey] set Tag='PGEPerf175' where [Id]='7c3c91a5-b4f0-f67b-b5d1-39dd87441f03'
UPDATE [dbo].[Survey] set Tag='PGEPerf176' where [Id]='c488057e-9fe8-6618-529c-39dd86dd72ec'
UPDATE [dbo].[Survey] set Tag='PGEPerf177' where [Id]='04efd5f7-cd4a-4542-53ad-39dd86fc80d8'
UPDATE [dbo].[Survey] set Tag='PGEPerf178' where [Id]='4751dff9-a791-b62f-7b60-39dd873bbb92'
UPDATE [dbo].[Survey] set Tag='PGEPerf179' where [Id]='9bc03445-f190-3e2c-4323-39dd86fd0b2e'
UPDATE [dbo].[Survey] set Tag='PGEPerf180' where [Id]='18e4c932-b5cf-f6ed-694c-39dd875c0ff6'
UPDATE [dbo].[Survey] set Tag='PGEPerf181' where [Id]='70ea83ca-31ac-9bf9-7fa4-39dd86fbc617'
UPDATE [dbo].[Survey] set Tag='PGEPerf182' where [Id]='81675607-a70e-5fd3-d824-39dd875304a5'
UPDATE [dbo].[Survey] set Tag='PGEPerf183' where [Id]='4cc845e9-8b82-f3f1-2e97-39dd8756878a'
UPDATE [dbo].[Survey] set Tag='PGEPerf184' where [Id]='3d690512-1264-86dc-7c2b-39dd8748814e'
UPDATE [dbo].[Survey] set Tag='PGEPerf185' where [Id]='20706292-b496-89ed-9dfe-39dd8754a50a'
UPDATE [dbo].[Survey] set Tag='PGEPerf186' where [Id]='c2d2763f-ec6c-6c57-4475-39dd8750369a'
UPDATE [dbo].[Survey] set Tag='PGEPerf187' where [Id]='2427f891-489e-1fc6-3d6a-39dd8760542d'
UPDATE [dbo].[Survey] set Tag='PGEPerf188' where [Id]='41801105-128a-1f16-5d85-39dd877df208'
UPDATE [dbo].[Survey] set Tag='PGEPerf189' where [Id]='76f256f3-49d9-1e4b-0978-39dd8769635d'
UPDATE [dbo].[Survey] set Tag='PGEPerf190' where [Id]='f6994180-bd9e-d3b9-21b4-39dd876eafe4'
UPDATE [dbo].[Survey] set Tag='PGEPerf191' where [Id]='cc9b3b20-d6b8-cf6d-9971-39dd8776c31a'
UPDATE [dbo].[Survey] set Tag='PGEPerf192' where [Id]='5210e45a-39bd-e488-c2ed-39dd879dbf83'
UPDATE [dbo].[Survey] set Tag='PGEPerf193' where [Id]='a4719c8f-d714-c29c-ecc7-39dd87a40c99'
UPDATE [dbo].[Survey] set Tag='PGEPerf194' where [Id]='54e029df-7175-bfb8-59ee-39dd879f0e07'
UPDATE [dbo].[Survey] set Tag='PGEPerf195' where [Id]='f8e5ea46-fc2d-95df-c071-39dd87996e05'
UPDATE [dbo].[Survey] set Tag='PGEPerf196' where [Id]='1674f0c3-a767-69cf-edde-39dd879ab7d3'
UPDATE [dbo].[Survey] set Tag='PGEPerf197' where [Id]='cb38158f-9c75-0843-c41d-39dd87936b4d'
UPDATE [dbo].[Survey] set Tag='PGEPerf198' where [Id]='6197ff96-2f1c-d5bb-71cb-39dd87978e35'
UPDATE [dbo].[Survey] set Tag='PGEPerf199' where [Id]='14dc8f72-7948-9ab7-082b-39dd879aee09'
UPDATE [dbo].[Survey] set Tag='PGEPerf200' where [Id]='347c5089-ebce-06c1-1656-39dd879948f7'
UPDATE [dbo].[Survey] set Tag='PGEPerf201' where [Id]='31f2c86f-1c09-dd60-6081-39dd875d8ade'
UPDATE [dbo].[Survey] set Tag='PGEPerf202' where [Id]='46c68068-967f-ffdf-d6c0-39dd8761ccc6'
UPDATE [dbo].[Survey] set Tag='PGEPerf203' where [Id]='058320af-f451-3485-2db8-39dd875c31a9'
UPDATE [dbo].[Survey] set Tag='PGEPerf204' where [Id]='6c8d3528-5956-6aa8-73c1-39dd8760a935'
UPDATE [dbo].[Survey] set Tag='PGEPerf205' where [Id]='01529a37-00cd-dc35-1410-39dd87426087'
UPDATE [dbo].[Survey] set Tag='PGEPerf206' where [Id]='06574b1c-9099-bede-d422-39dd95f9b357'
UPDATE [dbo].[Survey] set Tag='PGEPerf207' where [Id]='ed30602f-d992-a716-b459-39dd95e54f9c'
UPDATE [dbo].[Survey] set Tag='PGEPerf208' where [Id]='8091e987-926a-1c1c-38ef-39dd95ec0aa9'
UPDATE [dbo].[Survey] set Tag='PGEPerf209' where [Id]='5e70eb88-4bb5-cbfa-09bb-39dd95def612'
UPDATE [dbo].[Survey] set Tag='PGEPerf210' where [Id]='50229e41-b37c-f1ad-2bbe-39dd961bd7c1'
UPDATE [dbo].[Survey] set Tag='PGEPerf211' where [Id]='f1d27b5e-d50e-ef06-95a5-39dd964a5773'
UPDATE [dbo].[Survey] set Tag='PGEPerf212' where [Id]='3e229ee2-9614-a3af-eee1-39dd961a1e37'
UPDATE [dbo].[Survey] set Tag='PGEPerf213' where [Id]='141df34b-526c-492c-f481-39dd96445019'
UPDATE [dbo].[Survey] set Tag='PGEPerf214' where [Id]='b92346d9-1d56-c659-ffe8-39dd965ad5cd'
UPDATE [dbo].[Survey] set Tag='PGEPerf215' where [Id]='03673ac0-af3a-0cae-2b0d-39dd9618a287'
UPDATE [dbo].[Survey] set Tag='PGEPerf216' where [Id]='95e7921a-9097-72cf-5017-39dd962e5db0'
UPDATE [dbo].[Survey] set Tag='PGEPerf217' where [Id]='0220794d-806e-13a4-17a6-39dd96ad3cd1'
UPDATE [dbo].[Survey] set Tag='PGEPerf218' where [Id]='3a3c30f0-d246-fe9a-de89-39dd967c23ce'
UPDATE [dbo].[Survey] set Tag='PGEPerf219' where [Id]='7df6c70b-6073-662c-e0dc-39dd96b15570'
UPDATE [dbo].[Survey] set Tag='PGEPerf220' where [Id]='7644dee6-0034-a4bb-2913-39dd96b3023d'
UPDATE [dbo].[Survey] set Tag='PGEPerf221' where [Id]='0b76ff65-a5eb-e321-d576-39dd96b8ad0d'
UPDATE [dbo].[Survey] set Tag='PGEPerf222' where [Id]='0cc14be9-26ec-89fa-72fa-39de20defba4'
UPDATE [dbo].[Survey] set Tag='PGEPerf223' where [Id]='ae3acdb4-aff2-9567-86b2-39de211b4c64'
UPDATE [dbo].[Survey] set Tag='PGEPerf224' where [Id]='e295317d-0299-a774-1ca7-39de21660953'
UPDATE [dbo].[Survey] set Tag='PGEPerf225' where [Id]='5a4583c6-7124-c9dc-c256-39de2162a338'
UPDATE [dbo].[Survey] set Tag='PGEPerf226' where [Id]='be0cd1d4-bab3-0636-75c3-39de212cdf62'
UPDATE [dbo].[Survey] set Tag='PGEPerf227' where [Id]='53496f42-a318-0f12-d94e-39de215c7599'
UPDATE [dbo].[Survey] set Tag='PGEPerf228' where [Id]='666c8f65-80ef-d81d-caf5-39de213b20d2'
UPDATE [dbo].[Survey] set Tag='PGEPerf229' where [Id]='b273b299-21b0-3d38-1b0c-39de21400423'
UPDATE [dbo].[Survey] set Tag='PGEPerf230' where [Id]='6f8190f0-095d-6026-6a89-39de211ee05b'
UPDATE [dbo].[Survey] set Tag='PGEPerf231' where [Id]='066a2219-cc1f-409e-3b2f-39de21606674'
UPDATE [dbo].[Survey] set Tag='PGEPerf232' where [Id]='a2e30fd5-9013-d677-d3a5-39de21409641'
UPDATE [dbo].[Survey] set Tag='PGEPerf233' where [Id]='a5e226ec-b130-2a32-00f7-39de20f07fb2'
UPDATE [dbo].[Survey] set Tag='PGEPerf234' where [Id]='9dc1599a-e6fa-d681-7de0-39de2115a41d'
UPDATE [dbo].[Survey] set Tag='PGEPerf235' where [Id]='7e9d3e0f-2bc8-2aa9-aad6-39de211aab71'
UPDATE [dbo].[Survey] set Tag='PGEPerf236' where [Id]='f09a0ec9-b37e-371e-e8c1-39de21271097'
UPDATE [dbo].[Survey] set Tag='PGEPerf237' where [Id]='32c76b8d-59e7-3e8b-b5bb-39de212966b8'
UPDATE [dbo].[Survey] set Tag='PGEPerf238' where [Id]='4f4197c2-fd7e-ec34-9303-39de21135c4f'
UPDATE [dbo].[Survey] set Tag='PGEPerf239' where [Id]='5e2e5376-ef55-0a4b-f3c0-39de20ef8f4a'
UPDATE [dbo].[Survey] set Tag='PGEPerf240' where [Id]='6428bf82-d8f9-e397-74e2-39de20ceed38'
UPDATE [dbo].[Survey] set Tag='PGEPerf241' where [Id]='6aa2f8a4-e410-27f7-9a31-39de20d49c11'
UPDATE [dbo].[Survey] set Tag='PGEPerf242' where [Id]='3e5ff57b-0a15-1b9c-a11b-39de40ac60a1'
UPDATE [dbo].[Survey] set Tag='PGEPerf243' where [Id]='4e71db05-5fa6-e24f-3019-39de40b1e836'
UPDATE [dbo].[Survey] set Tag='PGEPerf244' where [Id]='995513ba-8e08-789a-1afd-39de408151ac'
UPDATE [dbo].[Survey] set Tag='PGEPerf245' where [Id]='0eb5704b-e3b2-037f-cf8d-39de408dabb4'
UPDATE [dbo].[Survey] set Tag='PGEPerf246' where [Id]='e0703272-3471-0f7c-727a-39de40602460'
UPDATE [dbo].[Survey] set Tag='PGEPerf247' where [Id]='0981d7c6-4f46-37f6-32f1-39de40518a1e'
UPDATE [dbo].[Survey] set Tag='PGEPerf248' where [Id]='0059826b-4ae1-622c-22de-39de4592e4b2'
UPDATE [dbo].[Survey] set Tag='PGEPerf249' where [Id]='e241cc59-7351-33d4-21f9-39de408617d9'
UPDATE [dbo].[Survey] set Tag='PGEPerf250' where [Id]='d3f30afe-4173-eefd-50ba-39de45994ed6'
UPDATE [dbo].[Survey] set Tag='PGEPerf251' where [Id]='719045f9-8688-77fb-aa95-39de456fca41'
UPDATE [dbo].[Survey] set Tag='PGEPerf252' where [Id]='3020c392-cd4d-e301-8ad5-39de4ad7d7c5'
UPDATE [dbo].[Survey] set Tag='PGEPerf253' where [Id]='b83474fa-f4ad-66c5-1fd0-39de4abed19a'
UPDATE [dbo].[Survey] set Tag='PGEPerf254' where [Id]='33f4b8f2-51ad-971f-4440-39de4ab8a930'
UPDATE [dbo].[Survey] set Tag='PGEPerf255' where [Id]='826808e1-6025-ed58-3338-39de4ab46195'
UPDATE [dbo].[Survey] set Tag='PGEPerf256' where [Id]='1c493f93-2692-b57b-c85e-39de4625fd50'
UPDATE [dbo].[Survey] set Tag='PGEPerf257' where [Id]='5f82200c-dcf8-76db-36b2-39de462928f9'
UPDATE [dbo].[Survey] set Tag='PGEPerf258' where [Id]='54d4bd87-2370-4e7f-ce7c-39de4b3f4f30'
UPDATE [dbo].[Survey] set Tag='PGEPerf259' where [Id]='a05ba5cf-e6e5-a269-45bf-39de4b43986f'
UPDATE [dbo].[Survey] set Tag='PGEPerf260' where [Id]='3caee607-4739-2a35-ba1b-39de463d12cb'
UPDATE [dbo].[Survey] set Tag='PGEPerf261' where [Id]='2158f60a-7bb7-641f-8a1b-39de4615b9e8'
UPDATE [dbo].[Survey] set Tag='PGEPerf262' where [Id]='c3ffd699-0271-3477-4d19-39de460c178c'
UPDATE [dbo].[Survey] set Tag='PGEPerf263' where [Id]='c5ab2483-a51f-33a7-b03d-39de460fdd77'
UPDATE [dbo].[Survey] set Tag='PGEPerf264' where [Id]='c704a3eb-4874-bda0-6362-39de45e6cff7'
UPDATE [dbo].[Survey] set Tag='PGEPerf265' where [Id]='e176f6a5-3393-1b03-4985-39de460792c6'
UPDATE [dbo].[Survey] set Tag='PGEPerf266' where [Id]='ff1b464d-961a-c020-b4a4-39de4616ab4f'
UPDATE [dbo].[Survey] set Tag='PGEPerf267' where [Id]='4444443b-79fd-ce9d-474e-39de45fd320e'
UPDATE [dbo].[Survey] set Tag='PGEPerf268' where [Id]='2a16ad8f-1f52-b863-0807-39de4b2ffe21'
UPDATE [dbo].[Survey] set Tag='PGEPerf269' where [Id]='e69bdcf9-54aa-faae-47c7-39de460e87a6'
UPDATE [dbo].[Survey] set Tag='PGEPerf270' where [Id]='a9bcc33c-7aa6-d6f4-51fc-39de46066b80'
UPDATE [dbo].[Survey] set Tag='PGEPerf271' where [Id]='e2e9af4d-b7bf-9995-572c-39de4b2cc922'
UPDATE [dbo].[Survey] set Tag='PGEPerf272' where [Id]='d5dd7ed6-79cc-6c85-3718-39de4b179dd9'
UPDATE [dbo].[Survey] set Tag='PGEPerf273' where [Id]='68ee4025-ef6d-b8a2-0de0-39de4aff205e'
UPDATE [dbo].[Survey] set Tag='PGEPerf274' where [Id]='7080ab55-442f-e6e9-b769-39de4600933d'
UPDATE [dbo].[Survey] set Tag='PGEPerf275' where [Id]='06166239-5caa-101e-bc7e-39de4b18c14e'
UPDATE [dbo].[Survey] set Tag='PGEPerf276' where [Id]='d262f25a-2530-f53b-6726-39de4b15deef'
UPDATE [dbo].[Survey] set Tag='PGEPerf277' where [Id]='d95c8ffb-3630-2b39-cc2d-39de4af4927c'
UPDATE [dbo].[Survey] set Tag='PGEPerf278' where [Id]='5781115c-8493-238c-caec-39de4b0f8178'
UPDATE [dbo].[Survey] set Tag='PGEPerf279' where [Id]='a8ed4811-9d1d-56de-afff-39de4b15b71d'
UPDATE [dbo].[Survey] set Tag='PGEPerf280' where [Id]='aab8908a-fd16-85c0-f46f-39de4adef31c'
UPDATE [dbo].[Survey] set Tag='PGEPerf281' where [Id]='dac2ec20-0cc5-48fd-d25f-39de4afd8621'
UPDATE [dbo].[Survey] set Tag='PGEPerf282' where [Id]='6cfed9fd-4f3c-3616-6412-39de4af790cb'
UPDATE [dbo].[Survey] set Tag='PGEPerf283' where [Id]='4c3bd616-2499-5b71-9e65-39de4b0e6ebd'
UPDATE [dbo].[Survey] set Tag='PGEPerf284' where [Id]='3dc3cf83-e02e-b5bb-a689-39de4ace88be'
UPDATE [dbo].[Survey] set Tag='PGEPerf285' where [Id]='7c3af6e1-f05d-aed1-78bd-39de4ad17d14'
UPDATE [dbo].[Survey] set Tag='PGEPerf286' where [Id]='24355a2c-f9f0-c7d9-302f-39de4ad062f6'
UPDATE [dbo].[Survey] set Tag='PGEPerf287' where [Id]='cd267465-63f0-6646-2083-39de4adfcfd8'
UPDATE [dbo].[Survey] set Tag='PGEPerf288' where [Id]='418c715d-7ce0-8840-ad5f-39de4ae1a1e0'
UPDATE [dbo].[Survey] set Tag='PGEPerf289' where [Id]='035d8dab-2d9a-9a49-2109-39de4a94b499'
UPDATE [dbo].[Survey] set Tag='PGEPerf290' where [Id]='843b4690-88d0-28a5-9590-39de4a96226f'
UPDATE [dbo].[Survey] set Tag='PGEPerf291' where [Id]='9417a91e-d121-03bf-b856-39de4afcc3d2'
UPDATE [dbo].[Survey] set Tag='PGEPerf292' where [Id]='0e5998c2-0cef-b36f-d64a-39de4afdf255'
UPDATE [dbo].[Survey] set Tag='PGEPerf293' where [Id]='01d361d1-343a-219f-19a2-39de4b06426e'
UPDATE [dbo].[Survey] set Tag='PGEPerf294' where [Id]='8fad8725-4d9a-e545-0247-39de4af49634'
UPDATE [dbo].[Survey] set Tag='PGEPerf295' where [Id]='2ab0a7f5-b72e-4487-2adc-39de4b0958d1'
UPDATE [dbo].[Survey] set Tag='PGEPerf296' where [Id]='a9315bce-9db1-d3c4-0bcb-39de4b34921b'
UPDATE [dbo].[Survey] set Tag='PGEPerf297' where [Id]='673b80f1-fa9a-18a9-1c80-39de4b3a1385'
UPDATE [dbo].[Survey] set Tag='PGEPerf298' where [Id]='92d0a220-c67c-5b2c-7c2b-39de4b0f6985'
UPDATE [dbo].[Survey] set Tag='PGEPerf299' where [Id]='cee52a54-35f1-4e94-7ace-39de4b295e72'
UPDATE [dbo].[Survey] set Tag='PGEPerf300' where [Id]='99c1a662-f099-18b5-ce4a-39de4b194865'
UPDATE [dbo].[Survey] set Tag='PGEPerf301' where [Id]='705afa55-3ea3-0e8a-a888-39de4b0b6e0e'
UPDATE [dbo].[Survey] set Tag='PGEPerf302' where [Id]='8d78d060-7d72-d18b-6d28-39de4b283c65'
UPDATE [dbo].[Survey] set Tag='PGEPerf303' where [Id]='ce7cae0b-f262-9831-d339-39de4643cae1'
UPDATE [dbo].[Survey] set Tag='PGEPerf304' where [Id]='c7227b5a-4312-0c25-1977-39de464997bf'
UPDATE [dbo].[Survey] set Tag='PGEPerf305' where [Id]='32d2cc31-7f94-c803-e7c8-39de4624e2c5'
UPDATE [dbo].[Survey] set Tag='PGEPerf306' where [Id]='7ae20924-e82d-f5b8-82fb-39de463080e5'
UPDATE [dbo].[Survey] set Tag='PGEPerf307' where [Id]='208db35d-04f0-2f43-bca2-39de46403c90'
UPDATE [dbo].[Survey] set Tag='PGEPerf308' where [Id]='7976fb30-0d26-d880-0667-39de463958d5'
UPDATE [dbo].[Survey] set Tag='PGEPerf309' where [Id]='c05ba798-0686-82f7-fea0-39de4a6b68c1'
UPDATE [dbo].[Survey] set Tag='PGEPerf310' where [Id]='34672a97-7815-b024-75ca-39de4a84450f'
UPDATE [dbo].[Survey] set Tag='PGEPerf311' where [Id]='be4fb585-90a1-d349-ef7b-39de4a823b3f'
UPDATE [dbo].[Survey] set Tag='PGEPerf312' where [Id]='cfea9276-2e86-5258-1d8b-39de4aace2b3'
UPDATE [dbo].[Survey] set Tag='PGEPerf313' where [Id]='a1d0f949-cd04-764a-8341-39de4aa9ebc1'
UPDATE [dbo].[Survey] set Tag='PGEPerf314' where [Id]='5a2666d5-7b53-118f-fd79-39de4abd69ef'
UPDATE [dbo].[Survey] set Tag='PGEPerf315' where [Id]='e3ebc7a0-c763-cf03-1aa3-39de4a5a9b14'
UPDATE [dbo].[Survey] set Tag='PGEPerf316' where [Id]='340ec8aa-5b7e-e468-25fa-39de4a09d3ae'
UPDATE [dbo].[Survey] set Tag='PGEPerf317' where [Id]='2ffabeef-2765-4adf-bdc2-39de4a660857'
UPDATE [dbo].[Survey] set Tag='PGEPerf318' where [Id]='cf2d5a5a-e7a0-672a-382b-39de49de403b'
UPDATE [dbo].[Survey] set Tag='PGEPerf319' where [Id]='bee69161-829d-e263-c3ca-39de4a00c7e1'
UPDATE [dbo].[Survey] set Tag='PGEPerf320' where [Id]='c5d68a7e-ce3b-6f9d-2dbd-39de4a5d4318'
UPDATE [dbo].[Survey] set Tag='PGEPerf321' where [Id]='2061eb1b-6e36-5437-1142-39de4b088440'
UPDATE [dbo].[Survey] set Tag='PGEPerf322' where [Id]='f7390f6b-3bfe-e174-e820-39de4b2f1fb0'
UPDATE [dbo].[Survey] set Tag='PGEPerf323' where [Id]='8ddd1dc1-df3d-47c6-257b-39de69d330bb'
UPDATE [dbo].[Survey] set Tag='PGEPerf324' where [Id]='d627f2f9-18b5-70e4-263f-39de69bb19d8'
UPDATE [dbo].[Survey] set Tag='PGEPerf325' where [Id]='e9b42ea0-942c-65c6-ae77-39de69d0ed2f'
UPDATE [dbo].[Survey] set Tag='PGEPerf326' where [Id]='0646744e-d5f8-8a15-22e6-39de69cf1414'
UPDATE [dbo].[Survey] set Tag='PGEPerf327' where [Id]='d09d808c-a717-0c93-f4be-39de69e9dba5'
UPDATE [dbo].[Survey] set Tag='PGEPerf328' where [Id]='b47ddb07-67f2-8e13-b813-39de69e13b99'
UPDATE [dbo].[Survey] set Tag='PGEPerf329' where [Id]='1e3fa7b1-4f5c-7f00-881d-39de69ed77b6'
UPDATE [dbo].[Survey] set Tag='PGEPerf330' where [Id]='550cde48-e99b-13ff-c022-39de69d46084'
UPDATE [dbo].[Survey] set Tag='PGEPerf331' where [Id]='b4545ff7-30f8-1f43-ea19-39de69d9dd31'
UPDATE [dbo].[Survey] set Tag='PGEPerf332' where [Id]='2118fa0b-174d-0ba7-2c39-39de69dc5e8c'
UPDATE [dbo].[Survey] set Tag='PGEPerf333' where [Id]='dbff8f41-ecaa-26a0-2e8e-39de6a0e4046'
UPDATE [dbo].[Survey] set Tag='PGEPerf334' where [Id]='f391d2e8-9458-e59b-921a-39de6a140fcc'
UPDATE [dbo].[Survey] set Tag='PGEPerf335' where [Id]='d3655738-fa37-4adc-5d4a-39de6a058f56'
UPDATE [dbo].[Survey] set Tag='PGEPerf336' where [Id]='a5551505-cdd0-0c7f-3f81-39de6a0be58b'
UPDATE [dbo].[Survey] set Tag='PGEPerf337' where [Id]='5469fe4b-79ac-ef59-4ff4-39de6a0caeba'
UPDATE [dbo].[Survey] set Tag='PGEPerf338' where [Id]='24b62b3b-133e-0a32-167a-39de69f61aec'
UPDATE [dbo].[Survey] set Tag='PGEPerf339' where [Id]='c5d002d1-86f1-0196-0465-39de6a0836c8'
UPDATE [dbo].[Survey] set Tag='PGEPerf340' where [Id]='70676209-f349-41c5-b928-39de6a1ccdcb'
UPDATE [dbo].[Survey] set Tag='PGEPerf341' where [Id]='6e75c00f-f562-5b66-212a-39de6a21014d'
UPDATE [dbo].[Survey] set Tag='PGEPerf342' where [Id]='6336e9a7-61f0-a35a-06f2-39de64e65a9f'
UPDATE [dbo].[Survey] set Tag='PGEPerf343' where [Id]='d57f5a0e-690e-3b9d-49ef-39de6a2c67a6'
UPDATE [dbo].[Survey] set Tag='PGEPerf344' where [Id]='a30f61fc-1d27-74fe-ca7a-39de6a2a3148'
UPDATE [dbo].[Survey] set Tag='PGEPerf345' where [Id]='6569abd7-88ae-46f0-17fa-39de6a249a46'
UPDATE [dbo].[Survey] set Tag='PGEPerf346' where [Id]='901beb25-8b77-825c-3c06-39de64eb0fa6'
UPDATE [dbo].[Survey] set Tag='PGEPerf347' where [Id]='8f22e640-bf7c-45bc-0a89-39de64e3abb3'
UPDATE [dbo].[Survey] set Tag='PGEPerf348' where [Id]='6fb8fe9d-53e4-5a7d-2cb2-39de64d466bd'
UPDATE [dbo].[Survey] set Tag='PGEPerf349' where [Id]='0f9d1149-8a10-f442-947f-39de64ed4651'
UPDATE [dbo].[Survey] set Tag='PGEPerf350' where [Id]='0d6eb21f-e30f-8ad8-4a23-39de64e963c4'
UPDATE [dbo].[Survey] set Tag='PGEPerf351' where [Id]='991cfcc9-b580-7a31-0a51-39de6501c253'
UPDATE [dbo].[Survey] set Tag='PGEPerf352' where [Id]='ee1884df-b29e-bf0f-fbf5-39de64ffce05'
UPDATE [dbo].[Survey] set Tag='PGEPerf353' where [Id]='fd9e74c9-b1b2-bb72-5f8d-39de64f5b606'
UPDATE [dbo].[Survey] set Tag='PGEPerf354' where [Id]='7ce2493d-e102-f895-2106-39de6504143f'
UPDATE [dbo].[Survey] set Tag='PGEPerf355' where [Id]='41adadd9-1f99-f769-029b-39de64ef6150'
UPDATE [dbo].[Survey] set Tag='PGEPerf356' where [Id]='f39d81e9-ac4a-904b-b4b3-39de650346b9'
UPDATE [dbo].[Survey] set Tag='PGEPerf357' where [Id]='164e241b-0cfc-74a6-cc5b-39de650a5203'
UPDATE [dbo].[Survey] set Tag='PGEPerf358' where [Id]='7a5f18bf-cf3a-5086-aff5-39dea77a3bdc'
UPDATE [dbo].[Survey] set Tag='PGEPerf359' where [Id]='10f6af27-1efc-4402-d613-39dea765b44c'
UPDATE [dbo].[Survey] set Tag='PGEPerf360' where [Id]='b6e4aedd-c79b-0c5f-64a9-39dea76a861a'
UPDATE [dbo].[Survey] set Tag='PGEPerf361' where [Id]='b8ee3d4a-2a96-3caf-aa96-39dea7471c98'
UPDATE [dbo].[Survey] set Tag='PGEPerf362' where [Id]='048fc886-5a70-9d16-95b0-39dea773ead0'
UPDATE [dbo].[Survey] set Tag='PGEPerf363' where [Id]='252fc247-f2e2-d9dd-d408-39dea757faee'
UPDATE [dbo].[Survey] set Tag='PGEPerf364' where [Id]='7eac00a1-8432-6e3d-b58f-39dea72d9e5c'
UPDATE [dbo].[Survey] set Tag='PGEPerf365' where [Id]='5318f94f-0b78-7b28-9281-39dea7539125'
UPDATE [dbo].[Survey] set Tag='PGEPerf366' where [Id]='2ed129ed-7714-42bd-306a-39dea6fe62d9'
UPDATE [dbo].[Survey] set Tag='PGEPerf367' where [Id]='23fb8d58-50f2-b567-70a8-39dea752c1ea'
UPDATE [dbo].[Survey] set Tag='PGEPerf368' where [Id]='d5aa7ce4-6b38-737e-8211-39dea7198803'
UPDATE [dbo].[Survey] set Tag='PGEPerf369' where [Id]='60014d3c-99a7-7bf8-1457-39dea7126f3d'
UPDATE [dbo].[Survey] set Tag='PGEPerf370' where [Id]='9ec3ce93-36fb-c0ac-af5e-39dea74b9361'
UPDATE [dbo].[Survey] set Tag='PGEPerf371' where [Id]='dc3b5729-32d2-da8d-4e32-39dea70fe668'
UPDATE [dbo].[Survey] set Tag='PGEPerf372' where [Id]='1d1ced1e-80e9-0d08-ecf0-39dea7163730'
UPDATE [dbo].[Survey] set Tag='PGEPerf373' where [Id]='8dd43bba-5b36-9b76-bf70-39dea6b415fb'
UPDATE [dbo].[Survey] set Tag='PGEPerf374' where [Id]='4e607fa2-a061-257f-8dc4-39dea6b74cad'
UPDATE [dbo].[Survey] set Tag='PGEPerf375' where [Id]='9b1a3b4b-f6bd-8a14-dca2-39dea6ecb64e'
UPDATE [dbo].[Survey] set Tag='PGEPerf376' where [Id]='770b7aba-a702-0fb9-fd93-39dea6f2bc9f'
UPDATE [dbo].[Survey] set Tag='PGEPerf377' where [Id]='88d8aaf8-0c01-8f14-0da4-39dea6fc46bb'
UPDATE [dbo].[Survey] set Tag='PGEPerf378' where [Id]='fc205af4-c9f5-438e-3019-39df3cfb1254'
UPDATE [dbo].[Survey] set Tag='PGEPerf379' where [Id]='72006f98-ebc7-d30f-b17f-39df3cf595e2'
UPDATE [dbo].[Survey] set Tag='PGEPerf380' where [Id]='43ee2c6a-68a2-03f2-9c1c-39df3cf323b2'
UPDATE [dbo].[Survey] set Tag='PGEPerf381' where [Id]='61fd0919-37ce-9831-6337-39df3cf44d4f'
UPDATE [dbo].[Survey] set Tag='PGEPerf382' where [Id]='312686b1-1f4c-da81-08a3-39df3cf0e986'
UPDATE [dbo].[Survey] set Tag='PGEPerf383' where [Id]='6a9e30da-c8bd-1b7c-8080-39df3d121427'
UPDATE [dbo].[Survey] set Tag='PGEPerf384' where [Id]='8fe811ac-4ffe-e636-b693-39df3d053094'
UPDATE [dbo].[Survey] set Tag='PGEPerf385' where [Id]='e3d745e4-9319-cbd7-9e2a-39df3cfe0af9'
UPDATE [dbo].[Survey] set Tag='PGEPerf386' where [Id]='35028252-5886-75fe-ef34-39df3d22f032'
UPDATE [dbo].[Survey] set Tag='PGEPerf387' where [Id]='c6c9be2d-d36e-dcf5-9218-39df3d12c30b'
UPDATE [dbo].[Survey] set Tag='PGEPerf388' where [Id]='8f6be539-f4a6-6bc9-6af5-39df3d14c2d4'
UPDATE [dbo].[Survey] set Tag='PGEPerf389' where [Id]='2fe4a7fe-3d80-29cb-ca56-39df3d3bc948'
UPDATE [dbo].[Survey] set Tag='PGEPerf390' where [Id]='eddd4971-6ba1-39e4-2123-39df41ab8b7d'
UPDATE [dbo].[Survey] set Tag='PGEPerf391' where [Id]='b5014d25-e31c-af54-f997-39df41ada8ec'
UPDATE [dbo].[Survey] set Tag='PGEPerf392' where [Id]='3168e188-1962-69b6-c2b2-39df41b1a607'
UPDATE [dbo].[Survey] set Tag='PGEPerf393' where [Id]='68b661c4-675a-2eb4-5f21-39df3d41aa34'
UPDATE [dbo].[Survey] set Tag='PGEPerf394' where [Id]='3bc6382b-0611-2eed-25d9-39df41e57aee'
UPDATE [dbo].[Survey] set Tag='PGEPerf395' where [Id]='8842a034-d00d-715c-f072-39df41e38bdf'
UPDATE [dbo].[Survey] set Tag='PGEPerf396' where [Id]='25464fdc-dfcb-6045-badd-39df41e6868f'
UPDATE [dbo].[Survey] set Tag='PGEPerf397' where [Id]='c9966057-54c1-ef50-9223-39df41bdd1d9'
UPDATE [dbo].[Survey] set Tag='PGEPerf398' where [Id]='da7a1f44-44af-8df8-59d0-39df41d4d356'
UPDATE [dbo].[Survey] set Tag='PGEPerf399' where [Id]='4461446d-5f9d-7028-2193-39df33ebb3d9'
UPDATE [dbo].[Survey] set Tag='PGEPerf400' where [Id]='83679130-bf66-4731-7126-39df41e8e22f'
UPDATE [dbo].[Survey] set Tag='PGEPerf401' where [Id]='c0d34132-d4b1-acb1-be24-39df420012a3'
UPDATE [dbo].[Survey] set Tag='PGEPerf402' where [Id]='a03a9829-08a5-49fb-301f-39df33c4269b'
UPDATE [dbo].[Survey] set Tag='PGEPerf403' where [Id]='ec86cf06-8340-41fc-64a6-39df4205501f'
UPDATE [dbo].[Survey] set Tag='PGEPerf404' where [Id]='9f1f926d-b01e-8ed3-87b1-39df33c64ebf'
UPDATE [dbo].[Survey] set Tag='PGEPerf405' where [Id]='a3ad74b5-f847-b4c3-d8a3-39df33cc4c66'
UPDATE [dbo].[Survey] set Tag='PGEPerf406' where [Id]='a952cb04-f725-ed77-f777-39df33bd9249'
UPDATE [dbo].[Survey] set Tag='PGEPerf407' where [Id]='23f6c6f4-83a7-5b0d-0562-39df33f475de'
UPDATE [dbo].[Survey] set Tag='PGEPerf408' where [Id]='e09fb3d5-b6a5-6182-5253-39df33c73da7'
UPDATE [dbo].[Survey] set Tag='PGEPerf409' where [Id]='c27bafbf-2ed3-0574-3188-39df33bc585b'
UPDATE [dbo].[Survey] set Tag='PGEPerf410' where [Id]='1344c617-9b17-342b-b1df-39df339d0e59'
UPDATE [dbo].[Survey] set Tag='PGEPerf411' where [Id]='01b55e96-2fcb-1d35-f51d-39df339fcc1b'
UPDATE [dbo].[Survey] set Tag='PGEPerf412' where [Id]='0064f6a4-06e7-e8e4-5078-39df33a8985d'
UPDATE [dbo].[Survey] set Tag='PGEPerf413' where [Id]='86231113-18b2-e31e-7815-39df33af209e'
UPDATE [dbo].[Survey] set Tag='PGEPerf414' where [Id]='b8816273-5a3f-5539-e79f-39df33a5683c'
UPDATE [dbo].[Survey] set Tag='PGEPerf415' where [Id]='45501500-88b0-6213-74b5-39df339e10ff'
UPDATE [dbo].[Survey] set Tag='PGEPerf416' where [Id]='54f902a8-63dd-a52a-0e07-39df339abe83'
UPDATE [dbo].[Survey] set Tag='PGEPerf417' where [Id]='4ef104a8-c169-3269-f3fd-39dff0c648e1'
UPDATE [dbo].[Survey] set Tag='PGEPerf418' where [Id]='c7d5b986-23a9-4631-bb08-39dff0de9876'
UPDATE [dbo].[Survey] set Tag='PGEPerf419' where [Id]='c846b052-71a6-15e0-78cd-39dff0e1f315'
UPDATE [dbo].[Survey] set Tag='PGEPerf420' where [Id]='acd1685a-5f2a-1e9b-440b-39dff0e9cf76'
UPDATE [dbo].[Survey] set Tag='PGEPerf421' where [Id]='750db19a-971f-5d38-f700-39dff0f1d1ff'
UPDATE [dbo].[Survey] set Tag='PGEPerf422' where [Id]='23450a8f-88b2-40c5-0353-39dff0f4a4b7'
UPDATE [dbo].[Survey] set Tag='PGEPerf423' where [Id]='fdae7f4c-6a0e-1a06-3bce-39dff1033bac'
UPDATE [dbo].[Survey] set Tag='PGEPerf424' where [Id]='a97d5c3d-b271-413a-8df7-39dff10540ac'
UPDATE [dbo].[Survey] set Tag='PGEPerf425' where [Id]='14286166-5e62-c8ba-631c-39dff107c1d9'
UPDATE [dbo].[Survey] set Tag='PGEPerf426' where [Id]='5cc5fe47-a865-e8b1-fc2e-39dff11a05cc'
UPDATE [dbo].[Survey] set Tag='PGEPerf427' where [Id]='8d4fb749-b9d8-03fd-4144-39dff11f1a5c'
UPDATE [dbo].[Survey] set Tag='PGEPerf428' where [Id]='8ed220c0-93c5-bc44-8789-39dff123e8b2'
UPDATE [dbo].[Survey] set Tag='PGEPerf429' where [Id]='576e2f51-6ccb-acc2-f991-39dff13f2ff7'
UPDATE [dbo].[Survey] set Tag='PGEPerf430' where [Id]='80a02b0c-6f82-1bb1-a3d1-39dff141ed96'
UPDATE [dbo].[Survey] set Tag='PGEPerf431' where [Id]='5f9dee04-2757-65c3-45c9-39dff1481584'
UPDATE [dbo].[Survey] set Tag='PGEPerf432' where [Id]='b07f4f5d-9817-29c8-9efb-39dff14f4ff7'
UPDATE [dbo].[Survey] set Tag='PGEPerf433' where [Id]='fd903197-1d63-a37c-8aca-39dff1516dda'
UPDATE [dbo].[Survey] set Tag='PGEPerf434' where [Id]='c111dbaf-95d9-6c5d-0cb8-39dff1615051'
UPDATE [dbo].[Survey] set Tag='PGEPerf435' where [Id]='f3037d9c-2b5e-e3ee-df95-39dff163757e'
UPDATE [dbo].[Survey] set Tag='PGEPerf436' where [Id]='7e0d53a7-2171-693b-551a-39dff16605bb'
UPDATE [dbo].[Survey] set Tag='PGEPerf437' where [Id]='863403c3-f2d5-e0d6-ebce-39dff167c4ff'
UPDATE [dbo].[Survey] set Tag='PGEPerf438' where [Id]='2170a198-cf54-0aae-c30a-39dfe6acaaf1'
UPDATE [dbo].[Survey] set Tag='PGEPerf439' where [Id]='fb8ee1de-0e8b-3f79-36b5-39dfe6aa285e'
UPDATE [dbo].[Survey] set Tag='PGEPerf440' where [Id]='f37bb716-d1e0-f212-c41c-39dfe6a764c6'
UPDATE [dbo].[Survey] set Tag='PGEPerf441' where [Id]='d9b9bd4a-268f-8239-62b2-39dfe6a5702f'
UPDATE [dbo].[Survey] set Tag='PGEPerf442' where [Id]='d216ecf0-5235-ce47-e339-39dfe6a379f0'
UPDATE [dbo].[Survey] set Tag='PGEPerf443' where [Id]='42e9ea40-df9c-013c-78f9-39dfe69ad802'
UPDATE [dbo].[Survey] set Tag='PGEPerf444' where [Id]='efdabee4-a700-04fe-01e9-39dfe697f50d'
UPDATE [dbo].[Survey] set Tag='PGEPerf445' where [Id]='c73e297f-b96d-340b-74e1-39dfe6959f2d'
UPDATE [dbo].[Survey] set Tag='PGEPerf446' where [Id]='0f89c1e1-1f8e-a24f-200e-39dfe6917097'
UPDATE [dbo].[Survey] set Tag='PGEPerf447' where [Id]='4aee84ca-2126-6b70-223d-39dfe68c8636'
UPDATE [dbo].[Survey] set Tag='PGEPerf448' where [Id]='4f738c5e-0156-44c1-26e0-39dfe6676476'
UPDATE [dbo].[Survey] set Tag='PGEPerf449' where [Id]='da47206a-02df-ffe8-2b26-39e02eb110b3'
UPDATE [dbo].[Survey] set Tag='PGEPerf450' where [Id]='ff48a1bc-a39d-9eb8-6e33-39e02ea09b7f'
UPDATE [dbo].[Survey] set Tag='PGEPerf451' where [Id]='67b2365c-c0e8-d632-65ad-39e02e87b368'
UPDATE [dbo].[Survey] set Tag='PGEPerf452' where [Id]='b9150b93-bd38-48b0-7214-39e02e804523'
UPDATE [dbo].[Survey] set Tag='PGEPerf453' where [Id]='e27dcef4-8039-04ee-aaf8-39e02e75a276'
UPDATE [dbo].[Survey] set Tag='PGEPerf454' where [Id]='3f4acfa4-4ba4-2e32-1227-39e02e6a7663'
UPDATE [dbo].[Survey] set Tag='PGEPerf455' where [Id]='9d3584ab-0d3b-11b0-4bc2-39e02e3adf24'
UPDATE [dbo].[Survey] set Tag='PGEPerf456' where [Id]='b3c53ad0-826f-0b76-8f6d-39e0f7b46e76'
UPDATE [dbo].[Survey] set Tag='PGEPerf457' where [Id]='dafd4783-877c-244b-5bae-39e0f7b18dab'
UPDATE [dbo].[Survey] set Tag='PGEPerf458' where [Id]='7ee86c24-d826-e34d-72aa-39e0f77e2e90'
UPDATE [dbo].[Survey] set Tag='PGEPerf459' where [Id]='7c1e55ab-a7c0-1791-f467-39e0f785fe69'
UPDATE [dbo].[Survey] set Tag='PGEPerf460' where [Id]='807fbc22-2d02-3c02-ca0e-39e0f783c417'
UPDATE [dbo].[Survey] set Tag='PGEPerf461' where [Id]='c143338a-6a52-410a-3a2a-39e0f75dd880'
UPDATE [dbo].[Survey] set Tag='PGEPerf462' where [Id]='d380dcca-38ca-421a-d297-39e0f758ac2f'
UPDATE [dbo].[Survey] set Tag='PGEPerf463' where [Id]='d3a2a00f-22c6-03b0-2d27-39e0f74980d2'
UPDATE [dbo].[Survey] set Tag='PGEPerf464' where [Id]='c8c8b1ec-dc56-fb4b-2a22-39e0f746f6b4'
UPDATE [dbo].[Survey] set Tag='PGEPerf465' where [Id]='babbf871-29f0-953c-912e-39e0f7432e43'
UPDATE [dbo].[Survey] set Tag='PGEPerf466' where [Id]='1b0f9eea-45df-6e39-9452-39e0f73f691c'
UPDATE [dbo].[Survey] set Tag='PGEPerf467' where [Id]='8051404e-a6de-0179-95b2-39e0f29ec276'
UPDATE [dbo].[Survey] set Tag='PGEPerf468' where [Id]='cb9ae427-3246-97d3-e269-39e0f285ad2a'
UPDATE [dbo].[Survey] set Tag='PGEPerf469' where [Id]='9764ffee-42e8-f842-eaff-39e0f29c3e02'
UPDATE [dbo].[Survey] set Tag='PGEPerf470' where [Id]='75e3c4e3-6be7-e668-04c6-39e0f270c91e'
UPDATE [dbo].[Survey] set Tag='PGEPerf471' where [Id]='71279656-215a-377d-44aa-39e0f2803050'
UPDATE [dbo].[Survey] set Tag='PGEPerf472' where [Id]='8da6663b-d164-eb09-77b7-39e0f270aa5e'
UPDATE [dbo].[Survey] set Tag='PGEPerf473' where [Id]='b6a5b13e-b308-4a71-c026-39e0f26e528f'
UPDATE [dbo].[Survey] set Tag='PGEPerf474' where [Id]='9d4f1aa3-2128-4602-1c85-39e0f2593183'
UPDATE [dbo].[Survey] set Tag='PGEPerf475' where [Id]='2d16d2e5-a7e7-8370-a339-39e0f262edb4'
UPDATE [dbo].[Survey] set Tag='PGEPerf476' where [Id]='70abaaa2-91a2-6a77-2a32-39e0f2654dfa'
UPDATE [dbo].[Survey] set Tag='PGEPerf477' where [Id]='8debf24a-3008-0e69-4362-39e0f266e3e5'
UPDATE [dbo].[Survey] set Tag='PGEPerf478' where [Id]='3821e7e6-ef47-6587-8d6c-39e0f26aa73e'
UPDATE [dbo].[Survey] set Tag='PGEPerf479' where [Id]='03ef29ec-98ac-6e25-ea7c-39e0e3bea590'
UPDATE [dbo].[Survey] set Tag='PGEPerf480' where [Id]='b79ecda7-8363-0912-dc61-39e0e3d00db2'
UPDATE [dbo].[Survey] set Tag='PGEPerf481' where [Id]='bdf557be-ad4a-9084-8814-39e0e3dc7815'
UPDATE [dbo].[Survey] set Tag='PGEPerf482' where [Id]='516df9d1-7044-8bda-8971-39e0e3de848b'
UPDATE [dbo].[Survey] set Tag='PGEPerf483' where [Id]='f34e92d9-6100-b818-19ff-39e0e3e0803d'
UPDATE [dbo].[Survey] set Tag='PGEPerf484' where [Id]='11ae1f69-aa12-17ff-cd9f-39e0e3e4d975'
UPDATE [dbo].[Survey] set Tag='PGEPerf485' where [Id]='db8a9af5-fde5-165f-6a01-39e0e3e8b590'
UPDATE [dbo].[Survey] set Tag='PGEPerf486' where [Id]='c93dc5c7-bba4-da18-a533-39e0e3da1b2e'
UPDATE [dbo].[Survey] set Tag='PGEPerf487' where [Id]='5f96a429-eba2-d039-25e2-39e0e3f48a62'
UPDATE [dbo].[Survey] set Tag='PGEPerf488' where [Id]='c8ee4866-e716-3e0e-b85a-39e0e3eb090b'
UPDATE [dbo].[Survey] set Tag='PGEPerf489' where [Id]='69919267-956a-915b-33a2-39e0e3fc9162'
UPDATE [dbo].[Survey] set Tag='PGEPerf490' where [Id]='9abaa4fc-d52d-67ee-b6e2-39e0e3fa7dde'
UPDATE [dbo].[Survey] set Tag='PGEPerf491' where [Id]='b0651e6e-1f7d-4785-4b8f-39e0e401c11a'
UPDATE [dbo].[Survey] set Tag='PGEPerf492' where [Id]='7e97c9ce-7dfb-7796-ca89-39e19799d928'
UPDATE [dbo].[Survey] set Tag='PGEPerf493' where [Id]='17fe3431-7b70-8543-4b79-39e197ae1c1f'
UPDATE [dbo].[Survey] set Tag='PGEPerf494' where [Id]='62e2479a-e0ef-b5b2-302d-39e197b0a78f'
UPDATE [dbo].[Survey] set Tag='PGEPerf495' where [Id]='c41b5483-2076-0b2e-1d31-39e197b4881f'
UPDATE [dbo].[Survey] set Tag='PGEPerf496' where [Id]='75886dee-d746-785f-fd1f-39e197d440e3'
UPDATE [dbo].[Survey] set Tag='PGEPerf497' where [Id]='798d6fcc-c2c4-f7c5-c624-39e197d6a3dc'
UPDATE [dbo].[Survey] set Tag='PGEPerf498' where [Id]='ed2d861f-ee94-3352-4381-39e197db9831'
UPDATE [dbo].[Survey] set Tag='PGEPerf499' where [Id]='149699d3-d811-e491-d62a-39e19800d2d9'
UPDATE [dbo].[Survey] set Tag='PGEPerf500' where [Id]='b3c0193c-a718-c103-6b0e-39e1980c45f2'
UPDATE [dbo].[Survey] set Tag='PGEPerf501' where [Id]='fb0dadf4-074a-0ae4-c6d4-39e1981737fb'
UPDATE [dbo].[Survey] set Tag='PGEPerf502' where [Id]='9f0f14c2-89d9-af51-38dc-39e19818230f'
UPDATE [dbo].[Survey] set Tag='PGEPerf503' where [Id]='80c1926a-6d86-01d9-011d-39e198223b37'
UPDATE [dbo].[Survey] set Tag='PGEPerf504' where [Id]='036149df-407b-062d-0d6b-39e19830e1a7'
UPDATE [dbo].[Survey] set Tag='PGEPerf505' where [Id]='5056f562-b0c9-cbb1-0e65-39e198378ced'
UPDATE [dbo].[Survey] set Tag='PGEPerf506' where [Id]='cdb1bd38-0fc4-d3ee-7fe9-39e19838f6cf'
UPDATE [dbo].[Survey] set Tag='PGEPerf507' where [Id]='5c7bd063-7b40-5328-f852-39e1a701b5c4'
UPDATE [dbo].[Survey] set Tag='PGEPerf508' where [Id]='7f0d00e4-77aa-5e94-3b05-39e1a7220800'
UPDATE [dbo].[Survey] set Tag='PGEPerf509' where [Id]='640376db-0060-36d5-eca1-39e1a7248d69'
UPDATE [dbo].[Survey] set Tag='PGEPerf510' where [Id]='261d8fbc-a236-cbd6-4d19-39e1a72a391b'
UPDATE [dbo].[Survey] set Tag='PGEPerf511' where [Id]='8dac8b2a-4355-3049-a439-39e1a7329b67'
UPDATE [dbo].[Survey] set Tag='PGEPerf512' where [Id]='f1b8d909-290f-d12f-6ca0-39e1a74694a1'
UPDATE [dbo].[Survey] set Tag='PGEPerf513' where [Id]='6260eb67-9576-edbe-a6aa-39e1a7539a5e'
UPDATE [dbo].[Survey] set Tag='PGEPerf514' where [Id]='ce38ae43-776b-3d4e-a564-39e1a75cb2a7'
UPDATE [dbo].[Survey] set Tag='PGEPerf515' where [Id]='40d61c8a-e24d-aeab-a7bf-39e1a7717143'
UPDATE [dbo].[Survey] set Tag='PGEPerf516' where [Id]='c347f0e2-e27a-ccee-3fc7-39e1a777d22c'
UPDATE [dbo].[Survey] set Tag='PGEPerf517' where [Id]='0ae0d68a-0e4c-07ce-9f95-39e1a77ce05f'
UPDATE [dbo].[Survey] set Tag='PGEPerf518' where [Id]='ec600006-c5de-6d3d-f2d3-39e1a79b85c8'
UPDATE [dbo].[Survey] set Tag='PGEPerf519' where [Id]='24d8ab8a-a8e6-7956-1b1e-39e1a7a8ff37'
UPDATE [dbo].[Survey] set Tag='PGEPerf520' where [Id]='dfc11be9-ce76-1ddb-bc97-39e1a7ab68f6'
UPDATE [dbo].[Survey] set Tag='PGEPerf521' where [Id]='3da2977f-7b7e-8365-a070-39e1a7ac9782'
UPDATE [dbo].[Survey] set Tag='PGEPerf522' where [Id]='00a88873-1885-8704-66d6-39e1a7c38308'
UPDATE [dbo].[Survey] set Tag='PGEPerf523' where [Id]='4571c245-db22-0f4c-842f-39e1a7c5d1ac'
UPDATE [dbo].[Survey] set Tag='PGEPerf524' where [Id]='0fda6e00-7c4b-8471-5fd3-39e1a7c8896b'
UPDATE [dbo].[Survey] set Tag='PGEPerf525' where [Id]='bb2c4fd0-2b6a-2578-81ef-39e1a7caccd7'
UPDATE [dbo].[Survey] set Tag='PGEPerf526' where [Id]='a38fac5f-ac76-51a4-d051-39e1a7e1e245'
UPDATE [dbo].[Survey] set Tag='PGEPerf527' where [Id]='f717b0b3-fb3c-0353-5697-39e1a7ef776b'
UPDATE [dbo].[Survey] set Tag='PGEPerf528' where [Id]='72d7a3a0-6004-666f-e72a-39e1a7fd1d8e'
UPDATE [dbo].[Survey] set Tag='PGEPerf529' where [Id]='50b2482e-20a9-c4b9-1906-39e1a82a4aa5'
UPDATE [dbo].[Survey] set Tag='PGEPerf530' where [Id]='f1ddcfbc-1126-674c-3dc2-39e1a8461a0d'
UPDATE [dbo].[Survey] set Tag='PGEPerf531' where [Id]='e3507e8c-2fc8-570f-4a67-39e1a8509c66'

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
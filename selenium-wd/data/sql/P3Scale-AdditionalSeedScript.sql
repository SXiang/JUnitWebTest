DECLARE @customerId uniqueidentifier
DECLARE @locationID uniqueidentifier
DECLARE @userId uniqueidentifier

-- Add location 'AutoLocPicarroPerf1'
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE Name='Picarro'
UPDATE [dbo].[Location] SET [CustomerId]=@customerId, [Description]=N'AutoLocPicarroPerf1',[Latitude]='29.80910',[Longitude]='-95.16920' WHERE [Id]='DE13ACD0-C158-ECAC-7F48-39D18113E812' 
IF @@ROWCOUNT=0
	INSERT [dbo].[Location] ([Id], [CustomerId], [Description],[Latitude],[Longitude]) VALUES (N'DE13ACD0-C158-ECAC-7F48-39D18113E812', @customerId, N'AutoLocPicarroPerf1','29.80910','-95.16920')

-- Add 'AutomationAdmin' user
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Picarro' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='Default'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'Seed_first',[LastName]=N'seed_last',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',[SecurityStamp]=N'31facf51-53a8-44d7-bf92-828ea916693a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='AutomationAdmin'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C600',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'Seed_first',N'seed_last',NULL,NULL,N'0',N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',N'31facf51-53a8-44d7-bf92-828ea916693a',NULL,N'0',N'0',NULL,N'0',N'0','AutomationAdmin')

-- Add 'autoPicCustBoundaryPerfUser1@picarro.com' user
SELECT @customerId=[Id] FROM [dbo].[Customer] WHERE [Name]=N'Picarro' 
SELECT @locationID=[Id] FROM [dbo].[Location] WHERE Description='AutoLocPicarroPerf1'
UPDATE [dbo].[User] SET [CustomerId]=@customerId, [OpQualExpiration]=NULL,[Active]=N'1',[EulaAccepted]=N'1',[TimeZoneId]=N'00000000-0000-0000-0001-000000000000',[LocationId]=@locationID,[FirstName]=N'autoPicCustBoundary',[LastName]=N'PerfUser1',[CellPhoneNumber]=NULL,[Email]=NULL,[EmailConfirmed]=N'0',[PasswordHash]=N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',[SecurityStamp]=N'31facf51-53a8-44d7-bf92-828ea916693a',[PhoneNumber]=NULL,[PhoneNumberConfirmed]=N'0',[TwoFactorEnabled]=N'0',[LockoutEndDateUtc]=NULL,[LockoutEnabled]=N'0',[AccessFailedCount]=N'0' WHERE [UserName]='autoPicCustBoundaryPerfUser1@picarro.com'
IF @@ROWCOUNT=0
	INSERT INTO [dbo].[User] ([Id] ,[CustomerId],[OpQualExpiration],[Active],[EulaAccepted],[TimeZoneId],[LocationId],[FirstName],[LastName],[CellPhoneNumber],[Email],[EmailConfirmed],[PasswordHash],[SecurityStamp],[PhoneNumber],[PhoneNumberConfirmed],[TwoFactorEnabled],[LockoutEndDateUtc],[LockoutEnabled],[AccessFailedCount],[UserName]) VALUES   (N'DE734DDF-363E-49FC-8DBC-39C8C221C711',@customerId, NULL,N'1',N'1',N'00000000-0000-0000-0001-000000000000',@locationID,N'autoPicCustBoundary',N'PerfUser1',NULL,NULL,N'0',N'AKTzRlmNPUItAmRzgnIjNThBDKrraxHuWD4Q6e6zVlC/Sk0Ya4ItdeT5eoii+7jg2w==',N'31facf51-53a8-44d7-bf92-828ea916693a',NULL,N'0',N'0',NULL,N'0',N'0','autoPicCustBoundaryPerfUser1@picarro.com')

-- Add roles to the users
SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='AutomationAdmin'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='2DAD1417-00B6-D003-63B9-39CA528B5884' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'2DAD1417-00B6-D003-63B9-39CA528B5884',@userId)
	
SELECT @userId=[Id] FROM [dbo].[User] WHERE [UserName]='autoPicCustBoundaryPerfUser1@picarro.com'
IF NOT EXISTS (SELECT * FROM [dbo].[UserRole] WHERE [RoleId]='00000000-0000-0000-0003-000000000000' AND [UserId]=@userId)
	INSERT INTO [dbo].[UserRole]([RoleId],[UserId]) VALUES (N'00000000-0000-0000-0003-000000000000',@userId)
	
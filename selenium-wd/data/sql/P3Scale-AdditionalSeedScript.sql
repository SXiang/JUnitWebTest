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
	
-- Assign licenses to Picarro and Centerpoint customers.
-- Assign licenses to Picarro customer.
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

-- Assign licenses to CenterPoint customer.	
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'46FB8592-4477-4EE1-AB49-04A991036785' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'46FB8592-4477-4EE1-AB49-04A991036785', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'3EB7107C-EE5E-467C-92CE-222A83BCB7CF', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'34664FDF-C940-45AB-B37C-34ABD5EBF0A1', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'55CD10C5-80DB-004C-F0D6-39D4D9124478' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'55CD10C5-80DB-004C-F0D6-39D4D9124478', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5F93BB71-4F95-4E26-ABD3-500B68838D7B' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5F93BB71-4F95-4E26-ABD3-500B68838D7B', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'5EDF6C34-5769-43D1-AFE9-5E9223A7F48F', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'B5D075A8-94EE-4D28-AFC4-69283D124A53' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'B5D075A8-94EE-4D28-AFC4-69283D124A53', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'05D91EB6-90B2-440C-B887-69AC44A071ED' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'05D91EB6-90B2-440C-B887-69AC44A071ED', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A104C92B-38B6-4C1A-AA25-70B3B8308CB7', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'765F2DBB-B6F2-4D53-A6D3-9A071E54091E', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'4405B107-F234-43A6-BAC4-A7F0B8ECD7AC', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'944B3EF5-3F25-4358-82AD-AE632EA3F4C9', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'A42CFE59-405B-445B-AE63-AF74C5D9CBBA', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'CFD1EAD2-9386-4B81-95C7-B3BF1C8252FB', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'0105E725-4913-477A-B58D-CBD9D83E7C70' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'0105E725-4913-477A-B58D-CBD9D83E7C70', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'F5E43F3D-9F69-430D-9013-E902A34A1D18' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'F5E43F3D-9F69-430D-9013-E902A34A1D18', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'8CEF7673-80D2-407A-B833-F164C472CFDA' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'8CEF7673-80D2-407A-B833-F164C472CFDA', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
IF NOT EXISTS (SELECT * FROM [dbo].[CustomerLicensedFeatureOptions] WHERE [LicensedFeatureOptionId]=N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB' AND [CustomerId]=N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
    INSERT [dbo].[CustomerLicensedFeatureOptions] ([LicensedFeatureOptionId], [CustomerId]) VALUES (N'6A412F8C-E97F-4C88-B4D4-F2BCECBEABBB', N'85DDA6EE-106A-A783-4B0D-39CD1FBC3999')
	
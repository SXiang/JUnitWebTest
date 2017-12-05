package surveyor.unittest.source;

import org.junit.Test;
import org.junit.BeforeClass;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.DBCache;
import surveyor.dataaccess.source.Survey;
import surveyor.dbseed.source.DbSeedExecutor;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.source.BaseTest;

public class DbSeedExecutorTest extends DbSeedExecutorBaseTest {
	private static final String OP_PIC_1_SURVEY_ID_FROM_SEED = "1556ac85-a125-0347-2a02-39d4b529c6bd";
	private static final String OP_PIC_1_ANALZYER_ID_FROM_SEED = "34a34021-8814-8a01-9183-39d4b4de03be";
	private static LoginPageActions loginPageAction;
	private static ManageCustomerPageActions manageCustomerPageAction;

	private static final String EMPTY = BaseActions.EMPTY;
	private static final Integer NOTSET = BaseActions.NOTSET;

	@BeforeClass
	public static void BeforeClass()	{
		BaseTest.initializeTestObjects();

		loginPageAction = ActionBuilder.createLoginPageAction();
		manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();
	}

	@Test
	public void execute01_GenericSeedTest() throws Exception {
		DbSeedExecutor.executeGenericDataSeed();
		verifyGenericSeedDataIsPresent();
	}

	@Test
	public void execute02_GisDataSeedTest() throws Exception {
		// By default is no customerId is specified the GIS data is pushed for Picarro customer.
		DbSeedExecutor.executeGisSeed();
	}

	@Test
	public void execute02_GisDataSeedSpecifiedCustomer() throws Exception {
		String customerId = "5D073EF1-40E1-9BA0-E7BC-39DA8027337E";
		DbSeedExecutor.executeGisSeed(customerId);
		verifyGisSeedDataIsPresent(customerId);
	}

	@Test
	public void execute03_SurveyDataSeedTest() throws Exception {
		DbSeedExecutor.executeSurveyDataSeed();
		verifySurveySeedDataIsPresent();
	}

	@Test
	public void execute03_SurveyDataSeed_SqacusCustomer_Test() throws Exception {
		final String[] surveyTags = DbSeedExecutor.SQACUS_CUSTOMER_SURVEYS;
		DbSeedExecutor.executeSurveyDataSeed(surveyTags);
		verifySurveySeedDataIsPresent(surveyTags);
	}

	@Test
	public void execute03_SurveyDataSeedTest_SpecificSurveys() throws Exception {
		final String[] surveyTags = {"op-pic-1"};
		DbSeedExecutor.executeSurveyDataSeed(surveyTags);
		verifySurveySeedDataIsPresent(surveyTags);
	}

	@Test
	public void execute03_SurveyDataSeedTest_GeneratedSurveys() throws Exception {
		final String[] surveyTags = {"8HourSurvey-2", "8HourSurvey-3", "8HourSurvey-4", "8HourSurvey-5", "8HourSurvey-6", "8HourSurvey-7"
				,"8HourSurvey-8","8HourSurvey-9","8HourSurvey-10","8HourSurvey-11","8HourSurvey-12"};
		DbSeedExecutor.executeSurveyDataSeed(surveyTags);
		verifySurveySeedDataIsPresent(surveyTags, true, true);
	}

	@Test
	public void execute03_SurveyDataSeedALLTest() throws Exception {
		execute03_SurveyDataSeedTest();
		execute03_SurveyDataSeedTest_SpecificSurveys();
		execute03_SurveyDataSeedTest_GeneratedSurveys();
	}

	@Test
	public void execute03_LoadTestSurveyDataSeedTest() throws Exception {
		final String[] surveyTags = {PICGREATER4HR_DATAFILE, PICLESS4HR_DATAFILE
				,PIC8HR01_DATAFILE, PIC8HR02_DATAFILE, PIC8HR03_DATAFILE, PIC8HR04_DATAFILE, PIC8HR05_DATAFILE, PIC8HR06_DATAFILE
				,PIC8HR07_DATAFILE, PIC8HR08_DATAFILE, PIC8HR09_DATAFILE, PIC8HR10_DATAFILE, PIC8HR11_DATAFILE, PIC8HR12_DATAFILE};
		DbSeedExecutor.executeSurveyDataSeed(surveyTags);
		verifySurveySeedDataIsPresent(surveyTags, true, true);
	}

	@Test
	public void execute04_executeAllSeedTest() throws Exception {
		DbSeedExecutor.executeGenericDataSeed();
		verifyGenericSeedDataIsPresent();

		DbSeedExecutor.executeGisSeed();
		verifyGisSeedDataIsPresent(Customer.getCustomer(CUSTOMER_PICARRO).getId());
		verifyGisSeedDataIsPresent(Customer.getCustomer(CUSTOMER_SQACUS).getId());
		verifyGisSeedDataIsPresent(Customer.getCustomer(CUSTOMER_PGE).getId());

		DbSeedExecutor.executeSurveyDataSeed();
		verifySurveySeedDataIsPresent();

		DbSeedExecutor.executeSurveyDataSeed(DbSeedExecutor.SQACUS_CUSTOMER_SURVEYS);
		verifySurveySeedDataIsPresent(DbSeedExecutor.SQACUS_CUSTOMER_SURVEYS);
	}

	@Test
	public void execute05_pushAssetBoundariesForSpecificCustomerTest() throws Exception {
		// By default if no customerId is specified the GIS data is pushed for Picarro customer.
		String customerId = "D0DE3902-B726-7CA8-6FE1-39DBC4D817EB";
		DbSeedExecutor.executeGisSeed(customerId);
		verifyGisSeedDataIsPresent(customerId);
	}

	/**
	 * NOTES: PGE asset/boundaries seed is required by few Driver view test cases.
	 * @throws Exception
	 */
	@Test
	public void execute06_pushAssetBoundariesForPGECustomerTest() throws Exception {
		String customerId = "E871C797-B62D-EF28-0EA7-39CAE44E5C19";
		DbSeedExecutor.executeGisSeed(customerId);
		verifyGisSeedDataIsPresent(customerId);
	}

	@Test
	public void execute07_executeGISRefreshSeed_AllCustomers() throws Exception {
		DbSeedExecutor.executeGisRefreshDataSeed();
		verifyGisRefreshSeedDataIsPresent(Customer.getCustomer(CUSTOMER_PICARRO).getId());
		verifyGisRefreshSeedDataIsPresent(Customer.getCustomer(CUSTOMER_SQACUS).getId());
		verifyGisRefreshSeedDataIsPresent(Customer.getCustomer(CUSTOMER_PGE).getId());
	}

	@Test
	public void execute07_executeGISRefreshSeed_SingleCustomer() throws Exception {
		DbSeedExecutor.executeGisRefreshDataSeed(Customer.getCustomer(CUSTOMER_SQACUS).getId());
		verifyGisRefreshSeedDataIsPresent(Customer.getCustomer(CUSTOMER_SQACUS).getId());
	}

	@Test
	public void execute08_GisCustomerDataSeedTest() throws Exception {
		DbSeedExecutor.executeGISCustomerDataSeed();
		verifyGisCustomerSeedDataIsPresent();
	}

	@Test
	public void execute09_GisCustomerDataSeedSingleCustomerTest_GISSeedCustomer() throws Exception {
		final String customerName = "AutomationSeedCustomer00001";

		// PREP: execute GIS customer seed for customer.
		DbSeedExecutor.executeGISCustomerDataSeed();
		verifyGisCustomerSeedDataIsPresent();

		try {
			Customer customer = new Customer().get(customerName);

			String customerId = customer.getId();

			// modify customer name.
			customer.executeNonQuery(String.format("UPDATE [dbo].[Customer] SET Name='%s' WHERE Id='%s'", TestSetup.getUUIDString(), customerId));

			// remove licenses associated with customer.
			customer.executeNonQuery(String.format("DELETE [dbo].[CustomerLicensedFeatureOptions] WHERE CustomerId='%s'", customerId));

			// execute GIS customer seed for customer.
			DbSeedExecutor.executeGISCustomerDataSeedForSingleCustomer(customerName);

			// verify seed data was applied correctly.
			verifyGisCustomerSeedDataForCustomerIsCorrect(customerName);
		} finally {
			// RE-ADD GIS customer seed data.
			DbSeedExecutor.executeGISCustomerDataSeed();
			verifyGisCustomerSeedDataIsPresent();
		}
	}

	@Test
	public void execute09_GisCustomerDataSeedSingleCustomerTest_CustomerWithReferenceTableEntries() throws Exception {
		final String customerName = "sqacus";

		// PREP: Ensure seed data present for 'sqacus'.
		DbSeedExecutor.executeGenericDataSeed();
		verifyGenericSeedDataIsPresent();

		String customerId = Customer.getCustomer(customerName).getId();
		Customer customer = new Customer();

		try {
			// modify customer name.
			customer.executeNonQuery(String.format("UPDATE [dbo].[Customer] SET Name='%s' WHERE Id='%s'", TestSetup.getUUIDString(), customerId));

			// remove licenses associated with customer.
			customer.executeNonQuery(String.format("DELETE [dbo].[CustomerLicensedFeatureOptions] WHERE CustomerId='%s'", customerId));

			// execute GIS customer seed for customer.
			DbSeedExecutor.executeGISCustomerDataSeedForSingleCustomer(customerName);

			// verify referenced table entries are deleted correctly.
			verifyGisCustomerReferencedTableEntriesDeletedCorrectly(customerId);
		} finally {
			// RE-ADD Generic seed data after cleaning up the added licenses.
			customer.executeNonQuery(String.format("DELETE [dbo].[CustomerLicensedFeatureOptions] WHERE CustomerId='%s'", customerId));
			DbSeedExecutor.executeGenericDataSeed();
			verifyGenericSeedDataIsPresent();
		}
	}

	@Test
	public void detectFix01_SurveyDataSeedTest_NoPollutionDetection_PicarroSurvey() throws Exception {
		String[] surveyFileTags = { "op-pic-1" };

		if (!TestContext.INSTANCE.getTestSetup().getDbIpAddress().equals("127.0.0.1")) {
			Log.warn("Test skipped. NOT running on localhost DB");
			return;
		}

		// Trigger detectFix first to get survey in good state (as data might have changed from other test executions).
		String surveyId = OP_PIC_1_SURVEY_ID_FROM_SEED;
		String analyzerId = OP_PIC_1_ANALZYER_ID_FROM_SEED;

		Survey survey = new Survey();
		survey.executeNonQuery(String.format("UPDATE [dbo].[CaptureEvent] SET Disposition=Disposition+1 WHERE SurveyId='%s' AND AnalyzerId='%s'", surveyId, analyzerId));
		assertTrue("Survey seed data polluted. Seed data correction should have been applied.", DbSeedExecutor.detectFixSurveySeed(surveyFileTags) == true);

		// Then check again and ensure no pollution is detected.
		assertTrue("Survey seed data correct. No pollution should have been detected.", DbSeedExecutor.detectFixSurveySeed(surveyFileTags) == false);
	}

	@Test
	public void detectFix02_SurveyDataSeedTest_NoPollutionDetection_SQACusSurvey() throws Exception {
		String[] surveyFileTags = { "op-pic-sqacus-1" };
		assertTrue("Survey seed data correct. No pollution should have been detected.", DbSeedExecutor.detectFixSurveySeed(surveyFileTags) == false);
	}

	/**
	 * This test updates DB seed data and will be skipped if NOT running on localhost DB.
 	*/
	@Test
	public void detectFix03_SurveyDataSeedTest_CaptureEvent_PollutionDetected() throws Exception {
		String[] surveyFileTags = { "op-pic-1" };

		if (!TestContext.INSTANCE.getTestSetup().getDbIpAddress().equals("127.0.0.1")) {
			Log.warn("Test skipped. NOT running on localhost DB");
			return;
		}

		String surveyId = OP_PIC_1_SURVEY_ID_FROM_SEED;
		String analyzerId = OP_PIC_1_ANALZYER_ID_FROM_SEED;

		Survey survey = new Survey();
		survey.executeNonQuery(String.format("UPDATE [dbo].[CaptureEvent] SET Disposition=Disposition+1 WHERE SurveyId='%s' AND AnalyzerId='%s'", surveyId, analyzerId));
		assertTrue("Survey seed data polluted. Seed data correction should have been applied.", DbSeedExecutor.detectFixSurveySeed(surveyFileTags) == true);
	}

	/**
	 * This test updates DB seed data and will be skipped if NOT running on localhost DB.
 	*/
	@Test
	public void detectFix04_SurveyDataSeedTest_FieldOfView_PollutionDetected() throws Exception {
		String[] surveyFileTags = { "op-pic-1" };

		if (!TestContext.INSTANCE.getTestSetup().getDbIpAddress().equals("127.0.0.1")) {
			Log.warn("Test skipped. NOT running on localhost DB");
			return;
		}

		String surveyId = OP_PIC_1_SURVEY_ID_FROM_SEED;
		String analyzerId = OP_PIC_1_ANALZYER_ID_FROM_SEED;

		Survey survey = new Survey();
		survey.executeNonQuery(String.format("UPDATE [dbo].[FieldOfView] SET EpochTime=EpochTime+1 WHERE SurveyId='%s' AND AnalyzerId='%s'", surveyId, analyzerId));
		assertTrue("Survey seed data polluted. Seed data correction should have been applied.", DbSeedExecutor.detectFixSurveySeed(surveyFileTags) == true);
	}

	/**
	 * This test updates DB seed data and will be skipped if NOT running on localhost DB.
 	*/
	@Test
	public void detectFix05_SurveyDataSeedTest_Peak_PollutionDetected() throws Exception {
		String[] surveyFileTags = { "op-pic-1" };

		if (!TestContext.INSTANCE.getTestSetup().getDbIpAddress().equals("127.0.0.1")) {
			Log.warn("Test skipped. NOT running on localhost DB");
			return;
		}

		String surveyId = OP_PIC_1_SURVEY_ID_FROM_SEED;
		String analyzerId = OP_PIC_1_ANALZYER_ID_FROM_SEED;

		Survey survey = new Survey();
		survey.executeNonQuery(String.format("UPDATE [dbo].[Peak] SET Amplitude=Amplitude+1 WHERE SurveyId='%s' AND AnalyzerId='%s'", surveyId, analyzerId));
		assertTrue("Survey seed data polluted. Seed data correction should have been applied.", DbSeedExecutor.detectFixSurveySeed(surveyFileTags) == true);
	}

	/**
	 * This test updates DB seed data and will be skipped if NOT running on localhost DB.
 	*/
	@Test
	public void detectFix06_SurveyDataSeedTest_Segment_PollutionDetected() throws Exception {
		String[] surveyFileTags = { "op-pic-1" };

		if (!TestContext.INSTANCE.getTestSetup().getDbIpAddress().equals("127.0.0.1")) {
			Log.warn("Test skipped. NOT running on localhost DB");
			return;
		}

		String surveyId = OP_PIC_1_SURVEY_ID_FROM_SEED;
		Survey survey = new Survey();
		survey.executeNonQuery(String.format("UPDATE [dbo].[Segment] SET Mode=Mode+1 WHERE SurveyId='%s'", surveyId));
		assertTrue("Survey seed data polluted. Seed data correction should have been applied.", DbSeedExecutor.detectFixSurveySeed(surveyFileTags) == true);
	}

	/**
	 * This test updates DB seed data and will be skipped if NOT running on localhost DB.
 	*/
	@Test
	public void detectFix07_SurveyDataSeedTest_SurveyResult_PollutionDetected() throws Exception {
		String[] surveyFileTags = { "op-pic-1" };

		if (!TestContext.INSTANCE.getTestSetup().getDbIpAddress().equals("127.0.0.1")) {
			Log.warn("Test skipped. NOT running on localhost DB");
			return;
		}

		String surveyId = OP_PIC_1_SURVEY_ID_FROM_SEED;
		Survey survey = new Survey();
		survey.executeNonQuery(String.format("UPDATE [dbo].[SurveyResult] SET FieldOfView=Breadcrumb WHERE SurveyId='%s'", surveyId));
		assertTrue("Survey seed data polluted. Seed data correction should have been applied.", DbSeedExecutor.detectFixSurveySeed(surveyFileTags) == true);
	}

	@Test
	public void cleanup01_GisDataSeedTest_SpecificCustomer_VerifyNoErrors() throws Exception {
		Log.info("\nRunning cleanup01_GisDataSeedTest_SpecificCustomer_VerifyNoErrors ...");

		final String customerId = "7084D990-2DF2-D11E-EAFD-39E29618B4BD";
		DbSeedExecutor.cleanUpGisSeed(customerId);

		// Verify GIS seed data was removed correctly.
		verifyGisSeedDataIsNotPresent(customerId);
	}

	@Test
	public void cleanup01_GisDataSeedTest() throws Exception {
		Log.info("\nRunning cleanup01_GisDataSeedTest ...");

		final int LOGIN_USER_ROW_ID = 6;	 	/* LoginRowID. AutomationAdmin */
		final int newCustomerRowID = 7;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, LOGIN_USER_ROW_ID);

		// Create new customer.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, newCustomerRowID /*customerRowID*/);

		// Add GIS seed for customer.
		Customer customer = Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name);
		String customerId = customer.getId();
		DbSeedExecutor.executeGisSeed(customerId);

		// Verify GIS seed was added correctly.
		verifyGisSeedDataIsPresent(customerId);

		// Remove GIS seed from the customer.
		DbSeedExecutor.cleanUpGisSeed(customerId);

		// Verify GIS seed data was removed correctly.
		verifyGisSeedDataIsNotPresent(customerId);
	}

	private void verifyGisCustomerSeedDataForCustomerIsCorrect(String customerName) {
		final Integer expectedLicenseCount = 23;
		DBCache.INSTANCE.purgeCache(Customer.CACHE_KEY);
		Customer customer = new Customer().get(customerName);
		assertTrue(customer != null);
		Integer actualLicenseCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[CustomerLicensedFeatureOptions] WHERE CustomerId='" + customer.getId() + "'");
		assertTrue(String.format("Expected name [%s]. Actual=[%s]", customerName, customer.getName()), customer.getName().equals(customerName));
		assertTrue(String.format("Expected [%d] licenses. Found=[%d]", expectedLicenseCount, actualLicenseCount), actualLicenseCount == expectedLicenseCount);
	}

	private void verifyGisCustomerReferencedTableEntriesDeletedCorrectly(String customerId) {
		Customer customer = new Customer();

		Integer anemometerCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[Anemometer]  WHERE CalibrationRecordId IN (SELECT [ID] FROM [dbo].[CalibrationRecord] WHERE [SurveyorUnitId] IN ((SELECT [Id] FROM [dbo].[SurveyorUnit] WHERE [LocationId] IN (SELECT [Id] FROM [dbo].[Location] WHERE CustomerID='" + customerId + "'))))");
		assertTrue(String.format("[Anemometer] Expected Count=0, Actual=%d", anemometerCount), anemometerCount == 0);

		Integer inletCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[Inlet]  WHERE CalibrationRecordId IN (SELECT [ID] FROM [dbo].[CalibrationRecord] WHERE [SurveyorUnitId] IN ((SELECT [Id] FROM [dbo].[SurveyorUnit] WHERE [LocationId] IN (SELECT [Id] FROM [dbo].[Location] WHERE CustomerID='" + customerId + "'))))");
		assertTrue(String.format("[Inlet] Expected Count=0, Actual=%d", inletCount), inletCount == 0);

		Integer calibrationRecordCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[CalibrationRecord] WHERE [SurveyorUnitId] IN ((SELECT [Id] FROM [dbo].[SurveyorUnit] WHERE [LocationId] IN (SELECT [Id] FROM [dbo].[Location] WHERE CustomerID='" + customerId + "')))");
		assertTrue(String.format("[CalibrationRecord] Expected Count=0, Actual=%d", calibrationRecordCount), calibrationRecordCount == 0);

		Integer referenceGasBottleCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[ReferenceGasBottle] WHERE SurveyorUnitId IN (SELECT [Id] FROM [dbo].[SurveyorUnit] WHERE LocationID IN (SELECT [Id] FROM [dbo].[Location] WHERE [CustomerId]='" + customerId + "'))");
		assertTrue(String.format("[ReferenceGasBottle] Expected Count=0, Actual=%d", referenceGasBottleCount), referenceGasBottleCount == 0);

		Integer surveyorUnitCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[SurveyorUnit] WHERE LocationID IN (SELECT [Id] FROM [dbo].[Location] WHERE [CustomerId]='" + customerId + "')");
		assertTrue(String.format("[SurveyorUnit] Expected Count=0, Actual=%d", surveyorUnitCount), surveyorUnitCount == 0);

		Integer userLoginCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[UserLogin] WHERE [UserId] IN (SELECT [ID] FROM [dbo].[User] WHERE CustomerId = '" + customerId + "')");
		assertTrue(String.format("[UserLogin] Expected Count=0, Actual=%d", userLoginCount), userLoginCount == 0);

		Integer userClaimCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[UserClaim] WHERE [UserId] IN (SELECT [ID] FROM [dbo].[User] WHERE CustomerId = '" + customerId + "')");
		assertTrue(String.format("[UserClaim] Expected Count=0, Actual=%d", userClaimCount), userClaimCount == 0);

		Integer userRoleCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[UserRole] WHERE [UserId] IN (SELECT [ID] FROM [dbo].[User] WHERE CustomerId = '" + customerId + "')");
		assertTrue(String.format("[UserRole] Expected Count=0, Actual=%d", userRoleCount), userRoleCount == 0);

		Integer userCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[User] WHERE CustomerId = '" + customerId + "'");
		assertTrue(String.format("[User] Expected Count=0, Actual=%d", userCount), userCount == 0);

		Integer locationCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[Location] WHERE [CustomerId]='" + customerId + "'");
		assertTrue(String.format("[Location] Expected Count=0, Actual=%d", locationCount), locationCount == 0);

		Integer customerSettingsCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[CustomerSettings] WHERE CustomerId='" + customerId + "'");
		assertTrue(String.format("[CustomerSettings] Expected Count=0, Actual=%d", customerSettingsCount), customerSettingsCount == 0);

		Integer ftpConfigurationCount = customer.executeSingleInt("SELECT COUNT(*) FROM [dbo].[FTPConfiguration] WHERE CustomerId='" + customerId + "'");
		assertTrue(String.format("[FTPConfiguration] Expected Count=0, Actual=%d", ftpConfigurationCount), ftpConfigurationCount == 0);
	}
}
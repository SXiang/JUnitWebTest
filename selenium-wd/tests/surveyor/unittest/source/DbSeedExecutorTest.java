package surveyor.unittest.source;

import org.junit.Test;
import org.junit.BeforeClass;

import common.source.Log;
import static surveyor.scommon.source.SurveyorConstants.*;

import surveyor.dataaccess.source.Customer;
import surveyor.dbseed.source.DbSeedExecutor;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.source.BaseTest;

public class DbSeedExecutorTest extends DbSeedExecutorBaseTest {
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
		final String[] surveyTags = {"menlo-night-11-17-EQ-1", "menlo-night-11-17-EQ-2", "standard-survey-for-EQ-Rpt-1", "standard-survey-for-EQ-Rpt-2",
				"menlo-night-11-17-EQ-1-sqacus", "menlo-night-11-17-EQ-2-sqacus", "standard-survey-for-EQ-Rpt-1-sqacus", "standard-survey-for-EQ-Rpt-2-sqacus"};
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
}
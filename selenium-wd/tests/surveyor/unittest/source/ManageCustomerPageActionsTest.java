/**
 *
 */
package surveyor.unittest.source;


import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.CustomerLicenses;
import surveyor.dataaccess.source.CustomerLicenses.License;
import surveyor.dataaccess.source.CustomerWithGisDataPool;
import surveyor.regression.source.BaseMapViewTest;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorTestRunner;
import common.source.BaseHelper;
import common.source.Log;
import common.source.RegexUtility;
import common.source.TestContext;

@RunWith(SurveyorTestRunner.class)
public class ManageCustomerPageActionsTest extends BaseMapViewTest {
	private static final String automationReportingApiEndpoint = "http://localhost:63087";
	private static String CustomerNamePrefix = "AutomationSeedCustomer";
	private static Integer CUSTOMERS_NEEDED_BY_TESTS = 3;
	private static Integer NUM_LICENSES_ASSIGNED_TO_GIS_CUSTOMER_IN_SEED = 23;

	private static LoginPage loginPage;
	private static ManageCustomerPageActions manageCustomerPageAction;

	@BeforeClass
	public static void beforeTestClass() {
		initializeTestObjects();

		TestContext.INSTANCE.getTestSetup().setAutomationReportingApiEndpoint(automationReportingApiEndpoint);

		manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();

		releaseCustomersNeededByTestsFromPool();
	}

	@Before
	public void beforeTestMethod() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);
	}

	/**
	 * Test Description: Verify a GIS customer is fetched correctly from pool.
	 */
	@Test
	public void fetchNewGisCustomer_DefaultCustomer_LicensedFeaturesNotSpecified() throws Exception{
		Log.info("\nTestcase - fetchNewGisCustomer_DefaultCustomer_LicensedFeaturesNotSpecified\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		// Fetch customer from pool.
		manageCustomerPageAction.fetchNewGisCustomer(EMPTY, 9 /*customer licensed features NOT specified*/);

		String customerName = ManageCustomerPageActions.workingDataRow.get().name;
		Customer customer = Customer.getCustomer(customerName);
		assertTrue("Customer expected to be fetched correctly from GIS pool.", customer != null);

		List<License> licenses = new CustomerLicenses().getLicenses(customerName);
		assertTrue("Customer should have number of licenses assigned in seed.", licenses != null && licenses.size()==NUM_LICENSES_ASSIGNED_TO_GIS_CUSTOMER_IN_SEED);
	}

	/**
	 * Test Description: Verify a GIS customer is fetched correctly from pool and assigned specified licenses.
	 */
	@Test
	public void fetchNewGisCustomer_WithAllLicensedFeatures() throws Exception{
		Log.info("\nTestcase - fetchNewGisCustomer_WithAllLicensedFeatures\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		// Fetch customer from pool.
		manageCustomerPageAction.fetchNewGisCustomer(EMPTY, 14 /*customer with all licenses*/);

		String customerName = ManageCustomerPageActions.workingDataRow.get().name;
		List<String> licensesRowIds = RegexUtility.split(ManageCustomerPageActions.workingDataRow.get().licensedFeaturesRowIDs, ",");
		Customer customer = Customer.getCustomer(customerName);
		assertTrue("Customer expected to be fetched correctly from GIS pool.", customer != null);

		List<License> licenses = new CustomerLicenses().getLicenses(customerName);
		assertTrue("Customer should have same number of licenses assigned in input.", licenses != null && licenses.size()==licensesRowIds.size());
	}

	/**
	 * Test Description: Verify a GIS customer is fetched correctly from pool and assigned specified licenses.
	 */
	@Test
	public void fetchNewGisCustomer_WithNotAllLicensedFeatures() throws Exception{
		Log.info("\nTestcase - fetchNewGisCustomer_WithNotAllLicensedFeatures\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		// Fetch customer from pool.
		manageCustomerPageAction.fetchNewGisCustomer(EMPTY, 15 /*customer with licenses*/);

		String customerName = ManageCustomerPageActions.workingDataRow.get().name;
		List<String> licensesRowIds = RegexUtility.split(ManageCustomerPageActions.workingDataRow.get().licensedFeaturesRowIDs, ",");
		Customer customer = Customer.getCustomer(customerName);
		assertTrue("Customer expected to be fetched correctly from GIS pool.", customer != null);

		List<License> licenses = new CustomerLicenses().getLicenses(customerName);
		assertTrue("Customer should have same number of licenses assigned in input.", licenses != null && licenses.size()==licensesRowIds.size());
	}

	private static void releaseCustomersNeededByTestsFromPool() {
		IntStream.range(0, CUSTOMERS_NEEDED_BY_TESTS)
			.forEach(i -> {
				Integer padTimes = 5;
				Integer n = i + 1;
				while (n > 0) {
					n = n / 10;
					padTimes--;
				}

				Integer idx = i + 1;
				String customerName = CustomerNamePrefix + BaseHelper.prependStringWithChar(String.valueOf(idx), '0', padTimes);
				try {
					CustomerWithGisDataPool.releaseCustomer(customerName);
				} catch (Exception e) {
					fail(e.getMessage());
				}
			});
	}
}

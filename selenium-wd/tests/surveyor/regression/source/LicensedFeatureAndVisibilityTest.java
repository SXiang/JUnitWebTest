/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.FleetMapPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import java.util.Map;
import common.source.Log;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class LicensedFeatureAndVisibilityTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static DriverViewPage driverViewPage;
	private static ManageLocationsPage manageLocationsPage;
	private static FleetMapPage fleetMapPage;
	private static LoginPage loginPage;
	private static HomePage homePage;

	private static Map<String, String> testAccount;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();
		initializePageObjects();

		if(testAccount == null){
			testAccount = createTestAccount("LicFeature");
		}else{
			loginPage.open();
			loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
			manageCustomersPage.open();
			manageCustomersPage.editAndSelectLicensedFeatures(testAccount.get("customerName"), LicensedFeatures.values());
		}
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		manageCustomersPage = pageObjectFactory.getManageCustomersPage();
		PageFactory.initElements(getDriver(), manageCustomersPage);

		manageLocationsPage = pageObjectFactory.getManageLocationsPage();
		PageFactory.initElements(getDriver(), manageLocationsPage);

		driverViewPage = pageObjectFactory.getDriverViewPage();
		PageFactory.initElements(getDriver(), driverViewPage);

		fleetMapPage = pageObjectFactory.getFleetMapPage();
		PageFactory.initElements(getDriver(), fleetMapPage);

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);
		setLoginPage(loginPage);

		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);
		setHomePage(homePage);
	}

	/* * Test Case ID: TC2078_FleetMapLinkNotPresentWithoutLicense
	 * Script:
	 * - Log in as utility admin or supervisor
	 * - On home page, click on Fleet map
	 * - Log in as Picarro admin
	 * - Navigate to Manage Customer page and click on Edit button of above customer (Eg, CNP)
	 * - Disable "Fleet Map" privilege
	 * - Log in as utility admin or supervisor
	 * - Provide Fleet map URL directly
	 * Results:
	 *- Fleet map link is present
	 * - Fleet Map page showing locations of customer's surveyor vehicles. If customer has no vehicles, Fleet Map should show default map
	 * - Fleet Map link is not present
	 * - User is navigated to Home page and not navigated to Fleet Map page
	 */
	@Test
	public void TC2078_FleetMapLinkNotPresentWithoutLicense() throws Exception {
		Log.info("\nRunning TC2078_FleetMapLinkNotPresentWithoutLicense");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		/* With License */
		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);
		homePage.clickOnFleetMapLink();
		fleetMapPage.waitForFleetMaptoLoad();
		homePage.logout();

		/* Disable FleetMapViw */
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.FLEETMAPVIEW);
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

		fleetMapPage.open();
		homePage.waitForPageLoad();
		homePage.logout();
	}

	/* * Test Case ID: TC2109_CustomerHasMinAmpValuesForOperatorRRManualSurveyModesWithLicense
	 * Script:
	 * 	 * - Log into UI as Picarro Admin
	 * - Navigate to Manage Locations page
	 * - Select a location for target customer and click Edit
	 * - Navigate to Manage Customers page, select target customer and click Edit
	 * - Check the Operator checkbox
	 * - Log out and log back in and navigate to same Locations page
	 * - Repeat with Rapid Response
	 * Results:
	 * 	 * - List of locations for all customer is displayed
	 * - Survey Mode: Standard is present, but Survey Mode:Operator and Survey Mode:Rapid Response are not
	 * - After logging back in, Survey Mode: Operator will be present
	 * - After logging back in again, Survey Mode: Rapid Response will be present
	 */
	@Test
	public void TC2109_CustomerHasMinAmpValuesForOperatorRRManualSurveyModesWithLicense() throws Exception {
		Log.info("\nRunning TC2109_CustomerHasMinAmpValuesForOperatorRRManualSurveyModesWithLicense");

		String customerName = testAccount.get("customerName");
		String locationName = testAccount.get("locationName");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.OPERATOR, LicensedFeatures.RAPIDRESPONSE);

		/* Without License */
		manageLocationsPage.open();
		manageLocationsPage.findExistingLocationAndClickEdit(customerName, locationName);
		assertTrue(manageLocationsPage.isStandardMinAmpShowing());
		assertFalse(manageLocationsPage.isOperatorMinAmpShowing());
		assertFalse(manageLocationsPage.isRapidResponseMinAmpShowing());

		manageCustomersPage.open();
		manageCustomersPage.editAndSelectLicensedFeatures(customerName, LicensedFeatures.OPERATOR);

		/* With Operator License */
		manageLocationsPage.open();
		manageLocationsPage.findExistingLocationAndClickEdit(customerName, locationName);
		assertTrue(manageLocationsPage.isStandardMinAmpShowing());
		assertTrue(manageLocationsPage.isOperatorMinAmpShowing());
		assertFalse(manageLocationsPage.isRapidResponseMinAmpShowing());

		manageCustomersPage.open();
		manageCustomersPage.editAndSelectLicensedFeatures(customerName, LicensedFeatures.RAPIDRESPONSE);

		/* With RapidResponse License */
		manageLocationsPage.open();
		manageLocationsPage.findExistingLocationAndClickEdit(customerName, locationName);
		assertTrue(manageLocationsPage.isStandardMinAmpShowing());
		assertTrue(manageLocationsPage.isOperatorMinAmpShowing());
		assertTrue(manageLocationsPage.isRapidResponseMinAmpShowing());
	}
}
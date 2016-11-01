/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.FleetMapPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.PreferencesPage;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorSystemsPage;
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
	private static Map<String, String> testAccount;

	@BeforeClass
	public static void setupACLandVisibilityTest() {
		manageCustomersPage = new ManageCustomersPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageCustomersPage);
		manageLocationsPage = new ManageLocationsPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageLocationsPage);
		driverViewPage = new DriverViewPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), driverViewPage);
		fleetMapPage = new FleetMapPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), fleetMapPage);
	}

	@Before
	public void beforeTest() throws Exception{
		if(testAccount == null){
			testAccount = createTestAccount("LicFeature");
		}else{
			getLoginPage().open();
			getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
			manageCustomersPage.open();
			manageCustomersPage.editAndSelectLicensedFeatures(testAccount.get("customerName"), LicensedFeatures.values());
		}
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
		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);
		getHomePage().clickOnFleetMapLink();
		fleetMapPage.waitForFleetMaptoLoad();
		getHomePage().logout();

		/* Disable FleetMapViw */
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.FLEETMAPVIEW);
		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		fleetMapPage.open();
		getHomePage().waitForPageLoad();
		getHomePage().logout();
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

		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

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
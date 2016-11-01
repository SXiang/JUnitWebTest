/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import common.source.WebElementExtender;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ManageLocationsPageTest_Ethane extends SurveyorBaseTest {
	private static ManageLocationsPage manageLocationsPage;
	private static ManageCustomersPage manageCustomersPage;
	private static LoginPage loginPage;

	/**
	 * This method is called by the 'main' thread
	 */
	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(); // ensures TestSetup and TestContext are initialized before Page object creation.
	}

	/**
	 * This method is called by the 'worker' thread
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();

		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);

		manageLocationsPage = pageObjectFactory.getManageLocationsPage();
		PageFactory.initElements(getDriver(), manageLocationsPage);

		manageCustomersPage = pageObjectFactory.getManageCustomersPage();
		PageFactory.initElements(getDriver(), manageCustomersPage);
	}

	/**
	 * Test Case ID: TC1695 Test Description: Ethane: Verify Manage Locations page appears when logged in as Picarro Admin
	 *
	 * @throws IOException
	 *
	 */
	@Test
	public void TC1695_Manage_Locations_Ethane_Methane_Ratio_Column() {
		Log.info("\nRunning - TC1695 - Test Description: Ethane: Verify Manage Locations page appears when logged in as Picarro Admin");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();

		manageLocationsPage.getBtnAddNewLocation().click();

		assertTrue(WebElementExtender.isElementPresentAndDisplayed(manageLocationsPage.getEthMthRtoLbl()));
	}

	/**
	 * Test Case ID: TC1696 Test Description: Ethane: Verify True Ethane/Methane ratio can be updated and saved
	 *
	 * @throws IOException
	 *
	 */
	@Test
	public void TC1696_Manage_Locations_Edit_Ethane_Methane_Ratio_Column() {
		String locationName = "TC1696 Ethane "+ getTestSetup().getRandomNumber();
		String locationNewName= locationName + "_New";
		Log.info("\nRunning - TC1696 - Test Description: Ethane: Verify setting the Ethane/Methane ratio for the first time");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();

		manageLocationsPage.addNewLocation(locationName, SQACUS, SQACUSSULOC, "1", "2");
		manageLocationsPage.getInputSearch().sendKeys(locationName);
		assertTrue(manageLocationsPage.findExistingLocation(SQACUS, locationName));

		manageLocationsPage.editPDExistingLocation(SQACUS, locationName, locationNewName, ETHRNELAT, ETHRNELAT, "5", "7");
		assertTrue(manageLocationsPage.findExistingLocation(SQACUS, locationNewName));
	}

	/**
	 * Test Case ID: TC1697 Test Description: Ethane: Verify setting the Ethane/Methane ratio for the first time
	 *
	 * @throws IOException
	 *
	 */
	@Test
	public void TC1697_Manage_Locations_Add_Ethane_Methane_Ratio_Column() {
		String locationName = "TC1697 Ethane "+ getTestSetup().getRandomNumber();
		Log.info("\nRunning - TC1697 - Test Description: Ethane: Verify setting the Ethane/Methane ratio for the first time");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();

		manageLocationsPage.addNewLocation(locationName, SQACUS, SQACUSSULOC, "1", "2");
		manageLocationsPage.getInputSearch().sendKeys(locationName);
		assertTrue(manageLocationsPage.findExistingLocation(SQACUS, locationName));
	}

	/**
	 * Test Case ID: TC1698 Test Description: Ethane: Verify Manage Locations page appears when logged in as Customer Admin
	 *
	 * @throws IOException
	 *
	 */
	@Test
	public void TC1698_Manage_Locations_Ethane_Methane_Ratio_Column() {
		Log.info("\nRunning - TC1698 - Test Description: Verify Manage Locations page appears when logged in as Customer Admin");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		manageLocationsPage.open();

		manageLocationsPage.getBtnAddNewLocation().click();
		manageLocationsPage.waitForNewPageLoad();

		assertTrue(manageLocationsPage.getEthMthRtoLbl().isDisplayed());
	}
}
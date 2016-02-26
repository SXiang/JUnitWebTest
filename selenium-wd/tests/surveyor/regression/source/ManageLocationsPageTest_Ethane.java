/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataprovider.UserDataProvider;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
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

	@BeforeClass
	public static void setupManageLocationsPageTest() {
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);

		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
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

		assertTrue(manageLocationsPage.getEthMthRtoLbl().isDisplayed());
	}

	/**
	 * Test Case ID: TC1696 Test Description: Ethane: Verify True Ethane/Methane ratio can be updated and saved
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1696_Manage_Locations_Edit_Ethane_Methane_Ratio_Column() {
		String locationName= AUTLCTNM;
		Log.info("\nRunning - TC1696 - Test Description: Ethane: Verify True Ethane/Methane ratio can be updated and saved");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();

		manageLocationsPage.editExistingEthaneLocation(SQACUS, locationName, "5", "10");
		assertTrue(manageLocationsPage.findExistingLocation(SQACUS, locationName));
	}

	/**
	 * Test Case ID: TC1697 Test Description: Ethane: Verify setting the Ethane/Methane ratio for the first time
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1697_Manage_Locations_Add_Ethane_Methane_Ratio_Column() {
		String locationName = "TC1697 Ethane "+ testSetup.getRandomNumber();
		Log.info("\nRunning - TC1697 - Test Description: Ethane: Verify setting the Ethane/Methane ratio for the first time");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();

		manageLocationsPage.addEthaneNewLocation(locationName, SQACUS, SQACUSSULOC, "2", "2");
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

		assertTrue(manageLocationsPage.getEthMthRtoLbl().isDisplayed());
	}


}


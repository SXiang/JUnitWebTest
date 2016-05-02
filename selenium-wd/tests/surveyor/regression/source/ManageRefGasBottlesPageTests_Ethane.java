/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.List;

/**
 *
 * 
 */
@RunWith(SurveyorTestRunner.class)
public class ManageRefGasBottlesPageTests_Ethane extends SurveyorBaseTest {
	private static ManageRefGasBottlesPage manageRefGasBottlesPage;

	@BeforeClass
	public static void setupManageRefGasBottlesPageTest() {
		manageRefGasBottlesPage = new ManageRefGasBottlesPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  manageRefGasBottlesPage);
	}

	
	/**
	 * Test Case ID: TC1734 Test Description: Ethane - Verify that Ethane To Methane Ratio column added to ReferenceGasBottle Page
	 * 
	 */
	@Test
	public void TC1734_VerifyEthMthClmn_RefGasBottles() {
		Log.info("\nRunning TC1734 - Test Description: Ethane - Verify that Ethane To Methane Ratio column added to ReferenceGasBottle Page");

		manageRefGasBottlesPage.login(SQAPICSUP, USERPASSWORD);

		manageRefGasBottlesPage.open();

		manageRefGasBottlesPage.getBtnAddNewRefGasBottle().click();
		assertTrue(manageRefGasBottlesPage.getInputEthMthRto().isDisplayed());

	}

	/**
	 * Test Case ID: TC1735 Test Description: Ethane - Verify that user can add value to Ethane To Methane Ratio column to ReferenceGasBottle Page
	 * 
	 */
	@Test
	public void TC1735_AddValueEthMthClmn_RefGasBottles() {
		String strLotNumber = "TC135_Ethane"+ testSetup.getFixedSizeRandomNumber(5);

		Log.info("\nRunning TC1735 - Test Description: Ethane - Verify that user can add value to Ethane To Methane Ratio column to ReferenceGasBottle Page");

		manageRefGasBottlesPage.login(SQAPICSUP, USERPASSWORD);

		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber, "-32", "15", SQAETHCUST, SQAETHLOC, SQAETHSUR);
		manageRefGasBottlesPage.waitForNewPageLoad();

		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber, SQAETHSUR));
	}

	/**
	 * Test Case ID: TC1738 Test Description: Ethane- Verify min/max value (%) from drop down/up to Ethane To Methane Ratio column to ReferenceGasBottle Page
	 * 
	 */
	@Test
	public void TC1738_MinMAxValueEthMthClmn_RefGasBottles() {
		String strLotNumber1 = "TC138_Ethane_1"+ testSetup.getFixedSizeRandomNumber(5);
		String strLotNumber2 = "TC138_Ethane_2"+ testSetup.getFixedSizeRandomNumber(5);

		Log.info("\nRunning TC1738 - Test Description: Ethane- Verify min/max value (%) from drop down/up to Ethane To Methane Ratio column to ReferenceGasBottle Page");

		manageRefGasBottlesPage.login(SQAPICSUP, USERPASSWORD);

		manageRefGasBottlesPage.open();

		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber1, "-32", "1", SQAETHCUST, SQAETHLOC, SQAETHSUR);
		manageRefGasBottlesPage.waitForNewPageLoad();

		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber1, SQAETHSUR));

		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber2, "-32.17", "99", SQAETHCUST, SQAETHLOC, SQAETHSUR);
		manageRefGasBottlesPage.waitForNewPageLoad();

		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber2, SQAETHSUR));

	}

	
}

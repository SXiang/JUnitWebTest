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

import common.source.Log;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

import static surveyor.scommon.source.SurveyorConstants.*;

/**
 *
 * 
 */
@RunWith(SurveyorTestRunner.class)
public class ManageRefGasBottlesPageTests_Ethane extends SurveyorBaseTest {
	private static ManageRefGasBottlesPage manageRefGasBottlesPage;
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

		manageRefGasBottlesPage = pageObjectFactory.getManageRefGasBottlesPage();
		PageFactory.initElements(getDriver(),  manageRefGasBottlesPage);
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
		String strLotNumber = "TC1735_Ethane"+ getTestSetup().getFixedSizeRandomNumber(5);

		Log.info("\nRunning TC1735 - Test Description: Ethane - Verify that user can add value to Ethane To Methane Ratio column to ReferenceGasBottle Page");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber, "-32", "15", SQACUS, SQACUSLOC, SQACUSLOCSUR);
		manageRefGasBottlesPage.waitForNewPageLoad();

		manageRefGasBottlesPage.getInputSearch().sendKeys(strLotNumber);
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber, SQACUSLOCSUR));
	}

	/**
	 * Test Case ID: TC1738 Test Description: Ethane- Verify min/max value (%) from drop down/up to Ethane To Methane Ratio column to ReferenceGasBottle Page
	 * 
	 */
	@Test
	public void TC1738_MinMAxValueEthMthClmn_RefGasBottles() {
		String strLotNumber1 = "TC138_Ethane_1"+ getTestSetup().getFixedSizeRandomNumber(5);
		String strLotNumber2 = "TC138_Ethane_2"+ getTestSetup().getFixedSizeRandomNumber(5);
		String ethMthRtoZero = "0";
		String ethMthRtoHund = "100";

		Log.info("\nRunning TC1738 - Test Description: Ethane- Verify min/max value (%) from drop down/up to Ethane To Methane Ratio column to ReferenceGasBottle Page");

		manageRefGasBottlesPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageRefGasBottlesPage.open();

		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber1, "-32", "1", SQACUS, SQACUSLOC, SQACUSLOCSUR);
		manageRefGasBottlesPage.waitForNewPageLoad();
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber1, SQACUSLOCSUR));

		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber2, "-32", "99", SQACUS, SQACUSLOC, SQACUSLOCSUR);
		manageRefGasBottlesPage.waitForNewPageLoad();
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber2, SQACUSLOCSUR));

		manageRefGasBottlesPage.addRefGasBottle(strLotNumber2, "-32", "0", SQACUS, SQACUSLOC, SQACUSLOCSUR, false);
		String actualErrorMessage = manageRefGasBottlesPage.getEthMthRtoErr().getText();
		Log.info("ACTUAL:" + actualErrorMessage);
		Log.info("EXPECTED:" + ETHMTHERRGRTMSG);
		assertTrue(actualErrorMessage.equals(ETHMTHERRGRTMSG));
		manageRefGasBottlesPage.clickOnCancelBtn();

		manageRefGasBottlesPage.addRefGasBottle(strLotNumber2, "-32", "100", SQACUS, SQACUSLOC, SQACUSLOCSUR, false);
		actualErrorMessage = manageRefGasBottlesPage.getEthMthRtoErr().getText();
		Log.info("ACTUAL:" + actualErrorMessage);
		Log.info("EXPECTED:" + ETHMTHERRLESSMSG);
		assertTrue(actualErrorMessage.equals(ETHMTHERRLESSMSG));
		manageRefGasBottlesPage.clickOnCancelBtn();

		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber2, SQACUSLOCSUR));

		manageRefGasBottlesPage.btnAddNewRefGasBottle.click();

		manageRefGasBottlesPage.inputLotNumber.clear();
		manageRefGasBottlesPage.inputLotNumber.sendKeys("TestZeroEthMthRto");
		manageRefGasBottlesPage.inputIsoValue.clear();
		manageRefGasBottlesPage.inputIsoValue.sendKeys("-32");

		assertTrue(manageRefGasBottlesPage.getEthMethRtoLbl().getText().equals(ETHMTHRTOLABEL));

		if ((ethMthRtoZero != null) && (ethMthRtoZero != "")) { 
			manageRefGasBottlesPage.inputEthMthRto.clear();
			manageRefGasBottlesPage.inputEthMthRto.sendKeys(ethMthRtoZero);
		}

		manageRefGasBottlesPage.getDropdownSurveyor().click();

		assertTrue(manageRefGasBottlesPage.getEthMthRtoErr().getText().equals(ETHMTHERRGRTMSG));
		manageRefGasBottlesPage.clickOnCancelBtn();

		manageRefGasBottlesPage.btnAddNewRefGasBottle.click();

		manageRefGasBottlesPage.inputLotNumber.clear();
		manageRefGasBottlesPage.inputLotNumber.sendKeys("TestZeroEthMthRto");
		manageRefGasBottlesPage.inputIsoValue.clear();
		manageRefGasBottlesPage.inputIsoValue.sendKeys("-32");

		assertTrue(manageRefGasBottlesPage.getEthMethRtoLbl().getText().equals(ETHMTHRTOLABEL));

		if ((ethMthRtoHund != null) && (ethMthRtoHund != "")) { 
			manageRefGasBottlesPage.inputEthMthRto.clear();
			manageRefGasBottlesPage.inputEthMthRto.sendKeys(ethMthRtoHund);
		}

		manageRefGasBottlesPage.getDropdownSurveyor().click();

		assertTrue(manageRefGasBottlesPage.getEthMthRtoErr().getText().equals(ETHMTHERRLESSMSG));
		manageRefGasBottlesPage.clickOnCancelBtn();

	}

	/**
	 * Test Case ID: TC1739 Test Description: Ethane - Ethane To Methane Ratio to ReferenceGasBottle Page should not take negative value
	 * 
	 */
	@Test
	public void TC1739_InvalidMinMAxValueEthMthClmn_RefGasBottles() {
		String ethMthRtoNeg = "-1";

		Log.info("\nRunning TC1739 - Test Description: Ethane - Ethane To Methane Ratio to ReferenceGasBottle Page should not take negative value");

		manageRefGasBottlesPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageRefGasBottlesPage.open();

		manageRefGasBottlesPage.btnAddNewRefGasBottle.click();

		manageRefGasBottlesPage.inputLotNumber.clear();
		manageRefGasBottlesPage.inputLotNumber.sendKeys("TestZeroEthMthRto");
		manageRefGasBottlesPage.inputIsoValue.clear();
		manageRefGasBottlesPage.inputIsoValue.sendKeys("-32");

		assertTrue(manageRefGasBottlesPage.getEthMethRtoLbl().getText().equals(ETHMTHRTOLABEL));

		if ((ethMthRtoNeg != null) && (ethMthRtoNeg != "")) { 
			manageRefGasBottlesPage.inputEthMthRto.clear();
			manageRefGasBottlesPage.inputEthMthRto.sendKeys(ethMthRtoNeg);
		}

		manageRefGasBottlesPage.getDropdownSurveyor().click();

		assertTrue(manageRefGasBottlesPage.getEthMthRtoErr().getText().equals(ETHMTHERRGRTMSG));
		manageRefGasBottlesPage.clickOnCancelBtn();
	}
}

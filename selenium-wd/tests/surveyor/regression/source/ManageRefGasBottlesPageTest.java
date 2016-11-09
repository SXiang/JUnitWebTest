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

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataprovider.RunAs;
import surveyor.dataprovider.UserDataProvider;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ManageRefGasBottlesPageTest extends SurveyorBaseTest {
	private static final String SQAPICAD_AND_SQAPICSUP = "sqapicad@picarro.com,sqapicsup@picarro.com";

	private static ManageRefGasBottlesPage manageRefGasBottlesPage;
	private static LoginPage loginPage;

	private enum ManageRefGasBottleTestCaseType {
		MaxCharsLimit
	}

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
	 * Test Case ID: TC135_AddRefGasBottle_PicAdmin
	 * Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro Default Administrator
	 *
	 */
	@Test
	public void TC135_AddRefGasBottle_PicAdmin() {
		String strLotNumber = getTestSetup().getFixedSizeRandomNumber(5) + "TC135";

		Log.info("\nRunning TC135_AddRefGasBottle_PicAdmin - Test Description: Adding a Ref Gas Bottle to a customer surveyor by "
				+ "Picarro Default Administrator");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber, "-32", SQACUS, SQACUSLOC, SQACUSLOCSUR);

		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber, SQACUSLOCSUR));
	}

	/**
	 * Test Case ID: TC1250_AddRefGasBottle_PicSU
	 * Test Description: Add Reference Gas Bottles as Picarro Support user
	 *
	 */
	@Test
	public void TC1250_AddRefGasBottle_PicSU() {
		String strLotNumber = getTestSetup().getRandomNumber() + "TC1250";

		Log.info("\nRunning TC1250_AddRefGasBottle_PicSU - Test Description: Add Reference Gas Bottles as Picarro Support user");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber, "-32", SQACUS, SQACUSLOC, SQACUSLOCSUR);

		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber, SQACUSLOCSUR));
	}

    /**
	 * Test Case ID: TC1251
     * Test Description: Picarro Support user - Add reference gas bottle - blank required fields
	 * Script:
	 *  - Log in as Picarro Support user
     *  - On Home Page, click Administration -> Manage Reference Gas Bottles -> Add New Reference Gas Bottle
     *  - Keep required fields blank. Click OK
     * Results:
     *  - "The field is required." message should be displayed
	 */
    @Test
	public void TC1251_AddLocationBlankFields_PicSupport(){

		Log.info("\nRunning TC1251_AddLocationBlankFields_PicSupport - "+
		         "Test Description: Add reference gas bottle - blank required fields");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addRefGasBottle("", "", null, SQACUS, SQACUSLOC, SQACUSLOCSUR, false);

		assertEquals(BLANKFIELDERROR, manageRefGasBottlesPage.getLotNumberError());
		assertEquals(BLANKFIELDERROR, manageRefGasBottlesPage.getIsotopicValueError());

		manageRefGasBottlesPage.clickOnCancelBtn();

	}

    /**
	 * Test Case ID: TC465_AddRefGasBottleLotNumber50CharLimit_PicarroAdminSupport
	 * Test Description:  	More than 50 characters not allowed in Lot Number field present on Add Reference Gas Bottle screens
	 * Test Script: - On Home Page, click Administration -> Manage Reference Gas Bottles -> Add New Reference Gas Bottle
	 * Expected Results: User cannot enter more than 50 characters
	 * Current implementation:
	 * Current Issue:
     * Future Improvement:
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users=SQAPICAD_AND_SQAPICSUP)
	public void TC137_TC1253_AddRefGasBottleLotNumber50CharLimit_PicarroAdminSupport(String user, String pwd) {
		String str34chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcd";
		String str35chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcde";
		String isoValue = "-32";
		String ethaneMethaneRatio = "1";
		String tcID = getTestCaseName(ManageRefGasBottleTestCaseType.MaxCharsLimit, user);
		String lotNum50Chars = getTestSetup().getFixedSizeRandomNumber(16) + str34chars;
		String lotNum51Chars = getTestSetup().getFixedSizeRandomNumber(16) + str35chars;
		String password = new CryptoUtility().decrypt(pwd);

		Log.info("\nRunning "
				+ tcID
				+ "_AddRefGasBottleLotNumber50CharLimit_PicarroAdminSupport - Test Description: More than 50 characters not allowed "
				+ "in Lot Number field present on Add Reference Gas Bottle screens");

		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(lotNum50Chars, isoValue, ethaneMethaneRatio, SQACUS, SQACUSLOC, SQACUSLOCSUR, true);
		manageRefGasBottlesPage.addNewRefGasBottle(lotNum51Chars, isoValue, ethaneMethaneRatio, SQACUS, SQACUSLOC, SQACUSLOCSUR, true);

		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(lotNum50Chars, SQACUSLOCSUR));

		manageRefGasBottlesPage.open();
		assertFalse(manageRefGasBottlesPage.findExistingRefGasBottle(lotNum51Chars, SQACUSLOCSUR));
	}

	/**
	 * Test Case ID: TC500_ManageRefGasBottle_PicSup
	 * Test Description: - On Home Page, click on Administration -> Manage Reference gas bottle
	 * Expected Result: - User can see list of all reference gas bottles
	 * - User can see Add New Reference Gas Bottle button
	 *
	 */
	@Test
	public void TC500_ManageRefGasBottle_PicSup() {
		String strLotNumber1 = getTestSetup().getFixedSizeRandomNumber(5) + "_PicAdmin_TC500";
		String strLotNumber2 = getTestSetup().getFixedSizeRandomNumber(5) + "_PicSup_TC500";

		Log.info("\nRunning TC500_ManageRefGasBottle_PicSup");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber1, "-32", SQACUS, SQACUSLOC, SQACUSLOCSUR);
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber1, SQACUSLOCSUR));

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageRefGasBottlesPage.open();
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber1, SQACUSLOCSUR));
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber2, "-32", SQACUS, SQACUSLOC, SQACUSLOCSUR);
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber2, SQACUSLOCSUR));
	}

	/**
	 * Returns the testCase ID based on the username provided by DataProvider.
	 */
	private String getTestCaseName(ManageRefGasBottleTestCaseType testCaseType, String username) {
		String testCase = "";
		switch (testCaseType) {
		case MaxCharsLimit:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC137";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC1253";
			}
			break;
		}
		return testCase;
	}
}
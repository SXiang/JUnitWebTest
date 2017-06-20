package surveyor.unittest.source;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorConstants;
import surveyor.scommon.source.SurveyorSystemsPage;

public class SurveyorSystemsPageTest extends BaseTest {

	private static SurveyorSystemsPage surveyorSystemsPage;
	private static LoginPage loginPage;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		initializeTestObjects(); // ensures TestSetup and TestContext are initialized before Page object creation.
	}

	@Before
	public void setUp() throws Exception {
		initializeTestObjects();

		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);

		surveyorSystemsPage = pageObjectFactory.getSurveyorSystemsPage();
		PageFactory.initElements(getDriver(), surveyorSystemsPage);
	}

	@Test
	public void testVerifyCustomerSpecificSurveyorsAreShown() {
		loginPage.open();
		loginPage.loginNormalAs(SurveyorConstants.SQACUSSU, SurveyorConstants.USERPASSWORD);

		surveyorSystemsPage.open();
		surveyorSystemsPage.waitForPageLoad();
		assertTrue(surveyorSystemsPage.verifyCustomerSpecificSurveyorsAreShown(SurveyorConstants.SQACUS));
	}
}

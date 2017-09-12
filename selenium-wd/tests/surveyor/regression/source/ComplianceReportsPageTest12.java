package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.EQDAYSURVEY;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;

import common.source.Log;
import common.source.WebElementExtender;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.dataprovider.EQReportDataProvider;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest12 extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static LatLongSelectionControl latLongSelectionControl = null;
	private static Map<String, String> testReport;
	
	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();

		initializePageActions();

		initializeTestPageObjects();

		// Select run mode here.
		setPropertiesForTestRunMode();
	}

	private void initializeTestPageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		LoginPage loginPage = pageObjectFactory.getLoginPage();
		setLoginPage(loginPage);
		PageFactory.initElements(getDriver(), loginPage);

		HomePage homePage = pageObjectFactory.getHomePage();
		setHomePage(homePage);
		PageFactory.initElements(getDriver(), homePage);
		
		latLongSelectionControl = new LatLongSelectionControl(getDriver());
		PageFactory.initElements(getDriver(), latLongSelectionControl);
	}

	private static void setPropertiesForTestRunMode() throws Exception {
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}
	
	/**
	 * Test Case ID: TC1255_CustomerBoundaryAreaSelectionPersistCustomerBoundarySelectorScreen
	 * Test Description: Customer boundary area selection persist on Customer Boundary Selector screen
	 * Script: -
	 *	- - Login to p3sqa.picarro.com
	 *	- - Navigate New or Copy compliance report screen
	 *	- - Select Customer boundary and click on Boundary Selector
	 *	- - Select any customer boundary and click OK
	 *	- - Click on Boundary Selector button
	 *	- - Click OK
	 * Results: -
	 *	- - Selected boundary areashould persist
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1255, location = ComplianceReportDataProvider.class)
	public void TC1255_CustomerBoundaryAreaSelectionPersistCustomerBoundarySelectorScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1255_CustomerBoundaryAreaSelectionPersistCustomerBoundarySelectorScreen ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewReportButton(EMPTY, reportDataRowID1);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, reportDataRowID1);
		complianceReportsPageAction.getComplianceReportsPage().openCustomerBoundarySelector();

		String boundaryName = "3350-F6";
		latLongSelectionControl.waitForModalDialogOpen()
			.switchMode(ControlMode.MapInteraction)
			.waitForMapImageLoad()
			.selectCustomerBoundaryType(ComplianceReportEntity.CustomerBoundaryFilterType.SmallBoundary.toString())
			.setCustomerBoundaryName(boundaryName)
			.switchMode(ControlMode.Default)
			.clickOkButton()
			.waitForModalDialogToClose();

		String actualValue = complianceReportsPageAction.getComplianceReportsPage().getBoundarySelectedText().getAttribute("value");
		Log.info(String.format("Expected Boundary Selected TextField value = '%s'. Actual value = '%s'", boundaryName, actualValue));
		assertTrue(actualValue.equals(boundaryName));
	}

	/**
	 * Test Case ID: TC557_ComplianceReportSurveySearchGridShouldNotDisplayEQModeSurvey
	 * Test Description: Compliance Report survey search grid should not display EQ mode surveys
	 * Script:
	 *	- Login as Customer Supervisor user
	 * - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 * - Search the Survey
	 * Results:
	 *- EQ surveys should not be displayed in searched survey grid
	 */
	@Test
	@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC557, location = EQReportDataProvider.class)
	public void TC557_ComplianceReportSurveySearchGridShouldNotDisplayEQModeSurvey(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC557_ComplianceReportSurveySearchGridShouldNotDisplayEQModeSurvey ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewReportButton(EMPTY, reportDataRowID1);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, reportDataRowID1);

		complianceReportsPageAction.getComplianceReportsPage().inputSurveyTag(EQDAYSURVEY);
		complianceReportsPageAction.getComplianceReportsPage().clickOnSearchSurveyButton();
		complianceReportsPageAction.getComplianceReportsPage().waitForSurveyTabletoLoad();
		assertTrue(complianceReportsPageAction.getComplianceReportsPage().isSurveyTableEmpty());
	}
	
	@Test
	public void ReportTest_TC37_VerifyCustomerUtilityAdminLoginProfile() throws Exception {
		testReport = addTestReport(SQACUSSU, USERPASSWORD,
				SurveyModeFilter.Standard);
		String rptTitle = testReport.get(SurveyType.Standard + "Title");
		String strCreatedBy = testReport.get("userName");

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);

		complianceReportsPageAction.getComplianceReportsPage().open();
		complianceReportsPageAction.getComplianceReportsPage().waitForPageLoad();
		complianceReportsPageAction.getComplianceReportsPage().searchAndDeleteReport(rptTitle, strCreatedBy);
	}
}

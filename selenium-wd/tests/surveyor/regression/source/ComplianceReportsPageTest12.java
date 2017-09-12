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
	 * Test Case ID: TC1496_AddLISABoxOptonToExistingCustomer
	 * Test Description: Add LISA Box option to existing customer
	 * Script: 
	 *	- A customer that does not have LISA Box 1.0 option enabled
	 *	- Customer has license for GIS Layers, Highlight LISA Assets
	 *	- Log into PCubed as Picarro Admin
	 *	- Navigate to Picarro Administration -> Manage Customers page
	 *	- Select a customer and click the Edit button
	 *	- Check LISA Box 1.0 checkbox, and make sure that the Report Shape File box is checked and click OK
	 *	- Navigate to Reports -> Compliance Reports and click on the New Compliance Report button
	 *	- Fill out the necessary fields and select LISAs in the Views section.
	 *	- Click OK
	 *	- Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- Click on the Shape File button
	 *	- Run the Shape Files through GIS software like ArcGIS
	 * Results: 
	 *	- In the report view, the LISA box should be rectagular in shape and circumscribe the LISA itself
	 *	- All LISA boxes should have a minimum width of 100 feet, regardless of the shape of the LISA itself
	 *	- There should be a 50 foot buffer between the LISA bubble and the side of the box perpendicular to the line bisecting the LISA
	 *	- The segment of Main contained within the LISA box should be highlighted
	 *	-  Any Service assets that are contained in or touch a LISA box should be highlighted along its entire length 
	 *	- The main to which each of the highlighted Services are attached should also be highlighted, up to 25 feet on either side of the "T" connected to the Service
	 *	- Overlapping LISA boxes should not be combined into a single box. Each LISA should have its own LISA box drawn as a distinct box unto itself
	 *	- The report View should have all LISAs in the shape of boxes, not fans or circles
	 *	- The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test //TBD
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1496, location = ComplianceReportDataProvider.class)
	public void TC1496_AddLISABoxOptonToExistingCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning  TC1496_AddLISABoxOptonToExistingCustomer...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
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

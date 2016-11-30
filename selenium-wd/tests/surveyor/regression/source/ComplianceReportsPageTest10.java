package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;

import org.junit.Before;

import common.source.BasePage;
import common.source.Log;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.SystemHistoryReportsPage;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.ReferenceGasReportsPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.dataprovider.ComplianceReportDataProvider;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest10 extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static ManageLocationPageActions manageLocationPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;
	private static SurveyViewPageActions surveyViewPageAction;

	private static ReferenceGasReportsPage referenceGasReportsPage = null;
	private static SystemHistoryReportsPage systemHistoryReportsPage = null;
	private static LatLongSelectionControl latLongSelectionControl = null;

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

		referenceGasReportsPage = pageObjectFactory.getReferenceGasReportsPage();
		PageFactory.initElements(getDriver(), referenceGasReportsPage);

		systemHistoryReportsPage = pageObjectFactory.getSystemHistoryReportsPage();
		PageFactory.initElements(getDriver(), systemHistoryReportsPage);

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
		homePageAction = new HomePageActions(getDriver(), getBaseURL(), getTestSetup());
		manageCustomerPageAction = new ManageCustomerPageActions(getDriver(), getBaseURL(), getTestSetup());
		manageUsersPageAction = new ManageUsersPageActions(getDriver(), getBaseURL(), getTestSetup());
		manageLocationPageAction = new ManageLocationPageActions(getDriver(), getBaseURL(), getTestSetup());
		surveyViewPageAction = new SurveyViewPageActions(getDriver(), getBaseURL(), getTestSetup());
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
		testEnvironmentAction = new TestEnvironmentActions();
	}

	/**
	 * Test Case ID: TC799_SearchReportsNon_ExistingOrInvalidReportName
	 * Test Description: Search reports for non-existing or invalid report name
	 * Script: -
	 *	- - Log in to application and navigate to compliance report page
	 *	- - Search non-existing or invalid compliance report with report name
	 *	- - Navigate to Ref Gas Report page
	 *	- - Searchnon-existing or invalidRef Gas report with report name
	 *	- - Navigate to System History Report page
	 *	- - Searchnon-existing or invalidSystem History report with report name
	 * Results: -
	 *	- - Reports page should display message as No matching records found
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC799, location = ComplianceReportDataProvider.class)
	public void TC799_SearchReportsNon_ExistingOrInvalidReportName(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC799_SearchReportsNon_ExistingOrInvalidReportName ...");

		String nonExistentReportTitle = "ThisIsANonExistentReportTitle_ZXCVBNM";

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		complianceReportsPageAction.open(EMPTY, NOTSET);
		assertFalse(complianceReportsPageAction.getComplianceReportsPage().searchReport(nonExistentReportTitle, LoginPageActions.workingDataRow.get().username));
		assertTrue(complianceReportsPageAction.getComplianceReportsPage().getEmptyTableMessage().equals(NOMATCHINGSEARCH));

		referenceGasReportsPage.open();
		assertFalse(referenceGasReportsPage.searchReport(nonExistentReportTitle, LoginPageActions.workingDataRow.get().username));
		assertTrue(referenceGasReportsPage.getEmptyTableMessage().equals(NOMATCHINGSEARCH));

		systemHistoryReportsPage.open();
		assertFalse(systemHistoryReportsPage.searchReport(nonExistentReportTitle, LoginPageActions.workingDataRow.get().username));
		assertTrue(systemHistoryReportsPage.getEmptyTableMessage().equals(NOMATCHINGSEARCH));
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
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, reportDataRowID1);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, reportDataRowID1);
		complianceReportsPageAction.getComplianceReportsPage().openCustomerBoundarySelector();

		String boundaryName = "Level 2-AA";
		latLongSelectionControl.waitForModalDialogOpen()
			.switchMode(ControlMode.MapInteraction)
			.waitForMapImageLoad()
			.selectCustomerBoundaryType(ReportsCompliance.CustomerBoundaryFilterType.SmallBoundary.toString())
			.setCustomerBoundaryName(boundaryName)
			.switchMode(ControlMode.Default)
			.clickOkButton()
			.waitForModalDialogToClose();

		String actualValue = complianceReportsPageAction.getComplianceReportsPage().getBoundarySelectedText().getAttribute("value");
		Log.info(String.format("Expected Boundary Selected TextField value = '%s'. Actual value = '%s'", boundaryName, actualValue));
		assertTrue(actualValue.equals(boundaryName));
	}

	/**
	 * Test Case ID: TC1274_SurveyTagLinkPresentSearchGridComplianceReportWorking
	 * Test Description: Survey tag link present in search grid on compliance report is working
	 * Script: -
	 *	- - Log in to application and navigate to new compliance report screen
	 *	- - Click on Search button to search the surveys
	 *	- - Click on survey tag link present in survey grid
	 * Results: -
	 *	- - User is navigated to survey view
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1274, location = ComplianceReportDataProvider.class)
	public void TC1274_SurveyTagLinkPresentSearchGridComplianceReportWorking(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1274_SurveyTagLinkPresentSearchGridComplianceReportWorking ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, reportDataRowID1);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, reportDataRowID1);

		complianceReportsPageAction.getComplianceReportsPage().inputSurveyTag(PICADMNSTDTAG);
		complianceReportsPageAction.getComplianceReportsPage().clickOnSearchSurveyButton();
		complianceReportsPageAction.getComplianceReportsPage().waitForSurveyTabletoLoad();
		complianceReportsPageAction.getComplianceReportsPage().clickOnFirstSurveyLink();

		// Verify clicking on first survey opens the survey page in new window.
		BasePage.verifyPageLoadedInNewTab(getDriver(), () -> surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC1256_DisplayErrorMessageThumbnailDownloadMapPdfOldReportsPrior21Build
	 * Test Description: Display error message for thumbnail/download map pdf for old reports (prior to 2.1 build)
	 * Script: -
	 *	- - Log in to application
	 *	- - On Home Page, Click on Reports -& Compliance Report -& Compliance Viewer button
	 *	- - Hover on grayed out image
	 * Results: -
	 *	- - Images that are not available should be grayed out and NO Image available will be displayed
	 *	- - Tool tip should be present - Map Preview Images are not available for reports run prior to version 2.1
	 *	- - An icon should be present indicating file download is not available
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1256, location = ComplianceReportDataProvider.class)
	public void TC1256_DisplayErrorMessageThumbnailDownloadMapPdfOldReportsPrior21Build(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1256_DisplayErrorMessageThumbnailDownloadMapPdfOldReportsPrior21Build ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC165_SortReportListBasedCompletionDateOtherAttributes
	 * Test Description: Sort report list based on completion date and other attributes
	 * Script: -
	 *	- Sort report list by report title or created by or date attributes present on all reports screen
	 * Results: -
	 *	- - User is able to sort the list of reports based on specified attribute
	 *	- - Sorting by Report Name not allowed
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC165, location = ComplianceReportDataProvider.class)
	public void TC165_SortReportListBasedCompletionDateOtherAttributes(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC165_SortReportListBasedCompletionDateOtherAttributes ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPaginationAndSortingOnAllColumns(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC194_VerifyMaxSurveyDurationReachedMessageDisplayedUserIfUserTriesAddSurveysHavingTotalDurationAbove100Hours
	 * Test Description: Verify "Max Survey Duration Reached" message is displayed to user if user tries to add surveys having total duration above 100 hours
	 * Script: -
	 *	- - Add Surveys having duration total equal to 100 hours
	 *	- - Add Surveys having duration total more than 100 hours
	 *	- - Delete any one survey and try to add new survey
	 * Results: -
	 *	- - Max Survey Duration Reached message is displayed on the search button
	 *	- - User is able to add survey which doesnot exceed 100 hours duration  range
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC194, location = ComplianceReportDataProvider.class)
	public void TC194_VerifyMaxSurveyDurationReachedMessageDisplayedUserIfUserTriesAddSurveysHavingTotalDurationAbove100Hours(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC194_VerifyMaxSurveyDurationReachedMessageDisplayedUserIfUserTriesAddSurveysHavingTotalDurationAbove100Hours ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifySurveyGreaterThan100HoursCannotBeAdded(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC238_SearchInvalidCustomerBoundaryBoundarySelectorScreen
	 * Test Description: Search invalid customer boundary on boundary selector screen
	 * Script: -
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Select 'Customer Boundary' and click on Boundary Selector button
	 *	- - Search invalid boundary
	 * Results: -
	 *	- - Customer Boundaries are present on map
	 *	- - Invalid boundary name is not present and user is notified that no such boundary exists
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC238, location = ComplianceReportDataProvider.class)
	public void TC238_SearchInvalidCustomerBoundaryBoundarySelectorScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC238_SearchInvalidCustomerBoundaryBoundarySelectorScreen ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.enterCustomerBoundaryUsingAreaSelector(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyErrorMessages("<ERROR_MESSAGE>", NOTSET));
	}

	/**
	 * Test Case ID: TC243_GenerateMultipleComplianceReports4_5AtSameTime
	 * Test Description: generate multiple compliance reports (4-5) at the same time
	 * Script: -
	 *	- - Generate 4 or 5 compliance reports simultaneously with multiple views and multiple surveys
	 * Results: -
	 *	- Reports generated successfully
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC243, location = ComplianceReportDataProvider.class)
	public void TC243_GenerateMultipleComplianceReports4_5AtSameTime(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC243_GenerateMultipleComplianceReports4_5AtSameTime ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC525_MapZoomsOutCorrectly
	 * Test Description: Map zooms in and out correctly
	 * Script: -
	 *	- - On the Compliance Reports page, click New Compliance Report
	 *	- - Click the Customer Boundary button
	 *	- - Select a boundary level
	 *	- - Zoom out by clicking the - button.
	 *	- - Click the button several more times, waiting a few seconds between each click
	 *	- - Repeat steps zooming in by clicking the + button
	 * Results: -
	 *	- - The zoom level should persist.
	 *	- - The zoom levels should persist after each click.
	 *	- - The zoom levels should persist after each click.
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC525, location = ComplianceReportDataProvider.class)
	public void TC525_MapZoomsOutCorrectly(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC525_MapZoomsOutCorrectly ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.enterCustomerBoundaryUsingAreaSelector(EMPTY, getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC622_ComplianceReportsCheckRedTraceDueDataLatency
	 * Test Description: Compliance Reports Check for red trace due to data latency
	 * Script: -
	 *	-         - Create a Compliance Report using a survey that includes red trace, selecting Breadcrumbs and   FOV
	 * Results: -
	 *	- - The   breadcrumb color should be red from the point of data latency until the point   where data latency ends
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC622, location = ComplianceReportDataProvider.class)
	public void TC622_ComplianceReportsCheckRedTraceDueDataLatency(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC622_ComplianceReportsCheckRedTraceDueDataLatency ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC624_ComplianceReportRedTraceIsotopicCaptureAnalysis
	 * Test Description: Compliance Report with Red Trace and Isotopic Capture Analysis
	 * Script: -
	 *	-      - Create a Compliance Report using a survey that includes red trace and Isotopic Capture, selecting Breadcrumbs and   FOV
	 * Results: -
	 *	- - The   Compliance Report view should exactly match the Driver View, including red   trace and Isotopic Capture
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC624, location = ComplianceReportDataProvider.class)
	public void TC624_ComplianceReportRedTraceIsotopicCaptureAnalysis(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC624_ComplianceReportRedTraceIsotopicCaptureAnalysis ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC1538_VerifyGeographicFilterSelectedByDefaultNewComplianceReportScreen
	 * Test Description: Verify Geographic filter is selected by default on new compliance report screen
	 * Script: -
	 *	- - Navigate to Reports -& Compliance -& New Compliance Report
	 *	- - Provide Lat/Long co-ordinates
	 *	- - Click on Search button
	 * Results: -
	 *	- - Geographic filter is by default selected in Survey Selector section
	 *	- - Surveys present in selected geographic area are displayed
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1538, location = ComplianceReportDataProvider.class)
	public void TC1538_VerifyGeographicFilterSelectedByDefaultNewComplianceReportScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1538_VerifyGeographicFilterSelectedByDefaultNewComplianceReportScreen ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyGeographicFilterIsSelected(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifySearchedSurveysAreForSelectedArea(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1539_VerifyGeographicFilterSelectedByDefaultCopyComplianceReportScreen
	 * Test Description: Verify Geographic filter is selected by default on copy compliance report screen
	 * Script: -
	 *	- - Navigate to Reports -& Compliance -& Copy Compliance Report
	 *	- - Select any one of the customerboundary
	 *	- - Click on Search button
	 * Results: -
	 *	- - Geographic filter is by default selected in Survey Selector section
	 *	- - Surveys present in selected customer boundary area are displayed
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1539, location = ComplianceReportDataProvider.class)
	public void TC1539_VerifyGeographicFilterSelectedByDefaultCopyComplianceReportScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1539_VerifyGeographicFilterSelectedByDefaultCopyComplianceReportScreen ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.copyReport(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyGeographicFilterIsSelected(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifySearchedSurveysAreForSelectedArea(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC207_VerifyReportSurveyModesAreNotModififedIfUserClicksNOChangeReportModeButton
	 * Test Description: Verify report and survey modes are not modififed if user clicks on NO change report mode button
	 * Script: -
	 *	- - Login as Picarro Admin
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Select Rapid Response report mode and include rapid reponse survey
	 *	- - Select Manual or Standard report mode
	 *	- - Click on NO button present on change report mode dialog pop up
	 *	- - Change the report mode to manual mode and include manual survey
	 *	- - Select Rapid response or Standard report mode
	 *	- - Click on NO button present on change report mode dialog pop up
	 * Results: -
	 *	- - Report Mode and Survey modes should presists and is not modified to other
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC207, location = ComplianceReportDataProvider.class)
	public void TC207_VerifyReportSurveyModesAreNotModififedIfUserClicksNOChangeReportModeButton(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC207_VerifyReportSurveyModesAreNotModififedIfUserClicksNOChangeReportModeButton ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC208_VerifyReportSurveyModesAreNotModififedIfUserClicksNOChangeReportModeButtonReportsUsingCopyFunctionality
	 * Test Description: Verify report and survey modes are not modififed if user clicks on NO change report mode button for reports using copy functionality
	 * Script: -
	 *	- - Login as Picarro Admin
	 *	- - On Home Page, click Reports -& Compliance -& Copy report button
	 *	- - Select Rapid Response report mode and include rapid reponse survey
	 *	- - Select Manual or Standard report mode
	 *	- - Click on NO button present on change report mode dialog pop up
	 *	- - Change the report mode to manual mode and include manual survey
	 *	- - Select Rapid response or Standard report mode
	 *	- - Click on NO button present on change report mode dialog pop up
	 * Results: -
	 *	- - Report Mode and Survey modes should presists and is not modified to other
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC208, location = ComplianceReportDataProvider.class)
	public void TC208_VerifyReportSurveyModesAreNotModififedIfUserClicksNOChangeReportModeButtonReportsUsingCopyFunctionality(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC208_VerifyReportSurveyModesAreNotModififedIfUserClicksNOChangeReportModeButtonReportsUsingCopyFunctionality ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.copyReport(EMPTY, getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC209_VerifyDefaultMapLocationNon_CaliforniaCustomers
	 * Test Description: Verify default map location for non-California customers
	 * Script: -
	 *	- Pre-requisities:
	 *	- - Create a non-cali customer's user (e.g. Houston) and location should have lat long coordiantes as per the location
	 *	- - Login as a non-cali customer's user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Provide Image Map height and width
	 *	- - Click on Lat/Long Map selector button
	 * Results: -
	 *	- - Default map location should be user's location (e.g. Houston and not Santa Clara)
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC209, location = ComplianceReportDataProvider.class)
	public void TC209_VerifyDefaultMapLocationNon_CaliforniaCustomers(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC209_VerifyDefaultMapLocationNon_CaliforniaCustomers ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageUsersPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageUsersPageAction.createNewCustomerUser(EMPTY, getReportRowID(reportDataRowID1));

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1));
	}
}

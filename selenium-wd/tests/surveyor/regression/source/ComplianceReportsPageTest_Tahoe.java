package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;

import org.junit.Before;

import common.source.BasePage;
import common.source.Log;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.dataprovider.ComplianceReportDataProvider;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest_Tahoe extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static SurveyViewPageActions surveyViewPageAction;
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
	 * Test Case ID: TC2238_ComplianceReportWithOnlyHighlightLISAAssetsInView
	 * Test Description: Generate compliance report for customer having license for Asset box feature and only highlight LISA Assets is selected in views 
	 *	- Customer has license for  Asset box and GIS layers
	 *	- Customer has GIS data
	 *	- Customer has survey with leaks and gaps, both intersecting with couple of assets 
	 * Script: -
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Timezone : PST, Report Mode: Standard, Exclusion Radius: 0
	 *	- - Click Custom Boundary
	 *	- - Click Lat/Long Map Selector
	 *	- - Press the Shift key, then right-click and drag to select the desired area on the map and click OK
	 *	- - Select one or more surveys
	 *	- - Select Asset Boxes from Search Area Preference
	 *	- - Add View with base map value: select only Highlight LISA Assets
	 *	- - Select indication, Gap table in Optional tabular PDF content
	 *	- - Select Asset layers
	 *	- - Click OK
	 *	- - When report has completed, click Report Viewer Icon and download Compliance Table and View PDFs
	 * Results: -
	 *	- - Assets intersecting LISA should be highlighted in report view PDF
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC238, location = ComplianceReportDataProvider.class)
	public void TC2238_ComplianceReportWithOnlyHighlightLISAAssetsInView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2238_ComplianceReportWithOnlyHighlightLISAAssetsInView ...");


		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
	}
}

package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG2;
import common.source.Log;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsWithNewSurveyPageTest extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static MeasurementSessionsPage measurementSessionsPage;
	private static HomePage homePage;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();

		initializePageActions();

		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());;

		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);
		measurementSessionsPage = pageObjectFactory.getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(),  measurementSessionsPage);

		// Select run mode here.
		setPropertiesForTestRunMode();
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
	 * Test Case ID: TC210_GenerateReportTryDeleteSurveyUsedWhileGeneratingReport
	 * Test Description: Generate report and try to delete the survey used while generating the report
	 * Script: -
	 *	- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- Provide report title, timezone : PST, Survey Mode: Standard, Exclusion Radius:0
	 *	- Select Indications and isotopic table
	 *	- Provide lat long values
	 *	- Include the survey and note down the survey tag
	 *	- Add View 1: select Isocapture, Field Notes, Assets and Boundaries. Base Map Value : Map
	 *	- Click on OK and click Download icon
	 *	- Navigate to Driving Surveys page and search the survey used while generating the report
	 *	- Click on Delete survey button
	 * Results: -
	 *	- - Report should be generated and user can download the report successfully
	 *	- - Show notification that survey is used in generated report or Delete Survey button itself is unavailable
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC210, location = ComplianceReportDataProvider.class)
	public void TC210_GenerateReportTryDeleteSurveyUsedWhileGeneratingReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC210_GenerateReportTryDeleteSurveyUsedWhileGeneratingReport ...");

		final int LOGIN_USER_ROW_ID = 6;        /* LoginRowID. AutomationAdmin */
	    final int DB3_ANALYZER_ROW_ID = 9;      /* Analyzer3/Surveyor3. Replay db3 file rowID */
	    final int SURVEY_ROW_ID = 51;           /* Survey information rowID */
	    final int SURVEY_RUNTIME_IN_SECONDS = 20; /* Number of seconds to run the survey for. */

		TestEnvironmentActions.generateSurveyForUser(LOGIN_USER_ROW_ID, DB3_ANALYZER_ROW_ID, SURVEY_ROW_ID, SURVEY_RUNTIME_IN_SECONDS);

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerCloseButton(EMPTY, getReportRowID(reportDataRowID1));

		measurementSessionsPage.open();
		measurementSessionsPage.performSearch(PICADMNSTDTAG2);
		measurementSessionsPage.clickOnFirstSurveyDeleteLink();
		assertTrue(homePage.getReturnHomePage().isEnabled());
		assertTrue(homePage.getReturnHomePage().isDisplayed());
		homePage.getReturnHomePage().click();
	}
}

package surveyor.regression.source;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.BaseReportsPageActionTest;

public class ActionsVerification_ComplianceReportsTest extends BaseReportsPageActionTest {

	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		homePageAction = new HomePageActions(getDriver(), getBaseURL(), getTestSetup());
		manageCustomerPageAction = new ManageCustomerPageActions(getDriver(), getBaseURL(), getTestSetup());
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		testEnvironmentAction = new TestEnvironmentActions();

		// Select run mode here.
		setTestRunMode(ReportTestRunMode.UnitTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	public ActionsVerification_ComplianceReportsTest() {
	}


	@Test
	public void TC_ComplianceReports_VerifyNewComplianceReportsPageFields() throws Exception {
		Log.info("\nRunning TC_ComplianceReports_VerifyNewComplianceReportsPageFields ...");

		Integer userDataRowID = 6;
		Integer reportDataRowID1 = 49;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userDataRowID);

		complianceReportsPageAction.open(EMPTY, reportDataRowID1);
		assertTrue(complianceReportsPageAction.verifyPageLoaded(EMPTY, reportDataRowID1));

		complianceReportsPageAction.clickOnNewReportButton(EMPTY, reportDataRowID1);
		complianceReportsPageAction.selectReportMode(EMPTY, reportDataRowID1);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, reportDataRowID1);
		assertTrue(complianceReportsPageAction.verifySurveyModeFiltersByReportMode(EMPTY, reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyCustomerSpecificAssetsAreDisplayed(EMPTY, reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyCustomerSpecificBoundariesAreDisplayed(EMPTY, reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyGeographicFilterIsSelected(EMPTY, reportDataRowID1));
	}

	@Test
	public void TC_ComplianceReports_VerifyMethodsForNewReports() throws Exception {
		Log.info("\nRunning TC_ComplianceReports_VerifyMethodsForNewReports ...");

		Integer userDataRowID = 6;
		Integer reportDataRowID1 = 49;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userDataRowID);

		complianceReportsPageAction.open(EMPTY, reportDataRowID1);
		createNewReport(complianceReportsPageAction, reportDataRowID1);
		waitForReportGenerationToComplete(complianceReportsPageAction, reportDataRowID1);
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, reportDataRowID1);
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, reportDataRowID1);
		complianceReportsPageAction.clickOnComplianceViewerCloseButton(EMPTY, reportDataRowID1);
		assertTrue(complianceReportsPageAction.verifyComplianceViewerDialogIsClosed(EMPTY, reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, reportDataRowID1));
	}

	/**
	 * This unit test is data dependent. Was tested by modifying lat/long coordinates on TC824.
	 * To run this unit test, generate report with following data and point ReportRowID to the title of generated report:
		 	listBoundary.add("37.4102286146667");
			listBoundary.add("-121.9813578675");
			listBoundary.add("37.399118039");
			listBoundary.add("-121.984789916667");
	 * @throws Exception
	 */
	@Test
	public void TC_ComplianceReports_EmptySSRSPDFVerifications() throws Exception {
		Log.info("\nRunning TC_ComplianceReports_EmptySSRSPDFVerifications ...");

		Integer userDataRowID = 6;
		Integer reportDataRowID1 = 49;   // Set a rowID that would generate EMPTY tables. If no such row then, create new report.

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userDataRowID);

		complianceReportsPageAction.open(EMPTY, reportDataRowID1);
		createNewReport(complianceReportsPageAction, reportDataRowID1);
		waitForReportGenerationToComplete(complianceReportsPageAction, reportDataRowID1);
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, reportDataRowID1);
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, reportDataRowID1);
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, reportDataRowID1);
		assertTrue(complianceReportsPageAction.verifyIndicationsTableIsEmpty(EMPTY, reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyIsotopicTableIsEmpty(EMPTY, reportDataRowID1));
	}

	/**
	 * This unit test simulates MinBoundary size error message by using the following lat/longs during report creation:
		 	listBoundary.add("37.4102286146667");
			listBoundary.add("-121.9813578675");
			listBoundary.add("37.4102296146667");
			listBoundary.add("-121.984789916667");
	 * @throws Exception
	 */
	@Test
	public void TC_ComplianceReports_VerifyErrorMessages() throws Exception {
		Log.info("\nRunning TC_ComplianceReports_VerifyErrorMessages ...");

		Integer userDataRowID = 6;
		Integer reportDataRowID1 = 74;   // Use a rowID that would generate error messages.

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userDataRowID);

		complianceReportsPageAction.open(EMPTY, reportDataRowID1);
		complianceReportsPageAction.createNewReport(EMPTY, reportDataRowID1);
		StringBuilder expectedErrorMessages = new StringBuilder();
		expectedErrorMessages.append(Resources.getResource(ResourceKeys.ComplianceReport_BoundaryMinSizeMessage));
		expectedErrorMessages.append("|");
		expectedErrorMessages.append(Resources.getResource(ResourceKeys.ComplianceReport_ValueMissingMessage));
		assertTrue(complianceReportsPageAction.verifyErrorMessages(expectedErrorMessages.toString(), reportDataRowID1));
	}

	/**
	 * Unit test to verify that the report generation has been cancelled successfully.
	 * @throws Exception
	 */
	@Test
	public void TC_ComplianceReports_VerifyReportCancelled() throws Exception {
		Log.info("\nRunning TC_ComplianceReports_VerifyReportCancelled ...");

		Integer userDataRowID = 6;
		Integer reportDataRowID1 = 49;   // Use a rowID that would generate error messages.

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userDataRowID);

		complianceReportsPageAction.open(EMPTY, reportDataRowID1);
		complianceReportsPageAction.createNewReport(EMPTY, reportDataRowID1);
		complianceReportsPageAction.verifyPageLoaded(EMPTY, reportDataRowID1);
		complianceReportsPageAction.cancelInProgressReport(EMPTY, reportDataRowID1);
		complianceReportsPageAction.verifyPageLoaded(EMPTY, reportDataRowID1);
		assertTrue(complianceReportsPageAction.verifyReportGenerationIsCancelled(EMPTY, reportDataRowID1));
		complianceReportsPageAction.deleteReport(EMPTY, reportDataRowID1);
	}

	/**
	 * Unit test to verify that the report SSRS footer is generated correctly.
	 * @throws Exception
	 */
	@Test
	public void TC_ComplianceReports_VerifySSRSPdfFooter() throws Exception {
		Log.info("\nRunning TC_ComplianceReports_VerifySSRSPdfFooter ...");

		Integer userDataRowID = 6;
		Integer reportDataRowID1 = 49;   // Use a rowID that would generate error messages.

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userDataRowID);

		complianceReportsPageAction.open(EMPTY, reportDataRowID1);
		createNewReport(complianceReportsPageAction, reportDataRowID1);
		waitForReportGenerationToComplete(complianceReportsPageAction, reportDataRowID1);
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, reportDataRowID1);
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, reportDataRowID1);
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, reportDataRowID1);
		assertTrue(complianceReportsPageAction.verifySSRSPDFFooter(EMPTY, reportDataRowID1));
	}

	/**
	 * Unit test to verify searched surveys results.
	 * NOTE: BEFORE running test: Make sure the reportRowID is using CustomerRowID=2
	 * @throws Exception
	 */
	@Test
	public void TC_ComplianceReports_VerifySearchedSurveys() throws Exception {
		Log.info("\nRunning TC_ComplianceReports_VerifySearchedSurveys ...");

		Integer userDataRowID = 6;
		Integer reportDataRowID1 = 49;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userDataRowID);

		complianceReportsPageAction.open(EMPTY, reportDataRowID1);
		assertTrue(complianceReportsPageAction.verifyPageLoaded(EMPTY, reportDataRowID1));

		complianceReportsPageAction.clickOnNewReportButton(EMPTY, reportDataRowID1);
		complianceReportsPageAction.selectReportMode(EMPTY, reportDataRowID1);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, reportDataRowID1);
		complianceReportsPageAction.selectCustomer(EMPTY, reportDataRowID1);
		complianceReportsPageAction.enterSurveySelectorTag("5", reportDataRowID1);
		complianceReportsPageAction.clickOnSurveySelectorSearchButton(EMPTY, reportDataRowID1);
		assertTrue(complianceReportsPageAction.verifySearchedSurveysMatchSelectedMode(EMPTY, reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifySearchedSurveysAreForSpecifiedCustomer(EMPTY, reportDataRowID1));
	}
}

package surveyor.regression.source;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.Log;
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
		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		manageCustomerPageAction = new ManageCustomerPageActions(driver, baseURL, testSetup);
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, baseURL, testSetup);
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
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1 ));
		assertTrue(complianceReportsPageAction.verifyPageLoaded(EMPTY, getReportRowID(reportDataRowID1 )));
		
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1 ));
		complianceReportsPageAction.selectReportMode(EMPTY, getReportRowID(reportDataRowID1 ));
		assertTrue(complianceReportsPageAction.verifySurveyModeFiltersByReportMode(EMPTY, getReportRowID(reportDataRowID1 )));
		assertTrue(complianceReportsPageAction.verifyCustomerSpecificAssetsAreDisplayed(EMPTY, getReportRowID(reportDataRowID1 )));
		assertTrue(complianceReportsPageAction.verifyCustomerSpecificBoundariesAreDisplayed(EMPTY, getReportRowID(reportDataRowID1 )));
		assertTrue(complianceReportsPageAction.verifyGeographicFilterIsSelected(EMPTY, getReportRowID(reportDataRowID1 )));
	}

	@Test
	public void TC_ComplianceReports_VerifyMethodsForNewReports() throws Exception {
		Log.info("\nRunning TC_ComplianceReports_VerifyMethodsForNewReports ...");

		Integer userDataRowID = 6;
		Integer reportDataRowID1 = 49;
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1 ));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		// TODO: Before verification perform action to Close the Compliance viewer dialog.
		assertTrue(complianceReportsPageAction.verifyComplianceViewerDialogIsClosed(EMPTY, getReportRowID(reportDataRowID1 )));
	}

	@Test
	public void TC_ComplianceReports_EmptySSRSPDFVerifications() throws Exception {
		Log.info("\nRunning TC_ComplianceReports_EmptySSRSPDFVerifications ...");

		Integer userDataRowID = 6;
		Integer reportDataRowID1 = 49;   // Set a rowID that would generate EMPTY tables. If no such row then, create new report. 
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1 ));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyIndicationsTableIsEmpty(EMPTY, getReportRowID(reportDataRowID1 )));
		assertTrue(complianceReportsPageAction.verifyIsotopicTableIsEmpty(EMPTY, getReportRowID(reportDataRowID1 )));
	}

	@Test
	public void TC_ComplianceReports_VerifyErrorMessages() throws Exception {
		Log.info("\nRunning TC_ComplianceReports_VerifyErrorMessages ...");

		Integer userDataRowID = 6;
		Integer reportDataRowID1 = 49;   // Use a rowID that would generate error messages. 
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1 ));
		complianceReportsPageAction.createNewReport(EMPTY, getReportRowID(reportDataRowID1));
		List<String> expectedErrorMessages = new ArrayList<String>();
		
		// TODO: Add expected error messages.
		expectedErrorMessages.add("TODO: Add expected error messages");
		assertTrue(complianceReportsPageAction.verifyErrorMessages(EMPTY, getReportRowID(reportDataRowID1 )));
	}
}

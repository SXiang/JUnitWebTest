package surveyor.unittest.source;

import common.source.Log;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.CR_EQLINES_MESSAGE;
import static surveyor.scommon.source.SurveyorConstants.CR_SURVEYMISSING_MESSAGE;
import static surveyor.scommon.source.SurveyorConstants.CR_VALUEMISSING_MESSAGE;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.dataprovider.AnalyticReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class AnalyticsReportsPageUnitTest extends BaseReportsPageActionTest {

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();
		initializePageActions();
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
	 * Test Case ID: UnitTest001_GenerateAnalyticReportPicarroAdminUser
	 * Test Description: - Generate Analytics Report as Picarro admin user
	 * Script: -
	 *	- - Log in as Picarro admin user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Select Analytics report and select a survey
	 *	- - Add View with no map: none
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: -
	 *	- - Report generated successfully
	 */
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST001, location = AnalyticReportDataProvider.class)
	public void UnitTest001_GenerateAnalyticReportPicarroAdminUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning UnitTest001_GenerateAnalyticReportPicarroAdminUser ..." +
			 "\nTest Description: UnitTest001_GenerateAnalyticReportPicarroAdminUser");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.getComplianceReportsPage().verifyErrorMessages(CR_SURVEYMISSING_MESSAGE));
		
		/*Update  survey data and enable following verifications */
//		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
//
//		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
//		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
//		assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}
	
	/**
	 * Test Case ID: UnitTest002_GenerateAnalyticReportPicarroAdminUser
	 * Test Description: - Generate Analytics Report as Picarro admin user
	 * Script: -
	 *	- - Log in as Picarro admin user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Select Analytics report and select a survey
	 *	- - Add 2 Views with base map value, satellite map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: -
	 *	- - Report generated successfully
	 */
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST002, location = AnalyticReportDataProvider.class)
	public void UnitTest002_GenerateAnalyticReportPicarroAdminUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning UnitTest002_GenerateAnalyticReportPicarroAdminUser ..." +
			 "\nTest Description: UnitTest002_GenerateAnalyticReportPicarroAdminUser");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.getComplianceReportsPage().verifyErrorMessages(CR_SURVEYMISSING_MESSAGE));
		
		/*Update  survey data and enable following verifications */
//		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
//
//		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
//		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
//		assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}
}

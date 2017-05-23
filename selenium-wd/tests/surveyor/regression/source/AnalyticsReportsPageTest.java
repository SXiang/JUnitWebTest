package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.SQAPICAD;

import java.util.Arrays;
import java.util.List;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import surveyor.dataprovider.AnalyticReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorTestRunner;

import com.tngtech.java.junit.dataprovider.UseDataProvider;
import common.source.Log;

@RunWith(SurveyorTestRunner.class)
public class AnalyticsReportsPageTest extends BaseReportsPageActionTest {

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
		new HomePageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}
	
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2373, location = AnalyticReportDataProvider.class)
	public void TC2373_LisaRankingInReportLisasAnalyticsCsvFile(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2373_LisaRankingInReportLisasAnalyticsCsvFile ..." +
				"\nTest Description: LISA ranking in ReportLISAS_analytics.csv file");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesArePresent("True:True:True:True", getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
	}
	
	/*
	 * * Test Case ID: TC2340_ReportModeOnComplianceReportListPage Test
	 * Description: Report Mode column on Compliance Reports (Report List) page
	 */
	@Test
	public void TC2340_ReportModeOnComplianceReportListPage() throws Exception {
		Log.info("\nTestcase - TC2340_ReportModeOnComplianceReportListPage\n");
		List<String> expectedReportHeader = Arrays.asList("Report Title",
				"Report Name", "Report Mode", "Created By", "Date", "Action",
				"Upload Status");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);
		complianceReportsPageAction.open(EMPTY, NOTSET);
		for (int count = 0; count < expectedReportHeader.size(); count++) {
			assertTrue(complianceReportsPageAction
					.getComplianceListPageHeader().get(count)
					.equals(expectedReportHeader.get(count)));
		}
	}

	/*
	 * * Test Case ID:
	 * TC2341_VerifyCorrectReportModesPresentOnComplianceReportListPage Test
	 * Description: Report Mode column on Compliance Reports (Report List) page
	 * shows correct modes
	 */
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2341, location = AnalyticReportDataProvider.class)
	public void TC2341_VerifyCorrectReportModesPresentOnComplianceReportListPage(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1,
			Integer reportDataRowID2, Integer reportDataRowID3,
			Integer reportDataRowID4) throws Exception {
		Log.info("\nTestcase - TC2341_VerifyCorrectReportModesPresentOnComplianceReportListPage\n");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID,
				getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction,
				getReportRowID(reportDataRowID1));

		String reportMode = complianceReportsPageAction
				.getReportModeForSpecifiedReportTitle(
						ComplianceReportsPageActions.workingDataRow.get().title,
						SQAPICAD);
		assertTrue(reportMode.equalsIgnoreCase("Analytics"));

		complianceReportsPageAction.open(testCaseID,
				getReportRowID(reportDataRowID2));
		createNewReport(complianceReportsPageAction,
				getReportRowID(reportDataRowID2));

		reportMode = complianceReportsPageAction
				.getReportModeForSpecifiedReportTitle(
						ComplianceReportsPageActions.workingDataRow.get().title,
						SQAPICAD);
		assertTrue(reportMode.equalsIgnoreCase("Standard"));

		complianceReportsPageAction.open(testCaseID,
				getReportRowID(reportDataRowID3));
		createNewReport(complianceReportsPageAction,
				getReportRowID(reportDataRowID3));

		reportMode = complianceReportsPageAction
				.getReportModeForSpecifiedReportTitle(
						ComplianceReportsPageActions.workingDataRow.get().title,
						SQAPICAD);
		assertTrue(reportMode.equalsIgnoreCase("Rapid Response"));

		complianceReportsPageAction.open(testCaseID,
				getReportRowID(reportDataRowID4));
		createNewReport(complianceReportsPageAction,
				getReportRowID(reportDataRowID4));

		reportMode = complianceReportsPageAction
				.getReportModeForSpecifiedReportTitle(
						ComplianceReportsPageActions.workingDataRow.get().title,
						SQAPICAD);
		assertTrue(reportMode.equalsIgnoreCase("Manual"));
	}
}

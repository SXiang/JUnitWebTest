/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import surveyor.dataprovider.FEQReportDataProvider;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.FacilityEQReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.FacilityEQReportsPage;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class FEQReportsPageTest extends BaseReportsPageActionTest {

	private static LoginPageActions loginPageAction;
	private static FacilityEQReportsPageActions feqReportsPageAction;
	private static FacilityEQReportsPage feqReportsPage;
	private static ManageLocationPageActions manageLocationPageAction;

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
			feqReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		manageLocationPageAction = ActionBuilder.createManageLocationPageAction();
		feqReportsPageAction = new FacilityEQReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		feqReportsPage = (FacilityEQReportsPage)feqReportsPageAction.getPageObject();
		setReportsPage(feqReportsPage);
	}

	/**
	 * Test Case ID: TC2408_FacilityEQReportsWithSurveyWithNoPeaks
	 * Test Description: Generate Facility EQ report with the survey with no peaks
	 * Script:
	 * - Customer should have FEQ license
	 * - Customer should have FEQ survey with no peaks.
	 * - login to pcube
	 * - Generate Facility EQ report including above mentioned survey (survey with no peaks)
	 * Results:
	 * - Report should generate successfully.
	 */
	@Test
	@UseDataProvider(value = FEQReportDataProvider.FEQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2408, location = FEQReportDataProvider.class)
	public void TC2408_FacilityEQReportsWithSurveyWithNoPeaks(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2408_FacilityEQReportsWithSurveyWithNoPeaks ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		feqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		feqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(feqReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(feqReportsPageAction, getReportRowID(reportDataRowID1));
		feqReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		feqReportsPageAction.clickOnViewerConcentrationChartZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(feqReportsPageAction.waitForConcentrationChartZIPFileDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC2410_GenerateFacilityEQReportWithLocationParameterMinClusterSize1AndDBScanUncheck
	 * Test Description: Generate EQ report with the location parameter which has Min cluster size 1 and DBScan uncheck
	 * Script:
	 * - Customer should have FEQ license
	 * - Customer should have FEQ survey with peaks.
	 * -  Log in to pcubed
	 * - Go to Manage Locations and create new location for that customer where
	 * - Min cluster size = 1 and DBScan uncheck
	 * - Generate FEQ report using above created location parameter and include survey which has indications.
	 * - Reprocess the same report for (min-2 and max-6 times - depending on how many Linux nodes are present in the environment)
	 * 	 * Results:
	 * -Report should fail, but EQWorker should still be alive and user should be able to generate other FEQ reports.
	 */
	@Test
	@UseDataProvider(value = FEQReportDataProvider.FEQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2410, location = FEQReportDataProvider.class)
	public void TC2410_GenerateFacilityEQReportWithLocationParameterMinClusterSize1AndDBScanUncheck(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2410_GenerateFacilityEQReportWithLocationParameterMinClusterSize1AndDBScanUncheck ...");


		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 30 /*locationRowID*/);

		feqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));

		feqReportsPage.openNewReportPage();
		feqReportsPageAction.fillAndCreateNewReport(getReportRowID(reportDataRowID1),false);
		feqReportsPageAction.waitForReportGenerationToCompleteWithErrorChecks(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(feqReportsPageAction.verifyReportGenerationIsCancelled(EMPTY, getReportRowID(reportDataRowID1)));

		for (int i=0; i <3; i++)
		{
			int y = i++;
			Log.info("Resubmiting report for " + y + " times ...");
			feqReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
			feqReportsPageAction.waitForReportGenerationToCompleteWithErrorChecks(EMPTY, getReportRowID(reportDataRowID1));
			assertTrue(feqReportsPageAction.verifyReportGenerationIsCancelled(EMPTY, getReportRowID(reportDataRowID1)));
		}

		feqReportsPageAction.copyReport(FacilityEQReportsPageActions.workingDataRow.get().title, getReportRowID(reportDataRowID1));
		modifyReport(feqReportsPageAction, getReportRowID(reportDataRowID2));
		waitForReportGenerationToComplete(feqReportsPageAction, getReportRowID(reportDataRowID2));
		feqReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID2));
		feqReportsPageAction.clickOnViewerConcentrationChartZIP(EMPTY, getReportRowID(reportDataRowID2));
		assertTrue(feqReportsPageAction.waitForConcentrationChartZIPFileDownloadToComplete(EMPTY, getReportRowID(reportDataRowID2)));
	}
}
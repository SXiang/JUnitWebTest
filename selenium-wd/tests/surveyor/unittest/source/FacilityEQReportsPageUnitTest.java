package surveyor.unittest.source;

import common.source.Log;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.dataprovider.FacilityEQReportDataProvider;
import surveyor.scommon.actions.FacilityEQReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.FacilityEQReportsPage;

@RunWith(SurveyorTestRunner.class)
public class FacilityEQReportsPageUnitTest extends BaseReportsPageActionTest {
		private static LoginPageActions loginPageAction;
		private static FacilityEQReportsPageActions feqReportsPageAction;
		private static FacilityEQReportsPage feqReportsPage;

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
			feqReportsPageAction = new FacilityEQReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
			feqReportsPage = (FacilityEQReportsPage)feqReportsPageAction.getPageObject();
			setReportsPage(feqReportsPage);
		}

		@Test
		@UseDataProvider(value = FacilityEQReportDataProvider.FEQ_REPORT_PAGE_ACTION_DATA_PROVIDER_FEQUnitTest1, location = FacilityEQReportDataProvider.class)
		public void facilityEQUnitTest1_FEQReport(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning FEQUnitTest1_FEQReport ...");

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
			feqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(feqReportsPageAction, getReportRowID(reportDataRowID1));
			waitForReportGenerationToComplete(feqReportsPageAction, getReportRowID(reportDataRowID1));
			feqReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			feqReportsPageAction.clickOnViewerConcentrationChartZIP(EMPTY, getReportRowID(reportDataRowID1));
			assertTrue(feqReportsPageAction.waitForConcentrationChartZIPFileDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
			feqReportsPageAction.clickOnViewerEmissionDataZIP(EMPTY, getReportRowID(reportDataRowID1));
			assertTrue(feqReportsPageAction.waitForEmissionDataZIPFileDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
			feqReportsPageAction.clickOnReportViewerView(EMPTY, getReportRowID(reportDataRowID1));
			assertTrue(feqReportsPageAction.waitForViewDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
			assertTrue(feqReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
			feqReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID1));
		}
}

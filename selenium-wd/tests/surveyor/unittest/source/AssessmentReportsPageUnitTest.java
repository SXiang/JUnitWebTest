package surveyor.unittest.source;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.junit.Test;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.AssessmentReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.AssessmentReportsPage;

@RunWith(SurveyorTestRunner.class)
public class AssessmentReportsPageUnitTest  extends BaseReportsPageActionTest {

	private static AssessmentReportsPageActions assessmentReportsPageAction;
	private static AssessmentReportsPage assessmentReportsPage;

	private static LoginPageActions loginPageAction;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializeTestObjects();

		assessmentReportsPage = new AssessmentReportsPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  assessmentReportsPage);
		initializePageActions();
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		assessmentReportsPageAction = new AssessmentReportsPageActions(getDriver(), getBaseURL(), getTestSetup());

		// Select run mode here.
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			assessmentReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	@Test
	public void testCleanupReport() throws Exception {
		final Integer userDataRowID = 2;
		final Integer reportDataRowID1 = 4;

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));

		assessmentReportsPageAction.deleteReport(EMPTY, getReportRowID(reportDataRowID1));
	}

	@Test
	public void verifyLISASMetaDataFile() throws Exception {
		// NOTE: Update report title and place meta data files in CSV file path for a valid report in DB when executing this test.
		final String SAMPLE_REPORT_TITLE = "TC1488-0-9d9902621a134047b5ed";
		final String CSV_FILE_PATH = "C:\\temp\\AS-1A3A7F-Meta";
		assertTrue(assessmentReportsPage.verifyLISASMetaDataFile(CSV_FILE_PATH, SAMPLE_REPORT_TITLE));
	}

	@Test
	public void verifyNumberOfLisasInShapeFiles() throws Exception {
		// NOTE: Update report title and place shape files in SHP file path for a valid report in DB when executing this test.
		final String SAMPLE_REPORT_TITLE = "TC1488-1-28be5bc9144846e48335";
		final String SHP_FILE_PATH = "C:\\temp\\AS-2E7EE6-Shape";
		final Integer EXPECTED_LISA_COUNT = 1;
		assertTrue(assessmentReportsPage.verifyNumberOfLisasInShapeFiles(SHP_FILE_PATH, SAMPLE_REPORT_TITLE, 0, EXPECTED_LISA_COUNT));
	}
}
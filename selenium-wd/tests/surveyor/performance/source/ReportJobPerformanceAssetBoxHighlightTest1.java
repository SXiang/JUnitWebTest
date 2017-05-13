package surveyor.performance.source;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import surveyor.dataprovider.PerformanceReportJobDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.ComplianceReportsPage;

public class ReportJobPerformanceAssetBoxHighlightTest1 extends BaseReportJobPerformanceReportGenTest {

	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;

	private static ComplianceReportsPage complianceReportsPage;

	@BeforeClass
	public static void beforeTestClassBaseReportJobPerfTest() throws Exception {
		initializeTestObjects();
	}

	@Before
	public void beforeTestMethodBaseReportJobPerfTest() throws Exception {
		initializeTestObjects();
		initializePageActions();

		initializeProperties();
	}

	/**
	 * Initializes the page action objects.
	 */
	protected void initializePageActions() {
		homePageAction = new HomePageActions(getDriver(), getBaseURL(), getTestSetup());
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		testEnvironmentAction = new TestEnvironmentActions();

		// initialize page object for post test processing.
		initializePageObjects(complianceReportsPageAction.getComplianceReportsPage());
	}

	@Override
	protected HomePageActions getHomePageAction() {
		return homePageAction;
	}

	@Override
	protected LoginPageActions getLoginPageAction() {
		return loginPageAction;
	}

	@Override
	protected ComplianceReportsPageActions getComplianceReportsPageAction() {
		return complianceReportsPageAction;
	}

	@Override
	protected TestEnvironmentActions getTestEnvironmentAction() {
		return testEnvironmentAction;
	}

	@Override
	protected ComplianceReportsPage getComplianceReportsPage() {
		return complianceReportsPage;
	}

	@Override
	protected void setComplianceReportsPage(ComplianceReportsPage cmpReportsPage) {
		complianceReportsPage = cmpReportsPage;
	}

	/**
	 * Test Case ID: ReportJob_PerformanceAssetBoxHighlightTest
	 * Script: -
	 *	- - Login as specified user
	 *	- - Create New Compliance Report with specified report data to trigger AssetboxHighlight job
	 *  - - Wait for Report to be generated
	 *  - - Post report job statistics to automation reporting DB.
	 */
	@Test
	@UseDataProvider(value = PerformanceReportJobDataProvider.REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX1, location = PerformanceReportJobDataProvider.class)
	public void ReportJob_PerformanceAssetBoxHighlightTest(String rallyTestCaseID, Integer userDataRowID, Integer reportDataRowID,
			Integer executionTimesForBaselines, String category) throws Exception {
		Log.info(String.format("\nRunning [%s] Performance Test ASSETBOX_HIGHLIGHT_JOB ...", rallyTestCaseID));

		executeReportGenerationTest(userDataRowID, reportDataRowID);
	}
}

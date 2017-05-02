package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataprovider.AnalyticReportDataProvider;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorTestRunner;

import com.tngtech.java.junit.dataprovider.UseDataProvider;
import common.source.Log;

@RunWith(SurveyorTestRunner.class)
public class AnalyticsReportsPageTest extends BaseReportsPageActionTest {

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static LoginPage loginPage;
	private static HomePage homePage;
	private static ComplianceReportsPage complianceReportsPage;
	
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
		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		new HomePageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
		loginPage = pageObjectFactory.getLoginPage();
		setLoginPage(loginPage);
		PageFactory.initElements(getDriver(), loginPage);
		HomePage homePage = pageObjectFactory.getHomePage();
		setHomePage(homePage);
		PageFactory.initElements(getDriver(), homePage);
		complianceReportsPage = pageObjectFactory.getComplianceReportsPage();
		PageFactory.initElements(getDriver(), complianceReportsPage);
	}

	/**
	 * Test Case ID: TC2338_LisasBelowThresholdValueAreFilteredOut
	 * Test Description: - Analytics Report - LISAs below threshold value are filtered out
	 * 			When an Analytics Report is run, LISAs below the threshold value indicated on the Location page should be filtered out.
	 * 			The SSRS, View PDFs, CSVs and Shapefiles should reflect this.
	 */

	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2338, location = AnalyticReportDataProvider.class)
	public void TC2338_LisasBelowThresholdValueAreFilteredOut(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2338_LisasBelowThresholdValueAreFilteredOut ..." +
			 "\nTest Description: Analytics Report - LISAs below threshold value are filtered out");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

        complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));

		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));


        assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
/* Shape file base line will be generated/enabled after dev completion */
//        assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
	
	
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC624, location = ComplianceReportDataProvider.class)
	public void TC624_ComplianceReportRedTraceIsotopicCaptureAnalysis(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC624_ComplianceReportRedTraceIsotopicCaptureAnalysis ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
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

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);
		complianceReportsPage.open();
		for (int count = 0; count < expectedReportHeader.size(); count++) {
			assertTrue(complianceReportsPage.getComplianceListPageHeader()
					.get(count).equals(expectedReportHeader.get(count)));
		}
	}
	
	/*
	 * * Test Case ID: TC2341_VerifyCorrectReportModesPresentOnComplianceReportListPage
	 * Test Description: Report Mode column on Compliance Reports (Report List) page
	 * shows correct modes
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2341, location = ComplianceReportDataProvider.class)
	public void TC2341_VerifyCorrectReportModesPresentOnComplianceReportListPage(String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2, Integer reportDataRowID3, Integer reportDataRowID4) throws Exception {
		Log.info("\nTestcase - TC2341_VerifyCorrectReportModesPresentOnComplianceReportListPage\n");

		String reportTitle = "";
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		complianceReportsPage.open();

		String reportMode = complianceReportsPage.getReportModeForProvidedReportTitle(reportTitle, PICDFADMIN);
	}
}

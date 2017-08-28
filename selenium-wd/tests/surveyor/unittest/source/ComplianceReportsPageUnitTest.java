package surveyor.unittest.source;

import common.source.FileUtility;
import common.source.Log;
import common.source.LogHelper;
import common.source.PDFUtility;
import common.source.TestContext;
import common.source.PDFTableUtility.PDFTable;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import androidapp.data.source.InvestigationReportDataVerifier;

import org.junit.Test;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;
import surveyor.parsers.source.SSRSIsotopicAnalysisTableParser;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.actions.data.ComplianceReportDataReader;
import surveyor.scommon.actions.data.UserDataReader;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorConstants;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageUnitTest  extends BaseReportsPageActionTest {

	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static ManageLocationPageActions manageLocationPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;
	private static ComplianceReportsPage complianceReportsPage;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializeTestObjects();

		complianceReportsPage = new ComplianceReportsPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  complianceReportsPage);
		initializePageActions();
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		homePageAction = new HomePageActions(getDriver(), getBaseURL(), getTestSetup());
		manageCustomerPageAction = new ManageCustomerPageActions(getDriver(), getBaseURL(), getTestSetup());
		manageUsersPageAction = new ManageUsersPageActions(getDriver(), getBaseURL(), getTestSetup());
		manageLocationPageAction = new ManageLocationPageActions(getDriver(), getBaseURL(), getTestSetup());
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		testEnvironmentAction = new TestEnvironmentActions();

		// Select run mode here.
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	@Test
	public void getLISAInvestigationPDFData_VerifyUsingReportsPresentInDB() throws Exception {
		final String[] tcIds = new String[] {"TC2448", "TC2440"};
		final String username = SurveyorConstants.SQAPICDR;
		for (String tcId : tcIds) {
			Report report = InvestigationReportDataVerifier.newVerifier().findReportOfMatchingPrefixWithLeakFoundOrInProgressLisaMarker(new String[] {tcId}, username);
			if (report != null) {
				String reportTitle = report.getReportTitle();
				downloadInvestigationPDFIfNotPresent(reportTitle);
				Long numMarkers = getNumberOfLISAMarkersInReport(username, report);
				IntStream.rangeClosed(1, numMarkers.intValue()).forEach(i -> {
					Integer lisaNumber = i;
					List<String> investigationPDFData = null;
					try {
						investigationPDFData = complianceReportsPage.getLISAInvestigationPDFData(lisaNumber , reportTitle );
						assertTrue(investigationPDFData != null && investigationPDFData.size()>0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
		}
	}

	@Test
	public void verifyAnalyticsPeakInfoIsCorrectInDB() throws Exception {
        loginPageAction.open(EMPTY, 6);
        loginPageAction.login("9c06ff17ed@email.com:sqa#Picarro$0", NOTSET);
		complianceReportsPageAction.open(EMPTY, 221);
		complianceReportsPageAction.verifyAnalyticsPeakInfoIsCorrectInDB(EMPTY, 221);
	}

	@Test
	public void verifyGetSSRSPDFTableValues() throws IOException {
		final String SAMPLE_REPORT_TITLE = "TC202 Report374876";
		List<String[]> isotopicAnalysisTblList = complianceReportsPage.getSSRSPDFTableValues(
				PDFTable.ISOTOPICANALYSISTABLE, SAMPLE_REPORT_TITLE);
		Log.info(String.format("ReportIsotopic ArrayList Values : %s",
				LogHelper.listOfArrayToString(isotopicAnalysisTblList)));
	}

	@Test
	public void verifySSRSIsotopicAnalysisTable() throws IOException {
		final String SAMPLE_REPORT_TITLE = "TC202 Report374876";
		final String CSV_FILE_PATH = "C:\\temp\\TC202";
		assertTrue(complianceReportsPage.verifyIsotopicAnalysisTable(CSV_FILE_PATH, SAMPLE_REPORT_TITLE));
	}

	@Test
	public void verifyLISASMetaDataFile() throws FileNotFoundException, IOException {
		final String SAMPLE_REPORT_TITLE = "TC1389-1a5eb25d4cec40c89c8e";
		final String CSV_FILE_PATH = "C:\\temp\\CR-0F737A-Meta";
		assertTrue(complianceReportsPage.verifyLISASMetaDataFile(CSV_FILE_PATH, SAMPLE_REPORT_TITLE));
	}

	@Test
	public void verifySSRSIsotopicAnalysisTableParser() throws Exception {
		String SAMPLE_REPORT_PDF_FILE = "C:\\Users\\spulikkal\\Downloads\\CR-E5ADE3.pdf";
		PDFUtility pdfUtility = new PDFUtility();
		String actualReportString = pdfUtility.extractPDFText(SAMPLE_REPORT_PDF_FILE);
		SSRSIsotopicAnalysisTableParser tableParser = new SSRSIsotopicAnalysisTableParser();
		List<String[]> parseAsTable = tableParser.parseAsTable(actualReportString, "`");
		Log.info(String.format("ReportIsotopic ArrayList Values : %s",
				LogHelper.listOfArrayToString(parseAsTable)));
	}

	@Test
	public void US2774_EnableBaselineShapeFilesForComplianceReports() throws Exception {
        Log.info("\nUS2774_EnableBaselineShapeFilesForComplianceReports");
        complianceReportsPageAction.workingDataRow.set(new ComplianceReportDataReader(null).new ComplianceReportsDataRow(null,null,
        		null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
        		null,null));
        complianceReportsPageAction.workingDataRow.get().title = "TC148 Report639729";
        complianceReportsPageAction.workingDataRow.get().tCID = "UnitTest-US2774";

        complianceReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.performSearch(complianceReportsPageAction.workingDataRow.get().title);
		LoginPageActions.workingDataRow.set(new UserDataReader(null).new UserDataRow(null,null,null,null,null,null,null,null,null,null,null,null));
		LoginPageActions.workingDataRow.get().username = "sqapicsup@picarro.com";

		//Delete all the download zips before test or change the parameter "0" to the download index of the this zip
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, 0);
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, 0);
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete("0", 0);

		for(int i=0;i<10;i++){ // Testing IO exceptions
		   Assert.assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines("0", 0));
		}
	}

	/**
	 * TA862 - Searched Surveys should be filtered by selected report mode
	 * @throws Exception
	 */
	@Test
	public void TA862_ComplianceReportTest_VerifySurveyFilters() throws Exception {

		Log.info("\nTA862 - Searched Surveys should be filtered by selected report mode");

		complianceReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.openNewReportPage();

		ReportModeFilter rmode = ReportModeFilter.Standard;
		testReportFilters(rmode);
		SurveyModeFilter smode = SurveyModeFilter.All;
		testSurveyFilters(smode);

		smode = SurveyModeFilter.Standard;
		testSurveyFilters(smode);

		smode = SurveyModeFilter.Operator;
		testSurveyFilters(smode);

		rmode = ReportModeFilter.RapidResponse;
		testReportFilters(rmode);
		smode = SurveyModeFilter.RapidResponse;
		testSurveyFilters(smode);

		rmode = ReportModeFilter.Manual;
		testReportFilters(rmode);
		smode = SurveyModeFilter.Manual;
		testSurveyFilters(smode);
	}

	private void downloadInvestigationPDFIfNotPresent(String reportTitle) throws Exception {
		String invPdfFullPath = Paths.get(TestContext.INSTANCE.getTestSetup().getDownloadPath(),
				complianceReportsPage.getInvestigationPDFFileName(reportTitle, true /*includeExtension*/)).toString();
		if (!FileUtility.fileExists(invPdfFullPath)) {
			complianceReportsPage.invokeInvestigationPDFFileDownload(reportTitle);
		}
	}

	private Long getNumberOfLISAMarkersInReport(final String username, Report report) {
		Long numMarkers = 0L;
		String reportId = report.getId();
		List<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication.getLisaInvestigation(reportId);
		if (lisaInvestigationfromSP != null && lisaInvestigationfromSP.size()>0) {
			numMarkers = lisaInvestigationfromSP.stream()
							.filter(r -> r.getAssignedUserName().equals(username))
							.count();
		}
		return numMarkers;
	}

	private void testReportFilters(ReportModeFilter rmode){
		complianceReportsPage.selectReportMode(rmode);
		Assert.assertTrue(complianceReportsPage.verifySurveyModeFilters(rmode));
	}

	private void testSurveyFilters(SurveyModeFilter smode){
		complianceReportsPage.selectSurveyModeForSurvey(smode);
		complianceReportsPage.clickOnSearchSurveyButton();
		Assert.assertTrue(complianceReportsPage.verifySurveySelectorWithFilter(smode));
	}
}

package surveyor.unittest.source;

import common.source.FileUtility;
import common.source.Log;
import common.source.LogHelper;
import common.source.PDFUtility;
import common.source.TestContext;
import common.source.PDFTableUtility.PDFTable;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
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
	public void testCleanupReport() throws Exception {
		final Integer userDataRowID = 5;
		final Integer reportDataRowID1 = 50;

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.deleteReport(EMPTY, getReportRowID(reportDataRowID1));
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
	public void getLISAInvestigationPDFData_VerifyMultiPageData() {
		String pdfFile = System.getProperty("user.dir") +
				"\\selenium-wd\\data\\test-data\\pdfutility-tests\\" +
				"CR-05F113-Investigation.pdf";
		String reportTitle = "TC2448-ab2e731651ee47f694e4";
		String reportId = "05F113";
		int[] lisas = {1,2,3,4,5,6,7,8,9};
		
		List<List<String>> listOfLisa = new ArrayList<>();
		
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		List<String> list3 = new ArrayList<>();
		List<String> list4 = new ArrayList<>();
		List<String> list5 = new ArrayList<>();
		List<String> list6 = new ArrayList<>();
		List<String> list7 = new ArrayList<>();
		List<String> list8 = new ArrayList<>();
		List<String> list9 = new ArrayList<>();
		
		list1.add("1 Found Gas Leak 10/17/2017 8:03 AM PDT sqapicdr@picarro.com 0 days 00:03:53");
		list1.add("Source: Gas Date/Time: 10/17/2017 8:07 AM PDT");
		list1.add("Investigator: sqapicdr@picarro.comLatitude: 37.396807, Longitude: -121.98419, Precison: m");
		list1.add("Leak Grade: 548Address: Crescent Bonaparte, 887, Cuthbert, OR");
		list1.add("Map Number: 341Surface Reading: 6 (PPM)");
		list1.add("Barhole Reading: 306 (PPM)");
		list1.add("Paved Wall-To-Wall: True");
		list1.add("Pipe Material Type: Cast IronLeak Location: Service");
		list1.add("Surface Over Leak: Concrete");
		list1.add("Meter Number: 583");
		list1.add("Leak Location Remarks: rlpcjxfarpbqzqaphgvjjfhzcpozsgfctjnddmhmmncoezhxpqxqdgqgqryysgqsnmryxzlwwqlbkvrksdtrftrgqxnozochtxmzna"+
                              "srhbuxumjzbaqtqujnefvhaeafvcyqcodilpwbnloyzeizcbhekebsdpilfhhrtaxffvnzahnynhnkcwhyvfahrsmywdbcqzsafifwjeielpeyxhbufisfheefybldypnmtpvebnwngmjvjled");
		list1.add("Additional Notes: ywwbqkvdyjuqzulxdstqukmpctrpxupgpldnobttngdxuvpvjyadoapvszdanspsyoyxefumgbafxdopspbxhocefvsohyutvggo"+
				"ccemreeqdlowwyntgztkbmippjnvqcydkzmwkydgpzdwrxwapgxwnkzeqgmopsfsipqacnkmpvxnkyvsrobppmolfefqbmupgsuwyqefoaiuxjnhxezxnwznyqjjoszmdeuhxomlvoptwmmntjfu");
		
		list3.add("3 Found Gas Leak 10/18/2017 7:35 AM PDT sqapicdr@picarro.com 0 days 00:04:23");
		list3.add("Source: Gas Date/Time: 10/18/2017 7:39 AM PDT");
		list3.add("Investigator: sqapicdr@picarro.comLatitude: 37.396745, Longitude: -121.984223, Precison: m");
		list3.add("Leak Grade: 564Address: Crescent Archer, 996, Rome, AZ");
		list3.add("Map Number: 601Surface Reading: 216 (PPM)");
		list3.add("Barhole Reading: 112 (PPM)");
		list3.add("Paved Wall-To-Wall: True");
		list3.add("Pipe Material Type: Cast IronLeak Location: Service");
		list3.add("Surface Over Leak: Concrete");
		list3.add("Meter Number: 297");
		list3.add("Leak Location Remarks: aphgvjjfhzcpozsgfctjnddmhmmncoezhxpqxqdgqgqryysgqsnmryxzlwwqlbkvrksdtrftrgqxnozochtxmznasrhbuxumjzba"+
				"qtqujnefvhaeafvcyqcodilpwbnloyzeizcbhekebsdpilfhhrtaxffvnzahnynhnkcwhyvfahrsmywdbcqzsafifwjeielpeyxhbufisfheefybldypnmtpvebnwngmjvjledwpywwbqkvdyjuq");
		list3.add("Additional Notes: lxdstqukmpctrpxupgpldnobttngdxuvpvjyadoapvszdanspsyoyxefumgbafxdopspbxhocefvsohyutvggoccemreeqdloww"+
				"yntgztkbmippjnvqcydkzmwkydgpzdwrxwapgxwnkzeqgmopsfsipqacnkmpvxnkyvsrobppmolfefqbmupgsuwyqefoaiuxjnhxezxnwznyqjjoszmdeuhxomlvoptwmmntjfuegdxvtewosrmpeg");

		list4.add("4 In Progress 10/18/2017 7:09 PM PDT sqapicdr@picarro.com");
		list4.add("Source: Gas Date/Time: 10/18/2017 7:12 PM PDT");
		list4.add("");
		list4.add("");
		list4.add("");
		list4.add("");
		list4.add("");
		list4.add("");
		list4.add("");
		list4.add("");
		list4.add("Leak Location Remarks: jaiohnjurnrlpcjxfarpbqzqaphgvjjfhzcpozsgfctjnddmhmmncoezhxpqxqdgqgqryysgqsnmryxzlwwqlbkvrksdtrftrgqxnozo"+
				"chtxmznasrhbuxumjzbaqtqujnefvhaeafvcyqcodilpwbnloyzeizcbhekebsdpilfhhrtaxffvnzahnynhnkcwhyvfahrsmywdbcqzsafifwjeielpeyxhbufisfheefybldypnmtpvebnwn");
		list4.add("Additional Notes: vjledwpywwbqkvdyjuqzulxdstqukmpctrpxupgpldnobttngdxuvpvjyadoapvszdanspsyoyxefumgbafxdopspbxhocefvsoh"+
				"yutvggoccemreeqdlowwyntgztkbmippjnvqcydkzmwkydgpzdwrxwapgxwnkzeqgmopsfsipqacnkmpvxnkyvsrobppmolfefqbmupgsuwyqefoaiuxjnhxezxnwznyqjjoszmdeuhxomlvoptwmm");
		
		list5.add("5 Found Gas Leak 10/18/2017 10:48 PM PDT sqapicdr@picarro.com 0 days 00:02:45");
		list5.add("Source: Gas Date/Time: 10/18/2017 10:50 PM PDT");
		list5.add("");
		list5.add("");
		list5.add("");
		list5.add("");
		list5.add("");
		list5.add("");
		list5.add("");
		list5.add("");
		list5.add("Leak Location Remarks: pcjxfarpbqzqaphgvjjfhzcpozsgfctjnddmhmmncoezhxpqxqdgqgqryysgqsnmryxzlwwqlbkvrksdtrftrgqxnozochtxmznasr"+
		"hbuxumjzbaqtqujnefvhaeafvcyqcodilpwbnloyzeizcbhekebsdpilfhhrtaxffvnzahnynhnkcwhyvfahrsmywdbcqzsafifwjeielpeyxhbufisfheefybldypnmtpvebnwngmjvjledwpy");
		list5.add("Additional Notes: bqkvdyjuqzulxdstqukmpctrpxupgpldnobttngdxuvpvjyadoapvszdanspsyoyxefumgbafxdopspbxhocefvsohyutvggocce"+
				"mreeqdlowwyntgztkbmippjnvqcydkzmwkydgpzdwrxwapgxwnkzeqgmopsfsipqacnkmpvxnkyvsrobppmolfefqbmupgsuwyqefoaiuxjnhxezxnwznyqjjoszmdeuhxomlvoptwmmntjfuegd");
		
		listOfLisa.add(list1);
		listOfLisa.add(list2);
		listOfLisa.add(list3);
		listOfLisa.add(list4);
		listOfLisa.add(list5);
		listOfLisa.add(list6);
		listOfLisa.add(list7);
		listOfLisa.add(list8);
		listOfLisa.add(list9);

		for(int lisaNumber:lisas){
			try {
				List<String> lisa = complianceReportsPage.getLISAInvestigationPDFData(lisaNumber, reportTitle,pdfFile,reportId);
				List<String> expectedLisa = listOfLisa.get(lisaNumber-1);
				if(expectedLisa.isEmpty()){
					assertTrue(lisa!=null&&!lisa.isEmpty());
				}else{
				for(int i=0;i<listOfLisa.get(lisaNumber-1).size();i++){
					String expect = expectedLisa.get(i).toLowerCase();
					String actual = lisa.get(i).toLowerCase();
					
					if(expect.isEmpty()){
						assertTrue(actual!=null&&!actual.isEmpty());
					}else if(!actual.equals(expect)){
						Log.error("Leak detail in PDF table: "+actual);
						Log.error("Leak detail expected: "+expect);
						fail();
					}
				}
			}
			} catch (Exception e) {
				Log.error(e.toString());
				fail(e.toString());
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

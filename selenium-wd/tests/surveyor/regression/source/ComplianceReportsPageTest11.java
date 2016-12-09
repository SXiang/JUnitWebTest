/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSMNTAG;

import static surveyor.scommon.source.ReportsCompliance.EthaneFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.MeasurementSessionsPage.DrivingSurveyButtonType;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest11 extends BaseReportsPageTest {
	private static ComplianceReportsPage complianceReportsPage = null;
	private static Map<String, String> testCaseMap = Collections.synchronizedMap(new HashMap<String, String>());

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
		createTestCaseMap();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();
		initializePageObjects();
	}

	private static void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		complianceReportsPage = pageObjectFactory.getComplianceReportsPage();
		PageFactory.initElements(getDriver(), complianceReportsPage);
	}

	/**
	 * Test Case ID: TC192, TC202, TC210
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws Exception
	 *
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PROVIDER_SET11, location = ComplianceReportDataProvider.class)
	public void ComplianceReportTest_VerifyNonEthaneReport(String index, String strCreatedBy, String password, String cutomer, String timeZone, String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate, String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) throws Exception {
		String rptTitle = null;
		String testCaseName = getTestCaseName(index);

		Log.info("\nRunning " + testCaseName + " - " + rptTitle);

		rptTitle = testCaseName + " " + "Report" + getTestSetup().getRandomNumber();

		complianceReportsPage.login(strCreatedBy, new CryptoUtility().decrypt(password));
		complianceReportsPage.open();

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, strCreatedBy, cutomer, timeZone, exclusionRadius, surveyorUnit, userName, startDate, endDate, fovOpacity, lisaOpacity, geoFilter, reportMode, surveyModeFilter, ethaneFilter, listBoundary, tagList, tablesList, viewList, viewLayersList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, strCreatedBy, testCaseName))) {
			complianceReportsPage.clickOnReportViewerCloseButton();
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, getTestSetup().getDownloadPath()));
			assertTrue(complianceReportsPage.verifyComplianceReportStaticText(rpt));
			assertTrue(complianceReportsPage.verifySSRSImages(getTestSetup().getDownloadPath(), rptTitle, testCaseName));
			if (!testCaseName.equals("TC192")) {
				if (tablesList != null) {
					if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
						assertTrue(complianceReportsPage.verifyShowCoverageTable(getTestSetup().getDownloadPath(), rptTitle));
						assertTrue(complianceReportsPage.verifyCoverageValuesTable(getTestSetup().getDownloadPath(), rptTitle, tablesList.get(0)));
					}
					if (cutomer.equalsIgnoreCase("Picarro")) {
						assertTrue(complianceReportsPage.verifyLayersTable(getTestSetup().getDownloadPath(), rptTitle, tablesList.get(0)));
					}
					assertTrue(complianceReportsPage.verifyViewsTable(getTestSetup().getDownloadPath(), rptTitle, viewList));
					assertTrue(complianceReportsPage.verifyDrivingSurveysTable(getTestSetup().getDownloadPath(), rptTitle));
					if (tablesList.get(0).get(KEYISOANA).equals("1")) {
						assertTrue(complianceReportsPage.verifyIsotopicAnalysisTable(getTestSetup().getDownloadPath(), rptTitle));
					}
					if (tablesList.get(0).get(KEYINDTB).equals("1")) {
						assertTrue(complianceReportsPage.verifyIndicationTable(getTestSetup().getDownloadPath(), rptTitle));
					}
				}
			}
			assertTrue(complianceReportsPage.verifyAllViewsImages(getTestSetup().getDownloadPath(), rptTitle, testCaseName, viewList.size()));

		} else
			fail("\nTestcase " + getTestCaseName(index) + " failed.\n");

		if(testCaseName.equals("TC210")){
			isSurveyDeleted(tagList);
		}
	}

	private void isSurveyDeleted(List<String> tagList) throws Exception{
		String tagName=tagList.get(0);
		MeasurementSessionsPage msp = new MeasurementSessionsPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), msp);
		msp.open();
		assertTrue(msp.actionOnDrivingSurvey(tagName, null, null, null, DrivingSurveyButtonType.DeleteSurvey));
		msp.open();
		tagList = msp.getTagNameList();
		assertTrue(tagList.contains(tagName));
	}


	/**
	 * Test Case ID: TC217, TC223,TC225,TC226
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws Exception
	 *
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PROVIDER_SET11_INVESTIGATION, location = ComplianceReportDataProvider.class)
	public void ComplianceReportTest_VerifyInvestigationReport(String index, String strCreatedBy, String password, String cutomer, String timeZone, String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate, String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) throws Exception {
		String rptTitle = null;
		String testCaseName = getTestCaseName(index);

		Log.info("\nRunning " + testCaseName + " - " + rptTitle);

		rptTitle = testCaseName + " " + "Report" + getTestSetup().getRandomNumber();

		complianceReportsPage.login(strCreatedBy, new CryptoUtility().decrypt(password));
		complianceReportsPage.open();

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, strCreatedBy, cutomer, timeZone, exclusionRadius, surveyorUnit, userName, startDate, endDate, fovOpacity, lisaOpacity, geoFilter, reportMode, surveyModeFilter, ethaneFilter, listBoundary, tagList, tablesList, viewList, viewLayersList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, strCreatedBy, testCaseName))) {
			complianceReportsPage.clickOnReportViewerCloseButton();
			complianceReportsPage.checkComplianceReportButtonPresenceAndClick(rptTitle, strCreatedBy, ComplianceReportButtonType.Investigate, true, true);
			if(testCaseName.equals("TC217")){
			assertFalse(complianceReportsPage.getBtnAssignInvestigators().isEnabled());
			}
			if(testCaseName.equals("TC223")){
				checkPagination();
			}
			if(testCaseName.equals("TC225")){
				searchInvalidLISA();
			}
			if(testCaseName.equals("TC226")){
				assertTrue(complianceReportsPage.areInvestigationTableColumnsSorted());
			}
		} else
			fail("\nTestcase " + getTestCaseName(index) + " failed.\n");
	}

	private void checkPagination(){
		assertTrue(complianceReportsPage.checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(!(complianceReportsPage.getNumberofRecords() > Integer.parseInt(PAGINATIONSETTING)));
	}

	private void searchInvalidLISA(){

		assertTrue(!complianceReportsPage.searchInvestigationReport("ZZZ", getTestSetup().getLoginUser()));
		assertEquals(NOMATCHINGSEARCH, complianceReportsPage.getEmptyTableMessage());
	}

	/**
	 * Test Case ID: TC207 Test Description: Verify report and survey modes are not modified if user clicks on NO change report mode button
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws Exception
	 *
	 */
	@Ignore
	public void TC207_ComplianceReportTest_VerifyReportModeNoChange() throws Exception {
		String testCaseName	="TC207";
		String rptTitle = testCaseName+" Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning " + testCaseName + " - " + rptTitle);
		complianceReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.clickOnNewComplianceReportBtn();
		assertTrue(complianceReportsPage.checkSurveyModeDidNotChange(ReportModeFilter.Standard, SQACUSDRTAG, ReportModeFilter.Manual) );
		complianceReportsPage.waitForAddSurveyButtonToLoad();
		assertTrue(complianceReportsPage.checkSurveyModeDidNotChange(ReportModeFilter.Manual, SQACUSMNTAG,ReportModeFilter.Standard ) );
	}



	private static String getTestCaseName(String key) {
		return testCaseMap.get(key);
	}

	private static void createTestCaseMap() {
		testCaseMap.put("1", "TC192");
		testCaseMap.put("2", "TC202");
		testCaseMap.put("3", "TC210");
		testCaseMap.put("4", "TC217");
		testCaseMap.put("5", "TC223");
		testCaseMap.put("6", "TC225");
		testCaseMap.put("7", "TC226");

	}

}
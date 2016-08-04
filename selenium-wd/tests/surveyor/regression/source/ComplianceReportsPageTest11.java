/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.RSURSTARTDATE;
import static surveyor.scommon.source.SurveyorConstants.RSURENDDATE;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.PICADMNMANTAG;
import static surveyor.scommon.source.SurveyorConstants.PICADMNRRTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG3200;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTLISAASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTGAPASSETS;
import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.ReportsCompliance.EthaneFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.dataprovider.ReportDataProvider;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
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
	private static HashMap<String, String> testCaseMap = new HashMap<String, String>();

	@BeforeClass
	public static void setupComplianceReportsPageTest() {
		initializePageObjects();
		createTestCaseMap();

	}

	private static void initializePageObjects() {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
	}

	/**
	 * Test Case ID: TC192 Test Description: Generate report having multiple surveys and provide exclusion radius
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

		rptTitle = testCaseName + " " + "Report" + testSetup.getRandomNumber();

		complianceReportsPage.login(strCreatedBy, CryptoUtility.decrypt(password));
		complianceReportsPage.open();

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, strCreatedBy, cutomer, timeZone, exclusionRadius, surveyorUnit, userName, startDate, endDate, fovOpacity, lisaOpacity, geoFilter, reportMode, surveyModeFilter, ethaneFilter, listBoundary, tagList, tablesList, viewList, viewLayersList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, strCreatedBy, testCaseName))) {
			complianceReportsPage.clickOnReportViewerCloseButton();
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.verifyComplianceReportStaticText(rpt));
			assertTrue(complianceReportsPage.verifySSRSImages(testSetup.getDownloadPath(), rptTitle, testCaseName));
			if (!testCaseName.equals("TC192")) {
				if (tablesList != null) {
					if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
						assertTrue(complianceReportsPage.verifyShowCoverageTable(testSetup.getDownloadPath(), rptTitle));
						assertTrue(complianceReportsPage.verifyCoverageValuesTable(testSetup.getDownloadPath(), rptTitle, tablesList.get(0)));
					}
					if (cutomer.equalsIgnoreCase("Picarro")) {
						assertTrue(complianceReportsPage.verifyLayersTable(testSetup.getDownloadPath(), rptTitle, tablesList.get(0)));
					}
					assertTrue(complianceReportsPage.verifyViewsTable(testSetup.getDownloadPath(), rptTitle, viewList));
					assertTrue(complianceReportsPage.verifyDrivingSurveysTable(testSetup.getDownloadPath(), rptTitle));
					if (tablesList.get(0).get(KEYISOANA).equals("1")) {
						assertTrue(complianceReportsPage.verifyIsotopicAnalysisTable(testSetup.getDownloadPath(), rptTitle));
					}
					if (tablesList.get(0).get(KEYINDTB).equals("1")) {
						assertTrue(complianceReportsPage.verifyIndicationTable(testSetup.getDownloadPath(), rptTitle));
					}
				}
			}
			assertTrue(complianceReportsPage.verifyAllViewsImages(testSetup.getDownloadPath(), rptTitle, testCaseName, viewList.size()));

		} else
			fail("\nTestcase " + getTestCaseName(index) + " failed.\n");

	}
	
	/**
	 * Test Case ID: TC517 Test Description: Generate compliance report with all default values/filters selected and download it
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws Exception
	 * 
	 */
	@Test
	public void ComplianceReportTest_VerifyReportModeNoChange(String index, String strCreatedBy, String password, String cutomer, String timeZone, String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate, String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) throws Exception {
		String testCaseName	="TC207";
		String rptTitle = testCaseName+" Report" + testSetup.getRandomNumber();
		Log.info("\nRunning " + testCaseName + " - " + rptTitle);
		complianceReportsPage.login(strCreatedBy, CryptoUtility.decrypt(password));
		complianceReportsPage.open();
		complianceReportsPage.clickOnNewComplianceReportBtn();
		
		


	}


	private static String getTestCaseName(String key) {
		return testCaseMap.get(key);
	}

	private static void createTestCaseMap() {
		testCaseMap.put("1", "TC192");
		testCaseMap.put("2", "TC202");
	}

}
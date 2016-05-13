package surveyor.unittest.source;

import common.source.Log;
import common.source.PDFUtility;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.junit.Test;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageUnitTest extends BaseReportsPageTest {
	
	private static ComplianceReportsPage complianceReportsPage;
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageObjects();
	}

	/**
	 * Initializes the page objects.
	 */
	protected static void initializePageObjects(){
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
	}
	
	@Test
	public void TempMethod_ForDebugging() throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		String pdfText = pdfUtility.extractPDFText("C:\\Users\\spulikkal\\Downloads\\CR-F6E6B8.pdf");
		Log.info(pdfText);
	}
	
	/**
	 * TA862 - Searched Surveys should be filtered by selected report mode
	 * @throws Exception
	 */
	@Test
	public void TA862_ComplianceReportTest_VerifySurveyFilters() throws Exception {
		
		Log.info("\nTA862 - Searched Surveys should be filtered by selected report mode");
		
		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
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
	
		complianceReportsPage.logout();
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

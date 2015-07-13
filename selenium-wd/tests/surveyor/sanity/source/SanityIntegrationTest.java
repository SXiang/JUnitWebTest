/**
 * 
 */
package surveyor.sanity.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASE;
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
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUAUSER;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class SanityIntegrationTest extends SurveyorBaseTest {
	private static ComplianceReportsPage complianceReportsPage;

	@BeforeClass
	public static void SetupSanityIntegrationTest() {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  complianceReportsPage);	
	}
	
	@Test
	public void loginTest_TC25_PicarroAdmin() {
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		
		homePage.open();
		assertTrue(PICDFADMIN + " user login unsuccessful!",
				homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}
	
	@Test
	public void loginTest_TC25_CustomerAdmin() {
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.open();
		assertTrue(SQACUSUAUSER
				+ " user login unsuccessful!", homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}
	
	@Test
	public void loginTest_TC25_CustomerDriver() {
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();
		assertTrue(SQACUSDRUSER
				+ " user login unsuccessful!", homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}
	
	@Test
	public void downloadTest() {
		
		complianceReportsPage.login(PICDFADMIN, USERPASSWORD);
		
		complianceReportsPage.open();
		
	}
	
	/**
	 * Test Case ID: TC739 Test Description: Generate compliance report as
	 * customer supervisor user by selecting report area using custom boundary
	 * 
	 */
	/*@Test
	public void TC739() {
		String rptTitle = "Customer Supervisor Report TC739 "
				+ testSetup.getRandomNumber();
		System.out
				.format("\nRunning TC739: Generate compliance report as customer supervisor user by selecting report area using custom boundary, %s\n",
						rptTitle);

		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		 complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("8.5");
		listBoundary.add("11");
		listBoundary.add("37.39554669269125");
		listBoundary.add("-121.93632605840963");
		listBoundary.add("37.42172677338927");
		listBoundary.add("-121.98619368841455");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();
		Map<String, String> viewMap3 = new HashMap<String, String>();

		viewMap3.put(KEYVIEWNAME, "First View");
		viewMap3.put(KEYLISA, "1");
		viewMap3.put(KEYFOV, "1");
		viewMap3.put(KEYBREADCRUMB, "1");
		viewMap3.put(KEYINDICATIONS, "1");
		viewMap3.put(KEYISOTOPICCAPTURE, "1");
		viewMap3.put(KEYANNOTATION, "1");
		viewMap3.put(KEYGAPS, "0");
		viewMap3.put(KEYASSETS, "1");
		viewMap3.put(KEYBOUNDARIES, "1");
		viewMap3.put(KEYBASEMAP, "Map");

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "0");
		viewMap2.put(KEYINDICATIONS, "0");
		viewMap2.put(KEYISOTOPICCAPTURE, "0");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "1");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, "Satellite");

		viewMap1.put(KEYVIEWNAME, "Third View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "0");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);
		viewList.add(viewMap2);
		viewList.add(viewMap3);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU,
				CUSNAMEBASE, TIMEZONEPT, "0", listBoundary, tablesList,
				SQACUSLOCSUR, SQACUSDRTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU))) {
			assertTrue(complianceReportsPage.findReport(rptTitle,
					SQACUSSU));
			assertTrue(complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath()));
		} else
			fail("\nTestcase TC739 failed." + rptTitle
					+ " report failed to generate by " + SQACUSSU + " user!!\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}*/
	
	@Test
	public void RPT001() {
//		String rptTitle = "RPT001 Report" + testSetup.getRandomNumber();
		String rptTitle = "Customer Supervisor Report TC739 994450";
		System.out
				.format("\nRunning RPT001: Generate compliance report with all default values/filters selected and download it, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		//String surUnit = "";
		// changed survey tag for time being---- remove the code later on and fix the tag value as per surveys available in SureyorConstants file
		//String SQAPICSUTAG = "SmokeTest 6/12";
		
//		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);
//		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rptTitle,
					testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, PICDFADMIN));
		} else
			fail("\nTestcase RPT001 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
}
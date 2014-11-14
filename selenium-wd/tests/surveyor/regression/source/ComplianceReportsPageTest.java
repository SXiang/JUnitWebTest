/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

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
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ComplianceReportsPageTest extends SurveyorBaseTest {
	private static ComplianceReportsPage complianceReportsPage = null;
	
	@BeforeClass
	public static void setupComplianceReportsPageTest() {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  complianceReportsPage);
	}
	
	/**
	 * Test Case ID: RPT000
	 * Test Description: Create a new compliance report, basic and generic by default Administrator
	 * 
	 */	
	@Test
	public void RPT000() {
		String rptTitle = TITLENAMEBASE + testSetup.getRandomNumber() + "RPT000";
		System.out.format("\nRunning - RPT000 - Test Description: Create a new compliance report, basic and generic, %s\n", rptTitle);
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		complianceReportsPage.open();
		complianceReportsPage.addNewPDReport(rptTitle, SQACUS);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser())))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, testSetup.getLoginUser()));
		else
			fail("\nTestcase RPT000 - Test Description: Create a new compliance report, basic and generic, failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000A
	 * Test Description: Create a compliance report by a user with Customer Utility Admin Role, survey owner
	 * 
	 */	
	@Test
	public void RPT000A() {
		String rptTitle = SQACUSUAUSER + testSetup.getRandomNumber() + "RPT000A";
		System.out.format("\nRunning - RPT000A - Test Description: Create a compliance report by a user with Customer Utility Admin Role, survey owner, %s\n", rptTitle);
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		complianceReportsPage.open();
		
		String surUnit = SQACUS + " - " + SQACUSLOC + "1" + " - " + SQACUSLOC1SUR;
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSUATAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQACUSUA));
		else
			fail("\nTestcase RPT000A - Test Description: Create a compliance report by a user with Customer Utility Admin Role, survey owner, failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000A1
	 * Test Description: Create a compliance report by a user with Customer Utility Admin Role, supervisor survey
	 * 
	 */	
	@Test
	public void RPT000A1() {
		String rptTitle = SQACUSUAUSER + testSetup.getRandomNumber() + "RPT000A1";
		System.out.format("\nRunning - RPT000A1 - Test Description: Create a compliance report by a user with Customer Utility Admin Role, supervisor survey, %s\n", rptTitle);
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		complianceReportsPage.open();
		
		String surUnit = SQACUS + " - " + SQACUSLOC + "2" + " - " + SQACUSLOC2SUR;
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSSUTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQACUSUA));
		else
			fail("\nTestcase RPT000A1 - Test Description: Create a compliance report by a user with Customer Utility Admin Role, supervisor survey, failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000A2
	 * Test Description: Create a compliance report by a user with Customer Utility Admin Role, driver survey
	 * 
	 */	
	@Test
	public void RPT000A2() {
		String rptTitle = SQACUSUAUSER + testSetup.getRandomNumber() + "RPT000A2";
		System.out.format("\nRunning - RPT000A2 - Test Description: Create a compliance report by a user with Customer Utility Admin Role, driver survey, %s\n", rptTitle);
		
		complianceReportsPage.login(SQACUSUA, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = SQACUS + " - " + SQACUSLOC + "3" + " - " + SQACUSLOC3SUR;
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSDRTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQACUSUA));
		else
			fail("\nTestcase RPT000A2 - Test Description: Create a compliance report by a user with Customer Utility Admin Role, driver survey, failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000B
	 * Test Description: Create a compliance report by a user with Customer Supervisor Role, survey owner
	 * 
	 */	
	@Test
	public void RPT000B() {
		String rptTitle = SQACUSSUUSER + testSetup.getRandomNumber() + "RPT000B";
		System.out.format("\nRunning - RPT000B - Test Description: Create a compliance report by a user with Customer Supervisor Role, survey owner, %s\n", rptTitle);
		
		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = SQACUS + " - " + SQACUSLOC + "2" + " - " + SQACUSLOC2SUR;
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSSUTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQACUSSU));
		else
			fail("\nTestcase RPT000B - Test Description: Create a compliance report by a user with Customer Supervisor Role, survey owner, failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000B1
	 * Test Description: Create a compliance report by a user with Customer Supervisor Role, driver survey
	 * 
	 */	
	@Test
	public void RPT000B1() {
		String rptTitle = SQACUSSUUSER + testSetup.getRandomNumber() + "RPT000B1";
		System.out.format("\nRunning - RPT000B1 - Test Description: Create a compliance report by a user with Customer Supervisor Role, driver survey, %s\n", rptTitle);
		
		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSDRTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQACUSSU));
		else
			fail("\nTestcase RPT000B1 - Test Description: Create a compliance report by a user with Customer Supervisor Role, driver survey, failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000B2
	 * Test Description: Create a compliance report by a user with Customer Supervisor Role, utility admin survey 
	 * 
	 */	
	@Test
	public void RPT000B2() {
		String rptTitle = SQACUSSUUSER + testSetup.getRandomNumber() + "RPT000B2";
		System.out.format("\nRunning - RPT000B2 - Test Description: Create a compliance report by a user with Customer Supervisor Role, utility admin survey, %s\n", rptTitle);
		
		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSUATAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQACUSSU));
		else
			fail("\nTestcase RPT000B2 - Test Description: Create a compliance report by a user with Customer Supervisor Role, utility admin survey, failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000C
	 * Test Description: Create a compliance report by a user with Administrator Role, survey owner 
	 * 
	 */	
	@Test
	public void RPT000C() {
		String rptTitle = SQAPICADUSER + testSetup.getRandomNumber() + "RPT000C";
		System.out.format("\nRunning - RPT000C - Test Description: Create a compliance report by a user with Administrator Role, survey owner, %s\n", rptTitle);
		
		complianceReportsPage.login(SQAPICAD, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICADTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICAD)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQAPICAD));
		else
			fail("\nTestcase RPT000C failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000C1
	 * Test Description: Create a compliance report by a user with Administrator Role, utility admin survey 
	 * 
	 */	
	@Test
	public void RPT000C1() {
		String rptTitle = SQAPICADUSER + testSetup.getRandomNumber() + "RPT000C1";
		System.out.format("\nRunning - RPT000C1 - Test Description: Create a compliance report by a user with Administrator Role, utility admin survey, %s\n", rptTitle);
		
		complianceReportsPage.login(SQAPICAD, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICUATAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICAD)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQAPICAD));
		else
			fail("\nTestcase RPT000C1 failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000C2
	 * Test Description: Create a compliance report by a user with Administrator Role, supervisor survey 
	 * 
	 */	
	@Test
	public void RPT000C2() {
		String rptTitle = SQAPICADUSER + testSetup.getRandomNumber() + "RPT000C2";
		System.out.format("\nRunning - RPT000C2 - Test Description: Create a compliance report by a user with Administrator Role, supervisor survey, %s\n", rptTitle);
		
		complianceReportsPage.login(SQAPICAD, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICAD)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQAPICAD));
		else
			fail("\nTestcase RPT000C2 failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000C3
	 * Test Description: Create a compliance report by a user with Administrator Role, driver survey 
	 * 
	 */	
	@Test
	public void RPT000C3() {
		String rptTitle = SQAPICADUSER + testSetup.getRandomNumber() + "RPT000C3";
		System.out.format("\nRunning - RPT000C3 - Test Description: Create a compliance report by a user with Administrator Role, driver survey, %s\n", rptTitle);
		
		complianceReportsPage.login(SQAPICAD, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICDRTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICAD)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQAPICAD));
		else
			fail("\nTestcase RPT000C3 failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000D
	 * Test Description: Create a compliance report by a user with Picarro Utility Admin Role, survey owner 
	 * 
	 */	
	@Test
	public void RPT000D() {
		String rptTitle = SQAPICUAUSER + testSetup.getRandomNumber() + "RPT000D";
		System.out.format("\nRunning - RPT000D - Test Description: Create a compliance report by a user with Picarro Utility Admin Role, survey owner, %s\n", rptTitle);
		
		complianceReportsPage.login(SQAPICUA, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICUATAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQAPICUA));
		else
			fail("\nTestcase RPT000D failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000D1
	 * Test Description: Create a compliance report by a user with Picarro Utility Admin Role, admin survey 
	 * 
	 */	
	@Test
	public void RPT000D1() {
		String rptTitle = SQAPICUAUSER + testSetup.getRandomNumber() + "RPT000D1";
		System.out.format("\nRunning - RPT000D1 - Test Description: Create a compliance report by a user with Picarro Utility Admin Role, admin survey, %s\n", rptTitle);
		
		complianceReportsPage.login(SQAPICUA, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICADTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQAPICUA));
		else
			fail("\nTestcase RPT000D1 failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000D2
	 * Test Description: Create a compliance report by a user with Picarro Utility Admin Role, supervisor survey 
	 * 
	 */	
	@Test
	public void RPT000D2() {
		String rptTitle = SQAPICUAUSER + testSetup.getRandomNumber() + "RPT000D2";
		System.out.format("\nRunning - RPT000D2 - Test Description: Create a compliance report by a user with Picarro Utility Admin Role, supervisor survey, %s\n", rptTitle);
		
		complianceReportsPage.login(SQAPICUA, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQAPICUA));
		else
			fail("\nTestcase RPT000D2 failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000D3
	 * Test Description: Create a compliance report by a user with Picarro Utility Admin Role, driver survey 
	 * 
	 */	
	@Test
	public void RPT000D3() {
		String rptTitle = SQAPICUAUSER + testSetup.getRandomNumber() + "RPT000D3";
		System.out.format("\nRunning - RPT000D3 - Test Description: Create a compliance report by a user with Picarro Utility Admin Role, driver survey, %s\n", rptTitle);
		
		complianceReportsPage.login(SQAPICUA, USERPASSWORD);
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICDRTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, SQAPICUA));
		else
			fail("\nTestcase RPT000D3 failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000E
	 * Test Description: Delete a compliance report by Administrator 
	 * 
	 */	
	@Test
	public void RPT000E() {
		String rptTitle = PICDFADMIN + testSetup.getRandomNumber() + "RPT000E";
		System.out.format("\nRunning - RPT000E - Test Description: Delete a compliance report by Administrator, %s\n", rptTitle);
		
		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICDRTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, PICDFADMIN));
		else
			fail("\nTestcase RPT000E failed.\n");
		
		if (complianceReportsPage.deleteReport(rptTitle, PICDFADMIN))
			assertTrue(!complianceReportsPage.findExistingReport(rptTitle, PICDFADMIN));
		else
			fail("\nTestcase RPT000E failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000F
	 * Test Description: copy an Administrator's compliance report by Administrator 
	 * 
	 */	
	@Test
	public void RPT000F() {
		String rptTitle = PICDFADMIN + testSetup.getRandomNumber() + "RPT000F";
		System.out.format("\nRunning - RPT000F - Test Description: copy an Administrator's compliance report by Administrator, %s\n", rptTitle);
		
		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICDRTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, PICDFADMIN));
		else
			fail("\nTestcase RPT000F failed.\n");
		
		complianceReportsPage.copyReport(rptTitle, PICDFADMIN, rptTitle + "COPY");
		
		if ((complianceReportsPage.checkActionStatus(rptTitle + "COPY", PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle + "COPY", PICDFADMIN));
		else
			fail("\nTestcase RPT000F failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000G
	 * Test Description: Create a compliance report by Administrator for a Picarro Survey 
	 * 
	 */	
	@Test
	public void RPT000G() {
		String rptTitle = PICDFADMIN + testSetup.getRandomNumber() + "RPT000G";
		System.out.format("\nRunning - RPT000G - Test Description: Create a compliance report by Administrator for a Picarro Survey, %s\n", rptTitle);
		
		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, "Picarro", surUnit, SQAPICDRTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, PICDFADMIN));
		else
			fail("\nTestcase RPT000G failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000H
	 * Test Description: Create a compliance report by Administrator for a customer Survey 
	 * 
	 */	
	@Test
	public void RPT000H() {
		String rptTitle = PICDFADMIN + testSetup.getRandomNumber() + "RPT000H";
		System.out.format("\nRunning - RPT000H - Test Description: Create a compliance report by Administrator for a customer Survey, %s\n", rptTitle);
		
		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		
		String surUnit = "";
		
		complianceReportsPage.addNewPDReport(rptTitle, SQACUS, surUnit, SQACUSDRTAG);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, PICDFADMIN));
		else
			fail("\nTestcase RPT000H failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT000I
	 * Test Description: Create a compliance report by Administrator with more general options 
	 * 
	 */	
	@Test
	public void RPT000I() {
		String rptTitle = PICDFADMIN + testSetup.getRandomNumber() + "RPT000I";
		System.out.format("\nRunning - RPT000I - Test Description: Create a compliance report by Administrator with more general options, %s\n", rptTitle);
		
		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		
		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("5");
		listBoundary.add("5");
		listBoundary.add("37.4353397926825");
		listBoundary.add("-121.84696197509766");
		listBoundary.add("37.33058362073965");
		listBoundary.add("-122.04883575439453");
		
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();
		
		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA,  "1");
		viewMap1.put(KEYFOV,  "1");
		viewMap1.put(KEYBREADCRUMB,  "1");
		viewMap1.put(KEYINDICATIONS,  "1");
		viewMap1.put(KEYISOTOPICCAPTURE,  "1");
		viewMap1.put(KEYANNOTATION,  "1");
		viewMap1.put(KEYGAPS,  "1");
		viewMap1.put(KEYASSETS,  "1");
		viewMap1.put(KEYBOUNDARIES,  "1");
		viewMap1.put(KEYBASEMAP,  "Map");

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA,  "1");
		viewMap2.put(KEYFOV,  "0");
		viewMap2.put(KEYBREADCRUMB,  "0");
		viewMap2.put(KEYINDICATIONS,  "0");
		viewMap2.put(KEYISOTOPICCAPTURE,  "0");
		viewMap2.put(KEYANNOTATION,  "0");
		viewMap2.put(KEYGAPS,  "0");
		viewMap2.put(KEYASSETS,  "0");
		viewMap2.put(KEYBOUNDARIES,  "0");
		viewMap2.put(KEYBASEMAP,  "Satellite");
		
		viewList.add(viewMap1);
		viewList.add(viewMap2);
		
		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);
		
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "3", listBoundary, tablesList, "", SQAPICDRTAG, viewList);
		complianceReportsPage.addNewReport(rpt);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle, testSetup.getLoginUser()));	
			}
		}
		else
			fail("\nTestcase RPT000I failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}	
	
	/**
	 * Test Case ID: RPT001 Test Description: Generate compliance report with
	 * all default values/filters selected and download it
	 * 
	 */
	@Test
	public void RPT001() {
		String rptTitle = "RPT001" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT001: Generate compliance report with all default values/filters selected and download it, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		} else
			fail("\nTestcase RPT001 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT003 Test Description: Generate compliance report by
	 * selecting custom boundary using date range and tag filters for more than
	 * one view and export the report
	 * 
	 */
	@Test
	public void RPT003() {
		String rptTitle = "RPT003 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT003: Generate compliance report by selecting custom boundary using date range and tag filters for more than one view and export the report, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();
		Map<String, String> viewMap3 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "0");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, "Map");

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "1");
		viewMap2.put(KEYINDICATIONS, "1");
		viewMap2.put(KEYISOTOPICCAPTURE, "1");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, "Satellite");

		viewMap3.put(KEYVIEWNAME, "Third View");
		viewMap3.put(KEYLISA, "0");
		viewMap3.put(KEYFOV, "0");
		viewMap3.put(KEYBREADCRUMB, "0");
		viewMap3.put(KEYINDICATIONS, "1");
		viewMap3.put(KEYISOTOPICCAPTURE, "0");
		viewMap3.put(KEYANNOTATION, "1");
		viewMap3.put(KEYGAPS, "0");
		viewMap3.put(KEYASSETS, "1");
		viewMap3.put(KEYBOUNDARIES, "1");
		viewMap3.put(KEYBASEMAP, "Satellite");

		viewList.add(viewMap1);
		viewList.add(viewMap2);
		viewList.add(viewMap3);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		// Date Range filter remaining
		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0",
				listBoundary, tablesList, "", SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			}
		} else
			fail("\nTestcase RPT003 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT003A Test Description: Generate compliance report using
	 * surveyor unit and tag filters for more than one view and download the
	 * report
	 * 
	 */
	@Test
	public void RPT003A() {
		String rptTitle = "RPT003A Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT003A: Generate compliance report using surveyor unit and tag filters for more than one view and download the report, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();
		Map<String, String> viewMap3 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "0");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, "Map");

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "0");
		viewMap2.put(KEYINDICATIONS, "0");
		viewMap2.put(KEYISOTOPICCAPTURE, "0");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, "Satellite");

		viewMap3.put(KEYVIEWNAME, "Third View");
		viewMap3.put(KEYLISA, "0");
		viewMap3.put(KEYFOV, "0");
		viewMap3.put(KEYBREADCRUMB, "1");
		viewMap3.put(KEYINDICATIONS, "1");
		viewMap3.put(KEYISOTOPICCAPTURE, "0");
		viewMap3.put(KEYANNOTATION, "1");
		viewMap3.put(KEYGAPS, "0");
		viewMap3.put(KEYASSETS, "0");
		viewMap3.put(KEYBOUNDARIES, "0");
		viewMap3.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);
		viewList.add(viewMap2);
		viewList.add(viewMap3);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0",
				listBoundary, tablesList, "", SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			}
		} else
			fail("\nTestcase RPT003A failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT004 Test Description: Generate Compliance Report and
	 * include Picarro Surveyor Unit filter
	 * 
	 */
	@Test
	public void RPT004() {
		String rptTitle = "RPT004 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT004: Generate Compliance Report and include Picarro Surveyor Unit filter, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, SQAPICLOC3SUR, SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			}
		} else
			fail("\nTestcase RPT004 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT005 Test Description: Generate report by providing tag
	 * filter
	 * 
	 */
	@Test
	public void RPT005() {
		String rptTitle = "RPT005 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT005: Generate report by providing tag filter, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, "", SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			}
		} else
			fail("\nTestcase RPT005 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT006 Test Description: Generate compliance report using
	 * date range and surveyor unit filters for more than one view and download
	 * the report
	 * 
	 */
	@Test
	public void RPT006() {
		String rptTitle = "RPT006 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT006: Generate compliance report using date range and surveyor unit filters for more than one view and download the report, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		// Start and end date filter insertion remaining
		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, SQAPICLOC3SUR, SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			}
		} else
			fail("\nTestcase RPT006 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT007 Test Description: Copy and modify report from
	 * previously run reports
	 * 
	 */
	@Test
	public void RPT007() {
		String rptTitle = "RPT007 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT007: Copy and modify report from previously run reports, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nNew Report Generation failed.\n");

		complianceReportsPage.copyReportAndModifyDetails(rptTitle, PICDFADMIN,
				rptTitle + "COPY");

		if ((complianceReportsPage.checkActionStatus(rptTitle + "COPY",
				PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle
					+ "COPY", PICDFADMIN));
		else
			fail("\nTestcase RPT007 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT008 Test Description: Generate Compliance Report and
	 * include Coverage Percentage of the assets
	 * 
	 */
	@Test
	public void RPT008() {
		String rptTitle = "RPT008 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT008: Generate Compliance Report and include Coverage Percentage of the assets, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		// Start and end date filter insertion remaining
		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, SQAPICLOC3SUR, SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			}
		} else
			fail("\nTestcase RPT008 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT009 Test Description: Generate Compliance Report and
	 * include Coverage Percentage by area
	 * 
	 */
	@Test
	public void RPT009() {
		String rptTitle = "RPT009 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT009: Generate Compliance Report and include Coverage Percentage by area, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		// Start and end date filter insertion remaining
		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, SQAPICLOC3SUR, SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			}
		} else
			fail("\nTestcase RPT009 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT010 Generate reports showing gaps that are not covered
	 * 
	 */
	@Test
	public void RPT010() {
		String rptTitle = "RPT010 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT010: Generate reports showing gaps that are not covered, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.40418739795187");
		listBoundary.add("-121.97484970092772");
		listBoundary.add("37.385435182627226");
		listBoundary.add("-121.99742317199707");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "0");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, SQAPICLOC3SUR, SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			}
		} else
			fail("\nTestcase RPT010 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT022 Test Description: Duplicate report
	 * 
	 */
	@Test
	public void RPT022() {
		String rptTitle = "RPT022 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning RPT022: Duplicate report, %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nNew Report Generation failed.\n");

		complianceReportsPage.copyReport(rptTitle, PICDFADMIN, rptTitle
				+ "COPY");

		if ((complianceReportsPage.checkActionStatus(rptTitle + "COPY",
				PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle
					+ "COPY", PICDFADMIN));
		else
			fail("\nTestcase RPT022 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
}
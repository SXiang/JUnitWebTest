/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ComplianceReportsPage;
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
}
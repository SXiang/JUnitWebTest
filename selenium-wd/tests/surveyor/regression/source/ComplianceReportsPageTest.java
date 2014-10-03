/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

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
	private ComplianceReportsPage complianceReportsPage = null;
	
	public ComplianceReportsPageTest() {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  complianceReportsPage);
		
		complianceReportsPage.open();
		
		if (driver.getTitle().equalsIgnoreCase(complianceReportsPage.STRPageTitle)) {
			complianceReportsPage.logout();
		}
	}
	
	/**
	 * Test Case ID: RPT000
	 * Test Description: Create a new compliance report, basic and generic
	 * 
	 */	
	@Test
	public void RPT000() {
		String rptTitle = TITLENAMEBASE + testSetup.getRandomNumber();
		System.out.format("\nRunning - RPT000 - Test Description: Create a new compliance report, basic and generic, %s\n", rptTitle);
		
		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		
		complianceReportsPage.addNewPDReport(rptTitle);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser())))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, testSetup.getLoginUser()));
		else
			fail("\nTestcase RPT000 - Test Description: Create a new compliance report, basic and generic, failed.\n");
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
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
		String rptTitle = SQACUSUAUSER + testSetup.getRandomNumber();
		System.out.format("\nRunning - RPT000A - Test Description: Create a compliance report by a user with Customer Utility Admin Role, survey owner, %s\n", rptTitle);
		
		complianceReportsPage.login(SQACUSUA, USERPASSWORD);
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
		String rptTitle = SQACUSUAUSER + testSetup.getRandomNumber();
		System.out.format("\nRunning - RPT000A1 - Test Description: Create a compliance report by a user with Customer Utility Admin Role, supervisor survey, %s\n", rptTitle);
		
		complianceReportsPage.login(SQACUSUA, USERPASSWORD);
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
		String rptTitle = SQACUSUAUSER + testSetup.getRandomNumber();
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
		String rptTitle = SQACUSSUUSER + testSetup.getRandomNumber();
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
		String rptTitle = SQACUSSUUSER + testSetup.getRandomNumber();
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
		String rptTitle = SQACUSSUUSER + testSetup.getRandomNumber();
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
}
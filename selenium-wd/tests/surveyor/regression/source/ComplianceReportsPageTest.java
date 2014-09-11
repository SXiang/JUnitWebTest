/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ComplianceReportsPageTest extends SurveyorBaseTest {
	public ComplianceReportsPageTest() {
	}
	
	/**
	 * Test Case ID: RPT000
	 * Test Description: Create a new compliance report
	 * 
	 */	
	@Test
	public void RPT000() {
		String rptTitle = ComplianceReportsPage.TITLENAMEBASE + testSetup.getRandomNumber();
		System.out.format("\nRunning - RPT000 - Test Description: Create a new compliance report %s\n", rptTitle);
		
		fail("\nKnown issue for the compliance reports so failed it manually without running the tests...\n");
		
//		ComplianceReportsPage complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
//		PageFactory.initElements(driver,  complianceReportsPage);
//		
//		complianceReportsPage.open();
//		
//		if (debug)
//			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
//		
//		complianceReportsPage.addNewPDReport(rptTitle);
//		
//		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
//		
//		//complianceReportsPage.open();
//		
//		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser())))
//			assertTrue(complianceReportsPage.findExistingReport(rptTitle, testSetup.getLoginUser()));
//		else
//			fail("\nTestcase RPT000 - Test Description: Create a new compliance report, failed.\n");
//		
//		if (debug)
//			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
	}		
}
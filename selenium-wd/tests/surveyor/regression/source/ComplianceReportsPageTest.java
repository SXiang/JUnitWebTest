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
	private static ComplianceReportsPage complianceReportsPage;
	
	String rptTitle = ComplianceReportsPage.TITLENAMEBASE + testSetup.getRandomNumber();
	
	public ComplianceReportsPageTest() {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup, rptTitle);
		PageFactory.initElements(driver,  complianceReportsPage);
	}
	
	/**
	 * Test Case ID: RPT000
	 * Test Description: Create a new compliance report
	 * 
	 */	
	@Test
	public void RPT000() {
		System.out.format("\nTestcase - RPT000: Create a new compliance report %s", rptTitle);
		
		try {
			complianceReportsPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(complianceReportsPage.addNewPDReport());
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			//assertTrue(complianceReportsPage.findExistingReport(rptTitle, testSetup.getLoginUser()));
		}
		catch (Exception e) {
			System.out.format("\nException on test case \"RPT000\": %s", e.getMessage());
			fail("\nTestcase RPT000 failed: \n" + e.getMessage());
		}		
	}
}
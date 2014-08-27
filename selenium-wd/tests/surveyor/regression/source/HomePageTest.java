/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class HomePageTest extends SurveyorBaseTest {
	private static HomePage homePage;
	
	public HomePageTest() {
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
	}
	
	/**
	 * Test Case ID: HOME000
	 * Test Description: Sanity check on home page with Picarro Admin login
	 */
	@Test
	public void HOME000() {
		System.out.println("\nTestcase - HOME000: Sanity check on home page with Picarro Admin login\n");
		
		try {
			homePage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(homePage.checkAdministratorHomePage());
			
			if (debug)
				testSetup.slowdownInSeconds(3);
		}
		catch (Exception e) {
			System.out.format("\nException on test case \"HOME000\": %s\n", e.getMessage());
			fail("\nTestcase HOME000 failed: \n" + e.getMessage());
		}
	}

	/**
	 * Test Case ID: HOME000A
	 * Test Description: Sanity check on home page DashBoard with Picarro Admin login
	 */
	@Test
	public void HOME000A() {
		System.out.println("\nTestcase - HOME000A: Sanity check on home page DashBoard with Picarro Admin login\n");
		
		try {
			homePage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(homePage.checkAdministratorDashboard());
			
			if (debug)
				testSetup.slowdownInSeconds(3);
		}
		catch (Exception e) {
			System.out.format("\nException on test case \"HOME000A\": %s\n", e.getMessage());
			fail("\nTestcase HOME000A failed: \n" + e.getMessage());
		}
	}

	/**
	 * Test Case ID: HOME000B
	 * Test Description: Sanity check on home page DashBoard/View All Surveyors Link with Picarro Admin login
	 */
	@Test
	public void HOME000B() {
		System.out.println("\nTestcase - HOME000B: Sanity check on home page DashBoard/View All Surveyors Link with Picarro Admin login\n");
		
		try {
			homePage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(homePage.checkDashBoardViewAllSurveyorsLink());
			
			if (debug)
				testSetup.slowdownInSeconds(3);
		}
		catch (Exception e) {
			System.out.format("\nException on test case \"HOME000B\": %s\n", e.getMessage());
			fail("\nTestcase HOME000B failed: \n" + e.getMessage());
		}
	}

	/**
	 * Test Case ID: HOME000C
	 * Test Description: Sanity check on home page DashBoard/View All Driving Surveys Link with Picarro Admin login
	 */
	@Test
	public void HOME000C() {
		System.out.println("\nTestcase - HOME000C: Sanity check on home page DashBoard/View All Driving Surveys Link with Picarro Admin login\n");
		
		try {
			homePage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(homePage.checkDashBoardViewAllDrivingSurveysLink());
			
			if (debug)
				testSetup.slowdownInSeconds(3);
		}
		catch (Exception e) {
			System.out.format("\nException on test case \"HOME000C\": %s\n", e.getMessage());
			fail("\nTestcase HOME000C failed: \n" + e.getMessage());
		}
	}	
}
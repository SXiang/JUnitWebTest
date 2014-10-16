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
	
	public HomePageTest() {
		
	}
	
	/**
	 * Test Case ID: HOME000
	 * Test Description: Sanity check on home page with Picarro Admin login
	 */
	@Test
	public void HOME000() {
		System.out.println("\nTestcase - HOME000: Sanity check on home page with Picarro Admin login\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		homePage.open();
		
		assertTrue(homePage.checkAdministratorHomePage());
	}

	/**
	 * Test Case ID: HOME000A
	 * Test Description: Sanity check on home page DashBoard with Picarro Admin login
	 */
	@Test
	public void HOME000A() {
		System.out.println("\nTestcase - HOME000A: Sanity check on home page DashBoard with Picarro Admin login\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		homePage.open();
		
		assertTrue(homePage.checkAdministratorDashboard());
	}

	/**
	 * Test Case ID: HOME000B
	 * Test Description: Sanity check on home page DashBoard/View All Surveyors Link with Picarro Admin login
	 */
	@Test
	public void HOME000B() {
		System.out.println("\nTestcase - HOME000B: Sanity check on home page DashBoard/View All Surveyors Link with Picarro Admin login\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		homePage.open();
		
		assertTrue(homePage.checkDashBoardViewAllSurveyorsLink());
	}

	/**
	 * Test Case ID: HOME000C
	 * Test Description: Sanity check on home page DashBoard/View All Driving Surveys Link with Picarro Admin login
	 */
	@Test
	public void HOME000C() {
		System.out.println("\nTestcase - HOME000C: Sanity check on home page DashBoard/View All Driving Surveys Link with Picarro Admin login\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		homePage.open();
		
		assertTrue(homePage.checkDashBoardViewAllDrivingSurveysLink());
	}	
}
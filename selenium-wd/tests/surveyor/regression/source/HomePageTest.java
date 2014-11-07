/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorP3URLs.*;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class HomePageTest extends SurveyorBaseTest {
	
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
	
	/**
	 * Test Case ID: HOME003
	 * Test Description: Picarro Surveyors link working
	 * Test Script: - Login to p-cubed
     *              - Click on Picarro Surveyors link
	 * Expected Results: - User is navigated to Picarro Surveyors page
	 */
	@Test
	public void HOME003() {
		System.out.println("\nRunning - HOME003 - Test Description: Picarro Surveyors link working\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		
		homePage.getLinkSurveyors().click();
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(testSetup.getBaseUrl() + SURVEYORS));
	}
	
	/**
	 * Test Case ID: HOME004
	 * Test Description: Picarro Administrator link working
	 * Test Script: - Login to p-cubed
     *              - Click on Picarro Administrator link
     * Expected Results: - Administrator Menu is displayed
	 */
	@Test
	public void HOME004() {
		System.out.println("\nRunning - HOME004 - Test Description: Picarro Administrator link working\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		
		homePage.getLinkPicarroAdmin().click();
		
		assertTrue(homePage.getLinkPicAdminManageCus().isDisplayed());
	}
	
	/**
	 * Test Case ID: HOME008
	 * Test Description: Send Feedback link working
	 * Test Script: - Login to p-cubed and click on Send Feedback link
	 *				- Click on Send button"
	 * Expected Results: - User is navigated to Send Feedback page
	 */
	@Test
	public void HOME008() {
		System.out.println("\nRunning - HOME008 - Test Description: Send Feedback link working\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);		
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		
		homePage.getLinkSendFB().click();
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(testSetup.getBaseUrl() + SENDFEEDBACK));
	}
	
	/**
	 * Test Case ID: HOME011
	 * Test Description: Reports link working and user is able to see the report menu
	 * Test Script: - Login to the site and click on Reports link
	 * Expected Results: - Report Menu is displayed
	 */
	@Test
	public void HOME011() {
		System.out.println("\nRunning - HOME011 - Test Description: Reports link working and user is able to see the report menu\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);		
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		
		homePage.getLinkReports().click();
		assertTrue(homePage.getLinkCompliance().isDisplayed());
		assertTrue(homePage.getLinkInvestigation().isDisplayed());
		assertTrue(homePage.getLinkReferenceGas().isDisplayed());
		assertTrue(homePage.getLinkSystemHistory().isDisplayed());
	}
	
	/**
	 * Test Case ID: HOME012
	 * Test Description: Driving Surveys link working
	 * Test Script: - Login to p-cubed and click on Driving Surveys link
	 * Expected Results: - User is navigated to Driving Surveys page
	 */
	@Test
	public void HOME012() {
		System.out.println("\nRunning - HOME012 - Test Description: Driving Surveys link working\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);		
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		
		homePage.getLinkDrivingSurveys().click();
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(testSetup.getBaseUrl() + DRIVINGSURVEYS));
	}
	
	/**
	 * Test Case ID: HOME014
	 * Test Description: Fleet Map link working
	 * Test Script: - On Home Page, click Fleet Map
	 * Expected Results: - User is navigated to Fleet Map page
	 */
	@Test
	public void HOME014() {
		System.out.println("\nRunning - HOME014 - Test Description: Fleet Map link working\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);		
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		
		homePage.getLinkFleetMap().click();
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(testSetup.getBaseUrl() + FLEETMAP));
	}
	
	/**
	 * Test Case ID: HOME017
	 * Test Description: Verify pagination for Driving Surveys and Surveyors section present on Dashboard
	 * Test Script: - On home page, in Dashboard section select 10, 25, 50, 100 records
	 * Expected Results: - Specified number of records will be listed in the table
     *                   - User can navigate to subsequent pages
	 */
	@Test
	public void HOME017() {
		List<String> tagList = new ArrayList<String>();
		int totalNum = 0;
		
		System.out.println("\nRunning - HOME017 - Test Description: Verify pagination for Driving Surveys and Surveyors section present on Dashboard\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String numTextString = homePage.getLabelRDSPagesInfo().getText(); 
		String[] strList = numTextString.split(" ");
		totalNum = Integer.parseInt(strList[5]); 
		
		homePage.setPagination("10");
		tagList = homePage.getTagListRecentDrivingSurveys();
		assertTrue(tagList.size() <= 10);
		
		if (totalNum > 10) {
			homePage.setPagination("25");
			tagList = homePage.getTagListRecentDrivingSurveys();
			assertTrue(tagList.size() > 10 && tagList.size() <= 25 );
		}
		
		if (totalNum > 25) {
			homePage.setPagination("50");
			tagList = homePage.getTagListRecentDrivingSurveys();
			assertTrue(tagList.size() > 25 && tagList.size() <= 50);
		}
		
		if (totalNum > 50) {
			homePage.setPagination("100");
			tagList = homePage.getTagListRecentDrivingSurveys();
			assertTrue(tagList.size() > 50 && tagList.size() <= 100);
		}
		
		homePage.open();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		homePage.setPagination("10");
		String pageNum2LinkXPath = homePage.getPageNumLinkBaseXPath() + "/a[2]";
		WebElement pageNum2Link = driver.findElement(By.xpath(pageNum2LinkXPath)); 
		
		if (pageNum2Link.isEnabled() && homePage.getBtnPageNext().isEnabled()) {
			homePage.getBtnPageNext().click();
			
			tagList = homePage.getTagListRecentDrivingSurveys();
			
			pageNum2Link = driver.findElement(By.xpath(pageNum2LinkXPath));
			assertTrue(pageNum2Link.getText().equalsIgnoreCase("2"));
			assertTrue(tagList.size() <= 10);
		}
	}
}
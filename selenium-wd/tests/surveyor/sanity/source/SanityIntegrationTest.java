/**
 * 
 */
package surveyor.sanity.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASE;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASELOC;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASESUR;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMERNAMEPREFIX;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEDR;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEUA;
import static surveyor.scommon.source.SurveyorConstants.EULASTRING;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.RGBNAMEBASE;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSUTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUATAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICUATAG;
import static surveyor.scommon.source.SurveyorConstants.STRFEEDBACK;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECTUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPTUA;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorP3URLs.DRIVINGSURVEYS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsAdminPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageRefGasBottlesAdminPage;
import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.ManageSurveyorAdminPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.ManageUsersAdminPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SendFeedbackPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.UserFeedbackAdminPage;
import surveyor.scommon.source.UserFeedbackPage;

/**
 * @author zlu
 *
 */
public class SanityIntegrationTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ComplianceReportsPage complianceReportsPage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageLocationsAdminPage manageLocationsAdminPage;
	private static ManageSurveyorPage manageSurveyorPage;
	private static ManageSurveyorAdminPage manageSurveyorAdminPage;
	private static ManageUsersPage manageUsersPage;
	private static ManageUsersAdminPage manageUsersAdminPage;
	private static ManageRefGasBottlesPage manageRefGasBottlesPage;
	private static ManageRefGasBottlesAdminPage manageRefGasBottlesAdminPage;
	private static MeasurementSessionsPage measurementSessionsPage;
	private static UserFeedbackPage userFeedbackPage;
	private static UserFeedbackAdminPage userFeedbackAdminPage;
	
	private static List<String> strListTagCus = null;
	private static List<String> strListTagPic = null;
	private static List<String> strListTagCusDr = null;

	@BeforeClass
	public static void SetupSanityIntegrationTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  complianceReportsPage);
		
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);
		
		manageLocationsAdminPage = new ManageLocationsAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsAdminPage);
		
		manageSurveyorPage = new ManageSurveyorPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorPage);
		
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		
		manageUsersAdminPage = new ManageUsersAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersAdminPage);
		
		manageRefGasBottlesPage = new ManageRefGasBottlesPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  manageRefGasBottlesPage);
		
		manageRefGasBottlesAdminPage = new ManageRefGasBottlesAdminPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  manageRefGasBottlesAdminPage);
		
		manageSurveyorAdminPage = new ManageSurveyorAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorAdminPage);
		
		measurementSessionsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  measurementSessionsPage);
		
		userFeedbackPage = new UserFeedbackPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  userFeedbackPage);		
		
		userFeedbackAdminPage = new UserFeedbackAdminPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  userFeedbackAdminPage);		
		
		strListTagCus = new ArrayList<String>();
		strListTagPic = new ArrayList<String>();
		
		strListTagCusDr = new ArrayList<String>();
		strListTagCusDr.add(SQACUSDRTAG);
		
		strListTagCus.add(SQACUSUATAG);
		strListTagCus.add(SQACUSSUTAG);
		strListTagCus.add(SQACUSDRTAG);
		
		strListTagPic.add(SQAPICADTAG);
		strListTagPic.add(SQAPICUATAG);
		strListTagPic.add(SQAPICSUTAG);
		strListTagPic.add(SQAPICDRTAG);		
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		homePage.open();
		
		if (!driver.getTitle().equalsIgnoreCase("Login"))
			homePage.logout();
		
		driver.quit();		
	}	
	
	/**
	 * Test Case ID: ACLV000A
	 * Test Description: Check ACLV for customer user with Utility Administrator role
	 * 
	 */
	@Test
	public void ACLV000A() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "aclv000a";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + REGBASEUSERNAME;
		
		System.out.println("\nRunning ACLV000A - Test Description: Check ACLV for customer user with Utility Administrator role");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName));
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA);
		
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		ManageCustomersPage cusPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  cusPage);
		
		cusPage.open();
		cusPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();
		
		assertTrue(homePage.checkIfAtHomePage());
		
		assertTrue(homePage.checkVisitilityForCusUA(userName));
		
		homePage.logout();
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
	 * Test Case ID: RPT000I
	 * Test Description: Create a compliance report by Administrator with more general options 
	 * 
	 */	
	@Test
	public void RPT000I() {
		String rptTitle = PICDFADMIN + " " + testSetup.getRandomNumber() + "RPT000I";
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
		
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "3", listBoundary, "", SQAPICDRTAG, viewList);
		complianceReportsPage.addNewReport(rpt);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser()))) {
			assertTrue(complianceReportsPage.findExistingReport(rptTitle, testSetup.getLoginUser()));
		}
		else
			fail("\nTestcase RPT000I failed.\n");
		
		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	
	/**
	 * Test Case ID: ADM001
	 * Test Description: Adding Customer
	 * 
	 */
	@Test
	public void ADM001() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "ADM001";
		String eula = customerName + ": " + EULASTRING;
		
		System.out.println("\nRunning ADM001 - Test Description: Adding Customer");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		

		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName));
	}
	
	/**
	 * Test Case ID: MLAP000A
	 * Test Description: Adding a Customer by Administrator then add Location by Customer Utility Administrator
	 * 
	 */
	@Test
	public void MLAP000A() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "mlap000a";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + "_ua" + REGBASEUSERNAME;
		String locationName = customerName + "Loc";
		
		System.out.println("\nRunning MLAP000A - Test Description: Adding a Customer by Administrator then add Location by Customer Utility Administrator");
	
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA);
		
		manageUsersPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		manageLocationsAdminPage.open();
		manageLocationsAdminPage.addNewLocation(locationName);
		
		assertTrue(manageLocationsAdminPage.findExistingLocation(customerName, locationName));
	}
	
	/**
	 * Test Case ID: ADM004
	 * Test Description: Adding Location
	 * 
	 */
	@Test
	public void ADM004() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "ADM004";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		
		System.out.println("\nRunning ADM004 - Test Description: Adding Location");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
	
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();			
		manageLocationsPage.addNewLocation(locationName,  customerName);
		
		assertTrue(manageLocationsPage.findExistingLocation(customerName, locationName));
	}
	
	/**
	 * Test Case ID: MRGBP000C
	 * Test Description: Adding a Ref Gas Bottle to a customer surveyor by Customer User with Utility Administrator Role
	 * 
	 */
	@Test
	public void MRGBP000C() {
		String strItemNumber = "Reg" + RGBNAMEBASE + testSetup.getRandomNumber() + "MRGBP000C";
		
		System.out.println("\nRunning MRGBP000C - Test Description: Adding a Ref Gas Bottle to a customer surveyor by Customer User with Utility Administrator Role");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.addNewRefGasBottle(strItemNumber, testSetup.getRandomNumber(), "-32", CUSNAMEBASE, CUSNAMEBASELOC, CUSNAMEBASESUR);
		
		assertTrue(manageRefGasBottlesAdminPage.findExistingRefGasBottle(strItemNumber, CUSNAMEBASESUR, CUSNAMEBASELOC));
	}
	
	/**
	 * Test Case ID: MRGBP000A
	 * Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro Default Administrator
	 * 
	 */
	@Test
	public void MRGBP000A() {
		String strItemNumber = "Reg" + RGBNAMEBASE + testSetup.getRandomNumber() + "MRGBP000A";
		
		System.out.println("\nRunning MRGBP000A - Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro Default Administrator");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strItemNumber, testSetup.getRandomNumber(), "-32", CUSNAMEBASE, CUSNAMEBASELOC, CUSNAMEBASESUR);
		
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strItemNumber, CUSNAMEBASESUR));
	}
	
	/**
	 * Test Case ID: CUSTADM019
	 * Test Description: edit surveyor
	 * Test Script: - On Home Page, click Administration -> Manage Customer's Surveyors
					- Click on Edit link
					- Modify Surveyor details and click OK
	 * Expected Results: - Customer Admin is navigated to Manage Customer's Surveyors page and modified Surveyor details are present in the table
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement: editing a surveyor to have it associate to a different customer location is covered in CUSTADM020
	 */	
	@Test
	public void CUSTADM019() {
		//String customerName = SQACUS;
		String locationName = SQACUSLOC;
		String surveyorName = SQACUSLOCSUR + testSetup.getRandomNumber() + "custadm019";
		String surveyorNameNew = surveyorName + "New";
		
		System.out.println("\nRunning - CUSTADM019 - Test Description: edit surveyor\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, SQACUS + " - " + SQACUSLOC);
		manageSurveyorPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);		
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkManageSurveyors().click();
		
		manageSurveyorAdminPage.editExistingSurveyor(locationName, surveyorName, surveyorNameNew);
		
		assertTrue(manageSurveyorAdminPage.findExistingSurveyor(locationName, surveyorNameNew));
	}

	/**
	 * Test Case ID: ADM007
	 * Test Description: Adding Surveyor
	 * 
	 */
	@Test
	public void ADM007() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "ADM007";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		
		System.out.println("\nRunning ADM007...");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName);
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName));
	}
	
	/**
	 * Test Case ID: CUSTADM001
	 * Test Description: add user
	 * Test Script: - On Home Page, click Administration -> Manage Users
				    - Click on 'Add New User' button
					- Provide required user details and click OK [E1]
					- Login to p-cubed with newly created user credentials [E2]
	 * Expected Results: E1. Admin user is navigated to Manage Users page and new user details are present in the table
						 E2. New user is able to login the application successfully and navigated to valid page
     * Future Improvement: 1. Create user by Default "Administrator", Picarro Administrator and Utility Administrator from both Picarro and customer
     * 					   2. Create users with different roles 
     * 					   3. Create users with different TimeZone
     * 					   4. Create users with the status "disabled" (non default)
	 */	
	@Test
	public void CUSTADM001() {
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm001" + REGBASEUSERNAME;
		
		System.out.println("\nRunning - CUSTADM001 - add user\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, CUSUSERROLEDR, TIMEZONECTUA);
		
		assertTrue(manageUsersAdminPage.findExistingUser(SQACUS, userName, CUSUSERROLEDR));
		manageUsersAdminPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
	
		assertTrue(homePage.checkIfAtHomePage());
	}
	
	/**
	 * Test Case ID: ADM013
	 * Test Description: Adding a customer and a User with Utility Administrator role
	 * 
	 */	
	@Test
	public void ADM013() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "adm013";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + REGBASEUSERNAME;
		
		System.out.println("\nRunning ADM013 - Test Description: Adding a customer and a User with Utility Administrator role");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA);
		
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		
		assertTrue(homePage.checkIfAtHomePage());
	}
	
	/**
	 * Test Case ID: HOME015
	 * Test Description: Recent Driving Surveys Section present on Dashboard should display latest surveys
	 * Test Script: - Login and verify recent driving surveys section
	 * Expected Results: - User should be able to see recent driving surveys
     * Future Improvement: - Visibility check, a customer user should be able to see all surveys generated by the other customer users with different roles 
	 */
	@Test
	public void HOME015() {
		System.out.println("\nRunning - HOME015 - Test Description: Recent Driving Surveys Section present on Dashboard should display latest surveys\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		
		MeasurementSessionsPage msp = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  msp);
		
		homePage.getLinkDrivingSurveys().click();
		
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(testSetup.getBaseUrl() + DRIVINGSURVEYS));
		
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(msp.getStrPageURL()));
		
		assertTrue(msp.getDriverSurveysByTag(SQACUSDR).containsAll(strListTagCusDr));
	}
	
	/**
	 * Test Case ID: CUSTADM018
	 * Test Description: Customer Admin can view the feedback notes sent by that customer's user 
	 * Test Script: - Login as specified customer's user (e.g. PGE's user - driver, supervisor)
					- On Home Page, click on Send Feedback
					- Provide feedback text and click Send button
					- Login as specified customer's Administrator (e.g. PGE Admin)
					- On Home Page, click Administration -> View User Feedback
	 * Expected Results: - User friendly message should be displayed. ""Successfully sent Feedback to Picarro Administrator"
						 - Feedback notes, date, customer and user name details are present in the list
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM018() {
		ManageUsersAdminPage manageUsersAdminPage = new ManageUsersAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersAdminPage);
		
		SendFeedbackPage sendFeedbackPage = new SendFeedbackPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, sendFeedbackPage);
		
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm018" + REGBASEUSERNAME;
		String strFeedback = "Feedback sent by: " + userName;
		
		System.out.println("\nRunning - CUSTADM018 - Test Description: Customer Admin can view the feedback notes sent by that customer's user\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, CUSUSERROLEDR, TIMEZONEPTUA, true);
		manageUsersAdminPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.getLinkSendFB().click();
		sendFeedbackPage.sendFeedback(userName, strFeedback);
		sendFeedbackPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		userFeedbackAdminPage.open();
		assertTrue(userFeedbackAdminPage.getUserFeedbackNote(customerName, userName).equalsIgnoreCase(strFeedback));
	}
	
	/**
	 * Test Case ID: UFBP000A
	 * Test Description: Sending feedback from Picarro Default Administrator
	 * 
	 */
	@Test
	public void UFBP000A() {
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK + " - UFBP000A";
		boolean bFound = false;
		
		System.out.println("\nRunning UFBP000A - Test Description: Sending feedback from Picarro Default Administrator");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		userFeedbackPage.sendFeedback(testSetup.getLoginUser(),  feedbackNote);
		
		userFeedbackPage.open();
		List<String> list = userFeedbackPage.getUserFeedbackNotes("Picarro",  testSetup.getLoginUser());
		
		for (String note : list) {
			if (note.equalsIgnoreCase(feedbackNote)) {
				bFound = true;
				break;
			}
		}
		
		if (!bFound)
			fail("\nTese case UFBP000A failed.\n");
	}	
}
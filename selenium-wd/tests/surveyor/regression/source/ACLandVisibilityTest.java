/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.source.Log;

/**
 * @author zlu
 *
 */
public class ACLandVisibilityTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ComplianceReportsPage complianceReportsPage;
	private static HomePage homePage;
	
	@BeforeClass
	public static void setupACLandVisibilityTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
	}
	
	/**
	 * Test Case ID: TC35_CheckACLVCustomerUser_DriverRole
	 * Test Description: Check ACLV for customer user with Driver role
	 * 
	 */
	@Test
	public void TC35_CheckACLVCustomerUser_DriverRole() {
		String customerName = SQACUS;
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = customerName + " - " + SQACUSLOC;
		
		Log.info("\nRunning TC35_CheckACLVCustomerUser_DriverRole - Test Description: Check ACLV for customer user with Driver role");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR, location);
		
		ManageCustomersPage manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		manageCustomersPage.open();
		manageCustomersPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisibilityForCusDR(userName));
		homePage.logout();				
	}
	
	/**
	 * Test Case ID: TC36_CheckACLVCustomerUser_SupervisorRole
	 * Test Description: Check ACLV for customer user with Supervisor role
	 * 
	 */
	@Test
	public void TC36_CheckACLVCustomerUser_SupervisorRole() {
		String eula = SQACUS + ": " + EULASTRING;
		String userName = SQACUS + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUS + " - " + SQACUSLOC;
		
		Log.info("\nRunning TC36_CheckACLVCustomerUser_SupervisorRole - Test Description: Check ACLV for customer user with Supervisor role");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD, CUSUSERROLESU,location);
		
		ManageCustomersPage manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		manageCustomersPage.open();
		manageCustomersPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisibilityForCusSU(userName));
		homePage.logout();				
	}
	
	//@Test
		public void TC36_CheckReportLink_SupervisorRole() {	
			String rptTitle = "TC36 Report" + testSetup.getRandomNumber();
			Log.info("\nRunning TC36_CheckReportLink_SupervisorRole - Test Description: Report link is working and user is able to view report's menu");		
			loginPage.open();
			loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
			complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver, complianceReportsPage);
			complianceReportsPage.open();

			List<String> listBoundary = new ArrayList<String>();
			listBoundary.add(IMGMAPHEIGHT);
			listBoundary.add(IMGMAPWIDTH);
			listBoundary.add(RNELAT);
			listBoundary.add(RNELON);
			listBoundary.add(RSWLAT);
			listBoundary.add(RSWLON);

			List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
			Map<String, String> tableMap = new HashMap<String, String>();

			tableMap.put(KEYINDTB, "1");
			tableMap.put(KEYISOANA, "1");
			tableMap.put(KEYPCA, "0");
			tableMap.put(KEYPCRA, "0");
			tableMap.put(KEYASSETCASTIRON, "0");
			tableMap.put(KEYASSETCOPPER, "0");
			tableMap.put(KEYASSETOTHERPLASTIC, "0");
			tableMap.put(KEYASSETPEPLASTIC, "0");
			tableMap.put(KEYASSETPROTECTEDSTEEL, "0");
			tableMap.put(KEYASSETUNPROTECTEDSTEEL, "0");
			tableMap.put(KEYBOUNDARYDISTRICT, "0");
			tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
			tablesList.add(tableMap);

			List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
			Map<String, String> viewMap = new HashMap<String, String>();
			
			viewMap.put(KEYVIEWNAME, "First View");
			viewMap.put(KEYLISA, "1");
			viewMap.put(KEYFOV, "1");
			viewMap.put(KEYBREADCRUMB, "1");
			viewMap.put(KEYINDICATIONS, "1");
			viewMap.put(KEYISOTOPICCAPTURE, "0");
			viewMap.put(KEYANNOTATION, "0");
			viewMap.put(KEYGAPS, "0");
			viewMap.put(KEYASSETS, "0");
			viewMap.put(KEYBOUNDARIES, "0");
			viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

			viewList.add(viewMap);

			ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", CUSDRVSTDTAG, "", "", viewList, SurveyModeFilter.Standard);

			complianceReportsPage.addNewReport(rpt);

			if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA))) {
				if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
					assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSUA));
				} else
					fail("\nTC36__CheckReportLink_SupervisorRole: Generate Report as Customer Admin failed.\n");
			} else
				fail("\nTC36__CheckReportLink_SupervisorRole: Generate Report as Customer Admin failed.\n");

			complianceReportsPage.open();
			complianceReportsPage.logout();	
			
			complianceReportsPage.login(SQACUSSU, USERPASSWORD);
			assertTrue(complianceReportsPage.getLinkReportMenu().isDisplayed());		
			complianceReportsPage.getLinkReportMenu().click();
			complianceReportsPage.waitForPageToLoad();
			assertTrue(complianceReportsPage.getLinkComplianceReportMenu().isDisplayed());
			complianceReportsPage.getLinkComplianceReportMenu().click();
			complianceReportsPage.waitForPageLoad();
			assertTrue(complianceReportsPage.getBtnNewComplianceRpt().isDisplayed());
			assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSUA));
			if (complianceReportsPage.deleteReport(rptTitle, SQACUSUA))
				assertTrue(!(complianceReportsPage.findReportbySearch(rptTitle, SQACUSUA)));
			else
				fail("\nTC36__CheckReportLink_SupervisorRole: Generate Report as Customer Supervisor failed.\n");

			complianceReportsPage.open();
			complianceReportsPage.logout();
		
		}
		
		/**
		 * Test Case ID: TC36_CheckUserLink_ReleaseNotes
		 * Test Description: Release Notes link is working and can download Release Notes
		 * 
		 */
		@Test
		public void TC36_CheckUserLink_ReleaseNotes() {	
			String rptTitle = "TC36 Report" + testSetup.getRandomNumber();
			Log.info("\nRunning TC36_CheckReportLink_SupervisorRole - Test Description: Release Notes link is working and can download Release Notes");		
			loginPage.open();
			loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);
			homePage.open();
			homePage.waitForPageLoad();		
			assertTrue(homePage.checkIfAtHomePage());
			assertTrue(homePage.checkVisibilityForCusSU(SQACUSSU));
			//homePage.getDropDownLoginUser().click();
			//homePage.getLinkReleaseNotes().click();
			ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		   driver.switchTo().window(newTab.get(0));
		}

	/**
	 * Test Case ID: TC37_CheckACLVCustomerUser_UtilityAdminRole
	 * Test Description: Check ACLV for customer user with Utility Administrator role
	 * 
	 */
	@Test
	public void TC37_CheckACLVCustomerUser_UtilityAdminRole() {
		String eula = SQACUS + ": " + EULASTRING;
		String userName = SQACUS + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUS + " - " + SQACUSLOC;
		
		Log.info("\nRunning TC37_CheckACLVCustomerUser_UtilityAdminRole - Test Description: Check ACLV for customer user with Utility Administrator role");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD, CUSUSERROLEUA, location);
		
		ManageCustomersPage manageCustomerPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomerPage);
		
		manageCustomerPage.open();
		manageCustomerPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();		
		assertTrue(homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisibilityForCusUA(userName));
		homePage.logout();
	}

	/**
	 * Test Case ID: TC38_CheckACLV_PicAdminRole
	 * Test Description: Check ACLV for Picarro Administrator role, non-default Administrator account
	 * 
	 */
	@Test
	public void TC38_CheckACLV_PicAdminRole() {
		String userName = PICNAMEPREFIX + "ad" + testSetup.getRandomNumber() + REGBASEPICUSERNAME;
		
		Log.info("\nRunning TC38_CheckACLV_PicAdminRole - Test Description: Check ACLV for Picarro Administrator role, non-default Administrator account\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();		
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, USERROLEADMIN);		
		if (!manageUsersPage.findExistingUser("Picarro", userName))
			fail("\nTC38: failed to create a non-default Picarro Administrator user.\n");		
		manageUsersPage.logout();
			
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);		
		
		homePage.open();		
		assertTrue(homePage.checkVisibilityForPicarroAdministrator(userName));		
		homePage.logout();
	}
	
	/**
	 * Test Case ID: TC40_CheckACLV_PicSupportRole
	 * Test Description: Check ACLV for Picarro user with Picarro Support role
	 * 
	 */
	@Test
	public void TC40_CheckACLV_PicSupportRole() {
		String userName = PICNAMEPREFIX + "su" + testSetup.getRandomNumber() + REGBASEPICUSERNAME;
		
		Log.info("\nRunning TC40_CheckACLV_PicSupportRole - Test Description: Check ACLV for Picarro user with Picarro Support role\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		
		Log.info("Creating Picarro support user with username-" + userName);
		
		manageUsersPage.open();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, PICUSERROLESUP);		
		if (!manageUsersPage.findExistingUser("Picarro", userName))
			fail("\nTC40: failed to create a Picarro user with Picarro Support role.\n");		
		manageUsersPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();		
		assertTrue(homePage.checkVisibilityForPicarroSUP(userName));		
		homePage.logout();
	}
}
/**
 * 
 */
package surveyor.ondemand.source;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageAnalyzersPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageCusLocSurAnaUserTest extends SurveyorBaseTest {
	//public static final String CUSTOMER = "CusA";
	//public static final String LOCATION = "Loc";
	public static final String CUSTOMER = "Picarro";
	public static final String LOCATION = "Default";
	
	public static final String SURVEYOR = "Sur";
	public static final String ANALYZER = "A";
	public static final String ANALYZERSHAREDKEY = "sqa#Picarro$0";	
	
	//public static final String USER = "@email.com";
	public static final String USER = "@picarro.com";
	public static final String PASSWORD = "sqa#Picarro$0";
	
//	public static final int CUSNUM = 5;
//	public static final int LOCNUM = 5;
//	public static final int SURNUM = 10;
//	public static final int ANANUM = 1;
//	public static final int UNUM = 100;
	
	public static final int CUSNUM = 1;
	public static final int LOCNUM = 0;
	public static final int SURNUM = 0;
	public static final int ANANUM = 0;
	public static final int UNUM = 110;	
	
	private static ManageCustomersPage manageCustomersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageSurveyorPage manageSurveyorPage; 
	private static ManageAnalyzersPage manageAnalyzersPage;
	private static ManageUsersPage manageUsersPage;
	
	public ManageCusLocSurAnaUserTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);
		
		manageSurveyorPage = new ManageSurveyorPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorPage);		
		
		manageAnalyzersPage = new ManageAnalyzersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageAnalyzersPage);
		
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);		
	}

	/**
	 * Test Case: Adding Customer, Location, Surveyor, Analyzer and User
	 */	
	@Test
	public void addingCusLocSurAnaUser() {
		System.out.println("\nRunning ManageCusLocSurAnaUserTest_addingCusLocSurAnaUser...");
		
		try {
			//manageCustomersPage.open();
			//manageCustomersPage.addNewCustomer(CUSTOMER, CUSTOMER + " eula");
			
			String strLocationName = "";
			String strSurveyorName = "";
			String strAnalyzerName = "";
			String strUserName = "";
			
			for (int i = 0; i < LOCNUM; i++) {
				manageLocationsPage.open();
				strLocationName = CUSTOMER + "_" + LOCATION + Integer.toString(i);
				manageLocationsPage.addNewLocation(strLocationName, CUSTOMER);
				
				for (int ii = 0; ii < SURNUM; ii++) {
					manageSurveyorPage.open();
					strSurveyorName = strLocationName + "_" + SURVEYOR + Integer.toString(ii);
					manageSurveyorPage.addNewSurveyor(strSurveyorName, CUSTOMER + " - " + strLocationName);
					
					manageAnalyzersPage.open();
					strAnalyzerName = strSurveyorName + ANALYZER;
					manageAnalyzersPage.addNewAnalyzer(strAnalyzerName, ANALYZERSHAREDKEY, CUSTOMER + " - " + strLocationName + " - " + strSurveyorName);
				}
			}
					
			for (int i = 0; i < UNUM; i++) {
				manageUsersPage.open();
				strUserName = CUSTOMER + Integer.toString(i) + USER;
				manageUsersPage.addNewUser(CUSTOMER, strUserName, PASSWORD, "Administrator");
			}
		}
		catch (Exception e) {
			System.out.println("\nException on test case \"ManageCusLocSurAnaUserTest_addingCusLocSurAnaUser\": " + e.getMessage());		
		}
	}
}
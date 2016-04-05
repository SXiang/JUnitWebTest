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
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import common.source.Log;
import common.source.TestSetup;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageUsersPageTest extends SurveyorBaseTest {
	private static ManageUsersPage manageUsersPage;
	
	public ManageUsersPageTest() {
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);		
	}

	/**
	 * Test Case: Adding new users
	 */
	@Test
	public void manageUsersPage_AddNewUsers() {
		Log.info("\nRunning manageUsersPage_AddNewUsers...");
		
		try {
			manageUsersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			String strCustomerName = "";
			String strUserName = "";			
			
			for (int i = 0; i < CUSTOMERNUM; i++) {
				if (i >= 0 && i < 10)
					strCustomerName = BASECUSTOMERNAME + "00" + Integer.toString(i);
				else if (i >= 10 && i < 100)
					strCustomerName = BASECUSTOMERNAME + "0" + Integer.toString(i);
				else
					strCustomerName = Integer.toString(i);
				
				for (int ii = 0; ii < USERNUM; ii++) {
					if (ii >= 0 && ii < 10)
						strUserName = strCustomerName + "_" + "00" + Integer.toString(ii) + BASEUSERNAME;
					else if (ii >= 10 && ii < 100)
						strUserName = strCustomerName + "_" + "0" + Integer.toString(ii) + BASEUSERNAME;
					else
						strUserName = strCustomerName + "_" + Integer.toString(ii) + BASEUSERNAME;
					
					//manageUsersPage.addNewUser(strCustomerName, strUserName, USERPASSWORD, "Administrator");
					
					//???
					
				}
			}
		}
		catch (Exception e) {
			Log.info("Exception on test case \"manageUsersPage_AddNewUsers\": " + e.getMessage());
		}
	}
}
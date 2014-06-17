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
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageCustomersPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	
	public ManageCustomersPageTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);		
	}

	/**
	 * Test Case: Adding new customers
	 */
	@Test
	public void manageCustomersPage_AddNewCustomers() {
		System.out.println("\nRunning manageCustomersPage_AddNewCustomers...");
	
		try {
			manageCustomersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			String strCustomerName = "";
			
			for (int i = 0; i < CUSTOMERNUM; i++) {
				if (i >= 0 && i < 10) {
					strCustomerName = BASECUSTOMERNAME + "00" + Integer.toString(i);
				}
				else if (i >= 10 && i < 100) {
					strCustomerName = BASECUSTOMERNAME + "0" + Integer.toString(i);					
				}
				else {
					strCustomerName = BASECUSTOMERNAME + Integer.toString(i);
				}
				
				manageCustomersPage.addNewCustomer(strCustomerName, strCustomerName + " eula");
			}
		}
		catch (Exception e) {
			System.out.println("Exception on test case \"manageCustomersPage_AddNewCustomers\": " + e.getMessage());
		}
	}
}
/**
 * 
 */
package surveyor.ondemand.source;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import common.source.Log;
import static surveyor.scommon.source.SurveyorConstants.*;
/**
 * @author zlu
 *
 */
public class ManageCustomersPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	
	public ManageCustomersPageTest() {
		manageCustomersPage = new ManageCustomersPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageCustomersPage);		
	}

	/**
	 * Test Case: Adding new customers
	 */
	@Test
	public void manageCustomersPage_AddNewCustomers() {
		Log.info("\nRunning manageCustomersPage_AddNewCustomers...");
	
		try {
			manageCustomersPage.open();
			
			if (isDebug())
				getTestSetup().slowdownInSeconds(3);
			
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
			Log.info("Exception on test case \"manageCustomersPage_AddNewCustomers\": " + e.getMessage());
		}
	}
}
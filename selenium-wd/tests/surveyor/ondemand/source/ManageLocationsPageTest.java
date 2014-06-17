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
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.SurveyorBaseTest;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageLocationsPageTest extends SurveyorBaseTest { 
	private static ManageLocationsPage manageLocationsPage;
	
	public ManageLocationsPageTest() {
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);		
	}
	
	/**
	 * Test Case: Adding new locations
	 */	
	@Test
	public void manageLocationsPage_AddNewLocations() {
		System.out.println("\nRunning manageLocationsPage_AddNewLocations...");
		
		try {
			manageLocationsPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			String strCustomerName = "";
			String strLocation = "";
			
			for (int i = 0; i < CUSTOMERNUM; i++) {
				if (i >= 0 && i < 10)
					strCustomerName = BASECUSTOMERNAME + "00" + Integer.toString(i);
				else if (i >= 10 && i < 100)
					strCustomerName = BASECUSTOMERNAME + "0" + Integer.toString(i);
				else 
					strCustomerName = BASECUSTOMERNAME + Integer.toString(i);
				
				for (int ii = 0; ii < LOCATIONNUM; ii++) {
					if (ii >= 0 && ii < 10)
						strLocation = strCustomerName + "_" + "00" + Integer.toString(ii) + BASELOCATIONNAME;
					else if (ii >= 10 && ii < 100)
						strLocation = strCustomerName + "_" + "0" + Integer.toString(ii) + BASELOCATIONNAME;
					else
						strLocation = strCustomerName + "_" + Integer.toString(ii) + BASELOCATIONNAME;
					
					manageLocationsPage.addNewLocation(strLocation, strCustomerName);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Exception on test case \"manageLocationsPage_AddNewLocations\": " + e.getMessage());
		}
	}
}
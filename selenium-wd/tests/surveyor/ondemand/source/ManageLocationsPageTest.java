/**
 * 
 */
package surveyor.ondemand.source;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.SurveyorBaseTest;
import common.source.Log;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageLocationsPageTest extends SurveyorBaseTest { 
	private static ManageLocationsPage manageLocationsPage;
	
	public ManageLocationsPageTest() {
		manageLocationsPage = new ManageLocationsPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageLocationsPage);		
	}
	
	/**
	 * Test Case: Adding new locations
	 */	
	@Test
	public void manageLocationsPage_AddNewLocations() {
		Log.info("\nRunning manageLocationsPage_AddNewLocations...");
		
		try {
			manageLocationsPage.open();
			
			if (isDebug())
				getTestSetup().slowdownInSeconds(3);
			
			String strCustomerName = "";
			String strLocation = "";
			String cityName="Santa clara";
			
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
					
					manageLocationsPage.addNewLocation(strLocation, strCustomerName,cityName);
				}
			}
		}
		catch (Exception e) {
			Log.info("Exception on test case \"manageLocationsPage_AddNewLocations\": " + e.getMessage());
		}
	}
}
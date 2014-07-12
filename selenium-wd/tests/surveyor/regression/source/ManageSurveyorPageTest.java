/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageSurveyorPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageSurveyorPage manageSurveyorPage;
	
	public ManageSurveyorPageTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);
		
		manageSurveyorPage = new ManageSurveyorPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorPage);
	}
	
	/**
	 * Test Case ID: ADM00??
	 * Test Description: ??
	 * 
	 */
	@Test
	public void ADM00() {
		try {
			
		}
		catch (Exception e) {
			
		}
	}
}
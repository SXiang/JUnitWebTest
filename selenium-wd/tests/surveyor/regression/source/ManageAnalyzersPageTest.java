/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageAnalyzersPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageAnalyzersPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageSurveyorPage manageSurveyorPage;
	private static ManageAnalyzersPage manageAnalyzersPage;
	
	public ManageAnalyzersPageTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);
		
		manageSurveyorPage = new ManageSurveyorPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorPage);
		
		manageAnalyzersPage = new ManageAnalyzersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageAnalyzersPage);
	}
	
	/**
	 * Test Case ID: ADM010
	 * Test Description: Adding Analyzer
	 * 
	 */	
	@Test
	public void ADM010() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "ADM010";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		String analyzerName = surveyorName + "Ana";
		
		System.out.println("\nRunning ADM010 - Test Description: Adding Analyzer");
		
		manageCustomersPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageLocationsPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageLocationsPage.addNewLocation(locationName, customerName);
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageSurveyorPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageAnalyzersPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageAnalyzersPage.addNewAnalyzer(analyzerName, ANALYZERSHAREDKEY, surveyorName, customerName, locationName);
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		System.out.format("\ncustomerName: %s, locationName: %s, surveyorName: %s, analyzerName: %s", customerName, locationName, surveyorName, analyzerName);
		
		assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName));		
	}
}
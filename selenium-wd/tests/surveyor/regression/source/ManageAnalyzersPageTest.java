/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageAnalyzersPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageAnalyzersPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageSurveyorPage manageSurveyorPage;
	private static ManageAnalyzersPage manageAnalyzersPage;
	
	@BeforeClass
	public static void setupManageAnalyzersPageTest() {
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		
		manageLocationsPage.addNewLocation(locationName, customerName);
		
		manageSurveyorPage.open();
		
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		manageAnalyzersPage.open();
		
		manageAnalyzersPage.addNewAnalyzer(analyzerName, ANALYZERSHAREDKEY, surveyorName, customerName, locationName);
		
		assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName));		
	}
	
	/**
	 * Test Case ID: MAP000A
	 * Test Description: Adding Analyzer for Picarro by Picarro default Administrator
	 * 
	 */	
	@Test
	public void MAP000A() {
		String customerName = "Picarro";
		String locationName = customerName + "loc";
		String surveyorName = locationName + testSetup.getRandomNumber() + "sur";
		String analyzerName = surveyorName + "ana";
		
		System.out.println("\nRunning MAP000A - Test Description: Adding Analyzer for Picarro by Picarro default Administrator");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		manageLocationsPage.open();
		
		manageLocationsPage.addNewLocation(locationName, customerName);
		
		manageSurveyorPage.open();
		
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		manageAnalyzersPage.open();
		
		manageAnalyzersPage.addNewAnalyzer(analyzerName, ANALYZERSHAREDKEY, surveyorName, customerName, locationName);
		
		assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName));
	}
	
	/**
	 * Test Case ID: MAP000B
	 * Test Description: Editing Analyzer for Picarro, associating an analyzer to a different surveyor, by Picarro default Administrator
	 * 
	 */	
	@Test
	public void MAP000B() {
		String customerName = "Picarro";
		String locationName = customerName + testSetup.getRandomNumber() + "loc";
		String surveyorName = locationName + "sur";
		String surveyorNameNew = locationName + "surnew";
		String analyzerName = surveyorName + "ana";
		
		System.out.println("\nRunning MAP000B - Test Description: Editing Analyzer for Picarro, changing the Analyzer Name, by Picarro default Administrator");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		manageLocationsPage.open();

		manageLocationsPage.addNewLocation(locationName, customerName);
		
		manageSurveyorPage.open();
		
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		manageSurveyorPage.addNewSurveyor(surveyorNameNew, locationName, customerName);
		
		manageAnalyzersPage.open();
		
		manageAnalyzersPage.addNewAnalyzer(analyzerName, ANALYZERSHAREDKEY, surveyorName, customerName, locationName);
		
		if (manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName))
			manageAnalyzersPage.associateAnalyzerToOtherSurveyor(customerName, locationName, surveyorName, analyzerName, 
					customerName + " - " + locationName + " - " + surveyorNameNew );
		
		assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorNameNew, analyzerName));		
	}
	
	/**
	 * Test Case ID: MAP000C
	 * Test Description: Adding Analyzer for Picarro by Picarro user with Administrator Role
	 * 
	 */	
	@Test
	public void MAP000C() {
		String customerName = "Picarro";
		String locationName = customerName + "loc";
		String surveyorName = locationName + testSetup.getRandomNumber() + "sur";
		String analyzerName = surveyorName + "ana";
		
		System.out.println("\nRunning MAP000C - Test Description: Adding Analyzer for Picarro by Picarro user with Administrator Role");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);
		
		manageLocationsPage.open();
		
		manageLocationsPage.addNewLocation(locationName, customerName);
		
		manageSurveyorPage.open();
		
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		manageAnalyzersPage.open();
		
		manageAnalyzersPage.addNewAnalyzer(analyzerName, ANALYZERSHAREDKEY, surveyorName, customerName, locationName);
		
		assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName));
	}
	
	/**
	 * Test Case ID: MAP000D
	 * Test Description: Editing Analyzer for Picarro, associating an analyzer to a different surveyor, by Picarro user with Administrator Role
	 * 
	 */	
	@Test
	public void MAP000D() {
		String customerName = "Picarro";
		String locationName = customerName + testSetup.getRandomNumber() + "loc" + "MAP000D";
		String surveyorName = locationName + "sur";
		String surveyorNameNew = locationName + "surnew";
		String analyzerName = surveyorName + "ana";
		
		System.out.println("\nRunning MAP000D - Test Description: Editing Analyzer for Picarro, associating an analyzer to a different surveyor, by Picarro user with Administrator Role");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);
		
		manageLocationsPage.open();
		
		manageLocationsPage.addNewLocation(locationName, customerName);
		
		manageSurveyorPage.open();
		
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		manageSurveyorPage.addNewSurveyor(surveyorNameNew, locationName, customerName);
		
		manageAnalyzersPage.open();
		
		manageAnalyzersPage.addNewAnalyzer(analyzerName, ANALYZERSHAREDKEY, surveyorName, customerName, locationName);
		
		if (manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName)) {
			if (manageAnalyzersPage.associateAnalyzerToOtherSurveyor(customerName, locationName, surveyorName, analyzerName, 
					customerName + " - " + locationName + " - " + surveyorNameNew ))
				assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorNameNew, analyzerName));
			else
				fail("\nTest case MAP000D failed\n");
		}
		else 
			fail("\nTest case MAP000D failed\n");
	}
}
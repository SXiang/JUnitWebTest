/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
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
	 * Test Case ID: TC65_AddAnalyzer_PicAdmin
	 * Test Description: Adding Analyzer for Picarro by Picarro default Administrator
	 * 
	 */	
	@Test
	public void TC65_AddAnalyzer_PicAdmin() {
		String customerName = "Picarro";
		String locationName = customerName + "loc";
		String surveyorName = locationName + testSetup.getRandomNumber() + "sur";
		String analyzerName = surveyorName + "ana";
		String cityName="Santa Clara";
		
		Log.info("\nRunning TC65_AddAnalyzer_PicAdmin - Test Description: Adding Analyzer for Picarro by Picarro default Administrator");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName ,cityName);
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		manageAnalyzersPage.open();
		manageAnalyzersPage.addNewAnalyzer(analyzerName, ANALYZERSHAREDKEY, surveyorName, customerName, locationName);
		assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName));
	}
	
	/**
	 * Test Case ID: TC67_EditAnalyzer_PicAdmin
	 * Test Description: Editing Analyzer for Picarro, associating an analyzer to a different surveyor, by Picarro default Administrator
	 * 
	 */	
	@Test
	public void TC67_EditAnalyzer_PicAdmin() {
		String customerName = "Picarro";
		String locationName = customerName + testSetup.getRandomNumber() + "loc";
		String surveyorName = locationName + "sur";
		String surveyorNameNew = locationName + "surnew";
		String analyzerName = surveyorName + "ana";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC67_EditAnalyzer_PicAdmin - Test Description: Editing Analyzer for Picarro, changing the Analyzer Name, "
				+ "by Picarro default Administrator");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName, cityName);
		
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
}
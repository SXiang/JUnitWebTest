/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.scommon.source.ManageAnalyzersPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.List;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
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
		String locationName = customerName + testSetup.getRandomNumber() + "loc";
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
 	/**
	 * Test Case ID: TC66_AddAnalyzerNonPicarroCustomer_PicAdmin
	 * Script:   	 	
	 * - On Home Page, click Picarro Administration -> Manage Analyzers
	 * - Click on 'Add New Analyzer' button
	 * - Select Surveyor Unit associated to customer other than Picarro
	 * - Provide required analyzer details shown on screen and click OK [E1]
	 * - Login with user of different Customer and navigate to Picarro Surveyors [E2]
	 * - Login with user of same Customer under which Analyzer is added and navigate to Picarro Surveyors [E3]
	 * Results: - 
	 * - E1. User is navigated to Manage Analyzers page and new analyzer entry is present in the table
	 * - E2. User is not able to see newly added Analyzer
	 * - E3. User should see the newly added Analyzer
	 */
	@Test
	public void TC66_AddAnalyzerNonPicarroCustomer_PicAdmin() {
		String customerName = "Picarro";
		String locationName = customerName + testSetup.getRandomNumber() + "loc";
		String surveyorName = locationName + testSetup.getRandomNumber() + "sur";
		String analyzerName = surveyorName + "ana";
		String cityName="Santa Clara";
		
		Log.info("\nRunning TC66_AddAnalyzerNonPicarroCustomer_PicAdmin - Test Description: add analyzer under customer other than Picarro");
		
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
		
		manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName);
		manageAnalyzersPage.associateAnalyzerToOtherSurveyor(customerName, locationName, surveyorName, analyzerName, 
					customerName + " - " + locationName + " - " + surveyorNameNew );
		manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorNameNew, analyzerName);
		assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorNameNew, analyzerName));		
	}

	/**
	 * Test Case ID: TC99_AnalyzerMax50CharsSerialNumber_PicAdmin
	 * Script:   	 	
	 * - On Home Page, and click Picarro Administration -> Manage Analyzers
	 * - Click on 'Add New Analyzer' button
	 * - Provide more than 50 characters in Serial Number field and click OK
	 * - Repeat same step for Edit analyzer screen
	 * Results: - 
	 * - User cannot enter more than 50 characters and message having limit of characters displayed
	 */
	@Test
	public void TC99_AnalyzerMax50CharsSerialNumber_PicAdmin() {
		final int MAX_SIZE = 50;
		String customerName = "Picarro";
		String locationName = customerName + testSetup.getRandomNumber() + "loc";
		String surveyorName = locationName + testSetup.getRandomNumber() + "sur";
		String analyzerName50 = "TC99" + testSetup.getFixedSizePseudoRandomString(MAX_SIZE-4);
		String analyzerName51 = "TC99" + testSetup.getFixedSizePseudoRandomString(MAX_SIZE-4) + "a";
		String cityName="Santa Clara";
		
		Log.info("\nRunning TC99_AnalyzerMax50CharsSerialNumber_PicAdmin - Test Description: More than 50 characters not allowed in Serial Number field");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName ,cityName);
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		manageAnalyzersPage.open();
		
		Log.info(String.format("Adding new Analyzer: Name=[%s],SharedKey=[%s],Surveyor=[%s],Customer=[%s],Location=[%s]", 
				analyzerName50, ANALYZERSHAREDKEY, surveyorName, customerName, locationName));
		manageAnalyzersPage.addNewAnalyzer(analyzerName50, ANALYZERSHAREDKEY, surveyorName, customerName, locationName);
		
		Log.info(String.format("Finding Analyzer: Customer=[%s],Location=[%s],Surveyor=[%s],Analyzer=[%s]", 
				customerName, locationName, surveyorName, analyzerName50));
		assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName50));
		
		// Reset analyzerName and create new Analyzer with 51 chars.
		analyzerName51 = "TC99" + testSetup.getFixedSizePseudoRandomString(MAX_SIZE-4) + "a";
		manageAnalyzersPage.open();
		manageAnalyzersPage.addNewAnalyzer(analyzerName51, ANALYZERSHAREDKEY, surveyorName, customerName, locationName);
		assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName51.substring(0, MAX_SIZE)));
		assertFalse(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName51));
	}
	
	/**
	 * Test Case ID: TC122_AddAnalyzer_PicAdmin
	 * Test Description: Admin not allowed to create duplicate Analyzer
	 * 
	 */	
	@Test
	public void TC122_DuplicateAnalyzer_PicAdmin() {
		String customerName = "Picarro";
		String locationName = customerName + testSetup.getRandomNumber() + "loc";
		String surveyorName = locationName + testSetup.getRandomNumber() + "sur";
		String analyzerName = surveyorName + "ana";
		String cityName="Santa Clara";
		
		Log.info("\nRunning TC122_DuplicateAnalyzer_PicAdmin - Test Description: Admin not allowed to create duplicate Analyzer");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName ,cityName);
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		manageAnalyzersPage.open();
		manageAnalyzersPage.addNewAnalyzer(analyzerName, ANALYZERSHAREDKEY, surveyorName, customerName, locationName);
		assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName));
		
		manageAnalyzersPage.open();
		assertFalse(manageAnalyzersPage.addNewAnalyzer(analyzerName, ANALYZERSHAREDKEY, surveyorName, customerName, locationName));
	}
	
	/**
	 * Test Case ID: TC123_EditDupliateAnalyzer_PicAdmin
	 * Test Description: Admin not allowed to edit Analyzer having details same as existing analyzer detials
	 * 
	 */	
	@Test
	public void TC123_EditDuplicateAnalyzer_PicAdmin() {
		String customerName = "Picarro";
		String locationName = customerName + testSetup.getRandomNumber() + "loc";
		String surveyorName = locationName + "sur";
		String analyzerName = surveyorName + "ana";
		String analyzerNameNew = surveyorName + "ananew";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC123_EditDuplicateAnalyzer_PicAdmin - Test Description: Admin not allowed to edit Analyzer having details same as existing analyzer detials");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName, cityName);
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		manageAnalyzersPage.open();
		manageAnalyzersPage.addNewAnalyzer(analyzerName, ANALYZERSHAREDKEY, surveyorName, customerName, locationName);
		
		manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName);
		manageAnalyzersPage.editExistingAnalyzer(customerName, locationName, surveyorName, analyzerName, 
					ANALYZERSHAREDKEY, customerName + " - " + locationName + " - " + surveyorName, analyzerNameNew);
		
		assertFalse(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerNameNew));		
	}
	
	/**
	 * Test Case ID: TC109_Associate_Disassociate_Analyzers_PicAdmin
	 * Test Description: Picarro Administrator is allowed to associate and disassociate Analyzers within Surveyor Unit of different Customers
	 * 
	 */	
	@Test
	public void TC109_Associate_Disassociate_Analyzers_PicAdmin() {
		String customerName = "Picarro";
		String locationName = customerName + testSetup.getRandomNumber() + "loc";
		String surveyorName = locationName + "sur";
		String analyzerName = surveyorName + "ana";
		String cityName ="Santa Clara";

		String customerNameNew = CUSNAMEBASE;
		String locationNameNew = customerName + testSetup.getRandomNumber() + CUSNAMEBASELOC;
		String surveyorNameNew = locationName + "sur";
				
		Log.info("\nRunning TC109_Associate_Disassociate_Analyzers_PicAdmin - Picarro Administrator is allowed to associate and disassociate Analyzers within Surveyor Unit of different Customers");
			
		addNewLocationSurveyorAnalyzer(testSetup.getLoginUser(), testSetup.getLoginPwd(), customerName, locationName,surveyorName,analyzerName,cityName,ANALYZERSHAREDKEY);
		addNewLocationSurveyorAnalyzer(testSetup.getLoginUser(), testSetup.getLoginPwd(), customerNameNew, locationNameNew,surveyorNameNew,analyzerName,cityName,ANALYZERSHAREDKEY);
				
		manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName);
		manageAnalyzersPage.associateAnalyzerToOtherSurveyor(customerName, locationName, surveyorName, analyzerName, 
					customerNameNew + " - " + locationNameNew + " - " + surveyorNameNew );
		
		assertFalse(manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName));	
		assertTrue(manageAnalyzersPage.findExistingAnalyzer(customerNameNew, locationNameNew, surveyorNameNew, analyzerName));
	}
	
	/**
	 * Test Case ID: TC110_SurveyorUnit_0or1_analyzer_associated
	 * Test Description: Surveyor Unit should have 0 or 1 analyzer associated
	 * 
	 */	
	@Test
	public void TC110_SurveyorUnit_0or1_analyzer_associated() {
		String customerName = "PG&E";
		String locationName = customerName + testSetup.getRandomNumber() + "loc";
		String surveyorName = locationName + "sur";
		String analyzerName = surveyorName + "ana";
		String cityName ="Santa Clara";

		String locationNameNew = customerName + testSetup.getRandomNumber() + "newloc";
		String surveyorNameNew = locationName + "newsur";
		String analyzerNameNew = surveyorNameNew + "newana";
				
		Log.info("\nRunning TC109_Associate_Disassociate_Analyzers_PicAdmin - Picarro Administrator is allowed to associate and disassociate Analyzers within Surveyor Unit of different Customers");
			
		addNewLocationSurveyorAnalyzer(testSetup.getLoginUser(), testSetup.getLoginPwd(), customerName, locationName,surveyorName,analyzerName,cityName,ANALYZERSHAREDKEY);
		addNewLocationSurveyorAnalyzer(testSetup.getLoginUser(), testSetup.getLoginPwd(), customerName, locationNameNew,surveyorNameNew,analyzerNameNew,cityName,ANALYZERSHAREDKEY);
		manageAnalyzersPage.findExistingAnalyzer(customerName, locationName, surveyorName, analyzerName);
		manageAnalyzersPage.associateAnalyzerToOtherSurveyor(customerName, locationNameNew, surveyorNameNew, analyzerNameNew, 
					customerName + " - " + locationName + " - " + surveyorName, false );

		assertTrue(manageAnalyzersPage.getElementText(manageAnalyzersPage.getWarningMsg()).trim().equals(ANALYZER_ALREADY_ASSOCIATED_ERROR));
	}
	
	/**
	 * Test Case ID: TC132_ManageAnalyzer_SortColumns Script: - Sort records based on attributes present Results: - - User is able to sort the list of records based on specified attribute
	 */
	@Test
	public void TC132_ManageAnalyzer_SortColumns() {
		Log.info("\nRunning TC132_ManageAnalyzer_SortColumns");
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		manageAnalyzersPage.open();
		assertTrue(manageAnalyzersPage.areTableColumnsSorted());	
	}
	
	/**
	 * Test Case ID: TC499_EditAnalyzer_PicSupport
	 * Test Description: Pic Support not allowed to edit Analyzer
	 * 
	 */	
	@Test
	public void TC499_EditAnalyzer_PicSupport() {
		Log.info("\nRunning TC499_EditAnalyzer_PicSupport - Test Description: Pic Support not allowed to edit Analyzer");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);	
		manageAnalyzersPage.open();
		List<WebElement> addAnalyzerButton=driver.findElements(By.xpath("//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a"));
		assertTrue(addAnalyzerButton.size()==0);
		List<WebElement> editAnalyzerButton=driver.findElements(By.xpath("//*[@id='datatable']/tbody/tr[1]/td[7]/a[1]"));
		assertTrue(editAnalyzerButton.size()==0);
	
	}
	
	private void addNewLocationSurveyorAnalyzer(String userName, String password, String customerName, String locationName, String surveyorName, String analyzerName, String city, String sharedKey){
		loginPage.open();
		loginPage.loginNormalAs(userName, password);			
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName, city);		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);				
		manageAnalyzersPage.open();
		manageAnalyzersPage.addNewAnalyzer(analyzerName, sharedKey, surveyorName, customerName, locationName);
	}

}
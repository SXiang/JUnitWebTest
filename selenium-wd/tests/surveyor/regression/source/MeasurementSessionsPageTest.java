/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;
import static surveyor.scommon.source.SurveyorP3URLs.*;

/**
 * @author zlu
 *
 */
public class MeasurementSessionsPageTest extends SurveyorBaseTest {
	private static MeasurementSessionsPage measurementSessionsPage;
	private static List<String> strListTagCus = null;
	private static List<String> strListTagPic = null;
	private static List<String> strListTagCusDr = null;
	
	@BeforeClass
	public static void setupMeasurementSessionsPageTest() {
		measurementSessionsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  measurementSessionsPage);
		
		strListTagCus = new ArrayList<String>();
		strListTagPic = new ArrayList<String>();
		
		strListTagCusDr = new ArrayList<String>();
		strListTagCusDr.add(SQACUSDRTAG);
		
		strListTagCus.add(SQACUSUATAG);
		strListTagCus.add(SQACUSSUTAG);
		strListTagCus.add(SQACUSDRTAG);
		
		strListTagPic.add(SQAPICADTAG);
		strListTagPic.add(SQAPICUATAG);
		strListTagPic.add(SQAPICSUTAG);
		strListTagPic.add(SQAPICDRTAG);
	}
	
	/**
	 * Test Case ID: DSMS000A
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Driver role
	 * 
	 */
	@Test
	public void DSMS000A() {
		System.out.println("\nRunning DSMS000A - Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Driver role");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);		
		
		measurementSessionsPage.open();
		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(SQACUSDR, CUSUSERROLEDR, strListTagCus, strListTagPic));
	}
	
	/**
	 * Test Case ID: DSMS000B
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Supervisor role
	 * 
	 */
	@Test
	public void DSMS000B() {
		System.out.println("\nRunning DSMS000B - Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Supervisor role");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);		
		
		measurementSessionsPage.open();
		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(SQACUSSU, CUSUSERROLESU, strListTagCus, strListTagPic));
	}
	
	/**
	 * Test Case ID: DSMS000C
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Utility Administrator role
	 * 
	 */
	@Test
	public void DSMS000C() {
		System.out.println("\nRunning DSMS000C - Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Utility Administrator role");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);		
		
		measurementSessionsPage.open();
		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(SQACUSUA, CUSUSERROLEUA, strListTagCus, strListTagPic));
	}
	
	/**
	 * Test Case ID: DSMS000D
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Administrator role
	 * 
	 */
	@Test
	public void DSMS000D() {
		System.out.println("\nRunning DSMS000D - Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Administrator role");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);		
		
		measurementSessionsPage.open();
		
		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(SQAPICAD, USERROLEADMIN, strListTagCus, strListTagPic));
	}
	
	/**
	 * Test Case ID: DSMS000E
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Utility Administrator role
	 * 
	 */
	@Test
	public void DSMS000E() {
		System.out.println("\nRunning DSMS000E - Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Utility Administrator role");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICUA, USERPASSWORD);		
		
		measurementSessionsPage.open();
		
		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(SQAPICUA, CUSUSERROLEUA, strListTagCus, strListTagPic));
	}
	
	/**
	 * Test Case ID: DSMS000F
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Supervisor role
	 * 
	 */
	@Test
	public void DSMS000F() {
		System.out.println("\nRunning DSMS000F - Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Supervisor role");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSU, USERPASSWORD);

		measurementSessionsPage.open();
		
		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(SQAPICSU, CUSUSERROLESU, strListTagCus, strListTagPic));
	}
	
	/**
	 * Test Case ID: DSMS000G
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Driver role
	 * 
	 */
	@Test
	public void DSMS000G() {
		System.out.println("\nRunning DSMS000G - Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Driver role");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICDR, USERPASSWORD);
		
		measurementSessionsPage.open();
		
		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(SQAPICDR, CUSUSERROLEDR, strListTagCus, strListTagPic));
	}
	
	/**
	 * Test Case ID: HOME015
	 * Test Description: Recent Driving Surveys Section present on Dashboard should display latest surveys
	 * Test Script: - Login and verify recent driving surveys section
	 * Expected Results: - User should be able to see recent driving surveys
     * Future Improvement: - Visibility check, a customer user should be able to see all surveys generated by the other customer users with different roles 
	 */
	@Test
	public void HOME015() {
		System.out.println("\nRunning - HOME015 - Test Description: Recent Driving Surveys Section present on Dashboard should display latest surveys\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		
		MeasurementSessionsPage msp = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  msp);
		
		homePage.getLinkDrivingSurveys().click();
		
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(testSetup.getBaseUrl() + DRIVINGSURVEYS));
		
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(msp.getStrPageURL()));
		
		assertTrue(msp.getDriverSurveysByTag(SQACUSDR).containsAll(strListTagCusDr));
	}
	
	/**
	 * Test Case ID: HOME013
	 * Test Description: Delete surveys
	 * Test Script: - On Home Page, Dashboard (test script might change once US is implemented)
					- Delete Standard survey
					- Delete Operator survey
					- Delete S1 survey
					- Delete Rapid Response survey"
	 * Expected Results: - Picarro Admin is able to delete surveys
	 * Future Improvement: 1. Delete S1 survey for now and should be the same for deleting surveys with different types
	 *                     2. Delete surveys generated by different users with different roles
	 *                     3. Delete surveys by different login users with different roles  
	 */
	@Test
	public void HOME013() {
		String tagName = "dmcs1-pgeua";
		boolean deleteAll = false;
		List<String> tagList;
		
		System.out.println("\nRunning - HOME013 - Delete surveys\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		
		MeasurementSessionsPage msp = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  msp);
		
		msp.open();
		assertTrue(msp.deleteDrivingSurveyByTag(tagName, deleteAll));
		
		msp.open();
		tagList = msp.getTagNameList();
		
		assertTrue(!tagList.contains(tagName));
	}
	
	/**
	 * Test Case ID: CUSTADM017
	 * Test Description: Export Raw Data
	 * Test Script: - On Home Page, click on Driving Surveys
					- Click on Export Survey
					- Click on Export Peaks
					- Click on Export Analysis
	 * Expected Results: - Customer Administrator should be allowed to export raw data and it should be .dat text file
	 * Current implementation: Raw Data is available to Picarro Administrator only now
	 * Current Issue:
     * Future Improvement:
	 */
	@Test
	public void CUSTADM017() {
		String startDT;
		
		System.out.println("\nRunning - CUSTADM017 - Test Description: Export Raw Data\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);
		
		homePage.getLinkDrivingSurveys().click();
		
		measurementSessionsPage.getInputSearch().sendKeys(SQAPICDRTAG);
		startDT = measurementSessionsPage.getStartDT(SQAPICDRTAG, SQAPICDR, SQAPICLOC3SUR, SQAPICLOC3SURANA, false);
		
		measurementSessionsPage.actionOnDrivingSurveys(SQAPICDRTAG, SQAPICDR, SQAPICLOC3SUR, SQAPICLOC3SURANA, startDT, DRIVINGSURVEYSEXPORTSURVEY, false);
		testSetup.slowdownInSeconds(15);
		assertTrue(measurementSessionsPage.validateDatFiles(DRIVINGSURVEYSEXPORTSURVEY, SQAPICDRTAG, SQAPICLOC3SURANA, testSetup.getDownloadPath(), true));
		
		measurementSessionsPage.actionOnDrivingSurveys(SQAPICDRTAG, SQAPICDR, SQAPICLOC3SUR, SQAPICLOC3SURANA, startDT, DRIVINGSURVEYSEXPORTPEAKS, false);
		testSetup.slowdownInSeconds(15);
		assertTrue(measurementSessionsPage.validateDatFiles(DRIVINGSURVEYSEXPORTPEAKS, SQAPICDRTAG, SQAPICLOC3SURANA, testSetup.getDownloadPath(), true));
		
		measurementSessionsPage.actionOnDrivingSurveys(SQAPICDRTAG, SQAPICDR, SQAPICLOC3SUR, SQAPICLOC3SURANA, startDT, DRIVINGSURVEYSEXPORTANALYSIS, false);
		testSetup.slowdownInSeconds(15);
		assertTrue(measurementSessionsPage.validateDatFiles(DRIVINGSURVEYSEXPORTANALYSIS, SQAPICDRTAG, SQAPICLOC3SURANA, testSetup.getDownloadPath(), true));
	}
}
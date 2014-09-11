/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class MeasurementSessionsPageTest extends SurveyorBaseTest {
	private List<String> strListTagCus = null;
	private List<String> strListTagPic = null;
	
	public MeasurementSessionsPageTest() {
		strListTagCus = new ArrayList<String>();
		strListTagPic = new ArrayList<String>();
		
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
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(SQACUSDR, USERPASSWORD);
		
		MeasurementSessionsPage dsmsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  dsmsPage);		
		
		dsmsPage.open();
		assertTrue(dsmsPage.checkVisibilityForDrivingSurveys(SQACUSDR, CUSUSERROLEDR, strListTagCus, strListTagPic));
		
		dsmsPage.logout();
	}
	
	/**
	 * Test Case ID: DSMS000B
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Supervisor role
	 * 
	 */
	@Test
	public void DSMS000B() {
		System.out.println("\nRunning DSMS000B - Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Supervisor role");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(SQACUSSU, USERPASSWORD);
		
		MeasurementSessionsPage dsmsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  dsmsPage);		
		
		dsmsPage.open();
		assertTrue(dsmsPage.checkVisibilityForDrivingSurveys(SQACUSSU, CUSUSERROLESU, strListTagCus, strListTagPic));
		
		dsmsPage.logout();
	}
	
	/**
	 * Test Case ID: DSMS000C
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Utility Administrator role
	 * 
	 */
	@Test
	public void DSMS000C() {
		System.out.println("\nRunning DSMS000C - Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Utility Administrator role");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		MeasurementSessionsPage dsmsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  dsmsPage);		
		
		dsmsPage.open();
		assertTrue(dsmsPage.checkVisibilityForDrivingSurveys(SQACUSUA, CUSUSERROLEUA, strListTagCus, strListTagPic));
		
		dsmsPage.logout();
	}
	
	/**
	 * Test Case ID: DSMS000D
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Administrator role
	 * 
	 */
	@Test
	public void DSMS000D() {
		System.out.println("\nRunning DSMS000D - Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Administrator role");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(SQAPICAD, USERPASSWORD);
		
		MeasurementSessionsPage dsmsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  dsmsPage);		
		
		dsmsPage.open();
		assertTrue(dsmsPage.checkVisibilityForDrivingSurveys(SQAPICAD, USERROLEADMIN, strListTagCus, strListTagPic));
		
		dsmsPage.logout();
	}
	
	/**
	 * Test Case ID: DSMS000E
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Utility Administrator role
	 * 
	 */
	@Test
	public void DSMS000E() {
		System.out.println("\nRunning DSMS000E - Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Utility Administrator role");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(SQAPICUA, USERPASSWORD);
		
		MeasurementSessionsPage dsmsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  dsmsPage);		
		
		dsmsPage.open();
		assertTrue(dsmsPage.checkVisibilityForDrivingSurveys(SQAPICUA, CUSUSERROLEUA, strListTagCus, strListTagPic));
		
		dsmsPage.logout();
	}
	
	/**
	 * Test Case ID: DSMS000F
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Supervisor role
	 * 
	 */
	@Test
	public void DSMS000F() {
		System.out.println("\nRunning DSMS000F - Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Supervisor role");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(SQAPICSU, USERPASSWORD);
		
		MeasurementSessionsPage dsmsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  dsmsPage);		
		
		dsmsPage.open();
		assertTrue(dsmsPage.checkVisibilityForDrivingSurveys(SQAPICSU, CUSUSERROLESU, strListTagCus, strListTagPic));
		
		dsmsPage.logout();
	}
	
	/**
	 * Test Case ID: DSMS000G
	 * Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Driver role
	 * 
	 */
	@Test
	public void DSMS000G() {
		System.out.println("\nRunning DSMS000G - Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Driver role");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(SQAPICDR, USERPASSWORD);
		
		MeasurementSessionsPage dsmsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  dsmsPage);		
		
		dsmsPage.open();
		assertTrue(dsmsPage.checkVisibilityForDrivingSurveys(SQAPICDR, CUSUSERROLEDR, strListTagCus, strListTagPic));
		
		dsmsPage.logout();
	}
}
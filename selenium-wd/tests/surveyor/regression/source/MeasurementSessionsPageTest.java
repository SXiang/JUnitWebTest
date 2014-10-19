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

/**
 * @author zlu
 *
 */
public class MeasurementSessionsPageTest extends SurveyorBaseTest {
	private static MeasurementSessionsPage measurementSessionsPage;
	private static List<String> strListTagCus = null;
	private static List<String> strListTagPic = null;
	
	@BeforeClass
	public static void setupMeasurementSessionsPageTest() {
		measurementSessionsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  measurementSessionsPage);
		
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
}
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
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.SurveyorBaseTest;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageSurveyorPageTest extends SurveyorBaseTest {
	private static ManageSurveyorPage manageSurveyorPage;	

	public ManageSurveyorPageTest() {
		manageSurveyorPage = new ManageSurveyorPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorPage);		
	}

	/**
	 * Test Case: Adding new Surveyors
	 */	
	@Test
	public void manageSurveyorPage_AddNewSurveyors() {
		System.out.println("\nRunning manageSurveyorPage_AddNewSurveyors...");
		
		try {
			manageSurveyorPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			String strSurveyorDesc = "";
			String strCustomerName = "";
			String strLocation = "";
			
			for (int i = 0; i < CUSTOMERNUM; i++) {
				if (i >= 0 && i < 10)
					strCustomerName = BASECUSTOMERNAME + "00" + Integer.toString(i);
				else if (i >=  10 && i < 100)
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
					
					for (int iii = 0; iii < SURVEYORNUM; iii++) {
						if (iii >= 0 && iii < 10)
							strSurveyorDesc = strLocation + "_" + "00" + Integer.toString(iii) + BASESURVEYORNAME;
						else if (iii >= 10 && iii < 100)
							strSurveyorDesc = strLocation + "_" + "0" + Integer.toString(iii) + BASESURVEYORNAME;
						else
							strSurveyorDesc = strLocation + "_" + Integer.toString(iii) + BASESURVEYORNAME;
						
						manageSurveyorPage.addNewSurveyor(strSurveyorDesc, strCustomerName + " - " + strLocation);
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("\nException on test case \"manageSurveyorPage_AddNewSurveyors\": " + e.getMessage());
		}
		
	}
}

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
import surveyor.scommon.source.ManageAnalyzersPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.SurveyorBaseTest;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageAnalyzersPageTest extends SurveyorBaseTest {
	private static ManageAnalyzersPage manageAnalyzersPage;
	
	public ManageAnalyzersPageTest() {
		manageAnalyzersPage = new ManageAnalyzersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageAnalyzersPage);		
	}
	
	/**
	 * Test Case: Adding new Analyzers
	 */	
	@Test
	public void manageAnalyzersPage_AddNewAnalyzers() {
		System.out.println("\nRunning manageAnalyzersPage_AddNewAnalyzers...");
		
		try {
			manageAnalyzersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			String strSurveyorDesc;
			String strCustomerName;
			String strLocation;
			String strAnalyzer;
			
			String strCusDigits;
			String strLocDigits;
			String strSurDigits;
			
			for (int i = 0; i < CUSTOMERNUM; i++) {
				if (i >= 0 && i < 10) {
					strCustomerName = BASECUSTOMERNAME + "00" + Integer.toString(i);
					strCusDigits = "00" + Integer.toString(i);
				}
				else if (i >= 10 && i < 100) {
					strCustomerName = BASECUSTOMERNAME + "0" + Integer.toString(i);
					strCusDigits = "0" + Integer.toString(i);
				}
				else {
					strCustomerName = BASECUSTOMERNAME + Integer.toString(i);
					strCusDigits = Integer.toString(i);
				}
				
				for (int ii = 0; ii < LOCATIONNUM; ii++) {
					if (ii >= 0 && ii < 10) {
						strLocation = strCustomerName + "_" + "00" + Integer.toString(ii) + BASELOCATIONNAME;
						strLocDigits = "00" + Integer.toString(ii);
					}
					else if (ii >= 10 && ii < 100) {
						strLocation = strCustomerName + "_" + "0" + Integer.toString(ii) + BASELOCATIONNAME;
						strLocDigits = "0" + Integer.toString(ii);
					}
					else {
						strLocation = strCustomerName + "_" + Integer.toString(ii) + BASELOCATIONNAME;
						strLocDigits = Integer.toString(ii);
					}
					
					for (int iii = 0; iii < SURVEYORNUM; iii++) {
						if (iii >= 0 && iii < 10) {
							strSurveyorDesc = strLocation + "_" + "00" + Integer.toString(iii) + BASESURVEYORNAME;
							strSurDigits = "00" + Integer.toString(iii);
						}
						else if (iii >= 10 && iii < 100) {
							strSurveyorDesc = strLocation + "_" + "0" + Integer.toString(iii) + BASESURVEYORNAME;
							strSurDigits = "0" + Integer.toString(iii);
						}
						else {
							strSurveyorDesc = strLocation + "_" + Integer.toString(iii) + BASESURVEYORNAME;
							strSurDigits = Integer.toString(iii);
						}
						
						for (int iiii = 0; iiii < ANALYZERNUM; iiii++) {
							if (iiii >= 0 && iiii < 10) {
							 	strAnalyzer = strCusDigits + strLocDigits + strSurDigits + "00" + Integer.toString(iiii);
							}
							else if (iiii >= 10 && iiii < 100) {
								strAnalyzer = strCusDigits + strLocDigits + strSurDigits + "0" + Integer.toString(iiii);
							}
							else {
								strAnalyzer = strCusDigits + strLocDigits + strSurDigits + Integer.toString(iiii);
							}
							
							manageAnalyzersPage.addNewAnalyzer(strAnalyzer, ANALYZERSHAREDKEY, strSurveyorDesc);
						}
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("\nException on test case \"manageAnalyzersPage_AddNewAnalyzers\": " + e.getMessage());
		}
	}
}
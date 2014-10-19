/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageLocationsAdminPage extends ManageLocationsPage {
	public static final String STRURLPath = "/Admin/ManageLocations";
	public static final String STRPageTitle = "Manage Locations - Surveyor";

	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public ManageLocationsAdminPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, baseURL, testSetup, STRURLPath);
		
		System.out.format("\nThe Manage Locations Admin Page URL is: %s\n", baseURL + STRURLPath);
	}

	public void addNewLocation(String locationDesc) {
		this.btnAddNewLocation.click();
				
		this.inputLocationDesc.sendKeys(locationDesc);
		
		this.btnOK.click();
		
		if (isElementPresent(this.panelDuplicationErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:"))
				this.btnCancel.click();
		}		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
}
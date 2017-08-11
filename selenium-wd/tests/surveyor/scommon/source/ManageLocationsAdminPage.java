/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

import common.source.Log;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageLocationsAdminPage extends ManageLocationsPage {
	public static final String STRURLPath = "/Admin/ManageLocations";
	public static final String STRPageTitle = String.format("%s - %s", 
			Resources.getResource(ResourceKeys.ManageLocations_PageTitle), Resources.getResource(ResourceKeys.Constant_Surveyor));

	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public ManageLocationsAdminPage(WebDriver driver, String baseURL,
			TestSetup testSetup) {
		super(driver, baseURL, testSetup, STRURLPath);

		Log.info("\nThe Manage Locations Admin Page URL is: %s\n" + baseURL
				+ STRURLPath);
	}

	public void addNewLocation(String locationDesc) {
		Log.clickElementInfo("Add New Location");
		this.btnAddNewLocation.click();
		Log.info("Set Location Desc - '"+locationDesc+"'");
		this.inputLocationDesc.sendKeys(locationDesc);
		Log.clickElementInfo("Ok");
		this.btnOK.click();

		if (isElementPresent(this.summaryErrorsBy)) {
			WebElement panelError = driver.findElement(this.summaryErrorsBy);
			if (panelError
					.getText()
					.equalsIgnoreCase(
							Resources
									.getResource(ResourceKeys.Validation_SummaryTitle))){
				Log.clickElementInfo("Cancel");
				this.btnCancel.click();
			}
		}
	}

	public boolean addNewLocation(String locationDesc, boolean bFlag) {
		Log.clickElementInfo("Add New Location");
		this.btnAddNewLocation.click();
		Log.info("Set Location Desc - '"+locationDesc+"'");
		this.inputLocationDesc.sendKeys(locationDesc);
		String curURL = driver.getCurrentUrl();
		Log.clickElementInfo("Ok");
		this.btnOK.click();

		if (locationDesc.equalsIgnoreCase("")) {
			if (driver.getCurrentUrl().equalsIgnoreCase(curURL))
				return false;
		}

		if (isElementPresent(this.summaryErrorsBy)) {
			WebElement panelError = driver.findElement(this.summaryErrorsBy);
			if (panelError
					.getText()
					.equalsIgnoreCase(
							Resources
									.getResource(ResourceKeys.Validation_SummaryTitle))) {
				Log.clickElementInfo("Cancel");
				this.btnCancel.click();
				return false;
			}
		}

		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
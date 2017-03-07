/**
 *
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

import common.source.Log;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageSurveyorAdminPage extends ManageSurveyorPage {
	public static final String STRURLPath = "/Admin/ManageSurveyors";
	public static final String STRPageTitle = String.format("%s - %s",
			Resources.getResource(ResourceKeys.ManageSurveyors_PageTitle), Resources.getResource(ResourceKeys.Constant_Surveyor));

	@FindBy(how = How.XPATH, using = "//*[@class='btn btn-primary' and text()='Add New Calibration Record']")
	protected WebElement addCalibrationRecordButton;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th")
	protected List<WebElement> tableHeader;

	// Overridden webElement to handle id case difference.
	@FindBy(how = How.XPATH, using = "//*[@id='LocationId']")
	protected WebElement dropDownLocation;

	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public ManageSurveyorAdminPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, baseURL, testSetup, STRURLPath);

		Log.info("\nThe Manage Surveyors Admin Page URL is: %s\n" + baseURL + STRURLPath);
	}

	public boolean findExistingSurveyor(String locationName, String surveyorName) {
		Log.info(String.format("Find surveyor %s, location = '%s'",
				surveyorName, locationName));
		setPaginationAny(PAGE_PAGINATIONSETTING);
		this.clearSearchFieldUsingSpace();   // clear any previous entries in search.

		this.waitForAJAXCallsToComplete();
		this.searchTable(surveyorName);
		if (this.searchHasNoMatchingRecords()) {
        	// revert back search field.
        	this.clearSearchField();
        	return false;
		}

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String locationNameXPath;
		String surveyorNameXPath;

		WebElement locationNameCell;
		WebElement surveyorNameCell;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			surveyorNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";

			locationNameCell = getTable().findElement(By.xpath(locationNameXPath));
			surveyorNameCell = getTable().findElement(By.xpath(surveyorNameXPath));

			if ((locationNameCell.getText().trim()).equalsIgnoreCase(locationName) && (surveyorNameCell.getText().trim()).equalsIgnoreCase(surveyorName)) {
				Log.info("Found entry at row=" + rowNum);
            	// revert back search field.
            	this.clearSearchField();
				return true;
			}

			if (rowNum == Integer.parseInt(PAGE_PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);

				rowNum = 0;
			}
		}

    	// revert back search field.
    	this.clearSearchField();
    	Log.info(String.format("Surveyor not found: %s, location = '%s'",
				surveyorName, locationName));
		return false;
	}

	public boolean editExistingSurveyor(String customer, String locationName, String surveyorName, String locationNameNew, String surveyorNameNew, boolean addCalibration) {
		Log.info(String.format("Edit surveyor %s, customer = '%s', location = '%s'",
				surveyorName, customer,locationName));
		setPaginationAny(PAGE_PAGINATIONSETTING);
		this.clearSearchFieldUsingSpace();		// clear any previous entries in search.

		this.searchTable(surveyorName);
		if (this.searchHasNoMatchingRecords()) {
        	// revert back search field.
        	this.clearSearchField();
        	return false;
		}

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String locationNameXPath;
		String surveyorNameXPath;
		String actionEditXPath;

		WebElement locationNameCell;
		WebElement surveyorNameCell;
		WebElement actionEditCell;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);

		int locationIndex = 0;
		int surveyorIndex = 0;
		int actionIndex = 0;

		for (int index = 0; index < tableHeader.size(); index++) {
			if (tableHeader.get(index).getText().equalsIgnoreCase("Location")) {
				locationIndex = index + 1;
			}
			if (tableHeader.get(index).getText().equalsIgnoreCase("Surveyor")) {
				surveyorIndex = index + 1;
			}
			if (tableHeader.get(index).getText().equalsIgnoreCase("Action")) {
				actionIndex = index + 1;
			}
		}

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[" + locationIndex + "]";
			surveyorNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[" + surveyorIndex + "]";

			locationNameCell = getTable().findElement(By.xpath(locationNameXPath));
			surveyorNameCell = getTable().findElement(By.xpath(surveyorNameXPath));

			if ((locationNameCell.getText().trim()).equalsIgnoreCase(locationName) && (surveyorNameCell.getText().trim()).equalsIgnoreCase(surveyorName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[" + actionIndex + "]/a";
				actionEditCell = getTable().findElement(By.xpath(actionEditXPath));

				Log.info("Found entry at rowNum=" + rowNum);

				Log.clickElementInfo("Edit",ElementType.ICON);
				actionEditCell.click();
				this.waitForEditPageLoad();

				if (!surveyorName.equals(surveyorNameNew)) {
					Log.info("Set surveyor name - '"+surveyorNameNew+"'");
					this.inputSurveyorDesc.clear();
					this.inputSurveyorDesc.sendKeys(surveyorNameNew);
				}

				if (!locationName.equalsIgnoreCase(locationNameNew)) {
					boolean bFound = false;
					List<WebElement> options = this.dropDownLocation.findElements(By.tagName("option"));
					for (WebElement option : options) {
						if (option.getText().trim().equalsIgnoreCase(locationNameNew)) {
							Log.info("Select location - '"+locationNameNew+"'");
							option.click();
							bFound = true;
							break;
						}
					}

					if (!bFound){
						Log.error("Location not found: '"+locationNameNew+"'");
						return false;
					}
				}

				if (addCalibration) {
					Log.clickElementInfo("Add Calibration Record");
					this.addCalibrationRecordButton.click();
					this.waitForPageToLoad();
					Log.clickElementInfo("Ok");
					this.btnOK.click();
					this.waitForEditPageLoad();
				}

				Log.clickElementInfo("Ok");
				this.btnOK.click();

				if (isElementPresent(this.panelDuplicationErrorXPath)) {
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						return false;
					}
				}

            	// revert back search field.
            	this.clearSearchField();
            	return true;
			}

			if (rowNum == Integer.parseInt(PAGE_PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);

				rowNum = 0;
			}
		}

    	// revert back search field.
    	this.clearSearchField();
    	Log.error(String.format("Surveyor not found: %s, customer = '%s', location = '%s'",
				surveyorName, customer,locationName));
		return false;
	}

	public WebElement getBtnCancel() {
		return this.btnEditCancel;
	}

	public void clickOnFirstEditSurveyorBtn() {
		Log.clickElementInfo("Edit Surveyor");
		this.btnEditSurveyor.click();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
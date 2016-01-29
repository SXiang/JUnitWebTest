/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

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
public class ManageSurveyorAdminPage extends ManageSurveyorPage {
	public static final String STRURLPath = "/Admin/ManageSurveyors";
	public static final String STRPageTitle = "Manage ??? Surveyors - Surveyor";
	
	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public ManageSurveyorAdminPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, baseURL, testSetup, STRURLPath);
		
		Log.info("\nThe Manage Surveyors Admin Page URL is: %s\n"+ baseURL + STRURLPath);
	}
	
	public boolean findExistingSurveyor(String locationName, String surveyorName) {
		setPagination(PAGE_PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String locationNameXPath;
		String surveyorNameXPath;
		
		WebElement locationNameCell;
		WebElement surveyorNameCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			surveyorNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			surveyorNameCell = table.findElement(By.xpath(surveyorNameXPath));
			
			if ((locationNameCell.getText().trim()).equalsIgnoreCase(locationName) 
					&& (surveyorNameCell.getText().trim()).equalsIgnoreCase(surveyorName)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGE_PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);
				
				rowNum = 0;
			}	
		}
		
		return false;
	}	
	
	public boolean editExistingSurveyor(String locationName, String surveyorName, String surveyorNameNew, boolean isCustomerLogin) {
		setPagination(PAGE_PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String locationNameXPath;
		String surveyorNameXPath;
		String actionEditXPath;
		
		WebElement locationNameCell;
		WebElement surveyorNameCell;
		WebElement actionEditCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			if (isCustomerLogin) {
				locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
				surveyorNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			} else {
				locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
				surveyorNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
			}
				
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			surveyorNameCell = table.findElement(By.xpath(surveyorNameXPath));
			
			Log.info(String.format("Looking for Location-[%s],Surveyor-[%s]. Found Location-[%s],Surveyor-[%s]", 
					locationName, surveyorName, locationNameCell.getText().trim(), surveyorNameCell.getText().trim()));
			
			if ((locationNameCell.getText().trim()).equalsIgnoreCase(locationName) 
					&& (surveyorNameCell.getText().trim()).equalsIgnoreCase(surveyorName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]/a";
				actionEditCell = table.findElement(By.xpath(actionEditXPath));
				
				Log.info("Found entry at rowNum=" + rowNum);
				
				actionEditCell.click();
				this.waitForEditPageLoad();
				
				this.inputSurveyorDesc.clear();
				this.inputSurveyorDesc.sendKeys(surveyorNameNew);
				
				List<WebElement> options = this.dropDownLocation.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if (option.getText().trim().equalsIgnoreCase(locationName))
						option.click();
				}
				
				String curURL = driver.getCurrentUrl();
				
				this.btnOK.click();
				
				if (surveyorNameNew.equalsIgnoreCase("")) {
					if (driver.getCurrentUrl().equalsIgnoreCase(curURL))
						return false;
				}
				
				if (isElementPresent(this.panelDuplicationErrorXPath)) {
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						this.btnEditCancel.click();
						return false;
					}
				}

				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGE_PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);
				
				rowNum = 0;
			}	
		}
		
		return false;
	}
	
	public boolean editExistingSurveyor(String locationName, String surveyorName, String locationNameNew, String surveyorNameNew) {
		setPagination(PAGE_PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String locationNameXPath;
		String surveyorNameXPath;
		String actionEditXPath;
		
		WebElement locationNameCell;
		WebElement surveyorNameCell;
		WebElement actionEditCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			surveyorNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			surveyorNameCell = table.findElement(By.xpath(surveyorNameXPath));
			
			if ((locationNameCell.getText().trim()).equalsIgnoreCase(locationName) 
					&& (surveyorNameCell.getText().trim()).equalsIgnoreCase(surveyorName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]/a";
				actionEditCell = table.findElement(By.xpath(actionEditXPath));
				
				Log.info("Found entry at rowNum=" + rowNum);
				
				actionEditCell.click();
				this.waitForEditPageLoad();
				
				this.inputSurveyorDesc.clear();
				this.inputSurveyorDesc.sendKeys(surveyorNameNew);
				
				boolean bFound = false;
				List<WebElement> options = this.dropDownLocation.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if (option.getText().trim().equalsIgnoreCase(locationNameNew)) {
						option.click();
						bFound = true;
						break;
					}
				}
				
				if (!bFound)
					return false;
				
				this.btnOK.click();
				
				if (isElementPresent(this.panelDuplicationErrorXPath)) {
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						return false;
					}
				}
				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGE_PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);
				
				rowNum = 0;
			}	
		}
		
		return false;
	}	
	
	public WebElement getBtnCancel() {
		return this.btnEditCancel;
	}
	
	public void clickOnFirstEditSurveyorBtn() {
		this.btnEditSurveyor.click();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

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
		
		System.out.format("\nThe Manage Surveyors Admin Page URL is: %s\n", baseURL + STRURLPath);
	}
	
	public boolean findExistingSurveyor(String locationName, String surveyorName) {
		setPagination(PAGINATIONSETTING);
		
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
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			surveyorNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			surveyorNameCell = table.findElement(By.xpath(surveyorNameXPath));
			
			if ((locationNameCell.getText().trim()).equalsIgnoreCase(locationName) 
					&& (surveyorNameCell.getText().trim()).equalsIgnoreCase(surveyorName)) {
				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);
				
				rowNum = 0;
			}	
		}
		
		return false;
	}	
	
	public boolean editExistingSurveyor(String locationName, String surveyorName, String surveyorNameNew) {
		setPagination(PAGINATIONSETTING);
		
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
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			surveyorNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			surveyorNameCell = table.findElement(By.xpath(surveyorNameXPath));
			
			if ((locationNameCell.getText().trim()).equalsIgnoreCase(locationName) 
					&& (surveyorNameCell.getText().trim()).equalsIgnoreCase(surveyorName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
				actionEditCell = table.findElement(By.xpath(actionEditXPath));
				
				actionEditCell.click();
				
				this.inputSurveyorDesc.clear();
				this.inputSurveyorDesc.sendKeys(surveyorNameNew);
				
				List<WebElement> options = this.dropDownLocation.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if (option.getText().trim().equalsIgnoreCase(locationName))
						option.click();
				}
				
				this.btnOK.click();
				
				if (isElementPresent(this.panelDuplicationErrorXPath)) {
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:")) {
						this.btnCancel.click();
						return false;
					}
				}

				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);
				
				rowNum = 0;
			}	
		}
		
		return false;
	}	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
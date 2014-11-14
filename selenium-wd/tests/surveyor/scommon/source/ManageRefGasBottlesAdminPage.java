/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageRefGasBottlesAdminPage extends ManageRefGasBottlesPage {
	public static final String STRURLPATH = "/Admin/ManageRefGasBottles";
	//public static final String STRPAGETITLE = "Manage Reference Gas Bottles - Surveyor";
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public ManageRefGasBottlesAdminPage(WebDriver driver, TestSetup testSetup, String strBaseURL) {
		super(driver, testSetup, strBaseURL, STRURLPATH);
		
		System.out.format("\nThe Manage Reference Gas Bottles Admin Page URL is: %s\n", strBaseURL + STRURLPATH);
	}
	
	public boolean findExistingRefGasBottle(String strItemNumber, String strSurveyor, String location) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String locationXPath;
		String strSurveyorXPath;
		String strRGBXPath;
		
		WebElement locationCell;
		WebElement surveyorCell;
		WebElement rgbCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationXPath = strTRXPath + "["+rowNum+"]/td[1]";
			strSurveyorXPath = strTRXPath + "["+rowNum+"]/td[2]";
			strRGBXPath = strTRXPath + "["+rowNum+"]/td[3]";
			
			locationCell = table.findElement(By.xpath(locationXPath));
			surveyorCell = table.findElement(By.xpath(strSurveyorXPath));
			rgbCell = table.findElement(By.xpath(strRGBXPath));
			
			if (locationCell.getText().trim().equalsIgnoreCase(location) && surveyorCell.getText().trim().equalsIgnoreCase(strSurveyor) 
					&& rgbCell.getText().trim().equalsIgnoreCase(strItemNumber)) {				
				return true;
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
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
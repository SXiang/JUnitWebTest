/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.BasePage;
import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;

/**
 * @author zlu
 *
 */
public class SurveyorSystemsPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Home/SurveyorSystems";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.Constant_Surveyors) + " - " + Resources.getResource(ResourceKeys.Constant_Surveyors);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.Constant_Surveyors);

	public enum SurveyorStatusType {
		Online,
		Offline
	}
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public SurveyorSystemsPage(WebDriver driver, TestSetup testSetup,
			String strBaseURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
	}
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public SurveyorSystemsPage(WebDriver driver, TestSetup testSetup,
			String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
	}
	
	public boolean checkSurveyorStatus(String surveyorName, SurveyorStatusType surveyorStatus) {
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String surveyorXPath;
		String statusXPath;
		
		String statusString = "Offline";
		switch (surveyorStatus) {
		case Online:
			statusString = "Online";
			break;
		case Offline:
			statusString = "Offline";
			break;
		default:
			break;
		}
		
		WebElement surveyorNameCell;
		WebElement statusCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			
			surveyorXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			statusXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]";
			
			surveyorNameCell = table.findElement(By.xpath(surveyorXPath));
			statusCell = table.findElement(By.xpath(statusXPath));
			
			if (surveyorNameCell.getText().trim().equalsIgnoreCase(surveyorName) && 
					statusCell.getText().trim().equalsIgnoreCase(statusString)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);
				
				rowNum = 0;
			}	
		}
		
		return false;
	}


	@Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }
}

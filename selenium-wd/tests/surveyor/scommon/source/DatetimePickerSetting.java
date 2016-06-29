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

import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class DatetimePickerSetting extends SurveyorBasePage {	
	@FindBy(how = How.XPATH, using = "//*[@id='report-date-start']")
	protected WebElement inputStartDT;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-date-end']")
	protected WebElement inputEndDT;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-survey-start-dt']")
	protected WebElement inputSurveyStartDT;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-survey-end-dt']")
	protected WebElement inputSurveyEndDT;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[1]/div[2]/img")
	protected WebElement pcubedLogo;
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public DatetimePickerSetting(WebDriver driver, TestSetup testSetup,
			String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);
	}
	
	//Temporary solution for now to bypass the test case date picker issue.  
	public boolean setDay(String startOrEnd, int numOfPreMonths, String day, boolean survey) {
		if (survey)
			if (startOrEnd.equalsIgnoreCase("start"))
				this.inputSurveyStartDT.click();
			else
				this.inputSurveyEndDT.click();
		else
			if (startOrEnd.contentEquals("start"))
				this.inputStartDT.click();
			else
				this.inputEndDT.click();
		
		for (int i = 1; i <= numOfPreMonths; i++)
			driver.findElement(By.className("prev")).click();
		
		WebElement dateWidget = driver.findElement(By.className("bootstrap-datetimepicker-widget"));
		
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));  
	    
		for (WebElement cell: columns){     
			if (cell.getText().trim().equals(day)){
				cell.click();
				this.pcubedLogo.click();
				return true;  
			}
		}
		
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
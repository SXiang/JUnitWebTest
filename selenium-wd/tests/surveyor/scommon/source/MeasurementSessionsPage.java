/**
 * 
 */
package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.TestSetup;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class MeasurementSessionsPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Home/MeasurementSessions";
	public static final String STRPageTitle = "Measurement Sessions - Surveyor";
	
	public static final String DSTAGUA = "dmcs1-sqacusua";
	public static final String DSTAGSU = "dmcs1-sqacussu";
	public static final String DSTAGDR = "dmcs1-sqacusdr";
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[8]/a[1]/img")
	private WebElement linkViewSurvey;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[8]/a[2]/img")
	private WebElement linkExportSurvey;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[8]/a[3]/img")
	private WebElement linkExportPeaks;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[8]/a[4]/img")
	private WebElement linkExportAnalysis;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[8]/a[5]/img")
	private WebElement linkDeleteSurvey;
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public MeasurementSessionsPage(WebDriver driver, TestSetup testSetup,
			String strBaseURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
	}
	
	public boolean checkVisibilityForDrivingSurveys(String loginUser, String role, List<String> strListTagCus, List<String> strListTagPic) {
		System.out.format("\nUser Role: %s\n", role);
		
		switch (role) {
		case "Driver":
			return checkVisibilityForDrivingSurveysWithDriverRole(loginUser, strListTagCus, strListTagPic);
			
		case "Supervisor":
			return checkVisibilityForDrivingSurveysWithSupervisorRole(loginUser, strListTagCus, strListTagPic);
			
		case "Utility Administrator":
			return checkVisibilityForDrivingSurveysWithUtilAdminRole(loginUser, strListTagCus, strListTagPic);
			
		case "Administrator":
			return checkVisibilityForDrivingSurveysWithPicarroAdminRole(loginUser, strListTagCus, strListTagPic);
		}
		
		return false;
	}
	
	private boolean checkVisibilityForDrivingSurveysWithPicarroAdminRole(String loginUser, List<String> strListTagCus, List<String> strListTagPic) {
		List<String> strListTag = getTagNameList();
		
		if (strListTag.containsAll(strListTagCus) && strListTag.containsAll(strListTagPic))
			return true;
		
		return false;
	}
	
	private boolean checkVisibilityForDrivingSurveysWithUtilAdminRole(String loginUser, List<String> strListTagCus, List<String> strListTagPic) {
		List<String> strListTag = getTagNameList();
		
		if (loginUser.contains(REGBASEPICUSERNAME)) {
			if (!strListTag.contains(strListTagCus) && strListTag.containsAll(strListTagPic))
				return true;
		}
		else {
			if (!strListTag.contains(strListTagPic) && strListTag.containsAll(strListTagCus))
				return true;
		}
		
		return false;
	}
	
	private boolean checkVisibilityForDrivingSurveysWithSupervisorRole(String loginUser, List<String> strListTagCus, List<String> strListTagPic) {
		List<String> strListTag = getTagNameList();
		
		if (loginUser.contains(REGBASEPICUSERNAME)) {
			if (!strListTag.contains(strListTagCus) && strListTag.containsAll(strListTagPic))
				return true;
		}
		else {
			if (!strListTag.contains(strListTagPic) && strListTag.containsAll(strListTagCus))
				return true;
		}
		
		return false;
	}
	
	private boolean checkVisibilityForDrivingSurveysWithDriverRole(String loginUser, List<String> strListTagCus, List<String> strListTagPic) {
		List<String> strListTag = getTagNameList();
		
		if (loginUser.contains(REGBASEPICUSERNAME)) {
			if (!strListTag.contains(strListTagCus) && strListTag.containsAll(strListTagPic))
				return true;
		}
		else {
			if (!strListTag.contains(strListTagPic) && strListTag.containsAll(strListTagCus))
				return true;
		}
		
		return false;
	}
	
	public List<String> getTagNameList() {
		setPagination(PAGINATIONSETTING);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		List<String> strListTag = new ArrayList<String>();
		WebElement col1;
		
		List<WebElement> rows = this.table.findElements(By.xpath(this.strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			col1 = this.table.findElement(By.xpath(this.strTRXPath + "["+rowNum+"]/td[1]"));
			
			strListTag.add(col1.getText().trim());
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = this.table.findElements(By.xpath(this.strTRXPath));
				rowSize = newRows.size();
				
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);
				
				rowNum = 0;
			}
		}
		
		return strListTag;
	}
	
	public List<String> getDriverSurveysByTag(String driver) {
		List<String> tagList = new ArrayList<String>();
		
		setPagination(PAGINATIONSETTING);
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String tagXPath;
		String driverXPath; 
		
		WebElement tagCell;
		WebElement driverCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			tagXPath = strTRXPath + "["+rowNum+"]/td[1]";
			tagCell = table.findElement(By.xpath(tagXPath));
			
			driverXPath = strTRXPath + "["+rowNum+"]/td[2]";
			driverCell = table.findElement(By.xpath(driverXPath));
			
			if (tagCell.getText() != null && driverCell.getText().trim().equalsIgnoreCase(driver)) {
				tagList.add(tagCell.getText().trim());
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
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
		
		return tagList; 
	}
	
	public boolean deleteDrivingSurveyByTag(String tagName, boolean deleteAll) {
		this.setPagination(PAGINATIONSETTING);
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String tagXPath;
		String deleteImgXPath;
		
		WebElement tagCell;
		WebElement deleteImg;
		
		boolean deleted = false;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
												
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			tagXPath = strTRXPath + "["+rowNum+"]/td[1]";
			tagCell = table.findElement(By.xpath(tagXPath));
			
			if (tagCell.getText().trim().equalsIgnoreCase(tagName)) {
				deleteImgXPath = strTRXPath + "["+rowNum+"]/td[10]/a[6]/img";
				deleteImg = table.findElement(By.xpath(deleteImgXPath));
				
				deleteImg.click();
				
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				
				if (this.isElementPresent(popupConfirmationBoxXPath) && this.isElementPresent(btnDeleteXPath)) {
					JavascriptExecutor js = (JavascriptExecutor)driver; 
					js.executeScript("arguments[0].click();", btnDelete);
					deleted = true;
					rowNum = rowNum - 1;
					if (!deleteAll)
						return true;
				}
				else
					return false;
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
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

		return deleted;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}
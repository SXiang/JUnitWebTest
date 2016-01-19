/**
 * 
 */
package surveyor.scommon.source;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.BaseHelper;
import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class MeasurementSessionsPage extends SurveyorBasePage {	
	public static final String STRURLPath = "/Home/MeasurementSessions";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.Layout_Nav_DrivingSurveys);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.Layout_Nav_DrivingSurveys);
	
	public static final String DSTAGUA = "dmcs1-sqacusua";
	public static final String DSTAGSU = "dmcs1-sqacussu";
	public static final String DSTAGDR = "dmcs1-sqacusdr";
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[8]/a[1]/img")
	private WebElement linkViewSurvey;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[8]/a[2]/img")
	@FindBy(how = How.XPATH, using = "//img[contains(@src,'/Content/Images/browser_download.png')]")
	private WebElement linkExportSurvey;
	
	@FindBy(how = How.XPATH, using = "//img[contains(@src,'/Content/Images/instrument.png')]")
	private WebElement linkExportPeaks;
	
	@FindBy(how = How.XPATH, using = "//img[contains(@src,'/Content/Images/filter-icon.png')]")
	private WebElement linkExportAnalysis;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[8]/a[5]/img")
	private WebElement linkDeleteSurvey;
	
	@FindBy(how = How.XPATH, using = "//img[contains(@src,'/Content/Images/browser_download.png')]")
	private WebElement exportSurvey;
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
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
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
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

		return deleted;
	}
	
	public String getStartDT(String tag, String user, String surveyor, String analyzer, boolean allPages) {
		this.setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String tagXPath;
		String userXPath;
		String surveyorXPath;
		String analyzerXPath;
		String startDTXPath;
		
		WebElement tagCell;
		WebElement userCell;
		WebElement surveyorCell;
		WebElement analyzerCell;
		WebElement startDTCell;
		
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
			
			userXPath = strTRXPath + "["+rowNum+"]/td[2]";
			userCell = table.findElement(By.xpath(userXPath));
			
			surveyorXPath = strTRXPath + "["+rowNum+"]/td[3]";
			surveyorCell = table.findElement(By.xpath(surveyorXPath));
			
			analyzerXPath = strTRXPath + "["+rowNum+"]/td[4]";
			analyzerCell = table.findElement(By.xpath(analyzerXPath));			
			
			startDTXPath = strTRXPath + "["+rowNum+"]/td[5]";
			startDTCell = table.findElement(By.xpath(startDTXPath));
			
			if (tagCell.getText().trim().equalsIgnoreCase(tag) && userCell.getText().trim().equalsIgnoreCase(user) &&
					surveyorCell.getText().trim().equalsIgnoreCase(surveyor) && analyzerCell.getText().trim().equalsIgnoreCase(analyzer)) {
				return startDTCell.getText().trim();
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled") && allPages) {
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
		
		return null;
	}
	
	public boolean actionOnDrivingSurveys(String tag, String user, String surveyor, String analyzer, String startDT, String action, boolean allPages) {
		this.setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String tagXPath;
		String userXPath;
		String surveyorXPath;
		String analyzerXPath;
		String startDTXPath;
			
		WebElement tagCell;
		WebElement userCell;
		WebElement surveyorCell;
		WebElement analyzerCell;
		WebElement startDTCell;
		WebElement actionCell;
		
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
			
			userXPath = strTRXPath + "["+rowNum+"]/td[2]";
			userCell = table.findElement(By.xpath(userXPath));
			
			surveyorXPath = strTRXPath + "["+rowNum+"]/td[3]";
			surveyorCell = table.findElement(By.xpath(surveyorXPath));
			
			analyzerXPath = strTRXPath + "["+rowNum+"]/td[4]";
			analyzerCell = table.findElement(By.xpath(analyzerXPath));			
			
			startDTXPath = strTRXPath + "["+rowNum+"]/td[5]";
			startDTCell = table.findElement(By.xpath(startDTXPath));
			
			if (tagCell.getText().trim().equalsIgnoreCase(tag) && userCell.getText().trim().equalsIgnoreCase(user) &&
					surveyorCell.getText().trim().equalsIgnoreCase(surveyor) && analyzerCell.getText().trim().equalsIgnoreCase(analyzer) &&
					startDTCell.getText().trim().equalsIgnoreCase(startDT)) {
				if (action.equalsIgnoreCase(DRIVINGSURVEYSEXPORTSURVEY)) {
					actionCell =this.linkExportSurvey;
				} 
				else if (action.equalsIgnoreCase(DRIVINGSURVEYSEXPORTPEAKS)) {
					actionCell = this.linkExportPeaks;
				} 
				else if (action.equalsIgnoreCase(DRIVINGSURVEYSEXPORTANALYSIS)) {
					actionCell = this.linkExportAnalysis;
				} 
				else
					actionCell = null;				
				
				if (actionCell != null) {
					actionCell.click();
					return true;
				}
				else
					return false;
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled") && allPages) {
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
	
	public boolean validateDatFiles(String type, String tag, String analyzer, String downloadPath, boolean delete) {
		String zipFileNameBase = type;

		String zipFileName = null;
		String datFileName = null;
		
		File dir = new File(downloadPath);
		String[] files = dir.list(); 
		
		for (int i = 0; i < files.length; i++) {
			if (files[i].startsWith(zipFileNameBase) && files[i].endsWith(".zip") && files[i].contains(tag) && files[i].contains(analyzer)) {
				zipFileName = files[i];
				datFileName = zipFileName.replaceFirst(".zip", ".dat");
				
				try {
					BaseHelper.deCompressZipFile(zipFileName, downloadPath, true);
				}
				catch (Exception e) {
					Log.error(e.toString());
					return false;
				}
			}
		}
		
		if (datFileName != null) {		
			if (BaseHelper.validateDatFile(downloadPath + File.separator + datFileName)) {
				if (delete) {
					File file = new File(downloadPath + File.separator + datFileName);
					file.delete();
					
					file = new File(downloadPath + File.separator + zipFileName);
					file.delete();
				}
				
				return true;
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
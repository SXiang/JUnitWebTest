/**
 * 
 */
package surveyor.scommon.source;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import common.source.BaseHelper;
import common.source.BrowserCommands;
import common.source.DatUtility;
import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.Analyzer;
import surveyor.dataaccess.source.CaptureEvent;
import surveyor.dataaccess.source.Measurement;
import surveyor.dataaccess.source.Peak;
import surveyor.dataaccess.source.ReferenceGasBottle;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.StoredProcReferenceGas;
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

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[7]")
	private WebElement firstSurveyStatusLabel;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[8]/a[5]/img")
	private WebElement linkDeleteSurvey;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[11]/a[1]/img")
	private WebElement linkViewSurvey;

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
		setPagination(PAGINATIONSETTING_100);

		this.waitForTableDataToLoad();

		List<String> strListTag = new ArrayList<String>();
		WebElement col1;

		List<WebElement> rows = this.table.findElements(By.xpath(this.strTRXPath));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			col1 = this.table.findElement(By.xpath(this.strTRXPath + "["+rowNum+"]/td[1]"));

			strListTag.add(col1.getText().trim());

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = this.table.findElements(By.xpath(this.strTRXPath));
				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return strListTag;
	}

	public List<String> getDriverSurveysByTag(String driver) {
		List<String> tagList = new ArrayList<String>();

		setPagination(PAGINATIONSETTING_100);
		this.waitForTableDataToLoad();

		String tagXPath;
		String driverXPath; 

		WebElement tagCell;
		WebElement driverCell;

		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			tagXPath = strTRXPath + "["+rowNum+"]/td[1]";
			tagCell = table.findElement(By.xpath(tagXPath));

			driverXPath = strTRXPath + "["+rowNum+"]/td[2]";
			driverCell = table.findElement(By.xpath(driverXPath));

			if (tagCell.getText() != null && driverCell.getText().trim().equalsIgnoreCase(driver)) {
				tagList.add(tagCell.getText().trim());
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return tagList; 
	}

	public boolean deleteDrivingSurveyByTag(String tagName, boolean deleteAll) {
		this.setPagination(PAGINATIONSETTING_100);
		this.waitForTableDataToLoad();

		String tagXPath;
		String deleteImgXPath;

		WebElement tagCell;
		WebElement deleteImg;

		boolean deleted = false;

		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

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

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return deleted;
	}

	public String getStartDT(String tag, String user, String surveyor, String analyzer, boolean allPages) {
		this.setPagination(PAGINATIONSETTING_100);

		this.waitForTableDataToLoad();

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

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

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

			Log.info(String.format("Look for StartDate for Tag-[%s];User=[%s];Surveyor-[%s];Analyzer=[%s]", 
					tag, user, surveyor, analyzer));

			if (tagCell.getText().trim().equalsIgnoreCase(tag) && userCell.getText().trim().equalsIgnoreCase(user) &&
					surveyorCell.getText().trim().equalsIgnoreCase(surveyor) && analyzerCell.getText().trim().equalsIgnoreCase(analyzer)) {
				Log.info(String.format("Found matching StartDate - %s", startDTCell.getText().trim()));
				return startDTCell.getText().trim();
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled") && allPages) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return null;
	}

	public boolean actionOnDrivingSurveys(String tag, String user, String surveyor, String analyzer, String startDT, String action, boolean allPages) {
		this.setPagination(PAGINATIONSETTING_100);

		this.waitForTableDataToLoad();

		String tagXPath;
		String userXPath;
		String surveyorXPath;
		String analyzerXPath;
		String startDTXPath;
		String actionXPath;

		WebElement tagCell;
		WebElement userCell;
		WebElement surveyorCell;
		WebElement analyzerCell;
		WebElement startDTCell;
		WebElement actionCell;

		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

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

			Log.info(String.format("Searching for Tag-[%s];User=[%s];Surveyor-[%s];Analyzer=[%s];StartDate-[%s]", 
					tag, user, surveyor, analyzer, startDT));

			if (tagCell.getText().trim().equalsIgnoreCase(tag) && userCell.getText().trim().equalsIgnoreCase(user) &&
					surveyorCell.getText().trim().equalsIgnoreCase(surveyor) && analyzerCell.getText().trim().equalsIgnoreCase(analyzer) &&
					startDTCell.getText().trim().equalsIgnoreCase(startDT)) {
				Log.info("Found a matching row..");

				if (action.equalsIgnoreCase(DRIVINGSURVEYSEXPORTSURVEY)) {
					actionXPath = strTRXPath + "["+rowNum+"]/td[11]/a[2]/img";
					actionCell = table.findElement(By.xpath(actionXPath));
				} 
				else if (action.equalsIgnoreCase(DRIVINGSURVEYSEXPORTPEAKS)) {
					actionXPath = strTRXPath + "["+rowNum+"]/td[11]/a[3]/img";
					actionCell = table.findElement(By.xpath(actionXPath));
				} 
				else if (action.equalsIgnoreCase(DRIVINGSURVEYSEXPORTANALYSIS)) {
					actionXPath = strTRXPath + "["+rowNum+"]/td[11]/a[4]/img";
					actionCell = table.findElement(By.xpath(actionXPath));
				} 
				else
					actionCell = null;				

				if (actionCell != null) {
					Log.info("Clicking on actionCell...");
					actionCell.click();
					return true;
				}
				else
					return false;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled") && allPages) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
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

	public boolean validateDatFiles(String type, String tag, String analyzer, String downloadPath, String mode, boolean delete) {
		String zipFileNameBase = type;
		System.out.println(zipFileNameBase);
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

					if (datFileName.contains(DRIVINGSURVEYSEXPORTSURVEY)){
						Assert.assertTrue((verifySurveyExportFile(downloadPath + File.separator + datFileName, analyzer)));
					}
/*					else if (datFileName.contains(DRIVINGSURVEYSEXPORTPEAKS)){
						Assert.assertTrue((verifyPeakExportFile(downloadPath + File.separator + datFileName, tag, analyzer, mode)));
					}
					else if (datFileName.contains(DRIVINGSURVEYSEXPORTANALYSIS)){
						Assert.assertTrue((verifyAnalysisExportFile(downloadPath + File.separator + datFileName, tag, analyzer)));
					}
*/				}
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

	public boolean verifyPeakExportFile (String datFileName, String tag, String analyzer, String mode) {

		boolean verifyPeak = false;
		DatUtility dUtil = new DatUtility();
		try {
			dUtil.convertDATtoCSV(datFileName);
			List<HashMap<String,String>> rows = dUtil.getAllRows();
			Map<String, String> map = new HashMap<String, String>();

			List<Peak> listOfDBPeak = Peak.getPeaks(tag, analyzer, mode);

			if (rows.size() > 0 && listOfDBPeak.size() > 0){
				if (rows.size()==listOfDBPeak.size()){

					for(int i=0; i<rows.size(); i++){
						map =rows.get(i);
						verifyPeak=checkPeakInDB(listOfDBPeak,map);
						if(!verifyPeak){
							Log.info("One of the row does not exist in database table.");
							return false;
						}
					}
				}
				else{
					verifyPeak= false;
					Log.info("The number of rows in downloaded file and rows of database table does not match.");
				}
			}
			else if (rows.size()==0 && listOfDBPeak.size()==0){
				verifyPeak=true;
				Log.info("The File and database table both do not have anything.");
			}

		} catch (IOException e) {
			Log.error(e.toString());
		}
		return verifyPeak;
	}

	private boolean checkPeakInDB(List<Peak> listOFDBPeak,Map<String, String> map)
	{
		for (Peak pk : listOFDBPeak){
			if (pk.equalsTo(map)) {
				return true;
			}
		}
		return false;
	}

	public boolean verifySurveyExportFile (String datFileName, String analyzer) {

		boolean verifySurvey=false;
		DatUtility dUtil = new DatUtility();
		try {
			dUtil.convertDATtoCSV(datFileName);
			List<HashMap<String,String>> rows = dUtil.getAllRows();
			Map<String, String> map = new HashMap<String, String>();
			
			List<Measurement> listOfDBMeasurement= Measurement.getMeasurements(analyzer);
			
			if (rows.size() > 0 && listOfDBMeasurement.size() > 0){
				if (rows.size()==listOfDBMeasurement.size()){

					for(int i=0; i<rows.size(); i++){
						map =rows.get(i);
						verifySurvey=checkMeasurementInDB(listOfDBMeasurement,map);
						if(!verifySurvey){
							Log.info("One of the row does not exist in database table.");
							return false;
						}
					}
				}
				else{
					verifySurvey= false;
					Log.info("The number of rows in downloaded file and rows of database table does not match.");
				}
			}
			else if (rows.size()==0 && listOfDBMeasurement.size()==0){
				verifySurvey=true;
				Log.info("The File and database table both do not have anything.");
			}

		} catch (IOException e) {
			Log.error(e.toString());
		}
		return verifySurvey;
	}

	private boolean checkMeasurementInDB(List<Measurement> listOFDBMeasurement,Map<String, String> map)
	{
		for (Measurement ms : listOFDBMeasurement){
			if (ms.equalsTo(map)) {
				return true;
			}
		}
		return false;	
	}

	public boolean verifyAnalysisExportFile (String datFileName, String tag, String analyzer) {

		boolean verifyAnalysis= false;
		DatUtility dUtil = new DatUtility();
		try {
			dUtil.convertDATtoCSV(datFileName);
			List<HashMap<String,String>> rows = dUtil.getAllRows();
			Map<String, String> map = new HashMap<String, String>();

			List<CaptureEvent> listOfDBCaptureEvent = CaptureEvent.getCaptureEvent(tag, analyzer);

			if (rows.size() > 0 && listOfDBCaptureEvent.size() > 0){
				if (rows.size()==listOfDBCaptureEvent.size()){

					for(int i=0; i<rows.size(); i++){
						map =rows.get(i);
						verifyAnalysis=checkAnalysisInDB(listOfDBCaptureEvent,map);
						if(!verifyAnalysis){
							Log.info("One of the row does not exist in database table.");
							return false;
						}
					}
				}
				else{
					verifyAnalysis= false;
					Log.info("The number of rows in downloaded file and rows of database table does not match.");
				}
			}
			else if (rows.size()==0 && listOfDBCaptureEvent.size()==0){
				verifyAnalysis=true;
				Log.info("The File and database table both do not have anything.");
			}

		} catch (IOException e) {
			Log.error(e.toString());
		}
		return verifyAnalysis;
	}

	private boolean checkAnalysisInDB(List<CaptureEvent> listOFDBCaptureEvent,Map<String, String> map)
	{
		for (CaptureEvent cE : listOFDBCaptureEvent){
			if (cE.equalsTo(map)) {
				return true;
			}
		}
		return false;
	}

	public WebElement getFirstViewSurveyLink() {
		return linkViewSurvey;
	}

	public void clickOnFirstViewSurveyLink() {
		this.linkViewSurvey.click();
	}

	@Override
	public void waitForPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(STRPageContentText);
			}
		});
	}

	public void waitForFirstSurveyInTableToBeCompleted() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				BrowserCommands.refresh();
				return firstSurveyStatusLabel.getText().equals("Completed");
			}
		});
	}
}
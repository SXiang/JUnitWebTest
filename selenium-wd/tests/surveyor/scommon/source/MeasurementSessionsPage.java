/**
 * 
 */
package surveyor.scommon.source;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import common.source.BaseHelper;
import common.source.BrowserCommands;
import common.source.DatUtility;
import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.CaptureEvent;
import surveyor.dataaccess.source.Measurement;
import surveyor.dataaccess.source.Peak;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;

import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class MeasurementSessionsPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Home/MeasurementSessions";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.Layout_Nav_DrivingSurveys);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.Layout_Nav_DrivingSurveys);
	public static final String Constant_NoMatchingRecordsFound = Resources.getResource(ResourceKeys.Constant_NoMatchingRecordsFound);
	public static final String Constant_Tag = Resources.getResource(ResourceKeys.Constant_Tag);
	public static final String Constant_User = Resources.getResource(ResourceKeys.Constant_User);
	public static final String Constant_Surveyor = Resources.getResource(ResourceKeys.Constant_Surveyor);
	public static final String Constant_Analyzer = Resources.getResource(ResourceKeys.Constant_Analyzer);
	public static final String ReportSSRS_StartDateTime = Resources.getResource(ResourceKeys.ReportSSRS_StartDateTime);
	public static final String ReportSSRS_EndDateTime = Resources.getResource(ResourceKeys.ReportSSRS_EndDateTime);
	public static final String Constant_Status = Resources.getResource(ResourceKeys.Constant_Status);
	public static final String Constant_Type = Resources.getResource(ResourceKeys.Constant_Type);
	public static final String Surveys_MinAmplitude = Resources.getResource(ResourceKeys.Surveys_MinAmplitude);
	public static final String Surveys_Duration = Resources.getResource(ResourceKeys.Surveys_Duration);
	public static final String Constant_Action = Resources.getResource(ResourceKeys.Constant_Action);

	public static final String DSTAGUA = "dmcs1-sqacusua";
	public static final String DSTAGSU = "dmcs1-sqacussu";
	public static final String DSTAGDR = "dmcs1-sqacusdr";

	private static final String DELETE_POPUP_CONFIRM_BUTTON_XPATH = "//*[@id='myModal']/div/div/div[3]/a[1]";
	private static final String DELETE_POPUP_CANCEL_BUTTON_XPATH = "//*[@id='myModal']/div/div/div[3]/a[2]";
	private static final String ERROR_URL = "Error";

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[7]")
	private WebElement firstSurveyStatusLabel;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[8]/a[5]/img")
	private WebElement linkDeleteSurvey;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[11]/a[1]/img")
	private WebElement linkViewSurvey;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td")
	private WebElement tableData;

	@FindBy(how = How.XPATH, using = "//a[starts-with(@href,'/Reports/DeleteReport?reportType=ComplianceReports')]")
	protected WebElement btnDeleteConfirm;
	protected String btnDeleteConfirmXpath = "//a[starts-with(@href,'/Reports/DeleteReport?reportType=ComplianceReports')]";

	public enum DrivingSurveyButtonType {
		ViewSurvey, ExportSurvey, ExportPeaks, ExportAnalysis, Resubmit, DeleteSurvey;

	}

	public enum UserRoleType {
		Driver("Driver"), Supervisor("Supervisor"), UtilityAdmin("Utility Administrator"), Admin("Administrator");
		private final String name;

		UserRoleType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public MeasurementSessionsPage(WebDriver driver, TestSetup testSetup, String strBaseURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
	}

	public boolean checkVisibilityForDrivingSurveys(String loginUser, UserRoleType userRole, List<String> strListTagCus, List<String> strListTagPic) throws Exception {
		List<String> strListTag = getTagNameList();

		if (userRole == UserRoleType.Admin) {
			return (strListTag.containsAll(strListTagCus) && strListTag.containsAll(strListTagPic));

		} else if (userRole == UserRoleType.UtilityAdmin) {
			if (loginUser.contains(REGBASEPICUSERNAME)) {
				return (!strListTag.contains(strListTagCus) && strListTag.containsAll(strListTagPic));
			} else {
				return (!strListTag.contains(strListTagPic) && strListTag.containsAll(strListTagCus));
			}

		} else if (userRole == UserRoleType.Supervisor) {
			if (loginUser.contains(REGBASEPICUSERNAME)) {
				return (!strListTag.contains(strListTagCus) && strListTag.containsAll(strListTagPic));
			} else {
				return (!strListTag.contains(strListTagPic) && strListTag.containsAll(strListTagCus));
			}

		} else if (userRole == UserRoleType.Driver) {
			if (loginUser.contains(REGBASEPICUSERNAME)) {
				return (!strListTag.contains(strListTagCus) && strListTag.containsAll(strListTagPic));
			} else {
				return (!strListTag.contains(strListTagPic) && strListTag.containsAll(strListTagCus));
			}
		} else {
			throw new Exception("Role Type NOT supported.");

		}
	}

	public boolean actionOnDrivingSurvey(String surveyTag, String user, String surveyor, String analyzer, DrivingSurveyButtonType buttonType) throws Exception {
		HashMap<String, String> userIndexMap = new HashMap<String, String>();
		boolean result = false;
		if (surveyTag != null) {
			userIndexMap.put(Constant_Tag, surveyTag);
		}
		if (user != null) {
			userIndexMap.put(Constant_User, user);
		}
		if (surveyor != null) {
			userIndexMap.put(Constant_Surveyor, surveyor);
		}
		if (analyzer != null) {
			userIndexMap.put(Constant_Analyzer, analyzer);
		}
		By tableContextBy = By.id("datatable_wrapper");
		this.searchTextBox.clear();
		if (surveyTag != null) {
			this.searchTextBox.sendKeys(surveyTag);
		} else if (surveyor != null) {
			this.searchTextBox.sendKeys(surveyor);
		}
		WebElement tableContext = driver.findElement(tableContextBy);
		DataTablePage dataTable = DataTablePage.getDataTablePage(driver, tableContext, this.testSetup, this.strBaseURL, this.strPageURL);
		try {
			WebElement row = dataTable.getMatchingRow(userIndexMap);
			WebElement btn = row.findElement(By.xpath(getButtonXpath(buttonType)));
			btn.click();
			if (buttonType == DrivingSurveyButtonType.DeleteSurvey) {
				this.waitForConfirmDeletePopupToShow();
				this.clickOnConfirmInDeleteReportPopup();
				if (driver.getCurrentUrl().contains(ERROR_URL)) {
					driver.get(strBaseURL + STRURLPath);
				}
			}
			result = true;
		} catch (Exception e) {
			Log.warn(e.toString());
			result = false;
		}
		return result;
	}

	private String getButtonXpath(DrivingSurveyButtonType buttonType) throws Exception {
		String buttonXPath;
		switch (buttonType) {
		case ViewSurvey:
			buttonXPath = "//td[11]/a[1]/img";
			break;
		case ExportSurvey:
			buttonXPath = "//td[11]/a[2]/img";
			break;
		case ExportPeaks:
			buttonXPath = "//td[11]/a[3]/img";
			break;
		case ExportAnalysis:
			buttonXPath = "//td[11]/a[4]/img";
			break;
		case Resubmit:
			buttonXPath = "//td[11]/a[5]/img";
			break;
		case DeleteSurvey:
			buttonXPath = "//td[11]/a[6]/img";
			break;
		default:
			throw new Exception("ButtonType NOT supported.");
		}
		return buttonXPath;
	}

	public List<String> getTagNameList() {
		return getTagNameList(null);
	}

	public List<String> getTagNameList(String driver) {
		List<String> strListTag = new ArrayList<String>();
		if (driver != null) {
			this.searchTextBox.sendKeys(driver);
			this.waitForPageLoad();
			if (tableData.getText().equals(Constant_NoMatchingRecordsFound)) {
				return strListTag;
			}
		}
		setPagination(PAGINATIONSETTING_100);
		this.waitForPageLoad();
		WebElement col1;
		List<WebElement> rows = this.getTable().findElements(By.xpath(this.strTRXPath));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			col1 = this.getTable().findElement(By.xpath(this.strTRXPath + "[" + rowNum + "]/td[1]"));

			strListTag.add(col1.getText().trim());

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.waitForPageLoad();

				List<WebElement> newRows = this.getTable().findElements(By.xpath(this.strTRXPath));
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

	public String getStartDT(String tag, String user, String surveyor, String analyzer, boolean allPages) {
		this.setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

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

		List<WebElement> rows = getTable().findElements(By.xpath(strTRXPath));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			tagXPath = strTRXPath + "[" + rowNum + "]/td[1]";
			tagCell = getTable().findElement(By.xpath(tagXPath));

			userXPath = strTRXPath + "[" + rowNum + "]/td[2]";
			userCell = getTable().findElement(By.xpath(userXPath));

			surveyorXPath = strTRXPath + "[" + rowNum + "]/td[3]";
			surveyorCell = getTable().findElement(By.xpath(surveyorXPath));

			analyzerXPath = strTRXPath + "[" + rowNum + "]/td[4]";
			analyzerCell = getTable().findElement(By.xpath(analyzerXPath));

			startDTXPath = strTRXPath + "[" + rowNum + "]/td[5]";
			startDTCell = getTable().findElement(By.xpath(startDTXPath));

			Log.info(String.format("Look for StartDate for Tag-[%s];User=[%s];Surveyor-[%s];Analyzer=[%s]", tag, user, surveyor, analyzer));

			if (tagCell.getText().trim().equalsIgnoreCase(tag) && userCell.getText().trim().equalsIgnoreCase(user) && surveyorCell.getText().trim().equalsIgnoreCase(surveyor) && analyzerCell.getText().trim().equalsIgnoreCase(analyzer)) {
				Log.info(String.format("Found matching StartDate - %s", startDTCell.getText().trim()));
				return startDTCell.getText().trim();
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled") && allPages) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = getTable().findElements(By.xpath(strTRXPath));
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

	public boolean validateDatFiles(String type, String tag, String analyzer, String downloadPath, String mode, boolean delete) {
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

					if (datFileName.contains(DRIVINGSURVEYSEXPORTSURVEY)) {
						Assert.assertTrue((verifySurveyExportFile(downloadPath + File.separator + datFileName, analyzer)));
					}
					/*
					 * else if (datFileName.contains(DRIVINGSURVEYSEXPORTPEAKS)){ Assert.assertTrue((verifyPeakExportFile(downloadPath + File.separator + datFileName, tag, analyzer, mode))); } else if
					 * (datFileName.contains(DRIVINGSURVEYSEXPORTANALYSIS)){ Assert.assertTrue((verifyAnalysisExportFile(downloadPath + File.separator + datFileName, tag, analyzer))); }
					 */ } catch (Exception e) {
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

	public boolean verifyPeakExportFile(String datFileName, String tag, String analyzer, String mode) {

		boolean verifyPeak = false;
		DatUtility dUtil = new DatUtility();
		try {
			dUtil.convertDATtoCSV(datFileName);
			List<HashMap<String, String>> rows = dUtil.getAllRows();
			Map<String, String> map = new HashMap<String, String>();

			List<Peak> listOfDBPeak = Peak.getPeaks(tag, analyzer, mode);

			if (rows.size() > 0 && listOfDBPeak.size() > 0) {
				if (rows.size() == listOfDBPeak.size()) {

					for (int i = 0; i < rows.size(); i++) {
						map = rows.get(i);
						verifyPeak = checkPeakInDB(listOfDBPeak, map);
						if (!verifyPeak) {
							Log.info("One of the row does not exist in database table.");
							return false;
						}
					}
				} else {
					verifyPeak = false;
					Log.info("The number of rows in downloaded file and rows of database table does not match.");
				}
			} else if (rows.size() == 0 && listOfDBPeak.size() == 0) {
				verifyPeak = true;
				Log.info("The File and database table both do not have anything.");
			}

		} catch (IOException e) {
			Log.error(e.toString());
		}
		return verifyPeak;
	}

	private boolean checkPeakInDB(List<Peak> listOFDBPeak, Map<String, String> map) {
		for (Peak pk : listOFDBPeak) {
			if (pk.equalsTo(map)) {
				return true;
			}
		}
		return false;
	}

	public boolean verifySurveyExportFile(String datFileName, String analyzer) {

		boolean verifySurvey = false;
		DatUtility dUtil = new DatUtility();
		try {
			dUtil.convertDATtoCSV(datFileName);
			List<HashMap<String, String>> rows = dUtil.getAllRows();
			Map<String, String> map = new HashMap<String, String>();

			List<Measurement> listOfDBMeasurement = Measurement.getMeasurements(analyzer);

			if (rows.size() > 0 && listOfDBMeasurement.size() > 0) {
				if (rows.size() == listOfDBMeasurement.size()) {

					for (int i = 0; i < rows.size(); i++) {
						map = rows.get(i);
						verifySurvey = checkMeasurementInDB(listOfDBMeasurement, map);
						if (!verifySurvey) {
							Log.info("One of the row does not exist in database table.");
							return false;
						}
					}
				} else {
					verifySurvey = false;
					Log.info("The number of rows in downloaded file and rows of database table does not match.");
				}
			} else if (rows.size() == 0 && listOfDBMeasurement.size() == 0) {
				verifySurvey = true;
				Log.info("The File and database table both do not have anything.");
			}

		} catch (IOException e) {
			Log.error(e.toString());
		}
		return verifySurvey;
	}

	private boolean checkMeasurementInDB(List<Measurement> listOFDBMeasurement, Map<String, String> map) {
		for (Measurement ms : listOFDBMeasurement) {
			if (ms.equalsTo(map)) {
				return true;
			}
		}
		return false;
	}

	public boolean verifyAnalysisExportFile(String datFileName, String tag, String analyzer) {

		boolean verifyAnalysis = false;
		DatUtility dUtil = new DatUtility();
		try {
			dUtil.convertDATtoCSV(datFileName);
			List<HashMap<String, String>> rows = dUtil.getAllRows();
			Map<String, String> map = new HashMap<String, String>();

			List<CaptureEvent> listOfDBCaptureEvent = CaptureEvent.getCaptureEvent(tag, analyzer);

			if (rows.size() > 0 && listOfDBCaptureEvent.size() > 0) {
				if (rows.size() == listOfDBCaptureEvent.size()) {

					for (int i = 0; i < rows.size(); i++) {
						map = rows.get(i);
						verifyAnalysis = checkAnalysisInDB(listOfDBCaptureEvent, map);
						if (!verifyAnalysis) {
							Log.info("One of the row does not exist in database table.");
							return false;
						}
					}
				} else {
					verifyAnalysis = false;
					Log.info("The number of rows in downloaded file and rows of database table does not match.");
				}
			} else if (rows.size() == 0 && listOfDBCaptureEvent.size() == 0) {
				verifyAnalysis = true;
				Log.info("The File and database table both do not have anything.");
			}

		} catch (IOException e) {
			Log.error(e.toString());
		}
		return verifyAnalysis;
	}

	private boolean checkAnalysisInDB(List<CaptureEvent> listOFDBCaptureEvent, Map<String, String> map) {
		for (CaptureEvent cE : listOFDBCaptureEvent) {
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

	public void clickOnConfirmInDeletePopup() {
		WebElement confirmDelete = this.driver.findElement(By.xpath(DELETE_POPUP_CONFIRM_BUTTON_XPATH));
		confirmDelete.click();
	}

	public void clickOnCancelInDeleteReportPopup() {
		WebElement cancelDelete = this.driver.findElement(By.xpath(DELETE_POPUP_CANCEL_BUTTON_XPATH));
		cancelDelete.click();
	}

	private String getReportTableCellText(String elementXPath) {
		WebElement cellElement = getReportTableCell(elementXPath);
		return cellElement.getText();
	}

	private WebElement getReportTableCell(String elementXPath) {
		boolean retry = false;
		WebElement tableCell = null;
		try {
			tableCell = getTableCell(elementXPath);
		} catch (Exception e) {
			retry = true;
		}
		if (retry) {
			this.waitForPageLoad();
			refreshPageUntilElementFound(elementXPath);
		}

		return getTableCell(elementXPath);
	}

	private WebElement getTableCell(String elementXPath) {
		return getTable().findElement(By.xpath(elementXPath));
	}

	public void clickOnConfirmInDeleteReportPopup() {
		WebElement confirmDelete = this.driver.findElement(By.xpath(DELETE_POPUP_CONFIRM_BUTTON_XPATH));
		confirmDelete.click();
	}

	@Override
	public void waitForPageLoad() {
		waitForAJAXCallsToComplete();
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(STRPageContentText);
			}
		});
	}

	public void waitForConfirmDeletePopupToShow() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				(new WebDriverWait(driver, timeout + 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("myModal")));
				WebElement confirmDeletePopupSection = d.findElement(By.id("myModal"));
				return confirmDeletePopupSection.getAttribute("style").contains("display:block") || confirmDeletePopupSection.getAttribute("style").contains("display: block");
			}
		});
	}

	public void waitForConfirmDeletePopupToClose() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				(new WebDriverWait(driver, timeout + 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("myModal")));
				WebElement confirmDeletePopupSection = d.findElement(By.id("myModal"));
				return confirmDeletePopupSection.getAttribute("style").contains("display:none") || confirmDeletePopupSection.getAttribute("style").contains("display: none");
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
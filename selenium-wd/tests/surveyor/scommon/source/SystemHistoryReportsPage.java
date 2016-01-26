/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.ACTIONTIMEOUT;
import static surveyor.scommon.source.SurveyorConstants.ENDDATE;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;
import static surveyor.scommon.source.SurveyorConstants.STARTDATE;
import static surveyor.scommon.source.SurveyorConstants.SURVEYORUNIT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONE;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.source.BaseHelper;
import common.source.DBConnection;
import common.source.DateUtility;
import common.source.Log;
import common.source.PDFUtility;
import common.source.TestSetup;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.StoredProcSystemHistory;

/**
 * @author pmahajan
 * 
 */
public class SystemHistoryReportsPage extends ReportsBasePage {
	public static final String STRURLPath = "/Reports/SystemHistoryReports";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.SystemHistoryReports_PageTitle);
	public static final String STRReportTitle = Resources.getResource(ResourceKeys.SystemHistoryReports_ReportTitle);
	public static final String STRPaginationMsg = "Showing 1 to ";
	public static final String STRRptSubHeading = Resources.getResource(ResourceKeys.SystemHistoryReports_PageSubTitle);
	public static final String STRRptColumnDate = Resources.getResource(ResourceKeys.SystemHistoryReports_DateColumn);
	public static final String STRRptColumnUser = Resources.getResource(ResourceKeys.SystemHistoryReports_UserNameColumn);
	public static final String STRRptColumnNote = Resources.getResource(ResourceKeys.SystemHistoryReports_NoteColumn);

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public SystemHistoryReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);

		System.out.format("\nThe System History Report Page URL is: %s\n", this.strPageURL);
	}

	private void addNewReport(String title, String timeZone, String surUnit, String startDate, String endDate) {

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnNewSysHistoryRpt.click();

		this.inputTitle.clear();
		this.inputTitle.sendKeys(title);

		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if ((timeZone).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}

		if (surUnit != "") {
			List<WebElement> optionsSU = this.cbSurveyUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
		}

		DatetimepickerSetting dateSetting = new DatetimepickerSetting(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
		PageFactory.initElements(driver, dateSetting);

		dateSetting.setDay("start", 0, startDate, false);
		dateSetting.setDay("end", 0, endDate, false);

		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);

		this.btnOK.click();
	}

	public void addNewPDReport(String reportTitle) {
		this.addNewReport(reportTitle, TIMEZONE, SURVEYORUNIT, STARTDATE, ENDDATE);
	}

	public void addNewPDReport(String reportTitle, String timezone, String surveyor, String startDate, String endDate) {
		this.addNewReport(reportTitle, timezone, surveyor, startDate, endDate);
	}

	public boolean checkActionStatus(String rptTitle, String strCreatedBy) {
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;

		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				long startTime = System.currentTimeMillis();
				long elapsedTime = 0;
				boolean bContinue = true;

				while (bContinue) {
					try {
						this.btnDownload.click();
						testSetup.slowdownInSeconds(15);
						return true;
					} catch (org.openqa.selenium.NoSuchElementException e) {
						elapsedTime = System.currentTimeMillis() - startTime;
						if (elapsedTime >= (ACTIONTIMEOUT * 1000)) {
							return false;
						}
						continue;
					}
				}
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

	public boolean findExistingReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;

		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
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

	public boolean validatePdfFiles(String reportTitle, String downloadPath) {
		String reportId;
		String reportName;
		DBConnection objDbConn = new DBConnection();

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		try {
			reportId = objDbConn.getIdOfSpecifiedReportTitle(reportTitle, this.testSetup);
			reportId = reportId.substring(0, 6);
			Log.info(reportId);
			Log.info(String.valueOf(reportId.length()));
			reportName = "SH-" + reportId;
			Log.info(reportName);
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}
		String pdfFile1;
		pdfFile1 = downloadPath + reportName + ".pdf";

		boolean result = false;
		result = BaseHelper.validatePdfFileForSysHis(pdfFile1);
		return result;
	}

	public boolean checkPaginationSetting(String numberOfReports) {
		setPagination(numberOfReports);
		testSetup.slowdownInSeconds(3);
		String msgToVerify = STRPaginationMsg + numberOfReports;
		String actualText = this.paginationMsg.getText().substring(0, 15);

		if (actualText.compareTo(msgToVerify) <= 0)
			return true;

		return false;
	}

	public boolean verifyCancelButtonFunctionality() {
		this.btnNewSysHistoryRpt.click();
		this.btnCancel.click();
		testSetup.slowdownInSeconds(3);

		if (isElementPresent(strNewSysHistoryRpt))
			return true;

		return false;
	}

	public boolean verifyStaticTextinPDF(String downloadPath, String reportTitle) {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String fullDownloadPath = downloadPath + "SH-" + reportId.substring(0, 6) + ".pdf";
		try {
			String pdfInText = (pdfUtility.extractPDFText(fullDownloadPath));
			if (!pdfInText.contains(STRReportTitle)) {
				return false;
			}
			if (!pdfInText.contains(STRRptSubHeading)) {
				return false;
			}
			if (!pdfInText.contains(STRRptColumnDate)) {
				return false;
			}
			if (!pdfInText.contains(STRRptColumnUser)) {
				return false;
			}
			if (!pdfInText.contains(STRRptColumnNote)) {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean verifyUserInputInPDF(String downloadPath, String reportTitle, ArrayList<String> inputs) {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String fullDownloadPath = downloadPath + "SH-" + reportId.substring(0, 6) + ".pdf";
		try {
			String pdfInText = (pdfUtility.extractPDFText(fullDownloadPath));
			Iterator<String> inputIterator = inputs.iterator();
			while (inputIterator.hasNext()) {
				if (!pdfInText.contains(inputIterator.next())) {
					return false;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean verifyNotesTable(String downloadPath, String reportTitle) {
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String fullDownloadPath = downloadPath + "SH-" + reportId.substring(0, 6) + ".pdf";
		ArrayList<StoredProcSystemHistory> notesReturnList = tokenizeSystemHistoryNotesTable(fullDownloadPath);
		ArrayList<StoredProcSystemHistory> objStoredProcSystemHistory = StoredProcSystemHistory.getSystemHistory(reportId);

		for (StoredProcSystemHistory storedProcSystemHistory : objStoredProcSystemHistory) {
			if (!storedProcSystemHistory.isInList(notesReturnList)) {
				System.out.print("failed");
				return false;
			}
		}

		return true;
	}

	public ArrayList<StoredProcSystemHistory> tokenizeSystemHistoryNotesTable(String fullPathtoPdf) {
		PDFUtility pdfUtility = new PDFUtility();
		DateUtility date = new DateUtility();
		String pdfInText;
		ArrayList<StoredProcSystemHistory> notesList = new ArrayList<StoredProcSystemHistory>();
		try {
			pdfInText = (pdfUtility.extractPDFText(fullPathtoPdf));
			InputStream inputStream = new ByteArrayInputStream(pdfInText.getBytes());
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;

			while ((line = bufferReader.readLine()) != null) {
				if (line.matches("^\\d.*")) {
					if (!line.contains("Date Printed")) {
						if (line.length() > 20) {
							String dateFormat = date.getDateFormatRegex(true);
							Pattern pattern = Pattern.compile(dateFormat);
							Matcher matcher = pattern.matcher(line);
							if (matcher.find()) {
								StoredProcSystemHistory storedProcObj = new StoredProcSystemHistory();
								storedProcObj.setDateCreated(matcher.group(0));
								String remaining = line.substring(matcher.end()).trim();
								storedProcObj.setUserName(remaining.substring(0, remaining.indexOf(" ")).trim());
								storedProcObj.setNote(remaining.substring(remaining.indexOf(" ")).trim());
								notesList.add(storedProcObj);
							}

						}
					}
				}
			}
		} catch (IOException e) {
			Log.info(e.toString());
		}

		return notesList;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
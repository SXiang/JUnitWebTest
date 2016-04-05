/**
 * 
 */
package surveyor.scommon.source;

import static common.source.RegexUtility.REGEX_PATTERN_EXTRACT_LINES_STARTING_WITH_DIGITS;
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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.BaseHelper;
import common.source.DBConnection;
import common.source.DateUtility;
import common.source.Log;
import common.source.PDFUtility;
import common.source.TestSetup;
import surveyor.dataaccess.source.Analyzer;
import surveyor.dataaccess.source.ReferenceGasBottle;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.StoredProcReferenceGas;
import surveyor.dataaccess.source.StoredProcSystemHistory;

/**
 * @author pmahajan
 * 
 */
public class ReferenceGasReportsPage extends ReportsBasePage {
	public static final String STRURLPath = "/Reports/ReferenceGasReports";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.RefGasReports_PageTitle);
	public static final String STRReportTitle = Resources.getResource(ResourceKeys.RefGasReport_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.RefGasReportSSRS_ReferenceGasReport);
	public static final String STRReportSubTitle = Resources.getResource(ResourceKeys.RefGasReportSSRS_ReferenceGasResultTable);
	public static final String STRReportTableColumnDate = Resources.getResource(ResourceKeys.RefGasReportSSRS_InstallationDateTime);
	public static final String STRReportTableColumnUserName = Resources.getResource(ResourceKeys.ReportSSRS_UserName);
	public static final String STRReportTableColumnLotNumber = Resources.getResource(ResourceKeys.RefGasReportSSRS_LotNumber);
	public static final String STRReportTableColumnIsotopic = Resources.getResource(ResourceKeys.RefGasReportSSRS_IsotopicValueUncertainty);
	public static final String STRReportTableColumnTestResult = Resources.getResource(ResourceKeys.RefGasReportSSRS_TestResult);

	public static final String STRPaginationMsg = "Showing 1 to ";
	public String LotNumberInReport = null;

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public ReferenceGasReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);

		Log.info("\nThe Reference Gas Report Page URL is: %s\n"+ this.strPageURL);
	}

	private void addNewReport(String title, String timeZone, String surUnit, String startDate, String endDate) {

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnNewRefGasRpt.click();

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

		DatetimePickerSetting dateSetting = new DatetimePickerSetting(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
		PageFactory.initElements(driver, dateSetting);

		dateSetting.setDay("start", 0, startDate, false);
		dateSetting.setDay("end", 0, endDate, false);

		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);

		this.btnOK.click();
	}

	public void addNewReport(String title, String timeZone, String surUnit, String startDate, String endDate, int noOfPreStartMonth, int noOfPreEndMonth) {

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnNewRefGasRpt.click();

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

		DatetimePickerSetting dateSetting = new DatetimePickerSetting(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
		PageFactory.initElements(driver, dateSetting);

		dateSetting.setDay("start", noOfPreStartMonth, startDate, false);
		dateSetting.setDay("end", noOfPreEndMonth, endDate, false);

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
		Log.info(String.valueOf(rowSize));
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

	public boolean findReport(String rptTitle, String strCreatedBy) {
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
			reportName = "RG-" + reportId;
			Log.info(reportName);
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}
		String pdfFile1;
		pdfFile1 = downloadPath + reportName + ".pdf";

		boolean result = false;
		result = BaseHelper.validatePdfFileForRefGas(pdfFile1);
		return result;
	}

	public boolean verifyStaticTextInPDF(String downloadPath, String reportTitle) {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String fullDownloadPath = downloadPath + "RG-" + reportId.substring(0, 6) + ".pdf";
		try {
			String pdfInText = (pdfUtility.extractPDFText(fullDownloadPath));
			if (!pdfInText.contains(STRReportTitle)) {
				return false;
			}
			if (!pdfInText.contains(STRReportSubTitle)) {
				return false;
			}
			if (!pdfInText.contains(STRReportTableColumnDate)) {
				return false;
			}
			if (!pdfInText.contains(STRReportTableColumnUserName)) {
				return false;
			}
			if (!pdfInText.contains(STRReportTableColumnLotNumber)) {
				return false;
			}
			if (!pdfInText.contains(STRReportTableColumnTestResult)) {
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
		String fullDownloadPath = downloadPath + "RG-" + reportId.substring(0, 6) + ".pdf";
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

	public boolean verifyResultTable(String downloadPath, String reportTitle) {
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String fullDownloadPath = downloadPath + "RG-" + reportId.substring(0, 6) + ".pdf";
		ArrayList<StoredProcReferenceGas> notesReturnList = tokenizeSystemHistoryNotesTable(fullDownloadPath);
		ArrayList<StoredProcReferenceGas> objStoredProcReferenceGas = StoredProcReferenceGas.getReferenceGas(reportId);
		String analyzerId = objStoredProcReferenceGas.get(0).getAnalyzerId();
		Analyzer objAnalyzer = Analyzer.getAnalyzer(analyzerId);
		ReferenceGasBottle objReferenceGasBottle = ReferenceGasBottle.getReferenceGasBottleBySurveorId(objAnalyzer.getSurveyorUnitId().toString());

		for (StoredProcReferenceGas storedProcReferenceGas : objStoredProcReferenceGas) {
			if (!storedProcReferenceGas.isInList(notesReturnList)) {
				return false;
			}
		}
		if (!objReferenceGasBottle.getBatchId().trim().equals(LotNumberInReport)) {
			return false;
		}

		return true;
	}

	public ArrayList<StoredProcReferenceGas> tokenizeSystemHistoryNotesTable(String fullPathtoPdf) {
		PDFUtility pdfUtility = new PDFUtility();
		String pdfInText;
		ArrayList<StoredProcReferenceGas> resultsList = new ArrayList<StoredProcReferenceGas>();
		try {
			pdfInText = (pdfUtility.extractPDFText(fullPathtoPdf));
			InputStream inputStream = new ByteArrayInputStream(pdfInText.getBytes());
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;

			try {
				while ((line = bufferReader.readLine()) != null) {
					if (line.matches(REGEX_PATTERN_EXTRACT_LINES_STARTING_WITH_DIGITS)) {
						if (!line.contains("Date Printed")) {
							if (line.length() > 45) {
								StoredProcReferenceGas storedProcRefGas = new StoredProcReferenceGas();
								String parts[] = line.split("\\s+");
								String firstPart[] = Arrays.copyOfRange(parts, 0, 4);
								String dateInstalled = String.join(" ", firstPart);
								storedProcRefGas.setInstallationDate(dateInstalled);
								storedProcRefGas.setUserName(parts[4]);
								LotNumberInReport = parts[5].trim();
								String[] uncertaintyDelta = parts[6].split("/");
								String delta = uncertaintyDelta[0].replace("+", "").trim();
								String uncertainty = uncertaintyDelta[1].replace("-", "").trim();
								storedProcRefGas.setUncertainty(uncertainty);
								storedProcRefGas.setDelta(delta);
								storedProcRefGas.setTestResult(parts[7]);
								resultsList.add(storedProcRefGas);
							}
						}
					}
				}
			} finally {
				bufferReader.close();
			}
		} catch (IOException e) {
			Log.info(e.toString());
		}

		return resultsList;

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
		this.btnNewRefGasRpt.click();
		this.waitForNewPageLoad();
		this.btnCancel.click();
		this.waitForPageLoad();
		if (isElementPresent(strNewRefGasRpt))
			return true;

		return false;
	}
	
    @Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRReportTitle);
            }
        });
    }

	public void waitForNewPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRNewPageContentText);
            }
        });
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
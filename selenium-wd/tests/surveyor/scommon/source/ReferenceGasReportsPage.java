/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.ACTIONTIMEOUT;
import static surveyor.scommon.source.SurveyorConstants.ENDDATE;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.STARTDATE;
import static surveyor.scommon.source.SurveyorConstants.SURVEYORUNIT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONE;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import common.source.BaseHelper;
import common.source.DBConnection;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

/**
 * @author pmahajan
 * 
 */
public class ReferenceGasReportsPage extends ReportsBasePage {
	public static final String STRURLPath = "/Reports/ReferenceGasReports";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.RefGasReports_PageTitle);
	public static final String STRPaginationMsg = "Showing 1 to ";
	
	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public ReferenceGasReportsPage(WebDriver driver, String strBaseURL,
			TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);

		System.out.format("\nThe Reference Gas Report Page URL is: %s\n",
				this.strPageURL);
	}

	private void addNewReport(String title, String timeZone, String surUnit,
			String startDate, String endDate) {

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnNewRefGasRpt.click();

		this.inputTitle.clear();
		this.inputTitle.sendKeys(title);

		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By
				.tagName("option"));
		for (WebElement option : optionsTZ) {
			if ((timeZone).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}

		if (surUnit != "") {
			List<WebElement> optionsSU = this.cbSurveyUnit.findElements(By
					.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
		}

		DatetimepickerSetting dateSetting = new DatetimepickerSetting(driver,
				testSetup, strBaseURL, strBaseURL + STRURLPath);
		PageFactory.initElements(driver, dateSetting);

		dateSetting.setDay("start", 0, startDate, false);
		dateSetting.setDay("end", 0, endDate, false);

		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);

		this.btnOK.click();
	}
	
	public void addNewReport(String title, String timeZone, String surUnit,
			String startDate, String endDate, int noOfPreStartMonth, int noOfPreEndMonth) {

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnNewRefGasRpt.click();

		this.inputTitle.clear();
		this.inputTitle.sendKeys(title);

		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By
				.tagName("option"));
		for (WebElement option : optionsTZ) {
			if ((timeZone).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}

		if (surUnit != "") {
			List<WebElement> optionsSU = this.cbSurveyUnit.findElements(By
					.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
		}

		DatetimepickerSetting dateSetting = new DatetimepickerSetting(driver,
				testSetup, strBaseURL, strBaseURL + STRURLPath);
		PageFactory.initElements(driver, dateSetting);

		dateSetting.setDay("start", noOfPreStartMonth, startDate, false);
		dateSetting.setDay("end", noOfPreEndMonth, endDate, false);

		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);

		this.btnOK.click();
	}

	public void addNewPDReport(String reportTitle) {
		this.addNewReport(reportTitle, TIMEZONE, SURVEYORUNIT, STARTDATE,
				ENDDATE);
	}

	public void addNewPDReport(String reportTitle, String timezone,
			String surveyor, String startDate, String endDate) {
		this.addNewReport(reportTitle, timezone, surveyor, startDate, endDate);
	}

	public boolean checkActionStatus(String rptTitle, String strCreatedBy) {
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		System.out.println(rowSize);
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[3]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle)
					&& createdByCell.getText().trim()
							.equalsIgnoreCase(strCreatedBy)) {
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

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));
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

	public boolean findReport(String rptTitle, String strCreatedBy) {
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[3]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle)
					&& createdByCell.getText().trim()
							.equalsIgnoreCase(strCreatedBy)) {
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));
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
	
	public boolean validatePdfFiles(String reportTitle, String downloadPath) {
		String reportId;
		String reportName;
		DBConnection objDbConn = new DBConnection();

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		try {
			reportId = objDbConn.getIdOfSpecifiedReportTitle(reportTitle, this.testSetup);
			reportId = reportId.substring(0, 6);
			System.out.println(reportId);
			System.out.println(reportId.length());
			reportName = "RG-" + reportId;
			System.out.println(reportName);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		String pdfFile1;
		pdfFile1 = downloadPath + reportName + ".pdf";
		
		boolean result = false;
		result = BaseHelper.validatePdfFileForRefGas(pdfFile1);
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
		this.btnNewRefGasRpt.click();
		this.btnCancel.click();
		testSetup.slowdownInSeconds(3);
		
		if(isElementPresent(strNewRefGasRpt))
			return true;

		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
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

import common.source.TestSetup;

/**
 * @author pmahajan
 * 
 */
public class SystemHistoryReportsPage extends ReportsBasePage {
	public static final String STRURLPath = "/Reports/SystemHistoryReports";
	public static final String STRPageTitle = "System History Reports - Surveyor";
	public static final String STRPaginationMsg = "Showing 1 to ";
	
	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public SystemHistoryReportsPage(WebDriver driver, String strBaseURL,
			TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);

		System.out.format("\nThe System History Report Page URL is: %s\n",
				this.strPageURL);
	}

	private void addNewReport(String title, String timeZone, String surUnit,
			String startDate, String endDate) {

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnNewSysHistoryRpt.click();

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
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[2]";

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

	public boolean findExistingReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);

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
					+ "]/td[2]";

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
		
		if(isElementPresent(strNewSysHistoryRpt))
			return true;

		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
/**
 * 
 */
package surveyor.scommon.source;

import static org.junit.Assert.fail;
import static common.source.BaseHelper.singlePatternMatching;
import static common.source.BaseHelper.patternMatchingforPairs;
import common.source.DateUtility;
import static surveyor.scommon.source.SurveyorConstants.ACTIONTIMEOUT;
import static surveyor.scommon.source.SurveyorConstants.APPRNAME;
import static surveyor.scommon.source.SurveyorConstants.APPRSIG;
import static surveyor.scommon.source.SurveyorConstants.CGIINV;
import static surveyor.scommon.source.SurveyorConstants.CUSBOUNDARY;
import static surveyor.scommon.source.SurveyorConstants.ENDDATE;
import static surveyor.scommon.source.SurveyorConstants.GAPINV;
import static surveyor.scommon.source.SurveyorConstants.REXCLUSIONRADIUS;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.LISAINV;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RPTCRTDATE;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.REPORTMODES;
import static surveyor.scommon.source.SurveyorConstants.REPORTMODES1;
import static surveyor.scommon.source.SurveyorConstants.REPORTTITLE;
import static surveyor.scommon.source.SurveyorConstants.STARTDATE;
import static surveyor.scommon.source.SurveyorConstants.SURVEYORUNIT;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.TAG;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONE;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import common.source.BaseHelper;
import common.source.DBConnection;
import common.source.FileUtility;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

import common.source.PDFUtility;
import common.source.BaseHelper;

/**
 * @author zlu
 *
 */
public class ComplianceReportsPage extends ReportsBasePage {
	private static final int CUSTOM_BOUNDARY_RADBUTTON_GROUP_IDX = 0;
	private static final int CUSTOMER_BOUNDARY_RADBUTTON_GROUP_IDX = 1;
	public static final String STRURLPath = "/Reports/ComplianceReports";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.ComplianceReports_PageTitle);
	public static final String STRPaginationMsg = "Showing 1 to ";
	public static final String STRSurveyIncludedMsg = Resources.getResource(ResourceKeys.ComplianceReport_AlreadyAdded);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ComplianceReports_PageTitle);

	private String reportName;

	@FindBy(how = How.ID, using = "pdf")
	protected WebElement pdfImg;

	@FindBy(how = How.ID, using = "zip-file_pdf")
	protected WebElement zipImg;

	@FindBy(how = How.ID, using = "zip-file_shapefile")
	protected WebElement zipShape;

	@FindBy(how = How.ID, using = "zip-file_reportmeta")
	protected WebElement zipMeta;

	@FindBy(name = "rdAreaMode")
	private List<WebElement> areaBoundaryRadioButtons;

	@FindBy(id = "btn-select-boundary")
	protected WebElement boundarySelectorBtn;

	@FindBy(id = "dvAreaMode_Customer")
	protected WebElement divCustomerBoundarySection;

	@FindBy(id = "dvAreaMode_Custom")
	protected WebElement divCustomBoundarySection;

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public ComplianceReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);

		System.out.format("\nThe Compliance Reports Page URL is: %s\n", this.strPageURL);
	}

	// more generic interface and implementation, more details will be added
	// into the reports objects
	public void addNewReport(Reports reportsCompliance) {
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		this.btnNewComplianceRpt.click();

		this.inputTitle.clear();
		this.inputTitle.sendKeys(reportsCompliance.getRptTitle());

		if (reportsCompliance.getCustomer() != null && reportsCompliance.getCustomer() != "Picarro") {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if ((reportsCompliance.getCustomer()).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}

			if (this.isElementPresent(btnChangeCustomerXPath)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", btnChangeCustomer);

				// temporary bypass the issue DE456
				this.inputTitle.clear();
				this.inputTitle.sendKeys(reportsCompliance.getRptTitle());
			}
		}

		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if ((reportsCompliance.getTimeZone()).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}

		this.inputExclusionRadius.clear();
		this.inputExclusionRadius.sendKeys(reportsCompliance.getExclusionRadius());

		this.inputNELat.sendKeys(reportsCompliance.getNELat());
		this.inputNELong.sendKeys(reportsCompliance.getNELong());
		this.inputSWLat.sendKeys(reportsCompliance.getSWLat());
		this.inputSWLong.sendKeys(reportsCompliance.getSWLong());

		if (reportsCompliance.getSurveyorUnit() != "") {
			List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((reportsCompliance.getSurveyorUnit()).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
		}

		if (reportsCompliance.getTag() != "") {
			this.cbTag.sendKeys(reportsCompliance.getTag());
		}

		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);
		this.btnSurveySearch.click();

		this.checkboxSurFirst.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnAddSurveys.click();

		this.inputImgMapHeight.clear();
		this.inputImgMapHeight.sendKeys(reportsCompliance.getImageMapHeight());
		this.inputImgMapWidth.clear();
		this.inputImgMapWidth.sendKeys(reportsCompliance.getImageMapWidth());

		addViews(reportsCompliance.customer, reportsCompliance.getViewList());

		List<Map<String, String>> tablesList = reportsCompliance.getTablesList();
		if (tablesList.get(0).get(KEYINDTB).equalsIgnoreCase("1")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", checkBoxIndTb);
		}
		if (tablesList.get(0).get(KEYISOANA).equalsIgnoreCase("1")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", checkBoxIsoAna);
		}
		if (tablesList.get(0).get(KEYPCA).equalsIgnoreCase("1")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", checkBoxPCA);
		}
		if (tablesList.get(0).get(KEYPCRA).equalsIgnoreCase("1")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", checkBoxPCRA);
		}

		this.checkBoxOtherPla.click();
		this.checkBoxPEPla.click();
		this.checkBoxProtectedSteel.click();
		this.checkBoxUnProtectedSteel.click();
		this.checkBoxCastIron.click();
		this.checkBoxCopper.click();

		this.checkBoxDistrict.click();
		this.checkBoxDistrictPlat.click();

		this.btnOK.click();
	}

	private void addViews(String customer, List<Map<String, String>> viewList) {
		int rowNum;
		int colNum;
		String strBaseXPath;

		for (int i = 0; i < viewList.size(); i++) {
			if (i != 0) {
				this.btnAddViews.click();
			}

			rowNum = i + 1;
			if (viewList.get(i).get(KEYVIEWNAME) != null) {
				colNum = 2;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/input";
				driver.findElement(By.xpath(strBaseXPath)).clear();
				driver.findElement(By.xpath(strBaseXPath)).sendKeys(viewList.get(i).get(KEYVIEWNAME));
			}

			if (viewList.get(i).get(KEYLISA).equalsIgnoreCase("1")) {
				colNum = 3;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}

			if (viewList.get(i).get(KEYFOV).equalsIgnoreCase("1")) {
				colNum = 4;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}

			if (viewList.get(i).get(KEYBREADCRUMB).equalsIgnoreCase("1")) {
				colNum = 5;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}

			if (viewList.get(i).get(KEYINDICATIONS).equalsIgnoreCase("1")) {
				colNum = 6;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}

			if (viewList.get(i).get(KEYISOTOPICCAPTURE).equalsIgnoreCase("1")) {
				colNum = 7;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}

			if (viewList.get(i).get(KEYANNOTATION).equalsIgnoreCase("1")) {
				colNum = 8;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}

			if (viewList.get(i).get(KEYGAPS).equalsIgnoreCase("1")) {
				colNum = 9;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}

			if (viewList.get(i).get(KEYASSETS).equalsIgnoreCase("1")) {
				colNum = 10;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}

			if (viewList.get(i).get(KEYBOUNDARIES).equalsIgnoreCase("1")) {
				colNum = 11;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}

			if (customer.equalsIgnoreCase("sqacus")) {
				colNum = 10;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/select";
				WebElement dropdownBaseMap = driver.findElement(By.xpath(strBaseXPath));

				List<WebElement> options = dropdownBaseMap.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if ((viewList.get(i).get(KEYBASEMAP)).equalsIgnoreCase(option.getText().trim())) {
						option.click();
					}
				}
			} else {
				colNum = 12;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/select";
				WebElement dropdownBaseMap = driver.findElement(By.xpath(strBaseXPath));

				List<WebElement> options = dropdownBaseMap.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if ((viewList.get(i).get(KEYBASEMAP)).equalsIgnoreCase(option.getText().trim())) {
						option.click();
					}
				}
			}
		}
	}

	// Nov. 1, 2014, this is obsolete and try not to call this for generating a
	// compliance report.
	// Temporary solution for now and should pass the params by a data structure
	private void addNewReport(String title, String customer, String timeZone, String exclusionRadius, String boundary, String imageMapHeight, String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong, String surUnit, String tag, String startDate, String endDate, String surModeFilter) {
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnNewComplianceRpt.click();

		this.inputTitle.clear();
		this.inputTitle.sendKeys(title);

		if (customer != null && customer != "Picarro") {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if ((customer).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}

			if (this.isElementPresent(btnChangeCustomerXPath)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", btnChangeCustomer);

				this.inputTitle.clear();
				this.inputTitle.sendKeys(title);
			}
		}

		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if ((timeZone).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}

		this.inputExclusionRadius.clear();
		this.inputExclusionRadius.sendKeys(exclusionRadius);

		this.inputNELat.sendKeys(NELat);
		this.inputNELong.sendKeys(NELong);
		this.inputSWLat.sendKeys(SWLat);
		this.inputSWLong.sendKeys(SWLong);

		if (surUnit != "") {
			List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
		}

		if (tag != "") {
			this.cbTag.clear();
			this.cbTag.sendKeys(tag);
		}

		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);

		this.btnSurveySearch.click();
		this.checkboxSurFirst.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnAddSurveys.click();

		this.inputViewLisa.click();
		this.inputViewFOV.click();
		this.inputViewBreadCrumb.click();
		this.inputViewInd.click();
		this.inputViewIso.click();
		this.inputViewAnno.click();
		this.inputViewGaps.click();
		this.inputViewAssets.click();
		this.inputViewBoundaries.click();

		this.inputImgMapHeight.clear();
		this.inputImgMapHeight.sendKeys(imageMapHeight);
		this.inputImgMapWidth.clear();
		this.inputImgMapWidth.sendKeys(imageMapWidth);

		this.checkBoxOtherPla.click();
		this.checkBoxPEPla.click();
		this.checkBoxProtectedSteel.click();
		this.checkBoxUnProtectedSteel.click();
		this.checkBoxCastIron.click();
		this.checkBoxCopper.click();

		this.checkBoxDistrict.click();
		this.checkBoxDistrictPlat.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkBoxIndTb);
		this.checkBoxIsoAna.click();
		this.checkBoxPCA.click();
		this.checkBoxPCRA.click();

		this.btnOK.click();
	}

	public boolean checkActionStatus(String rptTitle, String strCreatedBy) {
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		setPagination(PAGINATIONSETTING);
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;
		WebElement rptTitleCell;
		WebElement createdByCell;

		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

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
						if (rowSize == 1) {
							this.btnReportViewer = table.findElement(By.xpath("//*[@id='datatable']/tbody/tr/td[5]/a[3]"));
							this.btnReportViewer.click();
						} else {
							this.btnReportViewer = table.findElement(By.xpath("//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[3]/img"));
							this.btnReportViewer.click();
						}
						String srcPdfImg = this.pdfImg.getAttribute("src");
						String srcZipImg = this.zipImg.getAttribute("src");
						String srcZipMeta = this.zipMeta.getAttribute("src");
						String srcShapeImg = this.zipShape.getAttribute("src");

						if (srcPdfImg.contains("pdf") && srcZipImg.contains("zip") && srcZipMeta.contains("zip") && srcShapeImg.contains("zip")) {
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click();", pdfImg);
							testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
							this.zipImg.click();
							testSetup.slowdownInSeconds(15);
							this.zipMeta.click();
							testSetup.slowdownInSeconds(15);
							this.zipShape.click();
							testSetup.slowdownInSeconds(15);
							return true;
						}

						if (srcPdfImg.contains("pdf") && srcZipImg.contains("zip")) {
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click();", pdfImg);
							testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
							this.zipImg.click();
							testSetup.slowdownInSeconds(15);
							return true;
						} else
							return false;
					} catch (org.openqa.selenium.NoSuchElementException e) {
						elapsedTime = System.currentTimeMillis() - startTime;
						if (elapsedTime >= (ACTIONTIMEOUT * 1000)) {
							return false;
						}

						continue;
					} catch (NullPointerException ne) {
						System.out.println("Null Pointer Exception: " + ne);
						fail("Report failed to generate!!");
					}
				}
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
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

	public boolean checkActionStatusInSeconds(String rptTitle, String strCreatedBy, int seconds) {
		this.testSetup.slowdownInSeconds(seconds);

		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;
		String pdfImgXPath;
		String zipImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement pdfImg;
		WebElement zipImg;

		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				try {
					pdfImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[3]/img";
					pdfImg = table.findElement(By.xpath(pdfImgXPath));
					String srcPdfImg = pdfImg.getAttribute("src");

					zipImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[4]/img";
					zipImg = table.findElement(By.xpath(zipImgXPath));
					String srcZipImg = zipImg.getAttribute("src");

					if (srcPdfImg.contains("pdf") && srcZipImg.contains("zip")) {
						pdfImg.click();

						testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
						zipImg.click();

						testSetup.slowdownInSeconds(15);

						return true;
					} else
						return false;
				} catch (org.openqa.selenium.NoSuchElementException e) {
					return false;
				}
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
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

	public void addNewPDReport(String reportTitle) {
		this.addNewReport(reportTitle, null, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH, RNELAT, RNELON, RSWLAT, RSWLON, SURVEYORUNIT, TAG, STARTDATE, ENDDATE, REPORTMODES1);
	}

	public void addNewPDReport(String reportTitle, String customer) {
		this.addNewReport(reportTitle, customer, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH, RNELAT, RNELON, RSWLAT, RSWLON, SURVEYORUNIT, TAG, STARTDATE, ENDDATE, REPORTMODES1);
	}

	public void addNewPDReport(String reportTitle, String surveyor, String tag) {
		this.addNewReport(reportTitle, null, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH, RNELAT, RNELON, RSWLAT, RSWLON, surveyor, tag, STARTDATE, ENDDATE, REPORTMODES1);
	}

	public void addNewPDReport(String reportTitle, String customer, String surveyor, String tag) {
		this.addNewReport(reportTitle, customer, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH, RNELAT, RNELON, RSWLAT, RSWLON, surveyor, tag, STARTDATE, ENDDATE, REPORTMODES1);
	}

	public void addNewPDReport(String reportTitle, String surveyor, List<String> tag, boolean changeMode, String reportMode) {
		this.addNewReport(reportTitle, null, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH, RNELAT, RNELON, RSWLAT, RSWLON, surveyor, tag, STARTDATE, ENDDATE, changeMode, reportMode);
	}

	public boolean findReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;

		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
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

	public boolean deleteReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;
		String deleteImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement deleteImg;

		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				deleteImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[1]/img";
				deleteImg = table.findElement(By.xpath(deleteImgXPath));

				deleteImg.click();

				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

				if (this.isElementPresent(btnDeleteReportXPath)) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", btnDeleteReport);

					if (this.isElementPresent(errorMsgDeleteCompliacneReportXPath)) {
						this.btnReturnToHomePage.click();
						return false;
					} else
						return true;
				} else
					return false;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
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

	public boolean copyReport(String rptTitle, String strCreatedBy, String rptTitleNew) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;
		String copyImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement copyImg;

		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				copyImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[2]/img";
				copyImg = table.findElement(By.xpath(copyImgXPath));

				copyImg.click();

				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

				this.inputTitle.clear();
				this.inputTitle.sendKeys(rptTitleNew);

				this.btnOK.click();

				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
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
			reportName = "CR-" + reportId;
			setReportName(reportName);
			BaseHelper.deCompressZipFile(reportName, downloadPath);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		String nameBase = reportTitle.trim().replaceAll(" ", "_");
		String pdfFile1;
		String pdfFile2;
		String pdfFile3;

		pdfFile1 = downloadPath + reportName + ".pdf";

		pdfFile3 = downloadPath + reportName + File.separator + nameBase.replaceAll("_", "") + ".pdf";

		if (BaseHelper.validatePdfFile(pdfFile1) && BaseHelper.validatePdfFile(pdfFile3)) {
			try {
				if (!BaseHelper.compareTwoFilesByContent(pdfFile1, pdfFile3))
					return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else
			return false;

		pdfFile2 = downloadPath + reportName + File.separator + nameBase.replaceAll("_", "") + "_First View.pdf";

		if (!BaseHelper.validatePdfFile(pdfFile2))
			return false;

		return true;
	}

	public boolean validatePdfFiles(ReportsCompliance reportsCompliance, String downloadPath) {
		String reportId;
		String reportName;
		DBConnection objDbConn = new DBConnection();
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		try {
			Thread.sleep(10000);
			reportId = objDbConn.getIdOfSpecifiedReportTitle(reportsCompliance.getRptTitle(), this.testSetup);
			reportId = reportId.substring(0, 6);
			reportName = "CR-" + reportId;
			setReportName(reportName);
			this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
			BaseHelper.deCompressZipFile(reportName, downloadPath);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		String nameBase = reportsCompliance.getRptTitle().trim().replaceAll(" ", "_");
		String viewName;
		String pdfFile1;
		String pdfFile2;
		String pdfFile3;

		List<Map<String, String>> viewList = reportsCompliance.getViewList();

		pdfFile1 = downloadPath + reportName + ".pdf";
		pdfFile3 = downloadPath + reportName + File.separator + nameBase.replaceAll("_", "") + ".pdf";

		if (BaseHelper.validatePdfFile(pdfFile1) && BaseHelper.validatePdfFile(pdfFile3)) {
			try {
				if (!BaseHelper.compareTwoFilesByContent(pdfFile1, pdfFile3))
					return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else
			return false;

		for (int i = 0; i < viewList.size(); i++) {
			viewName = viewList.get(i).get(KEYVIEWNAME);
			pdfFile2 = downloadPath + reportName + File.separator + nameBase.replaceAll("_", "") + "_" + viewName + ".pdf";

			if (!BaseHelper.validatePdfFile(pdfFile2)) {
				System.out.println("PDF Validation failed for: " + pdfFile2);
				return false;
			}
		}

		return true;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportTitle) {
		this.reportName = reportTitle;
	}

	/*
	 * Method to compare text in pdf file with expected
	 */
	public boolean comparePDFText(String actualPath, String targetFileName) {
		String actualReport = actualPath + reportName.trim() + ".pdf";
		PDFUtility pdfUtility;
		pdfUtility = new PDFUtility();
		try {
			String expectedReport = TestSetup.getRootPath() + "\\selenium-wd\\data\\test-data\\compliancereport-tests" + targetFileName;
			String expectedReportString = pdfUtility.extractPDFText(expectedReport);
			String actualReportString = pdfUtility.extractPDFText(actualReport);
			if (expectedReportString.equals(actualReportString))
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Method to compare the report creation date with current date & Report creation date format with locale
	 * 
	 * @param actualPath
	 *            - actual path to the generated report
	 * @return boolean - true or false based on whether the report creation date matches the current date and format matches the locale format
	 * @throws IOException
	 */

	public boolean validateReportCreationDate(String actualPath) throws IOException {
		String reportDate = null;
		String actualReport = actualPath + reportName.trim() + ".pdf";
		PDFUtility pdfUtility = new PDFUtility();
		String actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);
		// System.out.println(actualReportString);
		String[] lines = actualReportString.split("\\n");
		Pattern pattertoMatch = Pattern.compile("Report Creation Date");
		for (String line : lines) {
			String formatteLine = line.trim();
			if (pattertoMatch.matcher(line).find()) {
				Matcher matcher = pattertoMatch.matcher(formatteLine);
				matcher.find();
				reportDate = formatteLine.substring(matcher.end() + 1).trim();
			}
		}
		DateUtility date = new DateUtility();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY hh:mm a zzz");
		String currentDate = dateFormat.format(new Date()).toString();
		if (date.compareDateTimeFormat(reportDate, true) && (date.compareDates(currentDate.toString(), reportDate, true))) {
			return true;
		}
		return false;
	}

	/**
	 * Method to compare the static text in the first page including the report title, other static texts and report parameter table that appears on the left
	 * 
	 * @param actualPath
	 *            - actual path to the generated report
	 * @return boolean - true or false based on whether the report text matches the given text
	 */

	public boolean compareComplianceRptFirstPageStaticText(String actualPath) {
		PDFUtility pdfUtility;
		pdfUtility = new PDFUtility();
		String actualReport = actualPath + reportName.trim() + ".pdf";
		String actualReportString = null;
		try {
			actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		List<String> staticString = new ArrayList<String>();
		staticString.add(REPORTTITLE);
		staticString.add(LISAINV);
		staticString.add(GAPINV);
		staticString.add(CGIINV);
		staticString.add(APPRNAME);
		staticString.add(APPRSIG);
		staticString.add(RPTCRTDATE);

		HashMap<String, Boolean> actualFirstPage = singlePatternMatching(actualReportString, staticString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		return true;
	}

	/**
	 * Method to verify the report parameter table that appears on the left side of the first page
	 * 
	 * @param actual
	 *            path to the generated report
	 * @return true or false based on whether the report text matches the given text
	 */
	public boolean compareComplianceRptFirstPageTable(String actualPath, HashMap<String, String> inputMap) {
		PDFUtility pdfUtility;
		pdfUtility = new PDFUtility();
		String actualReport = actualPath + reportName.trim() + ".pdf";
		String actualReportString = null;
		try {
			actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<String> tableStrings = new ArrayList<String>();
		tableStrings.add("Map Height & Width");
		tableStrings.add("Time Zone");
		tableStrings.add("Exclusion Radius");
		tableStrings.add("Report Mode");
		tableStrings.add("NE Lat & NE Long");
		tableStrings.add("SW Lat & SW Long");

		HashMap<String, String> actualFirstPage = patternMatchingforPairs(actualReportString, tableStrings);
		if (actualFirstPage.equals(inputMap)) {
			return true;
		}
		return false;
	}

	/**
	 * Method to verify rest of the compliance report text
	 * 
	 * @param actual
	 *            path to the generated report
	 * @return true or false based on whether the report text matches the given text
	 */
	public boolean verifyRestofComplianceReportText(String actualPath) {

		List<String> page2Static = new ArrayList<String>();
		page2Static.add("Show Coverage");
		page2Static.add("Percent Coverage Assets");
		page2Static.add("Percent Coverage Report Area");
		page2Static.add("Percent Coverage Forecast");
		page2Static.add("Coverage Values");
		page2Static.add("Total Linear Asset Coverage");
		page2Static.add("Percent Coverage Report Area");
		page2Static.add("Layers");
		page2Static.add("Asset");
		page2Static.add("Copper");
		page2Static.add("Un-protected Steel");
		page2Static.add("Protected Steel");
		page2Static.add("Cast Iron");
		page2Static.add("Other Plastic");
		page2Static.add("PE Plastic");
		page2Static.add("Boundary");
		page2Static.add("District Plat");
		page2Static.add("District");
		page2Static.add("PE Plastic");
		page2Static.add("View Table");
		page2Static.add("Selected Driving Surveys");
		page2Static.add("Isotopic Analysis Table");

		PDFUtility pdfUtility;
		pdfUtility = new PDFUtility();
		String actualReport = actualPath + reportName.trim() + ".pdf";
		String actualReportString = null;
		try {
			actualReportString = pdfUtility.extractPDFText(actualReport, 2, -1);
			// System.out.println(actualReportString);
			HashMap<String, Boolean> actualFirstPage = singlePatternMatching(actualReportString, page2Static);
			for (Boolean value : actualFirstPage.values()) {
				if (!value)
					return false;
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return true;
	}

	/**
	 * Method to verify the images appear on the compliance report
	 * 
	 * @param actual
	 *            path to the generated report
	 * @return true or false based on whether the images match the given images
	 */
	public boolean compareComplianceRptImages(String actualPath, String baselinePath) {
		String actualReport = actualPath + reportName.trim() + ".pdf";
		PDFUtility pdfUtility;
		pdfUtility = new PDFUtility();
		try {
			String imageDirectory = pdfUtility.extractPDFImages(actualReport, Paths.get(actualPath).getFileName().toString() + "_", 1, 2);
			System.out.println(imageDirectory);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Method to verify the images appear on Views
	 * 
	 * @param actual
	 *            path to the generated report
	 * @return true or false based on whether the images match the given images
	 */
	public boolean compareViewImages(String actualPath, String baselinePath) {
		String actualReport = actualPath + reportName.trim() + ".pdf";
		PDFUtility pdfUtility;
		pdfUtility = new PDFUtility();
		try {
			String imageDirectory = pdfUtility.extractPDFImages(actualReport, Paths.get(actualPath).getFileName().toString() + "_", 1, 2);
			System.out.println(imageDirectory);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean copyReportAndModifyDetails(String rptTitle, String strCreatedBy, String rptTitleNew, String surUnit, List<String> tag, boolean changeMode, String strReportMode) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;
		String copyImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement copyImg;

		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				copyImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[2]/img";
				copyImg = table.findElement(By.xpath(copyImgXPath));

				copyImg.click();

				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

				if (changeMode) {
					if (strReportMode.contentEquals("S1"))
						this.radioBtnS1Mode.click();
					else if (strReportMode.contentEquals(Resources.getResource(ResourceKeys.Constant_Standard)))
						this.radioBtnStndMode.click();
					else if (strReportMode.contentEquals("Rapid Response"))
						this.radioBtnRRmode.click();
					else if (strReportMode.contentEquals(Resources.getResource(ResourceKeys.Constant_Manual)))
						this.radioBtnManualMode.click();

					if (this.isElementPresent(btnChangeModeXPath)) {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].click();", btnChangeMode);
					}
					if (surUnit != "") {
						List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
						for (WebElement option : optionsSU) {
							if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
								option.click();
							}
						}
					}

					for (String tagValue : tag) {
						if (tagValue != "") {
							this.cbTag.clear();
							this.cbTag.sendKeys(tagValue);
							this.btnSurveySearch.click();
							if (testSetup.isRunningDebug())
								testSetup.slowdownInSeconds(3);
							testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
							this.checkboxSurFirst.click();
							testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
							this.btnAddSurveys.click();
						}
					}
				}

				this.inputTitle.clear();
				this.inputTitle.sendKeys(rptTitleNew);
				this.inputViewInd.click();
				this.inputViewIso.click();
				this.inputViewAnno.click();
				this.inputViewAssets.click();
				this.inputViewBoundaries.click();
				this.btnOK.click();

				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
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

	public void addNewReport(String title, String customer, String timeZone, String exclusionRadius, String boundary, String imageMapHeight, String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong, String surUnit, List<String> tag, String startDate, String endDate, boolean changeMode, String strReportMode) {
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnNewComplianceRpt.click();

		if (customer != null && customer != "Picarro") {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if ((customer).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}

			if (this.isElementPresent(btnChangeModeXPath)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", btnChangeMode);
			}
		}

		if (changeMode) {
			if (strReportMode.contentEquals("S1"))
				this.radioBtnS1Mode.click();
			else if (strReportMode.contentEquals(Resources.getResource(ResourceKeys.Constant_Standard)))
				this.radioBtnStndMode.click();
			else if (strReportMode.contentEquals("Rapid Response"))
				this.radioBtnRRmode.click();
			else if (strReportMode.contentEquals(Resources.getResource(ResourceKeys.Constant_Manual)))
				this.radioBtnManualMode.click();
		}

		this.inputTitle.clear();
		this.inputTitle.sendKeys(title);

		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if ((timeZone).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}

		this.inputExclusionRadius.clear();
		this.inputExclusionRadius.sendKeys(exclusionRadius);
		this.inputImgMapHeight.sendKeys(imageMapHeight);
		this.inputImgMapWidth.sendKeys(imageMapWidth);
		this.inputNELat.sendKeys(NELat);
		this.inputNELong.sendKeys(NELong);
		this.inputSWLat.sendKeys(SWLat);
		this.inputSWLong.sendKeys(SWLong);

		this.checkBoxIndTb.click();
		this.checkBoxIsoAna.click();
		this.checkBoxPCA.click();
		this.checkBoxPCRA.click();

		this.checkBoxOtherPla.click();
		this.checkBoxPEPla.click();
		this.checkBoxProtectedSteel.click();
		this.checkBoxUnProtectedSteel.click();
		this.checkBoxCastIron.click();
		this.checkBoxCopper.click();

		if (surUnit != "") {
			List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
		}

		for (String tagValue : tag) {
			if (tagValue != "") {
				this.cbTag.clear();
				this.cbTag.sendKeys(tagValue);
				this.btnSurveySearch.click();
				if (testSetup.isRunningDebug())
					testSetup.slowdownInSeconds(3);
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.checkboxSurFirst.click();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.btnAddSurveys.click();
			}
		}

		this.inputViewLisa.click();
		this.inputViewFOV.click();
		this.inputViewBreadCrumb.click();
		this.inputViewInd.click();
		this.inputViewIso.click();
		this.inputViewAnno.click();
		this.inputViewAssets.click();
		this.inputViewBoundaries.click();
		this.btnOK.click();
	}

	public boolean checkBlankReportErrorTextPresent() {
		this.btnNewComplianceRpt.click();
		this.btnOK.click();
		if (isElementPresent(strErrorText))
			return true;
		return false;
	}

	public boolean searchReport(String reportTitle, String reportCreatedBy) {
		this.inputSearchReport.sendKeys(reportTitle);

		if (this.tdReportTitle.getText().contentEquals(reportTitle)) {
			if (this.tdReportCreatedBy.getText().contentEquals(reportCreatedBy))
				return true;
		}
		return false;
	}

	public void addNewReportWithMultipleSurveysIncluded(Reports reportsCompliance) {
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnNewComplianceRpt.click();

		this.inputTitle.clear();
		this.inputTitle.sendKeys(reportsCompliance.getRptTitle());

		if (reportsCompliance.getCustomer() != null && reportsCompliance.getCustomer() != "Picarro") {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if ((reportsCompliance.getCustomer()).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}

			if (this.isElementPresent(btnChangeCustomerXPath)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", btnChangeCustomer);

				// temporary bypass the issue DE456
				this.inputTitle.clear();
				this.inputTitle.sendKeys(reportsCompliance.getRptTitle());
			}
		}

		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if ((reportsCompliance.getTimeZone()).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}

		this.inputExclusionRadius.clear();
		this.inputExclusionRadius.sendKeys(reportsCompliance.getExclusionRadius());

		this.inputImgMapHeight.sendKeys(reportsCompliance.getImageMapHeight());
		this.inputImgMapWidth.sendKeys(reportsCompliance.getImageMapWidth());
		this.inputNELat.sendKeys(reportsCompliance.getNELat());
		this.inputNELong.sendKeys(reportsCompliance.getNELong());
		this.inputSWLat.sendKeys(reportsCompliance.getSWLat());
		this.inputSWLong.sendKeys(reportsCompliance.getSWLong());

		List<Map<String, String>> tablesList = reportsCompliance.getTablesList();
		if (tablesList.get(0).get(KEYINDTB).equalsIgnoreCase("1")) {
			new Actions(driver).moveToElement(checkBoxIndTb).click();
			// this.checkBoxIndTb.click();
		}
		if (tablesList.get(0).get(KEYISOANA).equalsIgnoreCase("1")) {
			this.checkBoxIsoAna.click();
		}
		if (tablesList.get(0).get(KEYPCA).equalsIgnoreCase("1")) {
			this.checkBoxPCA.click();
		}
		if (tablesList.get(0).get(KEYPCRA).equalsIgnoreCase("1")) {
			this.checkBoxPCRA.click();
		}

		this.checkBoxOtherPla.click();
		this.checkBoxPEPla.click();
		this.checkBoxProtectedSteel.click();
		this.checkBoxUnProtectedSteel.click();
		this.checkBoxCastIron.click();
		this.checkBoxCopper.click();

		if (reportsCompliance.getSurveyorUnit() != "") {
			List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((reportsCompliance.getSurveyorUnit()).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
		}

		for (String tagValue : reportsCompliance.tagList) {
			if (tagValue != "") {
				this.cbTag.clear();
				this.cbTag.sendKeys(tagValue);
				this.btnSurveySearch.click();
				if (testSetup.isRunningDebug())
					testSetup.slowdownInSeconds(3);
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.checkboxSurFirst.click();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.btnAddSurveys.click();
			}
		}

		addViews(reportsCompliance.customer, reportsCompliance.getViewList());

		this.btnOK.click();
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
		this.btnNewComplianceRpt.click();
		this.btnCancel.click();
		testSetup.slowdownInSeconds(3);

		if (isElementPresent(strBtnNewCompRpt))
			return true;

		return false;
	}

	public void openNewComplianceReportPage() {
		this.btnNewComplianceRpt.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
	}

	public void clickOnCopyReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;
		String copyImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement copyImg;

		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				copyImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[2]/img";
				copyImg = table.findElement(By.xpath(copyImgXPath));

				copyImg.click();
				break;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}
	}

	public boolean verifySurveyAlreadyAdded(String customer, String surveyTag) {
		if (customer != null && customer != "Picarro") {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if ((customer).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}

			if (this.isElementPresent(btnChangeModeXPath)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", btnChangeMode);
			}
		}

		if (surveyTag != "") {
			this.cbTag.clear();
			this.cbTag.sendKeys(surveyTag);
			this.btnSurveySearch.click();
			if (testSetup.isRunningDebug())
				testSetup.slowdownInSeconds(3);
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
			this.checkboxSurFirst.click();
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
			this.btnAddSurveys.click();
		}

		if (isElementPresent(strFirstSurveyTag)) {
			if (surveyTag != "") {
				this.cbTag.clear();
				this.cbTag.sendKeys(surveyTag);
				this.btnSurveySearch.click();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.checkboxSurFirst.click();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.btnAddSurveys.click();

				if (this.btnAddSurveys.getAttribute("value").equalsIgnoreCase(STRSurveyIncludedMsg))
					return true;
			}
		}
		return false;
	}

	public boolean deleteSurveyAndIncludeAgain(String surveyTag) {
		this.btnDeleteDrivingSurvey.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		try {
			driver.findElement(By.xpath(strFirstSurveyTag));
			return false;
		} catch (NoSuchElementException e) {
			if (surveyTag != "") {
				this.cbTag.clear();
				this.cbTag.sendKeys(surveyTag);
				this.btnSurveySearch.click();
				if (testSetup.isRunningDebug())
					testSetup.slowdownInSeconds(3);
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.checkboxSurFirst.click();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.btnAddSurveys.click();
			}

			if (isElementPresent(strFirstSurveyTag))
				return true;

			return false;
		}
	}

	public String provideLatLongAtCustomBoundarySelectorWindow(List<String> listBoundary) {
		String actualMsg = "";
		this.btnNewComplianceRpt.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.inputImgMapHeight.sendKeys(listBoundary.get(0));
		this.inputImgMapWidth.sendKeys(listBoundary.get(1));
		this.btnLatLongSelector.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				// driver.manage().window().maximize();

				this.inputCustomNELat.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomNELat.sendKeys(listBoundary.get(2));
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomNELong.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomNELong.sendKeys(listBoundary.get(3));
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLat.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLat.sendKeys(listBoundary.get(4));
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLong.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLong.sendKeys(listBoundary.get(5));
				this.btnCustomOK.click();
				actualMsg = this.btnCustomOK.getText();
			}
		}
		// driver.close();
		// driver.switchTo().window(parentWindow);
		return actualMsg;
	}

	public void clickOnNewComplianceReportBtn() {
		this.btnNewComplianceRpt.click();
	}

	public void clickOnCancelBtn() {
		this.btnCancel.click();
	}

	public void clickOnFirstCopyComplianceBtn() {
		this.btnFirstCopyCompliance.click();
	}

	public void clickOnFirstInvestigateComplianceBtn() {
		this.btnFirstInvestigateCompliance.click();
	}

	public void openCustomerBoundarySelector() {
		this.selectCustomerBoundaryRadioButton();
		this.waitForCustomerBoundarySectionToShow();
		this.clickBoundarySelectorBtn();
	}

	public void openLatLongAreaSelector() {
		this.selectCustomBoundaryRadioButton();
		this.waitForCustomBoundarySectionToShow();
		this.clickLatLongMapSelectorBtn();
	}

	public void clickLatLongMapSelectorBtn() {
		this.latLongMapSelectorBtn.click();
	}

	public void clickBoundarySelectorBtn() {
		this.boundarySelectorBtn.click();
	}

	public void selectCustomBoundaryRadioButton() {
		this.areaBoundaryRadioButtons.get(CUSTOM_BOUNDARY_RADBUTTON_GROUP_IDX).click();
	}

	public void selectCustomerBoundaryRadioButton() {
		this.areaBoundaryRadioButtons.get(CUSTOMER_BOUNDARY_RADBUTTON_GROUP_IDX).click();
	}

	private void waitForCustomBoundarySectionToShow() {
		WebElement dvAreaModeCustom = this.divCustomBoundarySection;
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !dvAreaModeCustom.getAttribute("style").contains("display:none");
			}
		});
	}

	private void waitForCustomerBoundarySectionToShow() {
		WebElement dvAreaModeCustomer = this.divCustomerBoundarySection;
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !dvAreaModeCustomer.getAttribute("style").contains("display:none");
			}
		});
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
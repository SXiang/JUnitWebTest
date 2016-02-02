/**
 * 
 */
package surveyor.scommon.source;

import static org.junit.Assert.fail;
import static common.source.BaseHelper.matchSinglePattern;
import static common.source.BaseHelper.matchPatternforPairs;
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
import static surveyor.scommon.source.SurveyorConstants.REPORTMODES1;
import static surveyor.scommon.source.SurveyorConstants.REPORTTITLE;
import static surveyor.scommon.source.SurveyorConstants.STARTDATE;
import static surveyor.scommon.source.SurveyorConstants.SURVEYORUNIT;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.TAG;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;

import static surveyor.scommon.source.SurveyorConstants.KEYASSETCASTIRON;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCOPPER;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETOTHERPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPEPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETUNPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICT;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICTPLAT;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.source.BaseHelper;
import common.source.CSVUtility;
import common.source.DBConnection;
import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ReportGap;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import common.source.PDFUtility;

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
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ComplianceReports_AddNew);
	public static final String STRCopyPageTitle = Resources.getResource(ResourceKeys.ComplianceReport_PageTitle);

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

	@FindBy(id = "report-survey-start-dt")
	protected WebElement startDatePicker;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxAllSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxStndSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxRRSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxManualSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxOperatorSurvey;

	@FindBy(id = "datatableSurveys")
	protected WebElement surveysTable;

	@FindBy(how = How.XPATH, using = "//div[@id='surveyContent-0']//button[@class='btn btnDeleteSurvey btn-sm btn-danger']")
	protected WebElement btnDeleteSurvey;

	@FindBy(how = How.XPATH, using = "//table[@id='datatable']/tbody/tr")
	protected List<WebElement> numberofRecords;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[1]")
	protected WebElement tdCReportTitle;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[3]")
	protected WebElement tdCReportCreatedBy;

	@FindBy(how = How.XPATH, using = "//a[starts-with(@href,'/Reports/DeleteReport?reportType=ComplianceReports')]")
	protected WebElement btnDeleteConfirm;
	protected String btnDeleteConfirmXpath = "//a[starts-with(@href,'/Reports/DeleteReport?reportType=ComplianceReports')]";

	@FindBy(how = How.XPATH, using = "//*[@id='Standard']")
	protected WebElement checkBoxStndRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='Rapid Response']")
	protected WebElement checkBoxRRRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='Manual']")
	protected WebElement checkBoxManualRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='surveyModal']/div/div/div[3]/a[1]")
	protected WebElement btnChangeRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='dvErrorText']/ul/li[1]")
	protected WebElement areaErrorText;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable_filter']/label/input")
	protected WebElement textBoxReportSerach;

	@FindBy(how = How.XPATH, using = "//*[@class='dataTables_empty']")
	protected WebElement dataTableEmpty;

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
		openNewReportPage();
		inputReportTitle(reportsCompliance.getRptTitle());

		if (reportsCompliance.getCustomer() != null && reportsCompliance.getCustomer() != "Picarro") {
			selectCustomer(reportsCompliance.getCustomer());
			Boolean confirmed = confirmInChangeCustomerDialog();
			if (confirmed) {
				inputReportTitle(reportsCompliance.getRptTitle());
			}
		}

		selectTimeZone(reportsCompliance.getTimeZone());

		if (reportsCompliance.reportModeFilter != null) {
			selectReportMode(reportsCompliance.reportModeFilter);
		}

		inputExclusionRadius(reportsCompliance.getExclusionRadius());

		fillCustomBoundaryTextFields(reportsCompliance.getNELat(), reportsCompliance.getNELong(), reportsCompliance.getSWLat(), reportsCompliance.getSWLong());

		addSurveyInformation(reportsCompliance.getSurveyorUnit(), reportsCompliance.getUserName(), reportsCompliance.getTag(), reportsCompliance.getSurveyStartDate(), reportsCompliance.getSurveyEndDate(), reportsCompliance.getSurveyModeFilter(), reportsCompliance.getGeoFilter());

		inputImageMapHeight(reportsCompliance.getImageMapHeight());
		inputImageMapWidth(reportsCompliance.getImageMapWidth());

		addViews(reportsCompliance.getCustomer(), reportsCompliance.getViewList());

		List<Map<String, String>> tablesList = reportsCompliance.getTablesList();
		if (tablesList.get(0).get(KEYINDTB).equalsIgnoreCase("1")) {
			selectIndicationsTableCheckBox();
		}
		if (tablesList.get(0).get(KEYISOANA).equalsIgnoreCase("1")) {
			selectIsotopicAnalysisCheckBox();
		}
		if (tablesList.get(0).get(KEYPCA).equalsIgnoreCase("1")) {
			selectPercentCoverageAssetCheckBox();
		}
		if (tablesList.get(0).get(KEYPCRA).equalsIgnoreCase("1")) {
			selectPercentCoverageReportArea();
		}

		handleOptionalViewLayersSection(tablesList);

		this.btnOK.click();
	}

	public void selectTimeZone(String timeZone) {
		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if (timeZone.equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}
	}

	public void inputReportTitle(String rptTitle) {
		this.inputTitle.clear();
		this.inputTitle.sendKeys(rptTitle);
	}

	public void inputImageMapWidth(String imageMapWidth) {
		this.inputImgMapWidth.clear();
		this.inputImgMapWidth.sendKeys(imageMapWidth);
	}

	public void inputImageMapHeight(String imageMapHeight) {
		this.inputImgMapHeight.clear();
		this.inputImgMapHeight.sendKeys(imageMapHeight);
	}

	public void inputExclusionRadius(String exclusionRadius) {
		this.inputExclusionRadius.clear();
		this.inputExclusionRadius.sendKeys(exclusionRadius);
	}

	public void inputFOVOpacity(String fovOpacity) {
		this.inputFOVOpacity.clear();
		this.inputFOVOpacity.sendKeys(fovOpacity);
	}

	public void inputLISAOpacity(String lisaOpacity) {
		this.inputLISAOpacity.clear();
		this.inputLISAOpacity.sendKeys(lisaOpacity);
	}

	public void fillCustomBoundaryTextFields(String neLat, String neLong, String swLat, String swLong) {
		this.inputNELat.sendKeys(neLat);
		this.inputNELong.sendKeys(neLong);
		this.inputSWLat.sendKeys(swLat);
		this.inputSWLong.sendKeys(swLong);
	}

	public void addSurveyInformation(String surveyor, String username, String tag, String startDate, String endDate, SurveyModeFilter surveyModeFilter, Boolean geoFilterOn) {
		Log.info("Adding Survey information");

		if (surveyor != null) {
			List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if (surveyor.equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
		}

		if (username != null) {
			this.userName.sendKeys(username);
		}

		if (tag != "") {
			this.cbTag.clear();
			this.cbTag.sendKeys(tag);
		}
		// TO DO: Implement date picker

		if (surveyModeFilter != null) {
			switch (surveyModeFilter) {
			case All:
				this.inputSurModeFilterAll.click();
				break;
			case Standard:
				this.inputSurModeFilterStd.click();
				break;
			case Operator:
				this.inputSurModeFilterOperator.click();
				break;
			case RapidResponse:
				this.inputSurModeFilterRapidResponse.click();
				break;
			case Manual:
				this.inputSurModeFilterManual.click();
				break;
			default:
				break;
			}
		}

		if (geoFilterOn != null) {
			if (geoFilterOn) {
				this.checkGeoFilter.click();
			}
		}

		this.btnSurveySearch.click();
		this.waitForSurveyTabletoLoad();

		this.checkboxSurFirst.click();
		this.btnAddSurveys.click();
	}

	public void selectGapCheckBox() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkBoxGap);
	}

	public void selectPercentCoverageReportArea() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkBoxPCRA);
	}

	public void selectPercentCoverageAssetCheckBox() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkBoxPCA);
	}

	public void selectPercentCoverageForecastCheckBox() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkBoxPCF);
	}

	public void selectIsotopicAnalysisCheckBox() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkBoxIsoAna);
	}

	public void selectIndicationsTableCheckBox() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkBoxIndTb);
	}

	public void selectSurveySurveyor(String surveyorUnit) {
		List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
		for (WebElement option : optionsSU) {
			if (surveyorUnit.equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}
	}

	public ReportModeFilter getReportMode(String reportMode) {
		ReportModeFilter mode = ReportModeFilter.Manual;
		if (reportMode.equalsIgnoreCase("standard")) {
			mode = ReportModeFilter.Standard;
		} else if (reportMode.equalsIgnoreCase("manual")) {
			mode = ReportModeFilter.Manual;
		} else if (reportMode.equalsIgnoreCase("rr")) {
			mode = ReportModeFilter.RapidResponse;
		}
		return mode;
	}

	public void selectCustomer(String customer) {
		if (dropdownCustomer.isDisplayed()) {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if (customer.equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}

			confirmInChangeCustomerDialog();
		}
	}

	public boolean confirmInChangeCustomerDialog() {
		if (dropdownCustomer.isDisplayed()) {
			if (this.isElementPresent(btnChangeCustomerXPath)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", btnChangeCustomer);
				return true;
			}
		}
		return false;
	}

	private void openNewReportPage() {
		this.btnNewComplianceRpt.click();
		this.waitForNewPageLoad();
	}

	public void inputSurveyTag(String tag) {
		this.cbTag.clear();
		this.cbTag.sendKeys(tag);
	}

	public void inputSurveyUsername(String username) {
		this.userName.clear();
		this.userName.sendKeys(username);
	}

	private void handleOptionalViewLayersSection(List<Map<String, String>> viewLayersList) {
		if (viewLayersList != null) {
			boolean selectAssetCastIron = viewLayersList.get(0).get(KEYASSETCASTIRON).equalsIgnoreCase("1");
			boolean selectAssetCopper = viewLayersList.get(0).get(KEYASSETCOPPER).equalsIgnoreCase("1");
			boolean selectAssetOtherPlastic = viewLayersList.get(0).get(KEYASSETOTHERPLASTIC).equalsIgnoreCase("1");
			boolean selectAssetPEPlastic = viewLayersList.get(0).get(KEYASSETPEPLASTIC).equalsIgnoreCase("1");
			boolean selectAssetProtectedSteel = viewLayersList.get(0).get(KEYASSETPROTECTEDSTEEL).equalsIgnoreCase("1");
			boolean selectAssetUnprotectedSteel = viewLayersList.get(0).get(KEYASSETUNPROTECTEDSTEEL).equalsIgnoreCase("1");
			selectViewLayerAssets(selectAssetCastIron, selectAssetCopper, selectAssetOtherPlastic, selectAssetPEPlastic, selectAssetProtectedSteel, selectAssetUnprotectedSteel);

			boolean selectBoundaryDistrict = viewLayersList.get(0).get(KEYBOUNDARYDISTRICT).equalsIgnoreCase("1");
			boolean selectBoundaryDistrictPlat = viewLayersList.get(0).get(KEYBOUNDARYDISTRICTPLAT).equalsIgnoreCase("1");
			selectViewLayerBoundaries(selectBoundaryDistrict, selectBoundaryDistrictPlat);
		}

	}

	public void addViews(String customer, List<Map<String, String>> viewList) {
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
		openNewReportPage();

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

		selectTimeZone(timeZone);
		inputExclusionRadius(exclusionRadius);

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
			inputSurveyTag(tag);
		}

		this.btnSurveySearch.click();
		this.waitForSurveyTabletoLoad();
		this.waitForSurveySelectorCheckBoxToLoad();
		this.checkboxSurFirst.click();
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

		inputImageMapHeight(imageMapHeight);
		inputImageMapWidth(imageMapWidth);

		selectViewLayerAssets(true, true, true, true, true, true);
		selectViewLayerBoundaries(true, true);

		selectIndicationsTableCheckBox();
		this.checkBoxIsoAna.click();
		this.checkBoxPCA.click();
		this.checkBoxPCRA.click();

		this.btnOK.click();
	}

	public boolean checkActionStatus(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);
		this.waitForPageLoad();
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
							this.waitForPdfReportIcontoAppear();
						} else {
							this.btnReportViewer = table.findElement(By.xpath("//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[3]/img"));
							this.btnReportViewer.click();
							this.waitForPdfReportIcontoAppear();
						}
						String srcPdfImg = this.pdfImg.getAttribute("src");
						String srcZipImg = this.zipImg.getAttribute("src");
						String srcZipMeta = this.zipMeta.getAttribute("src");
						String srcShapeImg = this.zipShape.getAttribute("src");
						DBConnection objDbConn = new DBConnection();
						String reportId = objDbConn.getIdOfSpecifiedReportTitle(rptTitle, this.testSetup);
						reportId = reportId.substring(0, 6);
						reportName = "CR-" + reportId;

						if (srcPdfImg.contains("pdf") && srcZipImg.contains("zip") && srcZipMeta.contains("zip") && srcShapeImg.contains("zip")) {
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click();", pdfImg);
							waitForFileDownload(reportName + ".pdf", testSetup.getDownloadPath());
							js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click();", zipImg);
							waitForFileDownload(reportName + ".zip", testSetup.getDownloadPath());
							if (zipMeta.isDisplayed()) {
								js = (JavascriptExecutor) driver;
								js.executeScript("arguments[0].click();", zipMeta);
								waitForFileDownload(reportName + " (1).zip", testSetup.getDownloadPath());
							}
							if (zipShape.isDisplayed()) {
								js = (JavascriptExecutor) driver;
								js.executeScript("arguments[0].click();", zipShape);
								waitForFileDownload(reportName + " (2).zip", testSetup.getDownloadPath());
							}
							return true;
						}

						if (srcPdfImg.contains("pdf") && srcZipImg.contains("zip")) {
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click();", pdfImg);
							waitForFileDownload(reportName + ".pdf", testSetup.getDownloadPath());
							js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click();", zipImg);
							waitForFileDownload(reportName + ".zip", testSetup.getDownloadPath());
							return true;
						} else
							return false;
					} catch (org.openqa.selenium.NoSuchElementException e) {
						elapsedTime = System.currentTimeMillis() - startTime;
						if (elapsedTime >= (ACTIONTIMEOUT + 800 * 1000)) {
							System.out.print("Timed out in reporting");
							return false;
						}

						continue;
					} catch (NullPointerException ne) {
						Log.info("Null Pointer Exception: " + ne);
						fail("Report failed to generate!!");
					}
				}
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.waitForPageLoad();
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

		setPagination(PAGINATIONSETTING);
		this.waitForPageLoad();

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
						waitForFileDownload(reportName + ".pdf", testSetup.getDownloadPath());
						zipImg.click();
						waitForFileDownload(reportName + ".zip", testSetup.getDownloadPath());
						return true;
					} else
						return false;
				} catch (org.openqa.selenium.NoSuchElementException e) {
					return false;
				}
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.waitForPageLoad();

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

	public enum ComplianceReportButtonType {
		Delete, Copy, ReportViewer, Investigate, InvestigatePDF, Resubmit
	}

	public enum ReportViewerThumbnailType {
		ComplianceTablePDF, ComplianceZipPDF, ComplianceZipShape, ComplianceZipMeta, FirstView, SecondView, ThirdView, FourthView, FifthView, SixthView, SeventhView
	}

	public boolean verifyComplianceReportButton(String rptTitle, String strCreatedBy, ComplianceReportButtonType buttonType) throws Exception {
		return checkComplianceReportButtonPresenceAndClick(rptTitle, strCreatedBy, buttonType, false);
	}

	public boolean clickComplianceReportButton(String rptTitle, String strCreatedBy, ComplianceReportButtonType buttonType) throws Exception {
		return checkComplianceReportButtonPresenceAndClick(rptTitle, strCreatedBy, buttonType, true);
	}

	private boolean checkComplianceReportButtonPresenceAndClick(String rptTitle, String strCreatedBy, ComplianceReportButtonType buttonType, boolean clickButton) throws Exception {
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String reportTitleXPath;
		String createdByXPath;
		String buttonXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement buttonImg;

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
					switch (buttonType) {
					case Delete:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[1]/img";
						break;
					case Copy:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[2]/img";
						break;
					case ReportViewer:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[3]/img";
						break;
					case Investigate:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[4]/img";
						break;
					case InvestigatePDF:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[5]/img";
						break;
					case Resubmit:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[6]/img";
						break;
					default:
						throw new Exception("ButtonType NOT supported.");
					}
					buttonImg = table.findElement(By.xpath(buttonXPath));
					String srcButtonImg = pdfImg.getAttribute("src");

					if (buttonImg.isDisplayed()) {
						if (clickButton) {
							buttonImg.click();
						}
						return true;
					}
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

				this.waitForPageLoad();
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

	public boolean findReportbySearch(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);
		this.waitForPageLoad();
		String reportTitleXPath;
		String createdByXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;

		this.getTextBoxReportSerach().sendKeys(rptTitle);
		this.getTextBoxReportSerach().sendKeys(Keys.ENTER);
		this.waitForPageLoad();
		if (this.getDataTableEmpty().isDisplayed()) {
			return false;
		}
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		int rowSize = rows.size();

		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			this.waitForPageLoad();
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";
			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));
			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.waitForPageLoad();
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

		this.waitForPageLoad();

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
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				deleteImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[1]/img";
				deleteImg = table.findElement(By.xpath(deleteImgXPath));

				deleteImg.click();
				waitForDeletePopupLoad();

				if (this.isElementPresent(btnDeleteConfirmXpath)) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", btnDeleteConfirm);
					this.waitForPageLoad();

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

				this.waitForPageLoad();

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

		this.waitForPageLoad();

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
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				copyImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[2]/img";
				copyImg = table.findElement(By.xpath(copyImgXPath));

				copyImg.click();

				this.waitForCopyReportPagetoLoad();

				this.inputTitle.clear();
				this.inputTitle.sendKeys(rptTitleNew);
				this.waitForDeleteSurveyButtonToLoad();
				this.btnOK.click();

				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.waitForPageLoad();

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

		try {
			reportId = objDbConn.getIdOfSpecifiedReportTitle(reportTitle, this.testSetup);
			reportId = reportId.substring(0, 6);
			reportName = "CR-" + reportId;
			setReportName(reportName);
			BaseHelper.deCompressZipFile(reportName, downloadPath);
		} catch (Exception e) {
			Log.error(e.toString());
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
				Log.error(e.toString());
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

		try {
			reportId = objDbConn.getIdOfSpecifiedReportTitle(reportsCompliance.getRptTitle(), this.testSetup);
			reportId = reportId.substring(0, 6);
			reportName = "CR-" + reportId;
			setReportName(reportName);
			BaseHelper.deCompressZipFile(reportName, downloadPath);
		} catch (Exception e) {
			Log.error(e.toString());
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
				Log.error(e.toString());
				return false;
			}
		} else
			return false;

		for (int i = 0; i < viewList.size(); i++) {
			viewName = viewList.get(i).get(KEYVIEWNAME);
			pdfFile2 = downloadPath + reportName + File.separator + nameBase.replaceAll("_", "") + "_" + viewName + ".pdf";

			if (!BaseHelper.validatePdfFile(pdfFile2)) {
				Log.info("PDF Validation failed for: " + pdfFile2);
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
		// Log.info(actualReportString);
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

	public boolean compareComplianceRptFirstPageStaticText(String actualPath) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		String actualReport = actualPath + reportName.trim() + ".pdf";
		String actualReportString = null;
		actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(REPORTTITLE);
		expectedReportString.add(LISAINV);
		expectedReportString.add(GAPINV);
		expectedReportString.add(CGIINV);
		expectedReportString.add(APPRNAME);
		expectedReportString.add(APPRSIG);
		expectedReportString.add(RPTCRTDATE);

		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
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
	public boolean compareComplianceRptFirstPageTable(String actualPath, HashMap<String, String> inputMap) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		String actualReport = actualPath + reportName.trim() + ".pdf";
		String actualReportString = null;
		actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);
		List<String> expectedTableStrings = new ArrayList<String>();
		expectedTableStrings.add("Map Height & Width");
		expectedTableStrings.add("Time Zone");
		expectedTableStrings.add("Exclusion Radius");
		expectedTableStrings.add("Report Mode");
		expectedTableStrings.add("NE Lat & NE Long");
		expectedTableStrings.add("SW Lat & SW Long");

		HashMap<String, String> actualFirstPage = matchPatternforPairs(actualReportString, expectedTableStrings);
		if (actualFirstPage.equals(inputMap)) {
			return true;
		}
		return false;
	}

	/**
	 * Method to verify the images appear on the compliance report
	 * 
	 * @param actual
	 *            path to the generated report
	 * @return true or false based on whether the images match the given images
	 */
	public boolean compareComplianceRptImages(String actualPath, String baselinePath) throws IOException {
		String actualReport = actualPath + reportName.trim() + ".pdf";
		PDFUtility pdfUtility;
		pdfUtility = new PDFUtility();
		String imageDirectory = pdfUtility.extractPDFImages(actualReport, Paths.get(actualPath).getFileName().toString() + "_", 1, 2);
		Log.info(imageDirectory);
		return false;
	}

	/**
	 * Method to verify the images appear on Views
	 * 
	 * @param actual
	 *            path to the generated report
	 * @return true or false based on whether the images match the given images
	 */
	public boolean compareViewImages(String actualPath, String baselinePath) throws IOException {
		String actualReport = actualPath + reportName.trim() + ".pdf";
		PDFUtility pdfUtility;
		pdfUtility = new PDFUtility();
		String imageDirectory = pdfUtility.extractPDFImages(actualReport, Paths.get(actualPath).getFileName().toString() + "_", 1, 2);
		Log.info(imageDirectory);
		return false;
	}

	public boolean copyReportAndModifyDetails(String rptTitle, String strCreatedBy, String rptTitleNew, String surUnit, List<String> tag, boolean changeMode, String strReportMode) {
		setPagination(PAGINATIONSETTING);
		this.waitForPageLoad();
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

		this.waitForPageLoad();
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				copyImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[2]/img";
				copyImg = table.findElement(By.xpath(copyImgXPath));
				copyImg.click();
				this.inputTitle.clear();
				this.inputTitle.sendKeys(rptTitleNew);

				if (strReportMode != null && changeMode) {
					selectReportMode(getReportMode(strReportMode));
					this.waitForCopyReportPagetoLoad();

				} else
					this.btnDeleteSurvey.click();
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

						inputSurveyTag(tagValue);

						this.btnSurveySearch.click();
						this.waitForSurveyTabletoLoad();
						this.checkboxSurFirst.click();
						this.btnAddSurveys.click();
						this.waitForAddSurveyButtonToLoad();
					}
				}

				this.inputViewInd.click();
				this.inputViewIso.click();
				this.inputViewAnno.click();
				this.btnOK.click();

				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();

				this.waitForPageLoad();

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

	public void selectReportMode(ReportModeFilter mode) {
		switch (mode) {
		case Standard:
			this.checkBoxStndRptMode.click();
			break;
		case RapidResponse:
			this.checkBoxRRRptMode.click();
			break;
		case Manual:
			this.checkBoxManualRptMode.click();
			break;
		default:
			break;
		}

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		if (this.btnChangeRptMode.isDisplayed()) {
			this.btnChangeRptMode.click();
		}
	}

	public void addNewReport(String title, String customer, String timeZone, String exclusionRadius, String boundary, String imageMapHeight, String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong, String surUnit, List<String> tag, String startDate, String endDate, boolean changeMode, String strReportMode) {
		openNewReportPage();

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
			if (strReportMode.contentEquals(Resources.getResource(ResourceKeys.Constant_Standard)))
				this.radioBtnStndMode.click();
			else if (strReportMode.contentEquals("Rapid Response"))
				this.radioBtnRRmode.click();
			else if (strReportMode.contentEquals(Resources.getResource(ResourceKeys.Constant_Manual)))
				this.radioBtnManualMode.click();
		}

		this.inputTitle.clear();
		this.inputTitle.sendKeys(title);

		selectTimeZone(timeZone);
		inputExclusionRadius(exclusionRadius);
		inputImageMapWidth(imageMapWidth);
		inputImageMapHeight(imageMapHeight);

		this.inputNELat.sendKeys(NELat);
		this.inputNELong.sendKeys(NELong);
		this.inputSWLat.sendKeys(SWLat);
		this.inputSWLong.sendKeys(SWLong);

		this.checkBoxIndTb.click();
		this.checkBoxIsoAna.click();
		this.checkBoxPCA.click();
		this.checkBoxPCRA.click();

		selectViewLayerAssets(true, true, true, true, true, true);

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
				inputSurveyTag(tagValue);
				this.btnSurveySearch.click();
				this.waitForSurveyTabletoLoad();
				this.waitForSurveySelectorCheckBoxToLoad();
				this.checkboxSurFirst.click();
				this.waitForAddSurveyButtonToLoad();
				this.btnAddSurveys.click();
				this.waitForAddSurveyButtonToLoad();
				this.waitForNewPageLoad();
			}
		}

		this.inputViewLisa.click();
		this.inputViewFOV.click();
		this.inputViewBreadCrumb.click();
		this.inputViewInd.click();
		this.inputViewIso.click();
		this.inputViewAnno.click();
		this.inputViewAssets.click();

		if (boundary != null) {
			this.inputViewBoundaries.click();

			selectViewLayerBoundaries(true, true);
		}
		this.btnOK.click();
	}

	public boolean checkBlankReportErrorTextPresent() {
		openNewReportPage();
		this.btnOK.click();
		if (isElementPresent(strErrorText))
			return true;
		return false;
	}

	public boolean searchReport(String reportTitle, String reportCreatedBy) {
		this.inputSearchReport.sendKeys(reportTitle);

		if (this.tdCReportTitle.getText().contentEquals(reportTitle)) {
			if (this.tdCReportCreatedBy.getText().contentEquals(reportCreatedBy))
				return true;
		}
		return false;
	}

	public void addNewReportWithMultipleSurveysIncluded(Reports reportsCompliance) {
		openNewReportPage();
		inputReportTitle(reportsCompliance.getRptTitle());

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

				inputReportTitle(reportsCompliance.getRptTitle());
			}
		}

		// TODO : Check?? Was this call intentionally removed.
		selectTimeZone(reportsCompliance.getTimeZone());

		inputExclusionRadius(reportsCompliance.getExclusionRadius());
		inputImageMapHeight(reportsCompliance.getImageMapHeight());
		inputImageMapWidth(reportsCompliance.getImageMapWidth());

		fillCustomBoundaryTextFields(reportsCompliance.getNELat(), reportsCompliance.getNELong(), reportsCompliance.getSWLat(), reportsCompliance.getSWLong());

		List<Map<String, String>> tablesList = reportsCompliance.getTablesList();
		if (tablesList.get(0).get(KEYINDTB).equalsIgnoreCase("1")) {
			this.checkBoxIndTb.click();
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

		selectViewLayerAssets(true, true, true, true, true, true);

		if (reportsCompliance.getSurveyorUnit() != "") {
			selectSurveySurveyor(reportsCompliance.getSurveyorUnit());
		}

		for (String tagValue : reportsCompliance.tagList) {
			if (tagValue != "") {
				inputSurveyTag(tagValue);
				this.btnSurveySearch.click();
				this.waitForSurveyTabletoLoad();
				this.waitForSurveySelectorCheckBoxToLoad();
				this.checkboxSurFirst.click();
				this.waitForAddSurveyButtonToLoad();
				this.btnAddSurveys.click();
			}
		}

		addViews(reportsCompliance.getCustomer(), reportsCompliance.getViewList());

		this.btnOK.click();
	}

	public boolean checkPaginationSetting(String numberOfReports) {
		setPagination(numberOfReports);
		this.waitForPageLoad();

		String msgToVerify = STRPaginationMsg + numberOfReports;
		this.waitForNumberOfRecords(msgToVerify);

		if (msgToVerify.equals(this.paginationMsg.getText().substring(0, 16).trim()))
			return true;

		return false;
	}

	public boolean verifyCancelButtonFunctionality() {
		openNewReportPage();
		this.btnCancel.click();
		this.waitForPageLoad();

		if (isElementPresent(strBtnNewCompRpt))
			return true;

		return false;
	}

	public void openNewComplianceReportPage() {
		openNewReportPage();
	}

	public void clickOnCopyReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);

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
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				copyImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[2]/img";
				copyImg = table.findElement(By.xpath(copyImgXPath));

				copyImg.click();
				break;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();

				this.waitForPageLoad();

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
			inputSurveyTag(surveyTag);
			this.btnSurveySearch.click();
			this.waitForSurveyTabletoLoad();
			this.checkboxSurFirst.click();
			this.btnAddSurveys.click();
		}

		if (isElementPresent(strFirstSurveyTag)) {
			if (surveyTag != "") {
				inputSurveyTag(surveyTag);
				this.btnSurveySearch.click();
				this.waitForSurveyTabletoLoad();
				this.checkboxSurFirst.click();
				this.btnAddSurveys.click();

				if (this.btnAddSurveys.getAttribute("value").equalsIgnoreCase(STRSurveyIncludedMsg))
					return true;
			}
		}
		return false;
	}

	public boolean deleteSurveyAndIncludeAgain(String surveyTag) {
		this.btnDeleteDrivingSurvey.click();
		this.waitForCopyReportPagetoLoad();

		try {
			driver.findElement(By.xpath(strFirstSurveyTag));
			return false;
		} catch (NoSuchElementException e) {
			if (surveyTag != "") {
				inputSurveyTag(surveyTag);
				this.btnSurveySearch.click();
				this.waitForSurveyTabletoLoad();
				this.checkboxSurFirst.click();
				this.btnAddSurveys.click();
			}

			if (isElementPresent(strFirstSurveyTag))
				return true;

			return false;
		}
	}

	public String provideLatLongAtCustomBoundarySelectorWindow(List<String> listBoundary) {
		String actualMsg = "";
		openNewReportPage();

		inputImageMapHeight(listBoundary.get(0));
		inputImageMapWidth(listBoundary.get(1));

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

	public int getNumberofRecords() {
		List<WebElement> records = this.numberofRecords;
		return records.size();
	}

	public String getAreaErrorText() {
		return this.areaErrorText.getText();

	}

	public WebElement getTextBoxReportSerach() {
		return textBoxReportSerach;
	}

	public WebElement getDataTableEmpty() {
		return dataTableEmpty;
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

	public void waitForNumberOfRecords(String actualMessage) {
		(new WebDriverWait(driver, timeout + 50)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return paginationMsg.getText().substring(0, 16).trim().equals(actualMessage);
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

	public void waitForDeletePopupLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnDeleteConfirm.isDisplayed();
			}
		});
	}

	public void waitForReportGenerationtoComplete() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnReportViewer.isDisplayed();
			}
		});
	}

	public void waitForSurveyTabletoLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return surveysTable.isDisplayed();
			}
		});
	}
	
	public void waitForSurveySelectorCheckBoxToLoad() {
		(new WebDriverWait(driver, timeout+15)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return checkboxSurFirst.isDisplayed();
			}
		});
	}
	
	public void waitForAddSurveyButtonToLoad() {
		(new WebDriverWait(driver, timeout+15)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnAddSurveys.isDisplayed();
			}
		});
	}
	
	
	public void waitForDeleteSurveyButtonToLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnDeleteSurvey.isDisplayed();
			}
		});
	}

	public void waitForCopyReportPagetoLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(STRCopyPageTitle);
			}
		});
	}

	public void waitForPdfReportIcontoAppear() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return pdfImg.isDisplayed();
			}
		});
	}

	public boolean waitForReportGenerationtoComplete(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);

		String reportTitleXPath;
		String createdByXPath;
		WebElement rptTitleCell;
		WebElement createdByCell;

		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		this.waitForPageLoad();

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

						} else {
							this.btnReportViewer = table.findElement(By.xpath("//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[3]/img"));

						}

						return true;
					} catch (org.openqa.selenium.NoSuchElementException e) {
						elapsedTime = System.currentTimeMillis() - startTime;
						if (elapsedTime >= (ACTIONTIMEOUT + 900 * 1000)) {
							return false;
						}

						continue;
					} catch (NullPointerException ne) {
						Log.info("Null Pointer Exception: " + ne);
						fail("Report failed to generate!!");
					}
				}
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.waitForPageLoad();

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

	public void selectViewLayerAssets(Boolean selectAssetCastIron, Boolean selectAssetCopper, Boolean selectAssetOtherPlastic, Boolean selectAssetPEPlastic, Boolean selectAssetProtectedSteel, Boolean selectAssetUnprotectedSteel) {
		if (selectAssetCastIron) {
			if (driver.findElements(By.xpath("//*[@id='report-asset-layers-96caf1f5-d5c5-461d-9ce3-d210c20a1bb0']")).size() > 0) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", checkBoxCastIron);
			}
		}
		if (selectAssetCopper) {
			if (driver.findElements(By.xpath("//*[@id='report-asset-layers-d08fc87f-f979-4131-92a9-3d82f37f4bba']")).size() > 0) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", checkBoxCopper);
			}
		}
		if (selectAssetOtherPlastic) {
			if (driver.findElements(By.xpath("//*[@id='report-asset-layers-ad701312-c470-482a-be45-ef37770e2ce6']")).size() > 0) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", checkBoxOtherPla);
			}
		}
		if (selectAssetPEPlastic) {
			if (driver.findElements(By.xpath("//*[@id='report-asset-layers-f14735de-6c9b-4423-8533-f243a7fe4e90']")).size() > 0) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", checkBoxPEPla);
			}
		}
		if (selectAssetProtectedSteel) {
			if (driver.findElements(By.xpath("//*[@id='report-asset-layers-44353e68-0694-4f05-85cb-84d753ea278c']")).size() > 0) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", checkBoxProtectedSteel);
			}
		}
		if (selectAssetUnprotectedSteel) {
			if (driver.findElements(By.xpath("//*[@id='report-asset-layers-f3955e82-dd13-4842-84f7-502bcda6b57a']")).size() > 0) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", checkBoxUnProtectedSteel);
			}
		}
	}

	public void selectViewLayerBoundaries(boolean selectBoundaryDistrict, boolean selectBoundaryDistrictPlat) {
		if (selectBoundaryDistrict) {
			if (driver.findElements(By.xpath("//*[@id='report-boundry-layers-Small Boundary']")).size() > 0) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", checkBoxDistrict);
			}
		}
		if (selectBoundaryDistrictPlat) {
			if (driver.findElements(By.xpath("//*[@id='report-boundry-layers-Big Boundary']")).size() > 0) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", checkBoxDistrictPlat);
			}
		}
	}

	public enum CustomerBoundaryType {
		District, DistrictPlat
	}

	public void selectAnyCustomerBoundary(CustomerBoundaryType type) {
		// TODO open the Boundary selector and click on any customer boundary.
		// Could provide a search by boundary name.
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void waitForFileDownload(String fileName, String downloadPath) {
		(new WebDriverWait(driver, timeout+60)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return checkFileExists(fileName, downloadPath);
			}
		});
	}

	private boolean checkFileExists(String fileName, String downloadPath) {
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();
		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public void waitForPDFFileDownload() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateInvestigatePDFFile() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForReportViewerPopupToShow() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadMetaDataZipFile() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyMetaDataFiles() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyMetaDataFilesData() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyThumbnailInReportViewer(ReportViewerThumbnailType compliancezipmeta) {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadShapeZipFile() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForShapeZipFileDownload() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyShapeFilesData() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyShapeFiles() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType compliancezipmeta) {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/**
 * 
 */
package surveyor.scommon.source;

import static org.junit.Assert.fail;
import static common.source.BaseHelper.matchSinglePattern;
import common.source.DateUtility;
import static surveyor.scommon.source.SurveyorConstants.ACTIONTIMEOUT;
import static surveyor.scommon.source.SurveyorConstants.CUSBOUNDARY;
import static surveyor.scommon.source.SurveyorConstants.ENDDATE;
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
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.REPORTMODES1;
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
import surveyor.scommon.source.Reports.EthaneFilter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.source.BaseHelper;
import common.source.CSVUtility;
import common.source.DBConnection;
import common.source.Log;
import common.source.TestSetup;
import common.source.ZipUtility;
import surveyor.dataaccess.source.BaseMapType;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ReportView;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.StoredProcComplianceAssessmentGetReportDrivingSurveys;
import surveyor.dataaccess.source.StoredProcComplianceGetCoverage;
import surveyor.dataaccess.source.StoredProcComplianceGetGaps;
import surveyor.dataaccess.source.StoredProcComplianceGetIndications;
import surveyor.dataaccess.source.StoredProcComplianceGetIsotopics;
import surveyor.dataaccess.source.StoredProcReferenceGas;
import common.source.PDFUtility;
import common.source.RegexUtility;

/**
 * @author zlu
 *
 */
public class ComplianceReportsPage extends ReportsBasePage {
	private static final int CUSTOM_BOUNDARY_RADBUTTON_GROUP_IDX = 0;
	private static final int CUSTOMER_BOUNDARY_RADBUTTON_GROUP_IDX = 1;

	public static final String BOUNDARY_SELECTOR_CANVAS_X_PATH = "//*[@id=\"map\"]/div/canvas";
	public static final String STRURLPath = "/Reports/ComplianceReports";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.ComplianceReports_PageTitle);
	public static final String STRPaginationMsg = "Showing 1 to ";
	public static final String STRSurveyIncludedMsg = Resources.getResource(ResourceKeys.ComplianceReport_AlreadyAdded);
	public static final String ComplianceReport_SurveyMissingMessage = Resources.getResource(ResourceKeys.ComplianceReport_SurveyMissingMessage);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ComplianceReports_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ComplianceReports_AddNew);
	public static final String STRCopyPageTitle = Resources.getResource(ResourceKeys.ComplianceReport_PageTitle);
	public static final String STRReportTitle = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ComplianceReportSSRS);
	public static final String ComplianceReportSSRS_LISAInvestigationComplete = Resources.getResource(ResourceKeys.ComplianceReportSSRS_LISAInvestigationComplete);
	public static final String ComplianceReportSSRS_GAPInvestigationComplete = Resources.getResource(ResourceKeys.ComplianceReportSSRS_GAPInvestigationComplete);
	public static final String ComplianceReportSSRS_CGIInvestigationComplete = Resources.getResource(ResourceKeys.ComplianceReportSSRS_CGIInvestigationComplete);
	public static final String ComplianceReportSSRS_MapHeightWidth = Resources.getResource(ResourceKeys.ComplianceReportSSRS_MapHeightWidth);
	public static final String ComplianceReportSSRS_NELatNELong = Resources.getResource(ResourceKeys.ComplianceReportSSRS_NELatNELong);
	public static final String ComplianceReportSSRS_SWLatSWLong = Resources.getResource(ResourceKeys.ComplianceReportSSRS_SWLatSWLong);
	public static final String ComplianceReportSSRS_TimeZone = Resources.getResource(ResourceKeys.ComplianceReportSSRS_TimeZone);
	public static final String ComplianceReportSSRS_ShowCoverage = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowCoverage);
	public static final String ComplianceReportSSRS_PercentCoverageAssets = Resources.getResource(ResourceKeys.ComplianceReportSSRS_PercentCoverageAssets);
	public static final String ComplianceReportSSRS_PercentCoverageForecast = Resources.getResource(ResourceKeys.ComplianceReportSSRS_PercentCoverageForecast);
	public static final String ComplianceReportSSRS_PercentCoverageReportArea = Resources.getResource(ResourceKeys.ComplianceReportSSRS_PercentCoverageReportArea);
	public static final String ComplianceReportSSRS_Asset = Resources.getResource(ResourceKeys.ComplianceReportSSRS_Asset);
	public static final String ComplianceReportSSRS_Boundary = Resources.getResource(ResourceKeys.ComplianceReportSSRS_Boundary);
	public static final String ComplianceReportSSRS_ViewTable = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ViewTable);
	public static final String ComplianceReportSSRS_ViewName = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ViewName);
	public static final String ComplianceReportSSRS_ShowLISAs = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowLISAs);
	public static final String ComplianceReportSSRS_ShowFOV = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowFOV);
	public static final String ComplianceReportSSRS_ShowBreadcrumb = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowBreadcrumb);
	public static final String ComplianceReportSSRS_ShowIndications = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowIndications);
	public static final String ComplianceReportSSRS_ShowIsotopicAnalyses = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowIsotopicAnalyses);
	public static final String ComplianceReportSSRS_FieldNotes = Resources.getResource(ResourceKeys.ComplianceReportSSRS_FieldNotes);
	public static final String ComplianceReportSSRS_ShowGaps = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowGaps);
	public static final String ComplianceReportSSRS_ShowAssets = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowAssets);
	public static final String ComplianceReportSSRS_ShowBoundaries = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowBoundaries);
	public static final String ComplianceReportSSRS_BaseMap = Resources.getResource(ResourceKeys.ComplianceReportSSRS_BaseMap);
	public static final String ComplianceReportSSRS_TotalLinearAssetCoverage = Resources.getResource(ResourceKeys.ComplianceReportSSRS_TotalLinearAssetCoverage);
	public static final String ReportSSRS_SelectedDrivingSurveys = Resources.getResource(ResourceKeys.ReportSSRS_SelectedDrivingSurveys);
	public static final String ComplianceReportSSRS_IsotopicAnalysisTable = Resources.getResource(ResourceKeys.ComplianceReportSSRS_IsotopicAnalysisTable);
	public static final String ComplianceReportSSRS_IndicationTable = Resources.getResource(ResourceKeys.ComplianceReportSSRS_IndicationTable);
	public static final String ComplianceReportSSRS_GapTable = Resources.getResource(ResourceKeys.ComplianceReportSSRS_GapTable);

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

	@FindBy(id = "report-geo-filter")
	protected WebElement checkBoxGeoFilter;

	@FindBy(how = How.XPATH, using = "//*[@id='dvErrorText']/ul/li")
	protected WebElement msgEmptySurvey;

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

	@FindBy(how = How.XPATH, using = "//*[@id=resubmitReportModal']/div/div/div[3]/a[1]")
	protected WebElement btnResubmitReport;

	@FindBy(id = "resubmitReportModal")
	protected WebElement resubmitReportModal;

	@FindBy(how = How.XPATH, using = "//*[@id='resubmitReportModal']/div/div/div[3]/a[1]")
	protected WebElement btnProcessResubmit;

	@FindBy(how = How.XPATH, using = "//*[@id='dvErrorText']/ul/li[1]")
	protected WebElement assetErrorText;

	@FindBy(how = How.XPATH, using = "//*[@id='dvErrorText']/ul/li[2]")
	protected WebElement boundaryErrorText;

	@FindBy(id = "report-ethene-vehicle-exhaust")
	protected WebElement checkBoxVehicleExhaust;

	@FindBy(how = How.XPATH, using ="//*[@id='datatableViews']/thead/tr/th[7]/div")
	protected WebElement viewsAnalysesColumn;

	@FindBy(how = How.XPATH, using ="//*[@id='page-wrapper']/div/div[3]/div/div[11]/div/div/div/div[2]/div/label")
	protected WebElement tubularAnalysisOption;

	@FindBy(id = "report-ethene-biogenic-methane")
	protected WebElement checkBoxEtheneBiogeniceMethane;

	public enum CustomerBoundaryType {
		District, DistrictPlat
	}

	public enum ComplianceReportButtonType {
		Delete, Copy, ReportViewer, Investigate, InvestigatePDF, Resubmit
	}

	public enum ReportViewerThumbnailType {
		ComplianceTablePDF, ComplianceZipPDF, ComplianceZipShape, ComplianceZipMeta, FirstView, SecondView, ThirdView, FourthView, FifthView, SixthView, SeventhView
	}

	public enum ReportFileType {
		PDF, ZIP, MetaDataZIP, ShapeZIP
	}

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public ComplianceReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);

		Log.info("\nThe Compliance Reports Page URL is: %s\n" + this.strPageURL);
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

		if (reportsCompliance.getEthaneFilter() != null) {
			selectEthaneFilter(reportsCompliance.getEthaneFilter());
		}

		if (reportsCompliance.reportModeFilter != null) {
			selectReportMode(reportsCompliance.reportModeFilter);
		}

		if (reportsCompliance.getExclusionRadius() != null) {
			inputExclusionRadius(reportsCompliance.getExclusionRadius());
		}

		fillCustomBoundaryTextFields(reportsCompliance.getNELat(), reportsCompliance.getNELong(), reportsCompliance.getSWLat(), reportsCompliance.getSWLong());

		addSurveyInformation(reportsCompliance.getSurveyorUnit(), reportsCompliance.getUsername(), reportsCompliance.getTagList(), reportsCompliance.getSurveyStartDate(), reportsCompliance.getSurveyEndDate(), reportsCompliance.getSurveyModeFilter(), reportsCompliance.getGeoFilter());

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

		this.clickOnOKButton();
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
		this.waitForSurveySelectorCheckBoxToBeEnabled();
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

		this.clickOnOKButton();
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

	public void addNewPDReport(String reportTitle, String customer, String surveyor, List<String> tag, boolean changeMode, String reportMode) {
		this.addNewReport(reportTitle, customer, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH, RNELAT, RNELON, RSWLAT, RSWLON, surveyor, tag, STARTDATE, ENDDATE, changeMode, reportMode);
	}

	public void addNewReport(String title, String customer, String timeZone, String exclusionRadius, String boundary, String imageMapHeight, String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong, String surUnit, List<String> tagList, String startDate, String endDate, boolean changeMode, String strReportMode) {
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

		for (String tagValue : tagList) {
			if (tagValue != "") {
				inputSurveyTag(tagValue);
				this.btnSurveySearch.click();
				this.waitForSurveyTabletoLoad();
				this.waitForSurveySelectorCheckBoxToLoad();
				this.waitForSurveySelectorCheckBoxToBeEnabled();
				selectSurveyCheckBox(checkboxSurFirst);
				this.waitForAddSurveyButtonToLoad();
				this.btnAddSurveys.click();

			}
		}

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		addViews(customer, viewList);

		if (boundary != null) {
			this.inputViewBoundaries.click();

			selectViewLayerBoundaries(true, true);
		}

		this.clickOnOKButton();
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
				this.waitForSurveySelectorCheckBoxToBeEnabled();
				this.checkboxSurFirst.click();
				this.waitForAddSurveyButtonToLoad();
				this.btnAddSurveys.click();
			}
		}

		addViews(reportsCompliance.getCustomer(), reportsCompliance.getViewList());

		this.clickOnOKButton();
	}

	public void addSurveyInformation(String surveyor, String username, List<String> tagList, String startDate, String endDate, SurveyModeFilter surveyModeFilter, Boolean geoFilterOn) {
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

		if ((startDate != null) && (startDate != "")) {
			selectStartDateForSurvey(startDate);
		}

		if ((endDate != null) && (endDate != "")) {
			selectEndDateForSurvey(endDate);
		}

		if (surveyModeFilter != null) {
			selectSurveyModeForSurvey(surveyModeFilter);
		}

		if ((geoFilterOn == null) || (!geoFilterOn)) {
			this.checkGeoFilter.click();

		}

		for (String tagValue : tagList) {
			if (tagValue != "") {
				inputSurveyTag(tagValue);
				this.btnSurveySearch.click();
				this.waitForSurveyTabletoLoad();
				this.waitForSurveySelectorCheckBoxToLoad();
				this.waitForSurveySelectorCheckBoxToBeEnabled();
				this.checkboxSurFirst.click();
				this.waitForAddSurveyButtonToLoad();
				this.btnAddSurveys.click();
			}
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

			if (customer != null && customer.equalsIgnoreCase("sqacus")) {
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

	public void clickOnShapeZIPInReportViewer() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", zipShape);
	}

	public void clickOnMetadataZIPInReportViewer() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", zipMeta);
	}

	public void clickOnZIPInReportViewer() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", zipImg);
	}

	public void clickOnPDFInReportViewer() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", pdfImg);
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

	public void clickLatLongMapSelectorBtn() {
		this.latLongMapSelectorBtn.click();
	}

	public void clickBoundarySelectorBtn() {
		this.boundarySelectorBtn.click();
	}

	public boolean clickComplianceReportButton(String rptTitle, String strCreatedBy, ComplianceReportButtonType buttonType) throws Exception {
		return checkComplianceReportButtonPresenceAndClick(rptTitle, strCreatedBy, buttonType, true);
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
						Report objReport = Report.getReport(rptTitle);
						String reportId = objReport.getId();
						reportId = reportId.substring(0, 6);
						reportName = "CR-" + reportId;

						if (srcPdfImg.contains("pdf") && srcZipImg.contains("zip") && srcZipMeta.contains("zip") && srcShapeImg.contains("zip")) {
							clickOnPDFInReportViewer();
							waitForPDFFileDownload(reportName);
							clickOnZIPInReportViewer();
							waitForReportZIPFileDownload(reportName);
							if (zipMeta.isDisplayed()) {
								clickOnMetadataZIPInReportViewer();
								waitForMetadataZIPFileDownload(reportName);
								try {
									BaseHelper.deCompressZipFile(reportName + " (1)", testSetup.getDownloadPath());
								} catch (Exception e) {
									Log.error(e.toString());
								}
							}
							if (zipShape.isDisplayed()) {
								clickOnShapeZIPInReportViewer();
								waitForShapeZIPFileDownload(reportName);
							}
							return true;
						}

						if (srcPdfImg.contains("pdf") && srcZipImg.contains("zip")) {
							clickOnPDFInReportViewer();
							waitForPDFFileDownload(reportName);
							clickOnZIPInReportViewer();
							waitForReportZIPFileDownload(reportName);
							return true;
						} else
							return false;
					} catch (org.openqa.selenium.NoSuchElementException e) {
						elapsedTime = System.currentTimeMillis() - startTime;
						if (elapsedTime >= (ACTIONTIMEOUT + 800 * 1000)) {
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
						waitForPDFFileDownload(reportName);
						zipImg.click();
						waitForReportZIPFileDownload(reportName);
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
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				try {
					switch (buttonType) {
					case Delete:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[1]/img";
						break;
					case Copy:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[2]/img";
						break;
					case ReportViewer:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[3]/img";
						break;
					case Investigate:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[4]/img";
						break;
					case InvestigatePDF:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[5]/img";
						break;
					case Resubmit:
						buttonXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[6]/img";
						break;
					default:
						throw new Exception("ButtonType NOT supported.");
					}
					buttonImg = table.findElement(By.xpath(buttonXPath));
					String srcButtonImg = pdfImg.getAttribute("src");

					if (buttonImg.isDisplayed()) {
						if (clickButton) {
							buttonImg.click();
							// If resubmit then wait for modal and confirm resubmit.
							if (buttonType == ComplianceReportButtonType.Resubmit) {
								this.waitForResubmitPopupToShow();
								this.btnProcessResubmit.click();
								this.waitForResubmitPopupToClose();
							}
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

	public void inputSurveyTag(String tag) {
		this.cbTag.clear();
		this.cbTag.sendKeys(tag);
	}

	public void inputSurveyUsername(String username) {
		this.userName.clear();
		this.userName.sendKeys(username);
	}

	public void fillCustomBoundaryTextFields(String neLat, String neLong, String swLat, String swLong) {
		if (neLat != null) {
			this.inputNELat.sendKeys(neLat);
		}
		if (neLong != null) {
			this.inputNELong.sendKeys(neLong);
		}
		if (swLat != null) {
			this.inputSWLat.sendKeys(swLat);
		}
		if (swLong != null) {
			this.inputSWLong.sendKeys(swLong);
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

		if (driver.findElements(By.xpath("//*[@class='dataTables_empty']")).size() == 1) {
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

	public boolean investigateReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);

		this.waitForPageLoad();

		String reportTitleXPath;
		String createdByXPath;
		String investigateImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement investigateImg;

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
				investigateImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[4]/img";
				investigateImg = table.findElement(By.xpath(investigateImgXPath));

				investigateImg.click();
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
				this.waitForInputTitleToEnable();
				this.waitForDeleteSurveyButtonToLoad();
				this.inputTitle.clear();
				this.inputTitle.sendKeys(rptTitleNew);
				this.waitForOkButtonToEnable();
				clickOnOKButton();

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

	// error
	public boolean resubmitReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);

		this.waitForPageLoad();

		String reportTitleXPath;
		String createdByXPath;
		String resubmitImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement resubmitImg;

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
				resubmitImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[2]/img";
				resubmitImg = table.findElement(By.xpath(resubmitImgXPath));

				resubmitImg.click();

				this.waitForCopyReportPagetoLoad();
				this.waitForInputTitleToEnable();

				this.waitForOkButtonToEnable();
				clickOnOKButton();

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

	public boolean copyReportAndModifyDetails(String rptTitle, String strCreatedBy, String rptTitleNew, String surUnit, List<String> tagList, boolean changeMode, ReportModeFilter strReportMode) {
		setPagination(PAGINATIONSETTING);
		this.waitForCopyReportPagetoLoad();
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
					selectReportMode(strReportMode);
					this.waitForCopyReportPagetoLoad();
					this.inputTitle.clear();
					this.inputTitle.sendKeys(rptTitleNew);
				} else {
					this.waitForDeleteSurveyButtonToLoad();
					this.btnDeleteSurvey.click();
				}
				if (surUnit != "") {
					List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
					for (WebElement option : optionsSU) {
						if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
							option.click();
						}
					}
				}

				for (String tagValue : tagList) {
					if (tagValue != "") {

						inputSurveyTag(tagValue);
						this.waitForSurveySearchButtonToLoad();
						this.btnSurveySearch.click();
						this.waitForSurveyTabletoLoad();
						this.checkboxSurFirst.click();
						this.waitForAddSurveyButtonToLoad();
						this.btnAddSurveys.click();

					}
				}

				this.inputViewInd.click();
				this.inputViewIso.click();
				this.inputViewAnno.click();
				this.clickOnOKButton();

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

	public boolean checkBlankReportErrorTextPresent() {
		openNewReportPage();
		this.clickOnOKButton();
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

	public boolean checkPaginationSetting(String numberOfReports) {
		setPagination(numberOfReports);
		this.waitForPageLoad();

		String msgToVerify = STRPaginationMsg + numberOfReports;
		this.waitForNumberOfRecords(msgToVerify);

		if (msgToVerify.equals(this.paginationMsg.getText().substring(0, 16).trim()))
			return true;

		return false;
	}

	public void openNewComplianceReportPage() {
		openNewReportPage();
	}

	public void clickOnOKButton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnOK);
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

	public void openCustomerBoundarySelector() {
		this.selectCustomerBoundaryRadioButton();
		this.waitForCustomerBoundarySectionToShow();
		this.clickBoundarySelectorBtn();
	}

	public void openCustomBoundarySelector() {
		this.selectCustomBoundaryRadioButton();
		this.waitForCustomBoundarySectionToShow();
		this.clickLatLongMapSelectorBtn();
	}

	public void downloadReportPDFFile() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadReportZIPFile() {
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

	public void downloadShapeZipFile() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void selectTimeZone(String timeZone) {
		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if (timeZone.equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}
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

	public void selectSurveyCheckBox(WebElement checkboxSurFirst) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkboxSurFirst);
	}

	public void selectSurveySurveyor(String surveyorUnit) {
		List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
		for (WebElement option : optionsSU) {
			if (surveyorUnit.equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}
	}

	public void selectEndDateForSurvey(String endDate) {
		try {
			DatetimePickerSetting dateSetting = new DatetimePickerSetting(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
			PageFactory.initElements(driver, dateSetting);
			if (endDate.startsWith("0")) {
				endDate = endDate.replaceFirst("0*", "");
			}
			dateSetting.setDay("end", 0, endDate, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectStartDateForSurvey(String startDate) {
		try {
			DatetimePickerSetting dateSetting = new DatetimePickerSetting(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
			PageFactory.initElements(driver, dateSetting);
			if (startDate.startsWith("0")) {
				startDate = startDate.replaceFirst("0*", "");
			}
			dateSetting.setDay("start", 7, startDate, false);

		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void selectCustomBoundaryRadioButton() {
		this.areaBoundaryRadioButtons.get(CUSTOM_BOUNDARY_RADBUTTON_GROUP_IDX).click();
	}

	public void selectCustomerBoundaryRadioButton() {
		this.areaBoundaryRadioButtons.get(CUSTOMER_BOUNDARY_RADBUTTON_GROUP_IDX).click();
	}

	public void selectReportMode(ReportModeFilter mode) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		switch (mode) {
		case Standard:
			js.executeScript("arguments[0].click();", checkBoxStndRptMode);
			break;
		case RapidResponse:
			js.executeScript("arguments[0].click();", checkBoxRRRptMode);
			break;
		case Manual:
			js.executeScript("arguments[0].click();", checkBoxManualRptMode);
			break;
		default:
			break;
		}

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		if (this.btnChangeRptMode.isDisplayed()) {
			this.btnChangeRptMode.click();

		}
	}

	public void selectEthaneFilter(EthaneFilter ethaneFilter) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		switch (ethaneFilter) {
		case ExcludeVehicleExhaust:
			js.executeScript("arguments[0].click();", checkBoxVehicleExhaust);
			break;
		case ExcludeBiogenicMethane:
			js.executeScript("arguments[0].click();", checkBoxEtheneBiogeniceMethane);
			break;
		case Both:
			js.executeScript("arguments[0].click();", checkBoxVehicleExhaust);
			js.executeScript("arguments[0].click();", checkBoxEtheneBiogeniceMethane);
			break;
		default:
			break;
		}

	}

	public void selectSurveyModeForSurvey(SurveyModeFilter surveyModeFilter) {
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

	public void selectAnyCustomerBoundary(CustomerBoundaryType type) {
		// TODO open the Boundary selector and click on any customer boundary.
		// Could provide a search by boundary name.
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

	public boolean verifyComplianceReportButton(String rptTitle, String strCreatedBy, ComplianceReportButtonType buttonType) throws Exception {
		return checkComplianceReportButtonPresenceAndClick(rptTitle, strCreatedBy, buttonType, false);
	}

	/**
	 * Method to verify the static text
	 * 
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyComplianceReportStaticText(String reportTitle) throws IOException {
		return verifyComplianceReportStaticText(testSetup.getDownloadPath(), reportTitle);
	}

	/**
	 * Method to verify the static text
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyComplianceReportStaticText(String actualPath, String reportTitle) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(STRReportTitle);
		expectedReportString.add(ComplianceReportSSRS_LISAInvestigationComplete);
		expectedReportString.add(ComplianceReportSSRS_GAPInvestigationComplete);
		expectedReportString.add(ComplianceReportSSRS_CGIInvestigationComplete);
		expectedReportString.add(ComplianceReportSSRS_MapHeightWidth);
		expectedReportString.add(ComplianceReportSSRS_NELatNELong);
		expectedReportString.add(ComplianceReportSSRS_SWLatSWLong);

		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		return true;

	}

	/**
	 * Method to verify the static text
	 * 
	 * @param reportTitle
	 * @param expectedReportString
	 * @return
	 * @throws IOException
	 */
	public boolean verifyComplianceReportContainsText(String reportTitle, List<String> expectedReportString) throws IOException {
		String actualPath = testSetup.getDownloadPath();
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		return true;

	}


	/**
	 * Method to verify the Show Coverage Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @param userInput
	 * @return
	 * @throws IOException
	 */
	public boolean verifyShowCoverageTable(String actualPath, String reportTitle) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_ShowCoverage);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageAssets);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageForecast);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageReportArea);

		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		return true;
	}

	/**
	 * Method to verify the Coverage Values Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */

	public boolean verifyCoverageValuesTable(String actualPath, String reportTitle, Map<String, String> userSelection) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		String nextLine = RegexUtility.getNextLineAfterPattern(actualReportString, "Coverage Values");
		List<String> matches = RegexUtility.split(nextLine.trim(), "%");
		StoredProcComplianceGetCoverage coverageReportObj = new StoredProcComplianceGetCoverage();
		String PCA = matches.get(0).replaceAll("[\\D+]", "");
		coverageReportObj.setPercentCoverageAssets(PCA);
		String PCRA = matches.get(1).replaceAll("[\\D+]", "");
		coverageReportObj.setPercentCoverageReportArea(PCRA);
		StoredProcComplianceGetCoverage storedProcObj = StoredProcComplianceGetCoverage.getCoverage(reportId);
		List<String> expectedReportString = new ArrayList<String>();
		if (userSelection.get(KEYPCA).equals("1")) {
			expectedReportString.add(ComplianceReportSSRS_TotalLinearAssetCoverage);
		}
		if (userSelection.get(KEYPCRA).equals("1")) {
			expectedReportString.add(ComplianceReportSSRS_PercentCoverageReportArea);
		}

		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);

		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}

		if (!storedProcObj.isCoverageValuesEquals(coverageReportObj)) {
			return false;
		}

		return true;
	}

	/**
	 * Method to verify the Layers Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @param userInput
	 * @return
	 * @throws IOException
	 */
	public boolean verifyLayersTable(String actualPath, String reportTitle, Map<String, String> userInput) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_Asset);
		expectedReportString.add(ComplianceReportSSRS_Boundary);
		expectedReportString.add(KEYASSETCASTIRON);
		expectedReportString.add(KEYASSETCOPPER);
		expectedReportString.add(KEYASSETOTHERPLASTIC);
		expectedReportString.add(KEYASSETPEPLASTIC);
		expectedReportString.add(KEYASSETPROTECTEDSTEEL);
		expectedReportString.add(KEYASSETUNPROTECTEDSTEEL);

		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		return true;

	}

	/**
	 * Method to verify the Views Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @param userInput
	 * @return
	 * @throws IOException
	 */

	public boolean verifyViewsTable(String actualPath, String reportTitle, List<Map<String, String>> userInput) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_ViewTable);
		expectedReportString.add(ComplianceReportSSRS_ViewName);
		expectedReportString.add(ComplianceReportSSRS_ShowLISAs);
		expectedReportString.add(ComplianceReportSSRS_ShowFOV);
		expectedReportString.add(ComplianceReportSSRS_ShowBreadcrumb);
		expectedReportString.add(ComplianceReportSSRS_ShowIndications);
		expectedReportString.add(ComplianceReportSSRS_ShowIsotopicAnalyses);
		expectedReportString.add(ComplianceReportSSRS_FieldNotes);
		expectedReportString.add(ComplianceReportSSRS_ShowGaps);
		expectedReportString.add(ComplianceReportSSRS_ShowAssets);
		expectedReportString.add(ComplianceReportSSRS_ShowBoundaries);
		expectedReportString.add(ComplianceReportSSRS_BaseMap);
		String viewTable = RegexUtility.getStringInBetween(actualReportString, "Selected Views", "View Table");
		InputStream inputStream = new ByteArrayInputStream(viewTable.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		ArrayList<ReportView> viewListInReport = new ArrayList<ReportView>();
		try {
			while ((line = bufferReader.readLine()) != null) {
				if (line.length() > 3) {
					ReportView viewObj = new ReportView();
					String[] split = line.split("\\s+");
					viewObj.setBaseMapId(BaseMapType.getBaseMapTypeId(split[split.length - 1].trim()));
					viewObj.setViewName(line.replace(split[split.length - 1], "").trim());
					viewListInReport.add(viewObj);
				}
			}

		} finally {
			bufferReader.close();
		}

		List<ReportView> dbObjList = ReportView.getReportView(reportId);
		for (ReportView viewObj : dbObjList) {
			if (!viewObj.isViewNameAndMapInList(viewListInReport)) {
				return false;
			}

		}

		return true;

	}

	/**
	 * Method to verify the Driving Surveys Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyDrivingSurveysTable(String actualPath, String reportTitle) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ReportSSRS_SelectedDrivingSurveys);
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		String surveyTable = RegexUtility.getStringInBetween(actualReportString, "Indication Table", "LISA # Surveyor Date/Time Amplitude(ppm)");
		InputStream inputStream = new ByteArrayInputStream(surveyTable.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		ArrayList<String> lineList = new ArrayList<String>();
		try {
			int countLines = 0;
			StringBuilder lineBuilder = new StringBuilder();
			while ((line = bufferReader.readLine()) != null) {
				if (line.length() > 3) {
					lineBuilder.append(line).append(" ");
					countLines++;
					if (countLines % 4 == 0) {
						lineList.add(lineBuilder.toString());
						lineBuilder = new StringBuilder();
					}
				}
			}
			ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> reportSurveyList = new ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys>();
			Iterator<String> lineIterator = lineList.iterator();
			while (lineIterator.hasNext()) {
				StoredProcComplianceAssessmentGetReportDrivingSurveys reportSurveyEntry = new StoredProcComplianceAssessmentGetReportDrivingSurveys();
				Pattern datePattern = Pattern.compile(RegexUtility.getReportRegexDatePattern(true));
				String lineForMatching = lineIterator.next();
				Matcher matchingDate = datePattern.matcher(lineForMatching);
				int dateCounter = 1;
				String remaining = lineForMatching;
				while (matchingDate.find()) {

					if (dateCounter == 1) {
						reportSurveyEntry.setStartDateTimeWithTZ(matchingDate.group(0).trim());
						remaining = remaining.replace(matchingDate.group(0), "").trim();
					}
					if (dateCounter == 2) {
						reportSurveyEntry.setEndDateTimeWithTZ(matchingDate.group(0).trim());
						remaining = remaining.replace(matchingDate.group(0), "").trim();
					}
					dateCounter++;

				}
				String lineWithoutDates = remaining.trim();
				String[] splitWithSpace = lineWithoutDates.split("\\s+");
				reportSurveyEntry.setUserName(splitWithSpace[1].trim());
				remaining = remaining.replace(splitWithSpace[1], "");
				reportSurveyEntry.setStabilityClass(splitWithSpace[splitWithSpace.length - 1].trim());
				remaining = remaining.replace(splitWithSpace[splitWithSpace.length - 1], "");
				reportSurveyEntry.setTag(splitWithSpace[splitWithSpace.length - 2].trim());
				remaining = remaining.replace(splitWithSpace[splitWithSpace.length - 2], "");
				reportSurveyEntry.setAnalyzerId(splitWithSpace[splitWithSpace.length - 3].trim());
				remaining = remaining.replace(splitWithSpace[splitWithSpace.length - 3], "");
				reportSurveyEntry.setDescription(remaining.replace(splitWithSpace[0].trim(), "").trim());
				reportSurveyList.add(reportSurveyEntry);
			}
			ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> listFromStoredProc = StoredProcComplianceAssessmentGetReportDrivingSurveys.getReportDrivingSurveys(reportId);
			Iterator<StoredProcComplianceAssessmentGetReportDrivingSurveys> reportIterator = reportSurveyList.iterator();
			while (reportIterator.hasNext()) {
				if (!reportIterator.next().isInList(listFromStoredProc)) {
					return false;
				}
			}

		} finally {
			bufferReader.close();
		}

		return true;

	}

	public boolean verifySurveyNotAdded(String reportTitle, String customer, String NELat, String NELong, String SWLat, String SWLong, List<Map<String, String>> views) {
		openNewReportPage();
		inputReportTitle(reportTitle);
		fillCustomBoundaryTextFields(NELat, NELong, SWLat, SWLong);
		addViews(customer, views);
		this.clickOnOKButton();
		if (this.msgEmptySurvey.getText().equalsIgnoreCase(ComplianceReport_SurveyMissingMessage)) {
			return true;
		}

		return false;
	}

	public boolean verifySurveyAlreadyAdded(String customer, String surveyTag) {
		this.waitForCopyReportPagetoLoad();
		this.waitForDeleteSurveyButtonToLoad();
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
				this.waitForSurveySelectorCheckBoxToLoad();
				this.waitForSurveySelectorCheckBoxToBeEnabled();
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", checkboxSurFirst);
				this.btnAddSurveys.click();

				if (this.btnAddSurveys.getAttribute("value").equalsIgnoreCase(STRSurveyIncludedMsg))
					return true;
			}
		}
		return false;
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

	public boolean verifyReportSurveyMetaDataFile(String actualPath, String reportTitle) throws FileNotFoundException, IOException {
		CSVUtility csvUtility = new CSVUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String pathToMetaDataUnZip = actualPath + "//CR-" + reportId.substring(0, 6) + " (1)";
		String pathToCsv = pathToMetaDataUnZip + "//" + "CR-" + reportId.substring(0, 6) + "-ReportSurvey.csv";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		List<HashMap<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<HashMap<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceAssessmentGetReportDrivingSurveys> reportList = new ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceAssessmentGetReportDrivingSurveys reportDrivingObj = new StoredProcComplianceAssessmentGetReportDrivingSurveys();
			HashMap<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().toLowerCase().equals(reportId.trim().toLowerCase())) {
				return false;
			}
			if (!csvRow.get("ReportName").trim().equals(reportName.trim().substring(0, 9))) {
				return false;
			}
			reportDrivingObj.setStartDateTimeWithTZ(csvRow.get("SurveyStartDateTime").trim());
			reportDrivingObj.setEndDateTimeWithTZ(csvRow.get("SurveyEndDateTime").trim());
			reportDrivingObj.setUserName(csvRow.get("UserName").trim());
			reportDrivingObj.setDescription(csvRow.get("Surveyor").trim());
			reportDrivingObj.setAnalyzerId(csvRow.get("Analyzer").trim());
			reportDrivingObj.setTag(csvRow.get("Tag").trim());
			reportDrivingObj.setStabilityClass(csvRow.get("StabilityClass").trim());
			reportList.add(reportDrivingObj);
		}
		ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> listFromStoredProc = StoredProcComplianceAssessmentGetReportDrivingSurveys.getReportDrivingSurveys(reportId);
		Iterator<StoredProcComplianceAssessmentGetReportDrivingSurveys> reportIterator = reportList.iterator();
		while (reportIterator.hasNext()) {
			if (!reportIterator.next().isInList(listFromStoredProc)) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyIsotopicMetaDataFile(String actualPath, String reportTitle) throws FileNotFoundException, IOException {
		CSVUtility csvUtility = new CSVUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String pathToMetaDataUnZip = testSetup.getDownloadPath() + "//CR-" + reportId.substring(0, 6) + " (1)";
		String pathToCsv = pathToMetaDataUnZip + "//" + "CR-" + reportId.substring(0, 6) + "-ReportIsotopic.csv";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		List<HashMap<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<HashMap<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetIsotopics> reportList = new ArrayList<StoredProcComplianceGetIsotopics>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetIsotopics reportIsoObj = new StoredProcComplianceGetIsotopics();
			HashMap<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().toLowerCase().equals(reportId.trim().toLowerCase())) {
				return false;
			}
			if (!csvRow.get("ReportName").trim().equals(reportName.trim().substring(0, 9))) {
				return false;
			}
			reportIsoObj.setDateTime(csvRow.get("AnalysisDateTime").trim());
			reportIsoObj.setSurveyorUnitName(csvRow.get("Surveyor").trim());
			reportIsoObj.setDisposition(csvRow.get("Result").trim());
			String[] deltaUncertainty = csvRow.get("ValueUncertainty").split("\\+\\/");
			reportIsoObj.setDelta(Float.parseFloat(deltaUncertainty[0].trim()));
			reportIsoObj.setUncertainty(Float.parseFloat(deltaUncertainty[1].trim()));
			reportIsoObj.setText(csvRow.get("FieldNotes").trim());
			reportList.add(reportIsoObj);
		}
		ArrayList<StoredProcComplianceGetIsotopics> storedPodList = StoredProcComplianceGetIsotopics.getReportIsotopics(reportId);

		for (StoredProcComplianceGetIsotopics reportListObj : reportList) {
			if (!reportListObj.isInList(storedPodList)) {
				return false;
			}
		}

		return true;
	}

	public boolean verifyLISASMetaDataFile(String actualPath, String reportTitle) throws FileNotFoundException, IOException {
		CSVUtility csvUtility = new CSVUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String pathToMetaDataUnZip = testSetup.getDownloadPath() + "//CR-" + reportId.substring(0, 6) + " (1)";
		String pathToCsv = pathToMetaDataUnZip + "//" + "CR-" + reportId.substring(0, 6) + "-ReportLISAS.csv";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		List<HashMap<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<HashMap<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetIndications> reportList = new ArrayList<StoredProcComplianceGetIndications>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetIndications reportIndObj = new StoredProcComplianceGetIndications();
			HashMap<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().toLowerCase().equals(reportId.trim().toLowerCase())) {
				return false;
			}
			if (!csvRow.get("ReportName").trim().equals(reportName.trim().substring(0, 9))) {
				return false;
			}
			reportIndObj.setPeakNumber(csvRow.get("LisaNumber").trim());
			reportIndObj.setSurveyorUnitName(csvRow.get("Surveyor").trim());
			reportIndObj.setDateTime(csvRow.get("LISADateTime").trim());
			double amp = Math.round(Float.parseFloat((csvRow.get("Amplitude")).trim()) * 100.0) / 100.0;
			reportIndObj.setAmplitude((float) amp);
			double cH4 = Math.round(Float.parseFloat((csvRow.get("Concentration")).trim()) * 100.0) / 100.0;
			reportIndObj.setCh4((float) cH4);
			reportIndObj.setText(csvRow.get("FieldNotes").trim());
			reportList.add(reportIndObj);
		}
		ArrayList<StoredProcComplianceGetIndications> storedPodList = StoredProcComplianceGetIndications.getReportIndications(reportId);

		for (StoredProcComplianceGetIndications reportListObj : reportList) {
			if (!reportListObj.isInList(storedPodList)) {
				return false;
			}
		}

		return true;
	}

	public void verifyThumbnailInReportViewer(ReportViewerThumbnailType compliancezipmeta) {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to verify the Isotopic Analysis Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyIsotopicAnalysisTable(String actualPath, String reportTitle) throws IOException {

		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_IsotopicAnalysisTable);
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		String isoTable = RegexUtility.getStringInBetween(actualReportString, "Surveyor Date/Time Result", " Layers");
		if (isoTable != null) {
			InputStream inputStream = new ByteArrayInputStream(isoTable.getBytes());
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			try {
				ArrayList<String> reportIsotopicList = new ArrayList<String>();
				while ((line = bufferReader.readLine()) != null) {
					if (!line.trim().startsWith("Isotopic Value/ Uncertainty")) {
						reportIsotopicList.add(line);
					}
				}
				ArrayList<StoredProcComplianceGetIsotopics> storedProcIsotopicList = StoredProcComplianceGetIsotopics.getReportIsotopics(reportId);
				Iterator<StoredProcComplianceGetIsotopics> lineIterator = storedProcIsotopicList.iterator();
				ArrayList<String> storedProcConvStringList = new ArrayList<String>();
				while (lineIterator.hasNext()) {
					StoredProcComplianceGetIsotopics objStoredProc = lineIterator.next();
					String objAsString = objStoredProc.toString();
					storedProcConvStringList.add(objAsString.trim());
				}

				if (!reportIsotopicList.equals(storedProcConvStringList)) {
					return false;
				}
			} finally {
				bufferReader.close();
			}
		}
		return true;

	}

	/**
	 * Method to verify the Indication Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyIndicationTable(String actualPath, String reportTitle) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_IndicationTable);
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		InputStream inputStream = new ByteArrayInputStream(actualReportString.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		try {
			ArrayList<String> reportIndicationsList = new ArrayList<String>();
			while ((line = bufferReader.readLine()) != null) {
				if (line.trim().matches("^\\? \\d+ .*")) {
					reportIndicationsList.add(line.replaceAll("\\?", "").trim());
				}
			}
			ArrayList<StoredProcComplianceGetIndications> storedProcIndicationsList = StoredProcComplianceGetIndications.getReportIndications(reportId);
			Iterator<StoredProcComplianceGetIndications> lineIterator = storedProcIndicationsList.iterator();
			ArrayList<String> storedProcConvStringList = new ArrayList<String>();
			while (lineIterator.hasNext()) {
				StoredProcComplianceGetIndications objStoredProc = lineIterator.next();
				String objAsString = objStoredProc.toString();
				storedProcConvStringList.add(objAsString.trim());
			}

			if (!reportIndicationsList.equals(storedProcConvStringList)) {
				return false;
			}
		} finally {
			bufferReader.close();
		}

		return true;
	}

	/**
	 * Method to verify the Gaps Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyGapsTable(String actualPath, String reportTitle) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_GapTable);
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		InputStream inputStream = new ByteArrayInputStream(actualReportString.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		try {
			ArrayList<String> reportGapsList = new ArrayList<String>();
			while ((line = bufferReader.readLine()) != null) {
				if (line.trim().matches("^\\? \\w+\\d+.*")) {
					reportGapsList.add(line.replaceAll("\\?", "").replaceAll("\\s", "").trim());
				}
			}
			ArrayList<StoredProcComplianceGetGaps> storedProcGapsList = StoredProcComplianceGetGaps.getReportGaps(reportId);
			Iterator<StoredProcComplianceGetGaps> lineIterator = storedProcGapsList.iterator();
			ArrayList<String> storedProcConvStringList = new ArrayList<String>();
			while (lineIterator.hasNext()) {
				StoredProcComplianceGetGaps objStoredProc = lineIterator.next();
				String objAsString = objStoredProc.toString();
				storedProcConvStringList.add(objAsString.trim());
			}
			if (!reportGapsList.equals(storedProcConvStringList)) {
				return false;
			}
		} finally {
			bufferReader.close();
		}

		return true;
	}

	/**
	 * Method to verify the Views Images
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @param expectedImage
	 * @return
	 * @throws IOException
	 */

	public boolean verifyViewsImages(String actualPath, String reportTitle, String expectedImage) throws IOException {

		return true;
	}

	public boolean verifyCancelButtonFunctionality() {
		openNewReportPage();
		this.btnCancel.click();
		this.waitForPageLoad();

		if (isElementPresent(strBtnNewCompRpt))
			return true;

		return false;
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

	/**
	 * 1. Verify that the ZIP file has a PDF for report and 1 PDF for each view added in the Report. 2. Verify expected content in the PDF report. 3. Verify there are images present in the view PDFs.
	 */
	public void verifyReportPDFZIPFiles() {
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

	private void waitForCustomBoundarySectionToShow() {
		WebElement dvAreaModeCustom = this.divCustomBoundarySection;
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !dvAreaModeCustom.getAttribute("style").contains("display:none");
			}
		});
	}

	private void waitForResubmitPopupToShow() {
		WebElement resubmitPopupSection = this.driver.findElement(By.id("resubmitReportModal"));
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return resubmitPopupSection.getAttribute("style").contains("display:block") 
						|| resubmitPopupSection.getAttribute("style").contains("display: block");
			}
		});
	}

	private void waitForResubmitPopupToClose() {
		WebElement resubmitPopupSection = this.driver.findElement(By.id("resubmitReportModal"));
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return resubmitPopupSection.getAttribute("style").contains("display:none") 
						|| resubmitPopupSection.getAttribute("style").contains("display: none");
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
		(new WebDriverWait(driver, timeout + 15)).until(new ExpectedCondition<Boolean>() {
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
		(new WebDriverWait(driver, timeout + 15)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return checkboxSurFirst.isDisplayed();
			}
		});
	}

	public void waitForCopyReportPagetoLoad() {
		(new WebDriverWait(driver, timeout + 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(STRCopyPageTitle);

			}
		});
	}

	public void waitForSurveySelectorCheckBoxToBeEnabled() {
		(new WebDriverWait(driver, timeout + 15)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return checkboxSurFirst.isEnabled();
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

	public void waitForAddSurveyButtonToLoad() {
		(new WebDriverWait(driver, timeout + 15)).until(new ExpectedCondition<Boolean>() {
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

	public void waitForSurveySearchButtonToLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnSurveySearch.isDisplayed();
			}
		});
	}

	public void waitForInputTitleToEnable() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return inputTitle.isEnabled();
			}
		});
	}

	public void waitForOkButtonToEnable() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnOK.isEnabled();
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

	private void waitForFileDownload(String fileName, String downloadPath) {
		(new WebDriverWait(driver, timeout + 60)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return checkFileExists(fileName, downloadPath);
			}
		});
	}

	public void waitForMetadataZIPFileDownload(String reportName) {
		waitForFileDownload(reportName + " (1).zip", testSetup.getDownloadPath());
	}

	public void waitForPDFFileDownload(String reportName) {
		waitForFileDownload(reportName + ".pdf", testSetup.getDownloadPath());
	}

	public void waitForReportZIPFileDownload(String reportName) {
		waitForFileDownload(reportName + ".zip", testSetup.getDownloadPath());
	}

	public void waitForShapeZIPFileDownload(String reportName) {
		waitForFileDownload(reportName + " (2).zip", testSetup.getDownloadPath());
	}

	public void waitForReportViewerPopupToShow() {
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

	public void waitForResubmitButton() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnResubmitReport.isEnabled();
			}
		});
	}

	public WebElement getBtnResubmitReport() {
		return this.btnResubmitReport;
	}

	public WebElement getAssetErrorText() {
		return this.assetErrorText;
	}

	public WebElement getBoundaryErrorText() {
		return this.boundaryErrorText;
	}

	public WebElement getCheckBoxVehicleExhaust() {
		return checkBoxVehicleExhaust;
	}

	public WebElement getViewsAnalysesColumn() {
		return this.viewsAnalysesColumn;
	}

	public WebElement getTubularAnalysisOption() {
		return this.tubularAnalysisOption;
	}

	public WebElement getCheckBoxEtheneBiogeniceMethane() {
		return checkBoxEtheneBiogeniceMethane;
	}
	/**
	 * Method to verify the Driving Surveys Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifySurveysTableViaTag(boolean changeMode, ReportModeFilter strReportMode, String tag) throws IOException {
		boolean result = false;

		if (strReportMode != null && changeMode) {
			selectReportMode(strReportMode);

			this.getCbTag().clear();
			this.getCbTag().sendKeys(tag);
			this.waitForSurveySearchButtonToLoad();
			this.getBtnSurveySearch().click();
			this.waitForSurveyTabletoLoad();

			WebElement tabledata = driver.findElement(By.id("datatableSurveys"));
			List<WebElement> Rows = tabledata.findElements(By.xpath("//*[@id='datatableSurveys']/tbody/tr"));
			for (int getrowvalue=1; getrowvalue < Rows.size(); getrowvalue++)
			{ 
				List<WebElement> Columns = Rows.get(getrowvalue).findElements(By.xpath("//*[@id='datatableSurveys']/tbody/tr/td[6]"));
				for (int getcolumnvalue =1;getcolumnvalue<Columns.size(); getcolumnvalue++ )
				{
					String cellValue=driver.findElement(By.xpath("//*[@id='datatableSurveys']/tbody/tr["+getrowvalue+"]/td[6]")).getText(); 
					if (cellValue.contains(tag)) {
						result = true;
						break;
					}
					result=false ;
				}
			}
		}
		return result;
	}

	public boolean verifySurveysTableViaSurveyMode(boolean changeMode, ReportModeFilter strReportMode, SurveyModeFilter surveyModeFilter ) throws IOException {
		boolean result = false;

		if (strReportMode != null && changeMode) {
			selectReportMode(strReportMode);

			this.waitForSurveySearchButtonToLoad();
			this.getBtnSurveySearch().click();
			this.waitForSurveyTabletoLoad();

			WebElement tabledata = driver.findElement(By.id("datatableSurveys"));
			List<WebElement> Rows = tabledata.findElements(By.xpath("//*[@id='datatableSurveys']/tbody/tr"));
			for (int getrowvalue=1; getrowvalue < Rows.size(); getrowvalue++)
			{ 
				List<WebElement> Columns = Rows.get(getrowvalue).findElements(By.xpath("//*[@id='datatableSurveys']/tbody/tr/td[5]"));
				for (int getcolumnvalue =0;getcolumnvalue<Columns.size(); getcolumnvalue++ )
				{
					String cellValue=driver.findElement(By.xpath("//*[@id='datatableSurveys']/tbody/tr["+getrowvalue+"]/td[5]")).getText(); 
					if (cellValue.contains(" ")){
						String str=cellValue.replaceAll("\\s+", "");
		
						if (surveyModeFilter.name().equalsIgnoreCase(str)) {
							result = true;
							break;
						}
					}
					if (surveyModeFilter.name().equalsIgnoreCase(cellValue)) {
						result = true;
						break;
					}
				}

			}
		}
		return result;
	}

}

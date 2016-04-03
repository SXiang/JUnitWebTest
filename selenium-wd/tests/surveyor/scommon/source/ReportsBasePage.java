/**
 * 
 */
package surveyor.scommon.source;

import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.ACTIONTIMEOUT;
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
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.BaseHelper;
import common.source.ImagingUtility;
import common.source.Log;
import common.source.PDFUtility;
import common.source.TestSetup;
import net.avh4.util.imagecomparison.ImageComparisonResult;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.ReportsCompliance.ReportModeFilter;
import surveyor.scommon.source.ReportsCompliance.SurveyModeFilter;

/**
 * @author zlu
 *
 */
public class ReportsBasePage extends SurveyorBasePage {
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	protected WebElement btnNewComplianceRpt;
	protected String strBtnNewCompRpt = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a";

	@FindBy(how = How.ID, using = "report-title")
	protected WebElement inputTitle;

	@FindBy(how = How.ID, using = "report-timezone")
	protected WebElement cBoxTimezone;

	@FindBy(how = How.ID, using = "report-exclusion-radius")
	protected WebElement inputExclusionRadius;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div[3]/div/div/fieldset/div[3]/div[2]/div/label")
	protected WebElement inputReportModeS1;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/fieldset/div[3]/div[3]/div/label/input")
	protected WebElement inputReportModeStd;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/fieldset/div[3]/div[4]/div/label/input")
	protected WebElement inputReportRapidR;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/fieldset/div[3]/div[5]/div/label/input")
	protected WebElement inputReportModeManual;

	@FindBy(how = How.ID, using = "report-survey-mode-minimum-amplitude")
	protected WebElement inputMinAmp;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[2]/div/fieldset/div[1]/div/div/div[1]/label")
	protected WebElement inputCusBoundary;

	@FindBy(how = How.ID, using = "buttonMap")
	protected WebElement btnMapSel;

	@FindBy(how = How.ID, using = "report-map-height")
	protected WebElement inputImgMapHeight;

	@FindBy(how = How.ID, using = "report-map-width")
	protected WebElement inputImgMapWidth;

	@FindBy(how = How.ID, using = "area-start-latitude")
	protected WebElement inputNELat;

	@FindBy(how = How.ID, using = "area-start-longitude")
	protected WebElement inputNELong;

	@FindBy(how = How.ID, using = "area-end-latitude")
	protected WebElement inputSWLat;

	@FindBy(how = How.ID, using = "area-end-longitude")
	protected WebElement inputSWLong;

	@FindBy(id = "btn-select-area")
	protected WebElement latLongMapSelectorBtn;

	@FindBy(id = "report-show-indications")
	protected WebElement checkBoxIndTb;

	@FindBy(id = "report-show-isotopic")
	protected WebElement checkBoxIsoAna;

	@FindBy(id = "report-show-percent-coverage-assets")
	protected WebElement checkBoxPCA;

	@FindBy(id = "report-show-percent-coverage-report-area")
	protected WebElement checkBoxPCRA;

	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-ad701312-c470-482a-be45-ef37770e2ce6']")
	protected WebElement checkBoxOtherPla;

	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-f14735de-6c9b-4423-8533-f243a7fe4e90']")
	protected WebElement checkBoxPEPla;

	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-44353e68-0694-4f05-85cb-84d753ea278c']")
	protected WebElement checkBoxProtectedSteel;

	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-f3955e82-dd13-4842-84f7-502bcda6b57a']")
	protected WebElement checkBoxUnProtectedSteel;

	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-96caf1f5-d5c5-461d-9ce3-d210c20a1bb0']")
	protected WebElement checkBoxCastIron;

	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-d08fc87f-f979-4131-92a9-3d82f37f4bba']")
	protected WebElement checkBoxCopper;

	@FindBy(how = How.XPATH, using = "//*[@id='report-boundry-layers-Small Boundary']")
	protected WebElement checkBoxDistrictPlat;

	@FindBy(how = How.XPATH, using = "//*[@id='report-boundry-layers-Big Boundary']")
	protected WebElement checkBoxDistrict;

	@FindBy(how = How.XPATH, using = "//*[@id='report-boundry-layers-Level 1']")
	protected WebElement checkBoxLevel1;

	@FindBy(how = How.XPATH, using = "//*[@id='report-boundry-layers-Level 6']")
	protected WebElement checkBoxLevel6;

	@FindBy(how = How.ID, using = "report-surveyor-id")
	protected WebElement cbSurUnit;

	@FindBy(how = How.ID, using = "report-survey-tag")
	protected WebElement cbTag;

	@FindBy(how = How.ID, using = "report-survey-driver")
	protected WebElement userName;

	@FindBy(how = How.XPATH, using = "//*[normalize-space( )='All']//input[@name='survey-mode-type']")
	protected WebElement inputSurModeFilterAll;

	@FindBy(how = How.XPATH, using = "//*[normalize-space( )='Standard']//input[@name='survey-mode-type']")
	protected WebElement inputSurModeFilterStd;

	@FindBy(how = How.XPATH, using = "//*[normalize-space( )='Operator']//input[@name='survey-mode-type']")
	protected WebElement inputSurModeFilterOperator;

	@FindBy(how = How.XPATH, using = "//*[normalize-space( )='Rapid Response']//input[@name='survey-mode-type']")
	protected WebElement inputSurModeFilterRapidResponse;

	@FindBy(how = How.XPATH, using = "//*[normalize-space( )='Manual']//input[@name='survey-mode-type']")
	protected WebElement inputSurModeFilterManual;

	@FindBy(how = How.ID, using = "buttonSearchSurvey")
	protected WebElement btnSurveySearch;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableSurveys']/tbody/tr/td[7]/input")
	protected WebElement checkboxSurFirst;

	@FindBy(how = How.ID, using = "report-geo-filter")
	protected WebElement checkGeoFilter;

	@FindBy(how = How.ID, using = "buttonAddSurveys")
	protected WebElement btnAddSurveys;

	@FindBy(how = How.XPATH, using = "//*[@id='buttonAddViews']/span[2]")
	protected WebElement btnAddViews;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr[1]/td[2]/input")
	protected WebElement inputView1;

	@FindBy(how = How.XPATH, using = "//*[@id='deleteView2']/span")
	protected WebElement btnDeleteView2;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[2]/input")
	protected WebElement inputViewName;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[3]/input")
	protected WebElement inputViewLisa;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[4]/input")
	protected WebElement inputViewFOV;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[5]/input")
	protected WebElement inputViewBreadCrumb;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[6]/input")
	protected WebElement inputViewInd;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[7]/input")
	protected WebElement inputViewIso;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[8]/input")
	protected WebElement inputViewAnno;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr[1]/td[9]/input")
	protected WebElement inputViewGaps;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[10]/input")
	protected WebElement inputViewAssets;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[11]/input")
	protected WebElement inputViewBoundaries;

	// @FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[11]/input")
	// protected WebElement inputViewBaseMap;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[12]/select")
	protected WebElement cbMapSatellite;

	@FindBy(how = How.XPATH, using = "//*[@id='buttonOk']")
	protected WebElement btnOK;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Cancel')]")
	protected WebElement btnCancel;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable_length']/label/select")
	protected WebElement paginationInput;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]/img")
	protected WebElement actionStatus;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]/a[3]/img")
	// *[@id="datatable"]/tbody/tr[4]/td[4]/a[4]/img
	protected WebElement btnReportViewer;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]/span")
	protected WebElement errorSpan;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable_next']")
	protected WebElement nextBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='myModal']/div/div/div[3]/a[1]")
	protected WebElement btnDeleteReport;
	protected String btnDeleteReportXPath = "//*[@id='myModal']/div/div/div[3]/a[1]";

	@FindBy(how = How.XPATH, using = "//*[@id='report-customer']")
	protected WebElement dropdownCustomer;

	@FindBy(how = How.XPATH, using = "//*[@id='customerModal']/div/div/div[3]/a[1]")
	protected WebElement btnChangeCustomer;
	protected String btnChangeCustomerXPath = "//*[@id='customerModal']/div/div/div[3]/a[1]";

	@FindBy(how = How.XPATH, using = "//*[@id='surveyModal']/div/div/div[3]/a[1]")
	protected WebElement btnChangeMode;
	protected String btnChangeModeXPath = "//*[@id='surveyModal']/div/div/div[3]/a[1]";

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div/div[2]/p[1]")
	protected WebElement errorMsgDeleteCompliacneReport;
	protected String errorMsgDeleteCompliacneReportXPath = "/html/body/div/div[2]/div/div/div[2]/p[1]";

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div/div[3]/a[2]")
	protected WebElement btnReturnToHomePage;

	@FindBy(how = How.XPATH, using = "//*[@id='report-survey-start-dt']")
	protected WebElement inputStartData;

	@FindBy(how = How.XPATH, using = "//*[@id='report-survey-end-dt']")
	protected WebElement inputEndData;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	protected WebElement btnNewSysHistoryRpt;
	protected String strNewSysHistoryRpt = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a";

	@FindBy(how = How.XPATH, using = "//*[@id='surveyor']")
	protected WebElement cbSurveyUnit;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[5]/a[2]/img")
	protected WebElement btnDownload;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	protected WebElement btnNewRefGasRpt;
	protected String strNewRefGasRpt = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a";

	@FindBy(how = How.ID, using = "S1")
	protected WebElement radioBtnS1Mode;

	@FindBy(how = How.ID, using = "Standard")
	protected WebElement radioBtnStndMode;

	@FindBy(how = How.ID, using = "Rapid Response")
	protected WebElement radioBtnRRmode;

	@FindBy(how = How.ID, using = "Manual")
	protected WebElement radioBtnManualMode;

	@FindBy(how = How.ID, using = "//label[@id='report-run-0-surveymode']")
	protected WebElement labelSurveyMode;
	protected String labelStrSurveyMode = "//label[@id='report-run-0-surveymode']";

	@FindBy(how = How.ID, using = "dvErrorText")
	protected WebElement divErrorText;
	protected String strErrorText = "//div[@id='dvErrorText']";

	@FindBy(how = How.XPATH, using = "//*[@id='datatable_filter']/label/input")
	protected WebElement inputSearchReport;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[1]")
	protected WebElement tdReportTitle;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[2]")
	protected WebElement tdReportCreatedBy;

	@FindBy(how = How.XPATH, using = "//div[@id='datatable_info']")
	protected WebElement paginationMsg;

	@FindBy(how = How.XPATH, using = "//label[@id='report-run-0-surveytag']")
	protected WebElement labelFirstSurveyTag;
	protected String strFirstSurveyTag = "//label[@id='report-run-0-surveytag']";

	@FindBy(how = How.XPATH, using = "//*[@id='surveyContent-0']/div/fieldset/div/fieldset/p/button")
	protected WebElement btnDeleteDrivingSurvey;

	@FindBy(how = How.ID, using = "buttonMap")
	protected WebElement btnLatLongSelector;

	@FindBy(how = How.ID, using = "NE_lat")
	protected WebElement inputCustomNELat;

	@FindBy(how = How.ID, using = "NE_lon")
	protected WebElement inputCustomNELong;

	@FindBy(how = How.ID, using = "SW_lat")
	protected WebElement inputCustomSWLat;

	@FindBy(how = How.ID, using = "SW_lon")
	protected WebElement inputCustomSWLong;

	@FindBy(how = How.ID, using = "button_ok")
	protected WebElement btnCustomOK;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]/a[2]")
	protected WebElement btnFirstCopyCompliance;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]/a[4]")
	protected WebElement btnFirstInvestigateCompliance;

	@FindBy(how = How.ID, using = "report-FOV-opacity")
	protected WebElement inputFOVOpacity;

	@FindBy(how = How.ID, using = "report-LISA-opacity")
	protected WebElement inputLISAOpacity;

	@FindBy(id = "report-show-gaps")
	protected WebElement checkBoxGap;

	@FindBy(id = "report-show-percent-coverage-forecast")
	protected WebElement checkBoxPCF;

	@FindBy(how = How.XPATH, using = "//a[@data-target='#report-menu']")
	protected WebElement linkReportMenu;

	@FindBy(how = How.XPATH, using = "//*[@id='report-compliance']")
	protected WebElement linkComplianceReportMenu;

	@FindBy(id = "datatableSurveys")
	protected WebElement surveysTable;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable_filter']/label/input")
	protected WebElement textBoxReportSearch;

	@FindBy(how = How.XPATH, using = "//div[@id='surveyContent-0']//button[@class='btn btnDeleteSurvey btn-sm btn-danger']")
	protected WebElement btnDeleteSurvey;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[1]")
	protected WebElement tdCReportTitle;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[3]")
	protected WebElement tdCReportCreatedBy;

	@FindBy(how = How.XPATH, using = "//*[@class='dataTables_empty']")
	protected WebElement dataTableEmpty;

	@FindBy(how = How.XPATH, using = "//*[@id='dvErrorText']/ul/li")
	protected WebElement msgEmptySurvey;
	
	@FindBy(how = How.ID, using = "pdf")
	protected WebElement pdfImg;


	public static final String STRPaginationMsg = "Showing 1 to ";

	private String reportName;

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ReportsBasePage(WebDriver driver, String strBaseURL, TestSetup testSetup, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);
	}

	public WebElement getInputStartData() {
		return this.inputStartData;
	}

	public WebElement getInputEndData() {
		return this.inputEndData;
	}

	public WebElement getBtnNewComplianceRpt() {
		return this.btnNewComplianceRpt;
	}

	public WebElement getLinkReportMenu() {
		return linkReportMenu;
	}

	public WebElement getLinkComplianceReportMenu() {
		return linkComplianceReportMenu;
	}

	public void clickLatLongAreaSelectorBtn() {
		this.latLongMapSelectorBtn.click();
	}

	public WebElement getCbTag() {
		return cbTag;
	}

	public WebElement getBtnSurveySearch() {
		return btnSurveySearch;
	}

	public WebElement getCheckboxSurFirst() {
		return checkboxSurFirst;
	}

	public WebElement getBtnAddSurveys() {
		return btnAddSurveys;
	}

	public void addNewReport(Reports reports) {
		openNewReportPage();
		inputReportTitle(reports.getRptTitle());

		if (reports.getCustomer() != null && reports.getCustomer() != "Picarro") {
			selectCustomer(reports.getCustomer());
			Boolean confirmed = confirmInChangeCustomerDialog();
			if (confirmed) {
				inputReportTitle(reports.getRptTitle());
			}
		}

		fillComplianceSpecific(reports);
		fillEqSpecific(reports);
		addSurveyInformation(reports);
		this.clickOnOKButton();
	}

	public void addSurveyInformation(Reports reports) {
		Log.info("Adding Survey information");
		String surveyor = reports.getSurveyorUnit();
		String username = reports.getUsername();
		List<String> tagList = reports.getTagList();
		String startDate = reports.getSurveyStartDate();
		String endDate = reports.getSurveyEndDate();
		Boolean geoFilterOn = reports.getGeoFilter();
		
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

		handleExtraAddSurveyInfoParameters(reports);

		if ((geoFilterOn == null) || (!geoFilterOn)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", this.checkGeoFilter);
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

	/**
	 * Implementation to be provided by Derived classes.
	 */
	protected void handleExtraAddSurveyInfoParameters(Reports reports) {
		return;
	}



	public void selectStartDateForSurvey(String startDate) {
		try {
			DatetimePickerSetting dateSetting = new DatetimePickerSetting(driver, testSetup, strBaseURL, strBaseURL + getUrlString());
			PageFactory.initElements(driver, dateSetting);
			if (startDate.startsWith("0")) {
				startDate = startDate.replaceFirst("0*", "");
			}
			dateSetting.setDay("start", 7, startDate, false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectEndDateForSurvey(String endDate) {
		try {
			DatetimePickerSetting dateSetting = new DatetimePickerSetting(driver, testSetup, strBaseURL, strBaseURL + getUrlString());
			PageFactory.initElements(driver, dateSetting);
			if (endDate.startsWith("0")) {
				endDate = endDate.replaceFirst("0*", "");
			}
			dateSetting.setDay("end", 0, endDate, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openNewReportPage() {
		this.btnNewComplianceRpt.click();
		this.waitForNewPageLoad();
	}

	public void waitForNewPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(getNewPageString());
			}
		});
	}

	public boolean checkActionStatusInSeconds(String rptTitle, String strCreatedBy, int seconds) {

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
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";

			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				try {
					return handleFileDownloads(rowNum);
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

	protected boolean handleFileDownloads(int rowNum) {
		return true;
	}

	public void inputReportTitle(String rptTitle) {
		this.inputTitle.clear();
		this.inputTitle.sendKeys(rptTitle);
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

	public void clickOnOKButton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnOK);
	}

	public void inputSurveyTag(String tag) {
		this.cbTag.clear();
		this.cbTag.sendKeys(tag);
	}

	public void waitForSurveyTabletoLoad() {
		(new WebDriverWait(driver, timeout + 30)).until(new ExpectedCondition<Boolean>() {
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

	public void waitForSurveySelectorCheckBoxToBeEnabled() {
		(new WebDriverWait(driver, timeout + 15)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return checkboxSurFirst.isEnabled();
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

	public boolean checkFileExists(String fileName, String downloadPath) {
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();
		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().trim().equals(fileName.trim())) {
				Log.info("File found in the download dirctory");
				return true;
			}
		}
		return false;
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
			for (int getrowvalue = 1; getrowvalue < Rows.size(); getrowvalue++) {
				List<WebElement> Columns = Rows.get(getrowvalue).findElements(By.xpath("//*[@id='datatableSurveys']/tbody/tr/td[6]"));
				for (int getcolumnvalue = 1; getcolumnvalue < Columns.size(); getcolumnvalue++) {
					String cellValue = driver.findElement(By.xpath("//*[@id='datatableSurveys']/tbody/tr[" + getrowvalue + "]/td[6]")).getText();
					if (cellValue.contains(tag)) {
						result = true;
						break;
					}
					result = false;
				}
			}
		}
		return result;
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
		}

		this.inputTitle.clear();
		this.inputTitle.sendKeys(title);

		selectTimeZone(timeZone);

		complianceSpecificOtherDetails(exclusionRadius, boundary, imageMapHeight, imageMapWidth, NELat, NELong, SWLat, SWLong, surUnit, tagList, startDate, endDate, changeMode, strReportMode);

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

		complianceSpecificViewDetails(customer, boundary);

		this.clickOnOKButton();
	}

	public boolean verifyIfInDrivingSurvey(String columnName) {
		boolean result = false;
		int count = driver.findElements(By.xpath("//*[@id='surveyContent-0']/div/fieldset/div/fieldset/div[2]/div")).size();
		for (int i = 1; i < count + 1; i++) {
			String str = driver.findElement(By.xpath("//*[@id='surveyContent-0']/div/fieldset/div/fieldset/div[2]/div[" + i + "]/label")).getText();
			if (str != columnName) {
				result = true;
			}
		}
		return result;

	}

	public void waitForFileDownload(String fileName, String downloadPath) {
		(new WebDriverWait(driver, timeout + 60)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return checkFileExists(fileName, downloadPath);
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

	public String getUrlString() {
		return null;
	}

	public String getStrCopyPageText() {
		return null;
	}

	public String getNewPageString() {
		return null;
	}

	public void fillComplianceSpecific(Reports reportsCompliance) {
	}

	public void selectReportMode(ReportModeFilter mode) {

	}

	public void addNewReportWithMultipleSurveysIncluded(Reports reportsCompliance) {
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

		this.clickOnOKButton();

	}

	public void selectTimeZone(String timeZone) {
		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if (timeZone.equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}
	}

	public void selectSurveySurveyor(String surveyorUnit) {
		List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
		for (WebElement option : optionsSU) {
			if (surveyorUnit.equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}
	}

	public void selectSurveyCheckBox(WebElement checkboxSurFirst) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkboxSurFirst);
	}

	public void complianceSpecificMultipleSurveys(Reports reportsCompliance) {
	}

	public void complianceSpecificViewDetails(String customer, String boundary) {
		// TODO Auto-generated method stub

	}

	public void complianceSpecificOtherDetails(String exclusionRadius, String boundary, String imageMapHeight, String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong, String surUnit, List<String> tagList, String startDate, String endDate, boolean changeMode, String strReportMode) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// add class testing code here
	}

	public void addNewReport(String title, String customer, String timeZone, String exclusionRadius, String boundary, String imageMapHeight, String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong, String surUnit, String tag, String startDate, String endDate, String surModeFilter) {
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
		complianceSpecificAddNewReport(exclusionRadius, boundary, imageMapHeight, imageMapWidth, NELat, NELong, SWLat, SWLong);

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
		this.clickOnOKButton();
	}

	public boolean checkActionStatus(String rptTitle, String strCreatedBy, String testCaseID) throws Exception {
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
		
			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle.trim()) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy.trim())) {
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
						if(handleFileDownloads(rptTitle, testCaseID)){
							Log.info("Handle File Download");
							return true;
						}

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

	public void complianceSpecificAddNewReport(String exclusionRadius, String boundary, String imageMapHeight, String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong) {

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

	public boolean copyReport(String rptTitle, String strCreatedBy) {
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

				if (this.isElementPresent(getBtnDeleteConfirmXpath())) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", getBtnDeleteConfirm());
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

	public boolean modifyReportDetails(String rptTitleNew, String surUnit, List<String> tagList, boolean changeMode, ReportModeFilter strReportMode) {
		this.waitForCopyReportPagetoLoad();

		this.inputTitle.clear();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + rptTitleNew + "';", inputTitle);

		complianceChangeMode(rptTitleNew, changeMode, strReportMode);
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

		if (tagList.isEmpty()) {
			this.btnSurveySearch.click();
			this.waitForSurveyTabletoLoad();
			this.checkboxSurFirst.click();
			this.waitForAddSurveyButtonToLoad();
			this.btnAddSurveys.click();
		}

		modifyComplianceViews();

		this.clickOnOKButton();
		return true;
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

	public boolean verifySurveyNotAdded(String reportTitle, String customer, String NELat, String NELong, String SWLat, String SWLong, List<Map<String, String>> views) {
		openNewReportPage();
		inputReportTitle(reportTitle);
		addComplianceSpecificSurveys(customer, NELat, NELong, SWLat, SWLong, views);

		this.clickOnOKButton();
		if (this.msgEmptySurvey.getText().equalsIgnoreCase(getSurveyMissingMessage())) {
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

				if (this.btnAddSurveys.getAttribute("value").equalsIgnoreCase(getSTRSurveyIncludedMsg()))
					return true;
			}
		}
		return false;
	}

	
	public BufferedImage cropImage(BufferedImage src, Rectangle rect) {
		BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
		return dest;
	}

	public boolean verifyActualImageWithBase(String pathToActualImage, String pathToBaseImage) {
		ImagingUtility imageUtil = new ImagingUtility();
		ImageComparisonResult result = imageUtil.compareImages(pathToActualImage, pathToBaseImage);
		if ((result.getFailureMessage() != null) && (result.isEqual() == true)) {
			return false;
		}
		return true;
	}

	public String getSTRSurveyIncludedMsg() {
		return null;
	}

	public void openNewComplianceReportPage() {
		openNewReportPage();
	}

	public boolean verifyCancelButtonFunctionality() {
		openNewReportPage();
		this.btnCancel.click();
		this.waitForPageLoad();

		if (isElementPresent(strBtnNewCompRpt))
			return true;

		return false;
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

	public void fillEqSpecific(Reports reports) {

	}

	public void waitForCopyReportPagetoLoad() {
		(new WebDriverWait(driver, timeout + 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(getStrCopyPageText());

			}
		});
	}

	public void waitForDeletePopupLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return getBtnDeleteConfirm().isDisplayed();
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

	public void waitForDeleteSurveyButtonToLoad() {
		(new WebDriverWait(driver, timeout + 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnDeleteSurvey.isDisplayed();
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

	public void waitForNumberOfRecords(String actualMessage) {
		(new WebDriverWait(driver, timeout + 15)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return paginationMsg.getText().substring(0, 16).trim().equals(actualMessage);
			}
		});
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportTitle) {
		this.reportName = reportTitle;
	}

	public WebElement getTextBoxReportSerach() {
		return textBoxReportSearch;
	}

	public WebElement getDataTableEmpty() {
		return dataTableEmpty;
	}

	public boolean handleFileDownloads(String rptTitle, String testCaseID) {
		return true;
	}

	public void waitForPdfReportIcontoAppear() {
		(new WebDriverWait(driver, timeout + 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return pdfImg.isDisplayed();

			}
		});
	}

	public WebElement getBtnDeleteConfirm() {
		return null;
	}

	public String getBtnDeleteConfirmXpath() {
		return null;
	}

	public void modifyComplianceViews() {

	}

	public void complianceChangeMode(String rptTitleNew, boolean changeMode, ReportModeFilter strReportMode) {

	}

	public void addComplianceSpecificSurveys(String customer, String NELat, String NELong, String SWLat, String SWLong, List<Map<String, String>> views) {

	}

	public String getSurveyMissingMessage() {
		// TODO Auto-generated method stub
		return null;
	}


}
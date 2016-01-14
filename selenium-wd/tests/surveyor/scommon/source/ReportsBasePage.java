/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.TestSetup;

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

	// @FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[2]/div/fieldset/div[1]/div/div/div[1]/label")
	// protected WebElement inputCusBoundary;

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

	@FindBy(how = How.XPATH, using = "//*[@id='report-boundry-layers-District Plat']")
	protected WebElement checkBoxDistrictPlat;

	@FindBy(how = How.XPATH, using = "//*[@id='report-boundry-layers-District']")
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

	public void clickLatLongAreaSelectorBtn() {
		this.latLongMapSelectorBtn.click();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// add class testing code here
	}
}
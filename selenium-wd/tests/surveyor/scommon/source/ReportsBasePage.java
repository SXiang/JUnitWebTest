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
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	protected WebElement btnNewComplianceRpt;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-title']")
	protected WebElement inputTitle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-timezone']")
	protected WebElement cBoxTimezone;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-exclusion-radius']")
	protected WebElement inputExclusionRadius;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/fieldset/div[3]/div[2]/div/label/input")
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[2]/div/div/fieldset/div[3]/div[2]/div/label/input")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div[3]/div/div/fieldset/div[3]/div[2]/div/label")
	protected WebElement inputReportModeS1;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/fieldset/div[3]/div[3]/div/label/input")
	protected WebElement inputReportModeStd;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/fieldset/div[3]/div[4]/div/label/input")
	protected WebElement inputReportRapidR;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/fieldset/div[3]/div[5]/div/label/input")
	protected WebElement inputReportModeManual;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-survey-mode-minimum-amplitude']")
	protected WebElement inputMinAmp;
	
//	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[2]/div/fieldset/div[1]/div/div/div[1]/label")
//	protected WebElement inputCusBoundary;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[2]/div/fieldset/div[1]/div/div/div[1]/label")
	protected WebElement inputCusBoundary;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonMap']")
	protected WebElement btnMapSel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-map-height']")
	protected WebElement inputImgMapHeight;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-map-width']")
	protected WebElement inputImgMapWidth;
	
	@FindBy(how = How.XPATH, using = "//*[@id='start-lat']")
	protected WebElement inputNELat; 
	
	@FindBy(how = How.XPATH, using = "//*[@id='start-lng']")
	protected WebElement inputNELong;
	
	@FindBy(how = How.XPATH, using = "//*[@id='end-lat']")
	protected WebElement inputSWLat;

	@FindBy(how = How.XPATH, using = "//*[@id='end-lng']")
	protected WebElement inputSWLong;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-show-indications']")
	protected WebElement checkBoxIndTb;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-show-isotopic']")
	protected WebElement checkBoxIsoAna;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-show-percent-coverage-assets']")
	protected WebElement checkBoxPCA;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-show-percent-coverage-report-area']")
	protected WebElement checkBoxPCRA;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-00000000-0000-0000-0000-000000000001']")
	protected WebElement checkBoxOtherPla;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-00000000-0000-0000-0000-000000000002']")
	protected WebElement checkBoxPEPla;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-00000000-0000-0000-0000-000000000003']")
	protected WebElement checkBoxProtectedSteel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-00000000-0000-0000-0000-000000000004']")
	protected WebElement checkBoxUnProtectedSteel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-00000000-0000-0000-0000-000000000005']")
	protected WebElement checkBoxCastIron;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-00000000-0000-0000-0000-000000000006']")
	protected WebElement checkBoxCopper;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-boundry-layers-Level 1']")
	protected WebElement checkBoxLevel1;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-boundry-layers-Level 6']")
	protected WebElement checkBoxLevel6;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-surveyor-id']")
	protected WebElement cbSurUnit;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-survey-tag']")
	protected WebElement cbTag;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-survey-start-dt']")
	protected WebElement cbStartDate;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-survey-end-dt']")
	protected WebElement cbEndDate;
  
//	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[5]/div/div[1]/fieldset/div[5]/div/div[1]/div/label/input")
//	protected WebElement inputSurModeFilterS1;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[5]/div/div[1]/fieldset/div[5]/div/div[1]/div/label/input")
	protected WebElement inputSurModeFilterS1;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[5]/div/div[1]/fieldset/div[5]/div/div[2]/div/label/input")
	protected WebElement inputSurModeFilterStd;	
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[5]/div/div[1]/fieldset/div[5]/div/div[5]/div/label/input")
	protected WebElement inputSurModeFilterOperator;	
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonSearchSurvey']")
	protected WebElement btnSruveySearch;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatableSurveys']/tbody/tr/td[7]/input")
	protected WebElement checkboxSurFirst;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonAddSurveys']")
	protected WebElement btnAddSurveys;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonAddViews']") 
	protected WebElement btnAddViews;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr[1]/td[2]/input")
	protected WebElement inputView1;
	
	@FindBy(how = How.XPATH, using = "//*[@id='deleteView2']/span")
	protected WebElement btnDeleteView2;	
	
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
	
//	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr[1]/td[9]/input")
//	protected WebElement inputViewGaps;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[9]/input")
	protected WebElement inputViewAssets;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[10]/input")
	protected WebElement inputViewBoundaries;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[11]/input")
	protected WebElement inputViewBaseMap;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/td[12]/select")
	protected WebElement cbMapSatellite;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonOk']")
	protected WebElement btnOK;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div[7]/a")
	protected WebElement btnCancel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_length']/label/select")
	protected WebElement paginationInput;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]/img")
	protected WebElement actionStatus;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]/span")
	protected WebElement errorSpan;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='datatable_wrapper']/div[2]/div[2]/div/ul/li[3]/a")
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_next']")
	protected WebElement nextBtn;
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ReportsBasePage(WebDriver driver, String strBaseURL, TestSetup testSetup, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);
	}	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// add class testing code here
	}
}
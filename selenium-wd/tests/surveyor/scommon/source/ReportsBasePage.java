/**
 * 
 */
package surveyor.scommon.source;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.iterators.EntrySetMapIterator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import common.source.TestSetup;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;

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
	protected WebElement inputStartDate;

	@FindBy(how = How.XPATH, using = "//*[@id='report-survey-end-dt']")
	protected WebElement inputEndDate;

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

    @FindBy(name = "survey-mode-type")
    private List<WebElement> surveyModeTypeRadiobuttonList;
    
    @FindBy(name = "report-survey-mode-type")
    private List<WebElement> reportSurveyModeTypeRadiobuttonList;
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ReportsBasePage(WebDriver driver, String strBaseURL, TestSetup testSetup, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);
	}

	public WebElement getInputStartDate() {
		return this.inputStartDate;
	}

	public WebElement getInputEndDate() {
		return this.inputEndDate;
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

	public WebElement getCbTag(){
		return cbTag;
	}
	
	public WebElement getBtnSurveySearch(){
		return btnSurveySearch;
	}
	
	public WebElement getCheckboxSurFirst(){
		return checkboxSurFirst;
	}
	
	public WebElement getBtnAddSurveys(){
		return btnAddSurveys;
	}
	
	/***** Report values *****/
    
    public String getReportTitle() {
    	return this.inputTitle.getText();
    }
    
    /**
     * Returns the selected Timezone value.
     */
    public String getTimezoneValue() {
    	return new Select(cBoxTimezone).getFirstSelectedOption().getText();
    }    
    
    /**
     * Returns the selected Customer value.
     */
    public String getCustomerValue() {
    	return new Select(dropdownCustomer).getFirstSelectedOption().getText();
    }    

    public String getExclusionRadius() {
    	return this.inputExclusionRadius.getText();
    }

    /**
     * Get the selected Report Mode.
     */
    public ReportModeFilter getReportModeFilter() {
        for (WebElement radElement : reportSurveyModeTypeRadiobuttonList) {
        	HashMap<String, ReportModeFilter> reportSurveyModeFilterGuids = SurveyorConstants.ReportSurveyModeFilterGuids;
        	Set<Entry<String, ReportModeFilter>> entrySet = reportSurveyModeFilterGuids.entrySet();
        	for (Entry<String, ReportModeFilter> entry : entrySet) {
				if (entry.getKey().equals(radElement.getAttribute("value")) ) {
					if (radElement.isSelected()) 
						return entry.getValue();
				}
			}
        }
        return ReportModeFilter.All;
    }
    
    /***** Area Selector values *****/
    
    public String getNELatitude() {
    	return this.inputNELat.getText();
    }
    
    public String getNELongitude() {
    	return this.inputNELong.getText();
    }

    public String getSWLatitude() {
    	return this.inputSWLat.getText();
    }
    
    public String getSWLongitude() {
    	return this.inputSWLong.getText();
    }
    
    /***** Survey Selector values *****/

    public String getSurveySurveyorValue() {
    	return new Select(cbSurUnit).getFirstSelectedOption().getText();
    }    

    public String getSurveyUsername() {
    	return this.userName.getText(); 
    }

    public String getSurveyTag() {
    	return this.cbTag.getText();
    }

    public String getSurveyStartDate() {
    	return this.inputStartDate.getText();
    }

    public String getSurveyEndDate() {
    	return this.inputEndDate.getText();
    }

    /**
     * Get the selected Survey Mode.
     */
    public SurveyModeFilter getSurveyModeFilter() {
        for (WebElement radElement : surveyModeTypeRadiobuttonList) {
        	HashMap<String, SurveyModeFilter> surveyModeFilterGuids = SurveyorConstants.SurveyModeFilterGuids;
        	Set<Entry<String, SurveyModeFilter>> entrySet = surveyModeFilterGuids.entrySet();
        	for (Entry<String, SurveyModeFilter> entry : entrySet) {
				if (entry.getKey().equals(radElement.getAttribute("value")) ) {
					if (radElement.isSelected()) 
						return entry.getValue();
				}
			}
        }
        return SurveyModeFilter.All;
    }
    
    public boolean isSurveyGeoFilterSelected() {
    	return this.checkGeoFilter.isSelected();
    }

    /************ Opacity Fine Tuning Values *************/
    
    public String getFOVOpacity() {
    	return this.inputFOVOpacity.getText();
    }

    public String getLISAOpacity() {
    	return this.inputLISAOpacity.getText();
    }

    /************ PDF Output Values *************/
    
    public String getPDFWidth() {
    	return this.inputImgMapWidth.getText();
    }

    public String getPDFHeight() {
    	return this.inputImgMapHeight.getText();
    }
    
    /************ PDF Output Values *************/
    
    public boolean isViewLisaSelected() {
    	return inputViewLisa.isSelected();
    }

    public boolean isViewFOVSelected() {
    	return inputViewFOV.isSelected();
    }
    
    public boolean isViewBreadcrumbSelected() {
    	return inputViewBreadCrumb.isSelected();
    }

    public boolean isViewIndicationSelected() {
    	return inputViewInd.isSelected();
    }
    
    public boolean isViewIsotopicSelected() {
    	return inputViewIso.isSelected();
    }
    
    public boolean isViewFieldNotesSelected() {
    	return inputViewAnno.isSelected();
    }
    
    public boolean isViewGapsSelected() {
    	return inputViewGaps.isSelected();
    }
    
    public boolean isViewAssetsSelected() {
    	return inputViewAssets.isSelected();
    }
    
    public boolean isViewBoundariesSelected() {
    	return inputViewBoundaries.isSelected();
    }

    /************** Opt View Layers *****************/
    
    // Assets
    
    public boolean isCopperSelected() {
    	return checkBoxCopper.isSelected();
    }

    public boolean isUnprotectedSteelSelected() {
    	return checkBoxUnProtectedSteel.isSelected();
    }
    
    public boolean isProtectedSteelSelected() {
    	return checkBoxProtectedSteel.isSelected();
    }
    
    public boolean isCastIronSelected() {
    	return checkBoxCastIron.isSelected();
    }
    
    public boolean isOtherPlasticSelected() {
    	return checkBoxOtherPla.isSelected();
    }
    
    public boolean isPEPlasticSelected() {
    	return checkBoxPEPla.isSelected();
    }
    
    // Boundary
    
    public boolean isDistrictPlatSelected() {
    	return checkBoxDistrictPlat.isSelected();
    }
    
    public boolean isDistrictSelected() {
    	return checkBoxDistrict.isSelected();
    }
    
    /************** Opt Tabular PDF content *****************/
    
    public boolean isPDFIndicationSelected() {
    	return checkBoxIndTb.isSelected();
    }
    
    public boolean isPDFIsotopicAnalysisSelected() {
    	return checkBoxIsoAna.isSelected();
    }

    public boolean isPDFGapSelected() {
    	return checkBoxIsoAna.isSelected();
    }

    public boolean isPDFPercentCoverageAssetsSelected() {
    	return checkBoxPCA.isSelected();
    }
    
    public boolean isPDFPercentCoverageReportAreaSelected() {
    	return checkBoxPCRA.isSelected();
    }

    public boolean isPDFPercentCoverageForecastSelected() {
    	return checkBoxPCF.isSelected();
    }
    
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// add class testing code here
	}
}
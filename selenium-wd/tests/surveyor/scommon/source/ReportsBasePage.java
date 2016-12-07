/**
 *
 */
package surveyor.scommon.source;

import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_PICARRO;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Supplier;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.relevantcodes.extentreports.LogStatus;

import common.source.ApiUtility;
import common.source.BaseHelper;
import common.source.CSVUtility;
import common.source.Constants;
import common.source.DateUtility;
import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.ImagingUtility;
import common.source.Log;
import common.source.PDFUtility;
import common.source.PollManager;
import common.source.RegexUtility;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.WebElementExtender;
import net.avh4.util.imagecomparison.ImageComparisonResult;
import surveyor.api.source.ReportJobsStat;
import surveyor.dataaccess.source.DBCache;
import surveyor.dataaccess.source.Report;
import surveyor.scommon.source.ReportJobPerfDBStat;
import surveyor.scommon.source.Reports.ReportJobType;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.ReportStatusType;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsSurveyInfo;
import surveyor.scommon.source.SurveyorConstants.Environment;
import surveyor.scommon.source.SurveyorConstants.ReportColorOption;

/**
 * @author zlu
 *
 */
public class ReportsBasePage extends SurveyorBasePage {
	public static final String STRSurveyPaginationMsgPattern = "Showing [\\d,]+ to [\\d,]+ of [\\d,]+ entries \\(filtered from [\\d,]+ total entries\\)|Showing [\\d,]+ to [\\d,]+ of [\\d,]+ entries";

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

	@FindBy(css = "#page-wrapper  fieldset  div.radio > .report-survey-mode-text > #Standard")
	protected WebElement inputReportModeStd;

	@FindBy(css = "#page-wrapper  fieldset  div.radio > .report-survey-mode-text > [id='Rapid Response']")
	protected WebElement inputReportModeRapidR;

	@FindBy(css = "#page-wrapper  fieldset  div.radio > .report-survey-mode-text > #Manual")
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

	@FindBy(id = "report-show-gaps")
	protected WebElement checkBoxGapTb;

	@FindBy(id = "report-show-indications")
	protected WebElement checkBoxIndTb;

	@FindBy(id = "report-show-isotopic")
	protected WebElement checkBoxIsoAna;

	@FindBy(id = "report-show-percent-coverage-assets")
	protected WebElement checkBoxPCA;

	@FindBy(id = "report-show-percent-coverage-report-area")
	protected WebElement checkBoxPCRA;

	@FindBy(how = How.ID, using = "report-surveyor-id")
	protected WebElement cbSurUnit;

	@FindBy(how = How.ID, using = "report-survey-tag")
	protected WebElement cbTag;

	@FindBy(how = How.ID, using = "report-survey-driver")
	protected WebElement userName;

	@FindBy(how = How.XPATH, using = "//input[@name='survey-mode-type' and @id='All']")
	protected WebElement inputSurModeFilterAll;

	@FindBy(how = How.XPATH, using = "//input[@name='survey-mode-type' and @id='Standard']")
	protected WebElement inputSurModeFilterStd;

	@FindBy(how = How.XPATH, using = "//input[@name='survey-mode-type' and @id='Operator']")
	protected WebElement inputSurModeFilterOperator;

	@FindBy(how = How.XPATH, using = "//input[@name='survey-mode-type' and @id='Rapid Response']")
	protected WebElement inputSurModeFilterRapidResponse;

	@FindBy(how = How.XPATH, using = "//input[@name='survey-mode-type' and @id='Manual']")
	protected WebElement inputSurModeFilterManual;

	@FindBy(how = How.ID, using = "buttonSearchSurvey")
	protected WebElement btnSurveySearch;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableSurveys']/tbody/tr/td/input[@type='checkbox']")
	protected WebElement checkboxSurFirst;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableSurveys']/tbody/tr/td/input[@type='checkbox']")
	protected List<WebElement> checkboxSurveys;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableSurveys']/tbody/tr/td/a")
	protected WebElement firstSurveyLink;

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

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr")
	protected List<WebElement> dataTableViews;

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

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]/a[3]")
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

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[5]/a[2]")
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

	@FindBy(css = "#dvErrorText > ul > li")
	protected List<WebElement> listOfErrors;

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

	@FindBy(css = ".surveyGroup > [style=''] button.btnDeleteSurvey")
	protected List<WebElement> btnDeleteDrivingSurveys;

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

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td[5]/a[@title='Copy']")
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

	@FindBy(how = How.ID, using = "compliance-table-pdf-download")
	protected WebElement pdfImg;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableSurveys_length']/label/select")
	protected WebElement surveyTableRows;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableSurveys']/tbody")
	protected WebElement surveyTable;

	private String reportName;
	private String reportId;
	@FindBy(name = "survey-mode-type")
	private List<WebElement> surveyModeTypeRadiobuttonList;

	@FindBy(name = "report-survey-mode-type")
	private List<WebElement> reportSurveyModeTypeRadiobuttonList;

	@FindBy(id = "datatableSurveys_next")
	protected WebElement surveyNextButton;

	@FindBy(id = "modalClose")
	private WebElement reportViewerCloseButton;

	private static final String SURVEY_GROUP_XPATH = "*[@id='page-wrapper']/div/div[3]/div/div[6]/div/div[3]";

	@FindBy(how = How.XPATH, using = SURVEY_GROUP_XPATH)
	private WebElement surveyGroup;

	private static final String SURVEY_GROUP_DIVS_XPATH = "*[@id='page-wrapper']/div/div[3]/div/div[6]/div/div[3]/div";

	@FindBy(how = How.XPATH, using = SURVEY_GROUP_DIVS_XPATH)
	private WebElement surveyGroupDivs;

	@FindBy(how = How.CSS, using = ".surveyGroup > [id^=surveyContent-]:not(#surveyContent-x)")
	private List<WebElement> selectedSurveys;

	private Integer reportGenerationTimeoutInSeconds = SurveyorConstants.ACTIONTIMEOUT + 900;

	private static String surveyTableHeaderColumnBaseXPath = "//*[@id='datatableSurveys']/thead/tr/th[%d]";

	private List<ReportJobPerfDBStat> postDBStatList = null;

	private ChangeCustomerDialogControl changeCustomerDialog = null;

	private long reportStartEpochTime;

	private long reportEndEpochTime;

	private List<String> reportJobComparisonFailureMessages;

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ReportsBasePage(WebDriver driver, String strBaseURL, TestSetup testSetup, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);

		this.changeCustomerDialog = new ChangeCustomerDialogControl(driver);
		PageFactory.initElements(driver, changeCustomerDialog);

		reportJobComparisonFailureMessages = new ArrayList<String>();
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
		Log.clickElementInfo("Lat/Long Map Selector");
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

	protected String getSoftwareVersionFromPDF(Supplier<String> downloadPath) {
		String softwareVersion = "";
		PDFUtility pdfUtility = new PDFUtility();
		try {
			String pdfText = (pdfUtility.extractPDFText(downloadPath.get()));
			softwareVersion = getSoftwareVersionFromPDFText(pdfText);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return softwareVersion;
	}

	private String getSoftwareVersionFromPDFText(String inputString) {
		String softwareVersion = "";
		List<String> lines = RegexUtility.split(inputString, RegexUtility.NEWLINE_SPLIT_REGEX_PATTERN);
		for (String lineText : lines) {
			if (lineText.contains("Software Version")) {
				List<String> matchingGroups = RegexUtility.getMatchingGroups(lineText, RegexUtility.APP_VERSION_PATTERN);
				if (matchingGroups != null && matchingGroups.size() > 0) {
					softwareVersion = matchingGroups.get(0);
					break;
				}
			}
		}

		return softwareVersion;
	}

	/**
	 * Get the selected Report Mode.
	 */
	public ReportModeFilter getReportModeFilter() {
		for (WebElement radElement : reportSurveyModeTypeRadiobuttonList) {
			Map<String, ReportModeFilter> reportSurveyModeFilterGuids = Reports.ReportSurveyModeFilterGuids;
			Set<Entry<String, ReportModeFilter>> entrySet = reportSurveyModeFilterGuids.entrySet();
			for (Entry<String, ReportModeFilter> entry : entrySet) {
				if (entry.getKey().equals(radElement.getAttribute("value"))) {
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
			Map<String, SurveyModeFilter> surveyModeFilterGuids = Reports.SurveyModeFilterGuids;
			Set<Entry<String, SurveyModeFilter>> entrySet = surveyModeFilterGuids.entrySet();
			for (Entry<String, SurveyModeFilter> entry : entrySet) {
				if (entry.getKey().equals(radElement.getAttribute("value"))) {
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

	/************ Report Generation Timeout *************/

	public Integer getReportGenerationTimeout() {
		return reportGenerationTimeoutInSeconds;
	}

	public void setReportGenerationTimeout(Integer reportGenerationTimeout) {
		this.reportGenerationTimeoutInSeconds = reportGenerationTimeout;
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

	public void setSurveyRowsPagination(String numPages) {
		By tableInfoBy = By.id(DATATABLESURVEYS_RECORDS_ELEMENT_ID);
		List<WebElement> options = this.surveyTableRows.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (numPages.equals(option.getText().trim())) {
				Log.info(String.format("Select Pagination - '%s'", numPages));
				option.click();
				waitForNumberOfRecords(tableInfoBy, STRSurveyPaginationMsgPattern);
				break;
			}
		}
	}

	public void addNewReport(Reports reports) throws Exception {
		addNewReport(reports, true /**/);
	}

	public void addNewReport(Reports reports, boolean openNewReportsPage) throws Exception {
		if (openNewReportsPage) {
			openNewReportPage();
		}
		fillReport(reports);
		addReport();
	}

	public void selectFOVColor(ReportColorOption... colors){
		By fovPathBy = By.cssSelector(".form-group [id$=-fov-color-picker] > .ColorBlotch");
		selectColor(fovPathBy, colors);
	}

	public void selectLISAColor(ReportColorOption... colors){
		By fovPathBy = By.cssSelector(".form-group [id$=-lisa-color-picker] > .ColorBlotch");
		selectColor(fovPathBy, colors);
	}

	public void selectColor(By colorPickerBy, ReportColorOption... colors){
		for(int i=selectedSurveys.size()-1,j=0; i>-1; i--){
			WebElement selectedSurvey = selectedSurveys.get(i);
			List<WebElement> colorPicker = selectedSurvey.findElements(colorPickerBy);
			ReportColorOption colorOption = colors[j++ % colors.length];
			int colorIndex = colorOption.toIndex();

			Log.clickElementInfo(String.format("Select color '%s' at index '%d'", colorOption, colorIndex));
			colorPicker.get(colorIndex).click();
		}
	}
	public void fillReport(Reports reports) throws Exception {
		// 1. Title and Customer
		inputReportTitle(reports.getRptTitle());
		if (reports.getCustomer() != null && !reports.getCustomer().equalsIgnoreCase(CUSTOMER_PICARRO)) {
			Log.info("Select customer '"+reports.getCustomer()+"'");
			selectCustomer(reports.getCustomer());
			Boolean confirmed = getChangeCustomerDialog().confirmInChangeCustomerDialog();
			if (confirmed) {
				inputReportTitle(reports.getRptTitle());
			}
		}

		// 2. Other parameters
		fillReportSpecific(reports);

		// 3. Add Surveys
		if (reports.getSurveyInfoList() != null) {
			addMultipleSurveysToReport(reports);
		} else {
			addSurveyInformation(reports);
		}

		setReportStartEpochTime(DateUtility.getCurrentUnixEpochTime());

	}

	public void addReport(){
		this.clickOnOKButton();
	}

	public void cancelReport(){
		this.clickOnCancelBtn();
	}

	public void addSurveyInformation(Reports reports) throws Exception {
		addSurveyInformation(reports, null);
	}

	public void addSurveyInformation(Reports reports, List<Integer> tagIndexes) throws Exception {
		Log.info("Adding Survey information");
		String surveyor = reports.getSurveyorUnit();
		String username = reports.getUsername();
		List<String> tagList = reports.getTagList();
		String startDate = reports.getSurveyStartDate();
		String endDate = reports.getSurveyEndDate();
		Boolean geoFilterOn = reports.getGeoFilter();

		selectSurveyInfoSurveyorUnit(surveyor);
		enterSurveyInfoUsername(username);
		selectSurveyInfoStartDate(startDate);
		selectSurveyInfoEndDate(endDate);

		handleExtraAddSurveyInfoParameters(reports);

		selectSurveyInfoGeoFilter(geoFilterOn);

		for (String tagValue : tagList) {
			inputSurveyTag(tagValue);
			clickOnSearchSurveyButton();
			selectSurveysAndAddToReport(false /*selectAll*/, 1 /*numSurveysToSelect*/);
		}
	}

	public void addMultipleSurveysToReport(Reports reports) throws Exception {
		Log.info("Adding all Surveys matching information.");

		List<ReportsSurveyInfo> surveyInfoList = reports.getSurveyInfoList();
		for (ReportsSurveyInfo reportsSurveyInfo : surveyInfoList) {

			// Enter the survey fields.
			selectSurveyInfoSurveyorUnit(reportsSurveyInfo.getSurveyor());
			enterSurveyInfoUsername(reportsSurveyInfo.getUsername());
			selectSurveyInfoStartDate(reportsSurveyInfo.getStartDate());
			selectSurveyInfoEndDate(reportsSurveyInfo.getEndDate());
			handleExtraAddSurveyInfoParameters(reportsSurveyInfo.getSurveyModeFilter());
			selectSurveyInfoGeoFilter(reportsSurveyInfo.isGeoFilterOn());
			inputSurveyTag(reportsSurveyInfo.getTag());

			// Click on Search survey button.
			clickOnSearchSurveyButton();
			// Select the specified number of surveys and add them to report.
			selectSurveysAndAddToReport(reportsSurveyInfo.isSelectAllSurveys(),
					reportsSurveyInfo.getNumberOfSurveysToSelect());
		}
	}

	public void selectSurveysAndAddToReport(boolean selectAll, Integer numSurveysToSelect) throws Exception {
		Log.method("selectSurveysAndAddToReport", selectAll, numSurveysToSelect);
		if (selectAll || numSurveysToSelect > 0) {
			setSurveyRowsPagination(PAGINATIONSETTING);
			this.waitForSurveyTabletoLoad();

			Integer selectedSurveysCount = 0;
			if (selectAll) {
				numSurveysToSelect = Integer.MAX_VALUE;
			}

			String checkBoxXPath;
			WebElement checkBoxActionCell;

			List<WebElement> rows = surveyTable.findElements(By.xpath("tr"));

			int rowSize = rows.size();

			// Verify survey table is NOT empty.
			verifySurveyTableIsNotEmpty(rows);

			int loopCount = 0;

			if (rowSize < Integer.parseInt(PAGINATIONSETTING))
				loopCount = rowSize;
			else
				loopCount = Integer.parseInt(PAGINATIONSETTING);

			// Loop through table elements and check selected number of surveys.
			for (int rowNum = 1; rowNum <= loopCount && selectedSurveysCount < numSurveysToSelect; rowNum++) {
				checkBoxXPath = "tr[" + rowNum + "]/td/input[@type='checkbox']";
				checkBoxActionCell = surveyTable.findElement(By.xpath(checkBoxXPath));
				Log.info("Wait for survey checkbox to be clickable");
				WebElementExtender.waitForElementToBeClickable(timeout, driver, checkBoxActionCell);
				Log.info(String.format("Select survey - row %d", rowNum));
				checkBoxActionCell.click();
				selectedSurveysCount++;

				if (rowNum == Integer.parseInt(PAGINATIONSETTING)
						&& !this.surveyNextButton.getAttribute("class").contains("disabled")) {
					Log.clickElementInfo("Next");
					this.surveyNextButton.click();
					this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
					List<WebElement> newRows = surveyTable.findElements(By.xpath("tr"));

					rowSize = newRows.size();

					if (rowSize < Integer.parseInt(PAGINATIONSETTING))
						loopCount = rowSize;
					else
						loopCount = Integer.parseInt(PAGINATIONSETTING);

					rowNum = 0;
				}
			}

			// Add the selected surveys
			clickOnAddSurveysButton();
			waitForSelectedSurveysToBeAdded(numSurveysToSelect);
		}
	}

	private void verifySurveyTableIsNotEmpty(List<WebElement> rows) throws Exception {
		if (rows != null && rows.size() == 1) {
			// Verify survey datatable is NOT empty.
			if (rows.get(0).getAttribute("class").equals("dataTables_empty")) {
				TestContext.INSTANCE.getTestSetup().getScreenCapture().takeScreenshot(TestContext.INSTANCE.getDriver(),
						TestContext.INSTANCE.getTestClassName(), true /*takeBrowserScreenShot*/, LogStatus.ERROR);
				throw new Exception("Survey could NOT be selected. Verify specified survey exists in the environment.");
			}
		}
	}

	private void selectFirstSurveyCheckBox() {
		Log.info(String.format("Select the first survey in the table"));
		this.checkboxSurFirst.click();
	}

	public void clickOnFirstSurveyLink() {
		Log.clickElementInfo("First Survey Link");
		this.firstSurveyLink.click();
	}

	public void clickOnAddSurveysButton() {
		Log.clickElementInfo("Add Surveys");
		this.btnAddSurveys.click();
	}

	public void selectSurveyInfoGeoFilter(Boolean geoFilterOn) {
		if (geoFilterOn != null) {
			if (geoFilterOn) {
				if (!checkGeoFilter.isSelected()) {
					Log.info(String.format("Click to select Geofilter"));
					clickGeoFilterCheckBox();
				}
			} else {
				if (checkGeoFilter.isSelected()) {
					Log.info(String.format("Click to unselect Geofilter"));
					clickGeoFilterCheckBox();
				}
			}
		}
	}

	private void clickGeoFilterCheckBox() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", this.checkGeoFilter);
	}

	public void selectSurveyInfoEndDate(String endDate) {
		if ((endDate != null) && (!endDate.isEmpty())) {
			selectEndDateForSurvey(endDate);
		}
	}

	public void selectSurveyInfoStartDate(String startDate) {
		if ((startDate != null) && (!startDate.isEmpty())) {
			selectStartDateForSurvey(startDate);
		}
	}

	public void enterSurveyInfoUsername(String username) {
		if (username != null && (!username.isEmpty())) {
			Log.info(String.format("Set username - '%s'", username));
			this.userName.sendKeys(username);
		}
	}

	public void selectSurveyInfoSurveyorUnit(String surveyor) {
		if (surveyor != null && (!surveyor.isEmpty())) {
			List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if (surveyor.equalsIgnoreCase(option.getText().trim())) {
					Log.info(String.format("Select Surveyor Unit - '%s'", surveyor));
					option.click();
					break;
				}
			}
		}
	}

	public void checkErrorMessages() throws Exception {
		waitForAJAXCallsToComplete();
		if (listOfErrors.size() > 0) {
			String error = "";
			for (WebElement err : listOfErrors) {
				error += err.getText() + System.lineSeparator();
			}
			throw new Exception(error);
		}

	}

	public boolean verifyErrorMessages(String... errormessages) {
		try {
			(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return errormessages == null || errormessages.length == 0 || errormessages[0].isEmpty()
							|| listOfErrors.size() >= errormessages.length;
				}
			});
		} catch (Exception e) {
			return false;
		}
		for (WebElement e : listOfErrors) {
			Log.info(e.getText());
		}
		for (String err : errormessages) {
			boolean msgFound = false;
			for (WebElement element : listOfErrors) {
				msgFound = false;
				String msg = element.getText();
				if (msg.equals(err)) {
					msgFound = true;
					break;
				}
			}
			if (!msgFound) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Implementation to be provided by Derived classes.
	 */
	protected void handleExtraAddSurveyInfoParameters(Reports reports) throws Exception {
		throw new Exception("Not implemented");
	}

	/**
	 * Implementation to be provided by Derived classes.
	 */
	protected void handleExtraAddSurveyInfoParameters(SurveyModeFilter surveyModeFilter) throws Exception {
		throw new Exception("Not implemented");
	}

	public void selectStartDateForSurvey(String startDate) {
		try {
			DatetimePickerSetting dateSetting = new DatetimePickerSetting(driver, testSetup, strBaseURL,
					strBaseURL + getUrlString());
			PageFactory.initElements(driver, dateSetting);
			if (startDate.startsWith("0")) {
				startDate = startDate.replaceFirst("0*", "");
			}
			dateSetting.setDay("start", 7, startDate, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectEndDateForSurvey(String endDate) {
		try {
			DatetimePickerSetting dateSetting = new DatetimePickerSetting(driver, testSetup, strBaseURL,
					strBaseURL + getUrlString());
			PageFactory.initElements(driver, dateSetting);
			if (endDate.startsWith("0")) {
				endDate = endDate.replaceFirst("0*", "");
			}
			dateSetting.setDay("end", 0, endDate, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openNewReportPage() {
		waitUntilPresenceOfElementLocated(By.xpath(strBtnNewCompRpt));
		Log.clickElementInfo("New Compliance Report");
		jsClick(this.btnNewComplianceRpt);
		this.waitForNewPageLoad();
		String elementXPath = "//*[@id='datatableViews']/tbody/tr/td[2]/input";
		refreshPageUntilElementFound(elementXPath);
	}

	public void waitForNewPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean result = false;
				try {
					result = d.getPageSource().contains(getNewPageString());
				} catch (Exception e) {
					Log.error(e.toString());
				}
				return result;
			}
		});
	}

	protected boolean handleFileDownloads(int rowNum) throws Exception {
		throw new Exception("Not implemented");
	}

	public void inputReportTitle(String rptTitle) {
		Log.info(String.format("Input report title - '%s'", rptTitle));
		this.inputTitle.clear();
		this.inputTitle.sendKeys(rptTitle);
	}

	public void selectCustomer(String customer) {
		selectCustomer(customer, true);
	}

	public void selectCustomer(String customer, boolean confirm) {
		if (dropdownCustomer.isDisplayed()) {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if (customer.equalsIgnoreCase(option.getText().trim())) {
					Log.info(String.format("Select Customer - '%s'", customer));
					option.click();
					getChangeCustomerDialog().confirmInChangeCustomerDialog(confirm);
					return;
				}
			}
		}
	}

	public void clickOnOKButton() {
		Log.clickElementInfo("Ok");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnOK);
	}

	public void inputSurveyTag(String tag) {
		Log.info(String.format("Input survey tag - '%s'", tag));
		this.cbTag.clear();
		this.cbTag.sendKeys(tag);
	}

	public void waitForSurveyTabletoLoad() {
		Log.method("waitForSurveyTabletoLoad");
		(new WebDriverWait(driver, timeout + 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				Log.info(String.format("surveysTable.isDisplayed()=%b", surveysTable.isDisplayed()));
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

	public void waitForSelectedSurveysToBeAdded(Integer countOfSurveys) {
		if (countOfSurveys == Integer.MAX_VALUE) {
			// If all surveys are selected find the number of surveys shown in
			// UI.
			countOfSurveys = getRecordsInSurveyTable(driver);
		}

		(new WebDriverWait(driver, timeout + 15)).until(ExpectedConditions
				.presenceOfElementLocated(By.id(String.format("surveyContent-%d", countOfSurveys - 1))));
	}

	/**
	 * Method to verify the Driving Surveys Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws Exception
	 */
	public boolean verifySurveysTableViaTag(boolean changeMode, ReportModeFilter strReportMode, String tag)
			throws Exception {
		boolean result = false;

		if (strReportMode != null && changeMode) {
			selectReportMode(strReportMode);

			Log.info(String.format("Input survey tag - '%s'", tag));
			this.getCbTag().clear();
			this.getCbTag().sendKeys(tag);
			this.waitForSurveySearchButtonToLoad();
			Log.clickElementInfo("Survey Search");
			this.getBtnSurveySearch().click();
			this.waitForSurveyTabletoLoad();

			WebElement tabledata = driver.findElement(By.id("datatableSurveys"));
			List<WebElement> Rows = tabledata.findElements(By.xpath("//*[@id='datatableSurveys']/tbody/tr"));
			for (int getrowvalue = 1; getrowvalue <= Rows.size(); getrowvalue++) {
				List<WebElement> Columns = Rows.get(getrowvalue - 1)
						.findElements(By.xpath("//*[@id='datatableSurveys']/tbody/tr/td[6]"));
				for (int getcolumnvalue = 1; getcolumnvalue <= Columns.size(); getcolumnvalue++) {
					String cellValue = driver
							.findElement(By.xpath("//*[@id='datatableSurveys']/tbody/tr[" + getrowvalue + "]/td/a[starts-with(@href,'/Live/Survey')]"))
							.getText();
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

	public void addNewReport(String title, String customer, String timeZone, String exclusionRadius, String boundary,
			String imageMapHeight, String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong,
			String surUnit, List<String> tagList, String startDate, String endDate, boolean changeMode,
			String strReportMode) throws Exception {
		openNewReportPage();

		if (customer != null && customer != CUSTOMER_PICARRO) {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if ((customer).equalsIgnoreCase(option.getText().trim())) {
					Log.info(String.format("Select Customer - '%s'", customer));
					option.click();
					break;
				}
			}
		}
		Log.info(String.format("Input report title - '%s'", title));
		this.inputTitle.clear();
		this.inputTitle.sendKeys(title);

		selectTimeZone(timeZone);

		addOtherDetails(customer, exclusionRadius, boundary, imageMapHeight, imageMapWidth, NELat, NELong, SWLat,
				SWLong, surUnit, tagList, startDate, endDate, changeMode, strReportMode);

		if (surUnit != "") {
			List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
					Log.info(String.format("Select Surveyor Unit - '%s'", surUnit));
					option.click();
					break;
				}
			}
		}

		for (String tagValue : tagList) {
			if (tagValue != "") {
				inputSurveyTag(tagValue);
				clickOnSearchSurveyButton();
				this.waitForSurveyTabletoLoad();
				this.waitForSurveySelectorCheckBoxToLoad();
				this.waitForSurveySelectorCheckBoxToBeEnabled();
				selectSurveyCheckBox(checkboxSurFirst);
				this.waitForAddSurveyButtonToLoad();
				clickOnAddSurveysButton();

			}
		}

		addViewDetails(customer, boundary);
		this.clickOnOKButton();
	}

	public boolean verifyIfInDrivingSurvey(String columnName) {
		boolean result = false;
		int count = driver.findElements(By.xpath("//*[@id='surveyContent-0']/div/fieldset/div/fieldset/div[2]/div"))
				.size();
		for (int i = 1; i < count + 1; i++) {
			String str = driver
					.findElement(By
							.xpath("//*[@id='surveyContent-0']/div/fieldset/div/fieldset/div[2]/div[" + i + "]/label"))
					.getText();
			if (str != columnName) {
				result = true;
			}
		}
		return result;

	}

	public void waitForSurveySearchButtonToLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnSurveySearch.isDisplayed();
			}
		});
	}

	public String getUrlString() throws Exception {
		throw new Exception("Not implemented");
	}

	public String getStrPageText() throws Exception {
		throw new Exception("Not implemented");
	}

	public String getStrCopyPageText() throws Exception {
		throw new Exception("Not implemented");
	}

	public String getNewPageString() throws Exception {
		throw new Exception("Not implemented");
	}

	public void fillReportSpecific(Reports reportsCompliance) throws Exception {
		throw new Exception("Not implemented");
	}

	public void selectReportMode(ReportModeFilter mode) throws Exception {
		throw new Exception("Not implemented");
	}

	public void selectTimeZone(String timeZone) {
		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if (timeZone.equalsIgnoreCase(option.getText().trim())) {
				Log.info(String.format("Select Timezone - '%s'", timeZone));
				option.click();
				break;
			}
		}
	}

	public void selectSurveySurveyor(String surveyorUnit) {
		List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
		for (WebElement option : optionsSU) {
			if (surveyorUnit.equalsIgnoreCase(option.getText().trim())) {
				Log.info(String.format("Select surveyor unit - '%s'", surveyorUnit));
				option.click();
				break;
			}
		}
	}

	public void selectSurveyCheckBox(WebElement checkboxSurFirst) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkboxSurFirst);
	}

	public void addViewDetails(String customer, String boundary) throws Exception {
		throw new Exception("Not implemented");
	}

	public void addOtherDetails(String customer, String exclusionRadius, String boundary, String imageMapHeight,
			String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong, String surUnit,
			List<String> tagList, String startDate, String endDate, boolean changeMode, String strReportMode)
			throws Exception {
		throw new Exception("Not implemented");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// add class testing code here
	}

	public void addNewReport(String title, String customer, String timeZone, String exclusionRadius, String boundary,
			String imageMapHeight, String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong,
			String surUnit, String tag, String startDate, String endDate, String surModeFilter) throws Exception {
		openNewReportPage();
		this.inputTitle.clear();
		this.inputTitle.sendKeys(title);

		if (customer != null && customer != CUSTOMER_PICARRO) {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if ((customer).equalsIgnoreCase(option.getText().trim())) {
					Log.info(String.format("Select Customer - '%s'", customer));
					option.click();
					break;
				}
			}

			if (getChangeCustomerDialog().confirmInChangeCustomerDialog()) {
				this.inputTitle.clear();
				this.inputTitle.sendKeys(title);
				Log.info(String.format("Input title - '%s'",title));
			}
		}

		selectTimeZone(timeZone);
		reportSpecificAddNewReport(customer, exclusionRadius, boundary, imageMapHeight, imageMapWidth, NELat, NELong,
				SWLat, SWLong);

		if (surUnit != "") {
			List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
					Log.info(String.format("Select Surveyor Unit - '%s'", surUnit));
					option.click();
					break;
				}
			}
		}

		if (tag != "") {
			inputSurveyTag(tag);
		}

		clickOnSearchSurveyButton();
		this.waitForSurveyTabletoLoad();
		this.waitForSurveySelectorCheckBoxToLoad();
		this.waitForSurveySelectorCheckBoxToBeEnabled();
		selectFirstSurveyCheckBox();
		clickOnAddSurveysButton();
		this.clickOnOKButton();
	}

	public boolean checkActionStatus(String rptTitle, String strCreatedBy, String testCaseID) throws Exception {
		Log.method("ReportsBasePage.checkActionStatus", rptTitle, strCreatedBy, testCaseID);
		setPagination(PAGINATIONSETTING_100);
		this.waitForPageLoad();
		String reportTitleXPath;
		String reportNameXPath;
		String createdByXPath;
		List<WebElement> rows = getTable().findElements(By.xpath("tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		// Keep track of the last matching row that we processed.
		String lastSeenTitleCellText = "";
		String lastSeenReportNameCellText = "";
		String lastSeenCreatedByCellText = "";

		int maxRows = Integer.parseInt(PAGINATIONSETTING_100);

		reportId = getReportId(rptTitle);
		String strReportName = getReportFileName(rptTitle);

		final int MAX_PAGES_TO_MOVE_AHEAD = 3;
		int pageCounter = 0;

		Log.info(String.format("Looking for Report Title='%s', Report Name='%s', Created By='%s'",
				rptTitle.trim(), strReportName.trim(), strCreatedBy.trim()));
		for (int rowNum = 1; rowNum <= loopCount && pageCounter < MAX_PAGES_TO_MOVE_AHEAD; rowNum++) {
			reportTitleXPath = "tr[" + rowNum + "]/td[1]";
			reportNameXPath = "tr[" + rowNum + "]/td[2]";
			createdByXPath = "tr[" + rowNum + "]/td[3]";

			String rptTitleCellText = getReportTableCellText(reportTitleXPath);
			String rptNameCellText = getReportTableCellText(reportNameXPath);
			String createdByCellText = getReportTableCellText(createdByXPath);
			Log.info(String.format("Found cell : rptTitleCellText=[%s], rptNameCellText=[%s], createdByCellText=[%s]",
					rptTitleCellText.trim(), rptNameCellText.trim(), createdByCellText.trim()));

			if (rptTitleCellText.trim().equalsIgnoreCase(rptTitle.trim())
					&& rptNameCellText.trim().equalsIgnoreCase(strReportName.trim())
					&& createdByCellText.trim().equalsIgnoreCase(strCreatedBy.trim())) {

				Log.info(String.format("Found matching row for rptTitleCellText=[%s], createdByCellText=[%s]",
						rptTitleCellText.trim(), createdByCellText.trim()));

				lastSeenTitleCellText = rptTitleCellText.trim();
				lastSeenReportNameCellText = rptNameCellText.trim();
				lastSeenCreatedByCellText = createdByCellText.trim();

				Log.info(String.format("Setting reportId to TestContext. ReportId='%s'", reportId));
				reportId = getReportId(rptTitle);
				TestContext.INSTANCE.addReportId(reportId);

				long startTime = System.currentTimeMillis();
				long elapsedTime = 0;
				boolean bContinue = true;
				final int MAX_RETRIES_FOR_NULL_ERROR = 5;
				int numRetriesForNullError = 0;
				WebElement reportViewer;
				while (bContinue) {
					try {
						if (rowSize == 1) {
							Log.info("RowSize == 1. Getting ReportViewer button element...");
							reportViewer = getTable().findElement(By.xpath("tr/td[5]/a[3]"));
							Log.clickElementInfo("Report Viewer");
							reportViewer.click();
							this.waitForPdfReportIcontoAppear();
						} else {
							Log.info("First call -> skipNewlyAddedRows()");
							rowNum = skipNewlyAddedRows(lastSeenTitleCellText, lastSeenReportNameCellText,
									lastSeenCreatedByCellText, rowNum, maxRows);
							if (rowNum > maxRows) {
								Log.info("Block 1: rowNum > maxRows.. Break...");
								break;
							}
							reportViewer = getTable().findElement(By.xpath("tr[" + rowNum + "]/td[5]/a[3]"));

							// At this point it is possible that more reports got newly added, in which case our rowNum is incorrect.
							// Double check if we have the rowNum of interest.
							// If current rowNum doesn't match the new rowNum continue.
							Log.info("Second call -> skipNewlyAddedRows()");
							if(rowNum != skipNewlyAddedRows(lastSeenTitleCellText, lastSeenReportNameCellText,
									lastSeenCreatedByCellText, rowNum, maxRows)) {
								Log.info("Block 2: rowNum != rowNumPostSkip.. Continue...");
								continue;
							}

							// rowNum matches. Try to click on ReportViewer button.
							Log.clickElementInfo("Report Viewer");
							jsClick(reportViewer);
							this.waitForPdfReportIcontoAppear();
						}

						return handleFileDownloads(rptTitle, testCaseID);
					} catch (org.openqa.selenium.NoSuchElementException e) {
						elapsedTime = System.currentTimeMillis() - startTime;
						if (elapsedTime >= (getReportGenerationTimeout() * 1000)) {
							Log.info(String.format("wait action timed out in checkActionsStatus() method call. Elapsed time = %d",
									elapsedTime));
							return false;
						}
						continue;
					} catch (NullPointerException ne) {
						numRetriesForNullError++;
						if (numRetriesForNullError < MAX_RETRIES_FOR_NULL_ERROR) {
							Log.info(String.format("RETRY attempt-[%d]. Null Pointer Exception Encountered : %s",
									numRetriesForNullError, ExceptionUtility.getStackTraceString(ne)));
							if (elapsedTime >= (getReportGenerationTimeout() * 1000)) {
								return false;
							}
							continue;
						}

						Log.error(String.format("MAX Retry attempts exceeded. Null Pointer Exception Encountered again: %s",
								ExceptionUtility.getStackTraceString(ne)));
						fail("Report failed to be generated!!");
					}
				}
			}

			if (rowNum >= Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				pageCounter++;
				List<WebElement> newRows = getTable().findElements(By.xpath("tr"));
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

	private WebElement getTableCell(String elementXPath) {
		return getTable().findElement(By.xpath(elementXPath));
	}

	private WebElement getReportTableCell(String elementXPath) {
		boolean retry = false;
		WebElement tableCell = null;
		try {
			tableCell = getTableCell(elementXPath);
		} catch (Exception e) {
			retry = true;
		}

		if (retry) {
			this.waitForPageLoad();
			refreshPageUntilElementFound(elementXPath);
		}

		return getTableCell(elementXPath);
	}

	private String cellText = null;

	private String getReportTableCellText(String elementXPath) {
		PollManager.poll(() -> {
			try {
				cellText = getReportTableCell(elementXPath).getText();
				return BaseHelper.isNullOrEmpty(cellText);
			} catch (Exception e) {
				Log.warn(String.format("Polling -> getReportTableCellText(). Exception warning-> {0}", e.getMessage()));
			}
			return true;
		}, Constants.MILLISECONDS_IN_ONE_SECOND, Constants.DEFAULT_MAX_RETRIES_IN_POLL);
		return cellText;
	}

	public void reportSpecificAddNewReport(String customer, String exclusionRadius, String boundary,
			String imageMapHeight, String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong)
			throws Exception {
		throw new Exception("Not implemented");
	}

	public boolean waitForReportGenerationtoComplete(String rptTitle, String strCreatedBy) {
		String reportName = waitForReportGenerationtoCompleteAndGetReportName(rptTitle, strCreatedBy);
		if (reportName == null) {
			return false;
		}
		return true;
	}

	public String waitForReportGenerationtoCompleteAndGetReportName(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING_100);
		this.waitForPageLoad();
		String reportTitleXPath;
		String createdByXPath;

		List<WebElement> rows = getTable().findElements(By.xpath("tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		// Keep track of the last matching row that we processed.
		String lastSeenTitleCellText = "";
		String lastSeenCreatedByCellText = "";

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		int maxRows = Integer.parseInt(PAGINATIONSETTING_100);

		Log.info(String.format("Looking for report: Title=[%s], CreatedBy=[%s]", rptTitle, strCreatedBy));

		final int MAX_PAGES_TO_MOVE_AHEAD = 3;
		int pageCounter = 0;

		for (int rowNum = 1; rowNum <= loopCount && pageCounter < MAX_PAGES_TO_MOVE_AHEAD; rowNum++) {
			reportTitleXPath = "tr[" + rowNum + "]/td[1]";
			createdByXPath = "tr[" + rowNum + "]/td[3]";

			String rptTitleCellText = getReportTableCellText(reportTitleXPath);
			String createdByCellText = getReportTableCellText(createdByXPath);
			Log.info(String.format("Found cell : rptTitleCellText=[%s], createdByCellText=[%s]",
					rptTitleCellText.trim(), createdByCellText.trim()));

			if (rptTitleCellText.trim().equalsIgnoreCase(rptTitle)
					&& createdByCellText.trim().equalsIgnoreCase(strCreatedBy)) {

				Log.info(String.format("Found matching row for rptTitleCellText=[%s], createdByCellText=[%s]",
						rptTitleCellText.trim(), createdByCellText.trim()));

				lastSeenTitleCellText = rptTitleCellText.trim();
				lastSeenCreatedByCellText = createdByCellText.trim();

				// Use API call for environments where direct DB access is not available (eg P3Scale).
				/* DE2331 created: This method is not stable and throwing exceptions - need to be fixed
				 * The try catch block could be removed after the fix
				 */
				try{
					reportId = Report.getReport(rptTitle).getId();
				}catch(Exception e){
					ReportJobsStat reportJobsStatObj = getReportJobStat(rptTitle);
					reportId = reportJobsStatObj.Id;
				}
				TestContext.INSTANCE.addReportId(reportId);

				long startTime = System.currentTimeMillis();
				long elapsedTime = 0;
				boolean bContinue = true;
				WebElement reportViewer;
				while (bContinue) {
					try {
						if (rowSize == 1) {
							Log.info("RowSize == 1. Getting ReportViewer button element...");
							reportViewer = getTable().findElement(By.xpath("tr/td[5]/a[3]"));
						} else {
							Log.info("First call -> skipNewlyAddedRows()");
							rowNum = skipNewlyAddedRows(lastSeenTitleCellText, lastSeenCreatedByCellText, rowNum,
									maxRows);
							if (rowNum > maxRows) {
								break;
							}
							reportViewer = getTable().findElement(
									By.xpath("tr[" + rowNum + "]/td[5]/a[3]"));
							//* Double check the correctness of the rowNum
							Log.info("Second call -> skipNewlyAddedRows()");
							if(rowNum != skipNewlyAddedRows(lastSeenTitleCellText, lastSeenCreatedByCellText, rowNum,
									maxRows)){
								continue;
							}
						}
						return reportId;
					} catch (org.openqa.selenium.NoSuchElementException e) {
						elapsedTime = System.currentTimeMillis() - startTime;
						if (elapsedTime >= (getReportGenerationTimeout() * 1000)) {
							return null;
						}
						continue;
					} catch (NullPointerException ne) {
						Log.info("Null Pointer Exception: " + ne);
						fail("Report failed to generate!!");
					}
				}
			}

			if (rowNum >= Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				pageCounter++;
				this.waitForPageLoad();

				List<WebElement> newRows = getTable().findElements(By.xpath("tr"));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return null;
	}

	public boolean copyReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);
		String reportTitleXPath;
		String createdByXPath;
		String copyImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement copyImg;

		List<WebElement> rows = getTable().findElements(By.xpath("tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		this.waitForPageLoad();
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "tr[" + rowNum + "]/td[1]";
			createdByXPath = "tr[" + rowNum + "]/td[3]";

			String rptTitleCellText = getReportTableCellText(reportTitleXPath);
			String createdByCellText = getReportTableCellText(createdByXPath);
			if (rptTitleCellText.trim().equalsIgnoreCase(rptTitle)
					&& createdByCellText.trim().equalsIgnoreCase(strCreatedBy)) {
				copyImgXPath = "tr[" + rowNum + "]/td[5]/a[@title='Copy']"; // Don't
																			// use
																			// index
																			// for
																			// 'Copy'
																			// as
																			// it
																			// has
																			// diff
																			// values
				copyImg = getReportTableCell(copyImgXPath);
				Log.clickElementInfo("Copy", ElementType.ICON);
				jsClick(copyImg);
				DBCache.INSTANCE.remove(Report.CACHE_KEY + rptTitle);
				this.waitForCopyReportPagetoLoad();
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				Log.clickElementInfo("Next");
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath("tr"));
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
		Log.info(String.format("Find report with title = '%s', created by = '%s", rptTitle, strCreatedBy));
		setPagination(PAGINATIONSETTING_100);

		String reportTitleXPath;
		String createdByXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;

		List<WebElement> rows = getTable().findElements(By.xpath("tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "tr[" + rowNum + "]/td[1]";
			createdByXPath = "tr[" + rowNum + "]/td[3]";

			String rptTitleCellText = getReportTableCellText(reportTitleXPath);
			String createdByCellText = getReportTableCellText(createdByXPath);
			if (rptTitleCellText.trim().equalsIgnoreCase(rptTitle)
					&& createdByCellText.trim().equalsIgnoreCase(strCreatedBy)) {
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath("tr"));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}
		Log.error(String.format("Report not found: title = '%s', created by = '%s", rptTitle, strCreatedBy));
		return false;
	}

	public boolean findReportbySearch(String rptTitle, String strCreatedBy) {
		Log.info(String.format("Find report with title = '%s', created by = '%s", rptTitle, strCreatedBy));
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
		List<WebElement> rows = getTable().findElements(By.xpath("tr"));
		int rowSize = rows.size();

		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			this.waitForPageLoad();
			reportTitleXPath = "tr[" + rowNum + "]/td[1]";
			createdByXPath = "tr[" + rowNum + "]/td[3]";
			String rptTitleCellText = getReportTableCellText(reportTitleXPath);
			String createdByCellText = getReportTableCellText(createdByXPath);
			if (rptTitleCellText.trim().equalsIgnoreCase(rptTitle)
					&& createdByCellText.trim().equalsIgnoreCase(strCreatedBy)) {
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				this.waitForPageLoad();
				List<WebElement> newRows = getTable().findElements(By.xpath("tr"));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}
		Log.error(String.format("Report not found: title = '%s', created by = '%s", rptTitle, strCreatedBy));
		return false;
	}

	public boolean searchAndDeleteReport(String reportTitle, String reportCreatedBy) throws Exception {
		Log.method("searchAndDeleteReport", reportTitle, reportCreatedBy);
		boolean searchSuccess = searchReport(reportTitle, reportCreatedBy);
		boolean deleteSuccess = !searchSuccess;
		String exceptionMsg = null;
		try {
			if (searchSuccess) {
				Log.info("Found report to be deleted in Search.");
				deleteSuccess = deleteReport(reportTitle, reportCreatedBy);
			}
		} catch (Exception ex) {
			exceptionMsg = ExceptionUtility.getStackTraceString(ex);
		} finally {
			this.clearSearchFieldUsingSpace();
		}

		if (!BaseHelper.isNullOrEmpty(exceptionMsg)) {
			Log.info(String.format("Exception when deleting report. Exception message -> %s", exceptionMsg));
			throw new Exception("Delete report failed!");
		}

		return deleteSuccess;
	}

	public boolean deleteReport(String rptTitle, String strCreatedBy) throws Exception {
		Log.method("deleteReport", rptTitle, strCreatedBy);

		setPagination(PAGINATIONSETTING);

		this.waitForPageLoad();

		String reportTitleXPath;
		String createdByXPath;
		String deleteImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement deleteImg;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			String rptTitleCellText = getReportTableCellText(reportTitleXPath);
			String createdByCellText = getReportTableCellText(createdByXPath);
			if (rptTitleCellText.trim().equalsIgnoreCase(rptTitle)
					&& createdByCellText.trim().equalsIgnoreCase(strCreatedBy)) {
				deleteImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[1]";
				deleteImg = getReportTableCell(deleteImgXPath);
				Log.clickElementInfo("Delete", ElementType.ICON);
				deleteImg.click();
				waitForDeletePopupLoad();

				if (this.isElementPresent(getBtnDeleteConfirmXpath())) {
					Log.info("Found confirm Delete popup");
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", getBtnDeleteConfirm());
					this.waitForPageLoad();

					if (this.isElementPresent(errorMsgDeleteCompliacneReportXPath)) {
						Log.clickElementInfo("Error message shown on Delete. Return to home page.");
						this.btnReturnToHomePage.click();
						return false;
					} else {
						Log.info("Delete report was successful!");
						return true;
					}
				} else {
					return false;
				}
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				this.waitForPageLoad();

				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
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

	public boolean deleteReportById(String reportId) throws Exception {
		String reportName = "CR-" + reportId.substring(0, 6).toUpperCase();
		this.waitForPageLoad();
		this.performSearch(reportName);
		if (!this.waitForTableDataToLoad()) {
			return false;
		}
		String xpathDelete = "tr/td/a[@title='Delete' and contains(@data-delete,"+"'reportId="+reportId.toLowerCase()+"')]/img";
		WebElement deleteImg = getTable().findElement(By.xpath(xpathDelete));
		Log.clickElementInfo("Delete", ElementType.ICON);
		deleteImg.click();
		if (waitForDeletePopupLoad()) {
			jsClick(getBtnDeleteConfirm());
			this.waitForPageLoad();
			if (this.isElementPresent(errorMsgDeleteCompliacneReportXPath)) {
				Log.error(getElementText(errorMsgDeleteCompliacneReport));
				Log.clickElementInfo("Return to home page");
				this.btnReturnToHomePage.click();
				return false;
			}
		} else {
			return false;
		}
		return true;
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

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			String rptTitleCellText = getReportTableCellText(reportTitleXPath);
			String createdByCellText = getReportTableCellText(createdByXPath);
			if (rptTitleCellText.trim().equalsIgnoreCase(rptTitle)
					&& createdByCellText.trim().equalsIgnoreCase(strCreatedBy)) {
				copyImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[2]";
				copyImg = getReportTableCell(copyImgXPath);
				Log.clickElementInfo("Copy", ElementType.ICON);
				copyImg.click();

				this.waitForCopyReportPagetoLoad();
				this.waitForInputTitleToEnable();
				this.waitForDeleteSurveyButtonToLoad();
				Log.info(String.format("Input new report title - '%s'", rptTitleNew));
				this.inputTitle.clear();
				this.inputTitle.sendKeys(rptTitleNew);
				this.waitForOkButtonToEnable();
				clickOnOKButton();

				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				this.waitForPageLoad();

				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
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

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			String rptTitleCellText = getReportTableCellText(reportTitleXPath);
			String createdByCellText = getReportTableCellText(createdByXPath);
			if (rptTitleCellText.trim().equalsIgnoreCase(rptTitle)
					&& createdByCellText.trim().equalsIgnoreCase(strCreatedBy)) {
				copyImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[2]";
				copyImg = getReportTableCell(copyImgXPath);
				Log.clickElementInfo("Copy", ElementType.ICON);
				copyImg.click();
				break;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				Log.clickElementInfo("Next");
				toNextPage();
				this.waitForPageLoad();

				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}
	}

	public boolean modifyReportDetails(String rptTitleNew, String surUnit, List<String> tagList, boolean changeMode,
			ReportModeFilter strReportMode) throws Exception {
		this.waitForCopyReportPagetoLoad();

		this.inputTitle.clear();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + rptTitleNew + "';", inputTitle);

		complianceChangeMode(rptTitleNew, changeMode, strReportMode);
		if (surUnit != "") {
			List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
					Log.info(String.format("Select Surveyor Unit - '%s'", surUnit));
					option.click();
				}
			}
		}

		for (String tagValue : tagList) {
			if (tagValue != "") {

				inputSurveyTag(tagValue);
				this.waitForSurveySearchButtonToLoad();
				clickOnSearchSurveyButton();
				this.waitForSurveyTabletoLoad();
				selectFirstSurveyCheckBox();
				this.waitForAddSurveyButtonToLoad();
				clickOnAddSurveysButton();
			}
		}

		if (tagList.isEmpty()) {
			clickOnSearchSurveyButton();
			this.waitForSurveyTabletoLoad();
			selectFirstSurveyCheckBox();
			this.waitForAddSurveyButtonToLoad();
			clickOnAddSurveysButton();
		}

		modifyComplianceViews();

		this.clickOnOKButton();
		return true;
	}

	public boolean searchReport(String reportTitle, String reportCreatedBy) {
		this.inputSearchReport.sendKeys(reportTitle);
		waitForTableDataToLoad();
		if (this.tdCReportTitle.getText().contentEquals(reportTitle)) {
			if (this.tdCReportCreatedBy.getText().contentEquals(reportCreatedBy))
				return true;
		}
		return false;
	}

	public String getEmptyTableMessage() {
		String msg = dataTableEmpty.getText();
		return msg.trim();
	}

	public boolean verifySurveyNotAdded(String reportTitle, String customer, String NELat, String NELong, String SWLat,
			String SWLong, List<Map<String, String>> views) throws Exception {
		openNewReportPage();
		inputReportTitle(reportTitle);
		addReportSpecificSurveys(customer, NELat, NELong, SWLat, SWLong, views);
		this.clickOnOKButton();
		if (this.msgEmptySurvey.getText().equalsIgnoreCase(getSurveyMissingMessage())) {
			return true;
		}

		return false;
	}

	public boolean verifySurveyAlreadyAdded(String customer, String surveyTag) throws Exception {
		this.waitForCopyReportPagetoLoad();
		this.waitForDeleteSurveyButtonToLoad();
		if (customer != null && customer != CUSTOMER_PICARRO) {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if ((customer).equalsIgnoreCase(option.getText().trim())) {
					Log.info(String.format("Select Customer - '%s'", customer));
					option.click();
				}
			}

			if (this.isElementPresent(btnChangeModeXPath)) {
				Log.clickElementInfo("Change Mode");
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", btnChangeMode);
			}
		}

		if (surveyTag != "") {
			inputSurveyTag(surveyTag);
			clickOnSearchSurveyButton();
			this.waitForSurveyTabletoLoad();
			selectFirstSurveyCheckBox();
			clickOnAddSurveysButton();
		}

		if (isElementPresent(strFirstSurveyTag)) {
			if (surveyTag != "") {
				inputSurveyTag(surveyTag);
				clickOnSearchSurveyButton();
				this.waitForSurveyTabletoLoad();
				this.waitForSurveySelectorCheckBoxToLoad();
				this.waitForSurveySelectorCheckBoxToBeEnabled();
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", checkboxSurFirst);
				clickOnAddSurveysButton();

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

	public boolean verifyActualImageWithBase(String pathToActualImage, String pathToBaseImage) throws IOException{
		return verifyActualImageWithBase(pathToActualImage, pathToBaseImage, false);
	}

	public boolean verifyActualImageWithBase(String pathToActualImage, String pathToBaseImage, boolean generateBaseline) throws IOException {
		if(generateBaseline){
			FileUtility.copyFile(pathToActualImage, pathToBaseImage);
			return true;
		}
		ImageComparisonResult result = ImagingUtility.compareImages(pathToActualImage, pathToBaseImage);
		if ((result.getFailureMessage() != null) && (result.isEqual() == true)) {
			return false;
		}
		return true;
	}

	public String getSTRSurveyIncludedMsg() throws Exception {
		throw new Exception("Not implemented");
	}

	public void openNewComplianceReportPage() {
		openNewReportPage();
	}

	public boolean verifyCancelButtonFunctionality() {
		openNewReportPage();
		Log.clickElementInfo("Cancel");
		this.btnCancel.click();
		this.waitForPageLoad();

		if (isElementPresent(strBtnNewCompRpt))
			return true;

		return false;
	}

	public void clickOnNewComplianceReportBtn() {
		Log.clickElementInfo("New Compliance Report");
		this.btnNewComplianceRpt.click();
		this.waitForNewPageLoad();
	}

	public void clickOnCancelBtn() {
		Log.clickElementInfo("Cancel");
		jsClick(this.btnCancel);
		super.waitForPageLoad();
	}

	public void clickOnFirstCopyComplianceBtn() {
		Log.clickElementInfo("Copy", ElementType.ICON);
		this.btnFirstCopyCompliance.click();
		this.waitForCopyReportPagetoLoad();
	}

	@Override
	public void waitForPageLoad() {
		waitForAJAXCallsToComplete();
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean result = false;
				try {
					result = d.getPageSource().contains(getStrPageText());
				} catch (Exception e) {
					Log.error(e.toString());
				}
				return result;
			}
		});
	}

	public void waitForCopyReportPagetoLoad() {
		super.waitForPageToLoad();
		waitForAJAXCallsToComplete();
		(new WebDriverWait(driver, timeout + 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean result = false;
				try {
					result = d.getPageSource().contains(getStrCopyPageText());
				} catch (Exception e) {
					Log.error(e.toString());
				}
				return result;

			}
		});
	}

	public boolean waitForDeletePopupLoad() {
		Log.method("waitForDeletePopupLoad");
		return (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean isDisplayed = false;
				try {
					isDisplayed = getBtnDeleteConfirm().isDisplayed();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Log.info(String.format("Confirm delete button -> isDisplayed=[%b]", isDisplayed));
				return isDisplayed;
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

	public void clickOnSurveyTableColumnHeader(Integer columnIndex, Integer numTimesToClick) {
		WebElement headerElement = driver
				.findElement(By.xpath(String.format(surveyTableHeaderColumnBaseXPath, columnIndex)));
		for (int i = 0; i < numTimesToClick; i++) {
			headerElement.click();
		}
	}

	public Integer getRecordsInSurveyTable(WebDriver driver) {
		WebElement pageInfoLabel = driver.findElement(By.id("datatableSurveys_info"));
		return getRecordsShownOnPage(driver, pageInfoLabel);
	}

	public void waitForSurveyTableDataToLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (getRecordsInSurveyTable(d) > 0);
			}
		});
	}

	private String getReportId(String rptTitle) {
		// Use API call for environments where direct DB access is not available (eg P3Scale).
		/* Currently we are using DB call first and then API call since API calls is returned the oldest report.
		 * We want to always get the newest report first. US3605 tracks this issue.
		 */
		String reportId = "";
		try{
			reportId = Report.getReport(rptTitle).getId();
		}catch(Exception e){
			ReportJobsStat reportJobsStatObj = getReportJobStat(rptTitle);
			reportId = reportJobsStatObj.Id;
		}
		return reportId;
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

	public boolean handleFileDownloads(String rptTitle, String testCaseID) throws Exception {
		throw new Exception("Not implemented");
	}

	public void waitForPdfReportIcontoAppear() {
		(new WebDriverWait(driver, timeout + 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return pdfImg.isDisplayed();

			}
		});
	}

	public void waitForReportViewerDialogToOpen() {
		WebElement divModalcontent = this.driver.findElement(By.id("reportViewer"));
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return divModalcontent.getAttribute("style").contains("display:block")
						|| divModalcontent.getAttribute("style").contains("display: block");
			}
		});
	}

	public void waitForReportViewerDialogToClose() {
		WebElement divModalcontent = this.driver.findElement(By.id("reportViewer"));
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean contains1 = divModalcontent.getAttribute("style").contains("display:none");
				boolean contains2 = divModalcontent.getAttribute("style").contains("display: none");
				return contains1 || contains2;
			}
		});
	}

	public WebElement getBtnDeleteConfirm() throws Exception {
		throw new Exception("Not implemented");
	}

	public String getBtnDeleteConfirmXpath() throws Exception {
		throw new Exception("Not implemented");
	}

	public void modifyComplianceViews() throws Exception {
		throw new Exception("Not implemented");
	}

	public void complianceChangeMode(String rptTitleNew, boolean changeMode, ReportModeFilter strReportMode)
			throws Exception {
		throw new Exception("Not implemented");
	}

	public void addReportSpecificSurveys(String customer, String NELat, String NELong, String SWLat, String SWLong,
			List<Map<String, String>> views) throws Exception {
		throw new Exception("Not implemented");
	}

	public String getSurveyMissingMessage() throws Exception {
		throw new Exception("Not implemented");
	}

	/************** Baseline creation and comparison methods ***************/

	public String getUpdatedReportJobCSVFileContent(String fileAbsolutePath, String newLine) throws IOException {
		StringBuilder fileContent = new StringBuilder();
		if (!new File(fileAbsolutePath).exists()) {
			// File does not exists. Add header and newline.
			String headerLine = "ReportJobTypeId,StartTime,EndTime,ProcessingTimeInMs";
			fileContent.append(headerLine);
			fileContent.append(newLine);
		} else {
			// File already exists. Keep existing lines and replace oldline with
			// newline.
			List<String> splitNewLine = RegexUtility.split(newLine, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
			String newReportJobTypeId = splitNewLine.get(0);

			boolean reportJobTypeLineExists = false;
			List<String> existingLines = FileUtility.readFileLinesToList(fileAbsolutePath);
			for (String line : existingLines) {
				List<String> split = RegexUtility.split(line, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
				String reportJobTypeId = split.get(0);
				if (reportJobTypeId.equals(newReportJobTypeId)) {
					reportJobTypeLineExists = true;
					fileContent.append(newLine);
				} else {
					fileContent.append(line);
				}
				fileContent.append(System.lineSeparator());
			}
			if (!reportJobTypeLineExists) {
				fileContent.append(newLine);
			}
		}
		return fileContent.toString();
	}

	protected void generateBaselinePerfReportJobFiles(String testCaseID, String reportJobTypeId, String startTime,
			String endTime, Integer processingTimeInMs) throws IOException {
		Log.info(String.format(
				"Generating report job perf baseline for : [TestCase=%s, ReportJobTypeId=%s, StartTime=%s, EndTime=%s, ProcessingTimeInMs=%d]",
				testCaseID, reportJobTypeId, startTime, endTime, processingTimeInMs));

		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = rootFolder + File.separator + "perf-metric" + File.separator
				+ "report-job-metrics" + File.separator + testCaseID;
		// Create the directory for test case if it does not exist.
		FileUtility.createDirectoryIfNotExists(expectedDataFolderPath);
		Path expectedFilePath = Paths.get(expectedDataFolderPath, String.format("%s.csv", testCaseID));
		ReportJobType reportJobType = Reports.ReportJobTypeGuids.get(reportJobTypeId);
		BaseReportsPageTest.setRollingReportJobProcessingTime(reportJobType, processingTimeInMs);
		Integer reportJobRollingProcessingTimeAvg = BaseReportsPageTest
				.getReportJobRollingProcessingTimeAvg(reportJobType);
		Log.info(String.format("    CURRENT: {reportJobTypeId, startTime, endTime, processingTimeInMs} - {%s,%s,%s,%d}",
				reportJobTypeId, startTime, endTime, processingTimeInMs));
		Log.info(String.format("ROLLING AVG: {reportJobTypeId, startTime, endTime, processingTimeInMs} - {%s,%s,%s,%d}",
				reportJobTypeId, startTime, endTime, reportJobRollingProcessingTimeAvg));
		String reportMetricString = String.format("%s,%s,%s,%d", reportJobTypeId, startTime, endTime,
				reportJobRollingProcessingTimeAvg);

		// Recreate the text in the baseline file replacing only the content for
		// the current report job type.
		String fileContent = getUpdatedReportJobCSVFileContent(expectedFilePath.toString(), reportMetricString);
		FileUtility.createTextFile(expectedFilePath, fileContent);
	}

	protected void generateBaselineViewImage(String testCaseID, String imageFileFullPath, int counter)
			throws IOException {
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = rootFolder + File.separator + "test-expected-data" + File.separator
				+ "views-images" + File.separator + testCaseID;
		FileUtility.createDirectoryIfNotExists(expectedDataFolderPath);
		String expectedFilename = expectedDataFolderPath + File.separator + "View" + counter + ".png";
		FileUtils.copyFile(new File(imageFileFullPath), new File(expectedFilename));
	}

	protected void generateBaselineViewImage(String testCaseID, String imageFileFullPath, String viewName)
			throws IOException {
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = rootFolder + File.separator + "test-expected-data" + File.separator
				+ "views-images" + File.separator + testCaseID;
		FileUtility.createDirectoryIfNotExists(expectedDataFolderPath);
		String expectedFilename = expectedDataFolderPath + File.separator + viewName + ".png";
		FileUtils.copyFile(new File(imageFileFullPath), new File(expectedFilename));
	}

	/**
	 * Compares the processing times for each reportJob type with baseline
	 * processingTime values and checks actual values are not greater than the
	 * baseline values.
	 *
	 * @param testCaseID
	 *            - Test case ID
	 * @param reportTitle
	 *            - Title of the report
	 * @return - Whether actual report job type processing time values are
	 *         within the limits of baselines values
	 * @throws Exception
	 */
	public boolean compareReportJobPerfBaseline(String testCaseID, String reportTitle) throws Exception {
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = rootFolder + File.separator + "perf-metric" + File.separator
				+ "report-job-metrics" + File.separator + testCaseID;
		Path expectedFilePath = Paths.get(expectedDataFolderPath, String.format("%s.csv", testCaseID));
		// If NOT in baseline collection mode and NO baseline CSV file, throw No
		// Baseline exception.
		if (!TestContext.INSTANCE.getTestSetup().isCollectReportJobPerfMetric()) {
			if (!new File(expectedFilePath.toString()).exists()) {
				throw new Exception(String.format("Baseline CSV file-[%s] NOT found for TestCase-[%s]",
						expectedFilePath.toString(), testCaseID));
			}
		}

		// Clear existing failures messages.
		reportJobComparisonFailureMessages.clear();

		ReportJobsStat reportJobsStatObj = getReportJobStat(reportTitle);
		validateReportStatus(reportJobsStatObj);
		setPostDBStatList(new ArrayList<ReportJobPerfDBStat>());
		List<surveyor.api.source.ReportJob> reportJobs = reportJobsStatObj.ReportJobs;
		for (surveyor.api.source.ReportJob reportJob : reportJobs) {
			String reportJobTypeId = Reports.ReportJobTypeReverseGuids
					.get(ReportJobType.valueOf(reportJob.ReportJobType));

			// validate report job.
			validateReportJobStatus(reportJobsStatObj.ReportTitle, reportJob);
			validateReportJobProcessingTimesForNotNull(reportJobsStatObj.ReportTitle, reportJob, reportJobTypeId);

			Integer actualProcessingTimeInMs = (int) (reportJob.getProcessingCompletedTimeInMs()
					- reportJob.getProcessingStartedTimeInMs());

			addToListReportJobDBStat(reportJob, reportJobTypeId);

			if (TestContext.INSTANCE.getTestSetup().isCollectReportJobPerfMetric()) {
				// generating baselines. Skip comparison.
				generateBaselinePerfReportJobFiles(testCaseID, reportJobTypeId,
						String.valueOf(reportJob.getProcessingStartedTimeInMs()),
						String.valueOf(reportJob.getProcessingCompletedTimeInMs()), actualProcessingTimeInMs);
			} else {
				// compare actual with baseline.
				CSVUtility csvUtility = new CSVUtility();
				List<Map<String, String>> csvRows = csvUtility.getAllRows(expectedFilePath.toString());

				boolean foundInCsv = false;
				for (Map<String, String> csvRow : csvRows) {
					String expectedReportJobId = csvRow.get("ReportJobTypeId");
					if (reportJobTypeId.equals(expectedReportJobId)) {
						foundInCsv = true;
						Integer expectedProcessingTimeInMs = Integer.valueOf(csvRow.get("ProcessingTimeInMs"));
						if (actualProcessingTimeInMs > expectedProcessingTimeInMs) {
							// On comparison failure, let the test proceed on failure to collect the metrics for remaining report jobs.
							String failureMsg = String.format("Failure in ReportJobType=[%s] baselines comparison. Expected Processing Time in Msec=%s, "
									+ "Actual Processing Time in MSec=%s", reportJob.ReportJobType, expectedProcessingTimeInMs, actualProcessingTimeInMs);
							reportJobComparisonFailureMessages.add(failureMsg);
							Log.error(failureMsg);
						}
					}
				}

				// If no report job type in CSV, throw exception.
				if (!foundInCsv) {
					throw new Exception(
						String.format("Entry NOT found in Baseline CSV-[%s], for ReportJobType-[%s], ReportJobTypeId-[%s], TestCase-[%s]",
							expectedFilePath.toString(),
							Reports.ReportJobTypeGuids.get(reportJobTypeId).toString(),
							reportJobTypeId, testCaseID));
				}
			}
		}

		return (reportJobComparisonFailureMessages.size() == 0);
	}

	private void addToListReportJobDBStat(surveyor.api.source.ReportJob reportJob, String reportJobTypeId) {
		Log.method("addToListReportJobDBStat", reportJob, reportJobTypeId);
		ReportJobPerfDBStat reportJobPerfDBStat = new ReportJobPerfDBStat();
		reportJobPerfDBStat.setReportJobTypeId(reportJobTypeId);
		reportJobPerfDBStat.setBuildNumber(TestContext.INSTANCE.getTestSetup().getSoftwareVersion());
		reportJobPerfDBStat.setEnvironment(Environment.getEnvironment(TestContext.INSTANCE.getRunEnvironment()));
		reportJobPerfDBStat.setReportJobStartTime(DateUtility.fromUnixTime(reportJob.getProcessingStartedTimeInMs()));
		reportJobPerfDBStat.setReportJobEndTime(DateUtility.fromUnixTime(reportJob.getProcessingCompletedTimeInMs()));
		reportJobPerfDBStat.setReportJobTypeName(reportJob.ReportJobType);
		Log.info(String.format("Adding to postDBStat List : %s", reportJobPerfDBStat.toString()));
		getPostDBStatList().add(reportJobPerfDBStat);
	}

	public ReportJobsStat getReportJobStat(String reportTitle) {
		String apiRelativePath = String.format(ApiUtility.REPORTS_GET_REPORT_STAT_API_RELATIVE_URL, reportTitle);
		Log.info(String.format("Calling API Utility, URL : %s", apiRelativePath));
		String apiResponse = ApiUtility.getApiResponse(apiRelativePath);
		Log.info(String.format("API Response -> %s", apiResponse));
		Log.info("Creating gson Builder...");
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		Log.info("Getting ReportJobsStat from gson.fromJson()...");
		ReportJobsStat reportJobsStatObj = gson.fromJson(apiResponse, ReportJobsStat.class);
		Log.info(String.format("Successfully returned ReportJobsStat object -> %s", reportJobsStatObj.toString()));
		return reportJobsStatObj;
	}

	private int skipNewlyAddedRows(String lastSeenTitleCellText, String lastSeenCreatedByCellText, int rowNum,
			int maxRows) {
		String reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
		String createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

		String rptTitleCellText = getReportTableCellText(reportTitleXPath);
		String createByCellText = getReportTableCellText(createdByXPath);

		// If new rows get added in the time that we are waiting on report
		// processing to complete,
		// skip and move forward to the row that we were last processing.
		while (!(rptTitleCellText.trim().equalsIgnoreCase(lastSeenTitleCellText.trim())
				&& createByCellText.trim().equalsIgnoreCase(lastSeenCreatedByCellText.trim()))) {
			Log.info(String.format(
					"Found cell (skipping newly added) : rptTitleCell.getText()=[%s], createdByCell.getText()=[%s]",
					rptTitleCellText.trim(), createByCellText.trim()));

			rowNum++;
			if (rowNum > maxRows)
				break;

			Log.info(String.format("Processing row number - %d", rowNum));

			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCellText = getReportTableCellText(reportTitleXPath);
			createByCellText = getReportTableCellText(createdByXPath);
		}
		return rowNum;
	}

	private int skipNewlyAddedRows(String lastSeenTitleCellText, String lastSeenReportNameCellText,
			String lastSeenCreatedByCellText, int rowNum, int maxRows) {
		Log.method("skipNewlyAddedRows", lastSeenTitleCellText, lastSeenReportNameCellText, lastSeenCreatedByCellText, rowNum, maxRows);

		String reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
		String reportNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";
		String createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

		String rptTitleCellText = getReportTableCellText(reportTitleXPath);
		String rptNameCellText = getReportTableCellText(reportNameXPath);
		String createByCellText = getReportTableCellText(createdByXPath);

		// If new rows get added in the time that we are waiting on report
		// processing to complete,
		// skip and move forward to the row that we were last processing.
		Log.info(String.format("Looking for match on last seen values : lastSeenTitleCellText()=[%s], lastSeenReportNameCellText=[%s], lastSeenCreatedByCellText=[%s]",
				lastSeenTitleCellText.trim(), lastSeenReportNameCellText.trim(), lastSeenCreatedByCellText.trim()));
		while (!(rptTitleCellText.trim().equalsIgnoreCase(lastSeenTitleCellText.trim())
				&& rptNameCellText.trim().equalsIgnoreCase(lastSeenReportNameCellText.trim())
				&& createByCellText.trim().equalsIgnoreCase(lastSeenCreatedByCellText.trim()))) {
			Log.info(String.format("Found cell (skipping newly added) : rptTitleCellText=[%s], rptNameCellText=[%s], createdByCellText=[%s]",
					rptTitleCellText.trim(), rptNameCellText.trim(), createByCellText.trim()));

			rowNum++;
			if (rowNum > maxRows)
				break;

			Log.info(String.format("Processing row number - %d", rowNum));

			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			reportNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCellText = getReportTableCellText(reportTitleXPath);
			rptNameCellText = getReportTableCellText(reportNameXPath);
			createByCellText = getReportTableCellText(createdByXPath);
		}
		return rowNum;
	}

	private void validateReportStatus(ReportJobsStat reportJobsStatObj) throws Exception {
		if (!reportJobsStatObj.ReportStatus.equals(ReportStatusType.Complete.toString())) {
			throw new Exception(String.format("Report status NOT Complete. ReportTitle-[%s], ReportStatus-[%s]",
					reportJobsStatObj.ReportTitle, reportJobsStatObj.ReportStatus));
		}
	}

	private void validateReportJobStatus(String reportTitle, surveyor.api.source.ReportJob reportJob) throws Exception {
		if (!reportJob.ReportJobStatus.equals(ReportStatusType.Complete.toString())) {
			throw new Exception(String.format(
					"Report job status NOT Complete. ReportTitle-[%s], ReportJobType-[%s], " + "ReportJobStatus-[%s]",
					reportTitle, reportJob.ReportJobType, reportJob.ReportJobStatus));
		}
	}

	private void validateReportJobProcessingTimesForNotNull(String reportTitle, surveyor.api.source.ReportJob reportJob,
			String reportJobTypeId) throws Exception {
		if (reportJob.getProcessingStartedTimeInMs() < 0) {
			throw new Exception(String.format(
					"Incorrect value-[%s] encountered for column-[%s], ReportTitle-[%s], ReportJobTypeId-[%s]",
					reportJob.getProcessingStartedTimeInMs(), "ProcessingStarted", reportTitle, reportJobTypeId));
		}
		if (reportJob.getProcessingCompletedTimeInMs() < 0) {
			throw new Exception(String.format(
					"Incorrect value-[%s] encountered for column-[%s], ReportTitle-[%s], ReportJobTypeId-[%s]",
					reportJob.getProcessingCompletedTimeInMs(), "ProcessingCompleted", reportTitle, reportJobTypeId));
		}
	}

	/**
	 * Verify availability of survey modes with specific report mode selected
	 *
	 * @param rmf
	 *            - ReportModeFilter
	 * @return true if passed
	 */
	public boolean verifySurveyModeFilters(ReportModeFilter rmf) {
		boolean filtersFound = true;
		switch (rmf) {
		case Standard:
			filtersFound = isAllSurveyModeShown() && isStandardSurveyModeShown() && isOperatorSurveyModeShown()
					&& !isRapidResponseSurveyModeShown() && !isManualSurveyModeSelected();
			break;
		case RapidResponse:
			filtersFound = isAllSurveyModeShown() && isStandardSurveyModeShown() && isOperatorSurveyModeShown()
					&& isRapidResponseSurveyModeShown() && !isManualSurveyModeSelected();
			break;
		case Manual:
			filtersFound = isManualSurveyModeSelected() && !isStandardSurveyModeShown() && !isOperatorSurveyModeShown()
					&& !isAllSurveyModeShown() && !isRapidResponseSurveyModeShown();
			break;
		default:
			filtersFound = isAllSurveyModeShown() && isStandardSurveyModeShown() && isOperatorSurveyModeShown()
					&& !isRapidResponseSurveyModeShown() && !isManualSurveyModeSelected();
			break;
		}
		return filtersFound;
	}

	public boolean isAllSurveyModeShown() {
		return WebElementExtender.isElementPresentAndDisplayed(inputSurModeFilterAll);
	}

	public boolean isStandardReportModeShown() {
		return WebElementExtender.isElementPresentAndDisplayed(inputReportModeStd);
	}

	public boolean isRapidResponseReportModeShown() {
		return WebElementExtender.isElementPresentAndDisplayed(inputReportModeRapidR);
	}

	public boolean isManualReportModeShown() {
		return WebElementExtender.isElementPresentAndDisplayed(inputReportModeManual);
	}

	public boolean isStandardSurveyModeShown() {
		return WebElementExtender.isElementPresentAndDisplayed(inputSurModeFilterStd);
	}

	public boolean isOperatorSurveyModeShown() {
		return WebElementExtender.isElementPresentAndDisplayed(inputSurModeFilterOperator);
	}

	public boolean isRapidResponseSurveyModeShown() {
		return WebElementExtender.isElementPresentAndDisplayed(inputSurModeFilterRapidResponse);
	}

	public boolean isManualSurveyModeShown() {
		return WebElementExtender.isElementPresentAndDisplayed(inputSurModeFilterManual);
	}

	public boolean isManualSurveyModeSelected() {
		return inputSurModeFilterManual.isSelected();
	}

	/**
	 * Verify the Type of Surveys in the resulted table are valid for the Survey
	 * Mode Filter
	 *
	 * @param smf
	 * @return true if passed
	 */
	public boolean verifySurveySelectorWithFilter(SurveyModeFilter smf) {
		List<String> validType = new ArrayList<String>();
		switch (smf) {
		case All:
			if (inputReportModeRapidR.isSelected()) {
				validType.add(SurveyModeFilter.Standard.toString());
				validType.add(SurveyModeFilter.Operator.toString());
				validType.add(SurveyModeFilter.RapidResponse.toString());
			} else {
				validType.add(SurveyModeFilter.Standard.toString());
				validType.add(SurveyModeFilter.Operator.toString());
			}
			break;
		case Standard:
			validType.add(SurveyModeFilter.Standard.toString());
			break;
		case RapidResponse:
			validType.add(SurveyModeFilter.RapidResponse.toString());
			break;
		case Operator:
			validType.add(SurveyModeFilter.Operator.toString());
			break;
		case Manual:
			validType.add(SurveyModeFilter.Manual.toString());
			break;
		default:
			if (inputReportModeRapidR.isSelected()) {
				validType.add(SurveyModeFilter.Standard.toString());
				validType.add(SurveyModeFilter.Operator.toString());
				validType.add(SurveyModeFilter.RapidResponse.toString());
			} else {
				validType.add(SurveyModeFilter.Standard.toString());
				validType.add(SurveyModeFilter.Operator.toString());
			}
		}

		return !findInvalidSurveyType(validType);

	}

	/**
	 * Click on close button in report viewer.
	 */
	public void clickOnReportViewerCloseButton() {
		Log.clickElementInfo("Viewer Close");
		this.reportViewerCloseButton.click();
	}

	/**
	 * Click the search button for Survey filter and wait for the survey table
	 * to be loaded
	 */
	public void clickOnSearchSurveyButton() {
		Log.clickElementInfo("Survey Search");
		this.btnSurveySearch.click();
		this.waitForSurveyTabletoLoad();
	}

	/**
	 * Method to check for invalid surveys in the search result table
	 *
	 * @param invalidTypes
	 * @return true if invalid type found
	 */
	public boolean findInvalidSurveyType(List<String> invalidType) {

		String columnName = "Type";
		Map<String, List<String>> filter = new HashMap<String, List<String>>();
		filter.put(columnName, invalidType);

		By tableContextBy = By.id("datatableSurveys_wrapper");
		WebElement tableContext = driver.findElement(tableContextBy);
		DataTablePage dataTable = DataTablePage.getDataTablePage(driver, tableContext, this.testSetup, this.strBaseURL,
				this.strPageURL);

		return dataTable.hasRecord(filter, false);
	}

	public List<ReportJobPerfDBStat> getPostDBStatList() {
		return postDBStatList;
	}

	private void setPostDBStatList(List<ReportJobPerfDBStat> postDBStatList) {
		this.postDBStatList = Collections.synchronizedList(postDBStatList);
	}

	public ChangeCustomerDialogControl getChangeCustomerDialog() {
		return changeCustomerDialog;
	}

	public long getReportStartEpochTime() {
		return reportStartEpochTime;
	}

	public void setReportStartEpochTime(long unixEpochTime) {
		this.reportStartEpochTime = unixEpochTime;
	}

	public long getReportEndEpochTime() {
		return reportEndEpochTime;
	}

	public void setReportEndEpochTime(long reportEndEpochTime) {
		this.reportEndEpochTime = reportEndEpochTime;
	}

	public String getReportFileName(String rptTitle) {
		return getReportPDFFileName(rptTitle, false /*includeExtension*/);
	}

	public String getReportName(String rptTitle) {
		// Implementation to be provided by derived type.
		return null;
	}

	public String getReportPDFFileName(String rptTitle, boolean includeExtension) {
		// Implementation to be provided by derived type.
		return null;
	}
}
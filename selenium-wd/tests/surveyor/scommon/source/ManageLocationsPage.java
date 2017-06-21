/**
 *
 */
package surveyor.scommon.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;
import static surveyor.scommon.source.SurveyorConstants.REQUIRED_FIELD_VAL_MESSAGE;
import static surveyor.scommon.source.SurveyorConstants.SECONDS_10;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import common.source.BaseHelper;
import common.source.Log;
import common.source.TestSetup;
import common.source.WebElementExtender;

/**
 * @author zlu
 *
 */
public class ManageLocationsPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageLocations";
	public static final String STRPageTitle = String.format("%s - %s",
			Resources.getResource(ResourceKeys.ManageLocations_PageTitle), Resources.getResource(ResourceKeys.Constant_Surveyor));
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ManageLocations_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ManageLocation_NewLocation);
	public static final String STREditPageContentText = Resources.getResource(ResourceKeys.ManageLocation_EditLocation);
	public static final String STRDuplicateLocMsg = Resources.getResource(ResourceKeys.ManageLocation_DuplicateNameError);

	@FindBy(css = "a[href='/Picarro/ManageLocation']")
	protected WebElement btnAddNewLocation;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	protected WebElement dropDownAdministrator;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log out')]")
	protected WebElement linkLogOut;

	@FindBy(id = "Description")
	protected WebElement inputLocationDesc;

	@FindBy(id = "btn-select-point")
	private WebElement latLongSelectorBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='point-latitude']")
	protected WebElement inputLocationLat;

	@FindBy(how = How.XPATH, using = "//*[@id='point-longitude']")
	protected WebElement inputLocationLong;

	@FindBy(how = How.XPATH, using = "//*[@id='CustomerId']")
	protected WebElement dropDownCustomer;

	@FindBy(how = How.XPATH, using = "//*[@id='StandardMinimumAmplitude']")
	protected WebElement stdMinAmp;

	@FindBy(id = "AssessmentMinimumAmplitude")
	protected WebElement assessmentMinAmp;

	@FindBy(id = "EQMinimumAmplitude")
	protected WebElement eqMinAmp;

	@FindBy(how = How.XPATH, using = "//*[@id='OperatorMinimumAmplitude']")
	protected WebElement opdMinAmp;

	@FindBy(how = How.XPATH, using = "//*[@id='RapidResponseMinimumAmplitude']")
	protected WebElement RRMinAmp;

	@FindBy(how = How.XPATH, using = "//*[@id='NoLowerBound']")
	protected WebElement NoLower;

	@FindBy(how = How.XPATH, using = "//*[@id='YesLowerBound']")
	protected WebElement YesLower;

	@FindBy(how = How.XPATH, using = "//*[@id='NoUpperBound']")
	protected WebElement NoUpper;

	@FindBy(how = How.XPATH, using = "//*[@id='YesUpperBound']")
	protected WebElement YesUpper;

	@FindBy(how = How.XPATH, using = "//*[@id='hasFacilityEQ']")
	protected WebElement facilityEQCheckbox;

	@FindBy(how = How.XPATH, using = "//*[@id='hasMobileEQ']")
	protected WebElement mobileEQCheckbox;

	@FindBy(how = How.XPATH, using = "//*[@id='buttonOk']")
	protected WebElement btnOK;

	@FindBy(css = "a[class='button-cancel btn btn-danger']")
	protected WebElement btnCancel;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[5]/a")
	protected WebElement btnEditLocation;

	@FindBy(id = "Description-error")
	private WebElement labelLocDescError;
	private String labelLocDescErrorXPath = "//label[@id='Description-error']";
	private String labelLocDescErrorID = "Description-error";

	@FindBy(id = "point-latitude-error")
	private WebElement labelLatValueError;
	private String labelLatValueErrorXPath = "//label[@id='point-latitude-error']";

	@FindBy(id = "point-longitude-error")
	private WebElement labelLongValueError;
	private String labelLongValueErrorXPath = "//label[@id='point-longitude-error']";

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[1]")
	protected WebElement tdCustomerValue;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[2]")
	protected WebElement tdLocationValue;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[2]")
	protected WebElement theadLocation;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr")
	protected List<WebElement> rows;

	@FindBy(how = How.XPATH, using = "//*[@id='location-form']/fieldset/div/legend[text()='Ethane to Methane Ratio %']")
	protected WebElement ethMthRtioLbl;

	@FindBy(how = How.ID, using = "Min")
	protected WebElement ethMthMinUnit;

	@FindBy(how = How.ID, using = "Max")
	protected WebElement ethMthMaxUnit;

	@FindBy(id = "info")
	protected WebElement selectedPoint;

	@FindBy(id = "AnalyticsMinimumAmplitude")
	protected WebElement surMinAmp;

	@FindBy(id = "LocationAnalyticsParameter_RankingMinAmplitude")
	protected WebElement rankingMinAmp;

	@FindBy(id = "LocationAnalyticsParameter_PriorityScoreFirst")
	protected WebElement top10PS;

	@FindBy(id = "LocationAnalyticsParameter_PriorityScoreSecond")
	protected WebElement top25PS;

	@FindBy(id = "LocationAnalyticsParameter_PriorityScoreThird")
	protected WebElement top50PS;

	@FindBy(id = "LocationAnalyticsParameter_PriorityScoreFilterThreshold")
	protected WebElement psFilter;

	@FindBy(id = "LocationAnalyticsParameter_DbScanRadius")
	protected WebElement dbScanRd;

	@FindBy(id = "LocationAnalyticsParameter_MinClusterSize")
	protected WebElement minClusterSz;

	@FindBy(id = "LocationAnalyticsParameter_MaxSpatialScale")
	protected WebElement maxClusterScale;

	@FindBy(id = "LocationAnalyticsParameter_ExpansionPower")
	protected WebElement expansionPower;

	@FindBy(id = "LocationAnalyticsParameter_InflationPower")
	protected WebElement inflationPower;

	@FindBy(id = "LocationAnalyticsParameter_Percentile")
	protected WebElement percentile;

	@FindBy(id = "LocationAnalyticsParameter_JustDbScan")
	protected WebElement justDBScan;

	@FindBy(id = "LocationEQParameterList_0__EQShapeCorrelationMin")
	protected WebElement feqShapeCorrelationMin;

	@FindBy(id = "LocationEQParameterList_0__EQPeakIDXBuffer")
	protected WebElement feqPeakIDXBuffer;

	@FindBy(id = "LocationEQParameterList_0__EQPeakSEPDistanceScale")
	protected WebElement feqPeakSEPDistanceScale;

	@FindBy(id = "LocationEQParameterList_0__EQWidthMin")
	protected WebElement feqWidthMin;

	@FindBy(id = "LocationEQParameterList_0__EQWidthMax")
	protected WebElement feqWidthMax;

	@FindBy(id = "LocationEQParameterList_0__EQVariationMax")
	protected WebElement feqVariationMax;

	@FindBy(id = "LocationEQParameterList_0__EQCarSpeedMin")
	protected WebElement feqCarSpeedMin;

	@FindBy(id = "LocationEQParameterList_0__EQCarSpeedMax")
	protected WebElement feqCarSpeedMax;

	@FindBy(id = "LocationEQParameterList_0__EQCarWindAngleMin")
	protected WebElement feqCarWindAngleMin;

	@FindBy(id = "LocationEQParameterList_0__EQCarWindAngleMax")
	protected WebElement feqCarWindAngleMax;

	@FindBy(id = "LocationEQParameterList_0__EQDBScanSpatialScale")
	protected WebElement feqDBScanSpatialScale;

	@FindBy(id = "LocationEQParameterList_0__EQMinClusterSize")
	protected WebElement feqMinClusterSize;

	@FindBy(id = "LocationEQParameterList_0__EQBackgroundFilterThreshold")
	protected WebElement feqBackgroundFilterThreshold;

	@FindBy(id = "LocationEQParameterList_0__EQPPMTriggerThreshold")
	protected WebElement feqPPMTriggerThreshold;

	@FindBy(id = "LocationEQParameterList_0__EQAccelerationMax")
	protected WebElement feqAccelerationMax;

	@FindBy(id = "LocationEQParameterList_0__EQJustDBScan")
	protected WebElement feqJustDBScan;

	@FindBy(id = "LocationEQParameterList_1__EQShapeCorrelationMin")
	protected WebElement meqShapeCorrelationMin;

	@FindBy(id = "LocationEQParameterList_1__EQPeakIDXBuffer")
	protected WebElement meqPeakIDXBuffer;

	@FindBy(id = "LocationEQParameterList_1__EQPeakSEPDistanceScale")
	protected WebElement meqPeakSEPDistanceScale;

	@FindBy(id = "LocationEQParameterList_1__EQWidthMin")
	protected WebElement meqWidthMin;

	@FindBy(id = "LocationEQParameterList_1__EQWidthMax")
	protected WebElement meqWidthMax;


	@FindBy(id = "LocationEQParameterList_1__EQVariationMax")
	protected WebElement meqVariationMax;

	@FindBy(id = "LocationEQParameterList_1__EQCarSpeedMin")
	protected WebElement meqCarSpeedMin;

	@FindBy(id = "LocationEQParameterList_1__EQCarSpeedMax")
	protected WebElement meqCarSpeedMax;

	@FindBy(id = "LocationEQParameterList_1__EQCarWindAngleMin")
	protected WebElement meqCarWindAngleMin;

	@FindBy(id = "LocationEQParameterList_1__EQCarWindAngleMax")
	protected WebElement meqCarWindAngleMax;

	@FindBy(id = "LocationEQParameterList_1__EQDBScanSpatialScale")
	protected WebElement meqDBScanSpatialScale;

	@FindBy(id = "LocationEQParameterList_1__EQMinClusterSize")
	protected WebElement meqMinClusterSize;

	@FindBy(id = "LocationEQParameterList_1__EQBackgroundFilterThreshold")
	protected WebElement meqBackgroundFilterThreshold;

	@FindBy(id = "LocationEQParameterList_1__EQPPMTriggerThreshold")
	protected WebElement meqPPMTriggerThreshold;

	@FindBy(id = "LocationEQParameterList_1__EQAccelerationMax")
	protected WebElement meqAccelerationMax;

	@FindBy(id = "LocationEQParameterList_1__EQJustDBScan")
	protected WebElement meqJustDBScan;

	private LatLongSelectionControl latLongSelectionControl = null;

	private String latitude;
	private String longitude;

	private ChangeCustomerDialogControl changeCustomerDialog = null;

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageLocationsPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		latLongSelectionControl = new LatLongSelectionControl(driver);
		PageFactory.initElements(driver, latLongSelectionControl);

		changeCustomerDialog = new ChangeCustomerDialogControl(driver);
		PageFactory.initElements(driver, changeCustomerDialog);

		Log.info("\nThe Manage Locations Page URL is: " + this.strPageURL);
	}

	public ManageLocationsPage(WebDriver driver, String baseURL, TestSetup testSetup, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}

	public boolean addNewLocation(String locationDesc, String customer, String newLocationName) {
		Log.method("addNewLocation", locationDesc, customer, newLocationName);
		return addNewLocation(locationDesc, customer, newLocationName, false /* UseLatLongSelector */, "1", "2");
	}

	public boolean addNewLocationUsingLatLongSelector(String locationDesc, String customer, String newLocationName) {
		Log.method("addNewLocationUsingLatLongSelector", locationDesc, customer, newLocationName);
		return addNewLocation(locationDesc, customer, newLocationName, true /* UseLatLongSelector */, "1", "2");
	}

	public boolean addNewLocation(String locationDesc, String customer, String newLocationName, String ethMthMin, String ethMthMax) {
		Log.method("addNewLocation", locationDesc, customer, newLocationName, ethMthMin, ethMthMax);
		return addNewLocation(locationDesc, customer, newLocationName, false /* UseLatLongSelector */, ethMthMin, ethMthMax);
	}

	public boolean addNewLocationUsingLatLongSelector(String locationDesc,
			String customer, String newLocationName, String ethMthMin,
			String ethMthMax) {
		Log.method("addNewLocationUsingLatLongSelector", locationDesc,
				customer, newLocationName, ethMthMin, ethMthMax);
		return addNewLocation(locationDesc, customer, newLocationName, true /* UseLatLongSelector */, ethMthMin, ethMthMax, "","", "", "", "", "", "", "", "", "", "", "",
				null,null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, true);
	}

	private boolean addNewLocation(String locationDesc, String customer,
			String newLocationName, boolean useLatLongSelector,
			String ethMthMin, String ethMthMax) {
		Log.method("addNewLocation", locationDesc, customer, newLocationName,
				useLatLongSelector, ethMthMin, ethMthMax);
		return addNewLocation(locationDesc, customer, newLocationName,
				useLatLongSelector, ethMthMin, ethMthMax, "", "", "", "", "", "", "", "", "", "", "", "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, true);
	}

	public boolean addNewLocation(String locationDesc, String customer,	String newLocationName, String surMinAmp, String rankingMinAmp, String top10PS, String top25PS,
			String top50PS, String psFilter, String dbScanRd, String minClusterSz, String maxClusterScale, String expansionPower, String inflationPower, String percentile) {

		Log.method("addNewLocation", locationDesc, customer, newLocationName, surMinAmp, rankingMinAmp, psFilter, top10PS, top25PS,	top50PS, dbScanRd, minClusterSz,
				maxClusterScale, expansionPower, inflationPower, percentile);

		return addNewLocation(locationDesc, customer, newLocationName, false, "1", "2", surMinAmp, rankingMinAmp, psFilter, top10PS,
				top25PS, top50PS, dbScanRd, minClusterSz, maxClusterScale, expansionPower, inflationPower, percentile, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, true);
	}

	public boolean addNewLocation(String locationDesc, String customer,
			String newLocationName, boolean useLatLongSelector, String ethMthMin, String ethMthMax, String surMinAmp, String rankingMinAmp, String psFilter,
			String top10PS, String top25PS, String top50PS, String dbScanRd, String minClusterSz, String maxClusterScale, String expansionPower, String inflationPower,
			String percentile, String surveyMode, String ShapeCorrelationMin, String PeakIDXBuffer, String PeakSEPDistanceScale, String WidthMin, String WidthMax, String VariationMax,
			String CarSpeedMin, String CarSpeedMax, String CarWindAngleMin, String CarWindAngleMax, String DBScanSpatialScale, String MinClusterSize, String BackgroundFilterThreshold,
			String PPMTriggerThreshold,String AccelerationMax, boolean JustDBScan, boolean checkForError) {
		Log.method("addNewLocation", locationDesc, customer, newLocationName, useLatLongSelector, ethMthMin, ethMthMax, surveyMode, ShapeCorrelationMin, PeakIDXBuffer,
				PeakSEPDistanceScale, WidthMin, WidthMax, VariationMax, CarSpeedMin, CarSpeedMax, CarWindAngleMin, CarWindAngleMax, DBScanSpatialScale, MinClusterSize,
				BackgroundFilterThreshold, PPMTriggerThreshold,	AccelerationMax, surMinAmp, rankingMinAmp, psFilter, top10PS, top25PS, top50PS, dbScanRd, minClusterSz,
				maxClusterScale, expansionPower, inflationPower, percentile, checkForError);
		if (newLocationName.equalsIgnoreCase("Santa Clara")) {
			setLatitude("37.3971035425739");
			setLongitude("-121.98343231897");
		}

		this.btnAddNewLocation.click();
		waitForNewPageLoad();

		Log.info("Select customer - '"+customer+"'");
		selectDropdownOption(this.dropDownCustomer, customer);
		changeCustomerDialog.confirmInChangeCustomerDialog();

		Log.info("Enter location description - '" + locationDesc + "'");
		this.inputLocationDesc.sendKeys(locationDesc);

		// wait necessary for clickOnLatLongSelectorBtn to function correctly.
		this.waitForPageToLoad();

		if (!useLatLongSelector) {
			inputLatLong(getLatitude(), getLongitude());
		} else {
			final int X_OFFSET = 100;
			final int Y_OFFSET = 100;

			this.clickOnLatLongSelectorBtn();
			this.selectOnLatLong(X_OFFSET, Y_OFFSET);
			this.clickOnLatLongOkBtn();

			String locationLatitudeText = this.getLocationLatitudeText();
			Log.info("Location Latitude Field value = " + locationLatitudeText);
			assertTrue(!locationLatitudeText.isEmpty());

			String locationLongitudeText = this.getLocationLongitudeText();
			Log.info("Location Longitude Field value = " + locationLongitudeText);
			assertTrue(!locationLongitudeText.isEmpty());
		}

		this.stdMinAmp.clear();
		this.stdMinAmp.sendKeys("0.035");
		if (WebElementExtender.isElementPresentAndDisplayed(this.opdMinAmp)) {
			this.opdMinAmp.clear();
			this.opdMinAmp.sendKeys("5");
		}
		if (WebElementExtender.isElementPresentAndDisplayed(this.RRMinAmp)) {
			this.RRMinAmp.clear();
			this.RRMinAmp.sendKeys("5");
		}
		if (WebElementExtender.isElementPresentAndDisplayed(this.assessmentMinAmp)) {
			this.assessmentMinAmp.clear();
			this.assessmentMinAmp.sendKeys("0.035");
		}
		if (WebElementExtender.isElementPresentAndDisplayed(this.eqMinAmp)) {
			this.eqMinAmp.clear();
			this.eqMinAmp.sendKeys("0.035");
		}
		if (WebElementExtender.isElementPresentAndDisplayed(this.surMinAmp)) {
			if (surMinAmp != null && surMinAmp != "") {
				setSurveyMinAmp(surMinAmp);
			}
		}
		if (!BaseHelper.isNullOrEmpty(rankingMinAmp)) {
			if (WebElementExtender.isElementPresentAndDisplayed(this.rankingMinAmp)) {
				Log.info("Set Ranking Min Amp - '" + rankingMinAmp + "'");
				this.rankingMinAmp.clear();
				this.rankingMinAmp.sendKeys(rankingMinAmp);
			}
		}
		if (!BaseHelper.isNullOrEmpty(top10PS)) {
			if (WebElementExtender.isElementPresentAndDisplayed(this.top10PS)) {
				Log.info("Set Top 10 PS - '" + top10PS + "'");
				this.top10PS.clear();
				this.top10PS.sendKeys(top10PS);
			}
		}
		if (!BaseHelper.isNullOrEmpty(top25PS)) {
			if (WebElementExtender.isElementPresentAndDisplayed(this.top25PS)) {
				Log.info("Set Top 25 PS - '" + top25PS + "'");
				this.top25PS.clear();
				this.top25PS.sendKeys(top25PS);
			}
		}
		if (!BaseHelper.isNullOrEmpty(top50PS)) {
			if (WebElementExtender.isElementPresentAndDisplayed(this.top50PS)) {
				Log.info("Set Top 50 PS - '" + top50PS + "'");
				this.top50PS.clear();
				this.top50PS.sendKeys(top50PS);
			}
		}
		if (!BaseHelper.isNullOrEmpty(psFilter)) {
			if (WebElementExtender.isElementPresentAndDisplayed(this.psFilter)) {
				setPsFilter(psFilter);
			}
		}
		if (!BaseHelper.isNullOrEmpty(dbScanRd)) {
			if (WebElementExtender.isElementPresentAndDisplayed(this.dbScanRd)) {
				Log.info("Set DB Scan Radius - '" + dbScanRd + "'");
				this.dbScanRd.clear();
				this.dbScanRd.sendKeys(dbScanRd);
			}
		}
		if (!BaseHelper.isNullOrEmpty(minClusterSz)) {
			if (WebElementExtender.isElementPresentAndDisplayed(this.minClusterSz)) {
				Log.info("Set Min Cluster Size - '" + minClusterSz + "'");
				this.minClusterSz.clear();
				this.minClusterSz.sendKeys(minClusterSz);
			}
		}
		if (!BaseHelper.isNullOrEmpty(maxClusterScale)) {
			if (WebElementExtender.isElementPresentAndDisplayed(this.maxClusterScale)) {
				Log.info("Set Max Cluster Scale - '" + maxClusterScale + "'");
				this.maxClusterScale.clear();
				this.maxClusterScale.sendKeys(maxClusterScale);
			}
		}
		if (!BaseHelper.isNullOrEmpty(expansionPower)) {
			if (WebElementExtender.isElementPresentAndDisplayed(this.expansionPower)) {
				Log.info("Set Expansion Power - '" + expansionPower + "'");
				this.expansionPower.clear();
				this.expansionPower.sendKeys(expansionPower);
			}
		}
		if (!BaseHelper.isNullOrEmpty(inflationPower)) {
			if (WebElementExtender.isElementPresentAndDisplayed(this.inflationPower)) {
				Log.info("Set Inflation Power - '" + inflationPower + "'");
				this.inflationPower.clear();
				this.inflationPower.sendKeys(inflationPower);
			}
		}
		if (!BaseHelper.isNullOrEmpty(percentile)) {
			if (WebElementExtender.isElementPresentAndDisplayed(this.percentile)) {
				Log.info("Set Percentile - '" + percentile + "'");
				this.percentile.clear();
				this.percentile.sendKeys(percentile);
			}
		}

		this.NoLower.clear();
		this.NoLower.sendKeys("-45");
		this.YesLower.clear();
		this.YesLower.sendKeys("-42");
		this.YesUpper.clear();
		this.YesUpper.sendKeys("-30");
		this.NoUpper.clear();
		this.NoUpper.sendKeys("-25");

		if (!BaseHelper.isNullOrEmpty(ethMthMin)) {
			this.ethMthMinUnit.clear();
			this.ethMthMinUnit.sendKeys(ethMthMin);
		}

		if (!BaseHelper.isNullOrEmpty(ethMthMax)) {
			this.ethMthMaxUnit.clear();
			this.ethMthMaxUnit.sendKeys(ethMthMax);
		}

		if (!BaseHelper.isNullOrEmpty(surveyMode)) {
			if (surveyMode.equals("FEQ")){
				assertTrue(this.facilityEQCheckbox.isDisplayed());
				this.facilityEQCheckbox.click();
				inputFeqParameters(ShapeCorrelationMin, PeakIDXBuffer, PeakSEPDistanceScale, WidthMin, WidthMax, VariationMax, CarSpeedMin,
						CarSpeedMax, CarWindAngleMin, CarWindAngleMax, DBScanSpatialScale, MinClusterSize, BackgroundFilterThreshold, PPMTriggerThreshold,
						AccelerationMax, JustDBScan);
			}
			else if(surveyMode.equals("MEQ")){
				assertTrue(this.mobileEQCheckbox.isDisplayed());
				this.mobileEQCheckbox.click();
				inputMeqParameters(ShapeCorrelationMin, PeakIDXBuffer, PeakSEPDistanceScale, WidthMin, WidthMax, VariationMax, CarSpeedMin,
						CarSpeedMax, CarWindAngleMin, CarWindAngleMax, DBScanSpatialScale, MinClusterSize, BackgroundFilterThreshold, PPMTriggerThreshold,
						AccelerationMax, JustDBScan);
			}
			else{
				Log.warn(String.format("Do not support this location parameters. SurveyMode=[%s]", surveyMode));
			}
		}

		clickOnOkButton();

		if(checkForError && verifyErrorMessage(null, true /*checkOnlyErrorSummary*/)){
			Log.clickElementInfo("Cancel");
			this.btnCancel.click();
			return false;
		}
		return true;
	}

	public void setSurveyMinAmp(String surMinAmp){
		Log.info("Set Survey Min Amp - '" + surMinAmp + "'");
		this.surMinAmp.clear();
		this.surMinAmp.sendKeys(surMinAmp);
	}

	public void setRankingMinAmp(String rankingMinAmp){
		Log.info("Set Ranking Min Amp - '" + rankingMinAmp + "'");
		this.rankingMinAmp.clear();
		this.rankingMinAmp.sendKeys(rankingMinAmp);
	}

	public void setPsFilter(String psFilter){
		Log.info("Set Filter PS - '" + psFilter + "'");
		this.psFilter.clear();
		this.psFilter.sendKeys(psFilter);
	}

	public void setTop10Ps(String top10PS){
		Log.info("Set Top10PS - '" + top10PS + "'");
		this.top10PS.clear();
		this.top10PS.sendKeys(top10PS);
	}

	public void setTop25Ps(String top25PS){
		Log.info("Set Top25PS - '" + top25PS + "'");
		this.top25PS.clear();
		this.top25PS.sendKeys(top25PS);
	}

	public void setTop50Ps(String top50PS){
		Log.info("Set Top50PS - '" + top50PS + "'");
		this.top50PS.clear();
		this.top50PS.sendKeys(top50PS);
	}

	public void clickOnOkButton(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Log.clickElementInfo("Ok");
		js.executeScript("arguments[0].click();", this.btnOK);
		this.waitForPageToLoad();
	}

	public boolean verifyErrorMessage(String errorMsg){
		Log.method("verifyErrorMessage", errorMsg);
		return verifyErrorMessage(errorMsg, false /*checkOnlyErrorSummary*/);
	}

	private boolean verifyErrorMessage(String errorMsg, boolean checkOnlyErrorSummary){
		Log.method("verifyErrorMessage", errorMsg, checkOnlyErrorSummary);
		boolean found = false;
		if (isElementPresent(this.summaryErrorsBy)) {
			if (this.summaryErrors.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))){
				if (checkOnlyErrorSummary) {
					found = true;
				} else {
					for(WebElement element:this.panelErrors){
						if(element.getText().equals(errorMsg)){
							found = true;
							Log.info("Error message found - '"+errorMsg+"'");
							break;
						}
					}
				}
			}
		}
		return found;
	}

	public void inputFeqParameters(String shapeCorrelationMin, String peakIDXBuffer, String peakSEPDistanceScale, String widthMin, String widthMax, String variationMax,
			String carSpeedMin,	String carSpeedMax, String carWindAngleMin, String carWindAngleMax, String dBScanSpatialScale, String minClusterSize, String backgroundFilterThreshold,
			String pPMTriggerThreshold,	String accelerationMax, boolean justDBScan){
		Log.method("input FEQ Parameters", shapeCorrelationMin, peakIDXBuffer, peakSEPDistanceScale, widthMin, widthMax, variationMax, carSpeedMin, carSpeedMax, carWindAngleMin,
				carWindAngleMax, dBScanSpatialScale, minClusterSize, backgroundFilterThreshold, pPMTriggerThreshold, accelerationMax, justDBScan);

		this.waitForElementToBeClickable(feqShapeCorrelationMin);

		if (shapeCorrelationMin != null && shapeCorrelationMin != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqShapeCorrelationMin)) {
				Log.info("Set FEQ shapeCorrelationMin - '" + shapeCorrelationMin + "'");
				this.feqShapeCorrelationMin.clear();
				this.feqShapeCorrelationMin.sendKeys(shapeCorrelationMin);
			}
		}

		if (peakIDXBuffer != null && peakIDXBuffer != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqPeakIDXBuffer)) {
				Log.info("Set FEQ peakIDXBuffer - '"+ peakIDXBuffer+"'");
				this.feqPeakIDXBuffer.clear();
				this.feqPeakIDXBuffer.sendKeys(peakIDXBuffer);
			}
		}

		if (peakSEPDistanceScale != null && peakSEPDistanceScale != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqPeakSEPDistanceScale)) {
				Log.info("Set FEQ peakSEPDistanceScale - '"+ peakSEPDistanceScale+"'");
				this.feqPeakSEPDistanceScale.clear();
				this.feqPeakSEPDistanceScale.sendKeys(peakSEPDistanceScale);
			}
		}

		if (widthMin != null && widthMin != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqWidthMin)) {
				Log.info("Set FEQ widthMin - '"+ widthMin+"'");
				this.feqWidthMin.clear();
				this.feqWidthMin.sendKeys(widthMin);
			}
		}

		if (widthMax != null && widthMax != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqWidthMax)) {
				Log.info("Set FEQ widthMax - '"+ widthMax+"'");
				this.feqWidthMax.clear();
				this.feqWidthMax.sendKeys(widthMax);
			}
		}

		if (variationMax != null && variationMax != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqVariationMax)) {
				Log.info("Set FEQ variationMax - '"+ variationMax+"'");
				this.feqVariationMax.clear();
				this.feqVariationMax.sendKeys(variationMax);
			}
		}

		if (carSpeedMin != null && carSpeedMin != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqCarSpeedMin)) {
				Log.info("Set FEQ CarSpeedMin - '"+ carSpeedMin+"'");
				this.feqCarSpeedMin.clear();
				this.feqCarSpeedMin.sendKeys(carSpeedMin);
			}
		}

		if (carSpeedMin != null && carSpeedMin != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqCarSpeedMin)) {
				Log.info("Set FEQ carSpeedMax - '"+ carSpeedMax+"'");
				this.feqCarSpeedMax.clear();
				this.feqCarSpeedMax.sendKeys(carSpeedMax);
			}
		}

		if (carWindAngleMin != null && carWindAngleMin != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqCarWindAngleMin)) {
				Log.info("Set FEQ carWindAngleMin - '"+ carWindAngleMin+"'");
				this.feqCarWindAngleMin.clear();
				this.feqCarWindAngleMin.sendKeys(carWindAngleMin);
			}
		}

		if (carWindAngleMax != null && carWindAngleMax != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqCarWindAngleMax)) {
				Log.info("Set FEQ carWindAngleMax - '"+ carWindAngleMax+"'");
				this.feqCarWindAngleMax.clear();
				this.feqCarWindAngleMax.sendKeys(carWindAngleMax);
			}
		}

		if (dBScanSpatialScale != null && dBScanSpatialScale != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqDBScanSpatialScale)) {
				Log.info("Set FEQ DBScanSpatialScale - '"+ dBScanSpatialScale+"'");
				this.feqDBScanSpatialScale.clear();
				this.feqDBScanSpatialScale.sendKeys(dBScanSpatialScale);
			}
		}

		if (minClusterSize != null && minClusterSize != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqMinClusterSize)) {
				Log.info("Set FEQ minClusterSize - '"+ minClusterSize+"'");
				this.feqMinClusterSize.clear();
				this.feqMinClusterSize.sendKeys(minClusterSize);
			}
		}

		if (backgroundFilterThreshold != null && backgroundFilterThreshold != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqBackgroundFilterThreshold)) {
				Log.info("Set FEQ backgroundFilterThreshold - '"+ backgroundFilterThreshold+"'");
				this.feqBackgroundFilterThreshold.clear();
				this.feqBackgroundFilterThreshold.sendKeys(backgroundFilterThreshold);
			}
		}

		if (pPMTriggerThreshold != null && pPMTriggerThreshold != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqPPMTriggerThreshold)) {
				Log.info("Set FEQ PPMTriggerThreshold - '"+ pPMTriggerThreshold+"'");
				this.feqPPMTriggerThreshold.clear();
				this.feqPPMTriggerThreshold.sendKeys(pPMTriggerThreshold);
			}
		}

		if (accelerationMax != null && accelerationMax != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.feqAccelerationMax)) {
				Log.info("Set FEQ accelerationMax - '"+ accelerationMax+"'");
				this.feqAccelerationMax.clear();
				this.feqAccelerationMax.sendKeys(accelerationMax);
			}
		}

		if(justDBScan != this.feqJustDBScan.isSelected()){
			Log.info("Select FEQ JustDBScan - '"+ justDBScan +"'");
			this.feqJustDBScan.click();
		}

		clickOnOkButton();
	}

	public void inputMeqParameters(String shapeCorrelationMin, String peakIDXBuffer, String peakSEPDistanceScale, String widthMin, String widthMax, String variationMax,
			String carSpeedMin,	String carSpeedMax, String carWindAngleMin, String carWindAngleMax, String dBScanSpatialScale, String minClusterSize, String backgroundFilterThreshold,
			String pPMTriggerThreshold,	String accelerationMax, boolean justDBScan){
		Log.method("input MEQ Parameters", shapeCorrelationMin, peakIDXBuffer, peakSEPDistanceScale, widthMin, widthMax, variationMax, carSpeedMin, carSpeedMax, carWindAngleMin,
				carWindAngleMax, dBScanSpatialScale, minClusterSize, backgroundFilterThreshold, pPMTriggerThreshold, accelerationMax, justDBScan);

		this.waitForElementToBeClickable(meqShapeCorrelationMin);

		if (shapeCorrelationMin != null && shapeCorrelationMin != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqShapeCorrelationMin)) {
				Log.info("Set MEQ shapeCorrelationMin - '" + shapeCorrelationMin + "'");
				this.meqShapeCorrelationMin.clear();
				this.meqShapeCorrelationMin.sendKeys(shapeCorrelationMin);
			}
		}

		if (peakIDXBuffer != null && peakIDXBuffer != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqPeakIDXBuffer)) {
				Log.info("Set MEQ peakIDXBuffer - '"+ peakIDXBuffer+"'");
				this.meqPeakIDXBuffer.clear();
				this.meqPeakIDXBuffer.sendKeys(peakIDXBuffer);
			}
		}

		if (peakSEPDistanceScale != null && peakSEPDistanceScale != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqPeakSEPDistanceScale)) {
				Log.info("Set MEQ peakSEPDistanceScale - '"+ peakSEPDistanceScale+"'");
				this.meqPeakSEPDistanceScale.clear();
				this.meqPeakSEPDistanceScale.sendKeys(peakSEPDistanceScale);
			}
		}

		if (widthMin != null && widthMin != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqWidthMin)) {
				Log.info("Set MEQ widthMin - '"+ widthMin+"'");
				this.meqWidthMin.clear();
				this.meqWidthMin.sendKeys(widthMin);
			}
		}

		if (widthMax != null && widthMax != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqWidthMax)) {
				Log.info("Set MEQ widthMax - '"+ widthMax+"'");
				this.meqWidthMax.clear();
				this.meqWidthMax.sendKeys(widthMax);
			}
		}

		if (variationMax != null && variationMax != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqVariationMax)) {
				Log.info("Set MEQ variationMax - '"+ variationMax+"'");
				this.meqVariationMax.clear();
				this.meqVariationMax.sendKeys(variationMax);
			}
		}

		if (carSpeedMin != null && carSpeedMin != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqCarSpeedMin)) {
				Log.info("Set MEQ CarSpeedMin - '"+ carSpeedMin+"'");
				this.meqCarSpeedMin.clear();
				this.meqCarSpeedMin.sendKeys(carSpeedMin);
			}
		}

		if (carSpeedMin != null && carSpeedMin != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqCarSpeedMin)) {
				Log.info("Set MEQ carSpeedMax - '"+ carSpeedMax+"'");
				this.meqCarSpeedMax.clear();
				this.meqCarSpeedMax.sendKeys(carSpeedMax);
			}
		}

		if (carWindAngleMin != null && carWindAngleMin != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqCarWindAngleMin)) {
				Log.info("Set MEQ carWindAngleMin - '"+ carWindAngleMin+"'");
				this.meqCarWindAngleMin.clear();
				this.meqCarWindAngleMin.sendKeys(carWindAngleMin);
			}
		}

		if (carWindAngleMax != null && carWindAngleMax != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqCarWindAngleMax)) {
				Log.info("Set MEQ carWindAngleMax - '"+ carWindAngleMax+"'");
				this.meqCarWindAngleMax.clear();
				this.meqCarWindAngleMax.sendKeys(carWindAngleMax);
			}
		}

		if (dBScanSpatialScale != null && dBScanSpatialScale != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqDBScanSpatialScale)) {
				Log.info("Set MEQ DBScanSpatialScale - '"+ dBScanSpatialScale+"'");
				this.meqDBScanSpatialScale.clear();
				this.meqDBScanSpatialScale.sendKeys(dBScanSpatialScale);
			}
		}

		if (minClusterSize != null && minClusterSize != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqMinClusterSize)) {
				Log.info("Set MEQ minClusterSize - '"+ minClusterSize+"'");
				this.meqMinClusterSize.clear();
				this.meqMinClusterSize.sendKeys(minClusterSize);
			}
		}

		if (backgroundFilterThreshold != null && backgroundFilterThreshold != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqBackgroundFilterThreshold)) {
				Log.info("Set MEQ backgroundFilterThreshold - '"+ backgroundFilterThreshold+"'");
				this.meqBackgroundFilterThreshold.clear();
				this.meqBackgroundFilterThreshold.sendKeys(backgroundFilterThreshold);
			}
		}

		if (pPMTriggerThreshold != null && pPMTriggerThreshold != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqPPMTriggerThreshold)) {
				Log.info("Set MEQ PPMTriggerThreshold - '"+ pPMTriggerThreshold+"'");
				this.meqPPMTriggerThreshold.clear();
				this.meqPPMTriggerThreshold.sendKeys(pPMTriggerThreshold);
			}
		}

		if (accelerationMax != null && accelerationMax != "") {
			if (WebElementExtender.isElementPresentAndDisplayed(this.meqAccelerationMax)) {
				Log.info("Set MEQ accelerationMax - '"+ accelerationMax+"'");
				this.meqAccelerationMax.clear();
				this.meqAccelerationMax.sendKeys(accelerationMax);
			}
		}

		if(justDBScan != this.meqJustDBScan.isSelected()){
			Log.info("Select MEQ JustDBScan - '"+ justDBScan +"'");
			this.meqJustDBScan.click();
		}

		clickOnOkButton();
	}

	public void inputLatLong(String latitude, String longitude){
		Log.method("inputLatLong", latitude, longitude);
		this.inputLocationLat.clear();
		this.inputLocationLong.clear();

		Log.info("Set latitude - '"+latitude+"'");
		this.inputLocationLat.sendKeys(latitude);
		Log.info("Set longitude - '"+longitude+"'");
		this.inputLocationLong.sendKeys(longitude);
	}


	public void selectOnLatLong(int xOffset, int yOffset){
		Log.method("selectOnLatLong", xOffset, yOffset);
		String CANVAS_X_PATH = "//*[@id=\"map\"]/div/canvas";
		latLongSelectionControl.waitForModalDialogOpen()
		.switchMode(ControlMode.MapInteraction)
		.waitForMapImageLoad()
		.selectLatLong(CANVAS_X_PATH, xOffset, yOffset);
		latLongSelectionControl.switchMode(ControlMode.Default);
	}

	public void clickOnLatLongCancelBtn(){
		Log.method("clickOnLatLongCancelBtn");
		latLongSelectionControl.clickCancelButton()
		.waitForModalDialogToClose();
	}

	public void clickOnLatLongOkBtn(){
		Log.method("clickOnLatLongOkBtn");
		latLongSelectionControl.clickOkButton()
		.waitForModalDialogToClose();
	}

	public boolean findExistingLocationAndClickEdit(String customerName, String locationName){
		Log.method("findExistingLocationAndClickEdit", customerName, locationName);
		return editExistingLocation(customerName, locationName, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, true, true);
	}

	public boolean editExistingLocation(String customerName,
			String locationName, String newLocationName, boolean checkForError) {
		Log.method("editExistingLocation", customerName, locationName,
				newLocationName, checkForError);
		return editExistingLocation(customerName, locationName,
				newLocationName, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, false,
				checkForError);
	}

	public boolean findExistingLocation(String customerName, String locationName) {
		Log.method("findExistingLocation", customerName, locationName);
		Log.info(String.format("Find Location '%s', customer = '%s'",
				locationName, customerName));
		setPagination(PAGINATIONSETTING_100);
		this.clearSearchFieldUsingSpace();   // clear any previous entries in search.

		this.waitForAJAXCallsToComplete();
		this.searchTable(locationName);
		if (this.searchHasNoMatchingRecords()) {
			// revert back search field.
			this.clearSearchField();
			return false;
		}

		String customerNameXPath;
		String locationNameXPath;

		WebElement customerNameCell;
		WebElement locationNameCell;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";

			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));
			locationNameCell = getTable().findElement(By.xpath(locationNameXPath));

			Log.info(String.format("Looking for row - [Customer=%s], [Location=%s]", customerName, locationName));
			Log.info(String.format("Found row - [Customer=%s], [Location=%s]", customerNameCell.getText().trim(),
					locationNameCell.getText().trim()));

			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)
					&& (locationNameCell.getText().trim()).equalsIgnoreCase(locationName)) {
				Log.info("Found entry at row=" + rowNum);
				// revert back search field.
				this.clearSearchField();
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		// revert back search field.
		this.clearSearchField();
		Log.error(String.format("Location not found: '%s', customer = '%s'",
				locationName, customerName));
		return false;
	}

	public boolean editPDExistingLocation(String customerName,
			String locationName, String newLocationName) {
		Log.method("editPDExistingLocation", customerName, locationName,
				newLocationName);
		return this.editExistingLocation(customerName, locationName,
				newLocationName, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null);
	}

	public boolean editPDExistingLocation(String customerName,
			String locationName, String newLocationName, String latValue,
			String longValue) {
		Log.method("editPDExistingLocation", customerName, locationName,
				newLocationName, latValue, longValue);
		return this.editExistingLocation(customerName, locationName,
				newLocationName, latValue, longValue, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null);
	}

	public boolean editPDExistingLocation(String customerName,
			String locationName, String newLocationName, String latValue,
			String longValue, String newEthMthMin, String newEthMthMax) {
		Log.method("editPDExistingLocation", customerName, locationName,
				newLocationName, latValue, longValue, newEthMthMin,
				newEthMthMax);
		return this.editExistingLocation(customerName, locationName,
				newLocationName, latValue, longValue, newEthMthMin,
				newEthMthMax, null, null, null, null, null, null, null, null,
				null, null, null, null);
	}

	public boolean editExistingLocation(String customerName,
			String locationName, String newLocationName, String latValue,
			String longValue, String newEthMthMin, String newEthMthMax,
			String newSurMinAmp, String newRankingMinAmp, String newPsFilter,
			String newTop10PS, String newTop25PS, String newTop50PS,
			String newDbScanRd, String newMinClusterSz,
			String newMaxClusterScale, String newExpansionPower,
			String newInflationPower, String newPercentile) {
		Log.method("editExistingLocation", customerName, locationName,
				newLocationName, latValue, longValue, newEthMthMin,
				newEthMthMax, newSurMinAmp, newRankingMinAmp, newPsFilter,
				newTop10PS, newTop25PS, newTop50PS, newDbScanRd,
				newMinClusterSz, newMaxClusterScale, newExpansionPower,
				newInflationPower, newPercentile);
		return editExistingLocation(customerName, locationName,
				newLocationName, latValue, longValue, newEthMthMin,
				newEthMthMax, newSurMinAmp, newRankingMinAmp, newPsFilter,
				newTop10PS, newTop25PS, newTop50PS, newDbScanRd,
				newMinClusterSz, newMaxClusterScale, newExpansionPower,
				newInflationPower, newPercentile, false, true);
	}

	public boolean editExistingLocation(String customerName,
			String locationName, String newLocationName, String latValue,
			String longValue, String newEthMthMin, String newEthMthMax,
			String newSurMinAmp, String newRankingMinAmp, String newPsFilter,
			String newTop10PS, String newTop25PS, String newTop50PS,
			String newDbScanRd, String newMinClusterSz,
			String newMaxClusterScale, String newExpansionPower,
			String newInflationPower, String newPercentile,
			boolean openEditorOnly, boolean checkForError) {
		Log.method("editExistingLocation", customerName, locationName,
				newLocationName, latValue, longValue, newEthMthMin,
				newEthMthMax, openEditorOnly, newSurMinAmp, newRankingMinAmp,
				newPsFilter, newTop10PS, newTop25PS, newTop50PS, newDbScanRd,
				newMinClusterSz, newMaxClusterScale, newExpansionPower,
				newInflationPower, newPercentile, checkForError);
		Log.info(String.format("Edit Location '%s', customer = '%s'",
				locationName, customerName));
		setPagination(PAGINATIONSETTING_100);
		this.clearSearchFieldUsingSpace();		// clear any previous entries in search.

		this.searchTable(locationName);
		if (this.searchHasNoMatchingRecords()) {
			// revert back search field.
			this.clearSearchField();
			return false;
		}

		String customerNameXPath;
		String locationNameXPath;
		String actionEditXPath;

		WebElement customerNameCell;
		WebElement locationNameCell;
		WebElement actionEditCell;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";

			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));
			locationNameCell = getTable().findElement(By.xpath(locationNameXPath));

			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)
					&& (locationNameCell.getText().trim()).equalsIgnoreCase(locationName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a";
				actionEditCell = getTable().findElement(By.xpath(actionEditXPath));
				Log.info("Found entry at row=" + rowNum);

				actionEditCell.click();
				this.waitForEditPageLoad();

				if(openEditorOnly){
					return true;
				}

				if (this.inputLocationDesc != null) {
					Log.info("Set location desc - '"+newLocationName+"'");
					this.inputLocationDesc.clear();
					this.inputLocationDesc.sendKeys(newLocationName);
				}

				if (latValue != null && latValue != "") {
					if (this.inputLocationLat != null) {
						Log.info("Set location latitude - '"+latValue+"'");
						this.inputLocationLat.clear();
						this.inputLocationLat.sendKeys(latValue);
					}
				}

				if (longValue != null && longValue != "") {
					if (this.inputLocationLong != null) {
						Log.info("Set location latitude - '"+longValue+"'");
						this.inputLocationLong.clear();
						this.inputLocationLong.sendKeys(longValue);
					}
				}

				if (newSurMinAmp != null && newSurMinAmp != "") {
					Log.info("Set Survey Min Amp - '" + newSurMinAmp + "'");
					this.surMinAmp.clear();
					this.surMinAmp.sendKeys(newSurMinAmp);
				}

				if (newRankingMinAmp != null && newRankingMinAmp != "") {
					Log.info("Set Ranking Min Amp - '" + newRankingMinAmp + "'");
					this.rankingMinAmp.clear();
					this.rankingMinAmp.sendKeys(newRankingMinAmp);
				}

				if (newPsFilter != null && newPsFilter != "") {
					Log.info("Set PS Filter - '" + newPsFilter + "'");
					this.psFilter.clear();
					this.psFilter.sendKeys(newPsFilter);
				}
				if (newTop10PS != null && newTop10PS != "") {
					Log.info("Set Top 10% PS - '" + newTop10PS + "'");
					this.top10PS.clear();
					this.top10PS.sendKeys(newTop10PS);
				}

				if (newTop25PS != null && newTop25PS != "") {
					Log.info("Set Top 25% PS - '" + newTop25PS + "'");
					this.top25PS.clear();
					this.top25PS.sendKeys(newTop25PS);
				}

				if (newTop50PS != null && newTop50PS != "") {
					Log.info("Set Top 50% PS - '" + newTop50PS + "'");
					this.top50PS.clear();
					this.top50PS.sendKeys(newTop50PS);
				}
				if (newDbScanRd != null && newDbScanRd != "") {
					Log.info("Set DBScan Radius - '" + newDbScanRd + "'");
					this.dbScanRd.clear();
					this.dbScanRd.sendKeys(newDbScanRd);
				}

				if (newMinClusterSz != null && newMinClusterSz != "") {
					Log.info("Set Min Cluster Size - '" + newMinClusterSz + "'");
					this.minClusterSz.clear();
					this.minClusterSz.sendKeys(newMinClusterSz);
				}

				if (newMaxClusterScale != null && newMaxClusterScale != "") {
					Log.info("Set Max Cluster Scale - '" + newMaxClusterScale + "'");
					this.maxClusterScale.clear();
					this.maxClusterScale.sendKeys(newMaxClusterScale);
				}

				if (newExpansionPower != null && newExpansionPower != "") {
					Log.info("Set Expansion Power - '" + newExpansionPower + "'");
					this.expansionPower.clear();
					this.expansionPower.sendKeys(newExpansionPower);
				}

				if (newInflationPower != null && newInflationPower != "") {
					Log.info("Set Inflation Power - '" + newInflationPower + "'");
					this.inflationPower.clear();
					this.inflationPower.sendKeys(newInflationPower);
				}

				if (newPercentile != null && newPercentile != "") {
					Log.info("Set Percentile - '" + newPercentile + "'");
					this.percentile.clear();
					this.percentile.sendKeys(newPercentile);
				}

				if (newEthMthMin != null && newEthMthMin != "") {
					List<WebElement> optionsMIN = this.ethMthMinUnit.findElements(By.tagName("option"));
					for (WebElement option : optionsMIN) {
						if ((newEthMthMin).equalsIgnoreCase(option.getText().trim())) {
							Log.info("Select option - '"+option+"'");
							option.click();
							break;
						}
					}
				}

				if (newEthMthMax != null && newEthMthMax != "") {
					List<WebElement> optionsMAX = this.ethMthMaxUnit.findElements(By.tagName("option"));
					for (WebElement option : optionsMAX) {
						if ((newEthMthMax).equalsIgnoreCase(option.getText().trim())) {
							Log.info("Select option - '"+newEthMthMax+"'");
							option.click();
							break;
						}
					}
				}

				String curURL = driver.getCurrentUrl();
				this.btnOK.click();
				Log.info("ok button clicked");
				if(!checkForError){
					Log.info("Not checking for errors");
					if (isElementPresent(this.panelDuplicationErrorXPath)) {
						// We are still on the new locations page. Return.
						Log.info("Error on page. Returning to caller.");
						return true;
					}

					// Redirected to Manage location page. Revert back search field and return.
					this.clearSearchField();
					return true;
				}
				if (newLocationName.equalsIgnoreCase("")) {
					// Required field validation message should be shown.
					this.waitUntilPresenceOfElementLocated(labelLocDescErrorID);
					this.labelLocDescError = this.driver.findElement(By.id(labelLocDescErrorID));
					if (driver.getCurrentUrl().equalsIgnoreCase(curURL)
							&& this.labelLocDescError.getText().equalsIgnoreCase(REQUIRED_FIELD_VAL_MESSAGE))
						return false;
				}

				if (isElementPresent(this.panelDuplicationErrorXPath)) {
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText()
							.equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						return false;
					}
				}

				// revert back search field.
				this.clearSearchField();
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		// revert back search field.
		this.clearSearchField();
		Log.error(String.format("Location not found: '%s', customer = '%s'",
				locationName, customerName));
		return false;

	}

	public String getSelectedPoint(){
		Log.method("getSelectedPoint");
		return getSelectedPoint(SECONDS_10);
	}

	public String getSelectedPoint(int timeout){
		Log.method("getSelectedPoint", timeout);
		latLongSelectionControl.waitForModalDialogOpen()
		.switchMode(ControlMode.MapInteraction);

		String pt = null;
		try{
			pt = (new WebDriverWait(driver,timeout)).until(new ExpectedCondition<String>(){
				public String apply(WebDriver d){
					String text = null;
					try{
						text = selectedPoint.getText();
						if(text.indexOf("[")>=0){
							text = text.substring(text.indexOf("[")+1,text.indexOf("]"));
						}else{
							text = null;
						}
					}catch(Exception e){
						text = null;
					}
					return text;
				}
			});
		}catch(Exception e){
			pt = null;
			Log.warn(e.toString() + "Selected point is "+pt+"?");
		}

		latLongSelectionControl
		.switchMode(ControlMode.Default);
		return pt;
	}

	public WebElement getBtnAddNewLocation() {
		return this.btnAddNewLocation;
	}

	public WebElement getBtnCancel() {
		return this.btnCancel;
	}

	public String getLocationLatitudeText() {
		return this.inputLocationLat.getAttribute("value");
	}

	public String getLocationLongitudeText() {
		return this.inputLocationLong.getAttribute("value");
	}

	public String getLocationLatitudeError() {
		return this.labelLatValueError.getText();
	}

	public String getLocationLongitudeError() {
		return this.labelLongValueError.getText();
	}

	public String getLocationDescriptionError(){
		return this.labelLocDescError.getText();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void clickOnAddNewLocationBtn() {
		Log.clickElementInfo("Add New Location");
		this.btnAddNewLocation.click();
		this.waitForNewPageLoad();
	}

	public void clickOnFirstEditLocationBtn() {
		Log.clickElementInfo("Edit Location");
		this.btnEditLocation.click();
	}

	public void clickOnCancelBtn() {
		Log.clickElementInfo("Cancel");
		this.btnCancel.click();
	}

	public void clickOnOkBtn() {
		Log.clickElementInfo("Ok");
		this.btnOk.click();
	}

	public void clickOnLatLongSelectorBtn() {
		Log.clickElementInfo("Lat/Long Selector");
		this.latLongSelectorBtn.click();
	}

	public WebElement getTheadLocation() {
		return this.theadLocation;
	}

	public WebElement getEthMthRtoLbl() {
		return ethMthRtioLbl;
	}

	public List<String> getLocationList(boolean allPages, int paginationSize) {
		Log.method("getLocationList", allPages, paginationSize);
		List<String> locationList = new ArrayList<String>();

		String pageSizeStr = String.valueOf(paginationSize);
		setPagination(pageSizeStr);
		waitForPageLoad();

		String locationXPath;
		WebElement locationCell;

		int rowSize = rows.size();
		int loopCount = 0;
		if (rowSize < Integer.parseInt(pageSizeStr))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(pageSizeStr);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";
			locationCell = getTable().findElement(By.xpath(locationXPath));

			locationList.add(locationCell.getText().trim());

			if (rowNum == Integer.parseInt(pageSizeStr) && !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(pageSizeStr))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(pageSizeStr);

				rowNum = 0;
			}
		}
		return locationList;
	}

	public boolean searchLocation(String customer, String locationName) {
		Log.method("searchLocation", customer, locationName);
		this.searchTable(locationName);
		return findExistingLocation(customer, locationName);
	}

	public boolean isDuplicateLocMsgPresent() {
		Log.method("isDuplicateLocMsgPresent");
		return getElementText(this.liDuplicateMsg).equals(STRDuplicateLocMsg);
	}

	public boolean isStandardMinAmpShowing(){
		return WebElementExtender.isElementPresentAndDisplayed(stdMinAmp);
	}

	public boolean isOperatorMinAmpShowing(){
		return WebElementExtender.isElementPresentAndDisplayed(opdMinAmp);
	}

	public boolean isRapidResponseMinAmpShowing(){
		return WebElementExtender.isElementPresentAndDisplayed(RRMinAmp);
	}

	@Override
	public void open(){
		super.open();
		waitForPageLoad();
	}

	@Override
	public void waitForPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isPageTitleMatch(d.getTitle(),STRPageContentText);
			}
		});
	}

	public void waitForNewPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isPageTitleMatch(d.getTitle(),STRNewPageContentText);
			}
		});
	}

	public void waitForEditPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isPageTitleMatch(d.getTitle(),STREditPageContentText);
			}
		});
	}

	public void editSurveyMinAmplitude(String customerName, String locationName, String surMinAmp){
		findExistingLocationAndClickEdit(customerName, locationName);
		setSurveyMinAmp(surMinAmp);
		clickOnOkBtn();
	}

	public void editRankingMinAmplitude(String customerName, String locationName, String surMinAmp){
		findExistingLocationAndClickEdit(customerName, locationName);
		setRankingMinAmp(surMinAmp);
		clickOnOkBtn();
	}

	public void editLocationPSFilterThreshold(String customerName, String locationName, String psFilterThreshold){
		findExistingLocationAndClickEdit(customerName, locationName);
		setPsFilter(psFilterThreshold);
		clickOnOkBtn();
	}

	public void editLocationTopPSValues(String customerName, String locationName, String top10PS, String top25PS, String top50PS){
		findExistingLocationAndClickEdit(customerName, locationName);
		setTop10Ps(top10PS);
		setTop25Ps(top25PS);
		setTop50Ps(top50PS);
		clickOnOkBtn();
	}
	public void editEthaneToMethaneMinRatio(String customerName, String locationName, String newEthMthMin){
		editEthaneToMethaneRatio(customerName, locationName, newEthMthMin, null);
	}

	public void editEthaneToMethaneMaxRatio(String customerName, String locationName, String newEthMthMax){
		editEthaneToMethaneRatio(customerName, locationName, null, newEthMthMax);
	}

	public void editEthaneToMethaneRatio(String customerName, String locationName, String newEthMthMin, String newEthMthMax){
		findExistingLocationAndClickEdit(customerName, locationName);
		if(newEthMthMax!=null&&!newEthMthMax.isEmpty()){
			Log.info("Set Ethane to Methane Ratio Max % - '" + newEthMthMax + "'");
			this.ethMthMaxUnit.clear();
			this.ethMthMaxUnit.sendKeys(newEthMthMax);
		}
		if(newEthMthMin!=null&&!newEthMthMin.isEmpty()){
			Log.info("Set Ethane to Methane Ratio Min % - '" + newEthMthMin + "'");
			this.ethMthMinUnit.clear();
			this.ethMthMinUnit.sendKeys(newEthMthMin);
		}
		clickOnOkBtn();
	}

	public void editFEQLocationParameters(String customerName, String locationName, String shapeCorrelationMin, String peakIDXBuffer, String peakSEPDistanceScale,
			String widthMin, String widthMax, String variationMax, String carSpeedMin, String carSpeedMax, String carWindAngleMin, String carWindAngleMax,
			String dBScanSpatialScale, String minClusterSize, String backgroundFilterThreshold, String pPMTriggerThreshold, String accelerationMax, boolean eQJustDBScan){

		findExistingLocationAndClickEdit(customerName, locationName);

		this.waitForEditPageLoad();

		// wait necessary for FEQ Location parameters get added correctly.
		inputFeqParameters(shapeCorrelationMin, peakIDXBuffer, peakSEPDistanceScale, widthMin, widthMax, variationMax, carSpeedMin,
				carSpeedMax, carWindAngleMin, carWindAngleMax, dBScanSpatialScale, minClusterSize, backgroundFilterThreshold, pPMTriggerThreshold,
				accelerationMax, eQJustDBScan);
	}

	public void editMEQLocationParameters(String customerName, String locationName, String shapeCorrelationMin, String peakIDXBuffer, String peakSEPDistanceScale,
			String widthMin, String widthMax, String variationMax, String carSpeedMin, String carSpeedMax, String carWindAngleMin, String carWindAngleMax,
			String dBScanSpatialScale, String minClusterSize, String backgroundFilterThreshold, String pPMTriggerThreshold, String accelerationMax, boolean eQJustDBScan){

		findExistingLocationAndClickEdit(customerName, locationName);

		this.waitForEditPageLoad();

		// wait necessary for FEQ Location parameters get added correctly.
		inputMeqParameters(shapeCorrelationMin, peakIDXBuffer, peakSEPDistanceScale, widthMin, widthMax, variationMax, carSpeedMin,
				carSpeedMax, carWindAngleMin, carWindAngleMax, dBScanSpatialScale, minClusterSize, backgroundFilterThreshold, pPMTriggerThreshold,
				accelerationMax, eQJustDBScan);
	}

	public boolean isSurveyMinAmpShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(surMinAmp);
	}

	public boolean isRankingMinAmpShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(rankingMinAmp);
	}

	public boolean isTop10PsShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(top10PS);
	}

	public boolean isTop25PsShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(top25PS);
	}

	public boolean isTop50PsShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(top50PS);
	}

	public boolean isFilterPsShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(psFilter);
	}

	public boolean isDbScanRadiusShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(dbScanRd);
	}

	public boolean isMinClusterSizeShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(minClusterSz);
	}

	public boolean isMaxClusterScaleShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(maxClusterScale);
	}

	public boolean isExpansionPowerShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(expansionPower);
	}

	public boolean isInflationPowerShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(inflationPower);
	}

	public boolean isPercentileShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(percentile);
	}

	public boolean isJustDBScanShowing() {
		return WebElementExtender.isElementPresentAndDisplayed(justDBScan);
	}
}


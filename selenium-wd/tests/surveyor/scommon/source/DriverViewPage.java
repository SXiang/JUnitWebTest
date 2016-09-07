package surveyor.scommon.source;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.WebElementExtender;
import groovy.swing.factory.ActionFactory;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.ActionBuilder;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class DriverViewPage extends BaseDrivingViewPage {
	private static final float DEFAULT_MIN_AMPLITUDE = -1.0F;

	public static final String STRURLPath = "/Live/Driver?address=https%3A%2F%2Flocalhost&port=5600&serialNumber={0}";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.Constant_Live);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.Dialog_MapView);

	public enum SurveyTime {
		Day, Night
	}

	public enum SolarRadiation {
		Overcast, Moderate, Strong
	}

	public enum Wind {
		Calm, Light, Strong
	}

	public enum CloudCover {
		LessThan50, MoreThan50
	}

	public enum SurveyType {
		Standard, RapidResponse, Operator, Manual, Assessment
	}

	private Map<String, String> data;
	private int timeout = 15;

	@FindBy(id = "mode_start_survey_element")
	private WebElement startSurveyButtonDivElement;

	@FindBy(id = "mode_start_eq_survey_element")
	private WebElement startEQSurveyButtonDivElement;

	@FindBy(id = "mode_stop_survey_element")
	private WebElement stopSurveyButtonDivElement;
	
	@FindBy(id = "mode_shutdown_analyzer_element")
	private WebElement systemShutdownButtonDivElement;
	
	@FindBy(id = "mode_start_isotopic_capture_element")
	private WebElement startIsotopicCaptureButtonDivElement;

	@FindBy(id = "mode_cancel_capture")
	private WebElement cancelCaptureButton;

	@FindBy(id = "mode_cancel_capture_element")
	private WebElement cancelCaptureButtonDivElement;

	@FindBy(id = "mode_start_reference_element")
	private WebElement refBottleMeasButtonDivElement;
	
	@FindBy(id = "mode_start_survey")
	@CacheLookup
	private WebElement startSurveyButton;

	@FindBy(id = "mode_start_eq_survey")
	@CacheLookup
	private WebElement startEQSurveyButton;

	@FindBy(id = "mode_shutdown_analyzer")
	@CacheLookup
	private WebElement systemShutdownButton;

	@FindBy(id = "mode_shutdown_confirm")
	@CacheLookup
	private WebElement shutdownConfirmButton;

	@FindBy(id = "mode_shutdown_cancel")
	@CacheLookup
	private WebElement shutdownCancelButton;

	@FindBy(id = "mode_stop_survey")
	@CacheLookup
	private WebElement stopSurveyButton;

	@FindBy(id = "mode_start_isotopic_capture")
	@CacheLookup
	private WebElement startIsotopicCaptureButton;

	@FindBy(id = "mode_start_reference")
	@CacheLookup
	private WebElement refBottleMeasButton;

	@FindBy(id = "btn_cancel_annotation")
	@CacheLookup
	private WebElement fieldNotesCancel;
	
	@FindBy(id = "btn_close_annotation")
	@CacheLookup
	private WebElement fieldNotesClose;

	@FindBy(css = "a[href='http://openlayers.org/']")
	@CacheLookup
	private WebElement imageData;

	@FindBy(id = "survey_wind_calm")
	@CacheLookup
	private WebElement calm;

	@FindBy(id = "survey_time_day")
	@CacheLookup
	private WebElement day;

	@FindBy(id = "btn_delete_annotation")
	@CacheLookup
	private WebElement fieldNotesDelete;

	@FindBy(css = "button[title='Attributions']")
	@CacheLookup
	private WebElement i;

	@FindBy(id = "survey_wind_light")
	@CacheLookup
	private WebElement light;

	@FindBy(id = "survey_type_manual")
	@CacheLookup
	private WebElement manual;

	@FindBy(id = "manual_min_amplitude")
	@CacheLookup
	private WebElement manualModeMinAmp;

	@FindBy(id = "survey_radiation_moderate")
	@CacheLookup
	private WebElement moderate;

	@FindBy(id = "survey_cloud_cover_lessThan50")
	@CacheLookup
	private WebElement cloudCoverLessThan50;

	@FindBy(id = "survey_cloud_cover_moreThan50")
	@CacheLookup
	private WebElement cloudCoverMoreThan50;

	@FindBy(id = "survey_time_night")
	@CacheLookup
	private WebElement night;

	@FindBy(id = "btn_survey_warning_ok")
	@CacheLookup
	private WebElement ok1;

	@FindBy(id = "btn_survey_start_warning_ok")
	@CacheLookup
	private WebElement ok2;

	@FindBy(id = "survey_type_operator")
	@CacheLookup
	private WebElement operator;

	@FindBy(id = "survey_type_assessment")
	@CacheLookup
	private WebElement assessment;

	@FindBy(id = "survey_radiation_overcast")
	@CacheLookup
	private WebElement overcast;

	@FindBy(id = "survey_type_rapid_response")
	@CacheLookup
	private WebElement rapidResponse;

	@FindBy(id = "btn_save_annotation")
	@CacheLookup
	private WebElement fieldNotesSave;

	@FindBy(id = "survey_type_standard")
	@CacheLookup
	private WebElement standard;

	@FindBy(id = "survey_start_survey")
	@CacheLookup
	private WebElement startSurvey;

	@FindBy(id = "survey_radiation_strong")
	@CacheLookup
	private WebElement radiationStrong;

	@FindBy(id = "survey_wind_strong")
	@CacheLookup
	private WebElement windStrong;

	@FindBy(css = "a[class='ol-attribution-bing-tos']")
	@CacheLookup
	private WebElement termsOfUse;

	@FindBy(id = "anno_input")
	@CacheLookup
	private WebElement fieldNotesTextField;

	@FindBy(id = "survey_modal_tag")
	@CacheLookup
	private WebElement tagSurvey;
	
	@FindBy(id = "annotation_modal")
	@CacheLookup
	private WebElement fieldNotesModalDialog;
	
	@FindBy(id = "manual_survey_params")
	@CacheLookup
	private WebElement manualSurveySection;

	@FindBy(id = "btn_survey_warning_ok")
	@CacheLookup
	private WebElement surveyDurationWarningDialogOkButton;

	@FindBy(id = "btn_survey_start_warning_ok")
	@CacheLookup
	private WebElement failedToStartSurveyDialogOkButton;

	@FindBy(id = "survey_duration_warning")
	@CacheLookup
	protected WebElement divSurveyDurationWarning;

	@FindBy(id = "survey_start_warning")
	@CacheLookup
	protected WebElement divSurveyStartWarning;
	
	@FindBy(id = "survey_warning_message")
	@CacheLookup
	protected WebElement spanSurveyWarningMessage;
	
	@FindBy(id = "survey_start_warning_message")
	@CacheLookup
	protected WebElement spanSurveyFailedToStartMessage;
	
	@FindBy(id = "eq_mode_warning")
	@CacheLookup
	protected WebElement eqModeDialog;

	@FindBy(id = "eq_warning_message")
	@CacheLookup
	protected WebElement eqModeDialogMessage;

	@FindBy(id = "menu_layer")
	@CacheLookup
	protected WebElement modeMenu;

	@FindBy(id = "start_survey_modal")
	protected WebElement startSurveyModalDialog;
	
	@FindBy(xpath = "//*[@id='button_close_survey_modal']/..")
	protected WebElement closeSurveyModalButton;
	
	@FindBy(id = "featureinfo_modal")
	protected WebElement featureInfoModalDialog;
	
	@FindBy(id = "feature_info")
	protected WebElement featureInfoText;

	@FindBy(id = "btn_addupdate_annotation")
	protected WebElement addUpdateNoteButton;
	
	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public DriverViewPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, getPageFullUrl(baseURL));
		Log.info("\nThe DriverView Page URL is: " + this.strPageURL);
	}

	private static String getPageFullUrl(String baseURL) {
		String fullUrl = null;
		try {
			fullUrl = baseURL + STRURLPath.replace("{0}", ActionBuilder.createTestEnvironmentAction().getWorkingAnalyzerSerialNumber());
		} catch (Exception e) {
			Log.error(String.format("ERROR when getting workingAnalyzerSerialNumber. EXCEPTION - %s", ExceptionUtility.getStackTraceString(e)));
		}
		return fullUrl;
	}
	
	@Override
	public void open() {
		// build the page url each time as the Analyzer in use could have changed.
		driver.get(getPageFullUrl(this.strBaseURL));
	}

	public boolean checkIfAtDriverViewPage() {
		if (driver.getTitle().equalsIgnoreCase(STRPageTitle))
			return true;
		Log.warn("Current  page is not '"+STRPageTitle+"'");
		return false;
	}

	public DriverViewPage clickModeButton() {
		Log.clickElementInfo("Mode");
		this.modeButton.click();
		return this;
	}

	public DriverViewPage hideModeMenu() {
		clickModeButton();
		return this;
	}

	public DriverViewPage clickShutdownButton() {
		Log.clickElementInfo("System Shutdown");
		this.getSystemShutdownButton().click();
		return this;
	}

	public DriverViewPage clickShutdownConfirmButton() {
		Log.clickElementInfo("Confirm Shutdown");
		this.getShutdownConfirmButton().click();
		return this;
	}

	public DriverViewPage clickShutdownCancelButton() {
		Log.clickElementInfo("Cancel Shutdown");
		this.getShutdownCancelButton().click();
		return this;
	}

	public boolean isFieldNotesDialogShown() {
		return !this.fieldNotesModalDialog.getAttribute("class").contains("ng-hide");
	}
	
	public boolean isFeatureInfoDialogShown() {
		return !this.featureInfoModalDialog.getAttribute("class").contains("ng-hide");
	}
	
	public String getFeatureInfoDialogText() {
		return this.featureInfoText.getAttribute("value");
	}
	
	public WebElement getStartSurveyButton() {
		return this.startSurveyButton;
	}

	public boolean isStartSurveyButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.startSurveyButtonDivElement,"ng-cloak") ||
					this.startSurveyButtonDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isStartSurveyButtonEnabled() {
		return this.startSurveyButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public WebElement getStartEQSurveyButton() {
		return this.startEQSurveyButton;
	}

	public boolean isStartEQSurveyButtonEnabled() {
		return this.startEQSurveyButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public boolean isStartEQSurveyButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.startEQSurveyButtonDivElement,"ng-cloak") ||
					this.startEQSurveyButtonDivElement.getAttribute("class").contains("ng-hide"));
	}

	public WebElement getSystemShutdownButton() {
		return this.systemShutdownButton;
	}

	public boolean isSystemShutdownButtonEnabled() {
		return this.systemShutdownButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public boolean isSystemShutdownButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.systemShutdownButtonDivElement,"ng-cloak") ||
					this.systemShutdownButtonDivElement.getAttribute("class").contains("ng-hide"));
	}

	public WebElement getShutdownConfirmButton() {
		return this.shutdownConfirmButton;
	}

	public WebElement getShutdownCancelButton() {
		return this.shutdownCancelButton;
	}

	public WebElement getStopDrivingSurveyButton() {
		return this.stopSurveyButton;
	}

	public boolean isStopDrivingSurveyButtonEnabled() {
		return this.stopSurveyButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public boolean isStopDrivingSurveyButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.stopSurveyButtonDivElement,"ng-cloak") ||
					this.stopSurveyButtonDivElement.getAttribute("class").contains("ng-hide"));
	}

	public WebElement getStartIsotopicCaptureButton() {
		return this.startIsotopicCaptureButton;
	}

	public boolean isStartIsotopicCaptureButtonEnabled() {
		return this.startIsotopicCaptureButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public boolean isStartIsotopicCaptureButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.startIsotopicCaptureButtonDivElement,"ng-cloak") ||
					this.startIsotopicCaptureButtonDivElement.getAttribute("class").contains("ng-hide"));
	}

	public WebElement getCancelCaptureButton() {
		return this.cancelCaptureButton;
	}

	public boolean isCancelCaptureButtonEnabled() {
		return this.cancelCaptureButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public boolean isCancelCaptureButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.cancelCaptureButtonDivElement,"ng-cloak") ||
					this.cancelCaptureButtonDivElement.getAttribute("class").contains("ng-hide"));
	}

	public WebElement getRefBottleMeasButton() {
		return this.refBottleMeasButton;
	}

	public boolean isRefBottleMeasButtonEnabled() {
		return this.refBottleMeasButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public boolean isRefBottleMeasButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.refBottleMeasButtonDivElement,"ng-cloak") ||
					this.refBottleMeasButtonDivElement.getAttribute("class").contains("ng-hide"));
	}
	
	public DriverViewPage clickSurveyDurationWarningDialogOkButton() {
		Log.info("Click Ok in survey duration warning dialog");
		this.surveyDurationWarningDialogOkButton.click();
		return this;
	}

	public DriverViewPage clickFailedToStartSurveyDialogOkButton() {
		Log.info("Click Ok in failed to start survey dialog");
		this.failedToStartSurveyDialogOkButton.click();
		return this;
	}
	
	/**
	 * Verifies that the survey warning dialog is shown.
	 */
	public boolean isSurveyDurationWarningDialogShown() {
		if (divSurveyDurationWarning != null && divSurveyDurationWarning.isDisplayed()) {
			return divSurveyDurationWarning.getAttribute("class").equalsIgnoreCase("cssFade");
		}
		return false;
	}

	/**
	 * Verifies that correct message is shown in Survey Duration warning dialog.
	 */
	public boolean isCorrectSurveyDurationWarningMessageShowing() {
		if (isSurveyDurationWarningDialogShown()) {
			String surveyStopInMessage = Resources.getResource(ResourceKeys.Survey_SurveyWillStopIn);
			String surveyMinutesAndMessage = Resources.getResource(ResourceKeys.Survey_MinutesAnd);
			String surveySecondsMessage = Resources.getResource(ResourceKeys.Survey_Seconds);
			String msgText = spanSurveyWarningMessage.getText();
			return msgText.contains(surveyStopInMessage) && msgText.contains(surveyMinutesAndMessage) &&
					msgText.contains(surveySecondsMessage);
		}
		return false;
	}

	/**
	 * Verifies that the survey start dialog is shown.
	 */
	public boolean isSurveyFailedToStartDialogShown() {
		if (divSurveyStartWarning != null && divSurveyStartWarning.isDisplayed()) {
			return divSurveyStartWarning.getAttribute("class").equalsIgnoreCase("cssFade");
		}
		return false;
	}

	/**
	 * Verifies that correct message is shown in Survey Duration warning dialog.
	 */
	public boolean isCorrectSurveyFailedToStartMessageShowing() {
		if (isSurveyFailedToStartDialogShown()) {
			String msgText = spanSurveyFailedToStartMessage.getText();
			return msgText.equals(Resources.getResource(ResourceKeys.Dialog_SurveyFailedtoStart));
		}
		return false;
	}
	
	public DriverViewPage hidePositionMenu() {
		clickPositionButton();
		return this;
	}

	/**
	 * Click on Calm Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickCalmButton() {
		Log.clickElementInfo("Calm");
		calm.click();
		return this;
	}

	/**
	 * Click on Cancel Button in Field notes.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickFieldNotesCancelButton() {
		fieldNotesCancel = driver.findElement(By.id("btn_cancel_annotation"));
		Log.clickElementInfo("Cancel", "in field Notes dialog");
		return this;
	}

	/**
	 * Click on Close Button in Field Notes.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickFieldNotesCloseButton() {
		fieldNotesClose.click();
		return this;
	}

	/**
	 * Click on image data Link.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickImageDataLink() {
		Log.clickElementInfo("Image data");
		imageData.click();
		return this;
	}

	/**
	 * Click on Day Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickDayButton() {
		Log.clickElementInfo("Day");
		day.click();
		return this;
	}

	/**
	 * Click on Delete Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickFieldNotesDeleteButton() {
		Log.clickElementInfo("Delete", "in Field Notes dialog");
		fieldNotesDelete.click();
		return this;
	}

	/**
	 * Click on I Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickIButton() {
		Log.clickElementInfo("I");
		i.click();
		return this;
	}

	/**
	 * Click on Light Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickLightButton() {
		Log.clickElementInfo("Light");
		light.click();
		return this;
	}

	/**
	 * Get Manual Button.
	 *
	 * @return the WebElement.
	 */
	public WebElement getManualButton() {
		return manual;
	}

	/**
	 * Click on Manual Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickManualButton() {
		Log.clickElementInfo("Manual");
		manual.click();
		return this;
	}

	/**
	 * Click on Moderate Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickModerateButton() {
		Log.clickElementInfo("Moderate");
		moderate.click();
		return this;
	}

	/**
	 * Click on Cloud Cover Less Than 50 Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickCloudCoverLessThan50Button() {
		Log.clickElementInfo("Cloud Cover Less Than 50");
		cloudCoverLessThan50.click();
		return this;
	}

	/**
	 * Click on Cloud Cover More Than 50 Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickCloudCoverMoreThan50Button() {
		Log.clickElementInfo("Cloud Cover More Than 50");
		cloudCoverMoreThan50.click();
		return this;
	}

	/**
	 * Click on Night Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickNightButton() {
		Log.clickElementInfo("Night");
		night.click();
		return this;
	}

	/**
	 * Click on Ok Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickOk1Button() {
		Log.clickElementInfo("Ok1");
		ok1.click();
		return this;
	}

	/**
	 * Click on Ok Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickOk2Button() {
		Log.clickElementInfo("Ok2");
		ok2.click();
		return this;
	}

	/**
	 * Click on Operator Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickOperatorButton() {
		Log.clickElementInfo("Operator");
		operator.click();
		return this;
	}

	/**
	 * Click on Overcast Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickOvercastButton() {
		Log.clickElementInfo("Overcast");
		overcast.click();
		return this;
	}

	/**
	 * Click on Rapid Response Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickRapidResponseButton() {
		Log.clickElementInfo("Rapid Response");
		rapidResponse.click();
		return this;
	}

	/**
	 * Click on Save Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickFieldNotesSaveButton() {
		fieldNotesSave = driver.findElement(By.id("btn_save_annotation")); 
		Log.clickElementInfo("Save Field Notes");
		fieldNotesSave.click();
		return this;
	}

	/**
	 * Click on Standard Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickStandardButton() {
		Log.clickElementInfo("Standard");
		standard.click();
		return this;
	}

	/**
	 * Click on Assessment Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickAssessmentButton() {
		Log.clickElementInfo("Assessment");
		assessment.click();
		return this;
	}

	/**
	 * Click on Start Survey Button inside the Survey modal dialog.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickStartSurvey() {
		Log.info("Clicking on StartSurvey button..");
		startSurvey.click();
		return this;
	}

	/**
	 * Gets the Start Survey Button from the Survey modal dialog.
	 *
	 * @return button element.
	 */
	public WebElement getStartSurveyButtonFromStartSurveyDialog() {
		return startSurvey;
	}

	/**
	 * Click on Start Survey Button in the Driver view page to open the modal
	 * Start Survey dialog.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickStartSurveyButton() {
		Log.clickElementInfo("Start Survey");
		this.startSurveyButton.click();
		return this;
	}

	/**
	 * Click on the close survey modal dialog.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage closeSurveyModalDialog() {
		this.closeSurveyModalButton.click();
		return this;
	}
	
	/**
	 * Click on Strong Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickRadiationStrongButton() {
		Log.clickElementInfo("Radiation Strong");
		radiationStrong.click();
		return this;
	}

	/**
	 * Click on Strong Button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickWindStrongButton() {
		Log.clickElementInfo("Wind Strong");
		windStrong.click();
		return this;
	}

	/**
	 * Click on Add/Update note button.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickOnAddUpdateNoteButton() {
		addUpdateNoteButton.click();
		return this;
	}
	
	/**
	 * Click on Terms Of Use Link.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage clickTermsOfUseLink() {
		Log.clickElementInfo("Terms of Use");
		termsOfUse.click();
		return this;
	}

	/**
	 * Set default value to Min Amp Text field.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage setMinAmpTextField() {
		return setMinAmpTextField(data.get("MIN_AMP"));
	}

	/**
	 * Set value to Min Amp Text field.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage setMinAmpTextField(String minAmpValue) {
		Log.info("Set Min Amp to '"+minAmpValue+"'");
		manualModeMinAmp.sendKeys(minAmpValue);
		return this;
	}

	/**
	 * Set value to Field notes field.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage setFieldNotesTextField(String fieldNotes) {
		Log.info(String.format("Adding fields notes text - %s", fieldNotes));
		fieldNotesTextField = driver.findElement(By.id("anno_input"));
		Log.info("Set field notes as '"+fieldNotes+"'");
		fieldNotesTextField.clear();
		fieldNotesTextField.sendKeys(fieldNotes);
		return this;
	}
	
	/**
	 * Set default value to tag field.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage setTagSurveyTextField() {
		return setTagSurveyTextField(data.get("TAG_SURVEY"));
	}

	/**
	 * Set value to Tag field.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage setTagSurveyTextField(String tag) {
		Log.info(String.format("Setting tag text - %s", tag));
		tagSurvey.clear();
		tagSurvey.sendKeys(tag);
		return this;
	}

	/**
	 * Get Tag field value.
	 *
	 * @return the tag field value.
	 */
	public String getTagSurveyTextField() {
		return tagSurvey.getAttribute("value");
	}

	/**
	 * Gets the survey tag from the Start Survey dialog.
	 *
	 * @return the survey tag value.
	 */
	public String getSurveyTagFromStartSurveyDialog() {
		openStartSurveyModalDialog();
		
		return this.getTagSurveyTextField();
	}

	/**
	 * Starts a survey with the specified values.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage startDrivingSurvey(String tag, SurveyTime surveyTime, SolarRadiation solarRadiation,
			Wind wind, CloudCover cloudCover, SurveyType surveyType) {
		return startDrivingSurvey(tag, surveyTime, solarRadiation, wind, cloudCover, surveyType, DEFAULT_MIN_AMPLITUDE);
	}
	
	/**
	 * Starts a survey with the specified values.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage startDrivingSurvey(String tag, SurveyTime surveyTime, SolarRadiation solarRadiation,
			Wind wind, CloudCover cloudCover, SurveyType surveyType, float minAmplitude) {
		this.waitForSignalRCallsToComplete();
		this.waitForStartSurveyButtonToBeVisible();
		openStartSurveyModalDialog();

		this.waitForSignalRCallsToComplete();
		this.setTagSurveyTextField(tag);
		
		selectSurveyTimeAndRadiationInSurveyDialog(surveyTime, solarRadiation, cloudCover);
		selectWindInSurveyDialog(wind);
		selectSurveyTypeInSurveyDialog(surveyType);
		
		if (surveyType.equals(SurveyType.Manual)) {
			this.waitForMinAmpTextFieldToShow();
			this.setMinAmpTextField(String.valueOf(minAmplitude));
			this.waitForStartSurveyButtonToShow();
		}

		this.clickStartSurvey();
		this.waitForPageToLoad();

		return this;
	}

	/**
	 * Starts a survey with the specified values.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage startEQDrivingSurvey(String tag, SurveyTime surveyTime, SolarRadiation solarRadiation,
			Wind wind, CloudCover cloudCover) {
		openStartEQSurveyModalDialog();

		this.setTagSurveyTextField(tag);
		
		selectSurveyTimeAndRadiationInSurveyDialog(surveyTime, solarRadiation, cloudCover);
		selectWindInSurveyDialog(wind);

		this.clickStartSurvey();
		this.waitForPageToLoad();

		return this;
	}

	private void selectSurveyTypeInSurveyDialog(SurveyType surveyType) {
		// Until SurveyType is selected the StartSurvey button should NOT be displayed.
		// NOTE: This check will NOT always work correctly as this button might be showing when multiple tests are run one after the other. 
		//assertTrue(this.getStartSurveyButtonFromStartSurveyDialog().isDisplayed() == false);
		
		Log.info("Selecting surveyType - '"+surveyType+"'");		
		switch (surveyType) {
		case Manual:
			this.clickManualButton();
			break;
		case Operator:
			this.clickOperatorButton();
			break;
		case RapidResponse:
			this.clickRapidResponseButton();
			break;
		case Standard:
			this.clickStandardButton();
			break;
		case Assessment:
			this.clickAssessmentButton();
			break;
		default:
			break;
		}
	}

	private void selectWindInSurveyDialog(Wind wind) {
		Log.info("Selecting wind - '"+wind+"'");
		switch (wind) {
		case Calm:
			this.clickCalmButton();
			break;
		case Light:
			this.clickLightButton();
			break;
		case Strong:
			this.clickWindStrongButton();
			break;
		default:
			break;
		}
	}

	private void selectSurveyTimeAndRadiationInSurveyDialog(SurveyTime surveyTime, SolarRadiation solarRadiation, CloudCover cloudCover) {
		Log.info("Selecting surveyTime - '"+surveyTime+"'");
		switch (surveyTime) {
		case Day:
			selectDayInSurveyDialog(solarRadiation);
			break;
		case Night:
			selectNightInSurveyDialog(cloudCover);
			break;
		default:
			break;
		}
	}

	private void selectNightInSurveyDialog(CloudCover cloudCover) {
		this.clickNightButton();
		Log.info("Survey Time: Night selected.");
		// Cloud Cover option is valid only during Night time.
		Log.info("Selecting night in survey dialog - '"+cloudCover+"'");
		switch (cloudCover) {
		case LessThan50:
			this.clickCloudCoverLessThan50Button();
			break;
		case MoreThan50:
			this.clickCloudCoverMoreThan50Button();
			break;
		default:
			break;
		}
	}

	private void selectDayInSurveyDialog(SolarRadiation solarRadiation) {
		this.clickDayButton();
		Log.info("Survey Time: Day selected.");
		// Solar Radiation is valid only during Day time.
		Log.info("Selecting day in survey dialog - '" +solarRadiation +"'");
		switch (solarRadiation) {
		case Moderate:
			this.clickModerateButton();
			break;
		case Overcast:
			this.clickOvercastButton();
			break;
		case Strong:
			this.clickRadiationStrongButton();
			break;
		default:
			break;
		}
	}

	public void openStartSurveyModalDialog() {
		this.waitForSignalRCallsToComplete();
		Log.info("Opening the StartSurvey modal dialog..");
		this.clickStartSurveyButton();
		Log.info("Opened the StartSurvey modal dialog..");
		this.waitForStartSurveyModalDialogToShow();
		TestContext.INSTANCE.getTestSetup().slowdownInSeconds(TestContext.INSTANCE.getTestSetup().getSlowdownInSeconds());
	}

	public void openStartEQSurveyModalDialog() {
		Log.info("Opening the StartSurvey modal dialog..");
		this.clickStartEQSurveyButton();
		Log.info("Opened the StartSurvey modal dialog..");
		this.waitForStartSurveyModalDialogToShow();
		TestContext.INSTANCE.getTestSetup().slowdownInSeconds(TestContext.INSTANCE.getTestSetup().getSlowdownInSeconds());
	}

	public DriverViewPage stopDrivingSurvey() {
		Log.clickElementInfo("Stop Driving Survey");
		this.getStopDrivingSurveyButton().click();
		this.waitForUIUnBlock();
		return this;
	}

	/**
	 * Clicks on the Start EQ Survey button.
	 */
	private void clickStartEQSurveyButton() {
		Log.clickElementInfo("EQ Survey");
		this.startEQSurveyButton.click();
	}

	/**
	 * Verifies whether the EQ Mode dialog is shown.
	 */
	public boolean isEQModeDialogShown() {
		return this.eqModeDialog.getAttribute("class").equals("cssFade");
	}

	/**
	 * Verifies the EQ Mode dialog is NOT shown.
	 */
	public boolean isEQModeDialogHidden() {
		return this.eqModeDialog.getAttribute("class").equals("cssFade ng-hide");
	}
	
	/**
	 * Returns the message is EQ mode dialog.
	 */
	public String getEQModeDialogMessage() {
		return this.eqModeDialogMessage.getText();
	}

	/**
	 * Verifies the message shown in EQ mode dialog.
	 */
	public boolean verifyEQModeDialogMessageEquals(String message) {
		return this.eqModeDialogMessage.getText().equals(message);
	}

	public boolean isStartSurveyDialogVisible() {
		return !this.startSurveyModalDialog.getAttribute("class").contains("ng-hide");
	}

	/**
	 * Verify that current page URL matches the expected URL.
	 *
	 * @return the DriverViewPage class instance.
	 */
	public DriverViewPage verifyPageUrl() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getCurrentUrl().contains(STRURLPath);
			}
		});
		return this;
	}

	/**
	 * Verify that the page loaded completely.
	 */
	public void waitForPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(STRPageContentText);
			}
		});
	}

	/**
	 * Waits for the start survey modal dialog to show.
	 */
	public void waitForStartSurveyModalDialogToShow() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isStartSurveyDialogVisible();
			}
		});
	}

	/**
	 * Waits for the Start Survey button to be displayed.
	 */
	public void waitForStartSurveyButtonToShow() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !startSurvey.getAttribute("class").contains("ng-hide");
			}
		});
	}

	/**
	 * Waits for the Manual Survey text field section to display.
	 */
	public void waitForMinAmpTextFieldToShow() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return manualSurveySection.getAttribute("class").equalsIgnoreCase("");
			}
		});
	}

	public boolean isModeMenuOpen() {
		return this.modeMenu.getAttribute("class").equalsIgnoreCase("");
	}

	public boolean isModeMenuClosed() {
		return this.modeMenu.getAttribute("class").equalsIgnoreCase("ng-hide");
	}

	/**
	 * Waits for the survey mode menu to open.
	 */
	public void waitForModeMenuToOpen() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isModeMenuOpen();
			}
		});
	}

	/**
	 * Waits for the survey mode menu to close.
	 */
	public void waitForModeMenuToClose() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isModeMenuClosed();
			}
		});
	}

	/**
	 * Waits for the survey duration warning dialog to close.
	 */
	public void waitForSurveyDurationWarningDialogToClose() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return divSurveyDurationWarning.getAttribute("class").equalsIgnoreCase("cssFade ng-hide");
			}
		});
	}

	/**
	 * Waits for the survey start warning dialog to close.
	 */
	public void waitForSurveyFailedToStartDialogToClose() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return divSurveyStartWarning.getAttribute("class").equalsIgnoreCase("cssFade ng-hide");
			}
		});
	}

	/**
	 * Waits for the Field notes dialog to be shown.
	 */
	public void waitForFieldNotesDialogToOpen() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isFieldNotesDialogShown();
			}
		});
	}

	/**
	 * Waits for the Field notes dialog to be closed.
	 */
	public void waitForFieldNotesDialogToClose() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !isFieldNotesDialogShown();
			}
		});
	}

	/**
	 * Waits for the Feature info dialog to be shown.
	 */
	public void waitForFeatureInfoDialogToOpen() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isFeatureInfoDialogShown();
			}
		});
	}

	/**
	 * Waits for the Feature info dialog to be closed.
	 */
	public void waitForFeatureInfoDialogToClose() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !isFeatureInfoDialogShown();
			}
		});
	}

	/**
	 * Waits for StartSurvey button to be visible.
	 */
	public void waitForStartSurveyButtonToBeVisible() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isStartSurveyButtonVisible();
			}
		});
	}
}

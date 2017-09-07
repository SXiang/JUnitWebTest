package androidapp.screens.source;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import androidapp.entities.source.LeakInfoEntity;
import common.source.BaselineImages;
import common.source.Log;
import common.source.LogHelper;
import common.source.MobileActions;
import common.source.TestContext;
import common.source.MobileActions.KeyCode;
import common.source.MobileActions.SwipeDirection;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.scommon.mobile.source.LeakDataGenerator.DataKey;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakLocationType;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakPipeMaterialType;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakType;
import surveyor.scommon.mobile.source.LeakDataTypes.ReadingUnitType;
import surveyor.scommon.mobile.source.LeakDataTypes.SurfaceOverLeakType;

public class AndroidAddLeakSourceFormDialog extends AndroidBaseScreen {
	private static final String SUBMIT_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Submit\")";
	private static final String CANCEL_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Cancel\")";
	private static final String DELETE_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Delete\")";

	/****** Button elements ******/

	@AndroidFindBy(uiAutomator = SUBMIT_BUTTON_UI_SELECTOR)
	private WebElement submit;

	@AndroidFindBy(uiAutomator = CANCEL_BUTTON_UI_SELECTOR)
	private WebElement cancel;

	@AndroidFindBy(uiAutomator = DELETE_BUTTON_UI_SELECTOR)
	private WebElement delete;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]")
	@CacheLookup
	private WebElement formScrollView;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[2]")
	@CacheLookup
	private WebElement useCurrentLocation;

	/****** TextField elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.EditText[2]")
	@CacheLookup
	private WebElement apartmentNumber;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[8]/android.view.ViewGroup[1]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement barholeReading;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[5]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement city;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement latitude;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[9]/android.view.ViewGroup[1]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement leakGrade;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.EditText[2]")
	@CacheLookup
	private WebElement longitude;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[6]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement mapNumber;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[13]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement meterNumber;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[14]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement leakLocationRemarks;

	// Fetch at Runtime post swipe.
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[11]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement additionalNotes;

	// Fetch at Runtime post swipe when editing form. Keeping seperate as this at times differs.
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[11]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement additionalNotesInEdit;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[10]/android.view.ViewGroup[1]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement pipeMaterialType;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[5]/android.widget.EditText[2]")
	@CacheLookup
	private WebElement state;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement streetName;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement streetNumber;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[7]/android.view.ViewGroup[1]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement surfaceReading;

	/****** SelectBox elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[10]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement leakLocation;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[10]/android.view.ViewGroup[1]/android.widget.Spinner[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement leakLocationText;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[9]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement leakType;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[9]/android.view.ViewGroup[1]/android.widget.Spinner[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement leakTypeText;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[7]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement surfaceReadingUnit;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[7]/android.view.ViewGroup[1]/android.widget.Spinner[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement surfaceReadingUnitText;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[8]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement barholeReadingUnit;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[8]/android.view.ViewGroup[1]/android.widget.Spinner[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement barholeReadingUnitText;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[12]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement surfaceOverLeak;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[12]/android.view.ViewGroup[1]/android.widget.Spinner[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement surfaceOverLeakText;

	/****** CheckBox elements ********/
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[11]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement pavedWallToWall;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[11]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement pavedWallToWallText;

	private AndroidLeakLocationTypeListControl leakLocationTypeListControl;
	private AndroidLeakSourceTypeListControl leakSourceTypeListControl;
	private AndroidLeakTypeListControl leakTypeListControl;
	private AndroidReadingUnitTypeListControl surfaceReadingUnitListControl;
	private AndroidReadingUnitTypeListControl barholeReadingUnitListControl;
	private AndroidSurfaceOverLeakTypeListControl surfaceOverLeakTypeListControl;

	public AndroidAddLeakSourceFormDialog(WebDriver driver) {
		super(driver);
		initializeListControls();
	}

	private void initializeListControls() {
		leakLocationTypeListControl = new AndroidLeakLocationTypeListControl(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), leakLocationTypeListControl);

		leakSourceTypeListControl = new AndroidLeakSourceTypeListControl(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), leakSourceTypeListControl);

		leakTypeListControl = new AndroidLeakTypeListControl(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), leakTypeListControl);

		surfaceReadingUnitListControl = new AndroidReadingUnitTypeListControl(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), surfaceReadingUnitListControl);

		barholeReadingUnitListControl = new AndroidReadingUnitTypeListControl(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), barholeReadingUnitListControl);

		surfaceOverLeakTypeListControl = new AndroidSurfaceOverLeakTypeListControl(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), surfaceOverLeakTypeListControl);
	}

	/****** ListControl Methods ******/

	public void selectLocationType(LeakLocationType leakLocationType) {
		tap(this.leakLocation);
		this.leakLocationTypeListControl.selectLeakLocationType(leakLocationType);
	}

	public void selectSourceType(LeakSourceType leakSourceType) {
		this.leakSourceTypeListControl.selectLeakSourceType(leakSourceType);
	}

	public void selectLeakType(LeakType leakTypeValue) {
		tap(this.leakType);
		this.leakTypeListControl.selectLeakType(leakTypeValue);
	}

	public void selectSurfaceReadingUnit(ReadingUnitType readingUnitType) {
		tap(this.surfaceReadingUnit);
		this.surfaceReadingUnitListControl.selectReadingUnitType(readingUnitType);
	}

	public void selectBarholeReadingUnit(ReadingUnitType readingUnitType) {
		tap(this.barholeReadingUnit);
		this.barholeReadingUnitListControl.selectReadingUnitType(readingUnitType);
	}

	public void selectSurfaceOverleakType(SurfaceOverLeakType surfaceOverLeakType) {
		tap(this.surfaceOverLeak);
		this.surfaceOverLeakTypeListControl.selectSurfaceOverLeakType(surfaceOverLeakType);
	}

	public String getLocationTypeSelectedText() {
		Log.method("getLocationTypeSelectedText");
		return this.leakLocationText.getText();
	}

	public String getLeakTypeSelectedText() {
		Log.method("getLeakTypeSelectedText");
		return this.leakTypeText.getText();
	}

	public String getSurfaceReadingUnitSelectedText() {
		Log.method("getSurfaceReadingUnitSelectedText");
		return this.surfaceReadingUnitText.getText();
	}

	public String getBarholeReadingUnitSelectedText() {
		Log.method("getBarholeReadingUnitSelectedText");
		return this.barholeReadingUnitText.getText();
	}

	public String getSurfaceOverleakTypeSelectedText() {
		Log.method("getSurfaceOverleakTypeSelectedText");
		return this.surfaceOverLeakText.getText();
	}

	/****** Button Methods ******/

	public WebElement getSubmitButton() {
		Log.method("getSubmitButton");
		submit = getAndroidDriver().findElementByAndroidUIAutomator(SUBMIT_BUTTON_UI_SELECTOR);
		return submit;
	}

	public void clickOnSubmit() throws Exception {
		Log.method("clickOnSubmit");
		WebElement submitButton = getSubmitButton();
		tap(submitButton);
		press(submitButton);
		clickAndPressKey(submitButton, KeyCode.KEYCODE_ENTER);
	}

	public WebElement getCancelButton() {
		Log.method("getCancelButton");
		cancel = getAndroidDriver().findElementByAndroidUIAutomator(CANCEL_BUTTON_UI_SELECTOR);
		return cancel;
	}

	public void clickOnCancel() throws Exception {
		Log.method("clickOnCancel");
		WebElement cancelButton = getCancelButton();
		tap(cancelButton);
		cancelButton.click();
	}

	public void tapOnCancel() throws Exception {
		Log.method("tapOnCancel");
		tap(getCancelButton());
	}

	public WebElement getDeleteButton() {
		Log.method("getDeleteButton");
		delete = getAndroidDriver().findElementByAndroidUIAutomator(DELETE_BUTTON_UI_SELECTOR);
		return delete;
	}

	public void clickOnDelete() throws Exception {
		Log.method("clickOnDelete");
		WebElement deleteButton = getDeleteButton();
		clickAndPressKey(deleteButton, KeyCode.KEYCODE_ENTER);
	}

	public WebElement getUseCurrentLocationButton() {
		Log.method("getUseCurrentLocationButton");
		return useCurrentLocation;
	}

	public void clickOnUseCurrentLocation() throws Exception {
		Log.method("clickOnUseCurrentLocation");
		WebElement useCurrentLocationButton = getUseCurrentLocationButton();
		tap(useCurrentLocationButton);
		press(useCurrentLocationButton);
		clickAndPressKey(useCurrentLocationButton, KeyCode.KEYCODE_ENTER);
	}

	public void selectPavedWall2Wall() throws Exception {
		Log.method("selectPavedWall2Wall");
		if (isPavedWallToWallUnSelected()) {
			tapPressPavedWall2Wall();
		}
	}

	public void unselectPavedWall2Wall() throws Exception {
		Log.method("unselectPavedWall2Wall");
		if (isPavedWallToWallSelected()) {
			tapPressPavedWall2Wall();
		}
	}

	private void tapPressPavedWall2Wall() throws Exception {
		Log.method("tapPressPavedWall2Wall");
		tap(this.pavedWallToWall);
		press(this.pavedWallToWall);
		clickAndPressKey(this.pavedWallToWall, KeyCode.KEYCODE_ENTER);
	}

	private Boolean isPavedWallToWallSelected() throws IOException {
		Log.method("isPavedWallToWallSelected");
		return screenVerifier.verifyImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.PavedWallToWallChecked);
	}

	private Boolean isPavedWallToWallUnSelected() throws IOException {
		Log.method("isPavedWallToWallUnSelected");
		return screenVerifier.verifyImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.PavedWallToWallUnChecked);
	}

	/****** TextField Methods ******/

	public String getApartmentNumberText() {
		Log.method("getApartmentNumberText");
		return apartmentNumber.getText();
	}

	public void enterApartmentNumber(String value) throws Exception {
		Log.method("enterApartmentNumber");
		sendKeysInternal(apartmentNumber, value);
	}

	public String getBarholeReadingText() {
		Log.method("getBarholeReadingText");
		return barholeReading.getText();
	}

	public void enterBarholeReading(String value) throws Exception {
		Log.method("enterBarholeReading");
		sendKeysInternal(barholeReading, value);
	}

	public String getCityText() {
		Log.method("getCityText");
		return city.getText();
	}

	public void enterCity(String value) throws Exception {
		Log.method("enterCity");
		sendKeysInternal(city, value);
	}

	public String getLatitudeText() {
		Log.method("getLatitudeText");
		return latitude.getText();
	}

	public void enterLatitude(String value) throws Exception {
		Log.method("enterLatitude");
		sendKeysInternal(latitude, value);
	}

	public String getLeakGradeText() {
		Log.method("getLeakGradeText");
		return leakGrade.getText();
	}

	public void enterLeakGrade(String value) throws Exception {
		Log.method("enterLeakGrade");
		sendKeysInternal(leakGrade, value);
	}

	public String getLongitudeText() {
		Log.method("getLongitudeText");
		return longitude.getText();
	}

	public void enterLongitude(String value) throws Exception {
		Log.method("enterLongitude");
		sendKeysInternal(longitude, value);
	}

	public String getMapNumberText() {
		Log.method("getMapNumberText");
		return mapNumber.getText();
	}

	public void enterMapNumber(String value) throws Exception {
		Log.method("enterMapNumber");
		sendKeysInternal(mapNumber, value);
	}

	public String getMeterNumberText() {
		Log.method("getMeterNumberText");
		return meterNumber.getText();
	}

	public void enterMeterNumber(String value) throws Exception {
		Log.method("enterMeterNumber");
		sendKeysInternal(meterNumber, value);
	}

	public String getLeakLocationRemarksText() {
		Log.method("getLeakLocationRemarksText");
		return leakLocationRemarks.getText();
	}

	public void enterLeakLocationRemarks(String value) throws Exception {
		Log.method("enterLeakLocationRemarks");
		leakLocationRemarks.sendKeys(value);
	}

	public String getPipeMaterialTypeText() {
		Log.method("getPipeMaterialTypeText");
		return pipeMaterialType.getText();
	}

	public void enterPipeMaterialType(String value) throws Exception {
		Log.method("enterPipeMaterialType");
		sendKeysInternal(pipeMaterialType, value);
	}

	public String getStateText() {
		Log.method("getStateText");
		return state.getText();
	}

	public void enterState(String value) throws Exception {
		Log.method("enterState");
		sendKeysInternal(state, value);
	}

	public String getStreetNameText() {
		Log.method("getStreetNameText");
		return streetName.getText();
	}

	public void enterStreetName(String value) throws Exception {
		Log.method("enterStreetName");
		sendKeysInternal(streetName, value);
	}

	public String getStreetNumberText() {
		Log.method("getStreetNumberText");
		return streetNumber.getText();
	}

	public void enterStreetNumber(String value) throws Exception {
		Log.method("enterStreetNumber");
		sendKeysInternal(streetNumber, value);
	}

	public String getSurfaceReadingText() {
		Log.method("getSurfaceReadingText");
		return surfaceReading.getText();
	}

	public void enterSurfaceReading(String value) throws Exception {
		Log.method("enterSurfaceReading");
		sendKeysInternal(surfaceReading, value);
	}

	public String getAdditionalNotesText() {
		Log.method("getAdditionalNotesText");
		return additionalNotes.getText();
	}

	public void enterAdditionalNotes(String value) throws Exception {
		Log.method("enterAdditionalNotes");
		additionalNotes.clear();
		additionalNotes.sendKeys(value);
	}

	public String getAdditionalNotesTextInEditMode() {
		Log.method("getAdditionalNotesTextInEditMode");
		return additionalNotesInEdit.getText();
	}

	public void enterAdditionalNotesInEditMode(String value) throws Exception {
		Log.method("enterAdditionalNotesInEditMode");
		additionalNotesInEdit.clear();
		additionalNotesInEdit.sendKeys(value);
	}

	public void clearAndFillForm(Map<String, Object> formValues) throws Exception {
		Log.method("clearAndFillForm", LogHelper.mapToString(formValues));
		clearTextFields();
		fillForm(formValues, true /*editing*/, true /*clickOkButton*/);
	}

	public void fillForm(Map<String, Object> formValues) throws Exception {
		fillForm(formValues, false /*editing*/, true /*clickOkButton*/);
	}

	public void fillFormAndCancel(Map<String, Object> formValues) throws Exception {
		fillForm(formValues, false /*editing*/, false /*clickOkButton*/);
		this.clickOnCancel();
	}

	public void fillForm(Map<String, Object> formValues, Boolean editing, Boolean clickOkButton) throws Exception {
		Log.method("fillForm", LogHelper.mapToString(formValues), editing, clickOkButton);
		LeakSourceType leakSourceType = (LeakSourceType)formValues.get(DataKey.LEAK_SOURCE_TYPE);
		LeakLocationType locationType = (LeakLocationType)formValues.get(DataKey.LEAK_LOCATION_TYPE);
		LeakType leakType = (LeakType)formValues.get(DataKey.LEAK_TYPE);
		ReadingUnitType surfaceReadingUnit = (ReadingUnitType)formValues.get(DataKey.SURFACE_READING_UNIT);
		ReadingUnitType barholeReadingUnit = (ReadingUnitType)formValues.get(DataKey.BARHOLE_READING_UNIT);
		SurfaceOverLeakType surfaceOverLeakType = (SurfaceOverLeakType)formValues.get(DataKey.SURFACE_OVERLEAK_TYPE);
		LeakPipeMaterialType pipeMaterialType = (LeakPipeMaterialType)formValues.get(DataKey.PIPE_MATERIAL_TYPE);
		String streetNum = (String)formValues.get(DataKey.STREET_NUMBER);
		String streetName = (String)formValues.get(DataKey.STREET_NAME);
		String aptNum = (String)formValues.get(DataKey.APARTMENT_NUMBER);
		String city = (String)formValues.get(DataKey.CITY);
		String state = (String)formValues.get(DataKey.STATE);
		String mapNum = (String)formValues.get(DataKey.MAP_NUMBER);
		String surfaceReading = (String)formValues.get(DataKey.SURFACE_READING);
		String barholeReading = (String)formValues.get(DataKey.BARHOLE_READING);
		String leakGrade = (String)formValues.get(DataKey.LEAK_GRADE);
		String meterNum = (String)formValues.get(DataKey.METER_NUMBER);
		String locationRemarks = (String)formValues.get(DataKey.LOCATION_REMARKS);
		String additionalNotes = (String)formValues.get(DataKey.ADDITIONAL_NOTES);
		Boolean isPavedWallToWall = (Boolean)formValues.get(DataKey.IS_PAVED_WALL2WALL);
		Boolean useCurrentLocation = (Boolean)formValues.get(DataKey.USE_CURRENT_LOCATION);

		if (useCurrentLocation) {
			this.clickOnUseCurrentLocation();
		}

		this.enterStreetNumber(streetNum);
		this.enterApartmentNumber(aptNum);
		this.enterStreetName(streetName);
		this.enterCity(city);
		this.enterState(state);
		this.enterMapNumber(mapNum);

		this.enterSurfaceReading(surfaceReading);
		this.selectSurfaceReadingUnit(surfaceReadingUnit);

		this.enterBarholeReading(barholeReading);
		this.selectBarholeReadingUnit(barholeReadingUnit);

		this.selectLeakType(leakType);
		this.enterLeakGrade(leakGrade);

		this.selectLocationType(locationType);
		this.enterPipeMaterialType(pipeMaterialType.toString());

		if (isPavedWallToWall) {
			this.selectPavedWall2Wall();
		} else {
			this.unselectPavedWall2Wall();
		}

		this.selectSurfaceOverleakType(surfaceOverLeakType);

		this.enterMeterNumber(meterNum);
		this.enterLeakLocationRemarks(locationRemarks);

		scrollToNextPage();

		if (editing) {
			this.enterAdditionalNotesInEditMode(additionalNotes);
		} else {
			this.enterAdditionalNotes(additionalNotes);
		}

		if (clickOkButton) {
			this.clickOnSubmit();
		}
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidAddedLeakSourceFormDialog.screenLoadCondition");
		return this.mapNumber!=null && this.mapNumber.isDisplayed();
	}

	public void scrollToNextPage() throws Exception {
		Log.method("scrollToNextPage");
		MobileActions.newAction(getAndroidDriver()).swipeFromCenter(formScrollView, SwipeDirection.UP, 600);
	}

	public Boolean verifyCorrectDataIsShown(Map<String, Object> formValues) throws Exception {
		Log.method("verifyCorrectDataIsShown", LogHelper.mapToString(formValues));
		return verifyCorrectDataIsShown(formValues, false /*isEditMode*/);
	}

	public Boolean verifyCorrectDataIsShown(Map<String, Object> formValues, Boolean isEditMode) throws Exception {
		Log.method("verifyCorrectDataIsShown", LogHelper.mapToString(formValues), isEditMode);

		// expected
		LeakLocationType expectedLocationType = (LeakLocationType)formValues.get(DataKey.LEAK_LOCATION_TYPE);
		LeakType expectedLeakType = (LeakType)formValues.get(DataKey.LEAK_TYPE);
		ReadingUnitType expectedSurfaceReadingUnit = (ReadingUnitType)formValues.get(DataKey.SURFACE_READING_UNIT);
		ReadingUnitType expectedBarholeReadingUnit = (ReadingUnitType)formValues.get(DataKey.BARHOLE_READING_UNIT);
		SurfaceOverLeakType expectedSurfaceOverLeakType = (SurfaceOverLeakType)formValues.get(DataKey.SURFACE_OVERLEAK_TYPE);
		LeakPipeMaterialType expectedPipeMaterialType = (LeakPipeMaterialType)formValues.get(DataKey.PIPE_MATERIAL_TYPE);
		String expectedStreetNum = (String)formValues.get(DataKey.STREET_NUMBER);
		String expectedStreetName = (String)formValues.get(DataKey.STREET_NAME);
		String expectedAptNum = (String)formValues.get(DataKey.APARTMENT_NUMBER);
		String expectedCity = (String)formValues.get(DataKey.CITY);
		String expectedState = (String)formValues.get(DataKey.STATE);
		String expectedMapNum = (String)formValues.get(DataKey.MAP_NUMBER);
		String expectedSurfaceReading = (String)formValues.get(DataKey.SURFACE_READING);
		String expectedBarholeReading = (String)formValues.get(DataKey.BARHOLE_READING);
		String expectedLeakGrade = (String)formValues.get(DataKey.LEAK_GRADE);
		String expectedMeterNum = (String)formValues.get(DataKey.METER_NUMBER);
		String expectedLocationRemarks = (String)formValues.get(DataKey.LOCATION_REMARKS);
		String expectedAdditionalNotes = (String)formValues.get(DataKey.ADDITIONAL_NOTES);
		Boolean expectedIsPavedWallToWall = (Boolean)formValues.get(DataKey.IS_PAVED_WALL2WALL);

		LeakInfoEntity expectedLeakInfo = new LeakInfoEntity();
		expectedLeakInfo.setStreetNum(expectedStreetNum);
		expectedLeakInfo.setAptNum(expectedAptNum);
		expectedLeakInfo.setStreetName(expectedStreetName);
		expectedLeakInfo.setCity(expectedCity);
		expectedLeakInfo.setState(expectedState);
		expectedLeakInfo.setMapNum(expectedMapNum);
		expectedLeakInfo.setSurfaceReading(expectedSurfaceReading);
		expectedLeakInfo.setSurfaceReadingUnit(expectedSurfaceReadingUnit.toString());
		expectedLeakInfo.setBarHoleReading(expectedBarholeReading);
		expectedLeakInfo.setBarholeReadingUnit(expectedBarholeReadingUnit.toString());
		expectedLeakInfo.setLeakType(expectedLeakType.toString());
		expectedLeakInfo.setLeakGrade(expectedLeakGrade);
		expectedLeakInfo.setLocationType(expectedLocationType.toString());
		expectedLeakInfo.setPipeMaterialType(expectedPipeMaterialType.toString());
		expectedLeakInfo.setSurfaceOverleakType(expectedSurfaceOverLeakType.toString());
		expectedLeakInfo.setMeterNum(expectedMeterNum);
		expectedLeakInfo.setLocationRemarks(expectedLocationRemarks);
		expectedLeakInfo.setIsPavedWallToWall(expectedIsPavedWallToWall);
		expectedLeakInfo.setAdditionalNotes(expectedAdditionalNotes);

		// actual.
		String actualLatitude = this.getLatitudeText();
		String actualLongitude = this.getLongitudeText();
		String actualStreetNum = this.getStreetNumberText();
		String actualAptNum = this.getApartmentNumberText();
		String actualStreetName = this.getStreetNameText();
		String actualCity = this.getCityText();
		String actualState = this.getStateText();
		String actualMapNum = this.getMapNumberText();
		String actualSurfaceReading = this.getSurfaceReadingText();
		String actualSurfaceReadingUnit = this.getSurfaceReadingUnitSelectedText();
		String actualBarHoleReading = this.getBarholeReadingText();
		String actualBarholeReadingUnit = this.getBarholeReadingUnitSelectedText();
		String actualLeakType = this.getLeakTypeSelectedText();
		String actualLeakGrade = this.getLeakGradeText();
		String actualLocationType = this.getLocationTypeSelectedText();
		String actualPipeMaterialType = this.getPipeMaterialTypeText();
		String actualSurfaceOverleakType = this.getSurfaceOverleakTypeSelectedText();
		String actualMeterNum = this.getMeterNumberText();
		String actualLocationRemarks = this.getLeakLocationRemarksText();
		Boolean actualIsPavedWallToWall = false;

		// use image verification method corresponding to expected value to prevent waits.
		if (expectedIsPavedWallToWall) {
			actualIsPavedWallToWall = isPavedWallToWallSelected() ? true : false;
		} else {
			actualIsPavedWallToWall = isPavedWallToWallUnSelected() ? false : true;
		}

		scrollToNextPage();

		String actualAdditionalNotes = null;
		if (isEditMode) {
			actualAdditionalNotes = this.getAdditionalNotesTextInEditMode();
		} else {
			actualAdditionalNotes = this.getAdditionalNotesText();
		}

		LeakInfoEntity actualLeakInfo = new LeakInfoEntity();
		actualLeakInfo.setStreetNum(actualStreetNum);
		actualLeakInfo.setAptNum(actualAptNum);
		actualLeakInfo.setStreetName(actualStreetName);
		actualLeakInfo.setCity(actualCity);
		actualLeakInfo.setState(actualState);
		actualLeakInfo.setMapNum(actualMapNum);
		actualLeakInfo.setSurfaceReading(actualSurfaceReading);
		actualLeakInfo.setSurfaceReadingUnit(actualSurfaceReadingUnit);
		actualLeakInfo.setBarHoleReading(actualBarHoleReading);
		actualLeakInfo.setBarholeReadingUnit(actualBarholeReadingUnit);
		actualLeakInfo.setLeakType(actualLeakType);
		actualLeakInfo.setLeakGrade(actualLeakGrade);
		actualLeakInfo.setLocationType(actualLocationType);
		actualLeakInfo.setPipeMaterialType(actualPipeMaterialType);
		actualLeakInfo.setSurfaceOverleakType(actualSurfaceOverleakType);
		actualLeakInfo.setMeterNum(actualMeterNum);
		actualLeakInfo.setLocationRemarks(actualLocationRemarks);
		actualLeakInfo.setIsPavedWallToWall(actualIsPavedWallToWall);
		actualLeakInfo.setAdditionalNotes(actualAdditionalNotes);

		// verify
		Boolean match = actualLatitude.length()>5;
		if (!match) {
			Log.error(String.format("Latitude value NOT corrected. Expected length>5; Actual length=[%d]", actualLatitude.length()));
			return match;
		}

		match = match && actualLongitude.length()>5;
		if (!match) {
			Log.error(String.format("Longitude value NOT correct. Expected length>5; Actual length=[%d]", actualLongitude.length()));
			return match;
		}

		match = match && (actualLeakInfo.compareTo(expectedLeakInfo) == 0);

		if (!match) {
			Log.error(String.format("LeakInfo did NOT match. Expected=[%s]; Actual=[%s]", expectedLeakInfo, actualLeakInfo));
		}

		Log.info(String.format("Returning match=[%b]", match));
		return match;
	}

	private void clearTextFields() {
		Log.method("clearTextFields");
		apartmentNumber.clear();
		barholeReading.clear();
		city.clear();
		leakGrade.clear();
		mapNumber.clear();
		meterNumber.clear();
		leakLocationRemarks.clear();
		pipeMaterialType.clear();
		state.clear();
		streetName.clear();
		streetNumber.clear();
		surfaceReading.clear();
	}

	private void sendKeysInternal(WebElement element, String value) throws Exception {
		if (TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			element.sendKeys(value);
		} else {
			sendKeys(element, value);
		}
	}
}
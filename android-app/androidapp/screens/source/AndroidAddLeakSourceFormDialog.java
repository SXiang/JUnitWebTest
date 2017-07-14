package androidapp.screens.source;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import common.source.FunctionUtil;
import common.source.Log;
import common.source.LogHelper;
import common.source.MobileActions;
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

	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[13]/android.widget.TextView")
	@CacheLookup
	private WebElement oK;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[14]/android.widget.TextView")
	@CacheLookup
	private WebElement cancel;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[2]")
	@CacheLookup
	private WebElement useCurrentLocation;

	/****** TextField elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.EditText[2]")
	@CacheLookup
	private WebElement apartmentNumber;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[8]/android.view.ViewGroup[1]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement barholeReading;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[5]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement city;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement latitude;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[9]/android.view.ViewGroup[1]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement leakGrade;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.EditText[2]")
	@CacheLookup
	private WebElement longitude;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[6]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement mapNumber;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[13]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement meterNumber;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[14]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement leakLocationRemarks;

	// Fetch at Runtime post swipe.
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[12]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement additionalNotes;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[10]/android.view.ViewGroup[1]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement pipeMaterialType;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[5]/android.widget.EditText[2]")
	@CacheLookup
	private WebElement state;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement streetName;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement streetNumber;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[7]/android.view.ViewGroup[1]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement surfaceReading;

	/****** SelectBox elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[10]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement leakLocation;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[9]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement leakType;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[7]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement surfaceReadingUnit;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[8]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement barholeReadingUnit;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[12]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement surfaceOverLeak;

	/****** CheckBox elements ********/
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[11]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement pavedWallToWall;

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

	/****** Button Methods ******/

	public WebElement getOKButton() {
		Log.method("getOKButton");
		return oK;
	}

	public void clickOnOK() throws Exception {
		Log.method("clickOnOK");
		tap(getOKButton());
		press(getOKButton());
		clickAndPressKey(getOKButton(), KeyCode.KEYCODE_ENTER);
	}

	public WebElement getCancelButton() {
		Log.method("getCancelButton");
		return cancel;
	}

	public void clickOnCancel() throws Exception {
		Log.method("clickOnCancel");
		clickAndPressKey(getCancelButton(), KeyCode.KEYCODE_ENTER);
	}

	public WebElement getUseCurrentLocationButton() {
		Log.method("getUseCurrentLocationButton");
		return useCurrentLocation;
	}

	public void clickOnUseCurrentLocation() throws Exception {
		Log.method("clickOnUseCurrentLocation");
		tap(getUseCurrentLocationButton());
		press(getUseCurrentLocationButton());
		clickAndPressKey(getUseCurrentLocationButton(), KeyCode.KEYCODE_ENTER);
	}

	/****** TextField Methods ******/

	public void selectPavedWall2Wall() {
		Log.method("clickOnCancel");
		tap(this.pavedWallToWall);
	}

	/****** TextField Methods ******/

	public String getApartmentNumberText() {
		Log.method("getApartmentNumberText");
		return apartmentNumber.getText();
	}

	public void enterApartmentNumber(String value) throws Exception {
		Log.method("enterApartmentNumber");
		sendKeys(apartmentNumber, value);
	}

	public String getBarholeReadingText() {
		Log.method("getBarholeReadingText");
		return barholeReading.getText();
	}

	public void enterBarholeReading(String value) throws Exception {
		Log.method("enterBarholeReading");
		sendKeys(barholeReading, value);
	}

	public String getCityText() {
		Log.method("getCityText");
		return city.getText();
	}

	public void enterCity(String value) throws Exception {
		Log.method("enterCity");
		sendKeys(city, value);
	}

	public String getLatitudeText() {
		Log.method("getLatitudeText");
		return latitude.getText();
	}

	public void enterLatitude(String value) throws Exception {
		Log.method("enterLatitude");
		sendKeys(latitude, value);
	}

	public String getLeakGradeText() {
		Log.method("getLeakGradeText");
		return leakGrade.getText();
	}

	public void enterLeakGrade(String value) throws Exception {
		Log.method("enterLeakGrade");
		sendKeys(leakGrade, value);
	}

	public String getLongitudeText() {
		Log.method("getLongitudeText");
		return longitude.getText();
	}

	public void enterLongitude(String value) throws Exception {
		Log.method("enterLongitude");
		sendKeys(longitude, value);
	}

	public String getMapNumberText() {
		Log.method("getMapNumberText");
		return mapNumber.getText();
	}

	public void enterMapNumber(String value) throws Exception {
		Log.method("enterMapNumber");
		sendKeys(mapNumber, value);
	}

	public String getMeterNumberText() {
		Log.method("getMeterNumberText");
		return meterNumber.getText();
	}

	public void enterMeterNumber(String value) throws Exception {
		Log.method("enterMeterNumber");
		sendKeys(meterNumber, value);
	}

	public String getLeakLocationRemarksText() {
		Log.method("getLeakLocationRemarksText");
		return leakLocationRemarks.getText();
	}

	public void enterLeakLocationRemarks(String value) throws Exception {
		Log.method("enterLeakLocationRemarks");
		sendKeys(leakLocationRemarks, value);
	}

	public String getPipeMaterialTypeText() {
		Log.method("getPipeMaterialTypeText");
		return pipeMaterialType.getText();
	}

	public void enterPipeMaterialType(String value) throws Exception {
		Log.method("enterPipeMaterialType");
		sendKeys(pipeMaterialType, value);
	}

	public String getStateText() {
		Log.method("getStateText");
		return state.getText();
	}

	public void enterState(String value) throws Exception {
		Log.method("enterState");
		sendKeys(state, value);
	}

	public String getStreetNameText() {
		Log.method("getStreetNameText");
		return streetName.getText();
	}

	public void enterStreetName(String value) throws Exception {
		Log.method("enterStreetName");
		sendKeys(streetName, value);
	}

	public String getStreetNumberText() {
		Log.method("getStreetNumberText");
		return streetNumber.getText();
	}

	public void enterStreetNumber(String value) throws Exception {
		Log.method("enterStreetNumber");
		sendKeys(streetNumber, value);
	}

	public String getSurfaceReadingText() {
		Log.method("getSurfaceReadingText");
		return surfaceReading.getText();
	}

	public void enterSurfaceReading(String value) throws Exception {
		Log.method("enterSurfaceReading");
		sendKeys(surfaceReading, value);
	}

	public String getAdditionalNotesText() {
		Log.method("getAdditionalNotesText");
		return additionalNotes.getText();
	}

	public void enterAdditionalNotes(String value) throws Exception {
		Log.method("enterAdditionalNotes");
		sendKeys(additionalNotes, value);
	}

	public void fillForm(Map<String, Object> formValues) throws Exception {
		Log.method("enterAdditionalNotes", LogHelper.mapToString(formValues));
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
		}

		this.selectSurfaceOverleakType(surfaceOverLeakType);

		this.enterMeterNumber(meterNum);
		this.enterLeakLocationRemarks(locationRemarks);

		MobileActions.newAction(getAndroidDriver()).swipeFromCenter(SwipeDirection.UP, 600, 2000);

		this.enterAdditionalNotes(additionalNotes);
		this.clickOnOK();
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		return this.mapNumber!=null && this.mapNumber.isDisplayed();
	}
}
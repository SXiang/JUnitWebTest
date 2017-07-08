package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidAddLeakSourceFormDialog extends AndroidBaseScreen {

	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[13]")
	@CacheLookup
	private WebElement oK;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[14]")
	@CacheLookup
	private WebElement cancel;

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
	private WebElement readingUnit;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[8]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement readingUnit_D7E;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[12]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement surfaceOverLeak;

	public AndroidAddLeakSourceFormDialog(WebDriver driver) {
		super(driver);
	}

	/****** Button Methods ******/

	public WebElement getOKButton() {
		Log.method("getOKButton");
		return oK;
	}

	public void clickOnOK() {
		Log.method("clickOnOK");
		tap(getOKButton());
	}

	public WebElement getCancelButton() {
		Log.method("getCancelButton");
		return cancel;
	}

	public void clickOnCancel() {
		Log.method("clickOnCancel");
		tap(getCancelButton());
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

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		return mainFrameLayout!=null && mainFrameLayout.isDisplayed();
	}
}

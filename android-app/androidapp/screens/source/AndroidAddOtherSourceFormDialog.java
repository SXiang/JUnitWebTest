package androidapp.screens.source;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import common.source.Timeout;
import common.source.MobileActions.KeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;

public class AndroidAddOtherSourceFormDialog extends AndroidBaseScreen {

	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[6]")
	@CacheLookup
	private WebElement oK;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[7]")
	@CacheLookup
	private WebElement cancel;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[5]")
	@CacheLookup
	private WebElement delete;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[2]")
	@CacheLookup
	private WebElement useCurrentLocation;

	/****** TextField elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement latitude;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.EditText[2]")
	@CacheLookup
	private WebElement longitude;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement additionalNotes;

	/****** SelectBox elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement source;

	private AndroidLeakSourceTypeListControl leakSourceTypeListControl;

	public AndroidAddOtherSourceFormDialog(WebDriver driver) {
		super(driver);
		initializeListControls();
	}

	private void initializeListControls() {
		leakSourceTypeListControl = new AndroidLeakSourceTypeListControl(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), leakSourceTypeListControl);
	}

	/****** ListControl Methods ******/

	public void selectLeakSource(LeakSourceType leakSourceType) {
		Log.method("selectLeakSource", leakSourceType);
		tap(this.source);
		this.leakSourceTypeListControl.selectLeakSourceType(leakSourceType);
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

	public void clickOnCancel() {
		Log.method("clickOnCancel");
		tap(getCancelButton());
	}

	public WebElement getDeleteButton() {
		Log.method("getDeleteButton");
		return delete;
	}

	public void clickOnDelete() {
		Log.method("clickOnDelete");
		tap(getDeleteButton());
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

	public String getLatitudeText() {
		Log.method("getLatitudeText");
		return latitude.getText();
	}

	public String getLongitudeText() {
		Log.method("getLongitudeText");
		return longitude.getText();
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
		return getOKButton()!=null && getOKButton().isDisplayed();
	}
}
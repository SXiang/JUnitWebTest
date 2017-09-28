package androidapp.screens.source;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import common.source.EnumUtility;
import common.source.Log;
import common.source.Timeout;
import common.source.MobileActions.KeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;

public class AndroidAddOtherSourceFormDialog extends AndroidBaseScreen {

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

	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[2]")
	@CacheLookup
	private WebElement useCurrentLocation;

	/****** TextField elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement latitude;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.EditText[2]")
	@CacheLookup
	private WebElement longitude;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement additionalNotes;

	/****** SelectBox elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement source;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.Spinner[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement sourceText;

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

	public LeakSourceType getSelectedLeakSource() {
		Log.method("getSelectedLeakSource");
		return EnumUtility.fromName(this.sourceText.getText().trim(), () -> LeakSourceType.values());
	}

	public void selectLeakSource(LeakSourceType leakSourceType) {
		Log.method("selectLeakSource", leakSourceType);
		tap(this.source);
		this.leakSourceTypeListControl.selectLeakSourceType(leakSourceType);
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
		Log.method("enterAdditionalNotes", value);
		additionalNotes.clear();
		additionalNotes.sendKeys(value);
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidAddOtherSourceFormDialog.screenLoadCondition");
		return getSubmitButton()!=null && getSubmitButton().isDisplayed();
	}

	public Boolean verifyCorrectDataIsShown(final LeakSourceType otherSourceLeakSourceType, final String otherSourceAdditionalNotes) {
		String actualAdditionalNotes = this.getAdditionalNotesText();
		if (!actualAdditionalNotes.equals(otherSourceAdditionalNotes)) {
			Log.info(String.format("Additional notes string does NOT match. Expected=[%s]; Actual=[%s]", otherSourceAdditionalNotes, actualAdditionalNotes));
			return false;
		}

		LeakSourceType actualSourceLeakSourceType = this.getSelectedLeakSource();
		if (!actualSourceLeakSourceType.equals(otherSourceLeakSourceType)) {
			Log.info(String.format("LeakSourceType does NOT match. Expected=[%s]; Actual=[%s]", otherSourceLeakSourceType, actualSourceLeakSourceType));
			return false;
		}

		return true;
	}
}
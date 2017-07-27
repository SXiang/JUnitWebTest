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

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[5]")
	@CacheLookup
	private WebElement oKNewItem;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[6]")
	@CacheLookup
	private WebElement cancelNewItem;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[6]")
	@CacheLookup
	private WebElement oKExistingItem;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[7]")
	@CacheLookup
	private WebElement cancelExistingItem;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[5]")
	@CacheLookup
	private WebElement deleteExistingItem;

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

	private Boolean isExistingItem = false;

	private AndroidLeakSourceTypeListControl leakSourceTypeListControl;

	public AndroidAddOtherSourceFormDialog(WebDriver driver) {
		this(driver, false);
	}

	public AndroidAddOtherSourceFormDialog(WebDriver driver, Boolean existingItem) {
		super(driver);
		initializeListControls();
		isExistingItem = existingItem;
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

	public WebElement getOKButtonForNewItem() {
		Log.method("getOKButtonForNewItem");
		return oKNewItem;
	}

	public void clickOnOKForNewItem() throws Exception {
		Log.method("clickOnOKForNewItem");
		tap(getOKButtonForNewItem());
		press(getOKButtonForNewItem());
		clickAndPressKey(getOKButtonForNewItem(), KeyCode.KEYCODE_ENTER);
	}

	public WebElement getCancelButtonForNewItem() {
		Log.method("getCancelButtonForNewItem");
		return cancelNewItem;
	}

	public void clickOnCancelForNewItem() {
		Log.method("clickOnCancelForNewItem");
		tap(getCancelButtonForNewItem());
	}

	public WebElement getOKButtonForExistingItem() {
		Log.method("getOKButtonForExistingItem");
		return oKExistingItem;
	}

	public void clickOnOKForExistingItem() throws Exception {
		Log.method("clickOnOKForExistingItem");
		tap(getOKButtonForExistingItem());
		press(getOKButtonForExistingItem());
		clickAndPressKey(getOKButtonForExistingItem(), KeyCode.KEYCODE_ENTER);
	}

	public WebElement getCancelButtonForExistingItem() {
		Log.method("getCancelButtonForExistingItem");
		return cancelExistingItem;
	}

	public void clickOnCancelForExistingItem() {
		Log.method("clickOnCancelForExistingItem");
		tap(getCancelButtonForExistingItem());
	}

	public WebElement getDeleteButtonForExistingItem() {
		Log.method("getDeleteButtonForExistingItem");
		return deleteExistingItem;
	}

	public void clickOnDeleteForExistingItem() {
		Log.method("clickOnDeleteForExistingItem");
		tap(getDeleteButtonForExistingItem());
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
		if (isExistingItem) {
			return getOKButtonForExistingItem()!=null && getOKButtonForExistingItem().isDisplayed();
		}

		return getOKButtonForNewItem()!=null && getOKButtonForNewItem().isDisplayed();
	}
}
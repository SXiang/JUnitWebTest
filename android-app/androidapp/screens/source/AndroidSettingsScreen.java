package androidapp.screens.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import common.source.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidSettingsScreen extends AndroidBaseScreen {

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[1]")
	private MobileElement bpackServerAddressEditText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[2]")
	private MobileElement picServerAddressEditText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[3]")
	private MobileElement usernameEditText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]")
	private MobileElement clearButton;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]")
	private MobileElement saveButton;

	public AndroidSettingsScreen(WebDriver driver) {
		super(driver);
	}

	public void saveSettings(String backpackAddress, String picServerAddress, String username) {
		Log.method("saveSettings", backpackAddress, picServerAddress, username);
//		bpackServerAddressEditText = (MobileElement) this.driver.findElement(By.xpath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[1]"));
//		if (bpackServerAddressEditText != null) {
			Log.info(String.format("bpackServerAddressEditText=[%s]", bpackServerAddressEditText));
			Log.info("Clicking on bpackServerAddressEditText ...");
			bpackServerAddressEditText.click();
			bpackServerAddressEditText.sendKeys(backpackAddress);
//		}

//		picServerAddressEditText = (MobileElement) this.driver.findElement(By.xpath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[2]"));
//		if (picServerAddressEditText != null) {
			Log.info(String.format("picServerAddressEditText=[%s]", picServerAddressEditText));
			Log.info("Clicking on picServerAddressEditText ...");
			picServerAddressEditText.click();
			picServerAddressEditText.sendKeys(picServerAddress);
//		}

//		usernameEditText = (MobileElement) this.driver.findElement(By.xpath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[3]"));
//		if (usernameEditText != null) {
			Log.info(String.format("usernameEditText=[%s]", usernameEditText));
			Log.info("Clicking on usernameEditText ...");
			usernameEditText.click();
			usernameEditText.sendKeys(username);
//		}

		Log.info("Hiding keyboard, before clicking on Save button");
		((AppiumDriver)this.driver).hideKeyboard();

//		saveButton = (MobileElement) this.driver.findElement(By.xpath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"));
//		if (saveButton != null) {
			Log.info(String.format("saveButton=[%s]", saveButton));
			Log.info("Clicking on saveButton ...");
			saveButton.click();
//		}
	}

	public void clearSettings() {
		Log.method("clearSettings");
//		clearButton = (MobileElement) this.driver.findElement(By.xpath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]"));
//		if (clearButton != null) {
			clearButton.click();
//		}
	}

	@Override
	public Boolean screenLoadCondition() {
		saveButton = (MobileElement) this.driver.findElement(By.xpath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
		Log.info(String.format("Found saveButton - %s", (saveButton==null)?"NULL":saveButton.toString()));
		return saveButton!=null && saveButton.isDisplayed();
	}
}
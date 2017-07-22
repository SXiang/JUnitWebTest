package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidAddCgiFormDialog extends AndroidBaseScreen {

	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement oK;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement cancel;

	/****** Label elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement markerStatusLabel;

	/****** TextField elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement cgiTextField;

	public AndroidAddCgiFormDialog(WebDriver driver) {
		super(driver);
	}

	/****** Button Methods ******/

	public WebElement getCancelButton() {
		Log.method("getCancelButton");
		return cancel;
	}

	public void clickOnCancel() {
		Log.method("clickOnCancel");
		tap(getCancelButton());
	}

	public WebElement getOKButton() {
		Log.method("getOKButton");
		return oK;
	}

	public void clickOnOK() {
		Log.method("clickOnOK");
		tap(getOKButton());
	}

	/****** Label Methods ******/

	public String getMarkerStatusText() {
		Log.method("getMarkerStatusText");
		return markerStatusLabel.getText();
	}

	/****** TextField Methods ******/

	public String getCgiText() {
		Log.method("getCgiText");
		return cgiTextField.getText();
	}

	public void enterCgiText(String value) throws Exception {
		Log.method("enterCgiText");
		sendKeys(cgiTextField, value);
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		return getOKButton()!=null && getOKButton().isDisplayed();
	}
}
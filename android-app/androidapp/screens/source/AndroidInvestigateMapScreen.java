package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidInvestigateMapScreen extends AndroidBaseScreen {

	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[4]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement addCGI;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[4]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement addSource;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[5]/android.view.ViewGroup[1]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement markAsComplete;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[5]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement pause;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement directions;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement follow;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[4]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement investigate;

	/****** Label elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement markerInvestigationStatus;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView[3]")
	@CacheLookup
	private WebElement latitudeLongitude;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView[2]")
	@CacheLookup
	private WebElement precison;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView[4]")
	@CacheLookup
	private WebElement velocity;

	public AndroidInvestigateMapScreen(WebDriver driver) {
		super(driver);
	}

	/****** Button Methods ******/

	public WebElement getAddCGIButton() {
		Log.method("getAddCGIButton");
		return addCGI;
	}

	public void clickOnAddCGI() {
		Log.method("clickOnAddCGI");
		tap(getAddCGIButton());
	}

	public WebElement getAddSourceButton() {
		Log.method("getAddSourceButton");
		return addSource;
	}

	public void clickOnAddSource() {
		Log.method("clickOnAddSource");
		tap(getAddSourceButton());
	}

	public WebElement getMarkAsCompleteButton() {
		Log.method("getMarkAsCompleteButton");
		return markAsComplete;
	}

	public void clickOnMarkAsComplete() {
		Log.method("clickOnMarkAsComplete");
		tap(getMarkAsCompleteButton());
	}

	public WebElement getPauseButton() {
		Log.method("getPauseButton");
		return pause;
	}

	public void clickOnPause() {
		Log.method("clickOnPause");
		tap(getPauseButton());
	}

	public WebElement getDirectionsButton() {
		Log.method("getDirectionsButton");
		return directions;
	}

	public void clickOnDirections() {
		Log.method("clickOnDirections");
		tap(getDirectionsButton());
	}

	public WebElement getFollowButton() {
		Log.method("getFollowButton");
		return follow;
	}

	public void clickOnFollow() {
		Log.method("clickOnFollow");
		tap(getFollowButton());
	}

	public WebElement getInvestigateButton() {
		Log.method("getInvestigateButton");
		return investigate;
	}

	public void clickOnInvestigate() {
		Log.method("clickOnInvestigate");
		tap(getInvestigateButton());
	}

	/****** Label Methods ******/

	public String getMarkerInvestigationStatusText() {
		Log.method("getMarkerInvestigationStatusText");
		return markerInvestigationStatus.getText();
	}

	public String getLatitudeLongitudeText() {
		Log.method("getLatitudeLongitudeText");
		return latitudeLongitude.getText();
	}

	public String getPrecisonText() {
		Log.method("getPrecisonText");
		return precison.getText();
	}

	public String getVelocityText() {
		Log.method("getVelocityText");
		return velocity.getText();
	}

	@Override
	public Boolean screenLoadCondition() {
		return getAddSourceButton()!=null && getAddSourceButton().isDisplayed();
	}
}
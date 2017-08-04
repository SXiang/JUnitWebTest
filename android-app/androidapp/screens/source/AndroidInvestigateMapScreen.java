package androidapp.screens.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidInvestigateMapScreen extends AndroidBaseScreen {

	private static final String ADD_CGI_BTN_UISELECTOR = "new UiSelector().text(\"Add CGI\")";

	private static final String ADD_SOURCE_BTN_UISELECTOR = "new UiSelector().text(\"Add Source\")";

	private static final String MARK_AS_COMPLETE_BTN_UISELECTOR = "new UiSelector().text(\"Mark as Complete\")";

	private static final String PAUSE_BTN_UISELECTOR = "new UiSelector().text(\"Pause\")";

	private static final String DIRECTIONS_BTN_UISELECTOR = "new UiSelector().text(\"Directions\")";

	private static final String FOLLOW_BTN_UISELECTOR = "new UiSelector().text(\"Follow\")";

	private static final String INVESTIGATE_BTN_UISELECTOR = "new UiSelector().text(\"Investigate\")";

	/****** Button elements ******/

	@AndroidFindBy(uiAutomator = ADD_CGI_BTN_UISELECTOR)
	private WebElement addCGI;

	@AndroidFindBy(uiAutomator = ADD_SOURCE_BTN_UISELECTOR)
	private WebElement addSource;

	@AndroidFindBy(uiAutomator = MARK_AS_COMPLETE_BTN_UISELECTOR)
	private WebElement markAsComplete;

	@AndroidFindBy(uiAutomator = PAUSE_BTN_UISELECTOR)
	private WebElement pause;

	@AndroidFindBy(uiAutomator = DIRECTIONS_BTN_UISELECTOR)
	private WebElement directions;

	@AndroidFindBy(uiAutomator = FOLLOW_BTN_UISELECTOR)
	private WebElement follow;

	@AndroidFindBy(uiAutomator = INVESTIGATE_BTN_UISELECTOR)
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
	private WebElement precision;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView[4]")
	@CacheLookup
	private WebElement velocity;

	public AndroidInvestigateMapScreen(WebDriver driver) {
		super(driver);
	}

	/****** Button Methods ******/

	public WebElement getAddCGIButton() {
		Log.method("getAddCGIButton");
		addCGI = getAndroidDriver().findElementByAndroidUIAutomator(ADD_CGI_BTN_UISELECTOR);
		return addCGI;
	}

	public void clickOnAddCGI() {
		Log.method("clickOnAddCGI");
		tap(getAddCGIButton());
	}

	public WebElement getAddSourceButton() {
		Log.method("getAddSourceButton");
		addSource = getAndroidDriver().findElementByAndroidUIAutomator(ADD_SOURCE_BTN_UISELECTOR);
		return addSource;
	}

	public void clickOnAddSource() {
		Log.method("clickOnAddSource");
		tap(getAddSourceButton());
	}

	public WebElement getMarkAsCompleteButton() {
		Log.method("getMarkAsCompleteButton");
		markAsComplete = getAndroidDriver().findElementByAndroidUIAutomator(MARK_AS_COMPLETE_BTN_UISELECTOR);
		return markAsComplete;
	}

	public void clickOnMarkAsComplete() {
		Log.method("clickOnMarkAsComplete");
		tap(getMarkAsCompleteButton());
	}

	public WebElement getPauseButton() {
		Log.method("getPauseButton");
		pause = getAndroidDriver().findElementByAndroidUIAutomator(PAUSE_BTN_UISELECTOR);
		return pause;
	}

	public void clickOnPause() {
		Log.method("clickOnPause");
		tap(getPauseButton());
	}

	public WebElement getDirectionsButton() {
		Log.method("getDirectionsButton");
		directions = getAndroidDriver().findElementByAndroidUIAutomator(DIRECTIONS_BTN_UISELECTOR);
		return directions;
	}

	public void clickOnDirections() {
		Log.method("clickOnDirections");
		tap(getDirectionsButton());
	}

	public WebElement getFollowButton() {
		Log.method("getFollowButton");
		follow = getAndroidDriver().findElementByAndroidUIAutomator(FOLLOW_BTN_UISELECTOR);
		return follow;
	}

	public void clickOnFollow() {
		Log.method("clickOnFollow");
		tap(getFollowButton());
	}

	public WebElement getInvestigateButton() {
		Log.method("getInvestigateButton");
		investigate = getAndroidDriver().findElementByAndroidUIAutomator(INVESTIGATE_BTN_UISELECTOR);
		return investigate;
	}

	public void clickOnInvestigate() {
		Log.method("clickOnInvestigate");
		tap(getInvestigateButton());
	}

	/****** Label Methods ******/

	public String getMarkerInvestigationStatusText() {
		Log.method("getMarkerInvestigationStatusText");
		String xPathString = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView[1]";
		waitForElementToBeClickable(By.xpath(xPathString));
		markerInvestigationStatus = getAndroidDriver().findElementByXPath(xPathString);
		return markerInvestigationStatus.getText();
	}

	public String getLatitudeLongitudeText() {
		Log.method("getLatitudeLongitudeText");
		return latitudeLongitude.getText();
	}

	public String getPrecisonText() {
		Log.method("getPrecisonText");
		return precision.getText();
	}

	public String getVelocityText() {
		Log.method("getVelocityText");
		return velocity.getText();
	}

	public Boolean verifyMapIsShown() {
		Log.method("verifyMapIsShown");
		// TBD: To be implemented post image recognition sikuli prototype integrated in master.
		return false;
	}

	public Boolean waitForMarkAsCompleteButtonToBeDisplayed() {
		Log.method("waitForMarkAsCompleteToBeDisplayed");
		(new WebDriverWait(driver, Timeout.ANDROID_APP_SCREEN_ELEMENT_CHANGE_TIMEOUT)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return getMarkAsCompleteButton().isDisplayed();
			}
		});

		return true;
	}

	@Override
	public Boolean screenLoadCondition() {
		TestContext.INSTANCE.stayIdle(2);
		return getDirectionsButton()!=null && getDirectionsButton().isDisplayed();
	}

	@Override
	protected Integer getScreenLoadTimeout() {
		return 2 * Timeout.ANDROID_APP_SCREEN_LOAD_TIMEOUT;
	}
}
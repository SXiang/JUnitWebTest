package androidapp.screens.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.BaselineImages;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidInvestigateMapScreen extends AndroidBaseScreen {

	private static final String MARKER_INVESTIGATION_STATUS_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView[1]";
	private static final String FOOTER_INVESTIGATE_BUTTON_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[4]/android.view.ViewGroup[1]";

	private static final String ADD_CGI_BTN_UISELECTOR = "new UiSelector().text(\"Add CGI\")";

	private static final String ADD_SOURCE_BTN_UISELECTOR = "new UiSelector().text(\"Add Source\")";

	private static final String MARK_AS_COMPLETE_BTN_UISELECTOR = "new UiSelector().text(\"Mark as Complete\")";

	private static final String PAUSE_BTN_UISELECTOR = "new UiSelector().text(\"Pause\")";

	private static final String DIRECTIONS_BTN_UISELECTOR = "new UiSelector().text(\"Directions\")";

	private static final String FOLLOW_BTN_UISELECTOR = "new UiSelector().text(\"Follow\")";

	private static final String INVESTIGATE_BTN_UISELECTOR = "new UiSelector().text(\"Investigate\")";

	private static final String GOOGLE_MAPS_UI_SELECTOR = "new UiSelector().description(\"Google Map\")";

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

	/****** Map view ******/

	@AndroidFindBy(uiAutomator = GOOGLE_MAPS_UI_SELECTOR)
	private WebElement googleMapView;

	/****** Label elements ******/

	@AndroidFindBy(xpath = MARKER_INVESTIGATION_STATUS_XPATH)
	@CacheLookup
	private WebElement markerInvestigationStatus;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView[2]")
	@CacheLookup
	private WebElement latitudeLongitude;

	@AndroidFindBy(xpath = FOOTER_INVESTIGATE_BUTTON_XPATH)
	private WebElement footerInvestigateButton;

	public AndroidInvestigateMapScreen(WebDriver driver) {
		super(driver);
	}

	public void dismissPopup() {
		Log.method("dismissPopup");
		tap(getGoogleMapView());
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

	public WebElement getFooterInvestigateButton() {
		Log.method("getFooterInvestigateButton");
		footerInvestigateButton = getAndroidDriver().findElementByXPath(FOOTER_INVESTIGATE_BUTTON_XPATH);
		return footerInvestigateButton;
	}

	public void clickOnFooterInvestigate() {
		Log.method("clickOnFooterInvestigate");
		tap(getFooterInvestigateButton());
	}

	/****** Label Methods ******/

	public String getMarkerInvestigationStatusText() {
		Log.method("getMarkerInvestigationStatusText");
		String xPathString = MARKER_INVESTIGATION_STATUS_XPATH;
		waitForElementToBeClickable(By.xpath(xPathString));
		markerInvestigationStatus = getAndroidDriver().findElementByXPath(xPathString);
		return markerInvestigationStatus.getText();
	}

	public String getLatitudeLongitudeText() {
		Log.method("getLatitudeLongitudeText");
		return latitudeLongitude.getText();
	}

	public WebElement getGoogleMapView() {
		return googleMapView;
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

	// To be enabled post appium java client issue resolved. refer comments in MobileActions zoomIn method.
	private void applyMapZoomIn(int delta) {
		Log.method("applyMapZoomIn", delta);
		//MobileActions.newAction(getAndroidDriver()).zoomIn(getGoogleMapView(), ZoomDirection.HORIZONTAL, delta);
	}

	// To be enabled post appium java client issue resolved. refer comments in MobileActions zoomOut method.
	private void applyMapZoomOut(int delta) {
		Log.method("applyMapZoomOut", delta);
		//getGoogleMapView().click();
		//MobileActions.newAction(getAndroidDriver()).zoomOut(getGoogleMapView(), ZoomDirection.HORIZONTAL, delta);
	}

	public void assertMapShowsPicarroUserCurrentLocation() {
		Log.method("assertMapShowsPicarroUserCurrentLocation");
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.LOADERS, BaselineImages.ImageFile.DefaultMapScreenPicarroLoc);
		} else {
			Log.info("Skipping map shows Picarro user current location verification. Run test targetting backpack simulator to enable this verification");
		}
	}

	public void assertFollowButtonStateIsSelected() {
		Log.method("assertFollowButtonStateIsSelected");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.FollowButtonWhenSelected);
	}

	public void assertFollowButtonStateIsNotSelected() {
		Log.method("assertFollowButtonStateIsNotSelected");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.FollowButtonNotSelected);
	}

	public void assertMarkAsCompleteAndPauseButtonsAreShown() {
		Log.method("assertMarkAsCompleteAndPauseButtonsAreShown");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.MarkAsCompleteAndPauseButtons);
	}

	public void assertPipesAndMarkerShownAreCorrect(String baseFolder, String imageName) {
		Log.method("assertPipesAndMarkerShownAreCorrect");
		screenVerifier.assertImageFoundOnScreen(this, baseFolder, imageName);
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidInvestigateMapScreen.screenLoadCondition");
		return getDirectionsButton()!=null && getDirectionsButton().isDisplayed() && waitForProgressComplete();
	}

	@Override
	protected Integer getScreenLoadTimeout() {
		return 2 * Timeout.ANDROID_APP_SCREEN_LOAD_TIMEOUT;
	}
}
package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidAlarmSettingsScreen extends AndroidBaseScreen {

	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[5]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement apply;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[5]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement close;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement investigate;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement mode_HR;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement resetMax;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement toggleMode;

	/****** Slider elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[4]")
	@CacheLookup
	private WebElement amplitudeppm;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup[4]")
	@CacheLookup
	private WebElement thresholdppm;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]")
	@CacheLookup
	private WebElement volume;

	/****** Slider container elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement amplitudeppmContainer;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement thresholdppmContainer;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement volumeContainer;

	public AndroidAlarmSettingsScreen(WebDriver driver) {
		super(driver);
	}

	/****** Button Methods ******/

	public WebElement getApply() {
		Log.method("getApply");
		return apply;
	}

	public void clickOnApply() {
		Log.method("clickOnApply");
		tap(apply);
	}

	public WebElement getClose() {
		Log.method("getClose");
		return close;
	}

	public void clickOnClose() {
		Log.method("clickOnClose");
		tap(close);
	}

	public WebElement getInvestigate() {
		Log.method("getInvestigate");
		return investigate;
	}

	public void clickOnInvestigate() {
		Log.method("clickOnInvestigate");
		tap(investigate);
	}

	public WebElement getMode_HR() {
		Log.method("getMode_HR");
		return mode_HR;
	}

	public void clickOnMode_HR() {
		Log.method("clickOnMode_HR");
		tap(mode_HR);
	}

	public WebElement getResetMax() {
		Log.method("getResetMax");
		return resetMax;
	}

	public void clickOnResetMax() {
		Log.method("clickOnResetMax");
		tap(resetMax);
	}

	public WebElement getToggleMode() {
		Log.method("getToggleMode");
		return toggleMode;
	}

	public void clickOnToggleMode() {
		Log.method("clickOnToggleMode");
		tap(toggleMode);
	}

	/****** Slider Methods ******/

	public void slideByAmplitudeppm(Integer percValue) {
		Log.method("slideByAmplitudeppm");
		slideBy(amplitudeppm, amplitudeppmContainer, percValue);
	}

	public Integer getAmplitudeppmSliderLocationX() {
		Log.method("getAmplitudeppmSliderLocationX");
		return amplitudeppm.getLocation().getX();
	}

	public Integer getAmplitudeppmSliderLocationY() {
		Log.method("getAmplitudeppmSliderLocationY");
		return amplitudeppm.getLocation().getY();
	}

	public void slideByThresholdppm(Integer percValue) {
		Log.method("slideByThresholdppm");
		slideBy(thresholdppm, thresholdppmContainer, percValue);
	}

	public Integer getThresholdppmSliderLocationX() {
		Log.method("getThresholdppmSliderLocationX");
		return thresholdppm.getLocation().getX();
	}

	public Integer getThresholdppmSliderLocationY() {
		Log.method("getThresholdppmSliderLocationY");
		return thresholdppm.getLocation().getY();
	}

	public void slideByVolume(Integer percValue) {
		Log.method("slideByVolume");
		slideBy(volume, volumeContainer, percValue);
	}

	public Integer getVolumeSliderLocationX() {
		Log.method("getVolumeSliderLocationX");
		return volume.getLocation().getX();
	}

	public Integer getVolumeSliderLocationY() {
		Log.method("getVolumeSliderLocationY");
		return volume.getLocation().getY();
	}

	/****** Slider Container Methods ******/

	public Integer getAmplitudeppmContainerSliderWidth() {
		Log.method("getAmplitudeppmContainerSliderWidth");
		return amplitudeppmContainer.getSize().getWidth();
	}

	public Integer getThresholdppmContainerSliderWidth() {
		Log.method("getThresholdppmContainerSliderWidth");
		return thresholdppmContainer.getSize().getWidth();
	}

	public Integer getVolumeContainerSliderWidth() {
		Log.method("getVolumeContainerSliderWidth");
		return volumeContainer.getSize().getWidth();
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		return mainFrameLayout!=null && mainFrameLayout.isDisplayed();
	}
}

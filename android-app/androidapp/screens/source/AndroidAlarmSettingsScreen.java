package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.BaselineImages;
import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidAlarmSettingsScreen extends AndroidBaseScreen {
	private static final String APPLY_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Apply\")";
	private static final String CLOSE_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Close\")";

	public enum SliderType {
		Amplitude ("Amplitude"),
		Volume ("Volume"),
		Threshold ("Threshold");

		private final String name;

		SliderType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}


	public static class SliderTransform {
		public static final Float AMPLITUDE_MIN = 0.0f;
		public static final Float AMPLITUDE_MAX = 1000.0f;
		public static final Float VOLUME_MIN = 0.0f;
		public static final Float VOLUME_MAX = 31.0f;
		public static final Float THRESHOLD_MIN = 0.1f;
		public static final Float THRESHOLD_MAX = 100.0f;

		public static Float getPercentValue(SliderType sliderType, Float absoluteValue) {
			Log.method("getPercentValue", sliderType.toString(), absoluteValue);
			Float percent = 0.0f;
			Float range = 0.0f;
			Float adjustedAbsValue = absoluteValue;
			if (sliderType.equals(SliderType.Volume)) {
				range = VOLUME_MAX - VOLUME_MIN;
				adjustedAbsValue -= VOLUME_MIN;
			} else if (sliderType.equals(SliderType.Threshold)) {
				range = THRESHOLD_MAX - THRESHOLD_MIN;
				adjustedAbsValue -= THRESHOLD_MIN;
			} else if (sliderType.equals(SliderType.Amplitude)) {
				range = AMPLITUDE_MAX - AMPLITUDE_MIN;
				adjustedAbsValue -= AMPLITUDE_MIN;
			}

			percent = (adjustedAbsValue * 100.0f)/range;
			Log.info(String.format("Percent value is -> %f", percent));
			return percent;
		}
	}

	/****** Button elements ******/

	@AndroidFindBy(uiAutomator = APPLY_BUTTON_UI_SELECTOR)
	private WebElement apply;

	@AndroidFindBy(uiAutomator = CLOSE_BUTTON_UI_SELECTOR)
	private WebElement close;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[4]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement investigate;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[2]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement resetMax;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[1]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement toggleMode;

	/****** Slider elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]")
	@CacheLookup
	private WebElement volume;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[4]")
	@CacheLookup
	private WebElement amplitudeppm;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup[4]")
	@CacheLookup
	private WebElement thresholdppm;

	/****** Slider container elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement volumeContainer;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement amplitudeppmContainer;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement thresholdppmContainer;

	/****** Slider left delta elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement volumeLeftDelta;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement amplitudeppmLeftDelta;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement thresholdPpmLeftDelta;

	public AndroidAlarmSettingsScreen(WebDriver driver) {
		super(driver);
	}

	/****** Button Methods ******/

	public WebElement getApply() {
		Log.method("getApply");
		apply = getAndroidDriver().findElementByAndroidUIAutomator(APPLY_BUTTON_UI_SELECTOR);
		return apply;
	}

	public void clickOnApply() {
		Log.method("clickOnApply");
		tap(apply);
	}

	public WebElement getClose() {
		Log.method("getClose");
		close = getAndroidDriver().findElementByAndroidUIAutomator(CLOSE_BUTTON_UI_SELECTOR);
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

	public void slideToThresholdppm(Float value) {
		Log.method("slideToThresholdppm");
		slideBy(thresholdppm, thresholdppmContainer, SliderTransform.getPercentValue(SliderType.Threshold, value));
	}

	public Integer getThresholdppmSliderLocationX() {
		Log.method("getThresholdppmSliderLocationX");
		return thresholdppm.getLocation().getX();
	}

	public Integer getThresholdppmSliderLocationY() {
		Log.method("getThresholdppmSliderLocationY");
		return thresholdppm.getLocation().getY();
	}

	public void slideToVolume(Float value) {
		Log.method("slideToVolume");
		slideBy(volume, volumeContainer, SliderTransform.getPercentValue(SliderType.Volume, value));
	}

	public Integer getVolumeSliderLocationX() {
		Log.method("getVolumeSliderLocationX");
		return volume.getLocation().getX();
	}

	public Integer getVolumeSliderLocationY() {
		Log.method("getVolumeSliderLocationY");
		return volume.getLocation().getY();
	}

	public void slideToAmplitudeppm(Float value) {
		Log.method("slideToAmplitudeppm");
		slideBy(amplitudeppm, amplitudeppmContainer, SliderTransform.getPercentValue(SliderType.Amplitude, value));
	}

	public Integer getAmplitudeppmSliderLocationX() {
		Log.method("getAmplitudeppmSliderLocationX");
		return amplitudeppm.getLocation().getX();
	}

	public Integer getAmplitudeppmSliderLocationY() {
		Log.method("getAmplitudeppmSliderLocationY");
		return amplitudeppm.getLocation().getY();
	}

	/****** Slider Container Methods ******/

	public Integer getThresholdppmContainerSliderWidth() {
		Log.method("getThresholdppmContainerSliderWidth");
		return thresholdppmContainer.getSize().getWidth();
	}

	public Integer getVolumeContainerSliderWidth() {
		Log.method("getVolumeContainerSliderWidth");
		return volumeContainer.getSize().getWidth();
	}

	public Integer getAmplitudeppmContainerSliderWidth() {
		Log.method("getAmplitudeppmContainerSliderWidth");
		return amplitudeppmContainer.getSize().getWidth();
	}

	/****** Slider Left Delta Methods ******/

	public Integer getThresholdPpmLeftDeltaWidth() {
		Log.method("getThresholdPpmLeftDeltaWidth");
		return thresholdPpmLeftDelta.getSize().getWidth();
	}

	public Integer getVolumeLeftDeltaWidth() {
		Log.method("getVolumeLeftDeltaWidth");
		return volumeLeftDelta.getSize().getWidth();
	}

	public Integer getAmplitudeppmLeftDeltaWidth() {
		Log.method("getAmplitudeppmLeftDeltaWidth");
		return amplitudeppmLeftDelta.getSize().getWidth();
	}

	public void assertSlidersShownAreCorrect() {
		Log.method("assertSlidersShownAreCorrect");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.VolumeAmplitudeThresholdSliders);
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidAlarmSettingsDialog.screenLoadCondition");
		return thresholdppm!=null && thresholdppm.isDisplayed();
	}
}

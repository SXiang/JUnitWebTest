package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidAlarmSettingsScreen extends AndroidBaseScreen {
	public enum SliderType {
		Volume ("Volume"),
		Amplitude ("Amplitude"),
		Threshold ("Threshold"),
		Background ("Background");

		private final String name;

		SliderType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}


	public static class SliderTransform {
		public static final Float VOLUME_MIN = 0.0f;
		public static final Float VOLUME_MAX = 31.0f;
		public static final Float AMPLITUDE_MIN = 0.0f;
		public static final Float AMPLITUDE_MAX = 1000.0f;
		public static final Float THRESHOLD_MIN = 0.05f;
		public static final Float THRESHOLD_MAX = 100.0f;
		public static final Float BACKGROUND_MIN = 1.0f;
		public static final Float BACKGROUND_MAX = 10.0f;

		public static Float getPercentValue(SliderType sliderType, Float absoluteValue) {
			Log.method("getPercentValue", sliderType.toString(), absoluteValue);
			Float percent = 0.0f;
			Float range = 0.0f;
			Float adjustedAbsValue = absoluteValue;
			if (sliderType.equals(SliderType.Volume)) {
				range = VOLUME_MAX - VOLUME_MIN;
				adjustedAbsValue -= VOLUME_MIN;
			} else if (sliderType.equals(SliderType.Amplitude)) {
				range = AMPLITUDE_MAX - AMPLITUDE_MIN;
				adjustedAbsValue -= AMPLITUDE_MIN;
			} else if (sliderType.equals(SliderType.Threshold)) {
				range = THRESHOLD_MAX - THRESHOLD_MIN;
				adjustedAbsValue -= THRESHOLD_MIN;
			} else if (sliderType.equals(SliderType.Background)) {
				range = BACKGROUND_MAX - BACKGROUND_MIN;
				adjustedAbsValue -= BACKGROUND_MIN;
			}

			percent = (adjustedAbsValue * 100.0f)/range;
			Log.info(String.format("Percent value is -> %f", percent));
			return percent;
		}
	}


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

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]")
	@CacheLookup
	private WebElement volume;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[4]")
	@CacheLookup
	private WebElement amplitudeppm;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup[4]")
	@CacheLookup
	private WebElement thresholdppm;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]/android.view.ViewGroup[4]")
	@CacheLookup
	private WebElement backgroundDuration;

	/****** Slider container elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement volumeContainer;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement amplitudeppmContainer;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement thresholdppmContainer;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]")
	@CacheLookup
	private WebElement backgroundDurationContainer;

	/****** Slider left delta elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement amplitudePpmLeftDelta;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement backgroundDurationLeftDelta;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement thresholdPpmLeftDelta;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement volumeLeftDelta;

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
		volume = getAndroidDriver().findElementByXPath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]");
		volumeContainer = getAndroidDriver().findElementByXPath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]");
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

	public void slideToBackgroundDuration(Float value) {
		Log.method("slideToBackgroundDuration");
		slideBy(backgroundDuration, backgroundDurationContainer, SliderTransform.getPercentValue(SliderType.Background, value));
	}

	public Integer getBackgroundDurationSliderLocationX() {
		Log.method("getBackgroundDurationSliderLocationX");
		return backgroundDuration.getLocation().getX();
	}

	public Integer getBackgroundDurationSliderLocationY() {
		Log.method("getBackgroundDurationSliderLocationY");
		return backgroundDuration.getLocation().getY();
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

	public Integer getBackgroundduration5sContainerSliderWidth() {
		Log.method("getBackgroundduration5sContainerSliderWidth");
		return backgroundDurationContainer.getSize().getWidth();
	}

	/****** Slider Left Delta Methods ******/

	public Integer getamplitudePpmLeftDeltaWidth() {
		Log.method("getamplitudePpmLeftDeltaWidth");
		return amplitudePpmLeftDelta.getSize().getWidth();
	}

	public Integer getBackgrounddurationLeftDeltaWidth() {
		Log.method("getBackgrounddurationLeftDeltaWidth");
		return backgroundDurationLeftDelta.getSize().getWidth();
	}

	public Integer getThresholdPpmLeftDeltaWidth() {
		Log.method("getThresholdPpmLeftDeltaWidth");
		return thresholdPpmLeftDelta.getSize().getWidth();
	}

	public Integer getVolumeLeftDeltaWidth() {
		Log.method("getVolumeLeftDeltaWidth");
		return volumeLeftDelta.getSize().getWidth();
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		return amplitudeppm!=null && amplitudeppm.isDisplayed();
	}
}

package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.BaselineImages;
import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidAlarmSettingsScreen extends AndroidBaseScreen {
	public enum SliderType {
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
		public static final Float VOLUME_MIN = 0.0f;
		public static final Float VOLUME_MAX = 31.0f;
		public static final Float THRESHOLD_MIN = 0.05f;
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
			}

			percent = (adjustedAbsValue * 100.0f)/range;
			Log.info(String.format("Percent value is -> %f", percent));
			return percent;
		}
	}


	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement apply;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement close;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement investigate;

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
	private WebElement thresholdppm;

	/****** Slider container elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement volumeContainer;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement thresholdppmContainer;

	/****** Slider left delta elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement volumeLeftDelta;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement thresholdPpmLeftDelta;

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

	/****** Slider Container Methods ******/

	public Integer getThresholdppmContainerSliderWidth() {
		Log.method("getThresholdppmContainerSliderWidth");
		return thresholdppmContainer.getSize().getWidth();
	}

	public Integer getVolumeContainerSliderWidth() {
		Log.method("getVolumeContainerSliderWidth");
		return volumeContainer.getSize().getWidth();
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

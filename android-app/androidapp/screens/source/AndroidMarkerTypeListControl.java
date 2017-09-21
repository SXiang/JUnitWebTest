package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidMarkerTypeListControl extends AndroidBaseScreen {

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"LISA\")")
	private WebElement lisaListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Gap\")")
	private WebElement gapListItem;

	public AndroidMarkerTypeListControl(WebDriver driver) {
		super(driver);
	}

	public enum MarkerType {
		LISA ("LISA"),
		Gap ("Gap");

		private final String name;

		MarkerType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public void selectMarkerType(MarkerType markerType) {
		Log.method("selectMarkerType", markerType.toString());
		if (markerType == MarkerType.LISA) {
			lisaListItem.click();
		} else if (markerType == MarkerType.Gap) {
			gapListItem.click();
		}
	}
}

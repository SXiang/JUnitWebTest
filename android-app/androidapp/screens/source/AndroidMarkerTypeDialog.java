package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import common.source.TestContext;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidMarkerTypeDialog extends AndroidBaseScreen {

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"LISA\")")
	private WebElement lisaListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Gap\")")
	private WebElement gapListItem;

	public AndroidMarkerTypeDialog(WebDriver driver) {
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

		// allow time for dropdown value to get selected on screen.
		TestContext.INSTANCE.stayIdle(3);
	}
}

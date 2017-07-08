package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import common.source.TestContext;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidLeakTypeListControl extends AndroidBaseScreen {

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Above_Ground\")")
	private WebElement above_GroundListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Below_Ground\")")
	private WebElement below_GroundListItem;

	public AndroidLeakTypeListControl(WebDriver driver) {
		super(driver);
	}

	public enum LeakType {
		Above_Ground ("Above_Ground"),
		Below_Ground ("Below_Ground");
		private final String name;

		LeakType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public void selectLeakType(LeakType leakType) {
		Log.method("selectLeakType", leakType.toString());
		if (leakType == LeakType.Above_Ground) {
			above_GroundListItem.click();
		} else if (leakType == LeakType.Below_Ground) {
			below_GroundListItem.click();
		}

		// allow time for dropdown value to get selected on screen.
		TestContext.INSTANCE.stayIdle(2);
	}
}

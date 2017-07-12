package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import common.source.TestContext;
import io.appium.java_client.pagefactory.AndroidFindBy;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakType;

public class AndroidLeakTypeListControl extends AndroidBaseScreen {

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Above_Ground\")")
	private WebElement above_GroundListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Below_Ground\")")
	private WebElement below_GroundListItem;

	public AndroidLeakTypeListControl(WebDriver driver) {
		super(driver);
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

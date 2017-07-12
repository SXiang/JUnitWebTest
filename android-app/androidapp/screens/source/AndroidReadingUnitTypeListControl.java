package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import common.source.TestContext;
import io.appium.java_client.pagefactory.AndroidFindBy;
import surveyor.scommon.mobile.source.LeakDataTypes.ReadingUnitType;

public class AndroidReadingUnitTypeListControl extends AndroidBaseScreen {

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"None\")")
	private WebElement noneListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"PPM\")")
	private WebElement pPMListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"LEL\")")
	private WebElement lELListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"% Gas\")")
	private WebElement _GasListItem;

	public AndroidReadingUnitTypeListControl(WebDriver driver) {
		super(driver);
	}

	public void selectReadingUnitType(ReadingUnitType readingUnitType) {
		Log.method("selectReadingUnitType", readingUnitType.toString());
		if (readingUnitType == ReadingUnitType.None) {
			noneListItem.click();
		} else if (readingUnitType == ReadingUnitType.PPM) {
			pPMListItem.click();
		} else if (readingUnitType == ReadingUnitType.LEL) {
			lELListItem.click();
		} else if (readingUnitType == ReadingUnitType.PctGas) {
			_GasListItem.click();
		}

		// allow time for dropdown value to get selected on screen.
		TestContext.INSTANCE.stayIdle(2);
	}
}
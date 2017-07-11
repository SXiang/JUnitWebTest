package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import common.source.TestContext;
import io.appium.java_client.pagefactory.AndroidFindBy;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;

public class AndroidLeakSourceTypeListControl extends AndroidBaseScreen {

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Gas\")")
	private WebElement gasListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sewer\")")
	private WebElement sewerListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Catch_Basin\")")
	private WebElement catch_BasinListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Landfill\")")
	private WebElement landfillListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Swamp\")")
	private WebElement swampListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Customer\")")
	private WebElement customerListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Other_Enclosure\")")
	private WebElement other_EnclosureListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Other_Natural_Source\")")
	private WebElement other_Natural_SourceListItem;

	public AndroidLeakSourceTypeListControl(WebDriver driver) {
		super(driver);
	}

	public void selectLeakSourceType(LeakSourceType leakSourceType) {
		Log.method("selectLeakSourceType", leakSourceType.toString());
		if (leakSourceType == LeakSourceType.Gas) {
			gasListItem.click();
		} else if (leakSourceType == LeakSourceType.Sewer) {
			sewerListItem.click();
		} else if (leakSourceType == LeakSourceType.Catch_Basin) {
			catch_BasinListItem.click();
		} else if (leakSourceType == LeakSourceType.Landfill) {
			landfillListItem.click();
		} else if (leakSourceType == LeakSourceType.Swamp) {
			swampListItem.click();
		} else if (leakSourceType == LeakSourceType.Customer) {
			customerListItem.click();
		} else if (leakSourceType == LeakSourceType.Other_Enclosure) {
			other_EnclosureListItem.click();
		} else if (leakSourceType == LeakSourceType.Other_Natural_Source) {
			other_Natural_SourceListItem.click();
		}

		// allow time for dropdown value to get selected on screen.
		TestContext.INSTANCE.stayIdle(2);
	}
}

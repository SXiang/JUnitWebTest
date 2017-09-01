package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import common.source.TestContext;
import io.appium.java_client.pagefactory.AndroidFindBy;
import surveyor.scommon.mobile.source.LeakDataTypes.SurfaceOverLeakType;

public class AndroidSurfaceOverLeakTypeListControl extends AndroidBaseScreen {

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Above_Ground\")")
	private WebElement above_GroundListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Concrete\")")
	private WebElement concreteListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Un surfaced\")")
	private WebElement un_surfacedListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Tar Component\")")
	private WebElement tar_ComponentListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"In Substructure\")")
	private WebElement in_SubstructureListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Other\")")
	private WebElement otherListItem;

	public AndroidSurfaceOverLeakTypeListControl(WebDriver driver) {
		super(driver);
	}

	public void selectSurfaceOverLeakType(SurfaceOverLeakType surfaceOverLeakType) {
		Log.method("selectSurfaceOverLeakType", surfaceOverLeakType.toString());
		if (surfaceOverLeakType == SurfaceOverLeakType.Above_Ground) {
			above_GroundListItem.click();
		} else if (surfaceOverLeakType == SurfaceOverLeakType.Concrete) {
			concreteListItem.click();
		} else if (surfaceOverLeakType == SurfaceOverLeakType.Un_surfaced) {
			un_surfacedListItem.click();
		} else if (surfaceOverLeakType == SurfaceOverLeakType.Tar_Component) {
			tar_ComponentListItem.click();
		} else if (surfaceOverLeakType == SurfaceOverLeakType.In_Substructure) {
			in_SubstructureListItem.click();
		} else if (surfaceOverLeakType == SurfaceOverLeakType.Other) {
			otherListItem.click();
		}

		// allow time for dropdown value to get selected on screen.
		TestContext.INSTANCE.stayIdle(2);
	}
}
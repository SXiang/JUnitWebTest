package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import common.source.TestContext;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidLeakLocationTypeListControl extends AndroidBaseScreen {

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Main\")")
	private WebElement mainListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Service\")")
	private WebElement serviceListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Service_T\")")
	private WebElement service_TListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Service_Branch\")")
	private WebElement service_BranchListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Riser\")")
	private WebElement riserListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Meter_Set\")")
	private WebElement meter_SetListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Customer_Equipment\")")
	private WebElement customer_EquipmentListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sewer_Manhole\")")
	private WebElement sewer_ManholeListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Catch_Basin\")")
	private WebElement catch_BasinListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Substructure\")")
	private WebElement substructureListItem;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Other\")")
	private WebElement otherListItem;

	public AndroidLeakLocationTypeListControl(WebDriver driver) {
		super(driver);
	}

	public enum LeakLocationType {
		Main ("Main"),
		Service ("Service"),
		Service_T ("Service_T"),
		Service_Branch ("Service_Branch"),
		Riser ("Riser"),
		Meter_Set ("Meter_Set"),
		Customer_Equipment ("Customer_Equipment"),
		Sewer_Manhole ("Sewer_Manhole"),
		Catch_Basin ("Catch_Basin"),
		Substructure ("Substructure"),
		Other ("Other");
		private final String name;

		LeakLocationType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public void selectLeakLocationType(LeakLocationType leakLocationType) {
		Log.method("selectLeakLocationType", leakLocationType.toString());
		if (leakLocationType == LeakLocationType.Main) {
			mainListItem.click();
		} else if (leakLocationType == LeakLocationType.Service) {
			serviceListItem.click();
		} else if (leakLocationType == LeakLocationType.Service_T) {
			service_TListItem.click();
		} else if (leakLocationType == LeakLocationType.Service_Branch) {
			service_BranchListItem.click();
		} else if (leakLocationType == LeakLocationType.Riser) {
			riserListItem.click();
		} else if (leakLocationType == LeakLocationType.Meter_Set) {
			meter_SetListItem.click();
		} else if (leakLocationType == LeakLocationType.Customer_Equipment) {
			customer_EquipmentListItem.click();
		} else if (leakLocationType == LeakLocationType.Sewer_Manhole) {
			sewer_ManholeListItem.click();
		} else if (leakLocationType == LeakLocationType.Catch_Basin) {
			catch_BasinListItem.click();
		} else if (leakLocationType == LeakLocationType.Substructure) {
			substructureListItem.click();
		} else if (leakLocationType == LeakLocationType.Other) {
			otherListItem.click();
		}

		// allow time for dropdown value to get selected on screen.
		TestContext.INSTANCE.stayIdle(2);
	}
}
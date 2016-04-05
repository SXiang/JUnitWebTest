package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.OLMapUtility;
import common.source.BrowserCommands;
import common.source.TestContext;
import common.source.OLMapUtility.IconColor;
import common.source.TestSetup;
import surveyor.scommon.source.BaseMapViewPage.DisplaySwitchType;
import surveyor.scommon.source.BaseMapViewPage.GisSwitchType;
import surveyor.scommon.source.BaseMapViewPage.MapSwitchType;
import surveyor.scommon.source.ReportsBasePage;
import surveyor.scommon.actions.data.DriverViewDataReader.DriverViewDataRow;
import surveyor.scommon.source.BaseMapViewPage;

public class BaseReportsPageActions extends BasePageActions {
	
	private static final String CLS_REPORTS_PAGE_ACTIONS = "BaseMapViewPageActions::";
	
	protected ReportsBasePage pageObject;
	
	public BaseReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
	}

	public void initializePageObject(WebDriver driver, ReportsBasePage pageObj) {
		setPageObject(pageObj);
		PageFactory.initElements(driver, getPageObject());
	}

	public ReportsBasePage getPageObject() {
		return pageObject;
	}

	public void setPageObject(ReportsBasePage reportsBasePage) {
		this.pageObject = reportsBasePage;
	}
}
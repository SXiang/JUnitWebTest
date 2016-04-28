package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;
import surveyor.scommon.source.ReportsBasePage;

public class BaseReportsPageActions extends BasePageActions {
	
	public BaseReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
	}

	public void initializePageObject(WebDriver driver, ReportsBasePage pageObj) {
		setPageObject(pageObj);
		PageFactory.initElements(driver, getPageObject());
	}

	public ReportsBasePage getReportsBasePageObject() {
		return (ReportsBasePage) pageObject;
	}

	public void setPageObject(ReportsBasePage reportsBasePage) {
		this.pageObject = reportsBasePage;
	}
}
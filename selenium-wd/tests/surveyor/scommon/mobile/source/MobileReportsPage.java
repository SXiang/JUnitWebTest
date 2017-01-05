/**
 *
 */
package surveyor.scommon.mobile.source;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import common.source.Log;

/**
 * @author sxiang
 *
 */
public class MobileReportsPage extends MobileBasePage {
	public static final String STRURLPath = "/Investigation/Reports";

	/* Reports Filter */
	@FindBy(css = "[id='datatable_filter'] input[type='search']")
	private WebElement reportsSearchField;
	
	/* Reports Table */
	@FindBy(css = "table[id='datatable']")
	private WebElement dataTable;
	@FindBy(css = "table[id='datatable'] > tbody >tr")
	private List<WebElement> tableRow;
	@FindBy(css = "table[id='datatable'] > thead > tr > th")
	private List<WebElement> tableHeader;

	/* Reports Pagination */
	@FindBy(css = "[id='datatable_paginate'] > a[id='datatable_next']")
	private WebElement nextButton;
	@FindBy(css = "[id='datatable_paginate'] > a[id='datatable_previous']")
	private WebElement previousButton;
	@FindBy(css = "[id='datatable_paginate'] > a[id='datatable_last]")
	private WebElement lastButton;
	@FindBy(css = "[id='datatable_paginate'] > a[id='datatable_first']")
	private WebElement firstButton;
	@FindBy(css = "[id='datatable_paginate'] a.paginate_button.current")
	private WebElement currentButton;
	
	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public MobileReportsPage() {
		super(STRURLPath);
		pageKey = By.id("dropdownMenu");
		Log.info("\nThe Reports Page URL is: " + this.strPageURL);
	}

	@Override
	public void open() {
		driver.get(strPageURL);
		waitUntilPageLoad();
	}
	
	public boolean checkVisibilityForUser(String loginUser) {
		if (!this.picarroLogo.isDisplayed()){
			Log.error("Not found - picarro logo");
			return false;
		}

		openDropdownMenu();
		if (!this.linkDashboard.isDisplayed()){
			Log.error("Not found - link to Dashboard");
			return false;
		}

		if (!this.linkLogOut.isDisplayed()){
			Log.error("Not found - link to Log Out");
			return false;
		}
		
		closeDropdownMenu();
		return true;
	}
}
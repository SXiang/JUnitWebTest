/**
 *
 */
package surveyor.scommon.mobile.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.source.BasePage;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;

/**
 * @author sxiang
 *
 */
public class MobileBasePage extends BasePage {

	@FindBy(how = How.ID, using = "mobile_menu_dropdown")
	protected WebElement picarroLogo;

	@FindBy(how = How.ID, using = "dropdownMenu")
	protected WebElement dropdownMenu;

	@FindBy(how = How.XPATH, using = "//*[@id='user-logout']/a")
	protected WebElement linkLogOut;

	@FindBy(how = How.XPATH, using = "//*[@id='home']/a")
	protected WebElement linkDashboard;

	protected By pageKey;

	public MobileBasePage(String strUrlPath){
		super(TestContext.INSTANCE.getAppiumDriver(), TestContext.INSTANCE.getTestSetup(),
				TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getBaseUrl()+strUrlPath);
		PageFactory.initElements(driver, this);
	}

	public boolean waitUntilPageLoad(){
		return waitUntilPageLoad(pageKey);
	}
	
	public boolean waitUntilPageLoad(By pageKey){
		WebElement pageKeyElement = null;
		try{
			pageKeyElement = waitUntilPresenceOfElementLocated(pageKey);
		}catch(Exception e){
			Log.error("Expected page is not loaded successfully: "+e.toString());
		}
		return pageKeyElement != null;
	}
	
	public MobileLoginPage logout() {
		Log.method("logout");
		return logout(false);
	}

	private MobileLoginPage logout(boolean failOnError) {
		Log.method("logout", failOnError);
		try {
			openDropdownMenu();
			Log.clickElementInfo("Log Out",ElementType.LINK);
			this.linkLogOut.click();
		} catch (Exception e) {
			if (failOnError) {
				throw e;
			} else {
				Log.warn(String.format("Exception when calling logout : %s", ExceptionUtility.getStackTraceString(e)));
			}
		}

		MobileLoginPage loginPage = new MobileLoginPage();
		return loginPage;
	}

	public boolean openDropdownMenu(){
		if(!isMobileMenuExpanded()){
			dropdownMenu.click();
		}
		return true;
	}
	
	public boolean closeDropdownMenu(){
		if(isMobileMenuExpanded()){
			dropdownMenu.click();
		}
		return true;
	}
	
	public boolean isMobileMenuExpanded(){
		return Boolean.valueOf(getElementAttribute(dropdownMenu, "aria-expanded"));
	}
	
	
}
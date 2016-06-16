/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.BasePage;
import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

/**
 * @author zlu
 *
 */
public class PrimePage extends BasePage {
	
	public static final String STRURLPath = "/Prime";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.Constant_Index); //The title should be changed soon otherwise submit a bug for it
	
	@FindBy(how = How.CSS, using = "#bottom_button_side_panel > div")
	private WebElement btnSidePanel;
	
	@FindBy(how = How.ID, using = "switch_peaks")
	private WebElement btnPeakBubblesSwitch;

	/**
	 * @param driver
	 * @param pageTitle
	 */
	public PrimePage(WebDriver driver, String baseURL, TestSetup testSetup) {
		
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		Log.info("\nThe Prime Page URL is: " + this.strPageURL);
	}
	
	public void browsingSettingControls() {
		Log.clickElementInfo("Side Panel",ElementType.LINK);
		this.btnSidePanel.click();		
		this.testSetup.slowdownInSeconds(3);
		Log.clickElementInfo("Side Panel",ElementType.LINK);
		this.btnSidePanel.click();		
		this.testSetup.slowdownInSeconds(3);
	}
}
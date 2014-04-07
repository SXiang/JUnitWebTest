/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.BasePage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class PrimePage extends BasePage {
	
	public static final String STRURLPath = "/Prime";
	public static final String STRPageTitle = "Index"; //The title should be changed soon otherwise submit a bug for it
	
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
		
		System.out.println("\nThe Prime Page URL is: " + this.strPageURL);
	}
	
	public void browsingSettingControls() {
		
		this.btnSidePanel.click();
		
		this.testSetup.slowdownInSeconds(3);
		
		this.btnSidePanel.click();
		
		this.testSetup.slowdownInSeconds(3);
	}
}
package surveyor.scommon.source.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

/**
 * @author sxiang
 *
 */

public class MobileInvestigatePage extends MobileBasePage {

	public static final String STRURLPath = "/Investigation/Investigate?boxId=$";

	public MobileInvestigatePage(){
		super(STRURLPath);
		pageKey = By.cssSelector("button[id='boxType'].dropdown");
		Log.info("The Login Page URL is: " + this.strPageURL);
	}
	
	public void open(String boxId) {
		strPageURL = String.format(strPageURL, boxId);
		driver.get(strPageURL);
		waitUntillPageLoad();
	}
}
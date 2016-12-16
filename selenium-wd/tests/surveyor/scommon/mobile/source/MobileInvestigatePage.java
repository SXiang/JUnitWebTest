package surveyor.scommon.mobile.source;

import org.openqa.selenium.By;
import common.source.Log;

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
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
		pageKey = By.cssSelector("[id='legend-dropdown'].dropdown-toggle");
		Log.info("The Investigate Page URL is: " + this.strPageURL);
	}
	
	public void open(String boxId) {
		strPageURL = String.format(strPageURL, boxId);
		driver.get(strPageURL);
		waitUntilPageLoad();
	}
}
package surveyor.scommon.mobile.source;

import org.openqa.selenium.By;
import common.source.Log;

/**
 * @author sxiang
 *
 */

public class MobileInvestigationPage extends MobileBasePage {

	public static final String STRURLPath = "/Investigation/Investigations?reportId=$&type=$";

	public MobileInvestigationPage(){
		super(STRURLPath);
		pageKey = By.cssSelector("[id='boxType'].dropdown");
		Log.info("The Investigations Page URL is: " + this.strPageURL);
	}
	
	public void open(String reportId, String type) {
		strPageURL = String.format(strPageURL, reportId+","+type);
		driver.get(strPageURL);
		waitUntilPageLoad();
	}
}
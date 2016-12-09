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

public class MobileInvestigationPage extends MobileBasePage {

	public static final String STRURLPath = "/Investigation/Investigations?reportId=$&type=$";

	public MobileInvestigationPage(){
		super(STRURLPath);
		pageKey = By.cssSelector("button[id='boxType'].dropdown");
		Log.info("The Login Page URL is: " + this.strPageURL);
	}
	
	public void open(String reportId, String type) {
		strPageURL = String.format(strPageURL, reportId+","+type);
		driver.get(strPageURL);
		waitUntillPageLoad();
	}
}
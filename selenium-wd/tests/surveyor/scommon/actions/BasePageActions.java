package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;

public class BasePageActions extends BaseActions {
	protected static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss a";
	private WebDriver driver = null;
	private String baseURL = null;
	
	public BasePageActions(WebDriver driver, String baseURL) {
		super();
		this.setDriver(driver);
		this.setBaseURL(baseURL);
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	protected WebDriver getDriver() {
		return driver;
	}

	protected void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public boolean clickById(String elementID, Integer dataRowID) {
		return false;
	}

	public boolean clickByXPath(String elementXPath, Integer dataRowID) {
		return false;
	}

	public boolean clickByIdAndWait(String elementID, Integer dataRowID) {
		return false;
	}

	public boolean clickByXPathAndWait(String elementXPath, Integer dataRowID) {
		return false;
	}

	public boolean insertTextById(String elementID, Integer dataRowID) {
		return false;
	}

	public boolean insertTextByXPath(String elementXPath, Integer dataRowID) {
		return false;
	}

	public boolean selectDropDownByID(String elementXPath, Integer dataRowID) {
		return false;
	}

	public boolean selectDropDownByXPath(String elementXPath, Integer dataRowID) {
		return false;
	}

	public boolean selectRadioButtonByID(String elementXPath, Integer dataRowID) {
		return false;
	}

	public boolean selectRadioButtonByXPath(String elementXPath, Integer dataRowID) {
		return false;
	}
}
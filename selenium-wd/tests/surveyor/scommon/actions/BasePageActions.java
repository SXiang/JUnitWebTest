package surveyor.scommon.actions;

import java.io.File;

import org.openqa.selenium.WebDriver;

import common.source.ExcelUtility;
import common.source.Log;
import common.source.TestContext;

public class BasePageActions implements IPageActions {
	private static final String DATA_FOLDER = "data";
	private static final String TEST_DATA_XLSX = "TestCaseData.xlsx";
	private WebDriver driver = null;
	private String baseURL = null;
	protected ExcelUtility excelUtility = null;
	
	public BasePageActions(WebDriver driver, String baseURL) {
		this.setDriver(driver);
		this.setBaseURL(baseURL);
		String testDataExcelPath = TestContext.INSTANCE.getExecutionPath() + DATA_FOLDER + File.separator + TEST_DATA_XLSX;
		try {
			excelUtility = new ExcelUtility();
			excelUtility.setExcelFile(testDataExcelPath);
		} catch (Exception e) {
			Log.error(e.toString());
		}
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

	protected void logAction(String actionName, String data, Integer dataRowID) {
		Log.info(String.format("Executing action-[%s] : data=[%s], dataRowID=[%d]", actionName, data, dataRowID));
	}

	protected void log(String logText) {
		Log.info(logText);
	}

	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		return false;
	}
}
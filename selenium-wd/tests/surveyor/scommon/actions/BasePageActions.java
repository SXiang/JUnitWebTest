package surveyor.scommon.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.source.RegexUtility;
import surveyor.scommon.source.SurveyorBasePage;
import surveyor.scommon.source.SurveyorBasePage.TableSortOrder;

public class BasePageActions extends BaseActions {
	protected static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss a";

	private static final String FN_SORT_RECORDS_BY = "sortRecordsBy";
	private WebDriver driver = null;
	private String baseURL = null;
	
	protected SurveyorBasePage pageObject;
	
	public BasePageActions(WebDriver driver, String baseURL) {
		super();
		this.setDriver(driver);
		this.setBaseURL(baseURL);
	}
	
	public void initializePageObject(WebDriver driver, SurveyorBasePage pageObj) {
		setPageObject(pageObj);
		PageFactory.initElements(driver, getPageObject());
	}

	public SurveyorBasePage getPageObject() {
		return pageObject;
	}

	public void setPageObject(SurveyorBasePage basePage) {
		this.pageObject = basePage;
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

	protected void clickElement(WebElement element) {
		if (element != null) {
			element.click();
		}
	}

	protected WebElement getElementById(String elementID) {
		WebElement element = driver.findElement(By.id(elementID));
		return element;
	}

	protected WebElement getElementByXPath(String elementXPath) {
		WebElement element = driver.findElement(By.xpath(elementXPath));
		return element;
	}

	protected void sendKeysToElement(WebElement element, String keys) {
		if (element != null) {
			element.sendKeys(keys);
		}
	}

	public boolean clickById(String elementID, Integer dataRowID) {
		WebElement element = getElementById(elementID);
		clickElement(element);
		return true;
	}

	public boolean clickByXPath(String elementXPath, Integer dataRowID) {
		WebElement element = getElementByXPath(elementXPath);
		clickElement(element);
		return true;
	}

	public boolean clickByIdAndWait(String elementID, Integer dataRowID) {
		return false;
	}

	public boolean clickByXPathAndWait(String elementXPath, Integer dataRowID) {
		return false;
	}

	/**
	 * Inserts text in the element found by ID
	 * @param elementIDAndText - comma seperated element ID and text to insert
	 * @param dataRowID
	 * @return
	 */
	public boolean insertTextById(String elementIDAndText, Integer dataRowID) {
		List<String> split = RegexUtility.split(elementIDAndText, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		if (split != null && split.size() > 1) {
			String elementID = split.get(0);
			String text = elementIDAndText.substring(elementID.length(), elementIDAndText.length()-1);
			sendKeysToElement(getElementById(elementID), text);
		}
		return true;
	}

	/**
	 * Inserts text in the element found by XPath
	 * @param elementIDAndText - comma seperated element ID and text to insert
	 * @param dataRowID
	 * @return
	 */
	public boolean insertTextByXPath(String elementXPathAndText, Integer dataRowID) {
		List<String> split = RegexUtility.split(elementXPathAndText, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		if (split != null && split.size() > 1) {
			String elementID = split.get(0);
			String text = elementXPathAndText.substring(elementID.length(), elementXPathAndText.length()-1);
			sendKeysToElement(getElementById(elementID), text);
		}
		return true;
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
	
	/**
	 * Executes sortRecordsBy action.
	 * @param data - the column index and sort order in colon seperated format. 
	 * 	For eg. {1:DESC} implies sorting column 1 in Descending order
	 *  Use 1-based index and DESC/ASC for Descending/Ascending sort orders.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean sortRecordsBy(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.sortRecordsBy", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(FN_SORT_RECORDS_BY, ARG_DATA, data);
		List<String> parts = RegexUtility.split(data, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
		if (parts == null || parts.size() != 2) {
			throw new Exception(String.format("[%s] - Invalid data parameter specified. Data value='%s'.", FN_SORT_RECORDS_BY, data));
		}
		
		Integer columnIdx = Integer.valueOf(parts.get(0));
		TableSortOrder sortOrder = this.getPageObject().getSortOrderFromString(parts.get(1));
		this.getPageObject().sortTableByColumn(columnIdx, sortOrder);
		return true;
	}
 

}
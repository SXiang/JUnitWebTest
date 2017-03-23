/**
 *
 */
package surveyor.scommon.source;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import common.source.Log;
import common.source.TestSetup;

/**
 * @author sxiang
 *
 */
public class ReportInvestigationsPage extends ReportsBasePage {

	public static final String STRURLPath = "/Reports/Investigations?reportId=%s";
	protected String pagination = "100";

	@FindBy(how = How.CSS, using = "#boxType > button.btn")
	protected WebElement boxTypeDropdown;

	@FindBy(how = How.CSS, using = "#boxType > button.btn > #boxType_text")
	protected WebElement boxTypeText;

	@FindBy(how = How.ID, using = "buttonAssignPeaks")
	protected WebElement buttonAssignPeaks;

	@FindBy(how = How.ID, using = "buttonAssignInvestigator")
	protected WebElement buttonAssignInvestigator;

	@FindBy(how = How.CSS, using = "div[id='myModal'] select[id='User']")
	protected WebElement assignToUserDropdown;

	@FindBy(how = How.ID, using = "modal-okay-btn")
	protected WebElement buttonAssignOk;

	@FindBy(how = How.ID, using = "modal-cancel-btn")
	protected WebElement buttonAssignCancel;

	protected String checkBoxXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td/input[@type='checkbox']";
	protected String itemStatusXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td[3]";
	protected String itemValueXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td[2]";
	protected String itemDateXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td[4]";

	public static enum IndicationStatus {
		FOUNDGASLEAK ("Found Gas Leak"),
		INPROGRESS ("In Progress"),
		NOTINVESTIGATED ("Not Investigated"),
		FOUNDOTHERSOURCE ("Found Other Source"),
		NOGASFOUND("No Gas Found");

		private String status;

		IndicationStatus(String status){
			this.status = status;
		}

		public String toString(){
			return status;
		}

	};

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public ReportInvestigationsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);
		Log.info(String.format("\nThe Compliance Reports Page URL is: %s\n", this.strPageURL));
		PageFactory.initElements(driver, this);
	}

	public void open(String reportId) {
		strPageURL = String.format(strPageURL, reportId);
		driver.get(strPageURL);
		this.waitForPageToLoad();
	}

	public boolean selectLisa(String lisaNumber){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		return selectBox(lisaNumber);
	}

	public boolean selectGap(String gapNumber){
		String boxType = "Gap";
		selectDropdownItem(boxTypeDropdown, boxType);
		return selectBox(gapNumber);
	}

	public boolean selectBox(String boxNumber){
		performSearch(boxNumber);
		WebElement checkBox = driver.findElement(By.xpath(String.format(checkBoxXPattern, boxNumber)));
		if(!checkBox.isSelected()){
			checkBox.click();
			waitForPageToLoad();
		}
		performSearch("");
		return checkBox.isSelected();
	}

	public boolean assignPeaks(String username){
		buttonAssignPeaks.click();
		boolean userSelected = selectDropdownOption(assignToUserDropdown, username);
		buttonAssignOk.click();
		return userSelected;
	}

	public String getLisaStatus(String lisaNumber){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		return getItemStatus(lisaNumber);
	}

	public String getGapStatus(String gapNumber){
		String boxType = "Gap";
		selectDropdownItem(boxTypeDropdown, boxType);
		return getItemStatus(gapNumber);
	}

	public String getItemStatus(String itemNumber){
		WebElement itemStatus = driver.findElement(By.xpath(String.format(itemStatusXPattern, itemNumber)));
		String statusText = getElementText(itemStatus);
		return statusText;
	}

	public String getLisaDate(String lisaNumber){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		return getItemStatus(lisaNumber);
	}

	public String getGapDate(String gapNumber){
		String boxType = "Gap";
		selectDropdownItem(boxTypeDropdown, boxType);
		return getItemDate(gapNumber);
	}

	public String getItemDate(String itemNumber){
	WebElement itemDate = driver.findElement(By.xpath(String.format(itemDateXPattern, itemNumber)));
		String dateValue = getElementText(itemDate);
		return dateValue;
	}
	public String getLisaValue(String lisaNumber){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		WebElement itemValue = driver.findElement(By.xpath(String.format(itemValueXPattern, lisaNumber)));
		String value = getElementText(itemValue);
		return value;
	}

	public boolean isLisaValueSearchable(String lisaValue){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		performSearch(lisaValue);
		boolean valueMatch = true;
		List<WebElement> itemValues = driver.findElements(By.xpath("//*[@id='datatableBoxes']//td[2]"));
		for(WebElement item:itemValues){
			String value = getElementText(item);
			if(!value.equals(lisaValue)){
				Log.error("Lisa valeu '"+lisaValue+"' is not searchable or invalid");
				return false;
			}
		}
		return true;
	}
}
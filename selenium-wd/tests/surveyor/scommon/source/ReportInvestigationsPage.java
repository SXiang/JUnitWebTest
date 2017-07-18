/**
 *
 */
package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import common.source.Log;
import common.source.TestSetup;
import common.source.WebElementExtender;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.entities.InvestigationEntity;

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

	@FindBy(how = How.ID, using = "buttonInvestigate")
	protected WebElement buttonInvestigate;

	@FindBy(how = How.ID, using = "investigate-button")
	protected WebElement button_Investigate;
	
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

	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'Investigation Markers')]")
	protected WebElement investigationMarkers;

	@FindBy(how = How.ID, using = "pause-button")
	protected WebElement buttonPause;
	
	@FindBy(how = How.ID, using = "complete-button")
	protected WebElement buttonMarkAsComplete;

	@FindBy(how = How.ID, using = "directions-button")
	protected WebElement button_Directions;

	@FindBy(how = How.ID, using = "addsource-button")
	protected WebElement button_AddSource;
	
	@FindBy(how = How.ID, using = "addcgi-button")
	protected WebElement button_AddCgi;

	@FindBy(how = How.ID, using = "geolocate")
	protected WebElement buttonFollow;
	
	protected String checkBoxXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td/input[@type='checkbox']";
	protected String itemStatusXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td[3]";
	protected String itemValueXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td[2]";
	protected String itemDateXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td[4]";
	protected String itemNumberXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td[1]";
    protected String itemMarkerXPath = "//a[@class='list-group-item' and contains(text(),'%s')]";
	protected String boxItemXPattern = "//*[@id='boxType']/ul[@class='dropdown-menu']/li/a[text()='%s ']";
	protected String boxMarkerXPattern = "//div[@class='list-group']/a[starts-with(text(), '%s')]";
	protected By mapKey = By.cssSelector(".map[id='map']>.ol-viewport > canvas");
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.LisaInvestigations_PageTitle);
	
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
		resxProvider = getCommonResourceProvider();
		Log.info(String.format("\nThe Compliance Reports Page URL is: %s\n", this.strPageURL));
		PageFactory.initElements(driver, this);
	}

	public void open(String reportId) {
		strPageURL = String.format(strPageURL, reportId);
		driver.get(strPageURL);
		this.waitForPageToLoad();
	}

	public boolean selectLisa(String lisaNumber){
		return selectMultipleLisas(new String[] { lisaNumber });
	}

	public boolean selectMultipleLisas(String[] lisaNumbers){
		boolean retVal = true;
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		for (String lisaNumber : lisaNumbers) {
			retVal = retVal && selectBox(lisaNumber);
		}

		return retVal;
	}

	public boolean selectGap(String gapNumber){
		return selectMultipleGaps(new String[] { gapNumber });
	}

	public boolean selectMultipleGaps(String[] gapNumbers){
		boolean retVal = true;
		String boxType = "Gap";
		selectDropdownItem(boxTypeDropdown, boxType);
		for (String gapNumber : gapNumbers) {
			retVal = retVal && selectBox(gapNumber);
		}

		return retVal;
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

	public void investigateItem(String item){
		investigateItem(item, "LISA");
	}
	
	public void investigateItem(String item, String boxType){
		clickOnInvestigate();
		selectDropdownItem(boxTypeDropdown, boxType);
		WebElement itemLink = driver.findElement(By.xpath(String.format(itemMarkerXPath, item)));
		itemLink.click();
		WebElementExtender.waitForElementToBeClickable(timeout, driver, button_Investigate);
		button_Investigate.click();
	}

	public void clickOnInvestigate(){
		buttonInvestigate.click();
		WebElementExtender.waitForElementToBeClickable(timeout, driver, investigationMarkers);
	}
	public void clickOnPauseInvestigation(){
		jsClick(buttonPause);
		WebElementExtender.waitForElementToBeClickable(timeout, driver, investigationMarkers);
	}
	public void clickOnMarkAsComplete(){
		jsClick(buttonMarkAsComplete);
		WebElementExtender.waitForElementToBeClickable(timeout, driver, investigationMarkers);
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

	public boolean verifyLisasOrderByAmplitude(){
		By xpathToItem = By.xpath("//*[@id='datatableBoxes']//td[1]");
		selectDropdownItem(boxTypeDropdown, "LISA");
		waitForAJAXCallsToComplete();
		waitForElementToBeDisplayed(xpathToItem);
		List<WebElement> itemIDs = driver.findElements(xpathToItem);
		List<String> itemSorted = new ArrayList<>();
		itemIDs.stream().map((WebElement e) -> getElementText(e)).forEach((String s) -> itemSorted.add(s));

		itemSorted.sort(
				(String x, String y) -> {
					String[] temp1 = x.split("-");
					String[] temp2 = y.split("-");
					return Integer.valueOf(temp1[temp1.length-1]).compareTo(Integer.valueOf(temp2[temp2.length-1]));
				}
				);
		Double value1=null, value2=null;
		for(String item:itemSorted){
			if(item.isEmpty()){
				continue;
			}
			value1 = value2;
			value2 = Double.valueOf(getElementText(driver.findElement(By.xpath(String.format(itemValueXPattern, item)))));
			if(value1==null){
				value1=value2;
			}
			if(value2>value1){
				Log.error("Indication with Amplitude = '"+value2+"' is assigned a number lower than the number assigned to Amplitude = '"+value1+"'");
				return false;
			}
		}
		return true;
	}
	
	public void clickOnLisa(String lisaNumber){
		clickOnLisa(lisaNumber, null);
	}
	
	public void clickOnLisa(String lisaNumber, InvestigationEntity investigationEntity){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		if(investigationEntity!=null){
			investigationEntity.setBoxType(boxType);
		}
		clickOnMarker(lisaNumber);
	}

	public void clickOnMarker(String boxNumber){
		WebElement markerLink = driver.findElement(By.xpath(String.format(boxMarkerXPattern, boxNumber)));
		markerLink.click();
	}

	public void clickOnFollow(){
		buttonFollow.click();
		waitForAJAXCallsToComplete();
		waitForElementReady(mapKey);
	}

	private static ResourceProvider getCommonResourceProvider() {
		return new ResourceProvider(() -> {
			Map<String, String> resxMap = new HashMap<String, String>();
			resxMap.put(ResourceTable.Key_PageText, STRPageContentText);
			return new ResourceTable(resxMap);
		});
	}
}

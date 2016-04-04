/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import common.source.BasePage;
import common.source.Log;
import common.source.RegexUtility;
import common.source.TestSetup;
import surveyor.scommon.source.SurveyorConstants.TopNavMenuItem;
import surveyor.scommon.source.SurveyorConstants.UserTimezone;


/**
 * @author zlu
 *
 */
public class SurveyorBasePage extends BasePage {

	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/a")
	protected WebElement dropDownAdministrator;

	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/div[2]/ul/li/a")
	protected WebElement dropDownUser;

	@FindBy(css = "#wrapper > nav.navbar .dropdown > a.dropdown-toggle")
	protected WebElement topDropdownMenu; // should be good for both Admin and User
	
	@FindBy(css = "#wrapper > nav.navbar .dropdown > ul.dropdown-menu > li > a")
	protected List<WebElement> topNavMenuItems;
	
	@FindBy(how = How.CSS, using= "li.open #user-change-password")
	protected WebElement linkChangePwd;

	@FindBy(how = How.CSS, using = "li.open #user-logout")
	protected WebElement linkLogOut;
		
	@FindBy(how = How.XPATH, using = "//a[@data-target='#picarro-administration-menu']")
	protected WebElement linkPicarroAdmin;
	protected String strLinkPicarroAdminXPath = "//*[@id='picarro-administration-menu']/a";

	@FindBy(how = How.XPATH, using = "//a[@data-target='#picarro-administration-menu']/a")
	protected WebElement linkPicarroAdminXPath;

	@FindBy(how = How.XPATH, using = "//*[@data-target='#customer-administration-menu']")
	protected WebElement linkCusAdmin;
	protected String strLinkCusAdminXPath = "//*[@id='customer-administration-menu']/a";

	@FindBy(name = "datatable_length")
	protected WebElement paginationInput;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable_filter']/label/input")
	protected WebElement inputSearch;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody")
	protected WebElement table;
	protected String strTRXPath = "//*[@id='datatable']/tbody/tr";

	@FindBy(how = How.XPATH, using = "//*[@id='datatable_next']")
	protected WebElement nextBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='buttonOk']")
	protected WebElement btnOk;


	@FindBy(css = ".validation-summary-errors > .panel-heading")
	protected WebElement summaryErrors;
	protected By summaryErrorsBy = By.cssSelector(".validation-summary-errors > .panel-heading");
	
	@FindBy(css = ".validation-summary-errors > .panel-body li")
	protected List<WebElement> pannelErrors;
	
	// Following 2 declarations might be replaced by those 2 above this 
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]")
	protected WebElement panelDuplicationError;
	protected String panelDuplicationErrorXPath = "//*[@id='page-wrapper']/div/div[2]/div[1]";

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[2]/ul/li")
	protected WebElement liDuplicateMsg;

	
	@FindBy(how = How.XPATH, using = "//*[@id='customer-administration-manage-users']/a")
	protected WebElement linkAdminManageUsers;

	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-manage-users']/a")
	protected WebElement linkPicAdminManageUsers;

	@FindBy(how = How.XPATH, using = "//*[@id='myModalLabel']")
	protected WebElement popupConfirmationBox;
	protected String popupConfirmationBoxXPath = "//*[@id='myModalLabel']";

	@FindBy(how = How.XPATH, using = "//*[@id='myModal']/div/div/div[3]/a[1]")
	protected WebElement btnDelete;
	protected String btnDeleteXPath = "//*[@id='myModal']/div/div/div[3]/a[1]";

	@FindBy(how = How.XPATH, using = "//*[@id='datatable_info']")
	protected WebElement labelPageTableInfo;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td")
	protected WebElement labelNoMatchingSearch;
	
	@FindBy(css = "#user-timezone.open > a.dropdown-toggle > #selected-timezone")
	protected List<WebElement> timezoneCloseDropdown;

	@FindBy(css = "#user-timezone> a.dropdown-toggle > #selected-timezone")
	protected WebElement timezoneDropdown;
	
	@FindBy(xpath = "//*[@id='timezones' and @class='dropdown-menu']//a[contains(text(),'Pacific Standard Time')]")
	protected WebElement pacificTime;
	
	@FindBy(xpath = "//*[@id='timezones' and @class='dropdown-menu']//a[contains(text(),'Mountain Standard Time')]")
	protected WebElement mountainTime;
	
	@FindBy(xpath = "//*[@id='timezones' and @class='dropdown-menu']//a[contains(text(),'Central Standard Time')]")
	protected WebElement centralTime;
	
	@FindBy(xpath = "//*[@id='timezones' and @class='dropdown-menu']//a[contains(text(),'Eastern Standard Time')]")
	protected WebElement easternTime;
	
	private static String headerColumnBaseXPath = "//*[@id='datatable']/thead/tr/th[%d]";
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public SurveyorBasePage(WebDriver driver, TestSetup testSetup, String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);
	}

	public boolean closeTopDropdownMenu(){
		String opened = topDropdownMenu.getAttribute("aria-expanded");
		if(opened!=null&&opened.equals("true")){
			topDropdownMenu.click();
		}		 
		 return (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>(){
			 public Boolean apply(WebDriver d){
				 String value = topDropdownMenu.getAttribute("aria-expanded");
				return value==null||value.equals("false");
			 }
		 });
	}
	
	public boolean openTopDropdownMenu(){
		waitForPageToLoad(); // This will be removed after all wait conditions settled while jumping from page to page
		String opened = topDropdownMenu.getAttribute("aria-expanded");
		if(opened==null||opened.equals("false")){
			topDropdownMenu.click();
		}		 
		 return (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>(){
			 public Boolean apply(WebDriver d){
				return topDropdownMenu.getAttribute("aria-expanded").equals("true");
			 }
		 });		 		
	}
	public boolean verifyDropdownMenuItems(){
		openTopDropdownMenu();
						
		boolean itemFound = true;
		TopNavMenuItem[] items = TopNavMenuItem.values();
		for(int i=0;i<this.topNavMenuItems.size();i++){
			WebElement item = topNavMenuItems.get(i);
			String text = item.getText();
			if(text.trim().equals("")){
				break;
			}
			itemFound &= text.contains(items[i].toString());
		}
		closeTopDropdownMenu();		
		return itemFound;
	}
	public LoginPage logout() {
		openTopDropdownMenu();
		this.linkLogOut.click();

		LoginPage loginPage = new LoginPage(this.driver, this.strBaseURL, this.testSetup);
		PageFactory.initElements(driver, loginPage);
		return loginPage;
	}

	public void login(String user, String password) {
		LoginPage loginPage = new LoginPage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver, loginPage);

		loginPage.open();

		loginPage.loginNormalAs(user, password);
	}

	public void setPagination(String str) {
		List<WebElement> options = this.paginationInput.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (str.equals(option.getText().trim())) {
				option.click();
			}
		}
	}

	public WebElement getLinkPicarroAdmin() {
		return this.linkPicarroAdmin;
	}

	public WebElement getLinkCusAdmin() {
		return this.linkCusAdmin;
	}

	public WebElement getLinkAdminManageUsers() {
		return this.linkAdminManageUsers;
	}

	public WebElement getLabelPageTableInfo() {
		return this.labelPageTableInfo;
	}

	public WebElement getInputSearch() {
		return this.inputSearch;
	}

	public String getLabelNoMatchingSearch() {
		return this.labelNoMatchingSearch.getText().trim();
	}

	public WebElement getLinkPicAdminManageUsers() {
		return linkPicAdminManageUsers;
	}

	public WebElement getLinkPicarroAdminXPath() {
		return linkPicarroAdminXPath;
	}

	public void performSearch(String searchTerm) {
		this.inputSearch.sendKeys(searchTerm);
		this.inputSearch.sendKeys(Keys.ENTER);
	}

	public boolean getListSize(List<String> listOfElements) {
		String numTextString;
		String[] strList;
		int result = 0;

		numTextString = this.getLabelPageTableInfo().getText().trim();
		strList = numTextString.split(" ");
		result = Integer.parseInt(strList[3]);
		return (listOfElements.size() == result);
	}

	
	public String getUserTimezone(){		
		String text = "";		
		
		text = ( new WebDriverWait(driver, 5)).until(new ExpectedCondition<String>(){
			public String apply(WebDriver d){
				String value = null;
				try{
					value = timezoneDropdown.getText().trim();
				}catch(StaleElementReferenceException e){
					
				}finally{
					if(value!=null&&value.isEmpty()){
						value = null;
					}
				}
				return value;
			}
		});
		
		return text;
	}
	
	public boolean changeUserTimezone(UserTimezone ut){
		if(this.timezoneCloseDropdown.isEmpty()){
			this.timezoneDropdown.click();
		}
		WebElement utItem;
		switch(ut){
		   case PACIFIC:
			   utItem = pacificTime;
			   break;
		   case MOUNTAIN:
			   utItem = mountainTime;
			   break;
		   case CENTRAL:
			   utItem = centralTime;
			   break;
		   case EASTERN:
			   utItem = easternTime;
			   break;
		   default:
			   utItem = pacificTime;
			   break;
		}
	
		utItem.click();	
		
		return ( new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				return ut.toString().equals(getUserTimezone());
			}
		});
	}
	/*
	 * Helper method to wait for an Element to be ready on the page.
	 */
	public void WaitForElementReady(String elementID) {
		(new WebDriverWait(this.driver, this.timeout)).until(ExpectedConditions.presenceOfElementLocated(By.id(elementID)));
	}

	public Integer getRecordsShownOnPage(WebDriver driver) {
		WebElement pageInfoLabel = driver.findElement(By.id("datatable_info"));
		String numTextString = pageInfoLabel.getText().trim();
		List<String> strList = RegexUtility.split(numTextString, RegexUtility.SPACE_SPLIT_REGEX_PATTERN);
		Integer records = 0;
		if (strList != null && strList.size() > 3) {
			records = Integer.parseInt(strList.get(3));
		}
		return records;
	}
	
	public void clickOnColumnHeader(Integer columnIndex, Integer numTimesToClick) {
		WebElement headerElement = driver.findElement(By.xpath(String.format(headerColumnBaseXPath, columnIndex)));
		for (int i = 0; i < numTimesToClick; i++) {
			headerElement.click();
		}
	}
}
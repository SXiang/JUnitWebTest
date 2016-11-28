/**
 *
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.BLANKFIELDERROR;
import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import common.source.BasePage;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.LogHelper;
import common.source.RegexUtility;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.WebElementExtender;
import surveyor.dataaccess.source.Location;
import surveyor.scommon.source.DataTablePage.TableColumnType;
import surveyor.scommon.source.SurveyorConstants.TopNavMenuItem;
import surveyor.scommon.source.SurveyorConstants.UserTimezone;


/**
 * @author zlu
 *
 */
public class SurveyorBasePage extends BasePage {

	protected static final String TABLE_BUTTON_CLASS = "btn btn-primary";
	protected static final String DATA_TABLE_XPATH = "//*[@id='datatable']/tbody";
	protected static final String DATATABLE_TBODY_TR = "//*[@id='datatable']/tbody/tr";
	protected static final String DATATABLE_RECORDS_ELEMENT_ID= "datatable_info";
	protected static final String DATATABLESURVEYS_RECORDS_ELEMENT_ID = "datatableSurveys_info";
	protected static By DATATABLE_RECORDS_ELEMENT_BY = By.cssSelector(".dataTables_info[id$='_info']");

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

	@FindBy(css = "[name^='datatable'][name$='_length']")
	protected WebElement paginationInput;
	By paginationInputBy = By.cssSelector("[name^='datatable'][name$='_length']");

	@FindBy(css = "[name^='datatable'][name$='_length'] option")
	protected List<WebElement> paginationOptions;

	@FindBy(how = How.CSS, using = "[id^='datatable'][id$='_filter'] > label > input")
	protected WebElement inputSearch;

	@FindBy(how = How.XPATH, using = DATA_TABLE_XPATH)
	protected WebElement table;
	protected String strTRXPath = DATA_TABLE_XPATH+"/tr";

	@FindBy(how = How.CSS, using = ".paginate_button.next")
	protected WebElement nextBtn;

	@FindBy(how = How.CSS, using = ".paginate_button.previous")
	protected WebElement previousBtn;

	@FindBy(how = How.CSS, using = ".paginate_button.first")
	protected WebElement firstBtn;

	@FindBy(how = How.CSS, using = ".paginate_button.last")
	protected WebElement lastBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='buttonOk']")
	protected WebElement btnOk;

	@FindBy(css = ".validation-summary-errors > .panel-heading")
	protected WebElement summaryErrors;
	protected By summaryErrorsBy = By.cssSelector(".validation-summary-errors > .panel-heading");

	@FindBy(css = ".validation-summary-errors > .panel-body li")
	protected List<WebElement> panelErrors;

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
	protected By labelNoMatchingBy = By.xpath("//*[@id='datatable']/tbody/tr/td");

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

	@FindBy(xpath = "//*[@id='datatable_filter']/label/input")
	protected WebElement searchTextBox;

    @FindBy(css = ".dataTables_length> label>select> option")
	private List<WebElement> paginationOption;

	@FindBy(how = How.XPATH, using = "//div[@id='datatable_info']")
	protected WebElement paginationMsg;

	private static String headerColumnBaseXPath = "//*[@id='datatable']/thead/tr/th[%d]";
	public static final String STRPaginationMsgPattern_firstPage = "Showing 1 to %s of [\\d,]+ entries.*|Showing [10] to ([\\d]+) of \\1 entries.*";
	public static final String STRPaginationMsgPattern_anyPage = "Showing [\\d,]+ to [\\d,]+ of [\\d,]+ entries.*";

	@FindBy(how = How.XPATH, using = "//table[@id='datatable']/tbody/tr")
	protected List<WebElement> numberofRecords;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]")
    protected WebElement firstRowTr;

	@FindBy(how = How.XPATH, using = "//*[@id='footer']/div/footer/p")
	protected WebElement labelFooter;

	public enum TableSortOrder {
		ASC ("ASC"),
		DESC ("DESC");

		private final String name;

		TableSortOrder(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

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
		Log.method("closeTopDropdownMenu");
		String opened = topDropdownMenu.getAttribute("aria-expanded");
		if(opened!=null&&opened.equals("true")){
			Log.clickElementInfo("Menu",ElementType.DROPDOWN);
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
		Log.method("openTopDropdownMenu");
		waitForPageToLoad(); // This will be removed after all wait conditions settled while jumping from page to page
		String opened = topDropdownMenu.getAttribute("aria-expanded");
		if(opened==null||opened.equals("false")){
			Log.clickElementInfo("Menu",ElementType.DROPDOWN);
			topDropdownMenu.click();
		}
		 return (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>(){
			 public Boolean apply(WebDriver d){
				return topDropdownMenu.getAttribute("aria-expanded").equals("true");
			 }
		 });
	}

	public boolean verifyDropdownMenuItems(){
		Log.method("verifyDropdownMenuItems");
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
		Log.method("logout");
		return logout(false);
	}

	private LoginPage logout(boolean failOnError) {
		Log.method("logout", failOnError);
		try {
			openTopDropdownMenu();
			Log.clickElementInfo("Log Out",ElementType.LINK);
			this.linkLogOut.click();
		} catch (Exception e) {
			if (failOnError) {
				throw e;
			} else {
				Log.warn(String.format("Exception when calling logout : %s", ExceptionUtility.getStackTraceString(e)));
			}
		}

		LoginPage loginPage = new LoginPage(TestContext.INSTANCE.getDriver(), this.strBaseURL, TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(TestContext.INSTANCE.getDriver(), loginPage);
		return loginPage;
	}

	public void login(String user, String password) {
		Log.method("login", user, "<HIDDEN>");
		LoginPage loginPage = new LoginPage(TestContext.INSTANCE.getDriver(), this.strBaseURL, TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(TestContext.INSTANCE.getDriver(), loginPage);

		try {
			loginPage.open();
			loginPage.loginNormalAs(user, password);
		} catch (Exception ex) {
			Log.warn(String.format("[DEBUG LOG]: Exception on loginPage.loginNormalAs(user='%s', password='%s')", user, password));
			TestContext.INSTANCE.getTestSetup().getScreenCapture().takeScreenshot(driver,
					TestContext.INSTANCE.getTestClassName(), true /*takeBrowserScreenShot*/, LogStatus.INFO);
		}

		// Post login Code first will revert back the default location entry.
		// This is a workaround to fix the Default location if lat/long is NULL.
		Location location = Location.getLocation("Default");
		if (location.getLatitude() < Float.MIN_VALUE + 1) {
			location.setLatitude(37.4020925705503F);
			location.setLongitude(-121.984820397399F);
			location.updateLocation();
		}
	}

	public void setPagination(String str) {
		setPagination(str, true);
	}

	public void setPaginationAny(String str) {
		Log.method("setPaginationAny", str);
		setPaginationCheckMessage(str, STRPaginationMsgPattern_anyPage);
	}

	public void setPagination(String str, boolean firstPage){
		Log.method("setPagination", str, firstPage);

		String paginationMsg = STRPaginationMsgPattern_anyPage;

		if(firstPage){
			paginationMsg = String.format(STRPaginationMsgPattern_firstPage,str);
			jsClick(firstBtn);
			this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		}
		setPaginationCheckMessage(str, paginationMsg);
	}

	private void setPaginationCheckMessage(String str, String paginationMsg) {
		for (WebElement option : paginationOptions) {
			try{
				if (str.equals(option.getText().trim())) {
					Log.info(String.format("Select pagination - '%s'",str));
					option.click();
					waitForNumberOfRecords(paginationMsg);
					getRecordsShownOnPage(TestContext.INSTANCE.getDriver());
					break;
				}
			}catch(StaleElementReferenceException e){
				continue;
			}catch(Exception e){
				Log.warn("Failed to go back to the first page: "+e.toString());
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
		return waitForPresenceOfElementText(labelNoMatchingBy);
	}

	public WebElement getLinkPicAdminManageUsers() {
		return linkPicAdminManageUsers;
	}

	public WebElement getbtnOk() {
		return btnOk;
	}

	public WebElement getLinkPicarroAdminXPath() {
		return linkPicarroAdminXPath;
	}

	public void performSearch(String searchTerm) {
		Log.method("performSearch", searchTerm);
		this.inputSearch.clear();
		Log.info(String.format("Input search text - '%s'",searchTerm));
		this.inputSearch.sendKeys(searchTerm);
		this.inputSearch.sendKeys(Keys.ENTER);
		super.waitForPageLoad();
	}

	public boolean getListSize(List<String> listOfElements) {
		Log.method("getListSize", LogHelper.strListToString(listOfElements));
		String numTextString;
		String[] strList;
		int result = 0;

		numTextString = this.getLabelPageTableInfo().getText().trim();
		strList = numTextString.split(" ");
		result = Integer.parseInt(strList[3]);
		return (listOfElements.size() == result);
	}


	public String getUserTimezone(){
		Log.method("getUserTimezone");
		String text = "";

		text = ( new WebDriverWait(TestContext.INSTANCE.getDriver(), timeout)).until(new ExpectedCondition<String>(){
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
		Log.method("changeUserTimezone", ut);
		if(this.timezoneCloseDropdown.isEmpty()){
			Log.clickElementInfo("Timezone",ElementType.DROPDOWN);
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
		Log.info(String.format("Select timezone - '%s'", ut));
		utItem.click();

		return ( new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				return ut.toString().equals(getUserTimezone());
			}
		});
	}

	public Integer getRecordsShownOnPage(WebDriver driver) {
		Log.method("getRecordsShownOnPage", driver);
		WebElement pageInfoLabel = null;
		try{
			this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
			(new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfElementLocated(DATATABLE_RECORDS_ELEMENT_BY));
			pageInfoLabel = driver.findElement(DATATABLE_RECORDS_ELEMENT_BY);
		}catch(Exception e){
			Log.error("Failed to get datatable info: "+e.toString());
			Log.warn("Current URL is: "+driver.getCurrentUrl());
			return 0;
		}
		return getRecordsShownOnPage(driver, pageInfoLabel);
	}

	public Integer getRecordsShownOnPage(WebDriver driver, WebElement tableElement) {
		Log.method("getRecordsShownOnPage", driver, tableElement);
		String numTextString = getElementText(tableElement);
		List<String> strList = RegexUtility.split(numTextString, RegexUtility.SPACE_SPLIT_REGEX_PATTERN);
		Integer records = -1;
		if (strList != null && strList.size() > 3) {
			records = Integer.parseInt(strList.get(3).replace(",", ""));
		}
		return records;
	}

	public void searchTable(String locationName) {
		Log.method("searchTable", locationName);
		this.clearSearchField();
		this.getInputSearch().sendKeys(locationName);
		this.waitForSearchResultsToLoad();
	}

	public boolean searchHasNoMatchingRecords() {
		Log.method("searchHasNoMatchingRecords");
		return this.getLabelNoMatchingSearch().equalsIgnoreCase(NOMATCHINGSEARCH);
	}

	public void clearSearchField() {
		Log.method("clearSearchField");
		Log.info("clearing search field");
		this.getInputSearch().clear();
	}

	public void clearSearchFieldUsingSpace() {
		Log.method("clearSearchFieldUsingSpace");
		try{
			this.getInputSearch().sendKeys(" ");
			this.waitForTableDataToLoad();
		}catch(Exception e){
			Log.error("Failed to clear search field: "+e.toString());
			Log.warn("Current URL is: " + TestContext.INSTANCE.getDriver().getCurrentUrl());
		}
	}

	private WebElement getTableHeader(Integer columnIndex) {
		Log.method("getTableHeader", columnIndex);
		WebElement headerElement = TestContext.INSTANCE.getDriver().findElement(By.xpath(String.format(headerColumnBaseXPath, columnIndex)));
		return headerElement;
	}

	public TableSortOrder getSortOrderFromString(String sortOrderString) {
		Log.method("getSortOrderFromString", sortOrderString);
		TableSortOrder tblSortOrder = TableSortOrder.ASC;
		if (sortOrderString.equals("DESC")) {
			tblSortOrder = TableSortOrder.DESC;
		}
		return tblSortOrder;
	}

	public WebElement getTable() {
		return this.table;
	}

	public void setTable(WebElement table) {
		this.table = table;
	}

	public void sortTableByColumn(Integer columnIndex, TableSortOrder sortOrder) {
		Log.method("sortTableByColumn", columnIndex, sortOrder);
		WebElement headerElement = getTableHeader(columnIndex);
		TableSortOrder currTblSortOrder = getCurrentColumnSortOrder(headerElement, columnIndex);
		// If current sort order is same as requested sort order, click twice to refresh data.
		// If current sort order is different than requested sort order, click once to change sorted order.
		Log.info(String.format("Sort table by column '%d'", columnIndex));
		if (currTblSortOrder.equals(sortOrder)) {
			multiClickElement(headerElement, 2);
		} else {
			multiClickElement(headerElement, 1);
		}

	}

	public boolean checkTableSort(String dataTableElement, HashMap<String, TableColumnType> columnHeadings, String str, List<WebElement> paginationOption){
		return checkTableSort(dataTableElement, columnHeadings, str, paginationOption, -1);
	}


	public boolean checkTableSort(String dataTableElement, HashMap<String, TableColumnType> columnHeadings, String str, List<WebElement> paginationOption, int numRecords){
		Log.method("checkTableSort", dataTableElement, columnHeadings, paginationOption);
		By tableContextBy = By.id(dataTableElement);
		WebElement tableContext = TestContext.INSTANCE.getDriver().findElement(tableContextBy);
		DataTablePage dataTable = DataTablePage.getDataTablePage(driver, tableContext, this.testSetup, this.strBaseURL, this.strPageURL);
		String headerCss = "thead > tr > th[aria-label^='%s:']";
		for (Entry<String, TableColumnType> entry : (columnHeadings.entrySet())) {
			String headerLabel = entry.getKey().trim();
			if(!isElementPresent(By.cssSelector(String.format(headerCss, headerLabel)))){
				continue;
			}
			WebElement headerLink = tableContext.findElement(By.cssSelector(String.format(headerCss, headerLabel)));
			String currentOrder = headerLink.getAttribute("aria-sort");
			boolean isAscending = true;
			if(currentOrder!=null){
				isAscending = !currentOrder.equals("ascending");
			}
			headerLink.click();
			waitForNumberOfRecords(STRPaginationMsgPattern_anyPage);
			try{
				if(isAscending){
					return dataTable.isTableSortedAsc(columnHeadings,str,paginationOption,tableContext, numRecords);
				}else{
					return dataTable.isTableSortedDesc(columnHeadings,str,paginationOption,tableContext, numRecords);
				}
			}catch(Exception e){
				Log.warn(String.format("Column '%s' of data table is not sortable?", entry.getKey().trim()));
				Log.warn(e.toString());
			}
		}
		return false;
	}

	private TableSortOrder getCurrentColumnSortOrder(WebElement headerElement, Integer columnIndex) {
		Log.method("getCurrentColumnSortOrder", headerElement, columnIndex);
		String classAttrValue = headerElement.getAttribute("class");
		// Get the current sorted order of the column.
		TableSortOrder currTblSortOrder = TableSortOrder.ASC;
		if (classAttrValue.contains("sorting_desc")) {
			currTblSortOrder = TableSortOrder.DESC;
		}
		return currTblSortOrder;
	}

	public void clickOnColumnHeader(Integer columnIndex, Integer numTimesToClick) {
		Log.method("clickOnColumnHeader", columnIndex, numTimesToClick);
		WebElement headerElement = getTableHeader(columnIndex);
		multiClickElement(headerElement, numTimesToClick);
	}

	private void multiClickElement(WebElement element, Integer numTimesToClick) {
		Log.method("multiClickElement", element, numTimesToClick);
		if (element != null && numTimesToClick > 0) {
			for (int i = 0; i < numTimesToClick; i++) {
				element.click();
			}
		}
	}

	public void refreshPageUntilElementFound(String elementXPath) {
		waitForAJAXCallsToComplete();
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				Boolean elementDetected = false;
				WebElement element = null;
				try {
					element = d.findElement(By.xpath(elementXPath));
					elementDetected = WebElementExtender.isElementPresentAndDisplayed(element);
				} catch (Exception ex) {
					Log.warn(String.format("Element with xpath=[%s] NOT found", elementXPath));
					Log.warn(String.format("Refreshing page to find element with xpath=[%s]", elementXPath));
					d.navigate().refresh();
				}
				return elementDetected;
			}
		});
	}

	public boolean checkPaginationSetting(String numberOfRecords) {
		Log.method("checkPaginationSetting", numberOfRecords);
		setPagination(numberOfRecords);
		return this.waitForNumberOfRecords(String.format(STRPaginationMsgPattern_firstPage, numberOfRecords));
	}

	public boolean checkFileExists(String fileName, String downloadPath) {
		Log.method("checkFileExists", fileName, downloadPath);
		Log.info(String.format("Looking for file-[%s] in download directory-[%s]", fileName, downloadPath));
		File file = new File(downloadPath,fileName);
		if(file.exists()){
			Log.info("File found in the download directory");
			return true;
		}
		return false;
	}

	public void waitForFileDownload(String fileName, String downloadPath) {
		Log.method("waitForFileDownload", fileName, downloadPath);
		(new WebDriverWait(driver, timeout + 60)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return checkFileExists(fileName, downloadPath);
			}
		});
	}

	public boolean verifyNoButtonsArePresentInTable() {
		Log.method("verifyNoButtonsArePresentInTable");
		return !WebElementExtender.getInnerHtml(this.firstRowTr).contains(TABLE_BUTTON_CLASS);
	}

	public boolean verifyFieldNotBlank(WebElement validationLabel, String fieldName) {
		Log.method("verifyFieldNotBlank", validationLabel, fieldName);
		if (!WebElementExtender.isElementPresentAndDisplayed(validationLabel)) {
			Log.info(String.format("%s error is NOT displayed.", fieldName));
			return false;
		}
		if (!validationLabel.getText().equals(BLANKFIELDERROR)) {
			Log.info(String.format("%s error text is NOT correct.", fieldName));
			return false;
		}
		Log.info(String.format("%s error validation passed", fieldName));
		return true;
	}

	public boolean waitForNumberOfRecords(String actualMessage){
		return waitForNumberOfRecords(DATATABLE_RECORDS_ELEMENT_BY, actualMessage);
	}

	public boolean waitForNumberOfRecords(By tableInfoBy, String actualMessage) {
		Log.method("waitForNumberOfRecords", actualMessage);
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		waitForAJAXCallsToComplete();
		(new WebDriverWait(TestContext.INSTANCE.getDriver(), timeout)).until(ExpectedConditions.presenceOfElementLocated(tableInfoBy));
		WebElement tableInfoElement = TestContext.INSTANCE.getDriver().findElement(tableInfoBy);
		boolean tableInfoMatches = (new WebDriverWait(TestContext.INSTANCE.getDriver(), timeout + 15)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				String text = getElementText(tableInfoElement).trim();
				boolean matches = text.matches(actualMessage);
				Log.info(String.format("MATCH=[%b] -> Text=[%s], MatchPattern=[%s]", matches, text, actualMessage));
				return matches;
			}
		});
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		return tableInfoMatches;
	}

	/*
	 * Helper method to wait for an Element to be ready on the page.
	 */
	public void waitForElementReady(String elementID) {
		Log.method("waitForElementReady", elementID);
		(new WebDriverWait(TestContext.INSTANCE.getDriver(), this.timeout)).until(ExpectedConditions.presenceOfElementLocated(By.id(elementID)));
	}

	/**
	 * Waits for search results to load once user has performed search in datatable.
	 */
	public void waitForSearchResultsToLoad() {
		Log.method("waitForSearchResultsToLoad");
		(new WebDriverWait(TestContext.INSTANCE.getDriver(), timeout)).until(ExpectedConditions.presenceOfElementLocated(By.id(DATATABLE_RECORDS_ELEMENT_ID)));
		WebElement tableInfoElement = TestContext.INSTANCE.getDriver().findElement(By.id(DATATABLE_RECORDS_ELEMENT_ID));
		(new WebDriverWait(TestContext.INSTANCE.getDriver(), timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				List<String> splitArgs = RegexUtility.split(tableInfoElement.getText(), RegexUtility.SPACE_SPLIT_REGEX_PATTERN);
				return (splitArgs.size()>0 && splitArgs.get(1)!="0");
			}
		});
	}

	public boolean waitForTableDataToLoad() {
		Log.method("waitForTableDataToLoad");
		try{
			(new WebDriverWait(TestContext.INSTANCE.getDriver(), timeout)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return getRecordsShownOnPage(d) > 0;
				}
			});
		}catch(Exception e){
			Log.warn("Empty data table!"+e.toString());
			Log.warn("Current URL = "+ TestContext.INSTANCE.getDriver().getCurrentUrl());
			return false;
		}
		return true;
	}

	public boolean toNextPage(){
		this.nextBtn.click();
		return waitForTableDataToLoad();
	}
	public void waitForDropdownToBePopulated(WebElement dropdownElement) {
		Log.method("waitForDropdownToBePopulated", dropdownElement);
		(new WebDriverWait(TestContext.INSTANCE.getDriver(), timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !new Select(dropdownElement).getOptions().isEmpty();
			}
		});
	}

	public void waitForTextElementToBeCleared(WebElement element) {
		Log.method("waitForTextElementToBeCleared", element);
		(new WebDriverWait(TestContext.INSTANCE.getDriver(), timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return element.getText().isEmpty();
			}
		});
	}

	public void waitForAJAXCallsToComplete() {
		ExpectedCondition<Boolean> jQueryActiveComplete = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					Object jQueryActive = ((JavascriptExecutor)d).executeScript("return jQuery.active");
					if (jQueryActive.toString().equalsIgnoreCase("0")) {
						return true;
					}
				} catch (WebDriverException e) {
					Log.info("jQuery NOT available. Skipping wait on jQuery.active");
					return true;
				}
				return false;
			}
		};
		ExpectedCondition<Boolean> documentReadyComplete = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				Object documentReadyState = ((JavascriptExecutor)d).executeScript("return document.readyState");
				if (documentReadyState.toString().equalsIgnoreCase("complete")) {
					return true;
				}
				return false;
			}
		};
		(new WebDriverWait(driver, timeout)).until(jQueryActiveComplete);
		(new WebDriverWait(driver, timeout)).until(documentReadyComplete);
	}

	public void waitForAnimationToComplete() {
		Log.method("waitForAnimationToComplete");
		ExpectedCondition<Boolean> jQueryAnimationComplete = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					Object jQueryAnimate = ((JavascriptExecutor)d).executeScript("return jQuery(':animated').length");
					if (jQueryAnimate.toString().equalsIgnoreCase("0")) {
						return true;
					}
				} catch (WebDriverException e) {
					Log.info("jQuery NOT available. Skipping wait on jQuery(':animated')");
					return true;
				}
				return false;
			}
		};
		(new WebDriverWait(driver, timeout)).until(jQueryAnimationComplete);
	}

	public void waitForSignalRCallsToComplete() {
		Log.method("waitForSignalRCallsToComplete");
		this.waitForAJAXCallsToComplete();
		this.waitForAnimationToComplete();
	}

	public List<WebElement> getPaginationOption() {
		return paginationOption;
	}

	public int getNumberofRecords() {
		List<WebElement> records = this.numberofRecords;
		return records.size();
	}

	public WebElement getNextBtn() {
		return nextBtn;
	}

	public WebElement getPreviousBtn() {
		return previousBtn;
	}

	public String getFooterText() {
		return labelFooter.getText();
	}

	public String getWebAppVersion() {
		String footerText = getFooterText();
		List<String> matchingGroups = RegexUtility.getMatchingGroups(footerText, RegexUtility.APP_VERSION_PATTERN);
		if (matchingGroups != null && matchingGroups.size() > 0) {
			return matchingGroups.get(0);
		}
		return "<unknown>";
	}
}
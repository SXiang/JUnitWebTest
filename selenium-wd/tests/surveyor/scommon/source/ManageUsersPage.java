/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import common.source.Log;
import common.source.TestSetup;

/**
 * Picarro Admin User's Page Generic User's related code (which can be used by
 * Picarro and Util admin both) should come in this class
 * 
 * @author zlu
 *
 */
public class ManageUsersPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageUsers";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.ManageUsers_PageTitle);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ManageUsers_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ManageUser_NewUser);
	public static final String STREditPageContentText = Resources.getResource(ResourceKeys.ManageUser_EditUser);
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a[1]")
	protected WebElement btnAddNewCustomerUser;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a[2]")
	protected WebElement btnAddNewPicarroUser;

	@FindBy(id = "User_LocationId")
	protected WebElement dropDownCustomer;

	@FindBy(how = How.XPATH, using = "//*[@id='User.UserName']")
	protected WebElement inputEmail;

	@FindBy(how = How.XPATH, using = "//*[@id='User_Password']")
	protected WebElement inputPassword;

	@FindBy(how = How.XPATH, using = "//*[@id='PasswordConfirm']")
	protected WebElement inputPasswordConfirm;

	@FindBy(how = How.XPATH, using = "//*[@id='User_RoleId']")
	protected WebElement dropDownRole;

	@FindBy(css = "a[class='button-cancel btn btn-danger']")
	protected WebElement cancelAddBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='NewPassword']")
	protected WebElement inputNewPassword;

	@FindBy(how = How.XPATH, using = "//*[@id='PasswordConfirm']")
	protected WebElement inputNewPasswordConfirm;

	@FindBy(how = How.XPATH, using = "//*[@id='User_Active']")
	protected WebElement inputAccountEnabled;

	@FindBy(how = How.XPATH, using = "//*[@id='User_TimeZoneId']")
	protected WebElement dropDownTimeZone;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[1]")
	protected WebElement theadUserName;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[2]")
	protected WebElement theadName;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[3]")
	protected WebElement theadLocation;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[4]")
	protected WebElement theadRoles;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[5]")
	protected WebElement theadStatus;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[7]/a[1]")
	protected WebElement btnEditUser;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[6]/a[1]")
	protected WebElement btnEditCustomerUser;

	@FindBy(css = "a[class='button-cancel btn btn-danger']")
	protected WebElement cancelEditBtn;
	
	@FindBy(how = How.ID, using="OldPassword")
	protected WebElement inputOldPassword;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[1]")
    protected WebElement tdUserNameValue;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[3]")
    protected WebElement tdLocationValue;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]")
    protected WebElement tdRoleValue;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[5]")
    protected WebElement tdStatusValue;
    
	// add more web elements here later

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageUsersPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		Log.info("\nThe Manager Users Page URL is: "
				+ this.strPageURL);
	}

	public ManageUsersPage(WebDriver driver, String baseURL,
			TestSetup testSetup, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}

	public void addNewPicarroUser(String email, String password, String role) {
		this.btnAddNewPicarroUser.click();
		this.waitForNewPageLoad();
		
		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);

		List<WebElement> roleOptions = this.dropDownRole.findElements(By
				.tagName("option"));
		for (WebElement roleOption : roleOptions) {
			if (roleOption.getText().trim().equalsIgnoreCase(role))
				roleOption.click();
		}

		this.btnOk.click();

		if (isElementPresent(this.panelDuplicationErrorXPath)) {
			WebElement panelError = driver.findElement(By
					.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase(
					Resources.getResource(ResourceKeys.Validation_SummaryTitle)))
				this.cancelAddBtn.click();
		}
	}

	/**
	 * Adds a new Picarro user.
	 * @param email - Email for new user.
	 * @param password - Password for new user.
	 * @param role - Role for new user.
	 * @param location - Location in format [Customer - Location]
	 * @param timeZone - TimeZone for new user.
	 */
	public void addNewPicarroUser(String email, String password, String role,
			String location, String timeZone) {
		this.btnAddNewPicarroUser.click();
		this.waitForNewPageLoad();

		Select droplist = new Select(this.dropDownCustomer);
		droplist.selectByVisibleText(location);

		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);

		List<WebElement> roleOptions = this.dropDownRole.findElements(By
				.tagName("option"));
		for (WebElement roleOption : roleOptions) {
			if (roleOption.getText().trim().equalsIgnoreCase(role))
				roleOption.click();
		}

		List<WebElement> tzOptions = this.dropDownTimeZone.findElements(By
				.tagName("option"));
		for (WebElement tzOption : tzOptions) {
			if (tzOption.getText().trim().equalsIgnoreCase(timeZone))
				tzOption.click();
		}

		this.btnOk.click();

		if (isElementPresent(this.panelDuplicationErrorXPath)) {
			WebElement panelError = driver.findElement(By
					.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase(
					Resources.getResource(ResourceKeys.Validation_SummaryTitle)))
				this.cancelAddBtn.click();
		}
	}

	public void addNewCustomerUser(String customerName, String email,
			String password, String role, String location) {
		addNewCustomerUser(customerName, email, password, role, location, true /*enabled*/);
	}
	
	public void addNewCustomerUser(String customerName, String email,
			String password, String role, String location, boolean enabled) {
		
		Log.info(String.format("Adding new Customer user. Name=%s, Email=%s, Password=[HIDDEN], Role=%s, Location=%s", customerName, 
				email, role, location));
		
		String custLoc = customerName + " - " + location;
		this.btnAddNewCustomerUser.click();
		
		selectCustomerLocationDropdown(customerName, location);
		
		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);

		List<WebElement> roleOptions = this.dropDownRole.findElements(By.tagName("option"));
		for (WebElement roleOption : roleOptions) {
			if (roleOption.getText().trim().equalsIgnoreCase(role))
				roleOption.click();
		}

		enableDisableUser(enabled);
		
		this.btnOk.click();

		if (isElementPresent(this.panelDuplicationErrorXPath)) {
			WebElement panelError = driver.findElement(By
					.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase(
					Resources.getResource(ResourceKeys.Validation_SummaryTitle)))
				this.cancelAddBtn.click();
		}
		
		this.waitForPageLoad();
	}

	public void addNewCustomerUser(String customerName, String email,
			String password, String role, String timeZone, String location) {
		this.btnAddNewCustomerUser.click();

		List<WebElement> options = this.dropDownCustomer.findElements(By
				.tagName("option"));
		for (WebElement option : options) {
			if (customerName.equalsIgnoreCase(option.getText().trim()))
				option.click();
		}

		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);

		List<WebElement> roleOptions = this.dropDownRole.findElements(By
				.tagName("option"));
		for (WebElement roleOption : roleOptions) {
			if (roleOption.getText().trim().equalsIgnoreCase(role))
				roleOption.click();
		}

		List<WebElement> tzOptions = this.dropDownTimeZone.findElements(By
				.tagName("option"));
		for (WebElement tzOption : tzOptions) {
			if (tzOption.getText().trim().equalsIgnoreCase(timeZone))
				tzOption.click();
		}

		this.btnOk.click();

		if (isElementPresent(this.panelDuplicationErrorXPath)) {
			WebElement panelError = driver.findElement(By
					.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase(
					Resources.getResource(ResourceKeys.Validation_SummaryTitle)))
				this.cancelAddBtn.click();
		}
		
		this.waitForPageLoad();
	}

	public boolean findExistingUser(String userName) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;

		WebElement userNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";

			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return false;
	}

	public boolean findExistingUser(String locationName, String userName, boolean isCustomerUser) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String locationXPath;
		String userNameXPath;

		WebElement locationCell;
		WebElement userNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			
			if (isCustomerUser) {
				locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";
			} else {
				locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]";
			}

			locationCell = table.findElement(By.xpath(locationXPath));
			userNameCell = table.findElement(By.xpath(userNameXPath));
			
			Log.info(String.format("Found User. Location-[%s], Username-[%s]", locationCell.getText().trim(),
					userNameCell.getText().trim()));

			if ((locationCell.getText().trim()).equalsIgnoreCase(locationName)
					&& (userNameCell.getText().trim())
							.equalsIgnoreCase(userName)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return false;
	}

	public boolean findExistingUser(String locationName, String userName,
			String roleName) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String locationXPath;
		String userNameXPath;
		String roleNameXPath;

		WebElement locationCell;
		WebElement userNameCell;
		WebElement roleNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[3]";
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";
			roleNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[4]";

			locationCell = table.findElement(By.xpath(locationXPath));
			userNameCell = table.findElement(By.xpath(userNameXPath));
			roleNameCell = table.findElement(By.xpath(roleNameXPath));

			if ((locationCell.getText().trim()).equalsIgnoreCase(locationName)
					&& (userNameCell.getText().trim())
							.equalsIgnoreCase(userName)
					&& (roleNameCell.getText().trim())
							.equalsIgnoreCase(roleName)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return false;
	}

	public String getUserRole(String userName) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		String roleNameXPath;

		WebElement userNameCell;
		WebElement roleNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";

			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				roleNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
						+ "]/td[4]";
				roleNameCell = table.findElement(By.xpath(roleNameXPath));
				Log.info("Found entry at row=" + rowNum);
				return roleNameCell.getText().trim();
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return null;
	}

	public String getUserStatus(String userName) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		String userStatusXPath;

		WebElement userNameCell;
		WebElement userStatusCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";

			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				userStatusXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
						+ "]/td[5]";
				userStatusCell = table.findElement(By.xpath(userStatusXPath));
				Log.info("Found entry at row=" + rowNum);
				return userStatusCell.getText().trim();
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return null;
	}

	public boolean editUser(String userName, String role, String timeZone,
			boolean accountEnable, boolean isCustomerUser) {
		return editUser(userName, role, timeZone, "" /*customerLocation*/, accountEnable, isCustomerUser);
	}
	
	/**
	 * Edits the specified user.
	 * @param userName - Username to edit
	 * @param roleNew - New Role
	 * @param timeZoneNew - New timezone
	 * @param locationDescNew - Location in format [Customer - Location]
	 * @param accountEnable - Enable or disable user.
	 * @param isCustomerUser - whether logged-in user is a customer user.
	 * @return
	 */
	public boolean editUser(String userName, String roleNew, String timeZoneNew, 
			String locationDescNew, boolean accountEnable, boolean isCustomerUser) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		String actionEditXPath;

		WebElement userNameCell;
		WebElement actionEditCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			userNameCell = table.findElement(By.xpath(userNameXPath));

			Log.info(String.format("Looking for: Username-[%s], Found: Username-[%s]", 
					userName, userNameCell.getText().trim()));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				if (isCustomerUser) {
					actionEditXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[6]/a[1]";
				} else {
					actionEditXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[7]/a[1]";
				}

				actionEditCell = table.findElement(By.xpath(actionEditXPath));
				Log.info("Found cell at xpath=" + actionEditXPath);
				actionEditCell.click();
				this.waitForEditPageLoad();

				if (roleNew != "") {
					selectRoleDropdown(roleNew);
				}
				if (timeZoneNew != "") {
					selectTimeZoneDropdown(timeZoneNew);
				}
				if (locationDescNew != "") {
					selectLocationDropdown(locationDescNew);
				}

				enableDisableUser(accountEnable);
				
				this.btnOk.click();
				this.waitForPageLoad();

				if (table.isDisplayed())
					return true;

				return false;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return false;
	}

	public boolean resetUserPassword(String userName, String newPassword) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		String actionResetPWDXPath;

		WebElement userNameCell;
		WebElement actionResetPWDCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";

			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				actionResetPWDXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
						+ "]/td[6]/a[2]";

				actionResetPWDCell = table.findElement(By
						.xpath(actionResetPWDXPath));
				Log.info("Found cell at xpath=" + actionResetPWDCell);
				actionResetPWDCell.click();
				waitForPageToLoad();

				inputNewPassword.sendKeys(newPassword);
				inputNewPasswordConfirm.sendKeys(newPassword);

				btnOk.click();

				waitForPageToLoad();
				if (table.isDisplayed())
					return true;

				return false;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return false;
	}

	public boolean findExistingUser(String userName, boolean allPages) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;

		WebElement userNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[2]";

			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return false;
	}

	private void enableDisableUser(boolean accountEnable) {
		if (accountEnable) {
			if (!inputAccountEnabled.isSelected())
				inputAccountEnabled.click();
		} else {
			if (inputAccountEnabled.isSelected())
				inputAccountEnabled.click();
		}
	}

	private void selectCustomerLocationDropdown(String customerName, String location) {
		String custLoc = customerName + " - " + location;
		selectLocationDropdown(custLoc);
	}

	private void selectLocationDropdown(String customerLocation) {
		List<WebElement> options = this.dropDownCustomer.findElements(By.tagName("option")); 
		for	(WebElement option : options) { 
			if (option.getText().trim().equalsIgnoreCase(customerLocation))
				option.click(); 
		}
	}

	private void selectTimeZoneDropdown(String timeZone) {
		List<WebElement> optionsTZ = dropDownTimeZone.findElements(By.tagName("option"));
		for (WebElement optionTZ : optionsTZ) {
			if (optionTZ.getText().trim().equals(timeZone))
				optionTZ.click();
		}
	}

	private void selectRoleDropdown(String role) {
		List<WebElement> options = dropDownRole.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().trim().equals(role))
				option.click();
		}
	}
	
	public boolean findExistingUser(String location, String userName,
			String roleName, boolean allPages) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String locationXPath;
		String userNameXPath;
		String roleNameXPath;

		WebElement locationCell;
		WebElement userNameCell;
		WebElement roleNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[3]";
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";
			roleNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[4]";

			locationCell = table.findElement(By.xpath(locationXPath));
			userNameCell = table.findElement(By.xpath(userNameXPath));
			roleNameCell = table.findElement(By.xpath(roleNameXPath));

			if ((locationCell.getText().trim()).equalsIgnoreCase(location)
					&& (userNameCell.getText().trim())
							.equalsIgnoreCase(userName)
					&& (roleNameCell.getText().trim())
							.equalsIgnoreCase(roleName)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return false;
	}

	public List<String> getUserNameList(boolean allPages) {
		return getUserNameList(allPages, Integer.valueOf(PAGINATIONSETTING_100));
	}
	
	public List<String> getUserNameList(boolean allPages, int paginationSize) {
		List<String> userList = new ArrayList<String>();

		String pageSizeStr = String.valueOf(paginationSize);
		setPagination(pageSizeStr);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		WebElement userNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(pageSizeStr))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(pageSizeStr);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";
			userNameCell = table.findElement(By.xpath(userNameXPath));

			userList.add(userNameCell.getText().trim());

			if (rowNum == Integer.parseInt(pageSizeStr)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(pageSizeStr))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(pageSizeStr);

				rowNum = 0;
			}
		}

		return userList;
	}

	public List<String> getNameList(boolean allPages) {
		List<String> userList = new ArrayList<String>();

		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		WebElement userNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[2]";
			userNameCell = table.findElement(By.xpath(userNameXPath));

			userList.add(userNameCell.getText().trim());

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return userList;
	}

	public List<String> getLocationList(boolean allPages) {
		List<String> locationList = new ArrayList<String>();
		setPagination(PAGINATIONSETTING_100);
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String locationXPath;
		WebElement locationCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[3]";
			locationCell = table.findElement(By.xpath(locationXPath));

			locationList.add(locationCell.getText().trim());

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}
		return locationList;
	}

	public List<String> getRolesList(boolean allPages) {
		List<String> rolesList = new ArrayList<String>();
		setPagination(PAGINATIONSETTING_100);
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String rolesXPath;
		WebElement rolesCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			rolesXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]";
			rolesCell = table.findElement(By.xpath(rolesXPath));

			rolesList.add(rolesCell.getText().trim());

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}
		return rolesList;
	}

	public List<String> getStatusList(boolean allPages) {
		List<String> statusList = new ArrayList<String>();
		setPagination(PAGINATIONSETTING_100);
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String statusXPath;
		WebElement statusCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			statusXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]";
			statusCell = table.findElement(By.xpath(statusXPath));

			statusList.add(statusCell.getText().trim());

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}
		return statusList;
	}
	
	public WebElement getTheadUserName() {
		return this.theadUserName;
	}

	public WebElement getTheadName() {
		return this.theadName;
	}

	public WebElement getTheadLocation() {
		return this.theadLocation;
	}
	
	public WebElement getTheadRoles() {
		return this.theadRoles;
	}
	
	public WebElement getTheadStatus() {
		return this.theadStatus;
	}

	public void clickOnAddNewPicarroUserBtn() {
		this.btnAddNewPicarroUser.click();
	}

	public void clickOnAddNewCustomerUserBtn() {
		this.btnAddNewCustomerUser.click();
	}

	public void clickOnCancelAddBtn() {
		this.cancelAddBtn.click();
	}

	public void clickOnFirstEditUserBtn() {
		this.btnEditUser.click();
	}

	public void clickOnCustomerFirstEditUserBtn() {
		this.btnEditCustomerUser.click();
	}

	public void clickOnCancelEditBtn() {
		this.cancelEditBtn.click();
	}
	
    @Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }

	public void waitForNewPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRNewPageContentText);
            }
        });
    }

    public void waitForEditPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STREditPageContentText);
            }
        });
    }

	public void changeUserPassword(String oldPassword, String newPassword) {
		this.dropDownUser.click();
		this.linkChangePwd.click();
		waitForPageToLoad();

		this.inputOldPassword.sendKeys(oldPassword);
		this.inputNewPassword.sendKeys(newPassword);
		this.inputNewPasswordConfirm.sendKeys(newPassword);
		btnOk.click();
		waitForPageToLoad();
	}
	
	public boolean searchUser(String userName, String locationName,
			String role, String status) {
		this.getInputSearch().sendKeys(userName);
		try {
			if (this.tdUserNameValue.getText().contentEquals(userName)) {
				if (this.tdLocationValue.getText().contentEquals(locationName)) {
					if (this.tdRoleValue.getText().contentEquals(role)) {
						if (this.tdStatusValue.getText().contentEquals(status))
							return true;
					}
				}
			}
		} catch (NoSuchElementException ne) {
			Log.info(ne.toString());
			return false;
		}
		return false;
	}
}
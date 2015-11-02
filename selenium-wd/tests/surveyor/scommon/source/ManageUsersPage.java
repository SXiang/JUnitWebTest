/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public static final String STRPageTitle = "Manage Users - Surveyor";
	public static final String STRPageContentText = "Manage Users";
	
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

	@FindBy(how = How.XPATH, using = "//*[@id='user-form']/div/div[8]/div[2]/a")
	protected WebElement cancelEditBtn;

	// add more web elements here later

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageUsersPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		System.out.println("\nThe Manager Users Page URL is: "
				+ this.strPageURL);
	}

	public ManageUsersPage(WebDriver driver, String baseURL,
			TestSetup testSetup, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}

	public void addNewPicarroUser(String email, String password, String role) {
		this.btnAddNewPicarroUser.click();

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
					"Please, correct the following errors:"))
				this.cancelAddBtn.click();
		}
	}

	public void addNewPicarroUser(String email, String password, String role,
			String location, String timeZone) {
		this.btnAddNewPicarroUser.click();

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

		List<WebElement> tzOptions = this.dropDownRole.findElements(By
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
					"Please, correct the following errors:"))
				this.cancelAddBtn.click();
		}
	}

	public void addNewCustomerUser(String customerName, String email,
			String password, String role, String location) {
		String custLoc = customerName + " - " + location;
		this.btnAddNewCustomerUser.click();
		
		List<WebElement> options = this.dropDownCustomer.findElements(By.tagName("option")); 
		for	(WebElement option : options) { 
			if (option.getText().trim().equalsIgnoreCase(custLoc))
				option.click(); 
		}
		
		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);

		List<WebElement> roleOptions = this.dropDownRole.findElements(By.tagName("option"));
		for (WebElement roleOption : roleOptions) {
			if (roleOption.getText().trim().equalsIgnoreCase(role))
				roleOption.click();
		}

		this.btnOk.click();

		if (isElementPresent(this.panelDuplicationErrorXPath)) {
			WebElement panelError = driver.findElement(By
					.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase(
					"Please, correct the following errors:"))
				this.cancelAddBtn.click();
		}
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

		List<WebElement> tzOptions = this.dropDownRole.findElements(By
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
					"Please, correct the following errors:"))
				this.cancelAddBtn.click();
		}
	}

	public boolean findExistingUser(String userName) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;

		WebElement userNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";

			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return false;
	}

	public boolean findExistingUser(String locationName, String userName) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String locationXPath;
		String userNameXPath;

		WebElement locationCell;
		WebElement userNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[3]";
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";

			locationCell = table.findElement(By.xpath(locationXPath));
			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((locationCell.getText().trim()).equalsIgnoreCase(locationName)
					&& (userNameCell.getText().trim())
							.equalsIgnoreCase(userName)) {

				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return false;
	}

	public boolean findExistingUser(String locationName, String userName,
			String roleName) {
		setPagination(PAGINATIONSETTING);

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

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

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
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return false;
	}

	public String getUserRole(String userName) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		String roleNameXPath;

		WebElement userNameCell;
		WebElement roleNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";

			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				roleNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
						+ "]/td[4]";
				roleNameCell = table.findElement(By.xpath(roleNameXPath));

				return roleNameCell.getText().trim();
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return null;
	}

	public String getUserStatus(String userName) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		String userStatusXPath;

		WebElement userNameCell;
		WebElement userStatusCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";

			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				userStatusXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
						+ "]/td[5]";
				userStatusCell = table.findElement(By.xpath(userStatusXPath));

				return userStatusCell.getText().trim();
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return null;
	}

	public boolean editUser(String userName, String role, String timeZone,
			boolean accountEnable) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		String actionEditXPath;

		WebElement userNameCell;
		WebElement actionEditCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";

			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
						+ "]/td[6]/a[1]";

				actionEditCell = table.findElement(By.xpath(actionEditXPath));

				actionEditCell.click();

				List<WebElement> options = dropDownRole.findElements(By
						.tagName("option"));
				for (WebElement option : options) {
					if (option.getText().trim().equals(role))
						option.click();
				}

				List<WebElement> optionsTZ = dropDownTimeZone.findElements(By
						.tagName("option"));
				for (WebElement optionTZ : optionsTZ) {
					if (optionTZ.getText().trim().equals(timeZone))
						optionTZ.click();
				}

				if (accountEnable) {
					if (!inputAccountEnabled.isSelected())
						inputAccountEnabled.click();
				} else {
					if (inputAccountEnabled.isSelected())
						inputAccountEnabled.click();
				}

				this.btnOk.click();

				if (table.isDisplayed())
					return true;

				return false;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return false;
	}

	public boolean resetUserPassword(String userName, String newPassword) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		String actionResetPWDXPath;

		WebElement userNameCell;
		WebElement actionResetPWDCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";

			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				actionResetPWDXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
						+ "]/td[6]/a[2]";

				actionResetPWDCell = table.findElement(By
						.xpath(actionResetPWDXPath));

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

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return false;
	}

	public boolean findExistingUser(String userName, boolean allPages) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;

		WebElement userNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[2]";

			userNameCell = table.findElement(By.xpath(userNameXPath));

			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return false;
	}

	public boolean findExistingUser(String location, String userName,
			String roleName, boolean allPages) {
		setPagination(PAGINATIONSETTING);

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

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

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
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return false;
	}

	public List<String> getUserNameList(boolean allPages) {
		List<String> userList = new ArrayList<String>();

		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		WebElement userNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";
			userNameCell = table.findElement(By.xpath(userNameXPath));

			userList.add(userNameCell.getText().trim());

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return userList;
	}

	public List<String> getNameList(boolean allPages) {
		List<String> userList = new ArrayList<String>();

		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String userNameXPath;
		WebElement userNameCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[2]";
			userNameCell = table.findElement(By.xpath(userNameXPath));

			userList.add(userNameCell.getText().trim());

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return userList;
	}

	public List<String> getLocationList(boolean allPages) {
		List<String> locationList = new ArrayList<String>();
		setPagination(PAGINATIONSETTING);
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String locationXPath;
		WebElement locationCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[3]";
			locationCell = table.findElement(By.xpath(locationXPath));

			locationList.add(locationCell.getText().trim());

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}
		return locationList;
	}

	public List<String> getRolesList(boolean allPages) {
		List<String> rolesList = new ArrayList<String>();
		setPagination(PAGINATIONSETTING);
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String rolesXPath;
		WebElement rolesCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			rolesXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]";
			rolesCell = table.findElement(By.xpath(rolesXPath));

			rolesList.add(rolesCell.getText().trim());

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}
		return rolesList;
	}

	public List<String> getStatusList(boolean allPages) {
		List<String> statusList = new ArrayList<String>();
		setPagination(PAGINATIONSETTING);
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String statusXPath;
		WebElement statusCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			statusXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]";
			statusCell = table.findElement(By.xpath(statusXPath));

			statusList.add(statusCell.getText().trim());

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

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
}
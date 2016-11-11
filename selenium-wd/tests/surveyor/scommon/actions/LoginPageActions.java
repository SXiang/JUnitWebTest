package surveyor.scommon.actions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.BaseHelper;
import common.source.ExcelUtility;
import common.source.RegexUtility;
import common.source.TestSetup;
import surveyor.dataaccess.source.Customer;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.UserDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;

public class LoginPageActions extends BasePageActions {
	private static final String REGEX_PATTERN_SPLIT_BY_COLON = ":";
	private LoginPage loginPage = null;
	private HomePage homePage = null;
	private UserDataReader dataReader = null;

	public static ThreadLocal<UserDataRow> workingDataRow = new ThreadLocal<UserDataRow>();    // Stores the workingDataRow from login action

	public LoginPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		loginPage = new LoginPage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver, loginPage);
		homePage = new HomePage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver, homePage);
		dataReader = new UserDataReader(this.excelUtility);
	}

	// Note: Not thread-safe.
	public static void clearStoredObjects() {
		workingDataRow.set(null);
	}

	public UserDataRow getUsernamePassword(String usernameColonPassword, Integer dataRowID) throws Exception {
		UserDataRow dataRow = null;
		if (!BaseHelper.isNullOrEmpty(usernameColonPassword)) {
			List<String> userPassList = RegexUtility.split(usernameColonPassword, REGEX_PATTERN_SPLIT_BY_COLON);
			if (userPassList == null || userPassList.size()!=2) {
				throw new Exception("Invalid argument value for username/password. Value should be in format [username:password]");
			}
			dataRow = dataReader.createDataRow("", userPassList.get(0), userPassList.get(1), "", "",
					"", "", "", "", "", "", "");
		} else if (workingDataRow.get() != null) {
			dataRow = workingDataRow.get();
		} else if (dataRowID >0) {
			dataRow = dataReader.getDataRow(dataRowID);
		} else {
			throw new Exception("Neither 'usernameColonPassword' nor 'dataRowID' specified for action 'login'");
		}
		return dataRow;
	}

	public Customer getLoggedInUserCustomer() throws Exception, IOException {
		Customer customer = null;
    	if (LoginPageActions.workingDataRow.get() != null) {
    		CustomerDataReader customerDataReader = new CustomerDataReader(this.excelUtility);
    		Integer customerRowID = Integer.valueOf(LoginPageActions.workingDataRow.get().customerRowID);
			CustomerDataRow customerDataRow = customerDataReader.getDataRow(customerRowID);
			customer = new Customer().get(customerDataRow.name);
    	}
		return customer;
	}

	public boolean open(String data, Integer dataRowID) throws Exception {
		logAction("LoginPageActions.open", data, dataRowID);
		loginPage.open();
		return true;
	}

	public boolean login(String usernameColonPassword, Integer dataRowID) throws Exception {
		UserDataRow dataRow = getUsernamePassword(usernameColonPassword, dataRowID);
		logAction("LoginPageActions.login", String.format("username=[%s],password=[%s]", dataRow.username, "HIDDEN"), dataRowID);
		loginPage.loginNormalAs(dataRow.username, dataRow.password);
		homePage.waitForPageLoad();
		// store the working login datarow
		workingDataRow.set(dataRow);
		return true;
	}

	public boolean verifyAccountEnabled(String usernameColonPassword, Integer dataRowID) throws Exception {
		logAction("LoginPageActions.verifyAccountEnabled", usernameColonPassword, dataRowID);
		return !loginPage.isAccountDisabled();
	}

	public boolean verifyAccountDisabled(String usernameColonPassword, Integer dataRowID) throws Exception {
		logAction("LoginPageActions.verifyAccountDisabled", usernameColonPassword, dataRowID);
		return !verifyAccountEnabled(usernameColonPassword, dataRowID);
	}

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("login")) { return this.login(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("sortRecordsBy")) { return this.sortRecordsBy(data, dataRowID); }
		else if (actionName.equals("verifyAccountDisabled")) { return this.verifyAccountDisabled(data, dataRowID); }
		else if (actionName.equals("verifyAccountEnabled")) { return this.verifyAccountEnabled(data, dataRowID); }
		return false;
	}
}

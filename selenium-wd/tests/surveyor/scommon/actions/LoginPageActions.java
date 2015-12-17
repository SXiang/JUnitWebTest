package surveyor.scommon.actions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.RegexUtility;
import common.source.TestSetup;
import surveyor.scommon.source.LoginPage;

public class LoginPageActions extends BasePageActions {
	private static final String REGEX_PATTERN_SPLIT_BY_COLON = ":";
	public static final int Excel_TestData_Report_Col_RowID = 0;	
	public static final int Excel_TestData_Report_Col_Username = 1;
	public static final int Excel_TestData_Report_Col_Password = 2;
	public static final int Excel_TestData_Report_Col_Enabled = 3;
	public static final int Excel_TestData_Report_Col_Role = 4;
	
	private static final String TESTDATA_SHEET_NAME = "User Test Data";
	
	private LoginPage loginPage = null;
	
	public class LoginDataRow {
		public String rowID;
		public String username;
		public String password;
		public String enabled;
		public String role;
		
		public LoginDataRow(String rowID, String username, String password,
				String enabled, String role) throws Exception {
			this.rowID = rowID;
			this.username = username;
			this.password = password;
			this.enabled = enabled;
			this.role = role;
		}
	}	

	private LoginDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_RowID, TESTDATA_SHEET_NAME);
		String username = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Username, TESTDATA_SHEET_NAME);
		String password = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Password, TESTDATA_SHEET_NAME);
		// username and password could be ActionFunctions. Check and get value.
		username = ActionArguments.evaluateArgForFunction(username);
		password = ActionArguments.evaluateArgForFunction(password);
		String enabled = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Enabled, TESTDATA_SHEET_NAME);
		String role = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Role, TESTDATA_SHEET_NAME);
		return new LoginDataRow(rowID, username, password, enabled, role);
	}
	
	public LoginPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		loginPage = new LoginPage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver, loginPage);
	}

	public LoginDataRow getUsernamePassword(String usernameColonPassword, Integer dataRowID) throws Exception {
		LoginDataRow dataRow = null;
		if (usernameColonPassword != null && !usernameColonPassword.isEmpty()) {		
			List<String> userPassList = RegexUtility.split(usernameColonPassword, REGEX_PATTERN_SPLIT_BY_COLON);
			if (userPassList == null || userPassList.size()!=2) {
				throw new Exception("Invalid argument value for username/password. Value should be in format [username:password]");
			}
			dataRow = new LoginDataRow(userPassList.get(0), userPassList.get(1), "", "", "");
		} else if (dataRowID >0) {
			dataRow = getDataRow(dataRowID);
		} else {
			throw new Exception("Neither 'usernameColonPassword' nor 'dataRowID' specified for action 'login'");
		}
		return dataRow;
	}

	public boolean open(String data, Integer dataRowID) throws Exception {
		loginPage.open();
		return true;
	}

	public boolean login(String usernameColonPassword, Integer dataRowID) throws Exception {
		LoginDataRow dataRow = getUsernamePassword(usernameColonPassword, dataRowID);
		loginPage.loginNormalAs(dataRow.username, dataRow.password);
		return true;
	}

	public boolean verifyAccountEnabled(String usernameColonPassword, Integer dataRowID) throws Exception {
		return !loginPage.isAccountDisabled();
	}
	
	public boolean verifyAccountDisabled(String usernameColonPassword, Integer dataRowID) throws Exception {
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
		else if (actionName.equals("verifyAccountDisabled")) { return this.verifyAccountDisabled(data, dataRowID); }
		else if (actionName.equals("verifyAccountEnabled")) { return this.verifyAccountEnabled(data, dataRowID); }
		return false;
	}
}

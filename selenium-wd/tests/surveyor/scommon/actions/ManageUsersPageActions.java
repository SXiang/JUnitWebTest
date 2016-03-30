package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;
import surveyor.scommon.actions.data.UserDataReader;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.source.ManageUsersPage;

public class ManageUsersPageActions extends BasePageActions {

	public ManageUsersPageActions(WebDriver driver, String baseURL) {
		super(driver, baseURL);
	}

	private ManageUsersPage manageUsersPage = null;
	private UserDataReader dataReader = null;
	public static ManageUsersPage workingCustomer = null;      // Stores the ManageUserssPage object from createNewUser action
	public static UserDataRow workingDataRow = null;    // Stores the workingDataRow from createNewUser action

	public ManageUsersPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		manageUsersPage = new ManageUsersPage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);
		
		setDataReader(new UserDataReader(this.excelUtility));
	}
	
	private void setDataReader(UserDataReader customerDataReader) {
		this.dataReader = customerDataReader;	
	}

	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction("ManageUsersPageActions.open", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes createNewUser action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean createNewUser(String data, Integer dataRowID) {
		logAction("ManageUsersPageActions.createNewUser", data, dataRowID);
		return true;
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
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("createNewUser")) { return this.createNewUser(data, dataRowID); }
		return false;
	}
}

package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.LocationDataReader;
import surveyor.scommon.actions.data.LocationDataReader.LocationDataRow;
import surveyor.scommon.actions.data.UserDataReader;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.source.ManageUsersPage;

public class ManageUsersPageActions extends BasePageActions {

	public ManageUsersPageActions(WebDriver driver, String baseURL) {
		super(driver, baseURL);
	}

	private ManageUsersPage manageUsersPage = null;
	private UserDataReader dataReader = null;
	public static ThreadLocal<ManageUsersPage> workingUser = new ThreadLocal<ManageUsersPage>();   // Stores the ManageUsersPage object from createNewCustomerUser or createNewPicarroUser action
	public static ThreadLocal<UserDataRow> workingDataRow = new ThreadLocal<UserDataRow>();    // Stores the workingDataRow from createNewCustomerUser or createNewPicarroUser action

	public ManageUsersPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		manageUsersPage = new ManageUsersPage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);
		
		setDataReader(new UserDataReader(this.excelUtility));
	}
	
	private void setDataReader(UserDataReader customerDataReader) {
		this.dataReader = customerDataReader;	
	}
	
	// Note: Not thread-safe.
	public static void clearStoredObjects() {
		workingUser.set(null);
		workingDataRow.set(null);
	}

	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction("ManageUsersPageActions.open", data, dataRowID);
		this.manageUsersPage.open();
		this.manageUsersPage.waitForPageLoad();
		return true;
	}

	/**
	 * Executes createNewPicarroUser action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean createNewPicarroUser(String data, Integer dataRowID) throws Exception {
		logAction("ManageUsersPageActions.createNewPicarroUser", data, dataRowID);
		workingDataRow.set(this.dataReader.getDataRow(dataRowID));
		LocationDataRow locationDataRow = getLocationDataRow(workingDataRow.get());
		CustomerDataRow customerDataRow = getCustomerDataRow(workingDataRow.get());
		this.manageUsersPage.addNewPicarroUser(workingDataRow.get().username, 
				workingDataRow.get().password, workingDataRow.get().role, customerDataRow.name + " - " + locationDataRow.name, 
				workingDataRow.get().timezone);
		workingUser.set(this.manageUsersPage);
		return true;
	}

	/**
	 * Executes createNewCustomerUser action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean createNewCustomerUser(String data, Integer dataRowID) throws Exception {
		logAction("ManageUsersPageActions.createNewCustomerUser", data, dataRowID);
		workingDataRow.set(this.dataReader.getDataRow(dataRowID));
		LocationDataRow locationDataRow = getLocationDataRow(workingDataRow.get());
		CustomerDataRow customerDataRow = getCustomerDataRow(workingDataRow.get());
		this.manageUsersPage.addNewCustomerUser(customerDataRow.name, 
				workingDataRow.get().username, workingDataRow.get().password, workingDataRow.get().role, 
				locationDataRow.name, Boolean.parseBoolean(workingDataRow.get().enabled));
		workingUser.set(this.manageUsersPage);
		return true;
	}

	/**
	 * Returns the location data row for the specified user data row.
	 * @param userDataRow - user data row with location row id information.
	 * @return - location data row
	 * @throws Exception
	 */
	private LocationDataRow getLocationDataRow(UserDataRow userDataRow) throws Exception {
		if (ManageLocationPageActions.workingDataRow.get() != null) {
			return ManageLocationPageActions.workingDataRow.get();
		} else {
			LocationDataReader locationDataReader = new LocationDataReader(excelUtility);
			LocationDataRow locationDataRow = locationDataReader.getDataRow(Integer.parseInt(userDataRow.locationRowID));
			return locationDataRow;
		}
	}

	/**
	 * Returns the customer data row for the specified user data row.
	 * @param userDataRow - user data row with customer row id information.
	 * @return - customer data row
	 * @throws Exception
	 */
	private CustomerDataRow getCustomerDataRow(UserDataRow userDataRow) throws Exception {
		if (ManageCustomerPageActions.workingDataRow.get() != null) {
			return ManageCustomerPageActions.workingDataRow.get();
		} else {
			CustomerDataReader customerDataReader = new CustomerDataReader(excelUtility);
			CustomerDataRow customerDataRow = customerDataReader.getDataRow(Integer.parseInt(userDataRow.customerRowID));
			return customerDataRow;
		}
	}

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("createNewCustomerUser")) { return this.createNewCustomerUser(data, dataRowID); }
		else if (actionName.equals("createNewPicarroUser")) { return this.createNewPicarroUser(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("sortRecordsBy")) { return this.sortRecordsBy(data, dataRowID); }
		return false;
	}
}

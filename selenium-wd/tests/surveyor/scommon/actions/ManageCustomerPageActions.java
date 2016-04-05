package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.source.ManageCustomersPage;

public class ManageCustomerPageActions extends BasePageActions {

	private ManageCustomersPage manageCustomersPage = null;
	private CustomerDataReader dataReader = null;
	public static ManageCustomersPage workingCustomer = null;      // Stores the ManageCustomersPage object from createNewCustomer action
	public static CustomerDataRow workingDataRow = null;    // Stores the workingDataRow from createNewCustomer action

	public ManageCustomerPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		manageCustomersPage = new ManageCustomersPage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver, manageCustomersPage);
		
		setDataReader(new CustomerDataReader(this.excelUtility));
	}
	
	private void setDataReader(CustomerDataReader customerDataReader) {
		this.dataReader = customerDataReader;	
	}

	/**
	 * Executes createNewCustomer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean createNewCustomer(String data, Integer dataRowID) throws Exception {
		logAction("ManageCustomersPageActions.createNewCustomer", data, dataRowID);
		workingDataRow = this.dataReader.getDataRow(dataRowID);
		this.manageCustomersPage.addNewCustomer(workingDataRow.name, workingDataRow.eULA, Boolean.parseBoolean(workingDataRow.enabled));
		workingCustomer = this.manageCustomersPage;
		return true;
	}
 
	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction("ManageCustomersPageActions.open", data, dataRowID);
		this.manageCustomersPage.open();
		this.manageCustomersPage.waitForPageLoad();
		return true;
	}
 
	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("createNewCustomer")) { return this.createNewCustomer(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		return false;
	}
}
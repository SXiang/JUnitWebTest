package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.LocationDataReader;
import surveyor.scommon.actions.data.LocationDataReader.LocationDataRow;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;

public class ManageLocationPageActions extends BasePageActions {

	private LocationDataReader dataReader = null;
	public static LocationDataRow workingDataRow = null;    // Stores the workingDataRow from createNewLocation action

	public ManageLocationPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		initializePageObject(driver, new ManageLocationsPage(driver, strBaseURL, testSetup));
		setDataReader(new LocationDataReader(this.excelUtility));
	}
	
	private void setDataReader(LocationDataReader customerDataReader) {
		this.dataReader = customerDataReader;	
	}

	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction("ManageLocationPageActions.open", data, dataRowID);
		this.getManageLocationsPage().open();
		return true;
	}

	/**
	 * Executes createNewLocation action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean createNewLocation(String data, Integer dataRowID) throws Exception {
		logAction("ManageLocationPageActions.addNewLocation", data, dataRowID);
		LocationDataReader locationDataReader = new LocationDataReader(excelUtility);
		LocationDataRow locationDataRow = locationDataReader.getDataRow(dataRowID);

		String customer = "";
		if (ManageCustomerPageActions.workingDataRow != null) {
			customer = ManageCustomerPageActions.workingDataRow.name;
		} else {	
			CustomerDataReader customerDataReader = new CustomerDataReader(excelUtility);
			CustomerDataRow customerDataRow = customerDataReader.getDataRow(Integer.valueOf(locationDataRow.customerDataRowID));
			customer = customerDataRow.name;
		}
		
		String locationDesc = locationDataRow.name;
		String newLocationName = locationDataRow.name;
		boolean useLatLongSelector = false;
		String ethMthMin = locationDataRow.ethMethRatioMin;
		String ethMthMax = locationDataRow.ethMethRatioMax;
		boolean checkForError = false;
		this.getManageLocationsPage().setLatitude(locationDataRow.latitude);
		this.getManageLocationsPage().setLongitude(locationDataRow.longitude);
		this.getManageLocationsPage().addNewLocation(locationDesc, customer, newLocationName, 
				useLatLongSelector, ethMthMin, ethMthMax, checkForError);
		
		workingDataRow = locationDataRow;
		
		return true;
	}

	private ManageLocationsPage getManageLocationsPage() {
		return (ManageLocationsPage)this.getPageObject();
	}

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("createNewLocation")) { return this.createNewLocation(data, dataRowID); }
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

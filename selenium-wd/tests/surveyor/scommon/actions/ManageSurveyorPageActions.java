package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;

import common.source.TestSetup;
import surveyor.scommon.actions.data.SurveyorDataReader;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.SurveyorDataReader.SurveyorDataRow;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.LocationDataReader;
import surveyor.scommon.actions.data.LocationDataReader.LocationDataRow;
import surveyor.scommon.source.ManageSurveyorPage;

public class ManageSurveyorPageActions extends BasePageActions {

	private SurveyorDataReader dataReader = null;
	public static ThreadLocal<SurveyorDataRow> workingDataRow = new ThreadLocal<SurveyorDataRow>();    // Stores the workingDataRow from createNewSurveyor action

	public ManageSurveyorPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		initializePageObject(driver, new ManageSurveyorPage(driver, strBaseURL, testSetup));
		setDataReader(new SurveyorDataReader(this.excelUtility));
	}
	
	private void setDataReader(SurveyorDataReader customerDataReader) {
		this.dataReader = customerDataReader;	
	}

	// Note: Not thread-safe.
	public static void clearStoredObjects() {
		workingDataRow.set(null);
	}

	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction("ManageSurveyorPageActions.open", data, dataRowID);
		this.getManageSurveyorPage().open();
		return true;
	}

	/**
	 * Executes createNewSurveyor action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public boolean createNewSurveyor(String data, Integer dataRowID) throws NumberFormatException, Exception {
		logAction("ManageSurveyorPageActions.createNewSurveyor", data, dataRowID);
		SurveyorDataReader surveyorDataReader = new SurveyorDataReader(excelUtility);
		SurveyorDataRow surveyorDataRow = surveyorDataReader.getDataRow(dataRowID);
		String surveyorDesc = surveyorDataRow.description;
		
		String customerName = null;
		if (ManageCustomerPageActions.workingDataRow.get() != null) {
			customerName = ManageCustomerPageActions.workingDataRow.get().name;
		} else {	
			CustomerDataReader customerDataReader = new CustomerDataReader(excelUtility);
			CustomerDataRow customerDataRow = customerDataReader.getDataRow(Integer.valueOf(surveyorDataRow.customerRowID));
			customerName = customerDataRow.name;
		}
		
		String locationName = null;
		if (ManageLocationPageActions.workingDataRow.get() != null) {
			locationName = ManageLocationPageActions.workingDataRow.get().name;
		} else {	
			LocationDataReader locationDataReader = new LocationDataReader(excelUtility);
			LocationDataRow locationDataRow = locationDataReader.getDataRow(Integer.valueOf(surveyorDataRow.locationRowID));
			locationName = locationDataRow.name;
		}
		
		this.getManageSurveyorPage().addNewSurveyor(surveyorDesc, locationName, customerName);
		
		workingDataRow.set(surveyorDataRow);
		
		return true;
	}
 
	private ManageSurveyorPage getManageSurveyorPage() {
		return (ManageSurveyorPage)this.getPageObject();
	}

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("createNewSurveyor")) { return this.createNewSurveyor(data, dataRowID); }
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

package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;

import common.source.TestSetup;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.LocationDataReader;
import surveyor.scommon.actions.data.RefGasBottleDataReader;
import surveyor.scommon.actions.data.SurveyorDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.LocationDataReader.LocationDataRow;
import surveyor.scommon.actions.data.RefGasBottleDataReader.RefGasBottleDataRow;
import surveyor.scommon.actions.data.SurveyorDataReader.SurveyorDataRow;
import surveyor.scommon.source.ManageRefGasBottlesPage;

public class ManageRefGasBottlesPageActions extends BasePageActions {

	private RefGasBottleDataReader dataReader = null;
	public static RefGasBottleDataRow workingDataRow = null;    // Stores the workingDataRow from createNewRefGasBottle action

	public ManageRefGasBottlesPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		initializePageObject(driver, new ManageRefGasBottlesPage(driver, testSetup, strBaseURL));
		setDataReader(new RefGasBottleDataReader(this.excelUtility));
	}
	
	private void setDataReader(RefGasBottleDataReader customerDataReader) {
		this.dataReader = customerDataReader;	
	}

	// Note: Not thread-safe.
	public static void clearStoredObjects() {
		workingDataRow = null;
	}

	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction("ManageRefGasBottlePageActions.open", data, dataRowID);
		this.getManageRefGasBottlesPage().open();
		return true;
	}

	/**
	 * Executes createNewRefGasBottle action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean createNewRefGasBottle(String data, Integer dataRowID) throws Exception {
		logAction("ManageRefGasBottlePageActions.addNewRefGasBottle", data, dataRowID);
		RefGasBottleDataReader refGasBottleDataReader = new RefGasBottleDataReader(excelUtility);
		RefGasBottleDataRow refGasBottleDataRow = refGasBottleDataReader.getDataRow(dataRowID);
		String strLotNumber = refGasBottleDataRow.lotNumber;
		String strIsoValue = refGasBottleDataRow.isotopicValue;
		String ethMthRto = refGasBottleDataRow.ethaneMethaneRatio;

		String customerName = null;
		if (ManageCustomerPageActions.workingDataRow != null) {
			customerName = ManageCustomerPageActions.workingDataRow.name;
		} else {	
			if (ManageSurveyorPageActions.workingDataRow != null) {
				SurveyorDataReader surveyorDataReader = new SurveyorDataReader(excelUtility);
				SurveyorDataRow surveyorDataRow = surveyorDataReader.getDataRow(Integer.valueOf(refGasBottleDataRow.surveyorRowID));
				CustomerDataReader customerDataReader = new CustomerDataReader(excelUtility);
				CustomerDataRow customerDataRow = customerDataReader.getDataRow(Integer.valueOf(surveyorDataRow.customerRowID));
				customerName = customerDataRow.name;
			} else {
				throw new Exception("Could not determine the correct customer to use for the the Analyzer.");
			}
		}
		
		String surveyor = null;
		if (ManageSurveyorPageActions.workingDataRow != null) {
			surveyor = ManageSurveyorPageActions.workingDataRow.description;
		} else {
			SurveyorDataReader surveyorDataReader = new SurveyorDataReader(excelUtility);
			SurveyorDataRow surveyorDataRow = surveyorDataReader.getDataRow(Integer.valueOf(refGasBottleDataRow.surveyorRowID));
			surveyor = surveyorDataRow.description;
		}

		String locationName = null;
		if (ManageLocationPageActions.workingDataRow != null) {
			locationName = ManageLocationPageActions.workingDataRow.name;
		} else {
			LocationDataReader locationDataReader = new LocationDataReader(excelUtility);
			LocationDataRow locationDataRow = locationDataReader.getDataRow(Integer.valueOf(refGasBottleDataRow.locationRowID));
			locationName = locationDataRow.name;
		}
		
		this.getManageRefGasBottlesPage().addNewRefGasBottle(strLotNumber, strIsoValue, ethMthRto, customerName, locationName, surveyor);
		
		workingDataRow = refGasBottleDataRow;
		
		return true;
	}

	private ManageRefGasBottlesPage getManageRefGasBottlesPage() {
		return (ManageRefGasBottlesPage)this.getPageObject();
	}
	
	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("createNewRefGasBottle")) { return this.createNewRefGasBottle(data, dataRowID); }
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

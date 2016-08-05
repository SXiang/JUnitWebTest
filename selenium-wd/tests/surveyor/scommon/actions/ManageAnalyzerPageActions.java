package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;

import common.source.TestSetup;
import surveyor.scommon.actions.data.AnalyzerDataReader;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.AnalyzerDataReader.AnalyzerDataRow;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.LocationDataReader;
import surveyor.scommon.actions.data.LocationDataReader.LocationDataRow;
import surveyor.scommon.actions.data.SurveyorDataReader;
import surveyor.scommon.actions.data.SurveyorDataReader.SurveyorDataRow;
import surveyor.scommon.source.ManageAnalyzersPage;

public class ManageAnalyzerPageActions extends BasePageActions {

	private AnalyzerDataReader dataReader = null;
	public static AnalyzerDataRow workingDataRow = null;    // Stores the workingDataRow from createNewAnalyzer action

	public ManageAnalyzerPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		initializePageObject(driver, new ManageAnalyzersPage(driver, strBaseURL, testSetup));
		setDataReader(new AnalyzerDataReader(this.excelUtility));
	}
	
	private void setDataReader(AnalyzerDataReader customerDataReader) {
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
		logAction("ManageAnalyzerPageActions.open", data, dataRowID);
		this.getManageAnalyzersPage().open();
		return true;
	}

	/**
	 * Executes createNewAnalyzer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public boolean createNewAnalyzer(String data, Integer dataRowID) throws NumberFormatException, Exception {
		logAction("ManageAnalyzerPageActions.createNewAnalyzer", data, dataRowID);
		AnalyzerDataReader analyzerDataReader = new AnalyzerDataReader(excelUtility);
		AnalyzerDataRow analyzerDataRow = analyzerDataReader.getDataRow(dataRowID);
		String serialNumber = analyzerDataRow.serialNumber;
		String sharedKey = analyzerDataRow.sharedKey;

		String customerName = null;
		if (ManageCustomerPageActions.workingDataRow != null) {
			customerName = ManageCustomerPageActions.workingDataRow.name;
		} else {	
			if (ManageSurveyorPageActions.workingDataRow != null) {
				SurveyorDataReader surveyorDataReader = new SurveyorDataReader(excelUtility);
				SurveyorDataRow surveyorDataRow = surveyorDataReader.getDataRow(Integer.valueOf(analyzerDataRow.surveyorRowID));
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
			SurveyorDataRow surveyorDataRow = surveyorDataReader.getDataRow(Integer.valueOf(analyzerDataRow.surveyorRowID));
			surveyor = surveyorDataRow.description;
		}

		String locationName = null;
		if (ManageLocationPageActions.workingDataRow != null) {
			locationName = ManageLocationPageActions.workingDataRow.name;
		} else {
			LocationDataReader locationDataReader = new LocationDataReader(excelUtility);
			LocationDataRow locationDataRow = locationDataReader.getDataRow(Integer.valueOf(analyzerDataRow.locationRowID));
			locationName = locationDataRow.name;
		}
		
		this.getManageAnalyzersPage().addNewAnalyzer(serialNumber, sharedKey, surveyor, customerName, locationName);
		
		workingDataRow = analyzerDataRow;
		
		return true;
	}
 
	private ManageAnalyzersPage getManageAnalyzersPage() {
		return (ManageAnalyzersPage)this.getPageObject();
	}

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("createNewAnalyzer")) { return this.createNewAnalyzer(data, dataRowID); }
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
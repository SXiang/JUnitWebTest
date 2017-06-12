package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import common.source.TestSetup;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.LocationDataReader;
import surveyor.scommon.actions.data.LocationDataReader.LocationDataRow;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.SurveyorConstants.MinAmplitudeType;
import surveyor.scommon.source.SurveyorConstants.SurveyModeType;

public class ManageLocationPageActions extends BasePageActions {

	public static ThreadLocal<LocationDataRow> workingDataRow = new ThreadLocal<LocationDataRow>();    // Stores the workingDataRow from createNewLocation action

	public ManageLocationPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		initializePageObject(driver, new ManageLocationsPage(driver, strBaseURL, testSetup));
		setDataReader(new LocationDataReader(this.excelUtility));
	}

	private void setDataReader(LocationDataReader customerDataReader) {
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
		if (ManageCustomerPageActions.workingDataRow.get() != null) {
			customer = ManageCustomerPageActions.workingDataRow.get().name;
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
		String surMinAmp = locationDataRow.surMinAmp;
		String rankingMinAmp = locationDataRow.rankingMinAmp;
		String psFilter = locationDataRow.psFilter;
		String top10PS = locationDataRow.top10PS;
		String top25PS = locationDataRow.top25PS;
		String top50PS = locationDataRow.top50PS;
		String dbScanRd = locationDataRow.dbScanRd;
		String minClusterSz = locationDataRow.minClusterSz;
		String maxClusterScale = locationDataRow.maxClusterScale;
		String expansionPower = locationDataRow.expansionPower;
		String inflationPower = locationDataRow.inflationPower;
		String percentile = locationDataRow.percentile;
		boolean checkForError = false;
		boolean fEQEnabled = true;
		String shapeCorrelationMin = locationDataRow.shapeCorrelationMin;
		String peakIDXBuffer = locationDataRow.peakIDXBuffer;
		String peakSEPDistanceScale = locationDataRow.peakSEPDistanceScale;
		String widthMin = locationDataRow.widthMin;
		String widthMax = locationDataRow.widthMax;
		String variationMax = locationDataRow.variationMax;
		String carSpeedMin = locationDataRow.carSpeedMin;
		String carSpeedMax = locationDataRow.carSpeedMax;
		String carWindAngleMin = locationDataRow.carWindAngleMin;
		String carWindAngleMax = locationDataRow.carWindAngleMax;
		String dBScanSpatialScale = locationDataRow.dBScanSpatialScale;
		String eQMinClusterSize = locationDataRow.eQMinClusterSize;
		String backgroundFilterThreshold = locationDataRow.backgroundFilterThreshold;
		String pPMTriggerThreshold = locationDataRow.pPMTriggerThreshold;
		String accelerationMax = locationDataRow.accelerationMax;
		boolean justDBScan = true;

		this.getManageLocationsPage().setLatitude(locationDataRow.latitude);
		this.getManageLocationsPage().setLongitude(locationDataRow.longitude);
		this.getManageLocationsPage().addNewLocation(locationDesc, customer, newLocationName, useLatLongSelector, ethMthMin, ethMthMax, surMinAmp, rankingMinAmp, psFilter, top10PS, top25PS, top50PS,
				dbScanRd, minClusterSz, maxClusterScale, expansionPower, inflationPower, percentile, fEQEnabled, shapeCorrelationMin, peakIDXBuffer, peakSEPDistanceScale, widthMin, widthMax, variationMax, 
				carSpeedMin, carSpeedMax, carWindAngleMin, carWindAngleMax, dBScanSpatialScale, eQMinClusterSize, backgroundFilterThreshold, pPMTriggerThreshold, accelerationMax, justDBScan, checkForError);

		workingDataRow.set(locationDataRow);

		return true;
	}

	/*
	 * Executes createNewLocation action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean findExistingLocation(String data, Integer dataRowID) throws Exception {
		logAction("ManageLocationPageActions.addNewLocation", data, dataRowID);
		LocationDataReader locationDataReader = new LocationDataReader(excelUtility);
		locationDataReader.getDataRow(dataRowID);

		this.getManageLocationsPage().findExistingLocation(workingDataRow.get().customerDataRowID, workingDataRow.get().name);

		return true;
	}

	/**
	 * Executes createNewLocation action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean editFEQLocationParameters(String data, Integer dataRowID) throws Exception {
		logAction("ManageLocationPageActions.addNewLocation", data, dataRowID);
		LocationDataReader locationDataReader = new LocationDataReader(excelUtility);
		LocationDataRow locationDataRow = locationDataReader.getDataRow(dataRowID);

		String shapeCorrelationMin = locationDataRow.shapeCorrelationMin;
		String peakIDXBuffer = locationDataRow.peakIDXBuffer;
		String peakSEPDistanceScale = locationDataRow.peakSEPDistanceScale;
		String widthMin = locationDataRow.widthMin;
		String widthMax = locationDataRow.widthMax;
		String variationMax = locationDataRow.variationMax;
		String carSpeedMin = locationDataRow.carSpeedMin;
		String carSpeedMax = locationDataRow.carSpeedMax;
		String carWindAngleMin = locationDataRow.carWindAngleMin;
		String carWindAngleMax = locationDataRow.carWindAngleMax;
		String dBScanSpatialScale = locationDataRow.dBScanSpatialScale;
		String eQMinClusterSize = locationDataRow.eQMinClusterSize;
		String backgroundFilterThreshold = locationDataRow.backgroundFilterThreshold;
		String pPMTriggerThreshold = locationDataRow.pPMTriggerThreshold;
		String accelerationMax = locationDataRow.accelerationMax;
		boolean eQJustDBScan = false;

		String customer = "";
		if (ManageCustomerPageActions.workingDataRow.get() != null) {
			customer = ManageCustomerPageActions.workingDataRow.get().name;
		} else {
			CustomerDataReader customerDataReader = new CustomerDataReader(excelUtility);
			CustomerDataRow customerDataRow = customerDataReader.getDataRow(Integer.valueOf(locationDataRow.customerDataRowID));
			customer = customerDataRow.name;
		}

		this.getManageLocationsPage().editFEQLocationParameters(customer, workingDataRow.get().name, shapeCorrelationMin, peakIDXBuffer, 
				peakSEPDistanceScale, widthMin, widthMax, variationMax, carSpeedMin, carSpeedMax, carWindAngleMin, carWindAngleMax, dBScanSpatialScale, eQMinClusterSize, 
				backgroundFilterThreshold, pPMTriggerThreshold, accelerationMax, eQJustDBScan);


		workingDataRow.set(locationDataRow);

		return true;
	}

	public ManageLocationsPage getManageLocationsPage() {
		return (ManageLocationsPage)this.getPageObject();
	}

	public Float getMinAmplitudeForLocation(Integer dataRowID, MinAmplitudeType minAmplitudeType) throws Exception {
		LocationDataReader locationDataReader = new LocationDataReader(excelUtility);
		return locationDataReader.getMinAmpForLocation(dataRowID, minAmplitudeType);
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

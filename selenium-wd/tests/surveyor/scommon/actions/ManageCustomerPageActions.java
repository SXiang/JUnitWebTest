package surveyor.scommon.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import common.source.EnumUtility;
import common.source.Log;
import common.source.LogHelper;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.CustomerLicenses;
import surveyor.dataaccess.source.CustomerLicenses.License;
import surveyor.dataaccess.source.CustomerWithGisDataPool;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.CustomerLicensedFeaturesDataReader;
import surveyor.scommon.actions.data.CustomerLicensedFeaturesDataReader.CustomerLicensedFeaturesDataRow;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

public class ManageCustomerPageActions extends BasePageActions {

	private CustomerDataReader dataReader = null;
	public static ThreadLocal<CustomerDataRow> workingDataRow = new ThreadLocal<CustomerDataRow>();    // Stores the workingDataRow from createNewCustomer action

	public ManageCustomerPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		initializePageObject(driver, new ManageCustomersPage(driver, strBaseURL, testSetup));
		setDataReader(new CustomerDataReader(this.excelUtility));
	}

	private void setDataReader(CustomerDataReader customerDataReader) {
		this.dataReader = customerDataReader;
	}

	// Note: Not thread-safe.
	public static void clearStoredObjects() {
		workingDataRow.set(null);
	}

	/**
	 * Executes createOrFetchNewGisCustomer action.
	 * Fetches new customer from CustomerWithGisPool if GeoServer is enabled.
	 * Creates a new customer from UI if GeoServer is NOT enabled.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean createOrFetchNewGisCustomer(String data, Integer dataRowID) throws Exception {
		logAction("ManageCustomersPageActions.createOrFetchNewGisCustomer", data, dataRowID);
		if (TestContext.INSTANCE.getTestSetup().isGeoServerEnabled()) {
			return fetchNewGisCustomer(data, dataRowID);
		}

		return createNewCustomer(data, dataRowID);
	}

	/**
	 * Executes fetchNewGisCustomer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean fetchNewGisCustomer(String data, Integer dataRowID) throws Exception {
		logAction("ManageCustomersPageActions.fetchNewGisCustomer", data, dataRowID);
		workingDataRow.set(this.dataReader.getDataRow(dataRowID));
		Customer gisCustomer = CustomerWithGisDataPool.acquireCustomerFailOnError();
		String customerName = gisCustomer.getName();
		workingDataRow.get().name = customerName;
		if (!workingDataRow.get().licensedFeaturesRowIDs.isEmpty()) {
			LicensedFeatures[] licensedFeatures = getLicensedFeatures(workingDataRow.get().licensedFeaturesRowIDs);
			workingDataRow.get().setLicensedFeatures(licensedFeatures);

			List<License> licensesForCustomerInDB = new CustomerLicenses().getLicenses(customerName);

			// un-select licenses that have not been specified.
			LicensedFeatures[] licensedFeaturesToUnselect = getNotSpecifiedLicensedFeatures(licensedFeatures, licensesForCustomerInDB);
			if (licensedFeaturesToUnselect != null && licensedFeaturesToUnselect.length > 0) {
				String unselectRowIds = getLicensedFeaturesRowIds(licensedFeaturesToUnselect);
				open(data, dataRowID);
				editCustomerUnSelectLicensedFeatures(unselectRowIds, dataRowID);
			}
		}

		return true;
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
		workingDataRow.set(this.dataReader.getDataRow(dataRowID));
		String customerName = ActionArguments.evaluateArgForFunction(workingDataRow.get().name);
		workingDataRow.get().name = customerName;
		if (workingDataRow.get().licensedFeaturesRowIDs.isEmpty()) {
			this.getManageCustomersPage().addNewCustomer(customerName, workingDataRow.get().eULA, Boolean.parseBoolean(workingDataRow.get().enabled));
		} else {
			LicensedFeatures[] licensedFeatures = getLicensedFeatures(workingDataRow.get().licensedFeaturesRowIDs);
			workingDataRow.get().setLicensedFeatures(licensedFeatures);
			this.getManageCustomersPage().addNewCustomer(customerName, workingDataRow.get().eULA, Boolean.parseBoolean(workingDataRow.get().enabled),
					licensedFeatures);
		}
		return true;
	}

	/**
	 * Executes editCustomerSelectLicensedFeatures action.
	 * @param data - comma seperated list of CustomerLicensedFeaturesRowID to be selected.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean editCustomerSelectLicensedFeatures(String data, Integer dataRowID) throws Exception {
		logAction("ManageCustomersPageActions.editCustomerSelectLicensedFeatures", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("editCustomerSelectLicensedFeatures", ARG_DATA, data);
		this.getManageCustomersPage().editAndSelectLicensedFeatures(workingDataRow.get().name, getLicensedFeatures(data));
		return true;
	}

	/**
	 * Executes editCustomerUnSelectLicensedFeatures action.
	 * @param data - comma seperated list of CustomerLicensedFeaturesRowID to be unselected.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean editCustomerUnSelectLicensedFeatures(String data, Integer dataRowID) throws Exception {
		logAction("ManageCustomersPageActions.editCustomerUnSelectLicensedFeatures", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("editCustomerUnSelectLicensedFeatures", ARG_DATA, data);
		this.getManageCustomersPage().editAndUnSelectLicensedFeatures(workingDataRow.get().name, getLicensedFeatures(data));
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
		this.getManageCustomersPage().open();
		this.getManageCustomersPage().waitForPageLoad();
		return true;
	}

	public ManageCustomersPage getManageCustomersPage() {
		return (ManageCustomersPage)this.getPageObject();
	}

	private String getLicensedFeaturesRowIds(LicensedFeatures[] licensedFeatures) throws Exception {
		Log.method("getLicensedFeaturesRowIds", LogHelper.arrayToString(licensedFeatures));
		List<String> rowIds = new ArrayList<>();
		CustomerLicensedFeaturesDataReader licensedFeaturesDataReader = new CustomerLicensedFeaturesDataReader(this.excelUtility);
		Integer rowCount = licensedFeaturesDataReader.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			CustomerLicensedFeaturesDataRow featuresDataRow = licensedFeaturesDataReader.getDataRow(i+1);
			if (!ActionArguments.isEmpty(featuresDataRow.rowID)) {
				if (isLicensedFeatureInArray(licensedFeatures, featuresDataRow.name)) {
					rowIds.add(featuresDataRow.rowID);
				}
			}
		}

		return StringUtils.join(rowIds, ",");
	}

	private LicensedFeatures[] getLicensedFeatures(String licFeaturesDataRowIDs) throws Exception {
		List<Integer> licenseRowIDs = ActionArguments.getNumericList(licFeaturesDataRowIDs);
		List<LicensedFeatures> licensedFeaturesList = new ArrayList<LicensedFeatures>();
		for (Integer licRowID : licenseRowIDs) {
			CustomerLicensedFeaturesDataReader licensedFeaturesDataReader = new CustomerLicensedFeaturesDataReader(this.excelUtility);
			CustomerLicensedFeaturesDataRow featuresDataRow = licensedFeaturesDataReader.getDataRow(licRowID);
			licensedFeaturesList.add(this.getManageCustomersPage().getLicensedFeature(featuresDataRow.name));
		}
		LicensedFeatures[] licensedFeatures = licensedFeaturesList.toArray(new LicensedFeatures[licensedFeaturesList.size()]);
		return licensedFeatures;
	}

	private LicensedFeatures[] getNotSpecifiedLicensedFeatures(LicensedFeatures[] licensedFeatures, List<License> licensesForCustomerInDB) {
		Log.method("getNotSpecifiedLicensedFeatures", licensedFeatures, licensesForCustomerInDB);
		List<LicensedFeatures> licenses = licensesForCustomerInDB.stream()
			.filter(lic -> !isLicensedFeatureInArray(licensedFeatures, lic.getDescription()))
			.map(lic -> EnumUtility.fromName(lic.getDescription(), () -> LicensedFeatures.values()))
			.collect(Collectors.toList());
		Log.info(String.format("Found %d not specified licenses. Not specified licenses are -> %s", licenses.size(), LogHelper.listToString(licenses)));
		return licenses.toArray(new LicensedFeatures[licenses.size()]);
	}

	private boolean isLicensedFeatureInArray(LicensedFeatures[] licensedFeatures, String licenseDesc) {
		Log.method("isLicensedFeatureInArray", licensedFeatures, licenseDesc);
		return Arrays.asList(licensedFeatures).stream()
			.anyMatch(lf -> lf.toString().equals(licenseDesc));
	}

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("createNewCustomer")) { return this.createNewCustomer(data, dataRowID); }
		else if (actionName.equals("createOrFetchNewGisCustomer")) { return this.createOrFetchNewGisCustomer(data, dataRowID); }
		else if (actionName.equals("editCustomerSelectLicensedFeatures")) { return this.editCustomerSelectLicensedFeatures(data, dataRowID); }
		else if (actionName.equals("editCustomerUnSelectLicensedFeatures")) { return this.editCustomerUnSelectLicensedFeatures(data, dataRowID); }
		else if (actionName.equals("fetchNewGisCustomer")) { return this.fetchNewGisCustomer(data, dataRowID); }
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

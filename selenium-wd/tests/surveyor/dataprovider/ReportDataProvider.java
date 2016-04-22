package surveyor.dataprovider;


import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import common.source.ExcelUtility;
import common.source.TestContext;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.ReportOptViewLayersAssetsDataReader;
import surveyor.scommon.actions.data.ReportOptViewLayersBoundaryDataReader;
import surveyor.scommon.actions.data.ReportOptViewLayersAssetsDataReader.ReportOptViewLayersAssetsDataRow;
import surveyor.scommon.actions.data.ReportOptViewLayersBoundaryDataReader.ReportOptViewLayersBoundaryDataRow;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorTestRunner;

public class ReportDataProvider extends SurveyorTestRunner {

	public ReportDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	
	public static HashMap<String, String> createViewsMapTable(String viewName, String lisa, String fov, String breadcrumb, String indications, String isotopic, String annotation, String gap, String asset, String boundary, String map) {
		HashMap<String, String> viewMap = new HashMap<String, String>();
		viewMap.put(KEYVIEWNAME, viewName);
		viewMap.put(KEYLISA, lisa);
		viewMap.put(KEYFOV, fov);
		viewMap.put(KEYBREADCRUMB, breadcrumb);
		viewMap.put(KEYINDICATIONS, indications);
		viewMap.put(KEYISOTOPICCAPTURE, isotopic);
		viewMap.put(KEYANNOTATION, annotation);
		viewMap.put(KEYGAPS, gap);
		viewMap.put(KEYASSETS, asset);
		viewMap.put(KEYBOUNDARIES, boundary);
		viewMap.put(KEYBASEMAP, map);
		return viewMap;
	}

	public static List<String> createBoundaryList() {
		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);
		return listBoundary;
	}

	public static HashMap<String, String> createOptionalTabularPDFContent(String indication, String isotopic, String pca, String pcra) {
		HashMap<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, indication);
		tableMap.put(KEYISOANA, isotopic);
		tableMap.put(KEYPCA, pca);
		tableMap.put(KEYPCRA, pcra);
		return tableMap;
	}

	public static HashMap<String, String> getAllViewLayerAssetsAndBoundariesForCustomer(String customerName) throws Exception {
		HashMap<String, String> viewLayerMap = new HashMap<String, String>();
		ExcelUtility excelUtility = getExcelUtility();
		int customerRowID = getCustomerRowID(customerName, excelUtility);
		if (customerRowID != -1) {
			// If found a matching customer, get all assets and boundaries for this customer.
			addAllViewLayersAssetsForCustomer(viewLayerMap, excelUtility, customerRowID);	
			addAllViewLayerBoundariesForCustomer(viewLayerMap, excelUtility, customerRowID);	
		}

		return viewLayerMap;
	}

	public static HashMap<String, String> getAllViewLayerAssetsForCustomer(String customerName) throws Exception {
		HashMap<String, String> viewLayerMap = new HashMap<String, String>();
		ExcelUtility excelUtility = getExcelUtility();
		int customerRowID = getCustomerRowID(customerName, excelUtility);
		if (customerRowID != -1) {
			// If found a matching customer, get all assets for this customer.
			addAllViewLayersAssetsForCustomer(viewLayerMap, excelUtility, customerRowID);	
		}

		return viewLayerMap;
	}

	public static HashMap<String, String> getAllViewLayerBoundariesForCustomer(String customerName) throws Exception {
		HashMap<String, String> viewLayerMap = new HashMap<String, String>();
		ExcelUtility excelUtility = getExcelUtility();
		int customerRowID = getCustomerRowID(customerName, excelUtility);
		if (customerRowID != -1) {
			// If found a matching customer, get all boundaries for this customer.
			addAllViewLayerBoundariesForCustomer(viewLayerMap, excelUtility, customerRowID);	
		}

		return viewLayerMap;
	}

	public static HashMap<String, String> createOptionalViewLayersContent(List<Integer> assetRowIDs, 
			List<Integer> boundaryRowIDs) throws Exception {
		HashMap<String, String> viewLayerMap = new HashMap<String, String>();
		ExcelUtility excelUtility = getExcelUtility();
		addAssetsToMap(assetRowIDs, viewLayerMap, excelUtility);
		addBoundariesToMap(boundaryRowIDs, viewLayerMap, excelUtility);
		return viewLayerMap;
	}

	public static HashMap<String, String> createViewLayerAssetsContent(List<Integer> assetRowIDs) throws Exception {
		HashMap<String, String> viewLayerMap = new HashMap<String, String>();
		ExcelUtility excelUtility = getExcelUtility();
		addAssetsToMap(assetRowIDs, viewLayerMap, excelUtility);
		return viewLayerMap;
	}

	public static HashMap<String, String> createViewLayerBoundariesContent(List<Integer> boundaryRowIDs) throws Exception {
		HashMap<String, String> viewLayerMap = new HashMap<String, String>();
		ExcelUtility excelUtility = getExcelUtility();
		addBoundariesToMap(boundaryRowIDs, viewLayerMap, excelUtility);
		return viewLayerMap;
	}

	private static ExcelUtility getExcelUtility() throws Exception, IOException {
		ExcelUtility excelUtility = new ExcelUtility();
		excelUtility.setExcelFile(TestContext.INSTANCE.getTestSetup().getTestCaseDataPath());
		return excelUtility;
	}

	private static int getCustomerRowID(String customerName, ExcelUtility excelUtility) throws Exception {
		int customerRowCount = excelUtility.getRowCount(CustomerDataReader.TESTDATA_SHEET_NAME);
		int customerRowID = -1;
		CustomerDataReader customerDataReader = new CustomerDataReader(excelUtility);
		for (int i = 0; i < customerRowCount; i++) {
			CustomerDataRow customerDataRow = customerDataReader.getDataRow(i);
			if (customerDataRow.name.equals(customerName)) {
				customerRowID = Integer.valueOf(customerDataRow.rowID);
				break;
			}
		}
		return customerRowID;
	}
	
	private static void addAssetsToMap(List<Integer> assetRowIDs, HashMap<String, String> viewLayerMap,
			ExcelUtility excelUtility) throws Exception {
		if (assetRowIDs != null && assetRowIDs.size()>0) {
			for (Integer rowID : assetRowIDs) {
				ReportOptViewLayersAssetsDataReader viewLayersAssetsDataReader = new ReportOptViewLayersAssetsDataReader(excelUtility);
				ReportOptViewLayersAssetsDataRow dataRow = viewLayersAssetsDataReader.getDataRow(rowID);
				viewLayerMap.put(dataRow.assetID, ReportsCompliance.ASSET_PREFIX + dataRow.assetName);
			}
		}
	}

	private static void addBoundariesToMap(List<Integer> boundaryRowIDs, HashMap<String, String> viewLayerMap,
			ExcelUtility excelUtility) throws Exception {
		if (boundaryRowIDs != null && boundaryRowIDs.size()>0) {
			for (Integer rowID : boundaryRowIDs) {
				ReportOptViewLayersBoundaryDataReader viewLayersBoundaryDataReader = new ReportOptViewLayersBoundaryDataReader(excelUtility);
				ReportOptViewLayersBoundaryDataRow dataRow = viewLayersBoundaryDataReader.getDataRow(rowID);
				viewLayerMap.put(dataRow.boundaryID, ReportsCompliance.BOUNDARY_PREFIX + dataRow.boundaryName);
			}
		}
	}

	private static void addAllViewLayersAssetsForCustomer(HashMap<String, String> viewLayerMap, ExcelUtility excelUtility, int customerRowID)
			throws Exception {
		int assetsRowCount = excelUtility.getRowCount(ReportOptViewLayersAssetsDataReader.TESTDATA_SHEET_NAME);
		ReportOptViewLayersAssetsDataReader assetsDataReader = new ReportOptViewLayersAssetsDataReader(excelUtility);
		for (int i = 0; i < assetsRowCount; i++) {
			ReportOptViewLayersAssetsDataRow assetsDataRow = assetsDataReader.getDataRow(i);
			if (Integer.valueOf(assetsDataRow.customerRowID) == customerRowID) {
				viewLayerMap.put(assetsDataRow.assetID, ReportsCompliance.ASSET_PREFIX + assetsDataRow.assetName);
			}
		}
	}

	private static void addAllViewLayerBoundariesForCustomer(HashMap<String, String> viewLayerMap, ExcelUtility excelUtility, int customerRowID)
			throws Exception {
		int boundaryRowCount = excelUtility.getRowCount(ReportOptViewLayersBoundaryDataReader.TESTDATA_SHEET_NAME);
		ReportOptViewLayersBoundaryDataReader boundaryDataReader = new ReportOptViewLayersBoundaryDataReader(excelUtility);
		for (int i = 0; i < boundaryRowCount; i++) {
			ReportOptViewLayersBoundaryDataRow boundaryDataRow = boundaryDataReader.getDataRow(i);
			if (Integer.valueOf(boundaryDataRow.customerRowID) == customerRowID) {
				viewLayerMap.put(boundaryDataRow.boundaryID, ReportsCompliance.BOUNDARY_PREFIX + boundaryDataRow.boundaryName);
			}
		}
	}
}
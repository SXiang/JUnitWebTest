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
import static surveyor.scommon.source.SurveyorConstants.KEYGAPTB;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTGAPASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTLISAASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTBOXASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYPCF;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import common.source.ArrayUtility;
import common.source.ExcelUtility;
import common.source.RegexUtility;
import common.source.TestContext;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.ReportOptViewLayersAssetsDataReader;
import surveyor.scommon.actions.data.ReportOptViewLayersBoundaryDataReader;
import surveyor.scommon.actions.data.ReportOptViewLayersAssetsDataReader.ReportOptViewLayersAssetsDataRow;
import surveyor.scommon.actions.data.ReportOptViewLayersBoundaryDataReader.ReportOptViewLayersBoundaryDataRow;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.ReportsSurveyInfo;
import surveyor.scommon.source.SurveyorTestRunner;

public class ReportDataProvider extends SurveyorTestRunner {

	public ReportDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	public static Map<String, String> createViewsMapTable(String viewName, String lisa, String fov, String breadcrumb, String indications, String isotopic, String annotation, String gap, String asset,
			String boundary, String lisaAsset, String boxAsset, String lisaBoundary, String map) {
		Map<String, String> viewMap = Collections.synchronizedMap(new HashMap<String, String>());
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
		viewMap.put(KEYHIGHLIGHTLISAASSETS, lisaAsset);
		viewMap.put(KEYHIGHLIGHTBOXASSETS, boxAsset);
		viewMap.put(KEYHIGHLIGHTGAPASSETS, lisaBoundary);
		viewMap.put(KEYBASEMAP, map);
		return viewMap;
	}

	public static List<String> createMapAndBoundaryList() {
		return createMapAndBoundaryList(true /*includeCustomBoundary*/);
	}

	public static List<String> createMapAndBoundaryList(boolean includeCustomBoundary) {
		List<String> listBoundary = Collections.synchronizedList(new ArrayList<String>());
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		if (includeCustomBoundary) {
			addCustomBoundary(listBoundary);
		}
		return listBoundary;
	}

	public static List<String> createMapAndBoundaryListForLoadTests() {
		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add("37.42982");
		listBoundary.add("-122.06283");
		listBoundary.add("37.33256");
		listBoundary.add("-121.85100");
		return listBoundary;
	}

	public static Map<String, String> createOptionalTabularPDFContent(String indication, String isotopic, String gaptable, String pca, String pcra, String pcf) {
		HashMap<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, indication);
		tableMap.put(KEYISOANA, isotopic);
		tableMap.put(KEYGAPTB, gaptable);
		tableMap.put(KEYPCA, pca);
		tableMap.put(KEYPCRA, pcra);
		tableMap.put(KEYPCF, pcf);
		return tableMap;
	}

	public static Map<String, String> getAllViewLayerAssetsAndBoundariesForCustomer(String customerName) throws Exception {
		Map<String, String> viewLayerMap = Collections.synchronizedMap(new HashMap<String, String>());
		ExcelUtility excelUtility = getExcelUtility();
		int customerRowID = getCustomerRowID(customerName, excelUtility);
		if (customerRowID != -1) {
			// If found a matching customer, get all assets and boundaries for this customer.
			addAllViewLayersAssetsForCustomer(viewLayerMap, excelUtility, customerRowID);
			addAllViewLayerBoundariesForCustomer(viewLayerMap, excelUtility, customerRowID);
		}

		return viewLayerMap;
	}

	public static Map<String, String> getAllViewLayerAssetsForCustomer(String customerName) throws Exception {
		Map<String, String> viewLayerMap = Collections.synchronizedMap(new HashMap<String, String>());
		ExcelUtility excelUtility = getExcelUtility();
		int customerRowID = getCustomerRowID(customerName, excelUtility);
		if (customerRowID != -1) {
			// If found a matching customer, get all assets for this customer.
			addAllViewLayersAssetsForCustomer(viewLayerMap, excelUtility, customerRowID);
		}

		return viewLayerMap;
	}

	public static Map<String, String> getAllViewLayerBoundariesForCustomer(String customerName) throws Exception {
		Map<String, String> viewLayerMap = Collections.synchronizedMap(new HashMap<String, String>());
		ExcelUtility excelUtility = getExcelUtility();
		int customerRowID = getCustomerRowID(customerName, excelUtility);
		if (customerRowID != -1) {
			// If found a matching customer, get all boundaries for this customer.
			addAllViewLayerBoundariesForCustomer(viewLayerMap, excelUtility, customerRowID);
		}

		return viewLayerMap;
	}

	public static Map<String, String> createOptionalViewLayersContent(List<Integer> assetRowIDs,
			List<Integer> boundaryRowIDs) throws Exception {
		Map<String, String> viewLayerMap = Collections.synchronizedMap(new HashMap<String, String>());
		ExcelUtility excelUtility = getExcelUtility();
		addAssetsToMap(assetRowIDs, viewLayerMap, excelUtility);
		addBoundariesToMap(boundaryRowIDs, viewLayerMap, excelUtility);
		return viewLayerMap;
	}

	public static Map<String, String> createViewLayerAssetsContent(List<Integer> assetRowIDs) throws Exception {
		Map<String, String> viewLayerMap = Collections.synchronizedMap(new HashMap<String, String>());
		ExcelUtility excelUtility = getExcelUtility();
		addAssetsToMap(assetRowIDs, viewLayerMap, excelUtility);
		return viewLayerMap;
	}

	public static Map<String, String> createViewLayerBoundariesContent(List<Integer> boundaryRowIDs) throws Exception {
		Map<String, String> viewLayerMap = Collections.synchronizedMap(new HashMap<String, String>());
		ExcelUtility excelUtility = getExcelUtility();
		addBoundariesToMap(boundaryRowIDs, viewLayerMap, excelUtility);
		return viewLayerMap;
	}

	public static List<ReportsSurveyInfo> buildReportSurveyInfoList(String surveyDataRowIDs) throws Exception {
		ExcelUtility excelUtility = getExcelUtility();
		List<String> strRowIDsList = RegexUtility.split(surveyDataRowIDs, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		List<Integer> intRowIDsList = ArrayUtility.convertStrListToIntList(strRowIDsList);
		return ComplianceReportsPageActions.getReportSurveyInfoList(excelUtility, intRowIDsList);
	}

	private static void addCustomBoundary(List<String> listBoundary) {
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);
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

	private static void addAssetsToMap(List<Integer> assetRowIDs, Map<String, String> viewLayerMap,
			ExcelUtility excelUtility) throws Exception {
		if (assetRowIDs != null && assetRowIDs.size()>0) {
			for (Integer rowID : assetRowIDs) {
				ReportOptViewLayersAssetsDataReader viewLayersAssetsDataReader = new ReportOptViewLayersAssetsDataReader(excelUtility);
				ReportOptViewLayersAssetsDataRow dataRow = viewLayersAssetsDataReader.getDataRow(rowID);
				viewLayerMap.put(dataRow.assetID, ReportsCompliance.ASSET_PREFIX + dataRow.assetName);
			}
		}
	}

	private static void addBoundariesToMap(List<Integer> boundaryRowIDs, Map<String, String> viewLayerMap,
			ExcelUtility excelUtility) throws Exception {
		if (boundaryRowIDs != null && boundaryRowIDs.size()>0) {
			for (Integer rowID : boundaryRowIDs) {
				ReportOptViewLayersBoundaryDataReader viewLayersBoundaryDataReader = new ReportOptViewLayersBoundaryDataReader(excelUtility);
				ReportOptViewLayersBoundaryDataRow dataRow = viewLayersBoundaryDataReader.getDataRow(rowID);
				viewLayerMap.put(dataRow.boundaryID, ReportsCompliance.BOUNDARY_PREFIX + dataRow.boundaryName);
			}
		}
	}

	private static void addAllViewLayersAssetsForCustomer(Map<String, String> viewLayerMap, ExcelUtility excelUtility, int customerRowID)
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

	private static void addAllViewLayerBoundariesForCustomer(Map<String, String> viewLayerMap, ExcelUtility excelUtility, int customerRowID)
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
package surveyor.scommon.actions;

import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTLISAASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTGAPASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETBOXNUMBER;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPTB;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCF;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static common.source.RegexUtility.REGEX_PATTEN_SPECIAL_CHARACTERS;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.ArrayUtility;
import common.source.BaseHelper;
import common.source.ExcelUtility;
import common.source.FileUtility;
import common.source.FunctionUtil;
import common.source.Log;
import common.source.LogHelper;
import common.source.NumberUtility;
import common.source.PDFTableUtility.PDFTable;
import common.source.PDFUtility;
import common.source.RegexUtility;
import common.source.SortHelper;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.CustomerBoundaryType;
import surveyor.dataaccess.source.CustomerMaterialType;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.User;
import surveyor.dataprovider.ReportDataProvider;
import surveyor.parsers.source.SSRSIsotopicAnalysisTableParser;
import surveyor.scommon.actions.data.AnalyzerDataReader;
import surveyor.scommon.actions.data.AnalyzerDataReader.AnalyzerDataRow;
import surveyor.scommon.actions.data.ReportsCommonDataReader;
import surveyor.scommon.actions.data.ReportsCommonDataReader.ReportsCommonDataRow;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.LocationDataReader;
import surveyor.scommon.actions.data.LocationDataReader.LocationDataRow;
import surveyor.scommon.actions.data.ReportOptTabularPDFContentDataReader;
import surveyor.scommon.actions.data.ReportOptTabularPDFContentDataReader.ReportOptTabularPDFContentDataRow;
import surveyor.scommon.actions.data.ReportOptViewLayersAssetsDataReader;
import surveyor.scommon.actions.data.ReportOptViewLayersAssetsDataReader.ReportOptViewLayersAssetsDataRow;
import surveyor.scommon.actions.data.ReportOptViewLayersBoundaryDataReader;
import surveyor.scommon.actions.data.ReportOptViewLayersBoundaryDataReader.ReportOptViewLayersBoundaryDataRow;
import surveyor.scommon.actions.data.ReportOptViewLayersDataReader;
import surveyor.scommon.actions.data.ReportOptViewLayersDataReader.ReportOptViewLayersDataRow;
import surveyor.scommon.actions.data.ReportSurveyDataReader;
import surveyor.scommon.actions.data.ReportSurveyDataReader.ReportSurveyDataRow;
import surveyor.scommon.actions.data.ReportViewsDataReader;
import surveyor.scommon.actions.data.ReportViewsDataReader.ReportViewsDataRow;
import surveyor.scommon.actions.data.ReportsBaseDataReader.ReportsBaseDataRow;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.entities.ReportsSurveyInfo;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.entities.ReportCommonEntity.IsotopicAnalysisTableColumns;
import surveyor.scommon.entities.ReportCommonEntity.LISAIndicationTableColumns;
import surveyor.scommon.source.ReportsCommonPage;
import surveyor.scommon.source.ReportsCommonPage.ReportsButtonType;
import surveyor.scommon.source.ReportsCommonPage.ReportFileType;
import surveyor.scommon.source.ReportsCommonPage.ReportViewerThumbnailType;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import surveyor.scommon.source.SurveyorBasePage.TableSortOrder;
import surveyor.scommon.source.SurveyorConstants;

public class ReportCommonPageActions extends BaseReportsPageActions {
	private static final String FN_WAIT_FOR_VIEW_DOWNLOAD_TO_COMPLETE_BY_VIEW_INDEX = "waitForViewDownloadToCompleteByViewIndex";
	private static final String FN_CLICK_ON_COMPLIANCE_VIEWER_VIEW_BY_INDEX = "clickOnComplianceViewerViewByIndex";

	public static ThreadLocal<List<ReportViewsDataRow>> workingReportViewsDataRows = new ThreadLocal<List<ReportViewsDataRow>>();    // Stores the dataRows for views created in createNewReport action.

	public ReportCommonPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
	}

	protected List<Map<String, String>> createView(Integer dataRowID) throws Exception {
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();
		workingReportViewsDataRows.set(new ArrayList<ReportViewsDataRow>());
		fillViewDetails(viewMap, new ReportViewsDataReader(this.excelUtility), dataRowID);
		viewList.add(viewMap);
		return viewList;
	}

	private boolean areTabularPDFContentSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return isPDFGapSelectionMatch(dataRow) && isPDFIndicationSelectionMatch(dataRow) &&
				isPDFIsotopicAnalysisSelectionMatch(dataRow) && isPDFPercentCoverageAssetsSelectionMatch(dataRow) &&
				isPDFPercentCoverageForecastSelectionMatch(dataRow) && isPDFPercentCoverageReportAreaSelectionMatch(dataRow);
	}

	/**
	 * Verified if asset and boundary checkboxes corresponding to the value in DataRow are selected in UI.
	 * Method supports known customer assets.
	 * @param dataRow
	 * @return
	 * @throws Exception
	 */
	private boolean areAssetBoundariesMatch(ReportOptViewLayersDataRow dataRow) throws Exception {
		Log.method("areAssetBoundariesMatch", dataRow);
		return areAssetsMatch(dataRow) && areBoundariesMatch(dataRow);
	}

	private boolean areAssetsMatch(ReportOptViewLayersDataRow dataRow) throws Exception {
		Log.method("areAssetsMatch", dataRow);
		boolean areAssetsMatch = true;
		if (!ActionArguments.isEmpty(dataRow.assetRowIDs)) {
			Map<String, String> assetsForCustomer = null;
			List<Integer> assetRowIDs = ActionArguments.getNumericList(dataRow.assetRowIDs);
			for (Integer assetRowID : assetRowIDs) {
				ReportOptViewLayersAssetsDataReader viewLayersAssetsDataReader = new ReportOptViewLayersAssetsDataReader(excelUtility);
				ReportOptViewLayersAssetsDataRow viewLayersAssetsDataRow = viewLayersAssetsDataReader.getDataRow(assetRowID);
				WebElement assetElement = null;
				if (!ActionArguments.isEmpty(viewLayersAssetsDataRow.assetID)) {
					assetElement = this.getReportsCommonPage().getViewLayerAssetCheckbox(viewLayersAssetsDataRow.assetID);
				} else {
					if (assetsForCustomer == null) {
						assetsForCustomer = ReportDataProvider.getAllViewLayerAssetsForCustomer(Integer.valueOf(viewLayersAssetsDataRow.customerRowID));
					}

					Optional<String> customerAssetID = assetsForCustomer.entrySet().stream()
						.filter(e -> e.getValue().replace(ReportCommonEntity.ASSET_PREFIX, "").equalsIgnoreCase(viewLayersAssetsDataRow.assetName))
						.map(e -> e.getKey())
						.findFirst();

					if (customerAssetID.isPresent()) {
						assetElement = this.getReportsCommonPage().getViewLayerAssetCheckbox(customerAssetID.get());
					}
				}

				areAssetsMatch = areAssetsMatch && (assetElement!=null) && assetElement.isSelected();

				if (!areAssetsMatch) {
					Log.error(String.format("Asset checkbox value MISMATCH for Asset=[%s]", viewLayersAssetsDataRow.assetName));
				}
			}
		}

		return areAssetsMatch;
	}

	private boolean areBoundariesMatch(ReportOptViewLayersDataRow dataRow) throws Exception {
		Log.method("areBoundariesMatch", dataRow);
		boolean areBoundariesMatch = true;
		if (!ActionArguments.isEmpty(dataRow.boundariesRowIDs)) {
			Map<String, String> boundariesForCustomer = null;
			List<Integer> boundaryRowIDs = ActionArguments.getNumericList(dataRow.boundariesRowIDs);
			for (Integer boundaryRowID : boundaryRowIDs) {
				ReportOptViewLayersBoundaryDataReader viewLayersBoundaryDataReader = new ReportOptViewLayersBoundaryDataReader(excelUtility);
				ReportOptViewLayersBoundaryDataRow viewLayersBoundaryDataRow = viewLayersBoundaryDataReader.getDataRow(boundaryRowID);
				WebElement boundaryElement = null;
				if (!ActionArguments.isEmpty(viewLayersBoundaryDataRow.boundaryID)) {
					boundaryElement = this.getReportsCommonPage().getViewLayerBoundaryCheckbox(viewLayersBoundaryDataRow.boundaryName);
				} else {
					if (boundariesForCustomer == null) {
						boundariesForCustomer = ReportDataProvider.getAllViewLayerBoundariesForCustomer(Integer.valueOf(viewLayersBoundaryDataRow.customerRowID));
					}

					Optional<String> customerBoundaryName = boundariesForCustomer.entrySet().stream()
						.filter(e -> e.getValue().replace(ReportCommonEntity.BOUNDARY_PREFIX, "").equalsIgnoreCase(viewLayersBoundaryDataRow.boundaryName))
						.map(e -> e.getValue().replace(ReportCommonEntity.BOUNDARY_PREFIX, ""))
						.findFirst();

					if (customerBoundaryName.isPresent()) {
						boundaryElement = this.getReportsCommonPage().getViewLayerBoundaryCheckbox(customerBoundaryName.get());
					}
				}

				areBoundariesMatch = areBoundariesMatch && (boundaryElement!=null) && boundaryElement.isSelected();

				if (!areBoundariesMatch) {
					Log.error(String.format("Boundary checkbox value MISMATCH for Boundary=[%s]", viewLayersBoundaryDataRow.boundaryName));
				}
			}
		}

		return areBoundariesMatch;
	}



	private boolean clickComplianceViewerViewByIndex(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(FN_CLICK_ON_COMPLIANCE_VIEWER_VIEW_BY_INDEX, ARG_DATA, data);
		Integer viewIdx = NumberUtility.getIntegerValueOf(data);
		ActionArguments.verifyGreaterThanZero(FN_CLICK_ON_COMPLIANCE_VIEWER_VIEW_BY_INDEX, ARG_DATA, viewIdx);
		this.getReportsCommonPage().clickViewThumbnailImageByIndex(viewIdx);
		return true;
	}

	protected List<ReportsSurveyInfo> buildReportSurveyInfoList(ReportsBaseDataRow dataRow, ExcelUtility excelUtility) throws Exception {
		List<Integer> reportSurveyRowIDs = ActionArguments.getNumericList(dataRow.reportSurveyRowIDs);
		List<ReportsSurveyInfo> reportsSurveyInfoList = getReportSurveyInfoList(excelUtility, reportSurveyRowIDs);
		return reportsSurveyInfoList;
	}

	public static List<ReportsSurveyInfo> getReportSurveyInfoList(ExcelUtility excelUtility, List<Integer> reportSurveyRowIDs) throws Exception {
		List<ReportsSurveyInfo> reportsSurveyInfoList = new ArrayList<ReportsSurveyInfo>();
		for (Integer rowID : reportSurveyRowIDs) {
			if(rowID==0){
				continue;
			}
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(rowID);
			SurveyModeFilter modeFilter = toSurveyModeFilter(surveyDataRow.surveyModeFilter);
			reportsSurveyInfoList.add(new ReportsSurveyInfo(
					surveyDataRow.surveySurveyor, surveyDataRow.surveyUsername, surveyDataRow.surveyTag,
					surveyDataRow.surveyStartDate, surveyDataRow.surveyEndDate,
					modeFilter, Boolean.valueOf(surveyDataRow.surveyGeoFilterON),
					NumberUtility.getIntegerValueOf(surveyDataRow.numberofSurveystoInclude),
					Boolean.valueOf(surveyDataRow.selectAllSurveys)));
		}
		return reportsSurveyInfoList;
	}

	private static SurveyModeFilter toSurveyModeFilter(String surveyModeFilter) {
		SurveyModeFilter modeFilter = SurveyModeFilter.All;
		if (surveyModeFilter.equalsIgnoreCase("standard")) {
			modeFilter = SurveyModeFilter.Standard;
		} else if (surveyModeFilter.equalsIgnoreCase("assessment")) {
			modeFilter = SurveyModeFilter.Assessment;
		} else if (surveyModeFilter.equalsIgnoreCase("eq")) {
			modeFilter = SurveyModeFilter.EQ;
		} else if (surveyModeFilter.equalsIgnoreCase("manual")) {
			modeFilter = SurveyModeFilter.Manual;
		} else if (surveyModeFilter.equalsIgnoreCase("operator")) {
			modeFilter = SurveyModeFilter.Operator;
		} else if (surveyModeFilter.equalsIgnoreCase("rapid response")) {
			modeFilter = SurveyModeFilter.RapidResponse;
		}
		return modeFilter;
	}

	protected void fillViewDetails(Map<String, String> viewMap, ReportViewsDataReader reader,
			Integer dataRowID) throws Exception {
		ReportViewsDataRow reportViewsDataRow = reader.getDataRow(dataRowID);
		workingReportViewsDataRows.get().add(reportViewsDataRow);
		String viewName = reportViewsDataRow.name;
		String showLISA = reportViewsDataRow.lISAs.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showFOV = reportViewsDataRow.fOV.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showBreadcrumb = reportViewsDataRow.breadcrumbs.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showIndications = reportViewsDataRow.indications.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showIsotopicCapture = reportViewsDataRow.isotopicCapture.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showAnnotation = reportViewsDataRow.fieldNotes.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showGaps = reportViewsDataRow.gaps.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showAssets = reportViewsDataRow.assets.equalsIgnoreCase("TRUE") ? "1" : "0";
		String highlightLisaAssets = reportViewsDataRow.highlightLisa.equalsIgnoreCase("TRUE") ? "1" : "0";
		String highlightGapAssets = reportViewsDataRow.highlightGap.equalsIgnoreCase("TRUE") ? "1" : "0";
		String assetBoxNumber = reportViewsDataRow.assetBoxNumber.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showBoundaries = reportViewsDataRow.boundaries.equalsIgnoreCase("TRUE") ? "1" : "0";
		String baseMapType = reportViewsDataRow.baseMap;
		viewMap.put(KEYVIEWNAME, viewName);
		if (showLISA != "") viewMap.put(KEYLISA, showLISA);
		if (showFOV != "") viewMap.put(KEYFOV, showFOV);
		if (showBreadcrumb != "") viewMap.put(KEYBREADCRUMB, showBreadcrumb);
		if (showIndications != "") viewMap.put(KEYINDICATIONS, showIndications);
		if (showIsotopicCapture != "") viewMap.put(KEYISOTOPICCAPTURE, showIsotopicCapture);
		if (showAnnotation != "") viewMap.put(KEYANNOTATION, showAnnotation);
		if (showGaps != "") viewMap.put(KEYGAPS, showGaps);
		if (showAssets != "") viewMap.put(KEYASSETS, showAssets);
		if (highlightLisaAssets != "") viewMap.put(KEYHIGHLIGHTLISAASSETS, highlightLisaAssets);
		if (highlightGapAssets != "") viewMap.put(KEYHIGHLIGHTGAPASSETS, highlightGapAssets);
		if (assetBoxNumber != "") viewMap.put(KEYASSETBOXNUMBER, assetBoxNumber);
		if (showBoundaries != "") viewMap.put(KEYBOUNDARIES, showBoundaries);
		viewMap.put(KEYBASEMAP, baseMapType);
	}

	protected void fillReportTableInfo(Map<String, String> tableMap, ReportOptTabularPDFContentDataReader reader,
			Integer dataRowID) throws Exception {
		String showIndicationsTable = reader.getDataRow(dataRowID).indicationTable.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showIsoAnalysisTable = reader.getDataRow(dataRowID).isotopicAnalysis.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showGapTable = reader.getDataRow(dataRowID).gapTable.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showPercentCovAssetsTable = reader.getDataRow(dataRowID).percentCoverageAssets.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showPercentCoverageReportAreaTable = reader.getDataRow(dataRowID).percentCoverageReportArea.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showPercentCoverageForecastTable = reader.getDataRow(dataRowID).percentCoverageForecast.equalsIgnoreCase("TRUE") ? "1" : "0";
		tableMap.put(KEYINDTB, showIndicationsTable);
		tableMap.put(KEYISOANA, showIsoAnalysisTable);
		tableMap.put(KEYGAPTB, showGapTable);
		tableMap.put(KEYPCA, showPercentCovAssetsTable);
		tableMap.put(KEYPCRA, showPercentCoverageReportAreaTable);
		tableMap.put(KEYPCF, showPercentCoverageForecastTable);
	}

	protected void fillViewLayersInfo(Map<String, String> viewLayerMap,
			ReportOptViewLayersDataReader reader, Customer customer, Integer dataRowID) throws Exception {
		if (dataRowID > 0) {
			String argValue = reader.getDataRow(dataRowID).assetRowIDs;
			String customerId = null;
			if (customer != null) {
				customerId = customer.getId();
			}
			if (!ActionArguments.isEmpty(argValue)) {
				List<Integer> assetRowIDs = ActionArguments.getNumericList(argValue);
				for (Integer rowID : assetRowIDs) {
					if(rowID==0){
						continue;
					}
					ReportOptViewLayersAssetsDataReader viewLayersAssetsDataReader = getViewLayersAssetsDataReader();
					ReportOptViewLayersAssetsDataRow dataRow = viewLayersAssetsDataReader.getDataRow(rowID);
					String assetID = dataRow.assetID;
					if (BaseHelper.isNullOrEmpty(dataRow.assetID)) {
						// AssetID not static. Determine assetId from DB.
						assetID = CustomerMaterialType.getCustomerMaterialTypeByName(dataRow.assetName, customerId).getId().toLowerCase();
					}
					viewLayerMap.put(assetID, ReportCommonEntity.ASSET_PREFIX + dataRow.assetName);
				}
			}
			argValue = reader.getDataRow(dataRowID).boundariesRowIDs;
			if (!ActionArguments.isEmpty(argValue)) {
				List<Integer> boundariesRowIDs = ActionArguments.getNumericList(argValue);
				for (Integer rowID : boundariesRowIDs) {
					if(rowID==0){
						continue;
					}
					ReportOptViewLayersBoundaryDataReader viewLayersBoundaryDataReader = getViewLayersBoundaryDataReader();
					ReportOptViewLayersBoundaryDataRow dataRow = viewLayersBoundaryDataReader.getDataRow(rowID);
					String boundaryID = dataRow.boundaryID;
					if (BaseHelper.isNullOrEmpty(dataRow.boundaryID)) {
						// BoundaryID not static. Determine boundaryId from DB.
						boundaryID = CustomerBoundaryType.getCustomerBoundaryTypeByName(dataRow.boundaryName, customerId).getId().toLowerCase();
					}
					viewLayerMap.put(boundaryID, ReportCommonEntity.BOUNDARY_PREFIX + dataRow.boundaryName);
				}
			}
		} else {
			// For new customers with newly added GIS assets, we do NOT know AssetIDs beforehand.
			// Currently if '-1' is specified for 'Report Opt View Layer RowID' we select all Assets ,
			//              '-2' = all Boundaries, '-4' = all assets & boundaries.
			// US3653 -> tracks improving this implementation and make it more flexible to select specific Assets/Boundaries
			//          if test cases require this implementation in future.
			if (dataRowID == -1) {
				viewLayerMap.put(ReportsCommonPage.REPORT_ASSET_SELECTALL_CHKBX_ID, ReportCommonEntity.ASSET_ALL_PREFIX);
			} else if (dataRowID == -2) {
				viewLayerMap.put(ReportsCommonPage.REPORT_BOUNDRY_SELECTALL_CHKBX_ID, ReportCommonEntity.BOUNDARY_ALL_PREFIX);
			} else if (dataRowID == -4) {
				viewLayerMap.put(ReportsCommonPage.REPORT_ASSET_SELECTALL_CHKBX_ID, ReportCommonEntity.ASSET_ALL_PREFIX);
				viewLayerMap.put(ReportsCommonPage.REPORT_BOUNDRY_SELECTALL_CHKBX_ID, ReportCommonEntity.BOUNDARY_ALL_PREFIX);
			}
		}
	}

	private boolean fillAndCreateNewReport(Integer dataRowID, boolean openNewReportsPage) throws Exception {
		ReportCommonEntity rpt = fillWorkingDataForReports(dataRowID);
		getReportsCommonPage().addNewReport(rpt, openNewReportsPage);
		return true;
	}

	public ReportCommonEntity fillWorkingDataForReports(Integer dataRowID) throws Exception {
		setWorkingReportsDataRow(getDataReader().getDataRow(dataRowID));
		ReportCommonEntity rpt = createNewReportsEntity();
		String rptTitle = getWorkingReportsDataRow().title;
		String customer = null;
		String customerRowID = getWorkingReportsDataRow().customerRowID;
		if (!BaseHelper.isNullOrEmpty(customerRowID)) {
			if (ManageCustomerPageActions.workingDataRow.get() != null) {
				customer = ManageCustomerPageActions.workingDataRow.get().name;
			} else {
				Integer custRowID = NumberUtility.getIntegerValueOf(customerRowID);
				customer = (new CustomerDataReader(this.excelUtility)).getDataRow(custRowID).name;
			}
		}
		String timeZone = getWorkingReportsDataRow().timezone;	
		List<ReportsSurveyInfo> reportsSurveyInfoList = buildReportSurveyInfoList(getWorkingReportsDataRow(), this.excelUtility);

		// Set report common properties.
		rpt.setRptTitle(rptTitle);
		rpt.setCustomer(customer);
		rpt.setTimeZone(timeZone);
		rpt.setSurveyInfoList(reportsSurveyInfoList);

        fillReportSpecificWorkingDataForReports(rpt);

        setWorkingReportsEntity(rpt);		// Store the working report properties.
        return rpt;
	}

	/* Working data for some reports - compliance and assessment */
	protected void addAdditionalWorkingDataForReports(ReportCommonEntity rpt) throws Exception {
		String exclusionRadius = getWorkingReportsCommonDataRow().exclusionRadius;

		List<String> listBoundary = new ArrayList<String>();
		fillCustomBoundary(listBoundary, getWorkingReportsCommonDataRow());

		// Fill views list.
		workingReportViewsDataRows.set(new ArrayList<ReportViewsDataRow>());
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		List<Integer> reportViewRowIDs = ActionArguments.getNumericList(getWorkingReportsCommonDataRow().reportViewRowIDs);
		for (Integer rowID : reportViewRowIDs) {
			if(rowID==0){
				continue;
			}
			Map<String, String> viewMap = new HashMap<String, String>();
			fillViewDetails(viewMap, new ReportViewsDataReader(this.excelUtility), rowID);
			viewList.add(viewMap);
		}

		// Fill optional tabular list.
		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		List<Integer> reportOptTabPDFRowIDs = ActionArguments.getNumericList(getWorkingReportsCommonDataRow().reportOptTabularPDFContentRowID);
		Map<String, String> tableMap = new HashMap<String, String>();
		fillReportTableInfo(tableMap, new ReportOptTabularPDFContentDataReader(this.excelUtility), reportOptTabPDFRowIDs.get(0));
		tablesList.add(tableMap);

		// Fill optional view layer list.
		List<Map<String, String>> viewLayersList = new ArrayList<Map<String, String>>();
		List<Integer> reportOptVwLayersRowIDs = ActionArguments.getNumericList(getWorkingReportsCommonDataRow().reportOptViewLayerRowID);
		Map<String, String> viewLayerMap = new HashMap<String, String>();
		fillViewLayersInfo(viewLayerMap, new ReportOptViewLayersDataReader(this.excelUtility),
				Customer.getCustomer(rpt.getCustomer()), reportOptVwLayersRowIDs.get(0));
		if (viewLayerMap.size() > 0) {
			viewLayersList.add(viewLayerMap);
		}
        rpt.setCustomerBoundaryInfo(getWorkingReportsCommonDataRow().customerBoundaryType, getWorkingReportsCommonDataRow().customerBoundaryName);
        rpt.setExclusionRadius(exclusionRadius);
		rpt.setListBoundary(listBoundary);
		rpt.setTablesList(tablesList);
		rpt.setViewList(viewList);
		rpt.setViewLayersList(viewLayersList);
	}
	
	private void fillCustomBoundary(List<String> listBoundary, ReportsCommonDataRow dataRow) throws Exception {
		String imgHeight = dataRow.pDFImageOutputHeight;
		String imgWidth = dataRow.pDFImageOutputWidth;
		String NELat = dataRow.customBoundaryNELat;
		String NELong = dataRow.customBoundaryNELong;
		String SWLat = dataRow.customBoundarySWLat;
		String SWLong = dataRow.customBoundarySWLong;
		listBoundary.add(imgHeight);
		listBoundary.add(imgWidth);
		listBoundary.add(NELat);
		listBoundary.add(NELong);
		listBoundary.add(SWLat);
		listBoundary.add(SWLong);
	}

	private ReportOptViewLayersAssetsDataReader getViewLayersAssetsDataReader() {
		return new ReportOptViewLayersAssetsDataReader(this.excelUtility);
	}

	private ReportOptViewLayersBoundaryDataReader getViewLayersBoundaryDataReader() {
		return new ReportOptViewLayersBoundaryDataReader(this.excelUtility);
	}

	private CustomerDataRow getCustomerDataRow() throws Exception {
		Integer custRowID = Integer.valueOf(getWorkingReportsDataRow().customerRowID);
		CustomerDataReader customerDataReader = new CustomerDataReader(excelUtility);
		CustomerDataRow customerDataRow = customerDataReader.getDataRow(custRowID);
		return customerDataRow;
	}

	private ReportOptTabularPDFContentDataRow getOptionalTabularPdfDataRow() throws Exception {
		Integer optionaltabPdfRowID = Integer.valueOf(getWorkingReportsCommonDataRow().reportOptTabularPDFContentRowID);
		ReportOptTabularPDFContentDataReader optTabularPDFContentDataReader = new ReportOptTabularPDFContentDataReader(excelUtility);
		ReportOptTabularPDFContentDataRow optTabularPDFContentDataRow = optTabularPDFContentDataReader.getDataRow(optionaltabPdfRowID);
		return optTabularPDFContentDataRow;
	}

	private List<String> getViewNamesList(Integer dataRowID) throws Exception {
		List<Integer> viewRowIDs = ActionArguments.getNumericList(getWorkingReportsCommonDataRow().reportViewRowIDs);
		List<String> viewNamesList = new ArrayList<String>();
		ReportViewsDataReader reportViewsDataReader = new ReportViewsDataReader(this.excelUtility);
		for (Integer viewRowID : viewRowIDs) {
			ReportViewsDataRow viewsDataRow = reportViewsDataReader.getDataRow(viewRowID);
			viewNamesList.add(viewsDataRow.name);
		}
		return viewNamesList;
	}

	protected String getDownloadPath(ReportFileType fileType) throws Exception {	
		return this.getReportsCommonPage().getDownloadPath(fileType, (getWorkingReportsDataRow().title));
	}

	private void clickComplianceReportButton(Integer dataRowID, ReportsButtonType buttonType) throws Exception {
		ReportsBaseDataRow compRptDataRow = getReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		this.getReportsCommonPage().clickComplianceReportButton(reportTitle, LoginPageActions.workingDataRow.get().username, buttonType,
				false /*confirmAction*/);  // By default use FALSE confirm action.
	}

	private boolean isPDFGapSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return dataRow.gapTable.equalsIgnoreCase("FALSE") || (dataRow.gapTable.equalsIgnoreCase("TRUE") && this.getReportsCommonPage().isPDFGapSelected());
	}

	private boolean isPDFIndicationSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return dataRow.indicationTable.equalsIgnoreCase("FALSE") || (dataRow.indicationTable.equalsIgnoreCase("TRUE") && this.getReportsCommonPage().isPDFIndicationSelected());
	}

	private boolean isPDFIsotopicAnalysisSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return dataRow.isotopicAnalysis.equalsIgnoreCase("FALSE") || (dataRow.isotopicAnalysis.equalsIgnoreCase("TRUE") && this.getReportsCommonPage().isPDFIsotopicAnalysisSelected());
	}

	private boolean isPDFPercentCoverageAssetsSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return dataRow.percentCoverageAssets.equalsIgnoreCase("FALSE") || (dataRow.percentCoverageAssets.equalsIgnoreCase("TRUE") && this.getReportsCommonPage().isPDFPercentCoverageAssetsSelected());
	}

	private boolean isPDFPercentCoverageForecastSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return dataRow.percentCoverageForecast.equalsIgnoreCase("FALSE") || (dataRow.percentCoverageForecast.equalsIgnoreCase("TRUE") && this.getReportsCommonPage().isPDFPercentCoverageForecastSelected());
	}

	private boolean isPDFPercentCoverageReportAreaSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return dataRow.percentCoverageReportArea.equalsIgnoreCase("FALSE") || (dataRow.percentCoverageReportArea.equalsIgnoreCase("TRUE") && this.getReportsCommonPage().isPDFPercentCoverageReportAreaSelected());
	}

	private void openComplianceViewerDialog(Integer dataRowID) throws Exception {
		clickComplianceReportButton(dataRowID, ReportsButtonType.ReportViewer);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		this.getReportsCommonPage().waitForPdfReportIcontoAppear();
	}

	private List<Integer> verifyLatLongCoordinates(String data) throws Exception {
		List<Integer> coordList = ActionArguments.getNumericList(data);
		if (coordList == null || coordList.size() != 4) {
			throw new Exception("Coordinates of top-left and bottom-right pixels not specified correctly. "
					+ "Specify coordinates in CSV format - For eg. x1,y1,x2,y2");
		}
		return coordList;
	}

	private List<String> verifyCustomerBoundaryInfo(String data) throws Exception {
		List<String> customerInfo = RegexUtility.split(data, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		if (customerInfo == null || customerInfo.size() != 2) {
			throw new Exception("Customer boundary info not specified correctly. "
					+ "Specify customer boundary info in format - <boundary_type>,<boundary_name>");
		}
		return customerInfo;
	}

	private void selectCoordinatesInLatLongBoundarySelector(List<Integer> coordList) {
		LatLongSelectionControl latLongSelectionControl = new LatLongSelectionControl(this.getDriver());
		latLongSelectionControl.waitForModalDialogOpen()
			.switchMode(ControlMode.MapInteraction)
			.waitForMapImageLoad()
			.drawSelectorRectangle(ReportsCommonPage.BOUNDARY_SELECTOR_CANVAS_X_PATH,
					coordList.get(0), coordList.get(1), coordList.get(2), coordList.get(3))
			.switchMode(ControlMode.Default)
			.clickOkButton()
			.waitForModalDialogToClose();
	}

	private boolean verifyPresenceOfButton(Integer dataRowID, ReportsButtonType buttonType) throws Exception {
		ReportsBaseDataRow compRptDataRow = getReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		return this.getReportsCommonPage().verifyComplianceReportButton(reportTitle, LoginPageActions.workingDataRow.get().username, buttonType);
	}

	private boolean verifyReportSurveyValuesMatch(List<Integer> surveyRowIDs) throws Exception {
		boolean retVal = true;
		int len = surveyRowIDs.size();
		for (int i = 0; i < len; i++) {
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(surveyRowIDs.get(i));
			SurveyModeFilter mode = toSurveyModeFilter(surveyDataRow.surveyModeFilter);
			String tag = surveyDataRow.surveyTag;

			// Divs appears on page in reverse order in UI. ie highest number appears first, lowest last.
			WebElement surveyDiv = getDriver().findElement(By.id(String.format("surveyContent-%d", len-i-1)));
			WebElement surveyMode = surveyDiv.findElement(By.id(String.format("report-run-%d-surveymode", len-i-1)));
			WebElement surveyTag = surveyDiv.findElement(By.id(String.format("report-run-%d-surveytag", len-i-1)));

			retVal = retVal && surveyMode.getText().equals(mode.toString()) && surveyTag.getText().startsWith(tag);

			if (!retVal) {
				Log.error(String.format("Incorrect match found for survey tag-'%s' -> "
						+ "Survey mode=[Expected-'%s';Actual-'%s'], Survey tag=[Expected-'%s';Actual-'%s']",
						surveyTag.getText(), mode, surveyMode.getText(), tag, surveyTag.getText()));
				break;
			}
		}

		return retVal;
	}

	/**
	 * Downloads the specified report file type and waits for download to complete.
	 * @param dataRowID - RowID for test case data.
	 * @param fileType - Type of file to download
	 * @param fileIndex - Index for file to be specified for View images. Ignored for other report file types.
	 * 			1-based index. For first view use - 1.
	 * @throws Exception
	 */
	protected void waitForReportFileDownload(Integer dataRowID, ReportFileType fileType, Integer fileIndex) throws Exception {
		waitForReportFileDownload(dataRowID, fileType, fileIndex, -1);
	}

	protected void waitForReportFileDownload(Integer dataRowID, ReportFileType fileType, Integer fileIndex, int zipIndex) throws Exception {
		ReportsBaseDataRow compRptDataRow = getReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		String reportName = "";

		switch(fileType) {
		case PDF:
			reportName = this.getReportsCommonPage().getReportPDFFileName(reportTitle, false /*includeExtension*/);
			this.getReportsCommonPage().waitForPDFFileDownload(reportName);
			break;
		case ZIP:
			// get the report name without extension.
			zipIndex = zipIndex==-1?0:zipIndex;
			reportName = this.getReportsCommonPage().getReportPDFFileName(reportTitle, false /*includeExtension*/);
			this.getReportsCommonPage().waitForReportZIPFileDownload(reportName,zipIndex);
			break;
		case MetaDataZIP:
			// get the report name without extension.
			zipIndex = zipIndex==-1?0:zipIndex;
			reportName = this.getReportsCommonPage().getReportPDFFileName(reportTitle, false /*includeExtension*/);
			this.getReportsCommonPage().waitForMetadataZIPFileDownload(reportName,zipIndex);
			break;
		case ShapeZIP:
			// get the report name without extension.
			zipIndex = zipIndex==-1?0:zipIndex;
			reportName = this.getReportsCommonPage().getReportPDFFileName(reportTitle, false /*includeExtension*/);
			this.getReportsCommonPage().waitForShapeZIPFileDownload(reportName,zipIndex);
			break;
		case View:
			reportName = this.getReportsCommonPage().getReportPDFFileName(getWorkingReportsDataRow().title, false /*includeExtension*/);
			List<Map<String, String>> viewList = getWorkingReportsEntity().getViewList();

			Map<String, String> map = viewList.get(fileIndex-1);
			String viewName = map.get(KEYVIEWNAME);
			this.getReportsCommonPage().waitForViewFileDownload(reportName, viewName);
			break;
		default:
			waitForReportSpecificFileDownload(dataRowID, fileType, fileIndex, zipIndex);
			break;
		}
	}

	private void waitForViewDownloadByViewIndex(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(FN_WAIT_FOR_VIEW_DOWNLOAD_TO_COMPLETE_BY_VIEW_INDEX, ARG_DATA, data);
		Integer viewIdx = NumberUtility.getIntegerValueOf(data);
		ActionArguments.verifyGreaterThanZero(FN_WAIT_FOR_VIEW_DOWNLOAD_TO_COMPLETE_BY_VIEW_INDEX, ARG_DATA, viewIdx);
		waitForReportFileDownload(dataRowID, ReportFileType.View, viewIdx);
	}

	/**
	 * Returns a COLON seperated list of assets selected in the report.
	 * @param dataRowID - RowID from compliance report test data.
	 * @return
	 * @throws Exception
	 */
	public String getSelectedAssetNames(Integer dataRowID) throws Exception {
		StringBuilder builder = new StringBuilder();
		ReportsCommonDataReader reportDataReader = (ReportsCommonDataReader) getDataReader();
		ReportsCommonDataRow reportsDataRow = reportDataReader.getDataRow(dataRowID);
		List<Integer> viewLayerRowIDs = ActionArguments.getNumericList(reportsDataRow.reportOptViewLayerRowID);
		String seperator = ":";
		for (Integer viewLayerRowID : viewLayerRowIDs) {
			ReportOptViewLayersDataReader viewLayersDataReader = new ReportOptViewLayersDataReader(excelUtility);
			ReportOptViewLayersDataRow viewLayersDataRow = viewLayersDataReader.getDataRow(viewLayerRowID);
			List<Integer> assetRowIDs = ActionArguments.getNumericList(viewLayersDataRow.assetRowIDs);
			for (Integer assetRowID : assetRowIDs) {
				ReportOptViewLayersAssetsDataReader viewLayersAssetsDataReader = new ReportOptViewLayersAssetsDataReader(excelUtility);
				ReportOptViewLayersAssetsDataRow viewLayersAssetsDataRow = viewLayersAssetsDataReader.getDataRow(assetRowID);
				builder.append(viewLayersAssetsDataRow.assetName);
				builder.append(seperator);
			}
		}

		String assetNames = builder.toString();
		if (assetNames.endsWith(seperator)) {
			assetNames = assetNames.substring(0, assetNames.length()-1);
		}

		return assetNames;
	}

	/**
	 * Returns a COLON seperated list of boundaries selected in the report.
	 * @param dataRowID - RowID from compliance report test data.
	 * @return
	 * @throws Exception
	 */
	public String getSelectedBoundaryNames(Integer dataRowID) throws Exception {
		StringBuilder builder = new StringBuilder();
		ReportsCommonDataReader reportDataReader = (ReportsCommonDataReader) getDataReader();
		ReportsCommonDataRow reportsDataRow = reportDataReader.getDataRow(dataRowID);
		List<Integer> viewLayerRowIDs = ActionArguments.getNumericList(reportsDataRow.reportOptViewLayerRowID);
		String seperator = ":";
		for (Integer viewLayerRowID : viewLayerRowIDs) {
			ReportOptViewLayersDataReader viewLayersDataReader = new ReportOptViewLayersDataReader(excelUtility);
			ReportOptViewLayersDataRow viewLayersDataRow = viewLayersDataReader.getDataRow(viewLayerRowID);
			List<Integer> boundaryRowIDs = ActionArguments.getNumericList(viewLayersDataRow.boundariesRowIDs);
			for (Integer boundaryRowID : boundaryRowIDs) {
				ReportOptViewLayersBoundaryDataReader viewLayersBoundaryDataReader = new ReportOptViewLayersBoundaryDataReader(excelUtility);
				ReportOptViewLayersBoundaryDataRow viewLayersBoundaryDataRow = viewLayersBoundaryDataReader.getDataRow(boundaryRowID);
				builder.append(viewLayersBoundaryDataRow.boundaryName);
				builder.append(seperator);
			}
		}

		String boundaryNames = builder.toString();
		if (boundaryNames.endsWith(seperator)) {
			boundaryNames = boundaryNames.substring(0, boundaryNames.length()-1);
		}

		return boundaryNames;
	}

	/* START - Actions on the Page*/

	/**
	 * Executes addDefaultView action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean addDefaultView(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.addDefaultView", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("addNewView", ARG_DATA_ROW_ID, dataRowID);
		addView(dataRowID);
		return true;
	}

	/**
	 * Executes addNewView action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean addNewView(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.addNewView", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("addNewView", ARG_DATA_ROW_ID, dataRowID);
		addView(dataRowID);
		return true;
	}

	/**
	 * Executes addSurveysToReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean addSurveysToReport(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.addSurveysToReport", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("addSurveysToReport", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportEntity reportsCompliance = new ComplianceReportEntity();
		ReportsBaseDataRow dataRow = getReportsDataRow(dataRowID);
		List<ReportsSurveyInfo> reportsSurveyInfoList = buildReportSurveyInfoList(dataRow, this.excelUtility);
		reportsCompliance.setSurveyInfoList(reportsSurveyInfoList);
		this.getReportsCommonPage().addSurveyInformation(reportsCompliance);
		return true;
	}

	/**
	 * Executes cancelInProgressReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean cancelInProgressReport(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.cancelInProgressReport", data, dataRowID);
		this.getReportsCommonPage().clickOnButtonInReportPage(getWorkingReportsDataRow().title,
				LoginPageActions.workingDataRow.get().username, ReportsButtonType.Cancel);
		return true;
	}
	/**
	 * Executes copyInProgressReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean copyInProgressReport(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.cancelInProgressReport", data, dataRowID);
		this.getReportsCommonPage().clickOnButtonInReportPage(getWorkingReportsDataRow().title,
				LoginPageActions.workingDataRow.get().username, ReportsButtonType.InProgressCopy);
		this.getReportsCommonPage().waitForCopyReportPagetoLoad();
		this.initializePageObject(TestContext.INSTANCE.getDriver(), this.createNewPageObject());
		return true;
	}
	/**
	 * Executes checkSurveySelectorGeographicFilter action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean checkSurveySelectorGeographicFilter(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.checkSurveySelectorGeographicFilter", data, dataRowID);
		this.getReportsCommonPage().selectSurveyInfoGeoFilter(true);
		return true;
	}

	/**
	 * Executes clickOnCancelButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnCancelButton(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.clickOnCancelButton", data, dataRowID);
		this.getReportsCommonPage().clickOnCancelBtn();
		return true;
	}

	/**
	 * Executes clickOnCancelConfirmDeleteReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnCancelConfirmDeleteReport(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.clickOnCancelConfirmDeleteReport", data, dataRowID);
		this.getReportsCommonPage().clickOnCancelInDeleteReportPopup();
		return true;
	}

	/**
	 * Executes clickOnComplianceViewerButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnComplianceViewerButton(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnComplianceViewerButton", data, dataRowID);
		clickComplianceReportButton(dataRowID, ReportsButtonType.ReportViewer);
		return true;
	}

	/**
	 * Executes clickOnComplianceViewerCloseButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnComplianceViewerCloseButton(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnComplianceViewerCloseButton", data, dataRowID);
		this.getReportsCommonPage().clickOnReportViewerCloseButton();
		return true;
	}

	/**
	 * Executes clickOnConfirmDeleteReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnConfirmDeleteReport(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.clickOnConfirmDeleteReport", data, dataRowID);
		this.getReportsCommonPage().clickOnConfirmInDeleteReportPopup();
		this.getReportsCommonPage().waitForPageLoad();
		this.getReportsCommonPage().waitForAJAXCallsToComplete();
		return true;
	}

	/**
	 * Executes clickOnCopyButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnCopyButton(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnCopyButton", data, dataRowID);
		clickComplianceReportButton(dataRowID, ReportsButtonType.Copy);
		return true;
	}

	/**
	 * Executes clickOnDeleteButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnDeleteButton(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnDeleteButton", data, dataRowID);
		clickComplianceReportButton(dataRowID, ReportsButtonType.Delete);
		return true;
	}

	/**
	 * Executes clickOnFirstCopyComplianceButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnFirstCopyComplianceButton(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnFirstCopyComplianceButton", data, dataRowID);
		this.getReportsCommonPage().clickOnFirstCopyComplianceBtn();
		this.getReportsCommonPage().waitForCopyReportPagetoLoad();
		this.initializePageObject(TestContext.INSTANCE.getDriver(), this.createNewPageObject());
		return true;
	}

	/**
	 * Executes clickOnLatLongSelectorButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnLatLongSelectorButton(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.clickOnLatLongSelectorButton", data, dataRowID);
		this.getReportsCommonPage().clickLatLongMapSelectorBtn();
		return true;
	}

	/**
	 * Executes clickOnOKButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnOKButton(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.clickOnOKButton", data, dataRowID);
		this.getReportsCommonPage().clickOnOKButton();
		return true;
	}

	/**
	 * Executes clickOnNewReportButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnNewReportButton(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.clickOnNewReportButton", data, dataRowID);
		this.getReportsCommonPage().clickOnNewReportBtn();
		return false;
	}

	/**
	 * Executes clickOnResubmitButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnResubmitButton(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnResubmitButton", data, dataRowID);
		clickComplianceReportButton(dataRowID, ReportsButtonType.Resubmit);
		return true;
	}

 	/**
	 * Executes clickOnSurveySelectorSearchButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnSurveySelectorSearchButton(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.clickOnSurveySelectorSearchButton", data, dataRowID);
		this.getReportsCommonPage().clickOnSearchSurveyButton();
		return true;
	}

	/**
	 * Executes copyReport action.
	 * @param data - specifies the title of the report to be copied.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean copyReport(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.copyReport", data, dataRowID);
		this.getReportsCommonPage().copyReport(data, LoginPageActions.workingDataRow.get().username);
		this.getReportsCommonPage().waitForCopyReportPagetoLoad();
		this.initializePageObject(TestContext.INSTANCE.getDriver(), this.createNewPageObject());
		return true;
	}

	/**
	 * Executes openComplianceViewerDialog action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean openComplianceViewerDialog(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.openComplianceViewerDialog", data, dataRowID);
		openComplianceViewerDialog(dataRowID);
		return true;
	}

	/**
	 * Executes modifyReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean modifyReport(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.modifyReport", data, dataRowID);
		return this.fillAndCreateNewReport(dataRowID, false /*openNewReportsPage*/);
	}

	/**
	 * Executes createNewReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean createNewReport(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.createNewReport", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("createNewReport", ARG_DATA_ROW_ID, dataRowID);

		return fillAndCreateNewReport(dataRowID, true /*openNewReportsPage*/);
	}

	/**
	 * Executes deleteReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean deleteReport(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.deleteReport", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("deleteReport", ARG_DATA_ROW_ID, dataRowID);
		ReportsBaseDataRow compRptDataRow = getReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		String createdBy = LoginPageActions.workingDataRow.get().username;
		this.getReportsCommonPage().deleteReport(reportTitle, createdBy);
		return true;
	}

	/**
	 * Executes enterCustomBoundaryUsingAreaSelector action.
	 * @param data - coordinates of top-left and bottom-right pixels in comma seperated list.
	 * 	For eg. x1,y1,x2,y2
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean enterCustomBoundaryUsingAreaSelector(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.enterCustomBoundaryUsingAreaSelector", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("enterCustomBoundaryUsingAreaSelector", ARG_DATA, data);
		List<Integer> coordList = verifyLatLongCoordinates(data);
		this.getReportsCommonPage().openCustomBoundarySelector();
		selectCoordinatesInLatLongBoundarySelector(coordList);
		return true;
	}

	/**
	 * Executes enterCustomBoundaryUsingTextFields action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean enterCustomBoundaryUsingTextFields(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.enterCustomBoundaryUsingTextFields", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("enterCustomBoundaryUsingTextFields", ARG_DATA, data);
		List<String> custBoundaryList = RegexUtility.split(data, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		String neLat = custBoundaryList.get(0);
		String neLong = custBoundaryList.get(1);
		String swLat = custBoundaryList.get(2);
		String swLong = custBoundaryList.get(3);
		this.getReportsCommonPage().fillCustomBoundaryTextFields(neLat, neLong, swLat, swLong);
		return true;
	}

	/**
	 * Executes enterCustomerBoundaryUsingAreaSelector action.
	 * @param data - Customer boundary info in format <boundaryType>,<boundaryName>
	 * 		For eg. SmallBoundary,Level-01
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean enterCustomerBoundaryUsingAreaSelector(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.enterCustomerBoundaryUsingAreaSelector", data, dataRowID);
		boolean retVal = false;
		if (dataRowID != -1) {
			ReportsCommonDataRow reportsDataRow = getReportsCommonDataRow(dataRowID);
			retVal = this.getReportsCommonPage().fillCustomerBoundary(reportsDataRow.customerBoundaryType,
					reportsDataRow.customerBoundaryName);
		} else {
			ActionArguments.verifyNotNullOrEmpty("enterCustomBoundaryUsingAreaSelector", ARG_DATA, data);
			List<String> boundaryInfo = verifyCustomerBoundaryInfo(data);
			retVal = this.getReportsCommonPage().fillCustomerBoundary(boundaryInfo.get(0), boundaryInfo.get(1));
		}
		return retVal;
	}

	/**
	 * Executes getCustomerBoundNamesUsingAreaSelector action.
	 * @param data - Customer boundary info in format <boundaryType>,<boundaryName>
	 * 		For eg. SmallBoundary,Level-01
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean fillAndVerifyCustomerBoundaryNamesListInAreaSelectorIsCorrect(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.fillAndVerifyCustomerBoundaryNamesListInAreaSelectorIsCorrect", data, dataRowID);
		List<String> boundaryNameList = new ArrayList<String>();
		String customerBoundaryName = "";
		if (dataRowID != -1) {
			ReportsCommonDataRow reportsDataRow = getReportsCommonDataRow(dataRowID);
			customerBoundaryName = reportsDataRow.customerBoundaryName;
			this.getReportsCommonPage().fillCustomerBoundary(reportsDataRow.customerBoundaryType,
					customerBoundaryName, boundaryNameList);
		} else {
			ActionArguments.verifyNotNullOrEmpty("enterCustomBoundaryUsingAreaSelector", ARG_DATA, data);
			List<String> boundaryInfo = verifyCustomerBoundaryInfo(data);
			customerBoundaryName = boundaryInfo.get(0);
			this.getReportsCommonPage().fillCustomerBoundary(customerBoundaryName, boundaryInfo.get(1), boundaryNameList);
		}

		return FunctionUtil.allListEntriesMatch(boundaryNameList, FunctionUtil.stringStartsWithPredicate(customerBoundaryName));
	}

	/**
	 * Executes enterExclusionRadius action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean enterExclusionRadius(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.enterExclusionRadius", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("enterExclusionRadius", ARG_DATA_ROW_ID, dataRowID);
		ReportsCommonDataRow dataRow = getReportsCommonDataRow(dataRowID);
		this.getReportsCommonPage().inputExclusionRadius(dataRow.exclusionRadius);
		return true;
	}

	/**
	 * Executes enterFOVOpacity action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean enterFOVOpacity(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.enterFOVOpacity", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("enterFOVOpacity", ARG_DATA_ROW_ID, dataRowID);
		ReportsCommonDataRow dataRow = getReportsCommonDataRow(dataRowID);
		this.getReportsCommonPage().inputFOVOpacity(dataRow.opacityFOV);
		return true;
	}

	/**
	 * Executes enterPDFImageHeight action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean enterPDFImageHeight(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.enterPDFImageHeight", data, dataRowID);
		if (!ActionArguments.isEmpty(data)) {
			this.getReportsCommonPage().inputImageMapHeight(data);
		} else {
			ReportsCommonDataRow dataRow = getReportsCommonDataRow(dataRowID);
			this.getReportsCommonPage().inputImageMapHeight(dataRow.pDFImageOutputHeight);
		}
		return true;
	}

	/**
	 * Executes enterPDFImageWidth action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean enterPDFImageWidth(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.enterPDFImageWidth", data, dataRowID);
		if (!ActionArguments.isEmpty(data)) {
			this.getReportsCommonPage().inputImageMapWidth(data);
		} else {
			ReportsCommonDataRow dataRow = getReportsCommonDataRow(dataRowID);
			this.getReportsCommonPage().inputImageMapWidth(dataRow.pDFImageOutputWidth);
		}
		return true;
	}

	/**
	 * Executes enterReportTitle action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean enterReportTitle(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.enterReportTitle", data, dataRowID);
		if (!ActionArguments.isEmpty(data)) {
			this.getReportsCommonPage().inputReportTitle(data);
		} else {
			ReportsBaseDataRow dataRow = getReportsDataRow(dataRowID);
			this.getReportsCommonPage().inputReportTitle(dataRow.title);
		}
		return true;
	}

	/**
	 * Executes enterSurveySelectorTag action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean enterSurveySelectorTag(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.enterSurveySelectorTag", data, dataRowID);
		if (!ActionArguments.isEmpty(data)) {
			this.getReportsCommonPage().inputSurveyTag(data);
		} else {
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(this.excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(dataRowID);
			this.getReportsCommonPage().inputSurveyTag(surveyDataRow.surveyTag);
		}
		return true;
	}

	/**
	 * Executes enterSurveySelectorUsername action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean enterSurveySelectorUsername(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.enterSurveySelectorUsername", data, dataRowID);
		if (!ActionArguments.isEmpty(data)) {
			this.getReportsCommonPage().inputSurveyUsername(data);
		} else {
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(this.excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(dataRowID);
			this.getReportsCommonPage().inputSurveyUsername(surveyDataRow.surveyUsername);
		}
		return true;
	}

	/**
	 * Executes findReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean findReport(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.findReport", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("findReport", ARG_DATA_ROW_ID, dataRowID);
		ReportsBaseDataRow compRptDataRow = getReportsDataRow(dataRowID);
		return findReportInternal(compRptDataRow.title, 1 /*colIdx = 1 for reportTitle*/);
	}

	public boolean findReportInternal(String searchKeyword, Integer columnIndex) throws Exception {
		String createdBy = LoginPageActions.workingDataRow.get().username;
		return this.getReportsCommonPage().findReportbySearch(searchKeyword, createdBy, columnIndex);
	}

	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.open", data, dataRowID);
		getReportsCommonPage().open();
		getReportsCommonPage().waitForPageLoad();
		return true;
	}

	/**
	 * Executes openNewReportPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean openNewReportPage(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.openNewReportPage", data, dataRowID);
		getReportsCommonPage().openNewReportsPage();
		getReportsCommonPage().waitForNewPageLoad();
		return true;
	}

	/**
	 * Executes searchAndDeleteReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean searchAndDeleteReport(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.searchAndDeleteReport", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("searchAndDeleteReport", ARG_DATA_ROW_ID, dataRowID);
		ReportsBaseDataRow compRptDataRow = getReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		String createdBy = LoginPageActions.workingDataRow.get().username;
		this.getReportsCommonPage().searchAndDeleteReport(reportTitle, createdBy);
		return true;
	}

	/**
	 * Executes searchForSurveyByKeyword action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean searchForSurveyByKeyword(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.searchForSurveyByKeyword", data, dataRowID);
		getReportsCommonPage().performSearch(data);
		return true;
	}

	/**
	 * Executes selectIndicationTableCheckbox action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectIndicationTableCheckbox(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.selectIndicationTableCheckbox", data, dataRowID);
		this.getReportsCommonPage().selectIndicationsTableCheckBox();
		return true;
	}

	/**
	 * Executes selectIsotopicTableCheckbox action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectIsotopicTableCheckbox(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.selectIsotopicTableCheckbox", data, dataRowID);
		this.getReportsCommonPage().selectIsotopicAnalysisCheckBox();
		return true;
	}

	/**
	 * Executes selectPaginationRows action.
	 * @param data - specifies the number of rows to be set in pagination.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean selectPaginationRows(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.selectPaginationRows", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("selectPaginationRows", ARG_DATA, data);
		ActionArguments.verifyGreaterThanZero("selectPaginationRows", ARG_DATA, NumberUtility.getIntegerValueOf(data));
		this.getReportsCommonPage().setPagination(data);
		return true;
	}

	/**
	 * Executes selectPercentCoverageReportAreaCheckBox action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectPercentCoverageReportAreaCheckBox(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.selectPercentCoverageReportAreaCheckBox", data, dataRowID);
		this.getReportsCommonPage().selectPercentCoverageReportArea();
		return true;
	}

	/**
	 * Executes selectShowPercentCoverageOfAssetsCheckBox action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectShowPercentCoverageOfAssetsCheckBox(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.selectShowPercentCoverageOfAssetsCheckBox", data, dataRowID);
		this.getReportsCommonPage().selectPercentCoverageAssetCheckBox();
		return true;
	}

	/**
	 * Executes selectCustomer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */

	public boolean selectCustomer(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.selectCustomer", data, dataRowID);
		String customer;
		if (!ActionArguments.isEmpty(data)) {
			customer = data;
		} else {
			ReportsBaseDataRow compRptDataRow = getReportsDataRow(dataRowID);
			Integer custRowID = NumberUtility.getIntegerValueOf(compRptDataRow.customerRowID);
			CustomerDataReader custDataReader = new CustomerDataReader(this.excelUtility);
			CustomerDataRow custDataRow = custDataReader.getDataRow(custRowID);
			customer = custDataRow.name;
		}

		this.getReportsCommonPage().selectCustomer(customer);
		this.getReportsCommonPage().getChangeCustomerDialog().confirmInChangeCustomerDialog();
		return true;
	}

	/**
	 * Executes selectSurveySelectorEndDateTime action.
	 * @param data - specifies the end date.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean selectSurveySelectorEndDateTime(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.selectSurveySelectorEndDateTime", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("selectSurveySelectorEndDateTime", ARG_DATA, data);
		this.getReportsCommonPage().selectEndDateForSurvey(data);
		return true;
	}

	/**
	 * Executes selectSurveySelectorStartDateTime action.
	 * @param data - specifies the start date.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean selectSurveySelectorStartDateTime(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.selectSurveySelectorStartDateTime", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("selectSurveySelectorStartDateTime", ARG_DATA, data);
		this.getReportsCommonPage().selectStartDateForSurvey(data);
		return true;
	}

	/**
	 * Executes selectSurveySelectorSurveyModeFilter action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean selectSurveySelectorSurveyModeFilter(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.selectSurveySelectorSurveyModeFilter", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("selectSurveySelectorSurveyModeFilter", ARG_DATA, data);

		SurveyModeFilter modeFilter = SurveyModeFilter.All;
		if (data.equals("Standard")) {
			modeFilter = SurveyModeFilter.Standard;
		} else if (data.equals("Assessment")) {
			modeFilter = SurveyModeFilter.Assessment;
		} else if (data.equals("EQ")) {
			modeFilter = SurveyModeFilter.EQ;
		} else if (data.equals("Manual")) {
			modeFilter = SurveyModeFilter.Manual;
		} else if (data.equals("Operator")) {
			modeFilter = SurveyModeFilter.Operator;
		} else if (data.equals("Rapid Response")) {
			modeFilter = SurveyModeFilter.RapidResponse;
		}

		this.getReportsCommonPage().selectSurveyModeForSurvey(modeFilter);
		return true;
	}

	/**
	 * Executes selectSurveySelectorSurveyor action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean selectSurveySelectorSurveyor(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.selectSurveySelectorSurveyor", data, dataRowID);
		String surveyorUnit;
		if (!ActionArguments.isEmpty(data)) {
			surveyorUnit = data;
		} else {
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(this.excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(dataRowID);
			surveyorUnit = surveyDataRow.surveySurveyor;
		}
		this.getReportsCommonPage().selectSurveySurveyor(surveyorUnit);
		return true;
	}

	/**
	 * Executes selectTabularPDFContent action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean selectTabularPDFContent(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.selectTabularPDFContent", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("selectTabularPDFContent", ARG_DATA_ROW_ID, dataRowID);
		ReportsCommonDataRow dataRow = getReportsCommonDataRow(dataRowID);
		Integer pdfContentRowID = NumberUtility.getIntegerValueOf(dataRow.reportOptTabularPDFContentRowID);
		ReportOptTabularPDFContentDataReader pdfContentDataReader = new ReportOptTabularPDFContentDataReader(this.excelUtility);
		ReportOptTabularPDFContentDataRow pdfContentDataRow = pdfContentDataReader.getDataRow(pdfContentRowID);
		Boolean selectGap = Boolean.valueOf(pdfContentDataRow.gapTable);
		Boolean selectIndicationTable = Boolean.valueOf(pdfContentDataRow.indicationTable);
		Boolean selectIsotopicAnalysis = Boolean.valueOf(pdfContentDataRow.isotopicAnalysis);
		Boolean selectPercentCoverageAssets = Boolean.valueOf(pdfContentDataRow.percentCoverageAssets);
		Boolean selectPercentCoverageReportArea = Boolean.valueOf(pdfContentDataRow.percentCoverageReportArea);

		if (selectGap) {
			this.getReportsCommonPage().selectGapTableCheckBox();
		}
		if (selectIndicationTable) {
			this.getReportsCommonPage().selectIndicationsTableCheckBox();
		}
		if (selectIsotopicAnalysis) {
			this.getReportsCommonPage().selectIsotopicAnalysisCheckBox();
		}
		if (selectPercentCoverageAssets) {
			this.getReportsCommonPage().selectPercentCoverageAssetCheckBox();
		}
		if (selectPercentCoverageReportArea) {
			this.getReportsCommonPage().selectPercentCoverageReportArea();
		}
		return true;
	}

	/**
	 * Executes selectTimeZone action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean selectTimeZone(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.selectTimeZone", data, dataRowID);
		String timeZone;
		if (!ActionArguments.isEmpty(data)) {
			timeZone = data;
		} else {
			ReportsBaseDataRow dataRow = getReportsDataRow(dataRowID);
			timeZone = dataRow.timezone;
		}
		this.getReportsCommonPage().selectTimeZone(timeZone);
		return true;
	}

	/**
	 * Executes selectViewLayersAsset action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean selectViewLayersAsset(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.selectViewLayersAsset", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("selectViewLayersAsset", ARG_DATA_ROW_ID, dataRowID);
		ReportsCommonDataRow dataRow = getReportsCommonDataRow(dataRowID);
		Integer rptViewLayerRowID = NumberUtility.getIntegerValueOf(dataRow.reportOptViewLayerRowID);
		ReportOptViewLayersDataReader optViewLayersDataReader = new ReportOptViewLayersDataReader(this.excelUtility);
		ReportOptViewLayersDataRow optViewLayersDataRow = optViewLayersDataReader.getDataRow(rptViewLayerRowID);
		HashMap<String, String> viewLayerMap = new HashMap<String, String>();
		List<Integer> assetRowIDs = ActionArguments.getNumericList(optViewLayersDataRow.assetRowIDs);
		for (Integer rowID : assetRowIDs) {
			ReportOptViewLayersAssetsDataReader viewLayersAssetsDataReader = getViewLayersAssetsDataReader();
			ReportOptViewLayersAssetsDataRow assetsDataRow = viewLayersAssetsDataReader.getDataRow(rowID);
			viewLayerMap.put(assetsDataRow.assetID, ComplianceReportEntity.ASSET_PREFIX + assetsDataRow.assetName);
		}

		this.getReportsCommonPage().selectViewLayerAssets(viewLayerMap);
		return true;
	}

	/**
	 * Executes selectViewLayersBoundary action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean selectViewLayersBoundary(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.selectViewLayersBoundary", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("selectViewLayersAsset", ARG_DATA_ROW_ID, dataRowID);
		ReportsCommonDataRow dataRow = getReportsCommonDataRow(dataRowID);
		Integer rptViewLayerRowID = NumberUtility.getIntegerValueOf(dataRow.reportOptViewLayerRowID);
		ReportOptViewLayersDataReader optViewLayersDataReader = new ReportOptViewLayersDataReader(this.excelUtility);
		ReportOptViewLayersDataRow optViewLayersDataRow = optViewLayersDataReader.getDataRow(rptViewLayerRowID);
		HashMap<String, String> viewLayerMap = new HashMap<String, String>();
		List<Integer> boundariesRowIDs = ActionArguments.getNumericList(optViewLayersDataRow.boundariesRowIDs);
		for (Integer rowID : boundariesRowIDs) {
			if(rowID==0){
				continue;
			}
			ReportOptViewLayersBoundaryDataReader viewLayersBoundaryDataReader = getViewLayersBoundaryDataReader();
			ReportOptViewLayersBoundaryDataRow boundariesDataRow = viewLayersBoundaryDataReader.getDataRow(rowID);
			viewLayerMap.put(boundariesDataRow.boundaryID, ComplianceReportEntity.BOUNDARY_PREFIX + boundariesDataRow.boundaryName);
		}

		this.getReportsCommonPage().selectViewLayerBoundaries(viewLayerMap);
		return true;
	}

	/**
	 * Executes setReportGenerationTimeout action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean setReportGenerationTimeout(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.selectViewLayersBoundary", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("setReportGenerationTimeout", data, ARG_DATA);
		this.getReportsCommonPage().setReportGenerationTimeout(Integer.valueOf(data));
		return true;
	}

	/**
	 * Executes verifyComplianceViewerButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyComplianceViewerButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyComplianceViewerButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyComplianceViewerButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ReportsButtonType.ReportViewer);
	}

	/**
	 * Executes verifyComplianceViewerViewCountEquals action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyComplianceViewerViewCountEquals(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifyComplianceViewerViewCountEquals", data, dataRowID);
		return true;
	}

	/**
	 * Executes verifyCopyButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyCopyButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyCopyButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyCopyButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ReportsButtonType.Copy);
	}

	/**
	 * Executes verifyCopyButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyCancelButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyCopyButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyCopyButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ReportsButtonType.Cancel);
	}
	/**
	 * Executes verifyDeleteButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyDeleteButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyDeleteButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyDeleteButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ReportsButtonType.Delete);
	}

	/**
	 * Executes verifyInvestigateButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyInvestigateButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyInvestigateButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyInvestigateButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ReportsButtonType.Investigate);
	}

	/**
	 * Executes verifyInvestigatePDFButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyInvestigatePDFButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyInvestigatePDFButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyInvestigatePDFButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ReportsButtonType.InvestigatePDF);
	}

	/**
	 * Executes verifyLastXDaysSurveysPresentInPDF action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyLastXDaysSurveysPresentInPDF(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifyLastXDaysSurveysPresentInPDF", data, dataRowID);
		// TODO: To be implemented.
		return false;
	}

	/**
	 * Executes verifyPDFContainsInputtedInformation action.
	 * @param data - Colon separated list of string values to be found in the PDF.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyPDFContainsInputtedInformation(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyPDFContainsInputtedInformation", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyPDFContainsInputtedInformation", ARG_DATA, data);
		List<String> listExpectedStrings = RegexUtility.split(data, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
		return this.getReportsCommonPage().verifyReportContainsText(getWorkingReportsDataRow().title, listExpectedStrings);
	}

	/**
	 * Executes verifyPDFDoesNotContainInputtedInformation action.
	 * @param data - Colon separated list of string values to be found in the PDF.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyPDFDoesNotContainInputtedInformation(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyPDFDoesNotContainInputtedInformation", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyPDFDoesNotContainInputtedInformation", ARG_DATA, data);
		List<String> listExpectedStrings = RegexUtility.split(data, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
		return this.getReportsCommonPage().verifyReportDoesNotContainText(getWorkingReportsDataRow().title, listExpectedStrings);
	}

	/**
	 * Executes verifyMetaDataFilesHaveCorrectData action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyMetaDataFilesHaveCorrectData(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyMetaDataFilesHaveCorrectData", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyMetaDataFilesHaveCorrectData", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ReportsButtonType.ReportViewer);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		waitForReportFileDownload(dataRowID, ReportFileType.MetaDataZIP, -1);
		return this.verifyAllMetadataFiles(data, dataRowID);
	}

	/**
	 * Executes verifyMetaDataZIPFilesAreCorrect action.
	 * Verifies that correct files are present in the Metadata ZIP.
	 * NOTE: This method does NOT verify the content within the Metadata Zip file.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyMetaDataZIPFilesAreCorrect(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyMetaDataZIPFilesAreCorrect", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyMetaDataZIPFilesAreCorrect", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ReportsButtonType.ReportViewer);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		waitForReportFileDownload(dataRowID, ReportFileType.MetaDataZIP, -1);

		// TODO: Check for specific metadata files in the ZIP.
		return false;
	}

	/**
	 * Executes verifyMetaDataZIPThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyMetaDataZIPThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyMetaDataZIPThumbnailDownloadFromComplianceViewer", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyMetaDataZIPThumbnailDownloadFromComplianceViewer", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ReportsButtonType.ReportViewer);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		// TODO: Internal method needs implementation.
		this.getReportsCommonPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.ComplianceZipMeta);
		return true;
	}

	/**
	 * Executes verifyMetaDataZIPThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyMetaDataZIPThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyMetaDataZIPThumbnailIsShownInComplianceViewer", data, dataRowID);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		return this.getReportsCommonPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceZipMeta);
	}

	/**
	 * Executes verifyPaginationAndSortingOnAllColumns action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyPaginationAndSortingOnAllColumns(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyPaginationAndSortingOnAllColumns", data, dataRowID);

		ActionArguments.verifyNotNullOrEmpty("verifyPaginationAndSortingOnAllColumns", ARG_DATA, data);

		boolean retVal = true;
		String paginationSetting = data;

		retVal = retVal && (this.getReportsCommonPage().checkPaginationSetting(paginationSetting));

		this.getReportsCommonPage().sortTableByColumn(1, TableSortOrder.ASC);
		this.getReportsCommonPage().sortTableByColumn(1, TableSortOrder.DESC);

		retVal = retVal && (!(this.getReportsCommonPage().getNumberofRecords() > Integer.parseInt(paginationSetting)));

		this.getReportsCommonPage().sortTableByColumn(2, TableSortOrder.ASC);
		this.getReportsCommonPage().sortTableByColumn(2, TableSortOrder.DESC);

		retVal = retVal && (!(this.getReportsCommonPage().getNumberofRecords() > Integer.parseInt(paginationSetting)));

		this.getReportsCommonPage().sortTableByColumn(3, TableSortOrder.ASC);
		this.getReportsCommonPage().sortTableByColumn(3, TableSortOrder.DESC);

		retVal = retVal && (!(this.getReportsCommonPage().getNumberofRecords() > Integer.parseInt(paginationSetting)));

		this.getReportsCommonPage().sortTableByColumn(4, TableSortOrder.ASC);
		this.getReportsCommonPage().sortTableByColumn(4, TableSortOrder.DESC);

		return retVal && (!(this.getReportsCommonPage().getNumberofRecords() > Integer.parseInt(paginationSetting)));
	}

	/**
	 * Executes verifyPDFThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyPDFThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyPDFThumbnailDownloadFromComplianceViewer", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyPDFThumbnailDownloadFromComplianceViewer", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ReportsButtonType.ReportViewer);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		// TODO: Internal method needs implementation.
		this.getReportsCommonPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.ComplianceTablePDF);
		return true;
	}

	/**
	 * Executes verifyPDFThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyPDFThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyPDFThumbnailIsShownInComplianceViewer", data, dataRowID);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		return this.getReportsCommonPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceTablePDF);
	}

	/**
	 * Executes verifyPDFZipFilesAreCorrect action.
	 * Verifies that the correct PDF files are present in the ZIP by checking files with expected file names.
	 * NOTE: This method does NOT check that the data inside the PDFs is correct.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyPDFZipFilesAreCorrect(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyPDFZipFilesAreCorrect", data, dataRowID);

		// Verify that there is a file for Report PDF.
		// and there is one PDF file for each view that was specified in the input.
		Integer expectedFileCount = 1;
		List<Integer> viewRowIDs = ActionArguments.getNumericList(getWorkingReportsCommonDataRow().reportViewRowIDs);
		if (viewRowIDs != null) {
			expectedFileCount += viewRowIDs.size();
		}

		List<String> expectedFileNames = new ArrayList<String>();
		String reportFileNameWithoutExt = getWorkingReportsDataRow().title.replace(" ", "").replaceAll(REGEX_PATTEN_SPECIAL_CHARACTERS, "_");
		expectedFileNames.add(reportFileNameWithoutExt + ".pdf");
		for (int i=1; i<expectedFileCount; i++) {
			String viewName = workingReportViewsDataRows.get().get(i-1).name;
			expectedFileNames.add(String.format("%s_%s.pdf", reportFileNameWithoutExt, viewName));
		}

		Path downloadDirectory = Paths.get(getDownloadPath(ReportFileType.ZIP));
		return FileUtility.compareFilesInDirectory(downloadDirectory, expectedFileNames);
	}

	/**
	 * Executes verifyPDFZipFilesArePresent action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyPDFZipFilesArePresent(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyPDFZipFilesArePresent", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyPDFZipFilesArePresent", ARG_DATA_ROW_ID, dataRowID);

		if (getWorkingReportsEntity() == null) {
			throw new Exception("Create new report before verifying report PDF files. Report has not been created.");
		}

		ReportsBaseDataRow compRptDataRow = getReportsDataRow(dataRowID);
		String rptTitle = compRptDataRow.title;

		if ((getReportsCommonPage().checkActionStatus(rptTitle, LoginPageActions.workingDataRow.get().username, null))) {
			if ((!getReportsCommonPage().findReport(rptTitle, LoginPageActions.workingDataRow.get().username)) ||
					(!getReportsCommonPage().validatePdfFiles(getWorkingReportsEntity(),
							TestContext.INSTANCE.getTestSetup().getDownloadPath()))) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Executes verifyPDFZIPThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyPDFZIPThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyPDFZIPThumbnailDownloadFromComplianceViewer", data, dataRowID);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		this.getReportsCommonPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.ComplianceZipPDF);
		return true;
	}

	/**
	 * Executes verifyPDFZIPThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyPDFZIPThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyPDFZIPThumbnailIsShownInComplianceViewer", data, dataRowID);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		return this.getReportsCommonPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceZipPDF);
	}

	/**
	 * Executes verifyPDFZipFilesArePresent action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyReportCreationInSSRSPDFIsCorrect(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyReportCreationInSSRSPDFIsCorrect", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyReportCreationInSSRSPDFIsCorrect", ARG_DATA_ROW_ID, dataRowID);

		if (getWorkingReportsEntity() == null) {
			throw new Exception("Create new report before verifying report PDF files. Report has not been created.");
		}

		return getReportsCommonPage().validateReportCreationDate(TestContext.INSTANCE.getTestSetup().getDownloadPath());
	}

	/**
	 * Executes verifyReportPDFMatches action.
	 * @param data - regex pattern string for matching.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyReportPDFMatches(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyReportPDFMatches", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyReportPDFMatches", ARG_DATA, data);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		String pdfFileName = getReportsCommonPage().getReportPDFFileName(getWorkingReportsDataRow().title, true /*includeExtension*/);
		String pdfFileFullPath = Paths.get(downloadPath, pdfFileName).toString();
		String pdfContent = new PDFUtility().extractPDFText(pdfFileFullPath);
		return RegexUtility.matchesPattern(pdfContent, data);
	}

	/**
	 * Executes verifyReportThumbnailMatches action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyReportThumbnailMatches(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifyReportThumbnailMatches", data, dataRowID);
		// TODO: To be implemented.
		return false;
	}

	/**
	 * Executes verifyRequiredFieldsAreShownInRed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyRequiredFieldsAreShownInRed(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifyRequiredFieldsAreShownInRed", data, dataRowID);
		// TODO: To be implemented.
		return false;
	}

	/**
	 * Executes verifyReportPageFieldsAreCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyReportPageFieldsAreCorrect(String data, Integer dataRowID) throws Exception {

		logAction("ReportsCommonPageActions.verifyReportPageFieldsAreCorrect", data, dataRowID);
		ReportsCommonDataRow dataRow = getReportsCommonDataRow(dataRowID);
		List<Integer> surveyRowIDs = ActionArguments.getNumericList(dataRow.reportSurveyRowIDs);
		Integer reportViewLayerRowID = NumberUtility.getIntegerValueOf(dataRow.reportOptViewLayerRowID);
		ReportOptViewLayersDataRow reportOptViewLayersDataRow = new ReportOptViewLayersDataReader(this.excelUtility).getDataRow(reportViewLayerRowID);
		Integer reportPDFContentRowID = NumberUtility.getIntegerValueOf(dataRow.reportOptTabularPDFContentRowID);
		ReportOptTabularPDFContentDataRow reportPDFContentDataRow = new ReportOptTabularPDFContentDataReader(this.excelUtility).getDataRow(reportPDFContentRowID);
		//String customerName = new CustomerDataReader(this.excelUtility).getDataRow(NumberUtility.getIntegerValueOf(dataRow.customerRowID)).name;
		//boolean reportTitleMatches = (dataRow.title == this.getReportsCommonPage().getReportTitle());

		String actualTimezoneValue = this.getReportsCommonPage().getTimezoneValue();
		String actualExclusionRadius = this.getReportsCommonPage().getExclusionRadius();
		String actualNELatitude = this.getReportsCommonPage().getNELatitude();
		String actualNELongitude = this.getReportsCommonPage().getNELongitude();
		String actualSWLatitude = this.getReportsCommonPage().getSWLatitude();
		String actualSWLongitude = this.getReportsCommonPage().getSWLongitude();
		String actualFOVOpacity = this.getReportsCommonPage().getFOVOpacity();
		String actualPDFWidth = this.getReportsCommonPage().getPDFWidth();
		String actualPDFHeight = this.getReportsCommonPage().getPDFHeight();

		Log.info(String.format("Matching timezone. Expected=[%s], Actual=[%s]", dataRow.timezone, actualTimezoneValue));
		Log.info(String.format("Matching exclusionRadius. Expected=[%s], Actual=[%s]", dataRow.exclusionRadius, actualExclusionRadius));
		Log.info(String.format("Matching customBoundaryNELat. Expected=[%s], Actual=[%s]", dataRow.customBoundaryNELat, actualNELatitude));
		Log.info(String.format("Matching customBoundaryNELong. Expected=[%s], Actual=[%s]", dataRow.customBoundaryNELong, actualNELongitude));
		Log.info(String.format("Matching customBoundarySWLat. Expected=[%s], Actual=[%s]", dataRow.customBoundarySWLat, actualSWLatitude));
		Log.info(String.format("Matching customBoundarySWLong. Expected=[%s], Actual=[%s]", dataRow.customBoundarySWLong, actualSWLongitude));
		Log.info(String.format("Matching opacityFOV. Expected=[%s], Actual=[%s]", dataRow.opacityFOV, actualFOVOpacity));
		Log.info(String.format("Matching pDFImageOutputWidth. Expected=[%f], Actual=[%f]", Double.valueOf(dataRow.pDFImageOutputWidth), Double.valueOf(actualPDFWidth)));
		Log.info(String.format("Matching pDFImageOutputHeight. Expected=[%f], Actual=[%f]", Double.valueOf(dataRow.pDFImageOutputHeight), Double.valueOf(actualPDFHeight)));

		boolean timezoneMatches = (dataRow.timezone.equals(actualTimezoneValue));
		boolean exclusionRadiusMatches = (dataRow.exclusionRadius.equals(actualExclusionRadius));
		boolean nELatMatches = (dataRow.customBoundaryNELat.equals(actualNELatitude));
		boolean nELongMatches = (dataRow.customBoundaryNELong.equals(actualNELongitude));
		boolean sWLatMatches = (dataRow.customBoundarySWLat.equals(actualSWLatitude));
		boolean sWLongMatches = (dataRow.customBoundarySWLong.equals(actualSWLongitude));
		boolean fovOpacityMatches = (dataRow.opacityFOV.equals(actualFOVOpacity));
		boolean pdfWidthMatches = (Double.valueOf(dataRow.pDFImageOutputWidth).equals(Double.valueOf(actualPDFWidth)));
		boolean pdfHeightMatches = (Double.valueOf(dataRow.pDFImageOutputHeight).equals(Double.valueOf(actualPDFHeight)));
		boolean verifyReportSurveyValuesMatch = verifyReportSurveyValuesMatch(surveyRowIDs);
		boolean areAssetBoundariesMatch = areAssetBoundariesMatch(reportOptViewLayersDataRow);
		boolean areTabularPDFContentSelectionMatch = areTabularPDFContentSelectionMatch(reportPDFContentDataRow);

		boolean reportsSpecifiedFieldsMatch = verifyReportsSpecificPageFieldsAreCorrect(dataRow);

		Log.info(String.format("MATCH result -> timezoneMatches=[%b];exclusionRadiusMatches=[%b];nELatMatches=[%b];nELongMatches=[%b];"
				+ "sWLatMatches=[%b];sWLongMatches=[%b];fovOpacityMatches=[%b];pdfWidthMatches=[%b];pdfHeightMatches=[%b];verifyReportSurveyValuesMatch=[%b]"
				+ ";areAssetBoundariesMatch=[%b];areTabularPDFContentSelectionMatch=[%b]",
				timezoneMatches,exclusionRadiusMatches,nELatMatches,nELongMatches,
				sWLatMatches,sWLongMatches,fovOpacityMatches,pdfWidthMatches,pdfHeightMatches,verifyReportSurveyValuesMatch,
				areAssetBoundariesMatch,areTabularPDFContentSelectionMatch));

		return timezoneMatches && exclusionRadiusMatches &&
				nELatMatches && nELongMatches && sWLatMatches && sWLongMatches && fovOpacityMatches &&
				pdfWidthMatches && pdfHeightMatches && verifyReportSurveyValuesMatch &&
				areAssetBoundariesMatch && areTabularPDFContentSelectionMatch && reportsSpecifiedFieldsMatch;
	}

	/**
	 * Executes verifyResubmitButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyResubmitButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyResubmitButtonIsDisplayed", data, dataRowID);
		this.getReportsCommonPage().checkButtonOnReportsPageAndClick(getWorkingReportsDataRow().title,
				LoginPageActions.workingDataRow.get().username, ReportsButtonType.Resubmit, false /*clickButton*/, false /*confirmAction*/);
		return true;
	}

	/**
	 * Executes verifySearchedSurveyIsShown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveyIsShown(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifySearchedSurveyIsShown", data, dataRowID);
		// TODO: To be implemented.
		return false;
	}

	/**
	 * Executes verifySearchedSurveysAreForLastXDays action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysAreForLastXDays(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifySearchedSurveysAreForLastXDays", data, dataRowID);
		// TODO: To be implemented.
		return false;
	}

	/**
	 * Executes verifySearchedSurveysMatchDateRange action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysMatchDateRange(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifySearchedSurveysMatchDateRange", data, dataRowID);
		// TODO: To be implemented.
		return false;
	}

	/**
	 * Executes verifySearchedSurveysMatchSurveyorUnit action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysMatchSurveyorUnit(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifySearchedSurveysMatchSurveyorUnit", data, dataRowID);
		// TODO: To be implemented.
		return false;
	}

	/**
	 * Executes verifySearchedSurveysMatchTag action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysMatchTag(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifySearchedSurveysMatchTag", data, dataRowID);
		// TODO: To be implemented.
		return false;
	}

	/**
	 * Executes verifyShapeZIPFilesAreCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	private boolean verifyShapeZIPFilesAreCorrect(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyShapeZIPFilesAreCorrect", data, dataRowID);
		// TODO: Action needs implementation.
		return false;
	}

	/**
	 * Executes verifyShapeZIPThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyShapeZIPThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyShapeZIPThumbnailDownloadFromComplianceViewer", data, dataRowID);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		this.getReportsCommonPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.ComplianceZipShape);
		return true;
	}

	/**
	 * Executes verifyShapeZIPThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyShapeZIPThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyShapeZIPThumbnailIsShownInComplianceViewer", data, dataRowID);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		return this.getReportsCommonPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceZipShape);
	}

	/**
	 * Executes verifyIsotopicTableSortedAscByColumn action.
	 * @param data - column name used for sorting.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyIsotopicTableSortedAscByColumn(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyIsotopicTableSortedAscByColumn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyIsotopicTableSortedAscByColumn", ARG_DATA, data);
		String extractPDFText = this.getReportsCommonPage().getSSRSPdfText(getWorkingReportsDataRow().title);
		List<String[]> isotopicAnalysisTblList = new SSRSIsotopicAnalysisTableParser().parseAsTable(extractPDFText,
				RegexUtility.BACKQUOTE_SPLIT_REGEX_PATTERN);
		IsotopicAnalysisTableColumns tableColumn = IsotopicAnalysisTableColumns.valueOf(data);
		List<String> tableValuesList = ArrayUtility.getColumnStringList(isotopicAnalysisTblList, tableColumn.getIndex());
		return SortHelper.isSortedASC(tableValuesList.toArray(new String[tableValuesList.size()]));
	}

	/**
	 * Executes verifyIsotopicTableSortedDescByColumn action.
	 * @param data - column name used for sorting.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyIsotopicTableSortedDescByColumn(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyIsotopicTableSortedDescByColumn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyIsotopicTableSortedDescByColumn", ARG_DATA, data);
		List<String[]> isotopicAnalysisTblList = this.getReportsCommonPage().getSSRSPDFTableValues(
				PDFTable.ISOTOPICANALYSISTABLE, getWorkingReportsDataRow().title);
		IsotopicAnalysisTableColumns tableColumn = IsotopicAnalysisTableColumns.valueOf(data);
		List<String> tableValuesList = ArrayUtility.getColumnStringList(isotopicAnalysisTblList, tableColumn.getIndex());
		return SortHelper.isSortedDESC(tableValuesList.toArray(new String[tableValuesList.size()]));
	}

	/**
	 * Executes verifyIsotopicValueIsFormattedCorrectly action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyIsotopicValueIsFormattedCorrectly(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyIsotopicValueIsFormattedCorrectly", data, dataRowID);
		List<String[]> isotopicAnalysisTblList = this.getReportsCommonPage().getSSRSPDFTableValues(
				PDFTable.ISOTOPICANALYSISTABLE, getWorkingReportsDataRow().title);
		String[] isotopicUncertaintyValues = isotopicAnalysisTblList.get(IsotopicAnalysisTableColumns.IsotopicValueUncertainty.getIndex());
		for (String isotopicUncertaintyValue : isotopicUncertaintyValues) {
			if (!this.getReportsCommonPage().verifyIsotopicValueIsFormattedCorrectly(isotopicUncertaintyValue)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Executes verifyUncertaintyValueIsFormattedCorrectly action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyUncertaintyValueIsFormattedCorrectly(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyUncertaintyValueIsFormattedCorrectly", data, dataRowID);
		List<String[]> isotopicAnalysisTblList = this.getReportsCommonPage().getSSRSPDFTableValues(
				PDFTable.ISOTOPICANALYSISTABLE, getWorkingReportsDataRow().title);
		String[] isotopicUncertaintyValues = isotopicAnalysisTblList.get(IsotopicAnalysisTableColumns.IsotopicValueUncertainty.getIndex());
		for (String isotopicUncertaintyValue : isotopicUncertaintyValues) {
			if (!this.getReportsCommonPage().verifyUncertaintyValueIsFormattedCorrectly(isotopicUncertaintyValue)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Executes verifyViewThumbnailDownloadFromComplianceViewerByViewIndex action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyViewThumbnailDownloadFromComplianceViewerByViewIndex(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyViewThumbnailDownloadFromComplianceViewerByViewIndex", data, dataRowID);
		openComplianceViewerDialog(dataRowID);
		clickComplianceViewerViewByIndex(data, dataRowID);
		waitForViewDownloadByViewIndex(data, dataRowID);
		return true;
	}

	/**
	 * Executes verifyViewThumbnailIsShownInComplianceViewerByViewIndex action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyViewThumbnailIsShownInComplianceViewerByViewIndex(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyViewThumbnailIsShownInComplianceViewerByViewIndex", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(FN_CLICK_ON_COMPLIANCE_VIEWER_VIEW_BY_INDEX, ARG_DATA, data);
		Integer viewIdx = NumberUtility.getIntegerValueOf(data);
		ActionArguments.verifyGreaterThanZero(FN_CLICK_ON_COMPLIANCE_VIEWER_VIEW_BY_INDEX, ARG_DATA, viewIdx);

		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		// Find the view image to click. Order of the images is the order returned by API.
		WebElement viewElement = this.getReportsCommonPage().getViewThumbnailImageByIndex(viewIdx);
		return viewElement.isDisplayed();
	}

	/**
	 * Executes verifyPercentCoverageForecastPresentInReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPercentCoverageForecastPresentInReport(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifyPercentCoverageForecastPresentInReport", data, dataRowID);
		// to be implemented
		return false;
	}

	/**
	 * Executes verifyPercentCoverageAssetsAndReportAreaValuesInReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPercentCoverageAssetsAndReportAreaValuesInReport(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifyPercentCoverageAssetsAndReportAreaValuesInReport", data, dataRowID);
		// to be implemented
		return false;
	}

	/**
	 * Executes verifyAssetsAreDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyAssetsAreDisplayed(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifyAssetsAreDisplayed", data, dataRowID);
		// to be implemented
		return false;
	}

	/**
	 * Executes verifyLISAsIndicationTableRowCountEquals action.
	 * @param data - specifies the expected number of rows in the Indication table.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyLISAsIndicationTableRowCountEquals(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyLISAsIndicationTableRowCountEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyLISAsIndicationTableRowCountEquals", ARG_DATA, data);
		Integer expectedRows = NumberUtility.getIntegerValueOf(data);
		List<String[]> lisasIndicationTblList = this.getReportsCommonPage().getSSRSPDFTableValues(
				PDFTable.LISAINDICATIONTABLE, getWorkingReportsDataRow().title);
		Integer actualRows = (lisasIndicationTblList != null) ? lisasIndicationTblList.size() : 0;
		Log.info(String.format("Expected Row Count=[%d], Actual Row Count=[%d]", expectedRows, actualRows));
		return (expectedRows == actualRows);
	}

	/**
	 * Executes verifyLisasTableSortedAscByColumn action.
	 * @param data - specifies the column name - 'LISAIndicationTableColumns' enum string value.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyLISAsIndicationTableSortedAscByColumn(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyLisasTableSortedAscByColumn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyLisasTableSortedAscByColumn", ARG_DATA, data);
		List<String[]> lisasIndicationTblList = this.getReportsCommonPage().getSSRSPDFTableValues(
				PDFTable.LISAINDICATIONTABLE, getWorkingReportsDataRow().title);
		LISAIndicationTableColumns tableColumn = LISAIndicationTableColumns.valueOf(data);
		List<String> tableValuesList = ArrayUtility.getColumnStringList(lisasIndicationTblList, tableColumn.getIndex());
		return SortHelper.isSortedASC(tableValuesList.toArray(new String[tableValuesList.size()]));
	}

	/**
	 * Executes verifyLisasTableSortedDescByColumn action.
	 * @param data - specifies the column name - 'LISAIndicationTableColumns' enum string value.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyLISAsIndicationTableSortedDescByColumn(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyLisasTableSortedDescByColumn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyLisasTableSortedDescByColumn", ARG_DATA, data);
		List<String[]> lisasIndicationTblList = this.getReportsCommonPage().getSSRSPDFTableValues(
				PDFTable.LISAINDICATIONTABLE, getWorkingReportsDataRow().title);
		LISAIndicationTableColumns tableColumn = LISAIndicationTableColumns.valueOf(data);
		List<String> tableValuesList = ArrayUtility.getColumnStringList(lisasIndicationTblList, tableColumn.getIndex());
		return SortHelper.isSortedDESC(tableValuesList.toArray(new String[tableValuesList.size()]));
	}

	/**
	 * Executes verifyWarningMessageOnDeleteButtonClickEquals action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyWarningMessageOnDeleteButtonClickEquals(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyWarningMessageOnDeleteButtonClickEquals", data, dataRowID);
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		this.getReportsCommonPage().verifyDeleteModalMessageIsCorrect(reportsDataRow.title);
		return true;
	}

	/**
	 * Executes clickOnComplianceViewerPDF action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnComplianceViewerPDF(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnComplianceViewerPDF", data, dataRowID);
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		this.getReportsCommonPage().invokePDFFileDownload(reportsDataRow.title);
		return true;
	}

	/**
	 * Executes clickOnComplianceViewerPDFZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnComplianceViewerPDFZIP(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnComplianceViewerPDFZIP", data, dataRowID);
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		this.getReportsCommonPage().invokePDFZipFileDownload(reportsDataRow.title);
		return true;
	}

	/**
	 * Executes clickOnComplianceViewerMetaZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnComplianceViewerMetaZIP(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnComplianceViewerMetaZIP", data, dataRowID);
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		this.getReportsCommonPage().invokeMetaZipFileDownload(reportsDataRow.title);
		return true;
	}

	/**
	 * Executes clickOnComplianceViewerShapeZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnComplianceViewerShapeZIP(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnComplianceViewerShapeZIP", data, dataRowID);
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		this.getReportsCommonPage().invokeShapeZipFileDownload(reportsDataRow.title);
		return true;
	}

	/**
	 * Executes clickOnComplianceViewerViewByIndex action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnComplianceViewerViewByIndex(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnComplianceViewerViewByIndex", data, dataRowID);
		return clickComplianceViewerViewByIndex(data, dataRowID);
	}

	public boolean clickOnCloseReportViewer(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.clickOnCloseReportViewer", data, dataRowID);
		this.getReportsCommonPage().clickOnCloseReportViewer(data);
		return true;
	}

	/**
	 * Executes clickNoOnChangeReportDialog action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickNoOnChangeReportDialog(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.clickNoOnChangeReportDialog", data, dataRowID);
		// to be implemented.
		return false;
	}

	/**
	 * Executes waitForReportGenerationToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean waitForReportGenerationToComplete(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.waitForReportGenerationToComplete", data, dataRowID);
		this.getReportsCommonPage().checkErrorMessages();
		this.getReportsCommonPage().waitForPageLoad();
		return this.getReportsCommonPage().waitForReportGenerationtoComplete(getWorkingReportsDataRow().title,
				TestContext.INSTANCE.getLoggedInUser());
	}

	/**
	 * Executes waitForComplianceViewerDialogToOpen action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForComplianceViewerDialogToOpen(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.waitForComplianceViewerDialogToOpen", data, dataRowID);
		this.getReportsCommonPage().waitForReportViewerDialogToOpen();
		return true;
	}

	/**
	 * Executes waitForComplianceViewerDialogToClose action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForComplianceViewerDialogToClose(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.waitForComplianceViewerDialogToClose", data, dataRowID);
		this.getReportsCommonPage().waitForReportViewerDialogToClose();
		return true;
	}

	/**
	 * Executes waitForPDFDownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean waitForPDFDownloadToComplete(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.waitForPDFDownloadToComplete", data, dataRowID);
		waitForReportFileDownload(dataRowID, ReportFileType.PDF, -1);
		return true;
	}

	/**
	 * Executes waitForPDFZIPDownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean waitForPDFZIPDownloadToComplete(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.waitForPDFZIPDownloadToComplete", data, dataRowID);
		waitForReportFileDownload(dataRowID, ReportFileType.ZIP, -1);
		return true;
	}

	/**
	 * Executes waitForMetaZIPDownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean waitForMetaZIPDownloadToComplete(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.waitForMetaZIPDownloadToComplete", data, dataRowID);
		waitForReportFileDownload(dataRowID, ReportFileType.MetaDataZIP, -1);
		return true;
	}

	/**
	 * Executes waitForShapeZIPDownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean waitForShapeZIPDownloadToComplete(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.waitForShapeZIPDownloadToComplete", data, dataRowID);
		waitForReportFileDownload(dataRowID, ReportFileType.ShapeZIP, -1, 0);
		return true;
	}

	/**
	 * Executes waitForView1DownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean waitForViewDownloadToCompleteByViewIndex(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.waitForViewDownloadToCompleteByViewIndex", data, dataRowID);
		waitForViewDownloadByViewIndex(data, dataRowID);
		return true;
	}

	/**
	 * Executes waitForConfirmDeletePopupToShow action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean waitForConfirmDeletePopupToShow(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.waitForConfirmDeletePopupToShow", data, dataRowID);
		getReportsCommonPage().waitForConfirmDeletePopupToShow();
		return true;
	}

	/**
	 * Executes waitForConfirmDeletePopupToClose action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean waitForConfirmDeletePopupToClose(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.waitForConfirmDeletePopupToClose", data, dataRowID);
		getReportsCommonPage().waitForConfirmDeletePopupToClose();
		return true;
	}

	/**
	 * Executes extractPDFZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean extractPDFZIP(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.extractPDFZIP", data, dataRowID);
		String fileName = this.getReportsCommonPage().getReportPDFZipFileName(getWorkingReportsDataRow().title, false /*includeExtension*/);
		BaseHelper.deCompressZipFile(fileName, TestContext.INSTANCE.getTestSetup().getDownloadPath());
		return true;
	}

	/**
	 * Executes extractMetaZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean extractMetaZIP(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.extractMetaZIP", data, dataRowID);
		String fileName = this.getReportsCommonPage().getReportMetaZipFileName(getWorkingReportsDataRow().title, false /*includeExtension*/);
		BaseHelper.deCompressZipFile(fileName, TestContext.INSTANCE.getTestSetup().getDownloadPath());
		return true;
	}

	/**
	 * Executes extractShapeZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean extractShapeZIP(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.extractShapeZIP", data, dataRowID);
		String fileName = this.getReportsCommonPage().getReportShapeZipFileName(getWorkingReportsDataRow().title, false /*includeExtension*/);
		BaseHelper.deCompressZipFile(fileName, TestContext.INSTANCE.getTestSetup().getDownloadPath());
		return true;
	}

	/**
	 * Executes verifyReportFilesHaveCorrectData action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyReportJobBaselines(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyReportFilesHaveCorrectData", data, dataRowID);
		return this.getReportsCommonPage().compareReportJobPerfBaseline(getWorkingReportsDataRow().tCID,
				getWorkingReportsDataRow().title);
	}

	/**
	 * Executes verifyAllMetadataFiles action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyAllMetadataFiles(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyAllMetadataFiles", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.MetaDataZIP);
		boolean verifyReportSurveyMetaDataFile = this.getReportsCommonPage().verifyReportSurveyMetaDataFile(downloadPath, getWorkingReportsDataRow().title);
		boolean verifyIsotopicMetaDataFile = this.getReportsCommonPage().verifyIsotopicMetaDataFile(downloadPath, getWorkingReportsDataRow().title);
		boolean verifyLISASMetaDataFile = this.getReportsCommonPage().verifyLISASMetaDataFile(downloadPath, getWorkingReportsDataRow().title);
		Log.info(String.format("verifyReportSurveyMetaDataFile = %b; verifyIsotopicMetaDataFile = %b; verifyLISASMetaDataFile = %b",
				verifyReportSurveyMetaDataFile, verifyIsotopicMetaDataFile, verifyLISASMetaDataFile));
		return verifyReportSurveyMetaDataFile && verifyIsotopicMetaDataFile && verifyLISASMetaDataFile;
	}

	/**
	 * Executes verifyAllSSRSTableInfos action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyAllSSRSTableInfos(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyAllSSRSTableInfos", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return verifySSRSTableInfos(downloadPath);
	}

	/**
	 * Executes verifyBoundariesAutoCompleteListContains action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyBoundariesAutoCompleteListContains(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyBoundariesAutoCompleteListContains", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyBoundariesAutoCompleteListContains", ARG_DATA, data);
		List<String> boundaryNamesList = RegexUtility.split(data, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		ReportsCommonDataRow reportsDataRow = getReportsCommonDataRow(dataRowID);
		return this.getReportsCommonPage().verifyCustomerBoundaryLatLongSelectorAutoCompleteListContains(
				reportsDataRow.customerBoundaryType, reportsDataRow.customerBoundaryName, boundaryNamesList);
	}

	/**
	 * Executes verifyGapsTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyGapsTableInfo(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyGapsTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getReportsCommonPage().verifyGapsTable(downloadPath, getWorkingReportsDataRow().title);
	}

	/**
	 * Executes verifyIsotopicAnalysisTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyIsotopicAnalysisTableInfo(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyIsotopicAnalysisTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getReportsCommonPage().verifyIsotopicAnalysisTable(downloadPath, getWorkingReportsDataRow().title);
	}

	/**
	 * Executes verifyIsotopicMetaDataFile action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyIsotopicMetaDataFile(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyIsotopicMetaDataFile", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.MetaDataZIP);
		return this.getReportsCommonPage().verifyIsotopicMetaDataFile(downloadPath, getWorkingReportsDataRow().title);
	}

	/**
	 * Executes verifyLISAsIndicationTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyLISAsIndicationTableInfo(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyLISAsIndicationTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getReportsCommonPage().verifyIndicationTable(downloadPath, getWorkingReportsDataRow().title);
	}

	/**
	 * Executes verifyLISAsIndicationTableMinAmplitudeValues action.
	 * @param data - specifies the min amplitude value.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyLISAsIndicationTableMinAmplitudeValues(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyLISAsIndicationTableMinAmplitudeValues", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyLISAsIndicationTableMinAmplitudeValues", ARG_DATA, data);
		List<String[]> lisasIndicationTblList = this.getReportsCommonPage().getSSRSPDFTableValues(
				PDFTable.LISAINDICATIONTABLE, getWorkingReportsDataRow().title);
		List<String> minAmplitudeValues = ArrayUtility.getColumnStringList(lisasIndicationTblList, LISAIndicationTableColumns.Amplitude.getIndex());
		Log.info(String.format("Verifying min amplitude array values are greater than expected location min amplitude = [%s]", data));
		Log.info(String.format("Min Amplitude array values are -> %s", LogHelper.listToString(minAmplitudeValues)));
		return ArrayUtility.areValuesGreater(minAmplitudeValues.toArray(new String[minAmplitudeValues.size()]), NumberUtility.getFloatValueOf(data));
	}

	/**
	 * Executes verifyLISASMetaDataFile action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyLISASMetaDataFile(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyLISASMetaDataFile", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.MetaDataZIP);
		return this.getReportsCommonPage().verifyLISASMetaDataFile(downloadPath, getWorkingReportsDataRow().title);
	}

	/**
	 * Executes verifyReportDeletedSuccessfully action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyReportDeletedSuccessfully(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyReportDeletedSuccessfully", data, dataRowID);
		return !this.getReportsCommonPage().searchReport(getWorkingReportsDataRow().title, LoginPageActions.workingDataRow.get().username);
	}

	/**
	 * Executes verifyReportSurveyMetadataFile action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyReportSurveyMetadataFile(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyReportSurveyMetadataFile", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.MetaDataZIP);
		return this.getReportsCommonPage().verifyReportSurveyMetaDataFile(downloadPath, getWorkingReportsDataRow().title);
	}

	/**
	 * Executes verifyShapeFilesWithBaselines action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyShapeFilesWithBaselines(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyShapeFilesWithBaselines", data, dataRowID);
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		return this.getReportsCommonPage().verifyShapeFilesWithBaselines(reportsDataRow.title,
				reportsDataRow.tCID, 0);
	}

	/**
	 * Executes verifySSRSCoverageTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifySSRSCoverageTableInfo(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifySSRSCoverageTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getReportsCommonPage().verifyCoverageValuesTable(downloadPath, getWorkingReportsDataRow().title,
				getWorkingReportsEntity().getTablesList().get(0));
	}

	/**
	 * Executes verifySSRSDrivingSurveyTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifySSRSDrivingSurveyTableInfo(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifySSRSDrivingSurveyTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getReportsCommonPage().verifyDrivingSurveysTable(downloadPath, getWorkingReportsDataRow().title);
	}

	/**
	 * Executes verifySSRSImagesWithBaselines action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifySSRSImagesWithBaselines(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifySSRSImagesWithBaselines", data, dataRowID);
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getReportsCommonPage().verifySSRSImages(downloadPath, reportsDataRow.title,
				reportsDataRow.tCID);
	}

	/**
	 * Executes verifySSRSLayersTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifySSRSLayersTableInfo(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifySSRSLayersTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getReportsCommonPage().verifyLayersTable(downloadPath, getWorkingReportsDataRow().title,
				getWorkingReportsEntity().getTablesList().get(0));
	}

	/**
	 * Executes verifySSRSShowCoverageTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifySSRSShowCoverageTableInfo(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifySSRSShowCoverageTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getReportsCommonPage().verifyShowCoverageTable(downloadPath, getWorkingReportsDataRow().title);
	}

	/**
	 * Executes verifySSRSViewsTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifySSRSViewsTableInfo(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifySSRSViewsTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getReportsCommonPage().verifyViewsTable(downloadPath, getWorkingReportsDataRow().title,
				getWorkingReportsEntity().getViewList());
	}

	/**
	 * Executes verifyViewsInSSRSPDFAreInCorrectSequence action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyViewsInSSRSPDFAreInCorrectSequence(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyViewsInSSRSPDFAreInCorrectSequence", data, dataRowID);
		List<String> expectedViewNamesList = getViewNamesList(dataRowID);
		return this.getReportsCommonPage().verifyViewsInSSRSPDFAreInCorrectSequence(
				expectedViewNamesList, getWorkingReportsDataRow().title);
	}

	/**
	 * Executes verifyViewsImagesWithBaselines action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyViewsImagesWithBaselines(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyViewsImagesWithBaselines", data, dataRowID);
		boolean retVal = true;
		boolean inZipFolder = data.equalsIgnoreCase("false")?false:true;
		ReportsCommonDataRow reportsDataRow = getReportsCommonDataRow(dataRowID);

		// for each view in the test case verify that the view image is present.
		List<Integer> viewRowIDs = ActionArguments.getNumericList(reportsDataRow.reportViewRowIDs);
		for (int i=0; i<viewRowIDs.size(); i++) {
			String viewName = workingReportViewsDataRows.get().get(i).name;
			retVal = retVal && this.getReportsCommonPage().verifyViewsImages(TestContext.INSTANCE.getTestSetup().getDownloadPath(),
					reportsDataRow.title, reportsDataRow.tCID, viewName, inZipFolder);
		}
		return retVal;
	}

	/**
	 * Executes verifyComplianceViewerDialogIsClosed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyComplianceViewerDialogIsClosed(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifyComplianceViewerDialogIsClosed", data, dataRowID);
		this.getReportsCommonPage().waitForReportViewerDialogToClose();
		return true;
	}

	/**
	 * Executes verifyCustomerSpecificAssetsAreDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyCustomerSpecificAssetsAreDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyCustomerSpecificAssetsAreDisplayed", data, dataRowID);
		boolean foundAtleastOne = false;
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		ReportOptViewLayersAssetsDataReader viewLayersAssetsDataReader = new ReportOptViewLayersAssetsDataReader(this.excelUtility);
		Integer customerRowID = Integer.valueOf(reportsDataRow.customerRowID);
		// loop through all the assets and check for presence of checkbox for each asset for the customer.
		for (int idx = 1; idx < viewLayersAssetsDataReader.getRowCount(); idx++) {
			ReportOptViewLayersAssetsDataRow viewLayersAssetsDataRow = viewLayersAssetsDataReader.getDataRow(idx);
			if (customerRowID == Integer.valueOf(viewLayersAssetsDataRow.customerRowID)) {
				foundAtleastOne = true;
				WebElement assetElement = this.getReportsCommonPage().getViewLayerAssetCheckbox(viewLayersAssetsDataRow.assetID);
				if (assetElement == null) {
					return false;
				}
			}
		}
		return foundAtleastOne;
	}

	/**
	 * Executes verifyCustomerSpecificBoundariesAreDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyCustomerSpecificBoundariesAreDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyCustomerSpecificBoundariesAreDisplayed", data, dataRowID);
		boolean foundAtleastOne = false;
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		ReportOptViewLayersBoundaryDataReader viewLayersBoundaryDataReader = new ReportOptViewLayersBoundaryDataReader(this.excelUtility);
		Integer customerRowID = Integer.valueOf(reportsDataRow.customerRowID);
		// loop through all the boundaries and check for presence of checkbox for each boundary for the customer.
		for (int idx = 1; idx < viewLayersBoundaryDataReader.getRowCount(); idx++) {
			ReportOptViewLayersBoundaryDataRow viewLayersBoundaryDataRow = viewLayersBoundaryDataReader.getDataRow(idx);
			if (customerRowID == Integer.valueOf(viewLayersBoundaryDataRow.customerRowID)) {
				foundAtleastOne = true;
				WebElement boundaryElement = this.getReportsCommonPage().getViewLayerBoundaryCheckbox(viewLayersBoundaryDataRow.boundaryName);
				if (boundaryElement == null) {
					return false;
				}
			}
		}
		return foundAtleastOne;
	}

	/**
	 * Executes verifyErrorMessages action.
	 * @param data - vertical bar (|) seperated list of error messages. For eg. 'error_msg1|error_msg2'.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyErrorMessages(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyErrorMessages", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyErrorMessages", ARG_DATA, data);
		List<String> errorMessages = RegexUtility.split(data, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
		return this.getReportsCommonPage().verifyErrorMessages(errorMessages.toArray(new String[errorMessages.size()]));
	}

	/**
	 * Executes verifyGeographicFilterIsSelected action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGeographicFilterIsSelected(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifyGeographicFilterIsSelected", data, dataRowID);
		return this.getReportsCommonPage().isSurveyGeoFilterSelected();
	}

	/**
	 * Executes verifyIndicationsTableIsEmpty action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyIndicationsTableIsEmpty(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyIndicationsTableIsEmpty", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		List<String> expectedStrings = new ArrayList<String>();
		expectedStrings.add(Resources.getResource(ResourceKeys.ReportSSRS_NoLisaRecordMsg));
		return this.getReportsCommonPage().verifySSRSPDFContainsText(downloadPath, getWorkingReportsDataRow().title, expectedStrings);
	}

	/**
	 * Executes verifyIsotopicTableIsEmpty action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyIsotopicTableIsEmpty(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyIsotopicTableIsEmpty", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		List<String> expectedStrings = new ArrayList<String>();
		expectedStrings.add(Resources.getResource(ResourceKeys.ComplianceReportSSRS_NoIsotopicMsg));
		return this.getReportsCommonPage().verifySSRSPDFContainsText(downloadPath, getWorkingReportsDataRow().title, expectedStrings);
	}

	/**
	 * Executes verifyPageLoaded action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPageLoaded(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifyPageLoaded", data, dataRowID);
		this.getReportsCommonPage().waitForPageLoad();
		return true;
	}

	/**
	 * Executes verifyNewPageLoaded action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyNewPageLoaded(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifyNewPageLoaded", data, dataRowID);
		this.getReportsCommonPage().waitForNewPageLoad();
		return true;
	}

	/**
	 * Executes verifyReportFilesArePresent action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyReportFilesArePresent(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyReportFilesArePresent", data, dataRowID);
		ReportsBaseDataRow reportsDataRow = this.getReportsDataRow(dataRowID);
		boolean successed = this.getReportsCommonPage().checkActionStatus(reportsDataRow.title,
				LoginPageActions.workingDataRow.get().username, reportsDataRow.tCID);
		if(successed){
			 this.getReportsCommonPage().clickOnReportViewerCloseButton();
		}
		return successed;
	}

	/**
	 * Executes verifyReportGenerationIsCancelled action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyReportGenerationIsCancelled(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifyReportGenerationIsCancelled", data, dataRowID);
		return this.getReportsCommonPage().checkButtonOnReportsPageAndClick(getWorkingReportsDataRow().title,
				LoginPageActions.workingDataRow.get().username, ReportsButtonType.ReportErrorLabel, false /*clickButton*/, false /*confirmAction*/);
	}

	/**
	 * Executes verifySSRSPDFFooter action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifySSRSPDFFooter(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifySSRSPDFFooter", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		String expectedSoftwareVersion = TestContext.INSTANCE.getTestSetup().getCIEnvironmentBuildNumber();
		return this.getReportsCommonPage().verifySSRSPDFFooter(downloadPath,
				getWorkingReportsDataRow().title, expectedSoftwareVersion , LoginPageActions.workingDataRow.get().username);
	}

	/**
	 * Executes verifySelectedSurveysAreForSpecifiedCustomer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifySearchedSurveysAreForSpecifiedCustomer(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifySelectedSurveysAreForSpecifiedCustomer", data, dataRowID);
		List<String> surveyUsernames = this.getReportsCommonPage().getSelectedSurveyTableValuesForColumn(ReportsSurveyInfo.ColumnHeaders.User);
		List<String> distinctUsernames = ArrayUtility.getDistinctValues(surveyUsernames);
		// for each distinct user check user belongs to the specified customer.
		Integer customerRowID = Integer.valueOf(getReportsDataRow(dataRowID).customerRowID);
		CustomerDataReader customerDataReader = new CustomerDataReader(excelUtility);
		CustomerDataRow customerDataRow = customerDataReader.getDataRow(customerRowID);
		Customer customer = Customer.getCustomer(customerDataRow.name);
		String customerID = customer.getId();

		for (String username : distinctUsernames) {
			User user = User.getUser(username);
			if (!customerID.equalsIgnoreCase(user.getCustomerId())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Executes verifySearchedSurveysAreForSelectedArea action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysAreForSelectedArea(String data, Integer dataRowID) {
		logAction("ReportsCommonPageActions.verifySearchedSurveysAreForSelectedArea", data, dataRowID);
		// TODO: Add implementation.
		return false;
	}

	/**
	 * Executes verifySurveyGreaterThan100HoursCannotBeAdded action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifySurveyGreaterThan100HoursCannotBeAdded(String data, Integer dataRowID) throws Exception {
		logAction("ReportsCommonPageActions.verifySurveyGreaterThan100HoursCannotBeAdded", data, dataRowID);
		getReportsCommonPage().selectSurveysAndAddToReport(false, 1, false);
		return getReportsCommonPage().isMaxSurveyDurationReached();
	}

	/**
	/* END - Actions on the Page*/

	public List<Float> getMinAmplitudesForSurveys(Integer reportDataRowID) throws Exception {
		List<Float> minAmps = new ArrayList<Float>();
		ReportsCommonDataReader reportDataReader = (ReportsCommonDataReader) getDataReader();
		ReportsCommonDataRow reportsDataRow = reportDataReader.getDataRow(reportDataRowID);
		List<Integer> surveyRowIDs = ActionArguments.getNumericList(reportsDataRow.reportSurveyRowIDs);
		for (Integer surveyRowID : surveyRowIDs) {
			Float minAmp = 0.0F;
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(surveyRowID);
			AnalyzerDataReader analyzerDataReader = new AnalyzerDataReader(excelUtility);
			AnalyzerDataRow analyzerDataRow = analyzerDataReader.getDataRow(NumberUtility.getIntegerValueOf(surveyDataRow.analyzerRowID));
			LocationDataReader locationDataReader = new LocationDataReader(excelUtility);
			LocationDataRow locationDataRow = locationDataReader.getDataRow(NumberUtility.getIntegerValueOf(analyzerDataRow.locationRowID));
			if (surveyDataRow.surveyModeFilter.equals("Standard")) {
				minAmp = NumberUtility.getFloatValueOf(locationDataRow.standardMinAmplitude);
			} else if (surveyDataRow.surveyModeFilter.equals("Operator")) {
				minAmp = NumberUtility.getFloatValueOf(locationDataRow.operatorMinAmplitude);
			} else if (surveyDataRow.surveyModeFilter.equals("Rapid Response")) {
				minAmp = NumberUtility.getFloatValueOf(locationDataRow.rapidResponseMinAmplitude);
			} else if (surveyDataRow.surveyModeFilter.equals("Assessment")) {
				minAmp = NumberUtility.getFloatValueOf(locationDataRow.assessmentMinAmplitude);
			} else if (surveyDataRow.surveyModeFilter.equals("EQ")) {
				minAmp = NumberUtility.getFloatValueOf(locationDataRow.eQMinAmplitude);
			}

			minAmps.add(minAmp);
		}
		return minAmps;
	}

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("addDefaultView")) { return this.addDefaultView(data, dataRowID); }
		else if (actionName.equals("addNewView")) { return this.addNewView(data, dataRowID); }
		else if (actionName.equals("addSurveysToReport")) { return this.addSurveysToReport(data, dataRowID); }
		else if (actionName.equals("cancelInProgressReport")) { return this.cancelInProgressReport(data, dataRowID); }
		else if (actionName.equals("checkSurveySelectorGeographicFilter")) { return this.checkSurveySelectorGeographicFilter(data, dataRowID); }
		else if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("clickNoOnChangeReportDialog")) { return this.clickNoOnChangeReportDialog(data, dataRowID); }
		else if (actionName.equals("clickOnCancelButton")) { return this.clickOnCancelButton(data, dataRowID); }
		else if (actionName.equals("clickOnCancelConfirmDeleteReport")) { return this.clickOnCancelConfirmDeleteReport(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerButton")) { return this.clickOnComplianceViewerButton(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerCloseButton")) { return this.clickOnComplianceViewerCloseButton(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerMetaZIP")) { return this.clickOnComplianceViewerMetaZIP(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerPDF")) { return this.clickOnComplianceViewerPDF(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerPDFZIP")) { return this.clickOnComplianceViewerPDFZIP(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerShapeZIP")) { return this.clickOnComplianceViewerShapeZIP(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerViewByIndex")) { return this.clickOnComplianceViewerViewByIndex(data, dataRowID); }
		else if (actionName.equals("clickOnCloseReportViewer")) { return this.clickOnCloseReportViewer(data, dataRowID); }
		else if (actionName.equals("clickOnConfirmDeleteReport")) { return this.clickOnConfirmDeleteReport(data, dataRowID); }
		else if (actionName.equals("clickOnCopyButton")) { return this.clickOnCopyButton(data, dataRowID); }
		else if (actionName.equals("clickOnDeleteButton")) { return this.clickOnDeleteButton(data, dataRowID); }
		else if (actionName.equals("clickOnFirstCopyComplianceButton")) { return this.clickOnFirstCopyComplianceButton(data, dataRowID); }
		else if (actionName.equals("clickOnLatLongSelectorButton")) { return this.clickOnLatLongSelectorButton(data, dataRowID); }
		else if (actionName.equals("clickOnOKButton")) { return this.clickOnOKButton(data, dataRowID); }
		else if (actionName.equals("clickOnNewReportButton")) { return this.clickOnNewReportButton(data, dataRowID); }
		else if (actionName.equals("clickOnResubmitButton")) { return this.clickOnResubmitButton(data, dataRowID); }
		else if (actionName.equals("clickOnSurveySelectorSearchButton")) { return this.clickOnSurveySelectorSearchButton(data, dataRowID); }
		else if (actionName.equals("copyReport")) { return this.copyReport(data, dataRowID); }
		else if (actionName.equals("createNewReport")) { return this.createNewReport(data, dataRowID); }
		else if (actionName.equals("deleteReport")) { return this.deleteReport(data, dataRowID); }
		else if (actionName.equals("enterCustomBoundaryUsingAreaSelector")) { return this.enterCustomBoundaryUsingAreaSelector(data, dataRowID); }
		else if (actionName.equals("enterCustomBoundaryUsingTextFields")) { return this.enterCustomBoundaryUsingTextFields(data, dataRowID); }
		else if (actionName.equals("enterCustomerBoundaryUsingAreaSelector")) { return this.enterCustomerBoundaryUsingAreaSelector(data, dataRowID); }
		else if (actionName.equals("enterExclusionRadius")) { return this.enterExclusionRadius(data, dataRowID); }
		else if (actionName.equals("enterFOVOpacity")) { return this.enterFOVOpacity(data, dataRowID); }
		else if (actionName.equals("enterPDFImageHeight")) { return this.enterPDFImageHeight(data, dataRowID); }
		else if (actionName.equals("enterPDFImageWidth")) { return this.enterPDFImageWidth(data, dataRowID); }
		else if (actionName.equals("enterReportTitle")) { return this.enterReportTitle(data, dataRowID); }
		else if (actionName.equals("enterSurveySelectorTag")) { return this.enterSurveySelectorTag(data, dataRowID); }
		else if (actionName.equals("enterSurveySelectorUsername")) { return this.enterSurveySelectorUsername(data, dataRowID); }
		else if (actionName.equals("extractMetaZIP")) { return this.extractMetaZIP(data, dataRowID); }
		else if (actionName.equals("extractPDFZIP")) { return this.extractPDFZIP(data, dataRowID); }
		else if (actionName.equals("extractShapeZIP")) { return this.extractShapeZIP(data, dataRowID); }
		else if (actionName.equals("findReport")) { return this.findReport(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("modifyReport")) { return this.modifyReport(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("openComplianceViewerDialog")) { return this.openComplianceViewerDialog(data, dataRowID); }
		else if (actionName.equals("openNewReportPage")) { return this.openNewReportPage(data, dataRowID); }
		else if (actionName.equals("searchAndDeleteReport")) { return this.searchAndDeleteReport(data, dataRowID); }
		else if (actionName.equals("searchForSurveyByKeyword")) { return this.searchForSurveyByKeyword(data, dataRowID); }
		else if (actionName.equals("selectCustomer")) { return this.selectCustomer(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectIndicationTableCheckbox")) { return this.selectIndicationTableCheckbox(data, dataRowID); }
		else if (actionName.equals("selectIsotopicTableCheckbox")) { return this.selectIsotopicTableCheckbox(data, dataRowID); }
		else if (actionName.equals("selectPaginationRows")) { return this.selectPaginationRows(data, dataRowID); }
		else if (actionName.equals("selectPercentCoverageReportAreaCheckBox")) { return this.selectPercentCoverageReportAreaCheckBox(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("selectShowPercentCoverageOfAssetsCheckBox")) { return this.selectShowPercentCoverageOfAssetsCheckBox(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorEndDateTime")) { return this.selectSurveySelectorEndDateTime(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorStartDateTime")) { return this.selectSurveySelectorStartDateTime(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorSurveyModeFilter")) { return this.selectSurveySelectorSurveyModeFilter(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorSurveyor")) { return this.selectSurveySelectorSurveyor(data, dataRowID); }
		else if (actionName.equals("selectTabularPDFContent")) { return this.selectTabularPDFContent(data, dataRowID); }
		else if (actionName.equals("selectTimeZone")) { return this.selectTimeZone(data, dataRowID); }
		else if (actionName.equals("selectViewLayersAsset")) { return this.selectViewLayersAsset(data, dataRowID); }
		else if (actionName.equals("selectViewLayersBoundary")) { return this.selectViewLayersBoundary(data, dataRowID); }
		else if (actionName.equals("setReportGenerationTimeout")) { return this.setReportGenerationTimeout(data, dataRowID); }
		else if (actionName.equals("sortRecordsBy")) { return this.sortRecordsBy(data, dataRowID); }
		else if (actionName.equals("verifyAllMetadataFiles")) { return this.verifyAllMetadataFiles(data, dataRowID); }
		else if (actionName.equals("verifyAllSSRSTableInfos")) { return this.verifyAllSSRSTableInfos(data, dataRowID); }
		else if (actionName.equals("verifyAssetsAreDisplayed")) { return this.verifyAssetsAreDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyBoundariesAutoCompleteListContains")) { return this.verifyBoundariesAutoCompleteListContains(data, dataRowID); }
		else if (actionName.equals("verifyComplianceViewerButtonIsDisplayed")) { return this.verifyComplianceViewerButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyComplianceViewerViewCountEquals")) { return this.verifyComplianceViewerViewCountEquals(data, dataRowID); }
		else if (actionName.equals("verifyCopyButtonIsDisplayed")) { return this.verifyCopyButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyDeleteButtonIsDisplayed")) { return this.verifyDeleteButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyGapsTableInfo")) { return this.verifyGapsTableInfo(data, dataRowID); }
		else if (actionName.equals("verifyInvestigateButtonIsDisplayed")) { return this.verifyInvestigateButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyInvestigatePDFButtonIsDisplayed")) { return this.verifyInvestigatePDFButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicAnalysisTableInfo")) { return this.verifyIsotopicAnalysisTableInfo(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicMetaDataFile")) { return this.verifyIsotopicMetaDataFile(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicTableSortedAscByColumn")) { return this.verifyIsotopicTableSortedAscByColumn(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicTableSortedDescByColumn")) { return this.verifyIsotopicTableSortedDescByColumn(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicValueIsFormattedCorrectly")) { return this.verifyIsotopicValueIsFormattedCorrectly(data, dataRowID); }
		else if (actionName.equals("verifyLastXDaysSurveysPresentInPDF")) { return this.verifyLastXDaysSurveysPresentInPDF(data, dataRowID); }
		else if (actionName.equals("verifyLISASMetaDataFile")) { return this.verifyLISASMetaDataFile(data, dataRowID); }
		else if (actionName.equals("verifyLISAsIndicationTableInfo")) { return this.verifyLISAsIndicationTableInfo(data, dataRowID); }
		else if (actionName.equals("verifyLISAsIndicationTableMinAmplitudeValues")) { return this.verifyLISAsIndicationTableMinAmplitudeValues(data, dataRowID); }
		else if (actionName.equals("verifyLISAsIndicationTableRowCountEquals")) { return this.verifyLISAsIndicationTableRowCountEquals(data, dataRowID); }
		else if (actionName.equals("verifyLISAsIndicationTableSortedAscByColumn")) { return this.verifyLISAsIndicationTableSortedAscByColumn(data, dataRowID); }
		else if (actionName.equals("verifyLISAsIndicationTableSortedDescByColumn")) { return this.verifyLISAsIndicationTableSortedDescByColumn(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataFilesHaveCorrectData")) { return this.verifyMetaDataFilesHaveCorrectData(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataZIPFilesAreCorrect")) { return this.verifyMetaDataZIPFilesAreCorrect(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataZIPThumbnailDownloadFromComplianceViewer")) { return this.verifyMetaDataZIPThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataZIPThumbnailIsShownInComplianceViewer")) { return this.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPaginationAndSortingOnAllColumns")) { return this.verifyPaginationAndSortingOnAllColumns(data, dataRowID); }
		else if (actionName.equals("verifyPDFContainsInputtedInformation")) { return this.verifyPDFContainsInputtedInformation(data, dataRowID); }
		else if (actionName.equals("verifyPDFThumbnailDownloadFromComplianceViewer")) { return this.verifyPDFThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPDFThumbnailIsShownInComplianceViewer")) { return this.verifyPDFThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPDFZipFilesAreCorrect")) { return this.verifyPDFZipFilesAreCorrect(data, dataRowID); }
		else if (actionName.equals("verifyPDFZipFilesArePresent")) { return this.verifyPDFZipFilesArePresent(data, dataRowID); }
		else if (actionName.equals("verifyPDFZIPThumbnailDownloadFromComplianceViewer")) { return this.verifyPDFZIPThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPDFZIPThumbnailIsShownInComplianceViewer")) { return this.verifyPDFZIPThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPercentCoverageAssetsAndReportAreaValuesInReport")) { return this.verifyPercentCoverageAssetsAndReportAreaValuesInReport(data, dataRowID); }
		else if (actionName.equals("verifyPercentCoverageForecastPresentInReport")) { return this.verifyPercentCoverageForecastPresentInReport(data, dataRowID); }
		else if (actionName.equals("verifyReportDeletedSuccessfully")) { return this.verifyReportDeletedSuccessfully(data, dataRowID); }
		else if (actionName.equals("verifyReportJobBaselines")) { return this.verifyReportJobBaselines(data, dataRowID); }
		else if (actionName.equals("verifyReportPageFieldsAreCorrect")) { return this.verifyReportPageFieldsAreCorrect(data, dataRowID); }
		else if (actionName.equals("verifyReportPDFMatches")) { return this.verifyReportPDFMatches(data, dataRowID); }
		else if (actionName.equals("verifyReportSurveyMetadataFile")) { return this.verifyReportSurveyMetadataFile(data, dataRowID); }
		else if (actionName.equals("verifyReportThumbnailMatches")) { return this.verifyReportThumbnailMatches(data, dataRowID); }
		else if (actionName.equals("verifyRequiredFieldsAreShownInRed")) { return this.verifyRequiredFieldsAreShownInRed(data, dataRowID); }
		else if (actionName.equals("verifyResubmitButtonIsDisplayed")) { return this.verifyResubmitButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveyIsShown")) { return this.verifySearchedSurveyIsShown(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysAreForLastXDays")) { return this.verifySearchedSurveysAreForLastXDays(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysMatchDateRange")) { return this.verifySearchedSurveysMatchDateRange(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysMatchSurveyorUnit")) { return this.verifySearchedSurveysMatchSurveyorUnit(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysMatchTag")) { return this.verifySearchedSurveysMatchTag(data, dataRowID); }
		else if (actionName.equals("verifyShapeFilesWithBaselines")) { return this.verifyShapeFilesWithBaselines(data, dataRowID); }
		else if (actionName.equals("verifyShapeZIPFilesAreCorrect")) { return this.verifyShapeZIPFilesAreCorrect(data, dataRowID); }
		else if (actionName.equals("verifyShapeZIPThumbnailDownloadFromComplianceViewer")) { return this.verifyShapeZIPThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyShapeZIPThumbnailIsShownInComplianceViewer")) { return this.verifyShapeZIPThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifySSRSCoverageTableInfo")) { return this.verifySSRSCoverageTableInfo(data, dataRowID); }
		else if (actionName.equals("verifySSRSDrivingSurveyTableInfo")) { return this.verifySSRSDrivingSurveyTableInfo(data, dataRowID); }
		else if (actionName.equals("verifySSRSImagesWithBaselines")) { return this.verifySSRSImagesWithBaselines(data, dataRowID); }
		else if (actionName.equals("verifySSRSLayersTableInfo")) { return this.verifySSRSLayersTableInfo(data, dataRowID); }
		else if (actionName.equals("verifySSRSShowCoverageTableInfo")) { return this.verifySSRSShowCoverageTableInfo(data, dataRowID); }
		else if (actionName.equals("verifySSRSViewsTableInfo")) { return this.verifySSRSViewsTableInfo(data, dataRowID); }
		else if (actionName.equals("verifyUncertaintyValueIsFormattedCorrectly")) { return this.verifyUncertaintyValueIsFormattedCorrectly(data, dataRowID); }
		else if (actionName.equals("verifyViewThumbnailDownloadFromComplianceViewerByViewIndex")) { return this.verifyViewThumbnailDownloadFromComplianceViewerByViewIndex(data, dataRowID); }
		else if (actionName.equals("verifyViewThumbnailIsShownInComplianceViewerByViewIndex")) { return this.verifyViewThumbnailIsShownInComplianceViewerByViewIndex(data, dataRowID); }
		else if (actionName.equals("verifyViewsCreatedAreInCorrectSequence")) { return this.verifyViewsInSSRSPDFAreInCorrectSequence(data, dataRowID); }
		else if (actionName.equals("verifyViewsImagesWithBaselines")) { return this.verifyViewsImagesWithBaselines(data, dataRowID); }
		else if (actionName.equals("verifyWarningMessageOnDeleteButtonClickEquals")) { return this.verifyWarningMessageOnDeleteButtonClickEquals(data, dataRowID); }
		else if (actionName.equals("verifyComplianceViewerDialogIsClosed")) { return this.verifyComplianceViewerDialogIsClosed(data, dataRowID); }
		else if (actionName.equals("verifyCustomerSpecificAssetsAreDisplayed")) { return this.verifyCustomerSpecificAssetsAreDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyCustomerSpecificBoundariesAreDisplayed")) { return this.verifyCustomerSpecificBoundariesAreDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyErrorMessages")) { return this.verifyErrorMessages(data, dataRowID); }
		else if (actionName.equals("verifyGeographicFilterIsSelected")) { return this.verifyGeographicFilterIsSelected(data, dataRowID); }
		else if (actionName.equals("verifyIndicationsTableIsEmpty")) { return this.verifyIndicationsTableIsEmpty(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicTableIsEmpty")) { return this.verifyIsotopicTableIsEmpty(data, dataRowID); }
		else if (actionName.equals("verifyPageLoaded")) { return this.verifyPageLoaded(data, dataRowID); }
		else if (actionName.equals("verifyNewPageLoaded")) { return this.verifyNewPageLoaded(data, dataRowID); }
		else if (actionName.equals("verifyReportCreationInSSRSPDFIsCorrect")) { return this.verifyReportCreationInSSRSPDFIsCorrect(data, dataRowID); }
		else if (actionName.equals("verifyReportFilesArePresent")) { return this.verifyReportFilesArePresent(data, dataRowID); }
		else if (actionName.equals("verifyReportGenerationIsCancelled")) { return this.verifyReportGenerationIsCancelled(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysAreForSelectedArea")) { return this.verifySearchedSurveysAreForSelectedArea(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysAreForSpecifiedCustomer")) { return this.verifySearchedSurveysAreForSpecifiedCustomer(data, dataRowID); }
		else if (actionName.equals("verifySSRSPDFFooter")) { return this.verifySSRSPDFFooter(data, dataRowID); }
		else if (actionName.equals("verifySurveyGreaterThan100HoursCannotBeAdded")) { return this.verifySurveyGreaterThan100HoursCannotBeAdded(data, dataRowID); }
		else if (actionName.equals("waitForComplianceViewerDialogToClose")) { return this.waitForComplianceViewerDialogToClose(data, dataRowID); }
		else if (actionName.equals("waitForComplianceViewerDialogToOpen")) { return this.waitForComplianceViewerDialogToOpen(data, dataRowID); }
		else if (actionName.equals("waitForMetaZIPDownloadToComplete")) { return this.waitForMetaZIPDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForPDFDownloadToComplete")) { return this.waitForPDFDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForPDFZIPDownloadToComplete")) { return this.waitForPDFZIPDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForReportGenerationToComplete")) { return this.waitForReportGenerationToComplete(data, dataRowID); }
		else if (actionName.equals("waitForShapeZIPDownloadToComplete")) { return this.waitForShapeZIPDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForViewDownloadToCompleteByViewIndex")) { return this.waitForViewDownloadToCompleteByViewIndex(data, dataRowID); }
		else if (actionName.equals("copyInProgressReport")) { return this.copyInProgressReport(data, dataRowID); }
		else if (actionName.equals("verifyShapeFilesWithBaselines")) { return this.verifyShapeFilesWithBaselines(data, dataRowID); }
		else if (actionName.equals("verifyCancelButtonIsDisplayed")) { return this.verifyCancelButtonIsDisplayed(data, dataRowID); }
		return false;
	}

	public ReportsCommonPage getReportsCommonPage() {
		return (ReportsCommonPage)this.getPageObject();
	}

	/* Methods to be implemented by derived class. */

	protected ReportCommonEntity createNewReportsEntity() throws Exception {
		throw new Exception("This method to be implemented by derived class.");
	}
	
	protected ReportCommonEntity createNewReportsEntity(String rptTitle, String customer, String timeZone, String exclusionRadius,
			List<String> listBoundary, List<Map<String, String>> viewList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewLayersList) throws Exception {
		throw new Exception("This method to be implemented by derived class.");
	}

	protected void fillReportSpecificWorkingDataForReports(ReportCommonEntity reportEntity) throws Exception {
        throw new Exception("This method to be implemented by derived class.");
	}

	protected boolean verifyReportsSpecificPageFieldsAreCorrect(ReportsCommonDataRow dataRow) throws Exception {
		throw new Exception("To be implemented by derived class.");
	}

	protected void selectReportSpecificTabularPDFContent(ReportOptTabularPDFContentDataRow pdfContentDataRow) throws Exception {
		throw new Exception("To be implemented by derived class.");
	}

	protected void waitForReportSpecificFileDownload(Integer dataRowID, ReportFileType fileType, Integer fileIndex, int zipIndex) throws Exception {
		throw new Exception("This method to be implemented by derived class");
	}

	public ReportsCommonPage createNewPageObject() throws Exception {
		throw new Exception("This method to be implemented by derived class");
	}

	protected void addView(Integer dataRowID) throws Exception {
		throw new Exception("This method should be implemented by specific class.");
	}

	public ReportCommonEntity getWorkingReportsEntity() throws Exception {
		throw new Exception("This method should be implemented by specific class.");
	}

	public void setWorkingReportsEntity(ReportCommonEntity reportsEntity) throws Exception {
		throw new Exception("This method should be implemented by specific class.");
	}

	private boolean verifySSRSTableInfos(String downloadPath) throws Exception {
		boolean retSuccess = true;
		boolean verifyCoverageValuesTable = false;
		boolean verifyShowCoverageTable = false;
		boolean verifyIsotopicAnalysisTable = false;
		boolean verifyIndicationTable = false;
		boolean verifyLayersTable = false;
		boolean verifyViewsTable = false;
		boolean verifyDrivingSurveysTable = false;

		ReportOptTabularPDFContentDataRow optTabularPDFContentDataRow = getOptionalTabularPdfDataRow();
		boolean pca = Boolean.valueOf(optTabularPDFContentDataRow.percentCoverageAssets);
		boolean pcra = Boolean.valueOf(optTabularPDFContentDataRow.percentCoverageReportArea);
		boolean isoAnalysis = Boolean.valueOf(optTabularPDFContentDataRow.isotopicAnalysis);
		boolean indTable = Boolean.valueOf(optTabularPDFContentDataRow.indicationTable);

		if (pca && pcra) {
			Log.info("Executing verifyCoverageValuesTable()...");
			verifyCoverageValuesTable = this.getReportsCommonPage().verifyCoverageValuesTable(downloadPath, getWorkingReportsDataRow().title,
					getWorkingReportsEntity().getTablesList().get(0));
			Log.info(String.format("verifyCoverageValuesTable() returned - '%b'", verifyCoverageValuesTable));
			retSuccess = retSuccess && verifyCoverageValuesTable;

			Log.info("Executing verifyShowCoverageTable()...");
			verifyShowCoverageTable = this.getReportsCommonPage().verifyShowCoverageTable(downloadPath, getWorkingReportsDataRow().title);
			Log.info(String.format("verifyShowCoverageTable() returned - '%b'", verifyShowCoverageTable));
			retSuccess = retSuccess && verifyShowCoverageTable;
		}

		if (isoAnalysis) {
			Log.info("Executing verifyIsotopicAnalysisTable()...");
			verifyIsotopicAnalysisTable = this.getReportsCommonPage().verifyIsotopicAnalysisTable(downloadPath, getWorkingReportsDataRow().title);
			Log.info(String.format("verifyIsotopicAnalysisTable() returned - '%b'", verifyIsotopicAnalysisTable));
			retSuccess = retSuccess && verifyIsotopicAnalysisTable;
		}

		if (indTable) {
			Log.info("Executing verifyIndicationTable()...");
			verifyIndicationTable = this.getReportsCommonPage().verifyIndicationTable(downloadPath, getWorkingReportsDataRow().title);
			Log.info(String.format("verifyIndicationTable() returned - '%b'", verifyIndicationTable));
			retSuccess = retSuccess && verifyIndicationTable;
		}

		List<String> customersWithAssets = SurveyorConstants.getCustomersWithAssets();
		CustomerDataRow customerDataRow = getCustomerDataRow();
		if (customersWithAssets.contains(customerDataRow.name)) {
			Log.info("Executing verifyLayersTable()...");
			verifyLayersTable = this.getReportsCommonPage().verifyLayersTable(downloadPath, getWorkingReportsDataRow().title, getWorkingReportsEntity().getTablesList().get(0));
			Log.info(String.format("verifyLayersTable() returned - '%b'", verifyLayersTable));
			retSuccess = retSuccess && verifyLayersTable;
		}

		Log.info("Executing verifyViewsTable()...");
		verifyViewsTable = this.getReportsCommonPage().verifyViewsTable(downloadPath, getWorkingReportsDataRow().title, getWorkingReportsEntity().getViewList());
		retSuccess = retSuccess && verifyViewsTable;
		Log.info(String.format("verifyViewsTable() returned - '%b'", verifyLayersTable));

		Log.info("Executing verifyDrivingSurveysTable()...");
		verifyDrivingSurveysTable = this.getReportsCommonPage().verifyDrivingSurveysTable(downloadPath, getWorkingReportsDataRow().title);
		retSuccess = retSuccess && verifyDrivingSurveysTable;
		Log.info(String.format("verifyDrivingSurveysTable() returned - '%b'", verifyLayersTable));

		Log.info(String.format("verifyCoverageValuesTable = %b; verifyShowCoverageTable = %b; verifyIsotopicAnalysisTable = %b; "
				+ "verifyIndicationTable = %b; verifyLayersTable = %b; verifyViewsTable = %b; verifyDrivingSurveysTable = %b",
				verifyCoverageValuesTable, verifyShowCoverageTable, verifyIsotopicAnalysisTable, verifyIndicationTable,
				verifyLayersTable, verifyViewsTable, verifyDrivingSurveysTable));

		return retSuccess;
	}
	
	private ReportsCommonDataRow getWorkingReportsCommonDataRow() throws Exception{
		return (ReportsCommonDataRow) getWorkingReportsDataRow();
	}
	
	private ReportsCommonDataRow getReportsCommonDataRow(Integer dataRowID) throws Exception{
		return (ReportsCommonDataRow) getReportsDataRow(dataRowID);
	}
}
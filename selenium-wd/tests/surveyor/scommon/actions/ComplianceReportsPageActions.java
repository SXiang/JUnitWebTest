package surveyor.scommon.actions;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.ArrayUtility;
import common.source.BaseHelper;
import common.source.ExcelUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.LogCategory;
import common.source.LogHelper;
import common.source.NumberUtility;
import common.source.PDFTableUtility;
import common.source.PDFTableUtility.PDFTable;
import common.source.PDFUtility;
import common.source.RegexUtility;
import common.source.SortHelper;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.api.source.ReportJob;
import surveyor.api.source.ReportJobsStat;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.ReportCompliance;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.User;
import surveyor.scommon.actions.data.AnalyzerDataReader;
import surveyor.scommon.actions.data.AnalyzerDataReader.AnalyzerDataRow;
import surveyor.scommon.actions.data.ComplianceReportDataReader;
import surveyor.scommon.actions.data.ComplianceReportDataReader.ComplianceReportsDataRow;
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
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.ComplianceReportsPage.ReportFileType;
import surveyor.scommon.source.ComplianceReportsPage.ReportViewerThumbnailType;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.Reports.ReportJobType;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.ReportsCompliance.IsotopicAnalysisTableColumns;
import surveyor.scommon.source.ReportsCompliance.LISAIndicationTableColumns;
import surveyor.scommon.source.SurveyorBasePage.TableSortOrder;
import surveyor.scommon.source.ReportsSurveyInfo;
import surveyor.scommon.source.SurveyorConstants;

public class ComplianceReportsPageActions extends BaseReportsPageActions {
	private static final String FN_WAIT_FOR_VIEW_DOWNLOAD_TO_COMPLETE_BY_VIEW_INDEX = "waitForViewDownloadToCompleteByViewIndex";
	private static final String FN_CLICK_ON_COMPLIANCE_VIEWER_VIEW_BY_INDEX = "clickOnComplianceViewerViewByIndex";

	private ComplianceReportDataReader dataReader = null;
	public static ReportsCompliance workingReportsComp = null;      // Stores the ReportsCompliance object from createNewReport action
	public static ComplianceReportsDataRow workingDataRow = null;    // Stores the workingDataRow from createNewReport action
	public static List<ReportViewsDataRow> workingReportViewsDataRows = null;    // Stores the dataRows for views created in createNewReport action.

	public ComplianceReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
		initializePageObject(driver, new ComplianceReportsPage(driver, strBaseURL, testSetup));
		setDataReader(new ComplianceReportDataReader(this.excelUtility));
	}
	
	private void addView(Integer dataRowID) throws Exception {
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();
		workingReportViewsDataRows = new ArrayList<ReportViewsDataRow>();
		fillViewDetails(viewMap, new ReportViewsDataReader(this.excelUtility), dataRowID);
		viewList.add(viewMap);
		this.getComplianceReportsPage().addViews(workingReportsComp.getCustomer(), viewList);
	}
 
	private boolean areTabularPDFContentSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return isPDFGapSelectionMatch(dataRow) && isPDFIndicationSelectionMatch(dataRow) &&
				isPDFIsotopicAnalysisSelectionMatch(dataRow) && isPDFPercentCoverageAssetsSelectionMatch(dataRow) &&
				isPDFPercentCoverageForecastSelectionMatch(dataRow) && isPDFPercentCoverageReportAreaSelectionMatch(dataRow);
	}

	// TODO: Check assets and boundaries provided to the dataRow.
	private boolean areAssetBoundariesMatch(ReportOptViewLayersDataRow dataRow) {
		return false;
	}

	private boolean clickComplianceViewerViewByIndex(String data, Integer dataRowID) throws Exception {		
		ActionArguments.verifyNotNullOrEmpty(FN_CLICK_ON_COMPLIANCE_VIEWER_VIEW_BY_INDEX, ARG_DATA, data);
		Integer viewIdx = NumberUtility.getIntegerValueOf(data);
		ActionArguments.verifyGreaterThanZero(FN_CLICK_ON_COMPLIANCE_VIEWER_VIEW_BY_INDEX, ARG_DATA, viewIdx);		
		this.getComplianceReportsPage().clickViewThumbnailImageByIndex(viewIdx);
		return true;
	}
	
	private List<ReportsSurveyInfo> buildReportSurveyInfoList(ComplianceReportsDataRow dataRow, ExcelUtility excelUtility) throws Exception {
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

			SurveyModeFilter modeFilter = SurveyModeFilter.All;
			if (surveyDataRow.surveyModeFilter.equalsIgnoreCase("standard")) {
				modeFilter = SurveyModeFilter.Standard;
			} else if (surveyDataRow.surveyModeFilter.equalsIgnoreCase("assessment")) {
				modeFilter = SurveyModeFilter.Assessment;
			} else if (surveyDataRow.surveyModeFilter.equalsIgnoreCase("eq")) {
				modeFilter = SurveyModeFilter.EQ;
			} else if (surveyDataRow.surveyModeFilter.equalsIgnoreCase("manual")) {
				modeFilter = SurveyModeFilter.Manual;
			} else if (surveyDataRow.surveyModeFilter.equalsIgnoreCase("operator")) {
				modeFilter = SurveyModeFilter.Operator;
			} else if (surveyDataRow.surveyModeFilter.equalsIgnoreCase("rapid response")) {
				modeFilter = SurveyModeFilter.RapidResponse;
			} 
			
			reportsSurveyInfoList.add(new ReportsSurveyInfo(
					surveyDataRow.surveySurveyor, surveyDataRow.surveyUsername, surveyDataRow.surveyTag, 
					surveyDataRow.surveyStartDate, surveyDataRow.surveyEndDate, 
					modeFilter, Boolean.valueOf(surveyDataRow.surveyGeoFilterON), 
					NumberUtility.getIntegerValueOf(surveyDataRow.numberofSurveystoInclude), 
					Boolean.valueOf(surveyDataRow.selectAllSurveys)));			
		}
		return reportsSurveyInfoList;
	}
 
	private void clickComplianceReportButton(Integer dataRowID, ComplianceReportButtonType buttonType) throws Exception {		
		ComplianceReportsDataRow compRptDataRow = getComplianceReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		this.getComplianceReportsPage().clickComplianceReportButton(reportTitle, LoginPageActions.workingDataRow.username, buttonType,
				false /*confirmAction*/);  // By default use FALSE confirm action.
	}
	
	private ComplianceReportsDataRow getComplianceReportsDataRow(Integer dataRowID) throws Exception {
		ComplianceReportsDataRow compRptDataRow = null;
		if (ComplianceReportsPageActions.workingDataRow != null) {
			compRptDataRow = ComplianceReportsPageActions.workingDataRow;
		} else {
			compRptDataRow = getDataReader().getDataRow(dataRowID);
		}
		return compRptDataRow;
	}
	
	private ComplianceReportsPage createNewPageObject() {
		ComplianceReportsPage compReportsPage = new ComplianceReportsPage(TestContext.INSTANCE.getDriver(), 
				TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		return compReportsPage;
	}

	private void fillCustomBoundary(List<String> listBoundary, ComplianceReportDataReader reader,
			Integer dataRowID) throws Exception {
		String imgHeight = reader.getDataRow(dataRowID).pDFImageOutputHeight;
		String imgWidth = reader.getDataRow(dataRowID).pDFImageOutputWidth;
		String NELat = reader.getDataRow(dataRowID).customBoundaryNELat;
		String NELong = reader.getDataRow(dataRowID).customBoundaryNELong;
		String SWLat = reader.getDataRow(dataRowID).customBoundarySWLat;
		String SWLong = reader.getDataRow(dataRowID).customBoundarySWLong;
		listBoundary.add(imgHeight);
		listBoundary.add(imgWidth);
		listBoundary.add(NELat);
		listBoundary.add(NELong);
		listBoundary.add(SWLat);
		listBoundary.add(SWLong);
	}

	private void fillViewDetails(Map<String, String> viewMap, ReportViewsDataReader reader,
			Integer dataRowID) throws Exception {
		ReportViewsDataRow reportViewsDataRow = reader.getDataRow(dataRowID);
		workingReportViewsDataRows.add(reportViewsDataRow);
		String viewName = reportViewsDataRow.name;
		String showLISA = reportViewsDataRow.lISAs.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showFOV = reportViewsDataRow.fOV.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showBreadcrumb = reportViewsDataRow.breadcrumbs.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showIndications = reportViewsDataRow.indications.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showIsotopicCapture = reportViewsDataRow.isotopicCapture.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showAnnotation = reportViewsDataRow.fieldNotes.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showGaps = reportViewsDataRow.gaps.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showAssets = reportViewsDataRow.assets.equalsIgnoreCase("TRUE") ? "1" : "0";
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
		if (showBoundaries != "") viewMap.put(KEYBOUNDARIES, showBoundaries);
		viewMap.put(KEYBASEMAP, baseMapType);
	}

	private void fillReportTableInfo(Map<String, String> tableMap, ReportOptTabularPDFContentDataReader reader,
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

	private void fillViewLayersInfo(Map<String, String> viewLayerMap,
			ReportOptViewLayersDataReader reader, Integer dataRowID) throws Exception {
		String argValue = reader.getDataRow(dataRowID).assetRowIDs;
		if (!ActionArguments.isEmpty(argValue)) {
			List<Integer> assetRowIDs = ActionArguments.getNumericList(argValue);
			for (Integer rowID : assetRowIDs) {
				if(rowID==0){
					continue;
				}
				ReportOptViewLayersAssetsDataReader viewLayersAssetsDataReader = getViewLayersAssetsDataReader();
				ReportOptViewLayersAssetsDataRow dataRow = viewLayersAssetsDataReader.getDataRow(rowID);
				viewLayerMap.put(dataRow.assetID, ReportsCompliance.ASSET_PREFIX + dataRow.assetName);
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
				viewLayerMap.put(dataRow.boundaryID, ReportsCompliance.BOUNDARY_PREFIX + dataRow.boundaryName);
			}
		}
	}

	private boolean fillAndCreateNewReport(Integer dataRowID, boolean openNewReportsPage) throws Exception {
		ReportsCompliance rpt = fillWorkingDataForReports(dataRowID);
		getComplianceReportsPage().addNewReport(rpt, openNewReportsPage);
		return true;
	}

	public ReportsCompliance fillWorkingDataForReports(Integer dataRowID) throws Exception {
		workingDataRow = getDataReader().getDataRow(dataRowID);
	
		String rptTitle = workingDataRow.title; 
		String customer = null; 
		String customerRowID = workingDataRow.customerRowID;
		if (customerRowID != "") {
			Integer custRowID = NumberUtility.getIntegerValueOf(customerRowID);
			customer = (new CustomerDataReader(this.excelUtility)).getDataRow(custRowID).name;
		}
		String timeZone = workingDataRow.timezone;
		String exclusionRadius = workingDataRow.exclusionRadius;

		List<String> listBoundary = new ArrayList<String>();
		fillCustomBoundary(listBoundary, getDataReader(), dataRowID);

		// Fill views list.
		workingReportViewsDataRows = new ArrayList<ReportViewsDataRow>();
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		List<Integer> reportViewRowIDs = ActionArguments.getNumericList(workingDataRow.reportViewRowIDs);
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
		List<Integer> reportOptTabPDFRowIDs = ActionArguments.getNumericList(workingDataRow.reportOptTabularPDFContentRowID);
		Map<String, String> tableMap = new HashMap<String, String>();
		fillReportTableInfo(tableMap, new ReportOptTabularPDFContentDataReader(this.excelUtility), reportOptTabPDFRowIDs.get(0));
		tablesList.add(tableMap);

		// Fill optional view layer list.
		List<Map<String, String>> viewLayersList = new ArrayList<Map<String, String>>();
		List<Integer> reportOptVwLayersRowIDs = ActionArguments.getNumericList(workingDataRow.reportOptViewLayerRowID);		
		Map<String, String> viewLayerMap = new HashMap<String, String>();
		fillViewLayersInfo(viewLayerMap, new ReportOptViewLayersDataReader(this.excelUtility), reportOptVwLayersRowIDs.get(0));
		if (viewLayerMap.size() > 0) {
			viewLayersList.add(viewLayerMap);
		} 

		// Set survey info list.
		List<ReportsSurveyInfo> reportsSurveyInfoList = buildReportSurveyInfoList(workingDataRow, this.excelUtility);
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, TestContext.INSTANCE.getLoggedInUser(), customer, timeZone, exclusionRadius,
				listBoundary, tablesList, null /*surveyorUnit*/, null /*tagList*/, viewList, viewLayersList);
		rpt.setSurveyInfoList(reportsSurveyInfoList);
        rpt.setCustomerBoundaryInfo(workingDataRow.customerBoundaryType, workingDataRow.customerBoundaryName);
        
		workingReportsComp = rpt;		// Store the working report properties.
		
		return rpt;
	}

	private ReportOptViewLayersAssetsDataReader getViewLayersAssetsDataReader() {
		return new ReportOptViewLayersAssetsDataReader(this.excelUtility);
	}

	private ReportOptViewLayersBoundaryDataReader getViewLayersBoundaryDataReader() {
		return new ReportOptViewLayersBoundaryDataReader(this.excelUtility);
	}
	
	private CustomerDataRow getCustomerDataRow() throws Exception {
		Integer custRowID = Integer.valueOf(workingDataRow.customerRowID);
		CustomerDataReader customerDataReader = new CustomerDataReader(excelUtility);
		CustomerDataRow customerDataRow = customerDataReader.getDataRow(custRowID);
		return customerDataRow;
	}

	private ReportOptTabularPDFContentDataRow getOptionalTabularPdfDataRow() throws Exception {
		Integer optionaltabPdfRowID = Integer.valueOf(workingDataRow.reportOptTabularPDFContentRowID);
		ReportOptTabularPDFContentDataReader optTabularPDFContentDataReader = new ReportOptTabularPDFContentDataReader(excelUtility);
		ReportOptTabularPDFContentDataRow optTabularPDFContentDataRow = optTabularPDFContentDataReader.getDataRow(optionaltabPdfRowID);
		return optTabularPDFContentDataRow;
	}
	
	private List<String> getViewNamesList(Integer dataRowID) throws Exception {
		List<Integer> viewRowIDs = ActionArguments.getNumericList(workingDataRow.reportViewRowIDs);
		List<String> viewNamesList = new ArrayList<String>();
		ReportViewsDataReader reportViewsDataReader = new ReportViewsDataReader(this.excelUtility);
		for (Integer viewRowID : viewRowIDs) {
			ReportViewsDataRow viewsDataRow = reportViewsDataReader.getDataRow(viewRowID);
			viewNamesList.add(viewsDataRow.name);
		}
		return viewNamesList;
	}

//	private String getViewThumbnailImageId(Integer viewIdx) {
//		String viewID = "";		
//			ReportJobsStat reportJobStat = this.getComplianceReportsPage().getReportJobStat(workingDataRow.title);		
//			List<ReportJob> reportJobs = reportJobStat.ReportJobs;
//			for (ReportJob reportJob : reportJobs) {
//				if (reportJob.ReportJobType.equals(ReportJobType.Map.toString())) {
//					viewIdx--;
//					if (viewIdx == 0) {
//						viewID = reportJob.ReportJobId;
//						break;
//					}
//				} 
//			}
//		return viewID;
//	}
	
	private String getDownloadPath(ReportFileType fileType) {
		String fileName = "";
		
		if (fileType == ReportFileType.PDF || fileType == ReportFileType.InvestigationPDF) {
			return TestContext.INSTANCE.getTestSetup().getDownloadPath();
		} else if (fileType == ReportFileType.ZIP) {
			fileName = this.getComplianceReportsPage().getReportPDFZipFileName(workingDataRow.title, false /*includeExtension*/);
		} else if (fileType == ReportFileType.MetaDataZIP) {
			fileName = this.getComplianceReportsPage().getReportMetaZipFileName(workingDataRow.title, false /*includeExtension*/);
		} else if (fileType == ReportFileType.ShapeZIP) {
			fileName = this.getComplianceReportsPage().getReportShapeZipFileName(workingDataRow.title, false /*includeExtension*/);
		}
		
		String downloadPath = Paths.get(TestContext.INSTANCE.getTestSetup().getDownloadPath(), fileName).toString();
		return downloadPath;
	}

	private int getDownloadFileIndex(String data, int fileIndex){
		if(data!=null&&data.matches("[0-2]")){
			fileIndex = Integer.valueOf(data);
		}
		return fileIndex;
	}
	private boolean isPDFGapSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return this.getComplianceReportsPage().isPDFGapSelected() && (dataRow.gapTable == "TRUE");
	}

	private boolean isPDFIndicationSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return this.getComplianceReportsPage().isPDFIndicationSelected() && (dataRow.indicationTable == "TRUE");
	}

	private boolean isPDFIsotopicAnalysisSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return this.getComplianceReportsPage().isPDFIsotopicAnalysisSelected() && (dataRow.isotopicAnalysis == "TRUE");
	}

	private boolean isPDFPercentCoverageAssetsSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return this.getComplianceReportsPage().isPDFPercentCoverageAssetsSelected() && (dataRow.percentCoverageAssets == "TRUE");
	}

	private boolean isPDFPercentCoverageForecastSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return this.getComplianceReportsPage().isPDFPercentCoverageForecastSelected() && (dataRow.percentCoverageForecast == "TRUE");
	}

	private boolean isPDFPercentCoverageReportAreaSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return this.getComplianceReportsPage().isPDFPercentCoverageReportAreaSelected() && (dataRow.percentCoverageReportArea == "TRUE");
	}

	private void openComplianceViewerDialog(Integer dataRowID) throws Exception {
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		this.getComplianceReportsPage().waitForPdfReportIcontoAppear();
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
			.drawSelectorRectangle(ComplianceReportsPage.BOUNDARY_SELECTOR_CANVAS_X_PATH, 
					coordList.get(0), coordList.get(1), coordList.get(2), coordList.get(3))
			.switchMode(ControlMode.Default)
			.clickOkButton()
			.waitForModalDialogToClose();
	}

	private boolean verifyPresenceOfButton(Integer dataRowID, ComplianceReportButtonType buttonType) throws Exception {
		ComplianceReportsDataRow compRptDataRow = getComplianceReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		return this.getComplianceReportsPage().verifyComplianceReportButton(reportTitle, LoginPageActions.workingDataRow.username, buttonType);
	}

	private boolean verifyReportSurveyValuesMatch(List<Integer> surveyRowIDs) throws IOException {
		return false;
	}

	/**
	 * Downloads the specified report file type and waits for download to complete.
	 * @param dataRowID - RowID for test case data.
	 * @param fileType - Type of file to download
	 * @param fileIndex - Index for file to be specified for View images. Ignored for other report file types.
	 * 			1-based index. For first view use - 1.		
	 * @throws Exception
	 */
	private void waitForReportFileDownload(Integer dataRowID, ReportFileType fileType, Integer fileIndex) throws Exception {
		waitForReportFileDownload(dataRowID, fileType, fileIndex, -1);
	}
	private void waitForReportFileDownload(Integer dataRowID, ReportFileType fileType, Integer fileIndex, int zipIndex) throws Exception {
		ComplianceReportsDataRow compRptDataRow = getComplianceReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		String reportName = "";
		
		switch(fileType) {
		case PDF:
			reportName = this.getComplianceReportsPage().getReportPDFFileName(reportTitle, false /*includeExtension*/);
			this.getComplianceReportsPage().waitForPDFFileDownload(reportName);
			break;
		case ZIP:
			// get the report name without extension.
			zipIndex = zipIndex==-1?0:zipIndex;
			reportName = this.getComplianceReportsPage().getReportPDFFileName(reportTitle, false /*includeExtension*/);
			this.getComplianceReportsPage().waitForReportZIPFileDownload(reportName,zipIndex);
			break;
		case MetaDataZIP:
			// get the report name without extension.
			zipIndex = zipIndex==-1?1:zipIndex;
			reportName = this.getComplianceReportsPage().getReportPDFFileName(reportTitle, false /*includeExtension*/);
			this.getComplianceReportsPage().waitForMetadataZIPFileDownload(reportName,zipIndex);
			break;
		case ShapeZIP:
			// get the report name without extension.
			zipIndex = zipIndex==-1?2:zipIndex;
			reportName = this.getComplianceReportsPage().getReportPDFFileName(reportTitle, false /*includeExtension*/);
			this.getComplianceReportsPage().waitForShapeZIPFileDownload(reportName,zipIndex);
			break;
		case View:
			reportName = this.getComplianceReportsPage().getReportPDFFileName(workingDataRow.title, false /*includeExtension*/); 
			List<Map<String, String>> viewList = workingReportsComp.getViewList();
			Map<String, String> map = viewList.get(fileIndex-1);
			String viewName = map.get(KEYVIEWNAME);
			this.getComplianceReportsPage().waitForViewFileDownload(reportName, viewName);
			break;
		default:
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
		ComplianceReportDataReader reportDataReader = new ComplianceReportDataReader(excelUtility);
		ComplianceReportsDataRow reportsDataRow = reportDataReader.getDataRow(dataRowID);
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
		ComplianceReportDataReader reportDataReader = new ComplianceReportDataReader(excelUtility);
		ComplianceReportsDataRow reportsDataRow = reportDataReader.getDataRow(dataRowID);
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
		logAction("ComplianceReportsPageActions.addDefaultView", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.addNewView", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.addSurveysToReport", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("addSurveysToReport", ARG_DATA_ROW_ID, dataRowID);
		ReportsCompliance reportsCompliance = new ReportsCompliance();
		ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
		List<ReportsSurveyInfo> reportsSurveyInfoList = buildReportSurveyInfoList(dataRow, this.excelUtility);
		reportsCompliance.setSurveyInfoList(reportsSurveyInfoList);
		this.getComplianceReportsPage().addSurveyInformation(reportsCompliance);
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
		logAction("ComplianceReportsPageActions.cancelInProgressReport", data, dataRowID);
		this.getComplianceReportsPage().clickComplianceReportButton(workingDataRow.title, 
				LoginPageActions.workingDataRow.username, ComplianceReportButtonType.Cancel);
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
		logAction("ComplianceReportsPageActions.cancelInProgressReport", data, dataRowID);
		this.getComplianceReportsPage().clickComplianceReportButton(workingDataRow.title, 
				LoginPageActions.workingDataRow.username, ComplianceReportButtonType.InProgressCopy);
		this.getComplianceReportsPage().waitForCopyReportPagetoLoad();
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
		logAction("ComplianceReportsPageActions.checkSurveySelectorGeographicFilter", data, dataRowID);
		this.getComplianceReportsPage().selectSurveyInfoGeoFilter(true);
		return true;
	}
 
	/**
	 * Executes clickOnCancelButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnCancelButton(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnCancelButton", data, dataRowID);
		this.getComplianceReportsPage().clickOnCancelBtn();
		return true;
	}
 
	/**
	 * Executes clickOnCancelConfirmDeleteReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnCancelConfirmDeleteReport(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnCancelConfirmDeleteReport", data, dataRowID);
		this.getComplianceReportsPage().clickOnCancelInDeleteReportPopup();
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
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerButton", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
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
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerCloseButton", data, dataRowID);
		this.getComplianceReportsPage().clickOnReportViewerCloseButton();
		return true;
	}

	/**
	 * Executes clickOnConfirmDeleteReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnConfirmDeleteReport(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnConfirmDeleteReport", data, dataRowID);
		this.getComplianceReportsPage().clickOnConfirmInDeleteReportPopup();
		this.getComplianceReportsPage().waitForPageLoad();
		this.getComplianceReportsPage().waitForAJAXCallsToComplete();
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
		logAction("ComplianceReportsPageActions.clickOnCopyButton", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.Copy);
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
		logAction("ComplianceReportsPageActions.clickOnDeleteButton", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.Delete);
		return true;
	}
 
	/**
	 * Executes clickOnFirstCopyComplianceButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnFirstCopyComplianceButton(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnFirstCopyComplianceButton", data, dataRowID);
		this.getComplianceReportsPage().clickOnFirstCopyComplianceBtn();
		this.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		this.initializePageObject(TestContext.INSTANCE.getDriver(), this.createNewPageObject());
		return true;
	}
 
	/**
	 * Executes clickOnFirstInvestigateComplianceButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnFirstInvestigateComplianceButton(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnFirstInvestigateComplianceButton", data, dataRowID);
		this.getComplianceReportsPage().clickOnFirstInvestigateComplianceBtn();
		return true;
	}
 
	/**
	 * Executes clickOnInvestigateButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean clickOnInvestigateButton(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.clickOnInvestigateButton", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.Investigate);
		return true;
	}
 
	/**
	 * Executes clickOnInvestigatePDFButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean clickOnInvestigatePDFButton(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.clickOnInvestigatePDFButton", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.InvestigatePDF);
		return true;
	}
 
	/**
	 * Executes clickOnLatLongSelectorButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnLatLongSelectorButton(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnLatLongSelectorButton", data, dataRowID);
		this.getComplianceReportsPage().clickLatLongMapSelectorBtn();
		return true;
	}

	/**
	 * Executes clickOnOKButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnOKButton(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnOKButton", data, dataRowID);
		this.getComplianceReportsPage().clickOnOKButton();
		return true;
	}
 
	/**
	 * Executes clickOnNewComplianceReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnNewComplianceReport(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnNewComplianceReport", data, dataRowID);
		this.getComplianceReportsPage().clickOnNewComplianceReportBtn();
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
		logAction("ComplianceReportsPageActions.clickOnResubmitButton", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.Resubmit);
		return true;
	}
 
 	/**
	 * Executes clickOnSurveySelectorSearchButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnSurveySelectorSearchButton(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnSurveySelectorSearchButton", data, dataRowID);
		this.getComplianceReportsPage().clickOnSearchSurveyButton();
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
		logAction("ComplianceReportsPageActions.copyReport", data, dataRowID);
		this.getComplianceReportsPage().copyReport(data, LoginPageActions.workingDataRow.username);
		this.getComplianceReportsPage().waitForCopyReportPagetoLoad();
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
		logAction("ComplianceReportsPageActions.openComplianceViewerDialog", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.modifyReport", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.createNewReport", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.deleteReport", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("deleteReport", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportsDataRow compRptDataRow = getComplianceReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		String createdBy = LoginPageActions.workingDataRow.username;
		this.getComplianceReportsPage().deleteReport(reportTitle, createdBy);
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
		logAction("ComplianceReportsPageActions.enterCustomBoundaryUsingAreaSelector", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("enterCustomBoundaryUsingAreaSelector", ARG_DATA, data);
		List<Integer> coordList = verifyLatLongCoordinates(data);		
		this.getComplianceReportsPage().openCustomBoundarySelector();		
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
		logAction("ComplianceReportsPageActions.enterCustomBoundaryUsingTextFields", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("enterCustomBoundaryUsingTextFields", ARG_DATA, data);
		List<String> custBoundaryList = RegexUtility.split(data, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		String neLat = custBoundaryList.get(0);
		String neLong = custBoundaryList.get(1);
		String swLat = custBoundaryList.get(2);	
		String swLong = custBoundaryList.get(3);		
		this.getComplianceReportsPage().fillCustomBoundaryTextFields(neLat, neLong, swLat, swLong);
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
		logAction("ComplianceReportsPageActions.enterCustomerBoundaryUsingAreaSelector", data, dataRowID);
		if (dataRowID != -1) {
			ComplianceReportsDataRow complianceReportsDataRow = getComplianceReportsDataRow(dataRowID);
			this.getComplianceReportsPage().fillCustomerBoundary(complianceReportsDataRow.customerBoundaryType,
					complianceReportsDataRow.customerBoundaryName);
		} else {		
			ActionArguments.verifyNotNullOrEmpty("enterCustomBoundaryUsingAreaSelector", ARG_DATA, data);
			List<String> boundaryInfo = verifyCustomerBoundaryInfo(data);		
			this.getComplianceReportsPage().fillCustomerBoundary(boundaryInfo.get(0), boundaryInfo.get(1));
		}
		return true;
	}
 
	/**
	 * Executes enterExclusionRadius action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean enterExclusionRadius(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.enterExclusionRadius", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("enterExclusionRadius", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
		this.getComplianceReportsPage().inputExclusionRadius(dataRow.exclusionRadius);
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
		logAction("ComplianceReportsPageActions.enterFOVOpacity", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("enterFOVOpacity", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
		this.getComplianceReportsPage().inputFOVOpacity(dataRow.opacityFOV);
		return true;
	}
 
	/**
	 * Executes enterLISAOpacity action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean enterLISAOpacity(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.enterLISAOpacity", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("enterLISAOpacity", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
		this.getComplianceReportsPage().inputLISAOpacity(dataRow.opacityLISA);
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
		logAction("ComplianceReportsPageActions.enterPDFImageHeight", data, dataRowID);
		if (!ActionArguments.isEmpty(data)) {
			this.getComplianceReportsPage().inputImageMapHeight(data);
		} else {
			ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
			this.getComplianceReportsPage().inputImageMapHeight(dataRow.pDFImageOutputHeight);
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
		logAction("ComplianceReportsPageActions.enterPDFImageWidth", data, dataRowID);
		if (!ActionArguments.isEmpty(data)) {
			this.getComplianceReportsPage().inputImageMapWidth(data);
		} else {
			ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
			this.getComplianceReportsPage().inputImageMapWidth(dataRow.pDFImageOutputWidth);
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
		logAction("ComplianceReportsPageActions.enterReportTitle", data, dataRowID);
		if (!ActionArguments.isEmpty(data)) {
			this.getComplianceReportsPage().inputReportTitle(data);
		} else {
			ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
			this.getComplianceReportsPage().inputReportTitle(dataRow.title);
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
		logAction("ComplianceReportsPageActions.enterSurveySelectorTag", data, dataRowID);
		if (!ActionArguments.isEmpty(data)) {
			this.getComplianceReportsPage().inputSurveyTag(data);
		} else {
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(this.excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(dataRowID);
			this.getComplianceReportsPage().inputSurveyTag(surveyDataRow.surveyTag);
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
		logAction("ComplianceReportsPageActions.enterSurveySelectorUsername", data, dataRowID);
		if (!ActionArguments.isEmpty(data)) {
			this.getComplianceReportsPage().inputSurveyUsername(data);
		} else {
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(this.excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(dataRowID);
			this.getComplianceReportsPage().inputSurveyUsername(surveyDataRow.surveyUsername);
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
		logAction("ComplianceReportsPageActions.findReport", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("findReport", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportsDataRow compRptDataRow = getComplianceReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		String createdBy = LoginPageActions.workingDataRow.username;
		this.getComplianceReportsPage().findReport(reportTitle, createdBy);
		return true;
	}
 
	/**
	 * Executes investigateReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean investigateReport(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.investigateReport", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("investigateReport", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportsDataRow compRptDataRow = getComplianceReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		String createdBy = LoginPageActions.workingDataRow.username;
		this.getComplianceReportsPage().investigateReport(reportTitle, createdBy);		
		return true;
	}
 
	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.open", data, dataRowID);
		getComplianceReportsPage().open();
		getComplianceReportsPage().waitForPageLoad();
		return true;
	}
 
	/**
	 * Executes openNewReportPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean openNewReportPage(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.openNewReportPage", data, dataRowID);
		getComplianceReportsPage().openNewComplianceReportPage();
		getComplianceReportsPage().waitForNewPageLoad();
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
		logAction("ComplianceReportsPageActions.searchAndDeleteReport", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("searchAndDeleteReport", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportsDataRow compRptDataRow = getComplianceReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		String createdBy = LoginPageActions.workingDataRow.username;
		this.getComplianceReportsPage().searchAndDeleteReport(reportTitle, createdBy);
		return true;
	}

	/**
	 * Executes searchForSurveyByKeyword action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean searchForSurveyByKeyword(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.searchForSurveyByKeyword", data, dataRowID);
		getComplianceReportsPage().performSearch(data);
		return true;
	}
 
	/**
	 * Executes selectIndicationTableCheckbox action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectIndicationTableCheckbox(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.selectIndicationTableCheckbox", data, dataRowID);
		this.getComplianceReportsPage().selectIndicationsTableCheckBox();
		return true;
	}
 
	/**
	 * Executes selectIsotopicTableCheckbox action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectIsotopicTableCheckbox(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.selectIsotopicTableCheckbox", data, dataRowID);
		this.getComplianceReportsPage().selectIsotopicAnalysisCheckBox();
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
		logAction("ComplianceReportsPageActions.selectPaginationRows", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("selectPaginationRows", ARG_DATA, data);
		ActionArguments.verifyGreaterThanZero("selectPaginationRows", ARG_DATA, NumberUtility.getIntegerValueOf(data));
		this.getComplianceReportsPage().setPagination(data);
		return true;
	}

	/**
	 * Executes selectPercentCoverageForecastCheckBox action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectPercentCoverageForecastCheckBox(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.selectPercentCoverageForecastCheckBox", data, dataRowID);
		this.getComplianceReportsPage().selectPercentCoverageForecastCheckBox();
		return true;
	}
 
	/**
	 * Executes selectPercentCoverageReportAreaCheckBox action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectPercentCoverageReportAreaCheckBox(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.selectPercentCoverageReportAreaCheckBox", data, dataRowID);
		this.getComplianceReportsPage().selectPercentCoverageReportArea();
		return true;
	}
 
	/**
	 * Executes selectShowPercentCoverageOfAssetsCheckBox action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectShowPercentCoverageOfAssetsCheckBox(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.selectShowPercentCoverageOfAssetsCheckBox", data, dataRowID);
		this.getComplianceReportsPage().selectPercentCoverageAssetCheckBox();
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
		logAction("ComplianceReportsPageActions.selectCustomer", data, dataRowID);
		String customer;
		if (!ActionArguments.isEmpty(data)) {
			customer = data;
		} else {
			ComplianceReportsDataRow compRptDataRow = getComplianceReportsDataRow(dataRowID);
			Integer custRowID = NumberUtility.getIntegerValueOf(compRptDataRow.customerRowID);
			CustomerDataReader custDataReader = new CustomerDataReader(this.excelUtility);
			CustomerDataRow custDataRow = custDataReader.getDataRow(custRowID);
			customer = custDataRow.name;
		}

		this.getComplianceReportsPage().selectCustomer(customer);
		this.getComplianceReportsPage().confirmInChangeCustomerDialog();
		return true;
	}
 
	/**
	 * Executes selectReportMode action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean selectReportMode(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.selectReportMode", data, dataRowID);
		String reportMode;
		if (!ActionArguments.isEmpty(data)) {
			reportMode = data;
		} else {
			ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
			reportMode = dataRow.reportMode;
		}
		ReportModeFilter mode = this.getComplianceReportsPage().getReportMode(reportMode);
		this.getComplianceReportsPage().selectReportMode(mode);
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
		logAction("ComplianceReportsPageActions.selectSurveySelectorEndDateTime", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("selectSurveySelectorEndDateTime", ARG_DATA, data);
		this.getComplianceReportsPage().selectEndDateForSurvey(data);
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
		logAction("ComplianceReportsPageActions.selectSurveySelectorStartDateTime", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("selectSurveySelectorStartDateTime", ARG_DATA, data);
		this.getComplianceReportsPage().selectStartDateForSurvey(data);
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
		logAction("ComplianceReportsPageActions.selectSurveySelectorSurveyModeFilter", data, dataRowID);
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
		
		this.getComplianceReportsPage().selectSurveyModeForSurvey(modeFilter);
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
		logAction("ComplianceReportsPageActions.selectSurveySelectorSurveyor", data, dataRowID);
		String surveyorUnit;
		if (!ActionArguments.isEmpty(data)) {
			surveyorUnit = data;
		} else {
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(this.excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(dataRowID);
			surveyorUnit = surveyDataRow.surveySurveyor;
		}
		this.getComplianceReportsPage().selectSurveySurveyor(surveyorUnit);
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
		logAction("ComplianceReportsPageActions.selectTabularPDFContent", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("selectTabularPDFContent", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
		Integer pdfContentRowID = NumberUtility.getIntegerValueOf(dataRow.reportOptTabularPDFContentRowID);
		ReportOptTabularPDFContentDataReader pdfContentDataReader = new ReportOptTabularPDFContentDataReader(this.excelUtility);
		ReportOptTabularPDFContentDataRow pdfContentDataRow = pdfContentDataReader.getDataRow(pdfContentRowID);
		Boolean selectGap = Boolean.valueOf(pdfContentDataRow.gapTable);
		Boolean selectIndicationTable = Boolean.valueOf(pdfContentDataRow.indicationTable);
		Boolean selectIsotopicAnalysis = Boolean.valueOf(pdfContentDataRow.isotopicAnalysis);
		Boolean selectPercentCoverageAssets = Boolean.valueOf(pdfContentDataRow.percentCoverageAssets);
		Boolean selectPercentCoverageForecast = Boolean.valueOf(pdfContentDataRow.percentCoverageForecast);
		Boolean selectPercentCoverageReportArea = Boolean.valueOf(pdfContentDataRow.percentCoverageReportArea);
		
		if (selectGap) {
			this.getComplianceReportsPage().selectGapTableCheckBox();
		}
		if (selectIndicationTable) {
			this.getComplianceReportsPage().selectIndicationsTableCheckBox();
		}
		if (selectIsotopicAnalysis) {
			this.getComplianceReportsPage().selectIsotopicAnalysisCheckBox();
		}
		if (selectPercentCoverageAssets) {
			this.getComplianceReportsPage().selectPercentCoverageAssetCheckBox();
		}
		if (selectPercentCoverageForecast) {
			this.getComplianceReportsPage().selectPercentCoverageForecastCheckBox();
		}
		if (selectPercentCoverageReportArea) {
			this.getComplianceReportsPage().selectPercentCoverageReportArea();
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
		logAction("ComplianceReportsPageActions.selectTimeZone", data, dataRowID);
		String timeZone;
		if (!ActionArguments.isEmpty(data)) {
			timeZone = data;
		} else {
			ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
			timeZone = dataRow.timezone;
		}
		this.getComplianceReportsPage().selectTimeZone(timeZone);
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
		logAction("ComplianceReportsPageActions.selectViewLayersAsset", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("selectViewLayersAsset", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
		Integer rptViewLayerRowID = NumberUtility.getIntegerValueOf(dataRow.reportOptViewLayerRowID);
		ReportOptViewLayersDataReader optViewLayersDataReader = new ReportOptViewLayersDataReader(this.excelUtility);
		ReportOptViewLayersDataRow optViewLayersDataRow = optViewLayersDataReader.getDataRow(rptViewLayerRowID);
		HashMap<String, String> viewLayerMap = new HashMap<String, String>();
		List<Integer> assetRowIDs = ActionArguments.getNumericList(optViewLayersDataRow.assetRowIDs);
		for (Integer rowID : assetRowIDs) {
			ReportOptViewLayersAssetsDataReader viewLayersAssetsDataReader = getViewLayersAssetsDataReader();
			ReportOptViewLayersAssetsDataRow assetsDataRow = viewLayersAssetsDataReader.getDataRow(rowID);
			viewLayerMap.put(assetsDataRow.assetID, ReportsCompliance.ASSET_PREFIX + assetsDataRow.assetName);
		}

		this.getComplianceReportsPage().selectViewLayerAssets(viewLayerMap);
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
		logAction("ComplianceReportsPageActions.selectViewLayersBoundary", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("selectViewLayersAsset", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
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
			viewLayerMap.put(boundariesDataRow.boundaryID, ReportsCompliance.BOUNDARY_PREFIX + boundariesDataRow.boundaryName);
		}

		this.getComplianceReportsPage().selectViewLayerBoundaries(viewLayerMap);
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
		logAction("ComplianceReportsPageActions.verifyComplianceViewerButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyComplianceViewerButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ComplianceReportButtonType.ReportViewer);
	}

	/**
	 * Executes verifyComplianceViewerViewCountEquals action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyComplianceViewerViewCountEquals(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyComplianceViewerViewCountEquals", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifyCopyButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyCopyButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ComplianceReportButtonType.Copy);
	}

	/**
	 * Executes verifyCopyButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyCancelButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyCopyButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyCopyButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ComplianceReportButtonType.Cancel);
	}
	/**
	 * Executes verifyDeleteButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyDeleteButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyDeleteButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyDeleteButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ComplianceReportButtonType.Delete);
	}
 
	/**
	 * Executes verifyInvestigateButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyInvestigateButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyInvestigateButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyInvestigateButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ComplianceReportButtonType.Investigate);
	}
 
	/**
	 * Executes verifyInvestigatePDFButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyInvestigatePDFButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyInvestigatePDFButtonIsDisplayed", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyInvestigatePDFButtonIsDisplayed", ARG_DATA_ROW_ID, dataRowID);
		return verifyPresenceOfButton(dataRowID, ComplianceReportButtonType.InvestigatePDF);
	}
 
	/**
	 * Executes verifyInvestigatePDFDownload action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyInvestigatePDFDownload(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyInvestigatePDFDownload", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyInvestigatePDFDownload", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.InvestigatePDF);
		// TODO wait for file download to complete.
		//this.getComplianceReportsPage().waitForPDFFileDownload();
		this.getComplianceReportsPage().validateInvestigatePDFFile();
		return true;
	}
 
	/**
	 * Executes verifyLastXDaysSurveysPresentInPDF action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyLastXDaysSurveysPresentInPDF(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyLastXDaysSurveysPresentInPDF", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifyPDFContainsInputtedInformation", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyPDFContainsInputtedInformation", ARG_DATA, data);
		List<String> listExpectedStrings = RegexUtility.split(data, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
		return this.getComplianceReportsPage().verifyComplianceReportContainsText(workingDataRow.title, listExpectedStrings);
	}

	/**
	 * Executes verifyMetaDataFilesHaveCorrectData action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyMetaDataFilesHaveCorrectData(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyMetaDataFilesHaveCorrectData", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyMetaDataFilesHaveCorrectData", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
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
		logAction("ComplianceReportsPageActions.verifyMetaDataZIPFilesAreCorrect", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyMetaDataZIPFilesAreCorrect", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
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
		logAction("ComplianceReportsPageActions.verifyMetaDataZIPThumbnailDownloadFromComplianceViewer", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyMetaDataZIPThumbnailDownloadFromComplianceViewer", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		// TODO: Internal method needs implementation.
		this.getComplianceReportsPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.ComplianceZipMeta);
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
		logAction("ComplianceReportsPageActions.verifyMetaDataZIPThumbnailIsShownInComplianceViewer", data, dataRowID);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceZipMeta);
		return true;
	}
 
	/**
	 * Executes verifyPaginationAndSortingOnAllColumns action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyPaginationAndSortingOnAllColumns(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyPaginationAndSortingOnAllColumns", data, dataRowID);

		ActionArguments.verifyNotNullOrEmpty("verifyPaginationAndSortingOnAllColumns", ARG_DATA, data);
		
		boolean retVal = true;
		String paginationSetting = data;

		retVal = retVal && (this.getComplianceReportsPage().checkPaginationSetting(paginationSetting));

		this.getComplianceReportsPage().sortTableByColumn(1, TableSortOrder.ASC);
		this.getComplianceReportsPage().sortTableByColumn(1, TableSortOrder.DESC);

		retVal = retVal && (!(this.getComplianceReportsPage().getNumberofRecords() > Integer.parseInt(paginationSetting)));
		
		this.getComplianceReportsPage().sortTableByColumn(2, TableSortOrder.ASC);
		this.getComplianceReportsPage().sortTableByColumn(2, TableSortOrder.DESC);

		retVal = retVal && (!(this.getComplianceReportsPage().getNumberofRecords() > Integer.parseInt(paginationSetting)));

		this.getComplianceReportsPage().sortTableByColumn(3, TableSortOrder.ASC);
		this.getComplianceReportsPage().sortTableByColumn(3, TableSortOrder.DESC);

		retVal = retVal && (!(this.getComplianceReportsPage().getNumberofRecords() > Integer.parseInt(paginationSetting)));

		this.getComplianceReportsPage().sortTableByColumn(4, TableSortOrder.ASC);
		this.getComplianceReportsPage().sortTableByColumn(4, TableSortOrder.DESC);

		retVal = retVal && (!(this.getComplianceReportsPage().getNumberofRecords() > Integer.parseInt(paginationSetting)));

		return false;
	}
	
	/**
	 * Executes verifyPDFThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyPDFThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyPDFThumbnailDownloadFromComplianceViewer", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyPDFThumbnailDownloadFromComplianceViewer", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		// TODO: Internal method needs implementation.
		this.getComplianceReportsPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.ComplianceTablePDF);
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
		logAction("ComplianceReportsPageActions.verifyPDFThumbnailIsShownInComplianceViewer", data, dataRowID);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceTablePDF);
		return true;
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
		logAction("ComplianceReportsPageActions.verifyPDFZipFilesAreCorrect", data, dataRowID);

		// Verify that there is a file for Report PDF.
		// and there is one PDF file for each view that was specified in the input.
		Integer expectedFileCount = 1;
		List<Integer> viewRowIDs = ActionArguments.getNumericList(workingDataRow.reportViewRowIDs);
		if (viewRowIDs != null) {
			expectedFileCount += viewRowIDs.size();
		}
		
		List<String> expectedFileNames = new ArrayList<String>();
		String reportFileNameWithoutExt = workingDataRow.title.replace(" ", "");
		expectedFileNames.add(reportFileNameWithoutExt + ".pdf");
		for (int i=1; i<expectedFileCount; i++) {
			String viewName = workingReportViewsDataRows.get(i-1).name;
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
		logAction("ComplianceReportsPageActions.verifyPDFZipFilesArePresent", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyPDFZipFilesArePresent", ARG_DATA_ROW_ID, dataRowID);
		
		if (workingReportsComp == null) {
			throw new Exception("Create new report before verifying report PDF files. Report has not been created.");
		}

		ComplianceReportsDataRow compRptDataRow = getComplianceReportsDataRow(dataRowID);
		String rptTitle = compRptDataRow.title;

		if ((getComplianceReportsPage().checkActionStatus(rptTitle, LoginPageActions.workingDataRow.username, null))) {
			if ((!getComplianceReportsPage().findReport(rptTitle, LoginPageActions.workingDataRow.username)) || 
					(!getComplianceReportsPage().validatePdfFiles(workingReportsComp, 
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
		logAction("ComplianceReportsPageActions.verifyPDFZIPThumbnailDownloadFromComplianceViewer", data, dataRowID);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		this.getComplianceReportsPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.ComplianceZipPDF);
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
		logAction("ComplianceReportsPageActions.verifyPDFZIPThumbnailIsShownInComplianceViewer", data, dataRowID);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceZipPDF);
		return true;
	}
 
	/**
	 * Executes verifyReportPDFMatches action.
	 * @param data - regex pattern string for matching.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyReportPDFMatches(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyReportPDFMatches", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyReportPDFMatches", ARG_DATA, data);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		String pdfFileName = getComplianceReportsPage().getReportPDFFileName(workingDataRow.title, true /*includeExtension*/);
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
		logAction("ComplianceReportsPageActions.verifyReportThumbnailMatches", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifyRequiredFieldsAreShownInRed", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifyReportPageFieldsAreCorrect", data, dataRowID);
		ComplianceReportsDataRow dataRow = getComplianceReportsDataRow(dataRowID);
		String customerName = new CustomerDataReader(this.excelUtility).getDataRow(NumberUtility.getIntegerValueOf(dataRow.customerRowID)).name;
		List<Integer> surveyRowIDs = ActionArguments.getNumericList(dataRow.reportSurveyRowIDs);
		Integer reportViewLayerRowID = NumberUtility.getIntegerValueOf(dataRow.reportOptViewLayerRowID);
		ReportOptViewLayersDataRow reportOptViewLayersDataRow = new ReportOptViewLayersDataReader(this.excelUtility).getDataRow(reportViewLayerRowID);
		Integer reportPDFContentRowID = NumberUtility.getIntegerValueOf(dataRow.reportOptTabularPDFContentRowID);
		ReportOptTabularPDFContentDataRow reportPDFContentDataRow = new ReportOptTabularPDFContentDataReader(this.excelUtility).getDataRow(reportPDFContentRowID);
		//boolean reportTitleMatches = (dataRow.title == this.getComplianceReportsPage().getReportTitle());

		String actualCustomerValue = this.getComplianceReportsPage().getCustomerValue();
		String actualTimezoneValue = this.getComplianceReportsPage().getTimezoneValue();
		String actualExclusionRadius = this.getComplianceReportsPage().getExclusionRadius();
		String actualReportModeFilter = this.getComplianceReportsPage().getReportModeFilter().toString();
		String actualNELatitude = this.getComplianceReportsPage().getNELatitude();
		String actualNELongitude = this.getComplianceReportsPage().getNELongitude();
		String actualSWLatitude = this.getComplianceReportsPage().getSWLatitude();
		String actualSWLongitude = this.getComplianceReportsPage().getSWLongitude();
		String actualFOVOpacity = this.getComplianceReportsPage().getFOVOpacity();
		String actualLISAOpacity = this.getComplianceReportsPage().getLISAOpacity();
		String actualPDFWidth = this.getComplianceReportsPage().getPDFWidth();
		String actualPDFHeight = this.getComplianceReportsPage().getPDFHeight();

		Log.info(String.format("Matching customer. Expected=[%s], Actual=[%s]", customerName, actualCustomerValue));
		Log.info(String.format("Matching timezone. Expected=[%s], Actual=[%s]", dataRow.timezone, actualTimezoneValue));
		Log.info(String.format("Matching exclusionRadius. Expected=[%s], Actual=[%s]", dataRow.exclusionRadius, actualExclusionRadius));
		Log.info(String.format("Matching reportMode. Expected=[%s], Actual=[%s]", dataRow.reportMode, actualReportModeFilter));
		Log.info(String.format("Matching customBoundaryNELat. Expected=[%s], Actual=[%s]", dataRow.customBoundaryNELat, actualNELatitude));
		Log.info(String.format("Matching customBoundaryNELong. Expected=[%s], Actual=[%s]", dataRow.customBoundaryNELong, actualNELongitude));
		Log.info(String.format("Matching customBoundarySWLat. Expected=[%s], Actual=[%s]", dataRow.customBoundarySWLat, actualSWLatitude));
		Log.info(String.format("Matching customBoundarySWLong. Expected=[%s], Actual=[%s]", dataRow.customBoundarySWLong, actualSWLongitude));
		Log.info(String.format("Matching opacityFOV. Expected=[%s], Actual=[%s]", dataRow.opacityFOV, actualFOVOpacity));
		Log.info(String.format("Matching opacityLISA. Expected=[%s], Actual=[%s]", dataRow.opacityLISA, actualLISAOpacity));
		Log.info(String.format("Matching pDFImageOutputWidth. Expected=[%s], Actual=[%s]", dataRow.pDFImageOutputWidth, actualPDFWidth));
		Log.info(String.format("Matching pDFImageOutputHeight. Expected=[%s], Actual=[%s]", dataRow.pDFImageOutputHeight, actualPDFHeight));
		
		boolean customerMatches = (customerName.equals(actualCustomerValue));
		boolean timezoneMatches = (dataRow.timezone.equals(actualTimezoneValue));
		boolean exclusionRadiusMatches = (dataRow.exclusionRadius.equals(actualExclusionRadius));
		boolean reportModeMatches = (dataRow.reportMode.equals(actualReportModeFilter));
		boolean nELatMatches = (dataRow.customBoundaryNELat.equals(actualNELatitude));
		boolean nELongMatches = (dataRow.customBoundaryNELong.equals(actualNELongitude));
		boolean sWLatMatches = (dataRow.customBoundarySWLat.equals(actualSWLatitude));
		boolean sWLongMatches = (dataRow.customBoundarySWLong.equals(actualSWLongitude));
		boolean fovOpacityMatches = (dataRow.opacityFOV.equals(actualFOVOpacity));
		boolean lisaOpacityMatches = (dataRow.opacityLISA.equals(actualLISAOpacity));
		boolean pdfWidthMatches = (dataRow.pDFImageOutputWidth.equals(actualPDFWidth));
		boolean pdfHeightMatches = (dataRow.pDFImageOutputHeight.equals(actualPDFHeight));
		boolean verifyReportSurveyValuesMatch = verifyReportSurveyValuesMatch(surveyRowIDs);
		boolean areAssetBoundariesMatch = areAssetBoundariesMatch(reportOptViewLayersDataRow);
		boolean areTabularPDFContentSelectionMatch = areTabularPDFContentSelectionMatch(reportPDFContentDataRow);
		return /*reportTitleMatches &&*/ customerMatches && timezoneMatches && exclusionRadiusMatches && reportModeMatches && 
				nELatMatches && nELongMatches && sWLatMatches && sWLongMatches && fovOpacityMatches &&
				lisaOpacityMatches && pdfWidthMatches && pdfHeightMatches && verifyReportSurveyValuesMatch &&
				areAssetBoundariesMatch && areTabularPDFContentSelectionMatch;
	}

	/**
	 * Executes verifyResubmitButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyResubmitButtonIsDisplayed(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyResubmitButtonIsDisplayed", data, dataRowID);
		this.getComplianceReportsPage().checkComplianceReportButtonPresenceAndClick(workingDataRow.title, 
				LoginPageActions.workingDataRow.username, ComplianceReportButtonType.Resubmit, false /*clickButton*/, false /*confirmAction*/);
		return true;
	}
 
	/**
	 * Executes verifySearchedSurveyIsShown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveyIsShown(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifySearchedSurveyIsShown", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifySearchedSurveysAreForLastXDays", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifySearchedSurveysMatchDateRange", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifySearchedSurveysMatchSurveyorUnit", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifySearchedSurveysMatchTag", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifyShapeZIPFilesAreCorrect", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifyShapeZIPThumbnailDownloadFromComplianceViewer", data, dataRowID);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		this.getComplianceReportsPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.ComplianceZipShape);
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
		logAction("ComplianceReportsPageActions.verifyShapeZIPThumbnailIsShownInComplianceViewer", data, dataRowID);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceZipShape);
		return true;
	}

	/**
	 * Executes verifyIsotopicTableSortedAscByColumn action.
	 * @param data - column name used for sorting.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyIsotopicTableSortedAscByColumn(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyIsotopicTableSortedAscByColumn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyIsotopicTableSortedAscByColumn", ARG_DATA, data);
		List<String[]> isotopicAnalysisTblList = this.getComplianceReportsPage().getSSRSPDFTableValues(
				PDFTable.ISOTOPICANALYSISTABLE, workingDataRow.title);
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
		logAction("ComplianceReportsPageActions.verifyIsotopicTableSortedDescByColumn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyIsotopicTableSortedDescByColumn", ARG_DATA, data);
		List<String[]> isotopicAnalysisTblList = this.getComplianceReportsPage().getSSRSPDFTableValues(
				PDFTable.ISOTOPICANALYSISTABLE, workingDataRow.title);
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
	public boolean verifyIsotopicValueIsFormattedCorrectly(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifyIsotopicValueIsFormattedCorrectly", data, dataRowID);
		List<String[]> isotopicAnalysisTblList = this.getComplianceReportsPage().getSSRSPDFTableValues(
				PDFTable.ISOTOPICANALYSISTABLE, workingDataRow.title);
		String[] isotopicUncertaintyValues = isotopicAnalysisTblList.get(IsotopicAnalysisTableColumns.IsotopicValueUncertainty.getIndex());
		for (String isotopicUncertaintyValue : isotopicUncertaintyValues) {
			if (!this.getComplianceReportsPage().verifyIsotopicValueIsFormattedCorrectly(isotopicUncertaintyValue)) {
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
	public boolean verifyUncertaintyValueIsFormattedCorrectly(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifyUncertaintyValueIsFormattedCorrectly", data, dataRowID);
		List<String[]> isotopicAnalysisTblList = this.getComplianceReportsPage().getSSRSPDFTableValues(
				PDFTable.ISOTOPICANALYSISTABLE, workingDataRow.title);
		String[] isotopicUncertaintyValues = isotopicAnalysisTblList.get(IsotopicAnalysisTableColumns.IsotopicValueUncertainty.getIndex());
		for (String isotopicUncertaintyValue : isotopicUncertaintyValues) {
			if (!this.getComplianceReportsPage().verifyUncertaintyValueIsFormattedCorrectly(isotopicUncertaintyValue)) {
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
		logAction("ComplianceReportsPageActions.verifyViewThumbnailDownloadFromComplianceViewerByViewIndex", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifyViewThumbnailIsShownInComplianceViewerByViewIndex", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(FN_CLICK_ON_COMPLIANCE_VIEWER_VIEW_BY_INDEX, ARG_DATA, data);
		Integer viewIdx = NumberUtility.getIntegerValueOf(data);
		ActionArguments.verifyGreaterThanZero(FN_CLICK_ON_COMPLIANCE_VIEWER_VIEW_BY_INDEX, ARG_DATA, viewIdx);
		
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		// Find the view image to click. Order of the images is the order returned by API. 
		WebElement viewElement = this.getComplianceReportsPage().getViewThumbnailImageByIndex(viewIdx);
		return viewElement.isDisplayed();
	}

	/**
	 * Executes verifyPercentCoverageForecastPresentInReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPercentCoverageForecastPresentInReport(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyPercentCoverageForecastPresentInReport", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifyPercentCoverageAssetsAndReportAreaValuesInReport", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifyAssetsAreDisplayed", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.verifyLISAsIndicationTableRowCountEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyLISAsIndicationTableRowCountEquals", ARG_DATA, data);
		Integer expectedRows = NumberUtility.getIntegerValueOf(data);
		List<String[]> lisasIndicationTblList = this.getComplianceReportsPage().getSSRSPDFTableValues(
				PDFTable.LISAINDICATIONTABLE, workingDataRow.title);
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
		logAction("ComplianceReportsPageActions.verifyLisasTableSortedAscByColumn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyLisasTableSortedAscByColumn", ARG_DATA, data);
		List<String[]> lisasIndicationTblList = this.getComplianceReportsPage().getSSRSPDFTableValues(
				PDFTable.LISAINDICATIONTABLE, workingDataRow.title);
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
		logAction("ComplianceReportsPageActions.verifyLisasTableSortedDescByColumn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyLisasTableSortedDescByColumn", ARG_DATA, data);
		List<String[]> lisasIndicationTblList = this.getComplianceReportsPage().getSSRSPDFTableValues(
				PDFTable.LISAINDICATIONTABLE, workingDataRow.title);
		LISAIndicationTableColumns tableColumn = LISAIndicationTableColumns.valueOf(data);
		List<String> tableValuesList = ArrayUtility.getColumnStringList(lisasIndicationTblList, tableColumn.getIndex());
		return SortHelper.isSortedDESC(tableValuesList.toArray(new String[tableValuesList.size()]));
	}

	/**
	 * Executes verifyWarningMessageOnDeleteButtonClickEquals action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyWarningMessageOnDeleteButtonClickEquals(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyWarningMessageOnDeleteButtonClickEquals", data, dataRowID);
		// clicking on Cancel button in popup will succeed only if the confirm delete popup is showing.
		this.getComplianceReportsPage().clickOnCancelInDeleteReportPopup();
		return true;
	}

	/**
	 * Executes clickOnComplianceViewerPDF action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerPDF(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerPDF", data, dataRowID);
		this.getComplianceReportsPage().clickOnPDFInReportViewer();
		return true;
	}
 
	/**
	 * Executes clickOnComplianceViewerPDFZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerPDFZIP(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerPDFZIP", data, dataRowID);
		this.getComplianceReportsPage().clickOnZIPInReportViewer();
		return true;
	}
 
	/**
	 * Executes clickOnComplianceViewerMetaZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerMetaZIP(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerMetaZIP", data, dataRowID);
		this.getComplianceReportsPage().clickOnMetadataZIPInReportViewer();
		return true;
	}
 
	/**
	 * Executes clickOnComplianceViewerShapeZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerShapeZIP(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerShapeZIP", data, dataRowID);
		this.getComplianceReportsPage().clickOnShapeZIPInReportViewer();
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
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerViewByIndex", data, dataRowID);
		return clickComplianceViewerViewByIndex(data, dataRowID);
	}

	public boolean clickOnCloseReportViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.clickOnCloseReportViewer", data, dataRowID);
		this.getComplianceReportsPage().clickOnCloseReportViewer();
		return true;
	}
	
	/**
	 * Executes clickNoOnChangeReportDialog action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickNoOnChangeReportDialog(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickNoOnChangeReportDialog", data, dataRowID);
		// to be implemented.
		return false;
	}
 
	/**
	 * Executes waitForReportGenerationToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForReportGenerationToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForReportGenerationToComplete", data, dataRowID);
		this.getComplianceReportsPage().waitForPageLoad();
		this.getComplianceReportsPage().waitForReportGenerationtoComplete(workingDataRow.title,
				TestContext.INSTANCE.getLoggedInUser());
		return true;
	}

	/**
	 * Executes waitForComplianceViewerDialogToOpen action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForComplianceViewerDialogToOpen(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForComplianceViewerDialogToOpen", data, dataRowID);
		this.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		return true;
	}

	/**
	 * Executes waitForComplianceViewerDialogToClose action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForComplianceViewerDialogToClose(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForComplianceViewerDialogToClose", data, dataRowID);
		this.getComplianceReportsPage().waitForReportViewerDialogToClose();
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
		logAction("ComplianceReportsPageActions.waitForPDFDownloadToComplete", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.waitForPDFZIPDownloadToComplete", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.waitForMetaZIPDownloadToComplete", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.waitForShapeZIPDownloadToComplete", data, dataRowID);
		waitForReportFileDownload(dataRowID, ReportFileType.ShapeZIP, -1, getDownloadFileIndex(data,2));
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
		logAction("ComplianceReportsPageActions.waitForViewDownloadToCompleteByViewIndex", data, dataRowID);
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
		logAction("ComplianceReportsPageActions.waitForConfirmDeletePopupToShow", data, dataRowID);
		getComplianceReportsPage().waitForConfirmDeletePopupToShow();
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
		logAction("ComplianceReportsPageActions.waitForConfirmDeletePopupToClose", data, dataRowID);
		getComplianceReportsPage().waitForConfirmDeletePopupToClose();
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
		logAction("ComplianceReportsPageActions.extractPDFZIP", data, dataRowID);
		String fileName = this.getComplianceReportsPage().getReportPDFZipFileName(workingDataRow.title, false /*includeExtension*/);
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
		logAction("ComplianceReportsPageActions.extractMetaZIP", data, dataRowID);
		String fileName = this.getComplianceReportsPage().getReportMetaZipFileName(workingDataRow.title, false /*includeExtension*/);
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
		logAction("ComplianceReportsPageActions.extractShapeZIP", data, dataRowID);
		String fileName = this.getComplianceReportsPage().getReportShapeZipFileName(workingDataRow.title, false /*includeExtension*/);
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
		logAction("ComplianceReportsPageActions.verifyReportFilesHaveCorrectData", data, dataRowID);
		return this.getComplianceReportsPage().compareReportJobPerfBaseline(ComplianceReportsPageActions.workingDataRow.tCID, 
				ComplianceReportsPageActions.workingDataRow.title);
	}
	
	/**
	 * Executes verifyAllMetadataFiles action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public boolean verifyAllMetadataFiles(String data, Integer dataRowID) throws FileNotFoundException, IOException {
		logAction("ComplianceReportsPageActions.verifyAllMetadataFiles", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.MetaDataZIP);
		boolean verifyReportSurveyMetaDataFile = this.getComplianceReportsPage().verifyReportSurveyMetaDataFile(downloadPath, workingDataRow.title);
		boolean verifyIsotopicMetaDataFile = this.getComplianceReportsPage().verifyIsotopicMetaDataFile(downloadPath, workingDataRow.title);
		boolean verifyLISASMetaDataFile = this.getComplianceReportsPage().verifyLISASMetaDataFile(downloadPath, workingDataRow.title);
		Log.info(String.format("verifyReportSurveyMetaDataFile = %b; verifyIsotopicMetaDataFile = %b; verifyLISASMetaDataFile = ",
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
		logAction("ComplianceReportsPageActions.verifyAllSSRSTableInfos", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return verifySSRSTableInfos(downloadPath);
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
			verifyCoverageValuesTable = this.getComplianceReportsPage().verifyCoverageValuesTable(downloadPath, workingDataRow.title,
					workingReportsComp.getTablesList().get(0));
			Log.info(String.format("verifyCoverageValuesTable() returned - '%b'", verifyCoverageValuesTable));
			retSuccess = retSuccess && verifyCoverageValuesTable;
			
			Log.info("Executing verifyShowCoverageTable()...");
			verifyShowCoverageTable = this.getComplianceReportsPage().verifyShowCoverageTable(downloadPath, workingDataRow.title);
			Log.info(String.format("verifyShowCoverageTable() returned - '%b'", verifyShowCoverageTable));
			retSuccess = retSuccess && verifyShowCoverageTable;
		}
		
		if (isoAnalysis) {
			Log.info("Executing verifyIsotopicAnalysisTable()...");
			verifyIsotopicAnalysisTable = this.getComplianceReportsPage().verifyIsotopicAnalysisTable(downloadPath, workingDataRow.title);
			Log.info(String.format("verifyIsotopicAnalysisTable() returned - '%b'", verifyIsotopicAnalysisTable));
			retSuccess = retSuccess && verifyIsotopicAnalysisTable;
		}
		
		if (indTable) {
			Log.info("Executing verifyIndicationTable()...");
			verifyIndicationTable = this.getComplianceReportsPage().verifyIndicationTable(downloadPath, workingDataRow.title);
			Log.info(String.format("verifyIndicationTable() returned - '%b'", verifyIndicationTable));
			retSuccess = retSuccess && verifyIndicationTable;
		}
		
		List<String> customersWithAssets = SurveyorConstants.getCustomersWithAssets();
		CustomerDataRow customerDataRow = getCustomerDataRow();
		if (customersWithAssets.contains(customerDataRow.name)) {
			Log.info("Executing verifyLayersTable()...");
			verifyLayersTable = this.getComplianceReportsPage().verifyLayersTable(downloadPath, workingDataRow.title, workingReportsComp.getTablesList().get(0));
			Log.info(String.format("verifyLayersTable() returned - '%b'", verifyLayersTable));
			retSuccess = retSuccess && verifyLayersTable;
		}
			
		Log.info("Executing verifyViewsTable()...");
		verifyViewsTable = this.getComplianceReportsPage().verifyViewsTable(downloadPath, workingDataRow.title, workingReportsComp.getViewList());
		retSuccess = retSuccess && verifyViewsTable;
		Log.info(String.format("verifyViewsTable() returned - '%b'", verifyLayersTable));
		
		Log.info("Executing verifyDrivingSurveysTable()...");
		verifyDrivingSurveysTable = this.getComplianceReportsPage().verifyDrivingSurveysTable(downloadPath, workingDataRow.title);
		retSuccess = retSuccess && verifyDrivingSurveysTable;
		Log.info(String.format("verifyDrivingSurveysTable() returned - '%b'", verifyLayersTable));
		
		Log.info(String.format("verifyCoverageValuesTable = %b; verifyShowCoverageTable = %b; verifyIsotopicAnalysisTable = %b; "
				+ "verifyIndicationTable = %b; verifyLayersTable = %b; verifyViewsTable = %b; verifyDrivingSurveysTable = %b",
				verifyCoverageValuesTable, verifyShowCoverageTable, verifyIsotopicAnalysisTable, verifyIndicationTable, 
				verifyLayersTable, verifyViewsTable, verifyDrivingSurveysTable)); 
		
		return retSuccess;
	}

	/**
	 * Executes verifyBoundariesAutoCompleteListContains action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyBoundariesAutoCompleteListContains(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyBoundariesAutoCompleteListContains", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyBoundariesAutoCompleteListContains", ARG_DATA, data);
		List<String> boundaryNamesList = RegexUtility.split(data, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		ComplianceReportsDataRow complianceReportsDataRow = getComplianceReportsDataRow(dataRowID);
		return this.getComplianceReportsPage().verifyCustomerBoundaryLatLongSelectorAutoCompleteListContains(
				complianceReportsDataRow.customerBoundaryType, complianceReportsDataRow.customerBoundaryName, boundaryNamesList);
	}
 
	/**
	 * Executes verifyGapsTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 */
	public boolean verifyGapsTableInfo(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifyGapsTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifyGapsTable(downloadPath, workingDataRow.title);
	}
 
	/**
	 * Executes verifyIsotopicAnalysisTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 */
	public boolean verifyIsotopicAnalysisTableInfo(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifyIsotopicAnalysisTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifyIsotopicAnalysisTable(downloadPath, workingDataRow.title);
	}
 
	/**
	 * Executes verifyIsotopicMetaDataFile action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public boolean verifyIsotopicMetaDataFile(String data, Integer dataRowID) throws FileNotFoundException, IOException {
		logAction("ComplianceReportsPageActions.verifyIsotopicMetaDataFile", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.MetaDataZIP);
		return this.getComplianceReportsPage().verifyIsotopicMetaDataFile(downloadPath, workingDataRow.title);
	}
 
	/**
	 * Executes verifyLISAsIndicationTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 */
	public boolean verifyLISAsIndicationTableInfo(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifyLISAsIndicationTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifyIndicationTable(downloadPath, workingDataRow.title);
	}
 
	/**
	 * Executes verifyLISAsIndicationTableMinAmplitudeValues action.
	 * @param data - specifies the min amplitude value.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyLISAsIndicationTableMinAmplitudeValues(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyLISAsIndicationTableMinAmplitudeValues", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyLISAsIndicationTableMinAmplitudeValues", ARG_DATA, data);
		List<String[]> lisasIndicationTblList = this.getComplianceReportsPage().getSSRSPDFTableValues(
				PDFTable.LISAINDICATIONTABLE, workingDataRow.title);
		List<String> minAmplitudeValues = ArrayUtility.getColumnStringList(lisasIndicationTblList, LISAIndicationTableColumns.Amplitude.getIndex());
		return ArrayUtility.areValuesGreater(minAmplitudeValues.toArray(new String[minAmplitudeValues.size()]), NumberUtility.getFloatValueOf(data));
	}

	/**
	 * Executes verifyLISASMetaDataFile action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public boolean verifyLISASMetaDataFile(String data, Integer dataRowID) throws FileNotFoundException, IOException {
		logAction("ComplianceReportsPageActions.verifyLISASMetaDataFile", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.MetaDataZIP);
		return this.getComplianceReportsPage().verifyLISASMetaDataFile(downloadPath, workingDataRow.title);
	}
 
	/**
	 * Executes verifyReportDeletedSuccessfully action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyReportDeletedSuccessfully(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyReportDeletedSuccessfully", data, dataRowID);
		return !this.getComplianceReportsPage().searchReport(workingDataRow.title, LoginPageActions.workingDataRow.username);
	}
 
	/**
	 * Executes verifyReportSurveyMetadataFile action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public boolean verifyReportSurveyMetadataFile(String data, Integer dataRowID) throws FileNotFoundException, IOException {
		logAction("ComplianceReportsPageActions.verifyReportSurveyMetadataFile", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.MetaDataZIP);
		return this.getComplianceReportsPage().verifyReportSurveyMetaDataFile(downloadPath, workingDataRow.title);
	}
 
	/**
	 * Executes verifyShapeFilesWithBaselines action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyShapeFilesWithBaselines(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyShapeFilesWithBaselines", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyShapeFilesWithBaselines", ARG_DATA, data);
		ComplianceReportsDataRow complianceReportsDataRow = getComplianceReportsDataRow(dataRowID);
		return this.getComplianceReportsPage().verifyShapeFilesWithBaselines(complianceReportsDataRow.title, 
				complianceReportsDataRow.tCID, getDownloadFileIndex(data,2));
	}
 
	/**
	 * Executes verifySSRSCoverageTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 */
	public boolean verifySSRSCoverageTableInfo(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifySSRSCoverageTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifyCoverageValuesTable(downloadPath, workingDataRow.title,
				workingReportsComp.getTablesList().get(0));
	}
	/**
	 * Executes verifySSRSCoverageForecastTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 */
	public boolean verifySSRSCoverageForecastTableInfo(String data, Integer dataRowID) throws IOException {
		return verifySSRSCoverageForecastTableInfo(data, dataRowID,true);
	}
	public boolean verifySSRSCoverageForecastTableInfo(String data, Integer dataRowID, boolean withPredication) throws IOException {
		logAction("ComplianceReportsPageActions.verifySSRSCoverageForecastTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifyCoverageForecastValuesTable(downloadPath, workingDataRow.title,withPredication);
	} 
	public boolean verifySSRSCoverageForecastTableInfoWithPreviousResult(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifySSRSCoverageForecastTableInfoWithPreviousResult", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifyCoverageForecastValuesTableWithPreviousResult(downloadPath, workingDataRow.title);
	} 
	/**
	 * Executes verifySSRSDrivingSurveyTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 */
	public boolean verifySSRSDrivingSurveyTableInfo(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifySSRSDrivingSurveyTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifyDrivingSurveysTable(downloadPath, workingDataRow.title);
	}
 
	/**
	 * Executes verifySSRSImagesWithBaselines action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifySSRSImagesWithBaselines(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifySSRSImagesWithBaselines", data, dataRowID);
		ComplianceReportsDataRow complianceReportsDataRow = getComplianceReportsDataRow(dataRowID);
		String reportName = this.getComplianceReportsPage().getReportPDFFileName(complianceReportsDataRow.title, false);
		this.getComplianceReportsPage().checkAndGenerateBaselineSSRSImage(reportName, complianceReportsDataRow.tCID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifySSRSImages(downloadPath, complianceReportsDataRow.title, 
				complianceReportsDataRow.tCID);
	}
 
	/**
	 * Executes verifySSRSLayersTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 */
	public boolean verifySSRSLayersTableInfo(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifySSRSLayersTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifyLayersTable(downloadPath, workingDataRow.title,
				workingReportsComp.getTablesList().get(0));
	}
 
	/**
	 * Executes verifySSRSShowCoverageTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 */
	public boolean verifySSRSShowCoverageTableInfo(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifySSRSShowCoverageTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifyShowCoverageTable(downloadPath, workingDataRow.title);
	}
 
	/**
	 * Executes verifySSRSViewsTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 */
	public boolean verifySSRSViewsTableInfo(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifySSRSViewsTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifyViewsTable(downloadPath, workingDataRow.title,
				workingReportsComp.getViewList());
	} 

	/**
	 * Executes verifySurveysTableInfoByTags action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifySurveysTableInfoByTags(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifySurveysTableInfoByTags", data, dataRowID);
		List<Integer> surveyRowIDs = ActionArguments.getNumericList(workingDataRow.reportSurveyRowIDs);
		boolean retVal = true;
		// For each survey tag verify survey table.
		for (Integer surveyRowID : surveyRowIDs) {
			ReportModeFilter reportMode = this.getComplianceReportsPage().getReportMode(workingDataRow.reportMode);
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(this.excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(surveyRowID);
			retVal = retVal && this.getComplianceReportsPage().verifySurveysTableViaTag(true /*changeMode*/, reportMode, surveyDataRow.surveyTag);
		}
		return retVal;
	}
 
	/**
	 * Executes verifyViewsInSSRSPDFAreInCorrectSequence action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyViewsInSSRSPDFAreInCorrectSequence(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyViewsInSSRSPDFAreInCorrectSequence", data, dataRowID);
		List<String> expectedViewNamesList = getViewNamesList(dataRowID);
		return this.getComplianceReportsPage().verifyViewsInSSRSPDFAreInCorrectSequence(
				expectedViewNamesList, workingDataRow.title);
	}

	/**
	 * Executes verifyViewsImagesWithBaselines action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyViewsImagesWithBaselines(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyViewsImagesWithBaselines", data, dataRowID);
		boolean retVal = true;
		
		ComplianceReportsDataRow complianceReportsDataRow = getComplianceReportsDataRow(dataRowID);
		String reportName = this.getComplianceReportsPage().getReportPDFFileName(complianceReportsDataRow.title, false);
		String unzipFolder = TestContext.INSTANCE.getTestSetup().getDownloadPath() + reportName;
		this.getComplianceReportsPage().checkAndGenerateBaselineViewImages(unzipFolder, complianceReportsDataRow.tCID);

		// for each view in the test case verify that the view image is present.
		List<Integer> viewRowIDs = ActionArguments.getNumericList(complianceReportsDataRow.reportViewRowIDs);
		boolean inZipFolder = data.equalsIgnoreCase("false")?false:true;
		for (Integer viewRowID : viewRowIDs) {
			ReportViewsDataReader viewsDataReader = new ReportViewsDataReader(this.excelUtility);
			ReportViewsDataRow viewsDataRow = viewsDataReader.getDataRow(viewRowID);
			retVal = retVal && this.getComplianceReportsPage().verifyViewsImages(TestContext.INSTANCE.getTestSetup().getDownloadPath(), 
					complianceReportsDataRow.title, complianceReportsDataRow.tCID, viewsDataRow.name, inZipFolder);
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
		logAction("ComplianceReportsPageActions.verifyComplianceViewerDialogIsClosed", data, dataRowID);
		this.getComplianceReportsPage().waitForReportViewerDialogToClose();
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
		logAction("ComplianceReportsPageActions.verifyCustomerSpecificAssetsAreDisplayed", data, dataRowID);
		boolean foundAtleastOne = false;
		ComplianceReportsDataRow complianceReportsDataRow = getComplianceReportsDataRow(dataRowID);
		ReportOptViewLayersAssetsDataReader viewLayersAssetsDataReader = new ReportOptViewLayersAssetsDataReader(this.excelUtility);
		Integer customerRowID = Integer.valueOf(complianceReportsDataRow.customerRowID);
		// loop through all the assets and check for presence of checkbox for each asset for the customer.
		for (int idx = 1; idx < viewLayersAssetsDataReader.getRowCount(); idx++) {
			ReportOptViewLayersAssetsDataRow viewLayersAssetsDataRow = viewLayersAssetsDataReader.getDataRow(idx);
			if (customerRowID == Integer.valueOf(viewLayersAssetsDataRow.customerRowID)) {
				foundAtleastOne = true; 
				List<WebElement> assetElements = this.getComplianceReportsPage().getViewLayerAssetCheckboxes(viewLayersAssetsDataRow.assetID);
				if (assetElements.size() <= 0) {
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
		logAction("ComplianceReportsPageActions.verifyCustomerSpecificBoundariesAreDisplayed", data, dataRowID);
		boolean foundAtleastOne = false;
		ComplianceReportsDataRow complianceReportsDataRow = getComplianceReportsDataRow(dataRowID);
		ReportOptViewLayersBoundaryDataReader viewLayersBoundaryDataReader = new ReportOptViewLayersBoundaryDataReader(this.excelUtility);
		Integer customerRowID = Integer.valueOf(complianceReportsDataRow.customerRowID);
		// loop through all the boundaries and check for presence of checkbox for each boundary for the customer.
		for (int idx = 1; idx < viewLayersBoundaryDataReader.getRowCount(); idx++) {
			ReportOptViewLayersBoundaryDataRow viewLayersBoundaryDataRow = viewLayersBoundaryDataReader.getDataRow(idx);
			if (customerRowID == Integer.valueOf(viewLayersBoundaryDataRow.customerRowID)) {
				foundAtleastOne = true; 
				List<WebElement> boundaryElements = this.getComplianceReportsPage().getViewLayerBoundaryCheckboxes(viewLayersBoundaryDataRow.boundaryName);
				if (boundaryElements.size() <= 0) {
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
		logAction("ComplianceReportsPageActions.verifyErrorMessages", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("verifyErrorMessages", ARG_DATA, data);
		List<String> errorMessages = RegexUtility.split(data, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
		return this.getComplianceReportsPage().verifyErrorMessages(errorMessages.toArray(new String[errorMessages.size()]));
	}
 
	/**
	 * Executes verifyGeographicFilterIsSelected action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGeographicFilterIsSelected(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyGeographicFilterIsSelected", data, dataRowID);
		return this.getComplianceReportsPage().isSurveyGeoFilterSelected();
	}
 
	/**
	 * Executes verifyIndicationsTableIsEmpty action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 */
	public boolean verifyIndicationsTableIsEmpty(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifyIndicationsTableIsEmpty", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		List<String> expectedStrings = new ArrayList<String>();
		expectedStrings.add(Resources.getResource(ResourceKeys.ReportSSRS_NoLisaRecordMsg));
		return this.getComplianceReportsPage().verifySSRSPDFContainsText(downloadPath, workingDataRow.title, expectedStrings);
	}
 
	/**
	 * Executes verifyIsotopicTableIsEmpty action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws IOException 
	 */
	public boolean verifyIsotopicTableIsEmpty(String data, Integer dataRowID) throws IOException {
		logAction("ComplianceReportsPageActions.verifyIsotopicTableIsEmpty", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		List<String> expectedStrings = new ArrayList<String>();
		expectedStrings.add(Resources.getResource(ResourceKeys.ComplianceReportSSRS_NoIsotopicMsg));
		return this.getComplianceReportsPage().verifySSRSPDFContainsText(downloadPath, workingDataRow.title, expectedStrings);
	}

	/**
	 * Executes verifySurveyModeFiltersByReportMode action.
	 * @param data - report mode as specified in getReportMode(). for eg. standard|assessment|eq|operator|manual|rr.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifySurveyModeFiltersByReportMode(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifySurveyModeFiltersByReportMode", data, dataRowID);
		boolean retVal = false;
		if (dataRowID > 0) {
			ComplianceReportsDataRow reportsDataRow = this.getComplianceReportsDataRow(dataRowID);
			retVal = this.getComplianceReportsPage().verifySurveyModeFilters(this.getComplianceReportsPage().getReportMode(reportsDataRow.reportMode));
		} else {
			ActionArguments.verifyNotNullOrEmpty("verifySurveyModeFiltersByReportMode", ARG_DATA, data);
			retVal = this.getComplianceReportsPage().verifySurveyModeFilters(this.getComplianceReportsPage().getReportMode(data));
		}
		return retVal;
	}
	
	/**
	 * Executes verifyPageLoaded action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPageLoaded(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyPageLoaded", data, dataRowID);
		this.getComplianceReportsPage().waitForPageLoad();
		return true;
	}

	/**
	 * Executes verifyNewPageLoaded action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyNewPageLoaded(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyNewPageLoaded", data, dataRowID);
		this.getComplianceReportsPage().waitForNewPageLoad();
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
		logAction("ComplianceReportsPageActions.verifyReportFilesArePresent", data, dataRowID);
		ComplianceReportsDataRow reportsDataRow = this.getComplianceReportsDataRow(dataRowID);
		return this.getComplianceReportsPage().checkActionStatus(reportsDataRow.title, 
				LoginPageActions.workingDataRow.username, reportsDataRow.tCID);
	}

	/**
	 * Executes verifyStandardSurveyModeIsShownOnPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyStandardSurveyModeIsShownOnPage(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyStandardSurveyModeIsShownOnPage", data, dataRowID);
		return this.getComplianceReportsPage().isStandardSurveyModeShown();
	}
 
	/**
	 * Executes verifyRapidResponseSurveyModeIsShownOnPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyRapidResponseSurveyModeIsShownOnPage(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyRapidResponseSurveyModeIsShownOnPage", data, dataRowID);
		return this.getComplianceReportsPage().isRapidResponseSurveyModeShown();
	}
	
	/**
	 * Executes verifyManualSurveyModeIsShownOnPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyManualSurveyModeIsShownOnPage(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyManualSurveyModeIsShownOnPage", data, dataRowID);
		return this.getComplianceReportsPage().isManualSurveyModeShown();
	}
 
	/**
	 * Executes verifyOperatorSurveyModeIsShownOnPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyOperatorSurveyModeIsShownOnPage(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyOperatorSurveyModeIsShownOnPage", data, dataRowID);
		return this.getComplianceReportsPage().isOperatorSurveyModeShown();
	}
 
	/**
	 * Executes verifyReportGenerationIsCancelled action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyReportGenerationIsCancelled(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyReportGenerationIsCancelled", data, dataRowID);
		return this.getComplianceReportsPage().checkComplianceReportButtonPresenceAndClick(workingDataRow.title, 
				LoginPageActions.workingDataRow.username, ComplianceReportButtonType.ReportErrorLabel, false /*clickButton*/, false /*confirmAction*/);
	}
 
	/**
	 * Executes verifySSRSPDFFooter action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifySSRSPDFFooter(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifySSRSPDFFooter", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		String expectedSoftwareVersion = TestContext.INSTANCE.getTestSetup().getSoftwareVersion();
		return this.getComplianceReportsPage().verifySSRSPDFFooter(downloadPath, 
				workingDataRow.title, expectedSoftwareVersion , LoginPageActions.workingDataRow.username);
	}
 
	/**
	 * Executes verifySelectedSurveysAreForSpecifiedCustomer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifySearchedSurveysAreForSpecifiedCustomer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifySelectedSurveysAreForSpecifiedCustomer", data, dataRowID);
		List<String> surveyUsernames = this.getComplianceReportsPage().getSelectedSurveyTableValuesForColumn(ReportsSurveyInfo.ColumnHeaders.User);
		List<String> distinctUsernames = ArrayUtility.getDistinctValues(surveyUsernames);
		// for each distinct user check user belongs to the specified customer.
		Integer customerRowID = Integer.valueOf(getComplianceReportsDataRow(dataRowID).customerRowID);
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
	 * Executes verifySearchedSurveysMatchSelectedMode action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifySearchedSurveysMatchSelectedMode(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifySearchedSurveysMatchSelectedMode", data, dataRowID);
		List<String> surveyModes = this.getComplianceReportsPage().getSelectedSurveyTableValuesForColumn(ReportsSurveyInfo.ColumnHeaders.SurveyType);
		List<String> distinctSurveyModes = ArrayUtility.getDistinctValues(surveyModes);
		ComplianceReportsDataRow reportsDataRow = getComplianceReportsDataRow(dataRowID);
		return (distinctSurveyModes != null && distinctSurveyModes.size()==1 && distinctSurveyModes.get(0).equalsIgnoreCase(reportsDataRow.reportMode));
	}
 
	/**
	 * Executes verifySearchedSurveysAreForSelectedArea action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysAreForSelectedArea(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifySearchedSurveysAreForSelectedArea", data, dataRowID);
		// TODO: Add implementation.
		return false;
	}
 
	/**
	 * Executes verifySurveyGreaterThan100HoursCannotBeAdded action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySurveyGreaterThan100HoursCannotBeAdded(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifySurveyGreaterThan100HoursCannotBeAdded", data, dataRowID);
		// TODO: Add implementation.
		return false;
	}

	/**
	/* END - Actions on the Page*/

	public List<Float> getMinAmplitudesForSurveys(Integer reportDataRowID) throws Exception {
		List<Float> minAmps = new ArrayList<Float>();
		ComplianceReportDataReader reportDataReader = new ComplianceReportDataReader(excelUtility);
		ComplianceReportsDataRow reportsDataRow = reportDataReader.getDataRow(reportDataRowID);
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
		else if (actionName.equals("clickOnFirstInvestigateComplianceButton")) { return this.clickOnFirstInvestigateComplianceButton(data, dataRowID); }
		else if (actionName.equals("clickOnInvestigateButton")) { return this.clickOnInvestigateButton(data, dataRowID); }
		else if (actionName.equals("clickOnInvestigatePDFButton")) { return this.clickOnInvestigatePDFButton(data, dataRowID); }
		else if (actionName.equals("clickOnLatLongSelectorButton")) { return this.clickOnLatLongSelectorButton(data, dataRowID); }
		else if (actionName.equals("clickOnOKButton")) { return this.clickOnOKButton(data, dataRowID); }
		else if (actionName.equals("clickOnNewComplianceReport")) { return this.clickOnNewComplianceReport(data, dataRowID); }
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
		else if (actionName.equals("enterLISAOpacity")) { return this.enterLISAOpacity(data, dataRowID); }
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
		else if (actionName.equals("investigateReport")) { return this.investigateReport(data, dataRowID); }
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
		else if (actionName.equals("selectPercentCoverageForecastCheckBox")) { return this.selectPercentCoverageForecastCheckBox(data, dataRowID); }
		else if (actionName.equals("selectPercentCoverageReportAreaCheckBox")) { return this.selectPercentCoverageReportAreaCheckBox(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("selectReportMode")) { return this.selectReportMode(data, dataRowID); }
		else if (actionName.equals("selectShowPercentCoverageOfAssetsCheckBox")) { return this.selectShowPercentCoverageOfAssetsCheckBox(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorEndDateTime")) { return this.selectSurveySelectorEndDateTime(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorStartDateTime")) { return this.selectSurveySelectorStartDateTime(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorSurveyModeFilter")) { return this.selectSurveySelectorSurveyModeFilter(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorSurveyor")) { return this.selectSurveySelectorSurveyor(data, dataRowID); }
		else if (actionName.equals("selectTabularPDFContent")) { return this.selectTabularPDFContent(data, dataRowID); }
		else if (actionName.equals("selectTimeZone")) { return this.selectTimeZone(data, dataRowID); }
		else if (actionName.equals("selectViewLayersAsset")) { return this.selectViewLayersAsset(data, dataRowID); }
		else if (actionName.equals("selectViewLayersBoundary")) { return this.selectViewLayersBoundary(data, dataRowID); }
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
		else if (actionName.equals("verifyInvestigatePDFDownload")) { return this.verifyInvestigatePDFDownload(data, dataRowID); }
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
		else if (actionName.equals("verifySurveysTableInfoByTags")) { return this.verifySurveysTableInfoByTags(data, dataRowID); }
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
		else if (actionName.equals("verifyReportFilesArePresent")) { return this.verifyReportFilesArePresent(data, dataRowID); }
		else if (actionName.equals("verifyReportGenerationIsCancelled")) { return this.verifyReportGenerationIsCancelled(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysAreForSelectedArea")) { return this.verifySearchedSurveysAreForSelectedArea(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysMatchSelectedMode")) { return this.verifySearchedSurveysMatchSelectedMode(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysAreForSpecifiedCustomer")) { return this.verifySearchedSurveysAreForSpecifiedCustomer(data, dataRowID); }
		else if (actionName.equals("verifySSRSPDFFooter")) { return this.verifySSRSPDFFooter(data, dataRowID); }
		else if (actionName.equals("verifyStandardSurveyModeIsShownOnPage")) { return this.verifyStandardSurveyModeIsShownOnPage(data, dataRowID); }
		else if (actionName.equals("verifyRapidResponseSurveyModeIsShownOnPage")) { return this.verifyRapidResponseSurveyModeIsShownOnPage(data, dataRowID); }
		else if (actionName.equals("verifyManualSurveyModeIsShownOnPage")) { return this.verifyManualSurveyModeIsShownOnPage(data, dataRowID); }
		else if (actionName.equals("verifyOperatorSurveyModeIsShownOnPage")) { return this.verifyOperatorSurveyModeIsShownOnPage(data, dataRowID); }
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
		else if (actionName.equals("verifySSRSCoverageForecastTableInfo")) { return this.verifySSRSCoverageForecastTableInfo(data, dataRowID); }
		else if (actionName.equals("verifySSRSCoverageForecastTableInfoWithPreviousResult")) { return this.verifySSRSCoverageForecastTableInfoWithPreviousResult(data, dataRowID); }
		return false;
	}

	public ComplianceReportDataReader getDataReader() {
		if (dataReader == null) {
			setDataReader(new ComplianceReportDataReader(this.excelUtility));
		}
		return dataReader;
	}

	public void setDataReader(ComplianceReportDataReader dataReader) {
		this.dataReader = dataReader;
	}

	public ComplianceReportsPage getComplianceReportsPage() {
		return (ComplianceReportsPage)this.pageObject;
	}
}
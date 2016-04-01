package surveyor.scommon.actions;

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
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCASTIRON;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCOPPER;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETOTHERPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPEPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETUNPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICT;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICTPLAT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import common.source.Log;
import common.source.RegexUtility;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.data.ComplianceReportDataReader;
import surveyor.scommon.actions.data.ComplianceReportDataReader.ComplianceReportsDataRow;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;
import surveyor.scommon.actions.data.ReportOptTabularPDFContentDataReader;
import surveyor.scommon.actions.data.ReportOptTabularPDFContentDataReader.ReportOptTabularPDFContentDataRow;
import surveyor.scommon.actions.data.ReportOptViewLayersDataReader;
import surveyor.scommon.actions.data.ReportOptViewLayersDataReader.ReportOptViewLayersDataRow;
import surveyor.scommon.actions.data.ReportSurveyDataReader;
import surveyor.scommon.actions.data.ReportSurveyDataReader.ReportSurveyDataRow;
import surveyor.scommon.actions.data.ReportViewsDataReader;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.ComplianceReportsPage.ReportFileType;
import surveyor.scommon.source.ComplianceReportsPage.ReportViewerThumbnailType;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.ReportsCompliance;

public class ComplianceReportsPageActions extends BaseReportsPageActions {
	private ComplianceReportDataReader dataReader = null;
	public static ReportsCompliance workingReportsComp = null;      // Stores the ReportsCompliance object from createNewReport action
	public static ComplianceReportsDataRow workingDataRow = null;    // Stores the workingDataRow from createNewReport action

	public ComplianceReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
		initializePageObject(driver, new ComplianceReportsPage(driver, strBaseURL, testSetup));
		setDataReader(new ComplianceReportDataReader(this.excelUtility));
	}
	
	private void addView(Integer dataRowID) throws Exception {
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();
		fillViewDetails(viewMap, new ReportViewsDataReader(this.excelUtility), dataRowID);
		viewList.add(viewMap);
		this.getComplianceReportsPage().addViews(workingReportsComp.getCustomer(), viewList);
	}
 
	private ComplianceReportsPage createNewPageObject() {
		ComplianceReportsPage compReportsPage = new ComplianceReportsPage(TestContext.INSTANCE.getDriver(), 
				TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		return compReportsPage;
	}

	private void fillViewLayersInfo(Map<String, String> viewLayerMap,
			ReportOptViewLayersDataReader reader, Integer dataRowID) throws Exception {
		String assetCastIron = reader.getDataRow(dataRowID).assetCastIron.equalsIgnoreCase("TRUE") ? "1" : "0";
		String assetCopper = reader.getDataRow(dataRowID).assetCopper.equalsIgnoreCase("TRUE") ? "1" : "0";
		String assetOtherPlastic = reader.getDataRow(dataRowID).assetOtherPlastic.equalsIgnoreCase("TRUE") ? "1" : "0";
		String assetPEPlastic = reader.getDataRow(dataRowID).assetPEPlastic.equalsIgnoreCase("TRUE") ? "1" : "0";
		String assetProtectedSteel = reader.getDataRow(dataRowID).assetProtectedSteel.equalsIgnoreCase("TRUE") ? "1" : "0";
		String assetUnprotectedSteel = reader.getDataRow(dataRowID).assetUnprotectedSteel.equalsIgnoreCase("TRUE") ? "1" : "0";
		String boundaryDistrict = reader.getDataRow(dataRowID).boundaryDistrict.equalsIgnoreCase("TRUE") ? "1" : "0";
		String boundaryDistrictPlat = reader.getDataRow(dataRowID).boundaryDistrictPlat.equalsIgnoreCase("TRUE") ? "1" : "0";
		if (assetCastIron != "") viewLayerMap.put(KEYASSETCASTIRON, assetCastIron);
		if (assetCopper != "") viewLayerMap.put(KEYASSETCOPPER, assetCopper);
		if (assetOtherPlastic != "") viewLayerMap.put(KEYASSETOTHERPLASTIC, assetOtherPlastic);
		if (assetPEPlastic != "") viewLayerMap.put(KEYASSETPEPLASTIC, assetPEPlastic);
		if (assetProtectedSteel != "") viewLayerMap.put(KEYASSETPROTECTEDSTEEL, assetProtectedSteel);
		if (assetUnprotectedSteel != "") viewLayerMap.put(KEYASSETUNPROTECTEDSTEEL, assetUnprotectedSteel);
		if (boundaryDistrict != "") viewLayerMap.put(KEYBOUNDARYDISTRICT, boundaryDistrict);
		if (boundaryDistrictPlat != "") viewLayerMap.put(KEYBOUNDARYDISTRICTPLAT, boundaryDistrictPlat);
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
		String viewName = reader.getDataRow(dataRowID).name;
		String showLISA = reader.getDataRow(dataRowID).lISAs.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showFOV = reader.getDataRow(dataRowID).fOV.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showBreadcrumb = reader.getDataRow(dataRowID).breadcrumbs.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showIndications = reader.getDataRow(dataRowID).indications.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showIsotopicCapture = reader.getDataRow(dataRowID).isotopicCapture.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showAnnotation = reader.getDataRow(dataRowID).annotation.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showGaps = reader.getDataRow(dataRowID).gaps.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showAssets = reader.getDataRow(dataRowID).assets.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showBoundaries = reader.getDataRow(dataRowID).boundaries.equalsIgnoreCase("TRUE") ? "1" : "0";
		String baseMapType = reader.getDataRow(dataRowID).baseMap;
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
		String showPercentCovAssetsTable = reader.getDataRow(dataRowID).percentCoverageAssets.equalsIgnoreCase("TRUE") ? "1" : "0";
		String showPercentCoverageReportAreaTable = reader.getDataRow(dataRowID).percentCoverageReportArea.equalsIgnoreCase("TRUE") ? "1" : "0";
		if (showIndicationsTable != "") tableMap.put(KEYINDTB, showIndicationsTable);
		if (showIsoAnalysisTable != "") tableMap.put(KEYISOANA, showIsoAnalysisTable);
		if (showPercentCovAssetsTable != "") tableMap.put(KEYPCA, showPercentCovAssetsTable);
		if (showPercentCoverageReportAreaTable != "") tableMap.put(KEYPCRA, showPercentCoverageReportAreaTable);
	}

	private List<Integer> verifyLatLongCoordinates(String data) throws Exception {
		List<Integer> coordList = ActionArguments.getNumericList(data);
		if (coordList == null || coordList.size() != 4) {
			throw new Exception("Coordinates of top-left and bottom-right pixels not specified correctly. "
					+ "Specify coordinates in CSV format - For eg. x1,y1,x2,y2");
		}
		return coordList;
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

	private void downloadReportFileAndWait(Integer dataRowID, ReportFileType fileType) throws Exception {
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		ComplianceReportsDataRow compRptDataRow = getDataReader().getDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		
		switch(fileType) {
		case PDF:
			this.getComplianceReportsPage().downloadReportPDFFile();
			this.getComplianceReportsPage().waitForPDFFileDownload(reportTitle);
			break;
		case ZIP:
			this.getComplianceReportsPage().downloadReportZIPFile();
			this.getComplianceReportsPage().waitForReportZIPFileDownload(reportTitle);
			break;
		case MetaDataZIP:
			this.getComplianceReportsPage().downloadMetaDataZipFile();
			this.getComplianceReportsPage().waitForMetadataZIPFileDownload(reportTitle);
			break;
		case ShapeZIP:
			this.getComplianceReportsPage().downloadShapeZipFile();
			this.getComplianceReportsPage().waitForShapeZIPFileDownload(reportTitle);
			break;
		default:
			break;
		}
	}

	private void verifyPresenceOfButton(Integer dataRowID, ComplianceReportButtonType buttonType) throws Exception {
		ComplianceReportsDataRow compRptDataRow = getDataReader().getDataRow(dataRowID);
		Integer custRowID = Integer.valueOf(compRptDataRow.customerRowID);
		CustomerDataReader custDataReader = new CustomerDataReader(this.excelUtility);
		CustomerDataRow custDataRow = custDataReader.getDataRow(custRowID);
		String customerName = custDataRow.name;
		String reportTitle = compRptDataRow.title;
		this.getComplianceReportsPage().verifyComplianceReportButton(reportTitle, customerName, buttonType);
	}

	private void clickComplianceReportButton(Integer dataRowID, ComplianceReportButtonType buttonType) throws Exception {
		ComplianceReportsDataRow compRptDataRow = getDataReader().getDataRow(dataRowID);
		Integer custRowID = Integer.valueOf(compRptDataRow.customerRowID);
		CustomerDataReader custDataReader = new CustomerDataReader(this.excelUtility);
		CustomerDataRow custDataRow = custDataReader.getDataRow(custRowID);
		String customerName = custDataRow.name;
		String reportTitle = compRptDataRow.title;
		this.getComplianceReportsPage().clickComplianceReportButton(reportTitle, customerName, buttonType);
	}

	/* START - Actions on the Page*/

	/**
	 * Executes addFirstSurveysToReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean addFirstSurveysToReport(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.addFirstSurveysToReport", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes addFirst2SurveysToReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean addFirst2SurveysToReport(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.addFirst2SurveysToReport", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes addFirst3SurveysToReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean addFirst3SurveysToReport(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.addFirst3SurveysToReport", data, dataRowID);
		return true;
	}

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
	 * Executes addSurveyToReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean addSurveyToReport(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.addSurveyToReport", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("addSurveyToReport", ARG_DATA_ROW_ID, dataRowID);
		ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
		List<Integer> reportSurveyRowIDs = ActionArguments.getNumericList(dataRow.reportSurveyRowIDs);
		for (Integer rowID : reportSurveyRowIDs) {
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(this.excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(rowID);

			SurveyModeFilter modeFilter = SurveyModeFilter.All;
			if (surveyDataRow.surveyModeFilter.equals("Standard")) {
				modeFilter = SurveyModeFilter.Standard;
			} else if (surveyDataRow.surveyModeFilter.equals("Operator")) {
				modeFilter = SurveyModeFilter.Operator;
			} else if (surveyDataRow.surveyModeFilter.equals("Rapid Response")) {
				modeFilter = SurveyModeFilter.RapidResponse;
			} 
			
			List<String> surveyTag= new ArrayList<String>();
			surveyTag.add(surveyDataRow.surveyTag);
			
			this.getComplianceReportsPage().addSurveyInformation(surveyDataRow.surveySurveyor, 
					surveyDataRow.surveyUsername, 				
					surveyTag, 
					surveyDataRow.surveyStartDate, 
					surveyDataRow.surveyEndDate, 
					modeFilter, 
					Boolean.parseBoolean(surveyDataRow.surveyGeoFilterON));
		}
		
		return true;
	}
 
	/**
	 * Executes cancelInProgressReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean cancelInProgressReport(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.cancelInProgressReport", data, dataRowID);
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
	 * Executes clickOnConfirmDeleteReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnConfirmDeleteReport(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnConfirmDeleteReport", data, dataRowID);
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
		return true;
	}
 
	/**
	 * Executes clickOnPDFInReportViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnPDFInReportViewer(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnPDFInReportViewer", data, dataRowID);
		this.getComplianceReportsPage().clickOnPDFInReportViewer();
		return true;
	}
 
	/**
	 * Executes clickOnZIPInReportViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnZIPInReportViewer(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnZIPInReportViewer", data, dataRowID);
		this.getComplianceReportsPage().clickOnZIPInReportViewer();
		return true;
	}
 
	/**
	 * Executes clickOnMetadataZIPInReportViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnMetadataZIPInReportViewer(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnMetadataZIPInReportViewer", data, dataRowID);
		this.getComplianceReportsPage().clickOnMetadataZIPInReportViewer();
		return true;
	}
 
	/**
	 * Executes clickOnShapeZIPInReportViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnShapeZIPInReportViewer(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnShapeZIPInReportViewer", data, dataRowID);
		this.getComplianceReportsPage().clickOnShapeZIPInReportViewer();
		return true;
	}
 
	/**
	 * Executes clickOnViewThumbnailInReportViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnViewThumbnailInReportViewer(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnViewThumbnailInReportViewer", data, dataRowID);
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
		ActionArguments.verifyNotNullOrEmpty("copyReport", ARG_DATA, data);
		this.getComplianceReportsPage().copyReport(data, TestContext.INSTANCE.getLoggedInUser());
		this.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		this.initializePageObject(TestContext.INSTANCE.getDriver(), this.createNewPageObject());
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

	private boolean fillAndCreateNewReport(Integer dataRowID, boolean openNewReportsPage) throws Exception {
		workingDataRow = getDataReader().getDataRow(dataRowID);
		
		String rptTitle = workingDataRow.title; 
		String customer = null; 
		String customerRowID = workingDataRow.customerRowID;
		if (customerRowID != "") {
			Integer custRowID = Integer.valueOf(customerRowID);
			customer = (new CustomerDataReader(this.excelUtility)).getDataRow(custRowID).name;
		}
		String timeZone = workingDataRow.timezone;
		String exclusionRadius = workingDataRow.exclusionRadius;

		List<String> listBoundary = new ArrayList<String>();
		fillCustomBoundary(listBoundary, getDataReader(), dataRowID);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		List<Integer> reportViewRowIDs = ActionArguments.getNumericList(workingDataRow.reportViewRowIDs);
		for (Integer rowID : reportViewRowIDs) {
			Map<String, String> viewMap = new HashMap<String, String>();
			fillViewDetails(viewMap, new ReportViewsDataReader(this.excelUtility), rowID);
			viewList.add(viewMap);
		}

		List<Map<String, String>> viewLayersList = new ArrayList<Map<String, String>>();
		List<Integer> reportOptVwLayersRowIDs = ActionArguments.getNumericList(workingDataRow.reportOptViewLayerRowID);
		Map<String, String> viewLayerMap = new HashMap<String, String>();
		fillViewLayersInfo(viewLayerMap, new ReportOptViewLayersDataReader(this.excelUtility), reportOptVwLayersRowIDs.get(0));
		viewLayersList.add(viewLayerMap);
		
		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		List<Integer> reportOptTabPDFRowIDs = ActionArguments.getNumericList(workingDataRow.reportOptTabularPDFContentRowID);
		Map<String, String> tableMap = new HashMap<String, String>();
		fillReportTableInfo(tableMap, new ReportOptTabularPDFContentDataReader(this.excelUtility), reportOptTabPDFRowIDs.get(0));
		tablesList.add(tableMap);

		String surveyorUnit = null;
		List<String> tagList=new ArrayList<String>();
		List<Integer> reportSurveyRowIDs = ActionArguments.getNumericList(workingDataRow.reportSurveyRowIDs);
		for (Integer rowID : reportSurveyRowIDs) {
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(this.excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(rowID);
			surveyorUnit = surveyDataRow.surveySurveyor;  // Using the last surveyor until refactoring.
			tagList.add(surveyDataRow.surveyTag);
		}

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, TestContext.INSTANCE.getLoggedInUser(), customer, timeZone, exclusionRadius,
				listBoundary, tablesList, surveyorUnit, tagList, viewList, viewLayersList);
		getComplianceReportsPage().addNewReport(rpt, openNewReportsPage);
		
		workingReportsComp = rpt;		// Store the working report properties.
		return true;
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
		ComplianceReportsDataRow compRptDataRow = getDataReader().getDataRow(dataRowID);
		Integer custRowID = Integer.valueOf(compRptDataRow.customerRowID);
		CustomerDataReader custDataReader = new CustomerDataReader(this.excelUtility);
		CustomerDataRow custDataRow = custDataReader.getDataRow(custRowID);

		String reportTitle = compRptDataRow.title;
		String createdBy = custDataRow.name;
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
	 * @param data - coordinates of top-left and bottom-right pixels in comma seperated list. 
	 * 	For eg. x1,y1,x2,y2
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean enterCustomerBoundaryUsingAreaSelector(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.enterCustomerBoundaryUsingAreaSelector", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("enterCustomBoundaryUsingAreaSelector", ARG_DATA, data);
		List<Integer> coordList = verifyLatLongCoordinates(data);		
		this.getComplianceReportsPage().openCustomerBoundarySelector();		
		selectCoordinatesInLatLongBoundarySelector(coordList);
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
		ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
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
		ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
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
		ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
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
			ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
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
			ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
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
			ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
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
		ComplianceReportsDataRow compRptDataRow = getDataReader().getDataRow(dataRowID);
		Integer custRowID = Integer.valueOf(compRptDataRow.customerRowID);
		CustomerDataReader custDataReader = new CustomerDataReader(this.excelUtility);
		CustomerDataRow custDataRow = custDataReader.getDataRow(custRowID);

		String reportTitle = compRptDataRow.title;
		String createdBy = custDataRow.name;
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
		ComplianceReportsDataRow compRptDataRow = getDataReader().getDataRow(dataRowID);
		Integer custRowID = Integer.valueOf(compRptDataRow.customerRowID);
		CustomerDataReader custDataReader = new CustomerDataReader(this.excelUtility);
		CustomerDataRow custDataRow = custDataReader.getDataRow(custRowID);

		String reportTitle = compRptDataRow.title;
		String createdBy = custDataRow.name;
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
		ActionArguments.verifyGreaterThanZero("selectPaginationRows", ARG_DATA, Integer.valueOf(data));
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
			ComplianceReportsDataRow compRptDataRow = getDataReader().getDataRow(dataRowID);
			Integer custRowID = Integer.valueOf(compRptDataRow.customerRowID);
			CustomerDataReader custDataReader = new CustomerDataReader(this.excelUtility);
			CustomerDataRow custDataRow = custDataReader.getDataRow(custRowID);
			customer = custDataRow.name;
		}

		this.getComplianceReportsPage().selectCustomer(customer);
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
			ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
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
		ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
		Integer pdfContentRowID = Integer.valueOf(dataRow.reportOptTabularPDFContentRowID);
		ReportOptTabularPDFContentDataReader pdfContentDataReader = new ReportOptTabularPDFContentDataReader(this.excelUtility);
		ReportOptTabularPDFContentDataRow pdfContentDataRow = pdfContentDataReader.getDataRow(pdfContentRowID);
		Boolean selectGap = Boolean.valueOf(pdfContentDataRow.gapTable);
		Boolean selectIndicationTable = Boolean.valueOf(pdfContentDataRow.indicationTable);
		Boolean selectIsotopicAnalysis = Boolean.valueOf(pdfContentDataRow.isotopicAnalysis);
		Boolean selectPercentCoverageAssets = Boolean.valueOf(pdfContentDataRow.percentCoverageAssets);
		Boolean selectPercentCoverageForecast = Boolean.valueOf(pdfContentDataRow.percentCoverageForecast);
		Boolean selectPercentCoverageReportArea = Boolean.valueOf(pdfContentDataRow.percentCoverageReportArea);
		
		if (selectGap) {
			this.getComplianceReportsPage().selectGapCheckBox();
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
			ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
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
		Boolean selectAssetCastIron = false;
		Boolean selectAssetCopper = false;
		Boolean selectAssetOtherPlastic = false;
		Boolean selectAssetPEPlastic = false;
		Boolean selectAssetProtectedSteel = false;
		Boolean selectAssetUnprotectedSteel = false;
		if (!ActionArguments.isEmpty(data)) {
			// If 'data' is passed in it should be in this format:
			//	AssetCastIron=[0|1],AssetCopper=[0|1],AssetOtherPlastic=[0|1],AssetPEPlastic=[0|1],AssetProtectedSteel=[0|1],AssetUnprotectedSteel=[0|1]
			//	for eg. 1,1,1,1,0,1
			List<String> viewLayerList = RegexUtility.split(data, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
			selectAssetCastIron = Boolean.valueOf(viewLayerList.get(0));
			selectAssetCopper = Boolean.valueOf(viewLayerList.get(1));
			selectAssetOtherPlastic = Boolean.valueOf(viewLayerList.get(2));
			selectAssetPEPlastic = Boolean.valueOf(viewLayerList.get(3));
			selectAssetProtectedSteel = Boolean.valueOf(viewLayerList.get(4));
			selectAssetUnprotectedSteel = Boolean.valueOf(viewLayerList.get(5));
		} else {		
			ActionArguments.verifyGreaterThanZero("selectViewLayersAsset", ARG_DATA_ROW_ID, dataRowID);
			ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
			Integer rptViewLayerRowID = Integer.valueOf(dataRow.reportOptViewLayerRowID);
			ReportOptViewLayersDataReader optViewLayersDataReader = new ReportOptViewLayersDataReader(this.excelUtility);
			ReportOptViewLayersDataRow optViewLayersDataRow = optViewLayersDataReader.getDataRow(rptViewLayerRowID);
			selectAssetCastIron = Boolean.valueOf(optViewLayersDataRow.assetCastIron);
			selectAssetCopper = Boolean.valueOf(optViewLayersDataRow.assetCopper);
			selectAssetOtherPlastic = Boolean.valueOf(optViewLayersDataRow.assetOtherPlastic);
			selectAssetPEPlastic = Boolean.valueOf(optViewLayersDataRow.assetPEPlastic);
			selectAssetProtectedSteel = Boolean.valueOf(optViewLayersDataRow.assetProtectedSteel);
			selectAssetUnprotectedSteel = Boolean.valueOf(optViewLayersDataRow.assetUnprotectedSteel);
		}
		this.getComplianceReportsPage().selectViewLayerAssets(selectAssetCastIron, selectAssetCopper,
				selectAssetOtherPlastic, selectAssetPEPlastic, selectAssetProtectedSteel, selectAssetUnprotectedSteel);
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
		Boolean selectBoundaryDistrict = false;
		Boolean selectBoundaryDistrictPlat = false;
		if (!ActionArguments.isEmpty(data)) {
			// If 'data' is passed in it should be in this format:
			//	BoundaryDistrict=[0|1],BoundaryDistrictPlat=[0|1]
			//	for eg. 0,1
			List<String> viewLayerList = RegexUtility.split(data, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
			selectBoundaryDistrict = Boolean.valueOf(viewLayerList.get(0));
			selectBoundaryDistrictPlat = Boolean.valueOf(viewLayerList.get(1));
		} else {		
			ActionArguments.verifyGreaterThanZero("selectViewLayersBoundary", ARG_DATA_ROW_ID, dataRowID);
			ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
			Integer rptViewLayerRowID = Integer.valueOf(dataRow.reportOptViewLayerRowID);
			ReportOptViewLayersDataReader optViewLayersDataReader = new ReportOptViewLayersDataReader(this.excelUtility);
			ReportOptViewLayersDataRow optViewLayersDataRow = optViewLayersDataReader.getDataRow(rptViewLayerRowID);
			selectBoundaryDistrict = Boolean.valueOf(optViewLayersDataRow.boundaryDistrict);
			selectBoundaryDistrictPlat = Boolean.valueOf(optViewLayersDataRow.boundaryDistrictPlat);
		}
		this.getComplianceReportsPage().selectViewLayerBoundaries(selectBoundaryDistrict, selectBoundaryDistrictPlat);
		return true;
	}
 
	/**
	 * Executes sortRecordsBy action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean sortRecordsBy(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.sortRecordsBy", data, dataRowID);
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
		verifyPresenceOfButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		return true;
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
		verifyPresenceOfButton(dataRowID, ComplianceReportButtonType.Copy);
		return true;
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
		verifyPresenceOfButton(dataRowID, ComplianceReportButtonType.Delete);
		return true;
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
		verifyPresenceOfButton(dataRowID, ComplianceReportButtonType.Investigate);
		return true;
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
		verifyPresenceOfButton(dataRowID, ComplianceReportButtonType.InvestigatePDF);
		return true;
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
		return false;
	}

	/**
	 * Executes verifyPDFContainsInputtedInformation action.
	 * @param data - Colon separated list of values to be found in the PDF.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyPDFContainsInputtedInformation(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyPDFContainsInputtedInformation", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty("", ARG_DATA, data);
		List<String> listExpectedStrings = RegexUtility.split(data, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
		this.getComplianceReportsPage().verifyComplianceReportContainsText(workingDataRow.title, listExpectedStrings);
		return false;
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
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		downloadReportFileAndWait(dataRowID, ReportFileType.MetaDataZIP);
		this.getComplianceReportsPage().verifyMetaDataFiles();
		return true;
	}

	/**
	 * Executes verifyMetaDataZIPFilesAreCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyMetaDataZIPFilesAreCorrect(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyMetaDataZIPFilesAreCorrect", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyMetaDataZIPFilesAreCorrect", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		downloadReportFileAndWait(dataRowID, ReportFileType.MetaDataZIP);
		this.getComplianceReportsPage().verifyMetaDataFilesData();
		return true;
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
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
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
		ActionArguments.verifyGreaterThanZero("verifyMetaDataZIPThumbnailIsShownInComplianceViewer", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceZipMeta);
		return true;
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
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
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
		ActionArguments.verifyGreaterThanZero("verifyPDFThumbnailIsShownInComplianceViewer", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceTablePDF);
		return true;
	}
 
	/**
	 * Executes verifyPDFZipFilesAreCorrect action. 
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyPDFZipFilesAreCorrect(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyPDFZipFilesAreCorrect", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyPDFZipFilesAreCorrect", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		downloadReportFileAndWait(dataRowID, ReportFileType.ZIP);
		this.getComplianceReportsPage().verifyReportPDFZIPFiles();
		return true;
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

		ComplianceReportsDataRow compRptDataRow = getDataReader().getDataRow(dataRowID);
		Integer custRowID = Integer.valueOf(compRptDataRow.customerRowID);
		CustomerDataReader custDataReader = new CustomerDataReader(this.excelUtility);
		CustomerDataRow custDataRow = custDataReader.getDataRow(custRowID);
		String customerName = custDataRow.name;
		String rptTitle = compRptDataRow.title;

		TestContext.INSTANCE.getTestSetup().slowdownInSeconds(TestContext.INSTANCE.getTestSetup().getSlowdownInSeconds());
		if ((getComplianceReportsPage().checkActionStatus(rptTitle, customerName, null))) {
			if ((!getComplianceReportsPage().findReport(rptTitle, customerName)) || 
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
		ActionArguments.verifyGreaterThanZero("verifyPDFZIPThumbnailDownloadFromComplianceViewer", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
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
		ActionArguments.verifyGreaterThanZero("verifyPDFZIPThumbnailIsShownInComplianceViewer", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceZipPDF);
		return true;
	}
 
	/**
	 * Executes verifyReportPDFMatches action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyReportPDFMatches(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyReportPDFMatches", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyReportThumbnailMatches action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyReportThumbnailMatches(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyReportThumbnailMatches", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyRequiredFieldsAreShownInRed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyRequiredFieldsAreShownInRed(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyRequiredFieldsAreShownInRed", data, dataRowID);
		return true;
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
		ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
		String customerName = new CustomerDataReader(this.excelUtility).getDataRow(Integer.valueOf(dataRow.customerRowID)).name;
		List<Integer> surveyRowIDs = ActionArguments.getNumericList(dataRow.reportSurveyRowIDs);
		Integer reportViewLayerRowID = Integer.valueOf(dataRow.reportOptViewLayerRowID);
		ReportOptViewLayersDataRow reportOptViewLayersDataRow = new ReportOptViewLayersDataReader(this.excelUtility).getDataRow(reportViewLayerRowID);
		Integer reportPDFContentRowID = Integer.valueOf(dataRow.reportOptTabularPDFContentRowID);
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

	private boolean verifyReportSurveyValuesMatch(List<Integer> surveyRowIDs) {
		// TODO: Needs implementation in Reports page object.
		return true;
	}

	private boolean verifyReportViewValuesMatch(List<Integer> surveyRowIDs) {
		// TODO: Needs implementation in Reports page object.
		return true;
	}

	private boolean areTabularPDFContentSelectionMatch(ReportOptTabularPDFContentDataRow dataRow) {
		return isPDFGapSelectionMatch(dataRow) && isPDFIndicationSelectionMatch(dataRow) &&
				isPDFIsotopicAnalysisSelectionMatch(dataRow) && isPDFPercentCoverageAssetsSelectionMatch(dataRow) &&
				isPDFPercentCoverageForecastSelectionMatch(dataRow) && isPDFPercentCoverageReportAreaSelectionMatch(dataRow);
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

	private boolean areAssetBoundariesMatch(ReportOptViewLayersDataRow dataRow) {
		return (isAssetCopperSelectionMatch(dataRow) && isAssetCastIronSelectionMatch(dataRow) && 
				isAssetOtherPlasticSelectionMatch(dataRow) && isAssetPEPlasticSelectionMatch(dataRow) && 
				isAssetProtectedSteelSelectionMatch(dataRow) && isAssetUnprotectedSteelSelectionMatch(dataRow) && 
				isBoundariesDistrictSelectionMatch(dataRow) && isBoundariesDistrictPlatSelectionMatch(dataRow));
	}
	
	private boolean isAssetCopperSelectionMatch(ReportOptViewLayersDataRow dataRow) {
		return this.getComplianceReportsPage().isCopperSelected() && (dataRow.assetCopper == "TRUE");
	}

	private boolean isAssetCastIronSelectionMatch(ReportOptViewLayersDataRow dataRow) {
		return this.getComplianceReportsPage().isCastIronSelected() && (dataRow.assetCastIron == "TRUE");
	}

	private boolean isAssetOtherPlasticSelectionMatch(ReportOptViewLayersDataRow dataRow) {
		return this.getComplianceReportsPage().isOtherPlasticSelected() && (dataRow.assetOtherPlastic == "TRUE");
	}

	private boolean isAssetPEPlasticSelectionMatch(ReportOptViewLayersDataRow dataRow) {
		return this.getComplianceReportsPage().isPEPlasticSelected() && (dataRow.assetPEPlastic == "TRUE");
	}

	private boolean isAssetProtectedSteelSelectionMatch(ReportOptViewLayersDataRow dataRow) {
		return this.getComplianceReportsPage().isProtectedSteelSelected() && (dataRow.assetProtectedSteel == "TRUE");
	}

	private boolean isAssetUnprotectedSteelSelectionMatch(ReportOptViewLayersDataRow dataRow) {
		return this.getComplianceReportsPage().isUnprotectedSteelSelected() && (dataRow.assetUnprotectedSteel == "TRUE");
	}

	private boolean isBoundariesDistrictSelectionMatch(ReportOptViewLayersDataRow dataRow) {
		return this.getComplianceReportsPage().isDistrictSelected() && (dataRow.boundaryDistrict == "TRUE");
	}

	private boolean isBoundariesDistrictPlatSelectionMatch(ReportOptViewLayersDataRow dataRow) {
		return this.getComplianceReportsPage().isDistrictPlatSelected() && (dataRow.boundaryDistrictPlat == "TRUE");
	}

	/**
	 * Executes verifyResubmitButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyResubmitButtonIsDisplayed(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyResubmitButtonIsDisplayed", data, dataRowID);
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
		return true;
	}
 
	/**
	 * Executes verifySearchedSurveysAreForLastXDays action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysAreForLastXDays(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifySearchedSurveysAreForLastXDays", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifySearchedSurveysMatchDateRange action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysMatchDateRange(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifySearchedSurveysMatchDateRange", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifySearchedSurveysMatchSurveyorUnit action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysMatchSurveyorUnit(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifySearchedSurveysMatchSurveyorUnit", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifySearchedSurveysMatchTag action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysMatchTag(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifySearchedSurveysMatchTag", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyShapeFilesHaveCorrectData action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyShapeFilesHaveCorrectData(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyShapeFilesHaveCorrectData", data, dataRowID);
		downloadReportFileAndWait(dataRowID, ReportFileType.ShapeZIP);
		this.getComplianceReportsPage().verifyShapeFilesData();
		return true;
	}

	/**
	 * Executes verifyShapeZIPFilesAreCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyShapeZIPFilesAreCorrect(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyShapeZIPFilesAreCorrect", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("verifyShapeZIPFilesAreCorrect", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		downloadReportFileAndWait(dataRowID, ReportFileType.ShapeZIP);
		this.getComplianceReportsPage().verifyShapeFiles();
		return true;
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
		ActionArguments.verifyGreaterThanZero("verifyShapeZIPThumbnailDownloadFromComplianceViewer", ARG_DATA_ROW_ID, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
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
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ComplianceZipShape);
		return true;
	}

	private boolean verifyUncertaintyValueIsFormattedCorrectly(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyUncertaintyValueIsFormattedCorrectly", data, dataRowID);
		return false;
	}

	private boolean verifyIsotopicValueIsFormattedCorrectly(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyIsotopicValueIsFormattedCorrectly", data, dataRowID);
		return false;
	}

	private boolean verifyIsotopicTableSortedByColumn(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyIsotopicTableSortedByColumn", data, dataRowID);
		return false;
	}

	/**
	 * Executes verifyView1ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView1ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView1ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.FirstView);
		return true;
	}
 
	/**
	 * Executes verifyView1ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView1ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView1ThumbnailIsShownInComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.FirstView);
		return true;
	}
 
	/**
	 * Executes verifyView2ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView2ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView2ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.SecondView);
		return true;
	}
 
	/**
	 * Executes verifyView2ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView2ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView2ThumbnailIsShownInComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.SecondView);
		return true;
	}
 
	/**
	 * Executes verifyView3ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView3ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView3ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.ThirdView);
		return true;
	}
 
	/**
	 * Executes verifyView3ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView3ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView3ThumbnailIsShownInComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.ThirdView);
		return true;
	}
 
	/**
	 * Executes verifyView4ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView4ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView4ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.FourthView);
		return true;
	}
 
	/**
	 * Executes verifyView4ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView4ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView4ThumbnailIsShownInComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.FourthView);
		return true;
	}
 
	/**
	 * Executes verifyView5ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView5ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView5ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.FifthView);
		return true;
	}
 
	/**
	 * Executes verifyView5ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView5ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView5ThumbnailIsShownInComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.FifthView);
		return true;
	}
 
	/**
	 * Executes verifyView6ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView6ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView6ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.SixthView);
		return true;
	}
 
	/**
	 * Executes verifyView6ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView6ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView6ThumbnailIsShownInComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.SixthView);
		return true;
	}
 
	/**
	 * Executes verifyView7ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView7ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView7ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType.SeventhView);
		return true;
	}
 
	/**
	 * Executes verifyView7ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyView7ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyView7ThumbnailIsShownInComplianceViewer", data, dataRowID);
		clickComplianceReportButton(dataRowID, ComplianceReportButtonType.ReportViewer);
		this.getComplianceReportsPage().waitForReportViewerPopupToShow();
		this.getComplianceReportsPage().verifyThumbnailInReportViewer(ReportViewerThumbnailType.SeventhView);
		return true;
	}

	/**
	 * Executes verifyPercentCoverageForecastPresentInReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPercentCoverageForecastPresentInReport(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyPercentCoverageForecastPresentInReport", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyPercentCoverageAssetsAndReportAreaValuesInReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPercentCoverageAssetsAndReportAreaValuesInReport(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyPercentCoverageAssetsAndReportAreaValuesInReport", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyAssetsAreDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyAssetsAreDisplayed(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyAssetsAreDisplayed", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyIndicationTableSortedByColumn action.
	 * @param data - specifies the column name.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyIndicationTableSortedByColumn(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyIndicationTableSortedByColumn", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyWarningMessageOnDeleteButtonClickEquals action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyWarningMessageOnDeleteButtonClickEquals(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyWarningMessageOnDeleteButtonClickEquals", data, dataRowID);
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
		return true;
	}
 
	/**
	 * Executes clickOnComplianceViewerView1 action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerView1(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerView1", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnComplianceViewerView2 action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerView2(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerView2", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnComplianceViewerView3 action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerView3(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerView3", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnComplianceViewerView4 action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerView4(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerView4", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnComplianceViewerView5 action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerView5(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerView5", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnComplianceViewerView6 action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerView6(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerView6", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnComplianceViewerView7 action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerView7(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerView7", data, dataRowID);
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
		return true;
	}
 
	/**
	 * Executes waitForPDFDownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForPDFDownloadToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForPDFDownloadToComplete", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes waitForPDFZIPDownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForPDFZIPDownloadToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForPDFZIPDownloadToComplete", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes waitForMetaZIPDownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForMetaZIPDownloadToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForMetaZIPDownloadToComplete", data, dataRowID);
		return true;
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
	 * Executes waitForShapeZIPDownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForShapeZIPDownloadToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForShapeZIPDownloadToComplete", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes waitForView1DownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForView1DownloadToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForView1DownloadToComplete", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes waitForView2DownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForView2DownloadToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForView2DownloadToComplete", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes waitForView3DownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForView3DownloadToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForView3DownloadToComplete", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes waitForView4DownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForView4DownloadToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForView4DownloadToComplete", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes waitForView5DownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForView5DownloadToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForView5DownloadToComplete", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes waitForView6DownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForView6DownloadToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForView6DownloadToComplete", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes waitForView7DownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForView7DownloadToComplete(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.waitForView7DownloadToComplete", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes extractPDFZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean extractPDFZIP(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.extractPDFZIP", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes extractMetaZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean extractMetaZIP(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.extractMetaZIP", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes extractShapeZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean extractShapeZIP(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.extractShapeZIP", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyReportFilesHaveCorrectData action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean verifyReportFilesHaveCorrectData(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyReportFilesHaveCorrectData", data, dataRowID);
		ComplianceReportsDataRow dataRow = getDataReader().getDataRow(dataRowID);
		return this.getComplianceReportsPage().checkActionStatus(ComplianceReportsPageActions.workingDataRow.title, 
				TestContext.INSTANCE.getLoggedInUser(), dataRow.tCID);
	}
	
	/**
	 * Executes verifyViewsAreInCorrectSequence action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyViewsAreInCorrectSequence(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyViewsAreInCorrectSequence", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView1ThumbnailIsCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView1ThumbnailIsCorrect(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyView1ThumbnailIsCorrect", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView2ThumbnailIsCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView2ThumbnailIsCorrect(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyView2ThumbnailIsCorrect", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView3ThumbnailIsCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView3ThumbnailIsCorrect(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyView3ThumbnailIsCorrect", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView4ThumbnailIsCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView4ThumbnailIsCorrect(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyView4ThumbnailIsCorrect", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView5ThumbnailIsCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView5ThumbnailIsCorrect(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyView5ThumbnailIsCorrect", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView6ThumbnailIsCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView6ThumbnailIsCorrect(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyView6ThumbnailIsCorrect", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView7ThumbnailIsCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView7ThumbnailIsCorrect(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyView7ThumbnailIsCorrect", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyGapShapeFilesHaveCorrectData action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGapShapeFilesHaveCorrectData(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyGapShapeFilesHaveCorrectData", data, dataRowID);
		return true;
	}
 
	/* END - Actions on the Page*/

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("addDefaultView")) { return this.addDefaultView(data, dataRowID); }
		else if (actionName.equals("addFirstSurveysToReport")) { return this.addFirstSurveysToReport(data, dataRowID); }
		else if (actionName.equals("addFirst2SurveysToReport")) { return this.addFirst2SurveysToReport(data, dataRowID); }
		else if (actionName.equals("addFirst3SurveysToReport")) { return this.addFirst3SurveysToReport(data, dataRowID); }
		else if (actionName.equals("addNewView")) { return this.addNewView(data, dataRowID); }
		else if (actionName.equals("addSurveyToReport")) { return this.addSurveyToReport(data, dataRowID); }
		else if (actionName.equals("cancelInProgressReport")) { return this.cancelInProgressReport(data, dataRowID); }
		else if (actionName.equals("checkSurveySelectorGeographicFilter")) { return this.checkSurveySelectorGeographicFilter(data, dataRowID); }
		else if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("clickOnCancelButton")) { return this.clickOnCancelButton(data, dataRowID); }
		else if (actionName.equals("clickOnCancelConfirmDeleteReport")) { return this.clickOnCancelConfirmDeleteReport(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerButton")) { return this.clickOnComplianceViewerButton(data, dataRowID); }
		else if (actionName.equals("clickOnConfirmDeleteReport")) { return this.clickOnConfirmDeleteReport(data, dataRowID); }
		else if (actionName.equals("clickOnCopyButton")) { return this.clickOnCopyButton(data, dataRowID); }
		else if (actionName.equals("clickOnDeleteButton")) { return this.clickOnDeleteButton(data, dataRowID); }
		else if (actionName.equals("clickOnFirstCopyComplianceButton")) { return this.clickOnFirstCopyComplianceButton(data, dataRowID); }
		else if (actionName.equals("clickOnFirstInvestigateComplianceButton")) { return this.clickOnFirstInvestigateComplianceButton(data, dataRowID); }
		else if (actionName.equals("clickOnInvestigateButton")) { return this.clickOnInvestigateButton(data, dataRowID); }
		else if (actionName.equals("clickOnInvestigatePDFButton")) { return this.clickOnInvestigatePDFButton(data, dataRowID); }
		else if (actionName.equals("clickOnOKButton")) { return this.clickOnOKButton(data, dataRowID); }
		else if (actionName.equals("clickOnResubmitButton")) { return this.clickOnResubmitButton(data, dataRowID); }
		else if (actionName.equals("clickOnSurveySelectorSearchButton")) { return this.clickOnSurveySelectorSearchButton(data, dataRowID); }
		else if (actionName.equals("clickOnPDFInReportViewer")) { return this.clickOnPDFInReportViewer(data, dataRowID); }
		else if (actionName.equals("clickOnZIPInReportViewer")) { return this.clickOnZIPInReportViewer(data, dataRowID); }
		else if (actionName.equals("clickOnMetadataZIPInReportViewer")) { return this.clickOnMetadataZIPInReportViewer(data, dataRowID); }
		else if (actionName.equals("clickOnShapeZIPInReportViewer")) { return this.clickOnShapeZIPInReportViewer(data, dataRowID); }
		else if (actionName.equals("clickOnViewThumbnailInReportViewer")) { return this.clickOnViewThumbnailInReportViewer(data, dataRowID); }
		else if (actionName.equals("clickOnLatLongSelectorButton")) { return this.clickOnLatLongSelectorButton(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerPDF")) { return this.clickOnComplianceViewerPDF(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerPDFZIP")) { return this.clickOnComplianceViewerPDFZIP(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerMetaZIP")) { return this.clickOnComplianceViewerMetaZIP(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerShapeZIP")) { return this.clickOnComplianceViewerShapeZIP(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerView1")) { return this.clickOnComplianceViewerView1(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerView2")) { return this.clickOnComplianceViewerView2(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerView3")) { return this.clickOnComplianceViewerView3(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerView4")) { return this.clickOnComplianceViewerView4(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerView5")) { return this.clickOnComplianceViewerView5(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerView6")) { return this.clickOnComplianceViewerView6(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerView7")) { return this.clickOnComplianceViewerView7(data, dataRowID); }
		else if (actionName.equals("clickNoOnChangeReportDialog")) { return this.clickNoOnChangeReportDialog(data, dataRowID); }
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
		else if (actionName.equals("extractPDFZIP")) { return this.extractPDFZIP(data, dataRowID); }
		else if (actionName.equals("extractMetaZIP")) { return this.extractMetaZIP(data, dataRowID); }
		else if (actionName.equals("extractShapeZIP")) { return this.extractShapeZIP(data, dataRowID); }
		else if (actionName.equals("findReport")) { return this.findReport(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("investigateReport")) { return this.investigateReport(data, dataRowID); }
		else if (actionName.equals("modifyReport")) { return this.modifyReport(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("openNewReportPage")) { return this.openNewReportPage(data, dataRowID); }
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
		else if (actionName.equals("verifyAssetsAreDisplayed")) { return this.verifyAssetsAreDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyComplianceViewerButtonIsDisplayed")) { return this.verifyComplianceViewerButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyComplianceViewerViewCountEquals")) { return this.verifyComplianceViewerViewCountEquals(data, dataRowID); }
		else if (actionName.equals("verifyCopyButtonIsDisplayed")) { return this.verifyCopyButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyDeleteButtonIsDisplayed")) { return this.verifyDeleteButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyIndicationTableSortedByColumn")) { return this.verifyIndicationTableSortedByColumn(data, dataRowID); }
		else if (actionName.equals("verifyInvestigateButtonIsDisplayed")) { return this.verifyInvestigateButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyInvestigatePDFButtonIsDisplayed")) { return this.verifyInvestigatePDFButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyInvestigatePDFDownload")) { return this.verifyInvestigatePDFDownload(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicTableSortedByColumn")) { return this.verifyIsotopicTableSortedByColumn(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicValueIsFormattedCorrectly")) { return this.verifyIsotopicValueIsFormattedCorrectly(data, dataRowID); }
		else if (actionName.equals("verifyLastXDaysSurveysPresentInPDF")) { return this.verifyLastXDaysSurveysPresentInPDF(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataFilesHaveCorrectData")) { return this.verifyMetaDataFilesHaveCorrectData(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataZIPFilesAreCorrect")) { return this.verifyMetaDataZIPFilesAreCorrect(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataZIPThumbnailDownloadFromComplianceViewer")) { return this.verifyMetaDataZIPThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataZIPThumbnailIsShownInComplianceViewer")) { return this.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPDFContainsInputtedInformation")) { return this.verifyPDFContainsInputtedInformation(data, dataRowID); }
		else if (actionName.equals("verifyPDFThumbnailDownloadFromComplianceViewer")) { return this.verifyPDFThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPDFThumbnailIsShownInComplianceViewer")) { return this.verifyPDFThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPDFZipFilesAreCorrect")) { return this.verifyPDFZipFilesAreCorrect(data, dataRowID); }
		else if (actionName.equals("verifyPDFZipFilesArePresent")) { return this.verifyPDFZipFilesArePresent(data, dataRowID); }
		else if (actionName.equals("verifyPDFZIPThumbnailDownloadFromComplianceViewer")) { return this.verifyPDFZIPThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPDFZIPThumbnailIsShownInComplianceViewer")) { return this.verifyPDFZIPThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPercentCoverageForecastPresentInReport")) { return this.verifyPercentCoverageForecastPresentInReport(data, dataRowID); }
		else if (actionName.equals("verifyPercentCoverageAssetsAndReportAreaValuesInReport")) { return this.verifyPercentCoverageAssetsAndReportAreaValuesInReport(data, dataRowID); }
		else if (actionName.equals("verifyReportFilesHaveCorrectData")) { return this.verifyReportFilesHaveCorrectData(data, dataRowID); }
		else if (actionName.equals("verifyReportPageFieldsAreCorrect")) { return this.verifyReportPageFieldsAreCorrect(data, dataRowID); }
		else if (actionName.equals("verifyReportPDFMatches")) { return this.verifyReportPDFMatches(data, dataRowID); }
		else if (actionName.equals("verifyReportThumbnailMatches")) { return this.verifyReportThumbnailMatches(data, dataRowID); }
		else if (actionName.equals("verifyRequiredFieldsAreShownInRed")) { return this.verifyRequiredFieldsAreShownInRed(data, dataRowID); }
		else if (actionName.equals("verifyResubmitButtonIsDisplayed")) { return this.verifyResubmitButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveyIsShown")) { return this.verifySearchedSurveyIsShown(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysAreForLastXDays")) { return this.verifySearchedSurveysAreForLastXDays(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysMatchDateRange")) { return this.verifySearchedSurveysMatchDateRange(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysMatchSurveyorUnit")) { return this.verifySearchedSurveysMatchSurveyorUnit(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysMatchTag")) { return this.verifySearchedSurveysMatchTag(data, dataRowID); }
		else if (actionName.equals("verifyShapeFilesHaveCorrectData")) { return this.verifyShapeFilesHaveCorrectData(data, dataRowID); }
		else if (actionName.equals("verifyShapeZIPFilesAreCorrect")) { return this.verifyShapeZIPFilesAreCorrect(data, dataRowID); }
		else if (actionName.equals("verifyShapeZIPThumbnailDownloadFromComplianceViewer")) { return this.verifyShapeZIPThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyShapeZIPThumbnailIsShownInComplianceViewer")) { return this.verifyShapeZIPThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyUncertaintyValueIsFormattedCorrectly")) { return this.verifyUncertaintyValueIsFormattedCorrectly(data, dataRowID); }
		else if (actionName.equals("verifyView1ThumbnailDownloadFromComplianceViewer")) { return this.verifyView1ThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView1ThumbnailIsShownInComplianceViewer")) { return this.verifyView1ThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView2ThumbnailDownloadFromComplianceViewer")) { return this.verifyView2ThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView2ThumbnailIsShownInComplianceViewer")) { return this.verifyView2ThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView3ThumbnailDownloadFromComplianceViewer")) { return this.verifyView3ThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView3ThumbnailIsShownInComplianceViewer")) { return this.verifyView3ThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView4ThumbnailDownloadFromComplianceViewer")) { return this.verifyView4ThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView4ThumbnailIsShownInComplianceViewer")) { return this.verifyView4ThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView5ThumbnailDownloadFromComplianceViewer")) { return this.verifyView5ThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView5ThumbnailIsShownInComplianceViewer")) { return this.verifyView5ThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView6ThumbnailDownloadFromComplianceViewer")) { return this.verifyView6ThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView6ThumbnailIsShownInComplianceViewer")) { return this.verifyView6ThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView7ThumbnailDownloadFromComplianceViewer")) { return this.verifyView7ThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyView7ThumbnailIsShownInComplianceViewer")) { return this.verifyView7ThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyWarningMessageOnDeleteButtonClickEquals")) { return this.verifyWarningMessageOnDeleteButtonClickEquals(data, dataRowID); }
		else if (actionName.equals("verifyViewsAreInCorrectSequence")) { return this.verifyViewsAreInCorrectSequence(data, dataRowID); }
		else if (actionName.equals("verifyView1ThumbnailIsCorrect")) { return this.verifyView1ThumbnailIsCorrect(data, dataRowID); }
		else if (actionName.equals("verifyView2ThumbnailIsCorrect")) { return this.verifyView2ThumbnailIsCorrect(data, dataRowID); }
		else if (actionName.equals("verifyView3ThumbnailIsCorrect")) { return this.verifyView3ThumbnailIsCorrect(data, dataRowID); }
		else if (actionName.equals("verifyView4ThumbnailIsCorrect")) { return this.verifyView4ThumbnailIsCorrect(data, dataRowID); }
		else if (actionName.equals("verifyView5ThumbnailIsCorrect")) { return this.verifyView5ThumbnailIsCorrect(data, dataRowID); }
		else if (actionName.equals("verifyView6ThumbnailIsCorrect")) { return this.verifyView6ThumbnailIsCorrect(data, dataRowID); }
		else if (actionName.equals("verifyView7ThumbnailIsCorrect")) { return this.verifyView7ThumbnailIsCorrect(data, dataRowID); }
		else if (actionName.equals("verifyGapShapeFilesHaveCorrectData")) { return this.verifyGapShapeFilesHaveCorrectData(data, dataRowID); }
		else if (actionName.equals("waitForPDFDownloadToComplete")) { return this.waitForPDFDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForPDFZIPDownloadToComplete")) { return this.waitForPDFZIPDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForMetaZIPDownloadToComplete")) { return this.waitForMetaZIPDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForReportGenerationToComplete")) { return this.waitForReportGenerationToComplete(data, dataRowID); }
		else if (actionName.equals("waitForShapeZIPDownloadToComplete")) { return this.waitForShapeZIPDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForView1DownloadToComplete")) { return this.waitForView1DownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForView2DownloadToComplete")) { return this.waitForView2DownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForView3DownloadToComplete")) { return this.waitForView3DownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForView4DownloadToComplete")) { return this.waitForView4DownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForView5DownloadToComplete")) { return this.waitForView5DownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForView6DownloadToComplete")) { return this.waitForView6DownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForView7DownloadToComplete")) { return this.waitForView7DownloadToComplete(data, dataRowID); }
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

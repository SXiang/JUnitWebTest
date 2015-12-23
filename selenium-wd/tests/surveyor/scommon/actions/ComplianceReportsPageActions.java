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
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
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
import org.openqa.selenium.support.PageFactory;

import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.data.ComplianceReportDataReader;
import surveyor.scommon.actions.data.ComplianceReportDataReader.ComplianceReportDataRow;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.ReportOptTabularPDFContentDataReader;
import surveyor.scommon.actions.data.ReportOptViewLayersDataReader;
import surveyor.scommon.actions.data.ReportViewsDataReader;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ReportsCompliance;

public class ComplianceReportsPageActions extends BasePageActions {
	private ComplianceReportsPage complianceReportsPage = null;
	private ComplianceReportDataReader dataReader = null;
	public static ReportsCompliance workingReportsComp = null;      // Stores the ReportsCompliance object from createNewReport action
	public static ComplianceReportDataRow workingDataRow = null;    // Stores the workingDataRow from createNewReport action

	public ComplianceReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		complianceReportsPage = new ComplianceReportsPage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
		
		dataReader = new ComplianceReportDataReader(this.excelUtility);
	}
	
	/* START - Actions on the Page*/

	/**
	 * Executes addDefaultView action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean addDefaultView(String data, Integer dataRowID) {
		logAction("addDefaultView", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes addNewView action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean addNewView(String data, Integer dataRowID) {
		logAction("addNewView", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes addSurveyToReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean addSurveyToReport(String data, Integer dataRowID) {
		logAction("addSurveyToReport", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes cancelInProgressReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean cancelInProgressReport(String data, Integer dataRowID) {
		logAction("cancelInProgressReport", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes checkSurveySelectorGeographicFilter action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean checkSurveySelectorGeographicFilter(String data, Integer dataRowID) {
		logAction("checkSurveySelectorGeographicFilter", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickById action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickById(String data, Integer dataRowID) {
		logAction("clickById", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickByIdAndWait action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickByIdAndWait(String data, Integer dataRowID) {
		logAction("clickByIdAndWait", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickByXPath action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickByXPath(String data, Integer dataRowID) {
		logAction("clickByXPath", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickByXPathAndWait action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickByXPathAndWait(String data, Integer dataRowID) {
		logAction("clickByXPathAndWait", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnCancelButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnCancelButton(String data, Integer dataRowID) {
		logAction("clickOnCancelButton", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnCancelConfirmDeleteReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnCancelConfirmDeleteReport(String data, Integer dataRowID) {
		logAction("clickOnCancelConfirmDeleteReport", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnComplianceViewerButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnComplianceViewerButton(String data, Integer dataRowID) {
		logAction("clickOnComplianceViewerButton", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnConfirmDeleteReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnConfirmDeleteReport(String data, Integer dataRowID) {
		logAction("clickOnConfirmDeleteReport", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnCopyButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnCopyButton(String data, Integer dataRowID) {
		logAction("clickOnCopyButton", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnDeleteButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnDeleteButton(String data, Integer dataRowID) {
		logAction("clickOnDeleteButton", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnInvestigateButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnInvestigateButton(String data, Integer dataRowID) {
		logAction("clickOnInvestigateButton", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnInvestigatePDFButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnInvestigatePDFButton(String data, Integer dataRowID) {
		logAction("clickOnInvestigatePDFButton", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnOKButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnOKButton(String data, Integer dataRowID) {
		logAction("clickOnOKButton", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnResubmitButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnResubmitButton(String data, Integer dataRowID) {
		logAction("clickOnResubmitButton", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnSurveySelectorSearchButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnSurveySelectorSearchButton(String data, Integer dataRowID) {
		logAction("clickOnSurveySelectorSearchButton", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickThumbnailInViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickThumbnailInViewer(String data, Integer dataRowID) {
		logAction("clickThumbnailInViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes createNewReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean createNewReport(String data, Integer dataRowID) throws Exception {
		logAction("createNewReport", data, dataRowID);
		
		workingDataRow = dataReader.getDataRow(dataRowID);
		
		String rptTitle = workingDataRow.title;
		String surveyorUnit = workingDataRow.surveySurveyor;
		String tag = workingDataRow.surveyTag;
		String customer = null; 
		String customerRowID = workingDataRow.customerRowID;
		if (customerRowID != "") {
			Integer custRowID = Integer.valueOf(customerRowID);
			customer = (new CustomerDataReader(this.excelUtility)).getDataRow(custRowID).name;
		}
		String timeZone = workingDataRow.timezone;
		String exclusionRadius = workingDataRow.exclusionRadius;

		List<String> listBoundary = new ArrayList<String>();
		fillCustomBoundary(listBoundary, dataReader, dataRowID);

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, TestContext.INSTANCE.getLoggedInUser(), customer, timeZone, exclusionRadius,
				listBoundary, tablesList, surveyorUnit, tag, viewList, viewLayersList);
		complianceReportsPage.addNewReport(rpt);
		
		workingReportsComp = rpt;		// Store the working report properties.
		return true;
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

	/**
	 * Executes deleteReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean deleteReport(String data, Integer dataRowID) {
		logAction("deleteReport", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterCustomBoundaryUsingAreaSelector action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterCustomBoundaryUsingAreaSelector(String data, Integer dataRowID) {
		logAction("enterCustomBoundaryUsingAreaSelector", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterCustomBoundaryUsingTextFields action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterCustomBoundaryUsingTextFields(String data, Integer dataRowID) {
		logAction("enterCustomBoundaryUsingTextFields", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterCustomerBoundaryUsingAreaSelector action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterCustomerBoundaryUsingAreaSelector(String data, Integer dataRowID) {
		logAction("enterCustomerBoundaryUsingAreaSelector", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterCustomerBoundaryUsingTextFields action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterCustomerBoundaryUsingTextFields(String data, Integer dataRowID) {
		logAction("enterCustomerBoundaryUsingTextFields", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterExclusionRadius action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterExclusionRadius(String data, Integer dataRowID) {
		logAction("enterExclusionRadius", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterFOVOpacity action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterFOVOpacity(String data, Integer dataRowID) {
		logAction("enterFOVOpacity", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterLISAOpacity action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterLISAOpacity(String data, Integer dataRowID) {
		logAction("enterLISAOpacity", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterPDFImageHeight action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterPDFImageHeight(String data, Integer dataRowID) {
		logAction("enterPDFImageHeight", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterPDFImageWidth action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterPDFImageWidth(String data, Integer dataRowID) {
		logAction("enterPDFImageWidth", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterReportTitle action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterReportTitle(String data, Integer dataRowID) {
		logAction("enterReportTitle", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterSurveySelectorTag action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterSurveySelectorTag(String data, Integer dataRowID) {
		logAction("enterSurveySelectorTag", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes enterSurveySelectorUsername action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean enterSurveySelectorUsername(String data, Integer dataRowID) {
		logAction("enterSurveySelectorUsername", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes findReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean findReport(String data, Integer dataRowID) {
		logAction("findReport", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes insertTextById action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean insertTextById(String data, Integer dataRowID) {
		logAction("insertTextById", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes insertTextByXPath action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean insertTextByXPath(String data, Integer dataRowID) {
		logAction("insertTextByXPath", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes investigateReport action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean investigateReport(String data, Integer dataRowID) {
		logAction("investigateReport", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction("open", data, dataRowID);
		complianceReportsPage.open();
		complianceReportsPage.waitForPageLoad();
		return true;
	}
 
	/**
	 * Executes openNewReportPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean openNewReportPage(String data, Integer dataRowID) {
		logAction("openNewReportPage", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes searchForSurveyByKeyword action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean searchForSurveyByKeyword(String data, Integer dataRowID) {
		logAction("searchForSurveyByKeyword", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectCustomer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectCustomer(String data, Integer dataRowID) {
		logAction("selectCustomer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectDropDownByID action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectDropDownByID(String data, Integer dataRowID) {
		logAction("selectDropDownByID", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectDropDownByXPath action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectDropDownByXPath(String data, Integer dataRowID) {
		logAction("selectDropDownByXPath", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectRadioButtonByID action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectRadioButtonByID(String data, Integer dataRowID) {
		logAction("selectRadioButtonByID", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectRadioButtonByXPath action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectRadioButtonByXPath(String data, Integer dataRowID) {
		logAction("selectRadioButtonByXPath", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectReportMode action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectReportMode(String data, Integer dataRowID) {
		logAction("selectReportMode", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectSurveySelectorEndDateTime action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectSurveySelectorEndDateTime(String data, Integer dataRowID) {
		logAction("selectSurveySelectorEndDateTime", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectSurveySelectorStartDateTime action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectSurveySelectorStartDateTime(String data, Integer dataRowID) {
		logAction("selectSurveySelectorStartDateTime", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectSurveySelectorSurveyModeFilter action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectSurveySelectorSurveyModeFilter(String data, Integer dataRowID) {
		logAction("selectSurveySelectorSurveyModeFilter", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectSurveySelectorSurveyor action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectSurveySelectorSurveyor(String data, Integer dataRowID) {
		logAction("selectSurveySelectorSurveyor", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectTabularPDFContent action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectTabularPDFContent(String data, Integer dataRowID) {
		logAction("selectTabularPDFContent", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectTimeZone action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectTimeZone(String data, Integer dataRowID) {
		logAction("selectTimeZone", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectViewLayersAsset action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectViewLayersAsset(String data, Integer dataRowID) {
		logAction("selectViewLayersAsset", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes selectViewLayersBoundary action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean selectViewLayersBoundary(String data, Integer dataRowID) {
		logAction("selectViewLayersBoundary", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes sortRecordsBy action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean sortRecordsBy(String data, Integer dataRowID) {
		logAction("sortRecordsBy", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyComplianceViewerButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyComplianceViewerButtonIsDisplayed(String data, Integer dataRowID) {
		logAction("verifyComplianceViewerButtonIsDisplayed", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyComplianceViewerViewCountEquals action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyComplianceViewerViewCountEquals(String data, Integer dataRowID) {
		logAction("verifyComplianceViewerViewCountEquals", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyCopyButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyCopyButtonIsDisplayed(String data, Integer dataRowID) {
		logAction("verifyCopyButtonIsDisplayed", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyDeleteButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDeleteButtonIsDisplayed(String data, Integer dataRowID) {
		logAction("verifyDeleteButtonIsDisplayed", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyInvestigateButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyInvestigateButtonIsDisplayed(String data, Integer dataRowID) {
		logAction("verifyInvestigateButtonIsDisplayed", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyInvestigatePDFButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyInvestigatePDFButtonIsDisplayed(String data, Integer dataRowID) {
		logAction("verifyInvestigatePDFButtonIsDisplayed", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyInvestigatePDFDownload action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyInvestigatePDFDownload(String data, Integer dataRowID) {
		logAction("verifyInvestigatePDFDownload", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyMetaDataFilesHaveCorrectData action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyMetaDataFilesHaveCorrectData(String data, Integer dataRowID) {
		logAction("verifyMetaDataFilesHaveCorrectData", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyMetaDataZIPFilesAreCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyMetaDataZIPFilesAreCorrect(String data, Integer dataRowID) {
		logAction("verifyMetaDataZIPFilesAreCorrect", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyMetaDataZIPThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyMetaDataZIPThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyMetaDataZIPThumbnailDownloadFromComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyMetaDataZIPThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyMetaDataZIPThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyMetaDataZIPThumbnailIsShownInComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyPDFThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPDFThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyPDFThumbnailDownloadFromComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyPDFThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPDFThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyPDFThumbnailIsShownInComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyPDFZipFilesAreCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPDFZipFilesAreCorrect(String data, Integer dataRowID) {
		logAction("verifyPDFZipFilesAreCorrect", data, dataRowID);
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
		logAction("verifyPDFZipFilesArePresent", data, dataRowID);
		
		if (workingReportsComp == null) {
			throw new Exception("Create new report before verifying report PDF files. Report has not been created.");
		}
		
		TestContext.INSTANCE.getTestSetup().slowdownInSeconds(TestContext.INSTANCE.getTestSetup().getSlowdownInSeconds());
		String rptTitle = workingDataRow.title;
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU))) {
			if ((!complianceReportsPage.findReport(rptTitle, SQACUSSU)) || 
					(!complianceReportsPage.validatePdfFiles(workingReportsComp, 
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
	 */
	public boolean verifyPDFZIPThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyPDFZIPThumbnailDownloadFromComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyPDFZIPThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPDFZIPThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyPDFZIPThumbnailIsShownInComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyReportPDFMatches action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyReportPDFMatches(String data, Integer dataRowID) {
		logAction("verifyReportPDFMatches", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyReportThumbnailMatches action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyReportThumbnailMatches(String data, Integer dataRowID) {
		logAction("verifyReportThumbnailMatches", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyRequiredFieldsAreShownInRed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyRequiredFieldsAreShownInRed(String data, Integer dataRowID) {
		logAction("verifyRequiredFieldsAreShownInRed", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyResubmitButtonIsDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyResubmitButtonIsDisplayed(String data, Integer dataRowID) {
		logAction("verifyResubmitButtonIsDisplayed", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifySearchedSurveyIsShown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveyIsShown(String data, Integer dataRowID) {
		logAction("verifySearchedSurveyIsShown", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifySearchedSurveysAreForLastXDays action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysAreForLastXDays(String data, Integer dataRowID) {
		logAction("verifySearchedSurveysAreForLastXDays", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifySearchedSurveysMatchDateRange action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysMatchDateRange(String data, Integer dataRowID) {
		logAction("verifySearchedSurveysMatchDateRange", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifySearchedSurveysMatchSurveyorUnit action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysMatchSurveyorUnit(String data, Integer dataRowID) {
		logAction("verifySearchedSurveysMatchSurveyorUnit", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifySearchedSurveysMatchTag action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySearchedSurveysMatchTag(String data, Integer dataRowID) {
		logAction("verifySearchedSurveysMatchTag", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyShapeFilesHaveCorrectData action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyShapeFilesHaveCorrectData(String data, Integer dataRowID) {
		logAction("verifyShapeFilesHaveCorrectData", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyShapeZIPFilesAreCorrect action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyShapeZIPFilesAreCorrect(String data, Integer dataRowID) {
		logAction("verifyShapeZIPFilesAreCorrect", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyShapeZIPThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyShapeZIPThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyShapeZIPThumbnailDownloadFromComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyShapeZIPThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyShapeZIPThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyShapeZIPThumbnailIsShownInComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView1ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView1ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView1ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView1ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView1ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView1ThumbnailIsShownInComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView2ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView2ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView2ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView2ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView2ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView2ThumbnailIsShownInComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView3ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView3ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView3ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView3ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView3ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView3ThumbnailIsShownInComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView4ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView4ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView4ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView4ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView4ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView4ThumbnailIsShownInComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView5ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView5ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView5ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView5ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView5ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView5ThumbnailIsShownInComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView6ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView6ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView6ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView6ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView6ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView6ThumbnailIsShownInComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView7ThumbnailDownloadFromComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView7ThumbnailDownloadFromComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView7ThumbnailDownloadFromComplianceViewer", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes verifyView7ThumbnailIsShownInComplianceViewer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyView7ThumbnailIsShownInComplianceViewer(String data, Integer dataRowID) {
		logAction("verifyView7ThumbnailIsShownInComplianceViewer", data, dataRowID);
		return true;
	}
 
	/* END - Actions on the Page*/

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("addDefaultView")) { return this.addDefaultView(data, dataRowID); }
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
		else if (actionName.equals("clickOnInvestigateButton")) { return this.clickOnInvestigateButton(data, dataRowID); }
		else if (actionName.equals("clickOnInvestigatePDFButton")) { return this.clickOnInvestigatePDFButton(data, dataRowID); }
		else if (actionName.equals("clickOnOKButton")) { return this.clickOnOKButton(data, dataRowID); }
		else if (actionName.equals("clickOnResubmitButton")) { return this.clickOnResubmitButton(data, dataRowID); }
		else if (actionName.equals("clickOnSurveySelectorSearchButton")) { return this.clickOnSurveySelectorSearchButton(data, dataRowID); }
		else if (actionName.equals("clickThumbnailInViewer")) { return this.clickThumbnailInViewer(data, dataRowID); }
		else if (actionName.equals("createNewReport")) { return this.createNewReport(data, dataRowID); }
		else if (actionName.equals("deleteReport")) { return this.deleteReport(data, dataRowID); }
		else if (actionName.equals("enterCustomBoundaryUsingAreaSelector")) { return this.enterCustomBoundaryUsingAreaSelector(data, dataRowID); }
		else if (actionName.equals("enterCustomBoundaryUsingTextFields")) { return this.enterCustomBoundaryUsingTextFields(data, dataRowID); }
		else if (actionName.equals("enterCustomerBoundaryUsingAreaSelector")) { return this.enterCustomerBoundaryUsingAreaSelector(data, dataRowID); }
		else if (actionName.equals("enterCustomerBoundaryUsingTextFields")) { return this.enterCustomerBoundaryUsingTextFields(data, dataRowID); }
		else if (actionName.equals("enterExclusionRadius")) { return this.enterExclusionRadius(data, dataRowID); }
		else if (actionName.equals("enterFOVOpacity")) { return this.enterFOVOpacity(data, dataRowID); }
		else if (actionName.equals("enterLISAOpacity")) { return this.enterLISAOpacity(data, dataRowID); }
		else if (actionName.equals("enterPDFImageHeight")) { return this.enterPDFImageHeight(data, dataRowID); }
		else if (actionName.equals("enterPDFImageWidth")) { return this.enterPDFImageWidth(data, dataRowID); }
		else if (actionName.equals("enterReportTitle")) { return this.enterReportTitle(data, dataRowID); }
		else if (actionName.equals("enterSurveySelectorTag")) { return this.enterSurveySelectorTag(data, dataRowID); }
		else if (actionName.equals("enterSurveySelectorUsername")) { return this.enterSurveySelectorUsername(data, dataRowID); }
		else if (actionName.equals("findReport")) { return this.findReport(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("investigateReport")) { return this.investigateReport(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("openNewReportPage")) { return this.openNewReportPage(data, dataRowID); }
		else if (actionName.equals("searchForSurveyByKeyword")) { return this.searchForSurveyByKeyword(data, dataRowID); }
		else if (actionName.equals("selectCustomer")) { return this.selectCustomer(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("selectReportMode")) { return this.selectReportMode(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorEndDateTime")) { return this.selectSurveySelectorEndDateTime(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorStartDateTime")) { return this.selectSurveySelectorStartDateTime(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorSurveyModeFilter")) { return this.selectSurveySelectorSurveyModeFilter(data, dataRowID); }
		else if (actionName.equals("selectSurveySelectorSurveyor")) { return this.selectSurveySelectorSurveyor(data, dataRowID); }
		else if (actionName.equals("selectTabularPDFContent")) { return this.selectTabularPDFContent(data, dataRowID); }
		else if (actionName.equals("selectTimeZone")) { return this.selectTimeZone(data, dataRowID); }
		else if (actionName.equals("selectViewLayersAsset")) { return this.selectViewLayersAsset(data, dataRowID); }
		else if (actionName.equals("selectViewLayersBoundary")) { return this.selectViewLayersBoundary(data, dataRowID); }
		else if (actionName.equals("sortRecordsBy")) { return this.sortRecordsBy(data, dataRowID); }
		else if (actionName.equals("verifyComplianceViewerButtonIsDisplayed")) { return this.verifyComplianceViewerButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyComplianceViewerViewCountEquals")) { return this.verifyComplianceViewerViewCountEquals(data, dataRowID); }
		else if (actionName.equals("verifyCopyButtonIsDisplayed")) { return this.verifyCopyButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyDeleteButtonIsDisplayed")) { return this.verifyDeleteButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyInvestigateButtonIsDisplayed")) { return this.verifyInvestigateButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyInvestigatePDFButtonIsDisplayed")) { return this.verifyInvestigatePDFButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyInvestigatePDFDownload")) { return this.verifyInvestigatePDFDownload(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataFilesHaveCorrectData")) { return this.verifyMetaDataFilesHaveCorrectData(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataZIPFilesAreCorrect")) { return this.verifyMetaDataZIPFilesAreCorrect(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataZIPThumbnailDownloadFromComplianceViewer")) { return this.verifyMetaDataZIPThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyMetaDataZIPThumbnailIsShownInComplianceViewer")) { return this.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPDFThumbnailDownloadFromComplianceViewer")) { return this.verifyPDFThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPDFThumbnailIsShownInComplianceViewer")) { return this.verifyPDFThumbnailIsShownInComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPDFZipFilesAreCorrect")) { return this.verifyPDFZipFilesAreCorrect(data, dataRowID); }
		else if (actionName.equals("verifyPDFZipFilesArePresent")) { return this.verifyPDFZipFilesArePresent(data, dataRowID); }
		else if (actionName.equals("verifyPDFZIPThumbnailDownloadFromComplianceViewer")) { return this.verifyPDFZIPThumbnailDownloadFromComplianceViewer(data, dataRowID); }
		else if (actionName.equals("verifyPDFZIPThumbnailIsShownInComplianceViewer")) { return this.verifyPDFZIPThumbnailIsShownInComplianceViewer(data, dataRowID); }
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
		return false;
	}
}

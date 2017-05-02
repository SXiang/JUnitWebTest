package surveyor.scommon.actions;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.openqa.selenium.WebDriver;

import common.source.ArrayUtility;
import common.source.BaseHelper;
import common.source.FunctionUtil;
import common.source.Log;
import common.source.LogHelper;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.data.ComplianceReportDataReader;
import surveyor.scommon.actions.data.ReportSurveyDataReader;
import surveyor.scommon.actions.data.ComplianceReportDataReader.ComplianceReportsDataRow;
import surveyor.scommon.actions.data.ReportOptTabularPDFContentDataReader.ReportOptTabularPDFContentDataRow;
import surveyor.scommon.actions.data.ReportSurveyDataReader.ReportSurveyDataRow;
import surveyor.scommon.actions.data.ReportsBaseDataReader.ReportsBaseDataRow;
import surveyor.scommon.actions.data.ReportsCommonDataReader.ReportsCommonDataRow;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.entities.ReportsSurveyInfo;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SearchAreaPreference;
import surveyor.scommon.source.ReportsCommonPage;
import surveyor.scommon.source.ReportsCommonPage.ReportsButtonType;
import surveyor.scommon.source.ReportsCommonPage.ReportFileType;
import surveyor.scommon.source.ComplianceReportsPage;

public class ComplianceReportsPageActions extends ReportCommonPageActions {

	private ComplianceReportDataReader dataReader = null;
	public static ThreadLocal<ComplianceReportEntity> workingReportsEntity = new ThreadLocal<ComplianceReportEntity>();      		// Stores the ReportsCompliance object from createNewReport action
	public static ThreadLocal<ComplianceReportsDataRow> workingDataRow = new ThreadLocal<ComplianceReportsDataRow>();   // Stores the workingDataRow from createNewReport action

	public ComplianceReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
		initializePageObject(driver, new ComplianceReportsPage(driver, strBaseURL, testSetup));
		setDataReader(new ComplianceReportDataReader(this.excelUtility));
	}

	// Note: Not thread-safe.
	public static void clearStoredObjects() {
		workingReportsEntity.set(null);
		workingDataRow.set(null);
		workingReportViewsDataRows.set(null);
	}

	@Override
	protected void addView(Integer dataRowID) throws Exception {
		List<Map<String, String>> viewList = super.createView(dataRowID);
		this.getComplianceReportsPage().addViews(workingReportsEntity.get().getCustomer(), viewList);
	}

	private void clickComplianceReportButton(Integer dataRowID, ReportsButtonType buttonType) throws Exception {
		ComplianceReportsDataRow compRptDataRow = (ComplianceReportsDataRow)getReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		this.getComplianceReportsPage().clickComplianceReportButton(reportTitle, LoginPageActions.workingDataRow.get().username, buttonType,
				false /*confirmAction*/);  // By default use FALSE confirm action.
	}


	public ComplianceReportsDataRow getWorkingCmpReportsDataRow() throws Exception {
		return workingDataRow.get();
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
		clickComplianceReportButton(dataRowID, ReportsButtonType.Investigate);
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
		clickComplianceReportButton(dataRowID, ReportsButtonType.InvestigatePDF);
		return true;
	}

	/**
	 * Executes clickOnComplianceViewerPDFZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnComplianceViewerInvestigationPDF(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerInvestigationPDF", data, dataRowID);
		ComplianceReportsDataRow reportsDataRow = (ComplianceReportsDataRow) getReportsDataRow(dataRowID);
		this.getComplianceReportsPage().invokeInvestigationPDFFileDownload(reportsDataRow.title);
		return true;
	}

	/**
	 * Executes clickOnComplianceViewerMetaZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnComplianceViewerInvestigationData(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.clickOnComplianceViewerInvestigationData", data, dataRowID);
		ComplianceReportsDataRow reportsDataRow = (ComplianceReportsDataRow) getReportsDataRow(dataRowID);
		this.getComplianceReportsPage().invokeInvestigationDataFileDownload(reportsDataRow.title);
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
		ComplianceReportsDataRow dataRow = (ComplianceReportsDataRow)getReportsDataRow(dataRowID);
		this.getComplianceReportsPage().inputLISAOpacity(dataRow.opacityLISA);
		return true;
	}

	/**
	 * Executes findReportByName action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean findReportByName(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.findReportByName", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("findReportByName", ARG_DATA_ROW_ID, dataRowID);
		ReportsCommonDataRow rptDataRow = getReportsDataRow(dataRowID);
		return findReportInternal(this.getComplianceReportsPage().getFullReportName(rptDataRow.title), 2 /*colIdx = 2 for reportName*/);
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
		ReportsCommonDataRow compRptDataRow = getReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		String createdBy = LoginPageActions.workingDataRow.get().username;
		this.getComplianceReportsPage().investigateReport(reportTitle, createdBy);
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
			ComplianceReportsDataRow dataRow = (ComplianceReportsDataRow)getReportsDataRow(dataRowID);
			reportMode = dataRow.reportMode;
		}
		ReportModeFilter mode = this.getComplianceReportsPage().getReportMode(reportMode);
		this.getComplianceReportsPage().selectReportMode(mode);
		return true;
	}

	/**
	 * Executes verifyStandardReportModeIsShownOnPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyStandardReportModeIsShownOnPage(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyStandardReportModeIsShownOnPage", data, dataRowID);
		return this.getComplianceReportsPage().isStandardReportModeShown();
	}

	/**
	 * Executes verifyRapidResponseReportModeIsShownOnPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyRapidResponseReportModeIsShownOnPage(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyRapidResponseReportModeIsShownOnPage", data, dataRowID);
		return this.getComplianceReportsPage().isRapidResponseReportModeShown();
	}

	/**
	 * Executes verifyManualReportModeIsShownOnPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyManualReportModeIsShownOnPage(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyManualReportModeIsShownOnPage", data, dataRowID);
		return this.getComplianceReportsPage().isManualReportModeShown();
	}

	/**
	 * Executes verifyAnalyticsReportModeIsShownOnPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyAnalyticsReportModeIsShownOnPage(String data, Integer dataRowID) {
		logAction("ComplianceReportsPageActions.verifyManualReportModeIsShownOnPage", data, dataRowID);
		return this.getComplianceReportsPage().isAnalyticsReportModeShown();
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
		return this.getComplianceReportsPage().isManualSurveyModeSelected();
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
	 * Executes waitForInvestigationPDFDownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean waitForInvestigationPDFDownloadToComplete(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.waitForInvestigationPDFDownloadToComplete", data, dataRowID);
		waitForReportFileDownload(dataRowID, ReportFileType.InvestigationPDF, -1);
		return true;
	}

	/**
	 * Executes waitForInvestigationCSVFileDownloadToComplete action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean waitForInvestigationCSVFileDownloadToComplete(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.waitForInvestigationCSVFileDownloadToComplete", data, dataRowID);
		waitForReportFileDownload(dataRowID, ReportFileType.InvestigationCSV, -1);
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
		clickComplianceReportButton(dataRowID, ReportsButtonType.InvestigatePDF);
		// TODO wait for file download to complete.
		//this.getReportsCommonPage().waitForPDFFileDownload();
		this.getComplianceReportsPage().validateInvestigatePDFFile();
		return true;
	}

	/**
	 * Executes verifyLISASMetaDataFile action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyLISAInvestigationTable(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyLISAInvestigationTable", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.InvestigationPDF);
		return this.getComplianceReportsPage().verifyLISAInvestigationTable(downloadPath, getWorkingReportsDataRow().title);
	}

	/**
	 * Executes verifyLISASMetaDataFile action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyGAPInvestigationTable(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifyGAPInvestigationTable", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.InvestigationCSV);
		return this.getComplianceReportsPage().verifyGAPInvestigationTable(downloadPath, getWorkingReportsDataRow().title);
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
		List<String> surveyModes = this.getReportsCommonPage().getSelectedSurveyTableValuesForColumn(ReportsSurveyInfo.ColumnHeaders.SurveyType);
		Log.info(String.format("All Survey mode values found -> ", LogHelper.listToString(surveyModes)));
		List<String> distinctSurveyModes = ArrayUtility.getDistinctValues(surveyModes);
		Log.info(String.format("Distinct Survey mode values found -> ", LogHelper.listToString(distinctSurveyModes)));
		ComplianceReportsDataRow reportsDataRow = (ComplianceReportsDataRow)getReportsDataRow(dataRowID);
		return  this.getReportsCommonPage().isSurveyModesValidForReportMode(reportsDataRow.reportMode, distinctSurveyModes);
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
		List<Integer> surveyRowIDs = ActionArguments.getNumericList(getWorkingReportsDataRow().reportSurveyRowIDs);
		boolean retVal = true;
		// For each survey tag verify survey table.
		for (Integer surveyRowID : surveyRowIDs) {
			ReportModeFilter reportMode = this.getComplianceReportsPage().getReportMode(getWorkingCmpReportsDataRow().reportMode);
			ReportSurveyDataReader surveyDataReader = new ReportSurveyDataReader(this.excelUtility);
			ReportSurveyDataRow surveyDataRow = surveyDataReader.getDataRow(surveyRowID);
			retVal = retVal && this.getReportsCommonPage().verifySurveysTableViaTag(true /*changeMode*/, reportMode, surveyDataRow.surveyTag);
		}
		return retVal;
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
			ComplianceReportsDataRow reportsDataRow = (ComplianceReportsDataRow) this.getReportsDataRow(dataRowID);
			retVal = this.getComplianceReportsPage().verifySurveyModeFilters(this.getComplianceReportsPage().getReportMode(reportsDataRow.reportMode));
		} else {
			ActionArguments.verifyNotNullOrEmpty("verifySurveyModeFiltersByReportMode", ARG_DATA, data);
			retVal = this.getComplianceReportsPage().verifySurveyModeFilters(this.getComplianceReportsPage().getReportMode(data));
		}
		return retVal;
	}

	/**
	 * Executes verifySSRSCoverageForecastTableInfo action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifySSRSCoverageForecastTableInfo(String data, Integer dataRowID) throws Exception {
		return verifySSRSCoverageForecastTableInfo(data, dataRowID,true);
	}
	public boolean verifySSRSCoverageForecastTableInfo(String data, Integer dataRowID, boolean withPredication) throws Exception {
		logAction("ComplianceReportsPageActions.verifySSRSCoverageForecastTableInfo", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().verifyCoverageForecastValuesTable(downloadPath, workingDataRow.get().title,withPredication);
	}
	public Map<String, List<String[]>> getSSRSCoverageForecastTableInfo(String data, Integer dataRowID) throws Exception {
		logAction("ComplianceReportsPageActions.verifySSRSCoverageForecastTableInfoWithPreviousResult", data, dataRowID);
		String downloadPath = getDownloadPath(ReportFileType.PDF);
		return this.getComplianceReportsPage().getSSRSCoverageForecastTableInfo(downloadPath, workingDataRow.get().title);
	}

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickOnComplianceViewerInvestigationPDF")) { return this.clickOnComplianceViewerInvestigationPDF(data, dataRowID); }
		else if (actionName.equals("clickOnComplianceViewerInvestigationData")) { return this.clickOnComplianceViewerInvestigationData(data, dataRowID); }
		else if (actionName.equals("clickOnFirstInvestigateComplianceButton")) { return this.clickOnFirstInvestigateComplianceButton(data, dataRowID); }
		else if (actionName.equals("clickOnInvestigateButton")) { return this.clickOnInvestigateButton(data, dataRowID); }
		else if (actionName.equals("clickOnInvestigatePDFButton")) { return this.clickOnInvestigatePDFButton(data, dataRowID); }
		else if (actionName.equals("enterLISAOpacity")) { return this.enterLISAOpacity(data, dataRowID); }
		else if (actionName.equals("investigateReport")) { return this.investigateReport(data, dataRowID); }
		else if (actionName.equals("selectPercentCoverageForecastCheckBox")) { return this.selectPercentCoverageForecastCheckBox(data, dataRowID); }
		else if (actionName.equals("selectReportMode")) { return this.selectReportMode(data, dataRowID); }
		else if (actionName.equals("verifyInvestigatePDFDownload")) { return this.verifyInvestigatePDFDownload(data, dataRowID); }
		else if (actionName.equals("verifyLISAInvestigationTable")) { return this.verifyLISAInvestigationTable(data, dataRowID); }
		else if (actionName.equals("verifyGAPInvestigationTable")) { return this.verifyGAPInvestigationTable(data, dataRowID); }
		else if (actionName.equals("verifyStandardReportModeIsShownOnPage")) { return this.verifyStandardReportModeIsShownOnPage(data, dataRowID); }
		else if (actionName.equals("verifyRapidResponseReportModeIsShownOnPage")) { return this.verifyRapidResponseReportModeIsShownOnPage(data, dataRowID); }
		else if (actionName.equals("verifyManualReportModeIsShownOnPage")) { return this.verifyManualReportModeIsShownOnPage(data, dataRowID); }
		else if (actionName.equals("verifyStandardSurveyModeIsShownOnPage")) { return this.verifyStandardSurveyModeIsShownOnPage(data, dataRowID); }
		else if (actionName.equals("verifyRapidResponseSurveyModeIsShownOnPage")) { return this.verifyRapidResponseSurveyModeIsShownOnPage(data, dataRowID); }
		else if (actionName.equals("verifyManualSurveyModeIsShownOnPage")) { return this.verifyManualSurveyModeIsShownOnPage(data, dataRowID); }
		else if (actionName.equals("verifyOperatorSurveyModeIsShownOnPage")) { return this.verifyOperatorSurveyModeIsShownOnPage(data, dataRowID); }
		else if (actionName.equals("verifySurveyModeFiltersByReportMode")) { return this.verifySurveyModeFiltersByReportMode(data, dataRowID); }
		else if (actionName.equals("verifySurveysTableInfoByTags")) { return this.verifySurveysTableInfoByTags(data, dataRowID); }
		else if (actionName.equals("verifySearchedSurveysMatchSelectedMode")) { return this.verifySearchedSurveysMatchSelectedMode(data, dataRowID); }
		else if (actionName.equals("waitForInvestigationPDFDownloadToComplete")) { return this.waitForInvestigationPDFDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForInvestigationCSVFileDownloadToComplete")) { return this.waitForInvestigationCSVFileDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("verifySSRSCoverageForecastTableInfo")) { return this.verifySSRSCoverageForecastTableInfo(data, dataRowID); }
		else if (actionName.equals("verifyAnalyticsReportModeIsShownOnPage")) { return this.verifyAnalyticsReportModeIsShownOnPage(data, dataRowID); }
		return false;
	}

	public List<String> getLISAInvestigationPDFData(Integer lisaNumber, Integer dataRowID) throws Exception{
		return this.getComplianceReportsPage().getLISAInvestigationPDFData(lisaNumber, workingDataRow.get().title);
	}

	public Map<String, String> getLISAInvestigationMetaData(Integer lisaNumber, Integer dataRowID) throws Exception{
		return this.getComplianceReportsPage().getLISAInvestigationMetaData(lisaNumber, workingDataRow.get().title);
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
		return (ComplianceReportsPage)this.getPageObject();
	}

	@Override
	public Predicate<ReportsCommonPage> getReportSpecificVerifyMetadataFilesPredicate(String downloadPath, String reportTitle) {
		return reportsPage -> verifyComplianceReportMetadataFiles(reportsPage, downloadPath, reportTitle);
	}

	@Override
	public ReportsCommonDataRow getReportsDataRow(Integer dataRowID) throws Exception {
		ReportsCommonDataRow compRptDataRow = null;
		if (ComplianceReportsPageActions.workingDataRow.get() != null) {
			compRptDataRow = ComplianceReportsPageActions.workingDataRow.get();
			if(compRptDataRow.rowID.equals(dataRowID.toString())){
				return compRptDataRow;
			}
		}
		return getDataReader().getDataRow(dataRowID);
	}

	@Override
	public ReportCommonEntity getWorkingReportsEntity() throws Exception {
		return workingReportsEntity.get();
	}

	@Override
	public void setWorkingReportsEntity(ReportCommonEntity reportsEntity) throws Exception {
		workingReportsEntity.set((ComplianceReportEntity) reportsEntity);
	}

	@Override
	public ReportsCommonDataRow getWorkingReportsDataRow() throws Exception {
		return workingDataRow.get();
	}

	@Override
	public void setWorkingReportsDataRow(ReportsBaseDataRow dataRow) throws Exception {
		workingDataRow.set((ComplianceReportsDataRow) dataRow);
	}

	@Override
	public ReportsCommonPage createNewPageObject() {
		ReportsCommonPage compReportsPage = new ComplianceReportsPage(TestContext.INSTANCE.getDriver(),
				TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		return compReportsPage;
	}

	@Override
	protected void waitForReportSpecificFileDownload(Integer dataRowID, ReportFileType fileType, Integer fileIndex, int zipIndex) throws Exception {
		ReportsCommonDataRow compRptDataRow = getReportsDataRow(dataRowID);
		String reportTitle = compRptDataRow.title;
		String reportName = "";

		switch(fileType) {
		case InvestigationPDF:
			reportName = this.getComplianceReportsPage().getReportPDFFileName(reportTitle, false /*includeExtension*/);
			this.getComplianceReportsPage().waitForInvestigationPDFFileDownload(reportName);
			break;
		case InvestigationCSV:
			reportName = this.getComplianceReportsPage().getReportPDFFileName(reportTitle, false /*includeExtension*/);
			this.getComplianceReportsPage().waitForInvestigationCSVFileDownload(reportName);
			break;
		default:
			break;
		}
	}

	@Override
	protected ReportCommonEntity createNewReportsEntity() throws Exception {
		return new ComplianceReportEntity();
	}

	@Override
	protected ReportCommonEntity createNewReportsEntity(String rptTitle, String customer, String timeZone, String exclusionRadius,
			List<String> listBoundary, List<Map<String, String>> viewList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewLayersList) {
		return new ComplianceReportEntity(rptTitle, TestContext.INSTANCE.getLoggedInUser(), customer, timeZone, exclusionRadius,
				listBoundary, tablesList, null /*surveyorUnit*/, null /*tagList*/, viewList, viewLayersList);
	}

	@Override
	protected void fillReportSpecificWorkingDataForReports(ReportCommonEntity reportEntity) throws Exception {
        String reportMode = getWorkingCmpReportsDataRow().reportMode;
        if(!BaseHelper.isNullOrEmpty(reportMode)){
        	reportEntity.setReportModeFilter(ReportModeFilter.valueOf(reportMode.replaceAll(" ", "")));
        }

        // Set search area preference (highlighting algorithm)
        SearchAreaPreference srchAreaPref = SearchAreaPreference.LISAS;
        if (workingDataRow.get().searchAreaPreference.equalsIgnoreCase(SearchAreaPreference.ASSETBOXES.toString())) {
        	srchAreaPref = SearchAreaPreference.ASSETBOXES;
        }

        reportEntity.setSearchAreaPreference(srchAreaPref);
        addAdditionalWorkingDataForReports(reportEntity);
	}

	@Override
	protected void selectReportSpecificTabularPDFContent(ReportOptTabularPDFContentDataRow pdfContentDataRow) {
		Boolean selectPercentCoverageForecast = Boolean.valueOf(pdfContentDataRow.percentCoverageForecast);
		if (selectPercentCoverageForecast) {
			this.getComplianceReportsPage().selectPercentCoverageForecastCheckBox();
		}
	}

	@Override
	protected boolean verifyReportsSpecificPageFieldsAreCorrect(ReportsCommonDataRow dataRow) throws Exception {
		ComplianceReportsDataRow cmpRptDataRow = (ComplianceReportsDataRow) dataRow;
		String actualReportModeFilter = this.getComplianceReportsPage().getReportModeFilter().toString();
		String actualLISAOpacity = this.getComplianceReportsPage().getLISAOpacity();
		boolean reportModeMatches = (cmpRptDataRow.reportMode.equals(actualReportModeFilter));
		boolean lisaOpacityMatches = (cmpRptDataRow.opacityLISA.equals(actualLISAOpacity));
		return reportModeMatches && lisaOpacityMatches;
	}

	private boolean verifyComplianceReportMetadataFiles(ReportsCommonPage reportsPage, String downloadPath, String reportTitle) {
		return FunctionUtil.wrapException(reportsPage, r -> reportsPage.verifyIsotopicMetaDataFile(downloadPath, reportTitle));
	}
	
	public String getReportModeForSpecifiedReportTitle(String reportTitle,
			String reportCreatedBy) {
		String reportMode = "";
		reportMode = this.getComplianceReportsPage()
				.getReportModeForProvidedReportTitle(reportTitle,
						reportCreatedBy);
		return reportMode;
	}

	public boolean isReportModeAnalytics(String reportMode) {
		if (reportMode.equalsIgnoreCase("Analytics"))
			return true;
		else
			return false;
	}

	public boolean isReportModeStandard(String reportMode) {
		if (reportMode.equalsIgnoreCase("Standard"))
			return true;
		else
			return false;
	}

	public boolean isReportModeRapidResponse(String reportMode) {
		if (reportMode.equalsIgnoreCase("Rapid Response"))
			return true;
		else
			return false;
	}

	public boolean isReportModeManual(String reportMode) {
		if (reportMode.equalsIgnoreCase("Manual"))
			return true;
		else
			return false;
	}
}
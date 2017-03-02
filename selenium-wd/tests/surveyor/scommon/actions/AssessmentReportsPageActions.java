package surveyor.scommon.actions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.data.AssessmentReportDataReader;
import surveyor.scommon.actions.data.AssessmentReportDataReader.AssessmentReportsDataRow;
import surveyor.scommon.actions.data.ReportOptTabularPDFContentDataReader.ReportOptTabularPDFContentDataRow;
import surveyor.scommon.actions.data.ReportsCommonDataReader.ReportsCommonDataRow;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.entities.AssessmentReportEntity;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.entities.EQReportEntity;
import surveyor.scommon.source.AssessmentReportsPage;
import surveyor.scommon.source.Coordinates;
import surveyor.scommon.source.ReportsCommonPage;
import surveyor.scommon.source.ReportsCommonPage.ReportFileType;

/**
 * AssessmentReportsPageActions class.
 *
 * @author spulikkal
 */
public class AssessmentReportsPageActions extends ReportCommonPageActions {

	private AssessmentReportDataReader dataReader = null;
	public static ThreadLocal<AssessmentReportEntity> workingReportsEntity = new ThreadLocal<AssessmentReportEntity>();      		// Stores the ReportsCompliance object from createNewReport action
	public static ThreadLocal<AssessmentReportsDataRow> workingDataRow = new ThreadLocal<AssessmentReportsDataRow>();   // Stores the workingDataRow from createNewReport action

	public AssessmentReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
		initializePageObject(driver, new AssessmentReportsPage(driver, strBaseURL, testSetup));
		setDataReader(new AssessmentReportDataReader(this.excelUtility));
	}

	// Note: Not thread-safe.
	public static void clearStoredObjects() {
		workingReportsEntity.set(null);
		workingDataRow.set(null);
		workingReportViewsDataRows.set(null);
	}

	public AssessmentReportDataReader getDataReader() {
		if (dataReader == null) {
			setDataReader(new AssessmentReportDataReader(this.excelUtility));
		}
		return dataReader;
	}

	public void setDataReader(AssessmentReportDataReader dataReader) {
		this.dataReader = dataReader;
	}

	public AssessmentReportsPage getAssessmentReportsPage() {
		return (AssessmentReportsPage)this.getPageObject();
	}

	/* START - Actions on the Page*/

	/**
	 * Executes findReportByName action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean findReportByName(String data, Integer dataRowID) throws Exception {
		logAction("AssessmentReportsPageActions.findReportByName", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("findReportByName", ARG_DATA_ROW_ID, dataRowID);
		ReportsCommonDataRow rptDataRow = getReportsDataRow(dataRowID);
		return findReportInternal(this.getAssessmentReportsPage().getFullReportName(rptDataRow.title), 2 /*colIdx = 2 for reportName*/);
	}

	/**
	 * Executes verifyReportModeIsNotShownOnPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyReportModeIsNotShownOnPage(String data, Integer dataRowID) {
		logAction("AssessmentReportsPageActions.verifyReportModeIsNotShownOnPage", data, dataRowID);
		return !this.getAssessmentReportsPage().isStandardReportModeShown() &&
				!this.getAssessmentReportsPage().isRapidResponseReportModeShown() &&
				!this.getAssessmentReportsPage().isManualReportModeShown();
	}

	/* END - Actions on the Page*/

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("findReportByName")) { return this.findReportByName(data, dataRowID); }
		else if (actionName.equals("verifyReportModeIsNotShownOnPage")) { return this.verifyReportModeIsNotShownOnPage(data, dataRowID); }
		return false;
	}

	@Override
	public ReportsCommonDataRow getReportsDataRow(Integer dataRowID) throws Exception {
		ReportsCommonDataRow compRptDataRow = null;
		if (AssessmentReportsPageActions.workingDataRow.get() != null) {
			compRptDataRow = AssessmentReportsPageActions.workingDataRow.get();
		} else {
			compRptDataRow = getDataReader().getDataRow(dataRowID);
		}
		return compRptDataRow;
	}

	@Override
	public ReportCommonEntity getWorkingReportsEntity() throws Exception {
		return workingReportsEntity.get();
	}

	@Override
	public void setWorkingReportsEntity(ReportCommonEntity reportsEntity) throws Exception {
		workingReportsEntity.set((AssessmentReportEntity) reportsEntity);
	}

	@Override
	public ReportsCommonDataRow getWorkingReportsDataRow() throws Exception {
		return workingDataRow.get();
	}

	@Override
	public void setWorkingReportsDataRow(ReportsCommonDataRow dataRow) throws Exception {
		workingDataRow.set((AssessmentReportsDataRow) dataRow);
	}

	@Override
	public ReportsCommonPage createNewPageObject() {
		ReportsCommonPage compReportsPage = new AssessmentReportsPage(TestContext.INSTANCE.getDriver(),
				TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		return compReportsPage;
	}

	@Override
	protected void waitForReportSpecificFileDownload(Integer dataRowID, ReportFileType fileType, Integer fileIndex, int zipIndex) throws Exception {
		// No Assessment reports specific action.
	}

	@Override
	protected ReportCommonEntity createNewReportsEntity() throws Exception {
		return new AssessmentReportEntity();
	}
	
	@Override
	protected ReportCommonEntity createNewReportsEntity(String rptTitle, String customer, String timeZone, String exclusionRadius,
			List<String> listBoundary, List<Map<String, String>> viewList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewLayersList) {
		return new AssessmentReportEntity(rptTitle, TestContext.INSTANCE.getLoggedInUser(), customer, timeZone, exclusionRadius,
				listBoundary, tablesList, null /*surveyorUnit*/, null /*tagList*/, viewList, viewLayersList);
	}

	@Override
	protected void fillReportSpecificWorkingDataForReports(ReportCommonEntity reportEntity) throws Exception {
		addAdditionalWorkingDataForReports(reportEntity);
    }

	@Override
	protected void selectReportSpecificTabularPDFContent(ReportOptTabularPDFContentDataRow pdfContentDataRow) {
		// No Assessment reports specific action.
	}

	@Override
	protected boolean verifyReportsSpecificPageFieldsAreCorrect(ReportsCommonDataRow dataRow) throws Exception {
		return true;
	}
}
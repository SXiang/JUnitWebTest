package surveyor.scommon.actions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.data.EQReportDataReader;
import surveyor.scommon.actions.data.EQReportDataReader.EQReportsDataRow;
import surveyor.scommon.actions.data.ReportOptTabularPDFContentDataReader.ReportOptTabularPDFContentDataRow;
import surveyor.scommon.actions.data.ReportsCommonDataReader.ReportsCommonDataRow;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.entities.EQReportEntity;
import surveyor.scommon.source.EQReportsPage;
import surveyor.scommon.source.ReportsCommonPage;
import surveyor.scommon.source.ReportsCommonPage.ReportFileType;

/**
 * EQReportsPageActions class.
 *
 *
 */
public class EQReportsPageActions extends ReportCommonPageActions {

	private EQReportDataReader dataReader = null;
	public static ThreadLocal<EQReportEntity> workingReportsEntity = new ThreadLocal<EQReportEntity>();      		// Stores the ReportsCompliance object from createNewReport action
	public static ThreadLocal<EQReportsDataRow> workingDataRow = new ThreadLocal<EQReportsDataRow>();   // Stores the workingDataRow from createNewReport action

	public EQReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
		initializePageObject(driver, new EQReportsPage(driver, strBaseURL, testSetup));
		setDataReader(new EQReportDataReader(this.excelUtility));
	}

	// Note: Not thread-safe.
	public static void clearStoredObjects() {
		workingReportsEntity.set(null);
		workingDataRow.set(null);
		workingReportViewsDataRows.set(null);
	}

	public EQReportDataReader getDataReader() {
		if (dataReader == null) {
			setDataReader(new EQReportDataReader(this.excelUtility));
		}
		return dataReader;
	}

	public void setDataReader(EQReportDataReader dataReader) {
		this.dataReader = dataReader;
	}

	public EQReportsPage getEQReportsPage() {
		return (EQReportsPage)this.getPageObject();
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
		logAction("EQReportsPageActions.findReportByName", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("findReportByName", ARG_DATA_ROW_ID, dataRowID);
		ReportsCommonDataRow rptDataRow = getReportsDataRow(dataRowID);
		return findReportInternal(this.getEQReportsPage().getFullReportName(rptDataRow.title), 2 /*colIdx = 2 for reportName*/);
	}

	/**
	 * Executes verifyReportModeIsNotShownOnPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyReportModeIsNotShownOnPage(String data, Integer dataRowID) {
		logAction("EQReportsPageActions.verifyReportModeIsNotShownOnPage", data, dataRowID);
		return !this.getEQReportsPage().isStandardReportModeShown() &&
				!this.getEQReportsPage().isRapidResponseReportModeShown() &&
				!this.getEQReportsPage().isManualReportModeShown();
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
		if (EQReportsPageActions.workingDataRow.get() != null) {
			compRptDataRow = EQReportsPageActions.workingDataRow.get();
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
		workingReportsEntity.set((EQReportEntity) reportsEntity);
	}

	@Override
	public ReportsCommonDataRow getWorkingReportsDataRow() throws Exception {
		return workingDataRow.get();
	}

	@Override
	public void setWorkingReportsDataRow(ReportsCommonDataRow dataRow) throws Exception {
		workingDataRow.set((EQReportsDataRow) dataRow);
	}

	@Override
	public ReportsCommonPage createNewPageObject() {
		ReportsCommonPage compReportsPage = new EQReportsPage(TestContext.INSTANCE.getDriver(),
				TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		return compReportsPage;
	}

	@Override
	protected void waitForReportSpecificFileDownload(Integer dataRowID, ReportFileType fileType, Integer fileIndex, int zipIndex) throws Exception {
		// No EQ reports specific action.
	}

	@Override
	protected ReportCommonEntity createNewReportsEntity(String rptTitle, String customer, String timeZone, String exclusionRadius,
			List<String> listBoundary, List<Map<String, String>> viewList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewLayersList) {
		
		//TODO: need customization !!!
		return new EQReportEntity();
	}

	@Override
	protected void fillReportSpecificWorkingDataForReports(ReportCommonEntity reportEntity) throws Exception {
		// No EQ reports specific action.
    }

	@Override
	protected void selectReportSpecificTabularPDFContent(ReportOptTabularPDFContentDataRow pdfContentDataRow) {
		// No EQ reports specific action.
	}

	@Override
	protected boolean verifyReportsSpecificPageFieldsAreCorrect(ReportsCommonDataRow dataRow) throws Exception {
		return true;
	}
}
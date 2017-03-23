package surveyor.scommon.actions;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;

import common.source.ExcelUtility;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.data.CoordinateDataReader;
import surveyor.scommon.actions.data.CoordinateDataReader.CoordinateDataRow;
import surveyor.scommon.actions.data.EQReportDataReader;
import surveyor.scommon.actions.data.EQReportDataReader.EQReportsDataRow;
import surveyor.scommon.actions.data.LineSegmentDataReader;
import surveyor.scommon.actions.data.LineSegmentDataReader.LineSegmentDataRow;
import surveyor.scommon.actions.data.ReportsBaseDataReader.ReportsBaseDataRow;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.entities.EQReportEntity;
import surveyor.scommon.source.Coordinates;
import surveyor.scommon.source.EQReportsPage;
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
		EQReportsDataRow rptDataRow = getReportsDataRow(dataRowID);
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
	public EQReportsDataRow getReportsDataRow(Integer dataRowID) throws Exception {
		EQReportsDataRow eqRptDataRow = null;
		if (EQReportsPageActions.workingDataRow.get() != null) {
			eqRptDataRow = EQReportsPageActions.workingDataRow.get();
		} else {
			eqRptDataRow = getDataReader().getDataRow(dataRowID);
		}
		return eqRptDataRow;
	}

	@Override
	public EQReportEntity getWorkingReportsEntity() throws Exception {
		return workingReportsEntity.get();
	}

	@Override
	public void setWorkingReportsEntity(ReportCommonEntity reportsEntity) throws Exception {
		workingReportsEntity.set((EQReportEntity) reportsEntity);
	}

	@Override
	public EQReportsDataRow getWorkingReportsDataRow() throws Exception {
		return workingDataRow.get();
	}

	@Override
	public void setWorkingReportsDataRow(ReportsBaseDataRow dataRow) throws Exception {
		workingDataRow.set((EQReportsDataRow) dataRow);
	}

	@Override
	public EQReportsPage createNewPageObject() {
		EQReportsPage eqReportsPage = new EQReportsPage(TestContext.INSTANCE.getDriver(),
				TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		return eqReportsPage;
	}

	@Override
	protected void waitForReportSpecificFileDownload(Integer dataRowID, ReportFileType fileType, Integer fileIndex, int zipIndex) throws Exception {
		// No EQ reports specific action.
	}

	@Override
	protected ReportCommonEntity createNewReportsEntity() throws Exception {
		return new EQReportEntity();
	}
	
	protected EQReportEntity createNewReportsEntity(String rptTitle, String customer, String timeZone, String eqLocationParameter,
			List<List<Coordinates>> lineSegments) {		
		return new EQReportEntity(rptTitle, customer, timeZone, eqLocationParameter, lineSegments);
	}

	@Override
	protected void fillReportSpecificWorkingDataForReports(ReportCommonEntity reportEntity) throws Exception {
		EQReportEntity rpt = (EQReportEntity)reportEntity;
		String eqLocationParameter = getWorkingReportsDataRow().eqLocationParameter;
		List<List<Coordinates>> lineSegments = buildLineSegmentInfoList(getWorkingReportsDataRow(), this.excelUtility);
		
		rpt.setEQLocationParameter(eqLocationParameter);
		rpt.setLineSegments(lineSegments);
    }

	protected List<List<Coordinates>> buildLineSegmentInfoList(EQReportsDataRow dataRow, ExcelUtility excelUtility) throws Exception {
		List<Integer> lineSegmentRowIDs = ActionArguments.getNumericList(dataRow.lineSegmentRowIDs);
		List<List<Coordinates>> lineSegmentInfoList = getLineSegmentInfoList(excelUtility, lineSegmentRowIDs);
		return lineSegmentInfoList;
	}

	public static List<List<Coordinates>> getLineSegmentInfoList(ExcelUtility excelUtility, List<Integer> lineSegmentRowIDs) throws Exception {
		List<List<Coordinates>> lineSegmentInfoList = new ArrayList<List<Coordinates>>();
		for (Integer rowID : lineSegmentRowIDs) {
			if(rowID==0){
				continue;
			}
			LineSegmentDataReader lineSegmentDataReader = new LineSegmentDataReader(excelUtility);
			LineSegmentDataRow lineSegmentDataRow = lineSegmentDataReader.getDataRow(rowID);
			
			List<Integer> coordinatesRowIDs = ActionArguments.getNumericList(lineSegmentDataRow.coordinatesRowIDs);
			lineSegmentInfoList.add(getCoordinatesInfoList(excelUtility, coordinatesRowIDs));
		}
		return lineSegmentInfoList;
	}

	public static List<Coordinates> getCoordinatesInfoList(ExcelUtility excelUtility, List<Integer> coordinatesRowIDs) throws Exception {
		List<Coordinates> coordinates = new ArrayList<Coordinates>();
		for (Integer rowID : coordinatesRowIDs) {
			if(rowID==0){
				continue;
			}
			CoordinateDataReader coordinateDataReader = new CoordinateDataReader(excelUtility);
			CoordinateDataRow coordinateDataRow = coordinateDataReader.getDataRow(rowID);
			
			coordinates.add(new Coordinates(coordinateDataRow.longitude, coordinateDataRow.latitude));
		}
		return coordinates;
	}
}
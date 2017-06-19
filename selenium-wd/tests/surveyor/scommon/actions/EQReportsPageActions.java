package surveyor.scommon.actions;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;

import common.source.ExcelUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.data.CoordinateDataReader;
import surveyor.scommon.actions.data.CoordinateDataReader.CoordinateDataRow;
import surveyor.scommon.actions.data.EQReportDataReader;
import surveyor.scommon.actions.data.EQReportDataReader.EQReportsDataRow;
import surveyor.scommon.actions.data.LineSegmentDataReader;
import surveyor.scommon.actions.data.LocationDataReader;
import surveyor.scommon.actions.data.LineSegmentDataReader.LineSegmentDataRow;
import surveyor.scommon.actions.data.LocationDataReader.LocationDataRow;
import surveyor.scommon.actions.data.ReportsBaseDataReader.ReportsBaseDataRow;
import surveyor.scommon.actions.data.ReportsCommonDataReader.ReportsCommonDataRow;
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

	/**
	 * Executes clickOnEQViewerPDF action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnViewerPDF(String data, Integer dataRowID) throws Exception {
		logAction("EQReportsPageActions.clickOnViewerPDF", data, dataRowID);
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		this.getReportsCommonPage().invokeEQPDFFileDownload(reportsDataRow.title);
		return true;
	}

	@Override
	public boolean clickOnReportViewerView(String data, Integer dataRowID) throws Exception {
		String reportName = this.getReportsCommonPage().getReportPDFFileName(getWorkingReportsDataRow().title, false /*includeExtension*/);
		this.getReportsCommonPage().invokeViewFileDownload(reportName, ReportFileType.EQView.toString(), 1);
		return true;
	}

	/**
	 * Executes verifyViewsImageWithBaseline action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean verifyViewImageWithBaseline(String data, Integer dataRowID) throws Exception {
		logAction("EQReportsPageAction.verifyViewImageWithBaseline", data, dataRowID);
		ReportsCommonDataRow reportsDataRow = getReportsCommonDataRow(dataRowID);
		return  this.getReportsCommonPage().verifyViewsImages(TestContext.INSTANCE.getTestSetup().getDownloadPath(),
					reportsDataRow.title, reportsDataRow.tCID, ReportFileType.EQView.toString(), false);
	}

	@Override
	protected boolean verifySSRSTableInfos(String downloadPath) throws Exception {
		Log.info("Executing verifyDrivingSurveysTable()...");
		boolean verifyDrivingSurveysTable = this.getReportsCommonPage().verifyDrivingSurveysTable(downloadPath, getWorkingReportsDataRow().title);
		Log.info(String.format("verifyDrivingSurveysTable() returned - '%b'", verifyDrivingSurveysTable));

		Log.info("Executing verifyEmissionsQuantificationTable()...");
		boolean verifyEmissionsQuantificationTable = this.getEQReportsPage().verifyEmissionsQuantificationTable(downloadPath, getWorkingReportsDataRow().title);
		Log.info(String.format("verifyEmissionsQuantificationTable() returned - '%b'", verifyEmissionsQuantificationTable));

		Log.info(String.format("verifyEmissionsQuantificationTable = %b; verifyDrivingSurveysTable = %b",
				verifyEmissionsQuantificationTable, verifyDrivingSurveysTable));
		return verifyEmissionsQuantificationTable && verifyDrivingSurveysTable;
	}

	/**
	 * Executes verifyViewsImagesWithBaselines action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	@Override
	public boolean verifyViewsImagesWithBaselines(String data, Integer dataRowID) throws Exception {
		logAction("EQReportsPageActions.verifyViewsImagesWithBaselines", data, dataRowID);
		boolean retVal = true;
		EQReportsDataRow reportsDataRow = getReportsDataRow(dataRowID);
		retVal = retVal && this.getReportsCommonPage().verifyViewsImages(TestContext.INSTANCE.getTestSetup().getDownloadPath(),
					reportsDataRow.title, reportsDataRow.tCID, ReportFileType.EQView.toString(), false);
		return retVal;
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
		String reportName = "", viewName = "";

		switch(fileType) {
		case EQView:
			reportName = this.getReportsCommonPage().getReportPDFFileName(getWorkingReportsDataRow().title, false /*includeExtension*/);
			viewName = fileType.toString();
			this.getReportsCommonPage().waitForViewFileDownload(reportName, viewName);
			break;
		default:
			Log.warn("File type is not supported in EQ report: "+fileType);
			break;
		}
	}

	@Override
	public boolean waitForViewDownloadToComplete(String data, Integer dataRowID) throws Exception {
		logAction("EQReportsPageActions.waitForViewDownloadToComplete", data, dataRowID);
		waitForReportFileDownload(dataRowID, ReportFileType.EQView, 1);
		return true;
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
		EQReportsDataRow workingReportsDataRow = getWorkingReportsDataRow();
		List<List<Coordinates>> lineSegments = buildLineSegmentInfoList(workingReportsDataRow, this.excelUtility);
		String locationRowID = workingReportsDataRow.locationRowID;
		if (!ActionArguments.isEmpty(locationRowID)) {
			LocationDataRow locationDataRow = getLocationDataRow(Integer.valueOf(locationRowID));
			String eqLocationParameter = locationDataRow.name;
			rpt.setEQLocationParameter(eqLocationParameter);
		}

		rpt.setSelectLineSegmentsUsingJS(isSelectLineSegmentsUsingJS(workingReportsDataRow));
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

	/**
	 * Returns the location data row for the specified user data row.
	 * @param dataRow - user data row with location row id information.
	 * @return - location data row
	 * @throws Exception
	 */
	private LocationDataRow getLocationDataRow(Integer dataRowID) throws Exception {
		if (ManageLocationPageActions.workingDataRow.get() != null) {
			return ManageLocationPageActions.workingDataRow.get();
		} else {
			LocationDataReader locationDataReader = new LocationDataReader(excelUtility);
			LocationDataRow locationDataRow = locationDataReader.getDataRow(dataRowID);
			return locationDataRow;
		}
	}

	private Boolean isSelectLineSegmentsUsingJS(EQReportsDataRow dataRow) throws Exception {
		List<Integer> lineSegmentRowIDs = ActionArguments.getNumericList(dataRow.lineSegmentRowIDs);
		if (lineSegmentRowIDs != null && lineSegmentRowIDs.size() > 0) {
			for (Integer lSegRowID : lineSegmentRowIDs) {
				LineSegmentDataReader lineSegmentDataReader = new LineSegmentDataReader(excelUtility);
				LineSegmentDataRow lineSegmentDataRow = lineSegmentDataReader.getDataRow(lSegRowID);
				Boolean useJS = Boolean.valueOf(lineSegmentDataRow.setUsingJS);
				if (useJS) {
					return true;
				}
			}
		}

		return false;
	}
}
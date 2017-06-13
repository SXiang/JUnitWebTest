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
import surveyor.scommon.actions.data.FacilityEQReportDataReader;
import surveyor.scommon.actions.data.FacilityEQReportDataReader.FacilityEQReportsDataRow;
import surveyor.scommon.actions.data.LineSegmentDataReader;
import surveyor.scommon.actions.data.LineSegmentDataReader.LineSegmentDataRow;
import surveyor.scommon.actions.data.ReportsBaseDataReader.ReportsBaseDataRow;
import surveyor.scommon.actions.data.ReportsCommonDataReader.ReportsCommonDataRow;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.entities.FacilityEQReportEntity;
import surveyor.scommon.source.Coordinates;
import surveyor.scommon.source.FacilityEQReportsPage;
import surveyor.scommon.source.ReportsCommonPage.ReportFileType;

/**
 * FacilityEQReportsPageActions class.
 *
 *
 */
public class FacilityEQReportsPageActions extends ReportCommonPageActions {

	private FacilityEQReportDataReader dataReader = null;
	public static ThreadLocal<FacilityEQReportEntity> workingReportsEntity = new ThreadLocal<FacilityEQReportEntity>();      		// Stores the FacilityEQReport object from createNewReport action
	public static ThreadLocal<FacilityEQReportsDataRow> workingDataRow = new ThreadLocal<FacilityEQReportsDataRow>();   // Stores the workingDataRow from createNewReport action

	public FacilityEQReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
		initializePageObject(driver, new FacilityEQReportsPage(driver, strBaseURL, testSetup));
		setDataReader(new FacilityEQReportDataReader(this.excelUtility));
	}

	// Note: Not thread-safe.
	public static void clearStoredObjects() {
		workingReportsEntity.set(null);
		workingDataRow.set(null);
		workingReportViewsDataRows.set(null);
	}

	public FacilityEQReportDataReader getDataReader() {
		if (dataReader == null) {
			setDataReader(new FacilityEQReportDataReader(this.excelUtility));
		}
		return dataReader;
	}

	public void setDataReader(FacilityEQReportDataReader dataReader) {
		this.dataReader = dataReader;
	}

	public FacilityEQReportsPage getFacilityEQReportsPage() {
		return (FacilityEQReportsPage)this.getPageObject();
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
		logAction("FacilityEQReportsPageActions.findReportByName", data, dataRowID);
		ActionArguments.verifyGreaterThanZero("findReportByName", ARG_DATA_ROW_ID, dataRowID);
		FacilityEQReportsDataRow rptDataRow = getReportsDataRow(dataRowID);
		return findReportInternal(this.getFacilityEQReportsPage().getFullReportName(rptDataRow.title), 2 /*colIdx = 2 for reportName*/);
	}

	/**
	 * Executes verifyReportModeIsNotShownOnPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyReportModeIsNotShownOnPage(String data, Integer dataRowID) {
		logAction("FacilityEQReportsPageActions.verifyReportModeIsNotShownOnPage", data, dataRowID);
		return !this.getFacilityEQReportsPage().isStandardReportModeShown() &&
				!this.getFacilityEQReportsPage().isRapidResponseReportModeShown() &&
				!this.getFacilityEQReportsPage().isManualReportModeShown();
	}

	/**
	 * Executes clickOnViewerEmissionDataZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnViewerEmissionDataZIP(String data, Integer dataRowID) throws Exception {
		logAction("FacilityEQReportsPageActions.clickOnViewerPDF", data, dataRowID);
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		this.getReportsCommonPage().invokeEmissionDataZipFileDownload(reportsDataRow.title);
		return true;
	}

	/**
	 * Executes clickOnViewerConcentrationChartZIP action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 */
	public boolean clickOnViewerConcentrationChartZIP(String data, Integer dataRowID) throws Exception {
		logAction("FacilityEQReportsPageActions.clickOnViewerPDF", data, dataRowID);
		ReportsBaseDataRow reportsDataRow = getReportsDataRow(dataRowID);
		this.getReportsCommonPage().invokeConcentrationChartZipFileDownload(reportsDataRow.title);
		return true;
	}
	
	@Override
	public boolean clickOnReportViewerView(String data, Integer dataRowID) throws Exception {
		String reportName = this.getReportsCommonPage().getReportPDFFileName(getWorkingReportsDataRow().title, false /*includeExtension*/);
		this.getReportsCommonPage().invokeViewFileDownload(reportName, ReportFileType.FacilityEQView.toString(), 1);
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
		logAction("FacilityEQReportsPageAction.verifyViewImageWithBaseline", data, dataRowID);
		ReportsCommonDataRow reportsDataRow = getReportsCommonDataRow(dataRowID);
		return  this.getReportsCommonPage().verifyViewsImages(TestContext.INSTANCE.getTestSetup().getDownloadPath(),
					reportsDataRow.title, reportsDataRow.tCID, ReportFileType.FacilityEQView.toString(), false);
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
		logAction("FacilityEQReportsPageActions.verifyViewsImagesWithBaselines", data, dataRowID);
		boolean retVal = true;
		FacilityEQReportsDataRow reportsDataRow = getReportsDataRow(dataRowID);
		retVal = retVal && this.getReportsCommonPage().verifyViewsImages(TestContext.INSTANCE.getTestSetup().getDownloadPath(),
					reportsDataRow.title, reportsDataRow.tCID, ReportFileType.FacilityEQView.toString(), false);
		return retVal;
	}

	/* END - Actions on the Page*/

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("findReportByName")) { return this.findReportByName(data, dataRowID); }
		else if (actionName.equals("verifyReportModeIsNotShownOnPage")) { return this.verifyReportModeIsNotShownOnPage(data, dataRowID); }
		else if (actionName.equals("waitForConcentrationChartZIPFileDownloadToComplete")) { return this.waitForConcentrationChartZIPFileDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("waitForEmissionDataZIPFileDownloadToComplete")) { return this.waitForEmissionDataZIPFileDownloadToComplete(data, dataRowID); }
		else if (actionName.equals("clickOnViewerConcentrationChartZIP")) { return this.clickOnViewerConcentrationChartZIP(data, dataRowID); }
		else if (actionName.equals("clickOnViewerEmissionDataZIP")) { return this.clickOnViewerEmissionDataZIP(data, dataRowID); }
		return false;
	}

	@Override
	public FacilityEQReportsDataRow getReportsDataRow(Integer dataRowID) throws Exception {
		FacilityEQReportsDataRow eqRptDataRow = null;
		if (FacilityEQReportsPageActions.workingDataRow.get() != null) {
			eqRptDataRow = FacilityEQReportsPageActions.workingDataRow.get();
		} else {
			eqRptDataRow = getDataReader().getDataRow(dataRowID);
		}
		return eqRptDataRow;
	}

	@Override
	public FacilityEQReportEntity getWorkingReportsEntity() throws Exception {
		return workingReportsEntity.get();
	}

	@Override
	public void setWorkingReportsEntity(ReportCommonEntity reportsEntity) throws Exception {
		workingReportsEntity.set((FacilityEQReportEntity) reportsEntity);
	}

	@Override
	public FacilityEQReportsDataRow getWorkingReportsDataRow() throws Exception {
		return workingDataRow.get();
	}

	@Override
	public void setWorkingReportsDataRow(ReportsBaseDataRow dataRow) throws Exception {
		workingDataRow.set((FacilityEQReportsDataRow) dataRow);
	}

	@Override
	public FacilityEQReportsPage createNewPageObject() {
		FacilityEQReportsPage feqReportsPage = new FacilityEQReportsPage(TestContext.INSTANCE.getDriver(),
				TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		return feqReportsPage;
	}

	@Override
	protected void waitForReportSpecificFileDownload(Integer dataRowID, ReportFileType fileType, Integer fileIndex, int zipIndex) throws Exception {
		String reportName = "", viewName = "";

		switch(fileType) {
		case FacilityEQView:
			reportName = this.getReportsCommonPage().getReportPDFFileName(getWorkingReportsDataRow().title, false /*includeExtension*/);
			viewName = fileType.toString();
			this.getReportsCommonPage().waitForViewFileDownload(reportName, viewName);
			break;
		default:
			Log.warn("File type is not supported in FacilityEQ report: "+fileType);
			break;
		}
	}
	
	@Override
	public boolean waitForViewDownloadToComplete(String data, Integer dataRowID) throws Exception {
		logAction("FacilityEQReportsPageActions.waitForViewDownloadToComplete", data, dataRowID);
		waitForReportFileDownload(dataRowID, ReportFileType.FacilityEQView, 1);
		return true;
	}
	
	public boolean waitForEmissionDataZIPFileDownloadToComplete(String data, Integer dataRowID) throws Exception {
		logAction("FacilityEQReportsPageActions.waitForEmissionDataZIPFileDownloadToComplete", data, dataRowID);
		waitForReportFileDownload(dataRowID, ReportFileType.EmissionDataZIP, 1);
		return true;
	}

	public boolean waitForConcentrationChartZIPFileDownloadToComplete(String data, Integer dataRowID) throws Exception {
		logAction("FacilityEQReportsPageActions.waitForConcentrationChartZIPFileDownloadToComplete", data, dataRowID);
		waitForReportFileDownload(dataRowID, ReportFileType.ConcentrationChartZIP, 1);
		return true;
	}
	
	@Override
	protected ReportCommonEntity createNewReportsEntity() throws Exception {
		return new FacilityEQReportEntity();
	}
	
	protected FacilityEQReportEntity createNewReportsEntity(String rptTitle, String customer, String timeZone, String facilityEQLocationParameter,
			List<Coordinates> shapeCoordinates, boolean showLisas) {		
		return new FacilityEQReportEntity(rptTitle, customer, timeZone, facilityEQLocationParameter, shapeCoordinates, showLisas);
	}

	@Override
	protected void fillReportSpecificWorkingDataForReports(ReportCommonEntity reportEntity) throws Exception {
		FacilityEQReportEntity rpt = (FacilityEQReportEntity)reportEntity;
		String facilityEQLocationParameter = getWorkingReportsDataRow().facilityEQLocationParameter;
		List<Coordinates> shapeCoordinates = buildShapeInfoList(getWorkingReportsDataRow(), this.excelUtility);
		boolean showLisas = getWorkingReportsDataRow().showLisas;
		
		rpt.setFacilityEQLocationParameter(facilityEQLocationParameter);
		rpt.setShapeCoordinates(shapeCoordinates);
		rpt.setShowLisas(showLisas);
    }

	protected List<Coordinates> buildShapeInfoList(FacilityEQReportsDataRow dataRow, ExcelUtility excelUtility) throws Exception {
		List<Integer> shapeCoordinateRowIDs = ActionArguments.getNumericList(dataRow.shapeCoordinatesRowIDs);
		List<Coordinates> shapeInfoList = getCoordinatesInfoList(excelUtility, shapeCoordinateRowIDs);
		return shapeInfoList;
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
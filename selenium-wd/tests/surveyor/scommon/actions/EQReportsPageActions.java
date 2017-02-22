package surveyor.scommon.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import common.source.BaseHelper;
import common.source.ExcelUtility;
import common.source.NumberUtility;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.Customer;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.EQReportDataReader;
import surveyor.scommon.actions.data.ReportOptTabularPDFContentDataReader;
import surveyor.scommon.actions.data.ReportOptViewLayersDataReader;
import surveyor.scommon.actions.data.ReportSurveyDataReader;
import surveyor.scommon.actions.data.ReportViewsDataReader;
import surveyor.scommon.actions.data.EQReportDataReader.EQReportsDataRow;
import surveyor.scommon.actions.data.ReportOptTabularPDFContentDataReader.ReportOptTabularPDFContentDataRow;
import surveyor.scommon.actions.data.ReportSurveyDataReader.ReportSurveyDataRow;
import surveyor.scommon.actions.data.ReportViewsDataReader.ReportViewsDataRow;
import surveyor.scommon.actions.data.ReportsCommonDataReader.ReportsCommonDataRow;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.entities.ReportsSurveyInfo;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.entities.EQReportEntity;
import surveyor.scommon.source.Coordinates;
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
	public void setWorkingReportsDataRow(ReportsCommonDataRow dataRow) throws Exception {
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

	protected EQReportEntity createNewReportsEntity(String rptTitle, String customer, String timeZone, String eqLocationParameter,
			List<List<Coordinates>> lineSegments) {		
		return new EQReportEntity(rptTitle, customer, timeZone, eqLocationParameter, lineSegments);
	}

	@Override
	protected void fillReportSpecificWorkingDataForReports(ReportCommonEntity reportEntity) throws Exception {
		// No EQ reports specific action.
    }
	
	public EQReportEntity fillWorkingDataForReports(Integer dataRowID) throws Exception {
		setWorkingReportsDataRow(getDataReader().getDataRow(dataRowID));

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
		String eqLocationParameter = getWorkingReportsDataRow().eqLocationParameter;

		// Set survey info list.
		List<ReportsSurveyInfo> reportsSurveyInfoList = super.buildReportSurveyInfoList(getWorkingReportsDataRow(), this.excelUtility);
		List<List<Coordinates>> lineSegments = buildLineSegmentInfoList(getWorkingReportsDataRow(), this.excelUtility);
		// Create report specific Entity object.
		EQReportEntity rpt = createNewReportsEntity(rptTitle, customer, timeZone, eqLocationParameter, lineSegments);

		rpt.setSurveyInfoList(reportsSurveyInfoList);

        // Fill report specific info.
        fillReportSpecificWorkingDataForReports(rpt);

        setWorkingReportsEntity(rpt);		// Store the working report properties.
		return rpt;
	}
	
	protected List<List<Coordinates>> buildLineSegmentInfoList(EQReportsDataRow dataRow, ExcelUtility excelUtility) throws Exception {
		List<Integer> lineSegmentRowIDs = ActionArguments.getNumericList(dataRow.lineSegmentRowIDs);
		List<List<Coordinates>> lineSegmentInfoList = getLineSegmentInfoList(excelUtility, lineSegmentRowIDs);
		return lineSegmentInfoList;
	}

	public static List<List<Coordinates>> getLineSegmentInfoList(ExcelUtility excelUtility, List<Integer> lineSegmentRowIDs) throws Exception {
		List<List<Coordinates>> lineSegmentInfoList = new ArrayList<List<Coordinates>>();
		List<Coordinates> lineSegment = new ArrayList<Coordinates>();
		for (Integer rowID : lineSegmentRowIDs) {
			if(rowID==0){
				continue;
			}
			LineSegmentDataReader lineSegmentDataReader = new LineSegmentDataReader(excelUtility);
			LinePointDataRow linePointDataRow = lineSegmentDataReader.getDataRow(rowID);
			
			reportsSurveyInfoList.add(new ReportsSurveyInfo(
					surveyDataRow.surveySurveyor, surveyDataRow.surveyUsername, surveyDataRow.surveyTag,
					surveyDataRow.surveyStartDate, surveyDataRow.surveyEndDate,
					modeFilter, Boolean.valueOf(surveyDataRow.surveyGeoFilterON),
					NumberUtility.getIntegerValueOf(surveyDataRow.numberofSurveystoInclude),
					Boolean.valueOf(surveyDataRow.selectAllSurveys)));
		}
		return reportsSurveyInfoList;
	}


}
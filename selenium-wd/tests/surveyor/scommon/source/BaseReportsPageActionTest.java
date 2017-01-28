package surveyor.scommon.source;

import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.actions.ReportCommonPageActions;

public class BaseReportsPageActionTest extends BaseReportsPageTest {

	protected static final String EMPTY = BaseActions.EMPTY;
	protected static final Integer NOTSET = BaseActions.NOTSET;

	//==============================================================================================================
	// These variables are added for unit testing page actions.
	// Remove these variables after verifications.
	//==============================================================================================================
	private static final Integer testDataRowID1_User1 = 6;
	private static final Integer testDataRowID1_Report_Std1 = 49;

	//===============================================================================================================

	public enum ReportTestRunMode {
		// This is the default test mode. All actions will be executed in this mode.
		FullTestRun ("FullTestRun"),
		// Use this mode to verify page actions for Compliance report.
		// When test is running in this mode, we'll skip the createNewReport, waitForReportToComplete, modifyReport actions.
		UnitTestRun ("UnitTestRun");

		private final String name;

		ReportTestRunMode(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	private boolean cleanUpPerformed = false;

	public BaseReportsPageActionTest() {
		super();
	}

	//==============================================================================================================
	// Set working data row and reports row.
	// This method is used for unit testing page actions.
	//==============================================================================================================
	protected void setReportWorkingDataForUnitTest(ReportCommonPageActions reportsPageAction, Integer dataRowID) throws Exception {
		reportsPageAction.fillWorkingDataForReports(getReportRowID(dataRowID));
	}

	protected void createNewReport(ReportCommonPageActions reportsPageAction, Integer reportDataRowID) throws Exception {
		if (getTestRunMode() == ReportTestRunMode.FullTestRun) {
			reportsPageAction.createNewReport(EMPTY, reportDataRowID);
		}
	}

	protected void modifyReport(ReportCommonPageActions reportsPageAction, Integer reportDataRowID) throws Exception {
		if (getTestRunMode() == ReportTestRunMode.FullTestRun) {
			reportsPageAction.modifyReport(EMPTY, reportDataRowID);
		} else if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			// If running in unit test mode go back to manage reports page.
			reportsPageAction.open(EMPTY, NOTSET);
		}
	}

	protected void clickOnConfirmDeleteReport(ReportCommonPageActions reportsPageAction, Integer reportDataRowID) throws Exception {
		reportsPageAction.clickOnConfirmDeleteReport(EMPTY, reportDataRowID);
	}

	protected void deleteReport(ReportCommonPageActions reportsPageAction, Integer reportDataRowID) throws Exception {
		reportsPageAction.searchAndDeleteReport(EMPTY, reportDataRowID);
	}

	protected void waitForReportGenerationToComplete(ReportCommonPageActions reportsPageAction, Integer reportDataRowID) throws Exception {
		if (getTestRunMode() == ReportTestRunMode.FullTestRun) {
			reportsPageAction.waitForReportGenerationToComplete(EMPTY, reportDataRowID);
		}
	}

	protected static Integer getUserRowID(Integer dataRowID) {
		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			return testDataRowID1_User1;
		}
		return dataRowID;
	}

	protected static Integer getReportRowID(Integer dataRowID) {
		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			return testDataRowID1_Report_Std1;
		}
		return dataRowID;
	}

	protected static Integer getUnitTestReportRowID() {
		return testDataRowID1_Report_Std1;
	}

	protected static Integer getUnitTestUserRowID() {
		return testDataRowID1_User1;
	}

	public boolean isCleanUpPerformed() {
		return cleanUpPerformed;
	}

	public void setCleanUpPerformed(boolean cleanUpPerformed) {
		this.cleanUpPerformed = cleanUpPerformed;
	}
}
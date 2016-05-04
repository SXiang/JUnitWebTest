package surveyor.scommon.source;

import surveyor.scommon.actions.ComplianceReportsPageActions;

public class BaseReportsPageActionTest extends BaseReportsPageTest {

	protected static final String EMPTY = "";
	protected static final Integer NOTSET = -1;
	
	//==============================================================================================================
	// These variables are added for unit testing page actions.
	// Remove these variables after verifications. 
	//==============================================================================================================
	private static final Integer testDataRowID1_User1 = 11;
	private static final Integer testDataRowID1_Report_Std1 = 49;
	
	//===============================================================================================================
	
	public enum ReportTestRunMode {
		// This is the default test mode. All actions will be executed in this mode.
		FullTestRun ("FullTestRun"),
		// Use this mode to verify page actions for Compliance report.
		// When test is running in this mode, we'll skip the createNewReport and waitForReportToComplete actions.
		UnitTestRun ("UnitTestRun");
		
		private final String name;

		ReportTestRunMode(String nm) {
			name = nm;
		}
		
		public String toString() {
			return this.name;
		}
	}

	private static ReportTestRunMode testRunMode = ReportTestRunMode.FullTestRun; 	

	public BaseReportsPageActionTest() {
		super();
	}

	protected static ReportTestRunMode getTestRunMode() {
		return testRunMode;
	}

	protected static void setTestRunMode(ReportTestRunMode testRunModeValue) {
		testRunMode = testRunModeValue;
	}
	
	//==============================================================================================================
	// Set working data row and reports compliance row.
	// This method is used for unit testing page actions.
	//==============================================================================================================
	protected void setComplianceReportWorkingDataForUnitTest(ComplianceReportsPageActions complianceReportsPageAction, Integer dataRowID) throws Exception {
		complianceReportsPageAction.fillWorkingDataForReports(getReportRowID(dataRowID));
	}	
	
	protected void createNewComplianceReport(ComplianceReportsPageActions complianceReportsPageAction, Integer reportDataRowID) throws Exception {
		if (getTestRunMode() == ReportTestRunMode.FullTestRun) {
			complianceReportsPageAction.createNewReport(EMPTY, reportDataRowID);
		}
	}

	protected void modifyComplianceReport(ComplianceReportsPageActions complianceReportsPageAction, Integer reportDataRowID) throws Exception {
		if (getTestRunMode() == ReportTestRunMode.FullTestRun) {
			complianceReportsPageAction.modifyReport(EMPTY, reportDataRowID);
		} else if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			// If running in unit test mode go back to manage reports page.
			complianceReportsPageAction.open(EMPTY, NOTSET);
		}
	}

	protected void clickConfirmDeleteInComplianceReport(ComplianceReportsPageActions complianceReportsPageAction, Integer reportDataRowID) throws Exception {
		if (getTestRunMode() == ReportTestRunMode.FullTestRun) {
			complianceReportsPageAction.clickOnConfirmDeleteReport(EMPTY, reportDataRowID);
		} 
	}

	protected void waitForComplianceReportGenerationToComplete(ComplianceReportsPageActions complianceReportsPageAction, Integer reportDataRowID) {
		if (getTestRunMode() == ReportTestRunMode.FullTestRun) {
			complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, reportDataRowID);
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
}
package surveyor.scommon.source;

import surveyor.scommon.actions.BaseActions;
import java.util.HashMap;
import org.junit.After;
import common.source.ExceptionUtility;
import common.source.Log;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;

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

	private static ReportTestRunMode testRunMode = ReportTestRunMode.FullTestRun; 	
	
	private HashMap<Integer, ComplianceReportsPageActions> newReportsMap = null;

	public BaseReportsPageActionTest() {
		super();
	}

	@After
	public void afterTestMethod() {
		try {
			deleteComplianceReport();
			homePage.logout();
			LoginPageActions.workingDataRow = null;
			ComplianceReportsPageActions.workingDataRow = null;
		} catch (Exception e) {
			Log.error("Error when deleting compliance report page. Exception message:");
			Log.error(ExceptionUtility.getStackTraceString(e));
		}
	}

	protected void removeReportDataRowIDFromMap(Integer reportDataRowID) {
		if (newReportsMap != null) {
			if (newReportsMap.containsKey(reportDataRowID)) {
				newReportsMap.remove(reportDataRowID);
			}
		}
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
			storeNewReportDataRowID(reportDataRowID, complianceReportsPageAction);
		}
	}

	protected void modifyComplianceReport(ComplianceReportsPageActions complianceReportsPageAction, Integer reportDataRowID) throws Exception {
		if (getTestRunMode() == ReportTestRunMode.FullTestRun) {
			complianceReportsPageAction.modifyReport(EMPTY, reportDataRowID);
			storeNewReportDataRowID(reportDataRowID, complianceReportsPageAction);
		} else if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			// If running in unit test mode go back to manage reports page.
			complianceReportsPageAction.open(EMPTY, NOTSET);
		}
	}

	protected void clickOnConfirmDeleteReport(ComplianceReportsPageActions complianceReportsPageAction, Integer reportDataRowID) throws Exception {
		complianceReportsPageAction.clickOnConfirmDeleteReport(EMPTY, reportDataRowID);
		removeReportDataRowIDFromMap(reportDataRowID);
	}

	protected void deleteReport(ComplianceReportsPageActions complianceReportsPageAction, Integer reportDataRowID) throws Exception {
		complianceReportsPageAction.searchAndDeleteReport(EMPTY, reportDataRowID);
		removeReportDataRowIDFromMap(reportDataRowID);
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

	private void storeNewReportDataRowID(Integer reportDataRowID, ComplianceReportsPageActions complianceReportsPageAction) {
		if (newReportsMap == null) {
			newReportsMap = new HashMap<Integer, ComplianceReportsPageActions>();
		}
		if (!newReportsMap.containsKey(reportDataRowID)) {
			newReportsMap.put(reportDataRowID, complianceReportsPageAction);
		}
	}

	private void deleteComplianceReport() throws Exception {
		if (getTestRunMode() == ReportTestRunMode.FullTestRun) {
			if (newReportsMap != null) {
				for (Integer reportDataRowID : newReportsMap.keySet()) {
					ComplianceReportsPageActions complianceReportsPageAction = newReportsMap.get(reportDataRowID);
					complianceReportsPageAction.open(EMPTY, reportDataRowID);
					complianceReportsPageAction.searchAndDeleteReport(EMPTY, reportDataRowID);
				}
			}
		}
	}
}

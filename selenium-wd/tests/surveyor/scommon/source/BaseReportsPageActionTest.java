package surveyor.scommon.source;

import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.junit.Assert;

import common.source.ExceptionUtility;
import common.source.Log;
import surveyor.dataaccess.source.Customer;
import surveyor.dbseed.source.DbSeedExecutor;
import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ReportCommonPageActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.TestDataGenerator;

public class BaseReportsPageActionTest extends BaseReportsPageTest {

	protected static final String EMPTY = BaseActions.EMPTY;
	protected static final Integer NOTSET = BaseActions.NOTSET;

	//==============================================================================================================
	// These variables are added for unit testing page actions.
	// Remove these variables after verifications.
	//==============================================================================================================
	private static final Integer testDataRowID1_User1 = 2;
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

	/**
	 * Creates a new customer and a survey with specified parameters. Executes GIS seed and runs the steps specified in Predicate.
	 * @param pageAction - page action object
	 * @param custSrvInfo - customer and survey row id info
	 * @param reportDataRowID - report row id
	 * @param testActions - test actions to run
	 * @return
	 */
	protected boolean executeAsNewCustomerWithSurveyAndGISData(ReportCommonPageActions pageAction, CustomerSurveyInfoEntity custSrvInfo, Integer reportDataRowID, Predicate<ReportCommonPageActions> testActions) {
		try {
			new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo);
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION when creating customer and survey. Error -> %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		Customer customer = Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name);
		return executeAsCustomerWithGISData(pageAction, customer.getId(), reportDataRowID, testActions);
	}

	/**
	 * Executes GIS seed for specified customer and runs the steps specified in Predicate.
	 * @param pageAction - page action object
	 * @param customerId - guid of customer in DB
	 * @param reportDataRowID - report row id
	 * @param testActions - test actions to run
	 * @return
	 */
	protected boolean executeAsCustomerWithGISData(ReportCommonPageActions pageAction, String customerId, Integer reportDataRowID, Predicate<ReportCommonPageActions> testActions) {
		boolean retVal = false;
		try {
			// Add GIS seed for customer.
			DbSeedExecutor.executeGisSeed(customerId);

			retVal = testActions.test(pageAction);

		} catch (Exception ex) {
			Assert.fail(String.format("Exception: %s", ExceptionUtility.getStackTraceString(ex)));

		} finally {
			// Delete report before deleting GIS data pushed by test to prevent FK constraint violation.
			pageAction.open(EMPTY, getReportRowID(reportDataRowID));
			try {
				pageAction.deleteReport(EMPTY, getReportRowID(reportDataRowID));
				DbSeedExecutor.cleanUpGisSeed(customerId);
			} catch (Exception e) {
				Log.error(String.format("Error in FINALLY. Exception - %s", ExceptionUtility.getStackTraceString(e)));
			}
		}

		return retVal;
	}

	protected void createMultipleReports(ReportCommonPageActions reportsPageAction, Integer reportDataRowID1, Integer numReportsToCreate) {
		IntStream.rangeClosed(1, numReportsToCreate).forEach(i -> {
			try {
				reportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
				createNewReport(reportsPageAction, getReportRowID(reportDataRowID1));
				waitForReportGenerationToComplete(reportsPageAction, getReportRowID(reportDataRowID1));
			} catch (Exception e) {
				Log.error(String.format("Error creating report. Exception - %s", ExceptionUtility.getStackTraceString(e)));
			}
		});
	}
}
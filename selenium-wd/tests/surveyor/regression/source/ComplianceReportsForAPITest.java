package surveyor.regression.source;

import common.source.Log;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.dataaccess.source.Report;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ReportsCompliance;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsForAPITest extends BaseReportsPageActionTest {

		private static LoginPageActions loginPageAction;
		private static ComplianceReportsPageActions complianceReportsPageAction;

		@BeforeClass
		public static void beforeClass() {
			initializeTestObjects();
		}

		@Before
		public void beforeTest() throws Exception {
			initializeTestObjects();
			initializePageActions();
			// Select run mode here.
			setPropertiesForTestRunMode();
		}

		private static void setPropertiesForTestRunMode() throws Exception {
			setTestRunMode(ReportTestRunMode.FullTestRun);

			if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
				complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
			}
		}
	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}

	/**
	 * Copied from Test Case ID: TC1373_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastAssetsReportArea
	 * Test Description: - Generate Compliance Report as Picarro Admin and include Percent Coverage Forecast, Assets and Report Area
	 * Script: -
	 *	- -  Log in as Picarro Admin
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Time Zone : PST, Survey Mode: Standard, Exclusion Radius: 0
	 *	- - Add 2 or 3 surveys with different tag values
	 *	- - Select Customer boundary and select any Plat
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Assets, Report Area and Forecast check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1373, location = ComplianceReportDataProvider.class)
	public void APITesting_GenerateComplianceReportsForAPISecurityTests(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastAssetsReportArea ..." +
			 "\nTest Description: Generate Compliance Report as Picarro Admin and include Percent Coverage Forecast, Assets and Report Area");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));

		String[] titles = {"APISecurityTest_Report001","APISecurityTest_Report002",
				"APISecurityTest_ReportDelete001","APISecurityTest_ReportDelete002"};
		ReportsCompliance rpt = complianceReportsPageAction.fillWorkingDataForReports(reportDataRowID1);

		for(String rptTitle:titles){
			Report report = Report.getReport(rptTitle);
			if(report==null||report.getId()==null||report.getId().isEmpty()){
				rpt.setRptTitle(rptTitle);
				ComplianceReportsPageActions.workingDataRow.get().title = rptTitle;
				getComplianceReportsPage().addNewReport(rpt, true);
				waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			}
		}
	}

	/**
	 * Wait for the completion of US3493
	 * Test Description: - Generate Assessment Report as Picarro Admin
	 */
//	@Ignore
//	@UseDataProvider(value = AssessmentReportDataProvider.Assessment_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1373, location = AssessmentReportDataProvider.class)
//	public void APITesting_GenerateAssessmentReportsForAPISecurityTests(
//			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
//		Log.info("\nRunning TC1373_GenerateAssessmentReportPicarroAdminIncludePercentCoverageForecastAssetsReportArea ..." +
//			 "\nTest Description: Generate Assessment Report as Picarro Admin and include Percent Coverage Forecast, Assets and Report Area");
//
//		loginPageAction.open(EMPTY, NOTSET);
//		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
//		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
//
//		String[] titles = {"APISecurityTest_Assessment001","APISecurityTest_Assessment002"};
//		ReportsAssessment rpt = AssessmentReportsPageAction.fillWorkingDataForReports(reportDataRowID1);
//
//		for(String rptTitle:titles){
//			rpt.setRptTitle(rptTitle);
//			AssessmentReportsPageActions.workingDataRow.title = rptTitle;
//			getAssessmentReportsPage().addNewReport(rpt, true);
//			waitForAssessmentReportGenerationToComplete(AssessmentReportsPageAction, getReportRowID(reportDataRowID1));
//		}
//
//	}

	private ComplianceReportsPage getComplianceReportsPage() {
		return (ComplianceReportsPage)getReportsPage();
	}
}

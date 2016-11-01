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
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsForAPITest extends BaseReportsPageActionTest {

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();
		// Select run mode here.
		setPropertiesForTestRunMode();
	}

	@Before
	public void beforeTest() throws Exception{
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
	 * Results: -
	 *	- - Report generated successfully
	 *	- - Percent Coverage Assets and Report Area value is displayed
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *	- - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1373, location = ComplianceReportDataProvider.class)
	public void APITesting_GenerateComplianceReportsForAPISecurityTests(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1373_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastAssetsReportArea ..." +
			 "\nTest Description: Generate Compliance Report as Picarro Admin and include Percent Coverage Forecast, Assets and Report Area");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

//        rptTitle = "APISecurityTest_Report001";
//        complianceReportsPageAction.copyReport(rptTitle, getReportRowID(reportDataRowID1));
//        rptTitle = "APISecurityTest_Report002";
//        complianceReportsPageAction.copyReport(rptTitle, getReportRowID(reportDataRowID1));
//        rptTitle = "APISecurityTest_ReportDelete001";
//        complianceReportsPageAction.copyReport(rptTitle, getReportRowID(reportDataRowID1));
//        rptTitle = "APISecurityTest_ReportDelete002";
//        complianceReportsPageAction.copyReport(rptTitle, getReportRowID(reportDataRowID1));
	}

	private ComplianceReportsPage getComplianceReportsPage() {
		return (ComplianceReportsPage)getReportsPage();
	}
}

package surveyor.regression.source;

import common.source.Log;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.CR_EQLINES_MESSAGE;
import static surveyor.scommon.source.SurveyorConstants.CR_SURVEYMISSING_MESSAGE;
import static surveyor.scommon.source.SurveyorConstants.CR_VALUEMISSING_MESSAGE;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.dataprovider.AnalyticReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class AnalyticsReportsWithNewSurveyPageTest extends BaseReportsPageActionTest {

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static ManageLocationPageActions manageLocationPageActions;

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
		manageLocationPageActions = new ManageLocationPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}

	/**
	 * Test Case ID: TC2339_LisasAreFilteredOutAccordingToThresholdLevel
	 * Test Description: - Analytics Report - LISAs are filtered out according to threshold level set on Locations page
	 * 			When an Analytics Report is run, LISAs below the threshold value indicated on the Location page should be filtered out.
	 * 			Changing the threshold level and generating a new report with the same survey should result in filtering according to the newly entered values
	 * Script: -
	 *	- - Log in as Picarro admin user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Select Analytics report and select a survey
	 *	- - Add 2 Views with base map value, satellite map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: -
	 *	- - Report generated successfully
	 */
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2339, location = AnalyticReportDataProvider.class)
	public void TC2339_LisasAreFilteredOutAccordingToThresholdLevel(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2, CustomerSurveyInfoEntity surveyInfoEntity) throws Exception {
		Log.info("\nRunning TC2339_LisasAreFilteredOutAccordingToThresholdLevel ..." +
			 "\nTest Description:  Analytics Report - LISAs are filtered out according to threshold level set on Locations paget");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		// Create new customer with survey + GIS data and then execute test steps.
		assertTrue(executeAsNewCustomerWithSurveyAndGISData(complianceReportsPageAction, surveyInfoEntity, reportDataRowID1, pageAction -> {
			try {
				String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);
				loginPageAction.open(EMPTY, NOTSET);
				loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

				pageAction.open(EMPTY, getReportRowID(reportDataRowID1));
				createNewReport(pageAction, getReportRowID(reportDataRowID1));
				waitForReportGenerationToComplete(pageAction, getReportRowID(reportDataRowID1));

				pageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
				assertTrue(pageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
				assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableInfo(ReportModeFilter.Analytics.toString(), getReportRowID(reportDataRowID1)));
				
				// Modify PSFilter on location
				loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
				loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
//				manageLocationPageActions.
								
				// Verifyt indications agatin
				loginPageAction.open(EMPTY, NOTSET);
				loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

				pageAction.open(EMPTY, getReportRowID(reportDataRowID1));
				createNewReport(pageAction, getReportRowID(reportDataRowID1));
				waitForReportGenerationToComplete(pageAction, getReportRowID(reportDataRowID1));

				pageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
				assertTrue(pageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
				assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableInfo(ReportModeFilter.Analytics.toString(), getReportRowID(reportDataRowID1)));
			} catch (Exception ex) {
				return false;
			}
			return true;
		}));

	}

}

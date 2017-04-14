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
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.dataprovider.AnalyticReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class AnalyticsReportsPageTest extends BaseReportsPageActionTest {

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
	 * Test Case ID: TC2338_LisasBelowThresholdValueAreFilteredOut
	 * Test Description: - Analytics Report - LISAs below threshold value are filtered out
	 * 			When an Analytics Report is run, LISAs below the threshold value indicated on the Location page should be filtered out.
	 * 			The SSRS, View PDFs, CSVs and Shapefiles should reflect this.
	 * Script: -
	 *	- - Log in as Picarro admin user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Select Analytics report and select a survey
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: -
	 *	- - Report generated successfully
	 *	- - The SSRS, View PDFs, CSVs and Shapefiles should not show indications that are below the threshold
	 */

	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2338, location = AnalyticReportDataProvider.class)
	public void TC2338_LisasBelowThresholdValueAreFilteredOut(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2338_LisasBelowThresholdValueAreFilteredOut ..." +
			 "\nTest Description: Analytics Report - LISAs below threshold value are filtered out");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

        complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));

		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));


        assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(ReportModeFilter.Analytics.toString(), getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableInfo(ReportModeFilter.Analytics.toString(), getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(ReportModeFilter.Analytics.toString(), getReportRowID(reportDataRowID1)));
	}
}

package surveyor.unittest.source;

import common.source.Log;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.dataprovider.AnalyticReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class AnalyticsReportsPageUnitTest extends BaseReportsPageActionTest {

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
	 * Test Case ID: UnitTest001_GenerateAnalyticReportPicarroAdminUser
	 * Test Description: - Generate Analytics Report as Picarro admin user
	 * Script: -
	 *	- - Log in as Picarro admin user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Select Analytics report and select a survey
	 *	- - Add View with no map: none
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: -
	 *	- - Report generated successfully
	 */
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST001, location = AnalyticReportDataProvider.class)
	public void UnitTest001_GenerateAnalyticReportPicarroAdminUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning UnitTest001_GenerateAnalyticReportPicarroAdminUser ..." +
			 "\nTest Description: UnitTest001_GenerateAnalyticReportPicarroAdminUser");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}
	
	/**
	 * Test Case ID: UnitTest002_GenerateAnalyticReportPicarroAdminUser
	 * Test Description: - Generate Analytics Report as Picarro admin user
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
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST002, location = AnalyticReportDataProvider.class)
	public void UnitTest002_GenerateAnalyticReportPicarroAdminUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning UnitTest002_GenerateAnalyticReportPicarroAdminUser ..." +
			 "\nTest Description: UnitTest002_GenerateAnalyticReportPicarroAdminUser");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * As a user, I want the pdf reports to suppress some indications (LISAs or asset boxes) 
	 * if they do not meet the pre-defined priority score threshold, 
	 * which will be set by an administrator based on customer and location. 
	 */
	/**
	 * Test Case ID: TC2338_LisasBelowThresholdValueAreFilteredOut
	 * Test Description: - Analytics Report - LISAs below threshold value are filtered out
	 * 			When an Analytics Report is run, LISAs below the threshold value indicated on the Location page should be filtered out.
	 * 			The SSRS, View PDFs, CSVs and Shapefiles should reflect this.
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


        assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
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
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2339_LisasAreFilteredOutAccordingToThresholdLevel ..." +
			 "\nTest Description:  Analytics Report - LISAs are filtered out according to threshold level set on Locations paget");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}

}

package surveyor.regression.source;

import common.source.Log;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest4 extends BaseReportsPageActionTest {
	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;
	
	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;
	
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
		loginPageAction = new LoginPageActions(driver, getBaseURL(), getTestSetup());
		homePageAction = new HomePageActions(driver, getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
		testEnvironmentAction = new TestEnvironmentActions();
	}

	/**
	 * Test Case ID: TC153_CopyModifyComplianceReportFromPreviouslyRunReports
	 * Script: -  	
	 *	- - Login as picarro admin
	 *	- - On Home Page, click Reports -& Compliance 
	 *	- - Click on 'Copy' button 
	 *  - - Modify few details and generate report
	 *  - - Base Map Value as None 
	 *	- - Click on OK and click ZIP (PDF) icon
	 * Results: - 
	 *	- - Copy Compliance Report screen should have same details that were present while generating report. Report title, report area, surveys, colors selected, view combinations, etc
	 *	- - Report with specified modification is generated successfully
	 */
	@Test
	public void TC153_CopyModifyComplianceReportFromPreviouslyRunReports() throws Exception {
		Log.info("\nRunning TC153_CopyModifyComplianceReportFromPreviouslyRunReports ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, NOTSET);
		createNewComplianceReport(complianceReportsPageAction, 4);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 4);
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.title, NOTSET);
		complianceReportsPageAction.verifyReportPageFieldsAreCorrect(EMPTY, 4);
		modifyComplianceReport(complianceReportsPageAction, 5);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 5);
		complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, 5);
	}
 
	/**
	 * Test Case ID: TC180_GenerateManualReportFromExistingReportsHavingSurveysOfStandardOrRapidResponseTypesUsingCopyFeature
	 * Script: -  	
	 *	- - Generate Standard report (include Standard survey) 
	 *	- - Click on copy button present next to the generated report 
	 *  - - Change the report mode to Manual and provide min amplitude value 
	 *  - - Click on OK button
	 * Results: - 
	 *	- - UI should remove surveys other than manual and only manual surveys shows up in the filter
	 *	- - Manual Report should have only manual surveys.
	 */
	@Test
	public void TC180_GenerateManualReportFromExistingReportsHavingSurveysOfStandardOrRapidResponseTypesUsingCopyFeature() throws Exception {
		Log.info("\nRunning TC180_GenerateManualReportFromExistingReportsHavingSurveysOfStandardOrRapidResponseTypesUsingCopyFeature ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, NOTSET);
		createNewComplianceReport(complianceReportsPageAction, 6);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 6);
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.title, NOTSET);
		modifyComplianceReport(complianceReportsPageAction, 7);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 7);
		complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, 7);

		// TODO: Methods missing for survey filter verifications. Tracked by Task TA862
	}
 
	/**
	 * Test Case ID: TC182_GenerateStandardReportFromExistingReportsHavingSurveyOfRapidResponseTypeUsingCopyFeature
	 * Script: -  	
	 *	- - Generate Rapid Response report (include Rapid Response survey) 
	 *	- - Click on copy button present next to the generated report 
	 *  - - Change the report mode to Standard
	 *  - - Click on OK button
	 * Results: - 
	 *	- - UI should not allow user to generate the Standard report if that report contains Rapid Response Surveys
	 *	- - Standard report can have Standard and Operator Surveys
	 */
	@Test
	public void TC182_GenerateStandardReportFromExistingReportsHavingSurveyOfRapidResponseTypeUsingCopyFeature() throws Exception {
		Log.info("\nRunning TC182_GenerateStandardReportFromExistingReportsHavingSurveyOfRapidResponseTypeUsingCopyFeature ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, NOTSET);
		createNewComplianceReport(complianceReportsPageAction, 8);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 8);
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.title, NOTSET);
		modifyComplianceReport(complianceReportsPageAction, 9);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 9);
		complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, 9);

		// TODO: Methods missing for survey filter verifications. Tracked by Task TA862
	}
}
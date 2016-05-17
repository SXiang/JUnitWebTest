package surveyor.regression.source;

import common.source.Log;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest4 extends BaseReportsPageTest {
	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;
	
	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;

	private static ComplianceReportsPage complianceReportsPage;
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();
	}

	/**
	 * Initializes the page action objects.
	 */
	protected static void initializePageActions() {
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, baseURL, testSetup);
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
		complianceReportsPageAction.createNewReport(EMPTY, 4);
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, 4);
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.title, NOTSET);
		complianceReportsPageAction.verifyReportPageFieldsAreCorrect(EMPTY, 4);
		complianceReportsPageAction.modifyReport(EMPTY, 5);
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, 5);
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
		complianceReportsPageAction.createNewReport(EMPTY, 6);
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, 6);
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.title, NOTSET);
		complianceReportsPageAction.modifyReport(EMPTY, 7);
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, 7);
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
		complianceReportsPageAction.createNewReport(EMPTY, 8);
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, 8);
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.title, NOTSET);
		complianceReportsPageAction.modifyReport(EMPTY, 9);
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, 9);
		complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, 9);

		// TODO: Methods missing for survey filter verifications. Tracked by Task TA862
	}
}

package surveyor.regression.source;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.ExceptionUtility;
import common.source.HostSimInstructions;
import common.source.Log;
import common.source.TestSetup;
import common.source.HostSimInstructions.Action;
import common.source.HostSimInstructions.Measurement;
import common.source.HostSimInstructions.Selector;
import common.source.OLMapEntities.Indication;
import common.source.RegexUtility;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.DriverViewDataProvider;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.TestDataGenerator;
import surveyor.scommon.source.SurveyorTestRunner;

/*
 * **** NOTES ****:
 *  1. Action based tests that work on MapView (Survey, Observer, Driver) can derive from BaseMapViewTest.
 *  2. If any of the tests do NOT use TestEnvironment actions for starting Analyzer and simulator then
 *  they should follow this convention to start simulator:
 *    Mark the test as TC*_SimulatorTest_* and it will be detected as Simulator based test and will trigger
 *    installation of Simulator pre-requisites before running the test.
 *
 */
@RunWith(SurveyorTestRunner.class)
public class DriverViewPageTest_EQ extends BaseMapViewTest {

	private static DriverViewPageActions driverViewPageAction;
	private static SurveyViewPageActions surveyViewPageAction;

	public DriverViewPageTest_EQ() throws IOException {
		super();
	}

	@BeforeClass
	public static void beforeTestClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTestMethod() {
		try {
			initializeTestObjects();
			initializePageActions();
			TestSetup.restartAnalyzer();
		} catch (UnknownHostException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		} catch (IOException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		}
	}

	private void initializePageActions() {
		driverViewPageAction = ActionBuilder.createDriverViewPageAction();
		surveyViewPageAction = ActionBuilder.createSurveyViewPageAction();
	}

	/**
	 *	Test Case ID: TC1042
	 *	Test Case Description:  Disable EQ feature for customer having EQ access and check driver view
	 *	SCRIPT:
	 *	-  Login to driver view as Customer's user
	 *	- Click on Mode button
	 *	- Start the EQ survey and complete it
	 *	- Login as Picarro Admin
	 *	- Navigate to Manage Customer Page
	 *	- Click on Edit button of Customer having EQ privilege
	 *	- Disable the EQ access by un-selecting the EQ check box
	 *	- Click on OK button
	 *	- Login to driver view as Customer's user
	 *	- Click on Mode button
	 *	RESULT:
	 *	- EQ Survey mode option is present
	 *	- EQ survey should be completed successfully
	 *	- EQ survey mode option is not present and user cannot perform EQ survey
	**/
	@Test /* Depend on US4438 */
	public void TC1042_DisableEQFeatureAndCheckDriverView() throws Exception {
		Log.info("\nTestcase - TC1042_DisableEQFeatureAndCheckDriverView ...\n");

		final String testCaseId = "TC1042";

		final int picAdminUserDataRowID = 6;
		final int DB3_ANALYZER_ROW_ID = 73;	 	/* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 7;	/* Survey information  */
		final int SURVEY_RUNTIME_IN_SECONDS = 60; /* Number of seconds to run the survey for. */
		final int newCustomerRowID = 14;
		final int newLocationRowID = 17;
		final int newCustomerUserRowID = 26;
		final int newSurveyorRowID = 25;
		final int newAnalyzerRowID = 23;
		final int newRefGasBottleRowID = 7;

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, picAdminUserDataRowID);   /* Picarro Admin */

		CustomerSurveyInfoEntity custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
				newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID);
		new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo, (driverPageAction) -> {
			assertTrue(driverPageAction.verifySurveyModeWarningCorrect(Resources.getResource(ResourceKeys.Dialog_EQModeActive), NOTSET));
			return true;
		});
		
		
	}
}
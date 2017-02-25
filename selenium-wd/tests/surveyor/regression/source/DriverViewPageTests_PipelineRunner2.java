package surveyor.regression.source;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import surveyor.dataprovider.DriverViewDataProvider;
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
public class DriverViewPageTests_PipelineRunner2 extends DriverViewPageTests_PipelineRunnerBase {

	public DriverViewPageTests_PipelineRunner2() throws IOException {
		super();
	}

	/**
	 * Test Case ID: TC_TBD_ActionTest_DriverViewStartSurveyMultipleTimesMethaneSurveys_LongDB3
	 * Script: -
	 *	- Start a survey using Methane Analyzer with appropriate survey details in the dialog, select Survey Type "Standard" and click OK
	 *	- Run survey for about a minute.
	 *	- From the Mode menu, click Stop Survey
	 *	- From the Mode menu, click Start Survey again
	 *	- When the dialog pops up, change the survey type "Manual", "RapidResponse", "Assessment" or "Operator" and click Start Survey
	 *	- From the Mode menu, click Stop Survey again
	 *	- Repeat start/stop sequence for multiple times.
	 * Results: -
	 *	- Verify surveys are created correctly and there is NO runtime error in PipelineRunner.
	 *
	 * @throws Exception
 	 **/
	@Test
	@UseDataProvider(value = DriverViewDataProvider.DRIVERVIEW_START_STOP_SURVEY_METH_LONGDB3_PROVIDER, location = DriverViewDataProvider.class)
	public void TC_TBD_ActionTest_DriverViewStartSurveyMultipleTimesMethaneSurveys_LongDB3(Integer userDataRowID,
			Integer analyzerDb3DataRowID, Integer[] surveyDataRowIDs, Integer surveyRuntimeInSeconds,
			Integer numberOfSurveys) throws Exception {
		Log.info("\nRunning TC_TBD_ActionTest_DriverViewStartSurveyMultipleTimesMethaneSurveys_LongDB3");

		executeReplayWithLongDB3(userDataRowID, analyzerDb3DataRowID, surveyDataRowIDs, surveyRuntimeInSeconds, numberOfSurveys);
	}
}
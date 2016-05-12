package surveyor.regression.source;

import org.junit.Test;

import common.source.Log;
import common.source.TestContext;
import surveyor.scommon.source.SurveyorBaseTest;

public class UploadDb3Test extends SurveyorBaseTest {

	public UploadDb3Test() {
	}

	/**
	 * Test to verify that surveys specified in test properties are correctly uploaded.
	 * Pre-Req for running this test:
	 * - In Test.properties:
	 * 		surveyUpload.Surveys = {6,1,46,60};{3,2,47,60}  <-- [Surveys to be uploaded are specified in this format: 
	 * 					 										 {loginUserRowID1,db3AnalyzerRowID1,surveyRowID1,surveyRuntimeInSeconds1};{loginUserRowID2,db3AnalyzerRowID2,surveyRowID2,surveyRuntimeInSeconds2}]
	 * REMARKS: When running this test set the surveyUpload.Enabled=FALSE to prevent 
	 *   surveys from being uploaded in TestSetup initialize()
	 * 
	 * @throws Exception
	 */
	@Test
	public void PushDB3Data_Test() throws Exception {
		Log.info("\nRunning PushDB3Data_Test ...");
		TestContext.INSTANCE.getTestSetup().uploadSurveys();
	}	
}

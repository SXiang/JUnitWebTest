package surveyor.regression.source;

import org.junit.Test;

import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.source.SurveyorBaseTest;

public class UploadDb3Test extends SurveyorBaseTest {

	public UploadDb3Test() {
	}

	/**
	 * Unit Test to verify that surveys specified in test properties are correctly uploaded.
	 * Pre-Req for running this test:
	 * - In Test.properties:
	 * 		surveyUpload.Enabled = false   <-- [This value is set to FALSE]
	 * 		surveyUpload.Surveys = {6,1,46,60};{3,2,47,60}  <-- [Surveys to be uploaded are specified in this format: 
	 * 					 										 {loginUserRowID1,db3AnalyzerRowID1,surveyRowID1,surveyRuntimeInSeconds1};{loginUserRowID2,db3AnalyzerRowID2,surveyRowID2,surveyRuntimeInSeconds2}]
	 * @throws Exception
	 */
	@Test
	public void PushDB3Data_Test() throws Exception {
		Log.info("\nRunning PushDB3Data_Test ...");
		
		TestSetup testSetupObj = TestContext.INSTANCE.getTestSetup();
		if (!testSetupObj.isUploadSurveyEnabled()) {
			testSetupObj.uploadSurveys();
		}
	}	
}

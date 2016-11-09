package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.LicensedFeatureAndVisibilityTest;
import surveyor.regression.source.MeasurementSessionsPageTest;
import surveyor.regression.source.SurveyViewPageTest;
import surveyor.regression.source.SurveyViewPageTest1;
import surveyor.regression.source.SurveyViewPageTest2;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	MeasurementSessionsPageTest.class
	,SurveyViewPageTest.class
	,SurveyViewPageTest1.class
	,SurveyViewPageTest2.class
	,LicensedFeatureAndVisibilityTest.class
})
public class RegressionTestSuite4 {

	@BeforeClass
    public static void setUpClass() {
        Log.info("Setup for RegressionTestSuite4 executing...");
    }

    @AfterClass
    public static void tearDownClass() {
    	Log.info("Teardown for RegressionTestSuite4 executing...");
    }
}

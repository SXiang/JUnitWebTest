package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.SurveyViewPageTest_EQ;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	SurveyViewPageTest_EQ.class
})
public class RegressionTestSuite6 {

	@BeforeClass
    public static void setUpClass() {
        Log.info("Setup for RegressionTestSuite6 executing...");
    }

    @AfterClass
    public static void tearDownClass() {
    	Log.info("Teardown for RegressionTestSuite6 executing...");
    }
}

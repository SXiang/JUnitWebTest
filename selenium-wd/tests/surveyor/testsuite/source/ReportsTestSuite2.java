package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.EQReportsPageTest;
import surveyor.regression.source.EQReportsPageTest2;
import surveyor.regression.source.DriverViewPageTest_EQ;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	EQReportsPageTest.class
	,EQReportsPageTest2.class
})
public class ReportsTestSuite2 {

	@BeforeClass
    public static void setUpClass() {
        Log.info("Setup for ReportsTestSuite2 executing...");
    }

    @AfterClass
    public static void tearDownClass() {
    	Log.info("Teardown for ReportsTestSuite2 executing...");
    }
}

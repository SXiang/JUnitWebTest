package surveyor.performance.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	ReportJobPerformancePGELargeAreaTest1.class
	,ReportJobPerformancePGELargeAreaTest2.class
	,ReportJobPerformancePGELargeAreaTest3.class
	,ReportJobPerformancePGELargeAreaTest4.class
	,ReportJobPerformancePGELargeAreaTest5.class
})
public class ReportJobPerformancePGELargeAreaTestSuite {

	@BeforeClass
    public static void setUpClass() {
        Log.info("Setup for ReportJobPerformancePGELargeAreaTestSuite executing...");
    }

    @AfterClass
    public static void tearDownClass() {
    	Log.info("Teardown for ReportJobPerformancePGELargeAreaTestSuite executing...");
    }
}
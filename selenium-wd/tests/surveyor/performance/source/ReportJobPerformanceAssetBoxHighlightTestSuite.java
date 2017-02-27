package surveyor.performance.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	ReportJobPerformanceAssetBoxHighlightTest1.class
	,ReportJobPerformanceAssetBoxHighlightTest2.class
	,ReportJobPerformanceAssetBoxHighlightTest3.class
	,ReportJobPerformanceAssetBoxHighlightTest4.class
	,ReportJobPerformanceAssetBoxHighlightTest5.class
})
public class ReportJobPerformanceAssetBoxHighlightTestSuite {

	@BeforeClass
    public static void setUpClass() {
        Log.info("Setup for ReportJobPerformanceAssetBoxHighlightTestSuite executing...");
    }

    @AfterClass
    public static void tearDownClass() {
    	Log.info("Teardown for ReportJobPerformanceAssetBoxHighlightTestSuite executing...");
    }
}
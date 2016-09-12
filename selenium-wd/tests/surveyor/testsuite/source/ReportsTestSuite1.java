package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.EQReportsPageTest;
import surveyor.regression.source.ReferenceGasReportsPageTest;
import surveyor.regression.source.SystemHistoryReportsPageTest;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({ 
	EQReportsPageTest.class
	,ReferenceGasReportsPageTest.class
	,SystemHistoryReportsPageTest.class
})
public class ReportsTestSuite1 {
	
	@BeforeClass 
    public static void setUpClass() {      
        Log.info("Setup for ReportsTestSuite1 executing...");
    }

    @AfterClass 
    public static void tearDownClass() { 
    	Log.info("Teardown for ReportsTestSuite1 executing...");
    }
}

package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.SurveyViewPageTest3;
import surveyor.regression.source.SurveyViewPageTest4;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({ 
	SurveyViewPageTest3.class
	,SurveyViewPageTest4.class
})
public class RegressionTestSuite5 {
	
	@BeforeClass 
    public static void setUpClass() {      
        Log.info("Setup for RegressionTestSuite5 executing...");
    }

    @AfterClass 
    public static void tearDownClass() { 
    	Log.info("Teardown for RegressionTestSuite5 executing...");
    }
}

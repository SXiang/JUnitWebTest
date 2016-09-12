package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.ACLandVisibilityTest;
import surveyor.regression.source.HomePageTest;
import surveyor.regression.source.LoginPageTest;
import surveyor.regression.source.ManageAnalyzersPageTest;
import surveyor.regression.source.ManageCustomersPageTest;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({ 
	ACLandVisibilityTest.class
	,HomePageTest.class
	,LoginPageTest.class
	,ManageAnalyzersPageTest.class
	,ManageCustomersPageTest.class
})
public class RegressionTestSuite1 {
	
	@BeforeClass 
    public static void setUpClass() {      
        Log.info("Setup for RegressionTestSuite1 executing...");
    }

    @AfterClass 
    public static void tearDownClass() { 
    	Log.info("Teardown for RegressionTestSuite1 executing...");
    }
}

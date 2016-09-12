package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.ManageLocationsAdminPageTest;
import surveyor.regression.source.ManageLocationsPageTest;
import surveyor.regression.source.ManageLocationsPageTest_Ethane;
import surveyor.regression.source.ManageRefGasBottlesAdminPageTest;
import surveyor.regression.source.ManageRefGasBottlesPageTest;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({ 
	ManageLocationsAdminPageTest.class
	,ManageLocationsPageTest.class
	,ManageLocationsPageTest_Ethane.class
	,ManageRefGasBottlesAdminPageTest.class
	,ManageRefGasBottlesPageTest.class
})
public class RegressionTestSuite2 {
	
	@BeforeClass 
    public static void setUpClass() {      
        Log.info("Setup for RegressionTestSuite2 executing...");
    }

    @AfterClass 
    public static void tearDownClass() { 
    	Log.info("Teardown for RegressionTestSuite2 executing...");
    }
}

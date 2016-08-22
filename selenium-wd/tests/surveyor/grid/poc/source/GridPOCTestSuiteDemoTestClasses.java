package surveyor.grid.poc.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({ 
	GridPOCTestClass1.class
	,GridPOCTestClass3.class
	,GridPOCTestClass4.class
	,GridPOCTestClass5.class
	,GridPOCTestClass8.class})
public class GridPOCTestSuiteDemoTestClasses {
	
	@BeforeClass 
    public static void setUpClass() {      
        Log.info("Setup for GridPOCTestSuiteDemoTests executing...");
    }

    @AfterClass 
    public static void tearDownClass() { 
    	Log.info("Teardown for GridPOCTestSuiteDemoTests executing...");
    }
}

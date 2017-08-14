package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.ManageRefGasBottlesPageTests_Ethane;
import surveyor.regression.source.ManageSurveyorAdminPageTest;
import surveyor.regression.source.ManageSurveyorPageTest;
import surveyor.regression.source.SurveyViewPageTest3;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	ManageRefGasBottlesPageTests_Ethane.class
	,ManageSurveyorAdminPageTest.class
	,ManageSurveyorPageTest.class
	,SurveyViewPageTest3.class
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
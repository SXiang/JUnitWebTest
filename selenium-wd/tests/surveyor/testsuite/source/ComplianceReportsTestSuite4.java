package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.performance.source.ComplianceReportsLargeDatasetLoadTest;
import surveyor.regression.source.ComplianceReportsPageTest10;
import surveyor.regression.source.ComplianceReportsPageTest_Ethane;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	ComplianceReportsPageTest_Ethane.class
	,ComplianceReportsLargeDatasetLoadTest.class
	,ComplianceReportsPageTest10.class
})
public class ComplianceReportsTestSuite4 {

	@BeforeClass
    public static void setUpClass() {
        Log.info("Setup for ComplianceReportsTestSuite3 executing...");
    }

    @AfterClass
    public static void tearDownClass() {
    	Log.info("Teardown for ComplianceReportsTestSuite3 executing...");
    }
}

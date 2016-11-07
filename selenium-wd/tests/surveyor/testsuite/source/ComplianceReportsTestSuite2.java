package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.ComplianceReportsPageTest4;
import surveyor.regression.source.ComplianceReportsPageTest5;
import surveyor.regression.source.ComplianceReportsPageTest6;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	ComplianceReportsPageTest4.class
	,ComplianceReportsPageTest5.class
	,ComplianceReportsPageTest6.class
})
public class ComplianceReportsTestSuite2 {

	@BeforeClass
    public static void setUpClass() {
        Log.info("Setup for ComplianceReportsTestSuite2 executing...");
    }

    @AfterClass
    public static void tearDownClass() {
    	Log.info("Teardown for ComplianceReportsTestSuite2 executing...");
    }
}

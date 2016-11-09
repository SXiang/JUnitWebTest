package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.ComplianceReportsPageTest11;
import surveyor.regression.source.ComplianceReportsPageTest7;
import surveyor.regression.source.ComplianceReportsPageTest8;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	ComplianceReportsPageTest7.class
	,ComplianceReportsPageTest8.class
	,ComplianceReportsPageTest11.class
})
public class ComplianceReportsTestSuite3 {

	@BeforeClass
    public static void setUpClass() {
        Log.info("Setup for ComplianceReportsTestSuite3 executing...");
    }

    @AfterClass
    public static void tearDownClass() {
    	Log.info("Teardown for ComplianceReportsTestSuite3 executing...");
    }
}

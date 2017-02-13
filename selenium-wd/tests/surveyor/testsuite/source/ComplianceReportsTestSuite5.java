package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.AssessmentReportsPageTest;
import surveyor.regression.source.ComplianceReportsPageTest12;
import surveyor.regression.source.ComplianceReportsPageTest_AssetBox;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	ComplianceReportsPageTest12.class
	,ComplianceReportsPageTest_AssetBox.class
	,AssessmentReportsPageTest.class
})
public class ComplianceReportsTestSuite5 {

	@BeforeClass
    public static void setUpClass() {
        Log.info("Setup for ComplianceReportsTestSuite5 executing...");
    }

    @AfterClass
    public static void tearDownClass() {
    	Log.info("Teardown for ComplianceReportsTestSuite5 executing...");
    }
}

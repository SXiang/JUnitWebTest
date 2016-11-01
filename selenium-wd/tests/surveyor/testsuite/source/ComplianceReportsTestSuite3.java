package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.ComplianceReportsPageTest_CustomBuildRunner;
import surveyor.regression.source.ComplianceReportsPageTest_Ethane;
import surveyor.regression.source.ComplianceReportsWithNewSurveyPageTest;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	ComplianceReportsPageTest_CustomBuildRunner.class
	,ComplianceReportsPageTest_Ethane.class
	,ComplianceReportsWithNewSurveyPageTest.class
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

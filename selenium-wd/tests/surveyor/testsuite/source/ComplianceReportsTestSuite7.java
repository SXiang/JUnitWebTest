package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.mobile.source.ComplianceReportsInvestigationPageTest2;
import surveyor.regression.source.ComplianceReportsPageTest12;
import surveyor.regression.source.ComplianceReportsPageTest_Tahoe;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	/* !Don't Put more than one mobile test class in each suite */
	ComplianceReportsInvestigationPageTest2.class,
	ComplianceReportsPageTest12.class,
	ComplianceReportsPageTest_Tahoe.class
})
public class ComplianceReportsTestSuite7 {

	@BeforeClass
    public static void setUpClass() {
        Log.info("Setup for ComplianceReportsTestSuite6 executing...");
    }

    @AfterClass
    public static void tearDownClass() {
    	Log.info("Teardown for ComplianceReportsTestSuite6 executing...");
    }
}
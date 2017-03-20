package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.mobile.source.ComplianceReportsInvestigationPageTest;
import surveyor.regression.source.EQReportsPageTest;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({
	EQReportsPageTest.class,
	/* !Don't Put more than one mobile test class in each suite */
	ComplianceReportsInvestigationPageTest.class
})
public class ComplianceReportsTestSuite6 {

	@BeforeClass
    public static void setUpClass() {
        Log.info("Setup for ComplianceReportsTestSuite6 executing...");
    }

    @AfterClass
    public static void tearDownClass() {
    	Log.info("Teardown for ComplianceReportsTestSuite6 executing...");
    }
}
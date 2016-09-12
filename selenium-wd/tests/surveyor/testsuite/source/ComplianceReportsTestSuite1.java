package surveyor.testsuite.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;
import surveyor.regression.source.ComplianceReportsPageTest;
import surveyor.regression.source.ComplianceReportsPageTest2;
import surveyor.regression.source.ComplianceReportsPageTest3;
import surveyor.regression.source.ComplianceReportsPageTest4;
import surveyor.regression.source.ComplianceReportsPageTest5;
import surveyor.scommon.source.SurveyorParallelSuite;

@RunWith(SurveyorParallelSuite.class)
@SuiteClasses({ 
	ComplianceReportsPageTest.class
	,ComplianceReportsPageTest2.class
	,ComplianceReportsPageTest3.class
	,ComplianceReportsPageTest4.class
	,ComplianceReportsPageTest5.class
})
public class ComplianceReportsTestSuite1 {
	
	@BeforeClass 
    public static void setUpClass() {      
        Log.info("Setup for ComplianceReportsTestSuite1 executing...");
    }

    @AfterClass 
    public static void tearDownClass() { 
    	Log.info("Teardown for ComplianceReportsTestSuite1 executing...");
    }
}
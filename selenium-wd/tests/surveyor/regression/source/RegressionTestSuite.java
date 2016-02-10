package surveyor.regression.source;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import common.source.Log;

@RunWith(Suite.class)
@SuiteClasses({ 
	ACLandVisibilityTest.class,
	HomePageTest.class,
	LoginPageTest.class,
	ManageAnalyzersPageTest.class,
	ManageCustomersPageTest.class,
	ManageLocationsPageTest.class,
	ManageLocationsAdminPageTest.class,
	ManageRefGasBottlesAdminPageTest.class,
	ManageRefGasBottlesPageTest.class,
	ManageSurveyorPageTest.class,
	ManageSurveyorAdminPageTest.class,
	ManageSurveyorHistoriesPageTest.class,
	ManageUsersPageTest.class,
	ManageUsersAdminPageTest.class,
	MeasurementSessionsPageTest.class,
	UserFeedbackPageTest.class,
	UserFeedbackAdminPageTest.class,
	SystemHistoryReportsPageTest.class,	
	ReferenceGasReportsPageTest.class })
public class RegressionTestSuite {
	@BeforeClass 
    public static void setUpClass() {      
        Log.info("Setup for RegressionTestSuite executing...");
        // Add initialization that needs to happen once for all the test classes in the suite here.
    }

    @AfterClass 
    public static void tearDownClass() { 
    	Log.info("Teardown for RegressionTestSuite executing...");
    }
}

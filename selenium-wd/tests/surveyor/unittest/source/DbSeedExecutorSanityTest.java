package surveyor.unittest.source;

import org.junit.Test;
import org.junit.BeforeClass;

import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

import static surveyor.scommon.source.SurveyorConstants.*;

import java.io.IOException;
import surveyor.dataaccess.source.Customer;
import surveyor.dbseed.source.DbSeedExecutor;

public class DbSeedExecutorSanityTest extends DbSeedExecutorBaseTest {
	@BeforeClass
	public static void beforeTestClass()	{
		TestSetup testSetup = new TestSetup(true /* initialize */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initializeDBProperties();
		TestContext.INSTANCE.setTestSetup(testSetup);
	}

	@Test
	public void testSanityDBSeedPush() throws Exception {
		DbSeedExecutor.executeGenericDataSeed();
		verifyGenericSeedDataIsPresent();

		DbSeedExecutor.executeGisSeed();
		verifyGisSeedDataIsPresent(Customer.getCustomer(CUSTOMER_PICARRO).getId());
		verifyGisSeedDataIsPresent(Customer.getCustomer(CUSTOMER_SQACUS).getId());
		verifyGisSeedDataIsPresent(Customer.getCustomer(CUSTOMER_PGE).getId());

		final String[] surveyTags = {"assessment-1", "EthaneStnd", "stnd-pic"};
		DbSeedExecutor.executeSurveyDataSeed(surveyTags);
		verifySurveySeedDataIsPresent(surveyTags);
	}
}
package surveyor.unittest.source;

import org.junit.Test;
import org.junit.Assert;

import surveyor.dataaccess.source.ConnectionFactory;
import surveyor.dbseed.source.DbSeedExecutor;
import surveyor.dbseed.source.DbStateVerifier;
import surveyor.scommon.source.SurveyorBaseTest;

public class DbSeedExecutorTest extends SurveyorBaseTest {
	
	@Test
	public void executeGenericSeedTest() throws Exception {
		DbSeedExecutor.executeGenericDataSeed();
		Assert.assertTrue(new DbStateVerifier(ConnectionFactory.createConnection()).isGenericSeedPresent());
	}
}

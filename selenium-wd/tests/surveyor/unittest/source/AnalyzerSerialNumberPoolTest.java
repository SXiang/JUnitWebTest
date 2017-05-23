package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.source.AnalyzerSerialNumberPool;

public class AnalyzerSerialNumberPoolTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testFetchAnalyzerFromPool() {
		List<String> analyzers = new ArrayList<String>();
		int numAnalyzersInPool = Integer.valueOf(TestContext.INSTANCE.getTestSetup().getNumAnalyzersInPool());
		int maxIterations = 500000;
		int i = 0;
		while (i < maxIterations) {
			String analyzer = AnalyzerSerialNumberPool.INSTANCE.fetchNext();
			if (!analyzers.contains(analyzer)) {
				Log.info(String.format("Added analyzer -> '%s'", analyzer));
				analyzers.add(analyzer);
			}

			i++;
		}

		assertTrue("Not able to fetch analyzers from pool correctly.", analyzers.size()>0 && analyzers.size()<=numAnalyzersInPool);
		Log.info(String.format("Fetched analyzers from pool - %d times successfully. Fetched %d analyzers.", i, analyzers.size()));
	}
}
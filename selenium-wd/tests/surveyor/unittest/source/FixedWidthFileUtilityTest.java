package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.FixedWidthFileUtility;
import common.source.Log;
import common.source.LogHelper;
import common.source.TestContext;
import common.source.TestSetup;

public class FixedWidthFileUtilityTest {
	private static String rootPath;

	@BeforeClass
	public static void beforeTestClass()	{
		TestSetup testSetup = new TestSetup(true /* initialize */);
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
	public void testParseFileLines() throws IOException {
		Integer colWidth = 26;
		String filePath = rootPath + "\\android-app\\data\\test-expected-data\\unit-tests\\bpacksim-tests\\bpackanalyzer-data.dat";
		List<Map<String, String>> fileLines = FixedWidthFileUtility.parseFileLines(filePath , colWidth);
		Log.info(String.format("Total lines in the file = %d", fileLines.size()));
		fileLines.stream()
			.forEach(el -> {
				Log.info(LogHelper.mapToString(el));
				assertTrue(el.keySet().size()==19);
				assertTrue(el.values().size()==19);
				el.entrySet().stream()
					.forEach(es -> {
						assertTrue(es.getKey().length()>0);
						assertTrue(es.getValue().length()>0);
					});
			});
	}
}
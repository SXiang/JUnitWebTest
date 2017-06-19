package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.FileUtility;
import common.source.HostSimDefinitionGenerator;
import common.source.HostSimDefinitionGenerator.iGPSMode;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class HostSimDefinitionGeneratorTest {

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
	public void testGenerateDefaultMethDefinitionForMultiplePeaks() throws IOException {
		final Integer EXPECTED_NUM_LINES = 21;
		String[] ch4Values = {"5.5", "6.5", "7.5", "8.5", "9.5"};
		String defnFile = new HostSimDefinitionGenerator().generateDefaultMethDefinitionForMultiplePeaks(ch4Values);

		int numLines = FileUtility.readFileLinesToList(defnFile).size();

		Log.info(String.format("Defn file created at -> [%s]", defnFile));
		Log.info(String.format("Defn file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(defnFile));
		assertTrue(numLines==EXPECTED_NUM_LINES);
		Log.info(FileUtility.readFileContents(defnFile));
		FileUtility.deleteFile(Paths.get(defnFile));
	}

	@Test
	public void testGenerateDefaultEthDefinitionForMultiplePeaks_withCh4Values() throws IOException {
		final Integer EXPECTED_NUM_LINES = 21;
		String[] ch4Values = {"5.5", "6.5", "7.5", "8.5", "9.5"};
		String defnFile = new HostSimDefinitionGenerator().generateDefaultEthDefinitionForMultiplePeaks(ch4Values);

		int numLines = FileUtility.readFileLinesToList(defnFile).size();

		Log.info(String.format("Defn file created at -> [%s]", defnFile));
		Log.info(String.format("Defn file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(defnFile));
		assertTrue(numLines==EXPECTED_NUM_LINES);
		Log.info(FileUtility.readFileContents(defnFile));
		FileUtility.deleteFile(Paths.get(defnFile));
	}

	@Test
	public void testGenerateDefaultEthDefinitionForMultiplePeaks_withCh4AndC2H6Values() throws IOException {
		final Integer EXPECTED_NUM_LINES = 21;
		String[] ch4Values = {"5.5", "6.5", "7.5", "8.5", "9.5"};
		String[] c2h6Values = {"3.5", "4.0", "3.5", "4.0", "3.5"};
		String defnFile = new HostSimDefinitionGenerator().generateDefaultEthDefinitionForMultiplePeaks(ch4Values, c2h6Values);

		int numLines = FileUtility.readFileLinesToList(defnFile).size();

		Log.info(String.format("Defn file created at -> [%s]", defnFile));
		Log.info(String.format("Defn file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(defnFile));
		assertTrue(numLines==EXPECTED_NUM_LINES);
		Log.info(FileUtility.readFileContents(defnFile));
		FileUtility.deleteFile(Paths.get(defnFile));
	}

	@Test
	public void testGenerateMethDefinitionForiGPSGoingFromBlueToYellowToRed() throws IOException {
		Log.method("testGenerateMethDefinitionForiGPSGoingFromBlueToYellowToRed");
		final Integer EXPECTED_NUM_LINES = 17;
		final String EXPECTED_DEFN_CONTENT = "iCH4=True,Ethane=False";
		String defnFilePath = new HostSimDefinitionGenerator().generateMethDefinitionForiGPSGoingFromBlueToYellowToRed();
		executeGenerateiGPSDefinitionTest(EXPECTED_NUM_LINES, EXPECTED_DEFN_CONTENT, defnFilePath);
	}

	@Test
	public void testGenerateEthDefinitionForiGPSGoingFromBlueToYellowToRed() throws IOException {
		Log.method("testGenerateEthDefinitionForiGPSGoingFromBlueToYellowToRed");
		final Integer EXPECTED_NUM_LINES = 17;
		final String EXPECTED_DEFN_CONTENT = "iCH4=False,Ethane=True";
		String defnFilePath = new HostSimDefinitionGenerator().generateEthDefinitionForiGPSGoingFromBlueToYellowToRed();
		executeGenerateiGPSDefinitionTest(EXPECTED_NUM_LINES, EXPECTED_DEFN_CONTENT, defnFilePath);
	}

	@Test
	public void generateEthDefinitionForiGPSGoingFromBlueToYellowToRedWithPeakTest() throws IOException {
		Log.method("generateEthDefinitionForiGPSGoingFromBlueToYellowToRedWithPeakTest");
		final Integer EXPECTED_NUM_LINES = 41;
		final String EXPECTED_DEFN_CONTENT = "iCH4=False,Ethane=True";
		String[] ch4Values = {"5.5", "6.5", "7.5", "8.5", "9.5"};
		String[] c2h6Values = {"3.5", "3.2", "3.0", "3.5", "2.5"};
		String defnFilePath = new HostSimDefinitionGenerator().generateEthDefinitionForiGPSGoingFromBlueToYellowToRed(ch4Values, c2h6Values);
		executeGenerateiGPSDefinitionTest(EXPECTED_NUM_LINES, EXPECTED_DEFN_CONTENT, defnFilePath);
	}

	@Test
	public void generateMethDefinitionForiGPSGoingFromBlueToYellowToRedWithPeakTest() throws IOException {
		Log.method("generateMethDefinitionForiGPSGoingFromBlueToYellowToRedWithPeakTest");
		final Integer EXPECTED_NUM_LINES = 41;
		final String EXPECTED_DEFN_CONTENT = "iCH4=True,Ethane=False";
		String[] ch4Values = {"5.5", "6.5", "7.5", "8.5", "9.5"};
		String[] c2h6Values = {"3.5", "3.2", "3.0", "3.5", "2.5"};
		String defnFilePath = new HostSimDefinitionGenerator().generateMethDefinitionForiGPSGoingFromBlueToYellowToRed(ch4Values, c2h6Values);
		executeGenerateiGPSDefinitionTest(EXPECTED_NUM_LINES, EXPECTED_DEFN_CONTENT, defnFilePath);
	}

	@Test
	public void generateEthDefinitionForiGPSModeTest() throws IOException {
		Log.method("generateEthDefinitionForiGPSModeTest");
		final Integer EXPECTED_NUM_LINES = 13;
		final String EXPECTED_DEFN_CONTENT = "iCH4=False,Ethane=True";
		String defnFilePath = new HostSimDefinitionGenerator().generateEthDefinitionForiGPSMode(iGPSMode.None);
		executeGenerateiGPSDefinitionTest(EXPECTED_NUM_LINES, EXPECTED_DEFN_CONTENT, defnFilePath);
	}

	@Test
	public void generateMethDefinitionForiGPSModeTest() throws IOException {
		Log.method("generateMethDefinitionForiGPSModeTest");
		final Integer EXPECTED_NUM_LINES = 13;
		final String EXPECTED_DEFN_CONTENT = "iCH4=True,Ethane=False";
		String defnFilePath = new HostSimDefinitionGenerator().generateMethDefinitionForiGPSMode(iGPSMode.None);
		executeGenerateiGPSDefinitionTest(EXPECTED_NUM_LINES, EXPECTED_DEFN_CONTENT, defnFilePath);
	}

	@Test
	public void generateEthDefinitionForiGPSModeWithPeakTest() throws IOException {
		Log.method("generateEthDefinitionForiGPSModeWithPeakTest");
		final Integer EXPECTED_NUM_LINES = 21;
		final String EXPECTED_DEFN_CONTENT = "iCH4=False,Ethane=True";
		String[] ch4Values = {"5.5", "6.5", "7.5", "8.5", "9.5"};
		String[] c2h6Values = {"3.5", "3.2", "3.0", "3.5", "2.5"};
		String defnFilePath = new HostSimDefinitionGenerator().generateEthDefinitionForiGPSMode(iGPSMode.None, ch4Values, c2h6Values);
		executeGenerateiGPSDefinitionTest(EXPECTED_NUM_LINES, EXPECTED_DEFN_CONTENT, defnFilePath);
	}

	@Test
	public void generateMethDefinitionForiGPSModeWithPeakTest() throws IOException {
		Log.method("generateMethDefinitionForiGPSModeWithPeakTest");
		final Integer EXPECTED_NUM_LINES = 21;
		final String EXPECTED_DEFN_CONTENT = "iCH4=True,Ethane=False";
		String[] ch4Values = {"5.5", "6.5", "7.5", "8.5", "9.5"};
		String[] c2h6Values = {"3.5", "3.2", "3.0", "3.5", "2.5"};
		String defnFilePath = new HostSimDefinitionGenerator().generateMethDefinitionForiGPSMode(iGPSMode.None, ch4Values, c2h6Values);
		executeGenerateiGPSDefinitionTest(EXPECTED_NUM_LINES, EXPECTED_DEFN_CONTENT, defnFilePath);
	}

	private void executeGenerateiGPSDefinitionTest(final Integer expectedFileLines, final String expectedDefnContent, final String defnFilePath) throws IOException {
		int numLines = FileUtility.readFileLinesToList(defnFilePath).size();

		Log.info(String.format("Defn file created at -> [%s]", defnFilePath));
		Log.info(String.format("Defn file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(defnFilePath));
		assertTrue(numLines==expectedFileLines);
		String fileContents = FileUtility.readFileContents(defnFilePath);
		Log.info(fileContents);
		assertTrue(fileContents.contains(expectedDefnContent));

		FileUtility.deleteFile(Paths.get(defnFilePath));
	}
}
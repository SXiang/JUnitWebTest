package unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.BackPackSimInstructionsGenerator;
import common.source.FileUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class BackPackSimInstructionsGeneratorTest {

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
	public void testGenerateInstructionFileForDrop() throws IOException {
		final Integer EXPECTED_NUM_LINES = 3;
		String filename = "drop-inst.ini";
		List<String> dropColumns = new ArrayList<>();
		dropColumns.add("TIME");
		dropColumns.add("EthaneRatioSdev");
		String instFile = BackPackSimInstructionsGenerator.generateInstructionFileForDrop(filename, dropColumns);

		List<String> fileLines = FileUtility.readFileLinesToList(instFile);
		int numLines = fileLines.size();

		Log.info(String.format("Instructions file created at -> [%s]", instFile));
		Log.info(String.format("Instructions file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(instFile));
		assertTrue(numLines==EXPECTED_NUM_LINES);
		Log.info(FileUtility.readFileContents(instFile));
		assertTrue(fileLines.get(0).equals("[drop]"));
		assertTrue(fileLines.get(1).equals("TIME = delete"));
		assertTrue(fileLines.get(2).equals("EthaneRatioSdev = delete"));
		FileUtility.deleteFile(Paths.get(instFile));
	}

	@Test
	public void testGenerateInstructionFileForReplace() throws IOException {
		final Integer EXPECTED_NUM_LINES = 3;
		String filename = "replace-inst.ini";
		Map<String, String> replaceColsMap = new HashMap<>();
		replaceColsMap.put("Disposition", "3.0000000000E+00");
		replaceColsMap.put("CH4", "3.23478234783");
		String instFile = BackPackSimInstructionsGenerator.generateInstructionFileForReplace(filename, replaceColsMap);

		List<String> fileLines = FileUtility.readFileLinesToList(instFile);
		int numLines = fileLines.size();

		Log.info(String.format("Instructions file created at -> [%s]", instFile));
		Log.info(String.format("Instructions file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(instFile));
		assertTrue(numLines==EXPECTED_NUM_LINES);
		Log.info(FileUtility.readFileContents(instFile));
		assertTrue(fileLines.get(0).equals("[replace]"));
		assertTrue(fileLines.get(1).equals("Disposition = 3.0000000000E+00"));
		assertTrue(fileLines.get(2).equals("CH4 = 3.23478234783"));
		FileUtility.deleteFile(Paths.get(instFile));
	}

	@Test
	public void testGenerateInstructionFileForDropReplace() throws IOException {
		final Integer EXPECTED_NUM_LINES = 6;
		String filename = "dropreplace-inst.ini";
		Map<String, String> replaceColsMap = new HashMap<>();
		replaceColsMap.put("Disposition", "3.0000000000E+00");
		replaceColsMap.put("CH4", "3.23478234783");
		List<String> dropColumns = new ArrayList<>();
		dropColumns.add("TIME");
		dropColumns.add("EthaneRatioSdev");
		String instFile = BackPackSimInstructionsGenerator.generateInstructionFileForDropReplace(filename, replaceColsMap, dropColumns);

		List<String> fileLines = FileUtility.readFileLinesToList(instFile);
		int numLines = fileLines.size();

		Log.info(String.format("Instructions file created at -> [%s]", instFile));
		Log.info(String.format("Instructions file : number of lines = %d", numLines));

		assertTrue(FileUtility.fileExists(instFile));
		assertTrue(numLines==EXPECTED_NUM_LINES);
		Log.info(FileUtility.readFileContents(instFile));
		assertTrue(fileLines.get(0).equals("[replace]"));
		assertTrue(fileLines.get(1).equals("Disposition = 3.0000000000E+00"));
		assertTrue(fileLines.get(2).equals("CH4 = 3.23478234783"));
		assertTrue(fileLines.get(3).equals("[drop]"));
		assertTrue(fileLines.get(4).equals("TIME = delete"));
		assertTrue(fileLines.get(5).equals("EthaneRatioSdev = delete"));
		FileUtility.deleteFile(Paths.get(instFile));
	}
}

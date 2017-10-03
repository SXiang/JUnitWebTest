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

import common.source.BackPackSimDataTransformer;
import common.source.BackPackSimInstructionsGenerator;
import common.source.CollectionsUtil;
import common.source.FileUtility;
import common.source.FixedWidthFileUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class BackPackSimDataTransformerTest {

	private static final String DROP_COLUMN_01 = "TIME";
	private static final String DROP_COLUMN_02 = "EthaneRatioSdev";
	private static final String REPLACE_COLUMN_01 = "Disposition";
	private static final String REPLACE_COLUMN_02 = "CH4";
	private static final String REPLACE_COLUMN_01_VALUE = "3.0000000000E+00";
	private static final String REPLACE_COLUMN_02_VALUE = "3.23478234783";
	private static final Integer FILE_COLUMN_WIDTH = 26;

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

	private String getUniqueInputDataFile(String originalInputDataFile) throws IOException {
		TestContext.INSTANCE.getTestSetup();
		String uniqueFile = originalInputDataFile.replace(".dat", String.format("%s.dat", TestSetup.getUUIDString()));
		FileUtility.copyFile(originalInputDataFile, uniqueFile);
		return uniqueFile;
	}

	@Test
	public void testTransformDataFileWithRevert_ReplaceTrue() throws Exception {
		Log.method("testTransformDataFileWithRevert_ReplaceTrue");

		String inputDataFile = getUniqueInputDataFile(Paths.get(rootPath, "android-app\\data\\test-expected-data\\unit-tests\\bpacksim-tests\\bpackanalyzer-data.dat").toString());
		String instructionsFile = generateSampleInstructionFile();
		Boolean replaceInputFile = true;
		BackPackSimDataTransformer dataTransformer = BackPackSimDataTransformer.newDataTransformer(inputDataFile);
		dataTransformer.transformDataFileWithRevert(instructionsFile, replaceInputFile, () -> {
			Log.info("Test method log");

			assertTrue(String.format("Input File-'%s' should be present.", inputDataFile), FileUtility.fileExists(inputDataFile));
			assertTrue(String.format("Input Bak File-'%s' should be present.", dataTransformer.getInputDataBakFile()), FileUtility.fileExists(dataTransformer.getInputDataBakFile()));
			assertTrue(String.format("Output File-'%s' should be present.", dataTransformer.getOutputDataFile()), FileUtility.fileExists(dataTransformer.getOutputDataFile()));

			List<Map<String, String>> transformedFileLines = FixedWidthFileUtility.parseFileLines(inputDataFile, FILE_COLUMN_WIDTH);

			// [In transformed file]: verify column values replaced correctly
			verifyColumnValuesEquals(transformedFileLines, REPLACE_COLUMN_01, REPLACE_COLUMN_01_VALUE);
			verifyColumnValuesEquals(transformedFileLines, REPLACE_COLUMN_02, REPLACE_COLUMN_02_VALUE);

			// [In transformed file]: verify dropped column does not show.
			assertTrue(String.format("Dropped Column-'%s' should not be present in transformed file.", DROP_COLUMN_01),
					!isColumnPresentInFile(transformedFileLines, DROP_COLUMN_01));
			assertTrue(String.format("Dropped Column-'%s' should not be present in transformed file.", DROP_COLUMN_02),
					!isColumnPresentInFile(transformedFileLines, DROP_COLUMN_02));

			List<Map<String, String>> inputFileLines = FixedWidthFileUtility.parseFileLines(inputDataFile, FILE_COLUMN_WIDTH);

			// verify input and transformed file should be the same (before revert call in finally).
			assertTrue("InputFile and TransformedFile should be the same.", CollectionsUtil.isEqualsListMap(inputFileLines, transformedFileLines));

		});

		List<Map<String, String>> inputFileLines = FixedWidthFileUtility.parseFileLines(inputDataFile, FILE_COLUMN_WIDTH);
		List<Map<String, String>> inputBakFileLines = FixedWidthFileUtility.parseFileLines(dataTransformer.getInputDataBakFile(), FILE_COLUMN_WIDTH);

		// verify inputfile was reverted correctly. after revert input and inputbak should be the the same.
		assertTrue("InputFile and InputBakFile are NOT the same.", CollectionsUtil.isEqualsListMap(inputFileLines, inputBakFileLines));

		dataTransformer.cleanUp();
	}

	@Test
	public void testTransformDataFileWithRevert_ReplaceFalse() throws Exception {
		Log.method("testTransformDataFileWithRevert_ReplaceFalse");

		String inputDataFile = getUniqueInputDataFile(Paths.get(rootPath, "android-app\\data\\test-expected-data\\unit-tests\\bpacksim-tests\\bpackanalyzer-data.dat").toString());
		String instructionsFile = generateSampleInstructionFile();
		Boolean replaceInputFile = false;
		BackPackSimDataTransformer dataTransformer = BackPackSimDataTransformer.newDataTransformer(inputDataFile).transformDataFileWithRevert(instructionsFile, replaceInputFile, () -> {
			Log.info("Test method log");
		});

		assertTrue(String.format("Input File-'%s' should be present.", inputDataFile), FileUtility.fileExists(inputDataFile));
		assertTrue(String.format("Output File-'%s' should be present.", dataTransformer.getOutputDataFile()), FileUtility.fileExists(dataTransformer.getOutputDataFile()));

		List<Map<String, String>> transformedFileLines = FixedWidthFileUtility.parseFileLines(dataTransformer.getOutputDataFile(), FILE_COLUMN_WIDTH);

		// [In transformed file]: verify column values replaced correctly
		verifyColumnValuesEquals(transformedFileLines, REPLACE_COLUMN_01, REPLACE_COLUMN_01_VALUE);
		verifyColumnValuesEquals(transformedFileLines, REPLACE_COLUMN_02, REPLACE_COLUMN_02_VALUE);

		// [In transformed file]: verify dropped column does not show.
		assertTrue(String.format("Dropped Column-'%s' should not be present in transformed file.", DROP_COLUMN_01),
				!isColumnPresentInFile(transformedFileLines, DROP_COLUMN_01));
		assertTrue(String.format("Dropped Column-'%s' should not be present in transformed file.", DROP_COLUMN_02),
				!isColumnPresentInFile(transformedFileLines, DROP_COLUMN_02));

		List<Map<String, String>> inputFileLines = FixedWidthFileUtility.parseFileLines(inputDataFile, FILE_COLUMN_WIDTH);

		// verify input and transformed file should not be the same.
		assertTrue("InputFile and TransformedFile should NOT be the same.", !CollectionsUtil.isEqualsListMap(inputFileLines, transformedFileLines));

		dataTransformer.cleanUp();
	}

	@Test
	public void testTransformDataFileWithRevert_Cleanup() throws Exception {
		Log.method("testTransformDataFileWithRevert_Cleanup");

		String inputDataFile = getUniqueInputDataFile(Paths.get(rootPath, "android-app\\data\\test-expected-data\\unit-tests\\bpacksim-tests\\bpackanalyzer-data.dat").toString());
		String instructionsFile = generateSampleInstructionFile();
		Boolean replaceInputFile = true;
		BackPackSimDataTransformer dataTransformer = BackPackSimDataTransformer.newDataTransformer(inputDataFile).transformDataFileWithRevert(instructionsFile, replaceInputFile, () -> {
			Log.info("Test method log");
		}).cleanUp();

		assertTrue(String.format("Input File-'%s' should be present.", inputDataFile), FileUtility.fileExists(inputDataFile));
		assertTrue(String.format("Input Bak File-'%s' should NOT be present.", dataTransformer.getInputDataBakFile()), !FileUtility.fileExists(dataTransformer.getInputDataBakFile()));
		assertTrue(String.format("Output File-'%s' should NOT be present.", dataTransformer.getOutputDataFile()), !FileUtility.fileExists(dataTransformer.getOutputDataFile()));
	}

	private String generateSampleInstructionFile() throws IOException {
		String filename = "dropreplace-inst";
		Map<String, String> replaceColsMap = new HashMap<>();
		replaceColsMap.put(REPLACE_COLUMN_01, REPLACE_COLUMN_01_VALUE);
		replaceColsMap.put(REPLACE_COLUMN_02, REPLACE_COLUMN_02_VALUE);
		List<String> dropColumns = new ArrayList<>();
		dropColumns.add(DROP_COLUMN_01);
		dropColumns.add(DROP_COLUMN_02);
		return BackPackSimInstructionsGenerator.generateInstructionFileForDropReplace(filename, replaceColsMap, dropColumns);
	}

	private boolean isColumnPresentInFile(List<Map<String, String>> parsedFileLines, String columnName) {
		Map<String, String> firstRowValues = parsedFileLines.get(0);
		return firstRowValues.containsKey(columnName);
	}

	private void verifyColumnValuesEquals(List<Map<String, String>> parsedFileLines, String columnName, String columnValue) {
		int idx = 0;
		for (Map<String, String> map : parsedFileLines) {
			if (idx > 0) {
				String actualColValue = map.get(columnName);
				assertTrue(String.format("Column values did NOT match. Expected=[%s], Actual=[%s]", columnValue, actualColValue),
						actualColValue.equals(columnValue));
			}

			idx++;
		}
	}
}
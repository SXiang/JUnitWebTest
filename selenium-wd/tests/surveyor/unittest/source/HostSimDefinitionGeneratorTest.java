package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import common.source.FileUtility;
import common.source.HostSimDefinitionGenerator;
import common.source.Log;

public class HostSimDefinitionGeneratorTest {

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
}

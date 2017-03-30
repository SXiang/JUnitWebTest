package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;

import org.eclipse.jetty.util.log.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import common.source.FileUtility;
import surveyor.dbseed.source.DatFileBuilder;
import surveyor.scommon.source.BaseTest;

public class DatFileBuilderTest {

	private static final String PicarroCustomerId = "B1252204-04FB-4A67-82D4-3F4666FD855C";
	private static final String SqaCusCustomerId = "00000000-0000-0000-0000-000000000002";
	private static final String AssetDatFile = "C:\\Repositories\\surveyor-qa\\selenium-wd\\data\\sql\\Asset.BA.dat";
	private static final String BoundaryDatFile = "C:\\Repositories\\surveyor-qa\\selenium-wd\\data\\sql\\Boundary.BA.dat";

	private DatFileBuilder datFileBuilder = null;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		BaseTest.initializeTestObjects();
	}

	@Before
	public void setUp() throws Exception {
		datFileBuilder = new DatFileBuilder();
	}

	@After
	public void tearDown() throws Exception {
		datFileBuilder.close();
	}

	@Test
	public void testAssetDatFileBuild() throws IOException {
		Log.info("Executing testAssetDatFileBuild() ...");
		String datFilePath = datFileBuilder.build(AssetDatFile, PicarroCustomerId, SqaCusCustomerId);
		Integer originalLineCount = FileUtility.getLineCountInFile(Paths.get(AssetDatFile), FileUtility.ENCODING_UTF16LE);
		Integer expectedLineCount = FileUtility.getLineCountInFile(Paths.get(datFilePath), FileUtility.ENCODING_UTF16LE);
		Log.info(String.format("Original Line Count=%d; Expected Line Count=%d", originalLineCount, expectedLineCount));
		assertTrue(expectedLineCount.equals(originalLineCount));
		String firstLineText = FileUtility.readFileLine(datFilePath, 1, FileUtility.ENCODING_UTF16LE);
		Log.info(String.format("First line from output data file is - %s", firstLineText));
		assertTrue(firstLineText.contains(SqaCusCustomerId));
	}

	@Test
	public void testBoundaryDatFileBuild() throws IOException {
		Log.info("Executing testBoundaryDatFileBuild() ...");
		String datFilePath = datFileBuilder.build(BoundaryDatFile, PicarroCustomerId, SqaCusCustomerId);
		Log.info(String.format("Output data file created at - %s", datFilePath));
		Integer originalLineCount = FileUtility.getLineCountInFile(Paths.get(BoundaryDatFile), FileUtility.ENCODING_UTF16LE);
		Integer expectedLineCount = FileUtility.getLineCountInFile(Paths.get(datFilePath), FileUtility.ENCODING_UTF16LE);
		Log.info(String.format("Original Line Count=%d; Expected Line Count=%d", originalLineCount, expectedLineCount));
		assertTrue(expectedLineCount.equals(originalLineCount));
		String firstLineText = FileUtility.readFileLine(datFilePath, 1, FileUtility.ENCODING_UTF16LE);
		Log.info(String.format("First line from output data file is - %s", firstLineText));
		assertTrue(firstLineText.contains(SqaCusCustomerId));
	}
}
package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.FileUtility;
import common.source.Log;
import common.source.S3Interface;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.S3Interface.S3File;
import surveyor.scommon.source.BaseTest;

public class S3InterfaceTest {
	private static String baseUrl;
	private static S3Interface s3Interface = null;
	private static List<String> uploadedFileKeys = new ArrayList<String>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseTest.initializeTestObjects();
		baseUrl = TestContext.INSTANCE.getBaseUrl();
		s3Interface = S3Interface.newInterface();
	}

	@AfterClass
	public static void tearDownAfterClass() throws IOException {
		if (uploadedFileKeys.size() > 0) {
			deleteUploadedFiles();
		}

		TestSetup.stopChromeProcesses();
	}

	@Test
	public void test_01UploadFiles() throws IOException {
		uploadFilesFromTestDir();
	}

	@Test
	public void test_02DownloadFiles() throws IOException {
		downloadUploadedFiles();
	}

	@Test
	public void test_03DeleteFiles() throws IOException {
		deleteUploadedFiles();
	}

	private static void deleteUploadedFiles() throws IOException {
		if (uploadedFileKeys.size() == 0) {
			uploadFilesFromTestDir();
		}

		for (String fileKey : uploadedFileKeys) {
			S3File s3File = S3File.fromFileAndUri(Paths.get(fileKey), URI.create(baseUrl));
			s3Interface.deleteFile(s3File);
		}

		uploadedFileKeys.clear();
	}

	private static void downloadUploadedFiles() throws IOException {
		if (uploadedFileKeys.size() == 0) {
			uploadFilesFromTestDir();
		}

		Path outDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\s3interface-tests\\downloads");
		FileUtility.createDirectoryIfNotExists(outDirectory.toString());
		for (String fileKey : uploadedFileKeys) {
			S3File s3File = S3File.fromFileAndUri(Paths.get(fileKey), URI.create(baseUrl));
			s3Interface.downloadFile(s3File, outDirectory.toString());
		}

		List<String> filesInDirectory = FileUtility.getFilesInDirectory(outDirectory);
		Log.info(String.format("Found %d files in uploadedFileKeys list", uploadedFileKeys.size()));
		Log.info(String.format("Found %d files in download folder list", filesInDirectory.size()));
		assertTrue(filesInDirectory.size() == uploadedFileKeys.size());

		FileUtility.deleteDirectoryAndFiles(outDirectory);
	}

	private static void uploadFilesFromTestDir() throws IOException {
		Path testDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\s3interface-tests");
		List<String> imgFilesInDir = FileUtility.getFilesInDirectory(testDirectory);
		for (String filePath : imgFilesInDir) {
			if (filePath.endsWith("jpg")) {
				S3File s3File = S3File.fromFileAndUri(Paths.get(filePath), URI.create(baseUrl));
				uploadedFileKeys.add(s3Interface.uploadFile(s3File));
			}
		}
	}
}
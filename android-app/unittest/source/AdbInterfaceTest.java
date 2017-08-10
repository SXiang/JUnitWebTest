package unittest.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.AdbInterface;
import common.source.AndroidAutomationTools;
import common.source.BackPackAnalyzer;
import common.source.FileUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.source.BaseTest;

public class AdbInterfaceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseTest.initializeTestObjects();
		AdbInterface.init(TestContext.INSTANCE.getTestSetup().getAdbLocation());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AdbInterface.stop();
	}

	@Before
	public void setUp() throws Exception {
		BaseTest.initializeTestObjects();
		AndroidAutomationTools.start();
		BackPackAnalyzer.startSimulator();
	}

	@After
	public void tearDown() throws Exception {
		BaseTest.logoutQuitDriver();
		BackPackAnalyzer.stopSimulator();
		AndroidAutomationTools.stop();
	}

	@Test
	public void executeCommandTest() throws Exception {
		Log.info("Excecuting test -> executeCommandTest() ...");
		File apkFile = getApkFile();
		AndroidAutomationTools.installLaunchAPK(apkFile.getAbsolutePath(), "MainActivity");
		String commandText = "input text \"http://10.100.3.68:3000\"";
		AdbInterface.executeShellCmd(commandText);
	}

	private File getApkFile() throws IOException {
		Path apkFolderPath = Paths.get(TestSetup.getRootPath(), "apk");
		List<String> apkFiles = FileUtility.getFilesInDirectory(apkFolderPath, "*.apk");
		if (apkFiles == null || apkFiles.size() == 0) {
			throw new IOException(String.format("No APK files found in API directory - '%s'", apkFolderPath));
		}

		String apkFilePath = apkFiles.get(0);
		File apkFile = new File(apkFilePath);
		return apkFile;
	}

}
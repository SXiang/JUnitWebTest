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
		AndroidAutomationTools.start();
		BackPackAnalyzer.startSimulator();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BackPackAnalyzer.stopSimulator();
		AndroidAutomationTools.stop();
		AdbInterface.stop();
		BaseTest.logoutQuitDriver();
	}

	@Before
	public void setUp() throws Exception {
		BaseTest.initializeTestObjects();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void executeCommandTest() throws Exception {
		Log.info("Excecuting test -> executeCommandTest() ...");
		File apkFile = getApkFile();
		AndroidAutomationTools.installLaunchAPK(apkFile.getAbsolutePath(), "MainActivity");
		String commandText = "input text \"http://10.100.3.68:3000\"";
		AdbInterface.executeShellCmd(commandText);
	}

	@Test
	public void resetTest() throws Exception {
		Log.info("Excecuting test -> resetTest() ...");
		AdbInterface.reset();
		Log.info("DONE with reset. Verifying adb commands continue to work...");
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
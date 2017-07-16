package common.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Downloader {

	private static final String DOWNLOAD_FILE_CMD = "DownloadFile.cmd";

	public static void downloadFile(String downloadFileRelativeUrl, String outputFileFullPath) {		
		try {
			Files.deleteIfExists(Paths.get(outputFileFullPath));
			String workingFolder = TestSetup.getRootPath();
			String downloadFileCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
			String downloadFileCmdFullPath = downloadFileCmdFolder + File.separator + DOWNLOAD_FILE_CMD;
			String command = "cd \"" + downloadFileCmdFolder + "\" && " + downloadFileCmdFullPath + 
					String.format(" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"", 
							workingFolder, TestContext.INSTANCE.getBaseUrl(), downloadFileRelativeUrl, outputFileFullPath,
							TestContext.INSTANCE.getTestSetup().getLoginUser(), 
							TestContext.INSTANCE.getTestSetup().getLoginPwd());
			Log.info("Downloading File. Command -> " + command);
			ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}
}

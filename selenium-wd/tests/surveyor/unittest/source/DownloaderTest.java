package surveyor.unittest.source;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.Downloader;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class DownloaderTest {

	private static TestSetup testSetup = null;

	@BeforeClass
	public static void beforeClass() {
		testSetup = new TestSetup();
		TestContext.INSTANCE.setTestSetup(testSetup);
	}

	@Test
	public void downloadFileTest() {
		boolean hasError = false;

		String downloadFileRelativeUrl = "Reports/ViewReportPdf?reportId=1FAEFD6E-6F64-94C9-D2FD-39D91D198D2D&ReportType=Compliance";
		String outputFileFullPath = "C:\\Users\\spulikkal\\Downloads\\CR-1FAEFD.pdf";
		try {
			Downloader.downloadFile(downloadFileRelativeUrl, outputFileFullPath);
		} catch (Exception e) {
			hasError = true;
			Log.error(ExceptionUtility.getStackTraceString(e));
		}

		Assert.assertTrue(!hasError);
	}

}

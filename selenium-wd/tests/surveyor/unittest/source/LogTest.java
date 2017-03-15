package surveyor.unittest.source;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;

import common.source.FileUtility;
import common.source.Log;
import common.source.TestSetup;

public class LogTest {

	public String logFilePath;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		logFilePath = TestSetup.getRootPath() + File.separator + "logs" + File.separator + "qauto.log";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_LogInfo_WithMessage() {
		Log.info("Executing test_LogInfo_WithMessage() ...");
		String message = "Log Info";
		Log.info(message);
		assertLogLastEntryContains(new String[] {"INFO", message});
	}

	@Test
	public void test_LogWarn_WithMessage() {
		Log.info("Executing test_LogWarn_WithMessage() ...");
		String message = "Log Warn";
		Log.warn(message);
		assertLogLastEntryContains(new String[] {"WARN", message});
	}

	@Test
	public void test_LogDebug_WithMessage() {
		Log.info("Executing test_LogDebug_WithMessage() ...");
		String message = "Log Debug";
		Log.debug(message);
		assertLogLastEntryContains(new String[] {"DEBUG", message});
	}

	@Test
	public void test_LogError_WithMessage() {
		Log.info("Executing test_LogError_WithMessage() ...");
		String message = "Log Error";
		Log.error(message);
		assertLogLastEntryContains(new String[] {"ERROR", message});
	}

	@Test
	public void test_LogInfo_NoMessage() {
		Log.info("Executing test_LogInfo_NoMessage() ...");
		String message = "";
		Log.info(message);
		assertLogLastEntryContains(new String[] {"INFO", message});
	}

	@Test
	public void test_LogWarn_NoMessage() {
		Log.info("Executing test_LogWarn_NoMessage() ...");
		String message = "";
		Log.warn(message);
		assertLogLastEntryContains(new String[] {"WARN", message});
	}

	@Test
	public void test_LogDebug_NoMessage() {
		Log.info("Executing test_LogDebug_NoMessage() ...");
		String message = "";
		Log.debug(message);
		assertLogLastEntryContains(new String[] {"DEBUG", message});
	}

	@Test
	public void test_LogError_NoMessage() {
		Log.info("Executing test_LogError_NoMessage() ...");
		String message = "";
		Log.error(message);
		assertLogLastEntryContains(new String[] {"ERROR", message});
	}

	private void assertLogLastEntryContains(String[] messages) {
		try {
			List<String> contents = FileUtility.readFileLinesToList(logFilePath);
			String lastLine = contents.get(contents.size()-1);
			for (String msg : messages) {
				if (msg != "") {
					Assert.assertTrue(lastLine.contains(msg));
				}
			}
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}
}
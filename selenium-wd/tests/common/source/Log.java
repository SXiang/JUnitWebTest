package common.source;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import common.source.BasePage.ElementType;

public class Log {
	private static Logger log = null; 
	private static String logFilePath;

	static {
		try {
			System.setProperty("log4j.configurationFile", TestSetup.getExecutionPath(TestSetup.getRootPath()) + "log4j2" + File.separator + "log4j2.xml");
			log = LogManager.getLogger(Log.class.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void info(String message) {
		log.info(message);
	}

	public static void warn(String message) {
		log.warn(message);
	}

	public static void debug(String message) {
		log.debug(message);
	}

	public static void error(String message) {
		log.error(message);
	}

	public static void info(String message, LogCategory logCategory) {
		if (LogSwitches.INSTANCE.isEnabled(logCategory)) {
			log.info(message);
		}
	}

	public static void warn(String message, LogCategory logCategory) {
		if (LogSwitches.INSTANCE.isEnabled(logCategory)) {
			log.warn(message);
		}
	}

	public static void debug(String message, LogCategory logCategory) {
		if (LogSwitches.INSTANCE.isEnabled(logCategory)) {
			log.debug(message);
		}
	}

	public static void error(String message, LogCategory logCategory) {
		if (LogSwitches.INSTANCE.isEnabled(logCategory)) {
			log.error(message);
		}
	}

	// Extension to logs for page objects	
	public static void clickElementInfo(String name) {
		clickElementInfo(name,ElementType.BUTTON);
	}

	public static void clickElementInfo(String name, ElementType type){
		info(String.format("Click on '%s' %s",name,type), LogCategory.ClickWebElement);
	}
	
	public static void clickElementInfo(String name, String info) {
		clickElementInfo(name,ElementType.BUTTON);
	}

	public static void clickElementInfo(String name, String info, ElementType type){
		info(String.format("Click on '%s' %s - %s",name,type, info), LogCategory.ClickWebElement);
	}
	public static void error(String name, Throwable e){
		error("Failed to perform '"+name+"': "+ExceptionUtility.getStackTraceString(e));
	}

	public static void method(String methodName, Object... args) {
		info(String.format("Calling method '%s' with parameter values -> %s", methodName, Arrays.toString(args)));
	}

	/* Unit test */
	public static void main(String[] args) throws IOException {
		logFilePath = TestSetup.getRootPath() + File.separator + "logs" + File.separator + "log.log";
		
		test_LogInfo_WithMessage();
		test_LogWarn_WithMessage();
		test_LogDebug_WithMessage();
		test_LogError_WithMessage();
		test_LogInfo_NoMessage();
		test_LogWarn_NoMessage();
		test_LogDebug_NoMessage();
		test_LogError_NoMessage();
	}

	private static void assertLogLastEntryContains(String[] messages) {
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

	private static void test_LogInfo_WithMessage() {
		Log.info("Executing test_LogInfo_WithMessage() ...");
		String message = "Log Info";
		Log.info(message);
		assertLogLastEntryContains(new String[] {"INFO", message});
	}

	private static void test_LogWarn_WithMessage() {
		Log.info("Executing test_LogWarn_WithMessage() ...");
		String message = "Log Warn";
		Log.warn(message);
		assertLogLastEntryContains(new String[] {"WARN", message});
	}

	private static void test_LogDebug_WithMessage() {
		Log.info("Executing test_LogDebug_WithMessage() ...");
		String message = "Log Debug";
		Log.debug(message);
		assertLogLastEntryContains(new String[] {"DEBUG", message});
	}

	private static void test_LogError_WithMessage() {
		Log.info("Executing test_LogError_WithMessage() ...");
		String message = "Log Error";
		Log.error(message);
		assertLogLastEntryContains(new String[] {"ERROR", message});
	}

	private static void test_LogInfo_NoMessage() {
		Log.info("Executing test_LogInfo_NoMessage() ...");
		String message = "";
		Log.info(message);
		assertLogLastEntryContains(new String[] {"INFO", message});
	}

	private static void test_LogWarn_NoMessage() {
		Log.info("Executing test_LogWarn_NoMessage() ...");
		String message = "";
		Log.warn(message);
		assertLogLastEntryContains(new String[] {"WARN", message});
	}

	private static void test_LogDebug_NoMessage() {
		Log.info("Executing test_LogDebug_NoMessage() ...");
		String message = "";
		Log.debug(message);
		assertLogLastEntryContains(new String[] {"DEBUG", message});
	}

	private static void test_LogError_NoMessage() {
		Log.info("Executing test_LogError_NoMessage() ...");
		String message = "";
		Log.error(message);
		assertLogLastEntryContains(new String[] {"ERROR", message});
	}
}
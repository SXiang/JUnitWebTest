package common.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;

public class Log {
	private static Logger log = Logger.getLogger(Log.class.getName());
	private static String logFilePath;

	/* Generating log files for each test run tracked by US1399. 
	static {
		logFilePath = String.format("./selenium-wd/logs/%s-log.log", TestContext.INSTANCE.getRunUniqueId());
		updateLog4jConfiguration(logFilePath);
	}
	*/
	
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
	
	private static void updateLog4jConfiguration(String logFile) { 
	    Properties props = new Properties(); 
	    try { 
	    	String log4JPropFilePath = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "tests" + File.separator + "log4j.properties";;
	        InputStream inputStream = new FileInputStream(log4JPropFilePath); 
	        try {
	        	props.load(inputStream);
	        } finally {
	        	inputStream.close();
	        }
	    } catch (IOException ex) { 
	        Log.info("Could not load configuration file."); 
	    } 
	    props.setProperty("log4j.appender.FILE.file", logFile); 
	    LogManager.resetConfiguration(); 
	    PropertyConfigurator.configure(props); 
	}
	
	public static void main(String[] args) {
		test_LogInfo_WithMessage();
		test_LogWarn_WithMessage();
		test_LogDebug_WithMessage();
		test_LogError_WithMessage();
		test_LogInfo_NoMessage();
		test_LogWarn_NoMessage();
		test_LogDebug_NoMessage();
		test_LogError_NoMessage();
		FileUtility.deleteFile(Paths.get(logFilePath));
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

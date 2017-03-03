package common.source;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;

import common.source.BasePage.ElementType;

public class Log {
	private static Logger log = null;
	private static Logger stashLog  = null;
	private static String logFilePath;

	static {
		try {
			TestContext.INSTANCE.getTestSetup();
			System.setProperty("log4j.configurationFile", TestSetup.getExecutionPath(TestSetup.getRootPath()) + "log4j2" + File.separator + "log4j2.xml");
			log = LogManager.getLogger(Log.class.getName());
			stashLog = LogManager.getLogger("logstash.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	%d{dd MMM yyyy HH\:mm\:ss,SSS} - %t - %p - %F [%C -> %M, Line\: %L]\:
	public enum LogField{
		INDEX_ID ("index-id"),
		TEST_ENVIROMENT ("test-enviroment"),
		TEST_URL ("test-url"),
		TEST_CATEGORY ("test-category"),
		TEST_CLASS ("test-class"),
		TEST_METHOD ("test-method"),
		MSG_CLASS ("msg-class"),
		MSG_METHOD ("msg-meghod"),
		MSG_LINE ("msg-line"),
		MSG ("msg");

		private final String field;

		LogField(String field){
			this.field = field;
		}

		@Override
		public String toString(){
			return field;
		}
	}

	public static void info(String message) {
		log.info(formatLogMessage(message));
		TestContext.INSTANCE.updateTestMessage(message);
		stashLog.info(formatLogstashMessage(message));
	}

	public static void warn(String message) {
		log.warn(formatLogMessage(message));
		TestContext.INSTANCE.updateTestMessage(message);
		stashLog.warn(formatLogstashMessage(message));
	}

	public static void debug(String message) {
		log.debug(formatLogMessage(message));
		TestContext.INSTANCE.updateTestMessage(message);
		stashLog.debug(formatLogstashMessage(message));
	}

	public static void error(String message) {
		log.error(formatLogMessage(message));
		TestContext.INSTANCE.updateTestMessage(message);
		stashLog.error(formatLogstashMessage(message));
	}

	public static void info(String message, LogCategory logCategory) {
		if (LogSwitches.INSTANCE.isEnabled(logCategory)) {
			info(message);
		}
	}

	public static void warn(String message, LogCategory logCategory) {
		if (LogSwitches.INSTANCE.isEnabled(logCategory)) {
			warn(message);
		}
	}

	public static void debug(String message, LogCategory logCategory) {
		if (LogSwitches.INSTANCE.isEnabled(logCategory)) {
			debug(message);
		}
	}

	public static void error(String message, LogCategory logCategory) {
		if (LogSwitches.INSTANCE.isEnabled(logCategory)) {
			error(message);
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

	public static String formatLogMessage(String msg){
		StackTraceElement caller = getStackTraceElement();
		String logMessage = "["+caller.getClassName() + " -> " +caller.getMethodName() +
		", Line: "+caller.getLineNumber() +"]: "
		+msg.replaceAll(System.lineSeparator(), "").replaceAll("\\n", "");
		return logMessage;
	}

	public static String formatLogstashMessage(String msg){
		Map<String, ?> msgMap = getMessageMap(msg);
		String fieldSep = " ";
		String logstashMessage = "["+msgMap.get(LogField.INDEX_ID.toString()) + fieldSep + msgMap.get(LogField.TEST_CATEGORY.toString())
			+ fieldSep + msgMap.get(LogField.TEST_ENVIROMENT.toString()) + fieldSep + msgMap.get(LogField.TEST_URL.toString())
			+ fieldSep + msgMap.get(LogField.TEST_CLASS.toString()) + fieldSep + msgMap.get(LogField.TEST_METHOD.toString())
			+ fieldSep + msgMap.get(LogField.MSG_CLASS.toString()) + fieldSep + msgMap.get(LogField.MSG_METHOD.toString())
			+ fieldSep + msgMap.get(LogField.MSG_LINE.toString()) +"]:"
			+ fieldSep + msg.replaceAll(System.lineSeparator(), "").replaceAll("\\n", "");
		return logstashMessage;
	}

	public static String getJSONMessage(String msg){
		Map<String, ?> msgMap = getMessageMap(msg);
		String jsonString = new JSONObject(msgMap).toString();
		jsonString = new JSONObject(msgMap).toString();
		return jsonString;
	}

	private static Map<String, ?> getMessageMap(String msg) {
		Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());
		StackTraceElement caller = getStackTraceElement();
		map.put(LogField.MSG_CLASS.toString(), caller.getClassName());
		map.put(LogField.MSG_METHOD.toString(), caller.getMethodName());
		map.put(LogField.MSG_LINE.toString(), caller.getLineNumber());
		map.put(LogField.MSG.toString(), msg);
		map.putAll(TestContext.INSTANCE.getTestMap());
		return map;
    }

	public static StackTraceElement getStackTraceElement(){
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		if(elements.length<1){
			return null;
		}else if(elements.length<2){
			return elements[0];
		}
		String logClass = elements[1].getClassName();
		for(int i=1; i<elements.length; i++){
			String currentClass = elements[i].getClassName();
			if(!currentClass.equals(logClass)
					&& !currentClass.contains("$")
					&& !currentClass.contains("TestWatcher")
					&& !currentClass.contains("org.junit")
					){
				return elements[i];
			}
		}
		return elements[0];
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
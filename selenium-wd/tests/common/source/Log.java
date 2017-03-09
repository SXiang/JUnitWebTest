package common.source;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import common.source.BasePage.ElementType;

public class Log {

	private static final String COMMON_SOURCE_LOG = "common.source.Log";
	private static final Boolean DEBUG = false;   // enabled DEBUG to print additional Thread specific logs.

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
		return formatLogMessage(msg, DEBUG /*debugPrint*/);
	}

	public static String getJSONMessage(String msg){
		Map<String, ?> msgMap = getMessageMap(msg);
		String jsonString = new JSONObject(msgMap).toString();
		jsonString = new JSONObject(msgMap).toString();
		return jsonString;
	}

	private static String formatLogMessage(String msg, boolean debugPrint){
		StackTraceElement caller = getStackTraceElement(debugPrint);
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

	private static Map<String, ?> getMessageMap(String msg) {
		Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());
		StackTraceElement caller = getStackTraceElement();
		map.put(LogField.MSG_CLASS.toString(), caller.getClassName());
		map.put(LogField.MSG_METHOD.toString(), caller.getMethodName());
		map.put(LogField.MSG_LINE.toString(), caller.getLineNumber());
		map.put(LogField.MSG.toString(), msg);
		map.putAll(TestContext.INSTANCE.getLogData().toMap());
		return map;
    }

	private static StackTraceElement getStackTraceElement(){
		return getStackTraceElement(false);
	}

	private static StackTraceElement getStackTraceElement(boolean debugPrint){
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		if(elements.length<1){
			return null;
		}

		if (debugPrint) {
			Arrays.asList(elements).stream().forEach(
				s -> threadDebugPrint(ToStringBuilder.reflectionToString(s, ToStringStyle.DEFAULT_STYLE))
			);
		}

		if(elements.length<2){
			return elements[0];
		}

		for(int i=1; i<elements.length; i++){
			String currentClass = elements[i].getClassName();
			if(!currentClass.equals(COMMON_SOURCE_LOG)
					&& !currentClass.contains("$")
					&& !currentClass.contains("TestWatcher")
					&& !currentClass.contains("org.junit")
					){

				if (debugPrint) {
					threadDebugPrint("LogInternal :: TestMap values -> " + LogHelper.mapToString(TestContext.INSTANCE.getLogData().toMap()));
					threadDebugPrint("currentClass=" + currentClass);
					threadDebugPrint(ToStringBuilder.reflectionToString(elements[i], ToStringStyle.DEFAULT_STYLE));
					threadDebugPrint("ELEMENT className=" + elements[i].getClassName());
					threadDebugPrint("ELEMENT methodName=" + elements[i].getMethodName());
					threadDebugPrint("ELEMENT lineNumber=" + elements[i].getLineNumber());
				}

				return elements[i];
			}
		}
		return elements[0];
	}

	private static void threadDebugPrint(String message) {
		System.out.println(String.format("Thread=[%s], Message=[%s]", Thread.currentThread().getName(), message));
	}

}
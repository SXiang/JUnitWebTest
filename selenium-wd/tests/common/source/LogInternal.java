package common.source;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.JSONObject;

import common.source.Log.LogField;

public enum LogInternal {
	INSTANCE;

	public void info(String message) {
		LogFactory.getLog().info(formatLogMessage(message));
		TestContext.INSTANCE.updateTestMessage(message);
		LogFactory.getStashLog().info(formatLogstashMessage(message));
	}

	public void warn(String message) {
		LogFactory.getLog().warn(formatLogMessage(message));
		TestContext.INSTANCE.updateTestMessage(message);
		LogFactory.getStashLog().warn(formatLogstashMessage(message));
	}

	public void debug(String message) {
		LogFactory.getLog().debug(formatLogMessage(message));
		TestContext.INSTANCE.updateTestMessage(message);
		LogFactory.getStashLog().debug(formatLogstashMessage(message));
	}

	public void error(String message) {
		LogFactory.getLog().error(formatLogMessage(message, true /*debugPrint*/));
		TestContext.INSTANCE.updateTestMessage(message);
		LogFactory.getStashLog().error(formatLogstashMessage(message));
	}

	public void method(String methodName, Object... args) {
		info(String.format("Calling method '%s' with parameter values -> %s", methodName, Arrays.toString(args)));
	}

	public String formatLogMessage(String msg){
		return formatLogMessage(msg, false /*debugPrint*/);
	}

	public String getJSONMessage(String msg){
		Map<String, ?> msgMap = getMessageMap(msg);
		String jsonString = new JSONObject(msgMap).toString();
		jsonString = new JSONObject(msgMap).toString();
		return jsonString;
	}

	private String formatLogMessage(String msg, boolean debugPrint){
		StackTraceElement caller = getStackTraceElement(debugPrint);
		String logMessage = "["+caller.getClassName() + " -> " +caller.getMethodName() +
		", Line: "+caller.getLineNumber() +"]: "
		+msg.replaceAll(System.lineSeparator(), "").replaceAll("\\n", "");
		return logMessage;
	}

	private String formatLogstashMessage(String msg){
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

	private Map<String, ?> getMessageMap(String msg) {
		Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());
		StackTraceElement caller = getStackTraceElement();
		map.put(LogField.MSG_CLASS.toString(), caller.getClassName());
		map.put(LogField.MSG_METHOD.toString(), caller.getMethodName());
		map.put(LogField.MSG_LINE.toString(), caller.getLineNumber());
		map.put(LogField.MSG.toString(), msg);
		map.putAll(TestContext.INSTANCE.getTestMap());
		return map;
    }

	private StackTraceElement getStackTraceElement(){
		return getStackTraceElement(false);
	}

	private StackTraceElement getStackTraceElement(boolean debugPrint){
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

		String logClass1 = "common.source.LogInternal";
		String logClass2 = "common.source.Log";
		for(int i=1; i<elements.length; i++){
			String currentClass = elements[i].getClassName();
			if(!currentClass.equals(logClass1) && !currentClass.equals(logClass2)
					&& !currentClass.contains("$")
					&& !currentClass.contains("TestWatcher")
					&& !currentClass.contains("org.junit")
					){

				if (debugPrint) {
					threadDebugPrint("LogInternal :: TestMap values -> " + LogHelper.mapToString(TestContext.INSTANCE.getTestMap()));
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

	private void threadDebugPrint(String message) {
		System.out.println(String.format("Thread=[%s], Message=[%s]", Thread.currentThread().getName(), message));
	}
}

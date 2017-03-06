package common.source;

import java.util.Arrays;
import common.source.BasePage.ElementType;

public class Log {

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
		LogInternal.INSTANCE.info(message);
	}

	public static void warn(String message) {
		LogInternal.INSTANCE.warn(message);
	}

	public static void debug(String message) {
		LogInternal.INSTANCE.debug(message);
	}

	public static void error(String message) {
		LogInternal.INSTANCE.error(message);
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

	public static void method(String methodName, Object... args) {
		info(String.format("Calling method '%s' with parameter values -> %s", methodName, Arrays.toString(args)));
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
}
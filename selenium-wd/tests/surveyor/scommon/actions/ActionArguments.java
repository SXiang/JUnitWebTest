package surveyor.scommon.actions;

public class ActionArguments {
	public static boolean isEmpty(String argValue) {
		return (argValue == null || argValue.isEmpty());
	}

	public static void verifyNotNullOrEmpty(String actionName, String argName, String argValue) throws Exception {
		if (isEmpty(argValue)) {
			throw new Exception(String.format("'%s' action argument '%s' cannot be Null or Empty.", actionName, argName));
		}
	}
	
	public static void verifyGreaterThanZero(String actionName, String argName, Integer argValue) throws Exception {
		if (argValue <= 0) {
			throw new Exception(String.format("'%s' action argument '%s' MUST be greater than zero.", actionName, argName));
		}
	}
	
	public static String evaluateArgForFunction(String argValue) throws Exception {
		ActionFunctionExecutor funcExecutor = new ActionFunctionExecutor();
		if (funcExecutor.isValidFunction(argValue)) {
			return funcExecutor.executeFunction(argValue);	
		}
		return argValue;
	}
}

package surveyor.scommon.actions;

import java.lang.reflect.Method;
import java.util.List;

import common.source.RegexUtility;

public class ActionFunctionExecutor {
	
	private static final String REGEX_PATTERN_SPLIT_BY_COMMA = ",";
	private static final String REGEX_PATTERN_EXTRACT_FUNCTION_ARGS = "([a-zA-Z_]\\w+)\\((.+)\\)";
	private ActionFunctions actionFunc = null;
	private Method[] methods;
	
	public ActionFunctionExecutor() {
		actionFunc = new ActionFunctions();
		methods = actionFunc.getClass().getMethods();	
	}
	
	public boolean isValidFunction(String argValue) {
		List<String> groups = RegexUtility.getMatchingGroups(argValue, REGEX_PATTERN_EXTRACT_FUNCTION_ARGS);
		if (groups != null && groups.size() > 0) {
			String functionName = groups.get(0);
			for (int i=0; i< methods.length; i++) {
				if(methods[i].getName().equals(functionName)){
					return true;
				}
			}
		}
		return false;
	}

	public String executeFunction(String argValue)  throws Exception {
		Object outputValue = null;
		List<String> groups = RegexUtility.getMatchingGroups(argValue, REGEX_PATTERN_EXTRACT_FUNCTION_ARGS);
		if (groups != null && groups.size() > 0) {
			String functionName = groups.get(0);
			String functionArgs = groups.get(1);
			List<String> paramGroups = RegexUtility.split(functionArgs, REGEX_PATTERN_SPLIT_BY_COMMA);
			
			ActionFunctions actionFunc = new ActionFunctions();
			for (int i=0; i< methods.length; i++) {
				if(methods[i].getName().equals(functionName)){
					// Ensure user has support correct number of parameters.
					validateParameterCount(paramGroups, functionName, functionArgs, i);

					Object[] objArr = buildFunctionParameters(paramGroups, i);
					// Execute the action function.
					outputValue = methods[i].invoke(actionFunc, objArr);
				}
			}
			
		}
		return String.valueOf(outputValue);
	}

	private Object[] buildFunctionParameters(List<String> paramGroups, int i) {
		Object[] objArr = new Object[paramGroups.size()];
		for (int j = 0; j < paramGroups.size(); j++) {
			String param = paramGroups.get(i);
			// Remove the enclosing ' or " characters from string arguments
			if (param.startsWith("'") || param.startsWith("\"")) {
				param = param.substring(1);
			}
			if (param.endsWith("'") || param.endsWith("\"")) {
				param = param.substring(0, param.length()-1);
			}
			objArr[i] = param;
		}
		return objArr;
	}

	private void validateParameterCount(List<String> paramGroups, String functionName, String functionArgs, int i) throws Exception {
		int parameterCount = methods[i].getParameterCount();
		int pSize = paramGroups.size();
		if (pSize != parameterCount) {
			throw new Exception(String.format("Incorrect parameter count for Action Function - '%s'. Expected=%d, Provided=%d",
					functionName, parameterCount, pSize));
		}
	}
}

package surveyor.scommon.actions;

import java.lang.reflect.Method;
import java.util.List;

import common.source.RegexUtility;

public class ActionFunctionExecutor {
	
	private ActionFunctions actionFunc = null;
	private Method[] methods;
	
	public ActionFunctionExecutor() {
		actionFunc = new ActionFunctions();
		methods = actionFunc.getClass().getMethods();	
	}
	
	public boolean isValidFunction(String argValue) {
		List<String> groups = RegexUtility.getMatchingGroups(argValue, RegexUtility.REGEX_PATTERN_EXTRACT_FUNCTION_ARGS);
		if (groups != null && groups.size() > 0) {
			String functionName = groups.get(1);
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
		List<String> groups = RegexUtility.getMatchingGroups(argValue, RegexUtility.REGEX_PATTERN_EXTRACT_FUNCTION_ARGS);
		if (groups != null && groups.size() > 0) {
			String functionName = groups.get(1);
			String functionArgs = groups.get(2);
			List<String> paramGroups = RegexUtility.split(functionArgs, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
			
			ActionFunctions actionFunc = new ActionFunctions();
			for (int i=0; i< methods.length; i++) {
				if(methods[i].getName().equals(functionName)){
					// Ensure user has support correct number of parameters.
					validateParameterCount(paramGroups, functionName, functionArgs, i);

					Object[] objArr = buildFunctionParameters(paramGroups);
					// Execute the action function.
					outputValue = methods[i].invoke(actionFunc, objArr);
				}
			}
			
		}
		return String.valueOf(outputValue);
	}

	private Object[] buildFunctionParameters(List<String> paramGroups) throws Exception {
		Object[] objArr = new Object[paramGroups.size()];
		for (int j = 0; j < paramGroups.size(); j++) {
			String param = paramGroups.get(j);
			boolean isString = false;
			boolean isInteger = false;
			boolean isFloat = false;
			// Remove the enclosing ' or " characters from string arguments
			if (param.startsWith("'") || param.startsWith("\"")) {
				param = param.substring(1);
				isString = true;
			}
			if (isString) {
				if (param.endsWith("'") || param.endsWith("\"")) {
					param = param.substring(0, param.length()-1);
				} else {
					throw new Exception(String.format("String argument %s specified not enclosed correctly in quotes.", param));
				}
			}
			
			// loosely deduce type of the argument based on '.' in parameter.
			if (!isString) {
				if (param.contains(".")) { isFloat = true; }
				else { isInteger = true; }
			}
			
			if (isString) { objArr[j] = param; }
			else if (isInteger) { objArr[j] = Integer.valueOf(param); }
			else if (isFloat) { objArr[j] = Float.valueOf(param); }
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

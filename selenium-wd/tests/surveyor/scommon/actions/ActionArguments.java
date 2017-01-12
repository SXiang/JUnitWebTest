package surveyor.scommon.actions;

import java.util.ArrayList;
import java.util.List;

import common.source.NumberUtility;
import common.source.RegexUtility;

public class ActionArguments {
	public static boolean isEmpty(String argValue) {
		return (argValue == null || argValue.isEmpty());
	}

	public static List<Double> getDoubleList(String argValue) throws Exception {
		/* Supported argument formats include:
		 * 1. {n1,n2,n3} .. For eg "1.33,3.4,15.213"
		 */
		List<Double> list = new ArrayList<Double>();
		if (argValue.contains(RegexUtility.COMMA_SPLIT_REGEX_PATTERN)) {
			List<String> splitArr = RegexUtility.split(argValue, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
			if (splitArr.size() < 2) {
				throw new Exception(String.format("Unsupported pattern specified in argument - [%s]. "
						+ "Supported pattern: {n1,n2,n3} .. For eg '1.33,3.4,15.213'", argValue));
			}
			for (int i = 0; i < splitArr.size(); i++) {
				list.add(NumberUtility.getDoubleValueOf(splitArr.get(i)));
			}
		} else {
			list.add(NumberUtility.getDoubleValueOf(argValue));
		}
		return list;
	}

	public static List<Integer> getNumericList(String argValue) throws Exception {
		/* Supported argument formats include:
		 * 1. To specify range use: {n:m} .. For eg. "3:5"
		 * 2. To specify a specific row: {n} .. For eg "3" will run test ONLY for row 3.
		 * 3. To specify random rows: {n1,n2,n3} .. For eg "1,3,15" will run test for rows 1, 3 and 15.
		 */
		List<Integer> list = new ArrayList<Integer>();
		if (argValue.contains(RegexUtility.COLON_SPLIT_REGEX_PATTERN)) {
			List<String> splitArr = RegexUtility.split(argValue, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
			if (splitArr.size() != 2) {
				throw new Exception(String.format("Unsupported range pattern specified in argument - [%s]. "
						+ "Supported pattern: {n:m} .. For eg. '3:5'", argValue));
			}
			Integer startRange = NumberUtility.getIntegerValueOf(splitArr.get(0));
			Integer endRange = NumberUtility.getIntegerValueOf(splitArr.get(1));
			for (int num = startRange; num <= endRange; num++) {
				list.add(num);
			}
		} else if (argValue.contains(RegexUtility.COMMA_SPLIT_REGEX_PATTERN)) {
			List<String> splitArr = RegexUtility.split(argValue, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
			if (splitArr.size() < 2) {
				throw new Exception(String.format("Unsupported pattern specified in argument - [%s]. Supported pattern: {n1,n2,n3} .. For eg '1,3,15'", argValue));
			}
			for (int i = 0; i < splitArr.size(); i++) {
				list.add(NumberUtility.getIntegerValueOf(splitArr.get(i)));
			}
		} else {
			list.add(NumberUtility.getIntegerValueOf(argValue));
		}
		return list;
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

	public static Boolean evaluateArgForFunction(String argValue, StringBuilder evaluatedArgValue) throws Exception {
		ActionFunctionExecutor funcExecutor = new ActionFunctionExecutor();
		if (funcExecutor.isValidFunction(argValue)) {
			evaluatedArgValue.append(funcExecutor.executeFunction(argValue));
			return true;
		}
		return false;
	}
}

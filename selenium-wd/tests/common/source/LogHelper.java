package common.source;

import java.util.Arrays;
import java.util.List;

public class LogHelper {

	public LogHelper() {
	}

	public static String strArrayToString(String[] array) {
		if (array != null && array.length > 0) {
			return String.format("[Array values] -> %s", Arrays.toString(array));
		}
		return "";
	}

	public static String intArrayToString(Integer[] array) {
		if (array != null && array.length > 0) {
			return String.format("[Array values] -> %s", Arrays.toString(array));
		}
		return "";
	}

	public static String floatArrayToString(Float[] array) {
		if (array != null && array.length > 0) {
			return String.format("[Array values] -> %s", Arrays.toString(array));
		}
		return "";
	}

	public static String listOfStrArrayToString(List<String[]> listOfStrArray) {
		StringBuilder builder = new StringBuilder();
		if (listOfStrArray != null) {
			for (String[] array : listOfStrArray) {
				builder.append(strArrayToString(array));
				builder.append(BaseHelper.getLineSeperator());
			}
		}
		return builder.toString();
	}

	public static String strListToString(List<String> values) {
		String[] array = values.toArray(new String[values.size()]);
		return strArrayToString(array);
	}

	public static String intListToString(List<Integer> values) {
		Integer[] array = values.toArray(new Integer[values.size()]);
		return intArrayToString(array);
	}

	public static String floatListToString(List<Float> values) {
		Float[] array = values.toArray(new Float[values.size()]);
		return floatArrayToString(array);
	}
}

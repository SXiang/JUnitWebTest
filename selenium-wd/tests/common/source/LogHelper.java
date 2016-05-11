package common.source;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
				builder.append(System.getProperty("line.separator"));
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

	public static String boolMapToString(HashMap<String, Boolean> values) {
		StringBuilder builder = new StringBuilder();
		if (values != null && values.size() > 0) {
			Iterator<String> iterator = values.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				builder.append(String.format("    [%s=%b]", key, values.get(key)));
				builder.append(System.getProperty("line.separator"));
			}
		}
		return builder.toString();
	}
}

package common.source;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LogHelper {

	public LogHelper() {
	}

	public static <T> String arrayToString(T[] array) {
		if (array != null && array.length > 0) {
			return String.format("[Array values] -> %s", Arrays.toString(array));
		}
		return "";
	}

	public static <T> String listOfArrayToString(List<T[]> listOfArray) {
		StringBuilder builder = new StringBuilder();
		if (listOfArray != null) {
			for (T[] array : listOfArray) {
				builder.append(arrayToString(array));
				builder.append(BaseHelper.getLineSeperator());
			}
		}
		return builder.toString();
	}

	public static <T> String listToString(List<T> values) {
		StringBuilder builder = new StringBuilder();
		if (values != null && values.size() > 0) {
			builder.append(values.get(0));
			if (values.size() > 1) {
				for (int i = 1; i < values.size(); i++) {
					builder.append(",");
					builder.append(values.get(i));
				}
			}
		}
		return String.format("[List values] -> [%s]", builder.toString());
	}

	public static String strListToString(List<String> values) {
		return listToString(values);
	}

	public static String intListToString(List<Integer> values) {
		return listToString(values);
	}

	public static String floatListToString(List<Float> values) {
		return listToString(values);
	}

	public static <K,V> String mapToString(Map<K, V> values) {
		StringBuilder builder = new StringBuilder();
		if (values != null && values.size() > 0) {
			Iterator<K> iterator = values.keySet().iterator();
			while (iterator.hasNext()) {
				K key = iterator.next();
				builder.append(String.format("    [%s=%s]", key, values.get(key)));
				builder.append(System.getProperty("line.separator"));
			}
		}
		return builder.toString();
	}

	public static <K,V> String mapListToString(List<Map<K, V>> values) {
		StringBuilder builder = new StringBuilder();
		if (values != null && values.size() > 0) {
			builder.append(mapToString(values.get(0)));
			if (values.size() > 1) {
				for (int i = 1; i < values.size(); i++) {
					builder.append(",");
					builder.append(mapToString(values.get(i)));
				}
			}
		}
		return String.format("[List<Map> values] -> [%s]", builder.toString());
	}

	public static String objectToString(Object value) {
		return ToStringBuilder.reflectionToString(value, ToStringStyle.DEFAULT_STYLE);
	}
}

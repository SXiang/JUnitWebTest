package common.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArrayUtility {

	public ArrayUtility() {
	}

	public static List<String> getColumnStringList(List<String[]> valuesList, Integer columnIdx) {
		List<String> retList = null;
		if (valuesList != null) {
			retList = new ArrayList<String>(valuesList.size());
			for (String[] arr : valuesList) {
				if (arr != null && arr.length > columnIdx) {
					retList.add(arr[columnIdx]);
				}
			}
		}
		Log.info(String.format("Array values for column index=[%d] : %s", columnIdx, LogHelper.strListToString(retList)));
		return retList;
	}

	public static List<String[]> getListValuesSkipHeader(List<String[]> values) {
		List<String[]> retList = new ArrayList<String[]>();
		if (values != null && values.size() > 1) {
			for (int i = 1; i < values.size(); i++) {
				retList.add(values.get(i));
			}
		}
		return retList;
	}

	public static boolean listValuesHasHeader(List<String[]> values) {
		boolean retVal = false;
		if (values != null && values.size() > 1) {
			if (values.get(0).length != values.get(1).length) {
				retVal = true;
			}
		}
		return retVal;
	}

	public static List<String> getDistinctValues(List<String> stringValues) {
		List<String> outputList = new ArrayList<String>();
		if (stringValues != null) {
			HashMap<String, Boolean> map = new HashMap<String, Boolean>();
			if (stringValues != null) {
				for (String strValue : stringValues) {
					if (!map.containsKey(strValue)) {
						map.put(strValue, true);
					}
				}
			}

			if (map.size() > 0) {
				for (String key : map.keySet()) {
					outputList.add(key);
				}
			}
		}
		return outputList;
	}

	public static List<String> convertIntListToStrList(List<Integer> intList) {
		List<String> strList = null;
		if (intList != null) {
			strList = new ArrayList<String>(intList.size());
			for (Integer element : intList) {
				strList.add(String.valueOf(element));
			}
		}
		return strList;
	}

	public static List<Integer> convertStrListToIntList(List<String> strList) {
		List<Integer> intList = null;
		if (strList != null) {
			intList = new ArrayList<Integer>(strList.size());
			for (String element : strList) {
				if (!BaseHelper.isNullOrEmpty(element)) {
					intList.add(Integer.valueOf(element.trim()));
				}
			}
		}
		return intList;
	}

	/**
	 * Checks if all the values in the specified String array are greater than the specified minValue.
	 * @param values - String array to check
	 * @param minValue - minimum value
	 * @return
	 */
	public static boolean areValuesGreater(String[] values, Float minValue) {
		if (values == null || values.length == 0) {
			return false;
		}
		for (String value : values) {
			if (Float.valueOf(value) < minValue) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Append text value to the last element in a list
	 * @param list
	 * @param value
	 */
	public static void appendToLastString(List<String> list, String value){
		appendToString(list, list.size()-1, value);
	}

	/**
	 * Append text value to element indexed in a list
	 * @param list
	 * @param index
	 * @param value
	 */
	public static void appendToString(List<String>list, int index, String value){
		if(value.isEmpty()||list.size()<=index){
			return;
		}
		value = list.get(index) + value;
		list.set(index, value);
	}
}

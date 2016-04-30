package common.source;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtility {

	public ArrayUtility() {
	}

	public static List<String> getColumnStringList(List<String[]> valuesList, Integer columnIdx) {
		List<String> retList = null;
		if (valuesList != null) {
			retList = new ArrayList<String>(valuesList.size());
			for (String[] arr : valuesList) {
				if (arr != null || arr.length > columnIdx) {
					retList.add(arr[columnIdx]);
				}
			}
		}
		Log.info(String.format("Array values for column index=[%d] : %s", columnIdx, LogHelper.listOfStrArrayToString(valuesList)));
		return retList;
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
}

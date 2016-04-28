package common.source;

public class ArrayUtility {

	public ArrayUtility() {
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

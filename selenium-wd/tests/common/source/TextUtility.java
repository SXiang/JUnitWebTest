package common.source;

public class TextUtility {

	/*
	 * Retains ASCII characters in range 0-127. Replaces all other characters with EMPTY.
	 */
	public static String removeNonAsciiSpecialChars(String value) {
		return value.replaceAll("[^\\x00-\\x7F]", "");
	}
}
package common.source;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;

/**
 * Regex Matching Utility class.
 * 
 * @author spulikkal
 *
 */
public class RegexUtility {
	public static final String COLON_SPLIT_REGEX_PATTERN = ":";
	public static final String COMMA_SPLIT_REGEX_PATTERN = ",";
	public static final String REGEX_PATTERN_EXTRACT_FUNCTION_ARGS = "([a-zA-Z_]\\w+)\\((.+)\\)";
	public static final String REGEX_PATTERN_EXTRACT_VALUE_WRAPPED_IN_QUOTE = "'(.+)'";

	private static int flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;
	
	/**
	 * Returns whether the specified string matches with the specified regex pattern.
	 * 
	 * @param inputString - string to match.
	 * @param regexPattern - regex pattern to match against.
	 * @return
	 */
	public static boolean matchesPattern(String inputString, String regexPattern) {
		 Pattern pattern = Pattern.compile(regexPattern, flags);
		 Matcher matcher = pattern.matcher(inputString);
		 return matcher.matches();
	}

	/**
	 * Gets matching groups from the specified regex pattern.
	 * 
	 * @param inputString - string to match.
	 * @param regexPattern - regex pattern.
	 * @return
	 */
	public static List<String> getMatchingGroups(String inputString, String regexPattern) {
		List<String> output = new ArrayList<String>(); 
		Pattern pattern = Pattern.compile(regexPattern, flags);
		 Matcher matcher = pattern.matcher(inputString);
		 if (matcher.find()) {
			int count = matcher.groupCount();
			for (int i = 0; i <= count; i++) {
				 output.add(matcher.group(i));
			}
		 }
		 
		 return output;
	}

	/**
	 * Returns a list of String with matching parts after split.
	 * If parts are empty then they are not returned in the output
	 * 
	 * @param inputString - string to split.
	 * @param regexPattern - regex pattern used for splitting.
	 * @return
	 */
	public static List<String> split(String inputString, String regexPattern) {
		List<String> output = new ArrayList<String>(); 
		Pattern pattern = Pattern.compile(regexPattern, flags);
		String[] items = pattern.split(inputString);
        for(String s : items) {
            output.add(s);
        }
        return output;
	}
	
	public static void main(String[] args) {
		Log.info("Running test - testMatchesPattern_functionNameAndArgument_Success() ...");
		testMatchesPattern_functionNameAndArgument_Success();
		Log.info("Running test - testMatchesPattern_functionNameNoArgument_FailMatch() ...");
		testMatchesPattern_functionNameNoArgument_FailMatch();
		Log.info("Running test - testGetMatchingGroups_functionNameAndArgument_Success() ...");
		testGetMatchingGroups_functionNameAndArgument_Success();
		Log.info("Running test - testGetMatchingGroups_functionNameNoArgument_NoGroupsReturned() ...");
		testGetMatchingGroups_functionNameNoArgument_NoGroupsReturned();
		Log.info("Running test - testSplit_SplitByCommaMultipleParts_Success() ...");
		testSplit_SplitByCommaMultipleParts_Success();
		Log.info("Running test - testSplit_SplitByColonTwoPartsLastEmpty_Success() ...");
		testSplit_SplitByColonEmptyPartsNotReturned_Success();
		Log.info("Running test - testSplit_SplitByCommaOnePart_Success() ...");
		testSplit_SplitByCommaOnePart_Success();
	}

	private static void testMatchesPattern_functionNameAndArgument_Success() {
		String testValue = "GenerateRandomString(10)";
		Assert.assertTrue(RegexUtility.matchesPattern(testValue, REGEX_PATTERN_EXTRACT_FUNCTION_ARGS));
	}

	private static void testMatchesPattern_functionNameNoArgument_FailMatch() {
		String testValue = "GenerateRandomString()";
		Assert.assertFalse(RegexUtility.matchesPattern(testValue, REGEX_PATTERN_EXTRACT_FUNCTION_ARGS));
	}

	private static void testGetMatchingGroups_functionNameAndArgument_Success() {
		String testValue = "GenerateRandomString(10)";
		String functionName = null;
		String functionArgs = null;
		List<String> groups = RegexUtility.getMatchingGroups(testValue, REGEX_PATTERN_EXTRACT_FUNCTION_ARGS);
		if (groups != null && groups.size() > 0) {
			functionName = groups.get(1);
			functionArgs = groups.get(2);
		}
		Assert.assertTrue(functionName.equals("GenerateRandomString"));
		Assert.assertTrue(functionArgs.equals("10"));
	}

	private static void testGetMatchingGroups_functionNameNoArgument_NoGroupsReturned() {
		String testValue = "GenerateRandomString()";
		List<String> groups = RegexUtility.getMatchingGroups(testValue, REGEX_PATTERN_EXTRACT_FUNCTION_ARGS);
		Assert.assertTrue(groups.size() == 0);
	}
	
	private static void testSplit_SplitByCommaMultipleParts_Success() {
		String functionArgs = "10,10,20,43";
		List<String> paramGroups = RegexUtility.split(functionArgs , RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		Assert.assertTrue(paramGroups.size() == 4);
	}

	private static void testSplit_SplitByColonEmptyPartsNotReturned_Success() {
		String functionArgs = "10,,";
		List<String> paramGroups = RegexUtility.split(functionArgs , RegexUtility.COLON_SPLIT_REGEX_PATTERN);
		Assert.assertTrue(paramGroups.size() == 1);
	}

	private static void testSplit_SplitByCommaOnePart_Success() {
		String functionArgs = "10";
		List<String> paramGroups = RegexUtility.split(functionArgs , RegexUtility.COLON_SPLIT_REGEX_PATTERN);
		Assert.assertTrue(paramGroups.size() == 1);
	}
}

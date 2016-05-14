package common.source;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
	public static final String DOT_SPLIT_REGEX_PATTERN = "\\.";
	public static final String NEWLINE_SPLIT_REGEX_PATTERN = "\\r?\\n";
	public static final String SPACE_SPLIT_REGEX_PATTERN = " ";
	public static final String COLON_SPLIT_REGEX_PATTERN = ":";
	public static final String SEMI_COLON_SPLIT_REGEX_PATTERN = ";";
	public static final String VERTICAL_BAR_SPLIT_REGEX_PATTERN = "\\|";
	public static final String COMMA_SPLIT_REGEX_PATTERN = ",";
	public static final String REGEX_PATTERN_EXTRACT_FUNCTION_ARGS = "([a-zA-Z_]\\w+)\\((.+)\\)";
	public static final String REGEX_PATTERN_EXTRACT_VALUE_WRAPPED_IN_QUOTE = "'(.+)'";
	public static final String REGEX_PATTERN_EXTRACT_LINES_STARTING_WITH_DIGITS = "^\\d.*";
	public static final String REGEX_PATTERN_EXTRACT_EVERYTHING = "(.*?)";
	public static final String REGEX_PATTERN_SPACES = "\\s+";

	private static int flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;

	/**
	 * Returns whether the specified string matches with the specified regex pattern.
	 * 
	 * @param inputString
	 *            - string to match.
	 * @param regexPattern
	 *            - regex pattern to match against.
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
	 * @param inputString
	 *            - string to match.
	 * @param regexPattern
	 *            - regex pattern.
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
	 * Returns a list of String with matching parts after split. If parts are empty then they are not returned in the output
	 * 
	 * @param inputString
	 *            - string to split.
	 * @param regexPattern
	 *            - regex pattern used for splitting.
	 * @return
	 */
	public static List<String> split(String inputString, String regexPattern) {
		List<String> output = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regexPattern, flags);
		String[] items = pattern.split(inputString);
		for (String s : items) {
			output.add(s);
		}
		return output;
	}

	/**
	 * This methods looks at the culture of the user and determines the date format Regex accordingly Cultures supported right now: English, French, Chinese e.g. When used in combine with
	 * getMatchingGroups method, can extract the date from a String. "1/23/2016 11:16 PM PST" can be extracted from a String like "1/23/2016 11:16 PM PST Administrator Automation Test Note 122291"
	 * 
	 * @param whether
	 *            the date check is for a report or not (Note: Pages have the same Date format without TimeZone
	 * @return date format for the user locale
	 */

	public static String getReportRegexDatePattern(boolean useTimeZone) {
		String culture = TestContext.INSTANCE.getUserCulture();
		String dateFormat = null;
		if (useTimeZone) {
			if ((culture.equals("en-US")) || (culture.equals("fr"))) {
				dateFormat = "[0-9]{1,2}+['/']+[0-9]{1,2}+['/']+[0-9]{4}+[\\s+]+[0-9]{1,2}+[':']+[0-9]{1,2}+[\\s+]+[\\w]{2}+[\\s+]+[\\w]{3}";
			}
			if (culture.equals("zh-Hans")) {
				dateFormat = "[0-9]{4}+['/']+[0-9]{1,2}+['/']+[0-9]{1,2}+[\\s+]+[0-9]{1,2}+[':']+[0-9]{1,2}+[\\s+]+[\\w]{2}+[\\s+]+[\\w]{3}";
			}
		} else {
			if ((culture.equals("en-US")) || (culture.equals("fr"))) {
				dateFormat = "[0-9]{1,2}+['/']+[0-9]{1,2}+['/']+[0-9]{4}+[\\s+]+[0-9]{1,2}+[':']+[0-9]{1,2}+[\\s+]+[\\w]{2}";
			}
			if (culture.equals("zh-Hans")) {
				dateFormat = "[0-9]{4}+['/']+[0-9]{1,2}+['/']+[0-9]{1,2}+[\\s+]+[0-9]{1,2}+[':']+[0-9]{1,2}+[\\s+]+[\\w]{2}";
			}
		}
		return dateFormat;

	}

	/**
	 * Returns first String in between two given patterns
	 * 
	 * @param inputString
	 * @param regexPattern1
	 * @param regexPattern2
	 * @return
	 */
	public static String getStringInBetween(String inputString, String regexPattern1, String regexPattern2) {
		return getStringInBetween(inputString, regexPattern1, regexPattern2, false, false);
	}

	/**
	 * Returns all Strings in between two given patterns
	 * 
	 * @param inputString
	 * @param regexPattern1
	 * @param regexPattern2
	 * @return
	 */
	public static List<String> getStringsInBetween(String inputString, String regexPattern1, String regexPattern2) {
		return getStringsInBetween(inputString, regexPattern1, regexPattern2, false, false);
	}

	/**
	 * Returns last string match between two given patterns
	 * 
	 * @param inputString
	 * @param regexPattern1
	 * @param regexPattern2
	 * @return
	 */
	public static String getStringInBetween(String inputString, String regexPattern1, String regexPattern2, boolean matchBeginningOfLine, boolean matchEndOfLine) {
		List<String> matchingStrings = getStringsInBetween(inputString, regexPattern1, regexPattern2, matchBeginningOfLine, matchEndOfLine);
		return (matchingStrings.size() > 0) ? matchingStrings.get(matchingStrings.size()-1) : "";
	}

	/**
	 * Returns all String in between two given patterns
	 * 
	 * @param inputString
	 * @param regexPattern1
	 * @param regexPattern2
	 * @return
	 */
	public static List<String> getStringsInBetween(String inputString, String regexPattern1, String regexPattern2,
			boolean matchBeginningOfLine, boolean matchEndOfLine) {
		List<String> matchingStrings = new ArrayList<String>();
		String regexString = (matchBeginningOfLine ? "^" : "") + Pattern.quote(regexPattern1) + REGEX_PATTERN_EXTRACT_EVERYTHING + Pattern.quote(regexPattern2) + (matchEndOfLine ? "$" : "");
		Pattern pattern = Pattern.compile(regexString, flags | Pattern.DOTALL | Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(inputString);
		while (matcher.find()) {
			matchingStrings.add(matcher.group(1));
		}
		return matchingStrings;
	}

	/**
	 * Returns next line after a pattern
	 * 
	 * @param inputString
	 * @param regexPattern
	 * @return
	 */
	public static String getNextLineAfterPattern(String inputString, String subString) {
		String remaining = inputString.substring(inputString.lastIndexOf(subString));
		String splits[] = remaining.split("\\n");
		return splits[1].trim();
	}
	
	/**
	 * Compare strings by equals or matches
	 * @param line
	 * @param expectedLine
	 * @return
	 */
	public static boolean equalsOrMatches(String actualValue, String expectedValueOrPattern){
		if(actualValue==null){
			return false;
		}else{
			actualValue = actualValue.trim();
			expectedValueOrPattern = expectedValueOrPattern.trim();
		}
		boolean isEqual = actualValue.equals(expectedValueOrPattern);
		boolean isMatch = false;
		if(isEqual){
			return isEqual;
		}else{
			try{
				isMatch = actualValue.matches(expectedValueOrPattern);
			}catch(Exception e){				
			}
		}
		return isMatch;
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
		Log.info("Running test - testSplit_SplitByDotMultipleParts_Success() ...");
		testSplit_SplitByDotMultipleParts_Success();
		Log.info("Running test - testSplit_SplitByDotEmptyPartsNotReturned_Success() ...");
		testSplit_SplitByDotEmptyPartsNotReturned_Success();
		Log.info("Running test - testSplit_SplitByCommaMultipleParts_Success() ...");
		testSplit_SplitByCommaMultipleParts_Success();
		Log.info("Running test - testSplit_SplitByColonTwoPartsLastEmpty_Success() ...");
		testSplit_SplitByColonEmptyPartsNotReturned_Success();
		Log.info("Running test - testSplit_SplitByCommaOnePart_Success() ...");
		testSplit_SplitByCommaOnePart_Success();
		Log.info("Running test - testMatchesPatternEN_US_functiongetReportRegexDatePattern_Success() ...");
		testMatchesPatternEN_US_functiongetReportRegexDatePattern_Success();
		Log.info("Running test - testMatchesPatternCN_functiongetReportRegexDatePattern_Success() ...");
		testMatchesPatternCN_functiongetReportRegexDatePattern_Success();
		Log.info("Running test - testMatchesPatternFR_functiongetReportRegexDatePattern_Success() ...");
		testMatchesPatternFR_functiongetReportRegexDatePattern_Success();
		Log.info("Running test - testGetStringInBetween_Success() ...");
		test_functionGetStringInBetween_Success();
		testgetNextLineAfterPattern_Success();
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
		List<String> paramGroups = RegexUtility.split(functionArgs, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		Assert.assertTrue(paramGroups.size() == 4);
	}

	private static void testSplit_SplitByDotMultipleParts_Success() {
		String functionArgs = "10.10.20.43";
		List<String> paramGroups = RegexUtility.split(functionArgs, RegexUtility.DOT_SPLIT_REGEX_PATTERN);
		Assert.assertTrue(paramGroups.size() == 4);
	}

	private static void testSplit_SplitByDotEmptyPartsNotReturned_Success() {
		String functionArgs = "10..";
		List<String> paramGroups = RegexUtility.split(functionArgs, RegexUtility.DOT_SPLIT_REGEX_PATTERN);
		Assert.assertTrue(paramGroups.size() == 1);
	}

	private static void testSplit_SplitByColonEmptyPartsNotReturned_Success() {
		String functionArgs = "10,,";
		List<String> paramGroups = RegexUtility.split(functionArgs, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
		Assert.assertTrue(paramGroups.size() == 1);
	}

	private static void testSplit_SplitByCommaOnePart_Success() {
		String functionArgs = "10";
		List<String> paramGroups = RegexUtility.split(functionArgs, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
		Assert.assertTrue(paramGroups.size() == 1);
	}

	private static void testMatchesPatternEN_US_functiongetReportRegexDatePattern_Success() {
		TestContext.INSTANCE.setUserCulture("en-US");
		String dateTime = "1/21/2016 4:09 AM PST Administrator TC76 Automation Note 811835";
		String dateFormat = RegexUtility.getReportRegexDatePattern(true);
		List<String> groups = RegexUtility.getMatchingGroups(dateTime, dateFormat);
		Assert.assertTrue(groups.get(0).trim().equals("1/21/2016 4:09 AM PST"));
	}

	private static void testMatchesPatternCN_functiongetReportRegexDatePattern_Success() {
		TestContext.INSTANCE.setUserCulture("zh-Hans");
		String dateTime = "2016/1/21 4:08 AM CST sqapicsup@picarro.com TC1249 Automation Note 811835";
		String dateFormat = RegexUtility.getReportRegexDatePattern(true);
		List<String> groups = RegexUtility.getMatchingGroups(dateTime, dateFormat);
		Assert.assertTrue(groups.get(0).trim().equals("2016/1/21 4:08 AM CST"));
	}

	private static void testMatchesPatternFR_functiongetReportRegexDatePattern_Success() {
		TestContext.INSTANCE.setUserCulture("fr");
		String dateTime = "11/01/2016 4:08 AM CET sqapicsup@picarro.com TC1249 Automation Note 811835";
		String dateFormat = RegexUtility.getReportRegexDatePattern(true);
		List<String> groups = RegexUtility.getMatchingGroups(dateTime, dateFormat);
		Assert.assertTrue(groups.get(0).trim().equals("11/01/2016 4:08 AM CET"));
	}

	private static void test_functionGetStringInBetween_Success() {
		PDFUtility pdfUtility = new PDFUtility();
		try {
			String path = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data\\test-data\\pdfutility-tests\\MultipleSurveys.pdf";
			String inputText = pdfUtility.extractPDFText(path);
			String actual = RegexUtility.getStringInBetween(inputText, "la\r\nss", "Layers", true, false);
			Log.info(actual);
		} catch (IOException e) {
			Log.error(e.toString());

		}
	}

	private static void testgetNextLineAfterPattern_Success() {
		PDFUtility pdfUtility = new PDFUtility();
		try {
			String path = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data\\test-data\\pdfutility-tests\\MultipleSurveys.pdf";
			String inputText = pdfUtility.extractPDFText(path);
			String nextLine = RegexUtility.getNextLineAfterPattern(inputText, "Coverage Values");
			Assert.assertTrue(nextLine.equals("20%Total Linear Asset Coverage Percent Coverage Report Area 9%"));
		} catch (IOException e) {
			Log.error(e.toString());

		}

	}
}

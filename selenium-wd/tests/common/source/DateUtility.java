package common.source;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class DateUtility {

	public static Date DATE_MINVALUE = new Date(0);
	
	/**
	 * Compares the first time string with second time string and returns if the first time string is greater than the second.
	 * 
	 * @param timeString1
	 *            - Time string in 00:00:00 format or 00:00:00 PST format.
	 * @param timeString2
	 *            - Time string in 00:00:00 format or 00:00:00 PST format.
	 * @return - returns if first time string is greater than second time string.
	 */
	public static boolean isFirstTimeGreater(String timeString1, String timeString2) {
		boolean isGreater = false;
		List<String> timeParts1 = RegexUtility.split(timeString1, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
		List<String> timeParts2 = RegexUtility.split(timeString2, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
		if (timeParts1 == null || timeParts1.size() != 3) {
			throw new IllegalArgumentException("Invalid argument - 'timeString1'. Pass Time string in 00:00:00 format.");
		}
		if (timeParts2 == null || timeParts2.size() != 3) {
			throw new IllegalArgumentException("Invalid argument - 'timeString2'. Pass Time string in 00:00:00 format.");
		}

		Integer hr1 = Integer.valueOf(timeParts1.get(0));
		Integer hr2 = Integer.valueOf(timeParts2.get(0));
		Integer min1 = Integer.valueOf(timeParts1.get(1));
		Integer min2 = Integer.valueOf(timeParts2.get(1));
		Integer sec1 = Integer.valueOf(timeParts1.get(2));
		Integer sec2 = Integer.valueOf(timeParts2.get(2));
		if (hr1 > hr2) {
			isGreater = true;
		} else if (hr1 == hr2) {
			if (min1 > min2) {
				isGreater = true;
			} else if (min1 == min2) {
				if (sec1 > sec2) {
					isGreater = true;
				}
			}
		}
		return isGreater;
	}

	/**
	 * Checks if the time displayed in a WebElement is ticking forward. This method expects the time displayed in the WebElement to be in one of the 3 formats: 1. Time: 2:00:28 PM PST 2. Elapsed:
	 * 2:00:28 PM PST 3. Remaining: 2:00:28 PM PST
	 * 
	 * @throws InterruptedException
	 */
	public static boolean isTimeTickingForward(WebElement element) throws InterruptedException {
		return isTimeTickingForwardBackward(element, true);
	}

	/**
	 * Checks if the time displayed in a WebElement is ticking backward. This method expects the time displayed in the WebElement to be in one of the 3 formats: 1. Time: 2:00:28 PM PST 2. Elapsed:
	 * 2:00:28 PM PST 3. Remaining: 2:00:28 PM PST
	 * 
	 * @throws InterruptedException
	 */
	public static boolean isTimeTickingBackward(WebElement element) throws InterruptedException {
		return isTimeTickingForwardBackward(element, false);
	}

	private static boolean isTimeTickingForwardBackward(WebElement element, boolean checkForwardTick) throws InterruptedException {
		final String TIME_PREFIX = "Time: ";
		final String ELAPSED_TIME_PREFIX = "Elapsed: ";
		final String REMAINING_TIME_PREFIX = "Remaining: ";
		final int ONE_SECOND_IN_MILLISEC = 1000;

		int iterations = 3;
		String prevTimeString = "";
		String currTimeString = "";
		do {
			currTimeString = element.getText();
			currTimeString = currTimeString.replace(TIME_PREFIX, "");
			currTimeString = currTimeString.replace(ELAPSED_TIME_PREFIX, "");
			currTimeString = currTimeString.replace(REMAINING_TIME_PREFIX, "");

			List<String> timeParts = RegexUtility.split(currTimeString, RegexUtility.SPACE_SPLIT_REGEX_PATTERN);
			if (timeParts == null) {
				throw new IllegalArgumentException("Element time string should be in '00:00:00' or '00:00:00 PST' format.");
			}
			currTimeString = timeParts.get(0);

			if (prevTimeString != "") {
				if (checkForwardTick) {
					if (!isFirstTimeGreater(currTimeString, prevTimeString)) {
						return false;
					}
				} else {
					if (isFirstTimeGreater(currTimeString, prevTimeString)) {
						return false;
					}
				}
			}
			prevTimeString = currTimeString;
			Thread.sleep(ONE_SECOND_IN_MILLISEC);

		} while (iterations-- > 0);

		return true;
	}

	/**
	 * Parses and returns the Date object for the specified Date and Date format strings.
	 * 
	 * @param dateString
	 *            - Date represented as a string.
	 * @param dateFormatString
	 *            - Format represented as a string.
	 * @return - Date object for the specified Date and Date format strings
	 * @throws ParseException
	 */
	public static Date getDate(String dateString, String dateFormatString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		return dateFormat.parse(dateString);
	}
	


	/**
	 * Returns the current date for the current zone for which test is running.
	 * 
	 */
	public static Date getCurrentDate() {
		return getCalendarForCurrentZone().getTime();
	}

	/**
	 * Returns the current zone Calendar.
	 * 
	 * @return
	 */
	public static Calendar getCalendarForCurrentZone() {
		Calendar calendar = null;
		String culture = TestContext.INSTANCE.getUserCulture();
		if (culture.equals("en-US")) {
			calendar = Calendar.getInstance(TimeZone.getTimeZone("US/Pacific"));
		} else if (culture.equals("fr")) {
			calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		} else if (culture.equals("zh-Hans")) {
			calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
		}

		Log.info("Returning Calendar for current zone" + calendar.toString());
		return calendar;
	}

	/**
	 * This function takes an input datetime string String and compare the format is correct with the given locale long datetime format
	 * 
	 * @param inputDateTime
	 *            - given date time in String, reports- whether the format check is as per long date format.
	 * @return whether the String is a match for the locale format
	 */
	public static boolean compareLongDateTimeFormat(String inputDateTime) {
		TemporalAccessor inputDate;
		Locale locale = Locale.forLanguageTag(getLanguageTag());
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getLongDateFormat(), locale);
			inputDate = formatter.parse(inputDateTime.trim());
			if (inputDate != null) {
				return true;
			}
		} catch (DateTimeParseException e) {
			Log.info(e.toString());
		}
		return false;
	}
	
	/**
	 * This function takes a DateTime formatted String and convert it to DateTime object
	 * 
	 * @param inputDateTime
	 *            - given date time in String, reports- whether the format check is as per long date format.
	 * @return DateTime object
	 */
	public static TemporalAccessor stringToDateTime(String inputDateTime) {
		TemporalAccessor inputDate=null;
		Locale locale = Locale.forLanguageTag(getLanguageTag());
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getLongDateFormat(), locale);
			inputDate = formatter.parse(inputDateTime.trim());
		} catch (DateTimeParseException e) {
			Log.info(e.toString());
		}
		return inputDate;
		
	}


	/**
	 * This function takes a Date/Time format in String and compare the format is correct with the given locale format
	 * 
	 * @param inputDateTime
	 *            - given date time in String, reports- whether the format check is for the reports
	 * @return whether the String is a match for the locale format
	 */
	public static boolean compareDateTimeFormat(String inputDateTime, boolean useTimeZone) {
		TemporalAccessor inputDate;
		Locale locale = Locale.forLanguageTag(getLanguageTag());
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getDateFormat(useTimeZone), locale);
			inputDate = formatter.parse(inputDateTime.trim());
			if (inputDate != null) {
				return true;
			}
		} catch (DateTimeParseException e) {
			Log.info(e.toString());
		}
		return false;
	}
	
	/**
	 * This function takes two strings in Date/Time format and compare them to be equal
	 * 
	 * @param inputDateTime1,
	 *            inputDateTime2 and whether the date check is for the reports
	 * @return whether dates are a match or not
	 */
	public static boolean compareDateTimes(String inputDateTime1, String inputDateTime2, boolean useTimeZone) {
		Locale locale = Locale.forLanguageTag(getLanguageTag());
		try {
			TemporalAccessor date1 = DateTimeFormatter.ofPattern(getDateFormat(useTimeZone), locale).parse(inputDateTime1.trim());
			TemporalAccessor date2 = DateTimeFormatter.ofPattern(getDateFormat(useTimeZone), locale).parse(inputDateTime2.trim());
			String date1String = date1.toString();
			String date2String = date2.toString();
			if (date1String.equals(date2String)) {
				return true;
			}

		} catch (Exception e) {
			Log.info(e.toString());
		}

		return false;
	}

	/**
	 * This function takes two strings in Date/Time format and return the duration
	 * 
	 * @param inputDateTime1,
	 *            inputDateTime2 and whether the date check is for the reports
	 * @return difference in minutes
	 */
	public static long getDuration(String inputDateTime1, String inputDateTime2, boolean useTimeZone) {
		Locale locale = Locale.forLanguageTag(getLanguageTag());
		long diffInMinutes = 0;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getDateFormat(useTimeZone), locale);
			LocalDateTime startDateTime = LocalDateTime.parse(inputDateTime1, formatter);
			LocalDateTime endDateTime = LocalDateTime.parse(inputDateTime2, formatter);
			diffInMinutes = Duration.between(startDateTime, endDateTime).toMinutes();

		} catch (Exception e) {
			Log.info(e.toString());
		}

		return diffInMinutes;
	}

	/**
	 * This function takes two strings in Date format and compare them to be equal
	 * 
	 * @param inputDateTime1,
	 *            inputDateTime2 and whether the date check is for the reports
	 * @return whether dates are a match or not
	 */
	public static boolean compareDates(String inputDateTime1, String inputDateTime2, boolean useTimeZone) {
		Locale locale = Locale.forLanguageTag(getLanguageTag());
		try {
			TemporalAccessor date1 = DateTimeFormatter.ofPattern(getDateFormat(useTimeZone), locale).parse(inputDateTime1.trim());
			TemporalAccessor date2 = DateTimeFormatter.ofPattern(getDateFormat(useTimeZone), locale).parse(inputDateTime2.trim());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
			Format format = formatter.toFormat();
			String date1Part = format.format(date1);
			String date2Part = format.format(date2);
			if (date1Part.equals(date2Part)) {
				return true;
			}

		} catch (Exception e) {
		}

		return false;
	}

	/**
	 * This methods looks at the culture of the user and determines the long date format according to Cultures supported right now: English, French, Chinese
	 * 
	 * @return date format for the user locale
	 */

	public static String getLongDateFormat() {
		String culture = TestContext.INSTANCE.getUserCulture();
		String dateFormat = null;
		if (culture.equals("en-US")) {
			dateFormat = "M/d/yyyy h[h]:mm:ss a zzz";
		}
		if (culture.equals("fr")) {
			dateFormat = "d/M/yyyy H[H]:mm:ss zzz";
		}
		if (culture.equals("zh-Hans")) {
			dateFormat = "YYYY/M/d H[H]:mm:ss zzz";
		}
		return dateFormat;

	}
	
	/**
	 * This methods looks at the culture of the user and determines the short date format according to Cultures supported right now: English, French, Chinese
	 * 
	 * @return date format for the user locale
	 */

	public static String getShortSimpleDateFormat() {		
		String culture = TestContext.INSTANCE.getUserCulture();
		String dateFormat = null;
		if (culture.equals("en-US")) {
			dateFormat = "MM/dd/yyyy hh:mm:ss a";
		}
		if (culture.equals("fr")) {
			dateFormat = "dd/MM/yyyy HH:mm:ss";
		}
		if (culture.equals("zh-Hans")) {
			dateFormat = "YYYY/MM/dd HH:mm:ss";
		}
		return dateFormat;
	}

	/**
	 * This methods looks at the culture of the user and determines the date format accordingly Cultures supported right now: English, French, Chinese
	 * 
	 * @param whether
	 *            the date check is for a report or not (Note: Pages have the same Date format without TimeZone
	 * @return date format for the user locale
	 */

	public static String getDateFormat(boolean useTimeZone) {
		String culture = TestContext.INSTANCE.getUserCulture();
		String dateFormat = null;
		if (useTimeZone) {
			if (culture.equals("en-US")) {
				dateFormat = "M[M]/d[d]/yyyy h[h]:mm a zzz";
			}
			if (culture.equals("fr")) {
				dateFormat = "d[d]/M[M]/yyyy H[H]:mm zzz";
			}
			if (culture.equals("zh-Hans")) {
				dateFormat = "YYYY/M[M]/d[d] H[H]:mm zzz";
			}
		} else {
			if (culture.equals("en-US")) {
				dateFormat = "M[M]/d[d]/yyyy h[h]:mm a";
			}
			if (culture.equals("fr")) {
				dateFormat = "d[d]/M[M]/yyyy H[H]:mm";
			}
			if (culture.equals("zh-Hans")) {
				dateFormat = "YYYY/M[M]/d[d] H[H]:mm";
			}
		}
		return dateFormat;

	}

	/**
	 * This methods looks at the culture of the user and returns the Java specific Locale- language tag
	 * 
	 * @return locale language tag
	 */

	public static String getLanguageTag() {
		String culture = TestContext.INSTANCE.getUserCulture();
		String languageTag = null;
		if (culture.equals("en-US")) {
			languageTag = "en-US";
		} else if (culture.equals("fr")) {
			languageTag = "fr-FR";
		} else if (culture.equals("zh-Hans")) {
			languageTag = "zh-CN";
		}

		return languageTag;

	}

	public static boolean verifyDateMatchesToday(String dateString) throws ParseException {
		Date date = new Date(dateString);
		Date currentDate = getCurrentDate();
		return compareDatePart(date, currentDate);
	}

	/**
	 * Compares equality of only the date part of the Date objects.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDatePart(Date d1, Date d2) {
		if (d1.getYear() != d2.getYear()) {
			return false; 
		}
		if (d1.getMonth() != d2.getMonth()) {
			return false;
		}
		if (d1.getDate() != d2.getDate()) {
			return false;
		}
		return true;
	}

	/**
	 * Executes the unit tests for this class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Log.info(DateUtility.getCurrentDate().toGMTString());
		Log.info(DateUtility.getCurrentDate().toLocaleString());
		Log.info(DateUtility.getCurrentDate().toString());
		
		// ** Unit tests for isFirstTimeGreater() method **/
		String timeString1 = "00:00:00";
		String timeString2 = "00:00:00";
		Log.info(String.format("Comparing Time1 - '%s' with Time2 - '%s'", timeString1, timeString2));
		boolean firstTimeGreater = DateUtility.isFirstTimeGreater(timeString1, timeString2);
		Log.info(String.format("Expected = %b, Actual = %b", false, firstTimeGreater));
		Assert.assertTrue(!firstTimeGreater);
		timeString1 = "01:00:00";
		timeString2 = "00:55:50";
		Log.info(String.format("Comparing Time1 - '%s' with Time2 - '%s'", timeString1, timeString2));
		firstTimeGreater = DateUtility.isFirstTimeGreater(timeString1, timeString2);
		Log.info(String.format("Expected = %b, Actual = %b", true, firstTimeGreater));
		Assert.assertTrue(firstTimeGreater);
		timeString1 = "00:20:00";
		timeString2 = "00:20:20";
		Log.info(String.format("Comparing Time1 - '%s' with Time2 - '%s'", timeString1, timeString2));
		firstTimeGreater = DateUtility.isFirstTimeGreater(timeString1, timeString2);
		Log.info(String.format("Expected = %b, Actual = %b", false, firstTimeGreater));
		Assert.assertTrue(!firstTimeGreater);
		timeString1 = "07:59:01";
		timeString2 = "07:59:00";
		Log.info(String.format("Comparing Time1 - '%s' with Time2 - '%s'", timeString1, timeString2));
		firstTimeGreater = DateUtility.isFirstTimeGreater(timeString1, timeString2);
		Log.info(String.format("Expected = %b, Actual = %b", true, firstTimeGreater));
		Assert.assertTrue(firstTimeGreater);
		timeString1 = "23:59:58";
		timeString2 = "23:59:57";
		Log.info(String.format("Comparing Time1 - '%s' with Time2 - '%s'", timeString1, timeString2));
		firstTimeGreater = DateUtility.isFirstTimeGreater(timeString1, timeString2);
		Log.info(String.format("Expected = %b, Actual = %b", true, firstTimeGreater));
		Assert.assertTrue(firstTimeGreater);

		// ** Unit tests for compareDateTimeFormat(), compareDateTimes() and compareDates() methods **/

		DateUtility date = new DateUtility();
		String result;
		// Unit tests - compareLongDateTimeFormat(String inputDateTime)
		Log.info("Executing Unit tests for compareLongDateTimeFormat(String inputDateTime)");
		TestContext.INSTANCE.setUserCulture("en-US");
		Log.info(result = (date.compareLongDateTimeFormat("3/9/2016 6:04:41 PM CST")) ? "PASS" : "FAIL");
		Log.info(result = (date.compareLongDateTimeFormat("24/01/2015 18:42:01 PM CST")) ? "FAIL" : "PASS");
		TestContext.INSTANCE.setUserCulture("fr");
		Log.info(result = (date.compareLongDateTimeFormat("12/14/2015 8:42:01 CET")) ? "FAIL" : "PASS");
		Log.info(result = (date.compareLongDateTimeFormat("24/01/2015 18:42:01 CET")) ? "PASS" : "FAIL");
		TestContext.INSTANCE.setUserCulture("zh-Hans");
		Log.info(result = (date.compareLongDateTimeFormat("2015/01/12 18:42:22 CST")) ? "PASS" : "FAIL");
		Log.info(result = (date.compareLongDateTimeFormat("24/01/2015 18:42:22 CST")) ? "FAIL" : "PASS");

		// Unit tests - compareDateTimeFormat(String inputDateTime, boolean reports)
		Log.info("Executing Unit tests for compareDateTimeFormat(String inputDateTime, boolean reports)");
		TestContext.INSTANCE.setUserCulture("en-US");
		Log.info(result = (date.compareDateTimeFormat("12/14/2015 8:42 PM PST", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 CST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 PM CST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("01/24/2015 08:42 PM CST", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 PM", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/15 18:42 PM CST", true)) ? "FAIL" : "PASS");
		// US -en Page format tests
		TestContext.INSTANCE.setUserCulture("en-US");
		Log.info(result = (date.compareDateTimeFormat("12/14/2015 8:42 PM PST", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 PM", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("01/24/2015 08:42 PM", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 PM", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/15 18:42 PM CST", false)) ? "FAIL" : "PASS");
		// FR Report format tests
		TestContext.INSTANCE.setUserCulture("fr");
		Log.info(result = (date.compareDateTimeFormat("12/14/2015 8:42 CET", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("30/06/2009 18:42 CET", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("2015/01/12 18:42 CET", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 CET", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("24/01/15 18:42 PM CST", true)) ? "FAIL" : "PASS");
		// FR Page format tests
		TestContext.INSTANCE.setUserCulture("fr");
		Log.info(result = (date.compareDateTimeFormat("12/24/2015 8:42 ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 8:42 ", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("2015/01/12 18:42 ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 16:42 ", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 PM", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/15 18:42 PM ", false)) ? "FAIL" : "PASS");
		// China Report format tests
		TestContext.INSTANCE.setUserCulture("zh-Hans");
		Log.info(result = (date.compareDateTimeFormat("2015/12/14 8:42 PST ", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("2015/01/12 18:42 CST", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 CST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("2015/12/14 8:42 ", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("2015/12/14 8:42 PM CST", true)) ? "FAIL" : "PASS");
		// China Page format tests
		TestContext.INSTANCE.setUserCulture("zh-Hans");
		Log.info(result = (date.compareDateTimeFormat("2015/12/14 8:42 ", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("2015/01/12 18:42 ", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("12/14/2015 8:42 ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("2015/12/14 8:42 PM", false)) ? "FAIL" : "PASS");

		// Unit tests - compareDateTimes(String inputDateTime1, Date inputDateTime2, boolean reports)
		// US -en
		TestContext.INSTANCE.setUserCulture("en-US");
		Log.info(result = (date.compareDateTimes("12/14/2015 8:42 PM PST", "12/14/2015 8:42 PM PST", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimes("12/14/2015 8:40 PM PST", "12/14/2015 8:42 PM PST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimes("12/14/2015 8:42 PM PST", "12/10/2015 8:42 PM PST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimes("12/14/2015 8:42 PM ", "12/14/2015 8:42 PM ", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimes("12/14/2015 8:40 PM ", "12/14/2015 8:42 PM ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimes("12/14/2015 8:42 PM ", "12/10/2015 8:42 PM ", false)) ? "FAIL" : "PASS");
		// FR
		TestContext.INSTANCE.setUserCulture("fr");
		Log.info(result = (date.compareDateTimes("24/01/2015 18:42 CST", "24/01/2015 18:42 CST", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimes("24/01/2015 18:42 CST", "24/01/2015 18:40 CST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimes("24/01/2015 18:42 CST", "20/01/2015 18:42 CST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimes("24/01/2015 18:42 ", "24/01/2015 18:42", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimes("24/01/2015 18:42 ", "24/01/2015 18:40 ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimes("24/01/2015 18:42 ", "20/01/2015 18:42 ", false)) ? "FAIL" : "PASS");
		// China
		TestContext.INSTANCE.setUserCulture("zh-Hans");
		Log.info(result = (date.compareDateTimes("2015/01/12 18:42 CST", "2015/01/12 18:42 CST", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimes("2015/01/12 18:40 CST", "2015/01/12 18:42 CST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimes("2015/01/12 18:42 CST", "2014/01/12 18:42 CST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimes("2015/01/12 18:42 ", "2015/01/12 18:42 ", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimes("2015/01/12 18:40 ", "2015/01/12 18:42 ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimes("2015/01/12 18:42 ", "2014/01/12 18:42 ", false)) ? "FAIL" : "PASS");

		// Unit tests - compareDates(String inputDateTime1, String inputDateTime2, boolean reports)
		// US -en
		TestContext.INSTANCE.setUserCulture("en-US");
		Log.info(result = (date.compareDates("12/14/2015 8:42 PM PST", "12/14/2015 8:42 PM PST", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDates("12/14/2015 8:40 PM PST", "12/14/2016 8:42 PM PST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDates("12/14/2015 8:42 PM PST", "12/15/2015 8:42 PM PST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDates("12/14/2015 8:42 PM ", "12/14/2015 8:42 PM ", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDates("12/14/2015 8:40 PM ", "12/14/2016 8:42 PM ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDates("12/14/2015 8:42 PM ", "12/15/2015 8:42 PM ", false)) ? "FAIL" : "PASS");
		// FR
		TestContext.INSTANCE.setUserCulture("fr");
		Log.info(result = (date.compareDates("24/01/2015 18:42 CST", "24/01/2015 18:42 CST", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDates("24/01/2015 18:42 CST", "24/02/2015 18:42 CST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDates("24/01/2015 18:42 CST", "24/01/2016 18:42 CST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDates("24/01/2015 18:42 ", "24/01/2015 18:42 ", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDates("24/01/2015 18:42 ", "24/02/2015 18:42 ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDates("24/01/2015 18:42 ", "24/01/2016 18:42 ", false)) ? "FAIL" : "PASS");
		// China
		TestContext.INSTANCE.setUserCulture("zh-Hans");
		Log.info(result = (date.compareDates("2015/01/12 18:42 CST", "2015/01/12 18:42 CST", true)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDates("2015/01/12 18:40 CST", "2015/02/12 18:42 CST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDates("2015/01/12 18:42 CST", "2014/01/12 18:42 CST", true)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDates("2015/01/12 18:42 ", "2015/01/12 18:42 ", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDates("2015/01/12 18:40 ", "2015/02/12 18:42 ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDates("2015/01/12 18:42 ", "2014/01/12 18:42 ", false)) ? "FAIL" : "PASS");

		TestContext.INSTANCE.setUserCulture("en-US");
		Log.info(result = (date.getDuration("3/7/2016 1:55 PM PST", "3/7/2016 2:29 PM PST", true)) == 34 ? "PASS" : "FAIL");
		Log.info(result = (date.getDuration("3/7/2016 3:50 PM PST", "3/7/2016 4:02 PM PST", true)) == 12 ? "PASS" : "FAIL");

	}
}
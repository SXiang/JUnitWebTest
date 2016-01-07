package common.source;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;


public class DateUtility {
	
	/**
	 * Parses and returns the Date object for the specified Date and Date format strings.
	 * @param dateString - Date represented as a string.
	 * @param dateFormatString - Format represented as a string.
	 * @return - Date object for the specified Date and Date format strings
	 * @throws ParseException
	 */
	public static Date getDate(String dateString, String dateFormatString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		return dateFormat.parse(dateString);
	}
	
	/**
	 * This function takes a Date/Time format in String and compare the format is correct with the given locale format
	 * 
	 * @param inputDateTime
	 *            - given date time in String, reports- whether the format check is for the reports
	 * @return whether the String is a match for the locale format
	 */
	public boolean compareDateTimeFormat (String inputDateTime, boolean useTimeZone) {
		TemporalAccessor inputDate;
		Locale locale = Locale.forLanguageTag(getLanguageTag());
		try{
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern(getDateFormat(useTimeZone),locale);
		inputDate = formatter.parse(inputDateTime.trim());
		if (inputDate != null) {
			return true;
			}
		}
		catch (DateTimeParseException e){}
		return false;
	}

	/**
	 * This function takes two strings in Date/Time format and compare them to be equal
	 * 
	 * @param inputDateTime1, inputDateTime2 and whether the date check is for the reports
	 * @return whether dates are a match or not
	 */
	public boolean compareDateTimes(String inputDateTime1, String inputDateTime2, boolean useTimeZone) {
		Locale locale = Locale.forLanguageTag(getLanguageTag());
		try {
			TemporalAccessor date1 = DateTimeFormatter.ofPattern(getDateFormat(useTimeZone),locale).parse(inputDateTime1.trim());
			TemporalAccessor date2 = DateTimeFormatter.ofPattern(getDateFormat(useTimeZone),locale).parse(inputDateTime2.trim());
			String date1String=date1.toString();
			String date2String=date2.toString();
			if(date1String.equals(date2String))
			{		
				return true;
			}

		} catch (Exception e) {}

		return false;
	}
	
	/**
	 * This function takes two strings in Date format and compare them to be equal
	 * 
	 * @param inputDateTime1, inputDateTime2 and whether the date check is for the reports
	 * @return whether dates are a match or not
	 */
	public boolean compareDates(String inputDateTime1, String inputDateTime2, boolean useTimeZone) {
		Locale locale = Locale.forLanguageTag(getLanguageTag());
		try {
			TemporalAccessor date1 = DateTimeFormatter.ofPattern(getDateFormat(useTimeZone),locale).parse(inputDateTime1.trim());
			TemporalAccessor date2 = DateTimeFormatter.ofPattern(getDateFormat(useTimeZone),locale).parse(inputDateTime2.trim());
			DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("MM/dd/YYYY");  
			Format format = formatter.toFormat();  
			String date1Part = format.format(date1);  
			String date2Part = format.format(date2);
			if(date1Part.equals(date2Part))								
			{		
				return true;
			}

		} catch (Exception e) {	}

		return false;
	}
	
	/**
	 * This methods looks at the culture of the user and determines the date format accordingly Cultures supported right now: English, French, Chinese
	 * 
	 * @param whether the date check is for a report or not (Note: Pages have the same Date format without TimeZone
	 * @return date format for the user locale
	 */

	public String getDateFormat(boolean useTimeZone) {
		String culture=TestContext.INSTANCE.getUserCulture();
		String dateFormat = null;
		if (useTimeZone) {
			if (culture.equals("en-US")) {
				dateFormat = "MM/dd/yyyy h[h]:mm a zzz";
			}
			if (culture.equals("fr")) {
				dateFormat = "dd/MM/yyyy H[H]:mm zzz";
			}
			if (culture.equals("zh-Hans")) {
				dateFormat = "YYYY/MM/dd H[H]:mm zzz";
			}
		} else {
			if (culture.equals("en-US")) {
				dateFormat = "MM/dd/yyyy h[h]:mm a";
			}
			if (culture.equals("fr")) {
				dateFormat = "dd/MM/yyyy H[H]:mm";
			}
			if (culture.equals("zh-Hans")) {
				dateFormat = "YYYY/MM/dd H[H]:mm";
			}
		}
		return dateFormat;
		
	}
	
	/**
	 * This methods looks at the culture of the user and returns the Java specific Locale- language tag
	 * 
	 *  @return locale language tag
	 */

	public String getLanguageTag() {
		String culture=TestContext.INSTANCE.getUserCulture();
		String languageTag=null;
		if(culture.equals("en-US")){
			languageTag= "en-US";
		}
		if(culture.equals("fr")){
			languageTag= "fr-FR";
		}
		if(culture.equals("zh-Hans")){
			languageTag= "zh-CN";
		}
		
		return languageTag;
		
	}


	/**
	 * Executes the unit tests for this class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DateUtility date = new DateUtility();
		String result;
		//Unit tests -  compareDateTimeFormat(String inputDateTime, boolean reports)
		TestContext.INSTANCE.setUserCulture("en-US");
		// US -en  Report format tests	
		Log.info(result=(date.compareDateTimeFormat( "12/14/2015 8:42 PM PST", true))?"PASS":"FAIL"); 
		Log.info(result=(date.compareDateTimeFormat( "24/01/2015 18:42 CST",  true))?"FAIL":"PASS"); 
		Log.info(result=(date.compareDateTimeFormat( "24/01/2015 18:42 PM CST", true))?"FAIL":"PASS"); 
		Log.info(result=(date.compareDateTimeFormat( "01/24/2015 08:42 PM CST", true))?"PASS":"FAIL"); 
		Log.info(result=(date.compareDateTimeFormat( "24/01/2015 18:42 PM", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimeFormat( "24/01/15 18:42 PM CST", true))?"FAIL":"PASS");
		// US -en  Page format tests	
		TestContext.INSTANCE.setUserCulture("en-US");
		Log.info(result = (date.compareDateTimeFormat("12/14/2015 8:42 PM PST", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 ", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 PM", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("01/24/2015 08:42 PM", false)) ? "PASS" : "FAIL");
		Log.info(result = (date.compareDateTimeFormat("24/01/2015 18:42 PM", false)) ? "FAIL" : "PASS");
		Log.info(result = (date.compareDateTimeFormat("24/01/15 18:42 PM CST", false)) ? "FAIL" : "PASS");
		// FR  Report format tests		
		TestContext.INSTANCE.setUserCulture("fr");
		Log.info(result=(date.compareDateTimeFormat( "12/14/2015 8:42 CET", true))?"FAIL":"PASS"); 
		Log.info(result=(date.compareDateTimeFormat( "30/06/2009 18:42 CET", true))?"PASS":"FAIL"); 
		Log.info(result=(date.compareDateTimeFormat( "2015/01/12 18:42 CET", true))?"FAIL":"PASS"); 
		Log.info(result=(date.compareDateTimeFormat( "24/01/2015 18:42 CET", true))?"PASS":"FAIL"); 
		Log.info(result=(date.compareDateTimeFormat( "24/01/15 18:42 PM CST", true))?"FAIL":"PASS");
		// FR  Page format tests	
		TestContext.INSTANCE.setUserCulture("fr");
		Log.info(result=(date.compareDateTimeFormat( "12/24/2015 8:42 ", false))?"FAIL":"PASS"); 
		Log.info(result=(date.compareDateTimeFormat( "24/01/2015 8:42 ",  false))?"PASS":"FAIL"); 
		Log.info(result=(date.compareDateTimeFormat( "2015/01/12 18:42 ", false))?"FAIL":"PASS"); 
		Log.info(result=(date.compareDateTimeFormat( "24/01/2015 16:42 ", false))?"PASS":"FAIL"); 
		Log.info(result=(date.compareDateTimeFormat( "24/01/2015 18:42 PM", false))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimeFormat( "24/01/15 18:42 PM ", false))?"FAIL":"PASS");
		// China  Report format tests	
		TestContext.INSTANCE.setUserCulture("zh-Hans");
		Log.info(result=(date.compareDateTimeFormat( "2015/12/14 8:42 PST ", true))?"PASS":"FAIL"); 
		Log.info(result=(date.compareDateTimeFormat( "2015/01/12 18:42 CST", true))?"PASS":"FAIL"); 
		Log.info(result=(date.compareDateTimeFormat( "24/01/2015 18:42 CST", true))?"FAIL":"PASS"); 
		Log.info(result=(date.compareDateTimeFormat( "2015/12/14 8:42 ", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimeFormat( "2015/12/14 8:42 PM CST", true))?"FAIL":"PASS");
		//China  Page format tests	
		TestContext.INSTANCE.setUserCulture("zh-Hans");
		Log.info(result=(date.compareDateTimeFormat( "2015/12/14 8:42 ", false))?"PASS":"FAIL"); 
		Log.info(result=(date.compareDateTimeFormat( "2015/01/12 18:42 ", false))?"PASS":"FAIL"); 
		Log.info(result=(date.compareDateTimeFormat( "24/01/2015 18:42 ", false))?"FAIL":"PASS"); 
		Log.info(result=(date.compareDateTimeFormat( "12/14/2015 8:42 ", false))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimeFormat( "2015/12/14 8:42 PM", false))?"FAIL":"PASS");
		
		//Unit tests - compareDateTimes(String inputDateTime1, Date inputDateTime2, boolean reports)
		// US -en
		TestContext.INSTANCE.setUserCulture("en-US");
		Log.info(result=(date.compareDateTimes( "12/14/2015 8:42 PM PST","12/14/2015 8:42 PM PST", true))?"PASS":"FAIL");
		Log.info(result=(date.compareDateTimes( "12/14/2015 8:40 PM PST","12/14/2015 8:42 PM PST", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimes( "12/14/2015 8:42 PM PST","12/10/2015 8:42 PM PST", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimes( "12/14/2015 8:42 PM ","12/14/2015 8:42 PM ", false))?"PASS":"FAIL");
		Log.info(result=(date.compareDateTimes( "12/14/2015 8:40 PM ","12/14/2015 8:42 PM ", false))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimes( "12/14/2015 8:42 PM ","12/10/2015 8:42 PM ", false))?"FAIL":"PASS");
		// FR
		TestContext.INSTANCE.setUserCulture("fr");
		Log.info(result=(date.compareDateTimes( "24/01/2015 18:42 CST","24/01/2015 18:42 CST", true))?"PASS":"FAIL");
		Log.info(result=(date.compareDateTimes( "24/01/2015 18:42 CST","24/01/2015 18:40 CST", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimes( "24/01/2015 18:42 CST","20/01/2015 18:42 CST", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimes( "24/01/2015 18:42 ","24/01/2015 18:42", false))?"PASS":"FAIL");
		Log.info(result=(date.compareDateTimes( "24/01/2015 18:42 ","24/01/2015 18:40 ", false))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimes( "24/01/2015 18:42 ","20/01/2015 18:42 ", false))?"FAIL":"PASS");
		// China	
		TestContext.INSTANCE.setUserCulture("zh-Hans");
		Log.info(result=(date.compareDateTimes( "2015/01/12 18:42 CST","2015/01/12 18:42 CST", true))?"PASS":"FAIL");
		Log.info(result=(date.compareDateTimes( "2015/01/12 18:40 CST","2015/01/12 18:42 CST", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimes( "2015/01/12 18:42 CST","2014/01/12 18:42 CST", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimes( "2015/01/12 18:42 ","2015/01/12 18:42 ", false))?"PASS":"FAIL");
		Log.info(result=(date.compareDateTimes( "2015/01/12 18:40 ","2015/01/12 18:42 ", false))?"FAIL":"PASS");
		Log.info(result=(date.compareDateTimes( "2015/01/12 18:42 ","2014/01/12 18:42 ", false))?"FAIL":"PASS");
		
		//Unit tests - compareDates(String inputDateTime1, String inputDateTime2, boolean reports)
		// US -en		
		TestContext.INSTANCE.setUserCulture("en-US");
		Log.info(result=(date.compareDates( "12/14/2015 8:42 PM PST","12/14/2015 8:42 PM PST", true))?"PASS":"FAIL");
		Log.info(result=(date.compareDates( "12/14/2015 8:40 PM PST","12/14/2016 8:42 PM PST", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDates( "12/14/2015 8:42 PM PST","12/15/2015 8:42 PM PST", true))?"FAIL":"PASS");		
		Log.info(result=(date.compareDates( "12/14/2015 8:42 PM ","12/14/2015 8:42 PM ", false))?"PASS":"FAIL");
		Log.info(result=(date.compareDates( "12/14/2015 8:40 PM ","12/14/2016 8:42 PM ", false))?"FAIL":"PASS");
		Log.info(result=(date.compareDates( "12/14/2015 8:42 PM ","12/15/2015 8:42 PM ", false))?"FAIL":"PASS");
		//FR
		TestContext.INSTANCE.setUserCulture("fr");
		Log.info(result=(date.compareDates( "24/01/2015 18:42 CST","24/01/2015 18:42 CST", true))?"PASS":"FAIL");
		Log.info(result=(date.compareDates( "24/01/2015 18:42 CST","24/02/2015 18:42 CST", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDates( "24/01/2015 18:42 CST","24/01/2016 18:42 CST", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDates( "24/01/2015 18:42 ","24/01/2015 18:42 ", false))?"PASS":"FAIL");
		Log.info(result=(date.compareDates( "24/01/2015 18:42 ","24/02/2015 18:42 ", false))?"FAIL":"PASS");
		Log.info(result=(date.compareDates( "24/01/2015 18:42 ","24/01/2016 18:42 ", false))?"FAIL":"PASS");
		// China
		TestContext.INSTANCE.setUserCulture("zh-Hans");
		Log.info(result=(date.compareDates( "2015/01/12 18:42 CST","2015/01/12 18:42 CST", true))?"PASS":"FAIL");
		Log.info(result=(date.compareDates( "2015/01/12 18:40 CST","2015/02/12 18:42 CST", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDates( "2015/01/12 18:42 CST","2014/01/12 18:42 CST", true))?"FAIL":"PASS");
		Log.info(result=(date.compareDates( "2015/01/12 18:42 ","2015/01/12 18:42 ", false))?"PASS":"FAIL");
		Log.info(result=(date.compareDates( "2015/01/12 18:40 ","2015/02/12 18:42 ", false))?"FAIL":"PASS");
		Log.info(result=(date.compareDates( "2015/01/12 18:42 ","2014/01/12 18:42 ", false))?"FAIL":"PASS");



	}
}
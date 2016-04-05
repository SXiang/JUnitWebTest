package common.source;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtility {
	
	public static String getNumberStringForCurrentLocale(int num) {
		String culture=TestContext.INSTANCE.getUserCulture();
		NumberFormat numFormat = null; 
		String numString = null;
		if (culture.equals("en-US")) {
			numFormat = NumberFormat.getInstance(Locale.US);
		} else if (culture.equals("fr")) {
			numFormat = NumberFormat.getInstance(Locale.FRENCH);
		} else if (culture.equals("zh-Hans")) {
			numFormat = NumberFormat.getInstance(Locale.SIMPLIFIED_CHINESE);
		}		
		numString = numFormat.format(num);
		return numString;
	}
}

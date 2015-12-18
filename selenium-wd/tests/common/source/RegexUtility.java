package common.source;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtility {
	//([a-zA-Z_]\w+)\((\w+),(\w+)\)
	//([a-zA-Z_]\w+)\((.+),(.+)\)
	//([a-zA-Z_]\w+)\((.+)\) - Extract function name and the inner arguments
	//  .... 
	// '(.+)' - Extract the constant inside string 
	//(.+),(.+) - Will give the last parameter in 2nd group match. Remaining will all be in first group and can be recursively split.
	
	private static int flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;
	
	public static boolean matchesPattern(String inputString, String regexPattern) {
		 Pattern pattern = Pattern.compile(regexPattern, flags);
		 Matcher matcher = pattern.matcher(inputString);
		 return matcher.matches();
	}

	public static List<String> getMatchingGroups(String inputString, String regexPattern) {
		List<String> output = new ArrayList<String>(); 
		Pattern pattern = Pattern.compile(regexPattern, flags);
		 Matcher matcher = pattern.matcher(inputString);
		 if (matcher.find()) {
			int count = matcher.groupCount();
			for (int i = 0; i < count; i++) {
				 output.add(matcher.group(i));
			}
		 }
		 
		 return output;
	}

	public static List<String> split(String inputString, String regexPattern) {
		List<String> output = new ArrayList<String>(); 
		Pattern pattern = Pattern.compile(regexPattern, flags);
		String[] items = pattern.split(inputString);
        for(String s : items) {
            output.add(s);
        }
        return output;
	}
}

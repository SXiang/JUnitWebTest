package common.source;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.testng.Assert;

public class NumberUtility {
	private ArrayList<Integer> rollingNumbers = new ArrayList<Integer>();
	private Integer currentRollingSum = 0;
	private Integer rollingNumCount = 0;
	
	public NumberUtility() {
		initializeRollingNumbers();
	}
	
	public void initializeRollingNumbers() {
		setRollingNumbers(new ArrayList<Integer>());
		currentRollingSum = 0;
		rollingNumCount = 0;
	}
	
	public void addRollingNumber(Integer number) {
		rollingNumbers.add(number);
		currentRollingSum += number; 
		rollingNumCount++;
	}
	
	public Integer getMovingAverage() {
		if (rollingNumCount == 0) 
			return 0;
		
		return (Integer)currentRollingSum/rollingNumCount;
	}

	public ArrayList<Integer> getRollingNumbers() {
		return rollingNumbers;
	}

	public void setRollingNumbers(ArrayList<Integer> rollingNumbers) {
		this.rollingNumbers = rollingNumbers;
	}

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
	
	public static boolean isInRange(Float number, Float minValue, Float maxValue) {
		if (number < minValue) {
			return false;
		}
		if (number > maxValue) {
			return false;
		}
		return true;
	}
	
	public static Integer decimalsInNumber(String numberAsString) {
		Integer decimalCount = 0;
		
		try {
			Float.valueOf(numberAsString);
		} catch (Exception ex)  {
			throw new IllegalArgumentException(String.format("Argument:[%s] is NOT a number. "
					+ "Specify number in format {00[.00]}. ", numberAsString));
		}
		
		// valid number string. Checks for decimals.
		numberAsString = numberAsString.trim();
		List<String> splitNums = RegexUtility.split(numberAsString, RegexUtility.DOT_SPLIT_REGEX_PATTERN);
		if (splitNums != null && splitNums.size() > 0) {
			if (splitNums.size() == 1) {
				// String contains '.' but no decimal after the '.'
				if (numberAsString.contains(".")) {
					throw new IllegalArgumentException(String.format("Argument:[%s] NOT in format {00[.00]}. "
							+ "Found 0 numbers after decimal point.", numberAsString));
				}
			} else {
				// Found 2 parts. NOTE: If more than 2 parts exist this is not a valid number and will get caught during Float.valueOf() above.
				String decimalPlaces = splitNums.get(1).trim();
				decimalCount = decimalPlaces.length();
				if (decimalCount == 0) {
					// String contains '.' but no numbers after the '.'
					throw new IllegalArgumentException(String.format("Argument:[%s] NOT in format {00[.00]}. "
							+ "Found 0 numbers after decimal point.", numberAsString));
				}
			}
		} 
		return decimalCount;
	}
	
	public static void main(String[] args) {
		Log.info("Running test - testDecimalsInNumber_NoDecimals() ...");
		testDecimalsInNumber_NoDecimals();
		Log.info("Running test - testDecimalsInNumber_OneDecimal() ...");
		testDecimalsInNumber_OneDecimal();
		Log.info("Running test - testDecimalsInNumber_FourDecimals() ...");
		testDecimalsInNumber_FourDecimals();
		Log.info("Running test - testDecimalsInNumber_InvalidNumberNotANumber() ...");
		testDecimalsInNumber_InvalidNumberNotANumber();
		Log.info("Running test - testDecimalsInNumber_InvalidNumberMultipleDecimalPoints() ...");
		testDecimalsInNumber_InvalidNumberMultipleDecimalPoints();

		Log.info("Running test - testMovingAverage_ZeroNumbers() ...");
		testMovingAverage_ZeroNumbers();
		Log.info("Running test - testMovingAverage_OneNumber() ...");
		testMovingAverage_OneNumber();
		Log.info("Running test - testMovingAverage_MultipleNumbers_AllSame() ...");
		testMovingAverage_MultipleNumbers_AllSame();
		Log.info("Running test - testMovingAverage_MultipleNumbers_AllDifferent() ...");
		testMovingAverage_MultipleNumbers_AllDifferent();
	}

	private static void testDecimalsInNumber_NoDecimals() {
		String numberAsString = " 3323 ";
		Assert.assertTrue(decimalsInNumber(numberAsString) == 0);
	}

	private static void testDecimalsInNumber_OneDecimal() {
		String numberAsString = "332.3 ";
		Assert.assertTrue(decimalsInNumber(numberAsString) == 1);
	}

	private static void testDecimalsInNumber_FourDecimals() {
		String numberAsString = " 3.0323";
		Assert.assertTrue(decimalsInNumber(numberAsString) == 4);
	}

	private static void testDecimalsInNumber_InvalidNumberNotANumber() {
		String numberAsString = "ADSFDEGWEBB";
		boolean caughtCorrectException = false;
		try {
			decimalsInNumber(numberAsString);
		} catch (IllegalArgumentException ex) {
			if (ex.getMessage().contains("Specify number in format {00[.00]}")) {
				caughtCorrectException = true;
			}
		}
		Assert.assertTrue(caughtCorrectException);
	}

	private static void testDecimalsInNumber_InvalidNumberMultipleDecimalPoints() {
		String numberAsString = "10.10.10.10";
		boolean caughtCorrectException = false;
		try {
			decimalsInNumber(numberAsString);
		} catch (IllegalArgumentException ex) {
			if (ex.getMessage().contains("Specify number in format {00[.00]}")) {
				caughtCorrectException = true;
			}
		}
		Assert.assertTrue(caughtCorrectException);
	}

	private static void testMovingAverage_ZeroNumbers() {
		Integer expectedAvg = 0;
		NumberUtility numUtility = new NumberUtility();
		Log.info("No rolling numbers added.");
		Integer movingAverage = numUtility.getMovingAverage();
		Log.info(String.format("Moving Average is: %d", movingAverage));
		Log.info("Verifying moving average is equal to expected average...");
		Assert.assertTrue(movingAverage == expectedAvg);
	}

	private static void testMovingAverage_OneNumber() {
		Integer input = 9;
		Integer expectedAvg = 9;
		NumberUtility numUtility = new NumberUtility();
		Log.info(String.format("Adding number: %d", input));
		numUtility.addRollingNumber(input);
		Integer movingAverage = numUtility.getMovingAverage();
		Log.info(String.format("Moving Average is: %d", movingAverage));
		Log.info("Verifying moving average is equal to expected average...");
		Assert.assertTrue(movingAverage == expectedAvg);
	}

	private static void testMovingAverage_MultipleNumbers_AllDifferent() {
		Integer expectedAvg = 0;
		Integer sum = 0;
		NumberUtility numUtility = new NumberUtility();
		int totalCount = 10;
		for (int i = 1; i <= totalCount; i++) {
			Log.info(String.format("Adding number: %d", i));
			numUtility.addRollingNumber(i);
			sum += i;
		}
		expectedAvg = sum / totalCount;
		Integer movingAverage = numUtility.getMovingAverage();
		Log.info(String.format("Moving Average is: %d", movingAverage));
		Log.info("Verifying moving average is equal to expected average...");
		Assert.assertTrue(movingAverage == expectedAvg);
	}

	private static void testMovingAverage_MultipleNumbers_AllSame() {
		Integer input = 9;
		Integer expectedAvg = 9;
		NumberUtility numUtility = new NumberUtility();
		int totalCount = 10;
		for (int i = 1; i <= totalCount; i++) {
			Log.info(String.format("Adding number: %d", input));
			numUtility.addRollingNumber(input);
		}
		Integer movingAverage = numUtility.getMovingAverage();
		Log.info(String.format("Moving Average is: %d", movingAverage));
		Log.info("Verifying moving average is equal to expected average...");
		Assert.assertTrue(movingAverage == expectedAvg);
	}
}

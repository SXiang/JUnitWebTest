package common.source;

import java.text.NumberFormat;
import java.util.ArrayList;
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
	
	public static void main(String[] args) {
		Log.info("Running test - testMovingAverage_ZeroNumbers() ...");
		testMovingAverage_ZeroNumbers();
		Log.info("Running test - testMovingAverage_OneNumber() ...");
		testMovingAverage_OneNumber();
		Log.info("Running test - testMovingAverage_MultipleNumbers_AllSame() ...");
		testMovingAverage_MultipleNumbers_AllSame();
		Log.info("Running test - testMovingAverage_MultipleNumbers_AllDifferent() ...");
		testMovingAverage_MultipleNumbers_AllDifferent();
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

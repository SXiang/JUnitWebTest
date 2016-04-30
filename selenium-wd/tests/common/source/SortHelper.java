package common.source;

import java.util.Arrays;

import org.apache.commons.lang3.math.NumberUtils;
import org.testng.Assert;

public class SortHelper {

	//Method to determine whether a specific column is sorted (in ASC order)
	public static boolean isSortedASC(String[] strArray){
		return isSortedASC(strArray,0);
	}

	public static boolean isSortedASC(String[] strArray,int beginAt){
		return isSortedASC(strArray,beginAt,strArray.length-1);
	}

	public static boolean isSortedASC(String[] strArray, int beginAt, int endAt){
		boolean sorted = true;
		// The actual data type of the elements is determined by the type of the first element specified
		boolean isNumber = NumberUtils.isNumber(strArray[beginAt]);
		for(int i=beginAt+1;i<=endAt; i++){
			if(isNumber){
				if(NumberUtils.createFloat(strArray[i-1]).compareTo(NumberUtils.createFloat(strArray[i])) > 0){
					return false;
				}
			}else{
				if(strArray[i-1].compareTo(strArray[i]) > 0)
					return false;
			}
		}
		return sorted;
	}

	public static boolean isSortedDESC(String[] strArray){
		return isSortedDESC(strArray,0);
	}

	public static boolean isSortedDESC(String[] strArray,int beginAt){
		return isSortedDESC(strArray,beginAt,strArray.length-1);
	}

	public static boolean isSortedDESC(String[] strArray, int beginAt, int endAt){
		boolean sorted = true;
		// The actual data type of the elements is determined by the type of the first element specified
		boolean isNumber = NumberUtils.isNumber(strArray[beginAt]);
		for(int i=beginAt+1;i<=endAt; i++){
			if(isNumber){
				if(NumberUtils.createFloat(strArray[i-1]).compareTo(NumberUtils.createFloat(strArray[i])) < 0){
					return false;
				}
			}else{
				if(strArray[i-1].compareTo(strArray[i]) < 0)
					return false;
			}
		}
		return sorted;
	}

	/**
	 * Executes the unit tests for this class.
	 * @param args
	 */
	public static void main(String[] args) {
		testIsSortedASC_Number();
		testIsSortedASC_String();
		testIsSortedDESC_Number();
		testIsSortedDESC_String();
	}

	private static void testIsSortedASC_Number(){
		String[] numbers = {"TestNumber","0","1","2","3","4","5","6","7","8","9","10","9","-1"};		

		Assert.assertTrue(isSortedASC(numbers,1,11));
		Assert.assertFalse(isSortedASC(numbers,1,12));
		Assert.assertTrue(isSortedASC(numbers,13,13));
		Assert.assertFalse(isSortedASC(numbers,11,13));

		Log.info("Verified isSortedASC for numbers "+Arrays.toString(numbers));
	}
	private static void testIsSortedASC_String(){	
		String[] strings = {"TestString","Asss","C", "Cab","Cabe","Da","Whatever"};

		Assert.assertTrue(isSortedASC(strings,1));
		Assert.assertFalse(isSortedASC(strings));
		Assert.assertTrue(isSortedASC(strings,0,0));

		Log.info("Verified isSortedASC for strings "+Arrays.toString(strings));
	}

	private static void testIsSortedDESC_Number(){
		String[] numbers = {"TestNumber","5.3","5.29","3.087","2.11","1","10"};		

		Assert.assertTrue(isSortedDESC(numbers,1,5));
		Assert.assertFalse(isSortedDESC(numbers,1,6));
		Assert.assertTrue(isSortedDESC(numbers,6,6));
		Assert.assertFalse(isSortedDESC(numbers,5,6));

		Log.info("Verified isSortedDESC for numbers "+Arrays.toString(numbers));
	}
	private static void testIsSortedDESC_String(){	
		String[] strings = {"TestString","Whatever", "FDBBBB", "DDD", "Cabe","Cab","Ca"};

		Assert.assertTrue(isSortedDESC(strings,1));
		Assert.assertFalse(isSortedDESC(strings));
		Assert.assertTrue(isSortedDESC(strings,0,0));

		Log.info("Verified isSortedDESC for strings "+Arrays.toString(strings));
	}
}

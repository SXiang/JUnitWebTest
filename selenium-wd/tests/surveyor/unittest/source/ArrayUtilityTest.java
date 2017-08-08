package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.eclipse.jetty.util.log.Log;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.ArrayUtility;
import common.source.LogHelper;
import surveyor.scommon.source.BaseTest;

public class ArrayUtilityTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseTest.initializeTestObjects();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseTest.logoutQuitDriver();
	}

	@Test
	public void testShuffle_MultipleItems() {
		final String[] inputArr = {"TC2434", "TC2436", "TC2438", "TC2440", "TC2682", "TC2683", "TC2684"};
		String[] arrayToShuffle = ArrayUtility.createDeepCopyClone(inputArr);
		ArrayUtility.shuffle(arrayToShuffle);
		String[] shuffledArr = arrayToShuffle;
		assertTrue(shuffledArr != null && shuffledArr.length==inputArr.length);
		Log.info(String.format("Original array : %s", LogHelper.arrayToString(inputArr)));
		Log.info(String.format("Shuffled array : %s", LogHelper.arrayToString(shuffledArr)));
		String inputStr = flattenArrayToString(inputArr);
		String outputStr = flattenArrayToString(shuffledArr);
		assertTrue(String.format("Elements were NOT shuffled correctly. Flattened Input Array->[%s]; Flattened Ouput List->[%s]", inputStr, outputStr),
				!inputStr.equals(outputStr));
	}

	@Test
	public void testShuffle_SingleItem() {
		final String[] inputArr = {"FirstItem"};
		ArrayUtility.shuffle(inputArr);
		assertTrue(inputArr.length==1);
		assertTrue(inputArr[0]=="FirstItem");
	}

	@Test
	public void testShuffle_NullInput() {
		String[] inputArr = null;
		ArrayUtility.shuffle(inputArr);
		assertTrue(inputArr==null);
	}

	private String flattenArrayToString(String[] inputArr) {
		StringBuilder input = new StringBuilder();
		Arrays.asList(inputArr).stream().forEach(el -> input.append(el));
		return input.toString();
	}
}
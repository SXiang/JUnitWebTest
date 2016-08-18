package common.source;

import static org.junit.Assert.assertTrue;

public class AssertHelper {
	public static <T> void equals(T expected, T actual) {
		Log.method("AssertHelper.isEqual", expected, actual);
		assertTrue(actual.equals(expected));
	}
}

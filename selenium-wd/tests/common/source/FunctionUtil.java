package common.source;

import java.util.List;
import java.util.function.Predicate;

public class FunctionUtil {
	public static boolean allListEntriesMatch(List<String> listEntries, Predicate<String> matchPredicate) {
		boolean matches = false;
		if (listEntries != null) {
			matches = listEntries.stream()
				.allMatch(e -> matchPredicate.test(e));
		}
		return matches;
	}

	public static Predicate<String> stringStartsWithPredicate(String prefix) {
		return s -> s.startsWith(prefix);
	}

	public static void warnOnError(CheckedConsumer methodCall) {
		try
		{
			methodCall.execute();
		} catch (Exception ex) {
			Log.warn(ExceptionUtility.getStackTraceString(ex));
		}
	}

	public static <T> boolean wrapException(T arg, CheckedPredicate<T> predicate) {
		try
		{
			return predicate.test(arg);
		} catch (Exception ex) {
			Log.error(ExceptionUtility.getStackTraceString(ex));
		}

		return false;
	}

}

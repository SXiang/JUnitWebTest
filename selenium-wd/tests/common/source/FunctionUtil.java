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
}

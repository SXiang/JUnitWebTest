package common.source;

import java.util.function.Supplier;

public class EnumUtility {

	/*
	 * Looks up passed-in enumValues and returns an enum with value matching the specified enumName.
	 */
	public static <T> T fromName(String enumName, Supplier<T[]> enumValues) {
		T[] values = enumValues.get();
		for (T value : values) {
			if (value.toString().equals(enumName)) {
				return value;
			}
		}
		throw new IllegalArgumentException(String.format("Enum value - '%s' is NOT a valid value", enumName));
	}
}
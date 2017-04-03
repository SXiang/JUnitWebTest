package common.source;

public class UnicodeChars {
	private static final int TAB_CODE_POINT = 0x0009;
	private static final int CR_CODE_POINT = 0x000D;
	private static final int LF_CODE_POINT = 0x000A;

	public static String tab() {
		return String.valueOf(Character.toChars(TAB_CODE_POINT));
	}

	public static String lineSeperator() {
		return String.valueOf(Character.toChars(CR_CODE_POINT)) + String.valueOf(Character.toChars(LF_CODE_POINT));
	}

	public static void main(String[] args) {
		Log.info(String.format("Tab = [%s]", UnicodeChars.tab()));
		Log.info(String.format("Line Seperator = [%s]", UnicodeChars.lineSeperator()));
	}
}

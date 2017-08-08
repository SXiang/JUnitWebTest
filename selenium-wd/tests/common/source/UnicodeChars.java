package common.source;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class UnicodeChars {
	private static final int TAB_CODE_POINT = 0x0009;
	private static final int CR_CODE_POINT = 0x000D;
	private static final int LF_CODE_POINT = 0x000A;

	public static Integer toInteger(String utf16Text) {
		return ByteBuffer.wrap(utf16Text.getBytes(Charset.forName("UTF-16"))).getInt();
	}

	public static String tab() {
		return String.valueOf(Character.toChars(TAB_CODE_POINT));
	}

	public static String lineSeperator() {
		return String.valueOf(Character.toChars(CR_CODE_POINT)) + String.valueOf(Character.toChars(LF_CODE_POINT));
	}

	public static void main(String[] args) {
		Log.info(String.format("Tab = [%s]", UnicodeChars.tab()));
		Log.info(String.format("Line Seperator = [%s]", UnicodeChars.lineSeperator()));
		String sample1 = "This is an example é";
		Log.info(String.format("Integer value of string-[%s] is = [%d]", sample1, UnicodeChars.toInteger(sample1)));
		String sample2 = "";
		Log.info(String.format("Integer value of string-[%s] is = [%d]", sample2, UnicodeChars.toInteger(sample2)));
	}
}

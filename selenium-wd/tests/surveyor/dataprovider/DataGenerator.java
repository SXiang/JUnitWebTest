package surveyor.dataprovider;

import java.util.Date;

import org.fluttercode.datafactory.impl.DataFactory;

public class DataGenerator {
	public static Date getDateBetween(Date startDate, Date endDate) {
		DataFactory df = new DataFactory();
		return df.getDateBetween(startDate, endDate);
	}

	public static String getRandomText(int minLength, int maxLength) {
		DataFactory df = new DataFactory();
		return df.getRandomText(minLength, maxLength);
	}
	
	public static String getRandomChars(int length) {
		DataFactory df = new DataFactory();
		return df.getRandomChars(length);
	}
	
	public static String getRandomWord() {
		DataFactory df = new DataFactory();
		return df.getRandomWord();
	}

	public static String getRandomWords(int length) {
		StringBuffer buffer = new StringBuffer();
		while (buffer.length() < length) {
			buffer.append(DataGenerator.getRandomWord());
			buffer.append(" ");
		}
		return buffer.substring(0, length);
	}
}

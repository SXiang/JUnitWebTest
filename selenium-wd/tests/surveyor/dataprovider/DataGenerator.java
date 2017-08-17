package surveyor.dataprovider;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.fluttercode.datafactory.impl.DataFactory;

import static org.junit.Assert.*;
import common.source.DateUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.RegexUtility;

public class DataGenerator {

	private static String[] usStates = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH",
			"NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};

	private static DataFactory df = new DataFactory();

	public static class Invoker {
		private static final Integer MAX_RECURSIVE_ATTEMPTS = 5;

		public static String randomSupply(Supplier<String> method) {
			return randomSupplyInternal(MAX_RECURSIVE_ATTEMPTS, method);
		}

		private static String randomSupplyInternal(Integer attempt, Supplier<String> method) {
			String retVal = "";
			try {
				int nextInt = new Random().nextInt(19);
				for (int i = 0; i < nextInt+1; i++) {
					retVal = method.get();
				}

				// exclude reserved words used for UiSelector text.
				if (Arrays.asList(SelectorKeywords.RESERVED_WORDS).contains(retVal)) {
					if (attempt > 0) {
						return randomSupplyInternal(--attempt, method);
					}
				}

			} catch (Exception e) {
				Log.warn(String.format("Error in randomSupply. Exception -> %s", ExceptionUtility.getStackTraceString(e)));
			}

			return retVal;
		}
	}

	public static class Address {
		private String streetNumber;
		private String streetName;
		private String city;
		private String state;

		public Address(String streetNumber, String streetName, String city, String state) {
			this.setStreetNumber(streetNumber);
			this.setStreetName(streetName);
			this.setCity(city);
			this.setState(state);
		}

		public String getStreetNumber() {
			return streetNumber;
		}

		public void setStreetNumber(String streetNumber) {
			this.streetNumber = streetNumber;
		}

		public String getStreetName() {
			return streetName;
		}

		public void setStreetName(String streetName) {
			this.streetName = streetName;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
	}

	public static Date getDateBetween(Date startDate, Date endDate) {
		return df.getDateBetween(startDate, endDate);
	}

	public static Address getAddress() {
		return new Address(Invoker.randomSupply(() -> df.getStreetSuffix()), Invoker.randomSupply(() -> df.getStreetName()),
				Invoker.randomSupply(() -> df.getCity()), getAddressState());
	}

	public static String getAddressString() {
		return Invoker.randomSupply(() -> df.getAddress());
	}

	public static Integer getNumberBetween(int min, int max) {
		return df.getNumberBetween(min, max);
	}

	public static String getAddressState() {
		return Invoker.randomSupply(() -> getRandomState());
	}

	public static String getRandomState() {
		int arrIdx = new Random().nextInt(usStates.length);
		return usStates[arrIdx];
	}

	public static String getRandomText(int minLength, int maxLength) {
		if (minLength < 0 || maxLength < 0) {
			return "";
		}

		return df.getRandomText(minLength, maxLength);
	}

	public static String getRandomChars(int length) {
		if (length < 0) {
			return "";
		}

		return df.getRandomChars(length);
	}

	public static String getRandomWord() {
		return Invoker.randomSupply(() -> df.getRandomWord());
	}

	public static String getRandomWords(int length) {
		if (length < 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		while (buffer.length() < length) {
			buffer.append(DataGenerator.getRandomWord());
			buffer.append(" ");
		}
		return buffer.substring(0, length);
	}

	public static void main(String[] args) {
		Log.info("Executing testDataGenerator_GetAddress_VerifySelectorKeywords() ...");
		testDataGenerator_GetAddress_VerifySelectorKeywords();
		Log.info("Executing testDataGenerator_GetDateBetween_Valid() ...");
		testDataGenerator_GetDateBetween_ValidRange();
		Log.info("Executing testDataGenerator_GetDateBetween_SameDateRange() ...");
		testDataGenerator_GetDateBetween_SameDateRange();
		Log.info("Executing testDataGenerator_GetRandomText_ValidRange() ...");
		testDataGenerator_GetRandomText_ValidLengths();
		Log.info("Executing testDataGenerator_GetRandomText_InvalidLengths() ...");
		testDataGenerator_GetRandomText_InvalidLengths();
		Log.info("Executing testDataGenerator_GetRandomText_SameLength() ...");
		testDataGenerator_GetRandomText_SameLength();
		Log.info("Executing testDataGenerator_GetRandomText_LengthZero() ...");
		testDataGenerator_GetRandomText_LengthZero();
		Log.info("Executing testDataGenerator_GetRandomChars_ValidLength() ...");
		testDataGenerator_GetRandomChars_ValidLength();
		Log.info("Executing testDataGenerator_GetRandomChars_InvalidLength() ...");
		testDataGenerator_GetRandomChars_InvalidLength();
		Log.info("Executing testDataGenerator_GetRandomChars_LengthZero() ...");
		testDataGenerator_GetRandomChars_LengthZero();
		Log.info("Executing testDataGenerator_GetRandomWord_Valid() ...");
		testDataGenerator_GetRandomWord_Valid();
		Log.info("Executing testDataGenerator_GetRandomWords_ValidLength() ...");
		testDataGenerator_GetRandomWords_ValidLength();
		Log.info("Executing testDataGenerator_GetRandomWords_InvalidLength() ...");
		testDataGenerator_GetRandomWords_InvalidLength();
		Log.info("Executing testDataGenerator_GetRandomWords_LengthZero() ...");
		testDataGenerator_GetRandomWords_LengthZero();
	}

	private static void testDataGenerator_GetAddress_VerifySelectorKeywords() {
		List<String> reservedWords = Arrays.asList(SelectorKeywords.RESERVED_WORDS);
		IntStream.range(1, 500000)
			.forEach(i -> {
				Address address = DataGenerator.getAddress();
				Log.info("Address: " + address.toString());
				assertTrue(String.format("Invalid data generation. Found a reserved word in address state - '%s'", address.state),
						!reservedWords.contains(address.state));
			});
	}

	private static void testDataGenerator_GetDateBetween_ValidRange() {
		String dateFormatString = "dd/MM/yyyy/hh:mm:ss";
		Date startDate; Date endDate;
		try {
			startDate = DateUtility.getDate("10/01/2011/11:02:03", dateFormatString);
			endDate = DateUtility.getDate("10/01/2016/12:02:45", dateFormatString);
			Date dateBetween = DataGenerator.getDateBetween(startDate, endDate);
			Log.info("Result: " + dateBetween.toString());
			List<String> dateParts = RegexUtility.split(dateBetween.toString(), RegexUtility.SPACE_SPLIT_REGEX_PATTERN);
			String yearPart = dateParts.get(dateParts.size()-1);
			assertTrue((Integer.valueOf(yearPart) >= 2011) && (Integer.valueOf(yearPart) <= 2016));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private static void testDataGenerator_GetDateBetween_SameDateRange() {
		String dateFormatString = "dd/MM/yyyy/hh:mm:ss";
		Date startDate; Date endDate;
		try {
			startDate = DateUtility.getDate("10/01/2016/12:02:45", dateFormatString);
			endDate = DateUtility.getDate("10/01/2016/12:02:45", dateFormatString);
			Date dateBetween = DataGenerator.getDateBetween(startDate, endDate);
			Log.info("Result: " + dateBetween.toString());
			assertTrue(dateBetween.toString().equalsIgnoreCase("Sun Jan 10 00:02:45 PST 2016"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private static void testDataGenerator_GetRandomText_LengthZero() {
		String randomText = DataGenerator.getRandomText(0, 0);
		Log.info("Result: " + randomText);
		assertTrue(randomText.equals(""));
	}

	private static void testDataGenerator_GetRandomText_SameLength() {
		String randomText = DataGenerator.getRandomText(10, 10);
		Log.info("Result: " + randomText);
		assertTrue(randomText.length()==10);
	}

	private static void testDataGenerator_GetRandomText_InvalidLengths() {
		String randomText = DataGenerator.getRandomText(-1, -1);
		Log.info("Result: " + randomText);
		assertTrue(randomText.equals(""));
	}

	private static void testDataGenerator_GetRandomText_ValidLengths() {
		String randomText = DataGenerator.getRandomText(5, 15);
		Log.info("Result: " + randomText);
		assertTrue((randomText.length()>=5) && (randomText.length()<=15));
	}

	private static void testDataGenerator_GetRandomChars_LengthZero() {
		String randomChars = DataGenerator.getRandomChars(0);
		Log.info("Result: " + randomChars);
		assertTrue(randomChars.equals(""));
	}

	private static void testDataGenerator_GetRandomChars_InvalidLength() {
		String randomChars = DataGenerator.getRandomChars(-1);
		Log.info("Result: " + randomChars);
		assertTrue(randomChars.equals(""));
	}

	private static void testDataGenerator_GetRandomChars_ValidLength() {
		String randomChars = DataGenerator.getRandomChars(10);
		Log.info("Result: " + randomChars);
		assertTrue(randomChars.length()==10);
	}

	private static void testDataGenerator_GetRandomWords_LengthZero() {
		String randomWords = DataGenerator.getRandomWords(0);
		Log.info("Result: " + randomWords);
		assertTrue(randomWords.equals(""));
	}

	private static void testDataGenerator_GetRandomWords_InvalidLength() {
		String randomWords = DataGenerator.getRandomWords(-1);
		Log.info("Result: " + randomWords);
		assertTrue(randomWords.equals(""));
	}

	private static void testDataGenerator_GetRandomWords_ValidLength() {
		String randomWords = DataGenerator.getRandomWords(30);
		Log.info("Result: " + randomWords);
		assertTrue(randomWords.length()==30);
	}

	private static void testDataGenerator_GetRandomWord_Valid() {
		String randomWord = DataGenerator.getRandomWord();
		Log.info("Result: " + randomWord);
		assertTrue(randomWord.length()>0);
	}
}

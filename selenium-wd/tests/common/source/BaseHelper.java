/**
 *
 */
package common.source;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

/**
 * @author zlu
 *
 */
public class BaseHelper {

	private static final double SYSTEMHISTPDF_MINSIZE_IN_KB = 11.2;
	private static final int REFGASPDF_MINSIZE_IN_KB = 10;

	/**
	 *
	 */
	public BaseHelper() {
	}

	public static boolean compareTwoFilesByContent(String file1, String file2) throws IOException {
		return FileUtils.contentEquals(new File(file1), new File(file2));
	}

	public static void deCompressZipFile(String strNameBase, String strDLPath) throws Exception {
		Log.method("deCompressZipFile", strNameBase, strDLPath);
		String zipFile = Paths.get(strDLPath, strNameBase + ".zip").toString();
		String outputFolder = strDLPath + strNameBase;
		unZip(zipFile, outputFolder);
	}

	public static void deCompressZipFile(String strName, String strDLPath, boolean fullFileName) throws Exception {
		Log.method("deCompressZipFile", strName, strDLPath, fullFileName);
		String zipFile = Paths.get(strDLPath, strName).toString();
		String outputFolder = strDLPath;
		unZip(zipFile, outputFolder);
	}

	public static String getLineSeperator() {
		return System.getProperty("line.separator");
	}

	public static String getPaginationShowingStartString() {
		String paginationShowingText = Resources.getResource(ResourceKeys.Constant_ShowingStartToEndOfTotalEntries);
		// get first 3 parts of the string.
		List<String> strParts = RegexUtility.split(paginationShowingText, RegexUtility.SPACE_SPLIT_REGEX_PATTERN);
		StringBuffer showingStartString = new StringBuffer();
		if (strParts != null && strParts.size() > 1) {
			showingStartString.append(strParts.get(0));
			showingStartString.append(" ");
			showingStartString.append(strParts.get(1).replace("_START_", NumberUtility.getNumberStringForCurrentLocale(1)));
			showingStartString.append(" ");
			showingStartString.append(strParts.get(2));
		}
		return showingStartString.toString();
	}

	public static boolean isNullOrEmptyOrZero(String str){
    	return isNullOrEmpty(str)||str.matches("[0\\.]*");
    }

	public static boolean isNullOrEmpty(String str) {
		return str==null||str.length()==0;
	}

	public static boolean isStringListSorted(List<String> strList) {
		boolean sorted = true;
		for (int i = 1; i < strList.size(); i++) {
			if (strList.get(i - 1).compareToIgnoreCase(strList.get(i)) > 0)
				sorted = false;
		}
		return sorted;
	}

	public static boolean isStringListSortedDes(List<String> strList) {
		boolean sorted = true;
		for (int i = 1; i < strList.size(); i++) {
			if (strList.get(i - 1).compareToIgnoreCase(strList.get(i)) < 0)
				sorted = false;
		}
		return sorted;
	}

	/**
	 * This method checks for a list of key value pairs in a given string. Input
	 * is a list of Strings to be matched and returns the String and associated
	 * value
	 * @param actualReportString
	 * @param inputList
	 * @return HashMap<String, String> a map with the string and whether it's matched
	 */
	public static Map<String, String> matchPatternforPairs(String actualReportString, List<String> inputList) {
		Log.method("matchPatternforPairs", actualReportString, LogHelper.strListToString(inputList));
		Map<String, String> stringMatch = Collections.synchronizedMap(new HashMap<String, String>());
		String[] lines = actualReportString.split("\\n");
		Iterator<String> listIterator = inputList.iterator();
		while (listIterator.hasNext()) {
			Pattern pattertoMatch = Pattern.compile(listIterator.next().trim());
			for (String line : lines) {
				String formatteLine = line.trim();
				if (pattertoMatch.matcher(line).find()) {
					Matcher matcher = pattertoMatch.matcher(formatteLine);
					matcher.find();
					stringMatch.put((formatteLine.substring(matcher.start(), matcher.end()).trim()),
							(formatteLine.substring(matcher.end() + 1).trim().replace(":", "").trim()));
				}
			}
		}
		return stringMatch;
	}

	/**
	 * This method checks a list of strings are contained in a given string
	 *
	 * @param actualReportString
	 * @param inputList
	 * @return HashMap<String, Boolean> a map with the string and whether it's
	 *         matched
	 */
	public static Map<String, Boolean> matchSinglePattern(String actualReportString, List<String> inputList) {
		Log.method("matchSinglePattern", actualReportString, LogHelper.strListToString(inputList));
		Map<String, Boolean> stringsToMatch = Collections.synchronizedMap(new HashMap<String, Boolean>());
		String[] lines = actualReportString.split("\\n");
		Iterator<String> listIterator = inputList.iterator();
		while (listIterator.hasNext()) {
			String stringtoMatch = listIterator.next().trim();
			stringsToMatch.put(stringtoMatch, false);
		}
		for (String line : lines) {
			listIterator = inputList.iterator();
			while (listIterator.hasNext()) {
				String stringtoMatch = listIterator.next().trim();
				String formatteLine = line.trim();
				if (formatteLine.contains(stringtoMatch)) {
					stringsToMatch.put(stringtoMatch, true);
				}
			}
		}

		Log.info(String.format("Match String Map is : %s", LogHelper.mapToString(stringsToMatch)));
		return stringsToMatch;
	}

	public static String nullToEmpty(String input) {
		if (input == null) {
			return "";
		}
		return input;
	}

	public static String prependStringWithChar(String input, char prependChar, int times) {
		StringBuilder builder = new StringBuilder();
		if (times > 0) {
			char[] ch = new char[times];
			for (int i = 0; i < ch.length; i++) {
				ch[i] = prependChar;
			}
			builder.append(String.copyValueOf(ch));
		}

		if (input != null) {
			builder.append(input);
		}

		return builder.toString();
	}

	private static void unZip(String zipFile, String outputFolder) throws FileNotFoundException, IOException {
		Log.method("unZip", zipFile, outputFolder);
		FileInputStream inputStream = new FileInputStream(zipFile);
		ZipInputStream zis = new ZipInputStream(inputStream);
		ZipEntry ze = zis.getNextEntry();
		while (ze != null) {
			String entryName = ze.getName();

			File f = new File(outputFolder + File.separator + entryName);
			f.getParentFile().mkdirs();
			FileOutputStream fos = new FileOutputStream(f);
			try {
				int len;
				byte buffer[] = new byte[1024];
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			} finally {
				fos.close();
			}
			zis.closeEntry();
			ze = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
		inputStream.close();
	}

	public static boolean validatePdfFile(String pdfFileName) {
		Log.method("validatePdfFile", pdfFileName);
		File pdfFile = new File(pdfFileName);

		long sizeKB = 0;

		if (pdfFile.exists()) {
			sizeKB = (long) (pdfFile.length() / 1024);
		} else {
			Log.info(String.format("\nThe \"%s\" file doesn't exists!\n", pdfFileName));
			return false;
		}

		Log.info(String.format("\nThe \"%s\" size is: %d\n", pdfFileName, sizeKB));

		if (sizeKB > 1) {
			return true;
		}

		return false;
	}

	public static boolean validatePdfFileForRefGas(String pdfFileName) {
		Log.method("validatePdfFileForRefGas", pdfFileName);
		File pdfFile = new File(pdfFileName);

		long sizeKB = 0;

		if (pdfFile.exists()) {
			sizeKB = (long) (pdfFile.length() / 1024);
		} else {
			Log.info(String.format("\nThe \"%s\" file doesn't exists!\n", pdfFileName));
			return false;
		}

		Log.info(String.format("\nThe \"%s\" size is: %d\n", pdfFileName, sizeKB));

		if (sizeKB > REFGASPDF_MINSIZE_IN_KB) {
			return true;
		}

		return false;
	}

	public static boolean validatePdfFileForSysHis(String pdfFileName) {
		Log.method("validatePdfFileForSysHis", pdfFileName);
		File pdfFile = new File(pdfFileName);
		double sizeKB;

		if (pdfFile.exists()) {
			sizeKB = pdfFile.length() / 1024.2d + 0.5;
		} else {
			Log.info(String.format("\nThe \"%s\" file doesn't exists!\n", pdfFileName));
			return false;
		}
		Log.info(String.format("\nThe \"%s\" size is: %.2f\n", pdfFileName, sizeKB));

		if (sizeKB >= SYSTEMHISTPDF_MINSIZE_IN_KB) {
			Log.info(String.format("\nThe \"%s\" size is %s > min size = %s\n", pdfFileName, sizeKB, SYSTEMHISTPDF_MINSIZE_IN_KB));
			Log.info("validatePdfFileForSysHis = TRUE");
			return true;
		}

		Log.info(String.format("\nThe \"%s\" size is %s < min size = %s\n", pdfFileName, sizeKB, SYSTEMHISTPDF_MINSIZE_IN_KB));
		Log.info("validatePdfFileForSysHis = FALSE");
		return false;
	}

	public static boolean validateDatFile(String datFileName) {
		Log.method("validateDatFile", datFileName);
		File datFile = new File(datFileName);

		if (datFile.exists()) {
			if (datFile.canRead())
				return true;
		} else {
			Log.info(String.format("\nThe \"%s\" file doesn't exists!\n", datFileName));
			return false;
		}

		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialize TestSetup to instantiate the TestContext and DB connection parameters.
		TestSetup testSetup = new TestSetup(false /*skip initialization*/);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());;
		}
		testSetup.initializeDBProperties();
		TestContext.INSTANCE.setTestSetup(testSetup);

		// ** Unit tests for getPaginationShowingStartString() method **/
		Log.info("Setting user culture to en-US");
		TestContext.INSTANCE.setUserCulture("en-US");
		String startString = BaseHelper.getPaginationShowingStartString();
		Log.info(String.format("Expected = %s, Actual = %s", "Showing 1 to", startString));
		Assert.assertTrue(!startString.isEmpty());

		// No asserts in FR and ZH, since currently we dont have all the localized strings populated in the environment.
		Log.info(String.format("Setting user culture to fr"));
		TestContext.INSTANCE.setUserCulture("fr");
		startString = BaseHelper.getPaginationShowingStartString();
		Log.info("Paging start string is: " + startString);

		Log.info(String.format("Setting user culture to zh-Hans"));
		TestContext.INSTANCE.setUserCulture("zh-Hans");
		startString = BaseHelper.getPaginationShowingStartString();
		Log.info("Paging start string is: " + startString);
	}
}
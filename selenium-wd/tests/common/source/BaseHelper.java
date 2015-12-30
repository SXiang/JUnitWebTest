/**
 * 
 */
package common.source;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

import org.apache.commons.io.FileUtils;

/**
 * @author zlu
 *
 */
public class BaseHelper {

	/**
	 * 
	 */
	public BaseHelper() {
	}

	public static void deCompressZipFile(String strNameBase, String strDLPath) throws Exception {
		String zipFile = strDLPath + strNameBase + ".zip";
		String outputFolder = strDLPath + strNameBase;

		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
		ZipEntry ze = zis.getNextEntry();

		while (ze != null) {
			String entryName = ze.getName();

			File f = new File(outputFolder + File.separator + entryName);
			f.getParentFile().mkdirs();
			FileOutputStream fos = new FileOutputStream(f);

			int len;
			byte buffer[] = new byte[1024];
			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}

			fos.close();

			ze = zis.getNextEntry();
		}

		zis.closeEntry();

		zis.close();
	}

	public static void deCompressZipFile(String strName, String strDLPath, boolean fullFileName) throws Exception {
		String zipFile = strDLPath + strName;
		String outputFolder = strDLPath;

		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
		ZipEntry ze = zis.getNextEntry();

		while (ze != null) {
			String entryName = ze.getName();

			File f = new File(outputFolder + File.separator + entryName);
			f.getParentFile().mkdirs();
			FileOutputStream fos = new FileOutputStream(f);

			int len;
			byte buffer[] = new byte[1024];
			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}

			fos.close();

			ze = zis.getNextEntry();
		}

		zis.closeEntry();

		zis.close();
	}

	public static boolean validatePdfFile(String pdfFileName) {
		File pdfFile = new File(pdfFileName);

		long sizeKB = 0;

		if (pdfFile.exists()) {
			sizeKB = (long) (pdfFile.length() / 1024);
		} else {
			System.out.format("\nThe \"%s\" file doesn't exists!\n", pdfFileName);
			return false;
		}

		System.out.format("\nThe \"%s\" size is: %d\n", pdfFileName, sizeKB);

		if (sizeKB > 0) {
			return true;
		}

		return false;
	}

	public static boolean validatePdfFileForRefGas(String pdfFileName) {
		File pdfFile = new File(pdfFileName);

		long sizeKB = 0;

		if (pdfFile.exists()) {
			sizeKB = (long) (pdfFile.length() / 1024);
		} else {
			System.out.format("\nThe \"%s\" file doesn't exists!\n", pdfFileName);
			return false;
		}

		System.out.format("\nThe \"%s\" size is: %d\n", pdfFileName, sizeKB);

		if (sizeKB > 77) {
			return true;
		}

		return false;
	}

	public static boolean validatePdfFileForSysHis(String pdfFileName) {
		File pdfFile = new File(pdfFileName);
		double sizeKB;

		if (pdfFile.exists()) {
			sizeKB = pdfFile.length() / 1024.2d;
		} else {
			System.out.format("\nThe \"%s\" file doesn't exists!\n", pdfFileName);
			return false;
		}
		System.out.format("\nThe \"%s\" size is: %.2f\n", pdfFileName, sizeKB);

		if (sizeKB > (11.2)) {
			return true;
		}

		return false;
	}

	public static boolean validateDatFile(String datFileName) {
		// Temporary solution for now
		File datFile = new File(datFileName);

		if (datFile.exists()) {
			if (datFile.canRead())
				return true;
		} else {
			System.out.format("\nThe \"%s\" file doesn't exists!\n", datFileName);
			return false;
		}

		return false;
	}

	public static boolean compareTwoFilesByContent(String file1, String file2) throws IOException {
		return FileUtils.contentEquals(new File(file1), new File(file2));
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

	/**
	 * This method checks a list of strings are contained in a given string
	 * 
	 * @param actualReportString
	 * @param inputList
	 * @return HashMap<String, Boolean> a map with the string and whether it's
	 *         matched
	 */
	public static HashMap<String, Boolean> matchSinglePattern(String actualReportString, List<String> inputList) {

		HashMap<String, Boolean> stringMatch = new HashMap<String, Boolean>();
		String[] lines = actualReportString.split("\\n");
		Iterator<String> listIterator = inputList.iterator();
		while (listIterator.hasNext()) {
			String stringtoMatch = listIterator.next().trim();
			stringMatch.put(stringtoMatch, false);
		}
		for (String line : lines) {
			listIterator = inputList.iterator();
			while (listIterator.hasNext()) {
				String stringtoMatch = listIterator.next().trim();
				String formatteLine = line.trim();
				if (formatteLine.contains(stringtoMatch)) {
					stringMatch.put(stringtoMatch, true);
				}
			}
		}
		return stringMatch;
	}

	/**
	 * This method checks for a list of key value pairs in a given string. Input
	 * is a list of Strings to be matched and returns the String and associated
	 * value
	 * @param actualReportString
	 * @param inputList
	 * @return HashMap<String, String> a map with the string and whether it's matched
	 */
	public static HashMap<String, String> matchPatternforPairs(String actualReportString, List<String> inputList) {
		HashMap<String, String> stringMatch = new HashMap<String, String>();
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
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
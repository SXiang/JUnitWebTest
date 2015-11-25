/**
 * 
 */
package common.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

	public static void deCompressZipFile(String strNameBase, String strDLPath)
			throws Exception {
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

	public static void deCompressZipFile(String strName, String strDLPath,
			boolean fullFileName) throws Exception {
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
			System.out.format("\nThe \"%s\" file doesn't exists!\n",
					pdfFileName);
			return false;
		}

		System.out.format("\nThe \"%s\" size is: %d\n", pdfFileName, sizeKB);

		if (sizeKB > 100) {
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
			System.out.format("\nThe \"%s\" file doesn't exists!\n",
					pdfFileName);
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
			System.out.println("Length: " + pdfFile.length());
			sizeKB = pdfFile.length() / 1024.2d;
			System.out.println("SIZEKB::" + sizeKB);
		} else {
			System.out.format("\nThe \"%s\" file doesn't exists!\n",
					pdfFileName);
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
			System.out.format("\nThe \"%s\" file doesn't exists!\n",
					datFileName);
			return false;
		}

		return false;
	}

	public static boolean compareTwoFilesByContent(String file1, String file2)
			throws IOException {
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
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
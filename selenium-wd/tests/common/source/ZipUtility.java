package common.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.assertTrue;

public class ZipUtility {
	/**
	 * Unzip it
	 * 
	 * @param zipFile
	 *            input zip file (absolute path)
	 * @param output
	 *            zip file output folder (absolute path)
	 */
	public void unZip(String zipFile, String outputFolder) {

		byte[] buffer = new byte[1024];

		try {

			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator + fileName);
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		ZipUtility unZip = new ZipUtility();
		unZip.unZip(TestSetup.getRootPath()+ "\\selenium-wd\\data\\test-data\\zip-utility\\CR-FF2C65.zip",TestSetup.getRootPath()+ "\\selenium-wd\\data\\test-data\\zip-utility");
		assertTrue(new File( TestSetup.getRootPath()+ "\\selenium-wd\\data\\test-data\\zip-utility\\CR-FF2C65-Report.csv").exists());
	}
}

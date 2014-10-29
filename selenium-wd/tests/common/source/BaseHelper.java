/**
 * 
 */
package common.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
	
	public static void deCompressZipFile(String strNameBase, String strDLPath) throws Exception {
    	String zipFile = strDLPath + strNameBase + ".zip";
        String outputFolder = strDLPath + strNameBase;
        
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry ze = zis.getNextEntry();
        
        while(ze != null) {
            String entryName = ze.getName();
            
            File f = new File(outputFolder + File.separator +  entryName);
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
		//Temproary solution for now by just checking the file size
		File pdfFile = new File(pdfFileName);
		
		long sizeKB = 0;
		
		if (pdfFile.exists()) {
			sizeKB = (long)(pdfFile.length() / 1024);
		}
		else {
			System.out.format("\nThe \"%s\" file doesn't exists!\n", pdfFileName);
			return false;
		}
		
		System.out.format("\nThe \"%s\" size is: %d\n", pdfFileName, sizeKB);
		
		if (sizeKB > 100) {
			return true;
		}

		return false;
	}
	
	public static boolean compareTwoFilesByContent(String file1, String file2) throws IOException {
		return FileUtils.contentEquals(new File(file1), new File(file2));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
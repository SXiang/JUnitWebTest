package common.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtility {
	public static String readFileContents(String filePath) throws IOException {
		String lineText = null;
		StringBuilder builder = new StringBuilder();
		
		BufferedReader buffReader = new BufferedReader(new FileReader(filePath));
		while ((lineText = buffReader.readLine()) != null) {
			builder.append(lineText);
		}
		buffReader.close();
		
		return builder.toString();		
	}

	// NOTE: This method won't write line breaks into the file.
	public static void writeToFile(String filePath, String fileContent) throws IOException {
		BufferedWriter buffWriter = new BufferedWriter(new FileWriter(filePath));
		buffWriter.write(fileContent);
		buffWriter.close();
	}

	/*
	 * Searches the specified file for 'searchForText' and replaces it with 'replaceWithText'
	 * Creates a copy with the updated content and replaces the source file with the copy file.
	 */
	public static void updateFile(String filePath, String searchForText, String replaceWithText) throws IOException {

		String workingFile = TestSetup.getUUIDString() + "_" + Paths.get(filePath).getFileName();
		String workingFullPath = Paths.get(TestSetup.getSystemTempDirectory(), workingFile).toString();
		
		File writeFile = new File(workingFullPath);
		
		String lineText = null;
		BufferedReader buffReader = new BufferedReader(new FileReader(filePath));
		BufferedWriter buffWriter = new BufferedWriter(new FileWriter(writeFile));
		while ((lineText = buffReader.readLine()) != null) {
			lineText = lineText.replace(searchForText, replaceWithText);
			buffWriter.write(lineText);
			buffWriter.newLine();
		}
		
		buffWriter.close();
		buffReader.close();
		
		// Copy working file to source.
		Files.copy(Paths.get(workingFullPath), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
		
		// Delete the working file.
		Files.delete(Paths.get(workingFullPath));
	}
}

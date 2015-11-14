package common.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

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
	
	/*
	 * Writes specified string to the file.
	 * NOTE: This method won't write line breaks into the file. 
	 */
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
	
	/*
	 * Returns list of files in the specified directory.
	 */
	public static List<String> getFilesInDirectory(Path directory) throws IOException {
		List<String> files = new ArrayList<String>();
		DirectoryStream<Path> stream = Files.newDirectoryStream(directory);
	    for (Path file: stream) {
	    	files.add(file.toAbsolutePath().toString());
	    }
	    return files;
	}

	/*
	 * Deletes specified file.
	 */
	public static void deleteFile(Path file) {
		try {
		    Files.delete(file);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", file);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", file);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
	}
	
	/*
	 * Delete files in the specified directory.
	 */
	public static void deleteDirectoryAndFiles(Path directory) throws IOException {
		DirectoryStream<Path> stream = Files.newDirectoryStream(directory);
	    // First delete all files in directory
		for (Path file: stream) {
	    	deleteFile(file);
	    }
	    
		// Next delete the directory.
		deleteFile(directory);
	}
}

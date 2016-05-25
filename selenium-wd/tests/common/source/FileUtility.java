package common.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;

public class FileUtility {
	/**
	 * Reads content of the specified file into a String.
	 * @param filePath - Path of the file.
	 * @return - String containing file content.
	 * @throws IOException
	 */
	public static String readFileContents(String filePath) throws IOException {
		return readFileContents(filePath, false /*retainNewline*/);		
	}

	/**
	 * Reads content of the specified file into a String.
	 * @param filePath - Path of the file.
	 * @return - String containing file content.
	 * @throws IOException
	 */
	public static String readFileContents(String filePath, boolean retainNewline) throws IOException {
		String lineText = null;
		StringBuilder builder = new StringBuilder();
		
		BufferedReader buffReader = new BufferedReader(new FileReader(filePath));
		try {
			while ((lineText = buffReader.readLine()) != null) {
				builder.append(lineText);
				if (retainNewline) {
					builder.append(BaseHelper.getLineSeperator());
				}
			} 
		} finally {
			buffReader.close();
		}
		return builder.toString();		
	}
	
	/**
	 * Reads content of the specified file into an ArrayList where each line in the file represents an entry in the List.
	 * @param filePath - Path of the file.
	 * @return - List containing file lines.
	 * @throws IOException
	 */
	public static List<String> readFileLinesToList(String filePath) throws IOException {
		String lineText = null;
		List<String> list = new ArrayList<String>();
		
		BufferedReader buffReader = new BufferedReader(new FileReader(filePath));
		try {
			while ((lineText = buffReader.readLine()) != null) {
				list.add(lineText);
			}
		} finally {
			buffReader.close();
		}
		
		return list;		
	}

	/*
	 * Writes specified string to the file.
	 * NOTE: This method won't write line breaks into the file. 
	 */
	public static void writeToFile(String filePath, String fileContent) throws IOException {
		BufferedWriter buffWriter = new BufferedWriter(new FileWriter(filePath));
		try {
			buffWriter.write(fileContent);
		} finally {
			buffWriter.close();
		}	
	}

	/*
	 * Searches the specified file for 'searchForText' and replaces it with 'replaceWithText'
	 * Creates a copy with the updated content and replaces the source file with the copy file.
	 */
	public static void updateFile(String filePath, Hashtable<String,String> placeholderMap) throws IOException {
		String workingFile = TestSetup.getUUIDString() + "_" + Paths.get(filePath).getFileName();
		String workingFullPath = Paths.get(TestSetup.getSystemTempDirectory(), workingFile).toString();
		File writeFile = new File(workingFullPath);
		String lineText = null;
		BufferedReader buffReader = new BufferedReader(new FileReader(filePath));
		BufferedWriter buffWriter = new BufferedWriter(new FileWriter(writeFile));
		try {
			while ((lineText = buffReader.readLine()) != null) {
				for (Entry<String, String> entry : placeholderMap.entrySet()) {
					lineText = lineText.replace(entry.getKey(), entry.getValue());
				}
				buffWriter.write(lineText);
				buffWriter.newLine();
			}
		} finally {
			buffWriter.close();
			buffReader.close();
		}		
		// Copy working file to source.
		Files.copy(Paths.get(workingFullPath), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
		
		// Delete the working file.
		Files.delete(Paths.get(workingFullPath));
	}

	/*
	 * Returns list of files in the specified directory.
	 */
	public static List<String> getFilesInDirectory(Path directory, boolean includeFullPath) throws IOException {
		List<String> files = new ArrayList<String>();
		DirectoryStream<Path> stream = Files.newDirectoryStream(directory);
	    for (Path file: stream) {
	    	if (includeFullPath) {
	    		files.add(file.toAbsolutePath().toString());
	    	} else {
	    		files.add(file.getFileName().toString());
	    	}
	    }
	    return files;
	}

	/*
	 * Verifies that all the expected files are seen in the specified directory.
	 */
	public static boolean compareFilesInDirectory(Path directoryToCheck, List<String> expectedFileNames) throws IOException {
		Integer expectedFileCount = expectedFileNames.size();
		List<String> filesInDirectory = FileUtility.getFilesInDirectory(directoryToCheck, false/*includeFullPath*/);
		if (filesInDirectory == null || filesInDirectory.size() == 0 || filesInDirectory.size()!=expectedFileCount) {
			return false;
		}
		
		Integer seenFileCount = 0;
		for (String file : filesInDirectory) {
			if (expectedFileNames.contains(file)) {
				seenFileCount++;
			} else {
				return false;
			}
		}
		
		if (seenFileCount != expectedFileCount) {
			return false;
		}
		
		return true;
	}

	/*
	 * Returns list of files in the specified directory.
	 */
	public static List<String> getFilesInDirectory(Path directory) throws IOException {
		return getFilesInDirectory(directory, true /*includeFullPath*/);
	}
	
	public static boolean compareFilesInDirectories(String firstFolderPath, String secondFolderPath) throws IOException {
		return compareFilesInDirectories(firstFolderPath, secondFolderPath, false);
	}
	public static boolean compareFilesInDirectories(String firstFolderPath, String secondFolderPath, boolean contains) throws IOException {
		Log.info(String.format("Comparing files in Folders - [%s] <==> [%s]...", firstFolderPath, secondFolderPath));
		
		// Check that first folder and second folder have the exact same files.
		List<String> firstDirectoryFiles = FileUtility.getFilesInDirectory(Paths.get(firstFolderPath), false /*includeFullPath*/);
		List<String> secondDirectoryFiles = FileUtility.getFilesInDirectory(Paths.get(secondFolderPath), false /*includeFullPath*/);

		if ((firstDirectoryFiles == null && secondDirectoryFiles == null) || (firstDirectoryFiles.size() == 0 && secondDirectoryFiles.size() == 0)) {
			Log.info("Both first and second folder have 0 files. <-- [Return TRUE]");
			return true;
		}
		
		if ((firstDirectoryFiles == null && secondDirectoryFiles != null) || (firstDirectoryFiles.size() == 0 && secondDirectoryFiles.size() != 0)) {
			Log.info("First folder has 0 files. Second folder have 1 or more files. <-- [Return FALSE]");
			return false;
		}

		if ((firstDirectoryFiles != null && secondDirectoryFiles == null) || (firstDirectoryFiles.size() != 0 && secondDirectoryFiles.size() == 0)) {
			Log.info("First folder has 1 or more files. Second folder have 0 files. <-- [Return FALSE]");
			return false;
		}

		if(!contains){
			// Check both directories have same number of files.
			if ((firstDirectoryFiles.size() != secondDirectoryFiles.size())) {
				Log.info(String.format("First folder has [%d] files. Second folder has [%d] files. <-- [Return FALSE]",
						firstDirectoryFiles.size(), secondDirectoryFiles.size()));
				return false;
			}

			for (String sDirFile : secondDirectoryFiles) {
				if (!firstDirectoryFiles.contains(sDirFile)) {
					Log.info(String.format("First folder does NOT contain file - [%s] from second folder. <-- [Return FALSE]", sDirFile));
					return false;
				}
			}			
		}
		
		for (String fDirFile : firstDirectoryFiles) {
			if (!secondDirectoryFiles.contains(fDirFile)) {
				Log.info(String.format("Second folder does NOT contain file - [%s] from first folder. <-- [Return FALSE]", fDirFile));
				return false;
			}
		}
		return true;
	}
	/**
	 * Returns list of files matching the specified filter from the specified directory.
	 * @param directory - Directory to look for files.
	 * @param filter - file extension filter. For eg. "*.shp,*.txt"
	 * @return - list of files matching the specified filter.
	 * @throws IOException
	 */
	public static List<String> getFilesInDirectory(Path directory, String filter) throws IOException {
		List<String> files = new ArrayList<String>();
		DirectoryStream<Path> stream = Files.newDirectoryStream(directory);
		List<String> extFilterList = null;
		if (filter != null) {
			filter = filter.trim();
			filter = filter.replace("*.", "");
			extFilterList = RegexUtility.split(filter, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		}
	    for (Path file: stream) {
	    	String filePath = file.toAbsolutePath().toString();
	    	if (extFilterList == null || extFilterList.size() == 0) {
	    		// If no filter, add the file.
	    		files.add(filePath);
	    	} else {	    	
	    		// If filter specified, add only matching file.
		    	String ext = getFileExtension(filePath);
		    	if (extFilterList.contains(ext)) {
		    		files.add(filePath);
		    	}
	    	}
	    }
	    return files;
	}

	/**
	 * Returns filename part from full file path.
	 * @param filePath - Full path to the file.
	 * @return - name of the file.
	 */
	public static String getFileName(String filePath) {
		String fileName = "";
		if (filePath != null && filePath != "") {
			fileName = Paths.get(filePath).getFileName().toString();
		}
		return fileName;
	}

	/**
	 * Returns file extension part from full file path.
	 * @param filePath - Full path to the file.
	 * @return - extension of the file.
	 */
	public static String getFileExtension(String filePath) {
		String fileExt = "";
		if (filePath != null && filePath != "") {
			int dotIdx = filePath.lastIndexOf('.');
			if (dotIdx >= 0 && dotIdx < filePath.length()) {
				fileExt = filePath.substring(dotIdx + 1);
			}
		}
		return fileExt;
	}

	/*
	 * Creates or opens an existing file.
	 */
	public static File createOrOpenFile(Path filePath) throws IOException {
		File file = new File(filePath.toString());
		if(!file.exists()) {
		    file.createNewFile();
		} 
		return file; 
	}

	/*
	 * Create specified file and write text to it.
	 */
	public static void createTextFile(Path filePath, String fileContent) throws IOException {
		createOrWriteToExistingTextFile(filePath, fileContent, true /*createNew*/);
	}
	
	/*
	 * Create specified file or open existing file and write text to it.
	 */
	public static void createOrWriteToExistingTextFile(Path filePath, String fileContent) throws IOException {
		createOrWriteToExistingTextFile(filePath, fileContent, false /*createNew*/);
	}
		
	/*
	 * Create specified file or open existing file and write text to it.
	 */
	private static void createOrWriteToExistingTextFile(Path filePath, String fileContent, boolean createNew) throws IOException {
		BufferedWriter output = null;
        try {
            File file = null;
            if (createNew) {
            	file = new File(filePath.toString());
            } else {
            	file = createOrOpenFile(filePath);
            }
            output = new BufferedWriter(new FileWriter(file));
            output.write(fileContent);
        } catch ( IOException e ) {
            Log.error(String.format("IOException when creating file - %s",filePath.toString()));
        } finally {
            if (output != null) {
            	output.close();
            }
        }
	}

	/*
	 * Checks and creates the specified directory if it does NOT exist.
	 */
	public static void createDirectoryIfNotExists(String directoryPath) {
		File newDir = new File(directoryPath);
		if (!newDir.exists()) {
			try {
				newDir.mkdirs();
			} catch (SecurityException ex) {
				Log.error("Error creating new directory - " + directoryPath + " EXCEPTION: " + ExceptionUtility.getStackTraceString(ex));
			}
		}
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
		    Log.error(x.toString());
		}
	}
	
	/*
	 * Delete files in the specified directory and then deletes the directory. 
	 */
	public static void deleteDirectoryAndFiles(Path directory) throws IOException {
		deleteFilesInDirectory(directory);
	    
		// Next delete the directory.
		deleteFile(directory);
	}

	/*
	 * Delete files in the specified directory.
	 */
	public static void deleteFilesInDirectory(Path directory) throws IOException {
		DirectoryStream<Path> stream = Files.newDirectoryStream(directory);
	    // First delete all files in directory
		for (Path file: stream) {
	    	deleteFile(file);
	    }
	}

	public static void copyFile(String fromFile, String toFile) throws IOException{
		// Create the directory for test case if it does not exist.
         createDirectoryIfNotExists(toFile);
		 FileUtils.copyFile(new File(fromFile), new File(toFile));
	}
	public static void main(String[] args) throws IOException {
		Path directoryWithFiles = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\shapefileutility-tests");
		Path emptyDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\shapefileutility-tests\\empty-dir");
		
		// Unit tests for -> getFilesInDirectory(Path directory, String filter)
		Log.info("Executing test -> test_getFilesInDirectory_DirWithFiles_ValidFilterWithMatch() ...");
		test_getFilesInDirectory_DirWithFiles_ValidFilterWithMatch(directoryWithFiles);
		Log.info("Executing test -> test_getFilesInDirectory_DirWithFiles_ValidFilterWithNoMatch() ...");
		test_getFilesInDirectory_DirWithFiles_ValidFilterWithNoMatch(directoryWithFiles);
		Log.info("Executing test -> test_getFilesInDirectory_DirWithFiles_EmptyFilter() ...");
		test_getFilesInDirectory_DirWithFiles_EmptyFilter(directoryWithFiles);
		Log.info("Executing test -> test_getFilesInDirectory_DirWithFiles_ValidFilterSingleExtWithMatch() ...");
		test_getFilesInDirectory_DirWithFiles_ValidFilterSingleExtWithMatch(directoryWithFiles);
		Log.info("Executing test -> test_getFilesInDirectory_DirWithNoFiles_ValidFilter() ...");
		test_getFilesInDirectory_DirWithNoFiles_ValidFilter(emptyDirectory);
	}

	private static void test_getFilesInDirectory_DirWithFiles_ValidFilterWithMatch(Path rootDirectory) throws IOException {
		String validFilter = "*.shp,*.shx";
		List<String> filesInDirectory = getFilesInDirectory(rootDirectory, validFilter);
		Assert.assertTrue(filesInDirectory.size() > 0);
	}

	private static void test_getFilesInDirectory_DirWithFiles_ValidFilterWithNoMatch(Path rootDirectory) throws IOException {
		String validFilterWithNoMatch = "*.xlsx,*.docx";
		List<String> filesInDirectory = getFilesInDirectory(rootDirectory, validFilterWithNoMatch);
		Assert.assertTrue(filesInDirectory.size() == 0);
	}

	private static void test_getFilesInDirectory_DirWithFiles_EmptyFilter(Path rootDirectory) throws IOException {
		String emptyFilter = "";
		List<String> filesInDirectory = getFilesInDirectory(rootDirectory, emptyFilter);
		Assert.assertTrue(filesInDirectory.size() > 0);
	}

	private static void test_getFilesInDirectory_DirWithFiles_ValidFilterSingleExtWithMatch(Path rootDirectory) throws IOException {
		String validFilter = "*.shp";
		List<String> filesInDirectory = getFilesInDirectory(rootDirectory, validFilter);
		Assert.assertTrue(filesInDirectory.size() > 0);
	}

	private static void test_getFilesInDirectory_DirWithNoFiles_ValidFilter(Path rootDirectory) throws IOException {
		String validFilter = "*.shp,*.shx";
		List<String> filesInDirectory = getFilesInDirectory(rootDirectory, validFilter);
		Assert.assertTrue(filesInDirectory.size() == 0);
	}
}

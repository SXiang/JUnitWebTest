package common.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;

public class FileUtility {
	/**
	 * Checks whether a file exists at the specified path.
	 * @param filePath - Path of the file.
	 * @return - Whether file exists or not.
	 */
	public static boolean fileExists(String filePath) {
		return Files.exists(Paths.get(filePath));
	}

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
	 * Writes specified lines from the string array to file with a newline after each line.
	 */
	public static void writeToFile(String filePath, String[] linesToWrite) throws IOException {
		BufferedWriter buffWriter = new BufferedWriter(new FileWriter(filePath));
		try {
			for (String lineText : linesToWrite) {
				buffWriter.write(lineText);
				buffWriter.newLine();
			}
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

	public static boolean compareFilesForExactMatch(String firstFolderPath, String secondFolderPath, List<String> includeExtensions) throws IOException {
		Log.method("compareFilesForExactMatch", firstFolderPath, secondFolderPath, LogHelper.strListToString(includeExtensions));
		Log.info(String.format("Comparing files content (for specified extensions) in Folders - [%s] <==> [%s]...", firstFolderPath, secondFolderPath));

		// Check that first folder and second folder have files with exact same content (for specified extensions).
		List<String> firstDirectoryFiles = FileUtility.getFilesInDirectory(Paths.get(firstFolderPath), false /*includeFullPath*/);
		for (String fDirFile : firstDirectoryFiles) {
			Path fFileFullPath = Paths.get(firstFolderPath, fDirFile);
			Path sFileFullPath = Paths.get(secondFolderPath, fDirFile);

			String fFileExt= getFileExtension(fFileFullPath.toString());
			if ((includeExtensions == null) || (includeExtensions.contains(fFileExt.toLowerCase()))) {
				if (!FileUtils.contentEqualsIgnoreEOL(new File(fFileFullPath.toString()), new File(sFileFullPath.toString()), null /*charset name*/)) {
					Log.info(String.format("[File-1 =%s] and [File-2 =%s] content does NOT match . <-- [Return FALSE]",
							fFileFullPath, sFileFullPath));
					Log.info(String.format("[File-1 =%s] content : %s", fFileFullPath,
							FileUtility.readFileContents(fFileFullPath.toString(), true /*retainNewline*/)));
					Log.info(String.format("[File-2 =%s] content : %s", sFileFullPath,
							FileUtility.readFileContents(sFileFullPath.toString(), true /*retainNewline*/)));
					return false;
				}
			}
		}

		return true;
	}

	public static boolean compareFilesAndSizesInDirectories(String firstFolderPath, String secondFolderPath) throws IOException {
		return compareFilesAndSizesInDirectories(firstFolderPath, secondFolderPath, null /*skipExtensions*/);
	}

	public static boolean compareFilesAndSizesInDirectories(String firstFolderPath, String secondFolderPath, List<String> excludeExtensions) throws IOException {
		Log.info(String.format("Comparing files sizes in Folders - [%s] <==> [%s]...", firstFolderPath, secondFolderPath));

		// First check all files in both directories are the same.
		boolean retVal = compareFilesInDirectories(firstFolderPath, secondFolderPath, false);
		if (retVal) {
			// Check that first folder and second folder have files of exact same size.
			List<String> firstDirectoryFiles = FileUtility.getFilesInDirectory(Paths.get(firstFolderPath), false /*includeFullPath*/);
			for (String fDirFile : firstDirectoryFiles) {
				Path fFileFullPath = Paths.get(firstFolderPath, fDirFile);
				Path sFileFullPath = Paths.get(secondFolderPath, fDirFile);

				String fFileExt= getFileExtension(fFileFullPath.toString());
				if (excludeExtensions != null && excludeExtensions.contains(fFileExt.toLowerCase())) {
					continue;    // skip files which are in SKIP list.
				}

				long fFileSize = new File(fFileFullPath.toString()).length();
				long sFileSize = new File(sFileFullPath.toString()).length();

				if (fFileSize != sFileSize) {
					Log.info(String.format("[File-1 =%s, Size=%s] does NOT match in size with [File-2 =%s, Size=%s] . <-- [Return FALSE]",
							fFileFullPath, fFileSize, sFileFullPath, sFileSize));
					return false;
				}
			}
		}

		return retVal;
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

	public static Integer getLineCountInFile(Path filePath) throws IOException {
		Integer totalNumberOfLines = 0;
		if (!FileUtility.fileExists(filePath.toString())) {
			return 0;
		}

		LineNumberReader lineReader = null;
		try {
			lineReader = new LineNumberReader(new FileReader(filePath.toFile()));
		    lineReader.skip(Long.MAX_VALUE);
		    totalNumberOfLines = lineReader.getLineNumber() + 1;
		} finally {
			if (lineReader != null) {
				lineReader.close();
			}
		}
		return totalNumberOfLines;
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
		if (filePath != null && !BaseHelper.isNullOrEmpty(filePath)) {
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
		if (filePath != null && !BaseHelper.isNullOrEmpty(filePath)) {
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
			Log.info(String.format("Deleting file - '%s'", file.toString()));
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
	public static void deleteFilesInDirectory(Path directory){
		DirectoryStream<Path> stream = null;
		try{
		     stream = Files.newDirectoryStream(directory);
		}catch(IOException e){
			Log.warn(e.toString());
			return;
		}
	    // First delete all files in directory
		for (Path file: stream) {
	    	deleteFile(file);
	    }
	}

	/*
	 * Delete files in the specified directory that start with the specified filePrefix and match the specified extension.
	 */
	public static void deleteFilesInDirectory(Path directory, String filePrefix, String fileExtension) throws IOException {
		DirectoryStream<Path> stream = Files.newDirectoryStream(directory);
	    // First delete all files in directory
		for (Path file: stream) {
			String fileExt = FileUtility.getFileExtension(file.toString());
			String fileName = FileUtility.getFileName(file.toString());
			if (fileExt.equalsIgnoreCase(fileExt) && fileName.startsWith(filePrefix)) {
				deleteFile(file);
			}
	    }
	}
	/*
	 * Delete files in the specified directory and files that start with the specified filePrefix.
	 */
	public static void deleteFilesAndSubFoldersInDirectory(String directory, String filePrefix) throws IOException {
		deleteFilesAndSubFoldersInDirectory(directory, filePrefix, "[a-zA-Z]*");
	}

	/*
	 * Delete files in the specified directory and files that start with the specified filePrefix and match the specified extension.
	 */
	public static void deleteFilesAndSubFoldersInDirectory(String directory, String filePrefix, String fileExtension) throws IOException {
		DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directory));
		for (Path file: stream) {
			String fileExt = FileUtility.getFileExtension(file.toString());
			String fileName = FileUtility.getFileName(file.toString());
			boolean isDirectory = new File(file.toString()).isDirectory();
			if (fileExt.matches(fileExtension) && fileName.startsWith(filePrefix)) {
				if(isDirectory){
					deleteDirectoryAndFiles(file);
				}else{
					deleteFile(file);
				}
			}
	    }
	}
	public static void copyFile(String fromFile, String toFile) throws IOException{
		// Create the directory for test case if it does not exist.
		 String dirToFile = new File(toFile).getParent();
		 createDirectoryIfNotExists(dirToFile);
		 FileUtils.copyFile(new File(fromFile), new File(toFile));
	}

	public static void main(String[] args) throws IOException {
		Path directoryWithFiles = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\shapefileutility-tests");
		Path emptyDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\shapefileutility-tests\\empty-dir");
		createDirectoryIfNotExists(emptyDirectory.toString());

		Path compareFolder1a = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\fileutility-tests\\folder1a");
		Path compareFolder1b = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\fileutility-tests\\folder1b");
		Path compareFolder1c = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\fileutility-tests\\folder1c");

		Path compareFolder2a = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\fileutility-tests\\folder2a");
		Path compareFolder2b = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\fileutility-tests\\folder2b");

		// Unit tests for -> getFilesInDirectory(Path directory, String filter)
		Log.info("Executing test -> test_compareFilesForExactMatchNoFilter_Success() ...");
		test_compareFilesForExactMatchNoFilter_Failure(compareFolder2a, compareFolder2b);
		Log.info("Executing test -> test_compareFilesForExactMatchWithFilter_Success() ...");
		List<String> includeExtensions = new ArrayList<String>();
		includeExtensions.add("prj");
		test_compareFilesForExactMatchWithFilter_Success(compareFolder2a, compareFolder2b, includeExtensions);
		Log.info("Executing test -> test_compareFilesAndSizesInDirectoriesWithFilter_Success() ...");
		List<String> excludeExtensions = new ArrayList<String>();
		excludeExtensions.add("prj");
		test_compareFilesAndSizesInDirectoriesWithFilter_Success(compareFolder2a, compareFolder2b, excludeExtensions);

		Log.info("Executing test -> test_compareFilesAndSizesInDirectories_SameSize_Success() ...");
		test_compareFilesAndSizesInDirectories_SameSize_Success(compareFolder1a, compareFolder1b);
		Log.info("Executing test -> test_compareFilesAndSizesInDirectories_DifferentSizeFile_Failure() ...");
		test_compareFilesAndSizesInDirectories_DifferentSizeFile_Failure(compareFolder1a, compareFolder1c);
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

		// Unit Test or ->
		Log.info("Executing test -> test_deleteFilesAndSubFoldersInDirectory()......");
		test_deleteFilesAndSubFoldersInDirectory();
		Log.info("Executing test -> test_deleteDirectoryAndFiles() ...");
		test_deleteDirectoryAndFiles();
	}

	private static void test_deleteFilesAndSubFoldersInDirectory() throws IOException {
		createDirectoryIfNotExists(TestSetup.getSystemTempDirectory()+File.separator+"CR-D77653");
		createTextFile(Paths.get(TestSetup.getSystemTempDirectory()+File.separator+"CR-D77653"+File.separator+"test.txt"), "test");
		createTextFile(Paths.get(TestSetup.getSystemTempDirectory()+File.separator+"CR-D77653.txt"), "test");
		deleteFilesAndSubFoldersInDirectory(TestSetup.getSystemTempDirectory(), "CR-D77653");
		Assert.assertFalse(new File(TestSetup.getSystemTempDirectory()+File.separator+"CR-D77653.txt").exists());
		Assert.assertFalse(new File(TestSetup.getSystemTempDirectory()+File.separator+"CR-D77653").exists());

	}

	private static void test_deleteDirectoryAndFiles() throws IOException {
		createDirectoryIfNotExists(TestSetup.getSystemTempDirectory()+File.separator+"CR-D77654");
		createTextFile(Paths.get(TestSetup.getSystemTempDirectory()+File.separator+"CR-D77654"+File.separator+"test.txt"), "test");
		createTextFile(Paths.get(TestSetup.getSystemTempDirectory()+File.separator+"CR-D77654.txt"), "test");
		deleteDirectoryAndFiles(Paths.get(TestSetup.getSystemTempDirectory()+File.separator+"CR-D77654"));
		Assert.assertFalse(new File(TestSetup.getSystemTempDirectory()+File.separator+"CR-D77654").exists());

}
	private static void test_compareFilesForExactMatchNoFilter_Failure(Path firstFolderPath, Path secondFolderPath) throws IOException {
		Assert.assertFalse(FileUtility.compareFilesForExactMatch(firstFolderPath.toString(), secondFolderPath.toString(), null /*includeExtensions*/));
	}

	private static void test_compareFilesForExactMatchWithFilter_Success(Path firstFolderPath, Path secondFolderPath, List<String> includeExtensions) throws IOException {
		Assert.assertTrue(FileUtility.compareFilesForExactMatch(firstFolderPath.toString(), secondFolderPath.toString(), includeExtensions));
	}

	private static void test_compareFilesAndSizesInDirectoriesWithFilter_Success(Path firstFolderPath, Path secondFolderPath, List<String> skipExtensions) throws IOException {
		Assert.assertTrue(FileUtility.compareFilesAndSizesInDirectories(firstFolderPath.toString(), secondFolderPath.toString(), skipExtensions));
	}

	private static void test_compareFilesAndSizesInDirectories_SameSize_Success(Path firstFolderPath, Path secondFolderPath) throws IOException {
		Assert.assertTrue(FileUtility.compareFilesAndSizesInDirectories(firstFolderPath.toString(), secondFolderPath.toString()));
	}

	private static void test_compareFilesAndSizesInDirectories_DifferentSizeFile_Failure(Path firstFolderPath, Path secondFolderPath) throws IOException {
		Assert.assertFalse(FileUtility.compareFilesAndSizesInDirectories(firstFolderPath.toString(), secondFolderPath.toString()));
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

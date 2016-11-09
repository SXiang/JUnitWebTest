package common.source;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

public class DirectoryWatcher {

	private WatchService watcher = null;
	
	public DirectoryWatcher() {
	}

	public WatchService getWatcher() {
		return watcher;
	}

	public void setWatcher(WatchService watcher) {
		this.watcher = watcher;
	}

	public void registerWatcher(Path dirToWatch) throws IOException {
		setWatcher(FileSystems.getDefault().newWatchService());
		dirToWatch.register(getWatcher(), ENTRY_CREATE);
	}
	
	/**
	 * Using the specified watcher, watches for file of specified extension
	 * and filePrefix to be created in the directory until timeout.
	 * @param dirToWatch - Directory to watch.
	 * @param filePrefix - Look for filename that starts with this prefix string.
	 * @param fileExtension - Look for file of this extension. Specified without . or *. For eg. 'PDF' or 'TXT'.
	 * @return - Path to the newly created file.
	 */
	@SuppressWarnings("unchecked")
	public Path watchForNewlyCreatedFile(Path dirToWatch, String filePrefix, String fileExtension, long timeoutInSeconds) {
		try {
			WatchService watcherSvc = getWatcher();
			if (watcherSvc == null) {
				throw new Exception("Watcher NOT registered. Register watcher before calling watchForNewlyCreatedFile()");
			}
			
			long startTimeMillis = System.currentTimeMillis();
			
		    for (;;) {
			    WatchKey key = watcherSvc.poll(timeoutInSeconds, TimeUnit.SECONDS);
			    if (key != null) {
				    for (WatchEvent<?> event: key.pollEvents()) {
				        WatchEvent.Kind<?> kind = event.kind();
		
				        // This key is registered only for ENTRY_CREATE events,
				        // but an OVERFLOW event can occur regardless if events are lost or discarded.
				        if (kind == OVERFLOW) {
				            continue;
				        }
		
				        // The filename is the context of the event.
				        WatchEvent<Path> ev = (WatchEvent<Path>)event;
				        Path filename = ev.context();
		
				        // Verify that the new file matches the specified file prefix and file extension.
			            Path newFile = dirToWatch.resolve(filename);
			            String newFileName = FileUtility.getFileName(newFile.toString());
			            String extension = FileUtility.getFileExtension(newFile.toString());
			            if (!extension.equalsIgnoreCase(fileExtension)) {
			                Log.info(String.format("New file '%s' is NOT of specified extension - '%s' .%n", filename, fileExtension));
			                continue;
			            }
			            if (!newFileName.startsWith(filePrefix)) {
			                Log.info(String.format("New file '%s' does NOT start with - '%s' .%n", filename, filePrefix));
			                continue;
			            }
		
				        // Found matching file. Return file.
			            Log.info(String.format("Found file - '%s'", filename)); 
			            return filename;
				    }
		
				    // Reset the key -- this step is critical if you want to receive further watch events.  
				    // If the key is no longer valid, the directory is inaccessible so exit the loop.
				    boolean valid = key.reset();
				    if (!valid) {
				        break;
				    }
			    }
			    
			    // If executing for more than specified timeout, break out of the loop.
			    long currentTimeMillis = System.currentTimeMillis();
			    if ((currentTimeMillis - startTimeMillis) > (timeoutInSeconds * 1000)) {
			    	break;
			    }
		    }
		} catch (IOException e) {
			Log.info("Could NOT find newly created file - '%s'.%n  [IOException] : " + ExceptionUtility.getStackTraceString(e));
		} catch (InterruptedException e) {
			Log.info("Could NOT find newly created file - '%s'.%n  [InterruptedException] : " + ExceptionUtility.getStackTraceString(e));
		} catch (NullPointerException e) {
			Log.info("Could NOT find newly created file - '%s'.%n  [NullPointerException] : " + ExceptionUtility.getStackTraceString(e));
		} catch (Exception e) {
			Log.info("Could NOT find newly created file - '%s'.%n  [Exception] : " + ExceptionUtility.getStackTraceString(e));
		}
		
		return null;
	}

	public static void main(String[] args) throws IOException {
		Path directoryToWatch = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\downloads");

		Log.info("Executing test -> test_getMatchingNewlyCreatedFile_DropAValidFileReturnsTrue() ...");
		test_getMatchingNewlyCreatedFile_DropAValidFileReturnsTrue(directoryToWatch);
		Log.info("Executing test -> test_getMatchingNewlyCreatedFile_DropInvalidFilesReturnsNull() ...");
		test_getMatchingNewlyCreatedFile_DropInvalidFilesReturnsNull(directoryToWatch);
		Log.info("Executing test -> test_getMatchingNewlyCreatedFile_DoNOTDropAnyFilesReturnsNull() ...");
		test_getMatchingNewlyCreatedFile_DoNOTDropAnyFilesReturnsNull(directoryToWatch);
	}
	
	// [PRE-REQ for this test]: While the test is running drop a file matching 'filePrefix' and 'fileExtension' in watched folder before timeout expires.
	private static void test_getMatchingNewlyCreatedFile_DropAValidFileReturnsTrue(Path directoryToWatch) throws IOException {
		String filePrefix = "SurveyExport_test-000000212121_SimAuto-Analyzer1";
		String fileExtension = "zip";
		long timeoutInSeconds = 30;
		DirectoryWatcher directoryWatcher = new DirectoryWatcher();
		directoryWatcher.registerWatcher(directoryToWatch);
		Path newlyCreatedFile = directoryWatcher.watchForNewlyCreatedFile(directoryToWatch, filePrefix, fileExtension, timeoutInSeconds);
		Assert.assertTrue(newlyCreatedFile != null);
	}

	// [PRE-REQ for this test]: While the test is running drop invalid files. ie. files NOT matching 'filePrefix' and 'fileExtension' in watched folder before timeout expires.
	private static void test_getMatchingNewlyCreatedFile_DropInvalidFilesReturnsNull(Path directoryToWatch) throws IOException {
		String filePrefix = "SurveyExport_test-000000212121_SimAuto-Analyzer1";
		String fileExtension = "zip";
		long timeoutInSeconds = 30;
		DirectoryWatcher directoryWatcher = new DirectoryWatcher();
		directoryWatcher.registerWatcher(directoryToWatch);
		Path newlyCreatedFile = directoryWatcher.watchForNewlyCreatedFile(directoryToWatch, filePrefix, fileExtension, timeoutInSeconds);
		Assert.assertTrue(newlyCreatedFile == null);
	}

	// [PRE-REQ for this test]: Do NOT drop any files in the watched folder while the test is running for the specified timeout.
	private static void test_getMatchingNewlyCreatedFile_DoNOTDropAnyFilesReturnsNull(Path directoryToWatch) throws IOException {
		String filePrefix = "SurveyExport_test-000000212121_SimAuto-Analyzer1";
		String fileExtension = "zip";
		long timeoutInSeconds = 10;
		DirectoryWatcher directoryWatcher = new DirectoryWatcher();
		directoryWatcher.registerWatcher(directoryToWatch);
		Path newlyCreatedFile = directoryWatcher.watchForNewlyCreatedFile(directoryToWatch, filePrefix, fileExtension, timeoutInSeconds);
		Assert.assertTrue(newlyCreatedFile == null);
	}
}

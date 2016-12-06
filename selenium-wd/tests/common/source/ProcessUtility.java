package common.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.testng.Assert;

public class ProcessUtility {

	private static boolean DEBUG_ANALYZER = true;
	private static String AnalyzerDebugLogPath = "C:\\temp\\AnalyzerDebugLog.log";

	public static ProcessOutputInfo executeProcess(String command, boolean isShellCommand, boolean waitForExit) throws IOException {
		Process process = null;
		String output = "";
		String error = "";
		try {
			String execCommand = command;

			if (isShellCommand) {
				if (DEBUG_ANALYZER && command.toLowerCase().contains("picarro.surveyor.analyzer.exe")) {
					execCommand = String.format("cmd.exe /C \"%s > %s\"", command, AnalyzerDebugLogPath);
				} else {
					execCommand = "cmd.exe /C " + command;
				}
			}

			process = Runtime.getRuntime().exec(execCommand);
			Thread.sleep(1000);

			if (isShellCommand) {
				// Flush the output stream.
				process.getOutputStream().close();
			}

			if (waitForExit) {
				// Flush input stream.
				output = processInputStream(process.getInputStream());
				// Flush error stream.
				error = processInputStream(process.getErrorStream());

				int exit = process.waitFor();
				Log.info("Process Exit Code: " + exit);
			}
		} catch (InterruptedException e) {
			Log.error(e.toString());
		} finally {
			if (waitForExit) {
				if (process != null) {
					process.destroy();
				}
			}
		}

		if (!waitForExit)
			return new ProcessOutputInfo(process, output, error);

		return new ProcessOutputInfo(null, output, error);
	}

	private static String processInputStream(InputStream inputStream) throws IOException {
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(streamReader);
		StringBuilder builder = new StringBuilder();
		String lineText;
		try {
			while ((lineText = bufferedReader.readLine()) != null) {
				builder.append(lineText);
				Log.info(lineText);
			}
		} catch (IOException e) {
			Log.error(e.toString());
		} finally {
			bufferedReader.close();
		}
		return builder.toString();
	}

	public static void killProcess(String processName, boolean killChildProcesses) {
		String cmd = "TASKKIll /F /IM " + processName;
		if (killChildProcesses) {
		 cmd += " /T";
		}
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			process.getOutputStream().close();
			processInputStream(process.getInputStream());
			processInputStream(process.getErrorStream());

			int exit = process.waitFor();
			Log.info("Process Exit Code: " + exit);
		} catch (IOException | InterruptedException e) {
			Log.error(e.toString());
		}
	}

	public static boolean isProcessRunning(String processName) {
		Log.info(String.format("Checking if process %s is running ...", processName));
		try {
			Process process = Runtime.getRuntime().exec("wmic.exe");
			InputStreamReader streamReader = new InputStreamReader(process.getInputStream());

			// Query for the process.
			OutputStreamWriter oStreamWriter = new OutputStreamWriter(process.getOutputStream());
			oStreamWriter.write(String.format("process where name='%s'", processName));
			oStreamWriter.flush();
			oStreamWriter.close();

			// Read the input stream and check if process string was found in the input text.
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String lineText;
			try {
				while ((lineText = bufferedReader.readLine()) != null) {
					Log.info(lineText);
					if (lineText.toLowerCase().contains(processName.toLowerCase())) {
						Log.info(String.format("Found process - %s", processName));
						return true;
					}
				}
			} catch (IOException e) {
				Log.error(e.toString());
			} finally {
				bufferedReader.close();
			}
		} catch (IOException e) {
			Log.error(e.toString());
		}

		Log.info(String.format("Did NOT find process - %s", processName));
		return false;
	}

	public static void main(String[] args) {
		Log.info("Running test - testIsProcessRunning_RunningProcess_ReturnTrue() ...");
		testIsProcessRunning_RunningProcess_ReturnTrue();
		Log.info("Running test - testIsProcessRunning_NonExistentProcess_ReturnFalse() ...");
		testIsProcessRunning_NonExistentProcess_ReturnFalse();
	}

	private static void testIsProcessRunning_RunningProcess_ReturnTrue() {
		String testValue = "lsass.exe";
		Assert.assertTrue(ProcessUtility.isProcessRunning(testValue));
	}

	private static void testIsProcessRunning_NonExistentProcess_ReturnFalse() {
		String testValue = "nonexistentprocess.exe";
		Assert.assertFalse(ProcessUtility.isProcessRunning(testValue));
	}
}

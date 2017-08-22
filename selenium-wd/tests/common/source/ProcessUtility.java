package common.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class ProcessUtility {

	private static boolean DEBUG_ANALYZER = true;

	public static ProcessOutputInfo executeProcess(String command, boolean isShellCommand, boolean waitForExit) throws IOException {
		Process process = null;
		String output = "";
		String error = "";
		try {
			String execCommand = command;

			if (isShellCommand) {
				if (DEBUG_ANALYZER && command.toLowerCase().contains("picarro.surveyor.analyzer.exe")) {
					execCommand = String.format("cmd.exe /C \"%s > %s\"", command, TestContext.INSTANCE.getTestSetup().getAnalyzerDebugLogPath());
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
		Log.method("isProcessRunning", processName);
		return isProcessRunning(processName, null /*commandLineText*/);
	}

	public static boolean isProcessRunning(String processName, String commandLineText) {
		return isProcessRunning(processName, commandLineText, true /*logEnabled*/);
	}

	public static boolean isProcessRunning(String processName, String commandLineText, Boolean infoLogEnabled) {
		if (infoLogEnabled) {
			Log.info(String.format("Checking if process %s is running ... Command line filter = [%s]", processName, commandLineText));
		}

		try {
			Process process = Runtime.getRuntime().exec("cmd /c WMIC");

			// Query for the process.
			BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			if (!BaseHelper.isNullOrEmpty(commandLineText)) {
				bufWriter.write("process where \"name='" + processName + "' and commandline like '%" + commandLineText + "%'\"");
			} else {
				bufWriter.write(String.format("process where name='%s'", processName));
			}

			bufWriter.newLine();
			bufWriter.flush();
			bufWriter.close();

			// Read the input stream and check if process string was found in the input text.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String lineText;
			Boolean foundHeader = false;
			try {
				while ((lineText = bufferedReader.readLine()) != null) {
					if (infoLogEnabled) {
						Log.info(lineText);
					}

					if (!foundHeader) {
						foundHeader = lineText.contains("Caption") && lineText.contains("CommandLine");
					}

					if (foundHeader) {
						if (lineText.toLowerCase().contains(processName.toLowerCase())) {
							if (infoLogEnabled) {
								Log.info(String.format("Found process - %s", processName));
							}

							return true;
						}
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

		if (infoLogEnabled) {
			Log.info(String.format("Did NOT find process - %s", processName));
		}

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

package common.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessUtility {
	public static Process executeProcess(String command, boolean isShellCommand, boolean waitForExit) throws IOException {
		Process process = null;
		try {
			String execCommand = command;
			
			if (isShellCommand) {
				execCommand = "cmd.exe /C " + command;
			}
			
			process = Runtime.getRuntime().exec(execCommand);
			Thread.sleep(1000); // TEST
			
			if (isShellCommand) {
				// Flush the output stream.
				process.getOutputStream().close();
			}
			
			if (waitForExit) {
				// Flush input stream.
				processInputStream(process.getInputStream());
				// Flush error stream.
				processInputStream(process.getErrorStream());

				int exit = process.waitFor();
				System.out.println("Process Exit Code: " + exit);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (waitForExit) {
				if (process != null) {
					process.destroy();
				}
			}
		}
		
		if (!waitForExit)
			return process;
		
		return null;
	}

	private static void processInputStream(InputStream inputStream) throws IOException {
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(streamReader);
		String lineText;
		try {
			while ((lineText = bufferedReader.readLine()) != null) {
				System.out.println(lineText);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
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
			System.out.println("Process Exit Code: " + exit);	
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}

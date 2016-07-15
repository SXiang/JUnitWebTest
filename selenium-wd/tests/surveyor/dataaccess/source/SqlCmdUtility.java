package surveyor.dataaccess.source;

import java.io.File;
import java.io.IOException;

import common.source.Log;
import common.source.ProcessUtility;
import common.source.TestSetup;

public class SqlCmdUtility {
	private static final String INVOKE_SQL_CMD = "InvokeSqlCmd.cmd";

	public static void executeSQLFile(String dbIPAddress, String dbPort, String databaseName, String dbUser, String dbPass, 
			String sqlFileFullPath, String outLogFileFullPath) {
		try {
			String invokeCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
			String invokeCmdFullPath = invokeCmdFolder + File.separator + INVOKE_SQL_CMD;
			String command = "cd \"" + invokeCmdFolder + "\" && " + invokeCmdFullPath + 
					String.format(" %s %s %s %s %s \"%s\" \"%s\"", 
							dbIPAddress, dbPort, databaseName, dbUser, dbPass, sqlFileFullPath, outLogFileFullPath);
			Log.info("Executing SQL. Command -> " + command);
			ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}
}

package common.source;

import java.io.File;
import java.io.IOException;

public class GridNodesManager {
	private static final String GET_GRID_NODES_AVAILABILITY_CMD = "GetGridNodesAvailability.cmd";
	private static final String REQUEST_GRID_NODES_CMD = "RequestGridNodes.cmd";

	private static final Boolean isGridServletAlive = true;  // Disable this flag to test when Grid Servlet is down.
	
	public static boolean requestGridNodes(Integer nodesToSpin, String runUUID, String browser, String os) {
		Log.method("GridNodesManager.requestGridNodes", nodesToSpin, runUUID, browser, os);
		try {
			if (isGridServletAlive) {
				String gridHost = TestContext.INSTANCE.getTestSetup().getRemoteServerHost();
				String gridPort = TestContext.INSTANCE.getTestSetup().getRemoteServerPort();
				
				String invokeCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
				String invokeCmdFullPath = invokeCmdFolder + File.separator + REQUEST_GRID_NODES_CMD;
				String command = "cd \"" + invokeCmdFolder + "\" && " + invokeCmdFullPath + 
						String.format(" \"%s\" \"%s\" \"%s\" \"%d\" \"%s\" \"%s\"", 
								gridHost, gridPort, runUUID, nodesToSpin, browser, os);
				Log.info("Executing Request Grid Nodes Command -> " + command);
				ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
				// Possible return status (HANDLE these):
				//  HTTP/1.1 400 Test run already exists with the same UUID
				//  HTTP/1.1 201 Created
			}
		} catch (IOException e) {
			Log.error(e.toString());
		}
		return true;
	}
	
	/**
	 * Returns available Grid nodes matching specified run UUID, browser and OS
	 * @return
	 */
	public static Integer getAvailableNodes(Integer nodesToSpin, String runUUID, String browser, String os) {
		Log.method("GridNodesManager.getAvailableNodes", runUUID, browser, os);
		return getAvailableGridNodes(nodesToSpin, runUUID, browser, os);
	}
	
	private static Integer getAvailableGridNodes(Integer nodesToSpin, String runUUID, String browser, String os) {
		Integer nodesCount = 0;
		try {
			String gridHost = TestContext.INSTANCE.getTestSetup().getRemoteServerHost();
			String gridPort = TestContext.INSTANCE.getTestSetup().getRemoteServerPort();
			
			String invokeCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
			String invokeCmdFullPath = invokeCmdFolder + File.separator + GET_GRID_NODES_AVAILABILITY_CMD;
			String command = "cd \"" + invokeCmdFolder + "\" && " + invokeCmdFullPath + 
					String.format(" \"%s\" \"%s\" \"%s\" \"%s\" \"%d\" \"%s\" \"%s\"", 
							gridHost, gridPort, runUUID,  nodesToSpin, browser, os);
			Log.info("Executing Grid Nodes Availability Check Command -> " + command);
			ProcessOutputInfo processOutputInfo = ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
			String responseText = processOutputInfo.getOutput();
			nodesCount = extractNodesCountFromResponse(responseText);
		} catch (IOException e) {
			Log.error(e.toString());
		}
		return nodesCount;
	}
	
	private static Integer extractNodesCountFromResponse(String responseText) {
		String nodeCountText = RegexUtility.getStringInBetween(responseText, "[", "]");
		return Integer.valueOf(nodeCountText);
	}
}

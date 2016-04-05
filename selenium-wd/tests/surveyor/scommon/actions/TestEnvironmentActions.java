package surveyor.scommon.actions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import org.testng.Assert;

import common.source.FileUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import net.lightbody.bmp.core.har.Har;
import surveyor.scommon.actions.data.TestEnvironmentDataReader;
import surveyor.scommon.actions.data.TestEnvironmentDataReader.TestEnvironmentDataRow;

public class TestEnvironmentActions extends BaseActions {
	private static final String CLS_TEST_ENVIRONMENT_ACTIONS = "TestEnvironmentActions";
	private static final String FN_IDLE_FOR_SECONDS = "idleForSeconds";
	private static final String FN_START_SIMULATOR = "startAnalyzer";
	private static final String FN_START_REPLAY = "startReplay";
	private TestEnvironmentDataReader dataReader;

	public TestEnvironmentActions() {
		super();
		setDataReader(new TestEnvironmentDataReader(this.excelUtility));
	}

	/**
	 * Executes startAnalyzer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean startAnalyzer(String data, Integer dataRowID) throws Exception {
		logAction("TestEnvironmentActions.startAnalyzer", data, dataRowID);
		ActionArguments.verifyGreaterThanZero(CLS_TEST_ENVIRONMENT_ACTIONS + FN_START_SIMULATOR, ARG_DATA_ROW_ID, dataRowID);
		try {
			TestEnvironmentDataRow dataRow = getDataReader().getDataRow(dataRowID);

			TestSetup.updateAnalyzerConfiguration(dataRow.analyzerSerialNumber, dataRow.analyzerSharedKey);
			TestSetup.restartAnalyzer();
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}
		return true;
	}
 
	/**
	 * Executes startReplay action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean startReplay(String data, Integer dataRowID) throws Exception {
		logAction("TestEnvironmentActions.startReplay", data, dataRowID);
		ActionArguments.verifyGreaterThanZero(CLS_TEST_ENVIRONMENT_ACTIONS + FN_START_REPLAY, ARG_DATA_ROW_ID, dataRowID);
		try {
			TestEnvironmentDataRow dataRow = getDataReader().getDataRow(dataRowID);

			if (!ActionArguments.isEmpty(dataRow.replayScriptDB3File)) {
				TestSetup.replayDB3Script(dataRow.replayScriptDefnFile, dataRow.replayScriptDB3File);
			} else {
				TestSetup.replayDB3Script(dataRow.replayScriptDefnFile);
			}
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}
		return true;
	}
	
	/**
	 * Executes stopAnalyzer action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean stopAnalyzer(String data, Integer dataRowID) {
		logAction("TestEnvironmentActions.stopAnalyzer", data, dataRowID);
		try {
			TestSetup.stopAnalyzer();
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}
		return true;
	}
	
	/**
	 * Executes verifyAnalyzerIsRunning action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyAnalyzerIsRunning(String data, Integer dataRowID) {
		logAction("TestEnvironmentActions.verifyAnalyzerIsRunning", data, dataRowID);
		return TestSetup.isAnalyzerRunning();
	}
 
	/**
	 * Executes verifyAnalyzerIsShutdown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyAnalyzerIsShutdown(String data, Integer dataRowID) {
		logAction("TestEnvironmentActions.verifyAnalyzerIsShutdown", data, dataRowID);
		return TestSetup.isAnalyzerShutdown();
	}
	
	/**
	 * Executes verifyBrowserIsShutdown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyBrowserIsShutdown(String data, Integer dataRowID) {
		logAction("TestEnvironmentActions.verifyBrowserIsShutdown", data, dataRowID);
		return TestContext.INSTANCE.getTestSetup().hasBrowserQuit();
	}

	/**
	 * Executes idleForSeconds action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean idleForSeconds(String data, Integer dataRowID) throws Exception {
		logAction("TestEnvironmentActions.idleForSeconds", data, dataRowID);
		Integer idleSeconds = Integer.parseInt(data);
		ActionArguments.verifyGreaterThanZero(FN_IDLE_FOR_SECONDS, ARG_DATA, idleSeconds);
		TestContext.INSTANCE.stayIdle(idleSeconds);
		return true;
	}

	/**
	 * Executes startNetworkProxy action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean startNetworkProxy(String data, Integer dataRowID) throws Exception {
		logAction("TestEnvironmentActions.startNetworkProxy", data, dataRowID);
		TestContext.INSTANCE.getTestSetup().startNetworkProxy(true);
		return true;
	}

	/**
	 * Executes stopNetworkProxy action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean stopNetworkProxy(String data, Integer dataRowID) throws Exception {
		logAction("TestEnvironmentActions.stopNetworkProxy", data, dataRowID);
		TestContext.INSTANCE.getTestSetup().stopNetworkProxy();
		return true;
	}
	
	/* Invoke action using specified ActionName */
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("startReplay")) { return this.startReplay(data, dataRowID); }
		else if (actionName.equals("startAnalyzer")) { return this.startAnalyzer(data, dataRowID); }
		else if (actionName.equals("stopAnalyzer")) { return this.stopAnalyzer(data, dataRowID); }
		else if (actionName.equals("startNetworkProxy")) { return this.startNetworkProxy(data, dataRowID); }
		else if (actionName.equals("stopNetworkProxy")) { return this.stopNetworkProxy(data, dataRowID); }
		else if (actionName.equals("verifyAnalyzerIsRunning")) { return this.verifyAnalyzerIsRunning(data, dataRowID); }
		else if (actionName.equals("verifyAnalyzerIsShutdown")) { return this.verifyAnalyzerIsShutdown(data, dataRowID); }
		else if (actionName.equals("verifyBrowserIsShutdown")) { return this.verifyBrowserIsShutdown(data, dataRowID); }
		else if (actionName.equals("idleForSeconds")) { return this.idleForSeconds(data, dataRowID); }
		return false;
	}
	
	public TestEnvironmentDataReader getDataReader() {
		if (dataReader == null) {
			setDataReader(new TestEnvironmentDataReader(this.excelUtility));
		}
		return dataReader;
	}

	public void setDataReader(TestEnvironmentDataReader dataReader) {
		this.dataReader = dataReader;
	}
}

package surveyor.scommon.actions;

import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.data.TestEnvironmentDataReader;
import surveyor.scommon.actions.data.TestEnvironmentDataReader.TestEnvironmentDataRow;

public class TestEnvironmentActions extends BaseActions {
	private static final String DEFAULT_ANALYZER_SHARED_KEY = "SimAuto-AnalyzerKey1";
	private static final String DEFAULT_ANALYZER_SERIAL_NUMBER = "SimAuto-Analyzer1";
	private static final String CLS_TEST_ENVIRONMENT_ACTIONS = "TestEnvironmentActions";
	private static final String FN_IDLE_FOR_SECONDS = "idleForSeconds";
	private static final String FN_START_SIMULATOR = "startSimulator";
	private TestEnvironmentDataReader dataReader;

	public TestEnvironmentActions() {
		super();
		setDataReader(new TestEnvironmentDataReader(this.excelUtility));
	}

	/**
	 * Executes startSimulator action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean startSimulator(String data, Integer dataRowID) throws Exception {
		logAction("TestEnvironmentActions.startSimulator", data, dataRowID);
		ActionArguments.verifyGreaterThanZero(CLS_TEST_ENVIRONMENT_ACTIONS + FN_START_SIMULATOR, ARG_DATA_ROW_ID, dataRowID);
		try {
			TestEnvironmentDataRow dataRow = getDataReader().getDataRow(dataRowID);

			TestSetup.updateAnalyzerConfiguration(dataRow.analyzerSerialNumber, dataRow.analyzerSharedKey);
			TestSetup.restartAnalyzer();

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
	 * Executes stopSimulator action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean stopSimulator(String data, Integer dataRowID) {
		logAction("TestEnvironmentActions.stopSimulator", data, dataRowID);
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

	/* Invoke action using specified ActionName */
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("startSimulator")) { return this.startSimulator(data, dataRowID); }
		else if (actionName.equals("stopSimulator")) { return this.stopSimulator(data, dataRowID); }
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

package surveyor.scommon.actions;

import java.io.IOException;

import common.source.Log;
import common.source.NetworkProxyHandler;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.Analyzer;
import surveyor.dataaccess.source.SurveyorUnit;
import surveyor.scommon.actions.data.AnalyzerDataReader;
import surveyor.scommon.actions.data.AnalyzerDataReader.AnalyzerDataRow;
import surveyor.scommon.actions.data.TestEnvironmentDataReader;
import surveyor.scommon.actions.data.TestEnvironmentDataReader.TestEnvironmentDataRow;

public class TestEnvironmentActions extends BaseActions {
	private static final String CLS_TEST_ENVIRONMENT_ACTIONS = "TestEnvironmentActions";
	private static final String FN_IDLE_FOR_SECONDS = "idleForSeconds";
	private static final String FN_START_SIMULATOR = "startAnalyzer";
	private static final String FN_START_REPLAY = "startReplay";

	private TestEnvironmentDataReader dataReader;
	public static ThreadLocal<TestEnvironmentDataRow> workingDataRow = new ThreadLocal<TestEnvironmentDataRow>();

	public TestEnvironmentActions() {
		super();
		setDataReader(new TestEnvironmentDataReader(this.excelUtility));
	}

	/**
	 * Executes postSurveySessionsFromDB3ToCloud action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean checkPostSurveySessionsFromDB3ToCloud(String data, Integer dataRowID)  throws Exception {
		logAction("TestEnvironmentActions.checkPostSurveySessionsFromDB3ToCloud", data, dataRowID);
		checkPostSurveySessionsFromDB3(dataRowID);
		return true;
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
			StringBuffer outAnalyzerSerialNumber = new StringBuffer();
			StringBuffer outAnalyzerSharedKey = new StringBuffer();
			getAnalyzerInfo(dataRow, outAnalyzerSerialNumber, outAnalyzerSharedKey);
			String analyzerSerialNumber = outAnalyzerSerialNumber.toString();
			String analyzerSharedKey = outAnalyzerSharedKey.toString();
			Log.info("[startAnalyzer] -> Updating Analyzer configuration");
			TestSetup.updateAnalyzerConfiguration(TestContext.INSTANCE.getBaseUrl(),
					analyzerSerialNumber, analyzerSharedKey);
			Log.info("[startAnalyzer] -> Restarting Analyzer");
			TestSetup.restartAnalyzer();
			Log.info("[startAnalyzer] -> Restarted Analyzer");
			// When the Analyzer is started store the working data row.
			workingDataRow.set(dataRow);
			Log.info("[startAnalyzer] -> Set working dataRow");
		} catch (Exception e) {
			Log.error(e.toString());
			Log.info("[startAnalyzer] -> Encountered error. Returning FALSE.");
			return false;
		}

		Log.info("[startAnalyzer] -> Successful!");
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
			// When the Analyzer is stopped clear off the working data row.
			workingDataRow.set(null);
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
		new NetworkProxyHandler().startNetworkProxy(true);
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
		new NetworkProxyHandler().stopNetworkProxy();
		return true;
	}

	/* Invoke action using specified ActionName */
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("postSurveySessionsFromDB3ToCloud")) { return this.checkPostSurveySessionsFromDB3ToCloud(data, dataRowID); }
		else if (actionName.equals("startReplay")) { return this.startReplay(data, dataRowID); }
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

	public String getWorkingAnalyzerSerialNumber() throws NumberFormatException, Exception {
		if (TestEnvironmentActions.workingDataRow.get() != null) {
			if (!ActionArguments.isEmpty(TestEnvironmentActions.workingDataRow.get().analyzerRowID)) {
				if (ManageAnalyzerPageActions.workingDataRow.get() != null) {
					return ManageAnalyzerPageActions.workingDataRow.get().serialNumber;
				} else {
					AnalyzerDataReader analyzerDataReader = new AnalyzerDataReader(excelUtility);
					AnalyzerDataRow analyzerDataRow = analyzerDataReader.getDataRow(Integer.valueOf(TestEnvironmentActions.workingDataRow.get().analyzerRowID));
					return analyzerDataRow.serialNumber;
				}
			} else {
				return TestEnvironmentActions.workingDataRow.get().analyzerSerialNumber;
			}
		}
		return TestSetup.TEST_ANALYZER_SERIAL_NUMBER;
	}

	private void getAnalyzerInfo(TestEnvironmentDataRow dataRow, StringBuffer outAnalyzerSerialNumber, StringBuffer outAnalyzerSharedKey) throws NumberFormatException, Exception {
		String analyzerSerialNumber = null;
		String analyzerSharedKey = null;
		if (!ActionArguments.isEmpty(dataRow.analyzerRowID)) {
			if (ManageAnalyzerPageActions.workingDataRow.get() != null) {
				analyzerSerialNumber = ManageAnalyzerPageActions.workingDataRow.get().serialNumber;
				analyzerSharedKey = ManageAnalyzerPageActions.workingDataRow.get().sharedKey;
			} else {
				AnalyzerDataReader analyzerDataReader = new AnalyzerDataReader(this.excelUtility);
				AnalyzerDataRow analyzerDataRow = analyzerDataReader.getDataRow(Integer.valueOf(dataRow.analyzerRowID));
				analyzerSerialNumber = analyzerDataRow.serialNumber;
				analyzerSharedKey = analyzerDataRow.sharedKey;
			}
		} else {
			analyzerSerialNumber = dataRow.analyzerSerialNumber;
			analyzerSharedKey = dataRow.analyzerSharedKey;
		}

		outAnalyzerSerialNumber.append(analyzerSerialNumber);
		outAnalyzerSharedKey.append(analyzerSharedKey);
	}

	private void checkPostSurveySessionsFromDB3(Integer dataRowID) throws Exception, IOException {
		TestEnvironmentDataRow dataRow = getDataReader().getDataRow(dataRowID);
		StringBuffer outAnalyzerSerialNumber = new StringBuffer();
		StringBuffer outAnalyzerSharedKey = new StringBuffer();
		getAnalyzerInfo(dataRow, outAnalyzerSerialNumber, outAnalyzerSharedKey);
		String analyzerSerialNumber = outAnalyzerSerialNumber.toString();
		String analyzerSharedKey = outAnalyzerSharedKey.toString();
		Analyzer objAnalyzer = Analyzer.getAnalyzerBySerialNumber(analyzerSerialNumber);
		SurveyorUnit objSurveyorUnit = SurveyorUnit.getSurveyorUnitById(String.valueOf(objAnalyzer.getSurveyorUnitId()));
		String surveyor = objSurveyorUnit.getDescription();
		TestContext.INSTANCE.getTestSetup().checkPostSurveySessionFromDB3(analyzerSerialNumber, analyzerSharedKey, surveyor);
	}

	/**
	 * Generates a survey with specified Analyzer/Surveyor/DB3 for the specified user.
	 * Remarks:
	 *  User, Analyzer/Surveyor/DB3, Survey information specified as parameter to method are used for survey creation.
	 * 	Environment (Web app, DB information) specified from Test Properties file is used for survey creation.
	 * @param loginUserRowID - user to generate survey for.
	 * @param db3AnalyzerRowID - analyzer/surveyor/DB3 rowID to use for the survey.
	 * @param surveyRowID - survey information rowID.
	 * @param surveyRuntimeInSeconds - number of seconds to run the survey for.
	 * @throws Exception
	 */
	public static void generateSurveyForUser(int loginUserRowID, int db3AnalyzerRowID, int surveyRowID,
			int surveyRuntimeInSeconds) throws Exception {
		LoginPageActions loginPageAction = ActionBuilder.createLoginPageAction();
		DriverViewPageActions driverViewPageAction = ActionBuilder.createDriverViewPageAction();
		TestEnvironmentActions testEnvironmentAction = ActionBuilder.createTestEnvironmentAction();

		LoginPageActions.clearStoredObjects();

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, loginUserRowID);

		generateSurveyForUser(db3AnalyzerRowID, surveyRowID, surveyRuntimeInSeconds, driverViewPageAction, testEnvironmentAction);
	}

	/**
	 * Generates a survey with specified Analyzer/Surveyor/DB3 for the specified user.
	 * Use this method if the username/password for the newly generated user is a dynamic string.
	 * For eg. if you used 'GenerateRandomEmail(20)' for username then use this overload for generating survey for user.
	 * Remarks:
	 *  User, Analyzer/Surveyor/DB3, Survey information specified as parameter to method are used for survey creation.
	 * 	Environment (Web app, DB information) specified from Test Properties file is used for survey creation.
	 * @param loginUserRowID - user to generate survey for.
	 * @param db3AnalyzerRowID - analyzer/surveyor/DB3 rowID to use for the survey.
	 * @param surveyRowID - survey information rowID.
	 * @param surveyRuntimeInSeconds - number of seconds to run the survey for.
	 * @throws Exception
	 */
	public static void generateSurveyForUser(String username, String password, int db3AnalyzerRowID, int surveyRowID,
			int surveyRuntimeInSeconds) throws Exception {
		LoginPageActions loginPageAction = ActionBuilder.createLoginPageAction();
		DriverViewPageActions driverViewPageAction = ActionBuilder.createDriverViewPageAction();
		TestEnvironmentActions testEnvironmentAction = ActionBuilder.createTestEnvironmentAction();

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(String.format("%s:%s", username, password), NOTSET);

		generateSurveyForUser(db3AnalyzerRowID, surveyRowID, surveyRuntimeInSeconds, driverViewPageAction, testEnvironmentAction);
	}

	private static void generateSurveyForUser(int db3AnalyzerRowID, int surveyRowID, int surveyRuntimeInSeconds,
			DriverViewPageActions driverViewPageAction, TestEnvironmentActions testEnvironmentAction) throws Exception {
		testEnvironmentAction.startAnalyzer(EMPTY, db3AnalyzerRowID);
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		testEnvironmentAction.startReplay(EMPTY, db3AnalyzerRowID);
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, surveyRowID);
		testEnvironmentAction.idleForSeconds(String.valueOf(surveyRuntimeInSeconds), NOTSET);
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);
		testEnvironmentAction.idleForSeconds(String.valueOf(20), NOTSET);
		testEnvironmentAction.checkPostSurveySessionsFromDB3ToCloud(EMPTY, db3AnalyzerRowID);
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}
}

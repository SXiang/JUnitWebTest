package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.scommon.source.SurveyorTestRunner;

public class DriverViewDataProvider extends SurveyorTestRunner {
	public static final String DRIVERVIEW_MISSING_COLUMN_VALUES_PROVIDER = "dataProviderDriverViewMissingColumnValues";
	public static final String DRIVERVIEW_START_STOP_SURVEY_METH_SHORTDB3_PROVIDER = "dataProviderStartStopSurveyMethShortDB3";
	public static final String DRIVERVIEW_START_STOP_SURVEY_METH_LONGDB3_PROVIDER = "dataProviderStartStopSurveyMethLongDB3";
	public static final String DRIVERVIEW_START_STOP_SURVEY_ETH_PROVIDER = "dataProviderStartStopSurveyEth";
	public static final String DRIVERVIEW_COMPARE_INDICATIONS_DRIVERVIEW_SURVEYVIEW_PROVIDER = "dataProviderCompareIndicationsDriverViewSurveyView";
	public static final String DRIVERVIEW_RAWDATA_UPDATES_TC2411_2412_2413_2414 = "dataProviderRawDataUpdates_TC2411_2412_2413_2414";

	public DriverViewDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	@DataProvider
	public static Object[][] dataProviderRawDataUpdates_TC2411_2412_2413_2414() throws Exception {

		// ethane - bad iGPS (GpsFit=6, PeripheralStatus=98304), GPSLat/Long shifted by 0.5
		final int userDataRowID1 = 16;
		final int analyzerDb3DataRowID1 = 62;
		final int surveyRuntimeInSeconds1 = 60;
		final int surveyDataRowID1 = 3;

		// ethane - generate Peak with NaN Wind
		final int userDataRowID2 = 16;
		final int analyzerDb3DataRowID2 = 63;
		final int surveyRuntimeInSeconds2 = 600;
		final int surveyDataRowID2 = 4;

		// ethane - generate Peak with NaN EthaneRatio
		final int userDataRowID3 = 16;
		final int analyzerDb3DataRowID3 = 64;
		final int surveyRuntimeInSeconds3 = 60;
		final int surveyDataRowID3 = 5;

		// ethane - generate Peak with bad GPS (GpsFit=0, GpsLat/Long=NaN)
		final int userDataRowID4 = 16;
		final int analyzerDb3DataRowID4 = 65;
		final int surveyRuntimeInSeconds4 = 60;
		final int surveyDataRowID4 = 6;

		return new Object[][] {
			{ "TC2411", userDataRowID1, analyzerDb3DataRowID1, surveyRuntimeInSeconds1, surveyDataRowID1 },
			{ "TC2412", userDataRowID2, analyzerDb3DataRowID2, surveyRuntimeInSeconds2, surveyDataRowID2 },
			{ "TC2413", userDataRowID3, analyzerDb3DataRowID3, surveyRuntimeInSeconds3, surveyDataRowID3 },
			{ "TC2414", userDataRowID4, analyzerDb3DataRowID4, surveyRuntimeInSeconds4, surveyDataRowID4 },
		};
	}

	@DataProvider
	public static Object[][] dataProviderDriverViewMissingColumnValues() throws Exception {

		// methane - ch4 missing
		final int userDataRowID2 = 16;
		final int analyzerDb3DataRowID2 = 45;
		final int surveyRuntimeInSeconds2 = 200;
		final int surveyDataRowID2 = 3;

		// ethane - ch4 missing
		final int userDataRowID4 = 16;
		final int analyzerDb3DataRowID4 = 47;
		final int surveyRuntimeInSeconds4 = 200;
		final int surveyDataRowID4 = 3;

		// ethane - c2h6 missing
		final int userDataRowID5 = 16;
		final int analyzerDb3DataRowID5 = 48;
		final int surveyRuntimeInSeconds5 = 200;
		final int surveyDataRowID5 = 3;

		// ethane - ch4 & c2h6 missing
		final int userDataRowID6 = 16;
		final int analyzerDb3DataRowID6 = 49;
		final int surveyRuntimeInSeconds6 = 200;
		final int surveyDataRowID6 = 3;

		return new Object[][] {
			{ userDataRowID2, analyzerDb3DataRowID2, surveyRuntimeInSeconds2, surveyDataRowID2 },
			{ userDataRowID4, analyzerDb3DataRowID4, surveyRuntimeInSeconds4, surveyDataRowID4 },
			{ userDataRowID5, analyzerDb3DataRowID5, surveyRuntimeInSeconds5, surveyDataRowID5 },
			{ userDataRowID6, analyzerDb3DataRowID6, surveyRuntimeInSeconds6, surveyDataRowID6 },
		};
	}

	@DataProvider
	public static Object[][] dataProviderCompareIndicationsDriverViewSurveyView() throws Exception {

		// methane - manual
		final int userDataRowID1 = 17;          // utility admin.
		final int analyzerDb3DataRowID1 = 9;
		final int surveyRuntimeInSeconds1 = 40;
		final int surveyDataRowID1 = 24;

		// methane - rr
		final int userDataRowID2 = 16;          // driver.
		final int analyzerDb3DataRowID2 = 23;
		final int surveyRuntimeInSeconds2 = 60;
		final int surveyDataRowID2 = 27;

		// methane - std
		final int userDataRowID3 = 16;          // driver.
		final int analyzerDb3DataRowID3 = 52;
		final int surveyRuntimeInSeconds3 = 330;
		final int surveyDataRowID3 = 3;

		// ethane - manual
		final int userDataRowID4 = 17;          // utility admin.
		final int analyzerDb3DataRowID4 = 53;
		final int surveyRuntimeInSeconds4 = 180;
		final int surveyDataRowID4 = 32;

		// ethane - std
		final int userDataRowID5 = 16;          // driver.
		final int analyzerDb3DataRowID5 = 54;
		final int surveyRuntimeInSeconds5 = 180;
		final int surveyDataRowID5 = 4;

		// methane - operator
		final int userDataRowID6 = 16;          // driver.
		final int analyzerDb3DataRowID6 = 20;
		final int surveyRuntimeInSeconds6 = 90;
		final int surveyDataRowID6 = 5;

		return new Object[][] {
			{ userDataRowID1, analyzerDb3DataRowID1, surveyRuntimeInSeconds1, surveyDataRowID1 },
			{ userDataRowID2, analyzerDb3DataRowID2, surveyRuntimeInSeconds2, surveyDataRowID2 },
			{ userDataRowID3, analyzerDb3DataRowID3, surveyRuntimeInSeconds3, surveyDataRowID3 },
			{ userDataRowID4, analyzerDb3DataRowID4, surveyRuntimeInSeconds4, surveyDataRowID4 },
			{ userDataRowID5, analyzerDb3DataRowID5, surveyRuntimeInSeconds5, surveyDataRowID5 },
			{ userDataRowID6, analyzerDb3DataRowID6, surveyRuntimeInSeconds6, surveyDataRowID6 },
		};
	}

	@DataProvider
	public static Object[][] dataProviderStartStopSurveyMethShortDB3() throws Exception {
		// standard surveys - shorter DB3, shorter surveys.
		final Integer userDataRowID1 = 17;
		final Integer analyzerDb3DataRowID1 = 52;
		final Integer[] surveyDataRowIDs1 = {3,4,6,7,11,12,17,18,21,22,23};
		final Integer surveyRuntimeInSeconds1 = 10;
		final Integer numberOfSurveys1 = 3;

		// manual surveys - shorter DB3, shorter surveys.
		final Integer userDataRowID2 = 17;
		final Integer analyzerDb3DataRowID2 = 50;
		final Integer[] surveyDataRowIDs2 = {24,31,32,36,41};
		final Integer surveyRuntimeInSeconds2 = 30;
		final Integer numberOfSurveys2 = 3;

		// operator surveys - shorter DB3, shorter surveys.
		final Integer userDataRowID3 = 17;
		final Integer analyzerDb3DataRowID3 = 55;
		final Integer[] surveyDataRowIDs3 = {5,35,37,38,39,40};
		final Integer surveyRuntimeInSeconds3 = 3;
		final Integer numberOfSurveys3 = 20;

		// rapid response surveys - shorter DB3, shorter surveys.
		final Integer userDataRowID4 = 17;
		final Integer analyzerDb3DataRowID4 = 51;
		final Integer[] surveyDataRowIDs4 = {8,9,13,14,15,16,25,26,27,28,29};
		final Integer surveyRuntimeInSeconds4 = 3;
		final Integer numberOfSurveys4 = 20;

		return new Object[][] {
			{ userDataRowID1, analyzerDb3DataRowID1, surveyDataRowIDs1, surveyRuntimeInSeconds1, numberOfSurveys1 },
			{ userDataRowID2, analyzerDb3DataRowID2, surveyDataRowIDs2, surveyRuntimeInSeconds2, numberOfSurveys2 },
			{ userDataRowID3, analyzerDb3DataRowID3, surveyDataRowIDs3, surveyRuntimeInSeconds3, numberOfSurveys3 },
			{ userDataRowID4, analyzerDb3DataRowID4, surveyDataRowIDs4, surveyRuntimeInSeconds4, numberOfSurveys4 },
		};
	}

	@DataProvider
	public static Object[][] dataProviderStartStopSurveyMethLongDB3() throws Exception {
		// standard surveys - longer DB3, longer surveys.
		final Integer userDataRowID5 = 17;
		final Integer analyzerDb3DataRowID5 = 57;
		final Integer[] surveyDataRowIDs5 = {3,4,6,7,11,12,17,18,21,22,23};
		final Integer surveyRuntimeInSeconds5 = 30;
		final Integer numberOfSurveys5 = 100;

		// manual surveys - longer DB3, longer surveys.
		final Integer userDataRowID6 = 17;
		final Integer analyzerDb3DataRowID6 = 57;
		final Integer[] surveyDataRowIDs6 = {24,31,32,36,41};
		final Integer surveyRuntimeInSeconds6 = 30;
		final Integer numberOfSurveys6 = 100;

		// operator surveys - longer DB3, longer surveys.
		final Integer userDataRowID7 = 17;
		final Integer analyzerDb3DataRowID7 = 57;
		final Integer[] surveyDataRowIDs7 = {5,35,37,38,39,40};
		final Integer surveyRuntimeInSeconds7 = 30;
		final Integer numberOfSurveys7 = 100;

		// rapid response surveys - longer DB3, longer surveys.
		final Integer userDataRowID8 = 17;
		final Integer analyzerDb3DataRowID8 = 57;
		final Integer[] surveyDataRowIDs8 = {8,9,13,14,15,16,25,26,27,28,29};
		final Integer surveyRuntimeInSeconds8 = 30;
		final Integer numberOfSurveys8 = 100;

		return new Object[][] {
			{ userDataRowID5, analyzerDb3DataRowID5, surveyDataRowIDs5, surveyRuntimeInSeconds5, numberOfSurveys5 },
			{ userDataRowID6, analyzerDb3DataRowID6, surveyDataRowIDs6, surveyRuntimeInSeconds6, numberOfSurveys6 },
			{ userDataRowID7, analyzerDb3DataRowID7, surveyDataRowIDs7, surveyRuntimeInSeconds7, numberOfSurveys7 },
			{ userDataRowID8, analyzerDb3DataRowID8, surveyDataRowIDs8, surveyRuntimeInSeconds8, numberOfSurveys8 },
		};
	}

	@DataProvider
	public static Object[][] dataProviderStartStopSurveyEth() throws Exception {
		// standard surveys.
		final Integer userDataRowID1 = 17;
		final Integer analyzerDb3DataRowID1 = 54;
		final Integer[] surveyDataRowIDs1 = {3,4,6,7,11,12,17,18,21,22,23};
		final Integer surveyRuntimeInSeconds1 = 10;
		final Integer numberOfSurveys1 = 30;	// TODO: Increase when we have longer DB3.

		// manual surveys.
		final Integer userDataRowID2 = 17;
		final Integer analyzerDb3DataRowID2 = 53;
		final Integer[] surveyDataRowIDs2 = {24,31,32,36,41};
		final Integer surveyRuntimeInSeconds2 = 30;
		final Integer numberOfSurveys2 = 30;	// TODO: Increase when we have longer DB3.

		// operator surveys.
		final Integer userDataRowID3 = 17;
		final Integer analyzerDb3DataRowID3 = 56;
		final Integer[] surveyDataRowIDs3 = {5,35,37,38,39,40};
		final Integer surveyRuntimeInSeconds3 = 30;
		final Integer numberOfSurveys3 = 20;	// TODO: Increase when we have longer DB3.

		return new Object[][] {
			// TODO: Commenting. Need another DB3. Current DB3 stays in warming up state and will cause timeout in test.
			//{ userDataRowID1, analyzerDb3DataRowID1, surveyDataRowIDs1, surveyRuntimeInSeconds1, numberOfSurveys1 },
			{ userDataRowID2, analyzerDb3DataRowID2, surveyDataRowIDs2, surveyRuntimeInSeconds2, numberOfSurveys2 },
			{ userDataRowID3, analyzerDb3DataRowID3, surveyDataRowIDs3, surveyRuntimeInSeconds3, numberOfSurveys3 },
		};
	}
}
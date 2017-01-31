package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.scommon.source.SurveyorTestRunner;

public class DriverViewDataProvider extends SurveyorTestRunner {
	public static final String DRIVERVIEW_MISSING_COLUMN_VALUES_PROVIDER = "dataProviderDriverViewMissingColumnValues";
	public static final String DRIVERVIEW_START_STOP_SURVEY_METH_PROVIDER = "dataProviderStartStopSurveyMeth";
	public static final String DRIVERVIEW_START_STOP_SURVEY_ETH_PROVIDER = "dataProviderStartStopSurveyEth";
	public static final String DRIVERVIEW_COMPARE_INDICATIONS_DRIVERVIEW_SURVEYVIEW_PROVIDER = "dataProviderCompareIndicationsDriverViewSurveyView";

	public DriverViewDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	@DataProvider
	public static Object[][] dataProviderDriverViewMissingColumnValues() throws Exception {

		// methane - c2h6 missing
		final int userDataRowID1 = 16;
		final int analyzerDb3DataRowID1 = 44;
		final int surveyRuntimeInSeconds1 = 200;
		final int surveyDataRowID1 = 3;

		// methane - ch4 missing
		final int userDataRowID2 = 16;
		final int analyzerDb3DataRowID2 = 45;
		final int surveyRuntimeInSeconds2 = 200;
		final int surveyDataRowID2 = 3;

		// methane - ch4 & c2h6 missing
		final int userDataRowID3 = 16;
		final int analyzerDb3DataRowID3 = 46;
		final int surveyRuntimeInSeconds3 = 200;
		final int surveyDataRowID3 = 3;

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
			{ userDataRowID1, analyzerDb3DataRowID1, surveyRuntimeInSeconds1, surveyDataRowID1 },
			{ userDataRowID2, analyzerDb3DataRowID2, surveyRuntimeInSeconds2, surveyDataRowID2 },
			{ userDataRowID3, analyzerDb3DataRowID3, surveyRuntimeInSeconds3, surveyDataRowID3 },
			{ userDataRowID4, analyzerDb3DataRowID4, surveyRuntimeInSeconds4, surveyDataRowID4 },
			{ userDataRowID5, analyzerDb3DataRowID5, surveyRuntimeInSeconds5, surveyDataRowID5 },
			{ userDataRowID6, analyzerDb3DataRowID6, surveyRuntimeInSeconds6, surveyDataRowID6 },
		};
	}

	@DataProvider
	public static Object[][] dataProviderCompareIndicationsDriverViewSurveyView() throws Exception {

		// methane - manual
		final int userDataRowID1 = 17;          // utility admin.
		final int analyzerDb3DataRowID1 = 50;
		final int surveyRuntimeInSeconds1 = 300;
		final int surveyDataRowID1 = 24;

		// methane - rr
		final int userDataRowID2 = 16;          // driver.
		final int analyzerDb3DataRowID2 = 51;
		final int surveyRuntimeInSeconds2 = 300;
		final int surveyDataRowID2 = 27;

		// methane - std
		final int userDataRowID3 = 16;          // driver.
		final int analyzerDb3DataRowID3 = 52;
		final int surveyRuntimeInSeconds3 = 300;
		final int surveyDataRowID3 = 3;

		// ethane - manual
		final int userDataRowID4 = 17;          // utility admin.
		final int analyzerDb3DataRowID4 = 53;
		final int surveyRuntimeInSeconds4 = 300;
		final int surveyDataRowID4 = 32;

		// ethane - std
		final int userDataRowID5 = 16;          // driver.
		final int analyzerDb3DataRowID5 = 54;
		final int surveyRuntimeInSeconds5 = 300;
		final int surveyDataRowID5 = 4;

		// methane - operator
		final int userDataRowID6 = 16;          // driver.
		final int analyzerDb3DataRowID6 = 55;
		final int surveyRuntimeInSeconds6 = 100;
		final int surveyDataRowID6 = 5;

		return new Object[][] {
			//{ userDataRowID1, analyzerDb3DataRowID1, surveyRuntimeInSeconds1, surveyDataRowID1 },
			//{ userDataRowID2, analyzerDb3DataRowID2, surveyRuntimeInSeconds2, surveyDataRowID2 },
			//{ userDataRowID3, analyzerDb3DataRowID3, surveyRuntimeInSeconds3, surveyDataRowID3 },
			//{ userDataRowID4, analyzerDb3DataRowID4, surveyRuntimeInSeconds4, surveyDataRowID4 },
			//{ userDataRowID5, analyzerDb3DataRowID5, surveyRuntimeInSeconds5, surveyDataRowID5 },
			{ userDataRowID6, analyzerDb3DataRowID6, surveyRuntimeInSeconds6, surveyDataRowID6 },
		};
	}

	@DataProvider
	public static Object[][] dataProviderStartStopSurveyMeth() throws Exception {
		// standard surveys.
		final Integer userDataRowID1 = 17;
		final Integer analyzerDb3DataRowID1 = 52;
		final Integer[] surveyDataRowIDs1 = {3,4,6,7,11,12,17,18,21,22,23};
		final Integer surveyRuntimeInSeconds1 = 10;
		final Integer numberOfSurveys1 = 30;	// TODO: Increase when we have longer DB3.

		// manual surveys.
		final Integer userDataRowID2 = 17;
		final Integer analyzerDb3DataRowID2 = 50;
		final Integer[] surveyDataRowIDs2 = {24,31,32,36,41};
		final Integer surveyRuntimeInSeconds2 = 30;
		final Integer numberOfSurveys2 = 30;	// TODO: Increase when we have longer DB3.

		// operator surveys.
		final Integer userDataRowID3 = 17;
		final Integer analyzerDb3DataRowID3 = 55;
		final Integer[] surveyDataRowIDs3 = {5,35,37,38,39,40};
		final Integer surveyRuntimeInSeconds3 = 30;
		final Integer numberOfSurveys3 = 20;	// TODO: Increase when we have longer DB3.

		// rapid response surveys.
		final Integer userDataRowID4 = 17;
		final Integer analyzerDb3DataRowID4 = 51;
		final Integer[] surveyDataRowIDs4 = {8,9,13,14,15,16,25,26,27,28,29};
		final Integer surveyRuntimeInSeconds4 = 30;
		final Integer numberOfSurveys4 = 20;	// TODO: Increase when we have longer DB3.

		return new Object[][] {
			{ userDataRowID1, analyzerDb3DataRowID1, surveyDataRowIDs1, surveyRuntimeInSeconds1, numberOfSurveys1 },
			{ userDataRowID2, analyzerDb3DataRowID2, surveyDataRowIDs2, surveyRuntimeInSeconds2, numberOfSurveys2 },
			{ userDataRowID3, analyzerDb3DataRowID3, surveyDataRowIDs3, surveyRuntimeInSeconds3, numberOfSurveys3 },
			{ userDataRowID4, analyzerDb3DataRowID4, surveyDataRowIDs4, surveyRuntimeInSeconds4, numberOfSurveys4 },
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
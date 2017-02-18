package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class PerformanceReportJobDataProvider extends ReportDataProvider {

	public enum ReportJobTestCategory {
		Light ("Light"),
		Medium ("Medium"),
		High ("High"),
		UltraHigh ("UltraHigh"),
		LargeArea ("LargeArea"),
		LargePipes ("LargePipes");

		private final String name;

		ReportJobTestCategory(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	private static final int WARMUP_USER_ROW_ID = 10;
	private static final int WARMUP_REPORT_DATA_ROW_ID = 155;
	private static final int WARMUP_EXECUTIONS_FOR_BASELINES = 1;

	private static final int LIGHT_LOAD3_USER_ROW_ID = 10;
	private static final int LIGHT_LOAD3_REPORT_DATA_ROW_ID = 10;
	private static final int LIGHT_LOAD3_EXECUTIONS_FOR_BASELINES = 20;

	private static final int MEDIUM_LOAD3_USER_ROW_ID = 10;
	private static final int MEDIUM_LOAD3_REPORT_DATA_ROW_ID = 11;
	private static final int MEDIUM_LOAD3_EXECUTIONS_FOR_BASELINES = 15;

	private static final int HIGH_LOAD2_USER_ROW_ID = 15;
	private static final int HIGH_LOAD2_REPORT_DATA_ROW_ID = 12;
	private static final int HIGH_LOAD2_EXECUTIONS_FOR_BASELINES = 10;

	private static final int HIGH_LOAD4_USER_ROW_ID = 15;
	private static final int HIGH_LOAD4_REPORT_DATA_ROW_ID = 106;
	private static final int HIGH_LOAD4_EXECUTIONS_FOR_BASELINES = 10;

	private static final int HIGH_LOAD5_USER_ROW_ID = 15;
	private static final int HIGH_LOAD5_REPORT_DATA_ROW_ID = 108;
	private static final int HIGH_LOAD5_EXECUTIONS_FOR_BASELINES = 10;

	private static final int HIGH_LOAD6_USER_ROW_ID = 10;
	private static final int HIGH_LOAD6_REPORT_DATA_ROW_ID = 110;
	private static final int HIGH_LOAD6_EXECUTIONS_FOR_BASELINES = 10;

	private static final int HIGH_LOAD7_USER_ROW_ID = 15;
	private static final int HIGH_LOAD7_REPORT_DATA_ROW_ID = 144;
	private static final int HIGH_LOAD7_EXECUTIONS_FOR_BASELINES = 10;

	private static final int HIGH_LOAD8_USER_ROW_ID = 10;
	private static final int HIGH_LOAD8_REPORT_DATA_ROW_ID = 147;
	private static final int HIGH_LOAD8_EXECUTIONS_FOR_BASELINES = 10;

	private static final int ULTRA_HIGH_LOAD1_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD1_REPORT_DATA_ROW_ID = 13;
	private static final int ULTRA_HIGH_LOAD1_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD4_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD4_REPORT_DATA_ROW_ID = 107;
	private static final int ULTRA_HIGH_LOAD4_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD5_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD5_REPORT_DATA_ROW_ID = 109;
	private static final int ULTRA_HIGH_LOAD5_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD6_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD6_REPORT_DATA_ROW_ID = 111;
	private static final int ULTRA_HIGH_LOAD6_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD7_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD7_REPORT_DATA_ROW_ID = 145;
	private static final int ULTRA_HIGH_LOAD7_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD8_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD8_REPORT_DATA_ROW_ID = 146;
	private static final int ULTRA_HIGH_LOAD8_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD9_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD9_REPORT_DATA_ROW_ID = 148;
	private static final int ULTRA_HIGH_LOAD9_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_AREA1_USER_ROW_ID = 23;
	private static final int LARGE_AREA1_REPORT_DATA_ROW_ID = 149;
	private static final int LARGE_AREA1_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_AREA2_USER_ROW_ID = 24;
	private static final int LARGE_AREA2_REPORT_DATA_ROW_ID = 150;
	private static final int LARGE_AREA2_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_AREA3_USER_ROW_ID = 23;
	private static final int LARGE_AREA3_REPORT_DATA_ROW_ID = 151;
	private static final int LARGE_AREA3_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_PIPES1_USER_ROW_ID = 24;
	private static final int LARGE_PIPES1_REPORT_DATA_ROW_ID = 152;
	private static final int LARGE_PIPES1_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_PIPES2_USER_ROW_ID = 25;
	private static final int LARGE_PIPES2_REPORT_DATA_ROW_ID = 153;
	private static final int LARGE_PIPES2_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_PIPES3_USER_ROW_ID = 25;
	private static final int LARGE_PIPES3_REPORT_DATA_ROW_ID = 154;
	private static final int LARGE_PIPES3_EXECUTIONS_FOR_BASELINES = 5;

	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_WARMUP_SCRIPT = "dataProviderReportJobPerformanceWarmup";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_LIGHT_LOAD = "dataProviderReportJobPerformanceLight";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_MEDIUM_LOAD = "dataProviderReportJobPerformanceMedium";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_HIGH_LOAD = "dataProviderReportJobPerformanceHigh";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ULTRAHIGH_LOAD = "dataProviderReportJobPerformanceUltraHigh";

	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_LARGEAREA = "dataProviderReportJobPerformanceLargeArea";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_LARGEPIPES = "dataProviderReportJobPerformanceLargePipes";

	public PerformanceReportJobDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceLight() {

		return new Object[][] {
			{ "TC1841" /*TestCaseID*/, LIGHT_LOAD3_USER_ROW_ID  /*userDataRowID*/,
			   LIGHT_LOAD3_REPORT_DATA_ROW_ID /*reportDataRowID*/,
			   LIGHT_LOAD3_EXECUTIONS_FOR_BASELINES /*number of times to execute this test when generating baselines*/,
			   ReportJobTestCategory.Light.toString()},
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceMedium() {

		return new Object[][] {
			{ "TC1842", MEDIUM_LOAD3_USER_ROW_ID, MEDIUM_LOAD3_REPORT_DATA_ROW_ID, MEDIUM_LOAD3_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.Medium.toString() },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceWarmup() {

		return new Object[][] {
			{ "PerfWarmupScript", WARMUP_USER_ROW_ID, WARMUP_REPORT_DATA_ROW_ID, WARMUP_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.Medium.toString() },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceHigh() {

		return new Object[][] {
			{ "TC1843-31", HIGH_LOAD8_USER_ROW_ID, HIGH_LOAD8_REPORT_DATA_ROW_ID, HIGH_LOAD8_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() },
			{ "TC1843-11", HIGH_LOAD7_USER_ROW_ID, HIGH_LOAD7_REPORT_DATA_ROW_ID, HIGH_LOAD7_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() },
			{ "TC1843-3", HIGH_LOAD6_USER_ROW_ID, HIGH_LOAD6_REPORT_DATA_ROW_ID, HIGH_LOAD6_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() },
			// Disabling. DE2212 prevents this test case from completing.
			//{ "TC1843-2", HIGH_LOAD5_USER_ROW_ID, HIGH_LOAD5_REPORT_DATA_ROW_ID, HIGH_LOAD5_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() },
			{ "TC1843-1", HIGH_LOAD4_USER_ROW_ID, HIGH_LOAD4_REPORT_DATA_ROW_ID, HIGH_LOAD4_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() },
			{ "TC1843", HIGH_LOAD2_USER_ROW_ID, HIGH_LOAD2_REPORT_DATA_ROW_ID, HIGH_LOAD2_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() }
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceUltraHigh() {

		return new Object[][] {
			{ "TC1844-31", ULTRA_HIGH_LOAD9_USER_ROW_ID, ULTRA_HIGH_LOAD9_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD9_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844-21", ULTRA_HIGH_LOAD8_USER_ROW_ID, ULTRA_HIGH_LOAD8_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD8_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844-11", ULTRA_HIGH_LOAD7_USER_ROW_ID, ULTRA_HIGH_LOAD7_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD7_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844-3", ULTRA_HIGH_LOAD6_USER_ROW_ID, ULTRA_HIGH_LOAD6_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD6_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844-2", ULTRA_HIGH_LOAD5_USER_ROW_ID, ULTRA_HIGH_LOAD5_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD5_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844-1", ULTRA_HIGH_LOAD4_USER_ROW_ID, ULTRA_HIGH_LOAD4_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD4_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844", ULTRA_HIGH_LOAD1_USER_ROW_ID, ULTRA_HIGH_LOAD1_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD1_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() }
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceLargeArea() {

		return new Object[][] {
			// TODO: Disabled test case until DE2750 is fixed.
			//{ "TC2315-1", LARGE_AREA1_USER_ROW_ID, LARGE_AREA1_REPORT_DATA_ROW_ID, LARGE_AREA1_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargeArea.toString() },
			{ "TC2315-2", LARGE_AREA2_USER_ROW_ID, LARGE_AREA2_REPORT_DATA_ROW_ID, LARGE_AREA2_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargeArea.toString() },
			{ "TC2315-3", LARGE_AREA3_USER_ROW_ID, LARGE_AREA3_REPORT_DATA_ROW_ID, LARGE_AREA3_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargeArea.toString() },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceLargePipes() {

		return new Object[][] {
			{ "TC2316-1", LARGE_PIPES1_USER_ROW_ID, LARGE_PIPES1_REPORT_DATA_ROW_ID, LARGE_PIPES1_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargePipes.toString() },
			{ "TC2316-2", LARGE_PIPES2_USER_ROW_ID, LARGE_PIPES2_REPORT_DATA_ROW_ID, LARGE_PIPES2_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargePipes.toString() },
			{ "TC2316-3", LARGE_PIPES3_USER_ROW_ID, LARGE_PIPES3_REPORT_DATA_ROW_ID, LARGE_PIPES3_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargePipes.toString() },
		};
	}
}
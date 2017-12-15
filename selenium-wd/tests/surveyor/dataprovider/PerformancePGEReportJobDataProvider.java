package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class PerformancePGEReportJobDataProvider extends ReportDataProvider {

	public PerformancePGEReportJobDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_PGE1 = "dataProviderReportJobPerformancePGE1";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_PGE2 = "dataProviderReportJobPerformancePGE2";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_PGE3 = "dataProviderReportJobPerformancePGE3";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_PGE4 = "dataProviderReportJobPerformancePGE4";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_PGE5 = "dataProviderReportJobPerformancePGE5";

	private static final int PGEPERF0001_REPORT_DATA_ROW_ID = 313;
	private static final int PGEPERF0002_REPORT_DATA_ROW_ID = 314;
	private static final int PGEPERF0003_REPORT_DATA_ROW_ID = 315;
	private static final int PGEPERF0004_REPORT_DATA_ROW_ID = 316;
	private static final int PGEPERF0005_REPORT_DATA_ROW_ID = 317;
	private static final int PGEPERF0006_REPORT_DATA_ROW_ID = 318;
	private static final int PGEPERF0007_REPORT_DATA_ROW_ID = 319;
	private static final int PGEPERF0008_REPORT_DATA_ROW_ID = 320;
	private static final int PGEPERF0009_REPORT_DATA_ROW_ID = 321;
	private static final int PGEPERF0010_REPORT_DATA_ROW_ID = 322;
	private static final int PGEPERF0011_REPORT_DATA_ROW_ID = 323;
	private static final int PGEPERF0012_REPORT_DATA_ROW_ID = 324;
	private static final int PGEPERF0013_REPORT_DATA_ROW_ID = 325;
	private static final int PGEPERF0014_REPORT_DATA_ROW_ID = 326;
	private static final int PGEPERF0015_REPORT_DATA_ROW_ID = 327;
	private static final int PGEPERF0016_REPORT_DATA_ROW_ID = 328;
	private static final int PGEPERF0017_REPORT_DATA_ROW_ID = 329;
	private static final int PGEPERF0018_REPORT_DATA_ROW_ID = 330;
	private static final int PGEPERF0019_REPORT_DATA_ROW_ID = 331;
	private static final int PGEPERF0020_REPORT_DATA_ROW_ID = 332;
	private static final int PGEPERF0021_REPORT_DATA_ROW_ID = 333;
	private static final int PGEPERF0022_REPORT_DATA_ROW_ID = 334;
	private static final int PGEPERF0023_REPORT_DATA_ROW_ID = 335;
	private static final int PGEPERF0024_REPORT_DATA_ROW_ID = 336;
	private static final int PGEPERF0025_REPORT_DATA_ROW_ID = 337;
	private static final int PGEPERF0026_REPORT_DATA_ROW_ID = 338;
	private static final int PGEPERF0027_REPORT_DATA_ROW_ID = 339;
	private static final int PGEPERF0028_REPORT_DATA_ROW_ID = 340;
	private static final int PGEPERF0029_REPORT_DATA_ROW_ID = 341;
	private static final int PGEPERF0030_REPORT_DATA_ROW_ID = 342;
	private static final int PGEPERF0031_REPORT_DATA_ROW_ID = 343;
	private static final int PGEPERF0032_REPORT_DATA_ROW_ID = 344;
	private static final int PGEPERF0033_REPORT_DATA_ROW_ID = 345;
	private static final int PGEPERF0034_REPORT_DATA_ROW_ID = 346;
	private static final int PGEPERF0035_REPORT_DATA_ROW_ID = 347;
	private static final int PGEPERF0036_REPORT_DATA_ROW_ID = 348;
	private static final int PGEPERF0037_REPORT_DATA_ROW_ID = 349;
	private static final int PGEPERF0038_REPORT_DATA_ROW_ID = 350;
	private static final int PGEPERF0039_REPORT_DATA_ROW_ID = 351;
	private static final int PGEPERF0040_REPORT_DATA_ROW_ID = 352;
	private static final int PGEPERF0041_REPORT_DATA_ROW_ID = 353;
	private static final int PGEPERF0042_REPORT_DATA_ROW_ID = 354;

	private static final int PGEPERF0001_USER_ROW_ID = 37;
	private static final int PGEPERF0002_USER_ROW_ID = 37;
	private static final int PGEPERF0003_USER_ROW_ID = 37;
	private static final int PGEPERF0004_USER_ROW_ID = 37;
	private static final int PGEPERF0005_USER_ROW_ID = 35;
	private static final int PGEPERF0006_USER_ROW_ID = 35;
	private static final int PGEPERF0007_USER_ROW_ID = 37;
	private static final int PGEPERF0008_USER_ROW_ID = 36;
	private static final int PGEPERF0009_USER_ROW_ID = 36;
	private static final int PGEPERF0010_USER_ROW_ID = 35;
	private static final int PGEPERF0011_USER_ROW_ID = 34;
	private static final int PGEPERF0012_USER_ROW_ID = 36;
	private static final int PGEPERF0013_USER_ROW_ID = 34;
	private static final int PGEPERF0014_USER_ROW_ID = 34;
	private static final int PGEPERF0015_USER_ROW_ID = 35;
	private static final int PGEPERF0016_USER_ROW_ID = 35;
	private static final int PGEPERF0017_USER_ROW_ID = 35;
	private static final int PGEPERF0018_USER_ROW_ID = 35;
	private static final int PGEPERF0019_USER_ROW_ID = 35;
	private static final int PGEPERF0020_USER_ROW_ID = 35;
	private static final int PGEPERF0021_USER_ROW_ID = 34;
	private static final int PGEPERF0022_USER_ROW_ID = 35;
	private static final int PGEPERF0023_USER_ROW_ID = 35;
	private static final int PGEPERF0024_USER_ROW_ID = 34;
	private static final int PGEPERF0025_USER_ROW_ID = 34;
	private static final int PGEPERF0026_USER_ROW_ID = 34;
	private static final int PGEPERF0027_USER_ROW_ID = 34;
	private static final int PGEPERF0028_USER_ROW_ID = 34;
	private static final int PGEPERF0029_USER_ROW_ID = 34;
	private static final int PGEPERF0030_USER_ROW_ID = 35;
	private static final int PGEPERF0031_USER_ROW_ID = 35;
	private static final int PGEPERF0032_USER_ROW_ID = 35;
	private static final int PGEPERF0033_USER_ROW_ID = 35;
	private static final int PGEPERF0034_USER_ROW_ID = 34;
	private static final int PGEPERF0035_USER_ROW_ID = 35;
	private static final int PGEPERF0036_USER_ROW_ID = 34;
	private static final int PGEPERF0037_USER_ROW_ID = 34;
	private static final int PGEPERF0038_USER_ROW_ID = 34;
	private static final int PGEPERF0039_USER_ROW_ID = 34;
	private static final int PGEPERF0040_USER_ROW_ID = 34;
	private static final int PGEPERF0041_USER_ROW_ID = 34;
	private static final int PGEPERF0042_USER_ROW_ID = 34;

	@DataProvider
	public static Object[][] dataProviderReportJobPerformancePGE1() {

		return new Object[][] {
			{ "PerfTestPGE-0001", PGEPERF0001_USER_ROW_ID, PGEPERF0001_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0002", PGEPERF0002_USER_ROW_ID, PGEPERF0002_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0003", PGEPERF0003_USER_ROW_ID, PGEPERF0003_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0004", PGEPERF0004_USER_ROW_ID, PGEPERF0004_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0005", PGEPERF0005_USER_ROW_ID, PGEPERF0005_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0006", PGEPERF0006_USER_ROW_ID, PGEPERF0006_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0007", PGEPERF0007_USER_ROW_ID, PGEPERF0007_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0008", PGEPERF0008_USER_ROW_ID, PGEPERF0008_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0009", PGEPERF0009_USER_ROW_ID, PGEPERF0009_REPORT_DATA_ROW_ID },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformancePGE2() {

		return new Object[][] {
			{ "PerfTestPGE-0010", PGEPERF0010_USER_ROW_ID, PGEPERF0010_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0011", PGEPERF0011_USER_ROW_ID, PGEPERF0011_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0012", PGEPERF0012_USER_ROW_ID, PGEPERF0012_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0013", PGEPERF0013_USER_ROW_ID, PGEPERF0013_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0014", PGEPERF0014_USER_ROW_ID, PGEPERF0014_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0015", PGEPERF0015_USER_ROW_ID, PGEPERF0015_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0016", PGEPERF0016_USER_ROW_ID, PGEPERF0016_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0017", PGEPERF0017_USER_ROW_ID, PGEPERF0017_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0018", PGEPERF0018_USER_ROW_ID, PGEPERF0018_REPORT_DATA_ROW_ID },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformancePGE3() {

		return new Object[][] {
			{ "PerfTestPGE-0019", PGEPERF0019_USER_ROW_ID, PGEPERF0019_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0020", PGEPERF0020_USER_ROW_ID, PGEPERF0020_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0021", PGEPERF0021_USER_ROW_ID, PGEPERF0021_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0022", PGEPERF0022_USER_ROW_ID, PGEPERF0022_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0023", PGEPERF0023_USER_ROW_ID, PGEPERF0023_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0024", PGEPERF0024_USER_ROW_ID, PGEPERF0024_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0025", PGEPERF0025_USER_ROW_ID, PGEPERF0025_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0026", PGEPERF0026_USER_ROW_ID, PGEPERF0026_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0027", PGEPERF0027_USER_ROW_ID, PGEPERF0027_REPORT_DATA_ROW_ID },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformancePGE4() {

		return new Object[][] {
			{ "PerfTestPGE-0028", PGEPERF0028_USER_ROW_ID, PGEPERF0028_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0029", PGEPERF0029_USER_ROW_ID, PGEPERF0029_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0030", PGEPERF0030_USER_ROW_ID, PGEPERF0030_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0031", PGEPERF0031_USER_ROW_ID, PGEPERF0031_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0032", PGEPERF0032_USER_ROW_ID, PGEPERF0032_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0033", PGEPERF0033_USER_ROW_ID, PGEPERF0033_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0034", PGEPERF0034_USER_ROW_ID, PGEPERF0034_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0035", PGEPERF0035_USER_ROW_ID, PGEPERF0035_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0036", PGEPERF0036_USER_ROW_ID, PGEPERF0036_REPORT_DATA_ROW_ID },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformancePGE5() {

		return new Object[][] {
			{ "PerfTestPGE-0037", PGEPERF0037_USER_ROW_ID, PGEPERF0037_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0038", PGEPERF0038_USER_ROW_ID, PGEPERF0038_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0039", PGEPERF0039_USER_ROW_ID, PGEPERF0039_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0040", PGEPERF0040_USER_ROW_ID, PGEPERF0040_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0041", PGEPERF0041_USER_ROW_ID, PGEPERF0041_REPORT_DATA_ROW_ID },
			{ "PerfTestPGE-0042", PGEPERF0042_USER_ROW_ID, PGEPERF0042_REPORT_DATA_ROW_ID },
		};
	}
}

package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class PerformanceReportJobDataProvider extends ReportDataProvider {
	
	public enum ReportJobTestCategory {
		Light ("Light"), 
		Medium ("Medium"), 
		High ("High"), 
		UltraHigh ("UltraHigh");
		
		private final String name;

		ReportJobTestCategory(String nm) {
			name = nm;
		}
		
		public String toString() {
			return this.name;
		}
	}
	
	private static final int LIGHT_LOAD3_USER_ROW_ID = 10;		  
	private static final int LIGHT_LOAD3_REPORT_DATA_ROW_ID = 10;
	private static final int LIGHT_LOAD3_EXECUTIONS_FOR_BASELINES = 20;

	private static final int MEDIUM_LOAD3_USER_ROW_ID = 10;
	private static final int MEDIUM_LOAD3_REPORT_DATA_ROW_ID = 11;
	private static final int MEDIUM_LOAD3_EXECUTIONS_FOR_BASELINES = 15;
	
	private static final int HIGH_LOAD2_USER_ROW_ID = 10;
	private static final int HIGH_LOAD2_REPORT_DATA_ROW_ID = 12;
	private static final int HIGH_LOAD3_EXECUTIONS_FOR_BASELINES = 10;

	private static final int ULTRA_HIGH_LOAD1_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD1_REPORT_DATA_ROW_ID = 13;
	private static final int ULTRA_HIGH_LOAD3_EXECUTIONS_FOR_BASELINES = 5;

	public static final String REPORT_JOB_PERFORMANCE_PROVIDER = "dataProviderReportJobPerformance";

	public PerformanceReportJobDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformance() {
		
		return new Object[][] {				
			{ "TC1841" /*TestCaseID*/, LIGHT_LOAD3_USER_ROW_ID  /*userDataRowID*/, 
			   LIGHT_LOAD3_REPORT_DATA_ROW_ID /*reportDataRowID*/, 
			   LIGHT_LOAD3_EXECUTIONS_FOR_BASELINES /*number of times to execute this test when generating baselines*/,
			   ReportJobTestCategory.Light.toString()},
			{ "TC1842", MEDIUM_LOAD3_USER_ROW_ID, MEDIUM_LOAD3_REPORT_DATA_ROW_ID, MEDIUM_LOAD3_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.Medium.toString() },
			{ "TC1843", HIGH_LOAD2_USER_ROW_ID, HIGH_LOAD2_REPORT_DATA_ROW_ID, HIGH_LOAD3_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() },
			{ "TC1844", ULTRA_HIGH_LOAD1_USER_ROW_ID, ULTRA_HIGH_LOAD1_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD3_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() }
		};
	}
}
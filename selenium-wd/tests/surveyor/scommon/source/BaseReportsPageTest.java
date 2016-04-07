package surveyor.scommon.source;

import java.util.HashMap;

import common.source.NumberUtility;
import common.source.TestContext;
import surveyor.scommon.source.Reports.ReportJobType;
import surveyor.scommon.source.SurveyorBaseTest;

public class BaseReportsPageTest extends SurveyorBaseTest {

	private boolean isCollectReportJobPerfMetric;
	private boolean isGenerateBaselineSSRSImages;
	private boolean isGenerateBaselineViewImages;
	private boolean isGenerateBaselineShapeFiles;
	
	private static HashMap<ReportJobType, NumberUtility> reportJobProcessingTimeNumberMap;

	public BaseReportsPageTest() {
		this.isCollectReportJobPerfMetric = TestContext.INSTANCE.getTestSetup().isCollectReportJobPerfMetric();
		this.isGenerateBaselineSSRSImages = TestContext.INSTANCE.getTestSetup().isGenerateBaselineSSRSImages();
		this.isGenerateBaselineViewImages = TestContext.INSTANCE.getTestSetup().isGenerateBaselineViewImages();
		this.isGenerateBaselineShapeFiles = TestContext.INSTANCE.getTestSetup().isGenerateBaselineShapeFiles();
		
		initializeProperties();
	}

	public static void initializeProperties() {
		reportJobProcessingTimeNumberMap = new HashMap<ReportJobType, NumberUtility>();
		reportJobProcessingTimeNumberMap.put(ReportJobType.DataGeneration, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.EQDataGeneration, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.EQMap, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.EQSSRS, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.Map, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.PercentCoverageForecast, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.ReportMeta, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.ShapeFile, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.SSRS, new NumberUtility());
	}
	
	public static Integer getReportJobRollingProcessingTimeAvg(ReportJobType reportJobType) {
		NumberUtility numberUtility = reportJobProcessingTimeNumberMap.get(reportJobType);
		return numberUtility.getMovingAverage();
	}

	public static void setRollingReportJobProcessingTime(ReportJobType reportJobType, Integer reportJobProcessingTime) {
		// Add number to NumberUtility of the job type.
		NumberUtility numberUtility = reportJobProcessingTimeNumberMap.get(reportJobType);
		numberUtility.addRollingNumber(reportJobProcessingTime);
		reportJobProcessingTimeNumberMap.put(reportJobType, numberUtility);
	}
}

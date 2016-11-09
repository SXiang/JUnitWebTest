package common.source;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

public class ExtentReportGenerator {
	public static String reportDir = "reports/";
	
	private static Map<String, ExtentReports> extentReportsMap = Collections.synchronizedMap(new HashMap<String, ExtentReports>());
	
	public static ExtentReports createExtentReport(String reportClassName, StringBuilder outReportFilePath) {
		String executionPath = null;
		try {
			executionPath = TestSetup.getExecutionPath(TestSetup.getRootPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String runEnvironment = TestContext.INSTANCE.getRunEnvironment();
		String reportFilePath = executionPath + reportDir + TestContext.INSTANCE.getTestReportCategory()+File.separator
				+ String.format("report-%s-%s.html", runEnvironment, reportClassName);
		outReportFilePath.append(reportFilePath);
		String configFilePath = executionPath + "tests" + File.separator + "extent-config.xml";

		ExtentReports extent = new ExtentReports(reportFilePath, true /* replaceExisting */, DisplayOrder.NEWEST_FIRST,
				NetworkMode.ONLINE, Locale.US);
		extent.addSystemInfo("ChromeDriver Version", "2.20");
		extent.addSystemInfo("Environment", runEnvironment);
		extent.loadConfig(new File(configFilePath));
		return extent;
	}
	
	public static ExtentReports getExtentReport(String reportClassName) {
		if (extentReportsMap.containsKey(reportClassName)) {
			return extentReportsMap.get(reportClassName);
		}
		
		return null;
	}
	
	public static void setExtentReport(ExtentReports extentReport, String reportClassName) {
		extentReportsMap.put(reportClassName, extentReport);
	}
}

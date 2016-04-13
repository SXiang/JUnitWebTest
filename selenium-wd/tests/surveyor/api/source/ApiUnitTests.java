package surveyor.api.source;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.source.FileUtility;
import common.source.Log;
import common.source.TestSetup;

public class ApiUnitTests {

	public ApiUnitTests() {
	}

	public static void main(String[] args) throws IOException {
		
		/************** ReportJobsStat Unit Tests **************/
		Log.info("Running test - test_ReportJobsStat_toJson() ...");
		test_ReportJobsStat_toJson();	
		Log.info("Running test - test_ReportJobsStat_fromJson() ...");
		test_ReportJobsStat_fromJson();
	}

	private static void test_ReportJobsStat_fromJson() throws IOException {
		Path testFilePath = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\apiutility-tests\\sample.json");
		String fileContents = FileUtility.readFileContents(testFilePath.toString());
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		ReportJobsStat objReportJobsStat = gson.fromJson(fileContents, ReportJobsStat.class);
		Log.info(gson.toJson(objReportJobsStat));
		Assert.assertTrue(objReportJobsStat != null && objReportJobsStat.Id.length() > 0 &&
				objReportJobsStat.ReportJobs != null && objReportJobsStat.ReportJobs.size() > 0);
		
	}
		
	private static void test_ReportJobsStat_toJson() {
		ReportJobsStat reportJobsStat = new ReportJobsStat();
		reportJobsStat.ReportTitle = "cc17066cba3d4f77b654";
		reportJobsStat.Id = "7f94a86f-f255-0330-abe6-39d7035967d7";
		reportJobsStat.ReportStatus = "Complete";
		reportJobsStat.ProcessingStarted = "/Date(1460040146887)/";
		reportJobsStat.ProcessingCompleted = "/Date(1460040257573)/";
		
		ReportJob reportJob1 = new ReportJob();
		reportJob1.ReportJobId = "47e22e8e-1b88-f65e-8104-39d703596806";
		reportJob1.ReportJobType = "SSRS";
		reportJob1.ReportJobStatus = "Complete";
		reportJob1.ProcessingStarted = "/Date(1460040154073)/";
		reportJob1.ProcessingCompleted = "/Date(1460040155980)/";

		ReportJob reportJob2 = new ReportJob();
		reportJob2.ReportJobId = "b1aedcd5-f084-2ae7-243f-39d703596816";
		reportJob2.ReportJobType = "ReportMeta";
		reportJob2.ReportJobStatus = "Complete";
		reportJob2.ProcessingStarted = "/Date(1460040156870)/";
		reportJob2.ProcessingCompleted = "/Date(1460040157807)/";
		
		reportJobsStat.ReportJobs = new ArrayList<ReportJob>();
		reportJobsStat.ReportJobs.add(reportJob1);
		reportJobsStat.ReportJobs.add(reportJob2);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonString = gson.toJson(reportJobsStat);
		Log.info(jsonString);
		Assert.assertTrue(jsonString != null && jsonString.length() > 0);
	}
}

package surveyor.regression.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import common.source.FileUtility;
import common.source.ShapeFileUtility;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.source.SurveyorBaseTest;

public class BaseReportsPageTest extends SurveyorBaseTest {

	private boolean isCollectReportJobPerfMetric;
	private boolean isGenerateBaselineSSRSImages;
	private boolean isGenerateBaselineViewImages;
	private boolean isGenerateBaselineShapeFiles;

	public BaseReportsPageTest() {
		this.isCollectReportJobPerfMetric = TestContext.INSTANCE.getTestSetup().isCollectReportJobPerfMetric();
		this.isGenerateBaselineSSRSImages = TestContext.INSTANCE.getTestSetup().isGenerateBaselineSSRSImages();
		this.isGenerateBaselineViewImages = TestContext.INSTANCE.getTestSetup().isGenerateBaselineViewImages();
		this.isGenerateBaselineShapeFiles = TestContext.INSTANCE.getTestSetup().isGenerateBaselineShapeFiles();
	}

	protected void generateBaselineSSRSImage(String testCaseID, String reportId, String startTime, String endTime, Integer processingTimeInMs) throws IOException {
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = rootFolder + File.separator + "perf-metric" 
				+ File.separator + "report-job-metrics" + File.separator + testCaseID;
		Path expectedFilePath = Paths.get(expectedDataFolderPath, String.format("%s.csv", testCaseID));
		String reportMetricString = String.format("%s,%s,%s,%d", reportId, startTime, endTime, processingTimeInMs);
		FileUtility.createOrWriteToExistingTextFile(expectedFilePath, reportMetricString);
	}

	protected void generateBaselineSSRSImage(String testCaseID, String imageFileFullPath) throws IOException {
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = rootFolder + File.separator + "test-expected-data" 
				+ File.separator + "ssrs-images" + File.separator + testCaseID;
		String expectedFilename = FileUtility.getFileName(imageFileFullPath);
		Path expectedFilePath = Paths.get(expectedDataFolderPath, expectedFilename);		
		FileUtils.copyFile(new File(imageFileFullPath), new File(expectedFilePath.toString()));		
	}

	protected void generateBaselineViewImage(String testCaseID, String imageFileFullPath) throws IOException {
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = rootFolder + File.separator + "test-expected-data" 
				+ File.separator + "view-images" + File.separator + testCaseID;
		String expectedFilename = FileUtility.getFileName(imageFileFullPath);
		Path expectedFilePath = Paths.get(expectedDataFolderPath, expectedFilename);		
		FileUtils.copyFile(new File(imageFileFullPath), new File(expectedFilePath.toString()));		
	}

	protected void generateBaselineShapeAndGeoJsonFiles(String testCaseID, String shapeFileFullPath) throws Exception {
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = rootFolder + File.separator + "test-expected-data" 
				+ File.separator + "shape-files" + File.separator + testCaseID;
		String expectedFilename = FileUtility.getFileName(shapeFileFullPath);
		String expectedFileExt = FileUtility.getFileExtension(shapeFileFullPath);
		String expectedFilenameWithoutExt = expectedFilename.replace(".shp", "");
		Path expectedFilePath = Paths.get(expectedDataFolderPath, expectedFilename);		
		FileUtils.copyFile(new File(shapeFileFullPath), new File(expectedFilePath.toString()));		
		
		// If specified file is .shp get GeoJson string for the shape file and store the .geojson.
		if (expectedFileExt == "shp") {
			String geoJsonString = ShapeFileUtility.convertShapeToGeoJson(shapeFileFullPath);
			Path expectedGeoJsonFilePath = Paths.get(expectedDataFolderPath, expectedFilenameWithoutExt + ".geojson");		
			FileUtility.createTextFile(expectedGeoJsonFilePath, geoJsonString);
		}
	}
}

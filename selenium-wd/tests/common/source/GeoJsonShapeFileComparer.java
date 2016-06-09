package common.source;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.skyscreamer.jsonassert.JSONAssert;

public class GeoJsonShapeFileComparer implements IShapeFileComparer {

	public GeoJsonShapeFileComparer() {
	}

	/**
	 * Converts the 2 specified shape files to GeoJson and asserts that the created JSON objects are the same.
	 */
	@Override
	public void assertEquals(String file1Path, String file2Path) throws Exception {
		String jsonString1 = ShapeToGeoJsonConverter.convertToJsonString(file1Path);
		String jsonString2 = ShapeToGeoJsonConverter.convertToJsonString(file2Path);
		Log.info("jsonString1=" + jsonString1);
		Log.info("jsonString2=" + jsonString2);
		JSONAssert.assertEquals(jsonString1, jsonString2, false /*strict*/);
	}
	
	public static void main(String[] args) throws Exception {
		Log.info("Running test - testShapeFileComparison_assertEquals_Success() ...");
		testGeoJsonFileComparison_assertEquals_Success();
		Log.info("Running test - testShapeFileComparison_assertEquals_Failure() ...");
		testGeoJsonFileComparison_assertEquals_Failure();
	}

	private static void testGeoJsonFileComparison_assertEquals_Success() throws Exception {
		Path shpDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\shapefileutility-tests\\shape-compare-data\\01");
		List<String> shpFilesInDirectory = FileUtility.getFilesInDirectory(shpDirectory, "*.shp");
		for (String filePath : shpFilesInDirectory) {
			String file1Path = filePath;
			String file2Path = file1Path.replace("\\01\\", "\\01-Copy\\");
			Log.info("---------------------------------------------------------------------");
			Log.info("Comparing Shape Files... ");
			Log.info(String.format("File1=[%s]", file1Path));
			Log.info(String.format("File2=[%s]", file2Path));
			GeoJsonShapeFileComparer comparer = new GeoJsonShapeFileComparer();
			comparer.assertEquals(file1Path, file2Path);
			Log.info("---------------------------------------------------------------------");
		}
	}

	private static void testGeoJsonFileComparison_assertEquals_Failure() throws Exception {
		Path shpDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\shapefileutility-tests");
		String file1Path = shpDirectory.toString() + File.separator + "CR-0AEE84-FOV.shp";
		String file2Path = shpDirectory.toString() + File.separator + "CR-0AEE84-BreadCrumb.shp";
		GeoJsonShapeFileComparer comparer = new GeoJsonShapeFileComparer();
		comparer.assertEquals(file1Path, file2Path);
	}

}

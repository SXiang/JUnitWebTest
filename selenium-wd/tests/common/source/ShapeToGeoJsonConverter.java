package common.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ShapeToGeoJsonConverter {

	private static final String CONVERT_SHAPE_TO_GEOJSON_CMD = "ConvertShapeToGeoJson.cmd";

	public ShapeToGeoJsonConverter() {
	}

	public static String convertToJsonString(String shapeFileFullPath) {
		StringBuilder builder = new StringBuilder();
		List<String> lines = ShapeToGeoJsonConverter.convertToList(shapeFileFullPath);
		for (String line : lines) {
			builder.append(line);
			builder.append(BaseHelper.getLineSeperator());
		}
		return builder.toString();
	}

	public static List<String> convertToList(String shapeFileFullPath) {
		List<String> geoJsonFileLines = null;

		try {
			// Create the .geojson file in the same folder as the .shp file. Build path.
			String shapeFileName = FileUtility.getFileName(shapeFileFullPath);
			String geojsonFileName = shapeFileName.replace(".shp", ".geojson");
			Path pathGeoJsonFile = Paths.get(Paths.get(shapeFileFullPath).getParent().toString(), geojsonFileName);
			String geoJsonFileFullPath = pathGeoJsonFile.toString();

			// Execute shape to geojson cmd with parameters.
			String shpToJsonCmdFullPath = CONVERT_SHAPE_TO_GEOJSON_CMD + " \"" + geoJsonFileFullPath +"\" \"" + shapeFileFullPath+"\"";
			executeShapeToGeoJsonCmd(shpToJsonCmdFullPath);
			// Get the lines from .geojson as list of strings.
			geoJsonFileLines = Files.readAllLines(pathGeoJsonFile);

			// Delete the working copy of the .geojson file.
			Files.delete(pathGeoJsonFile);
		} catch (IOException e) {
			Log.error(e.toString());
		}

		return geoJsonFileLines;
	}

	private static void executeShapeToGeoJsonCmd(String workingShpToJsonCmd) {
		// Execute update analyzer configuration script from the contained folder.
		try {
			String shpToJsonCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
			String shpToJsonCmdFileFullPath = shpToJsonCmdFolder + File.separator + workingShpToJsonCmd;
			String command = "cd \"" + shpToJsonCmdFolder + "\" && " + shpToJsonCmdFileFullPath;
			Log.info("Executing .shp to .geojson conversion script. Command -> " + command);
			ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public static void main(String[] args) {
		test_ShapeToGeoJsonConverter_ValidSmallFile_Success();
		test_ShapeToGeoJsonConverter_ValidBigFile_Success();
		test_ShapeToGeoJsonConverter_InvalidFiles_Failure();
	}

	private static void test_ShapeToGeoJsonConverter_ValidSmallFile_Success() {
	}

	private static void test_ShapeToGeoJsonConverter_ValidBigFile_Success() {
	}

	private static void test_ShapeToGeoJsonConverter_InvalidFiles_Failure() {
	}
}

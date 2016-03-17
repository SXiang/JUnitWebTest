package common.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Hashtable;
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
			builder.append(System.getProperty("line.separator"));
		}
		return builder.toString();
	}

	public static List<String> convertToList(String shapeFileFullPath) {
		List<String> geoJsonFileLines = null;
		
		try {
			String workingFolder = TestSetup.getExecutionPath(TestSetup.getRootPath());
			String libFolder = workingFolder + "lib";
			String shpToJsonCmdFullPath = libFolder + File.separator + CONVERT_SHAPE_TO_GEOJSON_CMD;

			String workingShpToJsonCmdFile = TestSetup.getUUIDString() + "_" + CONVERT_SHAPE_TO_GEOJSON_CMD;
			String workingShpToJsonCmdFullPath = Paths.get(libFolder + File.separator, workingShpToJsonCmdFile).toString();

			// Create a copy of the shape to geojson cmd file.
			Files.copy(Paths.get(shpToJsonCmdFullPath), Paths.get(workingShpToJsonCmdFullPath), StandardCopyOption.REPLACE_EXISTING);
			
			// Create the .geojson file in the same folder as the .shp file. Build path.
			String shapeFileName = FileUtility.getFileName(shapeFileFullPath);
			String geojsonFileName = shapeFileName.replace(".shp", ".geojson");
			Path pathGeoJsonFile = Paths.get(Paths.get(shapeFileFullPath).getParent().toString(), geojsonFileName);
			String geoJsonFileFullPath = pathGeoJsonFile.toString();

			// Update the working copy.
			Hashtable<String, String> placeholderMap = new Hashtable<String, String>();
			placeholderMap.put("%1%", geoJsonFileFullPath);
			placeholderMap.put("%2%", shapeFileFullPath);
			FileUtility.updateFile(workingShpToJsonCmdFullPath, placeholderMap);

			// Execute shape to geojson cmd.
			executeShapeToGeoJsonCmd(workingShpToJsonCmdFile);

			// Get the lines from .geojson as list of strings.
			geoJsonFileLines = Files.readAllLines(pathGeoJsonFile);
			
			// Delete the working copy of the shape to geojson cmd file and delete the .geojson file.
			Files.delete(Paths.get(workingShpToJsonCmdFullPath));
			Files.delete(pathGeoJsonFile);
		} catch (IOException e) {
			Log.error(e.toString());
		}

		return geoJsonFileLines;
	}
	
	private static void executeShapeToGeoJsonCmd(String workingShpToJsonCmdFile) {
		// Execute update analyzer configuration script from the contained folder.
		try {
			String shpToJsonCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
			String shpToJsonCmdFileFullPath = shpToJsonCmdFolder + File.separator + workingShpToJsonCmdFile;
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

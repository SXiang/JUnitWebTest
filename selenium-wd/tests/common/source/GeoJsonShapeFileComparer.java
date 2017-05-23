package common.source;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.junit.Assert.assertTrue;

public class GeoJsonShapeFileComparer implements IShapeFileComparer {

	private static final String ID_REPLACE_REGEX = "(\"ID\":\\s*)[\\.0-9]+(,)";
	private static final String LABEL_REPLACE_REGEX = "(\"Label\":\\s*\"[\\w\\s]+\\s)[0-9]+(\")";
	private static final String LABEL_MATCH_REGEX = "\"Label\":\\s*\"([\\w\\s]+\\s[0-9]+)\"";
	private static final String[][] incomparableValues = {{ID_REPLACE_REGEX, "$1000.0$2"},
			{LABEL_REPLACE_REGEX, "$1000$2"}
		};

	public GeoJsonShapeFileComparer() {
	}

	/**
	 * Converts the 2 specified shape files to GeoJson and asserts that the created JSON objects are the same.
	 */
	@Override
	public void assertEquals(String file1Path, String file2Path) throws Exception {
		assertEquals(file1Path, file2Path, true);
	}

	public void assertEquals(String file1Path, String file2Path, boolean ignoreIncomparableShapeValue) throws Exception{
		String jsonString1 = ShapeToGeoJsonConverter.convertToJsonString(file1Path);
		String jsonString2 = ShapeToGeoJsonConverter.convertToJsonString(file2Path);

		// ensure labels match (ignoring order)
		assertTrue(compareLabels(jsonString1, jsonString2));

		if(ignoreIncomparableShapeValue){
			jsonString1 = removeIncomparableShapeValue(jsonString1);
			jsonString2 = removeIncomparableShapeValue(jsonString2);
		}
		Log.info("jsonString1=" + jsonString1);
		Log.info("jsonString2=" + jsonString2);

		JSONAssert.assertEquals(jsonString1, jsonString2, JSONCompareMode.NON_EXTENSIBLE);
	}

	public static Set<String> extractLabels(String jsonString) {
		List<String> matchingGroups = RegexUtility.getMatchingGroups(jsonString, LABEL_MATCH_REGEX, true /*matchMultiple*/);

		if (matchingGroups.size() <= 1) {
			return null;
		}

		Set<String> groupSet = Collections.synchronizedSet(new HashSet<String>());

		for (int i = 1; i < matchingGroups.size(); i+=2) {
			groupSet.add(matchingGroups.get(i));
		}

		return groupSet;
	}

	private static boolean compareLabels(String jsonString1, String jsonString2) {
		Set<String> labelSet1 = extractLabels(jsonString1);
		Set<String> labelSet2 = extractLabels(jsonString2);

		if (labelSet1==null && labelSet2== null) {
			return true;
		}

		if ((labelSet1==null) || (labelSet2==null)) {
			Log.info(String.format("One label set EMPTY. Other is NOT. Empty label set=%d", (labelSet1==null) ? 1 : 2));
			return false;
		}

		if (labelSet1.size() != labelSet2.size()) {
			Log.info(String.format("Sizes for labels do NOT match. Label set1 size=%d. Label set2 size=%d.", labelSet1.size(), labelSet2.size()));
			return false;
		}

		Log.info(String.format("Labels detected in jsonString1 - '%s', ", LogHelper.setToString(labelSet1)));
		Log.info(String.format("Labels detected in jsonString2 - '%s', ", LogHelper.setToString(labelSet2)));
		return labelSet1.equals(labelSet2);
	}

	public static String removeIncomparableShapeValue(String jsonString){
		for(String[] value:incomparableValues){
			if(value.length < 2)
				continue;
			jsonString = jsonString.replaceAll(value[0], value[1]);
		}
		return jsonString;
	}

	// Unit tests
	public static void main(String[] args) throws Exception {
		Log.info("Running test - testShapeFileComparison_assertEquals_Success() ...");
		testGeoJsonFileComparison_assertEquals_Success();
		Log.info("Running test - testShapeFileComparison_assertEquals_Failure() ...");
		testGeoJsonFileComparison_assertEquals_Failure();

		Log.info("Running test - testShapeFileComparison_assertJsonEquals() ");
		testGeoJsonFileComparison_assertJsonEquals_IgnoreIncomparableValues();
	}

	private static void testGeoJsonFileComparison_assertJsonEquals_IgnoreIncomparableValues() throws Exception{
		String dir = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\shapefileutility-tests\\shape-compare-data\\shapeJson").toString();
		String oriShpFile = Paths.get(dir,"originalShape.shp").toString();
		String jsonFile = Paths.get(dir,"originalJsonString.json").toString();
		String oriJsonFile = Paths.get(dir,"jsonString.json").toString();
		String idChangeJsonFile = Paths.get(dir,"jsonString_idchange.json").toString();
		String orderChangeJsonFile = Paths.get(dir,"jsonString_orderchange.json").toString();
		String idOrderChangeJsonFile = Paths.get(dir,"jsonString_idOrderchange.json").toString();
		String coordChangeJsonFile = Paths.get(dir,"jsonString_coordchange.json").toString();
		String idOrderCoordChangeJsonFile = Paths.get(dir,"jsonString_idOrderCoordchange.json").toString();

		String shpString = ShapeToGeoJsonConverter.convertToJsonString(oriShpFile);
		FileUtility.writeToFile(jsonFile, shpString);
		String jsonString = FileUtility.readFileContents(jsonFile);
		String oriJsonString = FileUtility.readFileContents(oriJsonFile, true);
		String idChangeJsonString = FileUtility.readFileContents(idChangeJsonFile, true);
		String orderChangeJsonString = FileUtility.readFileContents(orderChangeJsonFile, true);
		String idOrderChangeJsonString = FileUtility.readFileContents(idOrderChangeJsonFile, true);
		String coordChangeJsonString = FileUtility.readFileContents(coordChangeJsonFile, true);
		String idOrderCoordChangeJsonString = FileUtility.readFileContents(idOrderCoordChangeJsonFile, true);

		JSONAssert.assertEquals(removeIncomparableShapeValue(shpString), removeIncomparableShapeValue(jsonString), JSONCompareMode.NON_EXTENSIBLE);

		Log.info("Equals: Shape file -> Json String - convered" );

		JSONAssert.assertEquals(removeIncomparableShapeValue(jsonString), removeIncomparableShapeValue(oriJsonString), JSONCompareMode.NON_EXTENSIBLE);
		Log.info("Equals: Json String - converted -> Json String" );

		//
		JSONAssert.assertEquals(removeIncomparableShapeValue(shpString), removeIncomparableShapeValue(oriJsonString), JSONCompareMode.NON_EXTENSIBLE);
		Log.info("Equals: Shape file -> Json String" );

		JSONAssert.assertEquals(removeIncomparableShapeValue(shpString), removeIncomparableShapeValue(orderChangeJsonString), JSONCompareMode.NON_EXTENSIBLE);
		Log.info("Equals: Shape file -> Json String - order changed" );

		JSONAssert.assertEquals(removeIncomparableShapeValue(shpString), removeIncomparableShapeValue(idChangeJsonString), JSONCompareMode.NON_EXTENSIBLE);
		Log.info("Equals: Shape file -> Json String - id changed" );

		JSONAssert.assertEquals(removeIncomparableShapeValue(shpString), removeIncomparableShapeValue(idOrderChangeJsonString), JSONCompareMode.NON_EXTENSIBLE);
		Log.info("Equals: Shape file -> Json String - id and order changed" );

		/* Negative tests - find unmatched element */
		JSONAssert.assertNotEquals(removeIncomparableShapeValue(shpString), removeIncomparableShapeValue(coordChangeJsonString), JSONCompareMode.NON_EXTENSIBLE);
		Log.info("Not Equals: Shape file -> Json String - coordinates changed" );
		JSONAssert.assertNotEquals(removeIncomparableShapeValue(shpString), removeIncomparableShapeValue(idOrderCoordChangeJsonString), JSONCompareMode.NON_EXTENSIBLE);
		Log.info("Not Equals: Shape file -> Json String - id and label and coordinates changed" );
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
		boolean result = false;
		try {
			comparer.assertEquals(file1Path, file2Path);
		} catch (AssertionError e) {
			result = true;
		}
		Assert.assertTrue(result);
	}
}
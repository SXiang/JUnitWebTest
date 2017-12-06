package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import surveyor.dataaccess.source.SurveyResult;

public class SurveyResultDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[SurveyResult]";
	private static final String SEED_DATA_FOLDER = SURVEY_SEED_DATA_FOLDER;
	private static final String SEED_FILE_NAME = "SurveyResultSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[SurveyResult] ([SurveyId], [FieldOfView], [Breadcrumb]) VALUES (N'%s', %s, %s)";

	private static final String SURVEY_RESULT_SEED_FILE_PREFIX = "SurveyResult-";
	private static final String SURVEY_RESULT_GEOM_SEED_FILE_PREFIX = "SurveyResult-Geom-";

	public SurveyResultDbSeedBuilder() {
		setSeedFilePath(SEED_DATA_FOLDER, SEED_FILE_NAME);
	}

	public SurveyResultDbSeedBuilder(String seedFileName) {
		setSeedFilePath(SEED_DATA_FOLDER, seedFileName);
	}

	public DbSeed build() throws FileNotFoundException, IOException {
		String workingCSVFile = SeedDataFilePath;
		DbSeed seedData = new DbSeed();

        try
        {
    		CSVUtility csvUtility = new CSVUtility();
    		List<Map<String, String>> allRows = csvUtility.getAllRows(workingCSVFile);

    		boolean geomFileExists = false;
    		String geomSeedFilePath = workingCSVFile.replace(SURVEY_RESULT_SEED_FILE_PREFIX, SURVEY_RESULT_GEOM_SEED_FILE_PREFIX);
    		List<String> geomRows = readGeomFile(geomSeedFilePath);
    		if (geomRows != null) {
    			geomFileExists = true;
    		}

    		int rowIdx = 0;
    		for (Map<String, String> rowItem : allRows) {
    			String surveyId = rowItem.get("SurveyId");
    			String fieldOfView = rowItem.get("FieldOfView");
    			String breadcrumb = rowItem.get("Breadcrumb");

    			// Special handling for Geom type in SurveyResult table to handle issue described in DE2178.
    			// Convert Geom type to WKT and convert back to Geom again when executing INSERT statements.
    			if (geomFileExists) {
    				fieldOfView = String.format("geometry::STGeomFromText('%s', %d)", geomRows.get(rowIdx), SRID);
    			}

				seedData.addInsertStatement(String.format(INSERT_TEMPLATE, surveyId, fieldOfView, breadcrumb));
				rowIdx++;
			}

            seedData.setDestinationTableName(TABLE_NAME);
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtility.getStackTraceString(e));
        }
		return seedData;
	}

	public static List<SurveyResult> readRowsFromSeed(String seedFileTag) throws FileNotFoundException, IOException {
		String seedFilename = String.format("SurveyResult-%s.csv", seedFileTag);
		List<Map<String, String>> seedFileLines = new SurveyResultDbSeedBuilder(seedFilename).getSeedFileLines();
		List<SurveyResult> surveyResults = new ArrayList<>();
		seedFileLines.stream()
			.forEach(row -> {
				SurveyResult surveyResult = new SurveyResult();
				surveyResult.setBreadcrumb(row.get("Breadcrumb") != "NULL" ? row.get("Breadcrumb").toString().replace("0x", "") : null);
				surveyResult.setFieldOfView(row.get("FieldOfView") != "NULL" ? row.get("FieldOfView").toString().replace("0x", "") : null);
				surveyResult.setSurveyId(String.valueOf(row.get("SurveyId")).toUpperCase());
				surveyResults.add(surveyResult);
			});

		return surveyResults;
	}
}

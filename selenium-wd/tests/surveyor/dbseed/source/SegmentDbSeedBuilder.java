package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import surveyor.dataaccess.source.Segment;

public class SegmentDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[Segment]";
	private static final String SEED_DATA_FOLDER = SURVEY_SEED_DATA_FOLDER;
	private static final String SEED_FILE_NAME = "SegmentSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[Segment] ([SurveyId], [Order], [Mode], [Shape]) VALUES (N'%s', %s, %s, %s)";
	private static final CharSequence SEGMENT_SEED_FILE_PREFIX = "Segment-";;
	private static final CharSequence SEGMENT_GEOM_SEED_FILE_PREFIX = "Segment-Geom-";

	public SegmentDbSeedBuilder() {
		setSeedFilePath(SEED_DATA_FOLDER, SEED_FILE_NAME);
	}

	public SegmentDbSeedBuilder(String seedFileName) {
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
    		String geomSeedFilePath = workingCSVFile.replace(SEGMENT_SEED_FILE_PREFIX, SEGMENT_GEOM_SEED_FILE_PREFIX);
    		List<String> geomRows = readGeomFile(geomSeedFilePath);
    		if (geomRows != null) {
    			geomFileExists = true;
    		}

    		int rowIdx = 0;
    		for (Map<String, String> rowItem : allRows) {
    			String surveyId = rowItem.get("SurveyId");
    			String order = rowItem.get("Order");
    			String mode = rowItem.get("Mode");
    			String shape = rowItem.get("Shape");

    			// Special handling for Geom type in SurveyResult table to handle issue described in DE2178.
    			// Convert Geom type to WKT and convert back to Geom again when executing INSERT statements.
    			if (geomFileExists) {
    				shape = String.format("geometry::STGeomFromText('%s', %d)", geomRows.get(rowIdx), SRID);
    			}

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, surveyId, order, mode, shape));
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

	public static List<Segment> readRowsFromSeed(String seedFileTag) throws FileNotFoundException, IOException {
		String seedFilename = String.format("Segment-%s.csv", seedFileTag);
		List<Map<String, String>> seedFileLines = new SegmentDbSeedBuilder(seedFilename).getSeedFileLines();
		List<Segment> segments = new ArrayList<>();
		seedFileLines.stream()
			.forEach(row -> {
				Segment segment = new Segment();
				segment.setMode(Integer.valueOf(row.get("Mode")));
				segment.setOrder(Integer.valueOf(row.get("Order")));
				segment.setShape(row.get("Shape"));
				segment.setSurveyId(String.valueOf(row.get("SurveyId")));
				segments.add(segment);
			});

		return segments;
	}
}

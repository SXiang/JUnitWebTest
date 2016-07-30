package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import common.source.CSVUtility;
import common.source.DateUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.NumberUtility;
import common.source.Redater;
import surveyor.dataaccess.source.GPSRaw;

public class GPSRawDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[GPSRaw]";
	private static final String SEED_DATA_FOLDER = "SurveySeedData";
	private static final String SEED_FILE_NAME = "GPSRawSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[GPSRaw] ([AnalyzerId], [EpochTime], [GpsTime], [GpsLatitude], [GpsLongitude], [GpsFit], [GPSLatitudeUncertainty], [GPSLongitudeUncertainty]) VALUES (N'%s', %s, %s, %s, %s, %s, %s, %s)";
	private boolean redate = false;

	public GPSRawDbSeedBuilder() {
		setSeedFilePath(SEED_DATA_FOLDER, SEED_FILE_NAME);
	}

	public GPSRawDbSeedBuilder(String seedFileName, boolean redate) {
		setSeedFilePath(SEED_DATA_FOLDER, seedFileName);
		this.setRedate(redate);
	}

	public DbSeed build() throws FileNotFoundException, IOException {
		String workingCSVFile = SeedDataFilePath;
		DbSeed seedData = new DbSeed();
		
        try  
        {
    		CSVUtility csvUtility = new CSVUtility();
    		List<HashMap<String, String>> allRows = csvUtility.getAllRows(workingCSVFile);
    		Redater redater = null;
    		for (HashMap<String, String> rowItem : allRows) {
    			String analyzerId = rowItem.get("AnalyzerId");
    			
    			// Initialize the redater once.
    			redater = checkAndInitializeRedater(allRows, redater, analyzerId);
    			
    			String epochTime = isRedate() ? NumberUtility.formatString(redater.getNextUnixTime(), 3) : rowItem.get("EpochTime");
    			String gpsTime = rowItem.get("GpsTime");
    			String gpsLatitude = handleNullGetValue(rowItem.get("GpsLatitude"));
    			String gpsLongitude = handleNullGetValue(rowItem.get("GpsLongitude"));
    			String gpsFit = handleNullGetValue(rowItem.get("GpsFit"));
    			String gPSLatitudeUncertainty = handleNullGetValue(rowItem.get("GPSLatitudeUncertainty"));
    			String gPSLongitudeUncertainty = handleNullGetValue(rowItem.get("GPSLongitudeUncertainty"));

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, analyzerId, epochTime, gpsTime, gpsLatitude, gpsLongitude, gpsFit, gPSLatitudeUncertainty, gPSLongitudeUncertainty));
			}
    		
            seedData.setDestinationTableName(TABLE_NAME);
        }
        catch (Exception e)  
        {  
            Log.error(ExceptionUtility.getStackTraceString(e));  
        }  
		return seedData;
	}

	private Redater checkAndInitializeRedater(List<HashMap<String, String>> allRows, Redater redater, String analyzerId) {
		GPSRaw firstGPSRaw;
		if (redater == null) {
			firstGPSRaw = new GPSRaw().getFirst(analyzerId);
			double bufferTime = 1000;
			double baseUnixTime = DateUtility.getCurrentUnixEpochTime() - allRows.size() - bufferTime;
			if (firstGPSRaw != null) {
				baseUnixTime = DateUtility.getCurrentUnixEpochTime() - firstGPSRaw.getEpochTime() - allRows.size() - bufferTime;
				redater = new Redater(baseUnixTime);
			} else {
				redater = new Redater(baseUnixTime);
			}
		}
		return redater;
	}

	public boolean isRedate() {
		return redate;
	}

	public void setRedate(boolean redate) {
		this.redate = redate;
	}
}
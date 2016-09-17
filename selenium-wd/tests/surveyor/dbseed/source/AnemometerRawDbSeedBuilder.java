package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import common.source.CSVUtility;
import common.source.DateUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.NumberUtility;
import common.source.Redater;
import surveyor.dataaccess.source.AnemometerRaw;

public class AnemometerRawDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[AnemometerRaw]";
	private static final String SEED_DATA_FOLDER = "SurveySeedData";
	private static final String SEED_FILE_NAME = "AnemometerRawSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[AnemometerRaw] ([AnalyzerId], [EpochTime], [WindSpeedLateral], [WindSpeedLongitudinal], [Status], [Index]) VALUES (N'%s', %s, %s, %s, %s, %s)";
	private boolean redate = false;
	private double startEpoch;
	private double endEpoch;
	
	public AnemometerRawDbSeedBuilder() {
		setSeedFilePath(SEED_DATA_FOLDER, SEED_FILE_NAME);
	}

	public AnemometerRawDbSeedBuilder(String seedFileName, boolean redate) {
		setSeedFilePath(SEED_DATA_FOLDER, seedFileName);
		this.setRedate(redate);
	}

	public DbSeed build() throws FileNotFoundException, IOException {
		String workingCSVFile = SeedDataFilePath;
		DbSeed seedData = new DbSeed();
		
        try  
        {              
    		CSVUtility csvUtility = new CSVUtility();
    		List<Map<String, String>> allRows = csvUtility.getAllRows(workingCSVFile);
    		Redater redater = null; 
    		for (Map<String, String> rowItem : allRows) {
    			String analyzerId = rowItem.get("AnalyzerId");
    			
    			// Initialize the redater once.
    			redater = checkAndInitializeRedater(allRows, redater, analyzerId);

    			String epochTime = isRedate() ? NumberUtility.formatString(redater.getNextUnixTime(), 3) : rowItem.get("EpochTime");
    			String windSpeedLateral = handleNullGetValue(rowItem.get("WindSpeedLateral"));
    			String windSpeedLongitudinal = handleNullGetValue(rowItem.get("WindSpeedLongitudinal"));
    			String status = rowItem.get("Status");
    			String index = rowItem.get("Index");

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, analyzerId, epochTime, windSpeedLateral, windSpeedLongitudinal, status, index));
			}
    		
    		this.setStartEpoch(redater.getFirstTime());
    		this.setEndEpoch(redater.getLastTime());
    		
            seedData.setDestinationTableName(TABLE_NAME);
        }
        catch (Exception e)  
        {  
            Log.error(ExceptionUtility.getStackTraceString(e));  
        }  
		return seedData;
	}

	private Redater checkAndInitializeRedater(List<Map<String, String>> allRows, Redater redater, String analyzerId) {
		AnemometerRaw firstAnemometerRaw;
		if (redater == null) {
			AnemometerRaw anemometerRaw = new AnemometerRaw();
			anemometerRaw.purgeCache();
			firstAnemometerRaw = anemometerRaw.getFirst(analyzerId);
			double bufferTime = 1000;
			double baseUnixTime = DateUtility.getCurrentUnixEpochTime() - allRows.size() - bufferTime;
			if (firstAnemometerRaw != null) {
				baseUnixTime = firstAnemometerRaw.getEpochTime() - allRows.size() - bufferTime;
			}			
			redater = new Redater(baseUnixTime);
		}
		return redater;
	}

	public boolean isRedate() {
		return redate;
	}

	public void setRedate(boolean redate) {
		this.redate = redate;
	}

	public double getStartEpoch() {
		return startEpoch;
	}

	public void setStartEpoch(double startEpoch) {
		this.startEpoch = startEpoch;
	}

	public double getEndEpoch() {
		return endEpoch;
	}

	public void setEndEpoch(double endEpoch) {
		this.endEpoch = endEpoch;
	}
}

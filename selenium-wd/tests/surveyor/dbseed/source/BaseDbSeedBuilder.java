package surveyor.dbseed.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCSVFileRecord;

import common.source.CSVUtility;
import common.source.FileUtility;
import common.source.TestContext;
import common.source.TestSetup;

public class BaseDbSeedBuilder {
	
	public static final String SURVEY_SEED_DATA_FOLDER = "SurveySeedData";
	public static final Integer	SRID = 4326;

	protected String SeedDataFilePath;
	private DbSeedBuilderCache dbSeedCache;
	private DbSeed dbSeedData = null;

	protected void setSeedFilePath(String seedDataFolder, String seedFileName) {
		SeedDataFilePath = TestContext.INSTANCE.getExecutionPath() + TestSetup.SQL_DATA_FOLDER + File.separator + seedDataFolder + File.separator + seedFileName;
	}

	public String getSeedFilePath() {
		return SeedDataFilePath;
	}
	
	public DbSeed getDbSeedData() {
		return dbSeedData;
	}

	public void setDbSeedData(DbSeed dbSeedData) {
		this.dbSeedData = dbSeedData;
	}
	
	public DbSeedBuilderCache getDbSeedCache() {
		return dbSeedCache;
	}

	public void setDbSeedCache(DbSeedBuilderCache dbSeedCache) {
		this.dbSeedCache = dbSeedCache;
	}

	public String handleNullGetValue(String value) {
		if (value == null || value.isEmpty()) {
			return "NULL";
		}
		return value;
	}
	
	protected String createCSVFileWithCustomerData(String customerID, String primaryKeyColName, String tableName) throws FileNotFoundException, IOException {
		if (SeedDataFilePath == null) {
			throw new IllegalStateException("SeedData FilePath NOT set. Ensure SeedDataFilePath is set before calling createCSVFilesWithCustomerData().");
		}

		boolean customerIDSpecified = false;
		if (customerID != null && !customerID.isEmpty()) {
			customerIDSpecified = true;
		}

		String customerCSVFile = null;
		CSVUtility csvUtility = new CSVUtility();
		List<String> headings = csvUtility.getHeadings(SeedDataFilePath);
		List<HashMap<String, String>> allRows = csvUtility.getAllRows(SeedDataFilePath);
		String custSeedFilePath = TestContext.INSTANCE.getExecutionPath() + TestSetup.SQL_DATA_FOLDER + File.separator + 
				String.format("%s-%s.csv", TestContext.INSTANCE.getTestSetup().getFixedSizePseudoRandomString(10), customerID);
		
		List<String> filelines = new ArrayList<String>();
		// Add all the headers to the file lines.
		filelines.add(CSVUtility.createCsvString(headings));
		
		// Add each row to the file lines.
		for (int i = 0; i < allRows.size(); i++) {
			List<String> rowLineText = new ArrayList<String>();
			for (String colName : headings) {
				if (!customerIDSpecified) {
					// If no customerId is specified make no updates.
					rowLineText.add(allRows.get(i).get(colName));
				} else {
					// Customer ID is specified. Make updates to CSV.
					if (colName.equalsIgnoreCase("customerid")) {
						// replace customerID column value.
						rowLineText.add(customerID);
					} else if (colName.equalsIgnoreCase(primaryKeyColName)) {
						// replace primary key column value.
						// store each replace column value in cache.
						String oldId = allRows.get(i).get(colName);
						String newId = UUID.randomUUID().toString();
						getDbSeedCache().addTablePKIdCacheEntry(tableName, new TablePKCacheItem(oldId, newId));
						rowLineText.add(newId);
					} else {						
						rowLineText.add(allRows.get(i).get(colName));
					}
				}
			}
			filelines.add(CSVUtility.createCsvString(rowLineText));
		}
		
		FileUtility.writeToFile(custSeedFilePath, filelines.toArray(new String[filelines.size()]));
		customerCSVFile = custSeedFilePath;

		return customerCSVFile;
	}
	
	/**
	 * For shape geometry type which cannot be inserted directly as binary using JDBC insert statements,
	 * we store the data in WKT format and then insert using geometry::STGeomFromText('<WKT>')
	 * A geom file stores the data for geometry type for all the rows.
	 * @return
	 * @throws IOException 
	 */
	public List<String> readGeomFile(String geomFilePath) throws IOException {
		if (!FileUtility.fileExists(geomFilePath)) {
			return null;
		}
		return FileUtility.readFileLinesToList(geomFilePath);
	}
	
	public void close() {
		DbSeed dbSeedData = this.getDbSeedData();
		if (dbSeedData != null) {
			SQLServerBulkCSVFileRecord fileRecord = (SQLServerBulkCSVFileRecord)dbSeedData.getSeedData();
			if (fileRecord !=null) {
				if (fileRecord != null) try { fileRecord.close(); } catch(Exception e) {} 
			}
		}
	}
}

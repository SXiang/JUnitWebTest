package surveyor.dbseed.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class AssetDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[Asset]";
	private static final String PK_COL_NAME = "Id";
	private static final String SEED_DATA_FOLDER = "GisSeedData";
	private static final String SEED_FILE_NAME = "AssetSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[Asset] ([Id], [ExternalId], [CustomerId], [AssetTypeId], [CustomerMaterialTypeId], [Description], [Shape], [Date], [State]) VALUES "
			+ "(N'%s', N'%s', N'%s', N'%s', N'%s', NULL, %s, CAST(N'%s' AS DateTime), NULL)";

	public AssetDbSeedBuilder() {
		SeedDataFilePath = TestContext.INSTANCE.getExecutionPath() + TestSetup.SQL_DATA_FOLDER + File.separator + SEED_DATA_FOLDER + File.separator + SEED_FILE_NAME;
	}

	public DbSeed build(String customerID) throws FileNotFoundException, IOException {
		String workingCSVFile = SeedDataFilePath;
		boolean customerIDSpecified = false;
		if (customerID != null && !customerID.isEmpty()) {
			customerIDSpecified = true;
			workingCSVFile = createCSVFileWithCustomerData(customerID, PK_COL_NAME, TABLE_NAME);
		}

		DbSeed seedData = new DbSeed();
		if (customerIDSpecified) {
			seedData.addCleanupStatement(String.format("DELETE [dbo].[Asset] WHERE [CustomerId]='%s'", customerID));
		}

        try
        {
    		CSVUtility csvUtility = new CSVUtility();
    		List<HashMap<String, String>> allRows = csvUtility.getAllRows(workingCSVFile);
    		for (HashMap<String, String> rowItem : allRows) {
    			String id = rowItem.get("Id");
    			String externalId = rowItem.get("ExternalId");
    			String custId = rowItem.get("CustomerId");
    			if (customerIDSpecified) {
    				custId = customerID;
    			}
    			String assetTypeId = rowItem.get("AssetTypeId");

    			String customerMaterialTypeId = rowItem.get("CustomerMaterialTypeId");
    			// If cache contains a newId replace with new id.
    			String custMaterialTypeIdFromCache = getDbSeedCache().getIdFromTablePKIdCache(CustomerMaterialTypeDbSeedBuilder.TABLE_NAME, customerMaterialTypeId);
    			if (custMaterialTypeIdFromCache != null) {
    				customerMaterialTypeId = custMaterialTypeIdFromCache;
    			}

    			String shape = rowItem.get("Shape");
    			String date = rowItem.get("Date");

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, id, externalId, custId, assetTypeId, customerMaterialTypeId, shape, date));
			}

            seedData.setDestinationTableName(TABLE_NAME);
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtility.getStackTraceString(e));
        }
        finally
        {
            if (customerIDSpecified) {
            	FileUtility.deleteFile(Paths.get(workingCSVFile));
            }
        }
		return seedData;
	}

	public DbSeed cleanup(String customerID) {
		if (customerID == null || customerID.isEmpty()) {
			throw new IllegalArgumentException(String.format("Customer ID was not specified. CustomerId=[%s]", customerID));
		}

		DbSeed seedData = new DbSeed();
		seedData.addCleanupStatement(String.format("DELETE [dbo].[Asset] WHERE [CustomerId]='%s'", customerID));
        seedData.setDestinationTableName(TABLE_NAME);
		return seedData;
	}
}

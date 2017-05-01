package surveyor.dbseed.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class BoundaryDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[Boundary]";
	private static final String PK_COL_NAME = "Id";
	protected static final String SEED_DATA_FOLDER = "GisSeedData";
	protected static final String SEED_FILE_NAME = "BoundarySeed.csv";
	protected static final String SEED_FILE_NAME_NEW_CUSTOMER = "BoundarySeedAll.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[Boundary] ([Id], [ExternalId], [CustomerId], [CustomerBoundaryTypeID], [Description], [Level], [Shape], [State]) VALUES "
			+ "(N'%s', N'%s', N'%s', N'%s', N'%s', %s, %s, NULL)";

	public BoundaryDbSeedBuilder() {
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
			seedData.addCleanupStatement(String.format("DELETE [dbo].[Boundary] WHERE [CustomerId]='%s'", customerID));
		}

        try
        {
    		CSVUtility csvUtility = new CSVUtility();
    		List<Map<String, String>> allRows = csvUtility.getAllRows(workingCSVFile);
    		for (Map<String, String> rowItem : allRows) {
    			String id = rowItem.get("Id");
    			String externalId = rowItem.get("ExternalId");
    			String custId = rowItem.get("CustomerId");
    			if (customerIDSpecified) {
    				custId = customerID;
    			}

    			String customerBoundaryTypeId = rowItem.get("CustomerBoundaryTypeID");
    			// If cache contains a newId replace with new id.
    			String custBoundaryTypeIdFromCache = getDbSeedCache().getIdFromTablePKIdCache(CustomerBoundaryTypeDbSeedBuilder.TABLE_NAME, customerBoundaryTypeId);
    			if (custBoundaryTypeIdFromCache != null) {
    				customerBoundaryTypeId = custBoundaryTypeIdFromCache;
    			}

    			String description = rowItem.get("Description");
    			String level = rowItem.get("Level");
    			String shape = rowItem.get("Shape");

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, id, externalId, custId, customerBoundaryTypeId, description, level, shape));
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
		seedData.addCleanupStatement(String.format("DELETE [dbo].[Boundary] WHERE [CustomerId]='%s'", customerID));
        seedData.setDestinationTableName(TABLE_NAME);
		return seedData;
	}
}

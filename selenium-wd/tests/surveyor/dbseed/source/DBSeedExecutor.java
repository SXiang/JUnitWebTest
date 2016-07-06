package surveyor.dbseed.source;

import java.util.List;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.mssql.MsSqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import common.source.ExceptionUtility;
import common.source.Log;
import surveyor.dataaccess.source.ConnectionFactory;

public class DBSeedExecutor {

	/* Seed data for pushing GIS data (CustomerBoundaryType, CustomerMaterialType, Boundary and Asset */
	
	public static void executeGisSeed() throws Exception {
		executeGisSeed(null /*customerId*/);
	}
	
	public static void executeGisSeed(String customerId) throws Exception {
		Log.method("executeGisSeed", customerId);
		
		IDatabaseConnection connection = new DatabaseConnection(ConnectionFactory.createConnection());
		DatabaseConfig config = connection.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MsSqlDataTypeFactory());
		List<IDataSet> custBoundaryTypeDataSets = new CustomerBoundaryTypeDataSet().build(customerId);
		List<IDataSet> custMaterialTypeDataSets = new CustomerMaterialTypeDataSet().build(customerId);
		List<IDataSet> boundaryDataSets = new BoundaryDataSet().build(customerId);
		List<IDataSet> assetDataSets = new AssetDataSetAll().build(customerId);
        try
        {
	        for (IDataSet dataSet : custBoundaryTypeDataSets) {
	        	DatabaseOperation.INSERT.execute(connection, dataSet);
			}
	        for (IDataSet dataSet : custMaterialTypeDataSets) {
	            DatabaseOperation.INSERT.execute(connection, dataSet);
			}
	        for (IDataSet dataSet : boundaryDataSets) {
	            DatabaseOperation.INSERT.execute(connection, dataSet);
			}
	        for (IDataSet dataSet : assetDataSets) {
	            DatabaseOperation.INSERT.execute(connection, dataSet);
			}
        } catch (Exception ex) {
        	Log.error(String.format("EXCEPTION in executeGisSeed() - %s", ExceptionUtility.getStackTraceString(ex)));
        } finally {
            connection.close();
        }
	}
}

package surveyor.dbseed.source;

import java.util.List;

import com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord;

public interface IDbSeed {
	void addCleanupStatement(String value);
	void setSeedData(ISQLServerBulkRecord fileRecord);
	void setDestinationTableName(String tableName);
	void addInsertStatement(String value);

	String getDestinationTableName();

	List<String> getInsertStatements();
	List<String> getCleanupStatements();
	ISQLServerBulkRecord getSeedData();
}

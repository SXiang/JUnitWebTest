package surveyor.dbseed.source;

import java.util.List;

import com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord;

public interface IDbSeed {
	void addCleanupStatement(String value);
	List<String> getCleanupStatements();
	ISQLServerBulkRecord getSeedData();
	void setSeedData(ISQLServerBulkRecord fileRecord);
	void setDestinationTableName(String tableName);
	String getDestinationTableName();
	void addInsertStatement(String value);
	List<String> getInsertStatements();
}

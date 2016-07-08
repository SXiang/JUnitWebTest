package surveyor.dbseed.source;

import java.util.List;

import com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord;

public class DbSeed implements IDbSeed {

	private String destTableName;
	private List<String> cleanupStatements;
	private ISQLServerBulkRecord seedDataFileRecord;
	
	@Override
	public void addCleanupStatement(String value) {
		this.cleanupStatements.add(value);
	}

	@Override
	public List<String> getCleanupStatements() {
		return this.cleanupStatements;
	}

	@Override
	public void setSeedData(ISQLServerBulkRecord fileRecord) {
		this.seedDataFileRecord = fileRecord;
	}
	
	@Override
	public ISQLServerBulkRecord getSeedData() {
		return this.seedDataFileRecord;
	}

	@Override
	public void setDestinationTableName(String tableName) {
		this.destTableName = tableName;
	}

	@Override
	public String getDestinationTableName() {
		return this.destTableName;
	}
}

package surveyor.dbseed.source;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord;

public class DbSeed implements IDbSeed {

	private String destTableName;
	private List<String> cleanupStatements;
	private List<String> insertStatements;
	private ISQLServerBulkRecord seedDataFileRecord;
	
	public DbSeed() {
		this.cleanupStatements = new ArrayList<String>();
		this.insertStatements = new ArrayList<String>();
	}
	
	@Override
	public String toString() {
		return String.format("DbSeed -> [Destination table=%s], [CleanupStatements count=%d], [InsertStatements count=%d], [SeedDataFileRecord=%s]", 
				destTableName, cleanupStatements.size(), insertStatements.size(), seedDataFileRecord);
	}
	
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

	@Override
	public List<String> getInsertStatements() {
		return insertStatements;
	}

	@Override
	public void addInsertStatement(String value) {
		this.insertStatements.add(value);
	}	
}

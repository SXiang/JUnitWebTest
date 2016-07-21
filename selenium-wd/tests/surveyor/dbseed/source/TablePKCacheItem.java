package surveyor.dbseed.source;

public class TablePKCacheItem {
	private String oldId;
	private String newId;
	
	public TablePKCacheItem(String oldID, String newID) {
		this.oldId = oldID;
		this.newId = newID;
	}
	
	public String getOldId() {
		return oldId;
	}
	public void setOldId(String oldId) {
		this.oldId = oldId;
	}
	public String getNewId() {
		return newId;
	}
	public void setNewId(String newId) {
		this.newId = newId;
	}
}

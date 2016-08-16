package surveyor.dbseed.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.source.Log;

public class DbSeedBuilderCache {
	public static Map<String, List<TablePKCacheItem>> tablePKIdCache;
	public static Map<String, BaseDbSeedBuilder> dbSeedBuilderCache;
	
	public DbSeedBuilderCache() {
		tablePKIdCache = Collections.synchronizedMap(new HashMap<String, List<TablePKCacheItem>>());
		dbSeedBuilderCache = Collections.synchronizedMap(new HashMap<String, BaseDbSeedBuilder>());
	}
	
	public void addTablePKIdCacheEntry(String tableName, TablePKCacheItem cacheItem) {
		if (!tablePKIdCache.containsKey(tableName)) {
			ArrayList<TablePKCacheItem> cacheItemList = new ArrayList<TablePKCacheItem>();
			List<TablePKCacheItem> synchronizedList = Collections.synchronizedList(cacheItemList);
			synchronizedList.add(cacheItem);
			tablePKIdCache.put(tableName, synchronizedList);
		} else {
			List<TablePKCacheItem> synchronizedList = tablePKIdCache.get(tableName);
			synchronizedList.add(cacheItem);
			tablePKIdCache.replace(tableName, synchronizedList);
		}
	}
	
	public String getIdFromTablePKIdCache(String tableName, String oldId) {
		String id = null;
		if (tablePKIdCache.containsKey(tableName)) {
			List<TablePKCacheItem> synchronizedList = tablePKIdCache.get(tableName);
			for (TablePKCacheItem cacheItem : synchronizedList) {
				if (cacheItem.getOldId().equalsIgnoreCase(oldId)) {
					id = cacheItem.getNewId();
					break;
				}
			}
		}
		return id;
	}
	
	public void addDbSeedBuilder(String key, BaseDbSeedBuilder dbSeedBuilder) {
		Log.method("addDbSeedBuilder", key, dbSeedBuilder);
		if (!dbSeedBuilderCache.containsKey(key)) {
			dbSeedBuilderCache.put(key, dbSeedBuilder);
		} else {
			dbSeedBuilderCache.replace(key, dbSeedBuilder);
		}
	}

	public BaseDbSeedBuilder getDbSeedBuilder(String key) {
		return dbSeedBuilderCache.get(key);
	}
	
	public Set<String> getDbSeedBuilderCacheKeys() {
		return dbSeedBuilderCache.keySet();
	}
}

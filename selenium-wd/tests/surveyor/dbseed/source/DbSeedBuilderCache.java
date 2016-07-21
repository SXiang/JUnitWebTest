package surveyor.dbseed.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbSeedBuilderCache {
	public static Map<String, List<TablePKCacheItem>> tablePKIdCache;
	
	public DbSeedBuilderCache() {
		tablePKIdCache = Collections.synchronizedMap(new HashMap<String, List<TablePKCacheItem>>());
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
}

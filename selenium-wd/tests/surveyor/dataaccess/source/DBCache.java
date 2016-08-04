package surveyor.dataaccess.source;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public enum DBCache {
	INSTANCE;
	
	private Hashtable<String, Object> cache = new Hashtable<String, Object>();
	
	public Object get(String key) {
		if (cache.containsKey(key))
			return cache.get(key);
		
		return null;
	}

	public void set(String key, Object value) {
		if (cache.containsKey(key))
			cache.replace(key, value);
		else 
			cache.put(key, value);
	}

	public boolean containsKey(String key) {
		return cache.containsKey(key);
	}

	public void remove(String key) {
		cache.remove(key);
	}
	
	/**
	 * Purges all items from cache for keys starting with specified prefix.
	 * @param keyPrefix
	 */
	public void purgeCache(String keyPrefix) {
		Iterator<String> iterator = cache.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (key.startsWith(keyPrefix)) {
				iterator.remove();
			}
		}
	}
}

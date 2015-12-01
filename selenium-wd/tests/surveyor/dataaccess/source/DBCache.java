package surveyor.dataaccess.source;

import java.util.Hashtable;

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
}

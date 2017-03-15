package common.source;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Used to store thread specific objects in a Map.
 *
 * @param <T>
 */
public class ThreadLocalMap<T> {
	private Map<String, T> objMap = Collections.synchronizedMap(new HashMap<>());

	public ThreadLocalMap(T obj) {
		putObject(obj);
	}

	public T getObject() {
		if (objMap.containsKey(getThreadName())) {
			return objMap.get(getThreadName());
		}

		return null;
	}

	public void putObject(T obj) {
		if (!objMap.containsKey(getThreadName())) {
			objMap.put(getThreadName(), obj);
		} else {
			objMap.replace(getThreadName(), obj);
		}
	}

	private String getThreadName() {
		return Thread.currentThread().getName();
	}
}
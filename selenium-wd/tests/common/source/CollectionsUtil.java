package common.source;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionsUtil {

	public static <K, V> Map<K, V> toMap(List<K> keyList, List<V> valueList) {
		Map<K, V> retMap = null;
		if (keyList != null && keyList.size() > 0 && valueList != null && valueList.size() > 0) {
			retMap = Collections.synchronizedMap(new HashMap<K, V>());
			for (int i = 0; i < keyList.size(); i++) {
				retMap.put(keyList.get(i), valueList.get(i));
			}
		}

		return retMap;
	}

	public static <K, V> void populateListMap(Map<K, List<V>> listMap, K key, V value) {
		if (listMap != null && key != null && value != null) {
			List<V> valuesList = listMap.get(key);
			if (valuesList != null) {
				valuesList.add(value);
				listMap.replace(key, valuesList);
			} else {
				listMap.put(key, Arrays.asList(value));
			}
		}
	}
}

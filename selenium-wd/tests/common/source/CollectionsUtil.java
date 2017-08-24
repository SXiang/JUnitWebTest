package common.source;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CollectionsUtil {

	public static <V> Integer getListSize(List<V> list) {
		if (list != null) {
			return list.size();
		}

		return 0;
	}

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
				listMap.put(key, Collections.synchronizedList(new LinkedList<V>(Arrays.asList(value))));
			}
		}
	}

	public static boolean isEqualsArrayMap(String[][] arrayValues, Map<String, String> mapValues) {
		Log.method("isEqualsArrayMap", LogHelper.arrayOfArrayToString(arrayValues), LogHelper.mapToString(mapValues));
		for(int i=0;i<arrayValues[0].length; i++) {
			String actual = mapValues.get(arrayValues[0][i]).toLowerCase().trim();
			String expect = arrayValues[1][i].toLowerCase();
			if(!actual.equals(expect)){
				Log.error("NOT matching value found.");
				Log.error("Map value: "+arrayValues[0][i]+" = "+actual);
				Log.error("Array value: "+arrayValues[0][i]+" = "+expect);
				return false;
			}
		}

		return true;
	}
}

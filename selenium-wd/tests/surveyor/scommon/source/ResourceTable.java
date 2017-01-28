package surveyor.scommon.source;

import java.util.Map;

public class ResourceTable {
	public static final String Key_PageText = "PageText";
	public static final String Key_NewPageText = "NewPageText";
	public static final String Key_CopyPageText = "CopyPageText";

	private Map<String, String> resourceMap;

	public ResourceTable(Map<String, String> resourceMap) {
		this.resourceMap = resourceMap;
	}

	public String getResource(String key) {
		return this.resourceMap.get(key);
	}
}

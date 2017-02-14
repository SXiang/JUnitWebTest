package surveyor.scommon.source;

import java.util.function.Supplier;

public class ResourceProvider {

	private ResourceTable resourceTbl;

	public ResourceProvider(Supplier<ResourceTable> resxTblSupplier) {
		this.resourceTbl = resxTblSupplier.get();
	}

	public String getResource(String key) {
		return this.resourceTbl.getResource(key);
	}
}

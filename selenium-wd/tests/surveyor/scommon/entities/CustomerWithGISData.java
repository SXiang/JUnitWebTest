package surveyor.scommon.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CustomerWithGISData {
	public String Id;
	public String Name;
	public Integer EnvironmentId;
	public Double RunUUID;
	public Boolean IsLocked;
	public String LockingMachineIP;
	public Boolean UnlockedBySystem;
	public String LastUpdated;
	public String LastUnlockedBySystem;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}

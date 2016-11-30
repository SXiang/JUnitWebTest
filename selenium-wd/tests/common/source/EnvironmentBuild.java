package common.source;

public class EnvironmentBuild {
	public String BuildConfigId;
	public String BuildNumber;

	public String toString() {
		return String.format("EnvironmentBuild=[BuildConfigId=%s, BuildNumber=%s]", BuildConfigId, BuildNumber);
	}
}

package common.source;

public class HostSimDbReaderNode extends HostSimNode {
	private String db3FilePath;

	public HostSimDbReaderNode(String name, String db3File, HostSimNodeTarget target) {
		super(name, target);
		this.db3FilePath = db3File;
	}

	public String getDb3FilePath() {
		return db3FilePath;
	}

	public void setDb3FilePath(String db3FilePath) {
		this.db3FilePath = db3FilePath;
	}

	@Override
	public String getArgs() {
		return String.format("'%s'", this.getDb3FilePath());
	}

	@Override
	public HostSimNodeTarget getTarget() {
		HostSimNodeTarget nodeTarget = super.getTarget();
		if (nodeTarget != HostSimNodeTarget.dbreader_anemometer_reader
				&& nodeTarget != HostSimNodeTarget.dbreader_gps_reader
				&& nodeTarget != HostSimNodeTarget.dbreader_measurement_reader) {
			throw new IllegalArgumentException("Should be valid target type - {dbreader_anemometer_reader, dbreader_gps_reader, dbreader_measurement_reader}");
		}

		return nodeTarget;
	}
}

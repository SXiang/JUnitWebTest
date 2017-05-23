package common.source;

import java.util.ArrayList;

public class HostSimZmqSenderNode extends HostSimNode {
	private Integer delay = 30;
	private Boolean redate = true;
	private Boolean verbose = true;
	private Mode mode;

	public static enum Mode {
		FEDS_mode ("FEDS_mode"),
		RFADS_mode ("RFADS_mode");

		private final String name;

		Mode(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public HostSimZmqSenderNode(String name, HostSimNodeTarget target, Mode mode) {
		super(name, target);
		this.mode = mode;
		this.inNodes = new ArrayList<HostSimNode>();
		this.outPresent = false;
	}

	public Integer getDelay() {
		return delay;
	}

	public HostSimZmqSenderNode setDelay(Integer delay) {
		this.delay = delay;
		return this;
	}

	public Boolean getRedate() {
		return redate;
	}

	public HostSimZmqSenderNode setRedate(Boolean redate) {
		this.redate = redate;
		return this;
	}

	public Boolean getVerbose() {
		return verbose;
	}

	public HostSimZmqSenderNode setVerbose(Boolean verbose) {
		this.verbose = verbose;
		return this;
	}

	@Override
	public String getArgs() {
		return String.format("%s,%d,%s,%s", getBooleanString(getRedate()), getDelay(), mode, getBooleanString(getVerbose()));
	}
}
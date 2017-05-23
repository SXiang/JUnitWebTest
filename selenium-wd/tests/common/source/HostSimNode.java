package common.source;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class HostSimNode {
	private String name;
	private HostSimNodeTarget target;
	private String args;
	private String kwargs;
	protected List<HostSimNode> inNodes;
	protected Boolean outPresent = true;
	protected Boolean argsPresent = true;

	public static enum HostSimNodeTarget {
		dbreader_anemometer_reader ("dbreader.anemometer_reader"),
		dbreader_gps_reader ("dbreader.gps_reader"),
		dbreader_measurement_reader ("dbreader.measurement_reader"),
		data_conditioner_data_conditioner ("data_conditioner.data_conditioner"),
		data_conditioner_transformer ("data_conditioner.transformer"),
		stream_merger_stream_merger ("stream_merger.stream_merger"),
		zmqsender_zmq_sender ("zmqsender.zmq_sender");

		private final String name;

		HostSimNodeTarget(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public HostSimNode(String name, HostSimNodeTarget target) {
		this.name = name;
		this.target = target;
	}

	public String getName() {
		return name;
	}
	public HostSimNode setName(String name) {
		this.name = name;
		return this;
	}
	public HostSimNodeTarget getTarget() {
		return target;
	}
	public HostSimNode setTarget(HostSimNodeTarget target) {
		this.target = target;
		return this;
	}
	public List<HostSimNode> getInNodes() {
		return inNodes;
	}
	public HostSimNode addInNode(HostSimNode in) {
		this.inNodes.add(in);
		return this;
	}
	public String getOut() {
		return String.format("%s_out", getName());
	}
	public String getArgs() {
		return args;
	}
	public HostSimNode setArgs(String args) {
		this.args = args;
		return this;
	}
	public String getKwargs() {
		return kwargs;
	}
	public HostSimNode setKwargs(String kwargs) {
		this.kwargs = kwargs;
		return this;
	}
	public Boolean isOutPresent() {
		return this.outPresent;
	}
	public Boolean isArgsPresent() {
		return this.argsPresent;
	}

	protected String getBooleanString(Boolean value) {
		if (value) {
			return "True";
		}

		return "False";
	}

	@Override
	public String toString() {
		String line1 = String.format("node %s target=%s", getName(), getTarget().toString());
		StringBuilder line2 = new StringBuilder();
		line2.append("   ");
		if (getInNodes() != null) {
			line2.append(String.format(" in=%s", StringUtils.join(getInNodes().stream().map(n -> n.getOut()).collect(Collectors.toList()), ",")));
		}

		if (isOutPresent()) {
			line2.append(String.format(" out=%s", getOut()));
		}

		if (isArgsPresent()) {
			line2.append(String.format(" args=%s", getArgs()));
		}

		if (!BaseHelper.isNullOrEmpty(getKwargs())) {
			line2.append(String.format(" kwargs=%s", getKwargs()));
		}

		return line1 + BaseHelper.getLineSeperator() + line2.toString();
	}
}
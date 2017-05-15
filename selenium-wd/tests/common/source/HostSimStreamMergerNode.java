package common.source;

import java.util.ArrayList;

public class HostSimStreamMergerNode extends HostSimNode {

	public HostSimStreamMergerNode(String name, HostSimNodeTarget target) {
		super(name, target);
		this.inNodes = new ArrayList<HostSimNode>();
		this.argsPresent = false;
	}

	@Override
	public HostSimNodeTarget getTarget() {
		HostSimNodeTarget nodeTarget = super.getTarget();
		if (nodeTarget != HostSimNodeTarget.stream_merger_stream_merger) {
			throw new IllegalArgumentException("Should be valid target type - {stream_merger_stream_merger}");
		}

		return nodeTarget;
	}
}

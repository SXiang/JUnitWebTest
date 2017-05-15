package common.source;

import java.io.IOException;
import java.util.ArrayList;

public class HostSimDataTransformerNode extends HostSimNode {
	private HostSimInstructions instructions;
	private Integer after;

	public HostSimDataTransformerNode(String name, HostSimInstructions inst) {
		super(name, HostSimNodeTarget.data_conditioner_transformer);
		this.instructions = inst;
		this.inNodes = new ArrayList<HostSimNode>();
	}

	public HostSimInstructions getInstructions() {
		return instructions;
	}

	public void setInstructions(HostSimInstructions instructions) {
		this.instructions = instructions;
	}

	public Integer getAfter() {
		return after;
	}

	public void setAfter(Integer after) {
		this.after = after;
	}

	@Override
	public String getArgs() {
		try {
			return String.format("'%s'", this.getInstructions().createFile());
		} catch (IOException e) {
			Log.error(ExceptionUtility.getStackTraceString(e));
		}

		return "";
	}

	@Override
	public String getKwargs() {
		String kwArgs = super.getKwargs();
		if (!BaseHelper.isNullOrEmpty(kwArgs)) {
			return kwArgs;
		}

		return String.format("after=%d", after);
	}
}

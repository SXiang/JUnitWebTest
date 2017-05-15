package common.source;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class HostSimDataConditionerNode extends HostSimNode {
	private List<ConditionerMethod> conditionerMethods;
	private Integer after;
	private Integer every;
	private Integer repeat;

	public enum ConditionerMethod {
		delete_ch4 ("delete_ch4"),
		delete_c2h6 ("delete_c2h6"),
		set_ch4_to_0 ("set_ch4_to_0"),
		set_c2h6_to_0 ("set_c2h6_to_0");

		private final String name;

		ConditionerMethod(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public HostSimDataConditionerNode(String name, Integer after, Integer every, Integer repeat) {
		super(name, HostSimNodeTarget.data_conditioner_data_conditioner);
		this.conditionerMethods = new ArrayList<ConditionerMethod>();
		this.inNodes = new ArrayList<HostSimNode>();
		this.after = after;
		this.every = every;
		this.repeat = repeat;
	}

	public HostSimDataConditionerNode addConditionerMethod(ConditionerMethod method) {
		this.getConditionerMethods().add(method);
		return this;
	}

	public List<ConditionerMethod> getConditionerMethods() {
		return conditionerMethods;
	}

	@Override
	public String getArgs() {
		if (getConditionerMethods().isEmpty()) {
			throw new IllegalArgumentException("Atleast one ConditionerMethod needs to be specified.");
		}

		return StringUtils.join(getConditionerMethods(), ",");
	}

	@Override
	public String getKwargs() {
		return String.format("after=%d,every=%d,repeat=%d", after, every, repeat);
	}
}
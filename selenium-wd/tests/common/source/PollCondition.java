package common.source;

@FunctionalInterface
public interface PollCondition {
	public boolean waitCondition();
}

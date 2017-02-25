package common.source;

@FunctionalInterface
public interface CheckedConsumer {
	public void execute() throws Exception;
}

package common.source;

@FunctionalInterface
public interface CheckedSupplier<T> {
	public T get() throws Exception;
}

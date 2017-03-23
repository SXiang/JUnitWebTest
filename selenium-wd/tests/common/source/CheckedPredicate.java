package common.source;

@FunctionalInterface
public interface CheckedPredicate<T> {
	public boolean test(T arg) throws Exception;
}

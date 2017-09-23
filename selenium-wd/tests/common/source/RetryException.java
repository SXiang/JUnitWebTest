package common.source;

/**
 * Use RetryException for cases where the test can be retried if a RetryRunner is registered for the test.
 *
 * @author spulikkal
 *
 */
@SuppressWarnings("serial")
public class RetryException extends RuntimeException {
	public RetryException() {
		super();
	}

	public RetryException(String message) {
		super(message);
	}

	public RetryException(Throwable cause) {
		super(cause);
	}

	public RetryException(String message, Throwable cause) {
		super(message, cause);
	}
}

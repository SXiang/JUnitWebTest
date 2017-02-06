package common.source;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import com.relevantcodes.extentreports.LogStatus;

public class RetryUtil {

	/**
	 * Retries a method for specified number of times after handling exception.
	 * Captures a screenshot if failure happens after specified number of retries.
	 * @param mainMethod - functional main method to call.
	 * @param methodOnFailure - functional method to call on failure.
	 * @param waitInMSecsBetweenRetries - wait time in msec between retries.
	 * @param maxRetries - max number of retries.
	 */
	public static <T> boolean retryOnException(BooleanSupplier mainMethod, Supplier<T> methodOnFailure, Integer waitInMSecsBetweenRetries, Integer maxRetries,
			Boolean takeScreenshotOnFailure) {
		Integer retryAttempt = 0;
		Boolean success = false;
		do{
			try{
				retryAttempt++;
				success = mainMethod.getAsBoolean();
			} catch(Exception e) {
				if (waitInMSecsBetweenRetries > 0) {
					try {
						Thread.sleep(waitInMSecsBetweenRetries);
					} catch (InterruptedException e1) {
						Log.error(ExceptionUtility.getStackTraceString(e1));
					}
				}
				Log.warn(String.format("Attempt-[%d] - Method failure. Exception -> '%s'", retryAttempt, ExceptionUtility.getStackTraceString(e)));
				methodOnFailure.get();
			}
		} while(!success && retryAttempt < maxRetries);

		if (!success && takeScreenshotOnFailure) {
			Log.warn(String.format("Method FAILED after %d attempts", retryAttempt));
			TestContext.INSTANCE.getTestSetup().getScreenCapture().takeScreenshot(TestContext.INSTANCE.getDriver(),
					TestContext.INSTANCE.getTestClassName(), true /*takeBrowserScreenShot*/, LogStatus.INFO);
		}

		return success;
	}
}
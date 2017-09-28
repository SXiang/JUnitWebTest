package common.source;

import java.util.List;

import org.junit.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.internal.runners.model.MultipleFailureException;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.openqa.selenium.remote.UnreachableBrowserException;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;

import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.RunExecutionListener;

/**
* This is a custom test runner created to retry tests on failure.
* Tests will be re-run if following exceptions are encountered:
*    RetryException (Custom) | UnreachableBrowserException | SessionNotFoundException
*
* @author spulikkal
*
*/
@SuppressWarnings("deprecation")
public class AndroidRetryTestRunner extends DataProviderRunner {

	private static final Integer MAX_ATTEMPTS = 3;

	public AndroidRetryTestRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(final RunNotifier notifier){
		notifier.addListener(new RunExecutionListener());

		Description description = getDescription();
		EachTestNotifier testNotifier = new EachTestNotifier(notifier, description);
		Statement classStatement = null;
		try {
			classStatement = classBlock(notifier);
			classStatement.evaluate();
		} catch (AssumptionViolatedException e) {
			testNotifier.addFailedAssumption(e);
		} catch (StoppedByUserException e) {
			throw e;
		} catch (RetryException | UnreachableBrowserException | SessionNotFoundException e) {
			Log.warn(String.format("Retry - Class | Method : [%s.%s] on exception -> %s", description.getClassName(), description.getMethodName(),
			ExceptionUtility.getStackTraceString(e)));
			retryTest(testNotifier, classStatement, description, e);
		} catch (Throwable e) {
			testNotifier.addFailure(e);
		}
	}

	@Override
	protected void runChild(FrameworkMethod method, RunNotifier notifier) {
		Description description = describeChild(method);
		if (isIgnored(method)) {
			notifier.fireTestIgnored(description);
		} else {
			Statement statement = new Statement() {
				@Override
				public void evaluate() throws Throwable {
					EachTestNotifier testNotifier = new EachTestNotifier(notifier, description);
					testNotifier.fireTestStarted();
					Statement methodStatement = null;
					Throwable failureException = null;
					try {
						methodStatement = methodBlock(method);
						BaseTest.reportTestStarting(description);
						storeRetryCandidateTest(description);
						methodStatement.evaluate();
					} catch (AssumptionViolatedException e) {
						failureException = e;
						testNotifier.fireTestIgnored();
					} catch (StoppedByUserException e) {
						failureException = e;
						throw e;
					} catch (MultipleFailureException e) {
						List<Throwable> failures = e.getFailures();
						boolean retry = hasRetryExceptionInFailuresList(failures);
						if (retry) {
							Log.warn(String.format("Retry - Class | Method : [%s.%s] on exception -> %s", description.getClassName(), description.getMethodName(),
							ExceptionUtility.getStackTraceString(e)));
							failureException = retryTest(testNotifier, methodStatement, description, e);
						} else {
							failureException = e;
						}
					} catch (RetryException | UnreachableBrowserException | SessionNotFoundException e) {
						Log.warn(String.format("Retry - Class | Method : [%s.%s] on exception -> %s", description.getClassName(), description.getMethodName(),
						ExceptionUtility.getStackTraceString(e)));
						failureException = retryTest(testNotifier, methodStatement, description, e);
					} catch (Throwable e) {
						failureException = e;
						testNotifier.addFailure(e);
					} finally {
						try {
							releaseRetryCandidateTest(description);
							if (failureException != null) {
								BaseTest.reportTestFailed(failureException, description);
							} else {
								BaseTest.reportTestSucceeded(description);
							}
						} finally {
							BaseTest.reportTestFinished(description);
							testNotifier.fireTestFinished();
						}
					}
				}

				private boolean hasRetryExceptionInFailuresList(List<Throwable> failures) {
					if (failures == null) {
						return false;
					}

					for (Throwable failure: failures) {
						if (failure.getClass().equals(MultipleFailureException.class)) {
							if (hasRetryExceptionInFailuresList(((MultipleFailureException)failure).getFailures())) {
								return true;
							}
						}

						if (failure.getClass().equals(RetryException.class) ||
						failure.getClass().equals(UnreachableBrowserException.class) ||
						failure.getClass().equals(SessionNotFoundException.class)) {
							return true;
						}
					}

					return false;
				}

				private void storeRetryCandidateTest(Description description) {
					String testIdentifier = String.format("%s.%s", description.getClassName(), description.getMethodName());
					if (!ThreadLocalStore.getRetriedTests().contains(testIdentifier)) {
						ThreadLocalStore.getRetriedTests().add(testIdentifier);
					}
				}

				private void releaseRetryCandidateTest(Description description) {
					String testIdentifier = String.format("%s.%s", description.getClassName(), description.getMethodName());
					if (ThreadLocalStore.getRetriedTests().contains(testIdentifier)) {
						ThreadLocalStore.getRetriedTests().remove(testIdentifier);
					}
				}

			};

			runLeaf(statement, description, notifier);
		}
	}

	private Throwable retryTest(EachTestNotifier testNotifier, Statement statement, Description description, Throwable firstException) {
		int attempt = 1;
		Throwable exceptionOnRetry = firstException;
		do {
			try {
				Log.info(String.format("[RETRYING TEST] Attempt - %d", attempt));
				statement.evaluate();
				return null;

			} catch (AssumptionViolatedException e) {
				testNotifier.addFailedAssumption(e);
				return e;
			} catch (StoppedByUserException e) {
				throw e;
			} catch (UnreachableBrowserException | SessionNotFoundException e) {
				Log.warn(String.format("Retry - Class | Method : [%s.%s] on exception -> %s", description.getClassName(), description.getMethodName(),
				ExceptionUtility.getStackTraceString(e)));
			} catch (Throwable e) {
				exceptionOnRetry = e;
			}

			attempt++;

		} while (attempt <= MAX_ATTEMPTS);

		testNotifier.addFailure(exceptionOnRetry);
		return exceptionOnRetry;
	}
}

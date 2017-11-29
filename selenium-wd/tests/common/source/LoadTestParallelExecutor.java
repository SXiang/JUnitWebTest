package common.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import common.source.LoadTestExecutor.TestResult;

public class LoadTestParallelExecutor {
	private ExecutorService executorService;
	private CountDownLatch remainingTestCountDown;
	private List<LoadTestJob> jobs;
	private List<TestResult> testResults;

	private LoadTestParallelExecutor() { }

	public static LoadTestParallelExecutor newParallelExecutor() throws IOException {
		LoadTestParallelExecutor parallelExecutor = new LoadTestParallelExecutor();
		parallelExecutor.executorService = Executors.newCachedThreadPool();
		parallelExecutor.jobs = new ArrayList<LoadTestJob>();
		parallelExecutor.testResults = new ArrayList<TestResult>();
		return parallelExecutor;
	}

	public LoadTestParallelExecutor addTestJob(LoadTestJob job) {
		this.jobs.add(job);
		return this;
	}

	public static class ParallelLoadTestRunner implements Runnable {
		private LoadTestJob loadTestJob;
		private LoadTestExecutor loadTestExecutor;
		private Consumer<TestResult> testResultListener;
		private CountDownLatch countDownLatch;

		public ParallelLoadTestRunner(LoadTestJob loadTestJob, CountDownLatch countDownLatch) throws IOException {
			this.loadTestJob = loadTestJob;
			this.countDownLatch = countDownLatch;
			this.loadTestExecutor = LoadTestExecutor.newExecutor();
		}

		@Override
		public void run() {
			try {
				Log.info(String.format("Executing load test job -> %s", this.loadTestJob));
				TestResult testResult = this.loadTestExecutor.executeTest(this.loadTestJob.getTestCaseName(),
						this.loadTestJob.getApiURL(),
						this.loadTestJob.getContentType(),
						this.loadTestJob.getRequestBody(),
						this.loadTestJob.getMethod(),
						this.loadTestJob.getConcurrentRequests(),
						this.loadTestJob.getRequestsInOneSession(),
						this.loadTestJob.getNumPrimingRuns(),
						this.loadTestJob.getExpectedResponseContentLength());

				assertTrue(String.format("Load test failed. Test result file '%s' NOT found.", loadTestExecutor.getTestResultFile()), testResult != null);
				Log.info(String.format("Test result -> %s", testResult.toString()));

				if (getTestResultListener() != null) {
					getTestResultListener().accept(testResult);
				}

				this.countDownLatch.countDown();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public Consumer<TestResult> getTestResultListener() {
			return testResultListener;
		}

		public void setTestResultListener(Consumer<TestResult> testResultListener) {
			this.testResultListener = testResultListener;
		}
	}

	public List<TestResult> getTestResults() {
		return this.testResults;
	}

	public void invokeTestResultListener(TestResult testResult) {
		this.testResults.add(testResult);
	}

	public LoadTestParallelExecutor startTest() throws Exception {
		try {
			remainingTestCountDown = new CountDownLatch(this.jobs.size());
			for (LoadTestJob loadJob : jobs) {
				ParallelLoadTestRunner testRunner = new ParallelLoadTestRunner(loadJob, remainingTestCountDown);
				testRunner.setTestResultListener((tr) -> invokeTestResultListener(tr));
				executorService.execute(testRunner);
			}

			remainingTestCountDown.await();
		} catch (Exception ex) {
			Log.error("Error in test. Error -> " + ExceptionUtility.getStackTraceString(ex));
			throw new Exception(ex);
		} finally {
			executorService.shutdown();
			executorService.awaitTermination(20, TimeUnit.SECONDS);
		}

		return this;
	}
}
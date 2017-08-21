package common.source;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

public class ServiceCorrector {
	public static class ServiceInfo {
		public String processName;
		public String processCmdLine;
		public String serviceMethodArgs;
		public Predicate<String> invokeServiceMethod;

		public ServiceInfo(String processName, String processCmdLine, String serviceMethodArgs, Predicate<String> invokeServiceMethod) {
			this.processName = processName;
			this.processCmdLine = processCmdLine;
			this.serviceMethodArgs = serviceMethodArgs;
			this.invokeServiceMethod = invokeServiceMethod;
		}
	}

	private static final Integer INTERVAL_IN_SECONDS = 2;
	private static final Integer MAX_ATTEMPTS = 1000;

	private ExecutorService correctorService;
	private SvcCorrector corrector;

	public static class SvcCorrector implements Runnable {
		private ServiceInfo[] svcInfos = null;
		private List<String> servicesList = null;
		private boolean cancelled;

		public SvcCorrector(ServiceInfo[] expectedSvcs) {
			this.svcInfos = expectedSvcs;
			this.servicesList = new ArrayList<>();
		}

		@Override
		public void run() {
			int attempt = 0;
			while (attempt < MAX_ATTEMPTS) {
				if (isCancelled()) {
					break;
				}

				try {
					detectAndCorrect();
					Thread.sleep(INTERVAL_IN_SECONDS * 1000);
				} catch (Exception e) {
					Log.warn(e.getMessage());
				}

				attempt++;
			}
		}

		public boolean isCancelled() {
			return cancelled;
		}

		public void setCancelled(boolean cancelled) {
			this.cancelled = cancelled;
		}

		public List<String> getRunningServices() {
			return servicesList;
		}

		private void detectAndCorrect() throws Exception {
			if (svcInfos != null) {
				for (ServiceInfo svcInfo : svcInfos) {
					boolean isRunning = ProcessUtility.isProcessRunning(svcInfo.processName, svcInfo.processCmdLine, false /*infoLogEnabled*/);
					if (!isRunning) {
						this.servicesList.add(String.format("ERROR: [%s] - NOT Found %s (%s)", DateUtility.getLongDateString(LocalDateTime.now()), svcInfo.processName, svcInfo.processCmdLine));
						Log.error(String.format("Process - '%s' is NOT running. Attempting to fix -> Executing invoke process with args - '%s'",
								svcInfo.processName, (svcInfo.serviceMethodArgs != null) ? svcInfo.serviceMethodArgs : ""));
						svcInfo.invokeServiceMethod.test(svcInfo.serviceMethodArgs);
						Log.info(String.format("Invoked process - '%s'", svcInfo.processName));
					} else {
						this.servicesList.add(String.format("INFO: [%s] - Found %s (%s)", DateUtility.getLongDateString(LocalDateTime.now()), svcInfo.processName, svcInfo.processCmdLine));
					}
				}
			}
		}
	}

	public void startCorrector(ServiceInfo[] expectedServices) {
		correctorService = Executors.newSingleThreadExecutor();
		this.corrector = new SvcCorrector(expectedServices);
		correctorService.execute(this.corrector);
	}

	public void stopCorrector() {
		this.corrector.setCancelled(true);
		close();
	}

	public List<String> getRunningServices() throws Exception {
		this.corrector.detectAndCorrect();
		return this.corrector.getRunningServices();
	}

	private void close() {
		correctorService.shutdownNow();
	}
}
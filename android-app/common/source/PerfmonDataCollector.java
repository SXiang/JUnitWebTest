package common.source;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerfmonDataCollector {

	private static final Integer INTERVAL_IN_SECONDS = 2;
	private static final Integer MAX_ATTEMPTS = 1000;

	private ExecutorService perfmonExecutor;
	private PerfmonDumpsys perfDumpsys;

	public static class PerfmonDumpsys implements Runnable {
		private List<String> cpuInfos = new ArrayList<String>();
		private List<String> gfxInfos = new ArrayList<String>();
		private boolean cancelled;

		public PerfmonDumpsys() {
		}

		@Override
		public void run() {
			int attempt = 0;
			while (attempt < MAX_ATTEMPTS) {
				if (isCancelled()) {
					break;
				}

				try {
					flushMetrics();
					Thread.sleep(INTERVAL_IN_SECONDS * 1000);
				} catch (Exception e) {
					Log.warn(e.getMessage());
				}

				attempt++;
			}
		}

		public void flushMetrics() throws Exception {
			flushCpuInfo();
			flushGfxInfo();
		}

		private void flushGfxInfo() throws Exception {
			getGfxInfos().add(AndroidAutomationTools.getGraphicsInfo());
		}

		private void flushCpuInfo() throws Exception {
			getCpuInfos().add(AndroidAutomationTools.getCpuInfo());
		}

		public boolean isCancelled() {
			return cancelled;
		}

		public void setCancelled(boolean cancelled) {
			this.cancelled = cancelled;
		}

		public List<String> getCpuInfos() {
			return cpuInfos;
		}

		public List<String> getGfxInfos() {
			return gfxInfos;
		}
	}

	public void startCollectors() {
		perfmonExecutor = Executors.newSingleThreadExecutor();
		this.perfDumpsys = new PerfmonDumpsys();
		perfmonExecutor.execute(this.perfDumpsys);
	}

	public void stopCollectors() {
		this.perfDumpsys.setCancelled(true);
		close();
	}

	public List<String> getCpuMetrics() throws Exception {
		this.perfDumpsys.flushCpuInfo();
		return this.perfDumpsys.getCpuInfos();
	}

	public List<String> getGfxMetrics() throws Exception {
		this.perfDumpsys.flushGfxInfo();
		return this.perfDumpsys.getGfxInfos();
	}

	private void close() {
		perfmonExecutor.shutdownNow();
	}
}
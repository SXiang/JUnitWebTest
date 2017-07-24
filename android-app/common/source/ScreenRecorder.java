package common.source;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScreenRecorder {
	private Recording recording;
	private ExecutorService recordingExecutor;

	public static class Recording implements Runnable {
		private String remoteVideoFilePath;

		public Recording(String videoFilePath) {
			this.remoteVideoFilePath = videoFilePath;
		}

		@Override
		public void run() {
			AdbInterface.startScreenRecording(this.remoteVideoFilePath);
		}
	}

	public void startRecording(String videoFilePath) {
		recordingExecutor = Executors.newSingleThreadExecutor();
		this.recording = new Recording(videoFilePath);
		recordingExecutor.execute(this.recording);
	}

	public void stopRecording() {
		recordingExecutor.shutdownNow();
	}
}

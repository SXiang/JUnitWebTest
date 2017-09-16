package common.source;

public class Timer {
	private static final long MILLIS_IN_ONE_SECOND = 1000L;

	private Long timeout;
	private Long elapsedTime;
	private Long startTimeMillis;

	public static Timer createNew(Integer seconds) {
		return createNewInternal(seconds * MILLIS_IN_ONE_SECOND);
	}

	public static Timer createNew(Integer minutes, Integer seconds) {
		return createNewInternal((secondsInMinutes(minutes) + seconds) * MILLIS_IN_ONE_SECOND);
	}

	public static Timer createNew(Integer hours, Integer minutes, Integer seconds) {
		return createNewInternal((secondsInHours(hours) + secondsInMinutes(minutes) + seconds) * MILLIS_IN_ONE_SECOND);
	}

	private static Timer createNewInternal(Long timeoutInMilliSecs) {
		if (timeoutInMilliSecs < 1) {
			throw new IllegalArgumentException("'timeoutInMilliSecs' should be a positive number.");
		}

		Timer timer = new Timer();
		timer.setTimeout(timeoutInMilliSecs);
		timer.setStartTimeMillis(System.currentTimeMillis());
		return timer;
	}

	private static int minutesInHours(Integer hours) {
		return 60 * hours;
	}

	private static int secondsInHours(Integer hours) {
		return secondsInMinutes(minutesInHours(hours));
	}

	private static int secondsInMinutes(Integer minutes) {
		return 60 * minutes;
	}

	public Long getElapsedTime() {
		refreshElapsedTime();
		return this.elapsedTime;
	}

	public boolean hasElapsed() {
		refreshElapsedTime();
		return elapsedTime >= timeout;
	}

	private void refreshElapsedTime() {
		elapsedTime = System.currentTimeMillis() - getStartTimeMillis();
	}

	private void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	private Long getStartTimeMillis() {
		return this.startTimeMillis;
	}

	private void setStartTimeMillis(Long startTimeMillis) {
		this.startTimeMillis = startTimeMillis;
	}
}
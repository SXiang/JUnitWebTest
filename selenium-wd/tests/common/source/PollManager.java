package common.source;

public class PollManager {
	
	/**
	 * Keeps polling until timeout or the poll condition is TRUE.
	 * @param pollCondition - functional method to poll.
	 * @param waitInMSexBetweenPoll - wait time in msec between poll.
	 * @param maxRetries - max number of polls.
	 */
	public static void poll(PollCondition pollCondition, Integer waitInMSecsBetweenPoll, Integer maxRetries) {
		Integer retryAttempt = 0;
		do {
			try {
				retryAttempt++;
				Thread.sleep(waitInMSecsBetweenPoll);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (pollCondition.waitCondition() && (retryAttempt < maxRetries));
	}
}

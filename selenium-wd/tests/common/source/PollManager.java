package common.source;

import com.relevantcodes.extentreports.utils.ExceptionUtil;

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
				Log.info(String.format("polling attempt -> [%d]", retryAttempt));
				Thread.sleep(waitInMSecsBetweenPoll);
			} catch (InterruptedException e) {
				Log.error(ExceptionUtil.getStackTrace(e));
			}
		} while (pollCondition.waitCondition() && (retryAttempt < maxRetries));
	}
}

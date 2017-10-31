package surveyor.unittest.source;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.Timer;

public class TimerTest {

	private static final long MILLIS_IN_ONE_SECOND = 1000L;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testTimer_SecondsNotElapsed_Success() throws InterruptedException {
		int secs = 3;
		Timer timer = Timer.createNew(secs);
		Thread.sleep((secs-1) * MILLIS_IN_ONE_SECOND);
		assertFalse(String.format("Timer should NOT have timed out. Elapsed time in msecs = [%d]", timer.getElapsedTime()), timer.hasElapsed());
	}

	@Test
	public void testTimer_SecondsElapsed_Success() throws InterruptedException {
		int secs = 3;
		Timer timer = Timer.createNew(secs);
		Thread.sleep(secs * MILLIS_IN_ONE_SECOND);
		assertTrue(String.format("Timer should have timed out. Elapsed time in msecs = [%d]", timer.getElapsedTime()), timer.hasElapsed());
	}

	@Test
	public void testTimer_MinutesSecondsNotElapsed_Success() throws InterruptedException {
		int mins = 0;
		int secs = 2;
		Timer timer = Timer.createNew(secs);
		Thread.sleep(((60 * mins) + secs - 1) * MILLIS_IN_ONE_SECOND);
		assertFalse(String.format("Timer should NOT have timed out. Elapsed time in msecs = [%d]", timer.getElapsedTime()), timer.hasElapsed());
	}

	@Test
	public void testTimer_MinutesSecondsElapsed_Success() throws InterruptedException {
		int mins = 0;
		int secs = 2;
		Timer timer = Timer.createNew(secs);
		Thread.sleep(((60 * mins) + secs) * MILLIS_IN_ONE_SECOND);
		assertTrue(String.format("Timer should have timed out. Elapsed time in msecs = [%d]", timer.getElapsedTime()), timer.hasElapsed());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTime_InvalidArgument_ThrowsException() {
		Timer.createNew(0, 0, 0);
	}
}
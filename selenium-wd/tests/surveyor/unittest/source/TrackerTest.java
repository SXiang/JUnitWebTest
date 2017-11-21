package surveyor.unittest.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import common.source.Log;
import surveyor.scommon.source.BaseTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrackerTest extends BaseTest {
	private static Tracker classTracker = Tracker.newTracker(true);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseTest.setUpBeforeClass();
	}

	@Test
	public void testTracker_ClassLevelTrackerNoFailures_ShouldNotReturnError() {
		classTracker = Tracker.newTracker(true);

		withTrackerExecute(classTracker, () -> {
			assertTrue("Simulate assert pass.", true);
		});

		withTrackerExecute(classTracker, () -> {
			Log.info("Simulate pass.");
		});

		assertTrue(classTracker.failureEncountered() == false);
	}

	@Test
	public void testTracker_TestLevelTrackerNoFailures_ShouldNotReturnError() {
		Tracker testTracker = Tracker.newTracker(true);

		withTrackerExecute(testTracker, () -> {
			assertTrue("Simulate assert pass.", true);
		});

		withTrackerExecute(testTracker, () -> {
			Log.info("Simulate pass.");
		});

		assertTrue(testTracker.failureEncountered() == false);
	}

	// ## Turn ON the tests below to test assertion behavior in 'withTrackerExecute'.

	// Expected: Test fails on assertion.
	@Ignore
	public void testTracker_ClassLevelTrackerExceptionOnce_ShouldReturnError() {
		classTracker = Tracker.newTracker(true);

		withTrackerExecute(classTracker, () -> {
			throw new Exception("Exception #1");
		});

		withTrackerExecute(classTracker, () -> {
			Log.info("Simulate pass.");
		});

		assertTrue(classTracker.failureEncountered() == true);
	}

	// Expected: Test fails on assertion.
	@Ignore
	public void testTracker_ClassLevelTracker01_ExceptionCase_ShouldFailWithAssertion() {
		withTrackerExecute(classTracker, () -> {
			throw new Exception("Simulator Exception from test actions.");
		});
	}

	// Expected: Test fails on assertion.
	@Ignore
	public void testTracker_ClassLevelTracker02_AssertCase_ShouldFailWithAssertion() {
		withTrackerExecute(classTracker, () -> {
			assertTrue("Simulate assert failure", false);
		});
	}

	// Expected: Test passes after previous two tests failed on assertion.
	@Ignore
	public void testTracker_ClassLevelTracker03_RunAfterFailuresEncountered_FailureShouldBeSeen() {
		assertTrue("Failure flag should be set to TRUE as previous test were flagged as FAIL", classTracker.failureEncountered());
	}

	// Expected: Test fails on assertion.
	@Ignore
	public void testTracker_TestLevelTrackerExceptionOnce_ShouldReturnError() {
		Tracker testTracker = Tracker.newTracker(true);

		withTrackerExecute(testTracker, () -> {
			throw new Exception("Exception #1");
		});

		withTrackerExecute(testTracker, () -> {
			Log.info("Simulate pass.");
		});

		assertTrue(testTracker.failureEncountered() == true);
	}
}
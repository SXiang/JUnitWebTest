package unittest.source;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.NetworkEmulation;
import common.source.NetworkEmulation.NetworkDelay;
import common.source.NetworkEmulation.NetworkSpeed;

public class NetworkEmulationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreateDefault() {
		final String expectedArgs = "-netfast";
		final String emulatorArgs = NetworkEmulation.createDefault().toEmulatorArgs();
		assertTrue(String.format("Expected=[%s]; Actual=[%s]", expectedArgs, emulatorArgs), emulatorArgs.equals(expectedArgs));
	}

	@Test
	public void testCreateNewWithNetworkDelayNetworkSpeed() {
		final String expectedArgs = "-netdelay evdo -netspeed gprs -engine classic -tcpdump C:\\QATestLogs\\UNITTEST_testCreateNewWithNetworkDelayNetworkSpeed.tcpdump.cap";
		final NetworkDelay delay = NetworkDelay.EVDO;
		final NetworkSpeed networkSpeed = NetworkSpeed.GPRS;
		final String tcpDumpFile = "C:\\QATestLogs\\UNITTEST_testCreateNewWithNetworkDelayNetworkSpeed.tcpdump.cap";
		final String emulatorArgs = NetworkEmulation.createNew(delay, networkSpeed, tcpDumpFile).toEmulatorArgs();
		assertTrue(String.format("Expected=[%s]; Actual=[%s]", expectedArgs, emulatorArgs), emulatorArgs.equals(expectedArgs));
	}

	@Test
	public void testCreateNewWithNetworkSpeedAbsNetworkDelay() {
		final String expectedArgs = "-netdelay 100 -netspeed lte -engine classic -tcpdump C:\\QATestLogs\\UNITTEST_testCreateNewWithNetworkSpeedAbsNetworkDelay.tcpdump.cap";
		final NetworkSpeed speed = NetworkSpeed.LTE;
		final Integer networkDelay = 100;
		final String tcpDumpFile = "C:\\QATestLogs\\UNITTEST_testCreateNewWithNetworkSpeedAbsNetworkDelay.tcpdump.cap";
		final String emulatorArgs = NetworkEmulation.createNew(speed, networkDelay, tcpDumpFile).toEmulatorArgs();
		assertTrue(String.format("Expected=[%s]; Actual=[%s]", expectedArgs, emulatorArgs), emulatorArgs.equals(expectedArgs));
	}

	@Test
	public void testCreateNewWithNetworkSpeedMinNetworkDelayMaxNetworkDelay() {
		final String expectedArgs = "-netdelay 100:200 -netspeed umts -engine classic -tcpdump C:\\QATestLogs\\UNITTEST_testCreateNewWithNetworkSpeedMinNetworkDelayMaxNetworkDelay.tcpdump.cap";
		final NetworkSpeed speed = NetworkSpeed.UMTS;
		final Integer minDelay = 100;
		final Integer maxDelay = 200;
		final String tcpDumpFile = "C:\\QATestLogs\\UNITTEST_testCreateNewWithNetworkSpeedMinNetworkDelayMaxNetworkDelay.tcpdump.cap";
		final String emulatorArgs = NetworkEmulation.createNew(speed, minDelay, maxDelay, tcpDumpFile).toEmulatorArgs();
		assertTrue(String.format("Expected=[%s]; Actual=[%s]", expectedArgs, emulatorArgs), emulatorArgs.equals(expectedArgs));
	}

	@Test
	public void testCreateNewWithNetworkDelayAbsNetworkSpeed() {
		final String expectedArgs = "-netdelay none -netspeed 1024 -engine classic -tcpdump C:\\QATestLogs\\UNITTEST_testCreateNewWithNetworkDelayAbsNetworkSpeed.tcpdump.cap";
		final NetworkDelay delay = NetworkDelay.NONE;
		final Integer speed = 1024;
		final String tcpDumpFile = "C:\\QATestLogs\\UNITTEST_testCreateNewWithNetworkDelayAbsNetworkSpeed.tcpdump.cap";
		final String emulatorArgs = NetworkEmulation.createNew(delay, speed, tcpDumpFile).toEmulatorArgs();
		assertTrue(String.format("Expected=[%s]; Actual=[%s]", expectedArgs, emulatorArgs), emulatorArgs.equals(expectedArgs));
	}

	@Test
	public void testCreateNewWithNetworkDelayUpNetworkSpeedDownNetworkSpeed() {
		final String expectedArgs = "-netdelay gsm -netspeed 1024:2048 -engine classic -tcpdump C:\\QATestLogs\\UNITTEST_testCreateNewWithNetworkDelayUpNetworkSpeedDownNetworkSpeed.tcpdump.cap";
		final NetworkDelay delay = NetworkDelay.GSM;
		final Integer upSpeed = 1024;
		final Integer downSpeed = 2048;
		final String tcpDumpFile = "C:\\QATestLogs\\UNITTEST_testCreateNewWithNetworkDelayUpNetworkSpeedDownNetworkSpeed.tcpdump.cap";
		final String emulatorArgs = NetworkEmulation.createNew(delay, upSpeed, downSpeed, tcpDumpFile).toEmulatorArgs();
		assertTrue(String.format("Expected=[%s]; Actual=[%s]", expectedArgs, emulatorArgs), emulatorArgs.equals(expectedArgs));
	}

	@Test
	public void testCreateNewMinDelayMaxDelayUpSpeedDownSpeed() {
		final String expectedArgs = "-netdelay 100:200 -netspeed 1024:2048 -engine classic -tcpdump C:\\QATestLogs\\UNITTEST_testCreateNewMinDelayMaxDelayUpSpeedDownSpeed.tcpdump.cap";
		final Integer minDelay = 100;
		final Integer maxDelay = 200;
		final Integer upSpeed = 1024;
		final Integer downSpeed = 2048;
		final String tcpDumpFile = "C:\\QATestLogs\\UNITTEST_testCreateNewMinDelayMaxDelayUpSpeedDownSpeed.tcpdump.cap";
		final String emulatorArgs = NetworkEmulation.createNew(minDelay, maxDelay, upSpeed, downSpeed, tcpDumpFile).toEmulatorArgs();
		assertTrue(String.format("Expected=[%s]; Actual=[%s]", expectedArgs, emulatorArgs), emulatorArgs.equals(expectedArgs));
	}

	@Test
	public void testCreateNewDelaySpeed() {
		final String expectedArgs = "-netdelay 100 -netspeed 1024 -engine classic -tcpdump C:\\QATestLogs\\UNITTEST_testCreateNewDelaySpeed.tcpdump.cap";
		final Integer delay = 100;
		final Integer speed = 1024;
		final String tcpDumpFile = "C:\\QATestLogs\\UNITTEST_testCreateNewDelaySpeed.tcpdump.cap";
		final String emulatorArgs = NetworkEmulation.createNew(delay, speed, tcpDumpFile).toEmulatorArgs();
		assertTrue(String.format("Expected=[%s]; Actual=[%s]", expectedArgs, emulatorArgs), emulatorArgs.equals(expectedArgs));
	}
}
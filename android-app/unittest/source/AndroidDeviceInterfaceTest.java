package unittest.source;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.AdbInterface;
import common.source.AndroidAutomationTools;
import common.source.AndroidDeviceInterface;
import common.source.Log;
import common.source.TestContext;
import surveyor.scommon.source.BaseTest;

public class AndroidDeviceInterfaceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseTest.initializeTestObjects();
		AdbInterface.init(TestContext.INSTANCE.getTestSetup().getAdbLocation());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		AdbInterface.stop();
	}

	@Before
	public void setUp() throws Exception {
		BaseTest.initializeTestObjects();
		AndroidAutomationTools.start();
	}

	@After
	public void tearDown() throws Exception {
		BaseTest.logoutQuitDriver();
		AndroidAutomationTools.stop();
	}

	@Test
	public void turnOnGpsTest() throws Exception {
		Log.info("Excecuting test -> turnOnGpsTest() ...");
		AndroidDeviceInterface.turnOnGps();
		assertTrue("GPS was NOT turned ON correctly.", AndroidDeviceInterface.isGpsOn());
	}

	@Test
	public void turnOffGpsTest() throws Exception {
		Log.info("Excecuting test -> turnOffGpsTest() ...");
		AndroidDeviceInterface.turnOffGps();
		assertFalse("GPS was NOT turned OFF correctly.", AndroidDeviceInterface.isGpsOn());
	}

	@Test
	public void turnOnNetworkTest() throws Exception {
		Log.info("Excecuting test -> turnOnNetworkTest() ...");
		AndroidDeviceInterface.turnOnNetwork();
		assertTrue("Network was NOT turned ON correctly.", AndroidDeviceInterface.isNetworkOn());
	}

	@Test
	public void turnOffNetworkTest() throws Exception {
		Log.info("Excecuting test -> turnOffNetworkTest() ...");
		AndroidDeviceInterface.turnOffNetwork();
		assertFalse("Network was NOT turned OFF correctly.", AndroidDeviceInterface.isNetworkOn());
	}

	@Test
	public void turnOnWifiTest() throws Exception {
		Log.info("Excecuting test -> turnOnWifiTest() ...");
		AndroidDeviceInterface.turnOnWifi();
		assertTrue("Wifi was NOT turned ON correctly.", AndroidDeviceInterface.isWifiOn());
	}

	@Test
	public void turnOffWifiTest() throws Exception {
		Log.info("Excecuting test -> turnOffWifiTest() ...");
		AndroidDeviceInterface.turnOffWifi();
		assertFalse("Wifi was NOT turned OFF correctly.", AndroidDeviceInterface.isWifiOn());
	}
}
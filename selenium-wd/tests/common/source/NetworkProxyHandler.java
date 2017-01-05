package common.source;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy;
import org.testng.Assert;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import surveyor.dataaccess.source.Survey;

public class NetworkProxyHandler {

	private BrowserMobProxy networkProxy;

	public static String getNetworkProxyHarFileContent() throws Exception {
		Har harData = new NetworkProxyHandler().getNetworkProxyHarData();
		String harDataFile = TestSetup.getUUIDString() + "_HarData.dat";
		String harDataFullPath = Paths.get(TestSetup.getSystemTempDirectory(), harDataFile).toString();
		Log.info(String.format("Creating HAR data file at: %s", harDataFullPath));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(harDataFullPath));
		try {
			// HarData file should be created in the system temp directory.
			harData.writeTo(bufferedWriter);
			Log.info(String.format("Created HAR data file at: %s", harDataFullPath));
		} catch (IOException e) {
			Log.error(e.toString());
		} finally {
			bufferedWriter.close();
		}

		return FileUtility.readFileContents(harDataFullPath);
	}

	/* NETWORK PROXY related methods */
	/*
	 * EXAMPLE USAGE: 1. Using Proxy to limit Upstream/Downstream KBPS.
	 * startNetworkProxy(true|false); setNetworkProxyDownstreamKbps(<long>);
	 * setNetworkProxyUpstreamKbps(<long>); ... <perform test actions> ...
	 * stopNetworkProxy();
	 *
	 * 2. Using Proxy to turn OFF/ON HTTP Traffic for Selenium tests.
	 * turnOffHttpTraffic(); ... <perform test actions> ... turnOnHttpTraffic()
	 */

	public void startNetworkProxy(boolean createHarFile) throws MalformedURLException {
		// start the proxy
		networkProxy = new BrowserMobProxyServer();
		networkProxy.start(0);

		// when we start the network proxy we are recycling the driver object.
		// Quit the driver if present.
		if (WebDriverFactory.getDriver() != null) {
			WebDriverFactory.getDriver().quit();
		}

		// get the selenium proxy object
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(networkProxy);
		WebDriverFactory.setChromeBrowserCapabilities(seleniumProxy);

		if (createHarFile) {
			// create new Har file.
			networkProxy.newHar("Automation Proxy Results");
		}
	}

	public void stopNetworkProxy() {
		if (networkProxy != null) {
			if (networkProxy.isStarted()) {
				networkProxy.stop();
			}
		}
	}

	public Har getNetworkProxyHarData() {
		if (networkProxy != null) {
			return networkProxy.getHar();
		}
		return null;
	}

	public void setNetworkProxyDownstreamKbps(long kbps) {
		if (networkProxy != null) {
			networkProxy.setReadBandwidthLimit(kbps);
		}
	}

	public void setNetworkProxyUpstreamKbps(long kbps) {
		if (networkProxy != null) {
			networkProxy.setWriteBandwidthLimit(kbps);
		}
	}

	public void setNetworkProxyLatency(long latency, TimeUnit timeUnit) {
		if (networkProxy != null) {
			networkProxy.setLatency(latency, timeUnit);
		}
	}

	public void turnOffHttpTraffic() throws MalformedURLException {
		if (networkProxy == null || !networkProxy.isStarted()) {
			startNetworkProxy(false /* createHarFile */);
		}

		this.setNetworkProxyDownstreamKbps(0);
		this.setNetworkProxyUpstreamKbps(0);
	}

	public void turnOnHttpTraffic() {
		if (networkProxy != null) {
			// when we stop the network proxy we should recycling the driver
			// object to remove the Proxy capability.
			if (WebDriverFactory.getDriver() != null) {
				WebDriverFactory.getDriver().quit();
			}

			WebDriverFactory.setChromeBrowserCapabilities(); // No proxy.
			this.stopNetworkProxy();
		}
	}

	/**
	 * Tests startNetworkProxy() method.
	 *
	 * @throws MalformedURLException
	 */
	private static void testStartNetworkProxy() throws MalformedURLException {
		new NetworkProxyHandler().startNetworkProxy(true);
	}

	/**
	 * Tests stopNetworkProxy() method.
	 */
	private static void testStopNetworkProxy() {
		new NetworkProxyHandler().stopNetworkProxy();
	}

	/**
	 * Tests getNetworkProxyHarData() method.
	 *
	 * @param testSetup
	 */
	private static void testHarDataFile() throws IOException {
		Har harData = new NetworkProxyHandler().getNetworkProxyHarData();
		Assert.assertTrue(harData != null, "Har Data should NOT be NULL.");
		String harDataFile = TestSetup.getUUIDString() + "_HarData.dat";
		String harDataFullPath = Paths.get(TestSetup.getSystemTempDirectory(), harDataFile).toString();
		Log.info(String.format("Creating HAR data file at: %s", harDataFullPath));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(harDataFullPath));
		try {
			// HarData file should be created in the system temp directory.
			harData.writeTo(bufferedWriter);
			Log.info(String.format("Created HAR data file at: %s", harDataFullPath));
		} catch (IOException e) {
			// No exception thrown here.
			e.printStackTrace();
		} finally {
			bufferedWriter.close();
		}
		Assert.assertTrue(FileUtility.readFileContents(harDataFullPath).length() > 0,
				"HarData file should have content.");
	}

	/**
	 * Tests turnOnNetworkConnection() method.
	 *
	 * @param testSetup
	 */
	private static void testNetworkConnectionOn(String validTag, Survey objSurvey) {
		NetworkProxyHandler networkProxyHandler = new NetworkProxyHandler();
		networkProxyHandler.turnOnHttpTraffic();
		try {
			objSurvey = Survey.getSurvey(validTag);
			TestContext.INSTANCE.getTestSetup().getDriver().get(TestContext.INSTANCE.getTestSetup().getBaseUrl());
		} catch (Exception e) {
			// No exception should be thrown here, as Network Connection is back
			// ON.
			Log.error("UNEXPECTED ERROR: " + ExceptionUtility.getStackTraceString(e));
		}
		Assert.assertTrue(objSurvey != null, "Survey object should NOT be NULL.");
		Assert.assertTrue(TestContext.INSTANCE.getTestSetup().getDriver().getPageSource().contains("Log In"));
	}

	/**
	 * Tests turnOffNetworkConnection() method.
	 *
	 * @param testSetup
	 * @throws MalformedURLException
	 */
	private static void testNetworkConnectionOff(String validTag, Survey objSurvey)
			throws MalformedURLException {
		new NetworkProxyHandler().turnOffHttpTraffic();
		try {
			objSurvey = Survey.getSurvey(validTag);
			TestContext.INSTANCE.getDriver().get(TestContext.INSTANCE.getTestSetup().getBaseUrl());
		} catch (Exception e) {
			// This should throw an exception as Network Connection is OFF.
			Log.info("EXPECTED ERROR: " + ExceptionUtility.getStackTraceString(e));
		}
		Assert.assertTrue(objSurvey != null, "Survey object should NOT be NULL. "
				+ "Network connection is NOT disabled. ONLY Http traffic should be disabled.");
		Assert.assertTrue(TestContext.INSTANCE.getDriver().getPageSource().contains("ERR_EMPTY_RESPONSE"));
	}

	/**
	 * Executes the unit tests for BrowserMobProxy related methods.
	 *
	 * @param testSetup
	 */
	private static void testBrowserMobProxyMethods() {
		String validTag = "stnd-sqacudr";
		Survey objSurvey = null;
		boolean stoppedProxy = false;
		try {
			Log.info("Running test - testStartNetworkProxy() ...");
			NetworkProxyHandler.testStartNetworkProxy();
			Log.info("Running test - testNetworkConnectionOff() ...");
			testNetworkConnectionOff(validTag, objSurvey);
			Log.info("Running test - testNetworkConnectionOn() ...");
			testNetworkConnectionOn(validTag, objSurvey);
			stoppedProxy = true;
			Log.info("Running test - testHarDataFile() ...");
			testHarDataFile();
		} catch (Exception e) {
			Assert.fail("UNEXPECTED EXCEPTION: " + ExceptionUtility.getStackTraceString(e));
		} finally {
			if (!stoppedProxy) {
				NetworkProxyHandler.testStopNetworkProxy();
				if (TestContext.INSTANCE.getDriver() != null) {
					TestContext.INSTANCE.getDriver().quit();
				}
			}
		}
	}

	public static void main(String[] args) {
		// Run the Unit test for BrowserMob Proxy.
		testBrowserMobProxyMethods();
	}
}
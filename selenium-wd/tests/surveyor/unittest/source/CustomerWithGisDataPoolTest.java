package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.BaseHelper;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.CustomerWithGisDataPool;

public class CustomerWithGisDataPoolTest {
	private static String CustomerNamePrefix = "AutomationSeedCustomer";
	private static Integer MAX_AVAILABLE_CUSTOMERS = 5;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestSetup testSetup = new TestSetup(true /* initialize */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initializeDBProperties();
		TestContext.INSTANCE.setTestSetup(testSetup);

		Log.info("RunUUID=" + TestContext.INSTANCE.getRunUniqueId());
	}

	@Test
	public void testAcquireCustomer_AcquireMultipleTimes_ReturnsDifferentCustomerEachTime() {
		// first release all customers.
		releaseAllCustomersInPool();

		final List<String> customerIds = new ArrayList<>();
		IntStream.range(0, MAX_AVAILABLE_CUSTOMERS)
			.forEach(i -> {
				Customer customer = CustomerWithGisDataPool.acquireCustomer();
				Log.info(String.format("Iteration=%d: Customer -> %s", i, customer));
				assertTrue("Acquired customer should NOT have been seen earlier.", !customerIds.contains(customer.getId()));
				customerIds.add(customer.getId());
			});
	}

	@Test
	public void testAcquireCustomer_WhenNoCustomerAvailable_ReturnsNull() {
		// first release all customers.
		releaseAllCustomersInPool();

		// try to acquire one more than the available customers in pool.
		IntStream.rangeClosed(0, MAX_AVAILABLE_CUSTOMERS)
			.forEach(i -> {
				Customer customer = CustomerWithGisDataPool.acquireCustomer();
				if (i < MAX_AVAILABLE_CUSTOMERS) {
					Log.info(String.format("Iteration=%d: Customer -> %s", i, customer));
					assertTrue("Pool NOT empty. Customer should be available.", customer != null);
				} else {
					Log.info(String.format("Last Iteration=%d: Customer expected to be NULL.", i));
					assertTrue("Pool should be exhausted. No customer should be returned.", customer == null);
				}
			});
	}

	@Test
	public void testReleaseCustomer_SameCustomerReleasedMultipleTimes_Passes() {
		// release same customer multiple times should not fail.
		final String customerName = "AutomationSeedCustomer00001";
		IntStream.range(0, 3)
			.forEach(i -> CustomerWithGisDataPool.releaseCustomer(customerName));
	}

	private static void releaseAllCustomersInPool() {
		IntStream.range(0, MAX_AVAILABLE_CUSTOMERS)
			.forEach(i -> {
				Integer padTimes = 5;
				Integer n = i + 1;
				while (n > 0) {
					n = n / 10;
					padTimes--;
				}

				Integer idx = i + 1;
				String customerName = CustomerNamePrefix + BaseHelper.prependStringWithChar(String.valueOf(idx), '0', padTimes);
				CustomerWithGisDataPool.releaseCustomer(customerName);
			});
	}
}

package surveyor.unittest.source;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.OLMapEntities;
import common.source.OLMapEntities.Indication;

public class OLMapEntitiesTest {
	private static OLMapEntities.Indication i1 = null;
	private static OLMapEntities.Indication i2 = null;
	private static OLMapEntities.Indication i3 = null;
	private static OLMapEntities.Indication i4 = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		i1 = new OLMapEntities().new Indication("fsd", "da", "addd", "", "", "", "", "", 1, 0.0, 0.0, "2.7");
		i2 = new OLMapEntities().new Indication("sfd", "f", "aa", "", "", "", "", "", 2, 0.0, 0.0, "2.1");
		i3 = new OLMapEntities().new Indication("af", "adfs", "sfd", "", "", "", "", "", 1, 0.0, 0.0, "2.7");
		i4 = new OLMapEntities().new Indication("asdf", "a", "adsf", "", "", "", "", "", 2, 0.0, 0.0, "2.1");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_IndicationSetEquals_True() {
		Set<Indication> set1 = new HashSet<Indication>();
		set1.add(i1);
		set1.add(i2);

		Set<Indication> set2 = new HashSet<Indication>();
		set2.add(i3);
		set2.add(i4);

		Assert.assertTrue(set1.equals(set2));
	}

	@Test
	public void test_IndicationSetEquals_False() {
		Set<Indication> set1 = new HashSet<Indication>();
		set1.add(i1);
		set1.add(i3);

		Set<Indication> set2 = new HashSet<Indication>();
		set2.add(i3);
		set2.add(i2);

		Assert.assertFalse(set1.equals(set2));
	}
}
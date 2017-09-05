package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.CollectionsUtil;

public class CollectionsUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testMatchesExpressionList() {
		List<String> listWithMatchStrings = new ArrayList<>();;
		List<String> listToMatch = new ArrayList<>();

		// sample expression match values from LeakDetailEntity.toPDFLeakDetails() call
		listWithMatchStrings.add("4 Found Gas Leak .* sqapicdr@picarro.com [0-9]+ days [0-9]{2}:[0-9]{2}:[0-9]{2}");
		listWithMatchStrings.add("Source: Gas Date/Time: .*");
		listWithMatchStrings.add("Investigator: sqapicdr@picarro.comLatitude: (\\d+\\.\\d+)?");
		listWithMatchStrings.add("Longitude: \\-?(\\d+\\.\\d+)?");
		listWithMatchStrings.add("Precison: (.*)m");
		listWithMatchStrings.add("Leak Grade: 381Address: Ln Tuscarawas");
		listWithMatchStrings.add("(598+, )?Brunswick");
		listWithMatchStrings.add("AZ");
		listWithMatchStrings.add("Map Number: 882Surface Reading: 301 \\(PPM\\)");
		listWithMatchStrings.add("Barhole Reading: 110 \\(PPM\\)");
		listWithMatchStrings.add("Paved Wall-To-Wall: true");
		listWithMatchStrings.add("Pipe Material Type: Cast IronLeak Location: Service");
		listWithMatchStrings.add("Surface Over Leak: Concrete");
		listWithMatchStrings.add("Meter Number: 872");
		listWithMatchStrings.add("Leak Location Remarks: brhhqajaiohnjurnrlpcjxfarpbqzqaphgvjjfhzcpozsgfctjnddmhmmncoezhxpqxqdgqgqryysgqsnmryxzlw");
		listWithMatchStrings.add("Additional Notes: lbkvrksdtrftrgqxnozochtxmznasrhbuxumjzbaqtqujnefvhaeafvcyqcodi");

		// samples values when extracted from PDF.
		listToMatch.add("4 Found Gas Leak 9/5/2017 12:18 PM PDT sqapicdr@picarro.com 0 days 00:02:40");
		listToMatch.add("Source: Gas Date/Time: 9/5/2017 12:20 PM PDT");
		listToMatch.add("Investigator: sqapicdr@picarro.comLatitude: 37.396753");
		listToMatch.add("Longitude: -121.984383");
		listToMatch.add("Precison: 19.886999130249m");
		listToMatch.add("Leak Grade: 381Address: Ln Tuscarawas");
		listToMatch.add("598, Brunswick");
		listToMatch.add("AZ");
		listToMatch.add("Map Number: 882Surface Reading: 301 (PPM)");
		listToMatch.add("Barhole Reading: 110 (PPM)");
		listToMatch.add("Paved Wall-To-Wall: True");
		listToMatch.add("Pipe Material Type: Cast IronLeak Location: Service");
		listToMatch.add("Surface Over Leak: Concrete");
		listToMatch.add("Meter Number: 872");
		listToMatch.add("Leak Location Remarks: brhhqajaiohnjurnrlpcjxfarpbqzqaphgvjjfhzcpozsgfctjnddmhmmncoezhxpqxqdgqgqryysgqsnmryxzlw");
		listToMatch.add("Additional Notes: lbkvrksdtrftrgqxnozochtxmznasrhbuxumjzbaqtqujnefvhaeafvcyqcodi");

		assertTrue("Expression list did NOT match.", CollectionsUtil.matchesExpressionList(listWithMatchStrings, listToMatch));
	}
}

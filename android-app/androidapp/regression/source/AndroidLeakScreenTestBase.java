package androidapp.regression.source;

import static org.junit.Assert.assertTrue;

import java.util.List;

import androidapp.entities.source.LeakListInfoEntity;
import androidapp.entities.source.OtherSourceListInfoEntity;
import common.source.Log;
import common.source.LogHelper;
import surveyor.scommon.mobile.source.LeakDataGenerator.LeakDataBuilder;

public class AndroidLeakScreenTestBase extends BaseReportTest {
	private static final String OTHER_SOURCE = "Other Source";

	protected void assertLeakListInfoIsCorrect(LeakDataBuilder leakDataBuilder, List<LeakListInfoEntity> leaksList) {
		Log.method("assertLeakListInfoIsCorrect", leakDataBuilder, LogHelper.collectionToString(leaksList, "leaksList"));
		assertTrue(String.format("LeaksList is EMPTY. Leak list=[%s]", LogHelper.collectionToString(leaksList, "leaksList")), leaksList!=null && leaksList.size()>0);
		leaksList.stream()
			.forEach(el -> {
				assertTrue(String.format("ID length NOT > 0. Leak=[%s], Id=[%s], Id len=[%d]", el, el.getId(), el.getId().length()), el.getId().length()>0);
				assertTrue(String.format("Time length NOT > 10. Leak=[%s], Time=[%s], Time len=[%d]", el, el.getTime(), el.getTime().length()), el.getTime().length()>10);
				String expectedAddress = String.format("Address: %s %s", leakDataBuilder.getStreetNumber(), leakDataBuilder.getStreetName());
				assertTrue(String.format("Address is incorrect. Expected=[%s], Actual=[%s]", expectedAddress, el.getAddress()),
						el.getAddress().equals(expectedAddress));
			});
	}

	protected void assertOtherSourceListInfoIsCorrect(List<OtherSourceListInfoEntity> otherSourcesList) {
		Log.method("assertOtherSourceListInfoIsCorrect", otherSourcesList);
		assertTrue(String.format("OtherSourcesList is EMPTY. OtherSourcesList=[%s]", LogHelper.collectionToString(otherSourcesList, "otherSourcesList")),
				otherSourcesList!=null && otherSourcesList.size()>0);
		otherSourcesList.stream()
			.forEach(el -> {
				assertTrue(String.format("Source value is NOT correct. Expected=[%s], Actual=[%s]", OTHER_SOURCE, el.getSource().trim()), el.getSource().trim().equals(OTHER_SOURCE));
				assertTrue(String.format("Time length NOT > 10. OtherSource=[%s], Time=[%s], Time len=[%d]", el, el.getTime(), el.getTime().length()), el.getTime().length()>10);
			});
	}
}

package androidapp.regression.source;

import static org.junit.Assert.assertTrue;

import java.util.List;
import androidapp.entities.source.LeakListInfoEntity;
import androidapp.entities.source.OtherSourceListInfoEntity;
import androidapp.screens.source.AndroidAddOtherSourceFormDialog;
import androidapp.screens.source.AndroidAddSourceDialog;
import androidapp.screens.source.AndroidAddedSourceListDialog;
import common.source.Log;
import common.source.LogHelper;
import surveyor.dataprovider.DataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator.LeakDataBuilder;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;

public class AndroidLeakScreenTestBase extends BaseReportTest {
	private static final String OTHER_SOURCE = "Other Source";

	protected void addNewOtherSource(AndroidAddSourceDialog addSourceDialog, AndroidAddOtherSourceFormDialog addOtherSourceFormDialog,
			AndroidAddedSourceListDialog addedSourcesListDialog) throws Exception {
		Log.method("addNewOtherSource", addSourceDialog, addOtherSourceFormDialog, addedSourcesListDialog);
		addNewOtherSource(addSourceDialog, addOtherSourceFormDialog, addedSourcesListDialog, LeakSourceType.Other_Natural_Source, DataGenerator.getRandomText(20, 100));
	}

	protected void addNewOtherSource(AndroidAddSourceDialog addSourceDialog, AndroidAddOtherSourceFormDialog addOtherSourceFormDialog,
			AndroidAddedSourceListDialog addedSourcesListDialog, LeakSourceType leakSourceType, String additionalNotes) throws Exception {
		Log.method("addNewOtherSource", addSourceDialog, addOtherSourceFormDialog, addedSourcesListDialog, leakSourceType, additionalNotes);
		addSourceDialog.waitForScreenLoad();
		addSourceDialog.clickOnAddOtherSources();
		addOtherSourceFormDialog.waitForScreenLoad();
		addOtherSourceFormDialog.clickOnUseCurrentLocation();
		addOtherSourceFormDialog.selectLeakSource(leakSourceType);
		addOtherSourceFormDialog.enterAdditionalNotes(additionalNotes);
		addOtherSourceFormDialog.clickOnOKForNewItem();
		initializeAndroidAddedLeakListDialog();
		addedSourcesListDialog.waitForScreenAndDataLoad();
		List<OtherSourceListInfoEntity> otherSourcesList = addedSourcesListDialog.getOtherSourcesList();
		assertTrue("Sources list length should be greater than 0", otherSourcesList!=null && otherSourcesList.size()>0);
		assertOtherSourceListInfoIsCorrect(otherSourcesList);
	}

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

	/* Implementation to be provided by derived class */
	protected void initializeInvestigateReportScreen() {
	}

	/* Implementation to be provided by derived class */
	protected void initializeInvestigationScreen() {
	}

	/* Implementation to be provided by derived class */
	protected void initializeInvestigateMapScreen() {
	}

	/* Implementation to be provided by derived class */
	protected void initializeAddSourceDialog() {
	}

	/* Implementation to be provided by derived class */
	protected void initializeMarkerTypeDialog() {
	}

	/* Implementation to be provided by derived class */
	protected void initializeAddLeakSourceFormDialog() {
	}

	/* Implementation to be provided by derived class */
	protected void initializeAndroidAddedLeakListDialog() {
	}

	/* Implementation to be provided by derived class */
	protected void initializeAddOtherSourceFormDialog() {
	}
}
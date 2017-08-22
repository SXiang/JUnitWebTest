package androidapp.regression.source;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import androidapp.data.source.InvestigationReportDataVerifier.BoxType;
import androidapp.entities.source.InvestigationMarkerEntity;
import androidapp.entities.source.LeakListInfoEntity;
import androidapp.entities.source.OtherSourceListInfoEntity;
import androidapp.screens.source.AndroidAddLeakSourceFormDialog;
import androidapp.screens.source.AndroidAddOtherSourceFormDialog;
import androidapp.screens.source.AndroidAddSourceDialog;
import androidapp.screens.source.AndroidAddedSourceListDialog;
import androidapp.screens.source.AndroidConfirmationDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import common.source.Log;
import common.source.LogHelper;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.DataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator.LeakDataBuilder;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;
import surveyor.scommon.source.SurveyorConstants;

public class AndroidLeakScreenTestBase extends BaseReportTest {
	private static final int SOURCE_ITEMS_SHOWN_IN_ONE_PAGE = 8;
	private static final String OTHER_SOURCE = "Other Source";

	protected LeakDataBuilder addNewLeak(AndroidAddSourceDialog addSourceDialog, AndroidAddLeakSourceFormDialog addLeakSourceFormDialog, AndroidAddedSourceListDialog addedSourcesListDialog) throws Exception {
		addSourceDialog.clickOnAddLeak();
		addLeakSourceFormDialog.waitForScreenLoad();
		LeakDataBuilder leakDataBuilder = LeakDataGenerator.newBuilder().generateDefaultValues();
		Map<String, Object> leakMap = leakDataBuilder.toMap();
		addLeakSourceFormDialog.fillForm(leakMap);
		addedSourcesListDialog.waitForScreenLoad();
		return leakDataBuilder;
	}

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
		addOtherSourceFormDialog.clickOnOK();
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

	protected void assertLeakListInfoIsCorrect(LeakDataBuilder leakDataBuilder, List<LeakListInfoEntity> leaksList, Integer itemIndex) {
		Log.method("assertLeakListInfoIsCorrect", leakDataBuilder, LogHelper.collectionToString(leaksList, "leaksList"), itemIndex);
		assertTrue(String.format("LeaksList is EMPTY. Leak list=[%s]", LogHelper.collectionToString(leaksList, "leaksList")), leaksList!=null && leaksList.size()>0);
		if (leaksList.size() > itemIndex) {
			LeakListInfoEntity el = leaksList.get(itemIndex);
			assertTrue(String.format("ID length NOT > 0. Leak=[%s], Id=[%s], Id len=[%d]", el, el.getId(), el.getId().length()), el.getId().length()>0);
			assertTrue(String.format("Time length NOT > 10. Leak=[%s], Time=[%s], Time len=[%d]", el, el.getTime(), el.getTime().length()), el.getTime().length()>10);
			String expectedAddress = String.format("Address: %s %s", leakDataBuilder.getStreetNumber(), leakDataBuilder.getStreetName());
			assertTrue(String.format("Address is incorrect. Expected=[%s], Actual=[%s]", expectedAddress, el.getAddress()),
					el.getAddress().equals(expectedAddress));
		} else {
			throw new IllegalArgumentException(String.format("Invalid itemIndex specified. itemIndex=[%d]. List size=%d", itemIndex, leaksList.size()));
		}
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

	protected Integer getMarkerNumber(List<InvestigationMarkerEntity> investigationMarkers, int idx) {
		InvestigationMarkerEntity markerEntity = investigationMarkers.get(idx);
		String[] split = markerEntity.getMarkerNumber().split("-");
		String markerNum = split[split.length-1].trim();
		return Integer.valueOf(markerNum);
	}

	protected boolean hasMarkerOfStatus(List<InvestigationMarkerEntity> investigationMarkers, final String markerStatus) {
		return investigationMarkers.stream()
			.filter(m -> m.getInvestigationStatus().equals(markerStatus))
			.count() > 0;
	}

	protected Integer checkInvestigateNewMarkerAsComplete(AndroidInvestigateReportScreen investigateReportScreen, AndroidInvestigateMapScreen investigateMapScreen,
			AndroidAddSourceDialog addSourceDialog, AndroidAddOtherSourceFormDialog addOtherSourceFormDialog,AndroidAddedSourceListDialog addedSourcesListDialog,
			AndroidConfirmationDialog confirmationDialog, final List<String> markerStatuses, List<List<InvestigationMarkerEntity>> listOfListMarkers, String reportTitle)
			throws Exception {
		Log.method("checkInvestigateNewMarkerAsComplete", investigateReportScreen, investigateMapScreen, addSourceDialog, addOtherSourceFormDialog,
				addedSourcesListDialog, confirmationDialog, markerStatuses, listOfListMarkers, reportTitle);
		return checkInvestigateNewMarkerAsComplete(investigateReportScreen, investigateMapScreen, addSourceDialog, addOtherSourceFormDialog, null /*addLeakSourceFormDialog*/,
				addedSourcesListDialog, confirmationDialog, markerStatuses, listOfListMarkers, reportTitle);
	}

	protected Integer checkInvestigateNewMarkerAsComplete(AndroidInvestigateReportScreen investigateReportScreen, AndroidInvestigateMapScreen investigateMapScreen,
			AndroidAddSourceDialog addSourceDialog, AndroidAddLeakSourceFormDialog addLeakSourceFormDialog, AndroidAddedSourceListDialog addedSourcesListDialog,
			AndroidConfirmationDialog confirmationDialog, final List<String> markerStatuses, List<List<InvestigationMarkerEntity>> listOfListMarkers, String reportTitle)
			throws Exception {
		Log.method("checkInvestigateNewMarkerAsComplete", investigateReportScreen, investigateMapScreen, addSourceDialog, addLeakSourceFormDialog,
				addedSourcesListDialog, confirmationDialog, markerStatuses, listOfListMarkers, reportTitle);
		return checkInvestigateNewMarkerAsComplete(investigateReportScreen, investigateMapScreen, addSourceDialog, null /*addOtherSourceFormDialog*/, addLeakSourceFormDialog,
				addedSourcesListDialog, confirmationDialog, markerStatuses, listOfListMarkers, reportTitle);
	}

	protected void investigateNotInvestigatedMarkerToNoGasFound(AndroidInvestigateReportScreen investigateReportScreen, AndroidInvestigateMapScreen investigateMapScreen,
			AndroidConfirmationDialog confirmationDialog, final String noGasFound, final String notInvestigated) throws Exception {
		Log.method("investigateNotInvestigatedMarkerToNoGasFound", investigateReportScreen, investigateMapScreen, confirmationDialog, noGasFound, notInvestigated);
		List<String> markerStatuses = Arrays.asList(notInvestigated);
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(markerStatuses);
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnMarkAsComplete();
			confirmationDialog.waitForScreenLoad();
			confirmationDialog.clickOnCancel();
			investigateReportScreen.waitForScreenLoad();
			String actualMarkerStatus = investigateReportScreen.getInvestigationMarkers().get(idx-1).getInvestigationStatus();
			Log.info(String.format("Expected marker status=[%s]. Found marker status=[%s]", noGasFound, actualMarkerStatus));
			assertTrue(String.format("Incorrect marker status found. Expected=[%s]. Actual=[%s]", noGasFound, actualMarkerStatus),
					actualMarkerStatus.equals(noGasFound));
			return true;
		});
	}

	protected void investigateFirstNonInvestigatedMarkerAsLeakAndMarkAsComplete(AndroidInvestigateReportScreen investigateReportScreen, AndroidInvestigateMapScreen investigateMapScreen,
			AndroidAddSourceDialog addSourceDialog, AndroidAddLeakSourceFormDialog addLeakSourceFormDialog, AndroidAddedSourceListDialog addedSourcesListDialog,
			AndroidConfirmationDialog confirmationDialog) throws Exception {
		Log.method("investigateFirstNonInvestigatedMarkerAsLeakAndMarkAsComplete", investigateReportScreen, investigateMapScreen,
				addSourceDialog, addLeakSourceFormDialog, addedSourcesListDialog, confirmationDialog);
		String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		String[] markerStatusNotInv = {notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatusNotInv));

		// Add new leak. Mark as Complete.
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			addSourceDialog.clickOnAddLeak();
			addLeakSourceFormDialog.waitForScreenLoad();
			LeakDataBuilder leakDataBuilder = LeakDataGenerator.newBuilder().generateDefaultValues();
			addLeakSourceFormDialog.fillForm(leakDataBuilder.toMap());
			addedSourcesListDialog.waitForScreenLoad();
			assertLeakListInfoIsCorrect(leakDataBuilder, addedSourcesListDialog.getLeaksList());
			addedSourcesListDialog.clickOnCancel();
			investigateMapScreen.clickOnMarkAsComplete();
			investigateReportScreen.waitForScreenLoad();
			return true;
		});
	}

	protected void investigateFirstNonInvestigatedMarkerAsOtherSourceAndMarkAsComplete(AndroidInvestigateReportScreen investigateReportScreen, AndroidInvestigateMapScreen investigateMapScreen,
			AndroidAddSourceDialog addSourceDialog, AndroidAddOtherSourceFormDialog addOtherSourceFormDialog, AndroidAddedSourceListDialog addedSourcesListDialog,
			AndroidConfirmationDialog confirmationDialog) throws Exception {
		Log.method("investigateFirstNonInvestigatedMarkerAsOtherSourceAndMarkAsComplete", investigateReportScreen, investigateMapScreen, addSourceDialog,
				addOtherSourceFormDialog, addedSourcesListDialog, confirmationDialog);
		String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		String[] markerStatusNotInv = {notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatusNotInv));

		// Add new other source. Mark as Complete.
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			addSourceDialog.clickOnAddOtherSources();
			addOtherSourceFormDialog.waitForScreenLoad();
			addOtherSourceFormDialog.clickOnUseCurrentLocation();
			addOtherSourceFormDialog.selectLeakSource(LeakSourceType.Other_Natural_Source);
			addOtherSourceFormDialog.enterAdditionalNotes(DataGenerator.getRandomText(20, 120));
			addOtherSourceFormDialog.clickOnOK();
			initializeAndroidAddedLeakListDialog();
			addedSourcesListDialog.waitForScreenAndDataLoad();
			addedSourcesListDialog.clickOnCancel();
			investigateMapScreen.clickOnMarkAsComplete();
			investigateReportScreen.waitForScreenLoad();
			return true;
		});
	}

	private Integer checkInvestigateNewMarkerAsComplete(AndroidInvestigateReportScreen investigateReportScreen, AndroidInvestigateMapScreen investigateMapScreen,
			AndroidAddSourceDialog addSourceDialog, AndroidAddOtherSourceFormDialog addOtherSourceFormDialog, AndroidAddLeakSourceFormDialog addLeakSourceFormDialog,
			AndroidAddedSourceListDialog addedSourcesListDialog, AndroidConfirmationDialog confirmationDialog, final List<String> markerStatuses,
			List<List<InvestigationMarkerEntity>> listOfListMarkers, String reportTitle) throws Exception {
		Log.info(String.format("Checking for presence of existing marker with status -> [%s] ...", LogHelper.collectionToString(markerStatuses, "markerStatuses")));
		List<InvestigationMarkerEntity> investigationMarkers = listOfListMarkers.get(0);
		Integer idx = -1;
		boolean foundMarker = false;
		for (int i = 0; i < investigationMarkers.size(); i++) {
			InvestigationMarkerEntity markerEntity = investigationMarkers.get(i);
			String[] split = markerEntity.getMarkerNumber().split("-");
			String lisaNum = split[split.length-1].trim();
			if (markerStatuses.contains(markerEntity.getInvestigationStatus())) {
				idx++;
				if (!invReportDataVerifier.doesMarkerWithBoxNumberHaveSourceItemsSpanningMultiplePages(Report.getReport(reportTitle).getId(),
						SurveyorConstants.SQAPICDR, markerStatuses, BoxType.Indication, Integer.valueOf(lisaNum), SOURCE_ITEMS_SHOWN_IN_ONE_PAGE)) {
					Log.info(String.format("Found matching marker -> [%s]", markerEntity.toString()));
					foundMarker = true;
					break;
				}
			}
		}

		// If no existing marker of desired status, create new.
		if (!foundMarker) {
			final String foundGasLeak = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Gas_Leak);
			final String foundOtherSource = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Other_Source);
			final String noGasFound = Resources.getResource(ResourceKeys.InvestigationStatusTypes_No_Gas_Found);

			Log.info(String.format("No existing marker found with status - [%s]. Investigating and marking as Complete.",
					LogHelper.collectionToString(markerStatuses, "markerStatuses")));

			if (markerStatuses.contains(foundGasLeak)) {
				investigateFirstNonInvestigatedMarkerAsLeakAndMarkAsComplete(investigateReportScreen, investigateMapScreen, addSourceDialog,
						addLeakSourceFormDialog, addedSourcesListDialog, confirmationDialog);
			} else if (markerStatuses.contains(foundOtherSource)) {
				investigateFirstNonInvestigatedMarkerAsOtherSourceAndMarkAsComplete(investigateReportScreen, investigateMapScreen,
						addSourceDialog, addOtherSourceFormDialog, addedSourcesListDialog, confirmationDialog);
			} else if (markerStatuses.contains(noGasFound)) {
				final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
				investigateNotInvestigatedMarkerToNoGasFound(investigateReportScreen, investigateMapScreen, confirmationDialog, noGasFound, notInvestigated);
			}

			idx++;
		}

		return idx;
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
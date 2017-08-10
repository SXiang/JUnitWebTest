package androidapp.data.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.BoxTypes;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ReportStatusType;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.StoredProcLisaInvestigationLeaksByPeakId;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;

public class InvestigationReportDataVerifier {

	private static final String REPORT_STATUS_COMPLETE = "Complete";

	public enum BoxType {
		Indication ("Indication"),
		Gap ("Gap");

		private final String name;

		BoxType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	private InvestigationReportDataVerifier() {	}

	public static InvestigationReportDataVerifier newVerifier() {
		return new InvestigationReportDataVerifier();
	}

	public boolean doesMarkerWithBoxNumberHaveSourceItemsSpanningMultiplePages(String reportId, String assignedUsername, List<String> markerStatuses, BoxType boxType, Integer boxNumber, Integer sourceItemsInOnePage) {
		Log.method("doesMarkerWithBoxNumberHaveSourceItemsSpanningMultiplePages", reportId, assignedUsername, markerStatuses, boxType, boxNumber, sourceItemsInOnePage);
		final Integer boxTypeId = BoxTypes.getBoxTypeByName(boxType.toString()).getId();
		List<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication.getLisaInvestigation(reportId);
		if (lisaInvestigationfromSP != null && lisaInvestigationfromSP.size()>0) {
			StoredProcLisaInvestigationShowIndication showIndication = lisaInvestigationfromSP.stream()
				.filter(r -> markerStatuses.contains(r.getInvestigationStatus()) && r.getAssignedUserName().equals(assignedUsername) && r.getBoxTypeId() == boxTypeId && r.getBoxNumber() == boxNumber)
				.findFirst().orElse(null);
			if (showIndication != null) {
				Log.info(String.format("Found matching ShowIndication item. Item -> [%s]", showIndication.toString()));
				List<StoredProcLisaInvestigationLeaksByPeakId> leaksByPeakId = StoredProcLisaInvestigationLeaksByPeakId.getLisaInvestigationLeaksByPeakId(reportId, showIndication.getBoxId());
				int itemsFound = 0;
				if (leaksByPeakId != null) {
					itemsFound = leaksByPeakId.size();
				}

				Log.info(String.format("Found %d source items", itemsFound));
				return (itemsFound>sourceItemsInOnePage);
			}
		}

		return false;
	}

	public Report findReportOfMatchingPrefixWithNotInvestigatedLisaMarker(String[] prefixes, String assignedUsername) {
		Log.method("findReportOfPrefixWithNotInvestigatedLisaMarker", Arrays.toString(prefixes), assignedUsername);
		return findReportOfMatchingPrefixWithNotInvestigatedMarkerOfType(prefixes, assignedUsername, BoxType.Indication);
	}

	public Report findReportOfMatchingPrefixWithNotInvestigatedGapMarker(String[] prefixes, String assignedUsername) {
		Log.method("findReportOfPrefixWithNotInvestigatedGapMarker", Arrays.toString(prefixes), assignedUsername);
		return findReportOfMatchingPrefixWithNotInvestigatedMarkerOfType(prefixes, assignedUsername, BoxType.Gap);
	}

	public Report findReportOfMatchingPrefixWithCompleteOrInProgressLisaMarker(String[] prefixes, String assignedUsername) {
		Log.method("findReportOfMatchingPrefixWithCompleteOrInProgressLisaMarker", Arrays.toString(prefixes), assignedUsername);
		return findReportOfMatchingPrefixWithCompleteOrInProgressMarkerOfType(prefixes, assignedUsername, BoxType.Indication);
	}

	public Report findReportOfMatchingPrefixWithCompleteOrInProgressGapMarker(String[] prefixes, String assignedUsername) {
		Log.method("findReportOfMatchingPrefixWithCompleteOrInProgressGapMarker", Arrays.toString(prefixes), assignedUsername);
		return findReportOfMatchingPrefixWithCompleteOrInProgressMarkerOfType(prefixes, assignedUsername, BoxType.Gap);
	}

	public boolean hasNotInvestigatedLisaMarker(String tcId, String assignedUsername) {
		Log.method("hasNotInvestigatedLisaMarker", tcId, assignedUsername);
		return hasNotInvestigatedMarker(tcId, assignedUsername, BoxType.Indication);
	}

	public boolean hasNotInvestigatedGapMarker(String tcId, String assignedUsername) {
		Log.method("hasNotInvestigatedGapMarker", tcId, assignedUsername);
		return hasNotInvestigatedMarker(tcId, assignedUsername, BoxType.Gap);
	}

	public boolean hasCompleteOrInProgressLisaMarker(String tcId, String assignedUsername) {
		Log.method("hasCompleteOrInProgressLisaMarker", tcId, assignedUsername);
		return hasCompleteOrInProgressMarker(tcId, assignedUsername, BoxType.Indication);
	}

	public boolean hasCompleteOrInProgressGapMarker(String tcId, String assignedUsername) {
		Log.method("hasCompleteOrInProgressGapMarker", tcId, assignedUsername);
		return hasCompleteOrInProgressMarker(tcId, assignedUsername, BoxType.Gap);
	}

	private boolean hasNotInvestigatedMarker(String tcId, String assignedUsername, BoxType boxType) {
		Log.method("hasNotInvestigatedMarker", tcId, assignedUsername, boxType);
		return getNotInvestigatedMarkerReport(tcId, assignedUsername, boxType) != null;
	}

	private boolean hasCompleteOrInProgressMarker(String tcId, String assignedUsername, BoxType boxType) {
		Log.method("hasCompleteOrInProgressMarker", tcId, assignedUsername, boxType);
		return getCompleteOrInProgressMarker(tcId, assignedUsername, boxType) != null;
	}

	private Report findReportOfMatchingPrefixWithNotInvestigatedMarkerOfType(String[] prefixes, String assignedUsername, BoxType boxType) {
		return Arrays.asList(prefixes).stream()
			.map(p -> getNotInvestigatedMarkerReport(p, assignedUsername, boxType))
			.filter(r -> r != null)
			.findFirst().orElse(null);
	}

	private Report findReportOfMatchingPrefixWithCompleteOrInProgressMarkerOfType(String[] prefixes, String assignedUsername, BoxType boxType) {
		return Arrays.asList(prefixes).stream()
			.map(p -> getCompleteOrInProgressMarker(p, assignedUsername, boxType))
			.filter(r -> r != null)
			.findFirst().orElse(null);
	}

	private Report getNotInvestigatedMarkerReport(String tcId, String assignedUsername, BoxType boxType) {
		Log.method("getNotInvestigatedMarkerReport", tcId, assignedUsername, boxType);
		final Integer boxTypeId = BoxTypes.getBoxTypeByName(boxType.toString()).getId();
		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		Report report = new Report().getTitleLike(tcId);
		String reportCompleteStatusTypeId = ReportStatusType.getReportStatusType(REPORT_STATUS_COMPLETE).getId().toString();
		if (report != null && report.getReportStatusTypeId().equals(reportCompleteStatusTypeId)) {
			String reportId = report.getId();
			List<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication.getLisaInvestigation(reportId);
			if (lisaInvestigationfromSP != null && lisaInvestigationfromSP.size()>0) {
				boolean match = lisaInvestigationfromSP.stream()
					.anyMatch(r -> r.getInvestigationStatus().equals(notInvestigated) && r.getAssignedUserName().equals(assignedUsername) && r.getBoxTypeId() == boxTypeId);
				if (match) {
					Log.info(String.format("Found match. Returning report - [%s]", report.toString()));
					return report;
				}
			}
		}

		Log.info("Found NO match. Returning NULL.");
		return null;
	}

	private Report getCompleteOrInProgressMarker(String tcId, String assignedUsername, BoxType boxType) {
		Log.method("getCompleteOrInProgressMarker", tcId, assignedUsername, boxType);
		final String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);
		final Integer boxTypeId = BoxTypes.getBoxTypeByName(boxType.toString()).getId();
		Report report = new Report().getTitleLike(tcId);
		String reportCompleteStatusTypeId = ReportStatusType.getReportStatusType(REPORT_STATUS_COMPLETE).getId().toString();
		if (report != null && report.getReportStatusTypeId().equals(reportCompleteStatusTypeId)) {
			String reportId = report.getId();
			List<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication.getLisaInvestigation(reportId);
			if (lisaInvestigationfromSP != null && lisaInvestigationfromSP.size()>0) {
				boolean match = lisaInvestigationfromSP.stream()
					.anyMatch(r -> (r.getInvestigationStatus().equals(inProgress))
							&& r.getAssignedUserName().equals(assignedUsername) && r.getBoxTypeId() == boxTypeId);
				if (match) {
					Log.info(String.format("Found match. Returning report - [%s]", report.toString()));
					return report;
				}
			}
		}

		Log.info("Found NO match. Returning NULL.");
		return null;
	}

	public static void main(String[] args) {
		// Initialize TestSetup to instantiate the TestContext and DB connection parameters.
		TestSetup testSetup = new TestSetup(false /* skip initialization */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initializeDBProperties();
		TestContext.INSTANCE.setTestSetup(testSetup);

		// Report with source items NOT spanning multiple pages.
		InvestigationReportDataVerifier reportDataVerifier = new InvestigationReportDataVerifier();
		String reportId = "4c2debf6-7faa-3e02-d04e-39e0daba77ba";
		List<String> markerStatuses = new ArrayList<String>();
		markerStatuses.add("In Progress");
		Integer boxNumber = 1;
		boolean itemsSpanMultiplePages = reportDataVerifier.doesMarkerWithBoxNumberHaveSourceItemsSpanningMultiplePages(reportId ,
				"AutomationAdmin", markerStatuses, BoxType.Indication, boxNumber, 8);
		Log.info(String.format("itemsSpanMultiplePages = %b", itemsSpanMultiplePages));
		Assert.assertTrue(itemsSpanMultiplePages == false);

		// Report with source items spanning multiple pages.
		reportDataVerifier = new InvestigationReportDataVerifier();
		reportId = "4994B6AD-4339-BA77-3218-39E0B1E3BE6E";
		boxNumber = 2;
		itemsSpanMultiplePages = reportDataVerifier.doesMarkerWithBoxNumberHaveSourceItemsSpanningMultiplePages(reportId ,
				"sqapicdr@picarro.com", markerStatuses, BoxType.Indication, boxNumber, 8);
		Log.info(String.format("itemsSpanMultiplePages = %b", itemsSpanMultiplePages));
		Assert.assertTrue(itemsSpanMultiplePages == true);

	}
}
package androidapp.data.source;

import java.util.List;

import common.source.Log;
import surveyor.dataaccess.source.BoxTypes;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;

public class InvestigationReportDataVerifier {

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
		final Integer boxTypeId = BoxTypes.getBoxTypeByName(boxType.toString()).getId();
		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		Report report = new Report().getTitleLike(tcId);
		if (report != null) {
			String reportId = report.getId();
			List<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication.getLisaInvestigation(reportId);
			if (lisaInvestigationfromSP != null && lisaInvestigationfromSP.size()>0) {
				boolean match = lisaInvestigationfromSP.stream()
					.anyMatch(r -> r.getInvestigationStatus().equals(notInvestigated) && r.getAssignedUserName().equals(assignedUsername) && r.getBoxTypeId() == boxTypeId);
				Log.info(String.format("Returning - [%b]", match));
				return match;
			}
		}

		Log.info("Returning FALSE");
		return false;
	}

	private boolean hasCompleteOrInProgressMarker(String tcId, String assignedUsername, BoxType boxType) {
		Log.method("hasCompleteOrInProgressMarker", tcId, assignedUsername, boxType);
		final String complete = Resources.getResource(ResourceKeys.Constant_Complete);
		final String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);
		final Integer boxTypeId = BoxTypes.getBoxTypeByName(boxType.toString()).getId();
		Report report = new Report().getTitleLike(tcId);
		if (report != null) {
			String reportId = report.getId();
			List<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication.getLisaInvestigation(reportId);
			if (lisaInvestigationfromSP != null && lisaInvestigationfromSP.size()>0) {
				boolean match = lisaInvestigationfromSP.stream()
					.anyMatch(r -> (r.getInvestigationStatus().equals(complete) || r.getInvestigationStatus().equals(inProgress))
							&& r.getAssignedUserName().equals(assignedUsername) && r.getBoxTypeId() == boxTypeId);
				Log.info(String.format("Returning - [%b]", match));
				return match;
			}
		}

		Log.info("Returning FALSE");
		return false;
	}
}
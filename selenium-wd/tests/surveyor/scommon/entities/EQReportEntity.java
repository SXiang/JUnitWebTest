/**
 *
 */
package surveyor.scommon.entities;

import java.util.List;

import surveyor.scommon.source.Coordinates;

/**
 * @author zlu
 *
 */
public class EQReportEntity extends ReportCommonEntity {
	protected String eqLocationParameter;
	protected List<List<Coordinates>> lineSegments;
	public String getEQLocationParameter() {
		return eqLocationParameter;
	}

	public List<List<Coordinates>> getLineSegments() {
		return lineSegments;
	}

	public void setEQLocationParameter(String eqLocationParameter) {
		this.eqLocationParameter = eqLocationParameter;
	}
	
	public void setLineSegments(List<List<Coordinates>> lineSegments) {
		this.lineSegments = lineSegments;
	}

	public EQReportEntity() {
		super();
	}

	public EQReportEntity(String rptTitle, String customer, String timeZone, String eqLocationParameter, List<List<Coordinates>> lineSegments){
		this.setRptTitle(rptTitle);
		this.setCustomer(customer);
		this.timeZone = timeZone;

		this.eqLocationParameter = eqLocationParameter;
		this.lineSegments = lineSegments;
	}
	
	public enum EmissionsQuantificationTableColumns {
		SegmentID ("Segment ID", 0),
		SegmentRank ("Segment Rank", 1),
		EmissionRate ("Emission Rate (SCFH)", 2),
		EmissionRange ("Emission range(confidence)", 3),
		EmissionLength ("Segment Length (ft)", 4),
		EmissionsFactor ("Emissions Factor (SCFH/ft)", 5),
		EstimatedLeaks ("Estimated # of leaks", 6),
		Leaks ("#Leaks/ft", 7),
		EmissionRateLeak ("Emission Rate/Leak", 8);

		private final String name;
		private final Integer colIndex;

		EmissionsQuantificationTableColumns(String nm) {
			this(nm, -1);
		}

		EmissionsQuantificationTableColumns(String nm, Integer colIdx) {
			name = nm;
			colIndex = colIdx;
		}

		public Integer getIndex() {
			return colIndex;
		}

		public String toString() {
			return this.name;
		}
	}
}
/**
 *
 */
package surveyor.scommon.entities;

import java.util.List;

import surveyor.scommon.source.Coordinates;

public class FacilityEQReportEntity extends ReportCommonEntity {
	protected String facilityEQLocationParameter;
	protected List<Coordinates> shapeCoordinates;
	protected boolean showLisas;
	public String getFacilityEQLocationParameter() {
		return facilityEQLocationParameter;
	}

	public List<Coordinates> getShapeCoordinates() {
		return shapeCoordinates;
	}

	
	public boolean isShowLisas() {
		return showLisas;
	}

	public void setFacilityEQLocationParameter(String facilityEQLocationParameter) {
		this.facilityEQLocationParameter = facilityEQLocationParameter;
	}
	
	public void setShapeCoordinates(List<Coordinates> shapeCoordinates) {
		this.shapeCoordinates = shapeCoordinates;
	}

	
	public void setShowLisas(boolean showLisas) {
		this.showLisas = showLisas;
	}

	public FacilityEQReportEntity() {
		super();
	}

	public FacilityEQReportEntity(String rptTitle, String customer, String timeZone, String facilityEQLocationParameter, List<Coordinates> shapeCoordinates, boolean showLisas){
		this.setRptTitle(rptTitle);
		this.setCustomer(customer);
		this.timeZone = timeZone;

		this.facilityEQLocationParameter = facilityEQLocationParameter;
		this.shapeCoordinates = shapeCoordinates;
		this.showLisas = showLisas;
	}
}
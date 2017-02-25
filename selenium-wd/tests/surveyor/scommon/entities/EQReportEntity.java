/**
 *
 */
package surveyor.scommon.entities;

import java.util.List;

import com.sun.javafx.scene.paint.GradientUtils.Point;

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
	
	public EQReportEntity(String rptTitle, String customer, String timeZone, String eqLocationParameter, List<List<Coordinates>> lineSegments){
		this.setRptTitle(rptTitle);
		this.setCustomer(customer);
		this.timeZone = timeZone;

		this.eqLocationParameter = eqLocationParameter;
		this.lineSegments = lineSegments;
	}
}
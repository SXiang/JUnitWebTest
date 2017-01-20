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
public class EQReportEntity extends BaseReportEntity {

	protected List<List <Coordinates>> listOfCords;


	public EQReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, List<String> tagList, List<List <Coordinates>> listOfCords) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit,tagList);
		this.listOfCords = listOfCords;
	}

	public EQReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit,String startDate, String endDate, List<String> tagList, List<List <Coordinates>> listOfCords) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList, startDate, endDate);
		this.listOfCords = listOfCords;
	}

	public EQReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit,String startDate, String endDate, boolean geoFilter, List<String> tagList, List<List <Coordinates>> listOfCords) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList, startDate, endDate, geoFilter);
		this.listOfCords = listOfCords;
	}

	public EQReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit,String userName, String startDate, String endDate, boolean geoFilterOn, List<String> tagList, List<List <Coordinates>> listOfCords) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit,  startDate,endDate, userName, geoFilterOn,tagList);
		this.listOfCords = listOfCords;
	}

	public  List<List<Coordinates>> getListOfCords() {
		return listOfCords;
	}

	public void setListOfCords(List<List<Coordinates>> listOfCords) {
		this.listOfCords = listOfCords;
	}
}
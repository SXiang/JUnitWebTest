/**
 * 
 */
package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zlu
 *
 */
public class ReportsEQ extends Reports {

	protected List<List <Coordinates>> listOfCords;


	public ReportsEQ(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, List<String> tagList, List<List <Coordinates>> listOfCords) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit,tagList);
		this.listOfCords = listOfCords;
		
	}
	
	public ReportsEQ(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit,String startDate, String endDate, List<String> tagList, List<List <Coordinates>> listOfCords) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList, startDate, endDate);
		this.listOfCords = listOfCords;		
	}
	
	public ReportsEQ(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit,String startDate, String endDate, boolean geoFilter, List<String> tagList, List<List <Coordinates>> listOfCords) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList, startDate, endDate, geoFilter);
		this.listOfCords = listOfCords;		
	}

	public ReportsEQ(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit,String userName, String startDate, String endDate, boolean geoFilterOn, List<String> tagList, List<List <Coordinates>> listOfCords) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit,  startDate,endDate, userName, geoFilterOn,tagList);
		this.listOfCords = listOfCords;		
	}
	
	public  List<List<Coordinates>> getListOfCords() {
		return listOfCords;
	}



	public void setListOfCords(List<List<Coordinates>> listOfCords) {
		this.listOfCords = listOfCords;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}

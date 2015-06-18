/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;
import java.util.Map;

/**
 * @author zlu
 *
 */
public class Reports {
	protected String rptTitle;
	protected String strCreatedBy;
	protected String customer;
	protected String timeZone;
	protected String exclusionRadius;
	
	protected List<String> listBoundary;
	protected String imageMapHeight;
	protected String imageMapWidth;
	protected String NELat;
	protected String NELong;
	protected String SWLat;
	protected String SWLong;
	
	protected String surveyorUnit;
	protected String tag;
	
	protected List<Map<String, String>> viewList;
	protected List<Map<String, String>> tablesList;
	
	protected List<String> tagList;
	
	/**
	 * 
	 */
	public Reports(String rptTitle, String strCreatedBy, String customer,
			String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit,
			String tag, List<Map<String, String>> viewList) {
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.customer = customer;
		this.timeZone = timeZone;
		this.exclusionRadius = exclusionRadius;

		this.listBoundary = listBoundary;
		this.imageMapHeight = listBoundary.get(0);
		this.imageMapWidth = listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong = listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
		
		this.tablesList = tablesList;
		this.surveyorUnit = surveyorUnit;
		this.tag = tag;

		this.viewList = viewList;
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer,
			String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit,
			List<String> tagList, List<Map<String, String>> viewList) {
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.customer = customer;
		this.timeZone = timeZone;
		this.exclusionRadius = exclusionRadius;

		this.listBoundary = listBoundary;
		this.imageMapHeight = listBoundary.get(0);
		this.imageMapWidth = listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong = listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
		
		this.tablesList = tablesList;
		this.surveyorUnit = surveyorUnit;
		this.tagList = tagList;

		this.viewList = viewList;
	}

	public String getRptTitle() {
		return this.rptTitle;
	}
	
	public String getCustomer() {
		return this.customer;
	}
	
	public String getTimeZone() {
		return this.timeZone;
	}
	
	public String getExclusionRadius() {
		return this.exclusionRadius;
	}
	
	public List<String> getListBoundary() {
		return this.listBoundary;
	}

	public String getImageMapHeight() {
		return this.imageMapHeight;
	}
	
	public String getImageMapWidth() {
		return this.imageMapWidth;
	}
	
	public String getNELat() {
		return this.NELat;
	}
	
	public String getNELong() {
		return this.NELong;
	}
	
	public String getSWLat() {
		return this.SWLat;
	}

	public String getSWLong() {
		return this.SWLong;
	}
	
	public String getSurveyorUnit() {
		return this.surveyorUnit;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public List<Map<String, String>> getViewList() {
		return this.viewList;
	}
	
	public List<Map<String, String>> getTablesList() {
		return this.tablesList;
	}
	
	public List<String> getTagList() {
		return this.tagList;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}

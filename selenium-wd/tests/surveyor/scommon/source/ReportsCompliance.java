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
public class ReportsCompliance extends Reports {
	//This is mapping the compliance report object
	//It is ongoing and more details will be added when needed
	
	//temporary leave some fields here and will be moved to base class later
	
	
	
	
	//private Map<String, String> viewHashMap;

	/**
	 * 
	 */
	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, 
			String surveyorUnit, String tag, List<Map<String, String>> viewList) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, 
				surveyorUnit, tag, viewList);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
/**
 * 
 */
package surveyor.scommon.source;

/**
 * @author zlu
 *
 */
public interface Reports {
	public static final String TITLENAMEBASE = "sqacrpt";
	public static final String TIMEZONE = "Pacific Standard Time";
	public static final String EXCLUSIONRADIUS = "3";
	public static final String REPORTMODES1 = "S1";
	
	public static final String CUSBOUNDARY = "Custom Boundary";
	public static final String IMGMAPHEIGHT = "5";
	public static final String IMGMAPWIDTH = "5";
	public static final String NELAT = "37.46368606459705";
	public static final String NELON = "-121.84524536132811";
	public static final String SWLAT = "37.32908214394206";
	public static final String SWLON = "-122.12385177612305";
	
//	public static final String SURVEYORUNIT = "Picarro - Default - pdFDDS2037a";
//	public static final String TAG = "dmcs1-sqa01";
	
	public static final String SURVEYORUNIT = "Picarro - Default - Surveyor Unit 1";
	public static final String TAG = "RB-S1-2014-08-09-1059";
	
	public static final String STARTDATE = "01/01/2014";
	public static final String ENDDATE = "08/14/2014";
	
	public static final int ACTIONTIMEOUT = 360; //in seconds
}
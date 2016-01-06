package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;
 
public class ReportGap extends BaseEntity {
	private static final String CACHE_KEY = "REPORTGAP.";
 
	private String id;
	private String reportId;
	private Object shape;
	private Boolean isGapInvestigated;
	private Integer row;
	private String gapNumber;
	private Boolean isLeakFound;
	private Integer column;
 
	public ReportGap() {
		super();
	}
 
	public String getId() {
		return id;
	}
 
	public void setId(String id) {
		this.id = id;
	}
 
	public String getReportId() {
		return reportId;
	}
 
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
 
	public Object getShape() {
		return shape;
	}
 
	public void setShape(Object shape) {
		this.shape = shape;
	}
 
	public Boolean getIsGapInvestigated() {
		return isGapInvestigated;
	}
 
	public void setIsGapInvestigated(Boolean isGapInvestigated) {
		this.isGapInvestigated = isGapInvestigated;
	}
 
	public Integer getRow() {
		return row;
	}
 
	public void setRow(Integer row) {
		this.row = row;
	}
 
	public String getGapNumber() {
		return gapNumber;
	}
 
	public void setGapNumber(String gapNumber) {
		this.gapNumber = gapNumber;
	}
 
	public Boolean getIsLeakFound() {
		return isLeakFound;
	}
 
	public void setIsLeakFound(Boolean isLeakFound) {
		this.isLeakFound = isLeakFound;
	}
 
	public Integer getColumn() {
		return column;
	}
 
	public void setColumn(Integer column) {
		this.column = column;
	}
 
	public static ReportGap getReportGap(String gapNumber) {
		ReportGap objReportGap = new ReportGap().get(gapNumber);
		return objReportGap;
	}
 
	public ReportGap get(String gapNumber) {
		ReportGap objReportGap = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+gapNumber)) {
			objReportGap = (ReportGap)DBCache.INSTANCE.get(CACHE_KEY+gapNumber);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportGap] WHERE GapNumber='" + gapNumber + "'";
			ArrayList<ReportGap> objReportGapList = load(SQL);
			if (objReportGapList!=null && objReportGapList.size()>0) {
				objReportGap = objReportGapList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + gapNumber, objReportGap);
			}
		}
		return objReportGap;
	}
 
	private static ReportGap loadFrom(ResultSet resultSet) {
		ReportGap objReportGap = new ReportGap();
		try {
			objReportGap.setId(resultSet.getString("Id"));
			objReportGap.setReportId(resultSet.getString("ReportId"));
			objReportGap.setShape(resultSet.getObject("Shape"));
			objReportGap.setIsGapInvestigated(resultSet.getBoolean("IsGapInvestigated"));
			objReportGap.setRow(resultSet.getInt("Row"));
			objReportGap.setGapNumber(resultSet.getString("GapNumber"));
			objReportGap.setIsLeakFound(resultSet.getBoolean("IsLeakFound"));
			objReportGap.setColumn(resultSet.getInt("Column"));
		} catch (SQLException e) {
			Log.error("Class ReportGap | " + e.toString());
		}

		return objReportGap;
	}
	
	public ArrayList<ReportGap> getAll() {
		String SQL = "SELECT * FROM dbo.[ReportGap]";
		return load(SQL);
	}
 
	public ArrayList<ReportGap> load(String SQL) {
		ArrayList<ReportGap> objReportGapList = new ArrayList<ReportGap>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				ReportGap objReportGap = loadFrom(resultSet);
				objReportGapList.add(objReportGap);
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objReportGap.getGapNumber(), objReportGap);
			}
			
		} catch (SQLException e) {
			Log.error(e.toString());
		}
		
		return objReportGapList;
	}
}

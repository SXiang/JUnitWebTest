package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import common.source.DateUtility;
import common.source.Log;
 
public class StoredProcSystemHistory extends BaseEntity {
	private static final String CACHE_KEY = "SPSYSTEMHISTORY.";
 
	private Date dateCreated;
	private String note;
	private String UserName;
	
	public StoredProcSystemHistory() {
		super();
	}

 
	public Date getDateCreated() {
		return dateCreated;
	}


	public String getNote() {
		return note;
	}


	public String getUserName() {
		return UserName;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public void setUserName(String userName) {
		UserName = userName;
	}


	public StoredProcSystemHistory get(String reportId) {
		StoredProcSystemHistory objReport = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+reportId)) {
			objReport = (StoredProcSystemHistory)DBCache.INSTANCE.get(CACHE_KEY+reportId);
		} else {
			String SQL = "{call SysHis_GetData(reportId)}";
			ArrayList<StoredProcSystemHistory> objReportList = load(SQL,reportId);
			if (objReportList!=null && objReportList.size()>0) {
				objReport = objReportList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + reportId, objReport);
			}
		}
		return objReport;
	}
 
	private static StoredProcSystemHistory loadFrom(ResultSet resultSet) {
		StoredProcSystemHistory objReport = new StoredProcSystemHistory();
		try {
			objReport.setDateCreated(resultSet.getDate("DateStarted"));
			objReport.setNote(resultSet.getString("Note"));
			objReport.setUserName(resultSet.getString("UserId"));
			
		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}

		return objReport;
	}
	
 
	public ArrayList<StoredProcSystemHistory> load(String SQL, String reportId) {
		ArrayList<StoredProcSystemHistory> objReportList = new ArrayList<StoredProcSystemHistory>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				StoredProcSystemHistory objReport = loadFrom(resultSet);
				objReportList.add(objReport);
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY +reportId, objReport);
			}
			
		} catch (SQLException e) {
			Log.error(e.toString());
		}
		
		return objReportList;
	}
	
}



package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import common.source.Log;

public class ReportStatusType extends BaseEntity {
	private static final String CACHE_KEY = "REPORTSTATUSTYPE.";

	private Object id;
	private String description;

	public ReportStatusType() {
		super();
	}

	public ReportStatusType(Object id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static ReportStatusType getReportStatusType(String description) {
		ReportStatusType objReportStatusType = new ReportStatusType().get(description);
		return objReportStatusType;
	}

	public ReportStatusType get(String description) {
		ReportStatusType objReportStatusType = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+description)) {
			objReportStatusType = (ReportStatusType)DBCache.INSTANCE.get(CACHE_KEY+description);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportStatusType] WHERE Description='" + description + "'";
			ArrayList<ReportStatusType> objReportStatusTypeList = load(SQL);
			if (objReportStatusTypeList!=null && objReportStatusTypeList.size()>0) {
				objReportStatusType = objReportStatusTypeList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + description, objReportStatusType);
			}
		}
		return objReportStatusType;
	}

	private static ReportStatusType loadFrom(ResultSet resultSet) {
		ReportStatusType objReportStatusType = new ReportStatusType();
		try {
			objReportStatusType.setId(resultSet.getObject("Id"));
			objReportStatusType.setDescription(resultSet.getString("Description"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportStatusType;
	}

	public ArrayList<ReportStatusType> getAll() {
		String SQL = "SELECT * FROM dbo.[ReportStatusType]";
		return load(SQL);
	}

	public ArrayList<ReportStatusType> load(String SQL) {
		ArrayList<ReportStatusType> objReportStatusTypeList = new ArrayList<ReportStatusType>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				ReportStatusType objReportStatusType = loadFrom(resultSet);
				objReportStatusTypeList.add(objReportStatusType);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objReportStatusType.getId(), objReportStatusType);
			}

		} catch (SQLException e) {
			Log.error("Class ReportStatusType | " + e.toString());
		}

		return objReportStatusTypeList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
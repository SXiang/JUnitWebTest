package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import common.source.Log;

public class Box extends BaseEntity {
	private static final String CACHE_KEY = "BOX.";

	private Date investigationCompleteDateTime;
	private Object reportId;
	private Object id;
	private Date investigationDateTime;
	private Object reportPeakId;
	private Integer boxTypeId;
	private Integer investigationStatusTypeId;
	private Object boxShape;
	private Object assetShape;
	private String boxNumber;
	private String cantGetIn;

	public Box() {
		super();
	}

	public Box(Date investigationCompleteDateTime, Object reportId, Object id, Date investigationDateTime, Object reportPeakId, Integer boxTypeId, Integer investigationStatusTypeId, Object boxShape, Object assetShape, String boxNumber, String cantGetIn) {
		super();
		this.investigationCompleteDateTime = investigationCompleteDateTime;
		this.reportId = reportId;
		this.id = id;
		this.investigationDateTime = investigationDateTime;
		this.reportPeakId = reportPeakId;
		this.boxTypeId = boxTypeId;
		this.investigationStatusTypeId = investigationStatusTypeId;
		this.boxShape = boxShape;
		this.assetShape = assetShape;
		this.boxNumber = boxNumber;
		this.cantGetIn = cantGetIn;
	}

	public Date getInvestigationCompleteDateTime() {
		return investigationCompleteDateTime;
	}

	public void setInvestigationCompleteDateTime(Date investigationCompleteDateTime) {
		this.investigationCompleteDateTime = investigationCompleteDateTime;
	}

	public Object getReportId() {
		return reportId;
	}

	public void setReportId(Object reportId) {
		this.reportId = reportId;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Date getInvestigationDateTime() {
		return investigationDateTime;
	}

	public void setInvestigationDateTime(Date investigationDateTime) {
		this.investigationDateTime = investigationDateTime;
	}

	public Object getReportPeakId() {
		return reportPeakId;
	}

	public void setReportPeakId(Object reportPeakId) {
		this.reportPeakId = reportPeakId;
	}

	public Integer getBoxTypeId() {
		return boxTypeId;
	}

	public void setBoxTypeId(Integer boxTypeId) {
		this.boxTypeId = boxTypeId;
	}

	public Integer getInvestigationStatusTypeId() {
		return investigationStatusTypeId;
	}

	public void setInvestigationStatusTypeId(Integer investigationStatusTypeId) {
		this.investigationStatusTypeId = investigationStatusTypeId;
	}

	public Object getBoxShape() {
		return boxShape;
	}

	public void setBoxShape(Object boxShape) {
		this.boxShape = boxShape;
	}

	public Object getAssetShape() {
		return assetShape;
	}

	public void setAssetShape(Object assetShape) {
		this.assetShape = assetShape;
	}

	public String getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}

	public String getCantGetIn() {
		return cantGetIn;
	}

	public void setCantGetIn(String cantGetIn) {
		this.cantGetIn = cantGetIn;
	}

	public static Box getBox(String id) {
		Box objBox = new Box().get(id);
		return objBox;
	}

	public Box get(String id) {
		Box objBox = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+id)) {
			objBox = (Box)DBCache.INSTANCE.get(CACHE_KEY+id);
		} else {
			String SQL = "SELECT * FROM dbo.[Box] WHERE Id='" + id + "'";
			ArrayList<Box> objBoxList = load(SQL);
			if (objBoxList!=null && objBoxList.size()>0) {
				objBox = objBoxList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + id, objBox);
			}
		}
		return objBox;
	}

	private static Box loadFrom(ResultSet resultSet) {
		Box objBox = new Box();
		try {
			objBox.setInvestigationCompleteDateTime(getDateColumnValue(resultSet,"InvestigationCompleteDateTime"));
			objBox.setReportId(resultSet.getObject("ReportId"));
			objBox.setId(resultSet.getObject("Id"));
			objBox.setInvestigationDateTime(getDateColumnValue(resultSet,"InvestigationDateTime"));
			objBox.setReportPeakId(resultSet.getObject("ReportPeakId"));
			objBox.setBoxTypeId(getIntColumnValue(resultSet,"BoxTypeId"));
			objBox.setInvestigationStatusTypeId(getIntColumnValue(resultSet,"InvestigationStatusTypeId"));
			objBox.setBoxShape(resultSet.getObject("BoxShape"));
			objBox.setAssetShape(resultSet.getObject("AssetShape"));
			objBox.setBoxNumber(resultSet.getString("BoxNumber"));
			objBox.setCantGetIn(resultSet.getString("CantGetIn"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objBox;
	}

	public ArrayList<Box> getAll() {
		String SQL = "SELECT * FROM dbo.[Box]";
		return load(SQL);
	}

	public void updateInvestigationStatusTypeId(String reportId, Integer boxTypeId, Integer investigationStatusTypeId) {
		String SQL = String.format("UPDATE [dbo].[Box] SET InvestigationStatusTypeId=%d WHERE ReportID='%s' AND BoxTypeId=%d",
				investigationStatusTypeId, reportId, boxTypeId);
		executeNonQuery(SQL);
	}

	public ArrayList<Box> load(String SQL) {
		ArrayList<Box> objBoxList = new ArrayList<Box>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				Box objBox = loadFrom(resultSet);
				objBoxList.add(objBox);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objBox.getId(), objBox);
			}

		} catch (SQLException e) {
			Log.error("Class Box | " + e.toString());
		}

		return objBoxList;
	}
}

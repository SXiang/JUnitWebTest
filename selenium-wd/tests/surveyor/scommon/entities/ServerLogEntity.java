package surveyor.scommon.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ServerLogEntity {

	public static final String COL_DATE_TIME = "Date/Time";
	public static final String COL_EXCEPTION = "Exception";
	public static final String COL_MESSAGE = "Message";
	public static final String COL_LEVEL = "Level";
	public static final String COL_ID = "Id";

	private String id;
	private String thread;
	private String level;
	private String message;
	private String exception;
	private String dateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThread() {
		return thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setValue(String columnName, Object columnValue) {
		if (columnName.equals(COL_ID)) {
			this.setId(columnValue.toString());
		} else if (columnName.equals(COL_LEVEL)) {
			this.setLevel(columnValue.toString());
		} else if (columnName.equals(COL_MESSAGE)) {
			this.setMessage(columnValue.toString());
		} else if (columnName.equals(COL_EXCEPTION)) {
			this.setException(columnValue.toString());
		} else if (columnName.equals(COL_DATE_TIME)) {
			this.setDateTime(columnValue.toString());
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	public static List<ServerLogEntity> toList(Map<String, List<String>> mapData) {
		List<ServerLogEntity> logEntities = null;
		if (mapData != null && mapData.keySet().size() > 0) {
			logEntities = new ArrayList<ServerLogEntity>();
			String firstKey = mapData.keySet().iterator().next();
			List<String> listValues = mapData.get(firstKey);
			for (int i = 0; i < listValues.size(); i++) {
				ServerLogEntity logEntity = new ServerLogEntity();
				for (Entry<String, List<String>> entry : mapData.entrySet()) {
					logEntity.setValue(entry.getKey(), entry.getValue().get(i));
				}

				logEntities.add(logEntity);
			}
		}

		return logEntities;
	}
}
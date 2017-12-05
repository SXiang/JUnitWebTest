package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import common.source.Log;

public class FieldOfView extends BaseEntity {
	private static final String CACHE_KEY = "FIELDOFVIEW.";

	private Object analyzerId;
	private Float epochTime;
	private Object shape;
	private Object surveyId;

	public FieldOfView() {
		super();
	}

	public FieldOfView(Object analyzerId, Float epochTime, Object shape, Object surveyId) {
		super();
		this.analyzerId = analyzerId;
		this.epochTime = epochTime;
		this.shape = shape;
		this.surveyId = surveyId;
	}

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getAnalyzerId()).append(this.getEpochTime())
        		.append(this.getShape()).append(this.getSurveyId())
        		.toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FieldOfView) == false) {
            return false;
        }
        FieldOfView rhs = ((FieldOfView) other);
        return new EqualsBuilder().append(this.getAnalyzerId(), rhs.getAnalyzerId())
        		.append(this.getEpochTime(), rhs.getEpochTime())
        		.append(this.getShape(), rhs.getShape())
        		.append(this.getSurveyId(), rhs.getSurveyId())
        		.isEquals();
    }

	public Object getAnalyzerId() {
		return analyzerId;
	}

	public void setAnalyzerId(Object analyzerId) {
		this.analyzerId = analyzerId;
	}

	public Float getEpochTime() {
		return epochTime;
	}

	public void setEpochTime(Float epochTime) {
		this.epochTime = epochTime;
	}

	public Object getShape() {
		return shape;
	}

	public void setShape(Object shape) {
		this.shape = shape;
	}

	public Object getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Object surveyId) {
		this.surveyId = surveyId;
	}

	public static List<FieldOfView> getFieldOfView(String surveyId) {
		return new FieldOfView().get(surveyId);
	}

	@SuppressWarnings("unchecked")
	public List<FieldOfView> get(String surveyId) {
		List<FieldOfView> objFieldOfViewList = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+surveyId)) {
			objFieldOfViewList = (List<FieldOfView>)DBCache.INSTANCE.get(CACHE_KEY+surveyId);
		} else {
			String SQL = "SELECT * FROM dbo.[FieldOfView] WHERE SurveyId='" + surveyId + "'";
			objFieldOfViewList = load(SQL);
			if (objFieldOfViewList!=null && objFieldOfViewList.size()>0) {
				DBCache.INSTANCE.set(CACHE_KEY + surveyId, objFieldOfViewList);
			}
		}
		return objFieldOfViewList;
	}

	private static FieldOfView loadFrom(ResultSet resultSet) {
		FieldOfView objFieldOfView = new FieldOfView();
		try {
			objFieldOfView.setAnalyzerId(resultSet.getObject("AnalyzerId"));
			objFieldOfView.setEpochTime(getFloatColumnValue(resultSet,"EpochTime"));
			objFieldOfView.setShape(resultSet.getObject("Shape"));
			objFieldOfView.setSurveyId(resultSet.getObject("SurveyId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objFieldOfView;
	}

	public ArrayList<FieldOfView> getAll() {
		String SQL = "SELECT * FROM dbo.[FieldOfView]";
		return load(SQL);
	}

	public ArrayList<FieldOfView> load(String SQL) {
		ArrayList<FieldOfView> objFieldOfViewList = new ArrayList<FieldOfView>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				FieldOfView objFieldOfView = loadFrom(resultSet);
				objFieldOfViewList.add(objFieldOfView);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objFieldOfView.getSurveyId(), objFieldOfView);
			}

		} catch (SQLException e) {
			Log.error("Class FieldOfView | " + e.toString());
		}

		return objFieldOfViewList;
	}
}

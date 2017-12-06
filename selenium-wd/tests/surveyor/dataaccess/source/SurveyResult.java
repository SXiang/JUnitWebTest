package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import common.source.Log;

@SuppressWarnings("rawtypes")
public class SurveyResult extends BaseEntity implements Comparable {
	public static final String CACHE_KEY = "SURVEYRESULT.";

	private Object breadcrumb;
	private Object fieldOfView;
	private Object surveyId;

	public SurveyResult() {
		super();
	}

	public SurveyResult(Object breadcrumb, Object fieldOfView, Object surveyId) {
		super();
		this.breadcrumb = breadcrumb;
		this.fieldOfView = fieldOfView;
		this.surveyId = surveyId;
	}

	@Override
	public int compareTo(Object other) {
		return Integer.compare(this.hashCode(), other.hashCode());
	}

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(nullOrUpper(this.getBreadcrumb()))
        		.append(nullOrUpper(this.getFieldOfView()))
        		.append(nullOrUpper(this.getSurveyId()))
        		.toHashCode();
    }

    @Override
    public boolean equals(Object other) {
    	if (other == this) {
            return true;
        }
        if ((other instanceof SurveyResult) == false) {
            return false;
        }
        SurveyResult rhs = ((SurveyResult) other);
        return new EqualsBuilder().append(nullOrUpper(this.getBreadcrumb()), nullOrUpper(rhs.getBreadcrumb()))
        		.append(nullOrUpper(this.getFieldOfView()), nullOrUpper(rhs.getFieldOfView()))
        		.append(nullOrUpper(this.getSurveyId()), nullOrUpper(rhs.getSurveyId()))
        		.isEquals();
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

	public Object getBreadcrumb() {
		return breadcrumb;
	}

	public void setBreadcrumb(Object breadcrumb) {
		this.breadcrumb = breadcrumb;
	}

	public Object getFieldOfView() {
		return fieldOfView;
	}

	public void setFieldOfView(Object fieldOfView) {
		this.fieldOfView = fieldOfView;
	}

	public Object getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Object surveyId) {
		this.surveyId = surveyId;
	}

	public static List<SurveyResult> getSurveyResult(String surveyId) {
		return new SurveyResult().get(surveyId);
	}

	@SuppressWarnings("unchecked")
	public List<SurveyResult> get(String surveyId) {
		List<SurveyResult> objSurveyResultList = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+surveyId)) {
			objSurveyResultList = (List<SurveyResult>)DBCache.INSTANCE.get(CACHE_KEY+surveyId);
		} else {
			String SQL = "SELECT [SurveyId],CONVERT(VARBINARY(MAX), [FieldOfView]) AS FieldOfView,CONVERT(VARBINARY(MAX), [Breadcrumb]) AS Breadcrumb FROM dbo.[SurveyResult] WHERE SurveyId='" + surveyId + "'";
			objSurveyResultList = load(SQL);
			if (objSurveyResultList!=null && objSurveyResultList.size()>0) {
				DBCache.INSTANCE.set(CACHE_KEY + surveyId, objSurveyResultList);
			}
		}
		return objSurveyResultList;
	}

	private static SurveyResult loadFrom(ResultSet resultSet) {
		SurveyResult objSurveyResult = new SurveyResult();
		try {
			objSurveyResult.setBreadcrumb(resultSet.getString("Breadcrumb"));
			objSurveyResult.setFieldOfView(resultSet.getString("FieldOfView"));
			objSurveyResult.setSurveyId(resultSet.getObject("SurveyId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objSurveyResult;
	}

	public ArrayList<SurveyResult> getAll() {
		String SQL = "SELECT * FROM dbo.[SurveyResult]";
		return load(SQL);
	}

	public ArrayList<SurveyResult> load(String SQL) {
		ArrayList<SurveyResult> objSurveyResultList = new ArrayList<SurveyResult>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				SurveyResult objSurveyResult = loadFrom(resultSet);
				objSurveyResultList.add(objSurveyResult);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objSurveyResult.getSurveyId(), objSurveyResult);
			}

		} catch (SQLException e) {
			Log.error("Class SurveyResult | " + e.toString());
		}

		return objSurveyResultList;
	}
}
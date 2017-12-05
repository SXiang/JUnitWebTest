package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import common.source.Log;

public class Segment extends BaseEntity implements Comparable {
	public static final String CACHE_KEY = "SEGMENT.";

	private Integer mode;
	private Object shape;
	private Integer order;
	private Object surveyId;

	public Segment() {
		super();
	}

	public Segment(Integer mode, Object shape, Integer order, Object surveyId) {
		super();
		this.mode = mode;
		this.shape = shape;
		this.order = order;
		this.surveyId = surveyId;
	}

	@Override
	public int compareTo(Object other) {
		return Integer.compare(this.getOrder(), ((Segment)other).getOrder());
	}

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getMode())
        		.append(nullOrUpper(this.getShape())).append(this.getOrder())
        		.append(nullOrUpper(this.getSurveyId()))
        		.toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Segment) == false) {
            return false;
        }
        Segment rhs = ((Segment) other);
        return new EqualsBuilder().append(this.getMode(), rhs.getMode())
        		.append(nullOrUpper(this.getShape()), nullOrUpper(rhs.getShape())).append(this.getOrder(), rhs.getOrder())
        		.append(nullOrUpper(this.getSurveyId()), nullOrUpper(rhs.getSurveyId()))
        		.isEquals();
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Object getShape() {
		return shape;
	}

	public void setShape(Object shape) {
		this.shape = shape;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Object getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Object surveyId) {
		this.surveyId = surveyId;
	}

	public static List<Segment> getSegment(String surveyId) {
		return new Segment().get(surveyId);
	}

	@SuppressWarnings("unchecked")
	public List<Segment> get(String surveyId) {
		List<Segment> objSegmentList = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+surveyId)) {
			objSegmentList = (List<Segment>)DBCache.INSTANCE.get(CACHE_KEY+surveyId);
		} else {
			String SQL = "SELECT [SurveyId],[Order],[Mode],CONVERT(VARBINARY(MAX), [Shape]) AS Shape FROM dbo.[Segment] WHERE SurveyId='" + surveyId + "'";
			objSegmentList = load(SQL);
			if (objSegmentList!=null && objSegmentList.size()>0) {
				DBCache.INSTANCE.set(CACHE_KEY + surveyId, objSegmentList);
			}
		}
		return objSegmentList;
	}

	private static Segment loadFrom(ResultSet resultSet) {
		Segment objSegment = new Segment();
		try {
			objSegment.setMode(getIntColumnValue(resultSet,"Mode"));
			objSegment.setShape(resultSet.getString("Shape"));
			objSegment.setOrder(getIntColumnValue(resultSet,"Order"));
			objSegment.setSurveyId(resultSet.getObject("SurveyId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objSegment;
	}

	public ArrayList<Segment> getAll() {
		String SQL = "SELECT * FROM dbo.[Segment]";
		return load(SQL);
	}

	public ArrayList<Segment> load(String SQL) {
		ArrayList<Segment> objSegmentList = new ArrayList<Segment>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				Segment objSegment = loadFrom(resultSet);
				objSegmentList.add(objSegment);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objSegment.getSurveyId(), objSegment);
			}

		} catch (SQLException e) {
			Log.error("Class Segment | " + e.toString());
		}

		return objSegmentList;
	}
}

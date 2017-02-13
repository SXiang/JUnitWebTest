package common.source;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OLMapEntities {

	public class Indication {
		public String ch4;
		public String classificationConfidence;
		public String disposition;
		public String ethane;
		public String ethaneRatio;
		public String ethaneRatioSdev;
		public String amplitude;
		public String epochTime;
		public Integer index;
		public Double lat;
		public Double lon;
		public String text;

		public Indication(String ch4, String classConfid, String disposition, String ethane, String ethaneRatio,
				String ethaneRatioSdev, String amp, String epochTm, Integer idx, Double lat, Double lon, String txt) {
			this.ch4 = ch4;
			this.classificationConfidence = classConfid;
			this.disposition = disposition;
			this.ethane = ethane;
			this.ethaneRatio = ethaneRatio;
			this.ethaneRatioSdev = ethaneRatioSdev;
			this.amplitude = amp;
			this.epochTime = epochTm;
			this.index = idx;
			this.lat = lat;
			this.lon = lon;
			this.text = txt;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}

			if (!(obj instanceof Indication)) {
				return false;
			}

			Indication indObj = (Indication)obj;

			return new EqualsBuilder()
					.append(indObj.text, this.text)
					.append(indObj.disposition, this.disposition)
					.isEquals();
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder(29, 53)
					.append(this.text)
					.append(this.disposition)
					.hashCode();
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
	}
}
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

			return new EqualsBuilder().append(indObj.index, this.index)
					.append(indObj.text, this.text)
					.append(indObj.disposition, this.disposition)
					.isEquals();

			// NOTE: Seeing floating point differences in Indications shown in Driver view vs. Survey view.
			// Keeping this commented until deciding what properties from Indications need to match.
			//return new EqualsBuilder().append(indObj.CH4, this.CH4).append(indObj.ClassificationConfidence, this.ClassificationConfidence)
			//			.append(indObj.Disposition, this.Disposition).append(indObj.Ethane, this.Ethane)
			//			.append(indObj.EthaneRatio, this.EthaneRatio).append(indObj.EthaneRatioSdev, this.EthaneRatioSdev)
			//			.append(indObj.amplitude, this.amplitude).append(indObj.epochTime, this.epochTime)
			//			.append(indObj.index, this.index).append(indObj.lat, this.lat)
			//			.append(indObj.lon, this.lon).append(indObj.text, this.text)
			//			.isEquals();
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder(29, 53).append(this.index)
					.append(this.text)
					.append(this.disposition)
					.hashCode();

			// NOTE: Seeing floating point differences in Indications shown in Driver view vs. Survey view.
			// Keeping this commented until deciding what properties from Indications need to match.
			//return new HashCodeBuilder(29, 53).append(this.CH4).append(this.ClassificationConfidence)
			//	.append(this.Disposition).append(this.Ethane)
			//	.append(this.EthaneRatio).append(this.EthaneRatioSdev)
			//	.append(this.amplitude).append(this.epochTime)
			//	.append(this.index).append(this.lat)
			//	.append(this.lon).append(this.text)
			//	.hashCode();
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
	}
}
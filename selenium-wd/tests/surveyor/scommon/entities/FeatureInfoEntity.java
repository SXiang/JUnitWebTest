package surveyor.scommon.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FeatureInfoEntity {
	private String disposition;
	private String classificationConfidence;
	private String methaneConcentration;
	private String ethaneRatio;
	private String amplitude;

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getClassificationConfidence() {
		return classificationConfidence;
	}

	public void setClassificationConfidence(String classificationConfidence) {
		this.classificationConfidence = classificationConfidence;
	}

	public String getMethaneConcentration() {
		return methaneConcentration;
	}

	public void setMethaneConcentration(String methaneConcentration) {
		this.methaneConcentration = methaneConcentration;
	}

	public String getEthaneRatio() {
		return ethaneRatio;
	}

	public void setEthaneRatio(String ethaneRatio) {
		this.ethaneRatio = ethaneRatio;
	}

	public String getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(String amplitude) {
		this.amplitude = amplitude;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
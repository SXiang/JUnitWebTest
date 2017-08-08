package androidapp.entities.source;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class InvestigationMarkerEntity {
	private String lisaNumber;
	private String investigationStatus;

	public String getLisaNumber() {
		return lisaNumber;
	}

	public void setLisaNumber(String lisaNumber) {
		this.lisaNumber = lisaNumber;
	}

	public String getInvestigationStatus() {
		return investigationStatus;
	}

	public void setInvestigationStatus(String status) {
		this.investigationStatus = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}

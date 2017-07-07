package surveyor.api.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "InvestigationStatusTypeId",
    "BoxNumber",
    "WKT",
    "LeakInvestigaterId",
    "Id"
})
public class InvestigationReportBoxInfos {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("InvestigationStatusTypeId")
    private Double investigationStatusTypeId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("BoxNumber")
    private Double boxNumber;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("WKT")
    private String wKT;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("LeakInvestigaterId")
    private Double leakInvestigaterId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("Id")
    private String id;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("InvestigationStatusTypeId")
    public Double getInvestigationStatusTypeId() {
        return investigationStatusTypeId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("InvestigationStatusTypeId")
    public void setInvestigationStatusTypeId(Double investigationStatusTypeId) {
        this.investigationStatusTypeId = investigationStatusTypeId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("BoxNumber")
    public Double getBoxNumber() {
        return boxNumber;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("BoxNumber")
    public void setBoxNumber(Double boxNumber) {
        this.boxNumber = boxNumber;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("WKT")
    public String getWKT() {
        return wKT;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("WKT")
    public void setWKT(String wKT) {
        this.wKT = wKT;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("LeakInvestigaterId")
    public Double getLeakInvestigaterId() {
        return leakInvestigaterId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("LeakInvestigaterId")
    public void setLeakInvestigaterId(Double leakInvestigaterId) {
        this.leakInvestigaterId = leakInvestigaterId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(investigationStatusTypeId).append(boxNumber).append(wKT).append(leakInvestigaterId).append(id).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InvestigationReportBoxInfos) == false) {
            return false;
        }
        InvestigationReportBoxInfos rhs = ((InvestigationReportBoxInfos) other);
        return new EqualsBuilder().append(investigationStatusTypeId, rhs.investigationStatusTypeId).append(boxNumber, rhs.boxNumber).append(wKT, rhs.wKT).append(leakInvestigaterId, rhs.leakInvestigaterId).append(id, rhs.id).isEquals();
    }

}
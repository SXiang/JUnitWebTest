package surveyor.apitest.source;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "leaks",
    "Message",
    "investigationStatusTypeId"
})
public class InvestigationBoxInfo {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("leaks")
    private List<Object> leaks = new ArrayList<Object>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("Message")
    private String message;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("investigationStatusTypeId")
    private Double investigationStatusTypeId;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("leaks")
    public List<Object> getLeaks() {
        return leaks;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("leaks")
    public void setLeaks(List<Object> leaks) {
        this.leaks = leaks;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("Message")
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("investigationStatusTypeId")
    public Double getInvestigationStatusTypeId() {
        return investigationStatusTypeId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("investigationStatusTypeId")
    public void setInvestigationStatusTypeId(Double investigationStatusTypeId) {
        this.investigationStatusTypeId = investigationStatusTypeId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(leaks).append(message).append(investigationStatusTypeId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InvestigationBoxInfo) == false) {
            return false;
        }
        InvestigationBoxInfo rhs = ((InvestigationBoxInfo) other);
        return new EqualsBuilder().append(leaks, rhs.leaks).append(message, rhs.message).append(investigationStatusTypeId, rhs.investigationStatusTypeId).isEquals();
    }

}



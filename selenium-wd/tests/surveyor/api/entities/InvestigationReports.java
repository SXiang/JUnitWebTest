package surveyor.api.entities;

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
    "iTotalRecords",
    "aaData",
    "sEcho",
    "iTotalDisplayRecords"
})
public class InvestigationReports {

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("iTotalRecords")
    private Double iTotalRecords;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("aaData")
    private List<AaDatum> aaData = new ArrayList<AaDatum>();
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("sEcho")
    private String sEcho;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("iTotalDisplayRecords")
    private Double iTotalDisplayRecords;

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("iTotalRecords")
    public Double getITotalRecords() {
        return iTotalRecords;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("iTotalRecords")
    public void setITotalRecords(Double iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("aaData")
    public List<AaDatum> getAaData() {
        return aaData;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("aaData")
    public void setAaData(List<AaDatum> aaData) {
        this.aaData = aaData;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("sEcho")
    public String getSEcho() {
        return sEcho;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("sEcho")
    public void setSEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("iTotalDisplayRecords")
    public Double getITotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("iTotalDisplayRecords")
    public void setITotalDisplayRecords(Double iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(iTotalRecords).append(aaData).append(sEcho).append(iTotalDisplayRecords).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InvestigationReports) == false) {
            return false;
        }
        InvestigationReports rhs = ((InvestigationReports) other);
        return new EqualsBuilder().append(iTotalRecords, rhs.iTotalRecords).append(aaData, rhs.aaData).append(sEcho, rhs.sEcho).append(iTotalDisplayRecords, rhs.iTotalDisplayRecords).isEquals();
    }
}
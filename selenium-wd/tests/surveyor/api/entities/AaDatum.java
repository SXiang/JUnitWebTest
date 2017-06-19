package surveyor.api.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ReportName",
    "Id",
    "ReportTitle"
})
public class AaDatum {

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("ReportName")
    private String reportName;
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
    @JsonProperty("ReportTitle")
    private String reportTitle;

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("ReportName")
    public String getReportName() {
        return reportName;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("ReportName")
    public void setReportName(String reportName) {
        this.reportName = reportName;
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

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("ReportTitle")
    public String getReportTitle() {
        return reportTitle;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("ReportTitle")
    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(reportName).append(id).append(reportTitle).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AaDatum) == false) {
            return false;
        }
        AaDatum rhs = ((AaDatum) other);
        return new EqualsBuilder().append(reportName, rhs.reportName).append(id, rhs.id).append(reportTitle, rhs.reportTitle).isEquals();
    }
}
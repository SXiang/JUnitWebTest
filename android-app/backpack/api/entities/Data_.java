package backpack.api.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "heatmapImageTime",
    "heatmap"
})
public class Data_ {

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("heatmapImageTime")
    private Double heatmapImageTime;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("heatmap")
    private String heatmap;

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("heatmapImageTime")
    public Double getHeatmapImageTime() {
        return heatmapImageTime;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("heatmapImageTime")
    public void setHeatmapImageTime(Double heatmapImageTime) {
        this.heatmapImageTime = heatmapImageTime;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("heatmap")
    public String getHeatmap() {
        return heatmap;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("heatmap")
    public void setHeatmap(String heatmap) {
        this.heatmap = heatmap;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(heatmapImageTime).append(heatmap).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data_) == false) {
            return false;
        }
        Data_ rhs = ((Data_) other);
        return new EqualsBuilder().append(heatmapImageTime, rhs.heatmapImageTime).append(heatmap, rhs.heatmap).isEquals();
    }
}
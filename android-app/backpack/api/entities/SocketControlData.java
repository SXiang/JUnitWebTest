package backpack.api.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "webSocketEnabled"
})
public class SocketControlData {

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("webSocketEnabled")
    private Double webSocketEnabled;

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("webSocketEnabled")
    public Double getWebSocketEnabled() {
        return webSocketEnabled;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("webSocketEnabled")
    public void setWebSocketEnabled(Double webSocketEnabled) {
        this.webSocketEnabled = webSocketEnabled;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(webSocketEnabled).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SocketControlData) == false) {
            return false;
        }
        SocketControlData rhs = ((SocketControlData) other);
        return new EqualsBuilder().append(webSocketEnabled, rhs.webSocketEnabled).isEquals();
    }

}

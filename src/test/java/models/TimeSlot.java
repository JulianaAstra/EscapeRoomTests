package models;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TimeSlot(
        String time,
        @JsonProperty("isAvailable")
        boolean isAvailable) {
}

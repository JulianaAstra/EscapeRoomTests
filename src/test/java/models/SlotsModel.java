package models;

import java.util.List;

public record SlotsModel(
        List<TimeSlot> today,
        List<TimeSlot> tomorrow
) {
}

package models;

public record UserBookingResponseModel(
        String date,
        String time,
        String contactPerson,
        String phone,
        String withChildren,
        Integer peopleCount,
        String id,
        LocationModel location,
        QuestModel quest
) {
}
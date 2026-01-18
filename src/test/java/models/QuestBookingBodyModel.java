package models;

public record QuestBookingBodyModel(
        String date,
        String time,
        String contactPerson,
        String phone,
        Boolean withChildren,
        Integer peopleCount,
        String placeId
) {}
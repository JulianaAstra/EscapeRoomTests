package models;

public record BookingQuestResponseModel(
        String date,
        String time,
        String contactPerson,
        String phone,
        Boolean withChildren,
        Integer peopleCount,
        String id,
        LocationModel location,
        QuestModel quest
) {}
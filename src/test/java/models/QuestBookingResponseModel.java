package models;

public record QuestBookingResponseModel(
        String id,
        LocationModel location,
        SlotsModel slots) {}
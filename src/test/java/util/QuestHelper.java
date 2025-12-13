package util;

import models.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import static models.Day.TODAY;
import static models.Day.TOMORROW;

public class QuestHelper {
    private final Random random = new Random();

    public QuestModel getRandomQuest(List<QuestModel> quests) {
        if (quests == null || quests.isEmpty()) {
            throw new IllegalArgumentException("Список квестов пуст");
        }
        return quests.get(random.nextInt(quests.size()));
    }

    public SlotWithDay getFirstAvailableTimeSlotWithDay(QuestBookingResponseModel questInfo) {
        Optional<TimeSlot> todaySlot = questInfo.slots().today().stream()
                .filter(TimeSlot::isAvailable)
                .findFirst();

        if (todaySlot.isPresent()) {
            return new SlotWithDay(TODAY, todaySlot.get().time());
        }

        Optional<TimeSlot> tomorrowSlot = questInfo.slots().tomorrow().stream()
                .filter(TimeSlot::isAvailable)
                .findFirst();

        if (tomorrowSlot.isPresent()) {
            return new SlotWithDay(TOMORROW, tomorrowSlot.get().time());
        }

        throw new RuntimeException("Нет доступных тайм-слотов для бронирования");
    }

    public boolean hasAvailableTimeSlots(QuestBookingResponseModel questInfo) {
        return questInfo.slots().today().stream().anyMatch(TimeSlot::isAvailable) ||
                questInfo.slots().tomorrow().stream().anyMatch(TimeSlot::isAvailable);
    }

    public String findBookingIdByQuestId(List<UserBookingResponseModel> bookings, String questId) {
        return bookings.stream()
                .filter(booking -> booking.quest() != null)
                .filter(booking -> questId.equals(booking.quest().id()))
                .map(UserBookingResponseModel::id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Бронирование для квеста с ID '" + questId + "' не найдено"
                ));
    }
}

package util;

import java.util.Arrays;

public enum Quest {
    TOMB("Склеп", "3f01a970-95e0-4c26-a698-e07468c64d1c"),
    MANIAC("Маньяк", "37bcac46-4258-49dd-b97f-f382285ffc1d"),
    RITUAL("Ритуал", "2f6786ee-4186-4da6-9381-e34730ca55a3"),
    OLD_MANOR("Тайны старого особняка", "cceaebef-3926-4d60-b1ab-31231517798d"),
    FOREST_HUT("Хижина в лесу", "77a69cfb-be61-4563-9f68-21f07840e016"),
    FATAL_EXPERIMENT("Фатальный эксперимент", "f404a4cc-1a4e-4e87-9194-cae61dc43ba5"),
    METRO_2023("Метро 2033", "d014f95c-b4a3-4048-9f7f-8f0511c608d3"),
    OLD_ATTIC("Старый чердак", "6d591afd-5115-43f6-9d78-0e4b5ebadc95"),
    MARS_2056("Марс-2056", "6ace24c3-763a-40f4-9dca-710ca665dc12"),
    LAST_EDGE("Последний рубеж", "4d803fb3-ccc2-4aed-bf24-f735387ee7f1"),
    GHOST_STORY("История призраков", "427c2e29-a96a-4535-8cd5-61c0363609d8");

    private final String questName;
    private final String id;

    Quest(String questName, String id) {
        this.questName = questName;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static String getIdByName(String questName) {
        return Arrays.stream(values())
                .filter(quest -> quest.questName.equals(questName))
                .map(Quest::getId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Квест с названием '" + questName + "' не найден. "
                ));
    }

    public static String getBookingUrlByName(String questName) {
        return "quest/" + getIdByName(questName) + "/booking";
    }

}

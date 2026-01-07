package models;

import java.util.List;

public enum QuestType {
    ADVENTURE("Приключения", "adventures"),
    HORROR("Ужасы", "horror"),
    MYSTIC("Мистика", "mystic"),
    DETECTIVE("Детектив", "detective"),
    SCI_FI("Sci-fi", "sci-fi");

    private final String russianName;
    private final String englishName;

    QuestType(String russianName, String englishName) {
        this.russianName = russianName;
        this.englishName = englishName;
    }

    public String getRussianName() {
        return russianName;
    }

    public String getEnglishName() {
        return englishName;
    }
}


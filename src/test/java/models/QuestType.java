package models;

public enum QuestType {
    ADVENTURE("adventures"),
    HORROR("horror"),
    MYSTIC("mystic"),
    DETECTIVE("detective"),
    SCI_FI("sci-fi");

    private final String englishName;

    QuestType(String englishName) {
        this.englishName = englishName;
    }
    public String getEnglishName() {
        return englishName;
    }
}


package models;

public enum QuestDifficulty {
    EASY("easy", "легко"),
    MEDIUM("medium", "средне"),
    HARD("hard", "сложно");

    private final String englishName;
    private final String questCardName;

    QuestDifficulty(String englishName, String questCardName) {
        this.englishName = englishName;
        this.questCardName = questCardName;
    }

    public String getEnglishName() {
        return englishName;
    }
    public String getQuestCardName() {
        return questCardName;
    }
}


package models;

public enum Quest {
    CRYPT("Склеп", "сложно", "horror"),
    MANIAC("Маньяк", "средне", "horror"),
    RITUAL("Ритуал", "сложно", "mystic"),
    OLD_MANOR("Тайны старого особняка", "легко", "detective"),
    HUT("Хижина в лесу", "средне", "mystic"),
    FATAL_EXPERIMENT("Фатальный эксперимент", "сложно", "adventures"),
    METRO_2033("Метро 2033", "средне", "sci-fi"),
    LOFT("Старый чердак", "легко", "detective"),
    MARS_2056("Марс-2056", "легко", "sci-fi"),
    LAST_FRONTIER("Последний рубеж", "средне", "adventures"),
    GHOST_STORY("История призраков", "легко", "mystic");


    private final String name;
    private final String difficulty;
    private final String type;

    Quest(String name, String difficulty, String type) {
        this.name = name;
        this.difficulty = difficulty;
        this.type = type;
    }

    public String getname() {
        return name;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public String getType() {
        return type;
    }
}
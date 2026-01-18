package tests.ui;

import io.qameta.allure.Feature;
import models.Quest;
import models.QuestDifficulty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pages.MainPage;
import pages.QuestPage;
import tests.TestBase;
import java.util.List;

@Tags({@Tag("all"), @Tag("ui"), @Tag("filtration_all"), @Tag("filtration_ui")})
@Feature("Фильтрация квестов")
@DisplayName("UI тесты на фильтрацию квестов на главной странице")
public class FiltrationUITests extends TestBase {
    MainPage mainPage = new MainPage();
    QuestPage questPage = new QuestPage();

    @EnumSource(QuestDifficulty.class)
    @ParameterizedTest(name = "Сложность {0}")
    @DisplayName("Квесты фильтруются по сложности")
    void successfulFilterQuestsTest(QuestDifficulty questDifficulty) {
        mainPage.openMainPage()
                .filterQuestsByDifficulty(questDifficulty.getEnglishName());

        List<String> questsDifficulties = mainPage.getAllQuestsDiffs();

        mainPage.checkQuestsDifficultyInList(questsDifficulties, questDifficulty.getQuestCardName(), "Фактическая сложность квеста не соответствует фильтрации");
    }

    @EnumSource(Quest.class)
    @ParameterizedTest(name = "Квест {0}")
    @DisplayName("Страница квеста {questName} открывается при клике на карточку квеста в списке")
    void successfulOpenQuestPageTest(Quest quest) {
        String questName = quest.getname();
        String questType = quest.getType();
        String questDifficulty = quest.getDifficulty();

        mainPage.openMainPage()
                .openQuestPage(questName);
        questPage.checkQuestType(questName, questType);
        questPage.checkQuestDifficulty(questName, questDifficulty);
    }
}

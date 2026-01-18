package tests.ui;

import io.qameta.allure.Feature;
import models.Quest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pages.MainPage;
import pages.QuestPage;

@Tags({@Tag("all"), @Tag("ui")})
@Feature("Страница квеста с подробной информацией")
@DisplayName("UI тесты на проверку страницы квеста")
public class QuestPageTests {
    MainPage mainPage = new MainPage();
    QuestPage questPage = new QuestPage();

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

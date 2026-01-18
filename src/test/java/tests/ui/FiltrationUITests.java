package tests.ui;

import io.qameta.allure.Feature;
import models.QuestDifficulty;
import models.QuestType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pages.MainPage;
import tests.TestBase;

import java.util.List;

@Tags({@Tag("all"), @Tag("ui"), @Tag("filtration_all"), @Tag("filtration_ui")})
@Feature("Фильтрация квестов")
@DisplayName("UI тесты на фильтрацию квестов на главной странице")
public class FiltrationUITests extends TestBase {
    MainPage mainPage = new MainPage();

    // тест 1 добавить фильтр по сложности, чистый UI

    @EnumSource(QuestDifficulty.class)
    @ParameterizedTest(name = "Сложность {0}")
    @DisplayName("Квесты фильтруются по сложности")
    void successfulFilterQuestsTest(QuestDifficulty questDifficulty) {
        mainPage.openMainPage()
                .filterQuestsByDifficulty(questDifficulty.getEnglishName());

        List<String> questsDifficulties = mainPage.getAllQuestsDiffs();

        mainPage.checkQuestsDifficultyInList(questsDifficulties, questDifficulty.getQuestCardName(), "Фактическая сложность квеста не соответствует фильтрации");
    }

    // тест 2 при клике на карточку теста открывается страница теста - проверить название, сложность, какие-то данные

    @Test
    @DisplayName("Пользователь регистрируется с валидными данными")
    void registerWithValidDataTest() {
        mainPage.openMainPage();
    }
}

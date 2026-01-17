package tests.uiapi;

import api.QuestsApiSteps;
import io.qameta.allure.Feature;
import models.QuestType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pages.MainPage;
import tests.TestBase;

import java.util.List;

@Tags({@Tag("all"), @Tag("uiapi"), @Tag("filtration_all"), @Tag("filtration_uiapi")})
@Feature("Фильтрация квестов")
@DisplayName("UI+API тесты на фильтрацию квестов на главной странице")
public class FiltrationUIAPITests extends TestBase {
    MainPage mainPage = new MainPage();
    QuestsApiSteps questsApiSteps = new QuestsApiSteps();

    @EnumSource(QuestType.class)
    @ParameterizedTest(name = "Тематика {0}")
    @DisplayName("Квесты фильтруются по тематике")
    void successfulFilterQuestsTest(QuestType questType) {
        mainPage.openMainPage()
                .filterQuestsByType(questType.getEnglishName());

        List<String> questsIds = mainPage.getAllQuestsIds();
        questsApiSteps.checkQuestsTypeInList(questsIds, questType.getEnglishName());
    }
}

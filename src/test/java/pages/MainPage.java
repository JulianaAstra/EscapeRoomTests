package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final ElementsCollection questCards = $$(".quest-card");
    private final SelenideElement questPageHeader = $(".quest-page__content h1");
    private final SelenideElement loader = $(byText("Loading ..."));
    private final SelenideElement loginButtonNoAuth = $("[data-status='No_auth']");
    private final SelenideElement loginButtonAuth = $("[data-status='Auth']");
    private final SelenideElement myBookingsBtn = $("[href='/my-quests']");
    private final ElementsCollection filter = $$(".filter__list label");

    @Step("Открыть главную страницу")
    public MainPage openMainPage() {
        open("");
        loader.shouldNotBe(visible);
        questCards.shouldHave(sizeGreaterThan(0));

        return this;
    }

    @Step("Открыть страницу квеста {questName}")
    public void openQuestPage(String questName) {
        questCards.shouldHave(sizeGreaterThan(0))
                .findBy(text(questName))
                .click();
        questPageHeader.shouldHave(text(questName));
    }

    @Step("Перейти на страницу регистрации")
    public void openLoginPage() {
        loginButtonNoAuth.shouldBe(visible)
                .click();
    }

    @Step("Пользователь залогинен")
    public MainPage checkLogin() {
        myBookingsBtn.shouldBe(visible);
        loginButtonAuth.shouldBe(visible);
        return this;
    }

    @Step("Отфильтровать квесты по тематике {questType}")
    public MainPage filterQuestsByType(String questType) {
        filter.findBy(attribute("for", questType))
                .click();
        questCards.shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Получить id всех квестов в списке")
    public List<String> getAllQuestsIds() {
        return questCards.stream()
                .map(card -> card.$("a").getAttribute("href"))
                .map(url -> url.split("quest/")[1])
                .toList();
    }

    @Step("Отфильтровать квесты по сложности {questDifficulty}")
    public MainPage filterQuestsByDifficulty(String questDifficulty) {
        filter.findBy(attribute("for", questDifficulty))
                .click();
        questCards.shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Получить сложность всех квестов в списке")
    public List<String> getAllQuestsDiffs() {
        return questCards.stream()
                .map(card -> card.$$(".tags__item").get(1).getText())
                .toList();
    }

    @Step("Проверка всех квестов в списке на соответствие сложности {expectedQuestDifficulty}")
    public void checkQuestsDifficultyInList(List<String> questsDiffs, String expectedQuestDifficulty, String message) {
        for (String questDiff: questsDiffs) {
            checkQuestDifficulty(expectedQuestDifficulty, questDiff, message);
        }
    }

    @Step("Проверка сложности {expectedQuestDifficulty} квеста")
    private void checkQuestDifficulty(String expectedQuestDifficulty, String actualQuestDifficulty, String message) {
        Assertions.assertEquals(actualQuestDifficulty, expectedQuestDifficulty, message);
    }
}

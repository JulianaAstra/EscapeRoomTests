package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    ElementsCollection questCards = $$(".quest-card");
    SelenideElement questPageHeader = $(".quest-page__content h1");
    SelenideElement loader = $(byText("Loading ..."));

    @Step("Открыть главную страницу")
    public MainPage openMainPage() {
        open("");
        loader.shouldNotBe(visible);
        questCards.shouldHave(sizeGreaterThan(0));

        return this;
    }

    @Step("Открыть страницу квеста {questName}")
    public QuestPage openQuestPage(String questName) {
        questCards
                .shouldHave(sizeGreaterThan(0))
                .findBy(text(questName))
                .click();
        questPageHeader
                .shouldHave(text(questName));

        return new QuestPage();
    }
}

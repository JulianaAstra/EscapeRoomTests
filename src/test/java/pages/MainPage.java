package pages;

import com.codeborne.selenide.ElementsCollection;
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
    SelenideElement loginButtonNoAuth = $("[data-status='No_auth']");
    SelenideElement loginButtonAuth = $("[data-status='Auth']");
    SelenideElement myBookingsBtn = $("[href='/my-quests']");

    @Step("Открыть главную страницу")
    public MainPage openMainPage() {
        open("");
        loader.shouldNotBe(visible);
        questCards.shouldHave(sizeGreaterThan(0));

        return this;
    }

    @Step("Открыть страницу квеста {questName}")
    public void openQuestPage(String questName) {
        questCards
                .shouldHave(sizeGreaterThan(0))
                .findBy(text(questName))
                .click();
        questPageHeader
                .shouldHave(text(questName));
    }

    @Step("Перейти на страницу регистрации")
    public void openLoginPage() {
        loginButtonNoAuth
                .shouldBe(visible)
                .click();
    }

    @Step("Пользователь залогинен")
    public void checkLogin() {
        myBookingsBtn
                .shouldBe(visible);
        loginButtonAuth
                .shouldBe(visible);
    }
}

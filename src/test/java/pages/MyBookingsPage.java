package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MyBookingsPage {
    private final SelenideElement loader = $(byText("Loading ..."));
    private final SelenideElement myBookingsPageHeader = $("h1.page-content__title");
    private final ElementsCollection questCards = $$(".quest-card");

    @Step("Cтраница забронированных квестов отображается")
    public MyBookingsPage checkMyBookingsPageOpened() {
        loader.shouldNotBe(visible);
        myBookingsPageHeader.shouldBe(visible)
                .shouldHave(text("Мои бронирования"));
        return this;
    }

    @Step("Забронированный квест {questName} отображается на странице бронирований")
    public MyBookingsPage checkBookedQuest(String questName, String bookTime) {
        questCards.shouldHave(sizeGreaterThan(0))
                .findBy(text(questName))
                .$(".quest-card__info")
                .shouldHave(text(bookTime));
        return this;
    }
}

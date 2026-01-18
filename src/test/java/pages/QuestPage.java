package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QuestPage {
    SelenideElement bookingBtn = $(".quest-page__btn");
    ElementsCollection questTags = $$(".tags__item");
    SelenideElement questType = $(".quest-page__subtitle");

    @Step("Клик по кнопке бронирования квеста")
    public void bookingBtnClick() {
        bookingBtn.shouldBe(visible)
                .click();
    }

    @Step("Открыть страницу квеста {questName} с токеном залогиненного пользователя")
    public QuestPage openQuestPageWithLocalStorage(String token, String questID) {
        open("favicon.ico");
        executeJavaScript(String.format("localStorage.setItem('escape-room-token', '%s');", token));
        open("/quest/" + questID);

        return this;
    }

    @Step("Сложность квеста {questName} - {expectedQuestDifficulty}")
    public void checkQuestDifficulty(String questName, String expectedQuestDifficulty) {
        questTags.shouldHave(sizeGreaterThan(0))
                .get(1)
                .shouldHave(text(expectedQuestDifficulty));
    }

    @Step("Тип квеста {questName} - {expectedQuestType}")
    public void checkQuestType(String questName, String expectedQuestType) {
        questType.shouldBe(visible)
                .shouldHave(text(expectedQuestType));
    }
}

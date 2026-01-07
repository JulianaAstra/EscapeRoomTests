package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QuestPage {
    SelenideElement bookingBtn = $(".quest-page__btn");

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
}

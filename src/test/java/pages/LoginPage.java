package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    SelenideElement loginPageHeader = $("h1.login-form__title");
    SelenideElement loginForm = $("form.login-form");
    SelenideElement loader = $(byText("Loading ..."));

    @Step("Cтраница логина с формой регистрации отображается")
    public void checkLoginPageOpened() {
        loader.shouldNotBe(visible);
        loginPageHeader
                .shouldBe(visible)
                .shouldHave(text("Вход"));
        loginForm
                .shouldBe(visible);
    }
}

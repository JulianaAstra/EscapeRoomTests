package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    SelenideElement loginPageHeader = $("h1.login-form__title");
    SelenideElement loginForm = $("form.login-form");
    SelenideElement loader = $(byText("Loading ..."));
    SelenideElement emailInput = $("#email");
    SelenideElement passwordInput = $("#password");
    SelenideElement agreementCheckbox = $(".custom-checkbox__icon");
    SelenideElement submitBtn = $(".login-form__submit");

    @Step("Cтраница логина с формой регистрации отображается")
    public LoginPage checkLoginPageOpened() {
        loader.shouldNotBe(visible);
        loginPageHeader
                .shouldBe(visible)
                .shouldHave(text("Вход"));
        loginForm
                .shouldBe(visible);
        return this;
    }

    @Step("Ввести email {email}")
    public LoginPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Ввести пароль {password}")
    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Выбрать чек-бокс согласия с правилами обработки данных")
    public LoginPage setCheckbox() {
        agreementCheckbox
                .click();
        return this;
    }

    @Step("Клик по кнопке Войти")
    public void clickSubmitBtn() {
        checkSubmitBtn(true);
        submitBtn
                .click();
    }

    @Step("Состояние кнопки Войти - {submitBtnState}")
    public void checkSubmitBtn(boolean submitBtnState) {
        boolean btnState = submitBtn.isEnabled();
        Assertions.assertEquals(btnState, submitBtnState);
    }

    @Step("Регистрация пользователя с валидными данными")
    public void registerUser(String email, String password) {
        setEmail(email)
        .setPassword(password)
        .setCheckbox()
        .clickSubmitBtn();
    }
}

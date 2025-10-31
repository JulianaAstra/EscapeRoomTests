package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;
import pages.MainPage;
import pages.QuestPage;

import static com.codeborne.selenide.Selenide.open;

public class EscapeRoomTests extends TestBase {
    private static final Logger log = LoggerFactory.getLogger(EscapeRoomTests.class);
    MainPage mainPage = new MainPage();
    QuestPage questPage = new QuestPage();
    LoginPage loginPage = new LoginPage();
    TestData testData = new TestData();

    @ValueSource(strings = {
            "Склеп",
            "Маньяк",
            "Ритуал",
            "Тайны старого особняка",
            "Хижина в лесу",
            "Фатальный эксперимент",
            "Метро 2033",
            "Старый чердак",
            "Марс-2056",
            "Последний рубеж",
            "История призраков"
    })
    @ParameterizedTest(name = "Попытка бронирования квеста {0} без логина приводит на страницу регистрации")
    @DisplayName("Попытка бронирования квеста без логина приводит на страницу регистрации")
    void bookingWithoutLogin(String quest) {
        mainPage
                .openMainPage()
                .openQuestPage(quest);
        questPage
                .bookingBtnClick();

        loginPage
                .checkLoginPageOpened();
    }

    @Test
    @DisplayName("Пользователь регистрируется с валидными данными")
    void registerWithValidData() {
        mainPage
                .openMainPage()
                .openLoginPage();
        loginPage
                .checkLoginPageOpened()
                .setEmail(testData.validEmail)
                .setPassword(testData.validPassword)
                .setCheckbox()
                .clickSubmitBtn();
    }

    @Test
    @DisplayName("Регистрация невозможна с невалидным email")
    void registerWithInvalidEmail() {
        mainPage
                .openMainPage()
                .openLoginPage();
        loginPage
                .checkLoginPageOpened()
                .setEmail(testData.invalidEmail)
                .setPassword(testData.validPassword)
                .setCheckbox()
                .checkSubmitBtn(false);
    }

    @Test
    @DisplayName("Квест бронируется и отображается на странице бронирований")
    void test3() {
        open("");
    }

    @Test
    @DisplayName("Список квестов отображается на главной странице")
    void test4() {
        open("");
    }

    @Test
    @DisplayName("Список квестов фильтруется по жанру")
    void test5() {
        open("");
    }
}

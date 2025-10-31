package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.*;
import static tests.TestData.*;

public class EscapeRoomTests extends TestBase {
    MainPage mainPage = new MainPage();
    QuestPage questPage = new QuestPage();
    LoginPage loginPage = new LoginPage();
    TestData testData = new TestData();
    BookingPage bookingPage = new BookingPage();
    MyBookingsPage myBookingsPage = new MyBookingsPage();

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
    void bookingWithoutLoginTest(String quest) {
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
    void registerWithValidDataTest() {
        mainPage
                .openMainPage()
                .openLoginPage();
        loginPage
                .checkLoginPageOpened()
                .setEmail(testData.validEmail)
                .setPassword(testData.validPassword)
                .setCheckbox()
                .clickSubmitBtn();
        mainPage
                .checkLogin();
    }

    @ValueSource(strings = {
            EMAIL_NO_DOMAIN,
            EMAIL_NO_AT,
            EMAIL_DOUBLE_AT,
            EMAIL_NO_USERNAME,
            EMAIL_SPACES,
            EMAIL_SPECIAL_CHARS,
            EMAIL_ONLY_DOMAIN,
            EMAIL_EMPTY
    })
    @ParameterizedTest(name = "Регистрация невозможна с невалидным email {0}")
    @DisplayName("Регистрация невозможна с невалидным email")
    void registerWithInvalidEmailTest(String email) {
        mainPage
                .openMainPage()
                .openLoginPage();
        loginPage
                .checkLoginPageOpened()
                .setEmail(email)
                .setPassword(testData.validPassword)
                .setCheckbox()
                .checkSubmitBtn(false);
    }

    @ValueSource(strings = {
            PASSWORD_TOO_SHORT,
            PASSWORD_EMPTY,
            PASSWORD_ONE_CHAR,
            PASSWORD_16_CHARS,
            PASSWORD_ONLY_NUMBERS,
            PASSWORD_ONLY_LETTERS,
            PASSWORD_CYRILLIC
    })
    @ParameterizedTest(name = "Регистрация невозможна с невалидным паролем {0}")
    @DisplayName("Регистрация невозможна с невалидным паролем")
    void registerWithInvalidPasswordTest(String password) {
        mainPage
                .openMainPage()
                .openLoginPage();
        loginPage
                .checkLoginPageOpened()
                .setEmail(testData.validEmail)
                .setPassword(password)
                .setCheckbox()
                .checkSubmitBtn(false);
    }

    @Test
    @Disabled
    @DisplayName("Квест бронируется и отображается на странице бронирований")
    void successfulBookingQuestTest() {
        mainPage
                .openMainPage()
                .openLoginPage();
        loginPage
                .registerUser(testData.validEmail, testData.validPassword);
        mainPage
                .checkLogin()
                .openQuestPage(questName);
        questPage
                .bookingBtnClick();
        bookingPage
                .checkBookingPageOpened(questName);

        String bookTime = bookingPage.setTime();

        bookingPage
                .setName(testData.userName)
                .setPhone(testData.userPhone)
                .setPlayersCount(personsCount)
                .setCheckbox()
                .clickBookBtn();
        myBookingsPage
                .checkMyBookingsPageOpened()
                .checkBookedQuest(questName, bookTime);
    }
}

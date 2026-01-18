package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BookingPage {
    private final SelenideElement loader = $(byText("Loading ..."));
    private final SelenideElement bookingPageSubHeader = $("h1.page-content__subtitle");
    private final SelenideElement bookingPageHeader = $(".page-content__title");
    private final SelenideElement bookingMap = $(".booking-map");
    private final SelenideElement bookingDate = $(".booking-form__section");
    private final SelenideElement nameInput = $("#name");
    private final SelenideElement phoneInput = $("#tel");
    private final SelenideElement personsInput = $("#person");
    private final SelenideElement agreementCheckbox = $(".booking-form__checkbox--agreement .custom-checkbox__icon");
    private final SelenideElement bookBtn = $(".booking-form__submit");
    private final SelenideElement todayTimeSection = $x("//fieldset[legend[contains(text(), 'Сегодня')]]//div[contains(@class, 'booking-form__date-inner-wrapper')]");
    private final SelenideElement tomorrowTimeSection = $x("//fieldset[legend[contains(text(), 'Завтра')]]//div[contains(@class, 'booking-form__date-inner-wrapper')]");

    @Step("Cтраница бронирования квеста {questName} отображается")
    public BookingPage checkBookingPageOpened(String questName) {
        loader.shouldNotBe(visible);
        bookingPageSubHeader
                .shouldBe(visible)
                .shouldHave(text("Бронирование квеста"));
        bookingPageHeader
                .shouldBe(visible)
                .shouldHave(text(questName));
        bookingMap
                .shouldBe(visible);
        return this;
    }

    @Step("Указать время бронирования")
    public BookingPage setTime(String day, String time) {
        bookingDate.scrollTo();

        switch (day) {
            case "TODAY":
                todayTimeSection.$x(".//label//span[contains(text(), '" + time + "')]").click();
                break;
            case "TOMORROW":
                tomorrowTimeSection.$x(".label//span[contains(text(), '" + time + "')]").click();
                break;
            default:
                throw new RuntimeException("Такого времени бронирования не существует");
        }
        return this;
    }

    @Step("Указать имя {userName}")
    public BookingPage setName(String userName) {
        nameInput.setValue(userName);
        return this;
    }

    @Step("Указать телефон {userPhone}")
    public BookingPage setPhone(String userPhone) {
        phoneInput.setValue(userPhone);
        return this;
    }

    @Step("Указать количество участников")
    public BookingPage setPlayersCount(String personsCount) {
        personsInput.setValue(personsCount);
        return this;
    }

    @Step("Выбрать чек-бокс согласия с правилами обработки данных")
    public BookingPage setCheckbox() {
        agreementCheckbox.scrollTo()
                .click();
        return this;
    }

    @Step("Клик по кнопке Забронировать")
    public void clickBookBtn() {
        checkBookBtn(true);
        bookBtn.click();
    }

    @Step("Состояние кнопки Забронировать - {bookBtnState}")
    public void checkBookBtn(boolean bookBtnState) {
        boolean btnState = bookBtn.isEnabled();
        Assertions.assertEquals(btnState, bookBtnState);
    }
}

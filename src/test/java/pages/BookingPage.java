package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import util.Quest;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

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
    ElementsCollection timeSlots = $$(".booking-form__date");

    @Step("Открыть страницу бронирования квеста {questName} с токеном залогиненного пользователя")
    public BookingPage openQuestPageWithLocalStorage(String token, String questName) {
        String bookingUrl = Quest.getBookingUrlByName(questName);

        open("favicon.ico");
        executeJavaScript(String.format("localStorage.setItem('escape-room-token', '%s');", token));
        open(bookingUrl);

        return this;
    }

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

//    @Step("Указать время бронирования")
//    public BookingPage setTime(String day, String time) {
//        bookingDate
//                .scrollTo();
//
//        switch (day) {
//            case "today":
//                break;
//            case "tomorrow":
//                break;
//            default:
//                System.out.println("нет такого дня");
//        }
//
//
//        timeSlots.find(byText(time))
//                .click();
//        return this;
//    }

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
        agreementCheckbox
                .scrollTo()
                .click();
        return this;
    }

    @Step("Клик по кнопке Забронировать")
    public void clickBookBtn() {
        checkBookBtn(true);
        bookBtn
                .click();
    }

    @Step("Состояние кнопки Забронировать - {bookBtnState}")
    public void checkBookBtn(boolean bookBtnState) {
        boolean btnState = bookBtn.isEnabled();
        Assertions.assertEquals(btnState, bookBtnState);
    }

    private SelenideElement getAvailableTimeSlot() {
        return timeSlots
                .filterBy(match("Has enabled input",
                        el -> el.findElement(By.cssSelector("input"))
                                .getAttribute("disabled") == null))
                .shouldHave(sizeGreaterThan(0))
                .first();
    }
}

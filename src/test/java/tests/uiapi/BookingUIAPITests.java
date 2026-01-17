package tests.uiapi;

import api.AccountApiSteps;
import api.QuestsApiSteps;
import io.qameta.allure.Feature;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.*;
import tests.TestBase;
import tests.TestData;
import util.QuestHelper;
import java.util.List;

@Tags({@Tag("all"), @Tag("uiapi"), @Tag("booking")})
@Feature("Бронирование квеста")
@DisplayName("UI+API тесты на бронирование квестов")
public class BookingUIAPITests extends TestBase {
    TestData testData = new TestData();
    BookingPage bookingPage = new BookingPage();
    MyBookingsPage myBookingsPage = new MyBookingsPage();
    QuestsApiSteps questsApiSteps = new QuestsApiSteps();
    QuestHelper questHelper = new QuestHelper();
    QuestPage questPage = new QuestPage();
    AccountApiSteps accountApiSteps = new AccountApiSteps();

    @Test
    @DisplayName("Квест бронируется и отображается на странице бронирований")
    void successfulBookingQuestTest() {
        List <QuestModel> quests = questsApiSteps.getQuestsList();
        QuestModel quest = questHelper.getRandomQuest(quests);
        String questId = quest.id();
        String questName = quest.title();
        String minimalPersonsCount = String.valueOf(quest.peopleMinMax().get(0));

        QuestBookingResponseModel questBookingInfo = questsApiSteps.getQuestInfo(questId, questName).get(0);
        questsApiSteps.checkTimeSlots(questBookingInfo, questName);

        SlotWithDay availableTimeSlot = questHelper.getFirstAvailableTimeSlotWithDay(questBookingInfo);
        String time = availableTimeSlot.time();
        String day = String.valueOf(availableTimeSlot.day());
        String token = accountApiSteps.authUser(testData.randomAuthData).token();

        questPage.openQuestPageWithLocalStorage(token, questId);
        questPage.bookingBtnClick();
        bookingPage.checkBookingPageOpened(questName)
                .setTime(day, time)
                .setName(testData.userName)
                .setPhone(testData.userPhone)
                .setPlayersCount(minimalPersonsCount)
                .setCheckbox()
                .clickBookBtn();
        myBookingsPage.checkMyBookingsPageOpened()
                .checkBookedQuest(questName, time);

        List<UserBookingResponseModel> userBookings = questsApiSteps.getUserBookings(token);
        String bookingId = questHelper.findBookingIdByQuestId(userBookings, questId);
        questsApiSteps.deleteUserBooking(token, bookingId);
        accountApiSteps.logoutUser(token);
    }
}

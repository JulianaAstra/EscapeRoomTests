package tests;

import api.AccountApiSteps;
import api.QuestsApiSteps;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.*;
import util.QuestHelper;
import java.util.List;
import static tests.TestData.*;

public class BookingTests extends TestBase {
    TestData testData = new TestData();
    BookingPage bookingPage = new BookingPage();
    MyBookingsPage myBookingsPage = new MyBookingsPage();
    QuestsApiSteps questsApiSteps = new QuestsApiSteps();
    QuestHelper questHelper = new QuestHelper();
    QuestPage questPage = new QuestPage();
    MainPage mainPage = new MainPage();
    AccountApiSteps accountApiSteps = new AccountApiSteps();

    @Test
    @DisplayName("Квест бронируется и отображается на странице бронирований")
    @Tag("booking")
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

        accountApiSteps
                .authUser();
        mainPage
                .openMainPageWithLocalStorage(REGISTERED_USER_TOKEN, questName)
                .openQuestPage(questName);

        questPage
                .bookingBtnClick();
        bookingPage
                .checkBookingPageOpened(questName)
//                .setTime(day, time)
                .setName(testData.userName)
                .setPhone(testData.userPhone)
                .setPlayersCount(minimalPersonsCount)
                .setCheckbox()
                .clickBookBtn();
        myBookingsPage
                .checkMyBookingsPageOpened()
                .checkBookedQuest(questName, time);

        List<UserBookingResponseModel> userBookings = questsApiSteps.getUserBookings(REGISTERED_USER_TOKEN);
        String bookingId = questHelper.findBookingIdByQuestId(userBookings, questId);
        questsApiSteps.deleteUserBooking(REGISTERED_USER_TOKEN, bookingId);
    }
}

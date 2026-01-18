package tests.api;

import api.AccountApiSteps;
import api.CheckApiSteps;
import api.QuestsApiSteps;
import io.qameta.allure.Feature;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.TestData;
import util.QuestHelper;
import java.math.BigDecimal;
import java.util.List;

@Tags({@Tag("all"), @Tag("api"), @Tag("booking_all"), @Tag("booking_api")})
@Feature("Бронирование квеста")
@DisplayName("API тесты на бронирование квеста")
public class BookingApiTests extends TestBase {
    AccountApiSteps accountApiSteps = new AccountApiSteps();
    QuestsApiSteps questsApiSteps = new QuestsApiSteps();
    QuestHelper questHelper = new QuestHelper();
    CheckApiSteps checkApiSteps = new CheckApiSteps();

    @Test
    @DisplayName("Успешное бронирование квеста авторизованным пользователем")
    void successfulBookingTest() {
        TestData testData = new TestData();
        AuthBodyModel validAuthData = testData.randomAuthData;
        String contactPerson = testData.userName;
        String phone = testData.userPhone;
        Boolean withChildren = true;

        String token = accountApiSteps.getSuccessfulAuthUserBody(validAuthData, "Авторизация пользователя")
                .token();

        List<QuestModel> quests = questsApiSteps.getQuestsList();
        QuestModel quest = questHelper.getRandomQuest(quests);
        String questId = quest.id();
        String questName = quest.title();

        QuestBookingResponseModel questBookingInfo = questsApiSteps.getQuestInfo(questId, questName).get(0);
        String placeId = questBookingInfo.id();
        String address = questBookingInfo.location().address();

        questsApiSteps.checkTimeSlots(questBookingInfo, questName);
        SlotWithDay availableTimeSlot = questHelper.getFirstAvailableTimeSlotWithDay(questBookingInfo);
        String time = availableTimeSlot.time();
        String day = String.valueOf(availableTimeSlot.day()).toLowerCase();
        Integer minimalPersonsCount = quest.peopleMinMax().get(0);

        QuestBookingBodyModel bookingInfo = new QuestBookingBodyModel(
                day,
                time,
                contactPerson,
                phone,
                withChildren,
                minimalPersonsCount,
                placeId
        );

        BookingQuestResponseModel responseBody = questsApiSteps.getSuccessfulBookingBody(token, bookingInfo, questId, questName, "Бронирование квеста");

        checkApiSteps.checkBodyValue(responseBody.date(), day, "date");
        checkApiSteps.checkBodyValue(responseBody.time(), time, "time");
        checkApiSteps.checkBodyValue(responseBody.contactPerson(), contactPerson, "contactPerson");
        checkApiSteps.checkBodyValue(responseBody.phone(), phone, "phone");
        checkApiSteps.checkBodyValue(responseBody.location().address(), address, "address");
        checkApiSteps.checkBodyValue(responseBody.withChildren(), withChildren, "withChildren");
        checkApiSteps.checkBodyValue(responseBody.peopleCount(), minimalPersonsCount, "peopleCount");
        checkApiSteps.checkBodyValue(responseBody.quest().id(), questId, "questId");
        checkApiSteps.checkBodyValue(responseBody.quest().title(), questName, "title");
    }


    // тест2 с не существующим айди квеста 404 Quest with id aba664c3-bdf3-4fb3-b8f3-42e007864bbf not found
    @Test
    @DisplayName("404 при попытке бронирования квеста с несуществующим id")
    void notExistQuestIdBookingTest() {

    }
}

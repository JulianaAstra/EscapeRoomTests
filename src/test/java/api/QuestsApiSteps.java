package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.*;
import org.junit.jupiter.api.Assertions;
import util.QuestHelper;
import java.util.List;
import java.util.Objects;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static specs.Spec.responseSpec;

public class QuestsApiSteps {
    QuestRequestsSteps questRequests = new QuestRequestsSteps();
    CheckApiSteps checkApiSteps = new CheckApiSteps();

    @Step("Получить список квестов")
    public List <QuestModel> getQuestsList() {
        return questRequests.makeGetQuestListRequest()
                .then()
                .spec(responseSpec(200))
                .extract()
                .jsonPath()
                .getList("", QuestModel.class);
    }

    @Step("Получить тело ответа на успешный запрос POST quest/{questId}/booking {requestName}")
    public BookingQuestResponseModel getSuccessfulBookingBody(String token, QuestBookingBodyModel bookingData, String questId, String questName, String requestName) {
        return await().atMost(20, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> {
                            Response response = questRequests.makeBookQuestRequest(token, bookingData, questId, questName);
                            checkApiSteps.checkSuccessfulRequest(response, 200, "schemas/booking-schema.json", requestName);

                            BookingQuestResponseModel checkedResponse = response.then()
                                    .extract()
                                    .as(BookingQuestResponseModel.class);

                            return checkedResponse.id() != null ? checkedResponse : null;
                        },
                        Objects::nonNull);
    }

    @Step("Получить информацию о бронировании квеста {questName}")
    public List<QuestBookingResponseModel> getQuestInfo(String questId, String questName) {
        return questRequests.makeGetQuestBookingInfoRequest(questId, questName)
                .then()
                .spec(responseSpec(200))
                .extract()
                .jsonPath()
                .getList("", QuestBookingResponseModel.class);
    }

    @Step("Получить список бронирований пользователя")
    public List<UserBookingResponseModel> getUserBookings(String token, String userEmail) {
        return questRequests.makeGetUserBookingsRequest(token, userEmail)
                .then()
                .spec(responseSpec(200))
                .extract()
                .jsonPath()
                .getList("", UserBookingResponseModel.class);
    }

    @Step("Удалить бронирование квеста {questName} пользователя")
    public void deleteUserBooking(String token, String bookingId, String questName) {
        questRequests.makeDeleteBookingRequest(token, bookingId)
                .then()
                .spec(responseSpec(204));
    }

    @Step("Проверка доступных слотов для бронирования квеста {questName}")
    public void checkTimeSlots(QuestBookingResponseModel questBookingInfo, String questName) {
        QuestHelper questHelper = new QuestHelper();

        if (!questHelper.hasAvailableTimeSlots(questBookingInfo)) {
            throw new RuntimeException("У выбранного квеста нет доступных слотов");
        }
    }

    @Step("Получить информацию о квесте по id: {questId}")
    private QuestModel getQuestBaseInfo(String questId) {
        return questRequests.makeGetQuestInfoRequest(questId)
                .then()
                .spec(responseSpec(200))
                .extract().as(QuestModel.class);
    }

    @Step("Проверка тематики {expectedQuestType} по id квеста {questId}")
    private void checkQuestType(String expectedQuestType, String questId) {
        QuestModel quest = getQuestBaseInfo(questId);
        String questType = quest.type();
        Assertions.assertEquals(questType, expectedQuestType);
    }

    @Step("Проверка всех квестов в списке на соответствие тематике {expectedQuestType}")
    public void checkQuestsTypeInList(List<String> questsIds, String expectedQuestType) {
        for (String questsId: questsIds) {
            checkQuestType(expectedQuestType, questsId);
        }
    }
}

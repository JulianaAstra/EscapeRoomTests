package api;

import io.qameta.allure.Step;
import models.*;
import org.junit.jupiter.api.Assertions;
import util.QuestHelper;
import java.util.List;
import static io.restassured.RestAssured.given;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec;

public class QuestsApiSteps {
    @Step("Получить список квестов")
    public List <QuestModel> getQuestsList() {
        return given()
                .spec(requestSpec)
                .when()
                .get("quest")
                .then()
                .spec(responseSpec(200))
                .extract()
                .jsonPath()
                .getList("", QuestModel.class);
    }

    @Step("Получить информацию о бронировании квеста {questName}")
    public List<QuestBookingResponseModel> getQuestInfo(String questId, String questName) {
        return given()
                .spec(requestSpec)
                .when()
                .get("quest/" + questId + "/booking")
                .then()
                .spec(responseSpec(200))
                .extract()
                .jsonPath()
                .getList("", QuestBookingResponseModel.class);
    }

    @Step("Получить список бронирований пользователя")
    public List<UserBookingResponseModel> getUserBookings(String token) {
        return given()
                .spec(requestSpec)
                .header("X-Token", token)
                .when()
                .get("reservation")
                .then()
                .spec(responseSpec(200))
                .extract()
                .jsonPath()
                .getList("", UserBookingResponseModel.class);
    }

    @Step("Удалить бронирование квеста {questName} пользователя")
    public void deleteUserBooking(String token, String bookingId) {
        given()
                .spec(requestSpec)
                .header("X-Token", token)
                .when()
                .delete("reservation/" + bookingId)
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
        return given()
                .spec(requestSpec)
                .when()
                .get("quest/" + questId)
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
